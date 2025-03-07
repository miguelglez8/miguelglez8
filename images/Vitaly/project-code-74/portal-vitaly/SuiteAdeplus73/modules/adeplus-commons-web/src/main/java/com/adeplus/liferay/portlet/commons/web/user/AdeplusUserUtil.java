package com.adeplus.liferay.portlet.commons.web.user;

import com.adeplus.liferay.portlet.commons.web.audit.AdeplusAuditUtil;
import com.adeplus.liferay.portlet.commons.web.bean.AppPermission;
import com.adeplus.liferay.portlet.commons.web.constants.AdeplusAuditPortletKeys;
import com.adeplus.liferay.portlet.commons.web.constants.AdeplusCommonsPortletKeys;
import com.adeplus.liferay.portlet.commons.web.keycloak.AdeplusKeycloakUtil;
import com.adeplus.liferay.portlet.commons.web.keycloak.Realm;
import com.adeplus.liferay.portlet.commons.web.role.AdeplusRoleUtil;
import com.adeplus.liferay.portlet.mailing.web.mail.UserMailing;
import com.adeplus.liferay.portlet.suite.manager.model.Application;
import com.adeplus.liferay.portlet.suite.manager.model.Comp;
import com.adeplus.liferay.portlet.suite.manager.model.UserApplication;
import com.adeplus.liferay.portlet.suite.manager.model.UserCompany;
import com.adeplus.liferay.portlet.suite.manager.service.*;
import com.adeplus.liferay.portlet.suite.manager.service.persistence.UserCompanyPK;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Contact;
import com.liferay.portal.kernel.model.PasswordPolicy;
import com.liferay.portal.kernel.model.Phone;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.*;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import org.keycloak.representations.idm.UserRepresentation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class AdeplusUserUtil {

    private static final int PASSWORD_LENGTH = 8;

    private static Log _log = LogFactoryUtil.getLog(AdeplusUserUtil.class);

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");


    private static final String REALNAME 	= PrefsPropsUtil.getString("preving.keycloak.configuration.realname");
    private static final String URL 		= PrefsPropsUtil.getString("preving.keycloak.configuration.url");
    private static final String USERNAME 	= PrefsPropsUtil.getString("preving.keycloak.configuration.username");
    private static final String PASS 		= PrefsPropsUtil.getString("preving.keycloak.configuration.pass");
    private static final String CLIENT_ID 	= PrefsPropsUtil.getString("preving.keycloak.configuration.clientid");
    private static final String SECRET 		= PrefsPropsUtil.getString("preving.keycloak.configuration.secret");


    public static User createUser(long creatorUserId, long companyId, String portalURL, String pathMain,
                                  String name, String lastName, String nif, String email, String passwordRandon, String phone, boolean isAdmin,
                                  String dateEnd, long compId, Locale locale){

        //validar limite de usuarios/admins por app
        //if(!isValidLimitsApp(companyId,compId,isAdmin, null)) return null;

        User user = getUserByEmail(companyId, email);

        if(Validator.isNull(user)){
            user = createNewUserLiferay(creatorUserId, companyId, portalURL, pathMain, name, lastName, email, nif, passwordRandon, dateEnd, locale);
        }

        if(Validator.isNotNull(user)){

            //Add the comp to user in table usercompany.
            AdeplusUserUtil.setUserCompany(user, compId);

            //Set the password policy to user.
            String passwordPolicyName = PrefsPropsUtil.getString(AdeplusCommonsPortletKeys.USER_PASSWORD_POLICY);
            PasswordPolicy passwordPolicy = PasswordPolicyLocalServiceUtil.fetchPasswordPolicy(user.getCompanyId(), passwordPolicyName);
            UserLocalServiceUtil.addPasswordPolicyUsers(passwordPolicy.getPasswordPolicyId(), new long[]{user.getUserId()});

            //Set admin role to user.
            if(isAdmin){
                AdeplusRoleUtil.setCompanyAdminRole(companyId, user);
            }

            AdeplusRoleUtil.setCompanyUserRole(companyId, user);

            //Add the comp to user in table usercompany.
            UserCompanyLocalServiceUtil.updateUserCompany(user.getUserId(), compId, name, lastName, nif, email, phone, isAdmin, getDateFromString(dateEnd));

            //Set the phone.
            saveUserPhone(user, phone);

        }

        return user;
    }

    private static User createNewUserLiferay(long creatorUserId, long companyId, String portalURL, String pathMain,
                                             String name, String lastName, String email, String nif, String password,
                                             String endDate, Locale locale){

        User user = null;

        String screenName = nif;
        if(Validator.isBlank(nif)){
            Date now = new Date();
            screenName = String.valueOf(now.getTime());
        }

        ServiceContext serviceContext = new ServiceContext();
        serviceContext.setPortalURL(portalURL);
        serviceContext.setPathMain(pathMain);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());

        try {

            user = UserLocalServiceUtil.addUser(
                    creatorUserId,
                    companyId,
                    false,
                    password,
                    password,
                    true,
                    nif,
                    email,
                    0,
                    null,
                    locale,
                    name,
                    "",
                    lastName,
                    0,
                    0,
                    true,
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DATE),
                    calendar.get(Calendar.YEAR),
                    "",
                    new long[]{},
                    new long[]{},
                    new long[]{},
                    new long[]{},
                    false,
                    serviceContext
            );

            // No validate email.
            user.setEmailAddressVerified(true);

            UserLocalServiceUtil.updateUser(user);

            //Create user in keycloak
            // AdeplusKeycloakUtil.createUserKeycloak(name, email);

            AdeplusAuditUtil.addAudit(user.getCompanyId(), user.getGroupId(), creatorUserId, AdeplusAuditPortletKeys.AUDIT_USER_CREATE, "Create user "+ user.getEmailAddress() +".");

        }catch (Exception e) {
            _log.error(e);
        }

        return user;
    }

    private static boolean isLimitUserOrAdminByApp(long compId, AppPermission appPermission, boolean isSearchAdmin){
        //Comprobar si son usuarios en que apps
        Map<Long,Long> usersMap = new HashMap<Long, Long>();
        List<UserCompany>  usersUsers = null;
        if(isSearchAdmin){
            if(!appPermission.getApplication().getLimitAdmins()
                    &&  appPermission.getApplication().getLimitNumAdmins() == 0) return true;
            usersUsers = UserCompanyLocalServiceUtil.getAdminUsersFromCompany(compId);
        }else{
            if(!appPermission.getApplication().getLimitUsers()
                    &&  appPermission.getApplication().getLimitNumUsers() == 0) return true;
            usersUsers = UserCompanyLocalServiceUtil.getOnlyUsersFromCompany(compId);
        }


        List<UserApplication> apps = null;
        long countTotal = 0;
        long usersLong= 0;

        boolean isPermit = true;
        //if(!appPermission.getApplication().getLimitUsers()) return true;



        for( UserCompany u : usersUsers){
            apps = UserApplicationLocalServiceUtil.getApplicationsFromUser(u.getUserId(), compId);
            for(UserApplication ua : apps){
                if( ua.getApplicationId() == appPermission.getApplication().getApplicationId()){
                    if(usersMap.containsKey(ua.getApplicationId())){
                        usersLong =  (usersMap.get(ua.getApplicationId())).longValue() ;
                        usersLong++;
                        usersMap.put(ua.getApplicationId(), usersLong);
                    }else{
                        usersMap.put(ua.getApplicationId(), 1L);
                    }
                }
            }
        }

        if(isSearchAdmin){
            countTotal =  appPermission.getApplication().getLimitNumAdmins();
        }else {
            countTotal = appPermission.getApplication().getLimitNumUsers();
        }

        _log.info("appPermission.getApplication().getApplicationId(): " + appPermission.getApplication().getApplicationId());
        _log.info("isSearchAdmin: " + isSearchAdmin + " / countTotal: " + countTotal);
        _log.info("usersMap.get(appPermission.getApplication().getApplicationId()) : "
                + usersMap.get(appPermission.getApplication().getApplicationId()));

        if (usersUsers.size() > 0 &&  Validator.isNotNull(usersMap.get(appPermission.getApplication().getApplicationId()))  && usersMap.get(appPermission.getApplication().getApplicationId()) >= countTotal) {
            return true;
        }

        return false;
    }


    // typeUser : 0: all, 1: admin, 2 : user
    public static boolean isEnabledSelectApp(long compId, Application app, int typeUser){

        boolean isEnabledUsers = true;
        boolean isEnabledAdmin = true;



        AppPermission appPermission = null;
        if(!app.getLimitUsers() && (typeUser == 2 ||  typeUser == 0)){
            _log.info("USER : " + app.getName() + " tiene limite: " + app.getLimitUsers() );
            if(app.getLimitNumUsers() == 0){
                isEnabledUsers = false;
                _log.info("USER : " + app.getName() + " es cero");
            }else{
                appPermission = new AppPermission(app,true, 0);
                if(isLimitUserOrAdminByApp(compId,appPermission,false)){ // supera o iguala en num de usuarios dentro de la company de esa app
                    isEnabledUsers = false;
                    _log.info("USER : " + app.getName() + " es supera / iguala");
                }
            }
        }
        if(!app.getLimitAdmins() && (typeUser == 1 ||  typeUser == 0)){
            _log.info("ADMIN : " + app.getName() + " tiene limite: " + app.getLimitUsers() );
            if(app.getLimitNumAdmins() == 0){
                isEnabledAdmin = false;
                _log.info("ADMIN : " + app.getName() + " es cero");
            }else{
                appPermission = new AppPermission(app,true, 0);
                if(isLimitUserOrAdminByApp(compId,appPermission,true)){// supera o iguala en num de admins dentro de la company de esa app
                    isEnabledAdmin = false;
                    _log.info("ADMIN : " + app.getName() + " es supera / iguala");
                }
            }
        }

        _log.info("isEnabledAdmin: " + isEnabledAdmin + " / isEnabledUsers: " + isEnabledUsers);

        if(typeUser == 0 && !isEnabledAdmin && !isEnabledUsers) return false;
        if(typeUser == 1 && !isEnabledAdmin) return false;
        if(typeUser == 2 && !isEnabledUsers) return false;

        return true;
    }

    public static String isValidLimitsApp(long companyId, long compId,boolean isAdmin, User user, List<AppPermission> appPermissions){
        //validar limites
        _log.info("VALIDANDO LIMITES");

        boolean isOk = true;
        String result = "";
        //long maxAdmin = UserCompanyLocalServiceUtil.getCountAdminCompany(compId);
        //long maxUser = UserCompanyLocalServiceUtil.getCountUserCompany(compId);

        Map<Long,Long> adminsMap = new HashMap<Long, Long>();
        Map<Long,Long> usersMap = new HashMap<Long, Long>();
        long adminsLong = 0;
        long usersLong = 0;

        List<UserApplication> apps = null;


        //Comprobar si son usuarios en que apps
        List<UserCompany>  usersUsers = UserCompanyLocalServiceUtil.getOnlyUsersFromCompany(compId);

        for( UserCompany u : usersUsers){
            apps = UserApplicationLocalServiceUtil.getApplicationsFromUser(u.getUserId(), compId);
            for(UserApplication ua : apps){
                for(AppPermission appPermission : appPermissions){
                    if( ua.getApplicationId() == appPermission.getApplication().getApplicationId()){
                        if(usersMap.containsKey(ua.getApplicationId())){
                            usersLong =  (usersMap.get(ua.getApplicationId())).longValue() ;
                            usersLong++;
                            usersMap.put(ua.getApplicationId(), usersLong);
                        }else{
                            usersMap.put(ua.getApplicationId(), 1L);
                        }
                        //soy yo uno de los usuarios
                        if(Validator.isNotNull(user)  && u.getEmail().equals(user.getEmailAddress())){
                            usersLong =  (usersMap.get(ua.getApplicationId())).longValue() ;
                            usersLong--;
                            usersMap.put(ua.getApplicationId(), usersLong);
                        }
                    }
                }
            }
        }


        //Comprobar si son administradores en que apps
        List<UserCompany>  usersAdmins = UserCompanyLocalServiceUtil.getAdminUsersFromCompany(compId);
        for( UserCompany u : usersAdmins){
            apps = UserApplicationLocalServiceUtil.getApplicationsFromUser(u.getUserId(), compId);
            for(UserApplication ua : apps){
                for(AppPermission appPermission : appPermissions){
                    if( ua.getApplicationId() == appPermission.getApplication().getApplicationId()){
                        if(adminsMap.containsKey(ua.getApplicationId())){
                            adminsLong =  (adminsMap.get(ua.getApplicationId())).longValue() ;
                            adminsLong++;
                            adminsMap.put(ua.getApplicationId(), adminsLong);
                        }else{
                            adminsMap.put(ua.getApplicationId(), 1L);
                        }
                        // soy yo el admin
                        if(Validator.isNotNull(user)  && u.getEmail().equals(user.getEmailAddress())){
                            adminsLong =  (adminsMap.get(ua.getApplicationId())).longValue() ;
                            adminsLong--;
                            adminsMap.put(ua.getApplicationId(), adminsLong);
                        }
                    }
                }
            }
        }

        for(AppPermission app : appPermissions){
            if(app.isHasPermission()) {
                if (isAdmin && !app.getApplication().getLimitAdmins()) { // false significa limitado, true ilimitado

                    /*_log.info("adminsMap.get(app.getApplication().getApplicationId()) : " + adminsMap.get(app.getApplication().getApplicationId()) );
                    _log.info("app.getApplication().getLimitNumAdmins(): " + app.getApplication().getLimitNumAdmins() );*/

                    if(Validator.isNotNull(adminsMap.get(app.getApplication().getApplicationId()))
                            &&  (adminsMap.get(app.getApplication().getApplicationId()) >= app.getApplication().getLimitNumAdmins() || app.getApplication().getLimitNumAdmins() == 0 )){
                        _log.info("LIMITE SUPERADO - adminitradores - " + app.getApplication().getName());
                        isOk = false;
                        result =  "ADMIN: " + app.getApplication().getName();
                        break;
                    }
                }else if(!isAdmin && !app.getApplication().getLimitUsers() ){

                    _log.info("usersMap.get(app.getApplication().getApplicationId()) : 111 " + usersMap.get(app.getApplication().getApplicationId()));
                    _log.info("app.getApplication().getLimitNumUsers(): " + app.getApplication().getLimitNumUsers());

                    if(app.getApplication().getLimitNumUsers() == 0  || (Validator.isNotNull(usersMap.get(app.getApplication().getApplicationId()) )
                            && (usersMap.get(app.getApplication().getApplicationId()) >= app.getApplication().getLimitNumUsers())) ){
                        _log.info("LIMITE SUPERADO - Usuarios - " +  app.getApplication().getName() );
                        isOk = false;

                        result = "USER: " + app.getApplication().getName();

                        break;
                    }
                }
            }
        }

        return result;
        //return isOk;

    }


    public static User updateUser( long companyId, long userId, long compId, String name, String lastName, String nif, String email, String phone,
                                   boolean isAdmin, String dateEnd, Locale locale){


        _log.info("update user  !!! ");
        _log.info("isAdmin: " + isAdmin) ;



        String emailOld = "";
        User user = UserLocalServiceUtil.fetchUser(userId);

        //validar limite de usuarios/admins por app
        //if(!isValidLimitsApp( companyId, compId,  isAdmin, user,appPermissionList)) return null;

        if(Validator.isNotNull(user)) {

            UserCompanyLocalServiceUtil.updateUserCompany(user.getUserId(), compId, name, lastName, nif, email, phone, isAdmin, getDateFromString(dateEnd));


            Date endDateFormat = null;
            if(!Validator.isBlank(dateEnd)) {
                try {
                    endDateFormat = dateFormat.parse(dateEnd);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                if (Validator.isNotNull(endDateFormat) && endDateFormat.before(new Date()) && user.isActive()) {
                    //Send mail to deleted user.
                    UserMailing.userDelete(user);

                } else if(Validator.isBlank(dateEnd)) {
                    _log.debug("user " + user.getEmailAddress());
                    _log.debug("dateEnd " + dateEnd);

                    user.setStatus(WorkflowConstants.STATUS_APPROVED);
                    _log.debug("STATUS_APPROVED " + WorkflowConstants.STATUS_APPROVED);
                    UserLocalServiceUtil.updateUser(user);
                }
            }

            //Set admin role to user.
            if (isAdmin) {

                AdeplusRoleUtil.setCompanyAdminRole(companyId, user);

            } else if (!UserCompanyLocalServiceUtil.isUserAdminInActiveComp(user.getUserId())) {

                AdeplusRoleUtil.deleteCompanyAdminRole(companyId, user);
                AdeplusRoleUtil.setCompanyUserRole(companyId, user);

            }

            //Update user in keycloak
            _log.info("START AdeplusKeycloakUtil.updateUserKeycloak()");
            AdeplusKeycloakUtil.updateUserKeycloak(user.getUserId(), compId, name, lastName, nif, email, phone, isAdmin, getDateFromString(dateEnd));
            _log.info("END AdeplusKeycloakUtil.updateUserKeycloak()");
            AdeplusAuditUtil.addAudit(user.getCompanyId(), user.getGroupId(), 0, AdeplusAuditPortletKeys.AUDIT_USER_UPDATE, "Updated user " + user.getEmailAddress() + ".");

        }

        return user;
    }


    public static void createUserKeycloak(long companyId,
                                          User userLiferay,
                                          String nif,
                                          String phone,
                                          String pass,
                                          String endDate,
                                          boolean isAdmin,
                                          List<AppPermission> appPermissionList){


        _log.info("createUserKeycloak: " + userLiferay.getUserId());
        AdeplusKeycloakUtil.operationUser(true,
                companyId, userLiferay.getUserId(), userLiferay.getEmailAddress(), userLiferay.getFirstName(), userLiferay.getLastName(),
                nif, phone, pass, endDate, isAdmin, appPermissionList);

    }


    public static void passwordUpdate(long userId, String newPass) {
        UserRepresentation user = new UserRepresentation();
        String email;
        try {
            email = UserLocalServiceUtil.getUserById(userId).getEmailAddress();
            user.setEmail(email);
            Realm keyCloack = new Realm(URL, REALNAME, USERNAME, PASS, SECRET, CLIENT_ID);
            if (keyCloack.isExistUser(user)) {
                keyCloack.setUserPass(email, newPass);
            }
            keyCloack.close();
        } catch (PortalException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }




    private static void saveUserPhone(User user, String phone){

        try {

            if(!Validator.isBlank(phone)){

                List<Phone> phones = user.getPhones();
                if(Validator.isNotNull(phones) && phones.size() > 0){
                    //Update.
                    Phone pho = phones.get(0);

                    pho.setNumber(phone);

                    PhoneLocalServiceUtil.updatePhone(pho);

                } else {
                    //Create new.
                    Phone pho = PhoneLocalServiceUtil.createPhone(CounterLocalServiceUtil.increment(Phone.class.getName()));

                    pho.setCompanyId(user.getCompanyId());
                    pho.setNumber(phone);
                    pho.setUserId(user.getUserId());
                    pho.setUserName(user.getFullName());
                    pho.setTypeId(11006);
                    pho.setPrimary(true);
                    pho.setClassName(Contact.class.getName());
                    pho.setClassPK(user.getContactId());

                    PhoneLocalServiceUtil.updatePhone(pho);

                }
            }

        } catch (Exception e) {
            _log.error(e);
        }

    }

    public static void setUserCompany(User user, long compId){
        if(Validator.isNotNull(user) && compId > 0){
            UserCompany userCompany = UserCompanyLocalServiceUtil.getUserCompany(user.getUserId(), compId);
            if(Validator.isNull(userCompany)){
                UserCompanyPK pk = new UserCompanyPK(user.getUserId(), compId);
                UserCompany uc = UserCompanyLocalServiceUtil.createUserCompany(pk);
                UserCompanyLocalServiceUtil.addUserCompany(uc);

                //AdeplusAuditUtil.addAudit(user.getCompanyId(), user.getGroupId(), user.getUserId(), AdeplusAuditPortletKeys.AUDIT_USER_ADD_COMPANY, "Add user "+ user.getEmailAddress() +" to company " + compId + ".");
            }
        }
    }

    public static void setUserEndDate(User user, long compId, Date endDate){
        UserCompany userCompany = UserCompanyLocalServiceUtil.getUserCompany(user.getUserId(), compId);
        if(Validator.isNotNull(userCompany)){
            userCompany.setUserEndDate(endDate);
            UserCompanyLocalServiceUtil.updateUserCompany(userCompany);
        }
    }

    public static void remindPassword(User user, long compId){
        try {



            ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();
            serviceContext.setPlid(2);

            UserLocalServiceUtil.sendPassword(user.getCompanyId(), user.getEmailAddress(), null, null, null, null,serviceContext);

            //UserMailing.userRemindPassword(user, compId);

            AdeplusAuditUtil.addAudit(user.getCompanyId(), user.getGroupId(), 0, AdeplusAuditPortletKeys.AUDIT_USER_REMIND_PASSWORD, "Remind password to "+ user.getEmailAddress() +".");

            if(_log.isDebugEnabled()) {
                _log.debug("Remind password to user: " + user + ".");
            }

        } catch (PortalException e) {
            _log.error("Error reminding password.");
        }
    }

    private static int len = 8; //longitud de la clave
    private static String charsDigits = "0123456789";
    private static String charsLowercase = "abcdefghijklmnopqrstuvwxyz";
    private static String charsUppercase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static String chars = charsDigits + charsLowercase + charsUppercase;
    private static boolean hasDigits = false, hasLowercase = false, hasUppercase = false;
    private static Random rnd = new Random();
    private static StringBuilder sb = null;
    private static int[] arrPos = null;

    public static String generatePasswordRandon(){
        sb = new StringBuilder(len);
        positions(3); // 3 pos al azar
        int typeChar = 0; // 0: digit, 1: Upper, 2 : lower
        for (int i = 0; i < len; i++){
            if( arrPos[i] != 0 && typeChar == 0){
                sb.append(charsLowercase.charAt(rnd.nextInt(charsLowercase.length()))); //al menos siempre una minuscula
                typeChar++;
            }else if(arrPos[i] != 0 && typeChar == 1){
                sb.append(charsUppercase.charAt(rnd.nextInt(charsUppercase.length()))); //siempre al menos una mayuscula
                typeChar++;
            }else if( arrPos[i] != 0 && typeChar == 2){
                sb.append(charsDigits.charAt(rnd.nextInt(charsDigits.length()))); //siempre al menos un n�mero
                typeChar++;
            }else{
                sb.append(chars.charAt(rnd.nextInt(chars.length()))); // uno cualquiera
            }
        }
        return sb.toString();
    }
    //Obtener posiciones donde ira un obligatorio; al menos un digito, una minuscula y una mayuscula
    private static void positions(int countPos){
        int pos = -1;
        arrPos = new int[len];
        while(countPos > 0){
            pos = rnd.nextInt(len);
            if(arrPos[pos] == 0){
                arrPos[pos] = 1;
                countPos--;
            }
        }
    }

    public static String old_generatePasswordRandon(){
        String password = "wGAs@8pL1!b0l";

        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'

        String charsUpper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String charsLower = "abcdefghijklmnopqrstuvwxyz";
        String charsDigit = "0123456789";

        try {
            Random random = new Random();

            password = random.ints(leftLimit, rightLimit + 1)
                    .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                    .limit(PASSWORD_LENGTH)
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString();


            boolean upperCase = false, lowerCase = false, hasNumber = false;
            for(int i=0; i<password.length();i++) {
                if(Character.isUpperCase(password.charAt(i))){
                    upperCase = true;
                }
                if(Character.isLowerCase(password.charAt(i))){
                    lowerCase = true;
                }
                if(Validator.isNumber(String.valueOf(password.charAt(i)))){
                    hasNumber = true;
                }
            }
            if(!upperCase){
                password += (charsUpper.charAt(random.nextInt(charsUpper.length())));
            }
            if(!lowerCase){
                password += (charsLower.charAt(random.nextInt(charsLower.length())));
            }
            if(!hasNumber){
                password += (charsDigit.charAt(random.nextInt(charsLower.length())));
            }
        }catch(Exception e) {
            _log.error(e);
            return null;
        }

        if(_log.isDebugEnabled()) {
            _log.debug("Create password to user: " + password + ".");
        }

        return password;
    }

    public static Date getDateFromString(String date){

        try {

            if(!Validator.isBlank(date)){
                return dateFormat.parse(date);
            }

        } catch (ParseException e) {
            _log.error("No se ha indicado una fecha válida.");
        }
        return null;
    }

    public static User getUserByEmail(long companyId, String email){
        User userByEmailAddress = null;
        try {
            userByEmailAddress = UserLocalServiceUtil.fetchUserByEmailAddress(companyId, email);
        } catch (Exception e) {
            _log.error(e.getMessage());
        }
        return userByEmailAddress;
    }

    public static void inactiveAllUsersFromCompany(long compId){

        Date now = new Date();

        List<UserCompany> usersFromCompany = UserCompanyLocalServiceUtil.getUsersFromCompany(compId);

        for(UserCompany userCompany:usersFromCompany){
            User user = UserLocalServiceUtil.fetchUser(userCompany.getUserId());
            if(Validator.isNotNull(user) && (Validator.isNull(userCompany.getUserEndDate()) || userCompany.getUserEndDate().after(now))) {

                //Set date end in the usercompany table.
                setUserEndDate(user, userCompany.getCompId(), now);

                //disableUser(user);

            }
        }

        AdeplusAuditUtil.addAudit(0, 0, 0, AdeplusAuditPortletKeys.AUDIT_USER_INACTIVE_COMPANY, "Inactive users for company "+ compId +".");

    }
    public static void setEndDateAllUsersFromCompany(long compId, Date endDate){

        Date now = new Date();

        List<UserCompany> usersFromCompany = UserCompanyLocalServiceUtil.getUsersFromCompany(compId);

        for(UserCompany userCompany:usersFromCompany){
            User user = UserLocalServiceUtil.fetchUser(userCompany.getUserId());
            if(Validator.isNotNull(user) && (Validator.isNull(userCompany.getUserEndDate()) || userCompany.getUserEndDate().after(now))) {

                //Set date end in the usercompany table.
                setUserEndDate(user, compId, endDate);

            }
        }

        AdeplusAuditUtil.addAudit(0, 0, 0, AdeplusAuditPortletKeys.AUDIT_USER_INACTIVE_COMPANY, "Inactive users for company "+ compId +".");

    }

    public static void sendMailInactiveAllUsersFromCompany(long compId){

        Date now = new Date();

        List<UserCompany> usersFromCompany = UserCompanyLocalServiceUtil.getUsersFromCompany(compId);

        for(UserCompany userCompany:usersFromCompany){
            User user = UserLocalServiceUtil.fetchUser(userCompany.getUserId());
            if(Validator.isNotNull(user) && (Validator.isNull(userCompany.getUserEndDate()) || userCompany.getUserEndDate().after(now))) {

                //Send mail to deleted user.
                UserMailing.userDelete(user);

            }
        }

        AdeplusAuditUtil.addAudit(0, 0, 0, AdeplusAuditPortletKeys.AUDIT_USER_INACTIVE_COMPANY, "Inactive users for company "+ compId +".");

    }

    public static List<Comp> getCompaniesActiveFromUser(long userId){

        List<Comp> result = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        calendar.add(Calendar.DAY_OF_MONTH, -30);

        if(userId > 0) {
            Comp comp = null;
            List<UserCompany> userCompanyList = UserCompanyLocalServiceUtil.getCompaniesFromUser(userId);

            for (UserCompany uc : userCompanyList) {

                try {
                    comp = CompLocalServiceUtil.getComp(uc.getCompId());
                } catch (PortalException e) {
                    comp=null;
                }
                if (Validator.isNotNull(comp)
                        && (Validator.isNull(comp.getDeleteDate()) || (Validator.isNotNull(comp.getDeleteDate()) && comp.getDeleteDate().after(calendar.getTime())))
                        && (Validator.isNull(uc.getUserEndDate()) || (Validator.isNotNull(uc.getUserEndDate()) && uc.getUserEndDate().after(calendar.getTime())))
                ) {
                    result.add(comp);
                }
            }
        }

        return result;
    }



    public static List<User> getUsersFromCompany(long compId){

        List<User> userList = new ArrayList<>();

        if(compId > 0) {

            List<UserCompany> usersFromCompany = UserCompanyLocalServiceUtil.getUsersFromCompany(compId);

            for (UserCompany uc : usersFromCompany) {

                User user = UserLocalServiceUtil.fetchUser(uc.getUserId());
                if(Validator.isNotNull(user)){
                    userList.add(user);
                }
            }
        }
        return userList;
    }
}

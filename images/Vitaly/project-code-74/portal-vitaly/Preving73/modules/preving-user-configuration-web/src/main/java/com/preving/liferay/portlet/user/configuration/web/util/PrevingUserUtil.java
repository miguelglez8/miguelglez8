package com.preving.liferay.portlet.user.configuration.web.util;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Contact;
import com.liferay.portal.kernel.model.PasswordPolicy;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.service.*;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.preving.liferay.portlet.calendar.manager.model.UserData;
import com.preving.liferay.portlet.calendar.manager.service.UserDataLocalServiceUtil;
import com.preving.liferay.portlet.mailing.web.mail.UserMailing;
import com.preving.liferay.portlet.user.configuration.web.constants.UserConfigurationPortletKeys;
import org.apache.commons.lang3.RandomStringUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class PrevingUserUtil {

    private static Log _log = LogFactoryUtil.getLog(PrevingUserUtil.class);

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    private static SimpleDateFormat dateFormatInput = new SimpleDateFormat("yyyy-MM-dd");

    public static User createUser(long companyId, long creatorUserId, long groupId, String portalURL, String pathMain, String firstName,
                                  String lastName,
                                  String nif, String email, String password, String jobTitle, long workCenterId, String salary,
                                  String genre, String endDate, String active, String admin, Locale locale, String strWorkCenter){




        User user = getUserByCompanyAndEmail(companyId, email);
        _log.debug("ENVIO DE WELCOME_PACK");

        //Create user if not exists.
        if(Validator.isNull(user)){

            String screenName = email.toLowerCase().replace(StringPool.AT,StringPool.DASH).replace(StringPool.PERIOD,StringPool.DASH).replace(StringPool.PERIOD,StringPool.PLUS);

            user = createUserPortal(creatorUserId, companyId, portalURL, pathMain, firstName, lastName, screenName, password, email, salary, locale, active);

            // Send success notification to user.
            //PrevingMailUtil.sendMailCreateUser(creatorUserId, user, passwordRandon);
            UserMailing.userCreateToUser(user, password);

            if(_log.isDebugEnabled()){
                _log.debug("Created new user: ");
                _log.debug("   - id : " + user.getUserId());
                _log.debug("   - name : " + user.getFullName());
                _log.debug("   - screenname : " + user.getScreenName());
                _log.debug("   - email : " + user.getEmailAddress());
                _log.debug("   - active : " + user.isActive());

            }

        }

        //Save user data
        UserData userData = PrevingUserDataUtil.createUserDataValues(companyId, groupId, user.getUserId(), nif, firstName, lastName,
                                                                        email, jobTitle, workCenterId, salary, genre,true,strWorkCenter);

        // Set Role admin or user.
        PrevingRoleUtil.setAdminRole(groupId, user.getUserId(), admin);
        PrevingRoleUtil.setUserRole(groupId, user.getUserId());

        //Password politicy
        PasswordPolicy passwordPolicy = PasswordPolicyLocalServiceUtil.fetchPasswordPolicy(user.getCompanyId(),"Password Preving");
        UserLocalServiceUtil.addPasswordPolicyUsers(passwordPolicy.getPasswordPolicyId(), new long[]{user.getUserId()});

        // Add user to group.
        GroupLocalServiceUtil.addUserGroup(user.getUserId(), groupId);

        if(_log.isDebugEnabled()) {
            _log.debug("UserData values: ");
            _log.debug("   - active : " + userData.getNif());
            _log.debug("   - email : " + userData.getEmail());
            _log.debug("   - jobTitle : " + userData.getJobTitle());
            _log.debug("   - workcenter : " + userData.getWorkCenter());
        }

        return user;
    }

    public static User editUser(long companyId, long groupId, long userId, String firstName, String lastName, String nif, String email, String jobTitle,
                                long cmbWorkCenterId, String salary, String genre, String notificationEndDate,String endDate, String active, String admin, String language, String strWorkCenter){
        User user = null;

        if(_log.isDebugEnabled()){
            _log.debug("groupId " + groupId);
            _log.debug("userId " + userId);
        }

        try {

            user = UserLocalServiceUtil.getUser(userId);
            user.setEmailAddress(email);
            if(language.equals("es_ES")){
                user.setLanguageId("es_ES");
            }else if(language.equals("ca_ES")){
                user.setLanguageId("ca_ES");
            }

            if (Validator.isNotNull(user)) {

                Contact contact = ContactLocalServiceUtil.getContact(user.getContactId());

                //Update genre
                updateUserContact(user.getContactId(), genre);

                PrevingRoleUtil.setAdminRole(groupId, user.getUserId(), admin);

                //Save user data
                UserData userData = PrevingUserDataUtil.updateUserDataValues(companyId, groupId, user.getUserId(), nif, firstName, lastName, email,
                        jobTitle, cmbWorkCenterId, salary, genre, notificationEndDate, true, strWorkCenter);

                Date previousValue = userData.getDeleteDate();

                Date endDateFormat = null;
                if(!Validator.isBlank(endDate)) {
                    endDateFormat = dateFormat.parse(endDate);
                }

                // Deactivate user
                if(!Validator.isBlank(endDate)) {

                    userData.setDeleteDate(endDateFormat);
                    UserDataLocalServiceUtil.updateUserData(userData);

                    if(endDateFormat.before(new Date())) {

                        userData.setActive(false);
                        UserDataLocalServiceUtil.updateUserData(userData);

                        PrevingRoleUtil.deleteSiteUserRoleToUser(groupId, user.getUserId());
                        PrevingRoleUtil.deleteSiteAdminRoleToUser(groupId, user.getUserId());

                        //Inactive admin and user if is inactive in all groups
                        boolean hasActiveGroupAdmin = PrevingRoleUtil.isUserActiveRoleInAnyActiveGroups(user.getUserId(), PrefsPropsUtil.getString(UserConfigurationPortletKeys.PROPERTY_ROLE_COMPANY_SITE_ADMIN));
                        boolean hasActiveGroupUser = PrevingRoleUtil.isUserActiveRoleInAnyActiveGroups(user.getUserId(), PrefsPropsUtil.getString(UserConfigurationPortletKeys.PROPERTY_ROLE_COMPANY_SITE_USER));

                        if( !hasActiveGroupAdmin  && !hasActiveGroupUser){

                            user.setStatus(WorkflowConstants.STATUS_INACTIVE);
                            UserLocalServiceUtil.updateUser(user);

                            if(_log.isDebugEnabled()){
                                _log.debug("The user " +  user.getEmailAddress() + " is inactive now in all the portal.");
                            }

                            UserMailing.userDeleteToUser(user);
                        }

                    }

                }else{

                    if(!user.isActive()){

                        PrevingRoleUtil.setUserRole(groupId, user.getUserId());

                        UserMailing.userActivateToUser(user,Validator.isNotNull(user.getPasswordUnencrypted())?user.getPasswordUnencrypted():"********");

                    }

                    user.setStatus(WorkflowConstants.STATUS_APPROVED);
                    UserLocalServiceUtil.updateUser(user);

                    PrevingRoleUtil.setUserRole(groupId, user.getUserId());

                    userData.setActive(true);
                    userData.setDeleteDate(endDateFormat);
                    UserDataLocalServiceUtil.updateUserData(userData);

                }

            }
        } catch (Exception e) {
            _log.error(e);
            //NotificacionUtil.sendNotificacion(creatorUserId, creatorUserId, "Error actualizando el usuario.", "No se ha podido crear el usuario.");
        }

        return user;
    }

    private static User createUserPortal(long creatorUserId, long companyId, String portalURL, String pathMain, String firstName, String lastName, String screenName, String passwordRandon, String email, String salary, Locale locale, String active){

        ServiceContext serviceContext = new ServiceContext();
        serviceContext.setPortalURL(portalURL);
        serviceContext.setPathMain(pathMain);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());

        int birthdayMonth = calendar.get(Calendar.MONTH);
        int birthdayDay = calendar.get(Calendar.DATE);
        int birthdayYear = calendar.get(Calendar.YEAR);

        //email = email.replace("+",".");

        User user = null;

        try {

            user = UserLocalServiceUtil.addUser(
                    creatorUserId,
                    companyId,
                    false,
                    passwordRandon,
                    passwordRandon,
                    false,
                    screenName,
                    email,
                    0,
                    null,
                    locale,
                    firstName,
                    "",
                    lastName,
                    0,
                    0,
                    true,
                    birthdayMonth,
                    birthdayDay,
                    birthdayYear,
                    screenName,
                    new long[]{},
                    new long[]{},
                    new long[]{},
                    new long[]{},
                    false,
                    serviceContext
            );

            if(UserConfigurationPortletKeys.USER_ACTIVE_YES.equals(active)){
                user.setStatus(WorkflowConstants.STATUS_APPROVED);
            }else if(UserConfigurationPortletKeys.USER_ACTIVE_NO.equals(active)){
                user.setStatus(WorkflowConstants.STATUS_INACTIVE);
            }

            user.setPasswordReset(false);
            user.setEmailAddressVerified(true);

            PasswordPolicy passwordPolicy = PasswordPolicyLocalServiceUtil.fetchPasswordPolicy(user.getCompanyId(),"Password Preving");
            UserLocalServiceUtil.addPasswordPolicyUsers(passwordPolicy.getPasswordPolicyId(), new long[]{user.getUserId()});

            UserLocalServiceUtil.updateUser(user);

        } catch (Exception e) {
            _log.error(e);
            NotificacionUtil.sendNotificacion(creatorUserId, creatorUserId, "Error creando el usuario", "No se ha podido crear el usuario. Contacte con el administrador: " + e.getMessage());
        }

        return user;
    }

    private static void updateUserContact(long contactId, String genre){

        Contact contact = null;

        try {

            contact = ContactLocalServiceUtil.getContact(contactId);

            if(UserConfigurationPortletKeys.USER_GENRE_MALE.equals(genre)){
                contact.setMale(true);
            }else if(UserConfigurationPortletKeys.USER_GENRE_FEMALE.equals(genre)){
                contact.setMale(false);
            }

            ContactLocalServiceUtil.updateContact(contact);

        } catch (PortalException e) {
            e.printStackTrace();
        }


    }

    public static User getUserByCompanyAndEmail(long companyId, String email){

        return UserLocalServiceUtil.fetchUserByEmailAddress(companyId, email.replace("+","."));

    }

    public static void remindPassword(long userId){

        try {

            User user = UserLocalServiceUtil.getUser(userId);
            ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();
            serviceContext.setPlid(2);
            UserLocalServiceUtil.sendPassword(user.getCompanyId(), user.getEmailAddress(), null, null, null, null,serviceContext);

            UserMailing.userRemingPasswordToUser(user, Validator.isNotNull(user.getPasswordUnencrypted())?user.getPasswordUnencrypted():"********");

            if(_log.isDebugEnabled()) {
                _log.debug("Remind password to user: " + userId + ".");
            }

        } catch (PortalException e) {
            _log.error("Error reminding password.");
        }
    }

    /*public static User activeUser(long userId){

        try {
            String expandoUserEndDate = PrefsPropsUtil.getString(UserConfigurationPortletKeys.PROPERTY_EXPANDO_USER_END_DATE);

            User user = UserLocalServiceUtil.getUser(userId);

            UserLocalServiceUtil.updateStatus(userId, WorkflowConstants.STATUS_APPROVED, new ServiceContext());

            setExpandoValueToUser(user, expandoUserEndDate, "");

            // Send success notification to user.
            String subject = UserConfigurationPortletKeys.LANG_USER_ACTIVE_SUBJECT_es_ES;
            String body = UserConfigurationPortletKeys.LANG_WELCOME_es_ES +
                    UserConfigurationPortletKeys.LANG_USER_ACTIVE_BODY_es_ES +
                    UserConfigurationPortletKeys.LANG_GOODBYE_es_ES +
                    UserConfigurationPortletKeys.LANG_FOOTER_es_ES;

            PrevingMailUtil.sendMail(userId, subject, body);

            if(_log.isDebugEnabled()) {
                _log.debug("Activate user: " + userId + ".");
            }

        } catch (PortalException e) {
            _log.error("Error activating user.");
        }

        return null;
    }*/


/*    public static User deactiveUser(long userId){

        try {
            String expandoUserEndDate = PrefsPropsUtil.getString(UserConfigurationPortletKeys.PROPERTY_EXPANDO_USER_END_DATE);

            User user = UserLocalServiceUtil.getUser(userId);

            UserLocalServiceUtil.updateStatus(userId, WorkflowConstants.STATUS_INACTIVE, new ServiceContext());

            setExpandoValueToUser(user, expandoUserEndDate, dateFormat.format(new Date()));

            // Send success notification to user.
            String subject = UserConfigurationPortletKeys.LANG_USER_INACTIVE_SUBJECT_es_ES;
            String body = UserConfigurationPortletKeys.LANG_WELCOME_es_ES +
                    UserConfigurationPortletKeys.LANG_USER_INACTIVE_BODY_es_ES +
                    UserConfigurationPortletKeys.LANG_GOODBYE_es_ES +
                    UserConfigurationPortletKeys.LANG_FOOTER_es_ES;

            PrevingMailUtil.sendMail(userId, subject, body);

            if(_log.isDebugEnabled()) {
                _log.debug("Deactivate user: " + userId + ".");
            }

        } catch (PortalException e) {
            _log.error("Error deactivating user.");
        }

        return null;
    }*/

    public static String getExpandoValueFromUser(User user, String expandoName){

        String res = "";

        try {

            if(Validator.isNotNull(user) && user.getExpandoBridge().hasAttribute(expandoName)) {

                PermissionChecker checker = PermissionCheckerFactoryUtil.create(user);
                PermissionThreadLocal.setPermissionChecker(checker);

                if(Validator.isNotNull(user.getExpandoBridge().getAttribute(expandoName))){
                    res = user.getExpandoBridge().getAttribute(expandoName).toString();
                }
            }

        } catch (Exception e) {
            _log.error("Error getting expando value from user ", e);
        }

        return res;
    }

}

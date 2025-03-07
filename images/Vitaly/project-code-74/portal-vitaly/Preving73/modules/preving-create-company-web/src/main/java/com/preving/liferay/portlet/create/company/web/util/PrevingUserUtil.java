package com.preving.liferay.portlet.create.company.web.util;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.PasswordPolicy;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.UserConstants;
import com.liferay.portal.kernel.service.*;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.util.ContentUtil;
import com.preving.liferay.portlet.calendar.manager.model.UserData;
import com.preving.liferay.portlet.create.company.web.constants.CreateCompanyPortletKeys;
import com.preving.liferay.portlet.mailing.web.mail.CompanyMailing;
import org.apache.commons.lang3.RandomStringUtils;

import java.text.SimpleDateFormat;
import java.util.*;

public class PrevingUserUtil {

    private static Log _log = LogFactoryUtil.getLog(PrevingUserUtil.class);
    static SimpleDateFormat dateFormatLog = new SimpleDateFormat("yyyy-dd-MM HH:mm:ss.SSS");


    public static User getUser(long companyId, long groupId, String firstName, String lastName, String nif, String email, String password, String portalURL, String pathMain, Locale locale){

        User user = UserLocalServiceUtil.fetchUserByEmailAddress(companyId, email.replace("+","."));

        if(Validator.isNull(user)){

            //String passwordRandon = RandomStringUtils.random(8,true, true);

            String screenName = email.toLowerCase().replace("@","-").replace(".","-").replace("+","-");

            _log.debug(dateFormatLog.format(new Date()) + " Start to create new user " + firstName + " " + lastName + " " + email + " " + nif);

            user = createUserPortal(UserConstants.USER_ID_DEFAULT, companyId, portalURL, pathMain, firstName, lastName, screenName, password, email, locale);

            //Send mail to user created (administrator)
            CompanyMailing.companyCreateToAdministrator(user, password);

            _log.debug(dateFormatLog.format(new Date()) + " Admin User sended mail Welcome Pack " + user.getEmailAddress());

        } else {
            _log.debug(dateFormatLog.format(new Date()) + " User exists " + user.getEmailAddress() + " " + user.getFullName() + " " + user.isActive() + ", " + companyId );
        }

        //Save user data
        UserData userData = PrevingUserDataUtil.updateUserDataValues(companyId, groupId, user.getUserId(), nif, firstName, lastName, email, "", "", "", true);

        return user;
    }

    private static User createUserPortal(long creatorUserId, long companyId, String portalURL, String pathMain, String firstName, String lastName, String screenName, String passwordRandon, String email, Locale locale){

        ServiceContext serviceContext = new ServiceContext();
        serviceContext.setPortalURL(portalURL);
        serviceContext.setPathMain(pathMain);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());

        int birthdayMonth = calendar.get(Calendar.MONTH);
        int birthdayDay = calendar.get(Calendar.DATE);
        int birthdayYear = calendar.get(Calendar.YEAR);

        email = email.replace("+",".");

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

            user.setStatus(WorkflowConstants.STATUS_APPROVED);

            user.setPasswordReset(false);
            user.setEmailAddressVerified(true);

            UserLocalServiceUtil.updateUser(user);

            _log.debug(dateFormatLog.format(new Date()) + " Created user " + user.getEmailAddress() + " by " + creatorUserId);

            PasswordPolicy passwordPolicy = PasswordPolicyLocalServiceUtil.fetchPasswordPolicy(user.getCompanyId(),"Password Preving");
            UserLocalServiceUtil.addPasswordPolicyUsers(passwordPolicy.getPasswordPolicyId(), new long[]{user.getUserId()});

            _log.debug(dateFormatLog.format(new Date()) + " Add password policy to user " + user.getEmailAddress());

        } catch (Exception e) {
            _log.error(e);
            NotificacionUtil.sendNotificacion(creatorUserId, creatorUserId, "Error creando el usuario", "No se ha podido crear el usuario. Contacte con el administrador: " + e.getMessage());
        }

        return user;
    }

    public static void addRoleToUser(User user, long companyId, String roleName){

        if(Validator.isNotNull(user) && companyId > 0 && !Validator.isBlank(roleName)) {

            try {

                Role role = RoleLocalServiceUtil.fetchRole(companyId, roleName);

                UserLocalServiceUtil.addRoleUser(role.getRoleId(), user);

                if (_log.isDebugEnabled()) {
                    _log.debug(" companyId : " + companyId);
                    _log.debug(" roleName : " + roleName);
                    _log.debug(" role : " + role.getRoleId());
                    _log.debug(" user email: " + user.getEmailAddress());
                }

            } catch (Exception e) {
                _log.error("Error adding role to user.", e);
            }

        }else{
            _log.error("Role is not assigned to user.");
        }
    }


    public static void addRoleGroupToUser(long groupId, User user, long companyId, String roleName) {

        if(Validator.isNotNull(user) && companyId > 0 && !Validator.isBlank(roleName)) {

            try {

                Role role = RoleLocalServiceUtil.fetchRole(companyId, roleName);

                //UserLocalServiceUtil.addRoleUser(role.getRoleId(), user);

                UserGroupRoleLocalServiceUtil.addUserGroupRole(user.getUserId(), groupId, role.getRoleId());

                if (_log.isDebugEnabled()) {
                    _log.debug(" companyId : " + companyId);
                    _log.debug(" roleName : " + roleName);
                    _log.debug(" role : " + role.getRoleId());
                    _log.debug(" user email: " + user.getEmailAddress());
                }

            } catch (Exception e) {
                _log.error("Error adding role to user.", e);
            }

        }else{
            _log.error("Role is not assigned to user.");
        }

    }

    public static void deleteAdminRole(long groupId, long userId){

        String roleUserName = PrefsPropsUtil.getString(CreateCompanyPortletKeys.PROPERTY_ROLE_COMPANY_SITE_ADMIN);

        deleteRoleToUser(groupId, userId, roleUserName);

    }

    public static void deleteUserRole(long groupId, long userId){

        String roleUserName = PrefsPropsUtil.getString(CreateCompanyPortletKeys.PROPERTY_ROLE_COMPANY_SITE_USER);

        deleteRoleToUser(groupId, userId, roleUserName);

    }

    private static boolean deleteRoleToUser(long groupId, long userId, String roleName){

        try {

            User user = UserLocalServiceUtil.getUser(userId);

            Role role = RoleLocalServiceUtil.fetchRole(user.getCompanyId(), roleName);

            if(Validator.isNull(role)){
                _log.error("The role " + roleName + " dont exists.");
                return false;
            }

            UserGroupRoleLocalServiceUtil.deleteUserGroupRoles(user.getUserId(), groupId, new long[]{role.getRoleId()});

        } catch (PortalException e) {
            _log.error("Error deleting role to user " + userId);
        }

        return true;
    }

    public static void updateUser(long companyId, long groupId, long userId, String firstName, String lastName, String nif, String email, ServiceContext serviceContext){

        try {

            User user = UserLocalServiceUtil.fetchUser(userId);

            if (Validator.isNotNull(user)) {

                //Save user data
                UserData userData = PrevingUserDataUtil.updateUserDataValues(companyId, groupId, user.getUserId(), nif, firstName, lastName, email, "", "", "", true);

                // Send email verification.
                if (user.getEmailAddress().compareToIgnoreCase(email) != 0) {
                    UserLocalServiceUtil.sendEmailAddressVerification(user, email, serviceContext);
                }

                UserLocalServiceUtil.updateUser(user);

            }

        } catch (PortalException e) {
            _log.error("Error updating user.", e);
        }
    }


    public static List<User> getListAdminIdFromGroup(long groupId){
        List<User> admins = new ArrayList<User>();

        List<User> groupUsers = UserLocalServiceUtil.getGroupUsers(groupId);

        for(User user:groupUsers){
            if(user.isActive() && PrevingRoleUtil.hasSiteAdminRole(groupId, user.getUserId())){
                admins.add(user);
            }
        }

        return admins;
    }
}

package com.preving.liferay.portlet.create.company.web.util;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.UserGroupRoleLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.util.ContentUtil;
import com.preving.liferay.portlet.calendar.manager.model.UserData;
import com.preving.liferay.portlet.calendar.manager.service.UserDataLocalServiceUtil;
import com.preving.liferay.portlet.create.company.web.constants.CreateCompanyPortletKeys;
import com.preving.liferay.portlet.mailing.web.mail.UserMailing;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class PrevingRoleUtil {

    public static Log _log = LogFactoryUtil.getLog(PrevingRoleUtil.class);

    static String roleAdminName = PrefsPropsUtil.getString(CreateCompanyPortletKeys.PROPERTY_ROLE_COMPANY_ADMIN);

    public static void addAdminRole(long groupId, long userId){

        String roleSiteAdminName = PrefsPropsUtil.getString(CreateCompanyPortletKeys.PROPERTY_ROLE_COMPANY_SITE_ADMIN);

        setRoleToUser(groupId, userId, roleSiteAdminName);
    }

    public static void addUserRole(long groupId, long userId){

        String roleUserName = PrefsPropsUtil.getString(CreateCompanyPortletKeys.PROPERTY_ROLE_COMPANY_SITE_USER);

        setRoleToUser(groupId, userId, roleUserName);

    }
    public static void deleteAdminRole(long groupId, long userId){

        String roleUserName = PrefsPropsUtil.getString(CreateCompanyPortletKeys.PROPERTY_ROLE_COMPANY_SITE_ADMIN);

        deleteRoleToUser(groupId, userId, roleUserName);

    }

    public static void deleteUserRole(long groupId, long userId){

        String roleUserName = PrefsPropsUtil.getString(CreateCompanyPortletKeys.PROPERTY_ROLE_COMPANY_SITE_USER);

        deleteRoleToUser(groupId, userId, roleUserName);

    }

    private static boolean setRoleToUser(long groupId, long userId, String roleName){

        try {

            User user = UserLocalServiceUtil.getUser(userId);

            Role role = RoleLocalServiceUtil.fetchRole(user.getCompanyId(), roleName);

            if(Validator.isNull(role)){
                _log.error("The role " + roleName + " dont exists.");
                return false;
            }

            UserGroupRoleLocalServiceUtil.addUserGroupRole(userId, groupId, role.getRoleId());

        } catch (PortalException e) {
            _log.error("Error adding role to user.", e);
        } catch (Exception e) {
            _log.error("Error adding role to user.", e);
        }

        return true;
    }

    public static boolean hasSiteAdminRole(long groupId, long userId){

        boolean hasSiteAdminRole = false;

        try {

            User user = UserLocalServiceUtil.getUser(userId);

            String roleAdminName = PrefsPropsUtil.getString(CreateCompanyPortletKeys.PROPERTY_ROLE_COMPANY_SITE_ADMIN);

            hasSiteAdminRole = UserGroupRoleLocalServiceUtil.hasUserGroupRole(userId, groupId, roleAdminName);

        } catch (Exception e) {
            _log.error("Error checking roles.", e);
        }

        return hasSiteAdminRole;
    }

    public static boolean hasRole(User user, String roleName){
        List<Role> roles = user.getRoles();
        for(Role role:roles){
            if(roleName.equals(role.getName())){

                if(_log.isDebugEnabled()){
                    _log.debug("The user " + user.getEmailAddress() + " has role " + roleName+".");
                }

                return true;
            }
        }
        return false;
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


    public static void addUserRoleToGroup(long companyId, long groupId){

        if(groupId == 0){
            _log.error("Group cant be 0. ");
            return;
        }

        Group group = null;
        try {
            group = GroupLocalServiceUtil.getGroup(groupId);
        } catch (PortalException e) {
            _log.error("Group not exits. " + e);
        }

        if(Validator.isNull(group) || !group.isActive()){
            _log.error("Group is inactive.");
            return;
        }else{
            if(_log.isDebugEnabled()){
                _log.debug("Add user role to group " +group.getFriendlyURL() + ".");
            }
        }

        List<User> groupUsers = UserLocalServiceUtil.getGroupUsers(groupId);

        for(User user:groupUsers){

            if(Validator.isNotNull(group) && user.getUserId() == group.getCreatorUserId()){
                continue;
            }

            UserData userData = UserDataLocalServiceUtil.getUserDataByGroupIdAndUserId(groupId, user.getUserId());

            //Create user data
            if(Validator.isNull(userData)){
                userData = PrevingUserDataUtil.createNewUserData(companyId, groupId, user);
            }

            if( (Validator.isNotNull(userData.getDeleteDate()) && userData.getDeleteDate().after(new Date())) || Validator.isNull(userData.getDeleteDate())){

                PrevingRoleUtil.addUserRole(groupId, user.getUserId());
                userData.setActive(true);

                if(_log.isDebugEnabled()){
                    _log.debug(" - Add user role to " + user.getEmailAddress() + " and set active in company.");
                }

                if(hasRole(user, roleAdminName)){
                    addAdminRole(groupId, user.getUserId());
                    if(_log.isDebugEnabled()){
                        _log.debug("   * Add admin role to " + user.getEmailAddress() + ".");
                    }
                }

            }else{

                PrevingRoleUtil.deleteUserRole(groupId, user.getUserId());
                userData.setActive(false);

                if(_log.isDebugEnabled()){
                    _log.debug(" - Delete user role to " + user.getEmailAddress() + " and set inactive in company.");
                }

            }

            UserDataLocalServiceUtil.updateUserData(userData);

        }
    }

    public static void deleteUserRolUsers(long groupId, List<User> userList){

        Date now = new Date();

        //Inactive users for the company.
/*        String subjectUser = CreateCompanyPortletKeys.LANG_USER_INACTIVE_SUBJECT_es_ES;
        String bodyUser = CreateCompanyPortletKeys.LANG_WELCOME_es_ES +
                CreateCompanyPortletKeys.LANG_USER_INACTIVE_BODY_es_ES +
                CreateCompanyPortletKeys.LANG_GOODBYE_es_ES +
                CreateCompanyPortletKeys.LANG_FOOTER_es_ES;*/

        for(User u:userList) {

            UserData userData = UserDataLocalServiceUtil.getUserDataByGroupIdAndUserId(groupId, u.getUserId());

            if(Validator.isNotNull(userData)) {
                userData.setDeleteDate(now);
                userData.setActive(false);

                UserDataLocalServiceUtil.updateUserData(userData);
            }

/*            String subjectUser = LanguageUtil.get(ResourceBundle.getBundle("content/Language", u.getLocale()), "createcompany.mail.subject.deleted.user");
            String bodyUser = ContentUtil.get("/templates/DeleteFromCompanyTemplate_"+ u.getLocale() +".tmpl");
            if(Validator.isNull(bodyUser) || Validator.isBlank(bodyUser)){
                bodyUser = ContentUtil.get("/templates/DeleteFromCompanyTemplate_es_ES.tmpl");
            }
            PrevingMailUtil.sendMail(u.getUserId(), subjectUser, bodyUser);*/

            PrevingRoleUtil.deleteUserRole(groupId, u.getUserId());

            //Send mail
            if(groupId==148071) {
               _log.debug("Se intento enviar el correo a preving: "+u.getEmailAddress());
            }else{
                UserMailing.userDeleteToUser(u);
            }
            if (_log.isDebugEnabled()) {
                _log.debug("Paso por el metodo pero no envio el correo ");
            }

        }

    }

}

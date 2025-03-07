package com.preving.liferay.portlet.user.configuration.web.util;

import com.liferay.portal.kernel.exception.PortalException;
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
import com.preving.liferay.portlet.calendar.manager.model.UserData;
import com.preving.liferay.portlet.calendar.manager.service.UserDataLocalServiceUtil;
import com.preving.liferay.portlet.user.configuration.web.constants.UserConfigurationPortletKeys;

import java.util.ArrayList;
import java.util.List;

public class PrevingRoleUtil {

    public static Log _log = LogFactoryUtil.getLog(PrevingRoleUtil.class);

    public static void setAdminRole(long groupId, long userId, String admin){
        if(UserConfigurationPortletKeys.USER_ADMIN_YES.equals(admin)){
            setSiteAdminRoleToUser(groupId, userId);
        }else if(UserConfigurationPortletKeys.USER_ADMIN_NO.equals(admin)){
            deleteSiteAdminRoleToUser(groupId, userId);
        }
    }

    public static void setUserRole(long groupId,long userId){

        String roleUserName = PrefsPropsUtil.getString(UserConfigurationPortletKeys.PROPERTY_ROLE_COMPANY_SITE_USER);

        setRoleToUser(groupId,userId, roleUserName);

    }

    public static boolean hasSiteAdminRole(long groupId, long userId){

        boolean hasSiteAdminRole = false;

        try {

            User user = UserLocalServiceUtil.getUser(userId);

            String roleAdminName = PrefsPropsUtil.getString(UserConfigurationPortletKeys.PROPERTY_ROLE_COMPANY_SITE_ADMIN);

            hasSiteAdminRole = UserGroupRoleLocalServiceUtil.hasUserGroupRole(userId, groupId, roleAdminName);


        } catch (Exception e) {
            _log.error("Error checking roles.", e);
        }

        return hasSiteAdminRole;
    }


    public static List<String> getAdminsByCompany(long groupId){
        List<String> emails = new ArrayList<String>();
        List<User> users = UserLocalServiceUtil.getGroupUsers(groupId);
        UserData uData = null;
        if(Validator.isNotNull(users) && users.size() > 0){
            for(User u : users){
                uData = UserDataLocalServiceUtil.fetchUserData(u.getUserId());
                if(Validator.isNotNull(uData) && PrevingRoleUtil.hasSiteAdminRole(groupId, uData.getUserId())){
                    emails.add(uData.getEmail());
                }
            }

        }
        return emails;
    }


    public static boolean hasUserRole(long groupId, long userId){

        boolean hasSiteAdminRole = false;

        try {

            User user = UserLocalServiceUtil.getUser(userId);

            String roleName = PrefsPropsUtil.getString(UserConfigurationPortletKeys.PROPERTY_ROLE_COMPANY_SITE_USER);

            hasSiteAdminRole = UserGroupRoleLocalServiceUtil.hasUserGroupRole(userId, groupId, roleName);

        } catch (Exception e) {
            _log.error("Error checking roles.", e);
        }

        return hasSiteAdminRole;
    }

    public static boolean setSiteAdminRoleToUser(long groupId, long userId){

        try {

            User user = UserLocalServiceUtil.getUser(userId);

            String roleAdminName = PrefsPropsUtil.getString(UserConfigurationPortletKeys.PROPERTY_ROLE_COMPANY_SITE_ADMIN);

            Role role = RoleLocalServiceUtil.fetchRole(user.getCompanyId(), roleAdminName);

            UserLocalServiceUtil.addRoleUser(role.getRoleId(), user);

            UserGroupRoleLocalServiceUtil.addUserGroupRoles(user.getUserId(), groupId, new long[]{role.getRoleId()});

        } catch (PortalException e) {
            _log.error("Error adding role to user.", e);
        } catch (Exception e) {
            _log.error("Error adding role to user.", e);
        }

        return false;
    }

    private static boolean setRoleToUser(long groupId, long userId, String roleName){

        try {

            User user = UserLocalServiceUtil.getUser(userId);

            Role role = RoleLocalServiceUtil.fetchRole(user.getCompanyId(), roleName);

            if(Validator.isNull(role)){
                _log.error("The role " + roleName + " dont exists.");
                return false;
            }

            //UserLocalServiceUtil.addRoleUser(role.getRoleId(), user);

            UserGroupRoleLocalServiceUtil.addUserGroupRole(userId, groupId, role.getRoleId());

            UserGroupRoleLocalServiceUtil.addUserGroupRoles(user.getUserId(), user.getGroupId(), new long[]{role.getRoleId()});

        } catch (PortalException e) {
            _log.error("Error adding role to user.", e);
        } catch (Exception e) {
            _log.error("Error adding role to user.", e);
        }

        return true;
    }

    public static boolean deleteSiteAdminRoleToUser(long groupId, long userId){

        try {

            User user = UserLocalServiceUtil.getUser(userId);

            String roleAdminName = PrefsPropsUtil.getString(UserConfigurationPortletKeys.PROPERTY_ROLE_COMPANY_SITE_ADMIN);

            Role role = RoleLocalServiceUtil.fetchRole(user.getCompanyId(), roleAdminName);

            //RoleLocalServiceUtil.deleteUserRole(userId, role);

            UserGroupRoleLocalServiceUtil.deleteUserGroupRoles(userId, groupId, new long[]{role.getRoleId()});

        } catch (PortalException e) {
            _log.error("Error adding role to user.");
        }

        return false;
    }

    public static boolean deleteSiteUserRoleToUser(long groupId, long userId){

        try {

            String roleUserName = PrefsPropsUtil.getString(UserConfigurationPortletKeys.PROPERTY_ROLE_COMPANY_SITE_USER);

            deleteRoleToUser(groupId, userId, roleUserName);

        } catch (Exception e) {
            _log.error("Error adding role to user.");
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

            RoleLocalServiceUtil.deleteUserRole(userId, role);

            UserGroupRoleLocalServiceUtil.deleteUserGroupRoles(user.getUserId(), groupId, new long[]{role.getRoleId()});


        } catch (PortalException e) {
            _log.error("Error deleting role to user " + userId);
        }

        return true;
    }

    public static boolean isUserActiveRoleInGroup(long groupId, long userId, String roleName){

        boolean isActive = false;

        try {

            isActive = UserGroupRoleLocalServiceUtil.hasUserGroupRole(userId, groupId, roleName);

        } catch (Exception e) {
            _log.error("Error checking is active in group.", e);
        }

        return isActive;
    }

    public static boolean isUserActiveRoleInAnyActiveGroups(long userId, String roleName){

        try {

            User user = UserLocalServiceUtil.getUser(userId);

            List<Group> userGroups = user.getGroups();

            for(Group group:userGroups){

                if(group.isActive() && isUserActiveRoleInGroup(group.getGroupId(), userId, roleName)){
                    if(_log.isDebugEnabled()){
                        _log.debug("  User: "+ user.getEmailAddress() + " active in group " + group.getFriendlyURL()+ ", active: " + group.isActive() + " with role " + roleName);
                    }
                    return true;
                }else{
                    if(_log.isDebugEnabled()){
                        _log.debug("  User: "+ user.getEmailAddress() + " inactive in group: " + group.getFriendlyURL()+ ", active: " + group.isActive() +  " with role " + roleName);
                    }
                }
            }

        } catch (Exception e) {
            _log.error("Error checking is active in all groups.", e);
        }

        return false;
    }
}

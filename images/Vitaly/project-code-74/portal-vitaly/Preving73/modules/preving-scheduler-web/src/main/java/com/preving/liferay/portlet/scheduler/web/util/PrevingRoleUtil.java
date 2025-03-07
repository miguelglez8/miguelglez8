package com.preving.liferay.portlet.scheduler.web.util;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.UserGroupRoleLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.Validator;
import com.preving.liferay.portlet.scheduler.web.constants.PrevingSchedulerPortletKeys;

import java.util.List;

public class PrevingRoleUtil {

    public static Log _log = LogFactoryUtil.getLog(PrevingRoleUtil.class);

    public static void addAdminRole(long userId){

        String roleAdminName = PrefsPropsUtil.getString(PrevingSchedulerPortletKeys.PROPERTY_ROLE_COMPANY_SITE_ADMIN);

        setRoleToUser(userId, roleAdminName);
    }

    public static void deleteAdminRole(long groupId, long userId){

        String roleAdminName = PrefsPropsUtil.getString(PrevingSchedulerPortletKeys.PROPERTY_ROLE_COMPANY_SITE_ADMIN);

        deleteRoleToUser(userId, groupId, roleAdminName);
    }

    public static void addUserRole(long userId){

        String roleUserName = PrefsPropsUtil.getString(PrevingSchedulerPortletKeys.PROPERTY_ROLE_COMPANY_SITE_USER);

        setRoleToUser(userId, roleUserName);

    }
    public static void deleteUserRole(long groupId, long userId){

        String roleUserName = PrefsPropsUtil.getString(PrevingSchedulerPortletKeys.PROPERTY_ROLE_COMPANY_SITE_USER);

        deleteRoleToUser(groupId, userId, roleUserName);

    }

    private static boolean setRoleToUser(long userId, String roleName){

        try {

            User user = UserLocalServiceUtil.getUser(userId);

            Role role = RoleLocalServiceUtil.fetchRole(user.getCompanyId(), roleName);

            if(Validator.isNull(role)){
                _log.error("The role " + roleName + " dont exists.");
                return false;
            }

            UserLocalServiceUtil.addRoleUser(role.getRoleId(), user);

            UserGroupRoleLocalServiceUtil.addUserGroupRoles(user.getUserId(), user.getGroupId(), new long[]{role.getRoleId()});

        } catch (PortalException e) {
            _log.error("Error adding role to user.", e);
        } catch (Exception e) {
            _log.error("Error adding role to user.", e);
        }

        return true;
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
                        _log.debug("  Active in group " + group.getFriendlyURL() + "user: "+ user.getEmailAddress() + "  with role " + roleName);
                    }
                    return true;
                }else{
                    if(_log.isDebugEnabled()){
                        _log.debug("  Inactive in group; " + group.getFriendlyURL()+ ", active: " + group.isActive() + ", user: "+ user.getEmailAddress() + " with role " + roleName);
                    }
                }
            }

        } catch (Exception e) {
            _log.error("Error checking is active in all groups.", e);
        }

        return false;
    }

    public static boolean hasRoleUser(long groupId, long userId, String roleName){

        boolean hasSiteAdminRole = false;

        try {

            hasSiteAdminRole = UserGroupRoleLocalServiceUtil.hasUserGroupRole(userId, groupId, roleName);

        } catch (Exception e) {
            _log.error("Error checking roles.", e);
        }

        return hasSiteAdminRole;
    }
}

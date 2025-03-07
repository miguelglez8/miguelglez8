package com.adeplus.liferay.portlet.commons.web.role;

import com.adeplus.liferay.portlet.commons.web.audit.AdeplusAuditUtil;
import com.adeplus.liferay.portlet.commons.web.constants.AdeplusAuditPortletKeys;
import com.adeplus.liferay.portlet.commons.web.constants.AdeplusCommonsPortletKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.Validator;

public class AdeplusRoleUtil {

    public static Log _log = LogFactoryUtil.getLog(AdeplusRoleUtil.class);

    public static boolean isOmniAdminRole(long companyId, User user){
        String roleName = PrefsPropsUtil.getString(AdeplusCommonsPortletKeys.PROPERTY_ROLE_ADEPLUS_ADMIN);
        if(Validator.isNull(user)){
            return false;
        }
        return hasRole(companyId, roleName, user.getUserId());
    }

    public static boolean isCompanyAdminRole(long companyId, User user){
        String roleName = PrefsPropsUtil.getString(AdeplusCommonsPortletKeys.PROPERTY_ROLE_COMPANY_ADMIN);
        if(Validator.isNull(user)){
            return false;
        }
        return hasRole(companyId, roleName, user.getUserId());
    }
    public static void setCompanyAdminRole(long companyId, User user){
        String roleName = PrefsPropsUtil.getString(AdeplusCommonsPortletKeys.PROPERTY_ROLE_COMPANY_ADMIN);
        Role role = getRoleByName(companyId, roleName);
        if(Validator.isNotNull(role) && Validator.isNotNull(user)) {
            User userUpdate = UserLocalServiceUtil.fetchUser(user.getUserId());
            UserLocalServiceUtil.addRoleUser(role.getRoleId(), userUpdate);
            UserLocalServiceUtil.updateUser(userUpdate);

            AdeplusAuditUtil.addAudit(user.getCompanyId(), user.getGroupId(), 0, AdeplusAuditPortletKeys.AUDIT_USER_ROLE_ADD_ADMIN, "Add admin role to "+ user.getEmailAddress() +".");
        }
    }
    public static void deleteCompanyAdminRole(long companyId, User user){
        String roleName = PrefsPropsUtil.getString(AdeplusCommonsPortletKeys.PROPERTY_ROLE_COMPANY_ADMIN);
        Role role = getRoleByName(companyId, roleName);
        if(Validator.isNotNull(role) && Validator.isNotNull(user)) {
            try {
                User userUpdate = UserLocalServiceUtil.fetchUser(user.getUserId());
                UserLocalServiceUtil.deleteRoleUser(role.getRoleId(), userUpdate);
                UserLocalServiceUtil.updateUser(userUpdate);

                AdeplusAuditUtil.addAudit(user.getCompanyId(), user.getGroupId(), 0, AdeplusAuditPortletKeys.AUDIT_USER_ROLE_DELETE_ADMIN, "Delete admin role to "+ user.getEmailAddress() +".");
            } catch (PortalException e) {
                _log.error("Error deleting role: " + roleName, e);
            }
        }
    }

    public static boolean isCompanyUserRole(long companyId, User user){
        String roleName = PrefsPropsUtil.getString(AdeplusCommonsPortletKeys.PROPERTY_ROLE_COMPANY_USER);
        if(Validator.isNull(user)){
            return false;
        }
        return hasRole(companyId, roleName, user.getUserId());
    }
    public static void setCompanyUserRole(long companyId, User user){
        String roleName = PrefsPropsUtil.getString(AdeplusCommonsPortletKeys.PROPERTY_ROLE_COMPANY_USER);
        Role role = getRoleByName(companyId, roleName);
        if(Validator.isNotNull(role) && Validator.isNotNull(user)) {
            User userUpdate = UserLocalServiceUtil.fetchUser(user.getUserId());
            UserLocalServiceUtil.addRoleUser(role.getRoleId(), userUpdate);
            UserLocalServiceUtil.updateUser(userUpdate);

            AdeplusAuditUtil.addAudit(user.getCompanyId(), user.getGroupId(), 0, AdeplusAuditPortletKeys.AUDIT_USER_ROLE_ADD_USER, "Add user role to "+ user.getEmailAddress() +".");
        }
    }
    public static void deleteCompanyUserRole(long companyId, User user){
        String roleName = PrefsPropsUtil.getString(AdeplusCommonsPortletKeys.PROPERTY_ROLE_COMPANY_USER);
        Role role = getRoleByName(companyId, roleName);
        if(Validator.isNotNull(role) && Validator.isNotNull(user)) {
            try {
                User userUpdate = UserLocalServiceUtil.fetchUser(user.getUserId());
                UserLocalServiceUtil.deleteRoleUser(role.getRoleId(), userUpdate);
                UserLocalServiceUtil.updateUser(userUpdate);

                AdeplusAuditUtil.addAudit(user.getCompanyId(), user.getGroupId(), 0, AdeplusAuditPortletKeys.AUDIT_USER_ROLE_DELETE_USER, "Delete user role to "+ user.getEmailAddress() +".");
            } catch (PortalException e) {
                _log.error("Error deleting role: " + roleName, e);
            }
        }
    }

    private static boolean hasRole(long companyId, String roleName, long userId){
        Role role = null;
        try {
            role = getRoleByName(companyId, roleName);
            if(Validator.isNotNull(role)) {
                return UserLocalServiceUtil.hasRoleUser(role.getRoleId(), userId);
            }
        } catch (Exception e) {
            _log.error("Error checking role: " + roleName, e);
        }
        return false;
    }

    private static Role getRoleByName(long companyId, String roleName){
        Role role = null;
        try {
            role = RoleLocalServiceUtil.getRole(companyId, roleName);
        } catch (PortalException e) {
            _log.error("Error getting rol name: " + roleName, e);
        }
        return role;
    }

}

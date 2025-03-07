package com.canal.etico.liferay.portlet.commons.web.role;

import com.canal.etico.liferay.portlet.commons.web.constants.CanalEticoCommonsPortletKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.Validator;

public class CanalEticoRoleUtil {

    public static Log _log = LogFactoryUtil.getLog(CanalEticoRoleUtil.class);

    public static boolean isOmniAdminRole(long companyId, User user){
        String roleName = PrefsPropsUtil.getString(CanalEticoCommonsPortletKeys.PROPERTY_ROLE_ADEPLUS_ADMIN);
        if(Validator.isNull(user)){
            return false;
        }
        return hasRole(companyId, roleName, user.getUserId());
    }

    public static boolean isCompanyAdminRole(long companyId, User user){
        String roleName = PrefsPropsUtil.getString(CanalEticoCommonsPortletKeys.PROPERTY_ROLE_COMPANY_ADMIN);
        if(Validator.isNull(user)){
            return false;
        }
        return hasRole(companyId, roleName, user.getUserId());
    }
    public static void setCompanyAdminRole(long companyId, User user){
        String roleName = PrefsPropsUtil.getString(CanalEticoCommonsPortletKeys.PROPERTY_ROLE_COMPANY_ADMIN);
        Role role = getRoleByName(companyId, roleName);
        if(Validator.isNotNull(role) && Validator.isNotNull(user)) {
            User userUpdate = UserLocalServiceUtil.fetchUser(user.getUserId());
            UserLocalServiceUtil.addRoleUser(role.getRoleId(), userUpdate);
            UserLocalServiceUtil.updateUser(userUpdate);

        }
    }
    public static void deleteCompanyAdminRole(long companyId, User user){
        String roleName = PrefsPropsUtil.getString(CanalEticoCommonsPortletKeys.PROPERTY_ROLE_COMPANY_ADMIN);
        Role role = getRoleByName(companyId, roleName);
        if(Validator.isNotNull(role) && Validator.isNotNull(user)) {
            try {
                User userUpdate = UserLocalServiceUtil.fetchUser(user.getUserId());
                UserLocalServiceUtil.deleteRoleUser(role.getRoleId(), userUpdate);
                UserLocalServiceUtil.updateUser(userUpdate);

             } catch (PortalException e) {
                _log.error("Error deleting role: " + roleName, e);
            }
        }
    }

    public static boolean isCompanyUserRole(long companyId, User user){
        String roleName = PrefsPropsUtil.getString(CanalEticoCommonsPortletKeys.PROPERTY_ROLE_COMPANY_USER);
        if(Validator.isNull(user)){
            return false;
        }
        return hasRole(companyId, roleName, user.getUserId());
    }
    public static void setCompanyUserRole(long companyId, User user){
        String roleName = PrefsPropsUtil.getString(CanalEticoCommonsPortletKeys.PROPERTY_ROLE_COMPANY_USER);
        Role role = getRoleByName(companyId, roleName);
        if(Validator.isNotNull(role) && Validator.isNotNull(user)) {
            User userUpdate = UserLocalServiceUtil.fetchUser(user.getUserId());
            UserLocalServiceUtil.addRoleUser(role.getRoleId(), userUpdate);
            UserLocalServiceUtil.updateUser(userUpdate);

        }
    }
    public static void deleteCompanyUserRole(long companyId, User user){
        String roleName = PrefsPropsUtil.getString(CanalEticoCommonsPortletKeys.PROPERTY_ROLE_COMPANY_USER);
        Role role = getRoleByName(companyId, roleName);
        if(Validator.isNotNull(role) && Validator.isNotNull(user)) {
            try {
                User userUpdate = UserLocalServiceUtil.fetchUser(user.getUserId());
                UserLocalServiceUtil.deleteRoleUser(role.getRoleId(), userUpdate);
                UserLocalServiceUtil.updateUser(userUpdate);

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

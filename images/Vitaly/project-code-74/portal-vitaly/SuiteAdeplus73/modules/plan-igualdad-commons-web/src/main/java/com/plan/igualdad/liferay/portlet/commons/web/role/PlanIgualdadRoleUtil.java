package com.plan.igualdad.liferay.portlet.commons.web.role;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.Validator;
import com.plan.igualdad.liferay.portlet.commons.web.constants.PlanIgualdadCommonsPortletKeys;

public class PlanIgualdadRoleUtil {

    public static Log _log = LogFactoryUtil.getLog(PlanIgualdadRoleUtil.class);

    public static String getAdministratorRol() {
        return PrefsPropsUtil.getString(PlanIgualdadCommonsPortletKeys.PROPERTY_ROLE_PLAN_IGUALDAD_ADMINISTRADOR);
    }

    public static String getConsultorRol() {
        return PrefsPropsUtil.getString(PlanIgualdadCommonsPortletKeys.PROPERTY_ROLE_PLAN_IGUALDAD_CONSULTOR);
    }

    public static boolean isAdministradorRole(long companyId, User user){
        String roleName = PrefsPropsUtil.getString(PlanIgualdadCommonsPortletKeys.PROPERTY_ROLE_PLAN_IGUALDAD_ADMINISTRADOR);
        if(Validator.isNull(user)){
            return false;
        }
        return hasRole(companyId, roleName, user.getUserId());
    }

    public static boolean isConsultorRole(long companyId, User user){
        String roleName = PrefsPropsUtil.getString(PlanIgualdadCommonsPortletKeys.PROPERTY_ROLE_PLAN_IGUALDAD_CONSULTOR);
        if(Validator.isNull(user)){
            return false;
        }
        return hasRole(companyId, roleName, user.getUserId());
    }

    private static boolean hasRole(long companyId, String roleName, long userId){
        Role role = null;
        try {
            role = getRoleByName(companyId, roleName);
            if(Validator.isNotNull(role)) {
                return UserLocalServiceUtil.hasRoleUser(role.getRoleId(), userId);
            }
        } catch (Exception e) {
            _log.error(e, e);
        }
        return false;
    }

    private static Role getRoleByName(long companyId, String roleName){
        Role role = null;
        try {
            role = RoleLocalServiceUtil.getRole(companyId, roleName);
        } catch (PortalException e) {
            _log.error(e, e);
        }
        return role;
    }

}

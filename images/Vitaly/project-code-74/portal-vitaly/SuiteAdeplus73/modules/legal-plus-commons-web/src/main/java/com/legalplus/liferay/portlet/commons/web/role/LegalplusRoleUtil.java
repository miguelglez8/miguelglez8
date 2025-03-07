package com.legalplus.liferay.portlet.commons.web.role;

import com.adeplus.liferay.portlet.suite.manager.model.ApplicationLicense;
import com.adeplus.liferay.portlet.suite.manager.model.CompClientApplication;
import com.adeplus.liferay.portlet.suite.manager.model.UserCompany;
import com.adeplus.liferay.portlet.suite.manager.service.ApplicationLicenseLocalServiceUtil;
import com.adeplus.liferay.portlet.suite.manager.service.CompClientApplicationLocalServiceUtil;
import com.adeplus.liferay.portlet.suite.manager.service.UserCompanyLocalServiceUtil;
import com.adeplus.liferay.portlet.suite.manager.service.persistence.CompClientApplicationPK;
import com.legalplus.liferay.portlet.commons.web.constants.LegalPlusCommonsPortletKeys;
import com.legalplus.liferay.portlet.commons.web.version.LegalPlusVersion;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.PrefsPropsUtil;

import java.util.List;

public class LegalplusRoleUtil {

    public static Log _log = LogFactoryUtil.getLog(LegalplusRoleUtil.class);

    public static String getAdministratorRol() {
        return PrefsPropsUtil.getString(LegalPlusCommonsPortletKeys.PROPERTY_ROLE_LEGAL_ADMINISTRADOR);
    }

    public static String getAlimentadorRol() {
        return PrefsPropsUtil.getString(LegalPlusCommonsPortletKeys.PROPERTY_ROLE_LEGAL_ALIMENTADOR);
    }

    public static String getConsultorRol() {
        return PrefsPropsUtil.getString(LegalPlusCommonsPortletKeys.PROPERTY_ROLE_LEGAL_CONSULTOR);
    }

    public static boolean isAdministradorRole(long companyId, User user){
        String roleName = PrefsPropsUtil.getString(LegalPlusCommonsPortletKeys.PROPERTY_ROLE_LEGAL_ADMINISTRADOR);
        if(Validator.isNull(user)){
            return false;
        }
        return hasRole(companyId, roleName, user.getUserId());
    }

    public static boolean isAlimentadorRole(long companyId, User user){
        String roleName = PrefsPropsUtil.getString(LegalPlusCommonsPortletKeys.PROPERTY_ROLE_LEGAL_ALIMENTADOR);
        if(Validator.isNull(user)){
            return false;
        }
        return hasRole(companyId, roleName, user.getUserId());
    }

    public static boolean isConsultorRole(long companyId, User user){
        String roleName = PrefsPropsUtil.getString(LegalPlusCommonsPortletKeys.PROPERTY_ROLE_LEGAL_CONSULTOR);
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

    public static ApplicationLicense getLicenseNew(long empresaId){
        ApplicationLicense license=null;
        CompClientApplication aplicationsAndLicense = CompClientApplicationLocalServiceUtil.fetchCompClientApplication(empresaId);
        if(Validator.isNotNull(aplicationsAndLicense)){
            try {
                JSONArray jsonClientContratApps = JSONFactoryUtil.createJSONArray(aplicationsAndLicense.getApplicationId());
                for (Object objApp : jsonClientContratApps) {
                    JSONObject jsonApp = (JSONObject) objApp;
                    if (jsonApp.getJSONArray("licenses").length() > 0) {
                        _log.info("idlicencia: "+jsonApp.getJSONArray("licenses").get(0).toString());
                        license = ApplicationLicenseLocalServiceUtil.fetchApplicationLicense(Long.parseLong(jsonApp.getJSONArray("licenses").get(0).toString()));
                    }

                }

            } catch (Exception e) {
                //_log.error ("error al inicializar el array")
            }
        }
        return license;
    }

    public static UserCompany getUserDefaultCompany(long userId)  {
        _log.info("---> Intermediario para el resto de Aplicaciones de la suite");
        UserCompany userCompany = null;
        long idEmpresa = 0; long idComp = 0;
        if(!LegalPlusVersion.itsNewVersion()){
            userCompany = UserCompanyLocalServiceUtil.getUserDefaultCompany(userId);

        }else{
            _log.info("---> Realizamos el cambio para obtener alternar entre el compId y el idEmpresa");
            userCompany = UserCompanyLocalServiceUtil.getUserDefaultCompany(userId);
            if(Validator.isNotNull(userCompany)){
                idEmpresa=userCompany.getDefaultEmpresaId();
                idComp=userCompany.getCompId();
                userCompany.setCompId(idEmpresa);
                userCompany.setDefaultEmpresaId(idComp);
            }
        }
        return userCompany;
    }
/*
    public static ApplicationLicense getLicenseNew(long compId, long clientId, long contractId){
        ApplicationLicense license=null;
        if(clientId > 0 && contractId >0) {
            CompClientApplication aplicationsAndLicense = CompClientApplicationLocalServiceUtil.getClientByCompAndClientAndContract(compId,clientId,contractId);
            if(Validator.isNotNull(aplicationsAndLicense)){
                try {
                    JSONArray jsonClientContratApps = JSONFactoryUtil.createJSONArray(aplicationsAndLicense.getApplicationId());
                    for (Object objApp : jsonClientContratApps) {
                        JSONObject jsonApp = (JSONObject) objApp;
                        if (jsonApp.getJSONArray("licenses").length() > 0) {
                            license = ApplicationLicenseLocalServiceUtil.fetchApplicationLicense(Long.parseLong(jsonApp.getJSONArray("licenses").get(0).toString()));
                        }

                    }

                } catch (Exception e) {
                    //_log.error ("error al inicializar el array")
                }
            }

        }else {
            List<CompClientApplication> aplicationsAndLicenses = CompClientApplicationLocalServiceUtil.getClientsByCompanyId(compId);
            if (Validator.isNotNull(aplicationsAndLicenses) && aplicationsAndLicenses.size() > 0) {
                for (CompClientApplication aplicationsAndLicense : aplicationsAndLicenses) {
                    try {
                        JSONArray jsonClientContratApps = JSONFactoryUtil.createJSONArray(aplicationsAndLicense.getApplicationId());
                        for (Object objApp : jsonClientContratApps) {
                            JSONObject jsonApp = (JSONObject) objApp;
                            if (jsonApp.getJSONArray("licenses").length() > 0) {
                                license = ApplicationLicenseLocalServiceUtil.fetchApplicationLicense(Long.parseLong(jsonApp.getJSONArray("licenses").get(0).toString()));
                            }
                        }
                    } catch (Exception e) {
                        //_log.error ("error al inicializar el array")
                    }
                }
            }
        }
        return license;
    }

 */
}

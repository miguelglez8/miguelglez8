package com.adeplus.liferay.portlet.commons.web.permission;

import com.adeplus.liferay.portlet.commons.web.bean.AppPermission;
import com.adeplus.liferay.portlet.commons.web.constants.AdeplusAuditPortletKeys;
import com.adeplus.liferay.portlet.commons.web.audit.AdeplusAuditUtil;
import com.adeplus.liferay.portlet.suite.manager.model.Application;
import com.adeplus.liferay.portlet.suite.manager.model.CompApplication;
import com.adeplus.liferay.portlet.suite.manager.model.UserApplication;
import com.adeplus.liferay.portlet.suite.manager.model.UserCompany;
import com.adeplus.liferay.portlet.suite.manager.service.ApplicationLocalServiceUtil;
import com.adeplus.liferay.portlet.suite.manager.service.CompApplicationLocalServiceUtil;
import com.adeplus.liferay.portlet.suite.manager.service.UserApplicationLocalServiceUtil;
import com.adeplus.liferay.portlet.suite.manager.service.UserCompanyLocalServiceUtil;
import com.adeplus.liferay.portlet.suite.manager.service.persistence.CompApplicationPK;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AdeplusPermissionCompanyUtil {

    private static Log _log = LogFactoryUtil.getLog(AdeplusPermissionCompanyUtil.class);

    public static boolean saveCompanyPermissionApplications(long companyId, List<AppPermission> listAppPermission){

        boolean changeAppList = false;
        CompApplicationPK pk = null;
        CompApplication compApplication = null;
        CompApplication companyApplication = null;
        for(AppPermission ap:listAppPermission){
            companyApplication = CompApplicationLocalServiceUtil.getCompanyApplication(companyId, ap.getApplication().getApplicationId());

            if(ap.isHasPermission()){
                if(Validator.isNull(companyApplication)){
                    pk = new CompApplicationPK(companyId, ap.getApplication().getApplicationId());
                    compApplication = CompApplicationLocalServiceUtil.createCompApplication(pk);
                    compApplication.setLicenseId(ap.getLicenseId());

                    CompApplicationLocalServiceUtil.addCompApplication(compApplication);
                    AdeplusAuditUtil.addAudit(companyId, 0, 0, AdeplusAuditPortletKeys.AUDIT_COMPANY_ADD_PERMISSION, "Add permission to "+ companyId +" for " + ap.getApplication().getName() + ".");
                    changeAppList = true;
                
                }else{

                    companyApplication.setDeleteDate(null);
                    companyApplication.setLicenseId(ap.getLicenseId());

                    CompApplicationLocalServiceUtil.updateCompApplication(companyApplication);
                    AdeplusAuditUtil.addAudit(companyId, 0, 0, AdeplusAuditPortletKeys.AUDIT_COMPANY_ADD_PERMISSION, "Add permission to "+ companyId +" for " + ap.getApplication().getName() + ". Exists before.");
                    changeAppList = true;
                }
            }else{ //not hasPermission
                if(Validator.isNotNull(companyApplication)){
                    companyApplication.setDeleteDate(new Date());
                    companyApplication.setLicenseId(ap.getLicenseId());
                    
                    CompApplicationLocalServiceUtil.updateCompApplication(companyApplication);
                    deletePermissionApplicationToUsers(companyId, ap.getApplication().getApplicationId());
                    AdeplusAuditUtil.addAudit(companyId, 0, 0, AdeplusAuditPortletKeys.AUDIT_COMPANY_DELETE_PERMISSION, "Delete permission to "+ companyId +" for " + ap.getApplication().getName() + ".");
                    changeAppList = true;
                }

            }
        }
        
        return changeAppList;
    }

    public static List<Application> getCompanyApplications(long companyId){
		List<Application> result = new ArrayList<>();
    	try {
            List<CompApplication> list = CompApplicationLocalServiceUtil.getApplicationsFromCompany(companyId);
            Application app = null;
            for(CompApplication ca : list){
                    app = ApplicationLocalServiceUtil.getApplication(ca.getApplicationId());
                    result.add(app);            }
    		
    	 }catch (PortalException e){
             _log.error("getCompanyApplications: " + e);
         }
    	
        return result;
    }

    public static String getCompanyApplicationNames(long companyId){

        String result = "";
        try {
            List<CompApplication> list = CompApplicationLocalServiceUtil.getApplicationsFromCompany(companyId);
            int conta = 0;
            for(CompApplication ca : list){

                    Application app = ApplicationLocalServiceUtil.fetchApplication(ca.getApplicationId());
                    if(Validator.isNotNull(app)) {
                        result += app.getName();
                        conta++;
                        if (list.size() > conta) {
                            result += ", ";
                        }
                    }
            }
        } catch (Exception e) {
            _log.error(e);
        }

        return result;
    }

    public static boolean hasCompanyApplication(long companyId, long applicationId){
        return CompApplicationLocalServiceUtil.hasCompanyApplication(companyId, applicationId);
    }

    public static void deletePermissionApplicationToUsers(long compId, long applicationId){

        List<UserCompany> usersFromCompany = UserCompanyLocalServiceUtil.getUsersFromCompany(compId);
        UserApplication userApplication = null;
        Date now = new Date();

        for(UserCompany uc: usersFromCompany){
            userApplication = UserApplicationLocalServiceUtil.getUserCompanyApplication(uc.getUserId(), compId, applicationId);
            if(Validator.isNotNull(userApplication)) {
                userApplication.setDeleteDate(now);
                UserApplicationLocalServiceUtil.updateUserApplication(userApplication);
                AdeplusAuditUtil.addAudit(compId, 0, uc.getUserId(), AdeplusAuditPortletKeys.AUDIT_USER_DELETE_PERMISSION, "Delete permission to "+ uc.getUserId() +" for " + applicationId + ".");
            }
        }

    }

    public static List<CompApplication> getDeletedApps(List<CompApplication> listBefore, List<CompApplication> listAfter){

        List<CompApplication> result = new ArrayList<>();
        for(CompApplication ca:listBefore){
            if(!listAfter.contains(ca)){
                result.add(ca);
            }
        }
        return result;
    }

    public static List<CompApplication> getAddedApps(List<CompApplication> listBefore, List<CompApplication> listAfter){

        List<CompApplication> result = new ArrayList<>();
        
        for(CompApplication ca:listAfter){
            if(!listBefore.contains(ca)){
                result.add(ca);
            }
        }
        return result;
    }

}

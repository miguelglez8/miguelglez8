package com.adeplus.liferay.portlet.company.web.action;

import com.adeplus.liferay.portlet.commons.web.bean.AppPermission;
import com.adeplus.liferay.portlet.commons.web.company.AdeplusCompanyUtil;
import com.adeplus.liferay.portlet.commons.web.keycloak.AdeplusKeycloakUtil;
import com.adeplus.liferay.portlet.commons.web.permission.AdeplusPermissionCompanyUtil;
import com.adeplus.liferay.portlet.commons.web.permission.AdeplusPermissionUserUtil;
import com.adeplus.liferay.portlet.commons.web.user.AdeplusUserUtil;
import com.adeplus.liferay.portlet.company.web.constants.AdeplusCompaniesPortletKeys;
import com.adeplus.liferay.portlet.mailing.web.mail.CompanyMailing;
import com.adeplus.liferay.portlet.mailing.web.mail.UserMailing;
import com.adeplus.liferay.portlet.suite.manager.model.*;
import com.adeplus.liferay.portlet.suite.manager.service.*;
import com.adeplus.liferay.portlet.suite.manager.service.persistence.UserRolePK;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskManagerUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.*;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + AdeplusCompaniesPortletKeys.ADEPLUSCOMPANIES,
                "mvc.command.name=/company/save"
        },
        service = MVCActionCommand.class
)
public class SaveCompanyMVCActionCommand implements MVCActionCommand {

    private static Log _log = LogFactoryUtil.getLog(SaveCompanyMVCActionCommand.class);

    @Override
    public boolean processAction(ActionRequest actionRequest, ActionResponse actionResponse) throws PortletException {
    	
        ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

        Comp company = null;

        long companyEditId 	= ParamUtil.getLong(actionRequest, AdeplusCompaniesPortletKeys.COMPANY_ID_EDIT, 0);
        String name 		= ParamUtil.getString(actionRequest, AdeplusCompaniesPortletKeys.COMPANY_NAME, "");
        String cif 			= ParamUtil.getString(actionRequest, AdeplusCompaniesPortletKeys.COMPANY_CIF, "");
        String description 	= ParamUtil.getString(actionRequest, AdeplusCompaniesPortletKeys.COMPANY_DESCRIPTION, "");
        String agreement 	= ParamUtil.getString(actionRequest, AdeplusCompaniesPortletKeys.COMPANY_AGREEMENT, "");
        String observation 	= ParamUtil.getString(actionRequest, AdeplusCompaniesPortletKeys.COMPANY_OBSERVATION, "");
        String deleteDateStr = ParamUtil.getString(actionRequest, AdeplusCompaniesPortletKeys.COMPANY_DATE_END, "");

        String userName 	= ParamUtil.getString(actionRequest, AdeplusCompaniesPortletKeys.USER_NAME, "");
        String lastName 	= ParamUtil.getString(actionRequest, AdeplusCompaniesPortletKeys.USER_LAST_NAME, "");
        String nif		 	= ParamUtil.getString(actionRequest, AdeplusCompaniesPortletKeys.USER_NIF, "");
        String email		= ParamUtil.getString(actionRequest, AdeplusCompaniesPortletKeys.USER_EMAIL, "");
        String locale		= ParamUtil.getString(actionRequest, AdeplusCompaniesPortletKeys.USER_LANGUAGE, "");
        String idCRM		= ParamUtil.getString(actionRequest, AdeplusCompaniesPortletKeys.USER_ID_CRM, "");
        String idContrato		= ParamUtil.getString(actionRequest, AdeplusCompaniesPortletKeys.USER_ID_CONTRATO, "");

        try {
            String passwordRandon = AdeplusUserUtil.generatePasswordRandon();

            _log.debug("---> pass generado");


            //Get selected permissions.
            List<AppPermission> appPermissionList = new ArrayList<>();
            List<Application> appList = ApplicationLocalServiceUtil.getApplications(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
            String checkBoxValue = null;
            _log.debug("applist: " + appList.size());
            for (Application app : appList) {
                checkBoxValue = ParamUtil.getString(actionRequest, String.valueOf(app.getApplicationId()));
                long licenseId = 0;
                if ("true".equals(checkBoxValue)) {
                    licenseId = ParamUtil.getLong(actionRequest, "app_" + app.getApplicationId());
                    _log.debug("Application " + app.getName() + " licenseId " + licenseId);
                }

                appPermissionList.add(new AppPermission(app, "true".equals(checkBoxValue), licenseId));
            }


            Date deleteDate = AdeplusUserUtil.getDateFromString(deleteDateStr);

            if (Validator.isNull(CompLocalServiceUtil.getComp(companyEditId))) {

                Comp compByCif = CompLocalServiceUtil.getCompByCif(cif);
                if (Validator.isNotNull(compByCif)) {
                    _log.debug("Company CIF already exists: " + cif);
                    SessionErrors.add(actionRequest, "error-create-company");
                    actionResponse.setRenderParameter(AdeplusCompaniesPortletKeys.COMPANY_ID_EDIT, "0");
                    actionResponse.setRenderParameter("jspPage", "/company.jsp");
                    return true;
                }


                company = AdeplusCompanyUtil.createCompany(companyEditId, name, cif, description, observation, agreement, deleteDate, idCRM, idContrato);
                AdeplusPermissionCompanyUtil.saveCompanyPermissionApplications(company.getCompId(), appPermissionList);


                _log.info("---> permisos guardados");

                //AQUI

                boolean isNew = true;
                User userByEmail = AdeplusUserUtil.getUserByEmail(themeDisplay.getCompanyId(), email);
                _log.debug("---> user por su email");

                if (Validator.isNotNull(userByEmail)) isNew = false;

                _log.info("---> inicio crear usuario");

                User user = AdeplusUserUtil.createUser(
                        themeDisplay.getUserId(), themeDisplay.getCompanyId(),
                        themeDisplay.getPortalURL(), themeDisplay.getPathMain(),
                        userName, lastName, nif, email, passwordRandon, "", true,
                        "", companyEditId, LocaleUtil.fromLanguageId(locale));

                AdeplusPermissionUserUtil.saveUserPermissionApplications(user, company.getCompId(), appPermissionList);
                setRoleClienteDefault(appPermissionList, user, company.getCompId());
                setRoleClienteEticoDefault(appPermissionList, user, company.getCompId());
                AdeplusKeycloakUtil.createCompanyKeycloak(companyEditId, name, cif, description, observation, deleteDate, idCRM, idContrato);
                AdeplusUserUtil.createUserKeycloak(companyEditId, user, nif, "", passwordRandon, "", true, appPermissionList);

                //sendKeycloackCompanyBackground(companyEditId, name, cif, description, observation, deleteDate, themeDisplay);
                //sendKeycloackUserBackground(companyEditId,user, nif, "", true,passwordRandon,themeDisplay);

                //Send mail to created user admin.
                if (isNew) {
                    UserMailing.userCreate(user, company.getCompId(), passwordRandon, false);
                    //sendEmailBackground( user, BackTaskEmail.EMAIL_NEW_USER, passwordRandon,companyEditId,themeDisplay);
                } else {
                    UserMailing.userUpdate(user, companyEditId,false);
                    //sendEmailBackground( user, BackTaskEmail.EMAIL_UPDATE_USER, null,companyEditId,themeDisplay);
                }

                if (_log.isDebugEnabled()) {
                    _log.debug("Create New Company and admin");
                    _log.debug(" - name: " + name);
                    _log.debug(" - cif: " + cif);
                    _log.debug(" - userName: " + userName);
                    _log.debug(" - lastName: " + lastName);
                    _log.debug(" - nif: " + nif);
                    _log.debug(" - email: " + email);
                    _log.debug(" - Permission: ");
                    for (AppPermission app : appPermissionList) {
                        _log.debug("  * App: " + app.getApplication().getName() + " hasPermission: " + app.isHasPermission());
                    }
                }

            } else {

                //Update company
                company = AdeplusCompanyUtil.updateCompany(companyEditId, name, cif, description, observation, agreement, deleteDate, idCRM, idContrato);

                List<CompApplication> applicationsFromCompanyOld = CompApplicationLocalServiceUtil.getApplicationsFromCompany(companyEditId);

                //Save permission of all applications for company.
                boolean changeAppList = AdeplusPermissionCompanyUtil.saveCompanyPermissionApplications(company.getCompId(), appPermissionList);

                List<CompApplication> applicationsFromCompanyNew = CompApplicationLocalServiceUtil.getApplicationsFromCompany(companyEditId);

                List<CompApplication> addedApps = AdeplusPermissionCompanyUtil.getAddedApps(applicationsFromCompanyOld, applicationsFromCompanyNew);
                List<CompApplication> deletedApps = AdeplusPermissionCompanyUtil.getDeletedApps(applicationsFromCompanyOld, applicationsFromCompanyNew);

                //Save permission for all Administrators in company.
                List<UserCompany> adminUsersFromCompany = UserCompanyLocalServiceUtil.getAdminUsersFromCompany(companyEditId);
                User user = null;
                for (UserCompany uc : adminUsersFromCompany) {

                    user = UserLocalServiceUtil.fetchUser(uc.getUserId());

                    if (Validator.isNotNull(user)) {

                        AdeplusPermissionUserUtil.saveUserPermissionApplications(user, companyEditId, appPermissionList);

                        //actualizar en KC si existe el usuario
                    /*_log.info("Actualizando en kc: " + user.getEmailAddress());
                    try {
                        boolean isAdmin = UserCompanyLocalServiceUtil.isUserAdminInComp(user.getUserId(), companyEditId);
                        _log.info("Actualizando en kc  - isAdmin: " + isAdmin);
                        AdeplusUserUtil.createUserKeycloak(companyEditId, user, nif, user.getPhones().get(0).getNumber(),
                                null, "", isAdmin, appPermissionList);
                    }catch(Exception e){
                        _log.error("update KC: " + e.toString());
                        e.printStackTrace();
                    }*/
                        //Send mail to admin with changes in apps.
                        if (addedApps.size() > 0) {
                            CompanyMailing.companyAddApplication(user, companyEditId, addedApps);
                        }

                        if (deletedApps.size() > 0) {
                            CompanyMailing.companyDeleteApplication(user, companyEditId, deletedApps);
                        }

                    }
                }
                AdeplusKeycloakUtil.createCompanyKeycloak(companyEditId, name, cif, description, observation, deleteDate, idCRM, idContrato);
                //sendKeycloackCompanyBackground(companyEditId, name, cif, description, observation, deleteDate, themeDisplay);

                if (_log.isDebugEnabled()) {
                    _log.debug("Update Company");
                    _log.debug(" - name: " + name);
                    _log.debug(" - cif: " + cif);
                    _log.debug(" - Permission: ");
                    for (AppPermission app : appPermissionList) {
                        _log.debug("  * App: " + app.getApplication().getName() + " hasPermission: " + app.isHasPermission());
                    }
                }
            }

            SessionMessages.add(actionRequest, "company-save-success");

            actionResponse.setRenderParameter(AdeplusCompaniesPortletKeys.COMPANY_ID_EDIT, String.valueOf(company.getCompId()));
            actionResponse.setRenderParameter("jspPage", "/company.jsp");


        }catch (Exception e){e.printStackTrace();}

        return false;
    }

    private void setRoleClienteDefault(List<AppPermission> appPermissionList,User user, long idComp) {
        for(AppPermission ap:appPermissionList){
            if (ap.getApplication().getAlias().contains("IGUALDAD") && ap.isHasPermission()){
                List<Role> roles= RoleLocalServiceUtil.getRoleByApplicationId(ap.getApplication().getApplicationId());
                for(Role rol:roles){
                    String roleName = PrefsPropsUtil.getString("plan.igualdad.rol.cliente");
                    if(rol.getAlias().equals(roleName)){
                        UserRolePK userRolePK=new UserRolePK(user.getUserId(),idComp,rol.getRoleId());
                        UserRole ur=UserRoleLocalServiceUtil.createUserRole(userRolePK);
                        UserRoleLocalServiceUtil.addUserRole(ur);
                        UserRoleLocalServiceUtil.updateUserRole(ur);
                    }
                }
            }
        }
    }
    private void setRoleClienteEticoDefault(List<AppPermission> appPermissionList,User user, long idComp) {
        for(AppPermission ap:appPermissionList){
            if (ap.getApplication().getAlias().contains("DENUNCIAS") && ap.isHasPermission()){
                List<Role> roles= RoleLocalServiceUtil.getRoleByApplicationId(ap.getApplication().getApplicationId());
                for(Role rol:roles){
                    if(rol.getAlias().equals("Acceso completo")){
                        UserRolePK userRolePK=new UserRolePK(user.getUserId(),idComp,rol.getRoleId());
                        UserRole ur=UserRoleLocalServiceUtil.createUserRole(userRolePK);
                        UserRoleLocalServiceUtil.addUserRole(ur);
                        UserRoleLocalServiceUtil.updateUserRole(ur);
                    }
                }

            }

        }

    }

    private void  sendKeycloackCompanyBackground(long companyEditId, String name, String cif, String description,
                                                 String observation, Date deleteDate, ThemeDisplay themeDisplay){
        Map map = new HashMap();
        map.put("name",name);
        map.put("cif",cif);
        map.put("companyEditId",companyEditId);
        map.put("description",description);
        map.put("observation",observation);
        map.put("deleteDate",deleteDate);
        try {
           /* BackgroundTaskManagerUtil.addBackgroundTask(themeDisplay.getUserId(), themeDisplay.getScopeGroupId(),
                    BackTaskKeycloakEmpresa.class.getName(), BackTaskKeycloakEmpresa.class.getName(), map,
                    new ServiceContext());*/
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void  sendKeycloackUserBackground(long companyEditId,User user, String nif,
                                              String phone, boolean nuevo, String passwordRandon, ThemeDisplay themeDisplay){
        Map map = new HashMap();
        map.put("userId",user.getUserId());
        map.put("nif",nif);
        map.put("phone",phone);
        map.put("companyEditId",companyEditId);
        map.put("nuevo",nuevo);
        map.put("passwordRandon",passwordRandon);
        try {
            /*BackgroundTaskManagerUtil.addBackgroundTask(themeDisplay.getUserId(), themeDisplay.getScopeGroupId(),
                    BackTaskKeycloak.class.getName(), BackTaskKeycloak.class.getName(), map,
                    new ServiceContext());*/
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void sendEmailBackground(User user, int action, String passwordRandon,
                                     long companyEditId, ThemeDisplay themeDisplay) {
        Map map = new HashMap();
        map.put("userId",user.getUserId());
        map.put("action",action);
        map.put("companyEditId",companyEditId);
        map.put("passwordRandon",passwordRandon);
        

        try {
            BackgroundTaskManagerUtil.addBackgroundTask(themeDisplay.getUserId(), themeDisplay.getScopeGroupId(),
                    BackTaskEmail.class.getName(), BackTaskEmail.class.getName(), map,
                    new ServiceContext());
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}

package com.adeplus.liferay.portlet.company.web.action;

import com.adeplus.liferay.portlet.commons.web.bean.AppPermission;
import com.adeplus.liferay.portlet.commons.web.client.AdeplusClientUtil;
import com.adeplus.liferay.portlet.commons.web.company.AdeplusCompanyUtil;
import com.adeplus.liferay.portlet.commons.web.keycloak.AdeplusKeycloakMultiUtil;
import com.adeplus.liferay.portlet.commons.web.keycloak.AdeplusKeycloakUtil;
import com.adeplus.liferay.portlet.commons.web.permission.AdeplusPermissionUserUtil;
import com.adeplus.liferay.portlet.commons.web.user.AdeplusUserUtil;
import com.adeplus.liferay.portlet.commons.web.user.AdeplusUserUtilMulti;
import com.adeplus.liferay.portlet.company.web.constants.AdeplusCompaniesPortletKeys;
import com.adeplus.liferay.portlet.mailing.web.mail.UserMailing;
import com.adeplus.liferay.portlet.suite.manager.model.*;
import com.adeplus.liferay.portlet.suite.manager.service.*;

import com.adeplus.liferay.portlet.suite.manager.service.persistence.CompClientApplicationPK;
import com.adeplus.liferay.portlet.suite.manager.service.persistence.UserApplicationClientPK;
import com.adeplus.liferay.portlet.suite.manager.service.persistence.UserCompanyPK;
import com.liferay.journal.service.JournalArticleService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + AdeplusCompaniesPortletKeys.ADEPLUSCOMPANIESMULTI,
                "mvc.command.name=/company/saveMulti"
        },
        service = MVCActionCommand.class
)
public class SaveCompanyMultiMVCActionCommand implements MVCActionCommand{
    private static Log _log = LogFactoryUtil.getLog(SaveCompanyMultiMVCActionCommand.class);
    @Override
    public boolean processAction(ActionRequest actionRequest, ActionResponse actionResponse) throws PortletException {
        ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

        Comp company = null;
        CompClientApplication client =null;
        List<CompClientApplication> allClients=new ArrayList<CompClientApplication>();
        long companyEditId 	= ParamUtil.getLong(actionRequest, AdeplusCompaniesPortletKeys.COMPANY_ID_EDIT, 0);
        String name 		= ParamUtil.getString(actionRequest, AdeplusCompaniesPortletKeys.COMPANY_NAME, StringPool.BLANK);
        String cif 			= ParamUtil.getString(actionRequest, AdeplusCompaniesPortletKeys.COMPANY_CIF, StringPool.BLANK);
        String description 	= ParamUtil.getString(actionRequest, AdeplusCompaniesPortletKeys.COMPANY_DESCRIPTION, StringPool.BLANK);
        String agreement 	= ParamUtil.getString(actionRequest, AdeplusCompaniesPortletKeys.COMPANY_AGREEMENT, StringPool.BLANK);
        String observation 	= ParamUtil.getString(actionRequest, AdeplusCompaniesPortletKeys.COMPANY_OBSERVATION, StringPool.BLANK);
        String deleteDateStr = ParamUtil.getString(actionRequest, AdeplusCompaniesPortletKeys.COMPANY_DATE_END, StringPool.BLANK);

        String userName 	= ParamUtil.getString(actionRequest, AdeplusCompaniesPortletKeys.USER_NAME, StringPool.BLANK);
        String lastName 	= ParamUtil.getString(actionRequest, AdeplusCompaniesPortletKeys.USER_LAST_NAME, StringPool.BLANK);
        String nif		 	= ParamUtil.getString(actionRequest, AdeplusCompaniesPortletKeys.USER_NIF, StringPool.BLANK);
        String email		= ParamUtil.getString(actionRequest, AdeplusCompaniesPortletKeys.USER_EMAIL, StringPool.BLANK);
        String locale		= ParamUtil.getString(actionRequest, AdeplusCompaniesPortletKeys.USER_LANGUAGE, StringPool.BLANK);
        String deleteClientContract = ParamUtil.getString(actionRequest, AdeplusCompaniesPortletKeys.CLIENT_CONTRACT_DELETE, StringPool.BLANK);
        String json =  ParamUtil.getString(actionRequest, "jsonClientContractApps","");
        String jsonModified =  ParamUtil.getString(actionRequest, "modifiedClientContract","");
        String passwordRandon = null;
        JSONArray jsonArrayModified = null;
        JSONObject jsonObjectModified = null;

        _log.info("json string: " + json);
        JSONArray jsonArray = null;
        JSONObject jsonObject = null;
        if(!json.isEmpty()){
            try {
                jsonArray = JSONFactoryUtil.createJSONArray(json);
                if(!jsonModified.isEmpty()){
                    jsonArrayModified = JSONFactoryUtil.createJSONArray(jsonModified);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            jsonObject = (JSONObject) jsonArray.get(0);
            _log.info("JSON nombre: " + jsonObject.get("nombre"));
        }



        _log.debug("---> pass generado");

        //Get selected permissions.
        List<AppPermission> appPermissionList = new ArrayList<>();
        List<Application> appList = ApplicationLocalServiceUtil.getApplications(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
        String checkBoxValue = null;
        _log.debug("applist: " + appList.size());
        for(Application app:appList){
            checkBoxValue = ParamUtil.getString(actionRequest, String.valueOf(app.getApplicationId()));
            long licenseId = 0;
            if("true".equals(checkBoxValue)){
                licenseId = ParamUtil.getLong(actionRequest, "app_" + app.getApplicationId());
                _log.debug("Application " + app.getName() + " licenseId " + licenseId);
            }

            appPermissionList.add(new AppPermission(app,"true".equals(checkBoxValue), licenseId));
        }



        Date deleteDate = AdeplusUserUtil.getDateFromString(deleteDateStr);

        Comp comp = null;
        try{
            comp = CompLocalServiceUtil.getComp(companyEditId);
        }catch (Exception e){}
        if(Validator.isNull(comp)){
            company = AdeplusCompanyUtil.createCompany(companyEditId, name, cif, description, observation, agreement, deleteDate,"","");
            for(int i = 0; i < jsonArray.length(); i++){
                jsonObject = jsonArray.getJSONObject(i);
                _log.info("jsonObject apps: " + jsonObject.toString());
                if(!jsonObject.isNull("apps")){
                    allClients.add(AdeplusClientUtil.createClient(companyEditId, Long.valueOf(jsonObject.getString("cliente")), Long.valueOf(jsonObject.getString("contrato")),
                            jsonObject.getJSONArray("apps").toString(), cif, name, jsonObject.getString("nombre")));

                }

            }

            boolean isNew = true;
            User userByEmail = AdeplusUserUtil.getUserByEmail(themeDisplay.getCompanyId(), email);
            _log.debug("---> user por su email");

            if(Validator.isNotNull(userByEmail)) isNew = false;

            _log.debug("---> inicio crear usuario");
            passwordRandon = AdeplusUserUtil.generatePasswordRandon();
            User user = AdeplusUserUtil.createUser(
                    themeDisplay.getUserId(), themeDisplay.getCompanyId(),
                    themeDisplay.getPortalURL(), themeDisplay.getPathMain(),
                    userName, lastName, nif, email, passwordRandon, "" , true,
                    "",companyEditId, LocaleUtil.fromLanguageId(locale));


            //AdeplusPermissionUserUtil.saveUserPermissionApplications(user, company.getCompId(), appPermissionList);
            AdeplusPermissionUserUtil.saveUserPermissionApplicationsMulti(user, companyEditId, jsonArray,true, true);
            //Send mail to created user admin.
            if(isNew) {
                UserMailing.userCreate(user, company.getCompId(), passwordRandon, true);
            }else{
                UserMailing.userUpdate(user, companyEditId, true);
            }

            AdeplusKeycloakMultiUtil.createCompanyKeycloak(companyEditId, name, cif, description, observation, deleteDate);
            AdeplusUserUtilMulti.createUserKeycloak(companyEditId, user, nif, "", passwordRandon, "", true,true);

        }else{
            //Update company
           company = AdeplusCompanyUtil.updateCompany(companyEditId, name, cif, description, observation, agreement, deleteDate,"","");
            List<CompClientApplication> listEliminados = new ArrayList<>();
            //Update Client
            if(!Validator.isBlank(deleteClientContract)){
                listEliminados=setDeleteDateCompClient(deleteClientContract,companyEditId);
            }

            if(Validator.isNotNull(jsonArrayModified)){
                for(int index=0; index<jsonArrayModified.length();index++) {
                    jsonObjectModified = (JSONObject) jsonArrayModified.get(index);
                    modificarClienteContratoUser(jsonObjectModified,companyEditId);
                }
            }
            List<CompClientApplication> listClients = AdeplusClientUtil.getClientFromCompany(companyEditId);
            for(int i = 0; i < jsonArray.length(); i++){
                jsonObject = jsonArray.getJSONObject(i);
                _log.info("jsonObject apps: " + jsonObject.toString());
                if(!jsonObject.isNull("apps")){
                    allClients.add(AdeplusClientUtil.updateClient(companyEditId, Long.valueOf(jsonObject.getString("cliente")), Long.valueOf(jsonObject.getString("contrato")),
                                cif, jsonObject.getJSONArray("apps").toString(), jsonObject.getString("nombre")));

                }
            }
            HashMap mapUuserIdsToUpdateKC = null;
            List<UserApplicationClient> usersApps=UserApplicationClientLocalServiceUtil.getAllUserApplicationsByCompany(companyEditId);
            if(Validator.isNotNull(usersApps)) {
                mapUuserIdsToUpdateKC = removeAppPermison(usersApps,companyEditId);
            }
            //Si el usuario lo tiene y el comClientAPP no significa que se ha borrado si es a la inversa se añadio
            AdeplusKeycloakMultiUtil.createCompanyKeycloak(companyEditId, name, cif, description, observation, deleteDate);
            List<User> users = new ArrayList<>();
            for(UserApplicationClient userApp:usersApps ){
                try{
                    users.add(UserLocalServiceUtil.getUser(userApp.getUserId()));
                }catch(Exception e){
                    continue;
                }
            }

            //Arrastrar kc los apps
            UserCompany userCompany = null;
            JSONArray jsonAppsResult = null;
            JSONArray jsonApps = null;
            JSONObject jsonContrato = null;

            UserCompanyPK userCompanyPk = null;
            List<UserApplicationClient> userApps = null;
            CompClientApplication compApp = null;
            try {
                for (User user:users) {
                    userCompanyPk = new UserCompanyPK();
                    userCompanyPk.setUserId(user.getUserId());
                    userCompanyPk.setCompId(companyEditId);

                    jsonAppsResult = JSONFactoryUtil.createJSONArray();

                    //si no es admin no arrastramos toda el json de la empresa
                    userCompany = UserCompanyLocalServiceUtil.getUserCompany(userCompanyPk);
                    if(Validator.isNotNull(userCompany) && !userCompany.isAdmin()){

                        userApps = UserApplicationClientLocalServiceUtil.getApplicationsFromUser(user.getUserId(),companyEditId);
                        if(Validator.isNotNull(userApps) && userApps.size() > 0){
                            for(UserApplicationClient u : userApps){
                                jsonContrato = JSONFactoryUtil.createJSONObject();
                                jsonContrato.put("cliente", String.valueOf(u.getClientId()));
                                jsonContrato.put("contrato", String.valueOf(u.getContractId()));
                                compApp = CompClientApplicationLocalServiceUtil.fetchCompClientApplication(u.getEmpresaId());
                                if(Validator.isNotNull(compApp)){
                                    jsonContrato.put("nombre", compApp.getDescription());
                                }
                                jsonContrato.put("apps",JSONFactoryUtil.createJSONArray(u.getApplicationId()));
                                jsonAppsResult.put(jsonContrato);
                            }
                        }
                        /*No lanzar sino hay cambios en el usuario: que se quite una app de la empresa que tenia el usuario*/
                        _log.info("user" + user.getEmailAddress() + " NO es admin") ;
                        if(Validator.isNotNull(mapUuserIdsToUpdateKC) && mapUuserIdsToUpdateKC.containsKey(user.getUserId())){
                            AdeplusUserUtilMulti.createUserKeycloak(companyEditId, user, nif, "", passwordRandon, "", true,false,jsonAppsResult);
                        }
                        //AdeplusUserUtilMulti.createUserKeycloak(companyEditId, user, nif, "", passwordRandon, "", true,false,jsonAppsResult);
                    }else{
                        _log.info("user" + user.getEmailAddress() + " SI  es admin") ;
                        AdeplusPermissionUserUtil.saveUserPermissionApplicationsMulti(user, companyEditId, jsonArray,true, true);
                        AdeplusUserUtilMulti.createUserKeycloak(companyEditId, user, nif, "", passwordRandon, "", true,false,jsonArray);
                    }




                }
            }catch(Exception e) {e.printStackTrace();}
        }

        SessionMessages.add(actionRequest, "company-save-success");

        actionResponse.setRenderParameter(AdeplusCompaniesPortletKeys.COMPANY_ID_EDIT, String.valueOf(company.getCompId()));
        actionResponse.setRenderParameter("multi", "1");
        actionResponse.setRenderParameter("jspPage", "/company_MULTI.jsp");

        return false;
    }

    private HashMap removeAppPermison(List<UserApplicationClient> usersApps,long companyEditId) {

        HashMap mapUuserIdsToUpdateKC = new HashMap();
        for (UserApplicationClient userApps : usersApps) {
            CompClientApplication compClient=CompClientApplicationLocalServiceUtil.getClientByCompAndClientAndContract(companyEditId,userApps.getClientId(),userApps.getContractId());
            if(Validator.isNull(compClient)){
                UserApplicationClientLocalServiceUtil.deleteUserApplicationClient(userApps);
            }else{
                JSONArray jsonArray=null;
                JSONObject jsonObject = null;
                JSONArray jsonArrayAdd=JSONFactoryUtil.createJSONArray();
                try {
                    jsonArray = JSONFactoryUtil.createJSONArray(userApps.getApplicationId());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //recorremos el array y vamos insertando en el listado todos los ids de las APPS
                for(int i = 0; i < jsonArray.length(); i++){
                    jsonObject = jsonArray.getJSONObject(i);
                    if(!jsonObject.isNull("appId") &&
                        compClient.getApplicationId().contains((jsonObject.getString("appId")))){
                        //jsonArrayAdd.put(jsonObject);
                    }else{
                        //hay que actualizar
                        mapUuserIdsToUpdateKC.put(userApps.getUserId(), true);
                    }
                    jsonArrayAdd.put(jsonObject);
                }



                _log.info("jsonArrayAdd: " + jsonArrayAdd.toJSONString());
                userApps.setApplicationId(jsonArrayAdd.toString());
                UserApplicationClientLocalServiceUtil.updateUserApplicationClient(userApps);
            }
        }
        return mapUuserIdsToUpdateKC;

    }

    private List<CompClientApplication> setDeleteDateCompClient(String deleteClientContract, long companyEditId){
        List<CompClientApplication> listaEliminados = new ArrayList<>();
        String [] deleteClientContractList = deleteClientContract.split(StringPool.COMMA);
        String [] clientContractDelete ;
        long clientIdToDelete ; long contractIdToDelete ;
        for (int x=0; x<deleteClientContractList.length;x++){
            clientContractDelete = deleteClientContractList[x].split(StringPool.DASH);
            clientIdToDelete = Long.parseLong(clientContractDelete[0]);
            contractIdToDelete = Long.parseLong(clientContractDelete[1]);
            CompClientApplication compClientApplicationToDelete = CompClientApplicationLocalServiceUtil.getClientByCompAndClientAndContract(companyEditId,clientIdToDelete,contractIdToDelete);
            if (Validator.isNotNull(compClientApplicationToDelete)){
                compClientApplicationToDelete.setDeleteDate(new Date());
                CompClientApplicationLocalServiceUtil.updateCompClientApplication(compClientApplicationToDelete);
                listaEliminados.add(compClientApplicationToDelete);
            }
        }
        return listaEliminados;
    }

    public void modificarClienteContratoUser(JSONObject jsonObject,long compId){
        String clienteNuevo =jsonObject.getString("clienteNuevo");
        String clienteAnt =jsonObject.getString("clienteAnt");
        String contratoNuevo =jsonObject.getString("contratoNuevo");
        String contratoAnt =jsonObject.getString("contratoAnt");
        String nombreNuevo =jsonObject.getString("nombreNuevo");
        CompClientApplication compApp=CompClientApplicationLocalServiceUtil.getClientByCompAndClientAndContract(compId,Long.valueOf(clienteAnt),Long.valueOf(contratoAnt));
        compApp.setClientId(Long.valueOf(clienteNuevo));
        compApp.setContractId(Long.valueOf(contratoNuevo));
        compApp.setDescription(nombreNuevo);
        CompClientApplicationLocalServiceUtil.updateCompClientApplication(compApp);
        List<UserApplicationClient> listUsers=UserApplicationClientLocalServiceUtil.getAllUserApplicationsByCompany(compId);
        for(UserApplicationClient user:listUsers){
            if(user.getClientId()==Long.parseLong(clienteAnt) && user.getContractId()==Long.parseLong(contratoAnt)){
                user.setClientId(Long.parseLong(clienteNuevo));
                user.setContractId(Long.parseLong(contratoNuevo));
                UserApplicationClientLocalServiceUtil.updateUserApplicationClient(user);
            }

        }

    }

}

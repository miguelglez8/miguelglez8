package com.adeplus.liferay.portlet.commons.web.permission;

import com.adeplus.liferay.portlet.commons.web.audit.AdeplusAuditUtil;
import com.adeplus.liferay.portlet.commons.web.bean.AppPermission;
import com.adeplus.liferay.portlet.commons.web.constants.AdeplusAuditPortletKeys;
import com.adeplus.liferay.portlet.suite.manager.model.*;
import com.adeplus.liferay.portlet.suite.manager.service.*;
import com.adeplus.liferay.portlet.suite.manager.service.persistence.UserApplicationClientPK;
import com.adeplus.liferay.portlet.suite.manager.service.persistence.UserApplicationPK;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AdeplusPermissionUserUtil {

    private static Log _log = LogFactoryUtil.getLog(AdeplusPermissionUserUtil.class);

    /**
     * Return true is application changed, and false if is the same app list.
     * @param user
     * @param listAppPermission
     * @return
     */
    public static boolean saveUserPermissionApplications(User user, long compId, List<AppPermission> listAppPermission){

        boolean changeAppList = false;
        if(Validator.isNull(user) || Validator.isNull(listAppPermission)){
            //_log.error("The user is null o permission list is null. ");
            return changeAppList;
        }


        UserApplicationPK pk = null;
        UserApplication userApplication = null;
        boolean isNew = false;
        for(AppPermission ap:listAppPermission){
            isNew = false;
            //Get userapplication if exits.

            _log.info("Procesando app ");

            //UserApplicationLocalServiceUtil.getApplicationsFromUser(1,1).get(0).getDeleteDate();
            userApplication = UserApplicationLocalServiceUtil.getUserCompanyApplication(user.getUserId(), compId, ap.getApplication().getApplicationId());
            //_log.error("tiene permiso: " + ap.isHasPermission() + " " + ap.getApplication().getName());
            _log.info("userApplication : " + userApplication);
            if(Validator.isNull(userApplication) && ap.isHasPermission()){
                _log.info("aplicacion: "+ap.getApplication().getAlias());
                pk = new UserApplicationPK(user.getUserId(), compId, ap.getApplication().getApplicationId());
                userApplication = UserApplicationLocalServiceUtil.createUserApplication(pk);
                isNew = true;
            }else if(Validator.isNull(userApplication) && !ap.isHasPermission()){
                continue;
            }

            if(ap.isHasPermission()){
                userApplication.setDeleteDate(null);
            }else{
                userApplication.setDeleteDate(new Date());
            }
            if(isNew
                    || UserApplicationLocalServiceUtil.hasUserApplication(user.getUserId(), compId,ap.getApplication().getApplicationId()) && !ap.isHasPermission()
                        || !UserApplicationLocalServiceUtil.hasUserApplication(user.getUserId(), compId,ap.getApplication().getApplicationId()) && ap.isHasPermission()){
                changeAppList = true;
            }

            if(isNew){
                UserApplicationLocalServiceUtil.addUserApplication(userApplication);
            }else{
                UserApplicationLocalServiceUtil.updateUserApplication(userApplication);
            }

            AdeplusAuditUtil.addAudit(user.getCompanyId(), user.getGroupId(), user.getUserId(), AdeplusAuditPortletKeys.AUDIT_USER_ADD_PERMISSION, "Add permission to "+ user.getEmailAddress() +" for " + ap.getApplication().getName());

            
        }
        return changeAppList;
    }

    public static String getUserApplicationNames(long userId, long compId){

        String result = "";

        List<UserApplication> list = UserApplicationLocalServiceUtil.getApplicationsFromUser(userId, compId);
        Application app = null;
        //int conta = 0;
        for(UserApplication ua : list){
            try {

                app = ApplicationLocalServiceUtil.getApplication(ua.getApplicationId());
                if(result.indexOf(app.getName()) != -1){
                    if(!result.isEmpty()) result += ",";
                    result+= app.getName();
                    //conta++;
                }

                //if(list.size() > conta) result += ", ";

            } catch (PortalException e) {
                _log.error(e);
            }
        }

        return result;
    }
    public static String getUserApplicationNameHtml(long userId, long compId){

        String result = "";

        List<UserApplication> list = UserApplicationLocalServiceUtil.getApplicationsFromUser(userId, compId);
        Application app = null;
        for(UserApplication ua : list){
            try {
                app = ApplicationLocalServiceUtil.getApplication(ua.getApplicationId());
                result+= "<p>" + app.getName() + "</p>";
            } catch (PortalException e) {
                _log.error(e);
            }
        }
        return result;
    }

    //Utilizaremos este metodo para todos los clientes
    public static boolean saveUserPermissionApplicationsMulti(User user, long compId, JSONArray jsonArray, boolean isMaster, boolean isAdmin){
        JSONObject jsonObject=null;
        boolean changeAppList = false;
        long clientId=0;
        long contractId=0;
        if(Validator.isNull(user) || Validator.isNull(jsonArray)){
            //_log.error("The user is null o listApp is null. ");
            return changeAppList;
        }
        List<String> appBefore= new ArrayList<String>();
        List<String> appAfter= new ArrayList<String>();
        UserApplicationClientPK pk = null;
        /*Recorrecoms todas las aplicaciones para el usuario y compañia indicados borrandolos,
          guardando antes sus aplicaciones para poder realizar una comparativa posteriormente*/

        //List<UserApplicationClient> usersApplicationClient= UserApplicationClientLocalServiceUtil.getApplicationsFromUser(user.getUserId(),compId);
        List<UserApplicationClient> usersApplicationClient= UserApplicationClientLocalServiceUtil.getAllApplicationsFromUser(user.getUserId(),compId);
        if(Validator.isNotNull(usersApplicationClient) && usersApplicationClient.size()>0){
            _log.info("listClients: " + usersApplicationClient.size());
            for(UserApplicationClient userApp:usersApplicationClient){
                appBefore.add(userApp.getApplicationId());
                UserApplicationClientLocalServiceUtil.deleteUserApplicationClient(userApp);
            }
        }

        for(int i = 0; i < jsonArray.length(); i++){
            jsonObject = jsonArray.getJSONObject(i);
            _log.info("jsonObject apps: " + jsonObject.toString());
            if(!jsonObject.isNull("apps")){
                String app=jsonObject.getJSONArray("apps").toString();
                clientId=Long.parseLong(jsonObject.getString("cliente"));
                contractId=Long.parseLong(jsonObject.getString("contrato"));
                long idEmpresa=CompClientApplicationLocalServiceUtil.getClientByCompAndClientAndContract(compId,clientId,contractId).getEmpresaId();
                pk = new UserApplicationClientPK( idEmpresa, user.getUserId());
                UserApplicationClient userApp= UserApplicationClientLocalServiceUtil.createUserApplicationClient(pk);
                _log.info(" UPDATE jsonObject.getJSONArray(\"apps\").toString() : " + jsonObject.getJSONArray("apps").toString());
                if(isMaster){
                    try {
                        app=modifedJson(app);
                    } catch (JSONException e) {
                        _log.info("Error al modificar el JSON");
                    }
                }
                userApp.setApplicationId(app);
                userApp.setContractId(contractId);
                userApp.setClientId(clientId);
                userApp.setCompId(compId);
                userApp.setAdmin(isAdmin);
                UserApplicationClientLocalServiceUtil.updateUserApplicationClient(userApp);
                appAfter.add(app);
                AdeplusAuditUtil.addAudit(user.getCompanyId(), user.getGroupId(), user.getUserId(), AdeplusAuditPortletKeys.AUDIT_USER_ADD_PERMISSION, "Add permission to "+ user.getEmailAddress() +" for " + app);

            }else if(!jsonObject.isNull("applicationId")){
                try {
                    String app = jsonObject.getJSONArray("applicationId").toString();
                    clientId = jsonObject.getLong("clientId");
                    contractId = jsonObject.getLong("contractId");
                    long idEmpresa=CompClientApplicationLocalServiceUtil.getClientByCompAndClientAndContract(compId,clientId,contractId).getEmpresaId();
                    pk = new UserApplicationClientPK(idEmpresa, user.getUserId());
                    UserApplicationClient userApp = UserApplicationClientLocalServiceUtil.createUserApplicationClient(pk);
                    //_log.info(" UPDATE jsonObject.getJSONArray(\"apps\").toString() : " + jsonObject.getJSONArray("apps").toString());
                    if(isMaster){
                        app=modifedJson(app);
                    }
                    userApp.setApplicationId(app);
                    userApp.setContractId(contractId);
                    userApp.setClientId(clientId);
                    userApp.setCompId(compId);
                    UserApplicationClientLocalServiceUtil.updateUserApplicationClient(userApp);
                    appAfter.add(app);
                    AdeplusAuditUtil.addAudit(user.getCompanyId(), user.getGroupId(), user.getUserId(), AdeplusAuditPortletKeys.AUDIT_USER_ADD_PERMISSION, "Add permission to " + user.getEmailAddress() + " for " + app);
                }catch (Exception e){
                    _log.info("error: "+e.getMessage());
                }
            }
        }
        //Si al comparar ambas listas no son exactamente iguales entendemos que se modifico alguna APP
        if(!appBefore.containsAll(appAfter)){
            changeAppList=true;
        }
        return changeAppList;
    }
    //Utilizaremos este metodo solo para los clientes seleccionados
    public static boolean saveUserPermissionApplicationsMulti(User user, long compId,long clientId, JSONArray jsonArray) {
        JSONObject jsonObject=null;
        boolean changeAppList = false;
        if(Validator.isNull(user) || Validator.isNull(jsonArray)){
            //_log.error("The user is null o listApp is null. ");
            return changeAppList;
        }
        List<String> appBefore= new ArrayList<String>();
        List<String> appAfter= new ArrayList<String>();
        UserApplicationClientPK pk = null;
        /*Recorrecoms todas las aplicaciones para el usuario y compañia indicados borrandolos,
          guardando antes sus aplicaciones para poder realizar una comparativa posteriormente*/


        for(int i = 0; i < jsonArray.length(); i++){
            jsonObject = jsonArray.getJSONObject(i);
            _log.info("jsonObject apps: " + jsonObject.toString());
            if(!jsonObject.isNull("apps")){
                String app=jsonObject.getJSONArray("apps").toString();
                long clientIdApp=Long.parseLong(jsonObject.getString("cliente"));
                long contractId=Long.parseLong(jsonObject.getString("contrato"));
                long idEmpresa=CompClientApplicationLocalServiceUtil.getClientByCompAndClientAndContract(compId,clientIdApp,contractId).getEmpresaId();
                pk = new UserApplicationClientPK( idEmpresa, user.getUserId());
                UserApplicationClient userApp = UserApplicationClientLocalServiceUtil.fetchUserApplicationClient(pk);
                if(Validator.isNotNull(userApp)){
                    appBefore.add(userApp.getApplicationId());
                    try {
                        UserApplicationClientLocalServiceUtil.deleteUserApplicationClient(pk);
                    }catch (PortalException e){
                        _log.error("No se pudo eliminar las aplicaciones para el usuario");
                    }
                }
                userApp= UserApplicationClientLocalServiceUtil.createUserApplicationClient(pk);
                _log.info(" UPDATE jsonObject.getJSONArray(\"apps\").toString() : " + jsonObject.getJSONArray("apps").toString());
                userApp.setApplicationId(app);
                userApp.setContractId(contractId);
                userApp.setClientId(clientIdApp);
                userApp.setCompId(compId);
                UserApplicationClientLocalServiceUtil.updateUserApplicationClient(userApp);
                appAfter.add(app);
                //AdeplusAuditUtil.addAudit(user.getCompanyId(), user.getGroupId(), user.getUserId(), AdeplusAuditPortletKeys.AUDIT_USER_ADD_PERMISSION, "Add permission to "+ user.getEmailAddress() +" for " + app);

            }
        }
        //Si al comparar ambas listas no son exactamente iguales entendemos que se modifico alguna APP
        if(!appBefore.containsAll(appAfter)){
            changeAppList=true;
        }
        return changeAppList;
    }

    public static boolean saveUserPermissionApplicationsMultiApi(User user, long compId,long contractId,long clientId, String json) throws JSONException {
        boolean changeAppList = false;
        if(Validator.isNull(user) || Validator.isBlank(json)){
            //_log.error("The user is null o listApp is null. ");
            return changeAppList;
        }
        List<String> appBefore= new ArrayList<String>();
        List<String> appAfter= new ArrayList<String>();
        CompClientApplication compApp= CompClientApplicationLocalServiceUtil.getClientByCompAndClientAndContract(compId,clientId,contractId);
        List<UserCompany> listUserComp = UserCompanyLocalServiceUtil.getAdminUsersFromCompany(compId);

        for(UserCompany usuario:listUserComp) {

            UserApplicationClientPK pk = new UserApplicationClientPK(compApp.getEmpresaId(), usuario.getUserId());
        /*Recorrecoms todas las aplicaciones para el usuario y compañia indicados borrandolos,
          guardando antes sus aplicaciones para poder realizar una comparativa posteriormente*/

            UserApplicationClient usersApplication = UserApplicationClientLocalServiceUtil.fetchUserApplicationClient(pk);
            if (Validator.isNotNull(usersApplication)) {
                appBefore.add(usersApplication.getApplicationId());
                UserApplicationClientLocalServiceUtil.deleteUserApplicationClient(usersApplication);
            }
            _log.info("Json antes: " + json);
            json = modifedJson(json);
            _log.info("Json despues: " + json);
            UserApplicationClient userApp = UserApplicationClientLocalServiceUtil.createUserApplicationClient(pk);
            userApp.setCompId(compId);
            userApp.setClientId(clientId);
            userApp.setContractId(contractId);
            userApp.setApplicationId(json);
            userApp.setAdmin(true);
            UserApplicationClientLocalServiceUtil.updateUserApplicationClient(userApp);
            appAfter.add(json);
            //AdeplusAuditUtil.addAudit(user.getCompanyId(), user.getGroupId(), user.getUserId(), AdeplusAuditPortletKeys.AUDIT_USER_ADD_PERMISSION, "Add permission to "+ user.getEmailAddress() +" for ");
        }


        //Si al comparar ambas listas no son exactamente iguales entendemos que se modifico alguna APP
        if(!appBefore.containsAll(appAfter)){
            changeAppList=true;
        }
        return changeAppList;
    }



    public List<AppPermission> getAppPermisionByUserCompany(UserCompany user){
        List<AppPermission> appPermissionList = new ArrayList<AppPermission>();

        return appPermissionList;
    }

    public static String getUserApplicationClientByCompId(long userId, long compId){
        String res = "";
        List<UserApplicationClient> lst =  UserApplicationClientLocalServiceUtil.getApplicationsFromUser(userId, compId);
        if(Validator.isNull(lst) || lst.size() == 0) return "";
        for(UserApplicationClient cl : lst){
            res += cl.getApplicationId();
        }

        return res;

    }

    public List<AppPermission> getAppPermisionByUserCompany(long companyId, long userId){
        List<AppPermission> appPermissionList = new ArrayList<AppPermission>();

        return appPermissionList;
    }

    public static String modifedJson(String json) throws JSONException {
        JSONArray allAppsJson= JSONFactoryUtil.createJSONArray(json);
        JSONArray updateJsonArray= JSONFactoryUtil.createJSONArray();
        JSONObject jsonAplicacionesRol=null;
        Application app=null;
        List<Role> roles= null;
        JSONArray jsonLicense=null;
        _log.info("Size: "+allAppsJson.length());
        for(int i=0;i<allAppsJson.length();i++){
            jsonAplicacionesRol=allAppsJson.getJSONObject(i);
            jsonLicense= JSONFactoryUtil.createJSONArray();
            app=ApplicationLocalServiceUtil.fetchApplication(jsonAplicacionesRol.getLong("appId"));
            if(Validator.isNotNull(app)){
                if(app.getAlias().contains("IGUALDAD") || app.getAlias().contains("DENUNCIA") || app.getAlias().contains("REGISTRO")){
                    roles=RoleLocalServiceUtil.getRoleByApplicationId(app.getApplicationId());
                    for(Role rol:roles){
                        if(rol.getName().contains("completo") || rol.getName().contains("Administrador")){
                            jsonLicense.put(String.valueOf(rol.getRoleId()));
                        }
                    }
                }
                jsonAplicacionesRol.put("licenses",jsonLicense);
                jsonAplicacionesRol.put("appId",String.valueOf(app.getApplicationId()));
                updateJsonArray.put(jsonAplicacionesRol);
            }
        }
        _log.info("JSON array: "+updateJsonArray);
        json=updateJsonArray.toString();
        _log.info("JSON arrayString: "+json);
        return json;
    }

}

package com.plan.igualdad.liferay.portlet.synchronization.web.listener;

import com.adeplus.liferay.portlet.suite.manager.model.Application;
import com.adeplus.liferay.portlet.suite.manager.model.ApplicationLicense;
import com.adeplus.liferay.portlet.suite.manager.model.UserApplicationClient;
import com.adeplus.liferay.portlet.suite.manager.service.ApplicationLicenseLocalServiceUtil;
import com.adeplus.liferay.portlet.suite.manager.service.ApplicationLocalServiceUtil;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.*;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.Validator;
import org.osgi.service.component.annotations.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component(
        immediate = true,
        service = ModelListener.class
)
public class SuiteUserApplicationModelListener extends BaseModelListener<UserApplicationClient>  {

    private static final Log log = LogFactoryUtil.getLog(SuiteUserApplicationModelListener.class);
    static SimpleDateFormat dateFormatLog = new SimpleDateFormat("yyyy-dd-MM HH:mm:ss.SSS");

    @Override
    public void onAfterCreate(UserApplicationClient userApplication) throws ModelListenerException {
        log.info("se comienza la creacion");
        updateRoleToUser(userApplication);

        super.onAfterCreate(userApplication);
    }

    @Override
    public void onAfterUpdate(UserApplicationClient userApplication) throws ModelListenerException {

        updateRoleToUser(userApplication);

        super.onAfterUpdate(userApplication);
    }

    @Override
    public void onAfterRemove(UserApplicationClient userApplication) throws ModelListenerException {


        //updateRoleToUser(userApplication);

        super.onAfterRemove(userApplication);
    }

    private void updateRoleToUser(UserApplicationClient userApplication){
        boolean actualizarPlan=false;
        boolean actualizarCanal=false;
        boolean actualizarLegal=false;
        boolean clientIgualdad=false;
        boolean clienteCanal=false;
        try {
            JSONObject jsonApp = null;
            String value_attr = "";
            JSONArray jsonClientContratApps = JSONFactoryUtil.createJSONArray(userApplication.getApplicationId());
            for (Object objApp : jsonClientContratApps) {
                jsonApp = (JSONObject) objApp;
                //Añadimos todas las aplicaciones a la lista

                Application application = ApplicationLocalServiceUtil.fetchApplication(Long.parseLong(jsonApp.getString("appId")));
                log.info("alias aplicacion " + application.getAlias());
                if(jsonApp.getJSONArray("licenses").length()>0) {
                    long role = Long.parseLong(jsonApp.getJSONArray("licenses").get(0).toString());
                    com.adeplus.liferay.portlet.suite.manager.model.Role roleAppUser = com.adeplus.liferay.portlet.suite.manager.service.RoleLocalServiceUtil.fetchRole(role);
                    if (Validator.isNotNull(roleAppUser) && roleAppUser.getAlias().equals("Plan de igualdad cliente restrictivo")) {
                        clientIgualdad = true;
                    }
                    if(Validator.isNotNull(roleAppUser) && roleAppUser.getAlias().equals("Plan de igualdad cliente restrictivo")) {
                        clienteCanal = true;
                    }
                }
                if("PLAN_IGUALDAD".equals(application.getAlias())){
                    actualizarPlan=true;
                }
                if("LEGAL_PLUS".equals(application.getAlias())){
                    actualizarLegal=true;
                }
                if("CANAL_DENUNCIAS".equals(application.getAlias())){
                    actualizarCanal=true;
                }
            }
        } catch (Exception e) {
            clientIgualdad=false;

        }


        if(actualizarPlan){

            actualizarRolPlanIgualdad(userApplication.getUserId(),clientIgualdad);

        }

        if(actualizarCanal){

            actualizarRolCanalEtico(userApplication.getUserId(),clienteCanal);

        }
        if(actualizarLegal){

            actualizarRolLegal(userApplication.getUserId());

        }


    }

    private void actualizarRolPlanIgualdad(long userId, boolean clientIgualdad){
        User userUpdate = UserLocalServiceUtil.fetchUser(userId);

        //Get group
        Group group = null;
        try {
            group = GroupLocalServiceUtil.getFriendlyURLGroup(PortalUtil.getDefaultCompanyId(), "/plan-igualdad");
        } catch (PortalException e) {
            e.printStackTrace();
        }

        if (Validator.isNotNull(userUpdate) && Validator.isNotNull(group)) {

            //Get role
            String roleName = PrefsPropsUtil.getString("plan.igualdad.rol.cliente");
            if (clientIgualdad) {
                roleName = "Plan de igualdad cliente restrictivo";
            }
            Role role = null;
            try {
                role = RoleLocalServiceUtil.getRole(PortalUtil.getDefaultCompanyId(), roleName);
            } catch (PortalException e) {
                log.error("Error getting rol name: " + roleName, e);
            }

            if (Validator.isNotNull(role)) {
                GroupLocalServiceUtil.addUserGroup(userUpdate.getUserId(), group.getGroupId());
                UserLocalServiceUtil.addRoleUser(role.getRoleId(), userUpdate);
                UserLocalServiceUtil.updateUser(userUpdate);
            }
        }
    }

    private void actualizarRolCanalEtico(long userId, boolean clientEtico){
        User userUpdate = UserLocalServiceUtil.fetchUser(userId);

        //Get group
        Group group = null;
        try {
            group = GroupLocalServiceUtil.getFriendlyURLGroup(PortalUtil.getDefaultCompanyId(), "/canal-etico");
        } catch (PortalException e) {
            e.printStackTrace();
        }

        if (Validator.isNotNull(userUpdate) && Validator.isNotNull(group)) {

            //Get role
            String roleName = PrefsPropsUtil.getString("canal.etico.rol.company.admin");
            if (clientEtico) {
                roleName = "Canal Etico Company User";
            }
            Role role = null;
            try {
                role = RoleLocalServiceUtil.getRole(PortalUtil.getDefaultCompanyId(), roleName);
            } catch (PortalException e) {
                log.error("Error getting rol name: " + roleName, e);
            }

            if (Validator.isNotNull(role)) {
                GroupLocalServiceUtil.addUserGroup(userUpdate.getUserId(), group.getGroupId());
                UserLocalServiceUtil.addRoleUser(role.getRoleId(), userUpdate);
                UserLocalServiceUtil.updateUser(userUpdate);
            }
        }
    }
    private void actualizarRolLegal(long idUsuario){
        User userUpdate = UserLocalServiceUtil.fetchUser(idUsuario);

        //Get group
        Group group = null;
        try {
            group = GroupLocalServiceUtil.getFriendlyURLGroup(PortalUtil.getDefaultCompanyId(), "/legal");
        } catch (PortalException e) {
            e.printStackTrace();
        }

        if (Validator.isNotNull(userUpdate) && Validator.isNotNull(group)) {

            String roleName = PrefsPropsUtil.getString("legal.plus.rol.cliente");
            Role role = null;
            try {
                role = RoleLocalServiceUtil.getRole(PortalUtil.getDefaultCompanyId(), roleName);
            } catch (PortalException e) {
                log.error("Error getting rol name: " + roleName, e);
            }

            if (Validator.isNotNull(role)) {
                //Add permission
                GroupLocalServiceUtil.addUserGroup(userUpdate.getUserId(), group.getGroupId());
                UserLocalServiceUtil.addRoleUser(role.getRoleId(), userUpdate);
                UserLocalServiceUtil.updateUser(userUpdate);
            }
        }
    }

}

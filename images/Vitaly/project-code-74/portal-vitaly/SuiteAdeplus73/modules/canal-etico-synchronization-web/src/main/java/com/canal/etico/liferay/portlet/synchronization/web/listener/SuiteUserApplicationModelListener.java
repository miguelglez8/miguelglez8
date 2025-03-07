package com.canal.etico.liferay.portlet.synchronization.web.listener;

import com.adeplus.liferay.portlet.suite.manager.model.Application;
import com.adeplus.liferay.portlet.suite.manager.model.Comp;
import com.adeplus.liferay.portlet.suite.manager.model.UserApplication;
import com.adeplus.liferay.portlet.suite.manager.model.UserRole;
import com.adeplus.liferay.portlet.suite.manager.service.ApplicationLocalServiceUtil;
import com.adeplus.liferay.portlet.suite.manager.service.CompLocalServiceUtil;
import com.adeplus.liferay.portlet.suite.manager.service.UserApplicationLocalServiceUtil;
import com.adeplus.liferay.portlet.suite.manager.service.UserRoleLocalServiceUtil;
import com.canal.etico.liferay.portlet.commons.web.company.CanalEticoCompUtil;
import com.canal.etico.liferay.portlet.commons.web.role.CanalEticoRoleUtil;
import com.canal.etico.liferay.portlet.commons.web.user.CanalEticoUserUtil;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.exception.PortalException;
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

import java.util.Date;
import java.util.List;
@Component(
        immediate = true,
        service = ModelListener.class
)
public class SuiteUserApplicationModelListener extends BaseModelListener<UserRole> {
    private static final Log log = LogFactoryUtil.getLog(SuiteUserApplicationModelListener.class);

    @Override
    public void onAfterCreate(UserRole userApplication) throws ModelListenerException {

        updateRoleToUser(userApplication);

        super.onAfterCreate(userApplication);
    }

    @Override
    public void onAfterUpdate(UserRole userApplication) throws ModelListenerException {

        updateRoleToUser(userApplication);

        super.onAfterUpdate(userApplication);
    }

    @Override
    public void onAfterRemove(UserRole userApplication) throws ModelListenerException {


        updateRoleToUser(userApplication);

        super.onAfterRemove(userApplication);
    }

    private void updateRoleToUser(UserRole userApplication){
        boolean actualizar=false;
        List<UserApplication> aplicacionesUsuario = UserApplicationLocalServiceUtil.getApplicationsFromUser(userApplication.getUserId(),userApplication.getCompId());
        for(UserApplication aplicacionUsurario:aplicacionesUsuario){
            Application application = ApplicationLocalServiceUtil.fetchApplication(aplicacionUsurario.getApplicationId());
            if("CANAL_DENUNCIAS".equalsIgnoreCase(application.getAlias())){
                log.info("Entro en actualizar");
                actualizar=true;
                break;
            }
        }
        if(!actualizar){
            return;
        }
        List<UserRole> roles = UserRoleLocalServiceUtil.getUserRoleByUserId(userApplication.getUserId());
        boolean clientEtico=false;

        com.adeplus.liferay.portlet.suite.manager.model.Role roleAppUser= com.adeplus.liferay.portlet.suite.manager.service.RoleLocalServiceUtil.fetchRole(userApplication.getRoleId());
        if(roleAppUser.getAlias().equals("Rol acceso restringido")){
            clientEtico=true;
        }


        //Get user
        User userUpdate = UserLocalServiceUtil.fetchUser(userApplication.getUserId());

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
            if(clientEtico){
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

}

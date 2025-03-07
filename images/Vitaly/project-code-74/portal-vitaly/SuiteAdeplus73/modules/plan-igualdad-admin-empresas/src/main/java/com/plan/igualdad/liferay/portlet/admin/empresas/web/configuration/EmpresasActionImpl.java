package com.plan.igualdad.liferay.portlet.admin.empresas.web.configuration;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ParamUtil;
import com.plan.igualdad.liferay.portlet.admin.empresas.web.constants.AdminEmpresasPortletKeys;
import com.plan.igualdad.liferay.portlet.commons.web.estado.EstadoUtils;

import org.osgi.service.component.annotations.Component;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletPreferences;

@Component(
        configurationPid = AdminEmpresasPortletKeys.ADMINEMPRESAS,
        immediate = true,
        property = "javax.portlet.name=" + AdminEmpresasPortletKeys.ADMINEMPRESAS,
        service = ConfigurationAction.class
)
public class EmpresasActionImpl extends DefaultConfigurationAction {

    public static Log _log = LogFactoryUtil.getLog(EmpresasActionImpl.class);

    @Override
    public void processAction(PortletConfig portletConfig, ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {

        String portletResource = ParamUtil.getString(actionRequest, "portletResource");
        PortletPreferences preferences = PortletPreferencesFactoryUtil.getPortletSetup(actionRequest, portletResource);

        String rolConsultor = ParamUtil.getString(actionRequest, AdminEmpresasPortletKeys.CONSULTOR_ROL);
        preferences.setValue(AdminEmpresasPortletKeys.CONSULTOR_ROL, rolConsultor);

        String rolAdmin = ParamUtil.getString(actionRequest, AdminEmpresasPortletKeys.ADMIN_ROL);
        preferences.setValue(AdminEmpresasPortletKeys.ADMIN_ROL, rolAdmin);

        String appAlias = ParamUtil.getString(actionRequest, AdminEmpresasPortletKeys.APP_ALIAS);
        preferences.setValue(AdminEmpresasPortletKeys.APP_ALIAS, appAlias);

        preferences.store();

        SessionMessages.add(actionRequest, portletConfig.getPortletName() + ".doConfigure");
        String successMessage = ParamUtil.getString(actionRequest, "successMessage");
        SessionMessages.add(actionRequest, "request_processed", successMessage);
        
        EstadoUtils.updateEstadoAllComps();

        super.processAction(portletConfig, actionRequest, actionResponse);
    }

}

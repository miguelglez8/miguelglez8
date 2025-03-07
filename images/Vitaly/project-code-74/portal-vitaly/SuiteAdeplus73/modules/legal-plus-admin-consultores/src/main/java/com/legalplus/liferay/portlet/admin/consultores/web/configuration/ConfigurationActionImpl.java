package com.legalplus.liferay.portlet.admin.consultores.web.configuration;

import com.legalplus.liferay.portlet.admin.consultores.web.constants.AdminConsultoresPortletKeys;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ParamUtil;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletPreferences;
import java.util.List;

@Component(
        configurationPid = AdminConsultoresPortletKeys.ADMINCONSULTORES,
        immediate = true,
        property = "javax.portlet.name=" + AdminConsultoresPortletKeys.ADMINCONSULTORES,
        service = ConfigurationAction.class
)
public class ConfigurationActionImpl extends DefaultConfigurationAction {

    public static Log _log = LogFactoryUtil.getLog(ConfigurationActionImpl.class);

    @Override
    public void processAction(PortletConfig portletConfig, ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {

        String portletResource = ParamUtil.getString(actionRequest, "portletResource");
        PortletPreferences preferences = PortletPreferencesFactoryUtil.getPortletSetup(actionRequest, portletResource);

        String rolConsultor = ParamUtil.getString(actionRequest, AdminConsultoresPortletKeys.CONSULTOR_ROL);
        preferences.setValue(AdminConsultoresPortletKeys.CONSULTOR_ROL, rolConsultor);

        String rolCliente = ParamUtil.getString(actionRequest, AdminConsultoresPortletKeys.CLIENTE_ROL);
        preferences.setValue(AdminConsultoresPortletKeys.CLIENTE_ROL, rolCliente);

        String empresa = ParamUtil.getString(actionRequest, AdminConsultoresPortletKeys.CONSULTOR_EMPRESA);
        preferences.setValue(AdminConsultoresPortletKeys.CONSULTOR_EMPRESA, empresa);

        preferences.store();

        SessionMessages.add(actionRequest, portletConfig.getPortletName() + ".doConfigure");
        String successMessage = ParamUtil.getString(actionRequest, "successMessage");
        SessionMessages.add(actionRequest, "request_processed", successMessage);

        super.processAction(portletConfig, actionRequest, actionResponse);
    }
}

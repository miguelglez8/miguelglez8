package com.adeplus.liferay.portlet.menu.web.configuration;

import com.adeplus.liferay.portlet.menu.web.constants.MenuPortletKeys;
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

@Component(
        configurationPid = MenuPortletKeys.MENU,
        immediate = true,
        property = "javax.portlet.name=" + MenuPortletKeys.MENU,
        service = ConfigurationAction.class
)
public class ConfigurationActionImpl extends DefaultConfigurationAction {

    public static Log _log = LogFactoryUtil.getLog(ConfigurationActionImpl.class);

    @Override
    public void processAction(PortletConfig portletConfig, ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {

        String portletResource = ParamUtil.getString(actionRequest, "portletResource");
        PortletPreferences preferences = PortletPreferencesFactoryUtil.getPortletSetup(actionRequest, portletResource);

        String pagina = ParamUtil.getString(actionRequest, MenuPortletKeys.LAYOUT);
        preferences.setValue(MenuPortletKeys.LAYOUT, pagina);

        String paginaDefecto = ParamUtil.getString(actionRequest, MenuPortletKeys.LAYOUT_DEFECT);
        preferences.setValue(MenuPortletKeys.LAYOUT_DEFECT, paginaDefecto);

        String mostrarEstado= ParamUtil.getString(actionRequest, MenuPortletKeys.ESTADO_DEFECT);
        preferences.setValue(MenuPortletKeys.ESTADO_DEFECT, mostrarEstado);

        preferences.store();

        SessionMessages.add(actionRequest, portletConfig.getPortletName() + ".doConfigure");
        String successMessage = ParamUtil.getString(actionRequest, "successMessage");
        SessionMessages.add(actionRequest, "request_processed", successMessage);

        super.processAction(portletConfig, actionRequest, actionResponse);
    }

}

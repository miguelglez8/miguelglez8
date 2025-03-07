package com.preving.liferay.portlet.user.configuration.web.configuration;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.preving.liferay.portlet.user.configuration.web.constants.UserConfigurationPortletKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletPreferences;

@Component(
        configurationPid = UserConfigurationPortletKeys.USERCONFIGURATION,
        immediate = true,
        property = "javax.portlet.name=" + UserConfigurationPortletKeys.USERCONFIGURATION,
        service = ConfigurationAction.class
)
public class ConfigurationActionImpl extends DefaultConfigurationAction {

    public static Log _log = LogFactoryUtil.getLog(ConfigurationActionImpl.class);

    @Override
    public void processAction(PortletConfig portletConfig, ActionRequest actionRequest, ActionResponse actionResponse)
            throws Exception {

        String portletResource = ParamUtil.getString(actionRequest, "portletResource");
        PortletPreferences preferences = PortletPreferencesFactoryUtil.getPortletSetup(actionRequest, portletResource);

        String importHelpText = ParamUtil.getString(actionRequest, UserConfigurationPortletKeys.CONFIGURATION_IMPORT_HELP_TEXT);

        if(_log.isDebugEnabled()){
            _log.debug("importHelpText : " + importHelpText);
        }

        LocalizationUtil.setLocalizedPreferencesValues(actionRequest, preferences, UserConfigurationPortletKeys.CONFIGURATION_IMPORT_HELP_TEXT);
        preferences.setValue(UserConfigurationPortletKeys.CONFIGURATION_IMPORT_HELP_TEXT, importHelpText);

        preferences.store();

        SessionMessages.add(actionRequest, portletConfig.getPortletName() + ".doConfigure");
        String successMessage = ParamUtil.getString(actionRequest, "successMessage");
        SessionMessages.add(actionRequest, "request_processed", successMessage);

    }

}

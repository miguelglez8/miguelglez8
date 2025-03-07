package com.preving.liferay.portlet.create.company.web.configuration;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.preving.liferay.portlet.create.company.web.constants.CreateCompanyPortletKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.*;

@Component(
        configurationPid = CreateCompanyPortletKeys.CREATECOMPANY,
        immediate = true,
        property = "javax.portlet.name=" + CreateCompanyPortletKeys.CREATECOMPANY,
        service = ConfigurationAction.class
)
public class ConfigurationActionImpl extends DefaultConfigurationAction {

    public static Log _log = LogFactoryUtil.getLog(ConfigurationActionImpl.class);

    @Override
    public void processAction(PortletConfig portletConfig, ActionRequest actionRequest, ActionResponse actionResponse)
            throws Exception {

        String portletResource = ParamUtil.getString(actionRequest, "portletResource");
        PortletPreferences preferences = PortletPreferencesFactoryUtil.getPortletSetup(actionRequest, portletResource);

        String templateId = ParamUtil.getString(actionRequest, CreateCompanyPortletKeys.CONFIGURATION_TEMPLATE_ID);
        String importHelpText = ParamUtil.getString(actionRequest, CreateCompanyPortletKeys.CONFIGURATION_IMPORT_HELP_TEXT);
        String mailSubject = ParamUtil.getString(actionRequest, CreateCompanyPortletKeys.CONFIGURATION_MAIL_SUBJECT);
        String mailBody = ParamUtil.getString(actionRequest, CreateCompanyPortletKeys.CONFIGURATION_MAIL_BODY);

        if(_log.isDebugEnabled()){
            _log.debug("templateId : " + templateId);
            _log.debug("importHelpText : " + importHelpText);
        }

        preferences.setValue(CreateCompanyPortletKeys.CONFIGURATION_TEMPLATE_ID, templateId);

        LocalizationUtil.setLocalizedPreferencesValues(actionRequest, preferences, CreateCompanyPortletKeys.CONFIGURATION_IMPORT_HELP_TEXT);
        preferences.setValue(CreateCompanyPortletKeys.CONFIGURATION_IMPORT_HELP_TEXT, importHelpText);

        LocalizationUtil.setLocalizedPreferencesValues(actionRequest, preferences, CreateCompanyPortletKeys.CONFIGURATION_MAIL_SUBJECT);
        preferences.setValue(CreateCompanyPortletKeys.CONFIGURATION_MAIL_SUBJECT, mailSubject);

        LocalizationUtil.setLocalizedPreferencesValues(actionRequest, preferences, CreateCompanyPortletKeys.CONFIGURATION_MAIL_BODY);
        preferences.setValue(CreateCompanyPortletKeys.CONFIGURATION_MAIL_BODY, mailBody);

        preferences.store();

        SessionMessages.add(actionRequest, portletConfig.getPortletName() + ".doConfigure");
        String successMessage = ParamUtil.getString(actionRequest, "successMessage");
        SessionMessages.add(actionRequest, "request_processed", successMessage);

    }

}

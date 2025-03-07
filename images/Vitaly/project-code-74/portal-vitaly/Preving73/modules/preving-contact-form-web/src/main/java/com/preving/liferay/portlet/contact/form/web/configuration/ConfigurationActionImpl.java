package com.preving.liferay.portlet.contact.form.web.configuration;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.preving.liferay.portlet.contact.form.web.constants.ContactFormPortletKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletPreferences;
import java.util.Locale;
import java.util.Map;

@Component(
        configurationPid = ContactFormPortletKeys.CONTACTFORM,
        immediate = true,
        property = "javax.portlet.name=" + ContactFormPortletKeys.CONTACTFORM,
        service = ConfigurationAction.class
)
public class ConfigurationActionImpl extends DefaultConfigurationAction {

    public static Log _log = LogFactoryUtil.getLog(ConfigurationActionImpl.class);

    @Override
    public void processAction(PortletConfig portletConfig, ActionRequest actionRequest, ActionResponse actionResponse)
            throws Exception {

        String portletResource = ParamUtil.getString(actionRequest, "portletResource");
        PortletPreferences preferences = PortletPreferencesFactoryUtil.getPortletSetup(actionRequest, portletResource);

        String emails = ParamUtil.getString(actionRequest, ContactFormPortletKeys.TO_EMAIL_CONFIGURATION);
        String categories = ParamUtil.getString(actionRequest, ContactFormPortletKeys.CATEGORIES_CONFIGURATION);
        Map<Locale, String> categoriesLocalized = LocalizationUtil.getLocalizationMap(actionRequest, ContactFormPortletKeys.CATEGORIES_CONFIGURATION);


        if(_log.isDebugEnabled()){
            _log.debug("emails : " + emails);
            _log.debug("categories : " + categories);
            _log.debug("categories : " + categoriesLocalized);
        }

        preferences.setValue(ContactFormPortletKeys.TO_EMAIL_CONFIGURATION, emails);
        preferences.setValue(ContactFormPortletKeys.CATEGORIES_CONFIGURATION, categories);

        LocalizationUtil.setLocalizedPreferencesValues(actionRequest, preferences, ContactFormPortletKeys.CATEGORIES_CONFIGURATION);

        preferences.store();

        SessionMessages.add(actionRequest, portletConfig.getPortletName() + ".doConfigure");
        String successMessage = ParamUtil.getString(actionRequest, "successMessage");
        SessionMessages.add(actionRequest, "request_processed", successMessage);

        super.processAction(portletConfig, actionRequest, actionResponse);
    }

}

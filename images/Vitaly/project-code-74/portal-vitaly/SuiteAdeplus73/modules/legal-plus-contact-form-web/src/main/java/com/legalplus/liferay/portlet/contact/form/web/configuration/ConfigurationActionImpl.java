package com.legalplus.liferay.portlet.contact.form.web.configuration;

import com.legalplus.liferay.portlet.contact.form.web.constants.ContactPortletKeys;
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
        configurationPid = ContactPortletKeys.CONTACT,
        immediate = true,
        property = "javax.portlet.name=" + ContactPortletKeys.CONTACT,
        service = ConfigurationAction.class
)
public class ConfigurationActionImpl extends DefaultConfigurationAction {

    @Override
    public void processAction(PortletConfig portletConfig, ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {

        String portletResource = ParamUtil.getString(actionRequest, "portletResource");
        PortletPreferences preferences = PortletPreferencesFactoryUtil.getPortletSetup(actionRequest, portletResource);

        String fields = ParamUtil.getString(actionRequest, ContactPortletKeys.FIELDS);
        String emailTo = ParamUtil.getString(actionRequest, ContactPortletKeys.EMAIL_TO);
        String userEmailArticleId = ParamUtil.getString(actionRequest, ContactPortletKeys.USER_EMAIL_ARTICLE_ID);
        String respEmailArticleId = ParamUtil.getString(actionRequest, ContactPortletKeys.RESP_EMAIL_ARTICLE_ID);
        String sendEmailToUser = ParamUtil.getString(actionRequest, ContactPortletKeys.SEND_EMAIL_TO_USER);

        preferences.setValue(ContactPortletKeys.FIELDS, fields);
        preferences.setValue(ContactPortletKeys.EMAIL_TO, emailTo);
        preferences.setValue(ContactPortletKeys.USER_EMAIL_ARTICLE_ID, userEmailArticleId);
        preferences.setValue(ContactPortletKeys.RESP_EMAIL_ARTICLE_ID, respEmailArticleId);
        preferences.setValue(ContactPortletKeys.SEND_EMAIL_TO_USER, sendEmailToUser);

        preferences.store();

        SessionMessages.add(actionRequest, portletConfig.getPortletName() + ".doConfigure");
        String successMessage = ParamUtil.getString(actionRequest, "successMessage");
        SessionMessages.add(actionRequest, "request_processed", successMessage);

        super.processAction(portletConfig, actionRequest, actionResponse);
    }
}

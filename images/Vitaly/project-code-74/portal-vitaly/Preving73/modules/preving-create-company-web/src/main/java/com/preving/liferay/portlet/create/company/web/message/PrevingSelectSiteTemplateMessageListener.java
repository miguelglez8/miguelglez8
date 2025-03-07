package com.preving.liferay.portlet.create.company.web.message;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.messaging.MessageListenerException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.util.ContentUtil;
import com.preving.liferay.portlet.create.company.web.constants.CreateCompanyPortletKeys;
import com.preving.liferay.portlet.create.company.web.util.*;
import com.preving.liferay.portlet.mailing.web.mail.CompanyMailing;
import org.osgi.service.component.annotations.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

@Component(
        immediate = true,
        property = {"destination.name=" + CreateCompanyPortletKeys.MESSAGE_BUS_DESTINATION_NAME_SITE_TEMPLATE},
        service = MessageListener.class
)
public class PrevingSelectSiteTemplateMessageListener implements MessageListener {

    private static Log _log = LogFactoryUtil.getLog(PrevingSelectSiteTemplateMessageListener.class);

    static SimpleDateFormat dateFormatLog = new SimpleDateFormat("yyyy-dd-MM HH:mm:ss.SSS");

    @Override
    public void receive(Message message) throws MessageListenerException {

        //Get all data.
        long templateId = message.getLong(CreateCompanyPortletKeys.TEMPLATE_ID);
        long groupId = message.getLong(CreateCompanyPortletKeys.GROUP_ID);

        Group group = GroupLocalServiceUtil.fetchGroup(groupId);

        if(Validator.isNotNull(group) && templateId > 0) {

            Date startCreate = new Date();
            System.out.println(dateFormatLog.format(startCreate) + "   * START TO SELECT SITE TEMPLATE TO GROUP " + group.getFriendlyURL());

            // Use the site template in created site.
            PrevingCompanyUtil.addTemplateToCompany(group, templateId);

            Date endCreate = new Date();
            System.out.println(dateFormatLog.format(endCreate) + "   * END TO SELECT SITE TEMPLATE TO GROUP " + group.getFriendlyURL() + ", time " + ((endCreate.getTime() - startCreate.getTime())/1000) + " seconds.");

        }else{
            _log.error("Error selecting site template: groupId or templateId are incorrect.");
        }

    }

}

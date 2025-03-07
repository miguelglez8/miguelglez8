package com.adeplus.liferay.portlet.mailing.web.message;

import com.adeplus.liferay.portlet.mailing.web.constants.AdeplusMailingPortletKeys;
import com.liferay.mail.kernel.model.MailMessage;
import com.liferay.petra.mail.MailEngine;
import com.liferay.petra.mail.MailEngineException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.messaging.MessageListenerException;
import org.osgi.service.component.annotations.Component;

import javax.mail.internet.InternetAddress;
import java.io.UnsupportedEncodingException;

@Component(
        immediate = true,
        property = {"destination.name=" + AdeplusMailingPortletKeys.MESSAGE_BUS_DESTINATION_NAME},
        service = MessageListener.class
)
public class MailingMessageListener implements MessageListener {

    private static Log _log = LogFactoryUtil.getLog(MailingMessageListener.class);

    @Override
    public void receive(Message message) throws MessageListenerException {

        //Get data.
        String from = message.getString(AdeplusMailingPortletKeys.MAIL_FROM);
        String fromName = message.getString(AdeplusMailingPortletKeys.MAIL_FROM_NAME);
        String to = message.getString(AdeplusMailingPortletKeys.MAIL_TO);
        String toName = message.getString(AdeplusMailingPortletKeys.MAIL_TO_NAME);
        String subject = message.getString(AdeplusMailingPortletKeys.MAIL_SUBJECT);
        String body = message.getString(AdeplusMailingPortletKeys.MAIL_BODY);

        try {

            MailMessage mail = new MailMessage();

            mail.setFrom(new InternetAddress(from, fromName));
            mail.setTo(new InternetAddress(to, toName));

            mail.setSubject(subject);
            mail.setBody(body);
            mail.setHTMLFormat(true);

            MailEngine.send(mail);

        } catch (UnsupportedEncodingException e) {
            _log.error(e);
        } catch (MailEngineException e) {
            _log.error(e);
        }

    }
}

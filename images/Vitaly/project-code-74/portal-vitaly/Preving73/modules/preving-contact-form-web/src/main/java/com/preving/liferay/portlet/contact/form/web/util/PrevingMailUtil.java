package com.preving.liferay.portlet.contact.form.web.util;

import com.liferay.mail.kernel.model.MailMessage;
import com.liferay.petra.mail.MailEngine;
import com.liferay.petra.mail.MailEngineException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Validator;
import sun.misc.IOUtils;

import javax.activation.CommandMap;
import javax.activation.MailcapCommandMap;
import javax.mail.internet.InternetAddress;
import java.io.*;

public class PrevingMailUtil {

    public static Log _log = LogFactoryUtil.getLog(PrevingMailUtil.class);

    public static void sendMail(long userId, String subject, String message, File file, String fileName){

        String adminEmailAddress = PropsUtil.get(PropsKeys.ADMIN_EMAIL_FROM_ADDRESS);
        String adminEmailName = PropsUtil.get(PropsKeys.ADMIN_EMAIL_FROM_NAME);

        try {

            User user = UserLocalServiceUtil.getUser(userId);

            sendMail(adminEmailAddress, adminEmailName, user.getEmailAddress(), user.getFullName(), subject, message, file, fileName);

        } catch (PortalException e) {
            _log.error("Error sending mail ", e);
        }

    }

    public static void sendMail(String toName, String toEmail, String subject, String message, File file, String fileName){

        String adminEmailAddress = PropsUtil.get(PropsKeys.ADMIN_EMAIL_FROM_ADDRESS);
        String adminEmailName = PropsUtil.get(PropsKeys.ADMIN_EMAIL_FROM_NAME);

        try {

            sendMail(adminEmailAddress, adminEmailName, toEmail, toName, subject, message, file, fileName);

        } catch (Exception e) {
            _log.error("Error sending mail ", e);
        }


    }

    public static void sendMail(String from, String fromName, String to, String toName, String subject, String message, File file, String fileName){

        try {

            MailMessage mail = new MailMessage();

            mail.setFrom(new InternetAddress(from, fromName));
            mail.setTo(new InternetAddress(to, toName));

            mail.setSubject(subject);
            mail.setBody(message);
            mail.setHTMLFormat(true);

            if(Validator.isNotNull(file) && !Validator.isBlank(fileName)){

                MailcapCommandMap mc = (MailcapCommandMap) CommandMap.getDefaultCommandMap();
                mc.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");
                mc.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");
                mc.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain");
                mc.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
                mc.addMailcap("message/rfc822;; x-java-content- handler=com.sun.mail.handlers.message_rfc822");

                mail.addFileAttachment(file, fileName);
            }

            MailEngine.send(mail);

        } catch (UnsupportedEncodingException e) {
            _log.error(e);
        } catch (MailEngineException e) {
            _log.error(e);
        }

    }

}

package com.canal.etico.liferay.portlet.mailing.web.util;

import com.liferay.mail.kernel.model.MailMessage;
import com.liferay.petra.mail.MailEngine;
import com.liferay.petra.mail.MailEngineException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Validator;

import javax.activation.CommandMap;
import javax.activation.MailcapCommandMap;
import javax.mail.internet.InternetAddress;
import java.io.File;
import java.io.UnsupportedEncodingException;

public class PrevingMailUtil {

    private static Log _log = LogFactoryUtil.getLog(PrevingMailUtil.class);

    private static String adminEmailAddress = PropsUtil.get(PropsKeys.ADMIN_EMAIL_FROM_ADDRESS);
    private static String adminEmailName = PropsUtil.get(PropsKeys.ADMIN_EMAIL_FROM_NAME);


    public static void sendMailByUserId(long userId, String subject, String message){

        try {

            User user = UserLocalServiceUtil.fetchUser(userId);

            if(Validator.isNull(user)) {
                _log.error("The user doesn't exists: " + userId);
                return;
            }

            sendMail(user.getEmailAddress(), user.getFullName(), subject, message);

        } catch (Exception e) {
            _log.error("Error sending mail.", e);
        }

    }

    public static void sendMail(String to, String toName, String subject, String message){

        try {

            if(!Validator.isEmailAddress(to)) {
                _log.error("The email is incorrect: " + to);
                return;
            }

            MailMessage mail = new MailMessage();

            mail.setFrom(new InternetAddress(adminEmailAddress, adminEmailName));
            mail.setTo(new InternetAddress(to, toName));

            mail.setSubject(subject);
            mail.setBody(message);
            mail.setHTMLFormat(true);

            MailEngine.send(mail);

        } catch (UnsupportedEncodingException e) {
            _log.error(e);
        } catch (MailEngineException e) {
            _log.error(e);
        }

    }
    public static void sendMailWithAttachment(long userId, String subject, String message, File file, String fileName) {
        try {

            User user = UserLocalServiceUtil.fetchUser(userId);

            if(Validator.isNull(user)) {
                _log.error("The user doesn't exists: " + userId);
                return;
            }

            sendMailWithAttachment(user.getEmailAddress(), user.getFullName(), subject, message, file, fileName);

        } catch (Exception e) {
            _log.error("Error sending mail.", e);
        }
    }

    public static void sendMailWithAttachment(String to, String toName, String subject, String message, File file, String fileName){

        try {

            if(!Validator.isEmailAddress(to)) {
                _log.error("The email is incorrect: " + to);
                return;
            }

            MailMessage mail = new MailMessage();

            mail.setFrom(new InternetAddress(adminEmailAddress, adminEmailName));
            mail.setTo(new InternetAddress(to, toName));

            mail.setSubject(subject);
            mail.setBody(message);
            mail.setHTMLFormat(true);

            if(Validator.isNotNull(file) && !Validator.isBlank(fileName) && file.exists()){

                MailcapCommandMap mc = (MailcapCommandMap) CommandMap.getDefaultCommandMap();
                mc.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");
                mc.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");
                mc.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain");
                mc.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
                mc.addMailcap("message/rfc822;; x-java-content- handler=com.sun.mail.handlers.message_rfc822");

                mail.addFileAttachment(file, fileName);
            }else{
                _log.debug("The file is null.");
            }

            MailEngine.send(mail);

            System.out.println("subject " + subject);
            System.out.println("subject " + "CANAL ÉTICO - ");

        } catch (UnsupportedEncodingException e) {
            _log.error(e);
        } catch (MailEngineException e) {
            _log.error(e);
        }

    }

}

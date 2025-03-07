package com.adeplus.liferay.portlet.mailing.web.util;


import com.adeplus.liferay.portlet.mailing.web.configuration.SuiteAdeplusConfigurationManager;
import com.liferay.mail.kernel.model.MailMessage;
import com.liferay.petra.mail.MailEngine;
import com.liferay.petra.mail.MailEngineException;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.MessageBus;
import com.liferay.portal.kernel.model.PortalPreferences;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.PortalPreferencesLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.service.persistence.PortalPreferencesUtil;
import com.liferay.portal.kernel.util.*;
import org.osgi.service.component.annotations.Reference;

import javax.activation.CommandMap;
import javax.activation.MailcapCommandMap;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
//import com.adeplus.liferay.portlet.mailing.web.configuration.SuiteConfigurationManager;


public class PrevingMailUtil {

    private static Log _log = LogFactoryUtil.getLog(PrevingMailUtil.class);

    private static String adminEmailAddress = PropsUtil.get(PropsKeys.ADMIN_EMAIL_FROM_ADDRESS);
    private static String adminEmailName = PropsUtil.get(PropsKeys.ADMIN_EMAIL_FROM_NAME);

    public static void sendMail(User user, String subject, String message){

        String adminEmailAddress = PropsUtil.get(PropsKeys.ADMIN_EMAIL_FROM_ADDRESS);
        String adminEmailName = PropsUtil.get(PropsKeys.ADMIN_EMAIL_FROM_NAME);

        if(Validator.isNull(user)){
            _log.error("Error sending mail. User is null.");
            return ;
        }

        sendMail(adminEmailAddress, adminEmailName, user.getEmailAddress(), user.getFullName(), subject, message);

    }

    public static void sendMail(User user, String subject, String message, File file, String fileName){

        String adminEmailAddress = PropsUtil.get(PropsKeys.ADMIN_EMAIL_FROM_ADDRESS);
        String adminEmailName = PropsUtil.get(PropsKeys.ADMIN_EMAIL_FROM_NAME);

        if(Validator.isNull(user)){
            _log.error("Error sending mail. User is null.");
            return ;
        }

        sendMail(adminEmailAddress, adminEmailName, user.getEmailAddress(), user.getFullName(), subject, message, file, fileName);

    }

    public static void sendMail(long userId, String subject, String message){

        String adminEmailAddress = PropsUtil.get(PropsKeys.ADMIN_EMAIL_FROM_ADDRESS);
        String adminEmailName = PropsUtil.get(PropsKeys.ADMIN_EMAIL_FROM_NAME);

        try {

            User user = UserLocalServiceUtil.getUser(userId);

            sendMail(adminEmailAddress, adminEmailName, user.getEmailAddress(), user.getFullName(), subject, message);

        } catch (PortalException e) {
            _log.error("Error sending mail.", e);
        }

    }

    public static void sendMail(String mailTo, String subject, String message, File file, String fileName){

        String adminEmailAddress = PropsUtil.get(PropsKeys.ADMIN_EMAIL_FROM_ADDRESS);
        String adminEmailName = PropsUtil.get(PropsKeys.ADMIN_EMAIL_FROM_NAME);

        try {
            if(Validator.isEmailAddress(mailTo)) {

                sendMail(adminEmailAddress, adminEmailName, mailTo, "Administrador Formulario de contacto", subject, message, file, fileName);

            }

        } catch (Exception e) {
            _log.error("Error sending mail.", e);
        }

    }

    private static void sendMail(String from, String fromName, String to, String toName, String subject, String body){

        try {

            MailMessage mail = new MailMessage();

            mail.setFrom(new InternetAddress(from, fromName));
            mail.setTo(new InternetAddress(to, toName));

            //CCO para seguimiento de las comunicaciones, a la espera de mejora configuración portlets
            _log.info("SuiteAdeplusConfigurationManager._configuration.getEmailCC(): " + SuiteAdeplusConfigurationManager._configuration.getEmailCC());
            String dirEmails = SuiteAdeplusConfigurationManager._configuration.getEmailCC();

            InternetAddress[] bccList = null;
            if(Validator.isNotNull(dirEmails) && !dirEmails.isEmpty()){
                bccList = InternetAddress.parse(SuiteAdeplusConfigurationManager._configuration.getEmailCC());
                mail.setBCC(bccList);
            }

            mail.setSubject(subject);
            mail.setBody(body);
            mail.setHTMLFormat(true);

            MailEngine.send(mail);

        } catch (UnsupportedEncodingException e) {
            _log.error(e);
        } catch (MailEngineException e) {
            _log.error(e);
        } catch (Exception e) {
            e.printStackTrace();
        }


/*        Message message = new Message();

        //Set destination
        message.setDestinationName(AdeplusMailingPortletKeys.MESSAGE_BUS_DESTINATION_NAME);

        //Add fields
        message.put(AdeplusMailingPortletKeys.MAIL_FROM, from);
        message.put(AdeplusMailingPortletKeys.MAIL_FROM_NAME, fromName);
        message.put(AdeplusMailingPortletKeys.MAIL_TO, to);
        message.put(AdeplusMailingPortletKeys.MAIL_TO_NAME, toName);
        message.put(AdeplusMailingPortletKeys.MAIL_TO_NAME, subject);
        message.put(AdeplusMailingPortletKeys.MAIL_BODY, body);

        //Send message
        _messageBus.sendMessage(message.getDestinationName(), message);*/


    }

    private static void sendMail(String from, String fromName, String to, String toName, String subject, String message, File file, String fileName){

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

        } catch (UnsupportedEncodingException e) {
            _log.error(e);
        } catch (MailEngineException e) {
            _log.error(e);
        }

    }

    @Reference
    private static MessageBus _messageBus;
}

package com.preving.liferay.portlet.mailing.web.util;

import com.liferay.mail.kernel.model.MailMessage;
import com.liferay.petra.mail.MailEngine;
import com.liferay.petra.mail.MailEngineException;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.util.ContentUtil;


import javax.activation.CommandMap;
import javax.activation.MailcapCommandMap;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.io.File;
import java.io.UnsupportedEncodingException;

public class PrevingMailUtil {

    private static Log _log = LogFactoryUtil.getLog(PrevingMailUtil.class);

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
    public static void sendMailExtra(String mailGestor , String subject, String message, String mailToCC){

        String adminEmailAddress = PropsUtil.get(PropsKeys.ADMIN_EMAIL_FROM_ADDRESS);
        String adminEmailName = PropsUtil.get(PropsKeys.ADMIN_EMAIL_FROM_NAME);



        sendMail(adminEmailAddress, adminEmailName, mailGestor, mailGestor,mailToCC, subject, message);

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

    private static void sendMail(String from, String fromName, String to, String toName, String subject, String message){

        try {

            MailMessage mail = new MailMessage();

            mail.setFrom(new InternetAddress(from, fromName));
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

    private static void sendMail(String from, String fromName, String to, String toName, String cc, String subject, String message){

        try {

            MailMessage mail = new MailMessage();

            mail.setFrom(new InternetAddress(from, fromName));
            mail.setTo(new InternetAddress(to, toName));


            _log.info("cc: " + cc);

            if(!Validator.isBlank(cc)){
                String[] mailCopy=cc.split(StringPool.SEMICOLON);
                InternetAddress[] ccArray = new InternetAddress[mailCopy.length];
                /*for(String mailToCopy:mailCopy){
                    mail.setCC(new InternetAddress(mailToCopy));
                }*/
                for (int i = 0; i < mailCopy.length; i++) {
                    ccArray[i] = new InternetAddress(mailCopy[i]);
                }
                mail.setCC(ccArray);

            }
            mail.setSubject(subject);
            mail.setBody(message);
            mail.setHTMLFormat(true);

            MailEngine.send(mail);

        } catch (UnsupportedEncodingException e) {
            _log.error(e);
        } catch (MailEngineException e) {
            _log.error(e);
        } catch (AddressException e) {
            e.printStackTrace();
        }

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
            _log.info("Email enviado a " + to);

        } catch (UnsupportedEncodingException e) {
            _log.error(e);
        } catch (MailEngineException e) {
            _log.error(e);
        }

    }

}

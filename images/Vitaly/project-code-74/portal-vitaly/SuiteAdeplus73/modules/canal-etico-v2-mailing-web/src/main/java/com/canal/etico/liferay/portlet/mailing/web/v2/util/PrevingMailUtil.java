package com.canal.etico.liferay.portlet.mailing.web.v2.util;

import com.liferay.document.library.kernel.model.DLFileEntry;
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

import javax.activation.CommandMap;
import javax.activation.MailcapCommandMap;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeUtility;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Map;
import org.apache.commons.io.FileUtils;

public class PrevingMailUtil {

    private static Log _log = LogFactoryUtil.getLog(PrevingMailUtil.class);
    private static String adminEmailAddress = PropsUtil.get(PropsKeys.ADMIN_EMAIL_FROM_ADDRESS);
    private static String adminEmailName = PropsUtil.get(PropsKeys.ADMIN_EMAIL_FROM_NAME);

    public static void sendMailWithAttachmentMultiple(String to, String toName, String subject, String message, Map <String, DLFileEntry> files){
        try {

            if(!Validator.isEmailAddress(to)) {
                _log.error("sendMailWithAttachment(): The email is incorrect: " + to);
                return;
            }

            MailMessage mail = new MailMessage();
            mail.setFrom(new InternetAddress(adminEmailAddress, adminEmailName));
            mail.setTo(new InternetAddress(to, toName));
            mail.setSubject(subject);
            mail.setBody(message);
            mail.setHTMLFormat(true);
            InputStream is = null;
            File f = null;
            String name = "", extension = "";

            for (Map.Entry<String, DLFileEntry> entry : files.entrySet()) {
                if(Validator.isNotNull(entry.getValue()) && !Validator.isBlank(entry.getKey()) ){
                    MailcapCommandMap mc = (MailcapCommandMap) CommandMap.getDefaultCommandMap();
                    mc.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");
                    mc.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");
                    mc.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain");
                    mc.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
                    mc.addMailcap("message/rfc822;; x-java-content- handler=com.sun.mail.handlers.message_rfc822");


                    _log.info("############# entry.getKey() : " + entry.getKey());

                    name = entry.getKey().substring(0,entry.getKey().lastIndexOf("."));
                    extension = entry.getKey().substring(entry.getKey().lastIndexOf("."));

                    _log.info("############# name : " + name + " / extension: " + extension);

                    is = ((DLFileEntry)entry.getValue()).getContentStream();
                    f = File.createTempFile(name + "-",extension);

                    FileUtils.copyInputStreamToFile(is, f);
                    mail.addFileAttachment(f);





                    _log.info("############## ");

                }
            }

            MailEngine.send(mail);
            _log.info(" Mandado correo multiple attachment -> subject " + subject);

            //msg.setSubject(MimeUtility.encodeText(messageSubject,"UTF-8","B"));

        } catch (UnsupportedEncodingException e) {
            _log.error(e);
        } catch (MailEngineException e) {
            _log.error(e);
        } catch (PortalException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void sendMailWithAttachment(String to, String toName, String subject, String message, File file, String fileName){

        try {

            if(!Validator.isEmailAddress(to)) {
                _log.error("sendMailWithAttachment(): The email is incorrect: " + to);
                return;
            }

            MailMessage mail = new MailMessage();



            mail.setFrom(new InternetAddress(adminEmailAddress, adminEmailName));
            mail.setTo(new InternetAddress(to, toName));
            mail.setSubject(subject);
            //StringEscapeUtils.escapeHtml4()
            //mail.setSubject(MimeUtility.encodeText(subject ,"UTF-8","B"));


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

            _log.info(" Mandado correo  -> subject " + subject);

            //msg.setSubject(MimeUtility.encodeText(messageSubject,"UTF-8","B"));

        } catch (UnsupportedEncodingException e) {
            _log.error(e);
        } catch (MailEngineException e) {
            _log.error(e);
        }

    }

    public static void sendMail(String to, String toName, String subject, String message){

        try {

            if(!Validator.isEmailAddress(to)) {
                _log.info("The email is incorrect: " + to);
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
            _log.info(e);
        } catch (MailEngineException e) {
            _log.info(e);
        }

    }
}

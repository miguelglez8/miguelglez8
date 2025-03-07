package com.preving.liferay.portlet.user.configuration.web.util;

import com.liferay.mail.kernel.model.MailMessage;
import com.liferay.petra.mail.MailEngine;
import com.liferay.petra.mail.MailEngineException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.util.ContentUtil;
import com.preving.liferay.portlet.mailing.web.mail.UserMailing;
import com.preving.liferay.portlet.user.configuration.web.constants.UserConfigurationPortletKeys;

import javax.mail.internet.InternetAddress;
import java.io.UnsupportedEncodingException;
import java.util.ResourceBundle;

public class PrevingMailUtil {

    public static Log _log = LogFactoryUtil.getLog(PrevingMailUtil.class);


    public static void sendMail(long userId, String subject, String message){

        String adminEmailAddress = PropsUtil.get(PropsKeys.ADMIN_EMAIL_FROM_ADDRESS);
        String adminEmailName = PropsUtil.get(PropsKeys.ADMIN_EMAIL_FROM_NAME);

        try {

            User user = UserLocalServiceUtil.getUser(userId);

            sendMail(adminEmailAddress, adminEmailName, user.getEmailAddress(), user.getFullName(), subject, message);

        } catch (PortalException e) {
            _log.error("Error sending mail sign summary.", e);
        }


    }

    public static void sendMail(String from, String fromName, String to, String toName, String subject, String message){

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



/*    public static void sendMailCreateUser(long creatorUserId, User user, String passwordRandon){

        String subject = UserConfigurationPortletKeys.LANG_CREATE_USER_SUBJECT_es_ES;
        String body = UserConfigurationPortletKeys.LANG_WELCOME_es_ES +
                UserConfigurationPortletKeys.LANG_CREATE_USER_BODY_es_ES +
                "<p>" + UserConfigurationPortletKeys.LANG_CREATE_USER_BODY_INFO_USER_es_ES + user.getEmailAddress() + "</p>" +
                "<p>" + UserConfigurationPortletKeys.LANG_CREATE_USER_BODY_INFO_PASSWORD_es_ES + passwordRandon + "</p>" +
                UserConfigurationPortletKeys.LANG_CREATE_USER_REMIND_es_ES +
                UserConfigurationPortletKeys.LANG_GOODBYE_es_ES +
                UserConfigurationPortletKeys.LANG_FOOTER_es_ES;

        String subject = LanguageUtil.get(ResourceBundle.getBundle("content/Language", user.getLocale()), "user.mail.subject.welcome");
        String body = ContentUtil.get("/templates/CreateUserEmail_"+ user.getLocale() +".tmpl");
        if(Validator.isNull(body) || Validator.isBlank(body)){
            body = ContentUtil.get("/templates/CreateUserEmail_es_ES.tmpl");
        }

        body = StringUtil.replace(body,
                new String[] {"[$USER_EMAIL$]","[$USER_PASSWORD$]"},
                new String[] {user.getEmailAddress(), passwordRandon});

        //PrevingMailUtil.sendMail(user.getUserId(), subject, body);

        NotificacionUtil.sendNotificacion(creatorUserId, creatorUserId, "Se ha creado el usuario " + user.getFirstName() + " " + user.getLastName() + " correctamente."
                , "El usuario <b>" + user.getFirstName() + " "+user.getLastName() +"</b> con email: <b>" + user.getEmailAddress() + "</b> se ha guardado correctamente.");

    }*/

}

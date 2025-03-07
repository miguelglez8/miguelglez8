package com.preving.liferay.portlet.create.company.web.util;

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
import com.preving.liferay.portlet.create.company.web.constants.CreateCompanyPortletKeys;


import javax.mail.internet.InternetAddress;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.List;
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
            _log.error("Error sending mail:" + e.getLocalizedMessage());
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
            //_log.error(e);
            _log.error("Error sending mail:" + e.getLocalizedMessage());
        } catch (MailEngineException e) {
            //_log.error(e);
            _log.error("Error sending mail:" + e.getLocalizedMessage());
        }

    }

/*    public static void sendMailCreateNewUser(User user, String email, String passwordRandon){

        // Send success notification to user.
        String subject = CreateCompanyPortletKeys.LANG_CREATE_COMPANY_SUBJECT_es_ES;
        String body = CreateCompanyPortletKeys.LANG_WELCOME_es_ES +
                CreateCompanyPortletKeys.LANG_CREATE_COMPANY_BODY_es_ES +
                "<p>" + CreateCompanyPortletKeys.LANG_CREATE_COMPANY_BODY_INFO_USER_es_ES + email + "</p>" +
                "<p>" + CreateCompanyPortletKeys.LANG_CREATE_COMPANY_BODY_INFO_PASSWORD_es_ES + passwordRandon + "</p>" +
                CreateCompanyPortletKeys.LANG_CREATE_COMPANY_REMIND_es_ES +
                CreateCompanyPortletKeys.LANG_GOODBYE_es_ES +
                CreateCompanyPortletKeys.LANG_FOOTER_es_ES;

        String subject = LanguageUtil.get(ResourceBundle.getBundle("content/Language", user.getLocale()), "previngmailing.mail.subject.welcome");
        String body = ContentUtil.get("/templates/CreateCompanyUserTemplate_"+ user.getLocale() +".tmpl");
        if(Validator.isNull(body) || Validator.isBlank(body)){
            body = ContentUtil.get("/templates/CreateCompanyUserTemplate_es_ES.tmpl");
        }

        body = StringUtil.replace(body,
                new String[] {"[$USER_EMAIL$]","[$USER_PASSWORD$]"},
                new String[] {email, passwordRandon});

        //Notificacion admin of company
        PrevingMailUtil.sendMail(user.getUserId(), subject, body);
        NotificacionUtil.sendNotificacion(user.getUserId(), user.getUserId(), subject, body);

        if(_log.isDebugEnabled()){
            _log.debug("Created new user: ");
            _log.debug("   - id : " + user.getUserId());
            _log.debug("   - name : " + user.getFullName());
            _log.debug("   - screenname : " + user.getScreenName());
            _log.debug("   - email : " + user.getEmailAddress());
        }
    }*/

    public static void sendMailErrorCSVNumberFieldsLess6(User user){

        String subject = LanguageUtil.get(ResourceBundle.getBundle("content/Language", user.getLocale()), "createcompany.mail.subject.error.format");
        String body = ContentUtil.get("/templates/AdminNotificationImportErrorTemplate_"+ user.getLocale() +".tmpl");

        if(Validator.isNull(body) || Validator.isBlank(body)){
            body = ContentUtil.get("/templates/AdminNotificationImportErrorTemplate_es_ES.tmpl");
        }

        NotificacionUtil.sendNotificacion(user.getUserId(), user.getUserId(), subject , body);
        PrevingMailUtil.sendMail(user.getUserId(), subject, body );
    }

    public static void sendMailErrorCSVCompanyIncorrect(User user, int position, List<String> companyDataCorrect){

        String subject = LanguageUtil.get(ResourceBundle.getBundle("content/Language", user.getLocale()), "createcompany.csv.error.row.title") + position;
        String body = LanguageUtil.get(ResourceBundle.getBundle("content/Language", user.getLocale()), "createcompany.csv.error.row") + position;

        body += "<ul>";
        for(String errorMessage:companyDataCorrect){
            body += "<li>" + errorMessage + "</li>";
        }
        body += "</ul>";

        NotificacionUtil.sendNotificacion(user.getUserId(), user.getUserId(), subject , body);
        PrevingMailUtil.sendMail(user.getUserId(), subject, body);

    }

    public static void sendMailErrorCSVCompanyUnknown(User user, String[] values, int position){

        String subject = LanguageUtil.get(ResourceBundle.getBundle("content/Language", user.getLocale()), "createcompany.mail.subject.error.company");
        String body = LanguageUtil.get(ResourceBundle.getBundle("content/Language", user.getLocale()), "createcompany.csv.error.row");
        body += PrevingCSVUtil.getCSVLineWithFormat(values, position);

        NotificacionUtil.sendNotificacion(user.getUserId(), user.getUserId(), subject , body);
        PrevingMailUtil.sendMail(user.getUserId(), subject, body );

    }

    public static void sendMailErrorCSVCompany(User user){

        String subject = LanguageUtil.get(ResourceBundle.getBundle("content/Language", user.getLocale()), "createcompany.mail.subject.error.company");
        String body = LanguageUtil.get(ResourceBundle.getBundle("content/Language", user.getLocale()), "createcompany.csv.error.row");

        NotificacionUtil.sendNotificacion(user.getUserId(), user.getUserId(), subject , body);
        PrevingMailUtil.sendMail(user.getUserId(), subject, body );

    }

    public static void sendMailErrorCSVDuplicatedValues(User user, List<String> errors){

        /*			String subject = CreateCompanyPortletKeys.LANG_CSV_VALIDATE_SUBJECT_ERROR_es_ES;
			String body = CreateCompanyPortletKeys.LANG_WELCOME_es_ES
					+ CreateCompanyPortletKeys.LANG_CSV_VALIDATE_BODY_ERROR_es_ES;
			body += "<ul>";
			for(String errorMessage:errors){
				body += "<li>" + errorMessage + "</li>";
			}
			body += "</ul>";
			body += CreateCompanyPortletKeys.LANG_FOOTER_es_ES;*/

        String subject = LanguageUtil.get(ResourceBundle.getBundle("content/Language", user.getLocale()), "createcompany.csv.error.repeated.title");
        String body = LanguageUtil.get(ResourceBundle.getBundle("content/Language", user.getLocale()), "createcompany.csv.error.repeated");

        body += "<ul>";
        for(String errorMessage:errors){
            body += "<li>" + errorMessage + "</li>";
        }
        body += "</ul>";


        NotificacionUtil.sendNotificacion(user.getUserId(), user.getUserId(), subject , body);
        PrevingMailUtil.sendMail(user.getUserId(), subject, body);

    }

    public static void sendMailCSVCreateNewCompanies(User user, File file){

        /*			String subject = CreateCompanyPortletKeys.LANG_COMPANY_IMPORT_SUBJECT_es_ES;
			String body = CreateCompanyPortletKeys.LANG_WELCOME_es_ES
					+ CreateCompanyPortletKeys.LANG_COMPANY_IMPORT_BODY_es_ES;
			body += getCSVWithFormat(file);
			body += CreateCompanyPortletKeys.LANG_FOOTER_es_ES;*/

        String subject = LanguageUtil.get(ResourceBundle.getBundle("content/Language", user.getLocale()), "createcompany.mail.subject.new.companies");
        String body = ContentUtil.get("/templates/AdminNotificationImportTemplate_"+ user.getLocale() +".tmpl");
        if(Validator.isNull(body) || Validator.isBlank(body)){
            body = ContentUtil.get("/templates/AdminNotificationImportTemplate_es_ES.tmpl");
        }

        body = StringUtil.replace(body,
                new String[] {"[$CSV_DATA$]"},
                new String[] {PrevingCSVUtil.getCSVWithFormat(user.getCompanyId(), file)});

        NotificacionUtil.sendNotificacion(user.getUserId(), user.getUserId(), subject , body);
        PrevingMailUtil.sendMail(user.getUserId(), subject, body);

    }

}

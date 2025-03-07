package com.legalplus.liferay.portlet.mailing.web.mail;

import com.legalplus.liferay.portlet.mailing.web.constants.MailingPortletKeys;
import com.legalplus.liferay.portlet.mailing.web.constants.MailingTemplateImageKeys;
import com.legalplus.liferay.portlet.mailing.web.util.PrevingMailUtil;
import com.legalplus.liferay.portlet.mailing.web.util.PrevingTemplateUtil;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.List;
import java.util.ResourceBundle;

public class ImportadorMailing {

    private static String[] imageKeys = {"[$ADEPLUS_LOGO_SRC$]","[$ADEPLUS_LOGO_FOOTER_SRC$]", "[$FACEBOOK_LOGO_SRC$]","[$TWITTER_LOGO_SRC$]","[$YOUTUBE_LOGO_SRC$]","[$LINKEDIN_LOGO_SRC$]","[$SUBTITLE_BACKGROUND_SRC$]"};
    private static String[] imageBase64s = {
            MailingTemplateImageKeys.MAIL_TEMPLATE_IMAGE_LOGO,
            MailingTemplateImageKeys.MAIL_TEMPLATE_IMAGE_LOGO_FOOTER,
            MailingTemplateImageKeys.MAIL_TEMPLATE_IMAGE_LOGO_FACEBOOK,
            MailingTemplateImageKeys.MAIL_TEMPLATE_IMAGE_LOGO_TWITTER,
            MailingTemplateImageKeys.MAIL_TEMPLATE_IMAGE_LOGO_YOUTUBE,
            MailingTemplateImageKeys.MAIL_TEMPLATE_IMAGE_LOGO_LINKEDIN,
            MailingTemplateImageKeys.MAIL_TEMPLATE_IMAGE_BACKGROUND_TITLE};

    public static void importadorLegislaciones(String mailTo, User userTo, List<String> errors){
        String subject = LanguageUtil.get(ResourceBundle.getBundle("content/Language", userTo.getLocale()), "mailing.mail.subject.importador.legislaciones");
        String body = PrevingTemplateUtil.getTemplate(userTo.getLocale(), MailingPortletKeys.MAIL_TEMPLATE_IMPORTADOR_LEGISLACIONES);
        String enlace = MailingTemplateImageKeys.MAIL_TEMPLATE_SITE +
                        LanguageUtil.get(ResourceBundle.getBundle("content/Language", userTo.getLocale()), "mailing.mail.subject.importador.audit.url");

        body = PrevingTemplateUtil.replaceImageBase64ToTemplate(body, imageKeys, imageBase64s);

        body = StringUtil.replace(body,
                new String[] { "[$LISTA_ERRORES$]", "[$ENLACE$]"},
                new String[] {errorMessage(errors, userTo), enlace});

        PrevingMailUtil.sendMail(mailTo, userTo.getFullName(), subject, body);
    }

    public static void importadorRequisitos(String mailTo, User userTo, List<String> errors){
        String subject = LanguageUtil.get(ResourceBundle.getBundle("content/Language", userTo.getLocale()), "mailing.mail.subject.importador.requisitos");
        String body = PrevingTemplateUtil.getTemplate(userTo.getLocale(), MailingPortletKeys.MAIL_TEMPLATE_IMPORTADOR_REQUISITOS);
        String enlace = MailingTemplateImageKeys.MAIL_TEMPLATE_SITE +
                        LanguageUtil.get(ResourceBundle.getBundle("content/Language", userTo.getLocale()), "mailing.mail.subject.importador.audit.url");

        body = PrevingTemplateUtil.replaceImageBase64ToTemplate(body, imageKeys, imageBase64s);

        body = StringUtil.replace(body,
                new String[] { "[$LISTA_ERRORES$]", "[$ENLACE$]"},
                new String[] {errorMessage(errors, userTo), enlace});

        PrevingMailUtil.sendMail(mailTo, userTo.getFullName(), subject, body);
    }

    private static String errorMessage(List<String> errors, User user) {
        StringBuffer sb = new StringBuffer();

        if (!errors.isEmpty()) {

            String intro = LanguageUtil.get(ResourceBundle.getBundle("content/Language", user.getLocale()), "mailing.mail.errors.importador");
            sb.append("<p>" + intro + "</p>");
            sb.append("<ul>");
            for (String error : errors) {
                sb.append("<li>"  + error + "</li>");
            }
            sb.append("</ul>");

        }

        return sb.toString();
    }
}

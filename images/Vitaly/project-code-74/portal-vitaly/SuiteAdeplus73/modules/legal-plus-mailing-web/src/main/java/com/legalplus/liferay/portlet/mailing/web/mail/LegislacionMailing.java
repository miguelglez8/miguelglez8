package com.legalplus.liferay.portlet.mailing.web.mail;

import com.legalplus.liferay.portlet.mailing.web.constants.MailingPortletKeys;
import com.legalplus.liferay.portlet.mailing.web.constants.MailingTemplateImageKeys;
import com.legalplus.liferay.portlet.mailing.web.util.PrevingMailUtil;
import com.legalplus.liferay.portlet.mailing.web.util.PrevingTemplateUtil;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.ResourceBundle;

public class LegislacionMailing {

    private static String[] imageKeys = {"[$ADEPLUS_LOGO_SRC$]","[$ADEPLUS_LOGO_FOOTER_SRC$]", "[$FACEBOOK_LOGO_SRC$]","[$TWITTER_LOGO_SRC$]","[$YOUTUBE_LOGO_SRC$]","[$LINKEDIN_LOGO_SRC$]","[$SUBTITLE_BACKGROUND_SRC$]"};
    private static String[] imageBase64s = {
            MailingTemplateImageKeys.MAIL_TEMPLATE_IMAGE_LOGO,
            MailingTemplateImageKeys.MAIL_TEMPLATE_IMAGE_LOGO_FOOTER,
            MailingTemplateImageKeys.MAIL_TEMPLATE_IMAGE_LOGO_FACEBOOK,
            MailingTemplateImageKeys.MAIL_TEMPLATE_IMAGE_LOGO_TWITTER,
            MailingTemplateImageKeys.MAIL_TEMPLATE_IMAGE_LOGO_YOUTUBE,
            MailingTemplateImageKeys.MAIL_TEMPLATE_IMAGE_LOGO_LINKEDIN,
            MailingTemplateImageKeys.MAIL_TEMPLATE_IMAGE_BACKGROUND_TITLE};


    public static void nuevaLegislacion(String mailTo, User userTo, String nombre, String cnae, String familia, String tipo, String ambito, String fechaPublicacion, String fechaDerogacion, String descripcion, String enlace, String comunidad, String ayuntamiento){

        String subject = LanguageUtil.get(ResourceBundle.getBundle("content/Language", userTo.getLocale()), "mailing.mail.subject.legislacion.new");

        String body = PrevingTemplateUtil.getTemplate(userTo.getLocale(), MailingPortletKeys.MAIL_TEMPLATE_NEW_LEGISLACION);

        body = PrevingTemplateUtil.replaceImageBase64ToTemplate(body, imageKeys, imageBase64s);

        body = StringUtil.replace(body,
                new String[] { "[$NOMBRE$]","[$FAMILIA$]","[$TIPO$]","[$AMBITO$]","[$FECHA_PUBLICACION$]","[$FECHA_DEROGACION$]","[$DESCRIPCION$]","[$ENLACE$]","[$COMUNIDAD$]","[$AYUNTAMIENTO$]" },
                new String[] {nombre, familia, tipo, ambito, fechaPublicacion, fechaDerogacion, descripcion, enlace, existeComunidad(comunidad), existeAyuntamiento(ayuntamiento)});

        PrevingMailUtil.sendMail(mailTo, userTo.getFullName(), subject, body);

    }

    public static void legislacionUrgente(String mailTo, User userTo, String empresa, String publicacion, String nombre, String descripcion, String link, String requisitos, String nombreConsultor, String emailConsultor) {
        String subject = LanguageUtil.format(ResourceBundle.getBundle("content/Language", userTo.getLocale()), "mailing.mail.subject.legislacion.urgente", empresa);
        String body = PrevingTemplateUtil.getTemplate(userTo.getLocale(), MailingPortletKeys.MAIL_TEMPLATE_LEGISLACION_URGENETE);
        String enlace = MailingTemplateImageKeys.MAIL_TEMPLATE_SITE +
                        LanguageUtil.get(ResourceBundle.getBundle("content/Language", userTo.getLocale()), "mailing.mail.subject.legislacion.urgente.url");

        body = PrevingTemplateUtil.replaceImageBase64ToTemplate(body, imageKeys, imageBase64s);

        body = StringUtil.replace(body,
                new String[] { "[$FECHA_PUBLICACION$]","[$NOMBRE$]","[$DESCRIPCION$]", "[$ENLACE_LEGISLACION$]","[$REQUISITOS$]","[$ENLACE$]","[$NOMBRE_CONSULTOR$]","[$EMAIL_CONSULTOR$]" },
                new String[] {publicacion, nombre, descripcion, link, requisitos, enlace, nombreConsultor, emailConsultor});

        PrevingMailUtil.sendMail(mailTo, userTo.getFullName(), subject, body);
    }

    public static void informeMensualLegislaciones(String mailTo, User userTo, String tablaLegislaciones){

        String subject = LanguageUtil.get(ResourceBundle.getBundle("content/Language", userTo.getLocale()), "mailing.mail.subject.legislacion.monthly.report");

        String body = PrevingTemplateUtil.getTemplate(userTo.getLocale(), MailingPortletKeys.MAIL_TEMPLATE_MONTHLY_REPORT_LEGISLACION);

        body = PrevingTemplateUtil.replaceImageBase64ToTemplate(body, imageKeys, imageBase64s);

        body = StringUtil.replace(body,
                new String[] { "[$TABLA_LEGISLACIONES$]"},
                new String[] {tablaLegislaciones});

        PrevingMailUtil.sendMail(mailTo, userTo.getFullName(), subject, body);

    }

    private static String existeComunidad(String ccaa) {
        StringBuilder sb = new StringBuilder();
        if (!Validator.isBlank(ccaa)) {
            sb.append("<p><strong>Comunidad aut&#243;noma: </strong></p>");
            sb.append("<p>" + ccaa + "</p>");
        }
        return sb.toString();
    }

    private static String existeAyuntamiento(String ayto) {
        StringBuilder sb = new StringBuilder();
        if (!Validator.isBlank(ayto)) {
            sb.append("<p><strong>Ayuntamiento: </strong></p>");
            sb.append("<p>" + ayto + "</p>");
        }
        return sb.toString();
    }
}

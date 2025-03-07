package com.canal.etico.liferay.portlet.mailing.web.mail;

import com.canal.etico.liferay.portlet.mailing.web.constants.MailingPortletKeys;
import com.canal.etico.liferay.portlet.mailing.web.constants.MailingTemplateImageKeys;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.canal.etico.liferay.portlet.mailing.web.util.PrevingMailUtil;
import com.canal.etico.liferay.portlet.mailing.web.util.PrevingTemplateUtil;

import java.io.File;
import java.util.Locale;
import java.util.ResourceBundle;

public class DenunciaMailing {

    private static String[] imageKeys = {"[$ADEPLUS_LOGO_SRC$]","[$ADEPLUS_LOGO_FOOTER_SRC$]", "[$FACEBOOK_LOGO_SRC$]","[$TWITTER_LOGO_SRC$]","[$YOUTUBE_LOGO_SRC$]","[$LINKEDIN_LOGO_SRC$]","[$SUBTITLE_BACKGROUND_SRC$]"};
    private static String[] imageBase64s = {
            MailingTemplateImageKeys.MAIL_TEMPLATE_IMAGE_LOGO,
            MailingTemplateImageKeys.MAIL_TEMPLATE_IMAGE_LOGO_FOOTER,
            MailingTemplateImageKeys.MAIL_TEMPLATE_IMAGE_LOGO_FACEBOOK,
            MailingTemplateImageKeys.MAIL_TEMPLATE_IMAGE_LOGO_TWITTER,
            MailingTemplateImageKeys.MAIL_TEMPLATE_IMAGE_LOGO_YOUTUBE,
            MailingTemplateImageKeys.MAIL_TEMPLATE_IMAGE_LOGO_LINKEDIN,
            MailingTemplateImageKeys.MAIL_TEMPLATE_IMAGE_BACKGROUND_TITLE};


    public static void denunciaToUser(String mailToAdress, String mailtToName, String companyName, String codigo, String subjectDenuncia, String description, String categoryName, File file, String fileName, Locale locale){

        String subject = LanguageUtil.get(ResourceBundle.getBundle("content/Language", locale), "mailing.mail.subject.denuncia.user");

        String body = PrevingTemplateUtil.getTemplate(locale, MailingPortletKeys.MAIL_TEMPLATE_DENUNCIA_USER);

        body = PrevingTemplateUtil.replaceImageBase64ToTemplate(body, imageKeys, imageBase64s);

        body = StringUtil.replace(body,
                new String[] { "[$USER_COMPANY$]","[$CODE_DENUNCIA$]", "[$SUBJECT_MESSAGE$]","[$BODY_MESSAGE$]","[$CATEGORY_MESSAGE$]","[$FILE_NAME$]" },
                new String[] { companyName, codigo, subjectDenuncia, description, categoryName, fileName});

        PrevingMailUtil.sendMailWithAttachment(mailToAdress, mailtToName, codigo + " - " + subject, body, file, fileName);

    }

    public static void denunciaToAdmin(long userId, String companyName, String codigo, String subjectDenuncia, String description, String categoryName, File file, String fileName, Locale locale){

        String subject = LanguageUtil.get(ResourceBundle.getBundle("content/Language", locale), "mailing.mail.subject.denuncia.admin");

        String body = PrevingTemplateUtil.getTemplate(locale, MailingPortletKeys.MAIL_TEMPLATE_DENUNCIA_ADMIN);

        body = PrevingTemplateUtil.replaceImageBase64ToTemplate(body, imageKeys, imageBase64s);

        body = StringUtil.replace(body,
                new String[] { "[$USER_COMPANY$]","[$CODE_DENUNCIA$]", "[$SUBJECT_MESSAGE$]","[$BODY_MESSAGE$]","[$CATEGORY_MESSAGE$]","[$FILE_NAME$]" },
                new String[] { companyName, codigo, subjectDenuncia, description, categoryName, fileName});

        PrevingMailUtil.sendMailWithAttachment(userId, codigo + " - " + subject, body, file, fileName);

    }

    public static void denunciaToAdmin(String mailToAdress, String mailtToName, String companyName, String codigo, String subjectDenuncia, String description, String categoryName, File file, String fileName, Locale locale){

        String subject = LanguageUtil.get(ResourceBundle.getBundle("content/Language", locale), "mailing.mail.subject.denuncia.admin");

        String body = PrevingTemplateUtil.getTemplate(locale, MailingPortletKeys.MAIL_TEMPLATE_DENUNCIA_ADMIN);

        body = PrevingTemplateUtil.replaceImageBase64ToTemplate(body, imageKeys, imageBase64s);

        body = StringUtil.replace(body,
                new String[] { "[$USER_COMPANY$]","[$CODE_DENUNCIA$]", "[$SUBJECT_MESSAGE$]","[$BODY_MESSAGE$]","[$CATEGORY_MESSAGE$]","[$FILE_NAME$]" },
                new String[] { companyName, codigo, subjectDenuncia, description, categoryName, fileName});

        PrevingMailUtil.sendMailWithAttachment(mailToAdress, mailtToName, codigo + " - " + subject, body, file, fileName);

    }

    public static void comunicacionToUser(String mailToAdress, String mailtToName, String codigo, String comunicacion, File file, String fileName, Locale locale){

        String subject = LanguageUtil.get(ResourceBundle.getBundle("content/Language", locale), "mailing.mail.subject.denuncia.comunicacion");

        String body = PrevingTemplateUtil.getTemplate(locale, MailingPortletKeys.MAIL_TEMPLATE_DENUNCIA_COMUNICACION);

        body = PrevingTemplateUtil.replaceImageBase64ToTemplate(body, imageKeys, imageBase64s);

        body = StringUtil.replace(body,
                new String[] { "[$CODE_DENUNCIA$]","[$COMUNICACION_MESSAGE$]","[$FILE_NAME$]" },
                new String[] {  codigo, comunicacion, fileName});

        PrevingMailUtil.sendMailWithAttachment(mailToAdress, mailtToName, codigo + " - " + subject, body, file, fileName);

    }

    public static void finalizacionDenunciaToUser(String mailToAdress, String mailtToName, String codigo, String fecha, String motivo, String observaciones, Locale locale){

        String subject = LanguageUtil.get(ResourceBundle.getBundle("content/Language", locale), "mailing.mail.subject.denuncia.finalizar");

        String body = PrevingTemplateUtil.getTemplate(locale, MailingPortletKeys.MAIL_TEMPLATE_DENUNCIA_FINALIZAR_USER);

        body = PrevingTemplateUtil.replaceImageBase64ToTemplate(body, imageKeys, imageBase64s);

        body = StringUtil.replace(body,
                new String[] { "[$CODE_DENUNCIA$]", "[$FINALIZACION_FECHA$]","[$FINALIZACION_MOTIVO$]","[$FINALIZACION_OBSERVACIONES$]" },
                new String[] { codigo, fecha, motivo, observaciones});

        PrevingMailUtil.sendMail(mailToAdress, mailtToName, codigo + " - " + subject, body);

    }

    public static void finalizacionDenunciaToAdmin(long userId, String codigo, String fecha, String motivo, String observaciones, Locale locale){

        String subject = LanguageUtil.get(ResourceBundle.getBundle("content/Language", locale), "mailing.mail.subject.denuncia.finalizar");

        String body = PrevingTemplateUtil.getTemplate(locale, MailingPortletKeys.MAIL_TEMPLATE_DENUNCIA_FINALIZAR_ADMIN);

        body = PrevingTemplateUtil.replaceImageBase64ToTemplate(body, imageKeys, imageBase64s);

        body = StringUtil.replace(body,
                new String[] { "[$CODE_DENUNCIA$]", "[$FINALIZACION_FECHA$]","[$FINALIZACION_MOTIVO$]","[$FINALIZACION_OBSERVACIONES$]" },
                new String[] { codigo, fecha, motivo, observaciones});

        PrevingMailUtil.sendMailByUserId(userId, codigo + " - " + subject, body);

    }

    public static void finalizacionDenunciaAvisoToAdmin(long userId, String codigo, String fecha, String estado, String acciones, Locale locale){

        String subject = LanguageUtil.get(ResourceBundle.getBundle("content/Language", locale), "mailing.mail.subject.denuncia.aviso.finalizar");

        String body = PrevingTemplateUtil.getTemplate(locale, MailingPortletKeys.MAIL_TEMPLATE_DENUNCIA_FINALIZAR_AVISO_ADMIN);

        body = PrevingTemplateUtil.replaceImageBase64ToTemplate(body, imageKeys, imageBase64s);

        body = StringUtil.replace(body,
                new String[] { "[$CODE_DENUNCIA$]", "[$DENUNCIA_FECHA$]","[$DENUNCIA_ESTADO$]","[$DENUNCIA_ACCIONES$]" },
                new String[] { codigo, fecha, estado, acciones});

        PrevingMailUtil.sendMailByUserId(userId, codigo + " - " + subject, body);

    }

    public static void cambiarEstadoDenunciaToUser(String mailToAdress, String mailtToName, String codigo, String fecha, String estado, String descripcion, Locale locale){

        String subject = LanguageUtil.get(ResourceBundle.getBundle("content/Language", locale), "mailing.mail.subject.denuncia.cambio.estado");

        String body = PrevingTemplateUtil.getTemplate(locale, MailingPortletKeys.MAIL_TEMPLATE_DENUNCIA_CAMBIAR_ESTADO_USER);

        body = PrevingTemplateUtil.replaceImageBase64ToTemplate(body, imageKeys, imageBase64s);

        body = StringUtil.replace(body,
                new String[] { "[$CODE_DENUNCIA$]", "[$DENUNCIA_FECHA$]","[$DENUNCIA_ESTADO$]","[$DENUNCIA_DESCRIPCION$]" },
                new String[] { codigo, fecha, estado, descripcion});

        PrevingMailUtil.sendMail(mailToAdress, mailtToName, codigo + " - " + subject, body);

    }

}

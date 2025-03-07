package com.legalplus.liferay.portlet.mailing.web.mail;

import com.legalplus.liferay.portlet.mailing.web.constants.MailingPortletKeys;
import com.legalplus.liferay.portlet.mailing.web.constants.MailingTemplateImageKeys;
import com.legalplus.liferay.portlet.mailing.web.util.PrevingMailUtil;
import com.legalplus.liferay.portlet.mailing.web.util.PrevingTemplateUtil;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.StringUtil;
import java.util.ResourceBundle;

public class EvalRequisitoMailing {

    private static String[] imageKeys = {"[$ADEPLUS_LOGO_SRC$]","[$ADEPLUS_LOGO_FOOTER_SRC$]", "[$FACEBOOK_LOGO_SRC$]","[$TWITTER_LOGO_SRC$]","[$YOUTUBE_LOGO_SRC$]","[$LINKEDIN_LOGO_SRC$]","[$SUBTITLE_BACKGROUND_SRC$]"};
    private static String[] imageBase64s = {
            MailingTemplateImageKeys.MAIL_TEMPLATE_IMAGE_LOGO,
            MailingTemplateImageKeys.MAIL_TEMPLATE_IMAGE_LOGO_FOOTER,
            MailingTemplateImageKeys.MAIL_TEMPLATE_IMAGE_LOGO_FACEBOOK,
            MailingTemplateImageKeys.MAIL_TEMPLATE_IMAGE_LOGO_TWITTER,
            MailingTemplateImageKeys.MAIL_TEMPLATE_IMAGE_LOGO_YOUTUBE,
            MailingTemplateImageKeys.MAIL_TEMPLATE_IMAGE_LOGO_LINKEDIN,
            MailingTemplateImageKeys.MAIL_TEMPLATE_IMAGE_BACKGROUND_TITLE};

    public static void nuevaEvaluacionCumplimiento(String mailTo, User userTo, String empresa, String legislacion, String usuario, String fecha, String cumplimiento) {
        String subject = LanguageUtil.get(ResourceBundle.getBundle("content/Language", userTo.getLocale()), "mailing.mail.subject.new.eval.requisito");
        String body = PrevingTemplateUtil.getTemplate(userTo.getLocale(), MailingPortletKeys.MAIL_TEMPLATE_NUEVO_CUMPLIMIENTO_REQUISITO);

        body = PrevingTemplateUtil.replaceImageBase64ToTemplate(body, imageKeys, imageBase64s);

        body = StringUtil.replace(body,
                new String[] { "[$EMPRESA$]", "[$LEGISLACION$]", "[$USUARIO$]", "[$FECHA$]", "[$CUMPLIMIENTO$]"},
                new String[] { empresa, legislacion, usuario, fecha, cumplimiento});

        PrevingMailUtil.sendMail(mailTo, userTo.getFullName(), subject, body);
    }

    public static void informeMensualEvalRequisitos(String mailTo, User userTo, String empresa, String mes, String nombreConsultor, String emailConsultor, String enlace) {
        String subject = LanguageUtil.format(ResourceBundle.getBundle("content/Language", userTo.getLocale()), "mailing.mail.subject.informe.eval.requisito", empresa);
        String body = PrevingTemplateUtil.getTemplate(userTo.getLocale(), MailingPortletKeys.MAIL_TEMPLATE_INFORME_EVALUACION);

        body = PrevingTemplateUtil.replaceImageBase64ToTemplate(body, imageKeys, imageBase64s);

        body = StringUtil.replace(body,
                new String[] { "[$ENLACE$]", "[$MES$]", "[$NOMBRE_CONSULTOR$]", "[$EMAIL_CONSULTOR$]"},
                new String[] { enlace, mes, nombreConsultor, emailConsultor});

        PrevingMailUtil.sendMail(mailTo, userTo.getFullName(), subject, body);
    }

    public static void proximaFechaEvaluacionSemanal(String mailTo, User userTo, String empresa, String requisitosWeek, String nombreConsultor, String emailConsultor, String enlace) {
        String subject = LanguageUtil.format(ResourceBundle.getBundle("content/Language", userTo.getLocale()), "mailing.mail.subject.prox.eval.requisito", empresa);
        String body = PrevingTemplateUtil.getTemplate(userTo.getLocale(), MailingPortletKeys.MAIL_TEMPLATE_PROX_FECHA_EVALUACION_SEMANAL);

        body = PrevingTemplateUtil.replaceImageBase64ToTemplate(body, imageKeys, imageBase64s);

        body = StringUtil.replace(body,
                new String[] { "[$ENLACE$]", "[$REQUISITOS_WEEK$]", "[$NOMBRE_CONSULTOR$]", "[$EMAIL_CONSULTOR$]"},
                new String[] { enlace, requisitosWeek, nombreConsultor, emailConsultor});

        PrevingMailUtil.sendMail(mailTo, userTo.getFullName(), subject, body);

    }

    public static void proximaFechaEvaluacionDiaria(String mailTo, User userTo, String empresa, String requisitosTomorrow, String nombreConsultor, String emailConsultor, String enlace) {
        String subject = LanguageUtil.format(ResourceBundle.getBundle("content/Language", userTo.getLocale()), "mailing.mail.subject.prox.eval.requisito", empresa);
        String body = PrevingTemplateUtil.getTemplate(userTo.getLocale(), MailingPortletKeys.MAIL_TEMPLATE_PROX_FECHA_EVALUACION_DIARIA);

        body = PrevingTemplateUtil.replaceImageBase64ToTemplate(body, imageKeys, imageBase64s);

        body = StringUtil.replace(body,
                new String[] { "[$ENLACE$]", "[$REQUISITOS_TOMORROW$]", "[$NOMBRE_CONSULTOR$]", "[$EMAIL_CONSULTOR$]"},
                new String[] { enlace, requisitosTomorrow, nombreConsultor, emailConsultor});

        PrevingMailUtil.sendMail(mailTo, userTo.getFullName(), subject, body);

    }

    public static void periodoFechaEvaluacion(String mailTo, User userTo, String empresa, String requisitos, String nombreConsultor, String emailConsultor, String enlace) {
        String subject = LanguageUtil.format(ResourceBundle.getBundle("content/Language", userTo.getLocale()), "mailing.mail.subject.periodo.requisito", empresa);
        String body = PrevingTemplateUtil.getTemplate(userTo.getLocale(), MailingPortletKeys.MAIL_TEMPLATE_PERIODO_EVALUACION);

        body = PrevingTemplateUtil.replaceImageBase64ToTemplate(body, imageKeys, imageBase64s);

        body = StringUtil.replace(body,
                new String[] { "[$ENLACE$]", "[$REQUISITOS$]", "[$NOMBRE_CONSULTOR$]", "[$EMAIL_CONSULTOR$]"},
                new String[] { enlace, requisitos, nombreConsultor, emailConsultor});

        PrevingMailUtil.sendMail(mailTo, userTo.getFullName(), subject, body);
    }

}

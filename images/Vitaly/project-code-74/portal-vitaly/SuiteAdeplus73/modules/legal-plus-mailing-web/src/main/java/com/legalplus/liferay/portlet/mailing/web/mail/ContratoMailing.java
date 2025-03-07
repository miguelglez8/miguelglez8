package com.legalplus.liferay.portlet.mailing.web.mail;

import com.legalplus.liferay.portlet.mailing.web.constants.MailingPortletKeys;
import com.legalplus.liferay.portlet.mailing.web.constants.MailingTemplateImageKeys;
import com.legalplus.liferay.portlet.mailing.web.util.PrevingMailUtil;
import com.legalplus.liferay.portlet.mailing.web.util.PrevingTemplateUtil;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.StringUtil;
import java.util.ResourceBundle;

public class ContratoMailing {

    private static String[] imageKeys = {"[$ADEPLUS_LOGO_SRC$]","[$ADEPLUS_LOGO_FOOTER_SRC$]", "[$FACEBOOK_LOGO_SRC$]","[$TWITTER_LOGO_SRC$]","[$YOUTUBE_LOGO_SRC$]","[$LINKEDIN_LOGO_SRC$]","[$SUBTITLE_BACKGROUND_SRC$]"};
    private static String[] imageBase64s = {
            MailingTemplateImageKeys.MAIL_TEMPLATE_IMAGE_LOGO,
            MailingTemplateImageKeys.MAIL_TEMPLATE_IMAGE_LOGO_FOOTER,
            MailingTemplateImageKeys.MAIL_TEMPLATE_IMAGE_LOGO_FACEBOOK,
            MailingTemplateImageKeys.MAIL_TEMPLATE_IMAGE_LOGO_TWITTER,
            MailingTemplateImageKeys.MAIL_TEMPLATE_IMAGE_LOGO_YOUTUBE,
            MailingTemplateImageKeys.MAIL_TEMPLATE_IMAGE_LOGO_LINKEDIN,
            MailingTemplateImageKeys.MAIL_TEMPLATE_IMAGE_BACKGROUND_TITLE};

    public static void cambioContrato(String mailTo, User userTo, String empresa, String tipo, String cnaes, String consultor, String familias, String ccaa, String ayto, String nombreConsultor, String emailConsultor){
        String subject = LanguageUtil.format(ResourceBundle.getBundle("content/Language", userTo.getLocale()), "mailing.mail.subject.cambios.contrato", empresa);
        String body = PrevingTemplateUtil.getTemplate(userTo.getLocale(), MailingPortletKeys.MAIL_TEMPLATE_CAMBIOS_CONTRATO);
        String enlace = MailingTemplateImageKeys.MAIL_TEMPLATE_SITE +
                        LanguageUtil.get(ResourceBundle.getBundle("content/Language", userTo.getLocale()), "mailing.mail.subject.cambios.contrato.url");

        body = PrevingTemplateUtil.replaceImageBase64ToTemplate(body, imageKeys, imageBase64s);

        body = StringUtil.replace(body,
                new String[] { "[$TIPO_CONTRATO$]", "[$CNAES$]", "[$CONSULTOR$]", "[$FAMILIAS$]", "[$CCAA$]", "[$AYTO$]", "[$ENLACE$]", "[$NOMBRE_CONSULTOR$]", "[$EMAIL_CONSULTOR$]"},
                new String[] {tipo, cnaes, consultor, familias, ccaa, ayto, enlace, nombreConsultor, emailConsultor});

        PrevingMailUtil.sendMail(mailTo, userTo.getFullName(), subject, body);
    }
}

package com.preving.liferay.portlet.mailing.web.mail;

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.StringUtil;
import com.preving.liferay.portlet.mailing.web.constants.PrevingMailingPortletKeys;
import com.preving.liferay.portlet.mailing.web.constants.PrevingMailingTemplateImageKeys;
import com.preving.liferay.portlet.mailing.web.util.PrevingMailUtil;
import com.preving.liferay.portlet.mailing.web.util.PrevingTemplateUtil;

import java.util.ResourceBundle;

public class CompanyMailing {

    private static Log _log = LogFactoryUtil.getLog(CompanyMailing.class);

    private static String[] imageKeys = {"[$PREVING_LOGO_SRC$]","[$FACEBOOK_LOGO_SRC$]","[$TWITTER_LOGO_SRC$]","[$YOUTUBE_LOGO_SRC$]","[$LINKEDIN_LOGO_SRC$]","[$PREVING_BACKGROUND_SRC$]"};
    private static String[] imageBase64s = {PrevingMailingTemplateImageKeys.MAIL_TEMPLATE_IMAGE_LOGO_PREVING, PrevingMailingTemplateImageKeys.MAIL_TEMPLATE_IMAGE_LOGO_FACEBOOK,
            PrevingMailingTemplateImageKeys.MAIL_TEMPLATE_IMAGE_LOGO_TWITTER, PrevingMailingTemplateImageKeys.MAIL_TEMPLATE_IMAGE_LOGO_YOUTUBE,
            PrevingMailingTemplateImageKeys.MAIL_TEMPLATE_IMAGE_LOGO_LINKEDIN, PrevingMailingTemplateImageKeys.MAIL_TEMPLATE_IMAGE_BACKGROUND_TITLE};

    public static void companyCreateToCreator(User user, String companyName, String companyCIF){

        String subject = LanguageUtil.get(ResourceBundle.getBundle("content/Language", user.getLocale()), "previngmailing.mail.subject.new.company");

        String body = PrevingTemplateUtil.getTemplate(user, PrevingMailingPortletKeys.MAIL_TEMPLATE_COMPANY_CREATE_TO_CREATOR);

        body = PrevingTemplateUtil.replaceImageBase64ToTemplate(body, imageKeys, imageBase64s);

        body = StringUtil.replace(body,
                new String[] {"[$COMPANY_NAME$]","[$COMPANY_CIF$]","[$HELP_VIDEO_NEW_COMPANY$]"},
                new String[] {companyName, companyCIF, PrevingMailingTemplateImageKeys.MAIL_TEMPLATE_VIDEO_NEW_COMPANY });

        PrevingMailUtil.sendMail(user, subject, body);

        if(_log.isDebugEnabled()) {
            _log.debug("Send mail :: companyCreateToCreator :: ");
        }

    }

    public static void companyCreateToAdministrator(User user, String password){

        _log.debug("envio de welcomepack");

        String subject = LanguageUtil.get(ResourceBundle.getBundle("content/Language", user.getLocale()), "previngmailing.mail.subject.welcome");
        String body = PrevingTemplateUtil.getTemplate(user, PrevingMailingPortletKeys.MAIL_TEMPLATE_COMPANY_CREATE_TO_ADMIN);
        body = PrevingTemplateUtil.replaceImageBase64ToTemplate(body, imageKeys, imageBase64s);

        body = StringUtil.replace(body,
                new String[] {"[$USER_EMAIL$]","[$USER_PASSWORD$]","[$HELP_VIDEO_NEW_COMPANY$]"},
                new String[] {user.getEmailAddress(), password, PrevingMailingTemplateImageKeys.MAIL_TEMPLATE_VIDEO_NEW_COMPANY });

        PrevingMailUtil.sendMail(user, subject, body);

        if(_log.isDebugEnabled()) {
            _log.debug("Send mail :: companyCreateToAdministrator :: " + user.getEmailAddress());
        }

    }

    public static void companyDeleteToAdministrator(User user, String companyName, String companyCIF){

        String subject = LanguageUtil.get(ResourceBundle.getBundle("content/Language", user.getLocale()), "previngmailing.mail.subject.delete.company");

        String body = PrevingTemplateUtil.getTemplate(user, PrevingMailingPortletKeys.MAIL_TEMPLATE_COMPANY_DELETE_TO_ADMIN);

        body = PrevingTemplateUtil.replaceImageBase64ToTemplate(body, imageKeys, imageBase64s);

        body = StringUtil.replace(body,
                new String[] {"[$COMPANY_NAME$]","[$COMPANY_CIF$]"},
                new String[] {companyName, companyCIF});

        PrevingMailUtil.sendMail(user, subject, body);

        if(_log.isDebugEnabled()) {
            _log.debug("Send mail :: companyDeleteToAdministrator :: ");
        }
    }

}

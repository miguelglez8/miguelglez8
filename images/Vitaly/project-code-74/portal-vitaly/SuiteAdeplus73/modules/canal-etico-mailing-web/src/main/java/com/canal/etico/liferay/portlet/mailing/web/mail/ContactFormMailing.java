package com.canal.etico.liferay.portlet.mailing.web.mail;

import com.canal.etico.liferay.portlet.mailing.web.constants.MailingPortletKeys;
import com.canal.etico.liferay.portlet.mailing.web.constants.MailingTemplateImageKeys;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.canal.etico.liferay.portlet.mailing.web.util.PrevingMailUtil;
import com.canal.etico.liferay.portlet.mailing.web.util.PrevingTemplateUtil;

import java.io.File;
import java.util.ResourceBundle;

public class ContactFormMailing {

    private static String[] imageKeys = {"[$ADEPLUS_LOGO_SRC$]","[$ADEPLUS_LOGO_FOOTER_SRC$]", "[$FACEBOOK_LOGO_SRC$]","[$TWITTER_LOGO_SRC$]","[$YOUTUBE_LOGO_SRC$]","[$LINKEDIN_LOGO_SRC$]","[$SUBTITLE_BACKGROUND_SRC$]"};
    private static String[] imageBase64s = {
            MailingTemplateImageKeys.MAIL_TEMPLATE_IMAGE_LOGO,
            MailingTemplateImageKeys.MAIL_TEMPLATE_IMAGE_LOGO_FOOTER,
            MailingTemplateImageKeys.MAIL_TEMPLATE_IMAGE_LOGO_FACEBOOK,
            MailingTemplateImageKeys.MAIL_TEMPLATE_IMAGE_LOGO_TWITTER,
            MailingTemplateImageKeys.MAIL_TEMPLATE_IMAGE_LOGO_YOUTUBE,
            MailingTemplateImageKeys.MAIL_TEMPLATE_IMAGE_LOGO_LINKEDIN,
            MailingTemplateImageKeys.MAIL_TEMPLATE_IMAGE_BACKGROUND_TITLE};


    public static void contactToAdmin(String mailTo, User userFrom, String companyName, String subjectContact, String message, String sourceFileName, String categoryName, File file, String fileName){

        String subject = LanguageUtil.get(ResourceBundle.getBundle("content/Language", userFrom.getLocale()), "mailing.mail.subject.contact.admin");

        String body = PrevingTemplateUtil.getTemplate(userFrom.getLocale(), MailingPortletKeys.MAIL_TEMPLATE_CONTACT_ADMIN);

        body = PrevingTemplateUtil.replaceImageBase64ToTemplate(body, imageKeys, imageBase64s);

        body = StringUtil.replace(body,
                new String[] { "[$USER_FULL_NAME$]","[$USER_EMAIL$]","[$USER_COMPANY$]","[$SUBJECT_MESSAGE$]","[$BODY_MESSAGE$]","[$ATTACHMENT$]","[$CATEGORY_MESSAGE$]" },
                new String[] {userFrom.getFullName(), userFrom.getEmailAddress(), companyName, subjectContact, message, Validator.isBlank(sourceFileName)?"-":sourceFileName, categoryName});

        PrevingMailUtil.sendMailWithAttachment(mailTo, "", subject, body, file, fileName);

    }

    public static void contactToUser(User user, String subjectContact, String message, String categoryName, File file, String fileName){

        String subject = LanguageUtil.get(ResourceBundle.getBundle("content/Language", user.getLocale()), "mailing.mail.subject.contact.user");

        String body = PrevingTemplateUtil.getTemplate(user.getLocale(), MailingPortletKeys.MAIL_TEMPLATE_CONTACT_USER);

        body = PrevingTemplateUtil.replaceImageBase64ToTemplate(body, imageKeys, imageBase64s);

        body = StringUtil.replace(body,
                new String[] { "[$SUBJECT_MESSAGE$]","[$BODY_MESSAGE$]","[$ATTACHMENT$]","[$CATEGORY_MESSAGE$]" },
                new String[] { subjectContact, message, fileName, categoryName});

        PrevingMailUtil.sendMailWithAttachment(user.getEmailAddress(), user.getFullName(), subject, body, file, fileName);

    }


}

package com.preving.liferay.portlet.mailing.web.mail;

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.preving.liferay.portlet.mailing.web.constants.PrevingMailingPortletKeys;
import com.preving.liferay.portlet.mailing.web.constants.PrevingMailingTemplateImageKeys;
import com.preving.liferay.portlet.mailing.web.util.PrevingMailUtil;
import com.preving.liferay.portlet.mailing.web.util.PrevingTemplateUtil;

import java.io.File;
import java.util.ResourceBundle;

public class ContactMailing {

    private static String[] imageKeys = {"[$PREVING_LOGO_SRC$]","[$FACEBOOK_LOGO_SRC$]","[$TWITTER_LOGO_SRC$]","[$YOUTUBE_LOGO_SRC$]","[$LINKEDIN_LOGO_SRC$]","[$PREVING_BACKGROUND_SRC$]"};
    private static String[] imageBase64s = {PrevingMailingTemplateImageKeys.MAIL_TEMPLATE_IMAGE_LOGO_PREVING, PrevingMailingTemplateImageKeys.MAIL_TEMPLATE_IMAGE_LOGO_FACEBOOK,
            PrevingMailingTemplateImageKeys.MAIL_TEMPLATE_IMAGE_LOGO_TWITTER, PrevingMailingTemplateImageKeys.MAIL_TEMPLATE_IMAGE_LOGO_YOUTUBE,
            PrevingMailingTemplateImageKeys.MAIL_TEMPLATE_IMAGE_LOGO_LINKEDIN, PrevingMailingTemplateImageKeys.MAIL_TEMPLATE_IMAGE_BACKGROUND_TITLE};

    public static void contactToAdmin(User userTo, User userFrom, String companyName, String subjectContact, String message, String sourceFileName, String categoryName, File file, String fileName){

        String subject = LanguageUtil.get(ResourceBundle.getBundle("content/Language", userTo.getLocale()), "previngmailing.form.config.email.admin.subject");

        String body = PrevingTemplateUtil.getTemplate(userTo, PrevingMailingPortletKeys.MAIL_TEMPLATE_CONTACT_TO_ADMIN);

        body = PrevingTemplateUtil.replaceImageBase64ToTemplate(body, imageKeys, imageBase64s);

        body = StringUtil.replace(body,
                new String[] { "[$USER_FULL_NAME$]","[$USER_EMAIL$]","[$USER_COMPANY$]","[$SUBJECT_MESSAGE$]","[$BODY_MESSAGE$]","[$ATTACHMENT$]","[$CATEGORY_MESSAGE$]" },
                new String[] {userFrom.getFullName(), userFrom.getEmailAddress(), companyName, subjectContact, message, Validator.isBlank(sourceFileName)?"-":sourceFileName, categoryName});

        PrevingMailUtil.sendMail(userTo, subject, body, file, fileName);

    }

    public static void contactToAdmin(String mailTo, User userFrom, String companyName, String subjectContact, String message, String sourceFileName, String categoryName, File file, String fileName){

        String subject = LanguageUtil.get(ResourceBundle.getBundle("content/Language", userFrom.getLocale()), "previngmailing.form.config.email.admin.subject");

        String body = PrevingTemplateUtil.getTemplate(userFrom, PrevingMailingPortletKeys.MAIL_TEMPLATE_CONTACT_TO_ADMIN);

        body = PrevingTemplateUtil.replaceImageBase64ToTemplate(body, imageKeys, imageBase64s);

        body = StringUtil.replace(body,
                new String[] { "[$USER_FULL_NAME$]","[$USER_EMAIL$]","[$USER_COMPANY$]","[$SUBJECT_MESSAGE$]","[$BODY_MESSAGE$]","[$ATTACHMENT$]","[$CATEGORY_MESSAGE$]" },
                new String[] {userFrom.getFullName(), userFrom.getEmailAddress(), companyName, subjectContact, message, Validator.isBlank(sourceFileName)?"-":sourceFileName, categoryName});

        PrevingMailUtil.sendMail(mailTo, subject, body, file, fileName);

    }

    public static void contactToUser(User user, String subjectContact, String message, String categoryName){

        String subject = LanguageUtil.get(ResourceBundle.getBundle("content/Language", user.getLocale()), "previngmailing.form.config.email.user.subject");

        String body = PrevingTemplateUtil.getTemplate(user, PrevingMailingPortletKeys.MAIL_TEMPLATE_CONTACT_TO_USER);

        body = PrevingTemplateUtil.replaceImageBase64ToTemplate(body, imageKeys, imageBase64s);

        body = StringUtil.replace(body,
                new String[] { "[$SUBJECT_MESSAGE$]","[$BODY_MESSAGE$]","[$CATEGORY_MESSAGE$]" },
                new String[] { subjectContact, message, categoryName});

        PrevingMailUtil.sendMail(user, subject, body);

    }

}

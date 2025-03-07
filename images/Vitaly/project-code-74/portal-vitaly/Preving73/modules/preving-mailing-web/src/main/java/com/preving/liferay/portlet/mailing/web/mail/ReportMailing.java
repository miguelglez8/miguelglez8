package com.preving.liferay.portlet.mailing.web.mail;

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.StringUtil;
import com.preving.liferay.portlet.mailing.web.constants.PrevingMailingPortletKeys;
import com.preving.liferay.portlet.mailing.web.constants.PrevingMailingTemplateImageKeys;
import com.preving.liferay.portlet.mailing.web.util.PrevingMailUtil;
import com.preving.liferay.portlet.mailing.web.util.PrevingTemplateUtil;

import java.util.ResourceBundle;

public class ReportMailing {

    private static String[] imageKeys = {"[$PREVING_LOGO_SRC$]","[$FACEBOOK_LOGO_SRC$]","[$TWITTER_LOGO_SRC$]","[$YOUTUBE_LOGO_SRC$]","[$LINKEDIN_LOGO_SRC$]","[$PREVING_BACKGROUND_SRC$]"};
    private static String[] imageBase64s = {PrevingMailingTemplateImageKeys.MAIL_TEMPLATE_IMAGE_LOGO_PREVING, PrevingMailingTemplateImageKeys.MAIL_TEMPLATE_IMAGE_LOGO_FACEBOOK,
            PrevingMailingTemplateImageKeys.MAIL_TEMPLATE_IMAGE_LOGO_TWITTER, PrevingMailingTemplateImageKeys.MAIL_TEMPLATE_IMAGE_LOGO_YOUTUBE,
            PrevingMailingTemplateImageKeys.MAIL_TEMPLATE_IMAGE_LOGO_LINKEDIN, PrevingMailingTemplateImageKeys.MAIL_TEMPLATE_IMAGE_BACKGROUND_TITLE};

    public static void signUnclosedToUser(User user){

        String subject = LanguageUtil.get(ResourceBundle.getBundle("content/Language", user.getLocale()), "previngmailing.mail.sign.subject");

        String body = PrevingTemplateUtil.getTemplate(user, PrevingMailingPortletKeys.MAIL_TEMPLATE_SCHEDULER_USER_UNCLOSED_SIGN);

        body = PrevingTemplateUtil.replaceImageBase64ToTemplate(body, imageKeys, imageBase64s);

        PrevingMailUtil.sendMail(user, subject, body);

    }
    public static void signUnclosedWeeklyToUser(User user, String data){

        String subject = LanguageUtil.get(ResourceBundle.getBundle("content/Language", user.getLocale()), "previngmailing.mail.sign.subject.weekly");

        String body = PrevingTemplateUtil.getTemplate(user, PrevingMailingPortletKeys.MAIL_TEMPLATE_SCHEDULER_USER_UNCLOSED_SIGN_WEEKLY);

        body = PrevingTemplateUtil.replaceImageBase64ToTemplate(body, imageKeys, imageBase64s);

        body = StringUtil.replace(body,
                new String[] {"[$SIGN_REPORT$]"},
                new String[] {data});

        PrevingMailUtil.sendMail(user, subject, body);

    }

    public static void userWithoutSignToUser(User user, String companyName){

        String subject = LanguageUtil.get(ResourceBundle.getBundle("content/Language", user.getLocale()), "previngmailing.mail.holiday.subject");

        String body = PrevingTemplateUtil.getTemplate(user, PrevingMailingPortletKeys.MAIL_TEMPLATE_SCHEDULER_USER_WITHOUT_SIGN);

        body = PrevingTemplateUtil.replaceImageBase64ToTemplate(body, imageKeys, imageBase64s);

        body = StringUtil.replace(body,
                new String[] {"[$COMPANY_NAME$]"},
                new String[] {companyName});

        PrevingMailUtil.sendMail(user, subject, body);

    }

    public static void signReportToAdminDaily(User user, String data){

        String subject = LanguageUtil.get(ResourceBundle.getBundle("content/Language", user.getLocale()), "previngmailing.mail.daily.subject");

        String body = PrevingTemplateUtil.getTemplate(user, PrevingMailingPortletKeys.MAIL_TEMPLATE_SCHEDULER_SIGN_REPORT);

        body = PrevingTemplateUtil.replaceImageBase64ToTemplate(body, imageKeys, imageBase64s);

        body = StringUtil.replace(body,
                new String[] {"[$SIGN_REPORT$]"},
                new String[] {data});

        PrevingMailUtil.sendMail(user, subject, body);

    }

    public static void signReportToAdminWeekly(User user, String data){

        String subject = LanguageUtil.get(ResourceBundle.getBundle("content/Language", user.getLocale()), "previngmailing.mail.weekly.subject");

        String body = PrevingTemplateUtil.getTemplate(user, PrevingMailingPortletKeys.MAIL_TEMPLATE_SCHEDULER_SIGN_REPORT_WEEKLY);

        body = PrevingTemplateUtil.replaceImageBase64ToTemplate(body, imageKeys, imageBase64s);

        body = StringUtil.replace(body,
                new String[] {"[$SIGN_REPORT$]"},
                new String[] {data});

        PrevingMailUtil.sendMail(user, subject, body);

    }

    public static void signReportToAdminMonthly(User user, String data){

        String subject = LanguageUtil.get(ResourceBundle.getBundle("content/Language", user.getLocale()), "previngmailing.mail.monthly.subject");

        String body = PrevingTemplateUtil.getTemplate(user, PrevingMailingPortletKeys.MAIL_TEMPLATE_SCHEDULER_SIGN_REPORT_MONTHLY);

        body = PrevingTemplateUtil.replaceImageBase64ToTemplate(body, imageKeys, imageBase64s);

        body = StringUtil.replace(body,
                new String[] {"[$SIGN_REPORT$]"},
                new String[] {data});

        PrevingMailUtil.sendMail(user, subject, body);

    }

}

package com.adeplus.liferay.portlet.mailing.web.mail;


import com.adeplus.liferay.portlet.mailing.web.constants.AdeplusMailingPortletKeys;
import com.adeplus.liferay.portlet.mailing.web.constants.AdeplusMailingTemplateImageKeys;
import com.adeplus.liferay.portlet.mailing.web.util.PrevingMailUtil;
import com.adeplus.liferay.portlet.mailing.web.util.PrevingTemplateUtil;
import com.adeplus.liferay.portlet.suite.manager.model.Application;
import com.adeplus.liferay.portlet.suite.manager.model.ApplicationLicense;
import com.adeplus.liferay.portlet.suite.manager.model.CompApplication;
import com.adeplus.liferay.portlet.suite.manager.model.UserApplication;
import com.adeplus.liferay.portlet.suite.manager.service.ApplicationLicenseLocalServiceUtil;
import com.adeplus.liferay.portlet.suite.manager.service.ApplicationLocalServiceUtil;
import com.adeplus.liferay.portlet.suite.manager.service.CompApplicationLocalServiceUtil;
import com.adeplus.liferay.portlet.suite.manager.service.UserApplicationLocalServiceUtil;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.List;
import java.util.ResourceBundle;

public class UserMailing {
	private static Log _log = LogFactoryUtil.getLog(UserMailing.class);
    private static String[] imageKeys = {"[$ADEPLUS_LOGO_SRC$]","[$ADEPLUS_LOGO_FOOTER_SRC$]", "[$FACEBOOK_LOGO_SRC$]","[$TWITTER_LOGO_SRC$]","[$YOUTUBE_LOGO_SRC$]","[$LINKEDIN_LOGO_SRC$]","[$SUBTITLE_BACKGROUND_SRC$]"};
    private static String[] imageBase64s = {AdeplusMailingTemplateImageKeys.MAIL_TEMPLATE_IMAGE_LOGO,
                                            AdeplusMailingTemplateImageKeys.MAIL_TEMPLATE_IMAGE_LOGO_FOOTER,
                                            AdeplusMailingTemplateImageKeys.MAIL_TEMPLATE_IMAGE_LOGO_FACEBOOK,
                                            AdeplusMailingTemplateImageKeys.MAIL_TEMPLATE_IMAGE_LOGO_TWITTER,
                                            AdeplusMailingTemplateImageKeys.MAIL_TEMPLATE_IMAGE_LOGO_YOUTUBE,
                                            AdeplusMailingTemplateImageKeys.MAIL_TEMPLATE_IMAGE_LOGO_LINKEDIN,
                                            AdeplusMailingTemplateImageKeys.MAIL_TEMPLATE_IMAGE_BACKGROUND_TITLE};


    public static void userCreate(User user, long compId, String password, boolean isMulti){
        String subject = LanguageUtil.get(ResourceBundle.getBundle("content/Language", user.getLocale()), "mailing.mail.subject.create.user");
        String body = PrevingTemplateUtil.getTemplate(user, AdeplusMailingPortletKeys.MAIL_TEMPLATE_USER_CREATE);

        body = PrevingTemplateUtil.replaceImageBase64ToTemplate(body, imageKeys, imageBase64s);

        body = StringUtil.replace(body,
                new String[] {"[$USER_EMAIL$]","[$USER_PASSWORD$]","[$HELP_VIDEO_NEW_USER$]"},
                new String[] {user.getEmailAddress(), password, AdeplusMailingTemplateImageKeys.MAIL_TEMPLATE_VIDEO_NEW_USER });

        String userAppListHtml = "<ul style=\"list-style:none; padding-left:10px; margin:0\">";
        List<UserApplication> applicationsFromUser = UserApplicationLocalServiceUtil.getApplicationsFromUser(user.getUserId(), compId);

        for(UserApplication ua:applicationsFromUser){
            Application application = ApplicationLocalServiceUtil.fetchApplication(ua.getApplicationId());
            if(Validator.isNotNull(application)) {

                String contract = "";
                if ("LEGAL_PLUS".equals(application.getAlias())) {
                    CompApplication compApplication = CompApplicationLocalServiceUtil.getCompanyApplication(compId, application.getApplicationId(), isMulti);
                    ApplicationLicense applicationLicense = ApplicationLicenseLocalServiceUtil.fetchByAppLicense(application.getApplicationId(), compApplication.getLicenseId());
                    contract = "<ul><li>" + applicationLicense.getName() + "</li></ul>";
                }

                userAppListHtml += "<li style=\"margin-bottom: 6px;\" valign=\"middle\">"
                        + "<img src=\""+ AdeplusMailingTemplateImageKeys.MAIL_TEMPLATE_APLICATION_LIST +"\" style=\"display:inline-block;\" width=\"20\" />"
                        + "<span href=\"" + application.getUrl() +"\" style=\"text-decoration:none;font-size:13px;font-weight:bold;color:#004D71; text-align:left; vertical-align: super;margin-left:12px;\">"
                        + application.getName() + ": " + application.getDescription() + "</span>" + contract + "</li>";
            }
        }
        userAppListHtml += "</ul>";

        body = StringUtil.replace(body,
                new String[] {"[$ADEPLUS_USER_APPLICATIONS$]"},
                new String[] {userAppListHtml});

        PrevingMailUtil.sendMail(user, subject, body);

    }
    public static void userUpdate(User user, long compId, boolean isMulti){

        String subject = LanguageUtil.get(ResourceBundle.getBundle("content/Language", user.getLocale()), "mailing.mail.subject.update.user");

        String body = PrevingTemplateUtil.getTemplate(user, AdeplusMailingPortletKeys.MAIL_TEMPLATE_USER_UPDATE);

        body = PrevingTemplateUtil.replaceImageBase64ToTemplate(body, imageKeys, imageBase64s);

        body = StringUtil.replace(body,
                new String[] {"[$USER_EMAIL$]"},
                new String[] {user.getEmailAddress()});

        String userAppListHtml = "<ul style=\"list-style:none; padding-left:10px; margin:0\">";
        List<UserApplication> applicationsFromUser = UserApplicationLocalServiceUtil.getApplicationsFromUser(user.getUserId(), compId);

        for(UserApplication ua:applicationsFromUser){
            Application application = ApplicationLocalServiceUtil.fetchApplication(ua.getApplicationId());
            if(Validator.isNotNull(application)) {

                String contract = "";
                if ("LEGAL_PLUS".equals(application.getAlias())) {
                    CompApplication compApplication = CompApplicationLocalServiceUtil.getCompanyApplication(compId, application.getApplicationId(), isMulti);
                    ApplicationLicense applicationLicense = ApplicationLicenseLocalServiceUtil.fetchByAppLicense(application.getApplicationId(), compApplication.getLicenseId());
                    contract = "<ul><li>" + applicationLicense.getName() + "</li></ul>";
                }

                userAppListHtml += "<li style=\"margin-bottom: 6px;\" valign=\"middle\">"
                        + "<img src=\""+ AdeplusMailingTemplateImageKeys.MAIL_TEMPLATE_APLICATION_LIST +"\" style=\"display:inline-block;\" width=\"20\" />"
                        + "<span href=\"" + application.getUrl() +"\" style=\"text-decoration:none;font-size:13px;font-weight:bold;color:#004D71; text-align:left; vertical-align: super;margin-left:12px;\">"
                        + application.getName() + ": " + application.getDescription() + "</span>" + contract + "</li>";
            }
        }
        userAppListHtml += "</ul>";

        body = StringUtil.replace(body,
                new String[] {"[$ADEPLUS_USER_APPLICATIONS$]"},
                new String[] {userAppListHtml});


        PrevingMailUtil.sendMail(user, subject, body);

    }

    public static void userDelete(User user){

        String subject = LanguageUtil.get(ResourceBundle.getBundle("content/Language", user.getLocale()), "mailing.mail.subject.delete.user");

        String body = PrevingTemplateUtil.getTemplate(user, AdeplusMailingPortletKeys.MAIL_TEMPLATE_USER_DELETE);

        body = PrevingTemplateUtil.replaceImageBase64ToTemplate(body, imageKeys, imageBase64s);

        PrevingMailUtil.sendMail(user, subject, body);

    }

    public static void userDeleteLastDay(User user){

        String subject = LanguageUtil.get(ResourceBundle.getBundle("content/Language", user.getLocale()), "mailing.mail.subject.delete.user");

        String body = PrevingTemplateUtil.getTemplate(user, AdeplusMailingPortletKeys.MAIL_TEMPLATE_USER_DELETE_LAST_DAY);

        body = PrevingTemplateUtil.replaceImageBase64ToTemplate(body, imageKeys, imageBase64s);

        PrevingMailUtil.sendMail(user, subject, body);

    }
    
    
    public static void userChangePass(String ticketKey, ThemeDisplay theme) {
    	
    	_log.info("userChangePass");
    	String subject = LanguageUtil.get(ResourceBundle.getBundle("content/Language", theme.getUser().getLocale()), "mailing.mail.subject.remind.password.user");

        String body = PrevingTemplateUtil.getTemplate(theme.getUser(), AdeplusMailingPortletKeys.MAIL_TEMPLATE_USER_REMIND_PASSWORD);
        body = PrevingTemplateUtil.replaceImageBase64ToTemplate(body, imageKeys, imageBase64s);
        body = StringUtil.replace(body,
                new String[] {" [$ENLACE$]"},
                new String[] {ticketKey });
        
       
    	
    	PrevingMailUtil.sendMail(theme.getUser(), subject, body);
    }

    public static void userRemindPassword(User user, long compId){
    	
    	
/*        String subject = LanguageUtil.get(ResourceBundle.getBundle("content/Language", user.getLocale()), "mailing.mail.subject.remind.password.user");

        String body = PrevingTemplateUtil.getTemplate(user, AdeplusMailingPortletKeys.MAIL_TEMPLATE_USER_REMIND_PASSWORD);
        
        

        body = PrevingTemplateUtil.replaceImageBase64ToTemplate(body, imageKeys, imageBase64s);

        body = StringUtil.replace(body,
                new String[] {"[$USER_EMAIL$]","[$USER_PASSWORD$]","[$HELP_VIDEO_NEW_USER$]"},
                new String[] {user.getEmailAddress(), "*******", AdeplusMailingTemplateImageKeys.MAIL_TEMPLATE_VIDEO_NEW_USER });

        String userAppListHtml = "<ul style=\"list-style:none; padding-left:10px; margin:0\">";
        List<UserApplication> applicationsFromUser = UserApplicationLocalServiceUtil.getApplicationsFromUser(user.getUserId(), compId);

        for(UserApplication ua:applicationsFromUser){
            Application application = ApplicationLocalServiceUtil.fetchApplication(ua.getApplicationId());
            if(Validator.isNotNull(application)) {
                userAppListHtml += "<li style=\"margin-bottom: 6px;\" valign=\"middle\">"
                        + "<img src=\""+ AdeplusMailingTemplateImageKeys.MAIL_TEMPLATE_APLICATION_LIST +"\" style=\"display:inline-block;\" width=\"20\" />"
                        + "<span href=\"" + application.getUrl() +"\" style=\"text-decoration:none;font-size:13px;font-weight:bold;color:#004D71; text-align:left; vertical-align: super;\">"
                        + application.getName() + "</span> " + ": " + application.getDescription() + "</li>";
            }
        }
        userAppListHtml += "</ul>";

        body = StringUtil.replace(body,
                new String[] {"[$ADEPLUS_USER_APPLICATIONS$]"},
                new String[] {userAppListHtml});

        PrevingMailUtil.sendMail(user, subject, body);*/

    }

    public static void userPasswordChanged(User user){

        String subject = LanguageUtil.get(ResourceBundle.getBundle("content/Language", user.getLocale()), "mailing.test.remind.password");

        String body = PrevingTemplateUtil.getTemplate(user, AdeplusMailingPortletKeys.MAIL_TEMPLATE_USER_PASSWORD_CHANGED);

        body = PrevingTemplateUtil.replaceImageBase64ToTemplate(body, imageKeys, imageBase64s);

        PrevingMailUtil.sendMail(user, subject, body);

    }

}

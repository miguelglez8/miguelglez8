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
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.List;
import java.util.ResourceBundle;

public class CompanyMailing {

    private static String[] imageKeys = {"[$ADEPLUS_LOGO_SRC$]","[$ADEPLUS_LOGO_FOOTER_SRC$]", "[$FACEBOOK_LOGO_SRC$]","[$TWITTER_LOGO_SRC$]","[$YOUTUBE_LOGO_SRC$]","[$LINKEDIN_LOGO_SRC$]","[$SUBTITLE_BACKGROUND_SRC$]"};
    private static String[] imageBase64s = {AdeplusMailingTemplateImageKeys.MAIL_TEMPLATE_IMAGE_LOGO,
                                            AdeplusMailingTemplateImageKeys.MAIL_TEMPLATE_IMAGE_LOGO_FOOTER,
                                            AdeplusMailingTemplateImageKeys.MAIL_TEMPLATE_IMAGE_LOGO_FACEBOOK,
                                            AdeplusMailingTemplateImageKeys.MAIL_TEMPLATE_IMAGE_LOGO_TWITTER,
                                            AdeplusMailingTemplateImageKeys.MAIL_TEMPLATE_IMAGE_LOGO_YOUTUBE,
                                            AdeplusMailingTemplateImageKeys.MAIL_TEMPLATE_IMAGE_LOGO_LINKEDIN,
                                            AdeplusMailingTemplateImageKeys.MAIL_TEMPLATE_IMAGE_BACKGROUND_TITLE};

    public static void companyDelete(User user){

        String subject = LanguageUtil.get(ResourceBundle.getBundle("content/Language", user.getLocale()), "mailing.mail.subject.delete.company");

        String body = PrevingTemplateUtil.getTemplate(user, AdeplusMailingPortletKeys.MAIL_TEMPLATE_COMPANY_DELETE);

        body = PrevingTemplateUtil.replaceImageBase64ToTemplate(body, imageKeys, imageBase64s);

        PrevingMailUtil.sendMail(user, subject, body);

    }

    public static void companyAddApplication(User user, long compId, List<CompApplication> addedApps){

        String subject = LanguageUtil.get(ResourceBundle.getBundle("content/Language", user.getLocale()), "mailing.mail.subject.update.company");

        String body = PrevingTemplateUtil.getTemplate(user, AdeplusMailingPortletKeys.MAIL_TEMPLATE_COMPANY_ADD_APPLICATION);

        body = PrevingTemplateUtil.replaceImageBase64ToTemplate(body, imageKeys, imageBase64s);

        body = StringUtil.replace(body,
                new String[] {"[$USER_EMAIL$]"},
                new String[] {user.getEmailAddress()});

        String userAppListHtml = "<ul style=\"list-style:none; padding-left:10px; margin:0\">";

        for(CompApplication ca:addedApps){
            Application application = ApplicationLocalServiceUtil.fetchApplication(ca.getApplicationId());
            if(Validator.isNotNull(application)) {

                String contract = "";
                if ("LEGAL_PLUS".equals(application.getAlias())) {
                    CompApplication compApplication = CompApplicationLocalServiceUtil.getCompanyApplication(compId, application.getApplicationId());
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

    public static void companyDeleteApplication(User user, long compId, List<CompApplication> deletedApps){

        String subject = LanguageUtil.get(ResourceBundle.getBundle("content/Language", user.getLocale()), "mailing.mail.subject.update.company.delete.app");

        String body = PrevingTemplateUtil.getTemplate(user, AdeplusMailingPortletKeys.MAIL_TEMPLATE_COMPANY_DELETE_APPLICATION);

        body = PrevingTemplateUtil.replaceImageBase64ToTemplate(body, imageKeys, imageBase64s);

        body = StringUtil.replace(body,
                new String[] {"[$USER_EMAIL$]"},
                new String[] {user.getEmailAddress()});

        String userAppListHtml = "<ul style=\"list-style:none; padding-left:10px; margin:0\">";
        List<UserApplication> applicationsFromUser = UserApplicationLocalServiceUtil.getApplicationsFromUser(user.getUserId(), compId);

        for(CompApplication ca:deletedApps){
            Application application = ApplicationLocalServiceUtil.fetchApplication(ca.getApplicationId());
            if(Validator.isNotNull(application)) {

                String contract = "";
                if ("LEGAL_PLUS".equals(application.getAlias())) {
                    CompApplication compApplication = CompApplicationLocalServiceUtil.getCompanyApplication(compId, application.getApplicationId());
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

}

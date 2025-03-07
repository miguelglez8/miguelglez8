package com.preving.liferay.portlet.mailing.web.mail;

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.preving.liferay.portlet.calendar.manager.model.Activity;
import com.preving.liferay.portlet.mailing.web.constants.PrevingMailingPortletKeys;
import com.preving.liferay.portlet.mailing.web.constants.PrevingMailingTemplateImageKeys;
import com.preving.liferay.portlet.mailing.web.util.PrevingMailUtil;
import com.preving.liferay.portlet.mailing.web.util.PrevingTemplateUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class UserMailing {

    private static Log _log = LogFactoryUtil.getLog(UserMailing.class);

    private static String[] imageKeys = {"[$PREVING_LOGO_SRC$]","[$FACEBOOK_LOGO_SRC$]","[$TWITTER_LOGO_SRC$]","[$YOUTUBE_LOGO_SRC$]","[$LINKEDIN_LOGO_SRC$]","[$PREVING_BACKGROUND_SRC$]"};
    private static String[] imageBase64s = {PrevingMailingTemplateImageKeys.MAIL_TEMPLATE_IMAGE_LOGO_PREVING, PrevingMailingTemplateImageKeys.MAIL_TEMPLATE_IMAGE_LOGO_FACEBOOK,
            PrevingMailingTemplateImageKeys.MAIL_TEMPLATE_IMAGE_LOGO_TWITTER, PrevingMailingTemplateImageKeys.MAIL_TEMPLATE_IMAGE_LOGO_YOUTUBE,
            PrevingMailingTemplateImageKeys.MAIL_TEMPLATE_IMAGE_LOGO_LINKEDIN, PrevingMailingTemplateImageKeys.MAIL_TEMPLATE_IMAGE_BACKGROUND_TITLE};

    public static void userCreateToUser(User user, String password){

        String subject = LanguageUtil.get(ResourceBundle.getBundle("content/Language", user.getLocale()), "previngmailing.mail.subject.welcome");

        String body = PrevingTemplateUtil.getTemplate(user, PrevingMailingPortletKeys.MAIL_TEMPLATE_USER_CREATE_TO_USER);

        body = PrevingTemplateUtil.replaceImageBase64ToTemplate(body, imageKeys, imageBase64s);

        body = StringUtil.replace(body,
                new String[] {"[$USER_EMAIL$]","[$USER_PASSWORD$]","[$HELP_VIDEO_NEW_COMPANY$]"},
                new String[] {user.getEmailAddress(), password, PrevingMailingTemplateImageKeys.MAIL_TEMPLATE_VIDEO_NEW_COMPANY });

        PrevingMailUtil.sendMail(user, subject, body);

        if(_log.isDebugEnabled()) {
            _log.debug("Send mail :: userCreateToUser :: ");
        }

    }

    public static void userDeleteToUser(User user){

        String subject = LanguageUtil.get(ResourceBundle.getBundle("content/Language", user.getLocale()), "previngmailing.mail.subject.deleted.user");

        String body = PrevingTemplateUtil.getTemplate(user, PrevingMailingPortletKeys.MAIL_TEMPLATE_USER_DELETE_TO_USER);

        body = PrevingTemplateUtil.replaceImageBase64ToTemplate(body, imageKeys, imageBase64s);

        PrevingMailUtil.sendMail(user, subject, body);

        if(_log.isDebugEnabled()) {
            _log.debug("Send mail :: userDeleteToUser :: ");
        }

    }

    public static void userRemingPasswordToUser(User user, String password){

        String subject = LanguageUtil.get(ResourceBundle.getBundle("content/Language", user.getLocale()), "previngmailing.mail.subject.change.password");

        String body = PrevingTemplateUtil.getTemplate(user, PrevingMailingPortletKeys.MAIL_TEMPLATE_USER_REMIND_PASSWORD_TO_USER);

        body = PrevingTemplateUtil.replaceImageBase64ToTemplate(body, imageKeys, imageBase64s);

        body = StringUtil.replace(body,
                new String[] {"[$USER_EMAIL$]","[$USER_PASSWORD$]","[$HELP_VIDEO_NEW_COMPANY$]"},
                new String[] {user.getEmailAddress(), password, PrevingMailingTemplateImageKeys.MAIL_TEMPLATE_VIDEO_NEW_COMPANY });

        PrevingMailUtil.sendMail(user, subject, body);

        if(_log.isDebugEnabled()) {
            _log.debug("Send mail :: userRemingPasswordToUser :: ");
        }

    }

    public static void userActivateToUser(User user, String password){

        String subject = LanguageUtil.get(ResourceBundle.getBundle("content/Language", user.getLocale()), "previngmailing.mail.subject.welcome");

        String body = PrevingTemplateUtil.getTemplate(user, PrevingMailingPortletKeys.MAIL_TEMPLATE_USER_ACTIVATE_TO_USER);

        body = PrevingTemplateUtil.replaceImageBase64ToTemplate(body, imageKeys, imageBase64s);

        body = StringUtil.replace(body,
                new String[] {"[$USER_EMAIL$]","[$USER_PASSWORD$]","[$HELP_VIDEO_NEW_COMPANY$]"},
                new String[] {user.getEmailAddress(), password, PrevingMailingTemplateImageKeys.MAIL_TEMPLATE_VIDEO_NEW_COMPANY });

        PrevingMailUtil.sendMail(user, subject, body);

        if(_log.isDebugEnabled()) {
            _log.debug("Send mail :: userActivateToUser :: ");
        }

    }

    public static void userActivityExtraCreate(String userResponsable, User user, ThemeDisplay themeDisplay, Activity activity,
                                               String horaInicio,String horaFin,String fechaRegistro,String companyName,String observaciones){

        _log.info("entro en envio correo");
        String subject = LanguageUtil.get(ResourceBundle.getBundle("content/Language", themeDisplay.getLocale()), "previngmailing.mail.extra.activity")+companyName;

        String body = PrevingTemplateUtil.getTemplate(themeDisplay, PrevingMailingPortletKeys.MAIL_TEMPLATE_USER_EXTRA);
        body = PrevingTemplateUtil.replaceImageBase64ToTemplate(body, imageKeys, imageBase64s);
        String tieneResponsable="block";
        if(Validator.isBlank(userResponsable)){
            tieneResponsable="none";
        }




        body = StringUtil.replace(body,
                new String[] {"[$USER_EMAIL$]","[$FECHA_HOY$]","[$ACTIVIDAD_NOMBRE$]","[$HORA_INICIO$]","[$HORA_FIN$]","[$ACTIVIDAD_OBSERVACIONES$]",
                "[$TIENE_RESPONABLE$]","[$RESPONABLE_MAIL$]"},
                new String[] {user.getEmailAddress(),fechaRegistro,activity.getName(themeDisplay.getLocale()), horaInicio,
                        horaFin,observaciones,tieneResponsable, userResponsable });

        PrevingMailUtil.sendMailExtra(userResponsable, subject, body,activity.getUsersToInform());

        if(_log.isDebugEnabled()) {
            _log.debug("Send mail :: userActivateToUser :: ");
        }

    }

}

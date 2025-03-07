package com.preving.liferay.portlet.create.company.web.message;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.messaging.MessageListenerException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.util.ContentUtil;
import com.preving.liferay.portlet.create.company.web.constants.CreateCompanyPortletKeys;
import com.preving.liferay.portlet.create.company.web.util.*;
import com.preving.liferay.portlet.mailing.web.mail.CompanyMailing;
import org.osgi.service.component.annotations.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

@Component(
        immediate = true,
        property = {"destination.name=" + CreateCompanyPortletKeys.MESSAGE_BUS_DESTINATION_NAME},
        service = MessageListener.class
)
public class PrevingMessageListener implements MessageListener {

    private static Log _log = LogFactoryUtil.getLog(PrevingMessageListener.class);

    //SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    public void receive(Message message) throws MessageListenerException {

        //Create company for Preving.

        //Get all data.
        String companyName = message.getString(CreateCompanyPortletKeys.COMPANY_NAME);
        long companyId = message.getLong(CreateCompanyPortletKeys.COMPANY_ID);
        String portalURL = message.getString(CreateCompanyPortletKeys.PORTAL_URL);
        String pathMain = message.getString(CreateCompanyPortletKeys.PATH_MAIN);
        long creatorUserId = message.getLong(CreateCompanyPortletKeys.COMPANY_CREATOR_USER_ID);
        String cif = message.getString(CreateCompanyPortletKeys.COMPANY_CIF);
        String adminName = message.getString(CreateCompanyPortletKeys.COMPANY_ADMIN_NAME);
        String adminSurname = message.getString(CreateCompanyPortletKeys.COMPANY_ADMIN_SURNAME);
        String adminEmail = message.getString(CreateCompanyPortletKeys.COMPANY_ADMIN_EMAIL);
        String password = message.getString(CreateCompanyPortletKeys.COMPANY_ADMIN_PASSWORD);
        String adminNif = message.getString(CreateCompanyPortletKeys.COMPANY_ADMIN_NIF);
        String source = message.getString(CreateCompanyPortletKeys.COMPANY_SOURCE);
        long templateId = message.getLong(CreateCompanyPortletKeys.CONFIGURATION_TEMPLATE_ID);
        String languageId = message.getString(CreateCompanyPortletKeys.LANGUAGE_ID);

        String startDate = message.getString(CreateCompanyPortletKeys.COMPANY_START_DATE);
        String implementationDate = message.getString(CreateCompanyPortletKeys.COMPANY_IMPLANTATION_DATE);
        String endDate = message.getString(CreateCompanyPortletKeys.COMPANY_END_DATE);

        User user = null;
        try {
            user = UserLocalServiceUtil.getUser(creatorUserId);
        } catch (PortalException e) {
            _log.error(e);
            return;
        }

        //Format date values
        Date stDate = null;
        Date impDate = null;
        Date delDate = null;
        try {

            if(Validator.isNotNull(startDate) && !Validator.isBlank(startDate)){
                stDate = dateFormat.parse(startDate);
            }

            if(Validator.isNotNull(implementationDate) && !Validator.isBlank(implementationDate)){
                impDate = dateFormat.parse(implementationDate);
            }

            if(Validator.isNotNull(endDate) && !Validator.isBlank(endDate)){
                delDate = dateFormat.parse(endDate);
            }

        } catch (ParseException e) {
            _log.error(e);
        }

        Locale locale = LocaleUtil.fromLanguageId(languageId);

        if(Validator.isNull(locale)){
            locale = LocaleUtil.getSiteDefault();
        }

        if(Validator.isBlank(adminName)){adminName = companyName;}
        if(Validator.isBlank(adminSurname)){adminSurname = companyName;}

        //Create a new site.
        String siteDescription = "Registro de horas de " + companyName+".";
        Group group = PrevingCompanyUtil.createCompany(companyName, creatorUserId, cif, siteDescription, new ServiceContext());

        if (Validator.isNotNull(group)) {

            /** Operations in group: **/
            // Use the site template in created site.
            PrevingCompanyUtil.addTemplateToCompany(group, templateId);

            // Add the default activities for the company.
            PrevingActivityLocalizedUtil.createDefaultActivities(companyId, group.getGroupId());

            // Add the default activities for the company.
            PrevingHolidayLocalizedUtil.createDefaultHolidays(companyId, group.getGroupId());


            /** Operations for user admin in group: **/
            //Get user or create new user if doesn't exists.
            User userAdmin = PrevingUserUtil.getUser(companyId, group.getGroupId(), adminName, adminSurname, adminNif, adminEmail, password, portalURL, pathMain, locale);

            // Add the default configuration of company.
            PrevingCompanyUtil.createCompanyConfiguration(companyId, group.getGroupId(), userAdmin.getUserId(), cif, source, stDate, impDate, delDate, 0 ,0 );

            // Add user admin to new site.
            PrevingCompanyUtil.addUserToCompany(userAdmin.getUserId(), group.getGroupId());

            //Add role for admin in portal.
            String roleAdminName = PrefsPropsUtil.getString(CreateCompanyPortletKeys.PROPERTY_ROLE_COMPANY_ADMIN);
            if(!Validator.isBlank(roleAdminName)) {
                PrevingUserUtil.addRoleToUser(userAdmin, companyId, roleAdminName);
                // Add user to group with role admin for the site.
                PrevingCompanyUtil.addUserToGroup(userAdmin, companyId, group.getGroupId(), roleAdminName);
            }

            //Add role for admin in site.
            String roleSiteAdminName = PrefsPropsUtil.getString(CreateCompanyPortletKeys.PROPERTY_ROLE_COMPANY_SITE_ADMIN);
            if(!Validator.isBlank(roleSiteAdminName)) {
                PrevingUserUtil.addRoleGroupToUser(group.getGroupId(), userAdmin, companyId, roleSiteAdminName);
            }

            //Notificacion admin of Preving
            CompanyMailing.companyCreateToCreator(user, companyName, cif);

            // Send success notification to user.
            String subject = LanguageUtil.get(ResourceBundle.getBundle("content/Language", locale), "previngmailing.mail.subject.new.company");
            String body = ContentUtil.get("/templates/CreateCompanyAdminTemplate_"+ languageId +".tmpl");
            if(Validator.isNull(body) || Validator.isBlank(body)){
                body = ContentUtil.get("/templates/CreateCompanyAdminTemplate_es_ES.tmpl");
            }

            body = StringUtil.replace(body,
                    new String[] {"[$COMPANY_NAME$]","[$COMPANY_CIF$]"},
                    new String[] {companyName, cif});


            //Notification
            NotificacionUtil.sendNotificacion(creatorUserId, creatorUserId, subject, body);

            if (_log.isDebugEnabled()) {
                _log.debug("Company name: " + companyName);
                _log.debug("Site friendlyURL: " + group.getFriendlyURL());
                _log.debug("Admin email: " + userAdmin.getEmailAddress());
                _log.debug("title: " + userAdmin.getEmailAddress());
                _log.debug("body: " + userAdmin.getEmailAddress());
            }

        } else {

            String subject = LanguageUtil.get(ResourceBundle.getBundle("content/Language", locale), "createcompany.mail.subject.error");
            String body = ContentUtil.get("/templates/AdminNotificationImportErrorDefault_"+ languageId +".tmpl");
            if(Validator.isNull(body) || Validator.isBlank(body)){
                body = ContentUtil.get("/templates/AdminNotificationImportErrorDefault_es_ES.tmpl");
            }

            body = StringUtil.replace(body,
                    new String[] {"[$COMPANY_NAME$]","[$COMPANY_CIF$]"},
                    new String[] {companyName, cif});

            //Notificacion admin of Preving
            PrevingMailUtil.sendMail(creatorUserId, subject, body);
            NotificacionUtil.sendNotificacion(creatorUserId, creatorUserId, subject, body);
        }

    }

}

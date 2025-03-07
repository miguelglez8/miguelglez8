package com.preving.liferay.portlet.create.company.web.action;

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.preving.liferay.portlet.commons.web.keycloak.PrevingKeycloakUtil;
import com.preving.liferay.portlet.create.company.web.constants.CreateCompanyPortletKeys;
import com.preving.liferay.portlet.create.company.web.util.NotificacionUtil;
import com.preving.liferay.portlet.create.company.web.util.PrevingCreateCompanyUtil;
import com.preving.liferay.portlet.mailing.web.mail.CompanyMailing;
import org.apache.commons.lang3.RandomStringUtils;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletPreferences;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + CreateCompanyPortletKeys.CREATECOMPANY,
                "mvc.command.name=/company/create"
        },
        service = MVCActionCommand.class
)
public class CreateCompanyMVCActionCommand implements MVCActionCommand {

    private static Log _log = LogFactoryUtil.getLog(CreateCompanyMVCActionCommand.class);

    static SimpleDateFormat dateFormatLog = new SimpleDateFormat("yyyy-dd-MM HH:mm:ss.SSS");

    @Override
    public boolean processAction(ActionRequest actionRequest, ActionResponse actionResponse) throws PortletException {

        ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

        String companyName 	= ParamUtil.getString(actionRequest, CreateCompanyPortletKeys.COMPANY_NAME, "");
        String cif 			= ParamUtil.getString(actionRequest, CreateCompanyPortletKeys.COMPANY_CIF, "");
        String adminName 	= ParamUtil.getString(actionRequest, CreateCompanyPortletKeys.COMPANY_ADMIN_NAME, "");
        String adminSurname	= ParamUtil.getString(actionRequest, CreateCompanyPortletKeys.COMPANY_ADMIN_SURNAME, "");
        String adminNif 	= ParamUtil.getString(actionRequest, CreateCompanyPortletKeys.COMPANY_ADMIN_NIF, "");
        String adminEmail 	= ParamUtil.getString(actionRequest, CreateCompanyPortletKeys.COMPANY_ADMIN_EMAIL, "");
        String source 		= ParamUtil.getString(actionRequest, CreateCompanyPortletKeys.COMPANY_SOURCE, "");
        String endDate 	    = ParamUtil.getString(actionRequest, CreateCompanyPortletKeys.COMPANY_END_DATE, "");
        String startDate 	= ParamUtil.getString(actionRequest, CreateCompanyPortletKeys.COMPANY_START_DATE, "");
        String implementationDate = ParamUtil.getString(actionRequest, CreateCompanyPortletKeys.COMPANY_IMPLANTATION_DATE, "");

        Date startCreate = new Date();
        _log.debug(dateFormatLog.format(startCreate) + " START TO CREATE COMPANY " + companyName + ", CIF " + cif);

        // Use the site template in created site.
        PortletPreferences preferences = actionRequest.getPreferences();
        _log.debug("preferences.getValue(CreateCompanyPortletKeys.CONFIGURATION_TEMPLATE_ID: " + preferences.getValue(CreateCompanyPortletKeys.CONFIGURATION_TEMPLATE_ID, "0"));
        String templateId = preferences.getValue(CreateCompanyPortletKeys.CONFIGURATION_TEMPLATE_ID, "0");

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

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

        //VALIDATIONS
        //If user screenName already exists.
        /*if(Validator.isBlank(adminName)){
            _log.error("Admin name is blank. ");
            SessionErrors.add(actionRequest, "error-create-admin-name-blank");
            return true;
        }*/

        Group company = PrevingCreateCompanyUtil.createCompany(
                themeDisplay.getCompanyId(),
                companyName,
                themeDisplay.getUser(),
                cif,
                source,
                stDate,
                impDate,
                delDate,
                0,
                0);

        String passwordRandon = RandomStringUtils.random(8,true, true);

        User user = PrevingCreateCompanyUtil.createUser(
                themeDisplay.getCompanyId(),
                company.getGroupId(),
                adminName,
                adminSurname,
                adminNif,
                adminEmail,
                passwordRandon,
                themeDisplay.getPortalURL(),
                themeDisplay.getPathMain(),
                themeDisplay.getLocale()
        );

        //Notificacion admin of Preving
        CompanyMailing.companyCreateToCreator(themeDisplay.getUser(), companyName, cif);

        // Send success notification to user creator.
        String subject = LanguageUtil.get(ResourceBundle.getBundle("content/Language", themeDisplay.getUser().getLocale()), "previngmailing.mail.subject.new.company");
        String body = "CIF: " + cif + ", " +companyName;

        //Notification
        NotificacionUtil.sendNotificacion(themeDisplay.getUserId(), themeDisplay.getUserId(), subject, body);

        //Keycloak create company
        PrevingKeycloakUtil.createCompanyKeycloak(company.getGroupId(), companyName, cif, "", "", delDate, "");

        //Keycloak create user
        /*PrevingKeycloakUtil.operationUser(true,
                company.getCompanyId(), user.getUserId(), user.getEmailAddress(), user.getFirstName(), user.getLastName(), adminNif, "", passwordRandon, endDate, true);*/

        //_log.debug("create command : " + company.getGroupId());
        try {
            PrevingKeycloakUtil.operationUser(true,
                    company.getGroupId(), user.getUserId(), user.getEmailAddress(), user.getFirstName(), user.getLastName(), adminNif, "", passwordRandon, endDate, true);
        }catch(Exception e){
            _log.error("Error al crear/asociar al usuario con la empresa en keycloack");
        }


        Date endCreate = new Date();
        _log.debug(dateFormatLog.format(endCreate) + " END TO CREATE COMPANY " + companyName + ", time " + ((endCreate.getTime() - startCreate.getTime())/1000) + " seconds.");

        //Select template to site by messagelistener
        PrevingCreateCompanyUtil.selectSiteTemplateToGroup(company, Long.parseLong(templateId));

        SessionMessages.add(actionRequest, "create-company-success");
        actionResponse.setRenderParameter("mvcPath","/success.jsp");

        return false;
    }

}

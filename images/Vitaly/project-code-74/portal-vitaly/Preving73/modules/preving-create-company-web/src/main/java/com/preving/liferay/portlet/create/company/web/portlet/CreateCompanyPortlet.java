package com.preving.liferay.portlet.create.company.web.portlet;

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBus;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.*;
import com.preving.liferay.portlet.calendar.manager.model.CompanyConf;
import com.preving.liferay.portlet.calendar.manager.model.Sign;
import com.preving.liferay.portlet.calendar.manager.service.CompanyConfLocalServiceUtil;
import com.preving.liferay.portlet.calendar.manager.service.SignLocalServiceUtil;
import com.preving.liferay.portlet.commons.web.keycloak.PrevingKeycloakUtil;
import com.preving.liferay.portlet.create.company.web.bean.CSVData;
import com.preving.liferay.portlet.create.company.web.bean.CompanyBean;
import com.preving.liferay.portlet.create.company.web.constants.CreateCompanyPortletKeys;
import com.preving.liferay.portlet.create.company.web.util.*;
import com.preving.liferay.portlet.mailing.web.mail.CompanyMailing;
import org.apache.commons.lang3.RandomStringUtils;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author agarciap
 */
@Component(
	configurationPid = CreateCompanyPortletKeys.CREATECOMPANY,
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.preving",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=false",
		"javax.portlet.display-name=CreateCompany",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.init-param.config-template=/configuration.jsp",
		"javax.portlet.init-param.add-process-action-success-action=true",
		"javax.portlet.name=" + CreateCompanyPortletKeys.CREATECOMPANY,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class CreateCompanyPortlet extends MVCPortlet {

	private static Log _log = LogFactoryUtil.getLog(CreateCompanyPortlet.class);

	//SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException {

		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);

		PortletPreferences preferences = renderRequest.getPreferences();

		String importHelpText = LocalizationUtil.getPreferencesValue(preferences, CreateCompanyPortletKeys.CONFIGURATION_IMPORT_HELP_TEXT, themeDisplay.getLanguageId());

		List<CompanyBean> companyBeanList = PrevingCompanyUtil.getAllCompaniesConfiguration(themeDisplay.getCompanyId());

		String sources = PrefsPropsUtil.getString(CreateCompanyPortletKeys.PROPERTY_SOURCE_DEFAULT);
		List<String> sourceList = Arrays.asList(sources.split(";"));

		renderRequest.setAttribute(CreateCompanyPortletKeys.COMPANY_BEAN_LIST,companyBeanList);
		renderRequest.setAttribute(CreateCompanyPortletKeys.CONFIGURATION_IMPORT_HELP_TEXT, importHelpText);
		renderRequest.setAttribute(CreateCompanyPortletKeys.COMPANY_SOURCE_LIST, sourceList);

		super.doView(renderRequest, renderResponse);
	}

	/*public void create(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

		PortletPreferences preferences = actionRequest.getPreferences();

		String companyName 	= ParamUtil.getString(actionRequest, CreateCompanyPortletKeys.COMPANY_NAME, "");
		String cif 			= ParamUtil.getString(actionRequest, CreateCompanyPortletKeys.COMPANY_CIF, "");
		String adminName 	= ParamUtil.getString(actionRequest, CreateCompanyPortletKeys.COMPANY_ADMIN_NAME, "");
		String adminSurname	= ParamUtil.getString(actionRequest, CreateCompanyPortletKeys.COMPANY_ADMIN_SURNAME, "");
		String adminNif 	= ParamUtil.getString(actionRequest, CreateCompanyPortletKeys.COMPANY_ADMIN_NIF, "");
		String adminEmail 	= ParamUtil.getString(actionRequest, CreateCompanyPortletKeys.COMPANY_ADMIN_EMAIL, "");
		String source 		= ParamUtil.getString(actionRequest, CreateCompanyPortletKeys.COMPANY_SOURCE, "");
		String endDate 	= ParamUtil.getString(actionRequest, CreateCompanyPortletKeys.COMPANY_END_DATE, "");
		String startDate 	= ParamUtil.getString(actionRequest, CreateCompanyPortletKeys.COMPANY_START_DATE, "");
		String implementationDate = ParamUtil.getString(actionRequest, CreateCompanyPortletKeys.COMPANY_IMPLANTATION_DATE, "");

		if(_log.isDebugEnabled()){
			_log.debug("companyName: " + companyName);
			_log.debug("cif: " + cif);
			_log.debug("adminName: " + adminName);
			_log.debug("adminSurname: " + adminSurname);
			_log.debug("adminNif: " + adminNif);
			_log.debug("adminEmail: " + adminEmail);
			_log.debug("startDate: " + startDate);
			_log.debug("endDate: " + endDate);
		}

		// Use the site template in created site.
		String templateId = preferences.getValue(CreateCompanyPortletKeys.CONFIGURATION_TEMPLATE_ID, "0");

		createNewSiteWithUserAndTemplate(companyName,themeDisplay.getCompanyId(), themeDisplay.getPortalURL(), themeDisplay.getPathMain(), themeDisplay.getUserId(), cif, adminName, adminSurname,
				adminNif, adminEmail, source, templateId, startDate, implementationDate, endDate, themeDisplay.getLocale().toString());

		actionResponse.setRenderParameter("mvcPath","/success.jsp");

	}*/

/*

	public void createNewSiteWithUserAndTemplate(String companyName, long companyId, String portalURL, String pathMain, long creatorUserId, String cif, String adminName,
												 String adminSurname, String adminNif, String adminEmail, String source, String templateId, String startDate, String implementationDate, String endDate, String languageId){

		Message message = new Message();

		//Set destination
		message.setDestinationName(CreateCompanyPortletKeys.MESSAGE_BUS_DESTINATION_NAME);

		//Add fields
		message.put(CreateCompanyPortletKeys.COMPANY_NAME, companyName);
		message.put(CreateCompanyPortletKeys.COMPANY_ID, companyId);
		message.put(CreateCompanyPortletKeys.PORTAL_URL, portalURL);
		message.put(CreateCompanyPortletKeys.PATH_MAIN, pathMain);
		message.put(CreateCompanyPortletKeys.COMPANY_CREATOR_USER_ID, creatorUserId);
		message.put(CreateCompanyPortletKeys.COMPANY_CIF, cif);
		message.put(CreateCompanyPortletKeys.COMPANY_ADMIN_NAME, adminName);
		message.put(CreateCompanyPortletKeys.COMPANY_ADMIN_SURNAME, adminSurname);
		message.put(CreateCompanyPortletKeys.COMPANY_ADMIN_NIF, adminNif);
		message.put(CreateCompanyPortletKeys.COMPANY_ADMIN_EMAIL, adminEmail);
		message.put(CreateCompanyPortletKeys.COMPANY_SOURCE, source);
		message.put(CreateCompanyPortletKeys.COMPANY_START_DATE, startDate);
		message.put(CreateCompanyPortletKeys.COMPANY_IMPLANTATION_DATE, implementationDate);
		message.put(CreateCompanyPortletKeys.COMPANY_END_DATE, endDate);
		message.put(CreateCompanyPortletKeys.CONFIGURATION_TEMPLATE_ID, templateId);
		message.put(CreateCompanyPortletKeys.LANGUAGE_ID, languageId);

		//Send message
		_messageBus.sendMessage(message.getDestinationName(), message);

		if(_log.isDebugEnabled()){
			_log.debug("message: " + message.getDestinationName());
		}

	}
*/

	public void importCompanies(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

		// Use the site template in created site.
		PortletPreferences preferences = actionRequest.getPreferences();
		String templateId = preferences.getValue(CreateCompanyPortletKeys.CONFIGURATION_TEMPLATE_ID, "0");

		UploadPortletRequest uploadRequest = PortalUtil.getUploadPortletRequest(actionRequest);

		String sourceFileName = uploadRequest.getFileName("csv");
		File file = (File) uploadRequest.getFile("csv");

		if(_log.isDebugEnabled()){
			_log.debug("Fichero CSV a importar: " + sourceFileName);
			_log.debug("  Tamaño del fichero: " + uploadRequest.getSize("csv"));
		}

		// Get all data in list.
		List<CSVData> csvDataList = PrevingCSVUtil.getCSVDataList(themeDisplay.getCompanyId(), themeDisplay.getUser(), sourceFileName, file);

		int pos = 0;

		actionRequest.setAttribute(CreateCompanyPortletKeys.COMPANY_CSV_COMPANIES, PrevingCSVUtil.getCSVWithFormat(themeDisplay.getCompanyId(), file));

		//Validate duplicated values.
		List<String> errors = PrevingCSVValidatorUtil.duplicatedValues(csvDataList);
		if(errors.size() > 0){

			PrevingMailUtil.sendMailErrorCSVDuplicatedValues(themeDisplay.getUser(), errors);

			SessionErrors.add(actionRequest, "import-process-error");
			actionResponse.setRenderParameter("mvcPath","/error_import.jsp");

			return;
		}

		Date now = new Date();

		SimpleDateFormat dateFormatLog = new SimpleDateFormat("yyyy-dd-MM HH:mm:ss.SSS");


		//Create all companies with the clean data.
		for (CSVData data : csvDataList) {

			Date startCreate = new Date();
			System.out.println(dateFormatLog.format(startCreate) + " START TO CREATE COMPANY " + data.getName() + ", CIF " + data.getCif());

			Group company = PrevingCreateCompanyUtil.createCompany(
					themeDisplay.getCompanyId(),
					data.getName(),
					themeDisplay.getUser(),
					data.getCif(),
					"",
					now,
					now,
					null,
					0,
					0);

			String passwordRandon = RandomStringUtils.random(8,true, true);

			User user = PrevingCreateCompanyUtil.createUser(
					themeDisplay.getCompanyId(),
					company.getGroupId(),
					data.getAdminName(),
					data.getAdminLastName(),
					data.getAdminNIF(),
					data.getAdminEmail(),
					passwordRandon,
					themeDisplay.getPortalURL(),
					themeDisplay.getPathMain(),
					themeDisplay.getLocale()
			);

			//Notificacion admin of Preving
			CompanyMailing.companyCreateToCreator(themeDisplay.getUser(), data.getName(), data.getCif());

			// Send success notification to user creator.
			String subject = LanguageUtil.get(ResourceBundle.getBundle("content/Language", themeDisplay.getUser().getLocale()), "previngmailing.mail.subject.new.company");
			String body = "CIF: " + data.getCif() + ", " +data.getName();

			//Notification
			NotificacionUtil.sendNotificacion(themeDisplay.getUserId(), themeDisplay.getUserId(), subject, body);

			//Keycloak create company
			PrevingKeycloakUtil.createCompanyKeycloak(company.getGroupId(), data.getName(), data.getCif(), "", "", null, "");

			//Keycloak create user
			PrevingKeycloakUtil.operationUser(true,
					company.getGroupId(), user.getUserId(), user.getEmailAddress(), data.getAdminName(), data.getAdminLastName(), data.getAdminNIF(), "", passwordRandon, null, true);


			Date endCreate = new Date();
			System.out.println(dateFormatLog.format(endCreate) + " END TO CREATE COMPANY " + data.getName() + ", time " + ((endCreate.getTime() - startCreate.getTime())/1000) + " seconds.");

			selectSiteTemplateToGroup(company.getGroupId(), Long.parseLong(templateId));

		}

		PrevingMailUtil.sendMailCSVCreateNewCompanies(themeDisplay.getUser(), file);

		SessionMessages.add(actionRequest, "import-process-success");
		actionResponse.setRenderParameter("mvcPath","/success_import.jsp");

	}

	public void edit(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

		PortletPreferences preferences = actionRequest.getPreferences();

		long companyConfId	= ParamUtil.getLong(actionRequest, CreateCompanyPortletKeys.COMPANY_CONF_ID, 0);
		long companyGroupId	= ParamUtil.getLong(actionRequest, CreateCompanyPortletKeys.COMPANY_CONF_GROUP_ID, 0);
		long adminUserId	= ParamUtil.getLong(actionRequest, CreateCompanyPortletKeys.COMPANY_ADMIN_USER_ID, 0);
		String companyName 	= ParamUtil.getString(actionRequest, CreateCompanyPortletKeys.COMPANY_NAME, "");
		String cif 			= ParamUtil.getString(actionRequest, CreateCompanyPortletKeys.COMPANY_CIF, "");
		String adminName 	= ParamUtil.getString(actionRequest, CreateCompanyPortletKeys.COMPANY_ADMIN_NAME, "");
		String adminSurname	= ParamUtil.getString(actionRequest, CreateCompanyPortletKeys.COMPANY_ADMIN_SURNAME, "");
		String adminNif 	= ParamUtil.getString(actionRequest, CreateCompanyPortletKeys.COMPANY_ADMIN_NIF, "");
		String adminEmail 	= ParamUtil.getString(actionRequest, CreateCompanyPortletKeys.COMPANY_ADMIN_EMAIL, "");
		String deleteDate 	= ParamUtil.getString(actionRequest, CreateCompanyPortletKeys.COMPANY_END_DATE, "");
		String startDate 	= ParamUtil.getString(actionRequest, CreateCompanyPortletKeys.COMPANY_START_DATE, "");
		String implementationDate = ParamUtil.getString(actionRequest, CreateCompanyPortletKeys.COMPANY_IMPLANTATION_DATE, "");
		String source 	= ParamUtil.getString(actionRequest, CreateCompanyPortletKeys.COMPANY_SOURCE, "");

		Date stDate = null;
		if(!Validator.isBlank(startDate)){
			stDate = dateFormat.parse(startDate);
		}

		Date impDate = null;
		if(!Validator.isBlank(implementationDate)){
			impDate = dateFormat.parse(implementationDate);
		}

		Date delDate = null;
		if(!Validator.isBlank(deleteDate)){
			delDate = dateFormat.parse(deleteDate);
		}

		if(_log.isDebugEnabled()){
			_log.debug("companyConfId: " + companyConfId);
			_log.debug("companyName: " + companyName);
			_log.debug("cif: " + cif);
			_log.debug("adminName: " + adminName);
			_log.debug("adminSurname: " + adminSurname);
			_log.debug("adminNif: " + adminNif);
			_log.debug("adminEmail: " + adminEmail);
			_log.debug("endDate: " + deleteDate);
		}

		CompanyConf companyConf = null;

		//Create companyconf if not exists.
		if(companyConfId == 0) {

			// Add the configuration of company.
			companyConf = PrevingCompanyUtil.createCompanyConfiguration(themeDisplay.getCompanyId(), companyGroupId, adminUserId, cif, source, stDate, impDate, delDate, 0,0);

			companyConfId = companyConf.getCompanyConfId();
		}


		if(companyConfId > 0) {

			companyConf = CompanyConfLocalServiceUtil.getCompanyConf(companyConfId);

			if(Validator.isNotNull(companyConf)) {

				Group group = GroupLocalServiceUtil.getGroup(companyConf.getGroupId());
				if(Validator.isNotNull(group)) {

					ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();
					serviceContext.setUserId(companyConf.getUserId());
					serviceContext.setScopeGroupId(companyConf.getGroupId());

					PrevingUserUtil.updateUser(companyConf.getCompanyId(), companyConf.getGroupId(), companyConf.getUserId(), adminName, adminSurname, adminNif, adminEmail, serviceContext);
					PrevingCompanyUtil.updateCompany(companyConfId, companyName, cif, stDate, impDate, delDate,  source, 0, 0);

					//Keycloak create company
					PrevingKeycloakUtil.createCompanyKeycloak(companyGroupId, companyName, cif, "", "", delDate, "");

					//Keycloak create user
					/*PrevingKeycloakUtil.operationUser(false,
							companyConf.getCompanyId(), companyConf.getUserId(), adminEmail, adminName, adminSurname, adminNif, "", null, null, true);*/

					_log.debug("companyGroupId: " + companyGroupId);

					PrevingKeycloakUtil.operationUser(false,
							companyGroupId, companyConf.getUserId(), adminEmail, adminName, adminSurname, adminNif, "", null, null, true);


				}

			}

		}

	}

	public void delete(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

		long companyConfId	= ParamUtil.getLong(actionRequest, CreateCompanyPortletKeys.COMPANY_CONF_ID, 0);

		CompanyConf companyConf = CompanyConfLocalServiceUtil.getCompanyConf(companyConfId);

		if(Validator.isNotNull(companyConf)) {
			List<Sign> signsByCompanyIdAndGroupId = SignLocalServiceUtil.getSignsByCompanyIdAndGroupId(companyConf.getCompanyId(), companyConf.getGroupId());
			if (signsByCompanyIdAndGroupId.isEmpty()) {
				PrevingCompanyUtil.deleteCompany(companyConfId, themeDisplay.getUserId());

				if(_log.isDebugEnabled()){
					_log.debug("Deleted company.");
				}

			}
		}

	}

	public void addUserRoleToAllCompanies(ActionRequest actionRequest, ActionResponse actionResponse){

		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

		List<Group> groups = GroupLocalServiceUtil.getGroups(themeDisplay.getCompanyId(), 0, true);

		for(Group group:groups){
			PrevingRoleUtil.addUserRoleToGroup(themeDisplay.getCompanyId(), group.getGroupId());
		}

	}

	public void addUserRoleToCompany(ActionRequest actionRequest, ActionResponse actionResponse){

		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

		long companyGroupId	= ParamUtil.getLong(actionRequest, CreateCompanyPortletKeys.COMPANY_CONF_GROUP_ID, 0);

		PrevingRoleUtil.addUserRoleToGroup(themeDisplay.getCompanyId(), companyGroupId);

	}

	public void selectSiteTemplateToGroup(long groupId, long templateId){

		Message message = new Message();

		//Set destination
		message.setDestinationName(CreateCompanyPortletKeys.MESSAGE_BUS_DESTINATION_NAME_SITE_TEMPLATE);

		//Add fields
		message.put(CreateCompanyPortletKeys.GROUP_ID, groupId);
		message.put(CreateCompanyPortletKeys.TEMPLATE_ID, templateId);

		//Send message
		_messageBus.sendMessage(message.getDestinationName(), message);

	}


	@Reference
	private MessageBus _messageBus;

}

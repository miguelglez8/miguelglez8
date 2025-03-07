package com.adeplus.liferay.portlet.contact.form.web.portlet;

import com.adeplus.liferay.portlet.commons.web.audit.AdeplusAuditUtil;
import com.adeplus.liferay.portlet.commons.web.constants.AdeplusAuditPortletKeys;
import com.adeplus.liferay.portlet.commons.web.user.AdeplusUserUtil;
import com.adeplus.liferay.portlet.contact.form.web.constants.AdeplusContactPortletKeys;
import com.adeplus.liferay.portlet.contact.form.web.util.AdeplusCategoryUtil;
import com.adeplus.liferay.portlet.mailing.web.mail.ContactFormMailing;
import com.adeplus.liferay.portlet.suite.manager.model.Application;
import com.adeplus.liferay.portlet.suite.manager.model.Comp;
import com.adeplus.liferay.portlet.suite.manager.model.UserCompany;
import com.adeplus.liferay.portlet.suite.manager.service.ApplicationLocalServiceUtil;
import com.adeplus.liferay.portlet.suite.manager.service.CompLocalServiceUtil;
import com.adeplus.liferay.portlet.suite.manager.service.UserCompanyLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.*;
import org.osgi.service.component.annotations.Component;

import javax.portlet.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @author agarciap
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.adeplus",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=false",
		"javax.portlet.display-name=AdeplusContact",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + AdeplusContactPortletKeys.ADEPLUSCONTACT,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user",
		"javax.portlet.supported-public-render-parameter=userCompId"
	},
	service = Portlet.class
)
public class AdeplusContactPortlet extends MVCPortlet {

	private static Log _log = LogFactoryUtil.getLog(AdeplusContactPortlet.class);

	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException {

		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);

		PortletPreferences preferences = renderRequest.getPreferences();

		//Get categories
		String categories = LocalizationUtil.getPreferencesValue(preferences, AdeplusContactPortletKeys.CATEGORIES_CONFIGURATION, themeDisplay.getUser().getLanguageId());

		List<String> categoriesList = Arrays.asList(categories.split(";"));

		List<String> categoriesListWithoutDuplicates = AdeplusCategoryUtil.getCategoryListWithoutDuplicates(categoriesList);
		renderRequest.setAttribute(AdeplusContactPortletKeys.CATEGORIES, categoriesListWithoutDuplicates);

		//Get applications
		List<Application> applicationList = ApplicationLocalServiceUtil.getApplications(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
		renderRequest.setAttribute(AdeplusContactPortletKeys.APPLICATIONS, applicationList);

		//Get list of admins in company.
		long userCompId = ParamUtil.getLong(renderRequest, AdeplusContactPortletKeys.USER_COMPANY_ID_EDIT, 0);
		UserCompany defaultCompany = UserCompanyLocalServiceUtil.getUserDefaultCompany(themeDisplay.getUserId());

		if(Validator.isNotNull(defaultCompany)){
			userCompId = defaultCompany.getCompId();
		}
		boolean userAdminInComp = UserCompanyLocalServiceUtil.isUserAdminInComp(themeDisplay.getUserId(), userCompId);
		if(!userAdminInComp) {

			List<UserCompany> adminUsersFromCompany = UserCompanyLocalServiceUtil.getAdminUsersFromCompany(userCompId);

			renderRequest.setAttribute(AdeplusContactPortletKeys.USER_ADMIN_LIST, adminUsersFromCompany);
		}

		super.doView(renderRequest, renderResponse);
	}

	public void send(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		UploadPortletRequest uploadRequest = PortalUtil.getUploadPortletRequest(actionRequest);

		long userCompId = ParamUtil.getLong(actionRequest, AdeplusContactPortletKeys.USER_COMPANY_ID_EDIT, 0);

		if(userCompId == 0){
			List<Comp> companiesActiveFromUser = AdeplusUserUtil.getCompaniesActiveFromUser(themeDisplay.getUserId());
			if(companiesActiveFromUser.size() > 0) {
				userCompId = companiesActiveFromUser.get(0).getCompId();
			}
		}

		PortletPreferences preferences = actionRequest.getPreferences();

		String categories = LocalizationUtil.getPreferencesValue(preferences, AdeplusContactPortletKeys.CATEGORIES_CONFIGURATION, themeDisplay.getUser().getLanguageId());
		List<String> categoriesList = Arrays.asList(categories.split(";"));

		List<String> categoriesListWithoutDuplicates = AdeplusCategoryUtil.getCategoryListWithoutDuplicates(categoriesList);

		int category 	= ParamUtil.getInteger(actionRequest, AdeplusContactPortletKeys.CATEGORY, -1);
		int applicationId 	= ParamUtil.getInteger(actionRequest, AdeplusContactPortletKeys.APPLICATION, -1);
		String subject 	= ParamUtil.getString(actionRequest, AdeplusContactPortletKeys.SUBJECT, "");
		String body		= ParamUtil.getString(actionRequest, AdeplusContactPortletKeys.DESCRIPTION, "");

		String sourceFileName = uploadRequest.getFileName("file");
		File file           = (File) uploadRequest.getFile("file");
		String selectedFile = ParamUtil.getString(actionRequest, AdeplusContactPortletKeys.SELECTED_FILE, "");

		String categoryName = category !=-1 ? categoriesListWithoutDuplicates.get(category): "";

		String applicationName = "-";
		Application application = ApplicationLocalServiceUtil.fetchApplication(applicationId);
		if(Validator.isNotNull(application)){
			applicationName = application.getName();
		}

		_log.info("category: " + category);
		_log.info("categoryName: " + categoryName);
		_log.info("applicationId: " + applicationId);
		_log.info("applicationName: " + applicationName);
		_log.info("subject: " + subject);
		_log.info("body: " + body);
		_log.info("sourceFileName: " + sourceFileName);
		
		if(!selectedFile.equals("false") && Validator.isNotNull(file)) {
			
			_log.info("file: " + file.getAbsolutePath());
			
		}

		User user = themeDisplay.getUser();

		String companyName = "";
		
		if(userCompId > 0){
			Comp comp = CompLocalServiceUtil.getComp(userCompId);
			if(Validator.isNotNull(comp)) {
				companyName = comp.getName();
			}
		}

		AdeplusAuditUtil.addAudit(user.getCompanyId(), user.getGroupId(), themeDisplay.getUserId(), AdeplusAuditPortletKeys.AUDIT_CONTACT_FORM, "Contact user "+ user.getEmailAddress() + " category " +  categoryName + ", application " + applicationId + ". Subject: " + subject);

		// Get the email address from configuration.
		String emails = preferences.getValue(AdeplusContactPortletKeys.TO_EMAIL_CONFIGURATION, "");
		List<String> emailList = Arrays.asList(emails.split(";"));

		List<String> emailListByCategory = AdeplusCategoryUtil.getStringPositionInList(categoriesList, emailList, categoryName);


		//Send mail to admins
		for(String mail:emailListByCategory){
			if(Validator.isEmailAddress(mail)) {

				ContactFormMailing.contactToAdmin(mail, themeDisplay.getUser(), companyName, subject, body, Validator.isBlank(sourceFileName) ? "-" : sourceFileName, categoryName, applicationName, file, sourceFileName);

			}
		}

		//Send mail to user
		ContactFormMailing.contactToUser(themeDisplay.getUser(), subject, body, Validator.isBlank(sourceFileName) ? "-" : sourceFileName, categoryName, applicationName, file, sourceFileName);

		if(_log.isDebugEnabled()){
			_log.debug("New contact form. User " + user.getEmailAddress() + " with subject " + subject);
		}

		actionResponse.setRenderParameter("mvcPath","/success.jsp");

	}

}
package com.preving.liferay.portlet.contact.form.web.portlet;


import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.*;
import com.preving.liferay.portlet.calendar.manager.model.CompanyConf;
import com.preving.liferay.portlet.calendar.manager.model.UserData;
import com.preving.liferay.portlet.calendar.manager.service.CompanyConfLocalServiceUtil;
import com.preving.liferay.portlet.calendar.manager.service.UserDataLocalServiceUtil;
import com.preving.liferay.portlet.contact.form.web.constants.ContactFormPortletKeys;
import com.preving.liferay.portlet.contact.form.web.util.PrevingCategoryUtil;
import com.preving.liferay.portlet.contact.form.web.util.PrevingNotificacionUtil;
import com.preving.liferay.portlet.contact.form.web.util.PrevingUserUtil;
import com.preving.liferay.portlet.mailing.web.mail.ContactMailing;
import com.preving.liferay.portlet.user.configuration.web.util.PrevingRoleUtil;
import org.osgi.service.component.annotations.Component;

import javax.portlet.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author agarciap
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.preving",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=false",
		"javax.portlet.display-name=ContactForm",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.init-param.config-template=/configuration.jsp",
		"javax.portlet.name=" + ContactFormPortletKeys.CONTACTFORM,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class ContactFormPortlet extends MVCPortlet {

	private static Log _log = LogFactoryUtil.getLog(ContactFormPortlet.class);

	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException {

		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);

		PortletPreferences preferences = renderRequest.getPreferences();

		String categories = LocalizationUtil.getPreferencesValue(preferences, ContactFormPortletKeys.CATEGORIES_CONFIGURATION, themeDisplay.getUser().getLanguageId());

		List<String> categoriesList = Arrays.asList(categories.split(";"));

		List<String> categoriesListWithoutDuplicates = PrevingCategoryUtil.getCategoryListWithoutDuplicates(categoriesList);

		List<User> list = PrevingUserUtil.getListAdminIdFromGroup(themeDisplay.getScopeGroupId());

		String administratorList = "<ul>";
		for(User user:list){
			UserData userData = PrevingUserUtil.getUserData(themeDisplay.getScopeGroupId(), user.getUserId());
			if(Validator.isNotNull(userData)){
				administratorList += "<li>" + userData.getName() + " " + userData.getLastName() + " - <a href=\"mailto:"+ userData.getEmail() +"\">" + userData.getEmail() + "</a></li>";
			}else{
				administratorList += "<li>" + user.getFirstName() + " " + user.getLastName() + " - <a href=\"mailto:"+ user.getEmailAddress() +"\">" + user.getEmailAddress() + "</a></li>";

			}
		}
		administratorList += "</ul>";

		renderRequest.setAttribute(ContactFormPortletKeys.CATEGORIES, categoriesListWithoutDuplicates);
		renderRequest.setAttribute(ContactFormPortletKeys.ADMINISTRATORS, administratorList);

		super.doView(renderRequest, renderResponse);
	}

	public void send(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		UploadPortletRequest uploadRequest = PortalUtil.getUploadPortletRequest(actionRequest);

		PortletPreferences preferences = actionRequest.getPreferences();

		String categories = LocalizationUtil.getPreferencesValue(preferences, ContactFormPortletKeys.CATEGORIES_CONFIGURATION, themeDisplay.getUser().getLanguageId());
		List<String> categoriesList = Arrays.asList(categories.split(";"));

		List<String> categoriesListWithoutDuplicates = PrevingCategoryUtil.getCategoryListWithoutDuplicates(categoriesList);

		int category 	= ParamUtil.getInteger(actionRequest, ContactFormPortletKeys.CATEGORY, -1);
		String subject 	= ParamUtil.getString(actionRequest, ContactFormPortletKeys.SUBJECT, "");
		String body		= ParamUtil.getString(actionRequest, ContactFormPortletKeys.DESCRIPTION, "");

		String sourceFileName = uploadRequest.getFileName("file");
		File file = (File) uploadRequest.getFile("file");

		String categoryName = category !=-1 ? categoriesListWithoutDuplicates.get(category): "";

		_log.debug("category: " + category);
		_log.debug("categoryName: " + categoryName);
		_log.debug("subject: " + subject);
		_log.debug("body: " + body);
		_log.debug("sourceFileName: " + sourceFileName);
		if(Validator.isNotNull(file)) {
			_log.debug("file: " + file.getAbsolutePath());
		}

		User user = themeDisplay.getUser();

		String companyName = themeDisplay.getScopeGroup().getName(themeDisplay.getLocale());

		// Get the email address from configuration.
		String emails = preferences.getValue(ContactFormPortletKeys.TO_EMAIL_CONFIGURATION, "");
		//List<String> emailList = Arrays.asList(emails.split(";"));
		//List<String> emailListByCategoryRes = new  java.util.ArrayList<>(Arrays.asList(emails.split(";")));
		//List<String> emailListByCategory = PrevingCategoryUtil.getStringPositionInList(categoriesList, emailList, categoryName);
		List<String> emailListByCategory = new ArrayList<String>();
		CompanyConf companyConf = CompanyConfLocalServiceUtil.getCompanyConfByCompanyIdAndGroupId (themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId());
		if(Validator.isNotNull(companyConf)){
			if(!companyConf.getSoporteEmail().isEmpty()){
				emailListByCategory = Arrays.asList(companyConf.getSoporteEmail().split(";"));
			}else{
				emailListByCategory = PrevingRoleUtil.getAdminsByCompany(themeDisplay.getScopeGroupId());
			}
		}

		/*emailListByCategory.addAll(emailListByCategoryRes);
		//Dejamos los cfg en el portlet
		List<String> portletCfg =  PrevingCategoryUtil.getStringPositionInList(categoriesList, emailList, categoryName);
		if(Validator.isNotNull(portletCfg) && portletCfg.size() > 0){
			emailListByCategory.add((String) portletCfg.get(0).toString());
		}*/


		//Send mail to admins
		for(String mail:emailListByCategory){
			if(Validator.isEmailAddress(mail)) {
				//Send mail
				ContactMailing.contactToAdmin(mail, themeDisplay.getUser(), companyName, subject, body, Validator.isBlank(sourceFileName) ? "-" : sourceFileName, categoryName, file, sourceFileName);

				/*try {
					User userByEmailAddress = UserLocalServiceUtil.getUserByEmailAddress(themeDisplay.getCompanyId(), mail);
					if(Validator.isNotNull(userByEmailAddress)) {
						PrevingNotificacionUtil.sendNotificacion(userByEmailAddress.getUserId(), userByEmailAddress.getUserId(), subject, body);
					}

				} catch (PortalException e) {
					_log.error("No existe el usuario con el email "+ mail + " en el portal.");
				}*/
			}
		}

		//Send mail to user
		ContactMailing.contactToUser(themeDisplay.getUser(), subject, body, categoryName);

		//Send notification
		PrevingNotificacionUtil.sendNotificacion(user.getUserId(), themeDisplay.getScopeGroup().getCreatorUserId(), subject, body);

		if(_log.isDebugEnabled()){
			_log.debug("New contact form. User " + user.getEmailAddress() + " with subject " + subject + " message: " + body);
		}

		actionResponse.setRenderParameter("mvcPath","/success.jsp");

	}


}
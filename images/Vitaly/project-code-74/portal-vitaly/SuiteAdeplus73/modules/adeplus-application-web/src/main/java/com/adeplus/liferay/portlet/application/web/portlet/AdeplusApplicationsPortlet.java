package com.adeplus.liferay.portlet.application.web.portlet;

import com.adeplus.liferay.portlet.application.web.constants.AdeplusApplicationsPortletKeys;
import com.adeplus.liferay.portlet.application.web.util.AdeplusApplicationUtil;
import com.adeplus.liferay.portlet.suite.manager.model.Application;
import com.adeplus.liferay.portlet.suite.manager.model.Role;
import com.adeplus.liferay.portlet.suite.manager.service.ApplicationLocalServiceUtil;
import com.adeplus.liferay.portlet.suite.manager.service.RoleLocalServiceUtil;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.*;
import java.io.File;
import java.io.IOException;
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
		"javax.portlet.display-name=AdeplusApplications",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + AdeplusApplicationsPortletKeys.ADEPLUSAPPLICATIONS,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class AdeplusApplicationsPortlet extends MVCPortlet {

	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException {

		List<Application> appList = ApplicationLocalServiceUtil.getApplications(QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		renderRequest.setAttribute(AdeplusApplicationsPortletKeys.APPLICATIONS_LIST, appList);

		super.doView(renderRequest, renderResponse);
	}

	public void saveApplication(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

		UploadPortletRequest uploadRequest = PortalUtil.getUploadPortletRequest(actionRequest);

		long applicationId 		= ParamUtil.getLong(actionRequest, AdeplusApplicationsPortletKeys.APPLICATION_ID_EDIT, 0);
		String name 			= ParamUtil.getString(actionRequest, AdeplusApplicationsPortletKeys.APPLICATION_NAME, "");
		String url 				= ParamUtil.getString(actionRequest, AdeplusApplicationsPortletKeys.APPLICATION_URL, "");
		String description 		= ParamUtil.getString(actionRequest, AdeplusApplicationsPortletKeys.APPLICATION_DESCRIPTION, "");
		String configuration 	= ParamUtil.getString(actionRequest, AdeplusApplicationsPortletKeys.APPLICATION_CONFIGURATION, "");
		String sourceFileName 	= uploadRequest.getFileName(AdeplusApplicationsPortletKeys.APPLICATION_LOGO);
		File logo 				= (File) uploadRequest.getFile(AdeplusApplicationsPortletKeys.APPLICATION_LOGO);
		String roles 			= ParamUtil.getString(actionRequest, AdeplusApplicationsPortletKeys.APPLICATION_ROLES, "");
		String alias 			= ParamUtil.getString(actionRequest, AdeplusApplicationsPortletKeys.APPLICATION_ALIAS, "");
		String client 			= ParamUtil.getString(actionRequest, AdeplusApplicationsPortletKeys.APPLICATION_KEYCLOAK_CLIENT, "");
		String secret 			= ParamUtil.getString(actionRequest, AdeplusApplicationsPortletKeys.APPLICATION_KEYCLOAK_SECRET, "");

		String limitAdms 		= ParamUtil.getString(actionRequest, AdeplusApplicationsPortletKeys.LIMIT_ADMS, "");
		String limitUsers 		= ParamUtil.getString(actionRequest, AdeplusApplicationsPortletKeys.LIMIT_USERS, "");

		String limitNumAdms 	= ParamUtil.getString(actionRequest, AdeplusApplicationsPortletKeys.LIMIT_NUM_ADMS, "");
		if(limitNumAdms.isEmpty()) limitNumAdms = "0";
		String limitNumUsers	= ParamUtil.getString(actionRequest, AdeplusApplicationsPortletKeys.LIMIT_NUM_USERS, "");
		if(limitNumUsers.isEmpty()) limitNumUsers = "0";

		ServiceContext serviceContext = ServiceContextFactory.getInstance(DLFileEntry.class.getName(), actionRequest);

		if(applicationId == 0){
			//Create new application
			AdeplusApplicationUtil.createApplication(
					themeDisplay.getScopeGroupId(),
					themeDisplay.getUserId(),
					name,
					url,
					description,
					configuration,
					roles,
					alias,
					client,
					secret,
					AdeplusApplicationsPortletKeys.PARENT_FOLDER_NAME,
					name,
					sourceFileName,
					logo,
					serviceContext);
		}else{
			//Update application
			AdeplusApplicationUtil.updateApplication(
					themeDisplay.getScopeGroupId(),
					themeDisplay.getUserId(),
					applicationId,
					name,
					url,
					description,
					configuration,
					roles,
					alias,
					client,
					secret,
					Boolean.parseBoolean(limitAdms),
					Long.parseLong(limitNumAdms),
					Boolean.parseBoolean(limitUsers),
					Long.parseLong(limitNumUsers),
					AdeplusApplicationsPortletKeys.PARENT_FOLDER_NAME,
					name,
					sourceFileName,
					logo,
					serviceContext);
		}

	}

	public void deleteApplication(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {

		long applicationId = ParamUtil.getLong(actionRequest, AdeplusApplicationsPortletKeys.APPLICATION_ID, 0);

		AdeplusApplicationUtil.deleteApplication(applicationId);

	}

	public void deleteRole(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {

		long roleEditId = ParamUtil.getLong(actionRequest, AdeplusApplicationsPortletKeys.ROLE, 0);
		long applicationId = ParamUtil.getLong(actionRequest, AdeplusApplicationsPortletKeys.APPLICATION_ID_EDIT, 0);

		Role role = RoleLocalServiceUtil.fetchRole(roleEditId);

		if(Validator.isNotNull(role)) {
			RoleLocalServiceUtil.deleteRole(role);
		}

		actionResponse.setRenderParameter(AdeplusApplicationsPortletKeys.APPLICATION_ID_EDIT, String.valueOf(applicationId));
		actionResponse.setRenderParameter("jspPage", "/application.jsp");
	}

	public void cancel(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {

		List<Application> appList = ApplicationLocalServiceUtil.getApplications(QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		actionRequest.setAttribute(AdeplusApplicationsPortletKeys.APPLICATIONS_LIST, appList);

	}

}
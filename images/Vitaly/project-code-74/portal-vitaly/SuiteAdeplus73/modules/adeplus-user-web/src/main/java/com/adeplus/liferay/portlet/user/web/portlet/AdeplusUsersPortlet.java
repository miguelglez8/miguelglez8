package com.adeplus.liferay.portlet.user.web.portlet;

import com.adeplus.liferay.portlet.commons.web.user.AdeplusUserUtil;
import com.adeplus.liferay.portlet.suite.manager.model.UserCompany;
//import com.adeplus.liferay.portlet.suite.manager.service.SuiteConfigLocalServiceUtil;
import com.adeplus.liferay.portlet.suite.manager.service.UserCompanyLocalServiceUtil;
import com.adeplus.liferay.portlet.user.web.constants.AdeplusUsersPortletKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
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
		"javax.portlet.display-name=AdeplusUsers",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + AdeplusUsersPortletKeys.ADEPLUSUSERS,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user",
		"javax.portlet.supported-public-render-parameter=userCompId"
	},
	service = Portlet.class
)
public class AdeplusUsersPortlet extends MVCPortlet {

	private static Log _log = LogFactoryUtil.getLog(AdeplusUsersPortlet.class);

	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException {

		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);

		long userCompId = ParamUtil.getLong(renderRequest, AdeplusUsersPortletKeys.USER_COMPANY_ID_EDIT, 0);
		long userClientId=0;

		UserCompany defaultCompany = UserCompanyLocalServiceUtil.getUserDefaultCompany(themeDisplay.getUserId());

		if(Validator.isNotNull(defaultCompany)){
			userCompId = defaultCompany.getCompId();
			//userClientId=defaultCompany.getDefaultClientId();

		}

		List<User> userList = AdeplusUserUtil.getUsersFromCompany(userCompId);

		UserCompany userCompany = UserCompanyLocalServiceUtil.getUserCompany(themeDisplay.getUserId(), userCompId);



		renderRequest.setAttribute(AdeplusUsersPortletKeys.USER_LIST, userList);
		renderRequest.setAttribute(AdeplusUsersPortletKeys.USER_COMPANY_ID_EDIT, userCompId);
		renderRequest.setAttribute(AdeplusUsersPortletKeys.USER_CLIENT_ID_EDIT, userClientId);
		renderRequest.setAttribute(AdeplusUsersPortletKeys.USER_COMPANY, userCompany);


		super.doView(renderRequest, renderResponse);
	}

	public void search(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

		long userCompId = ParamUtil.getLong(actionRequest, AdeplusUsersPortletKeys.USER_COMPANY_ID_EDIT, 0);
		long userClientId=0;
		UserCompany defaultCompany = UserCompanyLocalServiceUtil.getUserDefaultCompany(themeDisplay.getUserId());
		if(Validator.isNotNull(defaultCompany)){
			userCompId = defaultCompany.getCompId();
		}

		List<User> userList = AdeplusUserUtil.getUsersFromCompany(userCompId);

		UserCompany userCompany = UserCompanyLocalServiceUtil.getUserCompany(themeDisplay.getUserId(), userCompId);

		actionRequest.setAttribute(AdeplusUsersPortletKeys.USER_LIST, userList);
		actionRequest.setAttribute(AdeplusUsersPortletKeys.USER_COMPANY_ID_EDIT, userCompId);
		actionRequest.setAttribute(AdeplusUsersPortletKeys.USER_CLIENT_ID_EDIT, userClientId);
		actionRequest.setAttribute(AdeplusUsersPortletKeys.USER_COMPANY, userCompany);
	}


}
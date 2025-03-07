package com.adeplus.liferay.portlet.mailing.web.portlet;

import com.adeplus.liferay.portlet.mailing.web.constants.AdeplusMailingPortletKeys;
import com.adeplus.liferay.portlet.mailing.web.mail.UserMailing;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;

/**
 * @author agarciap
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.adeplus",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=false",
		"javax.portlet.display-name=AdeplusMailing",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + AdeplusMailingPortletKeys.ADEPLUSMAILING,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class AdeplusMailingPortlet extends MVCPortlet {
	private static Log _log = LogFactoryUtil.getLog(AdeplusMailingPortlet.class);

	public void createUser(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		UserMailing.userCreate(themeDisplay.getUser(), 1,"********", false);
	}
	public void deleteUser(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		UserMailing.userDelete(themeDisplay.getUser());
	}
	/*public void remindPassword(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		UserMailing.userRemindPassword(themeDisplay.getUser(), 1);
	}*/
	public void remindPassword(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		UserMailing.userChangePass(ParamUtil.getString(actionRequest, "ticketKey"), themeDisplay);
		_log.info("AdeplusMailingPortlet.remindPassword");
		
	}
}
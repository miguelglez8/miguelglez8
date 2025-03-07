package com.preving.liferay.portlet.notifications.web.portlet;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.*;
import com.preving.liferay.portlet.calendar.manager.model.UserData;
import com.preving.liferay.portlet.calendar.manager.service.UserDataLocalServiceUtil;
import com.preving.liferay.portlet.notifications.web.constants.NotificationsPortletKeys;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

/**
 * @author agarciap
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.preving",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=false",
		"javax.portlet.display-name=Notifications",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + NotificationsPortletKeys.NOTIFICATIONS,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class NotificationsPortlet extends MVCPortlet {

	private static Log _log = LogFactoryUtil.getLog(NotificationsPortlet.class);

	public void save(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		String notificationEndDate = ParamUtil.getString(actionRequest, NotificationsPortletKeys.NOTIFICATION_END_DATE, "");

		Date notificationDate = null;
		if(!Validator.isBlank(notificationEndDate)){
			try {
				notificationDate = dateFormat.parse(notificationEndDate);
			} catch (ParseException e) {
				_log.error(e);
			}
		}

		UserData userData = UserDataLocalServiceUtil.getUserDataByGroupIdAndUserId(themeDisplay.getScopeGroupId(), themeDisplay.getUserId());

		if (Validator.isNotNull(userData)) {

			userData.setNotificationEndDate(notificationDate);

			UserDataLocalServiceUtil.updateUserData(userData);
		}
		else{
			_log.error("The user " + themeDisplay.getUser().getEmailAddress() + " haven't userData values in this company " + themeDisplay.getScopeGroupId());
		}


	}

}
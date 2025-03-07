package com.preving.liferay.portlet.user.custom.calendar.portlet;

import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.preving.liferay.portlet.calendar.manager.model.Activity;
import com.preving.liferay.portlet.calendar.manager.model.Sign;
import com.preving.liferay.portlet.calendar.manager.service.ActivityLocalServiceUtil;
import com.preving.liferay.portlet.calendar.manager.service.SignLocalServiceUtil;
import com.preving.liferay.portlet.user.custom.calendar.constants.PrevingUserCustomCalendarPortletKeys;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author rrodriguezn
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=PrevingUserCustomCalendar",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + PrevingUserCustomCalendarPortletKeys.PREVINGUSERCUSTOMCALENDAR,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class PrevingUserCustomCalendarPortlet extends MVCPortlet {

	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException {

		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);




		List<Activity> activityList = ActivityLocalServiceUtil.getActivitiesFromGroup(themeDisplay.getCompanyId(),themeDisplay.getScopeGroupId());
		List<Activity> activityListRes = new ArrayList<Activity>();
		for(Activity activity : activityList){
			if(activity.getType() != 3){ //quitar las horas extras
				activityListRes.add(activity);
			}

		}

		renderRequest.setAttribute(PrevingUserCustomCalendarPortletKeys.LIST_ACTIVITIES, activityListRes);

		super.doView(renderRequest, renderResponse);
	}
}
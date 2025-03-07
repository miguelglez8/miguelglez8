package com.preving.liferay.portlet.timesheet.web.portlet;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.preving.liferay.portlet.calendar.manager.model.Activity;
import com.preving.liferay.portlet.calendar.manager.model.Sign;
import com.preving.liferay.portlet.calendar.manager.service.ActivityLocalServiceUtil;
import com.preving.liferay.portlet.calendar.manager.service.SignLocalServiceUtil;
import com.preving.liferay.portlet.timesheet.web.constants.TimesheetPortletKeys;
import com.preving.liferay.portlet.timesheet.web.util.TimesheetUtil;
import org.osgi.service.component.annotations.Component;

import javax.portlet.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author agarciap
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.preving",
		"com.liferay.portlet.header-portlet-css=/css/jquery.calendar.css",
		"com.liferay.portlet.header-portlet-javascript=/js/jquery.calendar.js",
		"com.liferay.portlet.header-portlet-javascript=/js/calendarioPicker.js",
		"com.liferay.portlet.instanceable=false",
		"javax.portlet.display-name=Timesheet",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + TimesheetPortletKeys.TIMESHEET,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class TimesheetPortlet extends MVCPortlet {

	private static Log _log = LogFactoryUtil.getLog(TimesheetPortlet.class);

	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException {

		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
		Date dateCalendar 	= ParamUtil.getDate(renderRequest, TimesheetPortletKeys.TIMESHEET_CALENDAR_DATE, dateFormat);

		Date date = new Date();
		if(Validator.isNotNull(dateCalendar)){
			date = dateCalendar;
		}

		List<Sign> signs = SignLocalServiceUtil.getSignsByCompanyIdAndGroupIdAndUserIdAndDate(themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId(), themeDisplay.getUserId(), date);
		List<Activity> activityList = ActivityLocalServiceUtil.getActivitiesFromGroup(themeDisplay.getCompanyId(),themeDisplay.getScopeGroupId());

/*		activityList = activityList.stream()
				.sorted(Comparator.comparing(Activity::getName))
				.collect(Collectors.toList());*/

		activityList = activityList.stream().sorted((o1, o2)->o1.getName(themeDisplay.getLocale())
				.compareTo(o2.getName(themeDisplay.getLocale())))
				.collect(Collectors.toList());

		renderRequest.setAttribute(TimesheetPortletKeys.TIMESHEET_SIGN_LIST, signs);
		renderRequest.setAttribute(TimesheetPortletKeys.TIMESHEET_ACTIVITY_LIST, activityList);

		super.doView(renderRequest, renderResponse);
	}

	public void create(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String dateStr =  String.valueOf(dateFormat.format(new Date()));

		Date startDate 	= ParamUtil.getDate(actionRequest, TimesheetPortletKeys.TIMESHEET_START_DATE, dateFormat);
		String date 	= ParamUtil.getString(actionRequest, TimesheetPortletKeys.TIMESHEET_START_DATE, "");

		int startHour 	= ParamUtil.getInteger(actionRequest, TimesheetPortletKeys.TIMESHEET_START_HOUR, 0);
		int startMin 	= ParamUtil.getInteger(actionRequest, TimesheetPortletKeys.TIMESHEET_START_MIN, 0);
		int finishHour 	= ParamUtil.getInteger(actionRequest, TimesheetPortletKeys.TIMESHEET_FINISH_HOUR, 0);
		int finishMin 	= ParamUtil.getInteger(actionRequest, TimesheetPortletKeys.TIMESHEET_FINISH_MIN, 0);
		String observations = ParamUtil.getString(actionRequest, TimesheetPortletKeys.TIMESHEET_OBSERVATIONS, "");

		long activityId = ParamUtil.getLong(actionRequest, TimesheetPortletKeys.TIMESHEET_ACTIVITY_ID, 0);

		Calendar calendarStart = Calendar.getInstance();
		calendarStart.setTime(startDate);
		calendarStart.set(Calendar.HOUR_OF_DAY, startHour);
		calendarStart.set(Calendar.MINUTE, startMin);

		Calendar calendarEnd = Calendar.getInstance();
		calendarEnd.setTime(startDate);
		calendarEnd.set(Calendar.HOUR_OF_DAY, finishHour);
		calendarEnd.set(Calendar.MINUTE, finishMin);

		// Add to db.
		TimesheetUtil.addSign(themeDisplay.getCompanyId(),
				themeDisplay.getScopeGroupId(),
				themeDisplay.getUserId(),
				calendarStart.getTime(),
				calendarEnd.getTime(),
				activityId,
				observations);


		List<Sign> signs = SignLocalServiceUtil.getSignsByCompanyIdAndGroupIdAndUserIdAndDate(themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId(), themeDisplay.getUserId(), startDate);
		List<Activity> activityList = ActivityLocalServiceUtil.getActivitiesFromGroup(themeDisplay.getCompanyId(),themeDisplay.getScopeGroupId());

		actionRequest.setAttribute(TimesheetPortletKeys.TIMESHEET_SIGN_LIST, signs);
		actionRequest.setAttribute(TimesheetPortletKeys.TIMESHEET_SIGN_LIST, signs);

	}

}
package com.preving.liferay.portlet.timesheet.report.web.portlet;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.preving.liferay.portlet.calendar.manager.service.UserDataLocalServiceUtil;
import com.preving.liferay.portlet.timesheet.report.web.bean.PrevingSign;
import com.preving.liferay.portlet.timesheet.report.web.constants.TimesheetReportPortletKeys;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.*;

import com.preving.liferay.portlet.timesheet.report.web.util.PrevingSignUtil;
import org.osgi.service.component.annotations.Component;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
		"javax.portlet.display-name=TimesheetReport",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + TimesheetReportPortletKeys.TIMESHEETREPORT,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class TimesheetReportPortlet extends MVCPortlet {

	private static Log _log = LogFactoryUtil.getLog(TimesheetReportPortlet.class);

	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException {

		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);

		//super.doView(renderRequest, renderResponse);



		String startDate= ParamUtil.getString(renderRequest, TimesheetReportPortletKeys.START_DATE, "");
		String endDate 	= ParamUtil.getString(renderRequest, TimesheetReportPortletKeys.END_DATE, "");
		String jobtitle = ParamUtil.getString(renderRequest, TimesheetReportPortletKeys.JOBTITLE, "");

		String workCenter 	= ParamUtil.getString(renderRequest, TimesheetReportPortletKeys.WORK_CENTER, "0");

		List<Long> arrWorkCentersId = new ArrayList<Long>();
		if(!workCenter.equals("0")){
			String[] arr = workCenter.split(",");
			for(String c : arr){
				arrWorkCentersId.add(Long.valueOf(c));
			}
		}else{
			arrWorkCentersId.add(0L);
		}
		_log.info("PARAM workCenter: " + workCenter);

		Date stDate = null;
		Date enDate = null;

		try {
			if(!Validator.isBlank(startDate)){
				stDate = dateFormat.parse(startDate);
			}else{
				Calendar calStart = Calendar.getInstance();
				calStart.set(Calendar.DAY_OF_MONTH, 1);
				stDate = calStart.getTime();
			}

			if(!Validator.isBlank(endDate)){
				enDate = dateFormat.parse(endDate);
			}else{
				Calendar calEnd = Calendar.getInstance();
				enDate = calEnd.getTime();
			}
		} catch (ParseException e) {
			_log.error(e);
		}

		List<PrevingSign> signsBetwenDates = PrevingSignUtil.getSignsBetwenDates(themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId(), jobtitle, stDate, enDate, arrWorkCentersId);

		List<String> jobTitlesFromCompany = UserDataLocalServiceUtil.getJobTitlesFromCompany(themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId());

		jobTitlesFromCompany.sort(String.CASE_INSENSITIVE_ORDER);

		renderRequest.setAttribute(TimesheetReportPortletKeys.SIGN_LIST, signsBetwenDates);
		renderRequest.setAttribute(TimesheetReportPortletKeys.JOBTITLE_LIST, jobTitlesFromCompany);
		renderRequest.setAttribute(TimesheetReportPortletKeys.START_DATE, dateFormat.format(stDate));
		renderRequest.setAttribute(TimesheetReportPortletKeys.END_DATE, dateFormat.format(enDate));

		super.doView(renderRequest, renderResponse);


	}

	public void search(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

		String startDate= ParamUtil.getString(actionRequest, TimesheetReportPortletKeys.START_DATE, "");
		String endDate 	= ParamUtil.getString(actionRequest, TimesheetReportPortletKeys.END_DATE, "");

		String workCenter 	= ParamUtil.getString(actionRequest, TimesheetReportPortletKeys.WORK_CENTER, "0");

		actionRequest.setAttribute(TimesheetReportPortletKeys.START_DATE, startDate);
		actionRequest.setAttribute(TimesheetReportPortletKeys.END_DATE, endDate);

	}
}
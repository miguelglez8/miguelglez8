package com.preving.liferay.portlet.sign.register.report.web.portlet;

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
import com.preving.liferay.portlet.sign.register.report.web.bean.PrevingSign;
import com.preving.liferay.portlet.sign.register.report.web.constants.SignRegisterReportPortletKeys;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.*;

import com.preving.liferay.portlet.sign.register.report.web.util.PrevingSignUtil;
import org.osgi.service.component.annotations.Component;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
		"javax.portlet.display-name=SignRegisterReport",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.init-param.add-process-action-success-action=false",
		"javax.portlet.name=" + SignRegisterReportPortletKeys.SIGNREGISTERREPORT,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class SignRegisterReportPortlet extends MVCPortlet {

	private static Log _log = LogFactoryUtil.getLog(SignRegisterReportPortlet.class);

	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException {

		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);

		String startDate= ParamUtil.getString(renderRequest, SignRegisterReportPortletKeys.START_DATE, "");
		String endDate 	= ParamUtil.getString(renderRequest, SignRegisterReportPortletKeys.END_DATE, "");
		long userId 	= ParamUtil.getLong(renderRequest, SignRegisterReportPortletKeys.USERID, 0);
		String jobtitle = ParamUtil.getString(renderRequest, SignRegisterReportPortletKeys.JOBTITLE, "");

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

		List<PrevingSign> signList = PrevingSignUtil.getSignsBetwenDates(themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId(), userId, jobtitle, stDate, enDate);

		List<String> jobTitlesFromCompany = UserDataLocalServiceUtil.getJobTitlesFromCompany(themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId());

		jobTitlesFromCompany.sort(String.CASE_INSENSITIVE_ORDER);

		List<User> groupUsers = UserLocalServiceUtil.getGroupUsers(themeDisplay.getScopeGroupId());

		renderRequest.setAttribute(SignRegisterReportPortletKeys.SIGN_LIST, signList);
		renderRequest.setAttribute(SignRegisterReportPortletKeys.USER_LIST, groupUsers);
		renderRequest.setAttribute(SignRegisterReportPortletKeys.JOBTITLE_SELECTED, jobtitle);
		renderRequest.setAttribute(SignRegisterReportPortletKeys.JOBTITLE_LIST, jobTitlesFromCompany);
		renderRequest.setAttribute(SignRegisterReportPortletKeys.START_DATE, dateFormat.format(stDate));
		renderRequest.setAttribute(SignRegisterReportPortletKeys.END_DATE, dateFormat.format(enDate));

		super.doView(renderRequest, renderResponse);
	}

	public void search(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

		String startDate= ParamUtil.getString(actionRequest, SignRegisterReportPortletKeys.START_DATE, "");
		String endDate 	= ParamUtil.getString(actionRequest, SignRegisterReportPortletKeys.END_DATE, "");
		long userId 	= ParamUtil.getLong(actionRequest, SignRegisterReportPortletKeys.USERID, 0);
		String jobtitle = ParamUtil.getString(actionRequest, SignRegisterReportPortletKeys.JOBTITLE, "");

		actionRequest.setAttribute(SignRegisterReportPortletKeys.START_DATE, startDate);
		actionRequest.setAttribute(SignRegisterReportPortletKeys.END_DATE, endDate);

	}

}
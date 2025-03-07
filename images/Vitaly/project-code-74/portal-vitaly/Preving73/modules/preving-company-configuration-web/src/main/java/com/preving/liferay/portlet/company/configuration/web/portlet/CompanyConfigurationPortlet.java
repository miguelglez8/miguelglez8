package com.preving.liferay.portlet.company.configuration.web.portlet;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.*;
import com.preving.liferay.portlet.company.configuration.web.constants.CompanyConfigurationPortletKeys;
import com.preving.liferay.portlet.company.configuration.web.util.CompanyConfigurationUtil;

import org.osgi.service.component.annotations.Component;

/**
 * @author agarciap
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.preving",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=false",
		"javax.portlet.display-name=CompanyConfiguration",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.init-param.add-process-action-success-action=false",
		"javax.portlet.name=" + CompanyConfigurationPortletKeys.COMPANYCONFIGURATION,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class CompanyConfigurationPortlet extends MVCPortlet {


	private static Log _log = LogFactoryUtil.getLog(CompanyConfigurationPortlet.class);

	public void save(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);


		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		UploadPortletRequest uploadRequest = PortalUtil.getUploadPortletRequest(actionRequest);

		String sourceFileName = uploadRequest.getFileName(CompanyConfigurationPortletKeys.COMPANY_CONF_LOGO);
		File logo = (File) uploadRequest.getFile(CompanyConfigurationPortletKeys.COMPANY_CONF_LOGO);

		long companyConfId 	= ParamUtil.getLong(actionRequest, CompanyConfigurationPortletKeys.COMPANY_CONF_ID, 0);

		String notWorkable 	= ParamUtil.getString(actionRequest, CompanyConfigurationPortletKeys.COMPANY_CONF_NOTIFICATION_NOT_WORKABLE, "");
		String finishDate 	= ParamUtil.getString(actionRequest, CompanyConfigurationPortletKeys.COMPANY_CONF_NOTIFICATION_FINISH_DATE, "");
		String periodicity 	= ParamUtil.getString(actionRequest, CompanyConfigurationPortletKeys.COMPANY_CONF_NOTIFICATION_PERIODICITY, "");

		String soporteEmails 	= ParamUtil.getString(actionRequest, CompanyConfigurationPortletKeys.COMPANY_CONF_SOPORTE_EMAILS, "");

		String maxHoursDay = ParamUtil.getString(actionRequest, CompanyConfigurationPortletKeys.COMPANY_CONF_MAX_HOURS_DAYS, "");
		String maxHoursWeek = ParamUtil.getString(actionRequest, CompanyConfigurationPortletKeys.COMPANY_CONF_MAX_HOURS_WEEK, "");
		String savePastDays = ParamUtil.getString(actionRequest, CompanyConfigurationPortletKeys.COMPANY_CONF_SAVE_PAST_DAYS, "");
		String editSigns = ParamUtil.getString(actionRequest, CompanyConfigurationPortletKeys.COMPANY_CONF_EDIT_SIGNS, "");
		String editDeleteSigns = ParamUtil.getString(actionRequest, CompanyConfigurationPortletKeys.COMPANY_CONF_EDIT_DELETE_SIGNS, "");
		float maxHoursDayValue = ParamUtil.getFloat(actionRequest, CompanyConfigurationPortletKeys.COMPANY_CONF_MAX_HOURS_DAYS_VALUE, 0);
		float maxHoursWeekValue = ParamUtil.getFloat(actionRequest, CompanyConfigurationPortletKeys.COMPANY_CONF_MAX_HOURS_WEEK_VALUE, 0);
		int savePastDaysValue = ParamUtil.getInteger(actionRequest, CompanyConfigurationPortletKeys.COMPANY_CONF_SAVE_PAST_DAYS_VALUE, 0);
		int editSignsValue = ParamUtil.getInteger(actionRequest, CompanyConfigurationPortletKeys.COMPANY_CONF_EDIT_SIGNS_VALUE, 0);
		int editDeleteSignsValue = ParamUtil.getInteger(actionRequest, CompanyConfigurationPortletKeys.COMPANY_CONF_EDIT_DELETE_SIGNS_VALUE, 0);

		String isWorkCenters = ParamUtil.getString(actionRequest, CompanyConfigurationPortletKeys.COMPANY_CONF_IS_WORK_CENTERS, "");
		String isOwnCalendars = ParamUtil.getString(actionRequest, CompanyConfigurationPortletKeys.COMPANY_CONF_IS_OWN_CALENDARS, "");

		String startDate = ParamUtil.getString(actionRequest, CompanyConfigurationPortletKeys.COMPANY_CONF_START_DATE, "");
		String implantationDate = ParamUtil.getString(actionRequest, CompanyConfigurationPortletKeys.COMPANY_CONF_IMPLANTATION_DATE, "");

		String description = ParamUtil.getString(actionRequest, CompanyConfigurationPortletKeys.COMPANY_CONF_DESCRIPTION, "");
		Map<Locale, String> descriptionLocalized = LocalizationUtil.getLocalizationMap(actionRequest, CompanyConfigurationPortletKeys.COMPANY_CONF_DESCRIPTION);

		String agreement = ParamUtil.getString(actionRequest, CompanyConfigurationPortletKeys.COMPANY_CONF_AGREEMENT, "");
		Map<Locale, String> agreementLocalized = LocalizationUtil.getLocalizationMap(actionRequest, CompanyConfigurationPortletKeys.COMPANY_CONF_AGREEMENT);

		String observations = ParamUtil.getString(actionRequest, CompanyConfigurationPortletKeys.COMPANY_CONF_OBSERVATIONS, "");
		Map<Locale, String> observationsLocalized = LocalizationUtil.getLocalizationMap(actionRequest, CompanyConfigurationPortletKeys.COMPANY_CONF_OBSERVATIONS);


		boolean notWorkableBool = false;
		if(CompanyConfigurationPortletKeys.COMPANY_CONF_YES.equals(notWorkable)){notWorkableBool = true;}

		boolean finishDateBool = false;
		if(CompanyConfigurationPortletKeys.COMPANY_CONF_YES.equals(finishDate)){finishDateBool = true;}

		boolean maxHoursDayBool = false;
		if(CompanyConfigurationPortletKeys.COMPANY_CONF_YES.equals(maxHoursDay)){maxHoursDayBool = true;}

		boolean maxHoursWeekBool = false;
		if(CompanyConfigurationPortletKeys.COMPANY_CONF_YES.equals(maxHoursWeek)){maxHoursWeekBool = true;}

		boolean savePastDaysBool = false;
		if(CompanyConfigurationPortletKeys.COMPANY_CONF_YES.equals(savePastDays)){savePastDaysBool = true;}

		boolean editSignsBool = false;
		if(CompanyConfigurationPortletKeys.COMPANY_CONF_YES.equals(editSigns)){editSignsBool = true;}

		boolean editDeleteSignsBool = false;
		if(CompanyConfigurationPortletKeys.COMPANY_CONF_YES.equals(editSigns)){editDeleteSignsBool = true;}



		boolean isWorkCentersBool = false;
		if(CompanyConfigurationPortletKeys.COMPANY_CONF_YES.equals(isWorkCenters)){isWorkCentersBool = true;}

		boolean isOwnCalendarsBool = false;
		if(CompanyConfigurationPortletKeys.COMPANY_CONF_YES.equals(isOwnCalendars)){isOwnCalendarsBool = true;}



		Date stDate = null;
		if(!Validator.isBlank(startDate)){
			try {
				stDate = dateFormat.parse(startDate);
			} catch (ParseException e) {
				_log.error(e);
			}
		}

		Date implDate = null;
		if(!Validator.isBlank(implantationDate)){
			try {
				implDate = dateFormat.parse(implantationDate);
			} catch (ParseException e) {
				_log.error(e);
			}
		}

		CompanyConfigurationUtil.setCompanyLogo(themeDisplay.getScopeGroupId(), themeDisplay.getLayout().isPrivateLayout(), logo);

		CompanyConfigurationUtil.saveCompanyConfiguration(companyConfId,
				notWorkableBool, finishDateBool, periodicity,
				maxHoursDayBool, maxHoursWeekBool, savePastDaysBool,
				maxHoursDayValue, maxHoursWeekValue, savePastDaysValue,
				editSignsBool, editSignsValue,
				editDeleteSignsBool, editDeleteSignsValue,
				stDate, implDate, descriptionLocalized, agreementLocalized, observationsLocalized,
				isWorkCentersBool, isOwnCalendarsBool, soporteEmails);
	}

}

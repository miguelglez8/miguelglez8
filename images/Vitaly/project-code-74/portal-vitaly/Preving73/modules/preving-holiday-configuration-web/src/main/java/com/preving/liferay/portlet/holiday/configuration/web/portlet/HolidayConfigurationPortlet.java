package com.preving.liferay.portlet.holiday.configuration.web.portlet;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.preving.liferay.portlet.holiday.configuration.web.constants.HolidayConfigurationPortletKeys;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.*;

import com.preving.liferay.portlet.holiday.configuration.web.util.PrevingHolidayUtil;
import org.osgi.service.component.annotations.Component;

import java.io.IOException;
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
		"javax.portlet.display-name=HolidayConfiguration",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.init-param.add-process-action-success-action=false",
		"javax.portlet.name=" + HolidayConfigurationPortletKeys.HOLIDAY_CONFIGURATION,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class HolidayConfigurationPortlet extends MVCPortlet {
	private static Log _log = LogFactoryUtil.getLog(HolidayConfigurationPortlet.class);

	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException {

		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);

		super.doView(renderRequest, renderResponse);
	}

	public void create(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

		Map<Locale, String> nameLocalized = LocalizationUtil.getLocalizationMap(actionRequest, HolidayConfigurationPortletKeys.HOLIDAY_NAME);

		int day =  Integer.parseInt(ParamUtil.getString(actionRequest, HolidayConfigurationPortletKeys.HOLIDAY_DAY));
		int month =  Integer.parseInt(ParamUtil.getString(actionRequest, HolidayConfigurationPortletKeys.HOLIDAY_MONTH));
		int year = 0;
     	boolean active =  Boolean.parseBoolean(ParamUtil.getString(actionRequest, HolidayConfigurationPortletKeys.HOLIDAY_ACTIVE));
     	boolean allowSign =  Boolean.parseBoolean(ParamUtil.getString(actionRequest, HolidayConfigurationPortletKeys.HOLIDAY_ALLOW_SIGN));
		String typeHoliday = ParamUtil.getString(actionRequest, HolidayConfigurationPortletKeys.HOLIDAY_TYPE_HOLIDAY, HolidayConfigurationPortletKeys.HOLIDAY_TYPE_NACIONAL_VALUE);

		PrevingHolidayUtil.createHoliday(
				themeDisplay.getCompanyId(),
				themeDisplay.getScopeGroupId(),
				nameLocalized,
				day,
				month,
				year,
				active,
				allowSign,
				typeHoliday
		);
	}

	public void saveHoliday(ActionRequest actionRequest,  ActionResponse actionResponse) {

		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

		long id = Long.parseLong(ParamUtil.getString(actionRequest, "holidayId"));

		Map<Locale, String> nameLocalized = LocalizationUtil.getLocalizationMap(actionRequest, HolidayConfigurationPortletKeys.HOLIDAY_NAME);

		int day =  Integer.parseInt(ParamUtil.getString(actionRequest, HolidayConfigurationPortletKeys.HOLIDAY_DAY));
		int month =  Integer.parseInt(ParamUtil.getString(actionRequest, HolidayConfigurationPortletKeys.HOLIDAY_MONTH));
		int year = 0;
		boolean active =  Boolean.parseBoolean(ParamUtil.getString(actionRequest, HolidayConfigurationPortletKeys.HOLIDAY_ACTIVE));
		boolean allowSign =  Boolean.parseBoolean(ParamUtil.getString(actionRequest, HolidayConfigurationPortletKeys.HOLIDAY_ALLOW_SIGN));
		String typeHoliday = ParamUtil.getString(actionRequest, HolidayConfigurationPortletKeys.HOLIDAY_TYPE_HOLIDAY, HolidayConfigurationPortletKeys.HOLIDAY_TYPE_NACIONAL_VALUE);

		PrevingHolidayUtil.updateHoliday(
				id,
				themeDisplay.getCompanyId(),
				themeDisplay.getScopeGroupId(),
				nameLocalized,
				day,
				month,
				year,
				active,
				allowSign,
				typeHoliday
		);

	}

	public void delete(ActionRequest actionRequest,  ActionResponse actionResponse) {
		long id = Long.parseLong(ParamUtil.getString(actionRequest, "holidayId"));

		PrevingHolidayUtil.deleteHoliday(id);
	}

}
package com.preving.liferay.portlet.timesheet.history.web.portlet;

import com.liferay.portal.kernel.security.auth.AuthTokenUtil;
import com.liferay.portal.kernel.servlet.PortletServlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;
import com.preving.liferay.portlet.timesheet.history.web.constants.TimesheetHistoryPortletKeys;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;

import java.io.IOException;

/**
 * @author agarciap
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.preving",
		"com.liferay.portlet.header-portlet-css=/css/calendarioBarra.css",
		"com.liferay.portlet.header-portlet-javascript=/js/calendarioBarrasObj.js",
		"com.liferay.portlet.instanceable=false",
		"javax.portlet.display-name=TimesheetHistory",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + TimesheetHistoryPortletKeys.TIMESHEETHISTORY,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class TimesheetHistoryPortlet extends MVCPortlet {


	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException {

		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);

		HttpServletRequest httpRequest = (HttpServletRequest) renderRequest.getAttribute(PortletServlet.PORTLET_SERVLET_REQUEST);

		String p_auth = "?p_auth=" + AuthTokenUtil.getToken(httpRequest);

		String company = "/company-id/" + themeDisplay.getCompanyId();
		String group = "/group-id/" + themeDisplay.getScopeGroupId();
		String user = "/user-id/" + themeDisplay.getUserId();

		String activities = themeDisplay.getURLPortal() + TimesheetHistoryPortletKeys.URL_JSONWS_ACTIVITIES + company + group + p_auth;
		String holidays = themeDisplay.getURLPortal() + TimesheetHistoryPortletKeys.URL_JSONWS_HOLIDAYS  + company + group + p_auth;
		String signs    = themeDisplay.getURLPortal() + TimesheetHistoryPortletKeys.URL_JSONWS_SIGNS + company + group + user + p_auth;

		renderRequest.setAttribute(TimesheetHistoryPortletKeys.URL_ACTIVITIES, activities);
		renderRequest.setAttribute(TimesheetHistoryPortletKeys.URL_HOLIDAYS, holidays);
		renderRequest.setAttribute(TimesheetHistoryPortletKeys.URL_SIGNS, signs);

		super.doView(renderRequest, renderResponse);
	}

}
package com.preving.liferay.portlet.user.report.web.portlet;

import com.preving.liferay.portlet.user.report.web.constants.UserReportPortletKeys;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;

/**
 * @author agarciap
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.preving",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.header-portlet-css=/css/jquery.dataTables.css",
		"com.liferay.portlet.header-portlet-javascript=/js/dataTables.responsive.min.js",
		"com.liferay.portlet.header-portlet-javascript=/js/jquery.dataTables.js",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=UserReport",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + UserReportPortletKeys.USERREPORT,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class UserReportPortlet extends MVCPortlet {
}
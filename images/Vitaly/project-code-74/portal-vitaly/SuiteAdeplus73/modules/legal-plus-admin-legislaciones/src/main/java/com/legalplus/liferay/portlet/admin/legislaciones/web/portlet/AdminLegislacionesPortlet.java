package com.legalplus.liferay.portlet.admin.legislaciones.web.portlet;

import com.legalplus.liferay.portlet.admin.legislaciones.web.constants.AdminLegislacionesPortletKeys;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import org.osgi.service.component.annotations.Component;

import javax.portlet.Portlet;

/**
 * @author agarciap
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.legalplus",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=false",
		"javax.portlet.display-name=AdminLegislaciones",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + AdminLegislacionesPortletKeys.ADMINLEGISLACIONES,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class AdminLegislacionesPortlet extends MVCPortlet {
}
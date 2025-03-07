package com.legalplus.liferay.portlet.web.calendario.portlet;

import com.legalplus.liferay.portlet.web.calendario.constants.WebCalendarioPortletKeys;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.*;
import javax.servlet.http.HttpServletRequest;

import com.liferay.portal.kernel.util.PortalUtil;
import org.osgi.service.component.annotations.Component;

import java.io.IOException;

/**
 * @author abarrero
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.legalplus",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=false",
		"javax.portlet.display-name=WebCalendario",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + WebCalendarioPortletKeys.WEBCALENDARIO,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class WebCalendarioPortlet extends MVCPortlet {

}
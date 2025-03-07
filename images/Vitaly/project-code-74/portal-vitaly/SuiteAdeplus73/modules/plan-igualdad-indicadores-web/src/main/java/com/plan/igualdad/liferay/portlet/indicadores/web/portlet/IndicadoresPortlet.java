package com.plan.igualdad.liferay.portlet.indicadores.web.portlet;

import com.plan.igualdad.liferay.portlet.indicadores.web.constants.IndicadoresPortletKeys;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;

/**
 * @author abarrero
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.planigualdad",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=false",
		"javax.portlet.display-name=Indicadores",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + IndicadoresPortletKeys.INDICADORES,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class IndicadoresPortlet extends MVCPortlet {
}
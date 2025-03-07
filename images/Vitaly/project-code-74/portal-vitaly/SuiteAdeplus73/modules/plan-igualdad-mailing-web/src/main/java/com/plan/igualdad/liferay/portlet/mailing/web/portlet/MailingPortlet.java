package com.plan.igualdad.liferay.portlet.mailing.web.portlet;

import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;
import com.plan.igualdad.liferay.portlet.mailing.web.constants.MailingPortletKeys;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;

import com.plan.igualdad.liferay.portlet.mailing.web.mail.ConsultorMailing;
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
		"javax.portlet.display-name=Mailing",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + MailingPortletKeys.MAILING,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class MailingPortlet extends MVCPortlet {

	public void altaConsultor(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

		ConsultorMailing.asignarConsultor(
				themeDisplay.getUser().getEmailAddress(),
				themeDisplay.getUser(),
				"SKT",
				"Consultor SKT",
				"consultor@serikat.com");
	}

}
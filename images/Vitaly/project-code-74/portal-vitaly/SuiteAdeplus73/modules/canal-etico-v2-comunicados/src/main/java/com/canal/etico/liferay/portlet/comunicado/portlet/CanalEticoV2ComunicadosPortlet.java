package com.canal.etico.liferay.portlet.comunicado.portlet;

import com.canal.etico.liferay.portlet.comunicado.constants.CanalEticoV2ComunicadosPortletKeys;

import com.liferay.captcha.util.CaptchaUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.*;

import org.osgi.service.component.annotations.Component;

import java.io.IOException;

/**
 * @author rrodriguezn
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=CanalEticoV2Comunicados",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + CanalEticoV2ComunicadosPortletKeys.CANALETICOV2COMUNICADOS,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class CanalEticoV2ComunicadosPortlet extends MVCPortlet {
	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {

		PortletURL captchaURL = renderResponse.createActionURL();
		captchaURL.setParameter(ActionRequest.ACTION_NAME, "captchaURL");

		renderRequest.setAttribute("captchaURL", captchaURL.toString());

		super.render(renderRequest, renderResponse);
	}
}
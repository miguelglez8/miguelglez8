package com.canal.etico.liferay.portlet.comunicado.portlet;

import com.canal.etico.liferay.portlet.comunicado.constants.CanalEticoV2ComunicadosPortletKeys;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import org.osgi.service.component.annotations.Component;

import javax.portlet.Portlet;

/**
 * @author rrodriguezn
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=CanalEticoV2AcuseRecibo",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/sucess-recibo.jsp",
		"javax.portlet.name=" + CanalEticoV2ComunicadosPortletKeys.CANALETICOV2COMUNICADOS + "recibo",
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class CanalEticoV2AcuseReciboPortlet extends MVCPortlet {

}
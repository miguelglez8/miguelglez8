package com.adeplus.liferay.portlet.menu.web.portlet;

import com.adeplus.liferay.portlet.menu.web.constants.MenuPortletKeys;
import com.adeplus.liferay.portlet.suite.manager.model.Comp;
import com.adeplus.liferay.portlet.suite.manager.service.CompLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.plan.igualdad.liferay.portlet.commons.web.estado.EstadoUtils;
import com.plan.igualdad.liferay.portlet.manager.service.VersionLocalServiceUtil;

import java.io.IOException;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;

/**
 * @author abarrero
 */
@Component(
		immediate = true,
		property = {
				"com.liferay.portlet.display-category=category.adeplus",
				"com.liferay.portlet.header-portlet-css=/css/main.css",
				"com.liferay.portlet.instanceable=true",
				"javax.portlet.display-name=Menu",
				"javax.portlet.init-param.template-path=/",
				"javax.portlet.init-param.view-template=/view.jsp",
				"javax.portlet.name=" + MenuPortletKeys.MENU,
				"javax.portlet.resource-bundle=content.Language",
				"javax.portlet.security-role-ref=power-user,user"
		},
		service = Portlet.class
)
public class MenuPortlet extends MVCPortlet {

	private static Log _log = LogFactoryUtil.getLog(MenuPortlet.class);

	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {

		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);

		HttpServletRequest request = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(renderRequest));

		try {
			String pagina = renderRequest.getPreferences().getValue(MenuPortletKeys.LAYOUT, "0");
			String paginaDefecto = renderRequest.getPreferences().getValue(MenuPortletKeys.LAYOUT_DEFECT, "");

			String estadoDefecto = renderRequest.getPreferences().getValue(MenuPortletKeys.ESTADO_DEFECT, "0");

			renderRequest.setAttribute(MenuPortletKeys.LAYOUT_DEFECT, paginaDefecto);
			renderRequest.setAttribute(MenuPortletKeys.ESTADO_DEFECT, estadoDefecto);
			Layout layout = LayoutLocalServiceUtil.getLayout(Long.parseLong(pagina));
			Comp comp = null;
			if( layout.getName(themeDisplay.getLocale()).equals("Empresas") &&  Validator.isNotNull(request.getParameter(MenuPortletKeys.QUERY_PARAM)) && !request.getParameter(MenuPortletKeys.QUERY_PARAM).isEmpty()) {
				Long idCompany = Long.parseLong(request.getParameter(MenuPortletKeys.QUERY_PARAM));

				comp = CompLocalServiceUtil.fetchComp(idCompany);

				renderRequest.setAttribute(MenuPortletKeys.COMP_NAME_PARAM, comp.getName());
			}else if(Validator.isNotNull(request.getParameter("comp")) && !Validator.isBlank(request.getParameter("comp"))){
				Long idCompany = Long.parseLong(request.getParameter("comp"));

				comp = CompLocalServiceUtil.getComp(idCompany);

				renderRequest.setAttribute(MenuPortletKeys.COMP_NAME_PARAM, comp.getName());

			}

			if(Validator.isNotNull(comp) && Validator.isNotNull(VersionLocalServiceUtil.findVersionActiva(comp.getCompId()))) {
				renderRequest.setAttribute(MenuPortletKeys.ESTADOS_LIST_PARAM, EstadoUtils.getEstados());

				renderRequest.setAttribute(MenuPortletKeys.ESTADO_CURRENT_PARAM, EstadoUtils.getIdEstado(comp.getCompId()));
			}

		} catch (PortalException e) {
			_log.error("ERROR: ", e);
		}

		super.render(renderRequest, renderResponse);
	}

}
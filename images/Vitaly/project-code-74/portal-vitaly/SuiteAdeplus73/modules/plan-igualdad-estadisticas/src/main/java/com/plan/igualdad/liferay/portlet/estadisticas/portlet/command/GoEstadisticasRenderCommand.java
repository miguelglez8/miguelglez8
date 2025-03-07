package com.plan.igualdad.liferay.portlet.estadisticas.portlet.command;


import com.adeplus.liferay.portlet.suite.manager.model.UserCompany;
import com.adeplus.liferay.portlet.suite.manager.service.UserCompanyLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.security.sso.openid.connect.OpenIdConnectProviderRegistry;
import com.liferay.portal.security.sso.openid.connect.OpenIdConnectSession;
import com.liferay.portal.security.sso.openid.connect.constants.OpenIdConnectWebKeys;
import com.plan.igualdad.liferay.portlet.commons.web.constants.PlanIgualdadCommonsPortletKeys;
import com.plan.igualdad.liferay.portlet.commons.web.role.PlanIgualdadRoleUtil;
import com.plan.igualdad.liferay.portlet.estadisticas.constants.PlanIgualdadEstadisticasPortletKeys;
import com.plan.igualdad.liferay.portlet.manager.model.Version;
import com.plan.igualdad.liferay.portlet.manager.service.VersionLocalServiceUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.PortletException;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collection;

@Component(
        immediate=true,
        property= {
                "javax.portlet.name=" + PlanIgualdadEstadisticasPortletKeys.PLANIGUALDADESTADISTICAS,
                "mvc.command.name=/"
        },
        service = MVCRenderCommand.class
)
public class GoEstadisticasRenderCommand implements MVCRenderCommand {
    private static Log _log = LogFactoryUtil.getLog(GoEstadisticasRenderCommand.class);
    @Override
    public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {

        ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
        String compId;

        if (PlanIgualdadRoleUtil.isAdministradorRole(themeDisplay.getCompanyId(), themeDisplay.getUser())
                || PlanIgualdadRoleUtil.isConsultorRole(themeDisplay.getCompanyId(), themeDisplay.getUser())) {

            HttpServletRequest request = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(renderRequest));
            compId = request.getParameter(PlanIgualdadEstadisticasPortletKeys.QUERY_PARAM);

            if (Validator.isNull(compId)) compId = ParamUtil.getString(renderRequest, PlanIgualdadEstadisticasPortletKeys.COMPID_PARAM);
            if (Validator.isNull(compId)) return "/error.jsp";

        } else {

            UserCompany company = UserCompanyLocalServiceUtil.getUserDefaultCompany(themeDisplay.getUserId());
            //if(PlanIgualdadCommonsPortletKeys.ITS_NEW_VERSION) {
              //  compId = String.valueOf(company.getDefaultEmpresaId());
            //}else{
                compId = String.valueOf(company.getDefaultEmpresaId());
            //}

        }

        _log.info("Acceso a estadisticas para el usuario: "+themeDisplay.getUser().getEmailAddress()+" empresa: "+compId);

        Version version = VersionLocalServiceUtil.findVersionActiva(Long.parseLong(compId));

        if (Validator.isNull(version)) return "/error.jsp";
        renderRequest.setAttribute(PlanIgualdadEstadisticasPortletKeys.COMPID_PARAM, Long.parseLong(compId));
        renderRequest.setAttribute(PlanIgualdadEstadisticasPortletKeys.VERSIONID_PARAM, version.getVersionId());

        PortletSession session = renderRequest.getPortletSession();
        String myMessage = (String) session.getAttribute("myMessage", PortletSession.PORTLET_SCOPE);
        if (myMessage != null) {
            renderRequest.setAttribute("messageSave",myMessage);
            session.removeAttribute("myMessage", PortletSession.PORTLET_SCOPE);
        }
        return "/view.jsp";


    }
    @Reference
    private OpenIdConnectProviderRegistry openIdConnectProviderRegistry;

}

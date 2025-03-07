package com.plan.igualdad.liferay.portlet.informacion.web.portlet.command;

import com.adeplus.liferay.portlet.suite.manager.model.Comp;
import com.adeplus.liferay.portlet.suite.manager.model.UserCompany;
import com.adeplus.liferay.portlet.suite.manager.service.CompLocalServiceUtil;
import com.adeplus.liferay.portlet.suite.manager.service.UserCompanyLocalServiceUtil;
import com.legalplus.liferay.portlet.legalplus.manager.model.CNAES;
import com.legalplus.liferay.portlet.legalplus.manager.service.CNAESLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.plan.igualdad.liferay.portlet.commons.web.constants.PlanIgualdadCommonsPortletKeys;
import com.plan.igualdad.liferay.portlet.commons.web.role.PlanIgualdadRoleUtil;
import com.plan.igualdad.liferay.portlet.informacion.web.constants.PlanIgualdadInformacionWebPortletKeys;
import com.plan.igualdad.liferay.portlet.manager.model.Organizacion;
import com.plan.igualdad.liferay.portlet.manager.model.Version;
import com.plan.igualdad.liferay.portlet.manager.service.OrganizacionLocalServiceUtil;
import com.plan.igualdad.liferay.portlet.manager.service.VersionLocalServiceUtil;
import org.osgi.service.component.annotations.Component;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Component(
        immediate=true,
        property= {
                "javax.portlet.name=" + PlanIgualdadInformacionWebPortletKeys.PLANIGUALDADINFORMACIONWEB,
                "mvc.command.name=/"
        },
        service = MVCRenderCommand.class
)
public class GoInformacionPreliminarRenderCommand implements MVCRenderCommand {
    private static Log _log = LogFactoryUtil.getLog(GoInformacionPreliminarRenderCommand.class);
    @Override
    public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {

        ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
        String compId;

        if (PlanIgualdadRoleUtil.isAdministradorRole(themeDisplay.getCompanyId(), themeDisplay.getUser())
                || PlanIgualdadRoleUtil.isConsultorRole(themeDisplay.getCompanyId(), themeDisplay.getUser())) {

            HttpServletRequest request = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(renderRequest));
            compId = request.getParameter(PlanIgualdadInformacionWebPortletKeys.QUERY_PARAM);

            if (Validator.isNull(compId)) compId = ParamUtil.getString(renderRequest, PlanIgualdadInformacionWebPortletKeys.COMPID_PARAM);
            if (Validator.isNull(compId)) return "/error.jsp";

        } else {

            UserCompany company = UserCompanyLocalServiceUtil.getUserDefaultCompany(themeDisplay.getUserId());
            if(PlanIgualdadCommonsPortletKeys.ITS_NEW_VERSION) {
                compId = String.valueOf(company.getDefaultEmpresaId());
            }else{
                compId = String.valueOf(company.getCompId());
            }

        }

        Version version = VersionLocalServiceUtil.findVersionActiva(Long.parseLong(compId));
        if (Validator.isNull(version)) return "/error.jsp";

        Comp empresa = CompLocalServiceUtil.fetchComp(Long.parseLong(compId));
        List<CNAES> cnaes = CNAESLocalServiceUtil.getCNAESs(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
        Organizacion organizacion = OrganizacionLocalServiceUtil.findByOrganizacion(Long.parseLong(compId), version.getVersionId());

        renderRequest.setAttribute(PlanIgualdadInformacionWebPortletKeys.EMPRESA, empresa);
        renderRequest.setAttribute(PlanIgualdadInformacionWebPortletKeys.CNAES, cnaes);
        renderRequest.setAttribute(PlanIgualdadInformacionWebPortletKeys.VERSION, version.getVersionId());
        renderRequest.setAttribute(PlanIgualdadInformacionWebPortletKeys.ORGANIZACION, organizacion);

        return "/view.jsp";
    }

}

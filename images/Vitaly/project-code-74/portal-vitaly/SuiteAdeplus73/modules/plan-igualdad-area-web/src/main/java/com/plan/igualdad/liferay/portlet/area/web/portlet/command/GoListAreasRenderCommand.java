package com.plan.igualdad.liferay.portlet.area.web.portlet.command;

import com.adeplus.liferay.portlet.suite.manager.model.UserCompany;
import com.adeplus.liferay.portlet.suite.manager.service.UserCompanyLocalServiceUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.plan.igualdad.liferay.portlet.area.web.constants.PlanIgualdadAreaWebPortletKeys;
import com.plan.igualdad.liferay.portlet.commons.web.constants.PlanIgualdadCommonsPortletKeys;
import com.plan.igualdad.liferay.portlet.commons.web.role.PlanIgualdadRoleUtil;
import com.plan.igualdad.liferay.portlet.manager.model.Version;
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
                "javax.portlet.name=" + PlanIgualdadAreaWebPortletKeys.PLANIGUALDADAREAWEB,
                "mvc.command.name=/"
        },
        service = MVCRenderCommand.class
)
public class GoListAreasRenderCommand implements MVCRenderCommand {

        @Override
        public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
                ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
                String compId;

                if (PlanIgualdadRoleUtil.isAdministradorRole(themeDisplay.getCompanyId(), themeDisplay.getUser())
                        || PlanIgualdadRoleUtil.isConsultorRole(themeDisplay.getCompanyId(), themeDisplay.getUser())) {

                        HttpServletRequest request = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(renderRequest));
                        compId = request.getParameter(PlanIgualdadAreaWebPortletKeys.QUERY_PARAM);

                        if (Validator.isNull(compId)) compId = ParamUtil.getString(renderRequest, PlanIgualdadAreaWebPortletKeys.COMPID_PARAM);
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

                renderRequest.setAttribute(PlanIgualdadAreaWebPortletKeys.COMPID_PARAM, compId);
                renderRequest.setAttribute(PlanIgualdadAreaWebPortletKeys.VERSIONID_PARAM, version.getVersionId());

                return "/view.jsp";
        }

}

package com.plan.igualdad.liferay.portlet.version.web.portlet.command;

import com.adeplus.liferay.portlet.suite.manager.model.Comp;
import com.adeplus.liferay.portlet.suite.manager.model.CompClientApplication;
import com.adeplus.liferay.portlet.suite.manager.model.UserCompany;
import com.adeplus.liferay.portlet.suite.manager.service.CompClientApplicationLocalServiceUtil;
import com.adeplus.liferay.portlet.suite.manager.service.CompLocalServiceUtil;
import com.adeplus.liferay.portlet.suite.manager.service.UserCompanyLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.plan.igualdad.liferay.portlet.commons.web.constants.PlanIgualdadCommonsPortletKeys;
import com.plan.igualdad.liferay.portlet.commons.web.role.PlanIgualdadRoleUtil;
import com.plan.igualdad.liferay.portlet.manager.model.Version;
import com.plan.igualdad.liferay.portlet.manager.service.VersionLocalServiceUtil;
import com.plan.igualdad.liferay.portlet.version.web.constants.PlanIgualdadVersionWebPortletKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Component(
        immediate=true,
        property= {
                "javax.portlet.name=" + PlanIgualdadVersionWebPortletKeys.PLANIGUALDADVERSIONWEB,
                "mvc.command.name=/"
        },
        service = MVCRenderCommand.class
)
public class GoListVersionesRenderCommand implements MVCRenderCommand {

    @Override
    public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {

        ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
        String compId;

        if (PlanIgualdadRoleUtil.isAdministradorRole(themeDisplay.getCompanyId(), themeDisplay.getUser())
                || PlanIgualdadRoleUtil.isConsultorRole(themeDisplay.getCompanyId(), themeDisplay.getUser())) {

            HttpServletRequest request = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(renderRequest));
            compId = request.getParameter(PlanIgualdadVersionWebPortletKeys.QUERY_PARAM);

            if (Validator.isNull(compId)) compId = ParamUtil.getString(renderRequest, PlanIgualdadVersionWebPortletKeys.COMPID_PARAM);
            if (Validator.isNull(compId)) return "/error.jsp";

        } else {

            UserCompany company = UserCompanyLocalServiceUtil.getUserDefaultCompany(themeDisplay.getUserId());
            if(PlanIgualdadCommonsPortletKeys.ITS_NEW_VERSION) {
                compId = String.valueOf(company.getDefaultEmpresaId());
            }else{
                compId = String.valueOf(company.getCompId());
            }

        }
        
        Boolean refresh = false;
        Version version = VersionLocalServiceUtil.findVersionActiva(Long.parseLong(compId));
        if (Validator.isNull(version)) {
        	try {
                Comp comp = null;
                if(PlanIgualdadCommonsPortletKeys.ITS_NEW_VERSION) {
                    CompClientApplication compClientApplication = CompClientApplicationLocalServiceUtil.fetchCompClientApplication(Long.parseLong(compId));
                    if(Validator.isNotNull(compClientApplication)){
                        comp = CompLocalServiceUtil.getComp(compClientApplication.getCompId());
                        if(!compClientApplication.getIdEstado().isEmpty()){
                            compClientApplication.setIdEstado(StringPool.BLANK);
                            CompClientApplicationLocalServiceUtil.updateCompClientApplication(compClientApplication);
                        }
                    }
                }else{
                    comp = CompLocalServiceUtil.getComp(Long.parseLong(compId));

                }

                if(!comp.getIdEstado().isEmpty()) {
                    comp.setIdEstado(StringPool.BLANK);

                    CompLocalServiceUtil.updateComp(comp);
                    refresh = true;
                }

				
			} catch (NumberFormatException | PortalException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        renderRequest.setAttribute(PlanIgualdadVersionWebPortletKeys.REFRESH_PARAM, refresh);
        renderRequest.setAttribute(PlanIgualdadVersionWebPortletKeys.COMPID_PARAM, compId);
        renderRequest.setAttribute(PlanIgualdadVersionWebPortletKeys.VERSION_ACTIVA, VersionLocalServiceUtil.findVersionActiva(Long.parseLong(compId)));

        return "/view.jsp";
    }

}

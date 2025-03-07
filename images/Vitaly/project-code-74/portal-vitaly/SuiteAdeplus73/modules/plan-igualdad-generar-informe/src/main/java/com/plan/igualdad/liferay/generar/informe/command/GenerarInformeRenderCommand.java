package com.plan.igualdad.liferay.generar.informe.command;

import com.adeplus.liferay.portlet.suite.manager.model.UserCompany;
import com.adeplus.liferay.portlet.suite.manager.service.UserCompanyLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.plan.igualdad.liferay.generar.informe.constants.PlanIgualdadGenerarInformePortletKeys;
import com.plan.igualdad.liferay.portlet.commons.web.constants.PlanIgualdadCommonsPortletKeys;
import com.plan.igualdad.liferay.portlet.commons.web.indicadores.IndicadoresUtils;
import com.plan.igualdad.liferay.portlet.commons.web.role.PlanIgualdadRoleUtil;
import com.plan.igualdad.liferay.portlet.manager.model.Version;
import com.plan.igualdad.liferay.portlet.manager.service.VersionLocalServiceUtil;

import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;

@Component(
        immediate=true,
        property= {
                "javax.portlet.name=" + PlanIgualdadGenerarInformePortletKeys.PLANIGUALDADGENERARINFORME,
                "mvc.command.name=/"
        },
        service = MVCRenderCommand.class
)
public class GenerarInformeRenderCommand implements MVCRenderCommand{

	private static Log _log = LogFactoryUtil.getLog(GenerarInformeRenderCommand.class);
	
	@Override
    public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);

		String compId = StringPool.BLANK;
        if (PlanIgualdadRoleUtil.isAdministradorRole(themeDisplay.getCompanyId(), themeDisplay.getUser())
                || PlanIgualdadRoleUtil.isConsultorRole(themeDisplay.getCompanyId(), themeDisplay.getUser())) {

            HttpServletRequest request = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(renderRequest));
            
            compId = request.getParameter(PlanIgualdadGenerarInformePortletKeys.QUERY_PARAM);
            if(compId == null || compId.isEmpty()) {
            	compId = renderRequest.getParameter(PlanIgualdadGenerarInformePortletKeys.QUERY_PARAM);
            }
            if (Validator.isNull(compId)) compId = ParamUtil.getString(renderRequest, PlanIgualdadGenerarInformePortletKeys.COMPID_PARAM);
        }
        
        if(compId.isEmpty()) {
            UserCompany company = UserCompanyLocalServiceUtil.getUserDefaultCompany(themeDisplay.getUserId());
            if(PlanIgualdadCommonsPortletKeys.ITS_NEW_VERSION) {
                compId = String.valueOf(company.getDefaultEmpresaId());
            }else{
                compId = String.valueOf(company.getCompId());
            }
        }
        
        renderRequest.setAttribute(PlanIgualdadGenerarInformePortletKeys.COMPID_PARAM, Long.parseLong(compId));

        Version version = VersionLocalServiceUtil.findVersionActiva(Long.parseLong(compId));
        if (Validator.isNull(version)) return "/error.jsp";
        
        String showModal = "0" ;
        try {
            long indicadorDisennoPlan = IndicadoresUtils.getIndicadorDisennoPlan(Long.parseLong(compId), version.getVersionId());
            long indicadorEvaluacionPlan = IndicadoresUtils.getIndicadorEvaluacionPlan(Long.parseLong(compId), version.getVersionId());
			long indicadorSeguimientoPlan = IndicadoresUtils.getIndicadorSeguimientoPlan(Long.parseLong(compId), version.getVersionId());
			if(indicadorDisennoPlan==100L && indicadorEvaluacionPlan==100L && indicadorSeguimientoPlan==100L) {
				showModal = "1";
			}
        } catch (JSONException | NumberFormatException e) {
			_log.error("ERROR: ", e);
		}
        renderRequest.setAttribute(PlanIgualdadGenerarInformePortletKeys.SHOW_MODAL_PARAM, showModal);

        return "/view.jsp";
	}
}

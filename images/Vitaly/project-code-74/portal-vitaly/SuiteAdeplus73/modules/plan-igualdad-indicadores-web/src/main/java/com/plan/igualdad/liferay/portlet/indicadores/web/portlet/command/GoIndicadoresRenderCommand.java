package com.plan.igualdad.liferay.portlet.indicadores.web.portlet.command;

import com.adeplus.liferay.portlet.suite.manager.model.UserCompany;
import com.adeplus.liferay.portlet.suite.manager.service.UserCompanyLocalServiceUtil;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.plan.igualdad.liferay.portlet.commons.web.constants.PlanIgualdadCommonsPortletKeys;
import com.plan.igualdad.liferay.portlet.commons.web.indicadores.IndicadoresUtils;
import com.plan.igualdad.liferay.portlet.commons.web.role.PlanIgualdadRoleUtil;
import com.plan.igualdad.liferay.portlet.indicadores.web.constants.IndicadoresPortletKeys;
import com.plan.igualdad.liferay.portlet.manager.model.Version;
import com.plan.igualdad.liferay.portlet.manager.service.VersionLocalServiceUtil;
import org.osgi.service.component.annotations.Component;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.ResourceBundle;

@Component(
        immediate=true,
        property= {
                "javax.portlet.name=" + IndicadoresPortletKeys.INDICADORES,
                "mvc.command.name=/"
        },
        service = MVCRenderCommand.class
)
public class GoIndicadoresRenderCommand implements MVCRenderCommand {

    private static Log _log = LogFactoryUtil.getLog(GoIndicadoresRenderCommand.class);

    @Override
    public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
        try {

            ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
            ResourceBundle bundle = ResourceBundle.getBundle("content/Language", themeDisplay.getUser().getLocale());

            String currentUrl = themeDisplay.getScopeGroup().getDisplayURL(themeDisplay, false);
            String compId;
            String roleId;

            if (PlanIgualdadRoleUtil.isAdministradorRole(themeDisplay.getCompanyId(), themeDisplay.getUser())
                    || PlanIgualdadRoleUtil.isConsultorRole(themeDisplay.getCompanyId(), themeDisplay.getUser())) {

                HttpServletRequest request = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(renderRequest));
                compId = request.getParameter(IndicadoresPortletKeys.QUERY_PARAM);
                roleId = "admin";

                if (Validator.isNull(compId))
                    compId = ParamUtil.getString(renderRequest, IndicadoresPortletKeys.COMPID_PARAM);
                if (Validator.isNull(compId)) return "/error.jsp";

            } else {

                UserCompany company = UserCompanyLocalServiceUtil.getUserDefaultCompany(themeDisplay.getUserId());
                if(PlanIgualdadCommonsPortletKeys.ITS_NEW_VERSION) {
                    compId = String.valueOf(company.getDefaultEmpresaId());
                }else{
                    compId = String.valueOf(company.getCompId());
                }
                roleId = "client";

            }

            Version version = VersionLocalServiceUtil.findVersionActiva(Long.parseLong(compId));
            if (Validator.isNull(version)) return "/error.jsp";
            
            long indicadorDisennoPlan = IndicadoresUtils.getIndicadorDisennoPlan(Long.parseLong(compId), version.getVersionId());
            renderRequest.setAttribute(IndicadoresPortletKeys.INDICADOR_DISENNO_PLAN, indicadorDisennoPlan);

            long indicadorSeguimientoPlan = IndicadoresUtils.getIndicadorSeguimientoPlan(Long.parseLong(compId), version.getVersionId());
            renderRequest.setAttribute(IndicadoresPortletKeys.INDICADOR_SEGUIMIENTO_PLAN, indicadorSeguimientoPlan);

            long indicadorEvaluacionPlan = IndicadoresUtils.getIndicadorEvaluacionPlan(Long.parseLong(compId), version.getVersionId());
            renderRequest.setAttribute(IndicadoresPortletKeys.INDICADOR_EVALUACION_PLAN, indicadorEvaluacionPlan);

            //URL
            String hitosUrl = LanguageUtil.format(bundle, "indicadores.url." + roleId + ".hitosUrl", compId);
            String seguimientoUrl = LanguageUtil.format(bundle, "indicadores.url." + roleId + ".seguimientoUrl", compId);
            String evaluacionUrl = LanguageUtil.format(bundle, "indicadores.url." + roleId + ".evaluacionUrl", compId);

            renderRequest.setAttribute(IndicadoresPortletKeys.HITOS_URL, currentUrl + hitosUrl);
            renderRequest.setAttribute(IndicadoresPortletKeys.SEGUIMIENTO_URL, currentUrl + seguimientoUrl);
            renderRequest.setAttribute(IndicadoresPortletKeys.EVALUACION_URL, currentUrl + evaluacionUrl);

        } catch (JSONException e) {
            _log.error(e, e);
        }

        return "/view.jsp";
    }

}

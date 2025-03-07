package com.legalplus.liferay.portlet.web.evaluacion.portlet.command;

import com.legalplus.liferay.portlet.legalplus.manager.model.EvalLegislacion;
import com.legalplus.liferay.portlet.legalplus.manager.model.Legislacion;
import com.legalplus.liferay.portlet.legalplus.manager.service.EvalLegislacionLocalServiceUtil;
import com.legalplus.liferay.portlet.legalplus.manager.service.LegislacionLocalServiceUtil;
import com.legalplus.liferay.portlet.legalplus.manager.service.persistence.EvalLegislacionPK;
import com.legalplus.liferay.portlet.web.evaluacion.constants.WebEvaluacionPortletKeys;
import com.legalplus.liferay.portlet.web.evaluacion.utils.EvaluacionUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

@Component(
        immediate=true,
        property= {
                "javax.portlet.name=" + WebEvaluacionPortletKeys.WEBEVALUACION,
                "mvc.command.name=goEvaluacionLegislacion"
        },
        service = MVCRenderCommand.class
)
public class GoEvaluacionLegislacionRenderCommand implements MVCRenderCommand {

    private static Log _log = LogFactoryUtil.getLog(GoEvaluacionLegislacionRenderCommand.class);

    @Override
    public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
        return goEvaluacionLegislacion(renderRequest);
    }

    public static String goEvaluacionLegislacion(RenderRequest renderRequest) {
        ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);

        String compId = EvaluacionUtil.getCompany(themeDisplay, renderRequest);
        if (Validator.isBlank(compId)) return "/error.jsp";

        String legislacionId = EvaluacionUtil.getLegislacion(renderRequest, Long.parseLong(compId));
        if (Validator.isBlank(legislacionId)) return "/error.jsp";

        String versionId = EvaluacionUtil.getVersion(renderRequest);
        Long version = Validator.isNotNull(versionId) ? Long.parseLong(versionId) : -1;

        EvalLegislacion evalLegislacion = EvalLegislacionLocalServiceUtil.getLastEvalLegislacion(Long.parseLong(compId), legislacionId);
        if (Validator.isNotNull(evalLegislacion) && evalLegislacion.getCumplimiento() == -1 && version == -1) return "/error.jsp";

        EvalLegislacionPK evalLegislacionPK = new EvalLegislacionPK();
        evalLegislacionPK.setCompId(Long.parseLong(compId));
        evalLegislacionPK.setLegislacionId(legislacionId);
        evalLegislacionPK.setVersion(version);

        EvalLegislacion evaluacion = EvalLegislacionLocalServiceUtil.fetchEvalLegislacion(evalLegislacionPK);
        Legislacion legislacion = LegislacionLocalServiceUtil.fetchLegislacion(legislacionId);

        renderRequest.setAttribute(WebEvaluacionPortletKeys.EVALUACION, evaluacion);
        renderRequest.setAttribute(WebEvaluacionPortletKeys.LEGISLACION_PARAM, legislacionId);
        renderRequest.setAttribute(WebEvaluacionPortletKeys.COMP_PARAM, compId);
        renderRequest.setAttribute(WebEvaluacionPortletKeys.LEGISLACION, legislacion);

        return "/evaluacion-legislacion.jsp";
    }

}

package com.legalplus.liferay.portlet.web.evaluacion.portlet.command;

import com.legalplus.liferay.portlet.legalplus.manager.model.EvalRequisito;
import com.legalplus.liferay.portlet.legalplus.manager.model.Legislacion;
import com.legalplus.liferay.portlet.legalplus.manager.model.Requisito;
import com.legalplus.liferay.portlet.legalplus.manager.service.EvalRequisitoLocalServiceUtil;
import com.legalplus.liferay.portlet.legalplus.manager.service.LegislacionLocalServiceUtil;
import com.legalplus.liferay.portlet.legalplus.manager.service.RequisitoLocalServiceUtil;
import com.legalplus.liferay.portlet.legalplus.manager.service.persistence.EvalRequisitoPK;
import com.legalplus.liferay.portlet.web.evaluacion.constants.WebEvaluacionPortletKeys;
import com.legalplus.liferay.portlet.web.evaluacion.utils.EvaluacionUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import java.util.List;

@Component(
        immediate=true,
        property= {
                "javax.portlet.name=" + WebEvaluacionPortletKeys.WEBEVALUACION,
                "mvc.command.name=goEvaluacionRequisito"
        },
        service = MVCRenderCommand.class
)
public class GoEvaluacionRequisitoRenderCommand implements MVCRenderCommand {

    private static Log _log = LogFactoryUtil.getLog(GoEvaluacionRequisitoRenderCommand.class);

    @Override
    public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
        return goEvaluacionRequisito(renderRequest);
    }

    public static String goEvaluacionRequisito(RenderRequest renderRequest){
        ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);

        String compId = EvaluacionUtil.getCompany(themeDisplay, renderRequest);
        if (Validator.isBlank(compId)) return "/error.jsp";

        String legislacionId = EvaluacionUtil.getLegislacion(renderRequest, Long.parseLong(compId));
        if (Validator.isBlank(legislacionId)) return "/error.jsp";

        String requisitoId = EvaluacionUtil.getRequisito(renderRequest);
        String versionId = EvaluacionUtil.getVersion(renderRequest);
        Long version = Validator.isNotNull(versionId) ? Long.parseLong(versionId) : -1;

        EvalRequisitoPK evalRequisitoPK  = new EvalRequisitoPK();
        evalRequisitoPK.setCompId(Long.parseLong(compId));
        evalRequisitoPK.setLegislacionId(legislacionId);
        evalRequisitoPK.setRequisitoId(requisitoId);
        evalRequisitoPK.setVersion(version);

        EvalRequisito evaluacion = EvalRequisitoLocalServiceUtil.fetchEvalRequisito(evalRequisitoPK);
        Requisito requisito = RequisitoLocalServiceUtil.findByLegislacionRequisito(legislacionId, requisitoId);
        Legislacion legislacion = LegislacionLocalServiceUtil.fetchLegislacion(legislacionId);

        renderRequest.setAttribute(WebEvaluacionPortletKeys.REQUISITO, requisito);
        renderRequest.setAttribute(WebEvaluacionPortletKeys.EVALUACION, evaluacion);
        renderRequest.setAttribute(WebEvaluacionPortletKeys.LEGISLACION_PARAM, legislacionId);
        renderRequest.setAttribute(WebEvaluacionPortletKeys.COMP_PARAM, compId);
        renderRequest.setAttribute(WebEvaluacionPortletKeys.LEGISLACION, legislacion);

        return "/evaluacion-requisito.jsp";
    }

}

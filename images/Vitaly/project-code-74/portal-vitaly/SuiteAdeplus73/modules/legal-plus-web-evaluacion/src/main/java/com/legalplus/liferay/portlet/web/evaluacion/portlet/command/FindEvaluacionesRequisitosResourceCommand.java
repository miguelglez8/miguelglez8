package com.legalplus.liferay.portlet.web.evaluacion.portlet.command;

import com.legalplus.liferay.portlet.legalplus.manager.service.EvalLegislacionLocalServiceUtil;
import com.legalplus.liferay.portlet.legalplus.manager.service.EvalRequisitoLocalServiceUtil;
import com.legalplus.liferay.portlet.web.evaluacion.constants.WebEvaluacionPortletKeys;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.util.PortalUtil;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;

@Component(
        immediate=true,
        property= {
                "javax.portlet.name=" + WebEvaluacionPortletKeys.WEBEVALUACION,
                "mvc.command.name=buscarEvaluacionesRequisitos"
        },
        service = MVCResourceCommand.class
)
public class FindEvaluacionesRequisitosResourceCommand extends BaseMVCResourceCommand {

    @Override
    protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
        HttpServletRequest httpReq = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(resourceRequest));
        String compId = httpReq.getParameter(WebEvaluacionPortletKeys.COMP_PARAM);
        String legislacionId = httpReq.getParameter(WebEvaluacionPortletKeys.LEGISLACION_PARAM);

        String resultados = EvalRequisitoLocalServiceUtil.getEvaluacionesRequisitos(Long.parseLong(compId), legislacionId);
        JSONObject json = JSONFactoryUtil.createJSONObject(resultados);

        resourceResponse.setContentType("application/json");
        resourceResponse.setCharacterEncoding("UTF-8");
        resourceResponse.getWriter().write(json.toString());
    }

}

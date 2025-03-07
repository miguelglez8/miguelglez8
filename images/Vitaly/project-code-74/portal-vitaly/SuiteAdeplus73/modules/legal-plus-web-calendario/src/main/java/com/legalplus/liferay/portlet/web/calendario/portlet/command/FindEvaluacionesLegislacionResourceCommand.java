package com.legalplus.liferay.portlet.web.calendario.portlet.command;

import com.legalplus.liferay.portlet.legalplus.manager.service.EvalLegislacionLocalServiceUtil;
import com.legalplus.liferay.portlet.web.calendario.constants.WebCalendarioPortletKeys;
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
                "javax.portlet.name=" + WebCalendarioPortletKeys.WEBCALENDARIO,
                "mvc.command.name=buscarEvaluacionesLegislacion"
        },
        service = MVCResourceCommand.class
)
public class FindEvaluacionesLegislacionResourceCommand extends BaseMVCResourceCommand {


    @Override
    protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
        HttpServletRequest httpReq = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(resourceRequest));
        String compId = httpReq.getParameter(WebCalendarioPortletKeys.COMPANY_ID);

        String resultados = EvalLegislacionLocalServiceUtil.getCalendarioEvalLegislaciones(Long.valueOf(compId));
        JSONObject json = JSONFactoryUtil.createJSONObject(resultados);

        resourceResponse.setContentType("application/json");
        resourceResponse.setCharacterEncoding("UTF-8");
        resourceResponse.getWriter().write(json.toString());
    }
}

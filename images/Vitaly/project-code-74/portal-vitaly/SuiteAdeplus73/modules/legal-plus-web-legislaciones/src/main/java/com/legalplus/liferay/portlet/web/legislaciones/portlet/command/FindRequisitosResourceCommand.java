package com.legalplus.liferay.portlet.web.legislaciones.portlet.command;

import com.legalplus.liferay.portlet.legalplus.manager.model.Requisito;
import com.legalplus.liferay.portlet.legalplus.manager.service.LegislacionCNAESLocalServiceUtil;
import com.legalplus.liferay.portlet.legalplus.manager.service.RequisitoLocalServiceUtil;
import com.legalplus.liferay.portlet.web.legislaciones.constants.WebLegislacionesPortletKeys;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.util.PortalUtil;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Component(
        immediate=true,
        property= {
                "javax.portlet.name=" + WebLegislacionesPortletKeys.WEBLEGISLACIONES,
                "mvc.command.name=buscarRequisitos"
        },
        service = MVCResourceCommand.class
)
public class FindRequisitosResourceCommand extends BaseMVCResourceCommand {

    public static final String PARAM_LEGISLACION = "legislacionId";
    public static final String PARAM_COMPID = "compId";

    @Override
    protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {

        HttpServletRequest httpReq = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(resourceRequest));

        List<Requisito> resultados = RequisitoLocalServiceUtil.findRequisitoActivoByLegislacionAndCompId(
                                                httpReq.getParameter(PARAM_LEGISLACION),
                                                Long.parseLong(httpReq.getParameter(PARAM_COMPID)));

        JSONObject json = JSONFactoryUtil.createJSONObject();
        JSONArray array = JSONFactoryUtil.createJSONArray(resultados);

        json.put("count", resultados.size());
        json.put("items", array);
        json.put("param", httpReq.getParameter(PARAM_LEGISLACION));

        resourceResponse.setContentType("application/json");
        resourceResponse.setCharacterEncoding("UTF-8");
        resourceResponse.getWriter().write(json.toString());

    }

}

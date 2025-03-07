package com.plan.igualdad.liferay.portlet.area.web.portlet.command;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.plan.igualdad.liferay.portlet.area.web.constants.PlanIgualdadAreaWebPortletKeys;
import com.plan.igualdad.liferay.portlet.manager.model.Area;
import com.plan.igualdad.liferay.portlet.manager.model.PuestoTrabajo;
import com.plan.igualdad.liferay.portlet.manager.service.AreaLocalServiceUtil;
import com.plan.igualdad.liferay.portlet.manager.service.PuestoTrabajoLocalServiceUtil;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Component(
        immediate=true,
        property= {
                "javax.portlet.name=" + PlanIgualdadAreaWebPortletKeys.PLANIGUALDADAREAWEB,
                "mvc.command.name=buscarAreas"
        },
        service = MVCResourceCommand.class
)
public class FindAreasResourceCommand extends BaseMVCResourceCommand {

    @Override
    protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {

        HttpServletRequest httpReq = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(resourceRequest));
        Long compId = Long.parseLong( httpReq.getParameter(PlanIgualdadAreaWebPortletKeys.COMPID_PARAM) );
        Long versionId = Long.parseLong( httpReq.getParameter(PlanIgualdadAreaWebPortletKeys.VERSIONID_PARAM) );

        JSONObject json = JSONFactoryUtil.createJSONObject();
        JSONArray array = JSONFactoryUtil.createJSONArray();

        List<Area> areas = AreaLocalServiceUtil.findByArea(compId, versionId);
        for (Area area : areas) {

            List<PuestoTrabajo> puestos = PuestoTrabajoLocalServiceUtil.findByPuestoArea(compId, versionId, area.getAreaId());
            Long nHombres = puestos.stream().mapToLong(puesto -> puesto.getNHombres()).sum();
            Long nMujeres = puestos.stream().mapToLong(puesto -> puesto.getNMujeres()).sum();

            JSONObject jsonArea = JSONFactoryUtil.createJSONObject();
            jsonArea.put(PlanIgualdadAreaWebPortletKeys.AREAID_PARAM, area.getAreaId());
            jsonArea.put(PlanIgualdadAreaWebPortletKeys.AREA_NOMBRE, area.getNombre());
            jsonArea.put(PlanIgualdadAreaWebPortletKeys.AREA_BAJA, Validator.isNotNull(area.getBaja()) ? area.getBaja() : StringPool.BLANK);
            jsonArea.put(PlanIgualdadAreaWebPortletKeys.AREA_NHOMBRES, nHombres);
            jsonArea.put(PlanIgualdadAreaWebPortletKeys.AREA_NMUJERES, nMujeres);
            jsonArea.put(PlanIgualdadAreaWebPortletKeys.AREA_NTOTAL, nHombres + nMujeres);

            array.put(jsonArea);

        }
        json.put(PlanIgualdadAreaWebPortletKeys.DATA_PARAM, array);

        resourceResponse.setContentType("application/json");
        resourceResponse.setCharacterEncoding("UTF-8");
        resourceResponse.getWriter().write(json.toString());

    }

}

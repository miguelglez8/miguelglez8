package com.plan.igualdad.liferay.portlet.puestos.web.portlet.command;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.plan.igualdad.liferay.portlet.manager.model.Area;
import com.plan.igualdad.liferay.portlet.manager.model.PuestoTrabajo;
import com.plan.igualdad.liferay.portlet.manager.service.AreaLocalServiceUtil;
import com.plan.igualdad.liferay.portlet.manager.service.PuestoTrabajoLocalServiceUtil;
import com.plan.igualdad.liferay.portlet.manager.service.persistence.AreaPK;
import com.plan.igualdad.liferay.portlet.puestos.web.constants.PlanIgualdadPuestosWebPortletKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Component(
        immediate=true,
        property= {
                "javax.portlet.name=" + PlanIgualdadPuestosWebPortletKeys.PLANIGUALDADPUESTOSWEB,
                "mvc.command.name=buscarPuestos"
        },
        service = MVCResourceCommand.class
)
public class FindPuestosResourceCommand extends BaseMVCResourceCommand {

    @Override
    protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {

        HttpServletRequest httpReq = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(resourceRequest));
        String compId = httpReq.getParameter(PlanIgualdadPuestosWebPortletKeys.COMPID_PARAM);
        String versionId = httpReq.getParameter(PlanIgualdadPuestosWebPortletKeys.VERSIONID_PARAM);

        JSONObject json = JSONFactoryUtil.createJSONObject();
        JSONArray array = JSONFactoryUtil.createJSONArray();

        List<PuestoTrabajo> puestos = PuestoTrabajoLocalServiceUtil.findPuestoTrabajo(Long.parseLong(compId), Long.parseLong(versionId));
        for (PuestoTrabajo puesto : puestos) {

            AreaPK areaPK = new AreaPK();
            areaPK.setCompId(Long.parseLong(compId));
            areaPK.setVersionId(Long.parseLong(versionId));
            areaPK.setAreaId(puesto.getAreaId());

            Area area = AreaLocalServiceUtil.getArea(areaPK);

            JSONObject jsonPuesto = JSONFactoryUtil.createJSONObject();
            jsonPuesto.put(PlanIgualdadPuestosWebPortletKeys.PUESTOID_PARAM, puesto.getPuestoId());
            jsonPuesto.put(PlanIgualdadPuestosWebPortletKeys.PUESTO_AREA, Validator.isNotNull(area) ? area.getNombre() : StringPool.BLANK);
            jsonPuesto.put(PlanIgualdadPuestosWebPortletKeys.PUESTO_NOMBRE, puesto.getNombre());
            jsonPuesto.put(PlanIgualdadPuestosWebPortletKeys.PUESTO_RESPONSABILIDAD, puesto.getResponsabilidad());
            jsonPuesto.put(PlanIgualdadPuestosWebPortletKeys.PUESTO_NHOMBRES, puesto.getNHombres());
            jsonPuesto.put(PlanIgualdadPuestosWebPortletKeys.PUESTO_NMUJERES, puesto.getNMujeres());
            jsonPuesto.put(PlanIgualdadPuestosWebPortletKeys.PUESTO_TOTAL, puesto.getNMujeres() + puesto.getNHombres());
            jsonPuesto.put(PlanIgualdadPuestosWebPortletKeys.PUESTO_BAJA, Validator.isNotNull(puesto.getBaja()) ? puesto.getBaja() : StringPool.BLANK);

            array.put(jsonPuesto);

        }

        json.put(PlanIgualdadPuestosWebPortletKeys.DATA_PARAM, array);

        resourceResponse.setContentType("application/json");
        resourceResponse.setCharacterEncoding("UTF-8");
        resourceResponse.getWriter().write(json.toString());

    }

}

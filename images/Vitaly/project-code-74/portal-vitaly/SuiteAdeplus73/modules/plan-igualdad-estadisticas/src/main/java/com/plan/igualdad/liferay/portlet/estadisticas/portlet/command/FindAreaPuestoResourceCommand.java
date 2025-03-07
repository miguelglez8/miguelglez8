package com.plan.igualdad.liferay.portlet.estadisticas.portlet.command;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.plan.igualdad.liferay.portlet.estadisticas.constants.PlanIgualdadEstadisticasPortletKeys;
import com.plan.igualdad.liferay.portlet.manager.model.Area;
import com.plan.igualdad.liferay.portlet.manager.model.Estadistica;
import com.plan.igualdad.liferay.portlet.manager.model.PuestoTrabajo;
import com.plan.igualdad.liferay.portlet.manager.service.AreaLocalServiceUtil;
import com.plan.igualdad.liferay.portlet.manager.service.EstadisticaLocalServiceUtil;
import com.plan.igualdad.liferay.portlet.manager.service.PuestoTrabajoLocalServiceUtil;
import com.plan.igualdad.liferay.portlet.manager.service.persistence.AreaPK;
import com.plan.igualdad.liferay.portlet.manager.service.persistence.EstadisticaPK;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Component(
        immediate=true,
        property= {
                "javax.portlet.name=" + PlanIgualdadEstadisticasPortletKeys.PLANIGUALDADESTADISTICAS,
                "mvc.command.name=findAreaPuesto"
        },
        service = MVCResourceCommand.class
)
public class FindAreaPuestoResourceCommand extends BaseMVCResourceCommand {

    @Override
    protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {

        Long compId = ParamUtil.getLong(resourceRequest, PlanIgualdadEstadisticasPortletKeys.COMPID_PARAM);
        Long versionId = ParamUtil.getLong(resourceRequest, PlanIgualdadEstadisticasPortletKeys.VERSIONID_PARAM);

        EstadisticaPK estadisticaPK = new EstadisticaPK();
        estadisticaPK.setCompId(compId);
        estadisticaPK.setVersionId(versionId);
        estadisticaPK.setSeccionId(PlanIgualdadEstadisticasPortletKeys.AREA_PUESTO);

        Estadistica estadistica = EstadisticaLocalServiceUtil.fetchEstadistica(estadisticaPK);

        JSONObject json = JSONFactoryUtil.createJSONObject();
        if (Validator.isNotNull(estadistica)) {
            json = JSONFactoryUtil.createJSONObject(estadistica.getEstadistica());
        }

        JSONArray array = JSONFactoryUtil.createJSONArray();

        List<PuestoTrabajo> puestos = PuestoTrabajoLocalServiceUtil.findPuestoTrabajoActivo(compId, versionId);
        for (PuestoTrabajo puesto : puestos) {

            AreaPK areaPK = new AreaPK();
            areaPK.setAreaId(puesto.getAreaId());
            areaPK.setCompId(compId);
            areaPK.setVersionId(versionId);

            Area area = AreaLocalServiceUtil.fetchArea(areaPK);

            long total = puesto.getNHombres() + puesto.getNMujeres();
            long pHombres = calculatePercentage(puesto.getNHombres(), total);
            long pMujeres = calculatePercentage(puesto.getNMujeres(), total);

            JSONObject puestoJson = JSONFactoryUtil.createJSONObject();
            puestoJson.put("area", area.getNombre());
            puestoJson.put("puesto", puesto.getNombre());
            puestoJson.put("nHombres", puesto.getNHombres());
            puestoJson.put("pHombres", pHombres + StringPool.PERCENT);
            puestoJson.put("nMujeres", puesto.getNMujeres());
            puestoJson.put("pMujeres", pMujeres + StringPool.PERCENT);
            puestoJson.put("personas", total);
            puestoJson.put("analisis", calculateAnalysis(pHombres, pMujeres));
            array.put(puestoJson);

        }

        List<Area> areas = AreaLocalServiceUtil.findByAreaActiva(compId, versionId);
        for (Area area: areas) {
                List<PuestoTrabajo> puestosArea = PuestoTrabajoLocalServiceUtil.findByPuestoArea(compId, versionId, area.getAreaId());
                //long nHombres = puestosArea.stream().mapToLong(puesto -> puesto.getNHombres()).sum();
                //long nMujeres = puestosArea.stream().mapToLong(puesto -> puesto.getNMujeres()).sum();

                long nHombres = puestosArea.stream().filter(puesto -> Validator.isNull( puesto.getBaja())).mapToLong(puesto -> puesto.getNHombres()).sum();
                long nMujeres = puestosArea.stream().filter(puesto -> Validator.isNull( puesto.getBaja())).mapToLong(puesto -> puesto.getNMujeres()).sum();
                long total = nHombres + nMujeres;

                long pHombres = total != 0 ? calculatePercentage(nHombres, total)  : 0;
                long pMujeres = total != 0 ? calculatePercentage(nMujeres, total) : 0;

                JSONObject areaJson = JSONFactoryUtil.createJSONObject();
                areaJson.put("area", area.getNombre());
                areaJson.put("puesto", "Total");
                areaJson.put("nHombres", nHombres);
                areaJson.put("pHombres", pHombres + StringPool.PERCENT);
                areaJson.put("nMujeres", nMujeres);
                areaJson.put("pMujeres", pMujeres + StringPool.PERCENT);
                areaJson.put("personas", total);
                areaJson.put("analisis", calculateAnalysis(pHombres, pMujeres));
                array.put(areaJson);

        }

        json.put("data", array);

        resourceResponse.setContentType("application/json");
        resourceResponse.setCharacterEncoding("UTF-8");
        resourceResponse.getWriter().write(json.toString());

    }

    private long calculatePercentage(long obtained, long total) {
        if(total == 0) return 0;
        float value = (float)obtained * 100 / total;
        long valueLong = obtained * 100 / total;
        if(value-valueLong>=0.5){
            valueLong++;

        };
        return valueLong;
    }

    private String calculateAnalysis(long pHombres, long pMujeres) {
    	if (pHombres == 100) return "<i>Masculinizado</i>";
        if (pMujeres == 100) return "<i>Feminizado</i>";
        if (pHombres >= 60) return "Masculinizado";
        if (pMujeres >= 60) return "Feminizado";
        return "Equilibrado";
    }

}



package com.plan.igualdad.liferay.portlet.estadisticas.portlet.command;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.plan.igualdad.liferay.portlet.estadisticas.constants.PlanIgualdadEstadisticasPortletKeys;
import com.plan.igualdad.liferay.portlet.manager.model.Estadistica;
import com.plan.igualdad.liferay.portlet.manager.model.PuestoTrabajo;
import com.plan.igualdad.liferay.portlet.manager.service.EstadisticaLocalServiceUtil;
import com.plan.igualdad.liferay.portlet.manager.service.PuestoTrabajoLocalServiceUtil;
import com.plan.igualdad.liferay.portlet.manager.service.persistence.EstadisticaPK;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import java.util.List;

@Component(
        immediate=true,
        property= {
                "javax.portlet.name=" + PlanIgualdadEstadisticasPortletKeys.PLANIGUALDADESTADISTICAS,
                "mvc.command.name=findFormacion"
        },
        service = MVCResourceCommand.class
)
public class FindFormacionResourceCommand extends BaseMVCResourceCommand {

    @Override
    protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {

        Long compId = ParamUtil.getLong(resourceRequest, PlanIgualdadEstadisticasPortletKeys.COMPID_PARAM);
        Long versionId = ParamUtil.getLong(resourceRequest, PlanIgualdadEstadisticasPortletKeys.VERSIONID_PARAM);

        EstadisticaPK estadisticaPK = new EstadisticaPK();
        estadisticaPK.setCompId(compId);
        estadisticaPK.setVersionId(versionId);
        estadisticaPK.setSeccionId(PlanIgualdadEstadisticasPortletKeys.FORMACION);

        Estadistica estadistica = EstadisticaLocalServiceUtil.fetchEstadistica(estadisticaPK);

        JSONObject json = JSONFactoryUtil.createJSONObject();
        if (Validator.isNotNull(estadistica)) {
            json = JSONFactoryUtil.createJSONObject(estadistica.getEstadistica());
        }
        else {

            JSONArray array = JSONFactoryUtil.createJSONArray();

            JSONObject situacionIltJSON = JSONFactoryUtil.createJSONObject();
            situacionIltJSON.put("formacion", "FIO");
            situacionIltJSON.put("hMujeres", 0);
            situacionIltJSON.put("hHombres", 0);
            situacionIltJSON.put("nMujeres", 0);
            situacionIltJSON.put("nHombres", 0);
            array.put(situacionIltJSON);

            json.put("data", array);
        }

        long totalHombres = 0;
        long totalMujeres = 0;

        List<PuestoTrabajo> puestos = PuestoTrabajoLocalServiceUtil.findPuestoTrabajo(compId, versionId);
        for (PuestoTrabajo puesto : puestos) {
            if(Validator.isNull(puesto.getBaja())) {
                totalHombres = totalHombres + puesto.getNHombres();
                totalMujeres = totalMujeres + puesto.getNMujeres();
            }
        }

        json.put("totalHombres", totalHombres);
        json.put("totalMujeres", totalMujeres);

        resourceResponse.setContentType("application/json");
        resourceResponse.setCharacterEncoding("UTF-8");
        resourceResponse.getWriter().write(json.toString());

    }

}

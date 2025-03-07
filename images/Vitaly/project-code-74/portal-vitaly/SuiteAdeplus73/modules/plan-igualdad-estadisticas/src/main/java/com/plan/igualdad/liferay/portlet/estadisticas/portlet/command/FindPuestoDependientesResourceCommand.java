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
                "mvc.command.name=findPuestoDependientes"
        },
        service = MVCResourceCommand.class
)
public class FindPuestoDependientesResourceCommand  extends BaseMVCResourceCommand {

    @Override
    protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {

        Long compId = ParamUtil.getLong(resourceRequest, PlanIgualdadEstadisticasPortletKeys.COMPID_PARAM);
        Long versionId = ParamUtil.getLong(resourceRequest, PlanIgualdadEstadisticasPortletKeys.VERSIONID_PARAM);

        EstadisticaPK estadisticaPK = new EstadisticaPK();
        estadisticaPK.setCompId(compId);
        estadisticaPK.setVersionId(versionId);
        estadisticaPK.setSeccionId(PlanIgualdadEstadisticasPortletKeys.PUESTO_DEPENDIENTES);

        Estadistica estadistica = EstadisticaLocalServiceUtil.fetchEstadistica(estadisticaPK);

        JSONObject json = JSONFactoryUtil.createJSONObject();
        if (Validator.isNotNull(estadistica)) {
            json = JSONFactoryUtil.createJSONObject(estadistica.getEstadistica());
        }

        JSONArray array = JSONFactoryUtil.createJSONArray();

        long totalHombres = 0;
        long totalMujeres = 0;
        long nHombresResponsabilidad = 0;
        long nMujeresResponsabilidad = 0;

        List<PuestoTrabajo> puestos = PuestoTrabajoLocalServiceUtil.findPuestoTrabajoActivo(compId, versionId);
        for (PuestoTrabajo puesto : puestos) {

            JSONObject puestoJSON = JSONFactoryUtil.createJSONObject();
            puestoJSON.put("hombres", puesto.getNHombres());
            puestoJSON.put("mujeres", puesto.getNMujeres());
            puestoJSON.put("total", puesto.getNHombres() + puesto.getNMujeres());
            puestoJSON.put("puesto", puesto.getPuestoId());
            puestoJSON.put("responsabilidad", puesto.getResponsabilidad());
            array.put(puestoJSON);

            if (puesto.getResponsabilidad() == 0) {
                nHombresResponsabilidad = nHombresResponsabilidad + puesto.getNHombres();
                nMujeresResponsabilidad = nMujeresResponsabilidad + puesto.getNMujeres();
            }


            totalHombres = totalHombres + puesto.getNHombres();
            totalMujeres = totalMujeres + puesto.getNMujeres();

        }

        json.put("data", array);
        json.put("totalHombres", totalHombres);
        json.put("totalMujeres", totalMujeres);
        json.put("nHombresResponsabilidad", nHombresResponsabilidad);
        json.put("nMujeresResponsabilidad", nMujeresResponsabilidad);

        resourceResponse.setContentType("application/json");
        resourceResponse.setCharacterEncoding("UTF-8");
        resourceResponse.getWriter().write(json.toString());

    }

}

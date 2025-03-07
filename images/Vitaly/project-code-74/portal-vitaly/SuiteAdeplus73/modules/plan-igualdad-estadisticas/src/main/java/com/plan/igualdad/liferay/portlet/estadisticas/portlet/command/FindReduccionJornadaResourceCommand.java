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
                "mvc.command.name=findReduccionJornada"
        },
        service = MVCResourceCommand.class
)
public class FindReduccionJornadaResourceCommand extends BaseMVCResourceCommand {

    @Override
    protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {

        Long compId = ParamUtil.getLong(resourceRequest, PlanIgualdadEstadisticasPortletKeys.COMPID_PARAM);
        Long versionId = ParamUtil.getLong(resourceRequest, PlanIgualdadEstadisticasPortletKeys.VERSIONID_PARAM);

        EstadisticaPK estadisticaPK = new EstadisticaPK();
        estadisticaPK.setCompId(compId);
        estadisticaPK.setVersionId(versionId);
        estadisticaPK.setSeccionId(PlanIgualdadEstadisticasPortletKeys.REDUCCION_JORNADA);

        Estadistica estadistica = EstadisticaLocalServiceUtil.fetchEstadistica(estadisticaPK);

        long totalHombres = 0;
        long totalMujeres = 0;

        List<PuestoTrabajo> puestos = PuestoTrabajoLocalServiceUtil.findPuestoTrabajoActivo(compId, versionId);
        for (PuestoTrabajo puesto : puestos) {
            totalHombres = totalHombres + puesto.getNHombres();
            totalMujeres = totalMujeres + puesto.getNMujeres();
        }

        JSONObject json = JSONFactoryUtil.createJSONObject();
        
        JSONArray array = JSONFactoryUtil.createJSONArray();
        if (Validator.isNotNull(estadistica)) {
            json = JSONFactoryUtil.createJSONObject(estadistica.getEstadistica());
            
            for (PuestoTrabajo puesto : puestos) {
            	Boolean exist = Boolean.FALSE;

            	for(int i =0;i<json.getJSONArray("data").length(); i++) {
                	JSONObject puestoEstudioJSON = json.getJSONArray("data").getJSONObject(i);
                    try {
                        if (puesto.getPuestoId() == Long.parseLong(puestoEstudioJSON.getString("puesto"))) {
                            array.put(puestoEstudioJSON);
                            exist = Boolean.TRUE;
                        }
                    }catch(NumberFormatException e){

                    }
                }
            	
            	if(!exist) {
            		JSONObject puestoEstudioJSON = JSONFactoryUtil.createJSONObject();
                    puestoEstudioJSON.put("puesto", puesto.getPuestoId());
                    puestoEstudioJSON.put("hombres", 0);
                    puestoEstudioJSON.put("mujeres", 0);

                    array.put(puestoEstudioJSON);
            	}
            }
        } else {
            for (PuestoTrabajo puesto : puestos) {
                JSONObject puestoEstudioJSON = JSONFactoryUtil.createJSONObject();
                puestoEstudioJSON.put("puesto", puesto.getPuestoId());
                puestoEstudioJSON.put("hombres", 0);
                puestoEstudioJSON.put("mujeres", 0);

                array.put(puestoEstudioJSON);
            }
        }
        
        json.put("data", array);

        json.put("totalHombres", totalHombres);
        json.put("totalMujeres", totalMujeres);

        resourceResponse.setContentType("application/json");
        resourceResponse.setCharacterEncoding("UTF-8");
        resourceResponse.getWriter().write(json.toString());
    }
}

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
                "mvc.command.name=findPuestoEstudios"
        },
        service = MVCResourceCommand.class
)
public class FindPuestoEstudiosResourceCommand extends BaseMVCResourceCommand {

    @Override
    protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {

        Long compId = ParamUtil.getLong(resourceRequest, PlanIgualdadEstadisticasPortletKeys.COMPID_PARAM);
        Long versionId = ParamUtil.getLong(resourceRequest, PlanIgualdadEstadisticasPortletKeys.VERSIONID_PARAM);
        String[] estudios = {"00", "01", "02", "03", "04", "05", "06"};
        
        EstadisticaPK estadisticaPK = new EstadisticaPK();
        estadisticaPK.setCompId(compId);
        estadisticaPK.setVersionId(versionId);
        estadisticaPK.setSeccionId(PlanIgualdadEstadisticasPortletKeys.PUESTO_ESTUDIOS);

        Estadistica estadistica = EstadisticaLocalServiceUtil.fetchEstadistica(estadisticaPK);

        JSONObject json = JSONFactoryUtil.createJSONObject();
        if (Validator.isNotNull(estadistica)) {
            json = JSONFactoryUtil.createJSONObject(estadistica.getEstadistica());
            JSONArray array = JSONFactoryUtil.createJSONArray();
            List<PuestoTrabajo> puestos = PuestoTrabajoLocalServiceUtil.findPuestoTrabajoActivo(compId, versionId);
            
            for (PuestoTrabajo puesto : puestos) {
            	Boolean exist = Boolean.FALSE;
            
            	for(int i =0;i<json.getJSONArray("data").length(); i++) {
                	JSONObject puestoJson = json.getJSONArray("data").getJSONObject(i);
                    try {
                        if (puesto.getPuestoId() == Long.parseLong(puestoJson.getString("puesto"))) {
                            array.put(puestoJson);
                            exist = Boolean.TRUE;
                        }
                    }catch(NumberFormatException e){

                    }
                }
            	
            	if(!exist) {
            		for (String estudio : estudios) {

                        JSONObject puestoEstudioJSON = JSONFactoryUtil.createJSONObject();
                        puestoEstudioJSON.put("id", estudio);
                        puestoEstudioJSON.put("hombres", 0);
                        puestoEstudioJSON.put("mujeres", 0);
                        puestoEstudioJSON.put("puesto", puesto.getPuestoId());

                        array.put(puestoEstudioJSON);
                    }
            	}
            }
            json.remove("data");
            json.put("data", array);
        } else {

            JSONArray array = JSONFactoryUtil.createJSONArray();

            List<PuestoTrabajo> puestos = PuestoTrabajoLocalServiceUtil.findPuestoTrabajoActivo(compId, versionId);

            for (PuestoTrabajo puesto : puestos) {
                for (String estudio : estudios) {

                    JSONObject puestoEstudioJSON = JSONFactoryUtil.createJSONObject();
                    puestoEstudioJSON.put("id", estudio);
                    puestoEstudioJSON.put("hombres", 0);
                    puestoEstudioJSON.put("mujeres", 0);
                    puestoEstudioJSON.put("puesto", puesto.getPuestoId());

                    array.put(puestoEstudioJSON);
                }
            }

            json.put("data", array);

        }

        resourceResponse.setContentType("application/json");
        resourceResponse.setCharacterEncoding("UTF-8");
        resourceResponse.getWriter().write(json.toString());

    }

}

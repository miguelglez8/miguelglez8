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
                "mvc.command.name=findPuestoAntiguedad"
        },
        service = MVCResourceCommand.class
)
public class FindPuestoAntiguedadResourceCommand extends BaseMVCResourceCommand {

    @Override
    protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {

        Long compId = ParamUtil.getLong(resourceRequest, PlanIgualdadEstadisticasPortletKeys.COMPID_PARAM);
        Long versionId = ParamUtil.getLong(resourceRequest, PlanIgualdadEstadisticasPortletKeys.VERSIONID_PARAM);

        String[] antiguedades = {"0001", "0103", "0305", "0510", "1015", "1500"};
        
        EstadisticaPK estadisticaPK = new EstadisticaPK();
        estadisticaPK.setCompId(compId);
        estadisticaPK.setVersionId(versionId);
        estadisticaPK.setSeccionId(PlanIgualdadEstadisticasPortletKeys.PUESTO_ANTIGUEDAD);

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
            		for (String antiguedad : antiguedades) {
                        JSONObject puestoAntiguedadJSON = JSONFactoryUtil.createJSONObject();
                        puestoAntiguedadJSON.put("id", antiguedad);
                        puestoAntiguedadJSON.put("hombres", 0);
                        puestoAntiguedadJSON.put("hombresR", 0);
                        puestoAntiguedadJSON.put("mujeres", 0);
                        puestoAntiguedadJSON.put("mujeresR", 0);
                        puestoAntiguedadJSON.put("puesto", puesto.getPuestoId());

                        array.put(puestoAntiguedadJSON);
                    }
            	}
            }
            json.remove("data");
            json.put("data", array);
        } else {
            JSONArray array = JSONFactoryUtil.createJSONArray();

            List<PuestoTrabajo> puestos = PuestoTrabajoLocalServiceUtil.findPuestoTrabajoActivo(compId, versionId);

            for (PuestoTrabajo puesto : puestos) {
                for (String antiguedad : antiguedades) {

                    JSONObject puestoAntiguedadJSON = JSONFactoryUtil.createJSONObject();
                    puestoAntiguedadJSON.put("id", antiguedad);
                    puestoAntiguedadJSON.put("hombres", 0);
                    puestoAntiguedadJSON.put("hombresR", 0);
                    puestoAntiguedadJSON.put("mujeres", 0);
                    puestoAntiguedadJSON.put("mujeresR", 0);
                    puestoAntiguedadJSON.put("puesto", puesto.getPuestoId());

                    array.put(puestoAntiguedadJSON);
                }
            }

            json.put("data", array);

        }

        resourceResponse.setContentType("application/json");
        resourceResponse.setCharacterEncoding("UTF-8");
        resourceResponse.getWriter().write(json.toString());

    }

}

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
                "mvc.command.name=findPuestoEdad"
        },
        service = MVCResourceCommand.class
)
public class FindPuestoEdadResourceCommand  extends BaseMVCResourceCommand {

    @Override
    protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {

        Long compId = ParamUtil.getLong(resourceRequest, PlanIgualdadEstadisticasPortletKeys.COMPID_PARAM);
        Long versionId = ParamUtil.getLong(resourceRequest, PlanIgualdadEstadisticasPortletKeys.VERSIONID_PARAM);

        EstadisticaPK estadisticaPK = new EstadisticaPK();
        estadisticaPK.setCompId(compId);
        estadisticaPK.setVersionId(versionId);
        estadisticaPK.setSeccionId(PlanIgualdadEstadisticasPortletKeys.PUESTO_EDAD);

        Estadistica estadistica = EstadisticaLocalServiceUtil.fetchEstadistica(estadisticaPK);

        JSONObject json = JSONFactoryUtil.createJSONObject();
        String[] edades = {"0020", "2025", "2530", "3035", "3540", "4045", "4550", "5055", "5560", "6000"};

        if (Validator.isNotNull(estadistica)) {
            json = JSONFactoryUtil.createJSONObject(estadistica.getEstadistica());

            JSONArray array = JSONFactoryUtil.createJSONArray();
            
            List<PuestoTrabajo> puestos = PuestoTrabajoLocalServiceUtil.findByPuestoResponsabilidad(compId, versionId);
            
            if(Validator.isNotNull(puestos) && !puestos.isEmpty()) {
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
                		for (String edad : edades) {

                            JSONObject puestoEdadJSON = JSONFactoryUtil.createJSONObject();
                            puestoEdadJSON.put("id", edad);
                            puestoEdadJSON.put("hombres", 0);
                            puestoEdadJSON.put("mujeres", 0);
                            puestoEdadJSON.put("puesto", puesto.getPuestoId());
                            array.put(puestoEdadJSON);
                		}
                	}
                }
            }
            
            json.remove("data");
            json.put("data", array);
        } else {

            JSONArray array = JSONFactoryUtil.createJSONArray();

            List<PuestoTrabajo> puestos = PuestoTrabajoLocalServiceUtil.findByPuestoResponsabilidad(compId, versionId);

            for (PuestoTrabajo puesto : puestos) {
                for (String edad : edades) {

                    JSONObject puestoEdadJSON = JSONFactoryUtil.createJSONObject();
                    puestoEdadJSON.put("id", edad);
                    puestoEdadJSON.put("hombres", 0);
                    puestoEdadJSON.put("mujeres", 0);
                    puestoEdadJSON.put("puesto", puesto.getPuestoId());

                    array.put(puestoEdadJSON);
                }
            }

            json.put("data", array);

        }

        resourceResponse.setContentType("application/json");
        resourceResponse.setCharacterEncoding("UTF-8");
        resourceResponse.getWriter().write(json.toString());


    }

}

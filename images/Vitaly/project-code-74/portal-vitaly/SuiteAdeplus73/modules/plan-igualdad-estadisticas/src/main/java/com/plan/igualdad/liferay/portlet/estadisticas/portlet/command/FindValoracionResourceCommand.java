package com.plan.igualdad.liferay.portlet.estadisticas.portlet.command;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
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
import java.util.List;

@Component(
        immediate=true,
        property= {
                "javax.portlet.name=" + PlanIgualdadEstadisticasPortletKeys.PLANIGUALDADESTADISTICAS,
                "mvc.command.name=findValoracion"
        },
        service = MVCResourceCommand.class
)
public class FindValoracionResourceCommand extends BaseMVCResourceCommand {
    private static Log _log = LogFactoryUtil.getLog(FindValoracionResourceCommand.class);
    @Override
    protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {

        Long compId = ParamUtil.getLong(resourceRequest, PlanIgualdadEstadisticasPortletKeys.COMPID_PARAM);
        Long versionId = ParamUtil.getLong(resourceRequest, PlanIgualdadEstadisticasPortletKeys.VERSIONID_PARAM);

        EstadisticaPK estadisticaPK = new EstadisticaPK();
        estadisticaPK.setCompId(compId);
        estadisticaPK.setVersionId(versionId);
        estadisticaPK.setSeccionId(PlanIgualdadEstadisticasPortletKeys.VALORACION);

        Estadistica estadistica = EstadisticaLocalServiceUtil.fetchEstadistica(estadisticaPK);

        JSONObject json = JSONFactoryUtil.createJSONObject();
        if (Validator.isNotNull(estadistica)) {
            json = JSONFactoryUtil.createJSONObject(estadistica.getEstadistica());
            
            JSONArray arrayValoraciones = JSONFactoryUtil.createJSONArray();
            JSONArray arrayDistribuciones = JSONFactoryUtil.createJSONArray();
            
            List<PuestoTrabajo> puestos = PuestoTrabajoLocalServiceUtil.findPuestoTrabajoActivo(compId, versionId);
            if(Validator.isNotNull(puestos) && !puestos.isEmpty()) {
            	for (PuestoTrabajo puesto : puestos) {
            		Boolean existValorado = Boolean.FALSE;
            		Boolean existDistribucion = Boolean.FALSE;
            		
            		AreaPK areaPK = new AreaPK();
                    areaPK.setAreaId(puesto.getAreaId());
                    areaPK.setCompId(compId);
                    areaPK.setVersionId(versionId);
                    Area area = AreaLocalServiceUtil.fetchArea(areaPK);
            		for(int i =0;i<json.getJSONArray("valoraciones").length(); i++) {
                    	JSONObject valoracionJson = json.getJSONArray("valoraciones").getJSONObject(i);
                    	try{
                            if(puesto.getPuestoId()==Long.parseLong(valoracionJson.getString("puesto"))) {
                                JSONObject valoracionJson_ = JSONFactoryUtil.createJSONObject();
                                valoracionJson_.put("puesto", valoracionJson.getString("puesto"));
                                valoracionJson_.put("convenio", valoracionJson.getString("convenio"));
                                valoracionJson_.put("area", area.getNombre());
                                valoracionJson_.put("departamento", valoracionJson.getString("departamento"));
                                valoracionJson_.put("centro", valoracionJson.getString("centro"));

                                arrayValoraciones.put(valoracionJson_);
                                existValorado = Boolean.TRUE;
                            }
                        }
                        catch(NumberFormatException e){

                        }
                    }
            		
            		if(!existValorado) {        
                        JSONObject valoracionJson = JSONFactoryUtil.createJSONObject();
            			valoracionJson.put("puesto", puesto.getPuestoId());
                        valoracionJson.put("convenio", "");
                        valoracionJson.put("area", area.getNombre());
                        valoracionJson.put("departamento", "");
                        valoracionJson.put("centro", "");
                        arrayValoraciones.put(valoracionJson);
            		}
            		
        			JSONObject distribucionJson = JSONFactoryUtil.createJSONObject();
        			long total = puesto.getNHombres() + puesto.getNMujeres();
                    long pHombres = calculatePercentage(puesto.getNHombres(), total);
                    long pMujeres = calculatePercentage(puesto.getNMujeres(), total);
                    
                    distribucionJson.put("puesto", puesto.getPuestoId());
                    distribucionJson.put("categoria", calculateAnalysis(pHombres, pMujeres));
                    distribucionJson.put("nHombres", puesto.getNHombres());
                    distribucionJson.put("nMujeres", puesto.getNMujeres());
                    for(int i =0;i<json.getJSONArray("distribuciones").length(); i++) {
                    	JSONObject jsonExist = json.getJSONArray("distribuciones").getJSONObject(i);
                        try {
                            if (puesto.getPuestoId() == Long.parseLong(jsonExist.getString("puesto"))) {
                                distribucionJson.put("agrupacion", jsonExist.getString("agrupacion"));
                                distribucionJson.put("puntos", jsonExist.getString("puntos"));
                                existDistribucion = Boolean.TRUE;
                            }
                        }catch(NumberFormatException e){

                        }
                    }
            		
            		if(!existDistribucion) {
                        distribucionJson.put("agrupacion", "");
                        distribucionJson.put("puntos", 0);
            		}
            		arrayDistribuciones.put(distribucionJson);
                }
            }

            json.put("valoraciones", arrayValoraciones);
            json.put("distribuciones", arrayDistribuciones);
            
        }else {

            JSONArray arrayValoraciones = JSONFactoryUtil.createJSONArray();
            JSONArray arrayDistribuciones = JSONFactoryUtil.createJSONArray();

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

                JSONObject valoracionJson = JSONFactoryUtil.createJSONObject();
                valoracionJson.put("puesto", puesto.getPuestoId());
                valoracionJson.put("convenio", "");
                valoracionJson.put("area", area.getNombre());
                valoracionJson.put("departamento", "");
                valoracionJson.put("centro", "");

                JSONObject distribucionJson = JSONFactoryUtil.createJSONObject();
                distribucionJson.put("puesto", puesto.getPuestoId());
                distribucionJson.put("categoria", calculateAnalysis(pHombres, pMujeres));
                distribucionJson.put("agrupacion", "");
                distribucionJson.put("puntos", 0);
                distribucionJson.put("nHombres", puesto.getNHombres());
                distribucionJson.put("nMujeres", puesto.getNMujeres());

                arrayValoraciones.put(valoracionJson);
                arrayDistribuciones.put(distribucionJson);

            }

            json.put("valoraciones", arrayValoraciones);
            json.put("distribuciones", arrayDistribuciones);

        }

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
        if (pHombres >= 60) return "M";
        if (pMujeres >= 60) return "F";
        return "E";
    }

}

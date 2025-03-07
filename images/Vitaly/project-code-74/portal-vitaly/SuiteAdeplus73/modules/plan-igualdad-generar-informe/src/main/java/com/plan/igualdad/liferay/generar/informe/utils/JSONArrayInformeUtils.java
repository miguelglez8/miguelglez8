package com.plan.igualdad.liferay.generar.informe.utils;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.Validator;
import com.plan.igualdad.liferay.generar.informe.dto.MedidaDTO;
import com.plan.igualdad.liferay.portlet.manager.model.Area;
import com.plan.igualdad.liferay.portlet.manager.model.Estadistica;
import com.plan.igualdad.liferay.portlet.manager.model.PuestoTrabajo;
import com.plan.igualdad.liferay.portlet.manager.service.AreaLocalServiceUtil;
import com.plan.igualdad.liferay.portlet.manager.service.PuestoTrabajoLocalServiceUtil;
import com.plan.igualdad.liferay.portlet.manager.service.persistence.AreaPK;
import com.plan.igualdad.liferay.portlet.manager.service.persistence.PuestoTrabajoPK;
import org.apache.commons.lang3.math.NumberUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class JSONArrayInformeUtils {

	public static JSONArray getMedidasList(List<MedidaDTO> medidaList) {
		JSONArray array = JSONFactoryUtil.createJSONArray();
		
		for(MedidaDTO medida:  medidaList) {
			JSONObject objectJSON = JSONFactoryUtil.createJSONObject();
			
			if(!medida.getNoMedida()) {
				objectJSON.put("descripcion", medida.getDescripcion());
				objectJSON.put("indicadores", medida.getIndicadores());
				objectJSON.put("materia", medida.getMateria());
				objectJSON.put("medidaNombre", medida.getNombreMedida());
				objectJSON.put("objetivos", medida.getObjetivos());
				objectJSON.put("prioridad", medida.getPrioridad());
				objectJSON.put("recursos", medida.getRecursos());
				objectJSON.put("recurrente", medida.getRecurrente());
				objectJSON.put("responsable", medida.getResponsable());
				objectJSON.put("fechaImplantacion", medida.getFechaImplantacion());
				objectJSON.put("fechaSeguimiento", medida.getFechaSeguimiento());
				objectJSON.put("nivelEjecucion", medida.getNivelEjecucion());
				objectJSON.put("motivo", medida.getMotivo());
				objectJSON.put("otrosMotivos", medida.getOtrosMotivos());
				objectJSON.put("recursosAsociados", medida.getRecursosAsociados());
				objectJSON.put("dificultadesEncontradas", medida.getDificultadesEncontradas());
				objectJSON.put("solucionesAdoptadas", medida.getSolucionesAdoptadas());
				objectJSON.put("reduccionDesigualdades", medida.getReduccionDesigualdades());
				objectJSON.put("mejorasProducidas", medida.getMejorasProducidas());
				objectJSON.put("propuestasFuturo", medida.getPropuestasFuturo());
				objectJSON.put("documentacionAcreditativa", medida.getDocumentacionAcreditativa());
				objectJSON.put("fechaSubidaDocumento", medida.getFechaSubidaDocumento());

			}
			objectJSON.put("noMedida", medida.getNoMedida());
			
			array.put(objectJSON);
		}
		
		return array;
	}
	/**
	 * 
	 * @param compId
	 * @param versionId
	 * @return
	 * @throws PortalException
	 */
	public static JSONArray getAreasPuestosEstadisticas(long compId, long versionId) throws PortalException {
		JSONArray array = JSONFactoryUtil.createJSONArray();
		
		List<Area> areas = AreaLocalServiceUtil.findByAreaActiva(compId, versionId);
//		List<Area> areas = AreaLocalServiceUtil.findByArea(compId, versionId);
		
		if(Validator.isNotNull(areas)) {
			for(Area area: areas) {
				List<PuestoTrabajo> puestos = PuestoTrabajoLocalServiceUtil.findByPuestoArea(compId, versionId, area.getAreaId());
				if(Validator.isNotNull(puestos)) {
					int cont=0;
					for(PuestoTrabajo puesto: puestos) {
						cont++;
						if(Validator.isNull(puesto.getBaja()) || new Date().before(puesto.getBaja())) {
							long total = puesto.getNHombres() + puesto.getNMujeres();
							float pHombres = total != 0 ? calculatePercentage(puesto.getNHombres(), total): 0;
				            float pMujeres = total != 0 ? calculatePercentage(puesto.getNMujeres(), total): 0;
							
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
					}

					if(puestos.size()==cont) {
						List<PuestoTrabajo> puestosArea = PuestoTrabajoLocalServiceUtil.findByPuestoArea(compId, versionId,area.getAreaId());

						AreaPK areaPK = new AreaPK();
			            areaPK.setAreaId(area.getAreaId());
			            areaPK.setCompId(compId);
			            areaPK.setVersionId(versionId);
						Area area_ = AreaLocalServiceUtil.getArea(areaPK);
						
						long nHombres_ = puestosArea.stream().mapToLong(puesto_ -> puesto_.getNHombres()).sum();
						long nMujeres_ = puestosArea.stream().mapToLong(puesto_ -> puesto_.getNMujeres()).sum();
						long total_ = nHombres_ + nMujeres_;
                        float pHombres_ = total_ != 0 ? calculatePercentage(nHombres_, total_)  : 0;
                        float pMujeres_ = total_ != 0 ? calculatePercentage(nMujeres_, total_) : 0;
                        
                        JSONObject areaJson = JSONFactoryUtil.createJSONObject();
                        areaJson.put("area", area_.getNombre());
                        areaJson.put("puesto", "Total");
                        areaJson.put("nHombres", nHombres_);
                        areaJson.put("pHombres", pHombres_ + StringPool.PERCENT);
                        areaJson.put("nMujeres", nMujeres_);
                        areaJson.put("pMujeres", pMujeres_ + StringPool.PERCENT);
                        areaJson.put("personas", total_);
                        areaJson.put("analisis", calculateAnalysis(pHombres_, pMujeres_));
                        array.put(areaJson);
					}
				}
				
			}
		}
        
        return array;
	}
	
	/**
	 * 
	 * @param compId
	 * @param versionId
	 * @param bundle
	 * @return
	 * @throws PortalException
	 */
	public static JSONArray getPuestosAntiguedadEstadisticas(long compId, long versionId, ResourceBundle bundle, Estadistica estadistica) throws PortalException {
		JSONArray array = JSONFactoryUtil.createJSONArray();

		if(Validator.isNotNull(estadistica)) {
	    	JSONObject json = JSONFactoryUtil.createJSONObject(estadistica.getEstadistica());
	    	if(Validator.isNotNull(json)) {
	    		JSONArray arrayEstadistica = json.getJSONArray("data");
	    		String lastPuesto = StringPool.BLANK;
	    		Boolean saveTotal = Boolean.FALSE;
	    		List<JSONObject> listTotal = new ArrayList<>();
	    		if(Validator.isNotNull(arrayEstadistica)) {
	    			for(int i=0 ; i<arrayEstadistica.length() ; i++) {
	    				JSONObject objectEstadistica = arrayEstadistica.getJSONObject(i);
	    			
	    				if(i==0) {
	    					lastPuesto = objectEstadistica.getString("puesto");
	    				}
	    				String antiguedad = "Total";
	            		
	            		if (objectEstadistica.getString("id").equals("0001")) antiguedad = LanguageUtil.get(bundle, "estadisticas.puestoAntiguedad.0001");
	            	    if (objectEstadistica.getString("id").equals("0103")) antiguedad = LanguageUtil.get(bundle, "estadisticas.puestoAntiguedad.0103");
	            	    if (objectEstadistica.getString("id").equals("0305")) antiguedad = LanguageUtil.get(bundle, "estadisticas.puestoAntiguedad.0305");
	            	    if (objectEstadistica.getString("id").equals("0510")) antiguedad = LanguageUtil.get(bundle, "estadisticas.puestoAntiguedad.0510");
	            	    if (objectEstadistica.getString("id").equals("1015")) antiguedad = LanguageUtil.get(bundle, "estadisticas.puestoAntiguedad.1015");
	            	    if (objectEstadistica.getString("id").equals("1500")) antiguedad = LanguageUtil.get(bundle, "estadisticas.puestoAntiguedad.1500");

	            	    float pHombres = calculatePercentage(Long.parseLong(objectEstadistica.getString("hombresR")), Long.parseLong(objectEstadistica.getString("hombres")));
	            	    float pMujeres = calculatePercentage(Long.parseLong(objectEstadistica.getString("mujeresR")), Long.parseLong(objectEstadistica.getString("mujeres")));
	            	    
	            		JSONObject puestoAntiguedadJSON = JSONFactoryUtil.createJSONObject();
	                    puestoAntiguedadJSON.put("rangoEdad", antiguedad);
	                    puestoAntiguedadJSON.put("hombres", objectEstadistica.getString("hombres"));
	                    puestoAntiguedadJSON.put("hombresR", objectEstadistica.getString("hombresR"));
	                    puestoAntiguedadJSON.put("pHombres", pHombres + StringPool.PERCENT);
	                    puestoAntiguedadJSON.put("mujeres", objectEstadistica.getString("mujeres"));
	                    puestoAntiguedadJSON.put("mujeresR", objectEstadistica.getString("mujeresR"));
	                    puestoAntiguedadJSON.put("pMujeres", pMujeres + StringPool.PERCENT);
	                   
	                    PuestoTrabajoPK puestoPK = new PuestoTrabajoPK();
	                    puestoPK.setCompId(compId);
	                    puestoPK.setVersionId(versionId);
	                    puestoPK.setPuestoId(Long.parseLong(objectEstadistica.getString("puesto")));
	                    
	                    PuestoTrabajo puesto= PuestoTrabajoLocalServiceUtil.fetchPuestoTrabajo(puestoPK);
	                    
	                    puestoAntiguedadJSON.put("puesto", puesto.getNombre());
	                    
	                    if(i>0 && !lastPuesto.equals(objectEstadistica.getString("puesto"))) saveTotal = Boolean.TRUE;
	                    
	                    if(saveTotal || arrayEstadistica.length() == i) {

	                        array.put(jsonObjectTotalPuestoAntiguedad(listTotal));
	                    	saveTotal = Boolean.FALSE;
	                    	listTotal.clear();
	                    	
	                    	lastPuesto = objectEstadistica.getString("puesto");
	                    }

	                    listTotal.add(puestoAntiguedadJSON);

	                    array.put(puestoAntiguedadJSON);
	    			}
	    			array.put(jsonObjectTotalPuestoAntiguedad(listTotal));
	    		}
	    	}
		}
		
		return array;
	}
	
	/**
	 * 
	 * @param compId
	 * @param versionId
	 * @param bundle
	 * @return
	 * @throws PortalException
	 */
	public static JSONArray getHorarioTurnosEstadisticas(long compId, long versionId, ResourceBundle bundle, Estadistica estadistica) throws PortalException {
		JSONArray array = JSONFactoryUtil.createJSONArray();

		if(Validator.isNotNull(estadistica)) {
	    	JSONObject json = JSONFactoryUtil.createJSONObject(estadistica.getEstadistica());
	    	if(Validator.isNotNull(json)) {
	    		JSONArray arrayEstadistica = json.getJSONArray("data");
	    		String lastHorarioTurno = StringPool.BLANK;
	    		Boolean saveTotal = Boolean.FALSE;
	    		List<JSONObject> listTotal = new ArrayList<>();
	
	    		long totalTotal = 0L;
	    		
	    		if(Validator.isNotNull(arrayEstadistica)) {
	    			for(int i=0 ; i<arrayEstadistica.length() ; i++) {
	    				JSONObject objectEstadistica = arrayEstadistica.getJSONObject(i);
	    				long nHombres = Long.parseLong(objectEstadistica.getString("hombres"));
	             	    long nMujeres = Long.parseLong(objectEstadistica.getString("mujeres"));
	    			
	             	    totalTotal =totalTotal+nHombres + nMujeres;
	    			}
	    			for(int i=0 ; i<arrayEstadistica.length() ; i++) {
	    				JSONObject objectEstadistica = arrayEstadistica.getJSONObject(i);
	    				if(i==0) {
	    					lastHorarioTurno = objectEstadistica.getString("jornada");
	    				}
	    				
	    				String jornada = StringPool.BLANK;
	            		if (objectEstadistica.getString("jornada").equals("CON")) jornada = LanguageUtil.get(bundle, "estadisticas.horariosTurnos.CON");
	            	    if (objectEstadistica.getString("jornada").equals("PAR")) jornada = LanguageUtil.get(bundle, "estadisticas.horariosTurnos.PAR");
	            	    
	            	    String turno = StringPool.BLANK;
	            		if (objectEstadistica.getString("turno").equals("CTUR")) turno = LanguageUtil.get(bundle, "estadisticas.horariosTurnos.CTUR");
	            	    if (objectEstadistica.getString("turno").equals("STUR")) turno = LanguageUtil.get(bundle, "estadisticas.horariosTurnos.STUR");
	            	    
	            	    long nHombres = Long.parseLong(objectEstadistica.getString("hombres"));
	            	    long nMujeres = Long.parseLong(objectEstadistica.getString("mujeres"));
	            	    long total = nHombres + nMujeres;
	            	    float nHombresPorc = calculatePercentage(nHombres, totalTotal);
	            	    float nMujeresPorc = calculatePercentage(nMujeres, totalTotal);
	            	    float totalPorc = calculatePercentage(total, totalTotal);
	
	            	    JSONObject puestoAntiguedadJSON = JSONFactoryUtil.createJSONObject();
	            	    puestoAntiguedadJSON.put("horario", jornada);
	            	    puestoAntiguedadJSON.put("turno", turno);
	            	    puestoAntiguedadJSON.put("nHombres", nHombres);
	            	    puestoAntiguedadJSON.put("porcHombres", nHombresPorc+ StringPool.PERCENT);
	            	    puestoAntiguedadJSON.put("nMujeres", nMujeres);
	            	    puestoAntiguedadJSON.put("porcMujeres", nMujeresPorc+ StringPool.PERCENT);
	            	    puestoAntiguedadJSON.put("total", total);
	            	    puestoAntiguedadJSON.put("porcTotal", totalPorc+ StringPool.PERCENT);
	            	    
	            	    if(i>0 && !lastHorarioTurno.equals(objectEstadistica.getString("jornada"))) saveTotal = Boolean.TRUE;
	            	    
	            	    if(saveTotal || arrayEstadistica.length() == i) {
	
	                        array.put(jsonObjectTotalHorarioTurno(listTotal, totalTotal));
	                    	saveTotal = Boolean.FALSE;
	                    	listTotal.clear();
	                    	
	                    	lastHorarioTurno = objectEstadistica.getString("jornada");
	                    }
	            	    listTotal.add(puestoAntiguedadJSON);
	            	    
	            	    array.put(puestoAntiguedadJSON);
	    			}
	    			array.put(jsonObjectTotalHorarioTurno(listTotal, totalTotal));
	    		}
	    	}	
		}
		return array;
	}

	public static JSONArray getCesesEstadisticas(long compId, long versionId, ResourceBundle bundle, Estadistica estadistica) throws PortalException {
		JSONArray array = JSONFactoryUtil.createJSONArray();
    	
		if(Validator.isNotNull(estadistica)) {
		JSONObject json = JSONFactoryUtil.createJSONObject(estadistica.getEstadistica());
        	
    	if(Validator.isNotNull(json)) {
    		JSONArray arrayEstadistica = json.getJSONArray("data");
    		List<JSONObject> listTotal = new ArrayList<>();
    		long totalTotal = 0L;
    		
    		if(Validator.isNotNull(arrayEstadistica)) {
    			for(int i=0 ; i<arrayEstadistica.length() ; i++) {
    				JSONObject objectEstadistica = arrayEstadistica.getJSONObject(i);
    				long nHombres = Long.parseLong(objectEstadistica.getString("hombres"));
             	    long nMujeres = Long.parseLong(objectEstadistica.getString("mujeres"));
    			
             	    totalTotal =totalTotal+nHombres + nMujeres;
    			}
    			for(int i=0 ; i<arrayEstadistica.length() ; i++) {
    				JSONObject objectEstadistica = arrayEstadistica.getJSONObject(i);
    				
    				String causa = StringPool.BLANK;
            		if (objectEstadistica.getString("causa").equals("DIMISION")) causa = LanguageUtil.get(bundle, "estadisticas.ceses.DIMISION");
            	    if (objectEstadistica.getString("causa").equals("FIN_CONTRATO")) causa = LanguageUtil.get(bundle, "estadisticas.ceses.FIN_CONTRATO");
            	    if (objectEstadistica.getString("causa").equals("OTRAS")) causa = LanguageUtil.get(bundle, "estadisticas.ceses.OTRAS");
            	    if (objectEstadistica.getString("causa").equals("DESPIDO")) causa = LanguageUtil.get(bundle, "estadisticas.ceses.DESPIDO");
            	    
            	    long nHombres = Long.parseLong(objectEstadistica.getString("hombres"));
            	    long nMujeres = Long.parseLong(objectEstadistica.getString("mujeres"));
            	    long total = nHombres + nMujeres;
            	    float nHombresPorc = calculatePercentage(nHombres, total);
            	    float nMujeresPorc = calculatePercentage(nMujeres, total);
		            float totalPorc = calculatePercentage(total, total);
		            
            	    JSONObject objectJSON = JSONFactoryUtil.createJSONObject();
            	    objectJSON.put("tipoCese", causa);
            	    objectJSON.put("nMujeres", nMujeres);
            	    objectJSON.put("nMujeresPorc", nMujeresPorc+ StringPool.PERCENT);
            	    objectJSON.put("nHombres", nHombres);
            	    objectJSON.put("nHombresPorc", nHombresPorc+ StringPool.PERCENT);
            	    objectJSON.put("total", total);
            	    objectJSON.put("totalPorc", totalPorc+ StringPool.PERCENT);
            	    
            	    array.put(objectJSON);
            	    listTotal.add(objectJSON);
    			}
    			array.put(jsonObjectTotalCeses(listTotal, totalTotal));
    		}
    	}
		}
		return array;
	}
	
	public static JSONArray getIncapacidadesEstadisticas(long compId, long versionId, ResourceBundle bundle, Estadistica estadistica) throws PortalException {
		JSONArray array = JSONFactoryUtil.createJSONArray();
		
		if(Validator.isNotNull(estadistica)) {
			JSONObject json = JSONFactoryUtil.createJSONObject(estadistica.getEstadistica());
	    	
	    	if(Validator.isNotNull(json)) {
	    		JSONArray arrayEstadistica = json.getJSONArray("data");
	    		List<JSONObject> listTotal = new ArrayList<>();
	    		
	    		if(Validator.isNotNull(arrayEstadistica)) {
	    			
	    			for(int i=0 ; i<arrayEstadistica.length() ; i++) {
	    				JSONObject objectEstadistica = arrayEstadistica.getJSONObject(i);
	    				
	    				String situacion = StringPool.BLANK;
	            		if (objectEstadistica.getString("situacion").equals("AEP")) situacion = LanguageUtil.get(bundle, "estadisticas.situacionIlt.AEP");
	            	    if (objectEstadistica.getString("situacion").equals("COC")) situacion = LanguageUtil.get(bundle, "estadisticas.situacionIlt.COC");
	            	   
	            	    long nHombres = Long.parseLong(objectEstadistica.getString("nHombres"));
	            	    long nMujeres = Long.parseLong(objectEstadistica.getString("nMujeres"));
	            	    long total = nHombres + nMujeres;
			            long diasHombres = Long.parseLong(objectEstadistica.getString("dHombres"));
	            	    long diasMujeres = Long.parseLong(objectEstadistica.getString("dMujeres"));
	            	    long diasTotal = diasHombres + diasMujeres;
	            	    float porcMujeres = calculatePercentage(diasMujeres, diasTotal) ;
	            	    float porcHombres = calculatePercentage(diasHombres, diasTotal);
	            	    float porcTotal = calculatePercentage(diasTotal, diasTotal);
	            	    long promedioHombres = nHombres==0?0:diasHombres/nHombres;
	            	    long promedioMujeres = nMujeres==0?0:diasMujeres/nMujeres;
	            	    long promedioTotal = total==0? 0: diasTotal/total;
	            	    
	            	    JSONObject objectJSON = JSONFactoryUtil.createJSONObject();
	            	    objectJSON.put("tipoIncapacidad", situacion);
	            	    objectJSON.put("nMujeres", nMujeres);
	            	    objectJSON.put("nHombres", nHombres);
	            	    objectJSON.put("diasMujeres", diasMujeres);
	            	    objectJSON.put("diasHombres", diasHombres);
	            	    objectJSON.put("diasTotal", diasTotal);
	            	    objectJSON.put("porcMujeres", porcMujeres + StringPool.PERCENT);
	            	    objectJSON.put("porcHombres", porcHombres + StringPool.PERCENT);
	            	    objectJSON.put("porcTotal", porcTotal + StringPool.PERCENT);
	            	    objectJSON.put("promedioHombres", promedioHombres);
	            	    objectJSON.put("promedioMujeres", promedioMujeres);
	            	    objectJSON.put("promedioTotal", promedioTotal);
	            	    
	            	    array.put(objectJSON);
	            	    listTotal.add(objectJSON);
	    			}
	    			array.put(jsonObjectTotalIncapacidades(listTotal));
	    		}
	    	}
		}
		return array;
	}
	
	private static JSONObject jsonObjectTotalIncapacidades(List<JSONObject> listTotal) {
		
		long nHombres = 0;
	    long nMujeres = 0;
	    
        long diasHombres = 0;
	    long diasMujeres = 0;

		for(JSONObject totalObject : listTotal){
			 nMujeres = nMujeres + Long.parseLong(totalObject.getString("nMujeres"));
			 nHombres = nHombres + Long.parseLong(totalObject.getString("nHombres"));
			 diasHombres = diasHombres + Long.parseLong(totalObject.getString("diasHombres"));
			 diasMujeres = diasMujeres + Long.parseLong(totalObject.getString("diasMujeres"));
		}
		
		long total = nHombres + nMujeres;
		long diasTotal = diasHombres + diasMujeres;
		float porcMujeres = calculatePercentage(diasMujeres, diasTotal);
		float porcHombres = calculatePercentage(diasHombres, diasTotal);
	    float porcTotal = calculatePercentage(diasTotal, diasTotal);
	    long promedioHombres = nHombres==0? 0 : diasHombres/nHombres;
	    long promedioMujeres = nMujeres==0? 0 : diasMujeres/nMujeres;
	    long promedioTotal = total==0 ? 0 : diasTotal/total;
	    
		JSONObject objectJSON = JSONFactoryUtil.createJSONObject();
	    objectJSON.put("tipoIncapacidad", "Total");
	    objectJSON.put("nMujeres", nMujeres);
	    objectJSON.put("nHombres", nHombres);
	    objectJSON.put("diasMujeres", diasMujeres);
	    objectJSON.put("diasHombres", diasHombres);
	    objectJSON.put("diasTotal", diasTotal);
	    objectJSON.put("porcMujeres", porcMujeres+ StringPool.PERCENT);
	    objectJSON.put("porcHombres", porcHombres+ StringPool.PERCENT);
	    objectJSON.put("porcTotal", porcTotal+ StringPool.PERCENT);
	    objectJSON.put("promedioHombres", promedioHombres);
	    objectJSON.put("promedioMujeres", promedioMujeres);
	    objectJSON.put("promedioTotal", promedioTotal);
	    
	    return objectJSON;
	}
	
	private static JSONObject jsonObjectTotalCeses(List<JSONObject> listTotal, long totalTotal) {
		
		long hombresTotal = 0;
    	long mujeresTotal = 0;
    	for(JSONObject totalObject : listTotal){
    		hombresTotal = hombresTotal + Long.parseLong(totalObject.getString("nHombres"));    		
    		mujeresTotal = mujeresTotal + Long.parseLong(totalObject.getString("nMujeres"));
    	}
    	long total = hombresTotal + mujeresTotal;
    	
        JSONObject objectJSON = JSONFactoryUtil.createJSONObject();
	    objectJSON.put("tipoCese", "Total");
	    objectJSON.put("nMujeres", mujeresTotal);
	    objectJSON.put("nMujeresPorc", calculatePercentage(mujeresTotal, totalTotal)+ StringPool.PERCENT);
	    objectJSON.put("nHombres", hombresTotal);
	    objectJSON.put("nHombresPorc",  calculatePercentage(hombresTotal, totalTotal)+ StringPool.PERCENT);
	    objectJSON.put("total", total);
	    objectJSON.put("totalPorc", calculatePercentage(total, totalTotal)+ StringPool.PERCENT);
        
        return objectJSON;
	}
	
	/**
	 * 
	 * @param listTotal
	 * @param totalTotal
	 * @return
	 */
	private static JSONObject jsonObjectTotalHorarioTurno(List<JSONObject> listTotal, long totalTotal) {
		
		long hombresTotal = 0;
    	long mujeresTotal = 0;
    	for(JSONObject totalObject : listTotal){
    		hombresTotal = hombresTotal + Long.parseLong(totalObject.getString("nHombres"));    		
    		mujeresTotal = mujeresTotal + Long.parseLong(totalObject.getString("nMujeres"));
    	}
    	long total = hombresTotal + mujeresTotal;
    	
    	JSONObject puestoHorarioTurnoTotalJSON = JSONFactoryUtil.createJSONObject();
    	puestoHorarioTurnoTotalJSON.put("turno", "Total");
    	puestoHorarioTurnoTotalJSON.put("nHombres", hombresTotal);
    	puestoHorarioTurnoTotalJSON.put("porcHombres", calculatePercentage(hombresTotal, totalTotal)+ StringPool.PERCENT);
    	puestoHorarioTurnoTotalJSON.put("nMujeres", mujeresTotal);
    	puestoHorarioTurnoTotalJSON.put("porcMujeres", calculatePercentage(mujeresTotal, totalTotal)+ StringPool.PERCENT);
        puestoHorarioTurnoTotalJSON.put("total", total);
        puestoHorarioTurnoTotalJSON.put("porcTotal", calculatePercentage(total, totalTotal)+ StringPool.PERCENT);
        
        return puestoHorarioTurnoTotalJSON;
	}
	
	/**
	 * 
	 * @param compId
	 * @param versionId
	 * @param bundle
	 * @return
	 * @throws PortalException
	 */
	public static JSONArray getPuestosNivelEstudiosEstadisticas(long compId, long versionId, ResourceBundle bundle, Estadistica estadistica) throws PortalException {
		JSONArray array = JSONFactoryUtil.createJSONArray();

		if(Validator.isNotNull(estadistica)) {
	    	JSONObject json = JSONFactoryUtil.createJSONObject(estadistica.getEstadistica());
	    	if(Validator.isNotNull(json)) {
	    		JSONArray arrayEstadistica = json.getJSONArray("data");
	    		if(Validator.isNotNull(arrayEstadistica)) {
	    			
	    			for(int i=0 ; i<arrayEstadistica.length() ; i++) {
	    				JSONObject objectEstadistica = arrayEstadistica.getJSONObject(i);
	
	    				String nivelEstudios = StringPool.BLANK;
	            		
	            		if (objectEstadistica.getString("id").equals("00")) nivelEstudios = LanguageUtil.get(bundle, "estadisticas.puestoEstudios.00");
	            	    if (objectEstadistica.getString("id").equals("01")) nivelEstudios = LanguageUtil.get(bundle, "estadisticas.puestoEstudios.01");
	            	    if (objectEstadistica.getString("id").equals("02")) nivelEstudios = LanguageUtil.get(bundle, "estadisticas.puestoEstudios.02");
	            	    if (objectEstadistica.getString("id").equals("03")) nivelEstudios = LanguageUtil.get(bundle, "estadisticas.puestoEstudios.03");
	            	    if (objectEstadistica.getString("id").equals("04")) nivelEstudios = LanguageUtil.get(bundle, "estadisticas.puestoEstudios.04");
	            	    if (objectEstadistica.getString("id").equals("05")) nivelEstudios = LanguageUtil.get(bundle, "estadisticas.puestoEstudios.05");
	            	    if (objectEstadistica.getString("id").equals("06")) nivelEstudios = LanguageUtil.get(bundle, "estadisticas.puestoEstudios.06");
	            	    
	            	    JSONObject puestoEstudiosJSON = JSONFactoryUtil.createJSONObject();
	            	    
	            	    PuestoTrabajoPK puestoPK = new PuestoTrabajoPK();
	                    puestoPK.setCompId(compId);
	                    puestoPK.setVersionId(versionId);
	                    puestoPK.setPuestoId(Long.parseLong(objectEstadistica.getString("puesto")));
	                    
	                    PuestoTrabajo puesto= PuestoTrabajoLocalServiceUtil.fetchPuestoTrabajo(puestoPK);
	                    		
	                    puestoEstudiosJSON.put("puesto", puesto.getNombre());
	                    puestoEstudiosJSON.put("nivelEstudio", nivelEstudios);
	                    puestoEstudiosJSON.put("hombres", objectEstadistica.getString("hombres"));
	                    puestoEstudiosJSON.put("mujeres", objectEstadistica.getString("mujeres"));       
	                    puestoEstudiosJSON.put("total", (Long.parseLong(objectEstadistica.getString("hombres")) + Long.parseLong(objectEstadistica.getString("mujeres"))));
	    			
	                    array.put(puestoEstudiosJSON);
	    			}
	    		}
	    	}
		}

		return array;
	}
	
	public static Map<String, Object> getTable2PuestoEstudios(long compId, long versionId, ResourceBundle bundle, Estadistica estadistica) throws JSONException{
		Map<String, Object> parameters = new HashMap<>();
		
		if(Validator.isNotNull(estadistica)) {
	    	JSONObject json = JSONFactoryUtil.createJSONObject(estadistica.getEstadistica());
	    	if(Validator.isNotNull(json)) {
	    		JSONArray arrayEstadistica = json.getJSONArray("data");
	    		if(Validator.isNotNull(arrayEstadistica)) {
	    			
	    			String puestoPreHom =StringPool.BLANK;
	    			String puestoPreMuj =StringPool.BLANK;
	    			String formPreHom =StringPool.BLANK;
	    			String formPreMuj =StringPool.BLANK;
	    			
	    			int maxHomPuesto=0;
	    			int maxMujPuesto=0;
	    			int maxHomForm=0;
	    			int maxMujForm=0;
	    			
	    			int countHomPuesto=0;
	    			int countMujPuesto=0;
	    			Long puestoActual= 0L;
	    			String puestoPreHomCod =StringPool.BLANK;
	    			String puestoPreMujCod =StringPool.BLANK;
	    			Integer idFormHombreCod = -1;
	    			Integer idFormMujerCod = -1;
	    			for(int i=0 ; i<arrayEstadistica.length() ; i++) {
	    				JSONObject objectEstadistica = arrayEstadistica.getJSONObject(i);

	    				int nHombres = Integer.parseInt(objectEstadistica.getString("hombres"));
	    				int nMujeres = Integer.parseInt(objectEstadistica.getString("mujeres"));
	    				String nivelEstudios = StringPool.BLANK;
	            		
	            		if (objectEstadistica.getString("id").equals("00")) nivelEstudios = LanguageUtil.get(bundle, "estadisticas.puestoEstudios.00");
	            	    if (objectEstadistica.getString("id").equals("01")) nivelEstudios = LanguageUtil.get(bundle, "estadisticas.puestoEstudios.01");
	            	    if (objectEstadistica.getString("id").equals("02")) nivelEstudios = LanguageUtil.get(bundle, "estadisticas.puestoEstudios.02");
	            	    if (objectEstadistica.getString("id").equals("03")) nivelEstudios = LanguageUtil.get(bundle, "estadisticas.puestoEstudios.03");
	            	    if (objectEstadistica.getString("id").equals("04")) nivelEstudios = LanguageUtil.get(bundle, "estadisticas.puestoEstudios.04");
	            	    if (objectEstadistica.getString("id").equals("05")) nivelEstudios = LanguageUtil.get(bundle, "estadisticas.puestoEstudios.05");
	            	    if (objectEstadistica.getString("id").equals("06")) nivelEstudios = LanguageUtil.get(bundle, "estadisticas.puestoEstudios.06");
	            	    
	            	    PuestoTrabajoPK puestoPK = new PuestoTrabajoPK();
	                    puestoPK.setCompId(compId);
	                    puestoPK.setVersionId(versionId);
	                    puestoPK.setPuestoId(Long.parseLong(objectEstadistica.getString("puesto")));
	                    
	                    PuestoTrabajo puesto= PuestoTrabajoLocalServiceUtil.fetchPuestoTrabajo(puestoPK);
	        
	                    if(!puestoActual.equals(puesto.getPuestoId())) {
	                    	countHomPuesto = 0;
	                    	countMujPuesto = 0;
	                    	puestoActual = puesto.getPuestoId();
	                    }
                    	countHomPuesto = countHomPuesto + nHombres;
                    	countMujPuesto = countMujPuesto + nMujeres;
                    	
                    	if(countHomPuesto > maxHomPuesto) {
                    		maxHomPuesto = countHomPuesto;
                    		puestoPreHom = puesto.getNombre();
                    		puestoPreHomCod = Long.toString(puesto.getPuestoId());
                    	}
                    	
                    	if(countMujPuesto > maxMujPuesto) {
                    		maxMujPuesto = countMujPuesto;
                    		puestoPreMuj = puesto.getNombre();
                    		puestoPreMujCod = Long.toString(puesto.getPuestoId());
                    	}
                    	
                    	if(nHombres > maxHomForm) {
                    		maxHomForm = nHombres;
                    		formPreHom = nivelEstudios;
                    		idFormHombreCod = Integer.parseInt(objectEstadistica.getString("id"));
                    	}else if(nHombres == maxHomForm) {
                    		formPreHom = (!formPreHom.isEmpty()? formPreHom + ", " : "") + nivelEstudios;
                    	}
                    	
                    	if(nMujeres > maxMujForm) {
                    		maxMujForm = nMujeres;
                    		formPreMuj = nivelEstudios;
                    		idFormMujerCod = Integer.parseInt(objectEstadistica.getString("id"));
                    	}else if(nMujeres == maxMujForm) {
                    		formPreMuj = (!formPreMuj.isEmpty()? formPreMuj + ", " : "") + nivelEstudios;
                    	}
                    }
	    			
	    			int mujerInfTotal = 0;
	    			int mujerSupTotal =0 ;
	    			int hombreInfTotal = 0;
	    			int hombreSupTotal =0 ;
	    			int totalHombres=0;
	    			int totalMujeres=0;
	    			for(int i=0 ; i<arrayEstadistica.length() ; i++) {
	    				JSONObject objectEstadistica = arrayEstadistica.getJSONObject(i);
	    				
	    				if(objectEstadistica.getString("puesto").equals(puestoPreHomCod)) {
		    				totalHombres = totalHombres + Integer.parseInt(objectEstadistica.getString("hombres"));

	    					if(Integer.parseInt(objectEstadistica.getString("id")) < idFormHombreCod) {
	    						hombreInfTotal = hombreInfTotal + Integer.parseInt(objectEstadistica.getString("hombres"));
	    					}else if(Integer.parseInt(objectEstadistica.getString("id")) > idFormHombreCod) {
	    						hombreSupTotal = hombreSupTotal + Integer.parseInt(objectEstadistica.getString("hombres"));
	    					}
	    				}
	    				
	    				if(objectEstadistica.getString("puesto").equals(puestoPreMujCod)) {
		    				totalMujeres = totalMujeres + Integer.parseInt(objectEstadistica.getString("mujeres"));

	    					if(Integer.parseInt(objectEstadistica.getString("id")) < idFormMujerCod) {
	    						mujerInfTotal = mujerInfTotal + Integer.parseInt(objectEstadistica.getString("mujeres"));
	    					}else if(Integer.parseInt(objectEstadistica.getString("id")) > idFormMujerCod) {
	    						mujerSupTotal = mujerSupTotal + Integer.parseInt(objectEstadistica.getString("mujeres"));
	    					}
	    				}
	    			}

					if(totalHombres == 0) {
						parameters.put("formacionPredominanteHombre", StringPool.BLANK);
						parameters.put("puestoHabitualModaHombre", StringPool.BLANK);

						parameters.put("formacionEncimaEstudiosModaHombre", (hombreSupTotal * 100 / 1) + "%");
						parameters.put("formacionDebajoEstudiosModaHombre", (hombreInfTotal * 100 / 1) + "%");

					}else{
						parameters.put("formacionPredominanteHombre", formPreHom);
						parameters.put("puestoHabitualModaHombre", puestoPreHom);
						parameters.put("formacionEncimaEstudiosModaHombre", (hombreSupTotal * 100 / totalHombres) + "%");
						parameters.put("formacionDebajoEstudiosModaHombre", (hombreInfTotal * 100 / totalHombres) + "%");
					}
					if(totalMujeres == 0){
						parameters.put("formacionPredominanteMujer", StringPool.BLANK);
						parameters.put("puestoHabitualModaMujer", StringPool.BLANK);
						parameters.put("formacionEncimaEstudiosModaMujer", (mujerSupTotal * 100 / 1) + "%");
						parameters.put("formacionDebajoEstudiosModaMujer", (mujerInfTotal * 100 / 1) + "%");
					}else{
						parameters.put("formacionPredominanteMujer", formPreMuj);
						parameters.put("puestoHabitualModaMujer", puestoPreMuj);
						parameters.put("formacionEncimaEstudiosModaMujer", (mujerSupTotal * 100 / totalMujeres) + "%");
						parameters.put("formacionDebajoEstudiosModaMujer", (mujerInfTotal * 100 / totalMujeres) + "%");
					}
	    		}
	    	}
		}else {
			parameters.put("formacionPredominanteMujer", StringPool.BLANK);
			parameters.put("puestoHabitualModaMujer", StringPool.BLANK);
			parameters.put("formacionPredominanteHombre", StringPool.BLANK);
			parameters.put("puestoHabitualModaHombre", StringPool.BLANK);
			
			parameters.put("formacionEncimaEstudiosModaHombre", "%");
			parameters.put("formacionEncimaEstudiosModaMujer", "%");
			parameters.put("formacionDebajoEstudiosModaHombre", "%");
			parameters.put("formacionDebajoEstudiosModaMujer",  "%");
		}
	    	
		return parameters;
	}
	
	/**
	 * 
	 * @param compId
	 * @param versionId
	 * @param bundle
	 * @return
	 * @throws PortalException
	 */
	public static JSONArray getPuestosFamiliaresEstadisticas(long compId, long versionId, ResourceBundle bundle) throws PortalException {
		JSONArray array = JSONFactoryUtil.createJSONArray();
		
		List<PuestoTrabajo> puestos = PuestoTrabajoLocalServiceUtil.findPuestoTrabajoActivo(compId, versionId);
		if(Validator.isNotNull(puestos)) {
			for(PuestoTrabajo puesto : puestos) {
				if(puesto.getResponsabilidad() == 0) {
					JSONObject puestoJSON = JSONFactoryUtil.createJSONObject();
		            puestoJSON.put("hombres", puesto.getNHombres());
		            puestoJSON.put("mujeres", puesto.getNMujeres());
		            puestoJSON.put("total", puesto.getNHombres() + puesto.getNMujeres());
		            puestoJSON.put("nivelEstudio", puesto.getNombre());
		            puestoJSON.put("puesto", LanguageUtil.get(bundle, "estadisticas.puestoDependientes.0"));

		            array.put(puestoJSON);
				}
			}
			for(PuestoTrabajo puesto : puestos) {
				if(puesto.getResponsabilidad() == 1) {
					JSONObject puestoJSON = JSONFactoryUtil.createJSONObject();
		            puestoJSON.put("hombres", puesto.getNHombres());
		            puestoJSON.put("mujeres", puesto.getNMujeres());
		            puestoJSON.put("total", puesto.getNHombres() + puesto.getNMujeres());
		            puestoJSON.put("nivelEstudio", puesto.getNombre());
		            puestoJSON.put("puesto", LanguageUtil.get(bundle, "estadisticas.puestoDependientes.1"));

		            array.put(puestoJSON);
				}
			}
		}
				
		return array;
	}
	
	public static JSONArray getValoraciones(long compId, long versionId, Estadistica estadistica) throws PortalException {
		JSONArray array = JSONFactoryUtil.createJSONArray();
	
		if(Validator.isNotNull(estadistica)) {
			JSONObject json = JSONFactoryUtil.createJSONObject(estadistica.getEstadistica());
			if(Validator.isNotNull(json)) {
				JSONArray valoraciones = json.getJSONArray("valoraciones");
				for(int i=0;i<valoraciones.length();i++) {
					JSONObject valoracion = valoraciones.getJSONObject(i);
					try{
						Long.parseLong(valoracion.getString("puesto"));
					}catch (NumberFormatException error){
						continue;
					}
					PuestoTrabajoPK puestoPK = new PuestoTrabajoPK();
                    puestoPK.setCompId(compId);
                    puestoPK.setVersionId(versionId);
                    puestoPK.setPuestoId(Long.parseLong(valoracion.getString("puesto")));
                    
                    PuestoTrabajo puesto= PuestoTrabajoLocalServiceUtil.fetchPuestoTrabajo(puestoPK);

                    JSONObject objectJson = JSONFactoryUtil.createJSONObject();
                    objectJson.put("tituloPuesto", puesto.getNombre());
                    objectJson.put("convenio", valoracion.getString("convenio"));
                    objectJson.put("area", valoracion.getString("area"));
                    objectJson.put("departamento", valoracion.getString("departamento"));
                    objectJson.put("centro", valoracion.getString("centro"));
                    
					array.put(objectJson);
				}
			}
		}
		
		return array;
	}
	
	public static JSONArray getDistribuciones(long compId, long versionId, Estadistica estadistica) throws PortalException {
		JSONArray array = JSONFactoryUtil.createJSONArray();
		
		if(Validator.isNotNull(estadistica)) {
			JSONObject json = JSONFactoryUtil.createJSONObject(estadistica.getEstadistica());
			if(Validator.isNotNull(json)) {
				JSONArray distribuciones = json.getJSONArray("distribuciones");
				
				for(int i=0 ; i<distribuciones.length() ; i++) {
					JSONObject distribucion = distribuciones.getJSONObject(i);
					try{
						Long.parseLong(distribucion.getString("puesto"));
					}catch (NumberFormatException error){
						continue;
					}
					PuestoTrabajoPK puestoPK = new PuestoTrabajoPK();
                    puestoPK.setCompId(compId);
                    puestoPK.setVersionId(versionId);
                    puestoPK.setPuestoId(Long.parseLong(distribucion.getString("puesto")));
                    
                    PuestoTrabajo puesto= PuestoTrabajoLocalServiceUtil.fetchPuestoTrabajo(puestoPK);

                    String categorizacion = distribucion.getString("categoria").equals("F") ? "Feminizado" : "Masculinizado";
                    
					JSONObject objectJson = JSONFactoryUtil.createJSONObject();
					objectJson.put("tituloPuesto", puesto.getNombre());
					objectJson.put("categorizacion", categorizacion);
					objectJson.put("nHombres", distribucion.getString("nHombres"));
					objectJson.put("nMujeres", distribucion.getString("nMujeres"));
					objectJson.put("agrupacion", distribucion.getString("agrupacion"));
					objectJson.put("puntos", distribucion.getString("puntos"));
					
					array.put(objectJson);
				}
			}
		}
		
		return array;
	}
	
	public static JSONArray getAuditoriasEstadisticas(long compId, long versionId, ResourceBundle bundle, Estadistica estadistica) throws PortalException {
		JSONArray array = JSONFactoryUtil.createJSONArray();
		
		if(Validator.isNotNull(estadistica)) {
			JSONObject json = JSONFactoryUtil.createJSONObject(estadistica.getEstadistica());
			if(Validator.isNotNull(json)) {
	    		JSONArray arrayEstadistica = json.getJSONArray("data");
	    		if(Validator.isNotNull(arrayEstadistica)) {
	    			for(int i=0 ; i<arrayEstadistica.length() ; i++) {
	    				JSONObject objectEstadistica = arrayEstadistica.getJSONObject(i);
	
	    				String tipoSalario = StringPool.BLANK;
	            		if (objectEstadistica.getString("tipo").equals("00")) tipoSalario = LanguageUtil.get(bundle, "estadisticas.auditoria.MSA");
	            	    if (objectEstadistica.getString("tipo").equals("01")) tipoSalario = LanguageUtil.get(bundle, "estadisticas.auditoria.PRS");
	            	    if (objectEstadistica.getString("tipo").equals("02")) tipoSalario = LanguageUtil.get(bundle, "estadisticas.auditoria.MRE");
	
	            	    String salario = StringPool.BLANK;
	            		if (objectEstadistica.getString("tipo").equals("00") && objectEstadistica.getString("auditoria").equals("PRT")) salario = LanguageUtil.get(bundle, "estadisticas.auditoria.MSA.PRT");
	            	    if (objectEstadistica.getString("tipo").equals("01") && objectEstadistica.getString("auditoria").equals("SBA")) salario = LanguageUtil.get(bundle, "estadisticas.auditoria.PRS.SBA");
	            	    if (objectEstadistica.getString("tipo").equals("01") && objectEstadistica.getString("auditoria").equals("CSA")) salario = LanguageUtil.get(bundle, "estadisticas.auditoria.PRS.CSA");
	            	    if (objectEstadistica.getString("tipo").equals("01") && objectEstadistica.getString("auditoria").equals("CEX")) salario = LanguageUtil.get(bundle, "estadisticas.auditoria.PRS.CEX");
	            	    if (objectEstadistica.getString("tipo").equals("02") && objectEstadistica.getString("auditoria").equals("SBA")) salario = LanguageUtil.get(bundle, "estadisticas.auditoria.MRE.SBA");
	            	    if (objectEstadistica.getString("tipo").equals("02") && objectEstadistica.getString("auditoria").equals("CSA")) salario = LanguageUtil.get(bundle, "estadisticas.auditoria.MRE.CSA");
	            	    if (objectEstadistica.getString("tipo").equals("02") && objectEstadistica.getString("auditoria").equals("CEX")) salario = LanguageUtil.get(bundle, "estadisticas.auditoria.MRE.CEX");
	
	            	    JSONObject objectJson = JSONFactoryUtil.createJSONObject();
	            	    
	            	    float hombres = Float.parseFloat(objectEstadistica.getString("nHombres"));
	            	    float mujeres = Float.parseFloat(objectEstadistica.getString("nMujeres"));
	            	    float diferencia = 0;
	            	    
	            	    if(Validator.isNotNull(hombres) && Validator.isNotNull(mujeres)) {
	            	    	diferencia = calculatePercentage(Math.abs(hombres - mujeres), Math.max(hombres, mujeres));
	            	    }
	            	    
	            	    objectJson.put("tipoSalario", tipoSalario);
	            	    objectJson.put("salario", salario);
	            	    objectJson.put("hombres", hombres);
	            	    objectJson.put("mujeres", mujeres);
	            	    objectJson.put("diferencias", diferencia + StringPool.PERCENT);
	            	    objectJson.put("conceptos", objectEstadistica.getString("conceptos"));
	            	    
	                    array.put(objectJson);
	    			}
	    		}
	    	}
		}
		return array;
	}
	
	
	/**
	 * 
	 * @param listTotal
	 * @return
	 */
	private static JSONObject jsonObjectTotalPuestoAntiguedad(List<JSONObject> listTotal) {
		long hombresTotal = 0;
    	long hombresRTotal = 0;               
    	long mujeresTotal = 0;
    	long mujeresRTotal =0;
    	for(JSONObject totalObject : listTotal){
    		hombresTotal = hombresTotal + Long.parseLong(totalObject.getString("hombres"));
    		hombresRTotal = hombresRTotal + Long.parseLong(totalObject.getString("hombresR"));
    		
    		mujeresTotal = mujeresTotal + Long.parseLong(totalObject.getString("mujeres"));
    		mujeresRTotal = mujeresRTotal + Long.parseLong(totalObject.getString("mujeresR"));
    	}

    	float pHombresTotal = calculatePercentage(hombresRTotal, hombresTotal);
    	float pMujeresTotal = calculatePercentage(mujeresRTotal, mujeresTotal);
    	
    	JSONObject puestoAntiguedadTotalJSON = JSONFactoryUtil.createJSONObject();
    	puestoAntiguedadTotalJSON.put("rangoEdad", "Total");
        puestoAntiguedadTotalJSON.put("hombres", hombresTotal);
        puestoAntiguedadTotalJSON.put("hombresR", hombresRTotal);
        puestoAntiguedadTotalJSON.put("pHombres", pHombresTotal + StringPool.PERCENT);
        puestoAntiguedadTotalJSON.put("mujeres", mujeresTotal);
        puestoAntiguedadTotalJSON.put("mujeresR", mujeresRTotal);
        puestoAntiguedadTotalJSON.put("pMujeres", pMujeresTotal + StringPool.PERCENT);
        
        return puestoAntiguedadTotalJSON;
	}
	
	/**
	 * 
	 * @param obtained
	 * @param total
	 * @return
	 */
	public static float calculatePercentage(float obtained, float total) {
        if(total == 0) return 0;
		float value = obtained * 100 / total;
        return round(value, NumberUtils.INTEGER_ONE);
    }

	/**
	 * 
	 * @param pHombres
	 * @param pMujeres
	 * @return
	 */
    private static String calculateAnalysis(float pHombres, float pMujeres) {
    	if (pHombres == 100) return "<i>Masculinizado</i>";
        if (pMujeres == 100) return "<i>Feminizado</i>";
        if (pHombres >= 60) return "Masculinizado";
        if (pMujeres >= 60) return "Feminizado";
        return "Equilibrado";
    }

	/**
	 *
	 * @param value
	 * @param scale
	 * @return
	 */
	private static float round(float value, int scale) {
		BigDecimal bd = new BigDecimal(Double.parseDouble(Float.toString(value)));
		bd = bd.setScale(scale, RoundingMode.HALF_UP);
		return bd.floatValue(); 
	}
}

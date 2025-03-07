package com.plan.igualdad.liferay.generar.informe.utils;

import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.Validator;
import com.plan.igualdad.liferay.portlet.manager.model.Evaluacion;
import com.plan.igualdad.liferay.portlet.manager.service.EvaluacionLocalServiceUtil;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class EvaluacionUtils {
	
	public static Map<String, Object> getParameters (Long compId, Long versionId) throws JSONException{
		Map<String, Object> parameters = new HashMap<>();
	
		List<Evaluacion> evaluacionesList = EvaluacionLocalServiceUtil.findByCompVersion(compId, versionId);
	
		int versions = EvaluacionLocalServiceUtil.countByComp(compId);

		if(Validator.isNotNull(evaluacionesList) && !evaluacionesList.isEmpty()) { 
			for(Evaluacion evaluacion : evaluacionesList) {
				if(versions == evaluacion.getVersionEvaluacion()) {
					JSONObject jsonDatosGenerales = JSONFactoryUtil.createJSONObject(evaluacion.getDatosGenerales());
					Iterator<String> keysDatosGenerales = jsonDatosGenerales.keys();
					while(keysDatosGenerales.hasNext()) {
					    String key = keysDatosGenerales.next();
					    parameters.put(key, jsonDatosGenerales.get(key));  
					}
					
					JSONObject jsonResultados = JSONFactoryUtil.createJSONObject(evaluacion.getInformacionResultados());
					Iterator<String> keysResultados = jsonResultados.keys();
					while(keysResultados.hasNext()) {
					    String key = keysResultados.next();
					    parameters.put(key, jsonResultados.get(key));  
					}
					
					JSONObject jsonImplantacion = JSONFactoryUtil.createJSONObject(evaluacion.getInformacionImplantacion());
					Iterator<String> keysImplantacion = jsonImplantacion.keys();
					while(keysImplantacion.hasNext()) {
					    String key = keysImplantacion.next();
					    parameters.put(key, jsonImplantacion.get(key));  
					}
					
					JSONObject jsonImpacto = JSONFactoryUtil.createJSONObject(evaluacion.getInformacionImpacto());
					Iterator<String> keysImpacto = jsonImpacto.keys();
					while(keysImpacto.hasNext()) {
					    String key = keysImpacto.next();
					    parameters.put(key, jsonImpacto.get(key));  
					}
					
					parameters.put("conclusionPropuesta", evaluacion.getConclusion());
				}
			}
		}

		return parameters;
	}
}

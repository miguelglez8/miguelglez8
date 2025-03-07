package com.plan.igualdad.liferay.generar.informe.utils;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Validator;
import com.plan.igualdad.liferay.generar.informe.constants.PlanIgualdadGenerarInformePortletKeys;
import com.plan.igualdad.liferay.generar.informe.dto.ParticipantesCuestionarioDTO;
import com.plan.igualdad.liferay.portlet.manager.model.Respuesta;
import com.plan.igualdad.liferay.portlet.manager.service.RespuestaLocalServiceUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class CuestionarioEmpresaUtil {

	/**
	 * 
	 * @param themeDisplay
	 * @param compId
	 * @param versionId
	 * @return
	 * @throws JSONException
	 */
	public static Map<String, Object> getParameterAll(ThemeDisplay themeDisplay, Long compId, Long versionId) throws JSONException{
		Map<String, Object> parameters = new HashMap<>();
		
		List<Respuesta> listRespuestas = RespuestaLocalServiceUtil.fetchRespuestas(compId, versionId);
		if(Validator.isNotNull(listRespuestas) && !listRespuestas.isEmpty()) {
			for(Respuesta respuesta : listRespuestas) {
				if(respuesta.getSeccionId()!=0L) {
					JSONArray jsonArrayRespuesta = JSONFactoryUtil.createJSONArray(respuesta.getRespuestas());
					if(Validator.isNotNull(jsonArrayRespuesta)) {
						for(int x = 0 ; x< jsonArrayRespuesta.length() ; x++) {
							JSONObject objectRespuesta = jsonArrayRespuesta.getJSONObject(x);
							String respuestaIdPregunta = objectRespuesta.getString(PlanIgualdadGenerarInformePortletKeys.RESPUESTA_NAME);
							String valueRespuesta = objectRespuesta.getString(PlanIgualdadGenerarInformePortletKeys.RESPUESTA_VALUE);
							String tipoRespuesta = objectRespuesta.getString(PlanIgualdadGenerarInformePortletKeys.RESPUESTA_TYPE);
							String other = objectRespuesta.getString(PlanIgualdadGenerarInformePortletKeys.RESPUESTA_OTHER);

							if(PlanIgualdadGenerarInformePortletKeys.TIPO_TEXTAREA.equals(tipoRespuesta)) {
								parameters.put(PlanIgualdadGenerarInformePortletKeys.RESPUESTA +respuestaIdPregunta , !valueRespuesta.isEmpty() ? GeneralUtils.deleteHTML(valueRespuesta, Boolean.FALSE, Boolean.FALSE): PlanIgualdadGenerarInformePortletKeys.DATO_NO_INFORMADO);
							}else if(PlanIgualdadGenerarInformePortletKeys.TIPO_CHECKBOX.equals(tipoRespuesta)) {
								if(respuestaIdPregunta.contains(PlanIgualdadGenerarInformePortletKeys.TIPO_MATRIX)) {
									parameters.put(PlanIgualdadGenerarInformePortletKeys.RESPUESTA +respuestaIdPregunta , valueRespuesta);
								}else {
									parameters.put(PlanIgualdadGenerarInformePortletKeys.RESPUESTA +respuestaIdPregunta+"_"+valueRespuesta , valueRespuesta);
								}
							}else {
								parameters.put(PlanIgualdadGenerarInformePortletKeys.RESPUESTA +respuestaIdPregunta , valueRespuesta);
							}
							
							parameters.put(PlanIgualdadGenerarInformePortletKeys.RESPUESTA +respuestaIdPregunta+"_other" , other);					
						}
					}
				}
			}
		}
		
		return parameters;
	}
	
	/**
	 * 
	 * @param themeDisplay
	 * @param compId
	 * @param versionId
	 * @return
	 * @throws JSONException
	 */
	public static Map<String, Object> getParameterParticipantes(ThemeDisplay themeDisplay, Long compId, Long versionId) throws JSONException{
		Map<String, Object> parameters = new HashMap<>();
		
		List<ParticipantesCuestionarioDTO> participantes =  new ArrayList<>();
		Respuesta respuesta = RespuestaLocalServiceUtil.fetchRespuestaBySeccion(compId, versionId, 0L);
		if(Validator.isNotNull(respuesta)) {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject(respuesta.getRespuestas());
			
			JSONArray jsonArrayRespuesta = jsonObject.getJSONArray("data");
			if(Validator.isNotNull(jsonArrayRespuesta)) {
				for(int x = 0 ; x< jsonArrayRespuesta.length() ; x++) {
					ParticipantesCuestionarioDTO participante = new ParticipantesCuestionarioDTO();
					JSONObject objectRespuesta = jsonArrayRespuesta.getJSONObject(x);
					
					participante.setNombreApellidos(objectRespuesta.getString("nombre") + " " + objectRespuesta.getString("apellidos"));
					participante.setOrganizacion(objectRespuesta.getString("nif"));
					participante.setPuesto(objectRespuesta.getString("puesto"));
					participante.setEmail(objectRespuesta.getString("email"));
					participantes.add(participante);
				}
			}
		}
				
		JRBeanCollectionDataSource participantesBean = new JRBeanCollectionDataSource(participantes);
		parameters.put(PlanIgualdadGenerarInformePortletKeys.PARTICIPANTES_CUESTIONARIO_BEAN, participantesBean);	
		
		return parameters;
	}
	
	/**
	 * 
	 * @param themeDisplay
	 * @param compId
	 * @param versionId
	 * @return
	 * @throws JSONException
	 */
	public static JSONArray getParticipantesCuestionario(ThemeDisplay themeDisplay, Long compId, Long versionId) throws JSONException {
		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();
		Respuesta respuesta = RespuestaLocalServiceUtil.fetchRespuestaBySeccion(compId, versionId, 0L);
		if(Validator.isNotNull(respuesta)) {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject(respuesta.getRespuestas());
			
			JSONArray jsonArrayRespuesta = jsonObject.getJSONArray("data");
			if(Validator.isNotNull(jsonArrayRespuesta)) {
				for(int x = 0 ; x< jsonArrayRespuesta.length() ; x++) {
					JSONObject objectRespuesta = jsonArrayRespuesta.getJSONObject(x);
					
					JSONObject jsonObjectParticipante = JSONFactoryUtil.createJSONObject();
					jsonObjectParticipante.put("nombreApellidos", objectRespuesta.getString("nombre"));
					jsonObjectParticipante.put("nif", objectRespuesta.getString("nif"));
					jsonObjectParticipante.put("puesto", objectRespuesta.getString("puesto"));
					jsonObjectParticipante.put("email", objectRespuesta.getString("email"));
					
	        		jsonArray.put(jsonObjectParticipante);
				}
			}
		}
		return jsonArray;
	}
}

package com.plan.igualdad.liferay.portlet.commons.web.parametrizaciones;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Validator;
import com.plan.igualdad.liferay.portlet.commons.web.constants.PlanIgualdadCommonsPortletKeys;
import com.plan.igualdad.liferay.portlet.commons.web.dto.MedidaDTO;
import com.plan.igualdad.liferay.portlet.commons.web.dto.ParametrizacionDTO;
import com.plan.igualdad.liferay.portlet.commons.web.estado.EstadoUtils;
import com.plan.igualdad.liferay.portlet.manager.exception.NoSuchParametricasFDDException;
import com.plan.igualdad.liferay.portlet.manager.model.ParametricasFDD;
import com.plan.igualdad.liferay.portlet.manager.model.Pregunta;
import com.plan.igualdad.liferay.portlet.manager.model.Respuesta;
import com.plan.igualdad.liferay.portlet.manager.service.EstadoLocalServiceUtil;
import com.plan.igualdad.liferay.portlet.manager.service.ParametricasFDDLocalServiceUtil;
import com.plan.igualdad.liferay.portlet.manager.service.PreguntaLocalServiceUtil;
import com.plan.igualdad.liferay.portlet.manager.service.RespuestaLocalServiceUtil;
import com.plan.igualdad.liferay.portlet.manager.service.persistence.ParametricasFDDPK;

import java.util.List;

public class GeneralUtils {

	private static Log _log = LogFactoryUtil.getLog(GeneralUtils.class);

	public static Boolean isCumpleSelect(String nameInput, String respuestaParam, Long compId, Long versionId, Long seccionId) throws JSONException{
		Boolean cumpleSelect = Boolean.FALSE;		
		Respuesta respuesta = RespuestaLocalServiceUtil.fetchRespuestaBySeccion(compId, versionId, seccionId);
		 
		boolean haveRespuesta = Boolean.FALSE;
		if(Validator.isNotNull(respuesta) && Validator.isNotNull(respuesta.getRespuestas()) 
				&& !respuesta.getRespuestas().isEmpty() && !respuesta.getRespuestas().equals("[]")) {
			JSONArray arrayRespuestas = JSONFactoryUtil.createJSONArray(respuesta.getRespuestas());
			boolean answer_0 = Boolean.FALSE;
			for(int i=0; i<arrayRespuestas.length(); i++) {
				JSONObject objectRespuesta = arrayRespuestas.getJSONObject(i);
				if(objectRespuesta.getString("name").equals(nameInput)) {
					haveRespuesta = Boolean.TRUE;
					if( objectRespuesta.getString("value").equals("0")) {
						answer_0=Boolean.TRUE;
					}
					
					if(respuestaParam.equals("select_0") && answer_0) {
						cumpleSelect = Boolean.TRUE;
					}else if(respuestaParam.equals("select_1") && !answer_0) {
						cumpleSelect = Boolean.TRUE;
					}
				}
			}
		}
		if(!haveRespuesta && respuestaParam.equals("select_1")) {
			cumpleSelect = Boolean.TRUE;
		}
		
		return cumpleSelect;
	}
	
	/**
	 * 
	 * @param preguntas
	 * @param medidaParam
	 * @param compId
	 * @param versionId
	 * @return 
	 * @throws JSONException
	 */
	public static Boolean isCumpleRespuestasNAN(JSONArray preguntas, String medidaParam, Long compId, Long versionId, Long seccionId) throws JSONException{
		Boolean cumpleRespuestas = Boolean.TRUE;
		if(Validator.isNotNull(preguntas)) {
			for(int i = 0 ; i< preguntas.length() ; i++) {
				JSONObject object = preguntas.getJSONObject(i);
				if(medidaParam.equals(object.getString(PlanIgualdadCommonsPortletKeys.ID_NAN))) {
					Respuesta respuesta = RespuestaLocalServiceUtil.fetchRespuestaBySeccion(compId, versionId, seccionId);
					List<Pregunta> listPreguntas = PreguntaLocalServiceUtil.findBySeccion(seccionId);
					for(Pregunta pregunta: listPreguntas) {
						if(Long.toString(pregunta.getPreguntaId()).equals(object.getString(PlanIgualdadCommonsPortletKeys.PREGUNTA))) {
							boolean haveRespuesta= Boolean.FALSE;
							if(Validator.isNotNull(respuesta) && Validator.isNotNull(respuesta.getRespuestas()) && !respuesta.getRespuestas().isEmpty()) {
								if( respuesta.getSeccionId() != 0L && !respuesta.getRespuestas().equals("{\"data\":[{}]}")) {
									JSONArray jsonArrayRespuesta = JSONFactoryUtil.createJSONArray(respuesta.getRespuestas());
									for(int x = 0 ; x< jsonArrayRespuesta.length() ; x++) {
										JSONObject objectRespuesta = jsonArrayRespuesta.getJSONObject(x);
										if(objectRespuesta.getString("name").equals(Long.toString(pregunta.getPreguntaId())) && objectRespuesta.getString("name").equals(object.getString(PlanIgualdadCommonsPortletKeys.PREGUNTA))) {
											if(!objectRespuesta.getString("value").equals(object.getString(PlanIgualdadCommonsPortletKeys.RESPUESTA))) {
												cumpleRespuestas = Boolean.FALSE;
											}
											haveRespuesta = Boolean.TRUE;
										}
									}
									if(!haveRespuesta) {
										String valueRespuesta = getValueRespuesta(pregunta);
										if(!valueRespuesta.equals(object.getString(PlanIgualdadCommonsPortletKeys.RESPUESTA))) {
											cumpleRespuestas = Boolean.FALSE;
										}
									}
								}
							}else {
								String valueRespuesta = getValueRespuesta(pregunta);
								if(!valueRespuesta.equals(object.getString(PlanIgualdadCommonsPortletKeys.RESPUESTA))) {
									cumpleRespuestas = Boolean.FALSE;
								}
							}
						}
					}
				}
			}
		}
		return cumpleRespuestas;
	}
	
	public static Boolean isCumpleEstadisticaNANEst(JSONObject jsonObjectRepuesta, JSONArray preguntas, String medidaParam, Long compId, Long versionId) throws JSONException{
		Boolean cumpleRespuestas = Boolean.TRUE;
		if(Validator.isNotNull(preguntas)) {
			for(int i = 0 ; i< preguntas.length() ; i++) {
				JSONObject object = preguntas.getJSONObject(i);
				
				if(medidaParam.equals(object.getString(PlanIgualdadCommonsPortletKeys.ID_NAN))) {
					
					if(object.getString("pregunta").equals("puntos")) {
						if(jsonObjectRepuesta==null) {
							return Boolean.TRUE;
						}
						JSONArray jsonDistribuciones = jsonObjectRepuesta.getJSONArray("distribuciones");

						for(int y=0; y<jsonDistribuciones.length(); y++) {
							JSONObject distribucion = jsonDistribuciones.getJSONObject(y);
							
							if(Validator.isNotNull(distribucion) &&  !distribucion.getString("puntos").isEmpty() 
									&& !distribucion.equals("0")) {
								cumpleRespuestas = Boolean.FALSE;
							}
							
						}
					}
					
					if(object.getString("pregunta").equals("textarea")) {
						if(jsonObjectRepuesta==null) {
							return Boolean.FALSE;
						}
						if(Validator.isNull(jsonObjectRepuesta.getString(object.getString(PlanIgualdadCommonsPortletKeys.RESPUESTA))) || jsonObjectRepuesta.getString(object.getString(PlanIgualdadCommonsPortletKeys.RESPUESTA)).isEmpty()) {
							cumpleRespuestas = Boolean.FALSE;
						}
					}
				}
			}
		}
		
		return cumpleRespuestas;
	}
	
	/**
	 * 
	 * @param compId
	 * @param versionId
	 * @param respuestaIdPregunta
	 * @param respuestaParam
	 * @return
	 * @throws JSONException 
	 */
	public static Boolean isCumpleRespuestasEmpty(Long compId, Long versionId, String respuestaIdPregunta, String respuestaParam) throws JSONException {
		List<Respuesta> respuestasList = RespuestaLocalServiceUtil.fetchRespuestas(compId, versionId);
		Boolean cumpleRespuestas = Boolean.FALSE;
		
		if(Validator.isNotNull(respuestasList)) {
			for(Respuesta respuesta : respuestasList) {
				if(Validator.isNotNull(respuesta.getRespuestas()) && !respuesta.getRespuestas().isEmpty() 
						&& respuesta.getSeccionId() != 0L && !respuesta.getRespuestas().equals("{\"data\":[{}]}")) {
					JSONArray jsonArrayRespuesta = JSONFactoryUtil.createJSONArray(respuesta.getRespuestas());
					for(int x = 0 ; x< jsonArrayRespuesta.length() ; x++) {
						JSONObject objectRespuesta = jsonArrayRespuesta.getJSONObject(x);
						
						if(objectRespuesta.getString("name").equals(respuestaIdPregunta)) {
							_log.debug(">>PARAM: " +respuestaParam + " VALOR : " + objectRespuesta.getString("value"));
							if(PlanIgualdadCommonsPortletKeys.NOT_EMPTY.equals(respuestaParam)&& !objectRespuesta.getString("value").isEmpty()) {
								cumpleRespuestas = Boolean.TRUE;
								_log.debug("LO PONE A TRUE");
							}
							break;
						}
					}
				}
			}
		}
		return cumpleRespuestas;
	}
	
	/**
	 * 
	 * @param medidaDTO
	 * @return
	 */
	public static String getJSONDatosGenerales(MedidaDTO medidaDTO) {
		JSONObject jsonDatosGenerales = JSONFactoryUtil.createJSONObject();
		jsonDatosGenerales.put(PlanIgualdadCommonsPortletKeys.MATTER, Integer.parseInt(medidaDTO.getMateria()));
		jsonDatosGenerales.put(PlanIgualdadCommonsPortletKeys.MEASURE_NAME,  medidaDTO.getMedida());
		jsonDatosGenerales.put(PlanIgualdadCommonsPortletKeys.MEASURE_DESCRIPTION,  medidaDTO.getDescripcion().replace("\n", "\\n").replace("_", "\\n"));
		jsonDatosGenerales.put(PlanIgualdadCommonsPortletKeys.OBJECTIVES_PURSUED,  medidaDTO.getObjetivos().replace("\n", "\\n").replace("_", "\\n"));
		jsonDatosGenerales.put(PlanIgualdadCommonsPortletKeys.PRIORITY,  Integer.parseInt(medidaDTO.getPrioridad()));
		jsonDatosGenerales.put(PlanIgualdadCommonsPortletKeys.APPLY,  "Si");
		jsonDatosGenerales.put(PlanIgualdadCommonsPortletKeys.ASSOCIATED_RESOURCES,  medidaDTO.getRecursosAsociados().replace("\n", "\\n").replace("_", "\\n"));
		jsonDatosGenerales.put(PlanIgualdadCommonsPortletKeys.MONITORING_INDICATORS,  medidaDTO.getIndicadores().replace("\n", "\\n").replace("_", "\\n"));
		jsonDatosGenerales.put(PlanIgualdadCommonsPortletKeys.PERIODICITY,  medidaDTO.getPeriocidad());

		return jsonDatosGenerales.toString();
	}
	
	/**
	 * ADD PARAMETRIZACION
	 * @param compId
	 * @param versionId
	 * @param themeDisplay
	 * @param paramDTO
	 * @param tipo
	 * @param seccionId 
	 * @throws NoSuchParametricasFDDException
	 */
	public static void addParametrizacion(Long compId, Long versionId, ThemeDisplay themeDisplay, ParametrizacionDTO paramDTO, String tipo, Long seccionId){
		_log.debug("init addParametrizacion(Long compId, Long versionId, ThemeDisplay themeDisplay, ParametrizacionDTO paramDTO, String tipo)");

		ParametricasFDD fdd = ParametricasFDDLocalServiceUtil.findParametrica(compId, versionId, paramDTO.getIdParametrica());
		if(Validator.isNull(fdd)) {
			ParametricasFDDPK fddpk = new ParametricasFDDPK();
			fddpk.setCompId(compId);
			fddpk.setVersionId(versionId);
			fddpk.setIdParametrica(paramDTO.getIdParametrica());
			
			ParametricasFDD parametrica = ParametricasFDDLocalServiceUtil.createParametricasFDD(fddpk);
			parametrica.setContenido(paramDTO.getContenido());
			if(Validator.isNotNull(paramDTO.getMateria()) && !paramDTO.getMateria().isEmpty()) {
				parametrica.setMateria(Integer.valueOf(paramDTO.getMateria()));
			}
			parametrica.setTipo(tipo);
			parametrica.setSeccionId(seccionId);
			ParametricasFDDLocalServiceUtil.updateParametricasFDD(parametrica);
			_log.debug("Creada correctamente [" + tipo + "]" +" | idParametrica [" +paramDTO.getIdParametrica()+ "]");
		}else {
			_log.debug("Ya existe la parametriza, con el id: " + fdd.getIdParametrica() + " | " + fdd.getIdParametrica());
		}
	}
	
	/**
	 * DELETE PARAMETRIZACION
	 * @param compId
	 * @param versionId
	 * @param paramDTO
	 * @throws PortalException
	 */
	public static void deleteParametrizacion(Long compId,  Long versionId, ParametrizacionDTO paramDTO) throws PortalException {
		ParametricasFDD fdd = ParametricasFDDLocalServiceUtil.findParametrica(compId, versionId, paramDTO.getIdParametrica());

		if(Validator.isNotNull(fdd)) {
			_log.debug("Se esta eliminando la parametrizacion: " + paramDTO.getIdParametrica());
			ParametricasFDDPK fddpk = new ParametricasFDDPK();
			fddpk.setCompId(compId);
			fddpk.setVersionId(versionId);
			fddpk.setIdParametrica(paramDTO.getIdParametrica());
			ParametricasFDDLocalServiceUtil.deleteParametricasFDD(fddpk);
		}
	}
	
	public static boolean gestionEstadosAplica(MedidaDTO medidaDTO, String currentState, long compId, long versionId, ThemeDisplay themeDisplay) throws PortalException {
		boolean sigueProceso = true;
		if(medidaDTO.getIdEstadosAplicaSiempre().contains(",")) {
			String[] partsIdEstadosAplica = medidaDTO.getIdEstadosAplicaSiempre().split(",");
			for(String partIdEstado : partsIdEstadosAplica) {
				String partWhiteSpace = partIdEstado.replaceAll("\\s","");
				if(currentState.equals(partWhiteSpace)) {
					MedidaUtils.addMedida(compId, versionId, themeDisplay, medidaDTO);
					sigueProceso = false;
				}
			}
		}else if(currentState.equals(medidaDTO.getIdEstadosAplicaSiempre())){
			MedidaUtils.addMedida(compId, versionId, themeDisplay, medidaDTO);
			sigueProceso = false;
		}
		return sigueProceso;
	}
	
	public static boolean gestionEstadosAplicaNunca(MedidaDTO medidaDTO, String currentState, long compId) throws PortalException {
		boolean sigueProceso = true;
		if(medidaDTO.getIdEstadosAplicaNunca().contains(",")) {
			String[] partsIdEstadosAplica = medidaDTO.getIdEstadosAplicaNunca().split(",");
			for(String partIdEstado : partsIdEstadosAplica) {
				String partWhiteSpace = partIdEstado.replaceAll("\\s","");
				if(currentState.equals(partWhiteSpace)) {
					MedidaUtils.deleteMedida(compId, medidaDTO);
					sigueProceso = false;
				}
			}
		}else if(currentState.equals(medidaDTO.getIdEstadosAplicaNunca())){
			MedidaUtils.deleteMedida(compId, medidaDTO);
			sigueProceso = false;
		}
		return sigueProceso;
	}
	
	public static String getValueRespuesta(Pregunta pregunta) throws JSONException {
		String valueRespuesta = StringPool.BLANK;
		JSONObject contenidoPregunta = JSONFactoryUtil.createJSONObject(pregunta.getPregunta());
		
		if(pregunta.getTipo().equals("radio") || pregunta.getTipo().equals("checkbox")) {
			
			if(contenidoPregunta.getString("label").equals("cuestionario521")) {
				valueRespuesta="3";
			}else if(contenidoPregunta.getString("label").equals("cuestionario523") || contenidoPregunta.getString("label").equals("cuestionario517") || contenidoPregunta.getString("label").equals("cuestionario518") ||
					contenidoPregunta.getString("label").equals("cuestionario519") || contenidoPregunta.getString("label").equals("cuestionario522")  ||contenidoPregunta.getString("label").equals("cuestionario520")) {
				valueRespuesta="2";
			}else  {
				valueRespuesta="1";
			}
		}
		
		return valueRespuesta;
	}
	
	public static boolean isAllowManagementSetting(Long compId) {
		String idEstado = EstadoUtils.getIdEstado(compId);
		Boolean allowManagementSetting = Boolean.TRUE;
		if(Validator.isNotNull(idEstado) && !idEstado.isEmpty() && !idEstado.equals("0")) {
			allowManagementSetting = EstadoLocalServiceUtil.allowManagementSettings(Long.parseLong(idEstado));
		}
		return allowManagementSetting;
	}
}

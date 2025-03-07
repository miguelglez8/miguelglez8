package com.plan.igualdad.liferay.portlet.commons.web.parametrizaciones;

import com.liferay.dynamic.data.lists.model.DDLRecord;
import com.liferay.dynamic.data.mapping.storage.DDMFormFieldValue;
import com.liferay.dynamic.data.mapping.storage.DDMFormValues;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Validator;
import com.plan.igualdad.liferay.portlet.commons.web.constants.PlanIgualdadCommonsPortletKeys;
import com.plan.igualdad.liferay.portlet.commons.web.estado.EstadoUtils;
import com.plan.igualdad.liferay.portlet.manager.model.Pregunta;
import com.plan.igualdad.liferay.portlet.manager.model.Respuesta;
import com.plan.igualdad.liferay.portlet.manager.service.EstadoLocalServiceUtil;
import com.plan.igualdad.liferay.portlet.manager.service.PreguntaLocalServiceUtil;
import com.plan.igualdad.liferay.portlet.manager.service.RespuestaLocalServiceUtil;

import java.util.List;

public class PlanIgualdadCuestionarioUtils {

	private static Log _log = LogFactoryUtil.getLog(PlanIgualdadCuestionarioUtils.class);
	
	/**
	 * CREATEMEDIDA
	 * @param respuesta
	 * @param resourceRequest
	 * @param compId
	 * @param versionId 
	 * @param seccionId 
	 */
	public static void createMedida(ThemeDisplay themeDisplay, Long compId, Long versionId, List<DDLRecord> recordsParamList, List<DDLRecord> medidasRecordList, List<DDLRecord> debilidadRecordList, List<DDLRecord> fortalezaRecordList, List<DDLRecord> diagnosticoRecordList) {
		_log.debug("init createMedida---------------------------------");

		try {
			String idEstado = EstadoUtils.getIdEstado(compId);
			if(Validator.isNotNull(idEstado) && EstadoLocalServiceUtil.allowManagementSettings(Long.parseLong(idEstado))) {
				for (long seccionId = 1; seccionId <= 11; seccionId++) {
					Respuesta respuesta = RespuestaLocalServiceUtil.fetchRespuestaBySeccion(compId, versionId, seccionId);
					Respuesta respuestaSeccionUno = RespuestaLocalServiceUtil.fetchRespuestaBySeccion(compId, versionId, 1);
					if (Validator.isNull(respuestaSeccionUno)) {
						break;
					} else {
						boolean salimos = true;
						JSONArray jsonArrayRespuesta = JSONFactoryUtil.createJSONArray(respuestaSeccionUno.getRespuestas());
						for (int x = 0; x < jsonArrayRespuesta.length(); x++) {
							JSONObject objectRespuestaUno = jsonArrayRespuesta.getJSONObject(x);
							String respuestaIdPregunta = objectRespuestaUno.getString(PlanIgualdadCommonsPortletKeys.NAME);
							String valueRespuesta = objectRespuestaUno.getString(PlanIgualdadCommonsPortletKeys.VALUE);
							if (respuestaIdPregunta.equals("10") && !Validator.isBlank(valueRespuesta)) {
								salimos = false;
							}
						}
						if (salimos) {
							return;
						}

					}
					int contMatrix = 0;
					if (Validator.isNotNull(respuesta)) {
						JSONArray jsonArrayRespuesta = JSONFactoryUtil.createJSONArray(respuesta.getRespuestas());

						_log.debug(">> section: " + seccionId);
						if (Validator.isNotNull(jsonArrayRespuesta)) {

							if (jsonArrayRespuesta.toString().contains("matrix")) {
								String[] matrixs = jsonArrayRespuesta.toString().split("matrix");
								contMatrix = matrixs.length - 1;
							}
							List<Pregunta> listPreguntas = PreguntaLocalServiceUtil.findBySeccion(seccionId);
							for (Pregunta pregunta : listPreguntas) {
								if (pregunta.getSeccionId() == seccionId) {
									boolean haveRespuesta = Boolean.FALSE;

									for (int x = 0; x < jsonArrayRespuesta.length(); x++) {
										JSONObject objectRespuesta = jsonArrayRespuesta.getJSONObject(x);
										if (objectRespuesta.getString(PlanIgualdadCommonsPortletKeys.NAME).equals(Long.toString(pregunta.getPreguntaId()))) {
											haveRespuesta = Boolean.TRUE;

											String respuestaIdPregunta = objectRespuesta.getString(PlanIgualdadCommonsPortletKeys.NAME);
											String valueRespuesta = objectRespuesta.getString(PlanIgualdadCommonsPortletKeys.VALUE);
											String typeRespuesta = objectRespuesta.getString("type");

											gestionParametrizaciones(themeDisplay, recordsParamList, medidasRecordList, debilidadRecordList, fortalezaRecordList, diagnosticoRecordList, contMatrix, respuestaIdPregunta, valueRespuesta, compId, seccionId, versionId, typeRespuesta.equals("textarea") ? Boolean.TRUE : Boolean.FALSE);

										}
									}
									if (!haveRespuesta) {
										String valueRespuesta = GeneralUtils.getValueRespuesta(pregunta);
										gestionParametrizaciones(themeDisplay, recordsParamList, medidasRecordList, debilidadRecordList, fortalezaRecordList, diagnosticoRecordList, contMatrix, Long.toString(pregunta.getPreguntaId()), valueRespuesta, compId, seccionId, versionId, Boolean.FALSE);
									}
								}
							}


						}
					} else {
						_log.debug("NO HAY RESPUESTAS DE ESTA SECCION: " + seccionId);
						gestionParametrizaciones(themeDisplay, recordsParamList, medidasRecordList, debilidadRecordList, fortalezaRecordList, diagnosticoRecordList, contMatrix, null, null, compId, seccionId, versionId, Boolean.FALSE);
					}
				}
			}
			
		} catch (PortalException e) {
			_log.error("ERROR: ", e);
		}
		
		_log.debug("end createMedida");
	}
	
	private static void gestionParametrizaciones(ThemeDisplay themeDisplay, List<DDLRecord> recordsParamList, List<DDLRecord> medidasRecordList, List<DDLRecord> debilidadRecordList, List<DDLRecord> fortalezaRecordList, List<DDLRecord> diagnosticoRecordList, int contMatrix, String respuestaIdPregunta, String valueRespuesta, Long compId, long seccionId, Long versionId, Boolean checkTextarea) throws PortalException {
		
		for(DDLRecord recordParam : recordsParamList) {
			DDMFormValues formValuesParam = recordParam.getDDMFormValues();
			List<DDMFormFieldValue> formValuesParamList = formValuesParam.getDDMFormFieldValues();
			
			if(Validator.isNotNull(formValuesParamList)) {
				Boolean preguntaCorrecta = Boolean.FALSE;
				Long seccionParametrizacion = 0L;
				String jsonParametrizacion = StringPool.BLANK;
				for(DDMFormFieldValue formFieldParam : formValuesParamList) {
					if(PlanIgualdadCommonsPortletKeys.ID_PREGUNTA.equals(formFieldParam.getName())) {
						if(respuestaIdPregunta!=null) {
							preguntaCorrecta = (respuestaIdPregunta.equals(formFieldParam.getValue().getString(themeDisplay.getLocale())) || formFieldParam.getValue().getString(themeDisplay.getLocale()).equals("40") ) ? Boolean.TRUE : Boolean.FALSE;
						}else {
							List<Pregunta> listPreguntas = PreguntaLocalServiceUtil.findBySeccion(seccionId);
							for(Pregunta pregunta: listPreguntas) {
								if(!preguntaCorrecta && Long.toString(pregunta.getPreguntaId()).equals(formFieldParam.getValue().getString(themeDisplay.getLocale()))) {
									preguntaCorrecta = Boolean.TRUE;

									if(Validator.isNull(valueRespuesta)) {
										valueRespuesta = GeneralUtils.getValueRespuesta(pregunta);
										
									}
								}
							}
						}
					}
					
					if("idSeccion".equals(formFieldParam.getName())&& Validator.isNotNull(formFieldParam.getValue().getString(themeDisplay.getLocale()))) {
						seccionParametrizacion = Long.parseLong(formFieldParam.getValue().getString(themeDisplay.getLocale()));
					}
					
					if(PlanIgualdadCommonsPortletKeys.JSON_PARAMETROS.equals(formFieldParam.getName())&& Validator.isNotNull(formFieldParam.getValue().getString(themeDisplay.getLocale()))) {
						jsonParametrizacion = formFieldParam.getValue().getString(themeDisplay.getLocale());
					}
				}
				if(preguntaCorrecta && seccionParametrizacion == seccionId && !jsonParametrizacion.isEmpty()) {
					_log.debug(">>>>jsonParametrizacion: " + jsonParametrizacion);
					JSONObject jsonParametros = JSONFactoryUtil.createJSONObject(jsonParametrizacion);
					JSONArray respuestas = jsonParametros.getJSONArray(PlanIgualdadCommonsPortletKeys.RESPUESTAS);
					
					if(Validator.isNotNull(respuestas)) {
						for(int i = 0 ; i< respuestas.length() ; i++) {
							JSONObject jsonRespuesta = respuestas.getJSONObject(i);
							Boolean addMedida = jsonRespuesta.getString(PlanIgualdadCommonsPortletKeys.RESPUESTA).equals(valueRespuesta);
							if(checkTextarea && (jsonRespuesta.getString(PlanIgualdadCommonsPortletKeys.RESPUESTA).equals(PlanIgualdadCommonsPortletKeys.EMPTY) || jsonRespuesta.getString(PlanIgualdadCommonsPortletKeys.RESPUESTA).equals(PlanIgualdadCommonsPortletKeys.NOT_EMPTY))) {
								_log.debug(">> ES CHECKTEXTAREA");
								addMedida = GeneralUtils.isCumpleRespuestasEmpty(compId, versionId, respuestaIdPregunta, jsonRespuesta.getString(PlanIgualdadCommonsPortletKeys.RESPUESTA));
								_log.debug(">> ADD: " + addMedida);
							}
							
							ParametrizacionUtils.gestionParametrizacion(jsonRespuesta, addMedida , medidasRecordList,diagnosticoRecordList, fortalezaRecordList , debilidadRecordList, themeDisplay, compId, versionId, jsonParametros, respuestaIdPregunta, contMatrix, seccionId);
						}
					}
				}
			}
		}
	}
}

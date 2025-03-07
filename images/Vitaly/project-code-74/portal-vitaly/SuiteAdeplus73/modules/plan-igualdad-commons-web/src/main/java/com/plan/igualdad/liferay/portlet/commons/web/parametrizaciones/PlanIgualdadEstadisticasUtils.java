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
import com.plan.igualdad.liferay.portlet.manager.model.Estadistica;
import com.plan.igualdad.liferay.portlet.manager.model.Respuesta;
import com.plan.igualdad.liferay.portlet.manager.service.EstadisticaLocalServiceUtil;
import com.plan.igualdad.liferay.portlet.manager.service.EstadoLocalServiceUtil;
import com.plan.igualdad.liferay.portlet.manager.service.RespuestaLocalServiceUtil;
import com.plan.igualdad.liferay.portlet.manager.service.persistence.EstadisticaPK;

import java.util.List;

public class PlanIgualdadEstadisticasUtils {

	private static Log _log = LogFactoryUtil.getLog(PlanIgualdadEstadisticasUtils.class);

	public static void createMedida(ThemeDisplay themeDisplay, Long compId, Long versionId, List<DDLRecord> recordsParamList, List<DDLRecord> medidasRecordList, List<DDLRecord> debilidadRecordList, List<DDLRecord> fortalezaRecordList) {
		_log.debug("init createMedida");
        try {
			String idEstado = EstadoUtils.getIdEstado(compId);
			if(Validator.isNotNull(idEstado) && EstadoLocalServiceUtil.allowManagementSettings(Long.parseLong(idEstado))) {
				for (long seccionId = 1; seccionId <= 18; seccionId++) {

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


					EstadisticaPK estadisticaPK = new EstadisticaPK();
					estadisticaPK.setCompId(compId);
					estadisticaPK.setVersionId(versionId);
					estadisticaPK.setSeccionId(seccionId);

					Estadistica estadistica = EstadisticaLocalServiceUtil.fetchEstadistica(estadisticaPK);

					if (Validator.isNotNull(estadistica)) {
						JSONObject jsonObjectRespuesta = JSONFactoryUtil.createJSONObject(estadistica.getEstadistica());
						String valorMedida = jsonObjectRespuesta.getString(PlanIgualdadCommonsPortletKeys.MEDIDA);
						gestionParametrizaciones(themeDisplay, recordsParamList, medidasRecordList, debilidadRecordList, fortalezaRecordList, compId, seccionId, versionId, jsonObjectRespuesta, valorMedida);
					} else {
						_log.debug("NO HAY RESPUESTAS DE ESTA SECCION: " + seccionId);
						gestionParametrizaciones(themeDisplay, recordsParamList, medidasRecordList, debilidadRecordList, fortalezaRecordList, compId, seccionId, versionId, null, null);
					}
				}
			}
			
        } catch (PortalException e) {
			_log.error("ERROR: ", e);
        }
		_log.debug("end createMedida");
	}
	
	private static void gestionParametrizaciones(ThemeDisplay themeDisplay, List<DDLRecord> recordsParamList, List<DDLRecord> medidasRecordList, List<DDLRecord> debilidadRecordList, List<DDLRecord> fortalezaRecordList, Long compId, long seccionId, Long versionId, JSONObject jsonObjectRespuesta, String valorMedida) throws PortalException {
		if(Validator.isNull(valorMedida) || valorMedida.isEmpty()) {
			valorMedida ="1";
		}

		if((Validator.isNotNull(valorMedida) && !valorMedida.isEmpty()) || seccionId ==16 || seccionId == 17) {
			for(DDLRecord recordParam : recordsParamList) {
				DDMFormValues formValuesParam = recordParam.getDDMFormValues();
				
				List<DDMFormFieldValue> formValuesParamList = formValuesParam.getDDMFormFieldValues();
				
				if(Validator.isNotNull(formValuesParamList)) {
					
					boolean seccionCorrecta = Boolean.FALSE;
					boolean prodEstadistica = Boolean.FALSE;
					String jsonParametrizacion =StringPool.BLANK;
					for(DDMFormFieldValue formFieldParam : formValuesParamList) {
						if(PlanIgualdadCommonsPortletKeys.PROCEDENCIA.equals(formFieldParam.getName()) && "E".equals(formFieldParam.getValue().getString(themeDisplay.getLocale()))) {
							prodEstadistica = Boolean.TRUE;
						}
						
						if(PlanIgualdadCommonsPortletKeys.ID_SECCION.equals(formFieldParam.getName()) && Long.toString(seccionId).equals(formFieldParam.getValue().getString(themeDisplay.getLocale()))) {
							seccionCorrecta = Boolean.TRUE;
						}
						
						if(PlanIgualdadCommonsPortletKeys.JSON_PARAMETROS.equals(formFieldParam.getName())) {
							jsonParametrizacion = formFieldParam.getValue().getString(themeDisplay.getLocale());
						}
						
					}
					if(prodEstadistica && seccionCorrecta && !jsonParametrizacion.isEmpty()) {
						JSONObject jsonParametros = JSONFactoryUtil.createJSONObject(jsonParametrizacion);
						JSONArray respuestas = jsonParametros.getJSONArray(PlanIgualdadCommonsPortletKeys.RESPUESTAS);
						JSONArray preguntas = jsonParametros.getJSONArray(PlanIgualdadCommonsPortletKeys.PREGUNTAS);
						JSONArray medidaPreguntas = jsonParametros.getJSONArray(PlanIgualdadCommonsPortletKeys.MEDIDA_PREGUNTAS);
						
						if(Validator.isNotNull(respuestas)) {
							for(int i = 0 ; i< respuestas.length() ; i++) {
								JSONObject object = respuestas.getJSONObject(i);
								
								ParametrizacionUtils.gestionParametrizacionEst(jsonObjectRespuesta, object, object.getString(PlanIgualdadCommonsPortletKeys.RESPUESTA).equals(valorMedida), medidasRecordList, fortalezaRecordList , debilidadRecordList, themeDisplay, compId, versionId, preguntas, medidaPreguntas, seccionId);

								MedidaUtils.gestionMedidaEst(object, object.getString(PlanIgualdadCommonsPortletKeys.RESPUESTA).equals(valorMedida), 
										medidasRecordList, themeDisplay, compId, versionId, preguntas, medidaPreguntas);
								
							}
						}
					}
					

				}
			}
		}
	}
}

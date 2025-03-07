package com.plan.igualdad.liferay.portlet.commons.web.parametrizaciones;

import com.liferay.dynamic.data.lists.model.DDLRecord;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Validator;
import com.plan.igualdad.liferay.portlet.commons.web.constants.PlanIgualdadCommonsPortletKeys;

import java.util.List;

public class ParametrizacionUtils {

	private static Log _log = LogFactoryUtil.getLog(ParametrizacionUtils.class);
	
	/**
	 * GESTION PARAMETRIZACION
	 * @param medida
	 * @param addMedida
	 * @param medidasRecordList
	 * @param debilidadRecordList 
	 * @param fortalezaRecordList 
	 * @param diagnosticoRecordList 
	 * @param themeDisplay
	 * @param compId
	 * @param medidaPreguntas 
	 * @param preguntas 
	 * @param respuestaIdPregunta 
	 * @param contMatrix 
	 * @param seccionId 
	 * @throws PortalException
	 */
	public static void gestionParametrizacion(JSONObject jsonParametrizacion, Boolean addMedida, List<DDLRecord> medidasRecordList, List<DDLRecord> diagnosticoRecordList, List<DDLRecord> fortalezaRecordList, List<DDLRecord> debilidadRecordList, ThemeDisplay themeDisplay, Long compId, Long versionId, JSONObject jsonParametros, String respuestaIdPregunta, int contMatrix, Long seccionId) throws PortalException {
		JSONArray preguntas = jsonParametros.getJSONArray(PlanIgualdadCommonsPortletKeys.PREGUNTAS);
		JSONArray medidaPreguntas = jsonParametros.getJSONArray(PlanIgualdadCommonsPortletKeys.MEDIDA_PREGUNTAS);

		String medidaParam = jsonParametrizacion.getString(PlanIgualdadCommonsPortletKeys.MEDIDA);
		String debilidadParam = jsonParametrizacion.getString(PlanIgualdadCommonsPortletKeys.DEBILIDAD);
		String diagnosticoParam = jsonParametrizacion.getString(PlanIgualdadCommonsPortletKeys.DIAGNOSTICO);
		String fortalezaParam = jsonParametrizacion.getString(PlanIgualdadCommonsPortletKeys.FORTALEZA);
		
		_log.debug(">>>>diagnosticoParam: " + diagnosticoParam);
		_log.debug(">>>>debilidadParam: " + debilidadParam);
		_log.debug(">>>>fortalezaParam: " + fortalezaParam);
		_log.debug(">>>>medidaParam: " + medidaParam);
		
		String respuestaParam = jsonParametrizacion.getString(PlanIgualdadCommonsPortletKeys.RESPUESTA);
		_log.debug(">>>>respuestaParam: " + respuestaParam);
		_log.debug(">>>addMedida: " + addMedida);
		if(respuestaParam.contains("matrix")) {
			MedidaUtils.addOrDeleteMedida(medidasRecordList, medidaPreguntas, preguntas, themeDisplay, medidaParam,compId, versionId,  contMatrix > 1 ? Boolean.FALSE : Boolean.TRUE, contMatrix, seccionId, respuestaParam, respuestaIdPregunta);
			Boolean debilidadMatrix = contMatrix > 1 ? Boolean.FALSE : Boolean.TRUE;
			Boolean fortalezaMatrix = contMatrix <= 1 ? Boolean.FALSE : Boolean.TRUE;
			DebilidadUtils.addOrDeleteDebilidad(debilidadRecordList, medidaPreguntas, preguntas, medidaParam, themeDisplay, debilidadParam,compId, versionId, jsonParametrizacion, debilidadMatrix, seccionId, respuestaParam, respuestaIdPregunta);
			FortalezaUtils.addOrDeleteFortaleza(fortalezaRecordList, medidaPreguntas, preguntas, medidaParam, themeDisplay, fortalezaParam,compId, versionId, jsonParametrizacion, fortalezaMatrix, seccionId, respuestaParam, respuestaIdPregunta);
		}else {
			DiagnosticoUtils.addOrDeleteDiagnostico(diagnosticoRecordList, medidaPreguntas, preguntas, medidaParam, themeDisplay, diagnosticoParam, compId, versionId, jsonParametrizacion, addMedida, seccionId, respuestaParam, respuestaIdPregunta);
			DebilidadUtils.addOrDeleteDebilidad(debilidadRecordList, medidaPreguntas, preguntas, medidaParam, themeDisplay, debilidadParam,compId, versionId, jsonParametrizacion, addMedida, seccionId, respuestaParam, respuestaIdPregunta);
			FortalezaUtils.addOrDeleteFortaleza(fortalezaRecordList, medidaPreguntas, preguntas, medidaParam, themeDisplay, fortalezaParam,compId, versionId, jsonParametrizacion, addMedida, seccionId, respuestaParam, respuestaIdPregunta);
			MedidaUtils.addOrDeleteMedida(medidasRecordList, medidaPreguntas, preguntas, themeDisplay, medidaParam,compId, versionId,  addMedida, contMatrix, seccionId, respuestaParam, respuestaIdPregunta);
		}
	}
	
	public static void gestionParametrizacionEst(JSONObject jsonObjectRepuesta, JSONObject jsonParametrizacion, Boolean addMedida, List<DDLRecord> medidasRecordList, List<DDLRecord> fortalezaRecordList, List<DDLRecord> debilidadRecordList, ThemeDisplay themeDisplay, Long compId, Long versionId, JSONArray preguntas, JSONArray medidaPreguntas, Long seccionId) throws PortalException {
		String debilidadParam = jsonParametrizacion.getString(PlanIgualdadCommonsPortletKeys.DEBILIDAD);
		String fortalezaParam = jsonParametrizacion.getString(PlanIgualdadCommonsPortletKeys.FORTALEZA);
		
		if(debilidadParam.contains("NAN")) {
			if(Validator.isNotNull(medidaPreguntas)) {
				for(int i = 0 ; i< medidaPreguntas.length() ; i++) {
					JSONObject object = medidaPreguntas.getJSONObject(i);

					DebilidadUtils.addOrDeleteDebilidadEst(debilidadRecordList, themeDisplay, object.getString(PlanIgualdadCommonsPortletKeys.DEBILIDAD),compId, versionId, jsonParametrizacion, (debilidadParam.equals(object.getString(PlanIgualdadCommonsPortletKeys.ID_NAN)) &&
							object.getBoolean(PlanIgualdadCommonsPortletKeys.CUMPLE) == GeneralUtils.isCumpleEstadisticaNANEst(jsonObjectRepuesta, preguntas, debilidadParam, compId, versionId)) ? Boolean.TRUE : Boolean.FALSE, seccionId);
				}
				
				if(debilidadParam.contains(",")) {
					DebilidadUtils.addOrDeleteDebilidadEst(debilidadRecordList, themeDisplay, debilidadParam,compId, versionId, jsonParametrizacion, addMedida, seccionId);
				}
			}
		}
		
		if(fortalezaParam.contains("NAN")) {
			if(Validator.isNotNull(medidaPreguntas)) {
				for(int i = 0 ; i< medidaPreguntas.length() ; i++) {
					JSONObject object = medidaPreguntas.getJSONObject(i);

					FortalezaUtils.addOrDeleteFortalezaEst(fortalezaRecordList, themeDisplay, object.getString(PlanIgualdadCommonsPortletKeys.FORTALEZA),compId, versionId, jsonParametrizacion, (fortalezaParam.equals(object.getString(PlanIgualdadCommonsPortletKeys.ID_NAN)) &&
							object.getBoolean(PlanIgualdadCommonsPortletKeys.CUMPLE) == GeneralUtils.isCumpleEstadisticaNANEst(jsonObjectRepuesta, preguntas, fortalezaParam, compId, versionId)) ? Boolean.TRUE : Boolean.FALSE,  seccionId);
				}
				
				if(fortalezaParam.contains(",")) {
					FortalezaUtils.addOrDeleteFortalezaEst(fortalezaRecordList, themeDisplay, fortalezaParam,compId, versionId, jsonParametrizacion, addMedida, seccionId);
				}
			}
		}
	}
}

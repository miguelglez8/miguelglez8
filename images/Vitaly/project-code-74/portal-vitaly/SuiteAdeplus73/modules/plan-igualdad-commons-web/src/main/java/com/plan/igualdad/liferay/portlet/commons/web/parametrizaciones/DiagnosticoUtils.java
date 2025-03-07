package com.plan.igualdad.liferay.portlet.commons.web.parametrizaciones;

import com.liferay.dynamic.data.lists.model.DDLRecord;
import com.liferay.dynamic.data.mapping.storage.DDMFormFieldValue;
import com.liferay.dynamic.data.mapping.storage.DDMFormValues;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Validator;
import com.plan.igualdad.liferay.portlet.commons.web.constants.PlanIgualdadCommonsPortletKeys;
import com.plan.igualdad.liferay.portlet.commons.web.dto.ParametrizacionDTO;

import java.util.List;

public class DiagnosticoUtils {
		
	/**
	 * 
	 * @param diagnosticosRecordList
	 * @param medidaPreguntas
	 * @param preguntas
	 * @param medidaParam
	 * @param themeDisplay
	 * @param diagnosticoParam
	 * @param compId
	 * @param versionId
	 * @param jsonMedida
	 * @param addDiagnostico
	 * @param seccionId
	 * @param respuestaParam 
	 * @param respuestaIdPregunta 
	 * @throws PortalException
	 */
	public static void addOrDeleteDiagnostico(List<DDLRecord> diagnosticosRecordList, JSONArray medidaPreguntas, JSONArray preguntas, String medidaParam, ThemeDisplay themeDisplay, String diagnosticoParam, Long compId, Long versionId, JSONObject jsonMedida, Boolean addDiagnostico, Long seccionId, String respuestaParam, String respuestaIdPregunta) throws PortalException {
		if(Validator.isNotNull(diagnosticosRecordList) && diagnosticosRecordList.size()>0 && !diagnosticoParam.isEmpty()) {
			for(DDLRecord diagnosticosRecord : diagnosticosRecordList) {
				DDMFormValues diagnosticoValuesRecord = diagnosticosRecord.getDDMFormValues();			
				List<DDMFormFieldValue> diagnosticodValuesList = diagnosticoValuesRecord.getDDMFormFieldValues();
				
				if(Validator.isNotNull(diagnosticodValuesList)) {
					ParametrizacionDTO parametrizacionDTO = new ParametrizacionDTO();
					for(DDMFormFieldValue formFieldDiagnostico : diagnosticodValuesList) {
						String valueInputMedida = formFieldDiagnostico.getValue().getString(themeDisplay.getLocale());
						String nameInputMedida = formFieldDiagnostico.getName();
						
						if("IDDIAGNOSTICO".equals(nameInputMedida)) {
							parametrizacionDTO.setIdParametrica(valueInputMedida);
						}else if("MATERIA".equals(nameInputMedida)) {
							parametrizacionDTO.setMateria(valueInputMedida);
						}else if("DIAGNOSTICO".equals(nameInputMedida)) {
							parametrizacionDTO.setContenido(valueInputMedida);
						}
					}
					
					if(("select_0".equals(respuestaParam) || "select_1".equals(respuestaParam) )&&  diagnosticoParam.equals(parametrizacionDTO.getIdParametrica())) {
						Boolean addSelect = GeneralUtils.isCumpleSelect(respuestaIdPregunta, respuestaParam, compId, versionId, seccionId);												
						if(addSelect) {
							GeneralUtils.addParametrizacion(compId, versionId, themeDisplay, parametrizacionDTO, PlanIgualdadCommonsPortletKeys.DIAGNOSTICO, seccionId);
						}else {
							GeneralUtils.deleteParametrizacion(compId, versionId, parametrizacionDTO);
						}
					}else {
						if(diagnosticoParam.contains(",")) {
							String[] partsMedida = diagnosticoParam.split(",");
							for(String partMedida : partsMedida) {
								String partWhiteSpace = partMedida.replaceAll("\\s","");
								if(partWhiteSpace.equals(parametrizacionDTO.getIdParametrica())) {
									if(addDiagnostico) {
										GeneralUtils.addParametrizacion(compId, versionId, themeDisplay, parametrizacionDTO, PlanIgualdadCommonsPortletKeys.DIAGNOSTICO, seccionId);
									}else {
										GeneralUtils.deleteParametrizacion(compId, versionId, parametrizacionDTO);
									}
								}else if (partWhiteSpace.contains("NAN")) {
									if(Validator.isNotNull(medidaPreguntas)) {
										for(int i = 0 ; i< medidaPreguntas.length() ; i++) {
											JSONObject object = medidaPreguntas.getJSONObject(i);
											if(object.getString(PlanIgualdadCommonsPortletKeys.DIAGNOSTICO).equals(parametrizacionDTO.getIdParametrica())) {
												Boolean addDiagnostico_ = object.getBoolean(PlanIgualdadCommonsPortletKeys.CUMPLE) == GeneralUtils.isCumpleRespuestasNAN(preguntas, medidaParam, compId, versionId, seccionId);												
												if(addDiagnostico_) {
													GeneralUtils.addParametrizacion(compId, versionId, themeDisplay, parametrizacionDTO, PlanIgualdadCommonsPortletKeys.DIAGNOSTICO, seccionId);
												}else {
													GeneralUtils.deleteParametrizacion(compId, versionId, parametrizacionDTO);
												}
											}
										}
									}
								}
							}
						}else if(!diagnosticoParam.contains(",") && diagnosticoParam.contains("NAN")) {
							if(Validator.isNotNull(medidaPreguntas)) {
								for(int i = 0 ; i< medidaPreguntas.length() ; i++) {
									JSONObject object = medidaPreguntas.getJSONObject(i);
									if(object.getString(PlanIgualdadCommonsPortletKeys.DIAGNOSTICO).equals(parametrizacionDTO.getIdParametrica())) {
										Boolean addDiagnostico_ = object.getBoolean(PlanIgualdadCommonsPortletKeys.CUMPLE) == GeneralUtils.isCumpleRespuestasNAN(preguntas, medidaParam, compId, versionId, seccionId);
										if(addDiagnostico_) {
											GeneralUtils.addParametrizacion(compId, versionId, themeDisplay, parametrizacionDTO, PlanIgualdadCommonsPortletKeys.DIAGNOSTICO, seccionId);
										}else {
											GeneralUtils.deleteParametrizacion(compId, versionId, parametrizacionDTO);
										}
									}
								}
							}
						}else if (diagnosticoParam.equals(parametrizacionDTO.getIdParametrica())){
							if(addDiagnostico) {
								GeneralUtils.addParametrizacion(compId, versionId, themeDisplay, parametrizacionDTO, PlanIgualdadCommonsPortletKeys.DIAGNOSTICO, seccionId);
							}else {
								GeneralUtils.deleteParametrizacion(compId, versionId, parametrizacionDTO);
							}
						}
					}
				}
			}
		}
	}
}

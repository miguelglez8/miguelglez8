package com.plan.igualdad.liferay.portlet.commons.web.parametrizaciones;

import com.liferay.dynamic.data.lists.model.DDLRecord;
import com.liferay.dynamic.data.mapping.storage.DDMFormFieldValue;
import com.liferay.dynamic.data.mapping.storage.DDMFormValues;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Validator;
import com.plan.igualdad.liferay.portlet.commons.web.constants.PlanIgualdadCommonsPortletKeys;
import com.plan.igualdad.liferay.portlet.commons.web.dto.ParametrizacionDTO;

import java.util.List;

public class DebilidadUtils {
	private static Log _log = LogFactoryUtil.getLog(DebilidadUtils.class);

	/**
	 * 
	 * @param debilidadesRecordList
	 * @param medidaPreguntas
	 * @param preguntas
	 * @param medidaParam
	 * @param themeDisplay
	 * @param debilidadParam
	 * @param compId
	 * @param versionId
	 * @param jsonMedida
	 * @param addDebilidad
	 * @param seccionId
	 * @param respuestaParam 
	 * @param respuestaIdPregunta 
	 * @throws PortalException
	 */
	public static void addOrDeleteDebilidad(List<DDLRecord> debilidadesRecordList, JSONArray medidaPreguntas, JSONArray preguntas, String medidaParam, ThemeDisplay themeDisplay, String debilidadParam, Long compId, Long versionId, JSONObject jsonMedida, Boolean addDebilidad, Long seccionId, String respuestaParam, String respuestaIdPregunta) throws PortalException {
		if(Validator.isNotNull(debilidadesRecordList)&& !debilidadParam.isEmpty() && debilidadesRecordList.size()>0) {
			for(DDLRecord debilidadesRecord : debilidadesRecordList) {
				DDMFormValues debilidadValuesRecord = debilidadesRecord.getDDMFormValues();	
				List<DDMFormFieldValue> debilidadValuesList = debilidadValuesRecord.getDDMFormFieldValues();
				
				if(Validator.isNotNull(debilidadValuesList)) {
					ParametrizacionDTO parametrizacionDTO = new ParametrizacionDTO();
					for(DDMFormFieldValue formFieldDebilidad : debilidadValuesList) {
						String valueInputMedida = formFieldDebilidad.getValue().getString(themeDisplay.getLocale());
						String nameInputMedida = formFieldDebilidad.getName();
						
						if(PlanIgualdadCommonsPortletKeys.ID.equals(nameInputMedida)) {
							parametrizacionDTO.setIdParametrica(valueInputMedida);
						}else if(PlanIgualdadCommonsPortletKeys.MATERIA.equals(nameInputMedida)) {
							parametrizacionDTO.setMateria(valueInputMedida);
						}else if(PlanIgualdadCommonsPortletKeys.CONTENIDO.equals(nameInputMedida)) {
							parametrizacionDTO.setContenido(valueInputMedida);
						}
					}
					
					if(("select_0".equals(respuestaParam) || "select_1".equals(respuestaParam) )&&  debilidadParam.equals(parametrizacionDTO.getIdParametrica())) {
						Boolean addSelect = GeneralUtils.isCumpleSelect(respuestaIdPregunta, respuestaParam, compId, versionId, seccionId);												
						if(addSelect) {
							GeneralUtils.addParametrizacion(compId, versionId, themeDisplay, parametrizacionDTO, PlanIgualdadCommonsPortletKeys.DEBILIDAD, seccionId);
						}else {
							GeneralUtils.deleteParametrizacion(compId, versionId, parametrizacionDTO);
						}
					}else {
						if(debilidadParam.contains(",")) {
							String[] partsMedida = debilidadParam.split(",");
							for(String partMedida : partsMedida) {
								String partWhiteSpace = partMedida.replaceAll("\\s","");
								if(partWhiteSpace.equals(parametrizacionDTO.getIdParametrica())) {
									if(addDebilidad) {
										GeneralUtils.addParametrizacion(compId, versionId, themeDisplay, parametrizacionDTO, PlanIgualdadCommonsPortletKeys.DEBILIDAD, seccionId);
									}else {
										GeneralUtils.deleteParametrizacion(compId, versionId, parametrizacionDTO);
									}
								}else if (partWhiteSpace.contains("NAN")) {
									if(Validator.isNotNull(medidaPreguntas)) {
										for(int i = 0 ; i< medidaPreguntas.length() ; i++) {
											JSONObject object = medidaPreguntas.getJSONObject(i);
											if(object.getString(PlanIgualdadCommonsPortletKeys.DEBILIDAD).equals(parametrizacionDTO.getIdParametrica())) {
												Boolean addDebilidad_ = object.getBoolean(PlanIgualdadCommonsPortletKeys.CUMPLE) == GeneralUtils.isCumpleRespuestasNAN(preguntas, medidaParam, compId, versionId, seccionId);												
												if(addDebilidad_) {
													GeneralUtils.addParametrizacion(compId, versionId, themeDisplay, parametrizacionDTO, PlanIgualdadCommonsPortletKeys.DEBILIDAD, seccionId);
												}else {
													GeneralUtils.deleteParametrizacion(compId, versionId, parametrizacionDTO);
												}
											}
										}
									}
								}
							}
						}else if(!debilidadParam.contains(",") && debilidadParam.contains("NAN")) {
							if(Validator.isNotNull(medidaPreguntas)) {
								for(int i = 0 ; i< medidaPreguntas.length() ; i++) {
									JSONObject object = medidaPreguntas.getJSONObject(i);
									if(object.getString(PlanIgualdadCommonsPortletKeys.DEBILIDAD).equals(parametrizacionDTO.getIdParametrica())) {
										Boolean addDebilidad_ = object.getBoolean(PlanIgualdadCommonsPortletKeys.CUMPLE) == GeneralUtils.isCumpleRespuestasNAN(preguntas, medidaParam, compId, versionId, seccionId);
										_log.debug(">>>>addDebilidad_: "+addDebilidad_ );
										if(addDebilidad_) {
											GeneralUtils.addParametrizacion(compId, versionId, themeDisplay, parametrizacionDTO, PlanIgualdadCommonsPortletKeys.DEBILIDAD, seccionId);
										}else {
											GeneralUtils.deleteParametrizacion(compId, versionId, parametrizacionDTO);
										}
									}
								}
							}
						}else if (debilidadParam.equals(parametrizacionDTO.getIdParametrica())){
							if(addDebilidad) {
								GeneralUtils.addParametrizacion(compId, versionId, themeDisplay, parametrizacionDTO, PlanIgualdadCommonsPortletKeys.DEBILIDAD, seccionId);
							}else {
								GeneralUtils.deleteParametrizacion(compId, versionId, parametrizacionDTO);
							}
						}
					}
				}
			}
		}
	}
	
	public static void addOrDeleteDebilidadEst(List<DDLRecord> debilidadesRecordList, ThemeDisplay themeDisplay, String medidaParam, Long compId, Long versionId, JSONObject jsonMedida, Boolean addDebilidad, Long seccionId) throws PortalException {
		if(Validator.isNotNull(debilidadesRecordList) && debilidadesRecordList.size()>0) {
			for(DDLRecord debilidadesRecord : debilidadesRecordList) {
				DDMFormValues debilidadValuesRecord = debilidadesRecord.getDDMFormValues();
				
				List<DDMFormFieldValue> debilidadValuesList = debilidadValuesRecord.getDDMFormFieldValues();
				
				if(Validator.isNotNull(debilidadValuesList)) {
					Boolean haveDebilidad = Boolean.FALSE;
					ParametrizacionDTO parametrizacionDTO = new ParametrizacionDTO();
					for(DDMFormFieldValue formFieldDebilidad : debilidadValuesList) {
						String valueInputMedida = formFieldDebilidad.getValue().getString(themeDisplay.getLocale());
						String nameInputMedida = formFieldDebilidad.getName();
						
						if(PlanIgualdadCommonsPortletKeys.ID.equals(nameInputMedida)) {
							parametrizacionDTO.setIdParametrica(valueInputMedida);
						}else if(PlanIgualdadCommonsPortletKeys.MATERIA.equals(nameInputMedida)) {
							parametrizacionDTO.setMateria(valueInputMedida);
						}else if(PlanIgualdadCommonsPortletKeys.CONTENIDO.equals(nameInputMedida)) {
							parametrizacionDTO.setContenido(valueInputMedida);
						}
						
						if(!medidaParam.isEmpty() && medidaParam.contains(",")) {
							String[] partsMedida = medidaParam.split(",");
							
							for(String partMedida : partsMedida) {
								String partWhiteSpace = partMedida.replaceAll("\\s","");
								if(partWhiteSpace.equals(valueInputMedida)) {
									haveDebilidad= Boolean.TRUE ;
								}
							}
						}else if(!medidaParam.isEmpty() && medidaParam.equals(valueInputMedida)){
							haveDebilidad= Boolean.TRUE ;
						}
					}
					
					if(haveDebilidad) {
						if(addDebilidad) {
							GeneralUtils.addParametrizacion(compId, versionId, themeDisplay, parametrizacionDTO, PlanIgualdadCommonsPortletKeys.DEBILIDAD,seccionId);
						}else {
							GeneralUtils.deleteParametrizacion(compId, versionId, parametrizacionDTO);
						}
					}
				}
			}
		}
	}
}

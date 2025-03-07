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

public class FortalezaUtils {

	private static Log _log = LogFactoryUtil.getLog(FortalezaUtils.class);

	/**
	 * 
	 * @param fortalezaRecordList
	 * @param medidaPreguntas
	 * @param preguntas
	 * @param medidaParam
	 * @param themeDisplay
	 * @param fortalezaParam
	 * @param compId
	 * @param versionId
	 * @param jsonMedida
	 * @param addFortaleza
	 * @param seccionId
	 * @param respuestaParam 
	 * @param respuestaIdPregunta 
	 * @throws PortalException
	 */
	public static void addOrDeleteFortaleza(List<DDLRecord> fortalezaRecordList, JSONArray medidaPreguntas, JSONArray preguntas, String medidaParam, ThemeDisplay themeDisplay, String fortalezaParam, Long compId, Long versionId, JSONObject jsonMedida, Boolean addFortaleza, Long seccionId, String respuestaParam, String respuestaIdPregunta) throws PortalException {
		if(Validator.isNotNull(fortalezaRecordList)&& !fortalezaParam.isEmpty() && fortalezaRecordList.size()>0) {
			for(DDLRecord fortalezasRecord : fortalezaRecordList) {
				DDMFormValues fortalezaValuesRecord = fortalezasRecord.getDDMFormValues();			
				List<DDMFormFieldValue> fortalezaValuesList = fortalezaValuesRecord.getDDMFormFieldValues();
				
				if(Validator.isNotNull(fortalezaValuesList)) {
					ParametrizacionDTO parametrizacionDTO = new ParametrizacionDTO();
					for(DDMFormFieldValue formFieldFortaleza : fortalezaValuesList) {
						String valueInputMedida = formFieldFortaleza.getValue().getString(themeDisplay.getLocale());
						String nameInputMedida = formFieldFortaleza.getName();
						
						if("Id".equals(nameInputMedida)) {
							parametrizacionDTO.setIdParametrica(valueInputMedida);
						}else if(PlanIgualdadCommonsPortletKeys.MATERIA.equals(nameInputMedida)) {
							parametrizacionDTO.setMateria(valueInputMedida);
						}else if(PlanIgualdadCommonsPortletKeys.CONTENIDO.equals(nameInputMedida)) {
							parametrizacionDTO.setContenido(valueInputMedida);
						}
					}
					
					if(("select_0".equals(respuestaParam) || "select_1".equals(respuestaParam) )&& fortalezaParam.equals(parametrizacionDTO.getIdParametrica())) {
						Boolean addSelect = GeneralUtils.isCumpleSelect(respuestaIdPregunta, respuestaParam, compId, versionId, seccionId);												
						if(addSelect) {
							GeneralUtils.addParametrizacion(compId, versionId, themeDisplay, parametrizacionDTO, PlanIgualdadCommonsPortletKeys.FORTALEZA, seccionId);
						}else {
							GeneralUtils.deleteParametrizacion(compId, versionId, parametrizacionDTO);
						}
					}else {
						if(fortalezaParam.contains(",")) {
							String[] partsMedida = fortalezaParam.split(",");
							for(String partMedida : partsMedida) {
								String partWhiteSpace = partMedida.replaceAll("\\s","");
								if(partWhiteSpace.equals(parametrizacionDTO.getIdParametrica())) {
									if(addFortaleza) {
										GeneralUtils.addParametrizacion(compId, versionId, themeDisplay, parametrizacionDTO, PlanIgualdadCommonsPortletKeys.FORTALEZA, seccionId);
									}else {
										GeneralUtils.deleteParametrizacion(compId, versionId, parametrizacionDTO);
									}
								}else if (partWhiteSpace.contains("NAN")) {
									if(Validator.isNotNull(medidaPreguntas)) {
										for(int i = 0 ; i< medidaPreguntas.length() ; i++) {
											JSONObject object = medidaPreguntas.getJSONObject(i);
											if(object.getString(PlanIgualdadCommonsPortletKeys.FORTALEZA).equals(parametrizacionDTO.getIdParametrica())) {
												Boolean addFortaleza_ = object.getBoolean(PlanIgualdadCommonsPortletKeys.CUMPLE) == GeneralUtils.isCumpleRespuestasNAN(preguntas, medidaParam, compId, versionId, seccionId);												

												if(addFortaleza_) {
													GeneralUtils.addParametrizacion(compId, versionId, themeDisplay, parametrizacionDTO, PlanIgualdadCommonsPortletKeys.FORTALEZA, seccionId);
												}else {
													GeneralUtils.deleteParametrizacion(compId, versionId, parametrizacionDTO);
												}
											}
										}
									}
								}
							}
						}else if(!fortalezaParam.contains(",") && fortalezaParam.contains("NAN")) {
							if(Validator.isNotNull(medidaPreguntas)) {
								for(int i = 0 ; i< medidaPreguntas.length() ; i++) {
									JSONObject object = medidaPreguntas.getJSONObject(i);
									if(object.getString(PlanIgualdadCommonsPortletKeys.FORTALEZA).equals(parametrizacionDTO.getIdParametrica())) {
										Boolean addFortaleza_ = object.getBoolean(PlanIgualdadCommonsPortletKeys.CUMPLE) == GeneralUtils.isCumpleRespuestasNAN(preguntas, medidaParam, compId, versionId, seccionId);
										_log.debug(">>>>addFortaleza_: "+addFortaleza_);
										if(addFortaleza_) {
											GeneralUtils.addParametrizacion(compId, versionId, themeDisplay, parametrizacionDTO, PlanIgualdadCommonsPortletKeys.FORTALEZA, seccionId);
										}else {
											GeneralUtils.deleteParametrizacion(compId, versionId, parametrizacionDTO);
										}
									}
								}
							}
						}else if (fortalezaParam.equals(parametrizacionDTO.getIdParametrica())){
							if(addFortaleza) {
								GeneralUtils.addParametrizacion(compId, versionId, themeDisplay, parametrizacionDTO, PlanIgualdadCommonsPortletKeys.FORTALEZA, seccionId);
							}else {
								GeneralUtils.deleteParametrizacion(compId, versionId, parametrizacionDTO);
							}
						}
					}
				}
			}
		}
	}
	
	public static void addOrDeleteFortalezaEst(List<DDLRecord> fortalezaRecordList, ThemeDisplay themeDisplay, String medidaParam, Long compId, Long versionId, JSONObject jsonMedida, Boolean addFortaleza, Long seccionId) throws PortalException {
		if(Validator.isNotNull(fortalezaRecordList) && fortalezaRecordList.size()>0) {
			for(DDLRecord fortalezasRecord : fortalezaRecordList) {
				DDMFormValues fortalezaValuesRecord = fortalezasRecord.getDDMFormValues();
				
				List<DDMFormFieldValue> fortalezaValuesList = fortalezaValuesRecord.getDDMFormFieldValues();
				
				if(Validator.isNotNull(fortalezaValuesList)) {
					Boolean haveFortaleza = Boolean.FALSE;
					ParametrizacionDTO parametrizacionDTO = new ParametrizacionDTO();
					for(DDMFormFieldValue formFieldFortaleza : fortalezaValuesList) {
						String valueInputMedida = formFieldFortaleza.getValue().getString(themeDisplay.getLocale());
						String nameInputMedida = formFieldFortaleza.getName();
						
						if("Id".equals(nameInputMedida)) {
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
									haveFortaleza= Boolean.TRUE ;
								}
							}
						}else if(!medidaParam.isEmpty() && medidaParam.equals(valueInputMedida)){
							haveFortaleza= Boolean.TRUE ;
						}
					}
					
					if(haveFortaleza) {
						if(addFortaleza) {
							GeneralUtils.addParametrizacion(compId, versionId, themeDisplay, parametrizacionDTO, PlanIgualdadCommonsPortletKeys.FORTALEZA, seccionId);
						}else {
							GeneralUtils.deleteParametrizacion(compId, versionId, parametrizacionDTO);
						}
					}
				}
			}
		}
	}
}

package com.plan.igualdad.liferay.generar.informe.utils;

import com.liferay.dynamic.data.lists.model.DDLRecord;
import com.liferay.dynamic.data.lists.model.DDLRecordSet;
import com.liferay.dynamic.data.lists.service.DDLRecordLocalServiceUtil;
import com.liferay.dynamic.data.lists.service.DDLRecordSetLocalServiceUtil;
import com.liferay.dynamic.data.mapping.storage.DDMFormFieldValue;
import com.liferay.dynamic.data.mapping.storage.DDMFormValues;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.plan.igualdad.liferay.generar.informe.constants.PlanIgualdadGenerarInformePortletKeys;
import com.plan.igualdad.liferay.generar.informe.dto.SituacionIgualdadDTO;
import com.plan.igualdad.liferay.portlet.commons.web.constants.PlanIgualdadCommonsPortletKeys;
import com.plan.igualdad.liferay.portlet.manager.model.Estadistica;
import com.plan.igualdad.liferay.portlet.manager.model.ParametricasFDD;
import com.plan.igualdad.liferay.portlet.manager.service.EstadisticaLocalServiceUtil;
import com.plan.igualdad.liferay.portlet.manager.service.ParametricasFDDLocalServiceUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.portlet.ResourceRequest;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class ParametrizacionUtils {

	private static Log _log = LogFactoryUtil.getLog(ParametrizacionUtils.class);

	/**
	 * getParametersParametrizacion
	 * @param compId
	 * @param versionId
	 * @return
	 * @throws PortalException 
	 * @throws NumberFormatException 
	 */
	public static Map<String, Object> getParametersParametrizacion(Long compId, Long versionId, ResourceRequest resourceRequest) throws NumberFormatException, PortalException{
		Map<String, Object> parameters = new HashMap<>();
		ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);

		List<ParametricasFDD> fddsFortaleza = ParametricasFDDLocalServiceUtil.findParametricasByTipo(compId, versionId, PlanIgualdadGenerarInformePortletKeys.FORTALEZA);
		if(Validator.isNotNull(fddsFortaleza)) {
			DDLRecordSet recordSetFortaleza = DDLRecordSetLocalServiceUtil.getRecordSet(Long.parseLong(resourceRequest.getPreferences().getValue(PlanIgualdadGenerarInformePortletKeys.ID_FORTALEZA, "0")));
			
			for(ParametricasFDD fdd : fddsFortaleza) {
				parameters.put(fdd.getIdParametrica(), getContenido(themeDisplay, fdd.getIdParametrica(), fdd.getContenido(), recordSetFortaleza));
			}
		}
		
		List<ParametricasFDD> fddsDebilidad = ParametricasFDDLocalServiceUtil.findParametricasByTipo(compId, versionId, PlanIgualdadGenerarInformePortletKeys.DEBILIDAD);
		if(Validator.isNotNull(fddsDebilidad)) {
			DDLRecordSet recordSetDebilidad = DDLRecordSetLocalServiceUtil.getRecordSet(Long.parseLong(resourceRequest.getPreferences().getValue(PlanIgualdadGenerarInformePortletKeys.ID_DEBILIDAD, "0")));
			
			for(ParametricasFDD fdd : fddsDebilidad) {
				parameters.put(fdd.getIdParametrica(), getContenido(themeDisplay, fdd.getIdParametrica(), fdd.getContenido(), recordSetDebilidad));
			}
		}
		
		return parameters;
	}
	
	private static String getContenido(ThemeDisplay themeDisplay, String idParametrica, String contenido, DDLRecordSet recordSet) throws PortalException {
		List<DDLRecord> debilidadesRecordList = DDLRecordLocalServiceUtil.getRecords(recordSet.getRecordSetId());

		if(Validator.isNotNull(debilidadesRecordList)&& debilidadesRecordList.size()>0) {
			for(DDLRecord debilidadesRecord : debilidadesRecordList) {
				DDMFormValues debilidadValuesRecord = debilidadesRecord.getDDMFormValues();	
				List<DDMFormFieldValue> debilidadValuesList = debilidadValuesRecord.getDDMFormFieldValues();
				if(Validator.isNotNull(debilidadValuesList)) {
					boolean isParametrica = Boolean.FALSE;
					for(DDMFormFieldValue formFieldDebilidad : debilidadValuesList) {
						if(idParametrica.equals(formFieldDebilidad.getValue().getString(themeDisplay.getLocale()))) {
							isParametrica = Boolean.TRUE;
						}else if(isParametrica && PlanIgualdadCommonsPortletKeys.CONTENIDO.equals(formFieldDebilidad.getName())) {
							contenido= formFieldDebilidad.getValue().getString(themeDisplay.getLocale());
						}
					}
				}
			}
		}
		return contenido;
	}
	
	/**
	 * getParametersDiagnosticos
	 * @param compId
	 * @param versionId
	 * @return
	 * @throws JSONException 
	 */
	public static Map<String, Object> getParametersDiagnosticos(ThemeDisplay themeDisplay ,Long compId, Long versionId) throws JSONException{
		Map<String, Object> parameters = new HashMap<>();
        ResourceBundle bundle = ResourceBundle.getBundle("content/Language", themeDisplay.getUser().getLocale());
		
		parameters.putAll(getParametersByMateria(compId, versionId, 1, PlanIgualdadGenerarInformePortletKeys.SELECCION_CONTRATACION_BEAN, bundle));
		
		parameters.putAll(getParametersByMateria(compId, versionId, 10, PlanIgualdadGenerarInformePortletKeys.CLASIFICACION_PROFESIONAL_BEAN,bundle));

		parameters.putAll(getParametersByMateria(compId, versionId, 3, PlanIgualdadGenerarInformePortletKeys.FORMACION_BEAN, bundle));

		parameters.putAll(getParametersByMateria(compId, versionId, 4, PlanIgualdadGenerarInformePortletKeys.PROMOSION_PROFESIONAL_BEAN,bundle));
		
		parameters.putAll(getParametersByMateria(compId, versionId, 5, PlanIgualdadGenerarInformePortletKeys.CONDICIONES_TRABAJO_BEAN,bundle));

		parameters.putAll(getParametersByMateria(compId, versionId, 6, PlanIgualdadGenerarInformePortletKeys.EJERCICIO_CORRESPONSABLE_DERECHOS_BEAN,bundle));

		parameters.putAll(getParametersByMateria(compId, versionId, 7, PlanIgualdadGenerarInformePortletKeys.PREVENCION_ACOSO_SEXUAL_BEAN,bundle));
		
		parameters.putAll(getParametersByMateria(compId, versionId, 8, PlanIgualdadGenerarInformePortletKeys.DERECHOS_LABORALES_VICTIMAS_VG_BEAN,bundle));

		parameters.putAll(getParametersByMateria(compId, versionId, 9, PlanIgualdadGenerarInformePortletKeys.LENGUAJE_COMUNICACION_NO_SEXISTA_BEAN, bundle));
		
		return parameters;
	}
	
	/**
	 * 
	 * @param compId
	 * @param versionId
	 * @param materia
	 * @param nombreBean
	 * @return
	 * @throws JSONException 
	 */
	private static Map<String, Object> getParametersByMateria(Long compId, Long versionId, Integer materia, String nombreBean, ResourceBundle bundle) throws JSONException{
		Map<String, Object> parameters = new HashMap<>();
		
		List<ParametricasFDD> parametricas = ParametricasFDDLocalServiceUtil.findParametricasByMateria(compId, versionId, PlanIgualdadGenerarInformePortletKeys.DIAGNOSTICO, materia);
		_log.debug("PARAMETRICAS: " + materia + " | " + nombreBean);
		
		List<SituacionIgualdadDTO> listSituacionesIgualdad = new ArrayList<>();

		if(Validator.isNotNull(parametricas) && parametricas.size()>0) {
			
			for(ParametricasFDD fdd : parametricas) {
				_log.debug(">> " + fdd.getIdParametrica());
				listSituacionesIgualdad.add(new SituacionIgualdadDTO("- " +fdd.getContenido()));
			}
			
		}
		
		if(materia==1) {
			if(getSituacionIgualdadDTO(compId, versionId, 0L)!=null)listSituacionesIgualdad.add(getSituacionIgualdadDTO(compId, versionId, 0L));
			if(getSituacionIgualdadDTO(compId, versionId, 1L)!=null)listSituacionesIgualdad.add(getSituacionIgualdadDTO(compId, versionId, 1L));
			if(getSituacionIgualdadDTO(compId, versionId, 3L)!=null)listSituacionesIgualdad.add(getSituacionIgualdadDTO(compId, versionId, 3L));
			if(getSituacionIgualdadDTO(compId, versionId, 11L)!=null)listSituacionesIgualdad.add(getSituacionIgualdadDTO(compId, versionId, 11L));
			if(getSituacionIgualdadDTO(compId, versionId, 12L)!=null)listSituacionesIgualdad.add(getSituacionIgualdadDTO(compId, versionId, 12L));
		}else if(materia==10) {
			if(getSituacionIgualdadDTO(compId, versionId, 2L)!=null)listSituacionesIgualdad.add(getSituacionIgualdadDTO(compId, versionId, 2L));
		}else if(materia==3) {
			if(getSituacionIgualdadDTO(compId, versionId, 6L)!=null)listSituacionesIgualdad.add(getSituacionIgualdadDTO(compId, versionId, 6L));
			if(getSituacionIgualdadDTO(compId, versionId, 14L)!=null)listSituacionesIgualdad.add(getSituacionIgualdadDTO(compId, versionId, 14L));
		}else if(materia==4) {
			
		}else if(materia==5) {
			if(getSituacionIgualdadDTO(compId, versionId, 4L)!=null)listSituacionesIgualdad.add(getSituacionIgualdadDTO(compId, versionId, 4L));
			if(getSituacionIgualdadDTO(compId, versionId, 7L)!=null)listSituacionesIgualdad.add(getSituacionIgualdadDTO(compId, versionId, 7L));
			if(getSituacionIgualdadDTO(compId, versionId, 8L)!=null)listSituacionesIgualdad.add(getSituacionIgualdadDTO(compId, versionId, 8L));
			if(getSituacionIgualdadDTO(compId, versionId, 10L)!=null)listSituacionesIgualdad.add(getSituacionIgualdadDTO(compId, versionId, 10L));
			if(getSituacionIgualdadDTO(compId, versionId, 13L)!=null)listSituacionesIgualdad.add(getSituacionIgualdadDTO(compId, versionId, 13L));
		}else if(materia==6) {
			if(getSituacionIgualdadDTO(compId, versionId, 5L)!=null)listSituacionesIgualdad.add(getSituacionIgualdadDTO(compId, versionId, 5L));
			if(getSituacionIgualdadDTO(compId, versionId, 9L)!=null)listSituacionesIgualdad.add(getSituacionIgualdadDTO(compId, versionId, 9L));
		}
		
		if(Validator.isNull(listSituacionesIgualdad) || listSituacionesIgualdad.isEmpty()) {
			listSituacionesIgualdad.add(new SituacionIgualdadDTO(PlanIgualdadGenerarInformePortletKeys.DATO_NO_INFORMADO));
		}
		
		JRBeanCollectionDataSource beanSituacionIgualdad = new JRBeanCollectionDataSource(listSituacionesIgualdad);
		parameters.put(nombreBean, beanSituacionIgualdad);	

		return parameters;
	}
	
	/**
	 * 
	 * @param compId
	 * @param versionId
	 * @param seccionId
	 * @return
	 * @throws JSONException
	 */
	public static SituacionIgualdadDTO getSituacionIgualdadDTO (long compId, long versionId, long seccionId) throws JSONException {
		SituacionIgualdadDTO  igualdadDTO = null;
		Estadistica estadistica = EstadisticaLocalServiceUtil.fetchEstadisticasBySection(compId, versionId, seccionId);
		if(Validator.isNotNull(estadistica)) {
			JSONObject jsonEstadistica = JSONFactoryUtil.createJSONObject(estadistica.getEstadistica());
			if(Validator.isNotNull(jsonEstadistica)) {
				String valoracion = jsonEstadistica.getString(PlanIgualdadGenerarInformePortletKeys.VALORACION);
				if(!valoracion.isEmpty()) {
					igualdadDTO =new SituacionIgualdadDTO(GeneralUtils.deleteHTML(valoracion, Boolean.TRUE, Boolean.FALSE));
				}
			}
		}
		return igualdadDTO;
	}
	
	/**
	 * 
	 * @param compId
	 * @param versionId
	 * @param materia
	 * @param nombreBean
	 * @return
	 */
	public static JSONArray getArraySituacionesIgualdad(Long compId, Long versionId, Integer materia, String nombreBean){
		JSONArray jsonArrayListSituacionesIgualdad= JSONFactoryUtil.createJSONArray();
		
		List<ParametricasFDD> parametricas = ParametricasFDDLocalServiceUtil.findParametricasByMateria(compId, versionId, PlanIgualdadGenerarInformePortletKeys.DIAGNOSTICO, materia);
		_log.debug("PARAMETRICAS: " + materia + " | " + nombreBean);
		
		if(Validator.isNotNull(parametricas) && parametricas.size()>0) {
			
			for(ParametricasFDD fdd : parametricas) {
				_log.debug(">> " + fdd.getIdParametrica());
				JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
				jsonObject.put(PlanIgualdadGenerarInformePortletKeys.SITUACION_IGUALDAD, fdd.getContenido());
				jsonArrayListSituacionesIgualdad.put(jsonObject);
			}

		}
		return jsonArrayListSituacionesIgualdad;
	}
}

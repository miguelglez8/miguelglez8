package com.plan.igualdad.liferay.generar.informe.utils;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import com.plan.igualdad.liferay.generar.informe.constants.PlanIgualdadGenerarInformePortletKeys;
import com.plan.igualdad.liferay.portlet.manager.model.FechaHito;
import com.plan.igualdad.liferay.portlet.manager.service.FechaHitoLocalServiceUtil;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FechasRevelantesUtil {

	private static Log _log = LogFactoryUtil.getLog(FechasRevelantesUtil.class);

	/**
	 * getParameters Fechas Revelantes
	 * @param compId
	 * @param versionId
	 * @return
	 */
	public static Map<String, Object> getParameters (Long compId, Long versionId){
		Map<String, Object> parameters = new HashMap<>();
		
		parameters.put(PlanIgualdadGenerarInformePortletKeys.CONSULTOR_ASIGNADO, getFechaHito(compId, versionId, PlanIgualdadGenerarInformePortletKeys.CONSULTOR_ASIGNADO_ID, StringPool.BLANK));
		parameters.put(PlanIgualdadGenerarInformePortletKeys.PRESENTACION_PROCESO_CONSULTORIA, getFechaHito(compId, versionId, PlanIgualdadGenerarInformePortletKeys.PRESENTACION_PROCESO_CONSULTORIA_ID, StringPool.BLANK));
		parameters.put(PlanIgualdadGenerarInformePortletKeys.FORMALIZADO_COMPROMISO_ORGANIZACION, getFechaHito(compId, versionId, PlanIgualdadGenerarInformePortletKeys.FORMALIZADO_COMPROMISO_ORGANIZACION_ID, StringPool.BLANK));
		parameters.put(PlanIgualdadGenerarInformePortletKeys.APORTADA_INFORMACION_PRELIMINAR, getFechaHito(compId, versionId, PlanIgualdadGenerarInformePortletKeys.APORTADA_INFORMACION_PRELIMINAR_ID, StringPool.BLANK));
		parameters.put(PlanIgualdadGenerarInformePortletKeys.PROMOVIDA_NEGOCIACION_BANCO, getFechaHito(compId, versionId, PlanIgualdadGenerarInformePortletKeys.PROMOVIDA_NEGOCIACION_BANCO_ID, StringPool.BLANK));
		parameters.put(PlanIgualdadGenerarInformePortletKeys.CUMPLIMENTADO_VALORACION_PUESTOS, getFechaHito(compId, versionId, PlanIgualdadGenerarInformePortletKeys.CUMPLIMENTADO_VALORACION_PUESTOS_ID, StringPool.BLANK));
		parameters.put(PlanIgualdadGenerarInformePortletKeys.APORTADO_DATOS_PLANTILLA, getFechaHito(compId, versionId, PlanIgualdadGenerarInformePortletKeys.APORTADO_DATOS_PLANTILLA_ID, StringPool.BLANK));
		parameters.put(PlanIgualdadGenerarInformePortletKeys.APORTADOS_DATOS_SALARIALES, getFechaHito(compId, versionId, PlanIgualdadGenerarInformePortletKeys.APORTADOS_DATOS_SALARIALES_ID, StringPool.BLANK));
		parameters.put(PlanIgualdadGenerarInformePortletKeys.CUMPLIMENTADA_VALORACION_PUESTOS, getFechaHito(compId, versionId, PlanIgualdadGenerarInformePortletKeys.CUMPLIMENTADA_VALORACION_PUESTOS_ID, StringPool.BLANK));
		parameters.put(PlanIgualdadGenerarInformePortletKeys.CONSTITUIDA_COMISION_NEGOCIADORA, getFechaHito(compId, versionId, PlanIgualdadGenerarInformePortletKeys.CONSTITUIDA_COMISION_NEGOCIADORA_ID, StringPool.BLANK));
		parameters.put(PlanIgualdadGenerarInformePortletKeys.INFORMADA_PLANTILLA_PROCESO, getFechaHito(compId, versionId, PlanIgualdadGenerarInformePortletKeys.INFORMADA_PLANTILLA_PROCESO_ID, StringPool.BLANK));
		parameters.put(PlanIgualdadGenerarInformePortletKeys.REALIZADA_ENCUESTA_PLANTILLA, getFechaHito(compId, versionId, PlanIgualdadGenerarInformePortletKeys.REALIZADA_ENCUESTA_PLANTILLA_ID, StringPool.BLANK));
		parameters.put(PlanIgualdadGenerarInformePortletKeys.EMITIDO_INFORME_DIAGNOSTICO, getFechaHito(compId, versionId, PlanIgualdadGenerarInformePortletKeys.EMITIDO_INFORME_DIAGNOSTICO_ID, StringPool.BLANK));
		parameters.put(PlanIgualdadGenerarInformePortletKeys.EMITIDO_INFORME_AUDITORIA, getFechaHito(compId, versionId, PlanIgualdadGenerarInformePortletKeys.EMITIDO_INFORME_AUDITORIA_ID, StringPool.BLANK));
		parameters.put(PlanIgualdadGenerarInformePortletKeys.APROBADO_DIAGNOSTICO_HITO, getFechaHito(compId, versionId, PlanIgualdadGenerarInformePortletKeys.APROBADO_DIAGNOSTICO_HITO_ID, StringPool.BLANK));
		parameters.put(PlanIgualdadGenerarInformePortletKeys.FECHA_APROBACION, getFechaHito(compId, versionId, PlanIgualdadGenerarInformePortletKeys.APROBADO_DIAGNOSTICO_HITO_ID, PlanIgualdadGenerarInformePortletKeys.DATO_NO_INFORMADO_SHORT));
		parameters.put(PlanIgualdadGenerarInformePortletKeys.EMITIDO_PLAN_IGUALDAD, getFechaHito(compId, versionId, PlanIgualdadGenerarInformePortletKeys.EMITIDO_PLAN_IGUALDAD_ID, StringPool.BLANK));
		parameters.put(PlanIgualdadGenerarInformePortletKeys.APROBADO_PLAN_IGUALDAD, getFechaHito(compId, versionId, PlanIgualdadGenerarInformePortletKeys.APROBADO_PLAN_IGUALDAD_ID, StringPool.BLANK));
		parameters.put(PlanIgualdadGenerarInformePortletKeys.FECHA_APROBACION_PLAN, getFechaHito(compId, versionId, PlanIgualdadGenerarInformePortletKeys.APROBADO_PLAN_IGUALDAD_ID, PlanIgualdadGenerarInformePortletKeys.DATO_NO_INFORMADO_SHORT));
		parameters.put(PlanIgualdadGenerarInformePortletKeys.REGISTRADO_PLAN_IGUALDAD, getFechaHito(compId, versionId, PlanIgualdadGenerarInformePortletKeys.REGISTRADO_PLAN_IGUALDAD_ID, StringPool.BLANK));
		parameters.put(PlanIgualdadGenerarInformePortletKeys.FECHA_AUDITORIA_SALARIAL, getFechaHito(compId, versionId, PlanIgualdadGenerarInformePortletKeys.EMITIDO_INFORME_AUDITORIA_ID, PlanIgualdadGenerarInformePortletKeys.DATO_NO_INFORMADO_SHORT));

		return parameters;
	}
	
	/**
	 * 
	 * @param compId
	 * @param versionId
	 * @param hitoId
	 * @return
	 */
	private static String getFechaHito(Long compId, Long versionId, Long hitoId, String textoNoInformado) {
		String hito = !textoNoInformado.isEmpty() ? textoNoInformado : PlanIgualdadGenerarInformePortletKeys.DATO_NO_INFORMADO;

		List<FechaHito> listFechaHito = FechaHitoLocalServiceUtil.findByCompVersion(compId, versionId);
		
		_log.debug(">HITO ID: " + hitoId);
		if(Validator.isNotNull(listFechaHito)) {
			for(FechaHito fechaHito : listFechaHito) {
				if(fechaHito.getHitoId() == hitoId) {
					DateFormat dateFormat = new SimpleDateFormat(PlanIgualdadGenerarInformePortletKeys.FORMAT_DATE);  
					hito = dateFormat.format(fechaHito.getFecha());
					_log.debug(">>>HITO DATE: " + hito);
					break;
				}
			}
		}
		return hito;
	}
}

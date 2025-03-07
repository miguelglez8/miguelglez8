package com.plan.igualdad.liferay.generar.informe.utils;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Validator;
import com.plan.igualdad.liferay.generar.informe.constants.PlanIgualdadGenerarInformePortletKeys;
import com.plan.igualdad.liferay.portlet.manager.model.Valoracion;
import com.plan.igualdad.liferay.portlet.manager.service.ValoracionLocalServiceUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ValoracionConsultorUtils {

	public static Map<String, Object> getParameters(ThemeDisplay themeDisplay, Long compId) throws IOException, PortalException{
		Map<String, Object> parameters = new HashMap<>();
		
		List<Valoracion> valoraciones = ValoracionLocalServiceUtil.findByComp(compId);
		int versionActual = ValoracionLocalServiceUtil.countByComp(compId);
		
		String nivelEjecucion = PlanIgualdadGenerarInformePortletKeys.DATO_NO_INFORMADO;
		String cumplimientoPlanificacion = PlanIgualdadGenerarInformePortletKeys.DATO_NO_INFORMADO;
		String consecucionObjetivos = PlanIgualdadGenerarInformePortletKeys.DATO_NO_INFORMADO;
		String propuestaMedida = PlanIgualdadGenerarInformePortletKeys.DATO_NO_INFORMADO;
		
		if(Validator.isNotNull(valoraciones) && !valoraciones.isEmpty()) {
			for(Valoracion valoracion : valoraciones) {
				if(valoracion.getVersionValoracion() == versionActual) {
					JSONObject jsonRespuestas = JSONFactoryUtil.createJSONObject(valoracion.getRespuestasValoracion());
					
					if(jsonRespuestas.getString("answer1")!=null && !jsonRespuestas.getString("answer1").isEmpty()) nivelEjecucion = jsonRespuestas.getString("answer1");
					if(jsonRespuestas.getString("answer2")!=null && !jsonRespuestas.getString("answer2").isEmpty())cumplimientoPlanificacion = jsonRespuestas.getString("answer2");
					if(jsonRespuestas.getString("answer3")!=null && !jsonRespuestas.getString("answer3").isEmpty())consecucionObjetivos = jsonRespuestas.getString("answer3");
					if(valoracion.getObservaciones()!=null && !valoracion.getObservaciones().isEmpty())propuestaMedida = valoracion.getObservaciones();
				}
			}
		}
		
		parameters.put("nivelEjecucion", nivelEjecucion);
		parameters.put("cumplimentoPlanificacion", cumplimientoPlanificacion);
		parameters.put("consecucionObjetivos", consecucionObjetivos);
		parameters.put("propuestasMedidas", propuestaMedida);

		return parameters;
	}
}

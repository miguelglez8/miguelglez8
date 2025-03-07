package com.plan.igualdad.liferay.portlet.commons.web.indicadores;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.plan.igualdad.liferay.portlet.commons.web.constants.PlanIgualdadCommonsPortletKeys;
import com.plan.igualdad.liferay.portlet.manager.model.Evaluacion;
import com.plan.igualdad.liferay.portlet.manager.model.FechaHito;
import com.plan.igualdad.liferay.portlet.manager.model.Hito;
import com.plan.igualdad.liferay.portlet.manager.model.Medida;
import com.plan.igualdad.liferay.portlet.manager.service.EvaluacionLocalServiceUtil;
import com.plan.igualdad.liferay.portlet.manager.service.FechaHitoLocalServiceUtil;
import com.plan.igualdad.liferay.portlet.manager.service.HitoLocalServiceUtil;
import com.plan.igualdad.liferay.portlet.manager.service.MedidaLocalServiceUtil;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class IndicadoresUtils {

	/**
	 * 
	 * @param compId
	 * @param versionId
	 * @return
	 */
	public static long getIndicadorDisennoPlan(long compId, long versionId) {
        List<Hito> hitos = HitoLocalServiceUtil.getHitos(QueryUtil.ALL_POS, QueryUtil.ALL_POS)
                                               .stream()
                                               .filter(hito -> hito.getPeso().endsWith(PlanIgualdadCommonsPortletKeys.PESO_HITO))
                                               .collect(Collectors.toList());

        Set<Long> idHitos = hitos.stream()
                                 .map(Hito::getHitoId)
                                 .collect(Collectors.toSet());

        List<FechaHito> fechas = FechaHitoLocalServiceUtil.findByCompVersionEjecutados(compId, versionId)
                                               .stream()
                                               .filter(fechaHito -> idHitos.contains(fechaHito.getHitoId()))
                                               .collect(Collectors.toList());

        return calcularPorcentaje(fechas.size(), hitos.size());
    }

	/**
	 * 
	 * @param compId
	 * @param versionId
	 * @return
	 * @throws JSONException
	 */
    public static long getIndicadorSeguimientoPlan(long compId, long versionId) throws JSONException {
        List<Medida> medidas = MedidaLocalServiceUtil.findByCompVersion(compId, versionId);

        long medidasAplican = 0;
        long medidasFinalizadas = 0;

        for (Medida medida : medidas) {
            JSONObject datosGenerales = JSONFactoryUtil.createJSONObject(medida.getDatosGenerales());
            JSONObject cumplimentacion = JSONFactoryUtil.createJSONObject(medida.getCumplimentacion());

            String apply = datosGenerales.getString(PlanIgualdadCommonsPortletKeys.MEDIDA_APLICA);           //Medidas que aplican
            String execution = cumplimentacion.getString(PlanIgualdadCommonsPortletKeys.MEDIDA_EJECUCION);   //Medidas finalizadas

            if (PlanIgualdadCommonsPortletKeys.MEDIDA_APLICABLE.equals(apply)) {
                if (PlanIgualdadCommonsPortletKeys.MEDIDA_FINALIZADA.equals(execution)) medidasFinalizadas = medidasFinalizadas + 1;
                medidasAplican = medidasAplican + 1;
            }
        }
        return calcularPorcentaje(medidasFinalizadas, medidasAplican);
    }

    /**
     * 
     * @param compId
     * @param versionId
     * @return
     * @throws JSONException
     */
    public static long getIndicadorEvaluacionPlan(long compId, long versionId) throws JSONException {

        List<Evaluacion> evaluaciones = EvaluacionLocalServiceUtil.findByCompVersion(compId, versionId);

        if (evaluaciones.size() == 0) {
            return 0;
        } else {
            Evaluacion evaluacion = evaluaciones.stream().max(Comparator.comparingLong(Evaluacion::getVersionEvaluacion)).orElse(null);
            JSONObject datosGenerales = JSONFactoryUtil.createJSONObject(evaluacion.getDatosGenerales());

            String tipo = datosGenerales.getString(PlanIgualdadCommonsPortletKeys.EVALUACION_TIPO, "0");
            return PlanIgualdadCommonsPortletKeys.EVALUACION_FINAL.equals(tipo) ? 100 : 50;
        }
    }

    /**
     * 
     * @param value
     * @param total 
     * @return
     */
    private static long calcularPorcentaje(long value, long total) {
        if (total == 0) return 0;
        return Math.round((value * 100) / total);
    }
    
}

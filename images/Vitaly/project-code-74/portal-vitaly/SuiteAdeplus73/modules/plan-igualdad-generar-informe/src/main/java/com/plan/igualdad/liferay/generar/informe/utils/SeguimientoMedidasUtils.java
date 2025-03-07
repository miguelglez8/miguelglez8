package com.plan.igualdad.liferay.generar.informe.utils;

import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.util.Validator;
import com.plan.igualdad.liferay.generar.informe.constants.PlanIgualdadGenerarInformePortletKeys;
import com.plan.igualdad.liferay.generar.informe.dto.MedidaDTO;
import com.plan.igualdad.liferay.generar.informe.enums.MateriaEnum;
import com.plan.igualdad.liferay.generar.informe.enums.PrioridadEnum;
import com.plan.igualdad.liferay.portlet.manager.model.Medida;
import com.plan.igualdad.liferay.portlet.manager.service.MedidaLocalServiceUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class SeguimientoMedidasUtils {
	/**
	 * 
	 * @param compId
	 * @param versionId
	 * @param bundle
	 * @return
	 * @throws PortalException 
	 */
	public static Map<String, Object> getParameters (Long compId, Long versionId, ResourceBundle bundle, Boolean getNoMedida) throws PortalException{
		Map<String, Object> parameters = new HashMap<>();
		
		List<Medida> listMedidas = MedidaLocalServiceUtil.findByCompVersion(compId, versionId);
		
		List<MedidaDTO> medidasSeleccionContratacion = new ArrayList<>();
		List<MedidaDTO> medidasClasificacionProfesional = new ArrayList<>();
		List<MedidaDTO> medidasFormacion = new ArrayList<>();
		List<MedidaDTO> medidasPromocionProfesional = new ArrayList<>();
		List<MedidaDTO> medidasAuditoria = new ArrayList<>();
		List<MedidaDTO> medidasCondicionesTrabajo = new ArrayList<>();
		List<MedidaDTO> medidasEjercicio = new ArrayList<>();
		List<MedidaDTO> medidasInfraestructuraFemenina = new ArrayList<>();
		List<MedidaDTO> medidasPrevencionAcoso = new ArrayList<>();
		List<MedidaDTO> medidasDerechosLaborales = new ArrayList<>();
		List<MedidaDTO> medidasLenguajeComunicacion = new ArrayList<>();
		List<MedidaDTO> medidasAltaPrioridad = new ArrayList<>();
		List<MedidaDTO> medidasConDocumento = new ArrayList<>();
		
		List<MedidaDTO> medidasSeleccionContratacionSeg = new ArrayList<>();
		List<MedidaDTO> medidasClasificacionProfesionalSeg = new ArrayList<>();
		List<MedidaDTO> medidasFormacionSeg = new ArrayList<>();
		List<MedidaDTO> medidasPromocionProfesionalSeg = new ArrayList<>();
		List<MedidaDTO> medidasAuditoriaSeg = new ArrayList<>();
		List<MedidaDTO> medidasCondicionesTrabajoSeg = new ArrayList<>();
		List<MedidaDTO> medidasEjercicioSeg = new ArrayList<>();
		List<MedidaDTO> medidasInfraestructuraFemeninaSeg = new ArrayList<>();
		List<MedidaDTO> medidasPrevencionAcosoSeg = new ArrayList<>();
		List<MedidaDTO> medidasDerechosLaboralesSeg = new ArrayList<>();
		List<MedidaDTO> medidasLenguajeComunicacionSeg = new ArrayList<>();
		List<MedidaDTO> medidasAltaPrioridadSeg = new ArrayList<>();
		
		List<MedidaDTO> medidasDTO = new ArrayList<>();
		int nMedidasPlan= 0;
		int nMedidasInformadoSeguimiento = 0;
		int nMedidasPendientes= 0;
		int nMedidasEjecucion = 0;
		int nMedidasFinalizadas = 0;
		int nMedidasPrioritariasFinalizadas = 0;
		
		if(Validator.isNotNull(listMedidas)) {
			for(Medida medida : listMedidas) {
				boolean aplicaSi = Boolean.FALSE;
				if(Validator.isNotNull(medida.getDatosGenerales()) && !medida.getDatosGenerales().isEmpty()) {
					MedidaDTO medidaDTO = new MedidaDTO();
					JSONObject datosGenerales = JSONFactoryUtil.createJSONObject(medida.getDatosGenerales());
					
					if(datosGenerales.getString("apply").equals("Si")) {
						int matter = Integer.parseInt(datosGenerales.getString("matter"));
						int priority = Integer.parseInt(datosGenerales.getString("priority"));
						
						medidaDTO.setDescripcion(datosGenerales.getString("measureDescription").replaceAll("\\\\n", "<br>"));
						medidaDTO.setIndicadores(datosGenerales.getString("monitoringIndicators").replaceAll("\\\\n", "<br>"));
						medidaDTO.setMateria(LanguageUtil.get(bundle,MateriaEnum.getDescripcionByCodigo(matter)));
						medidaDTO.setNombreMedida(datosGenerales.getString("measureName"));
						medidaDTO.setObjetivos(datosGenerales.getString("objectivesPursued").replaceAll("\\\\n", "<br>"));
						medidaDTO.setPrioridad(LanguageUtil.get(bundle,PrioridadEnum.getDescripcionByCodigo(priority)));
						medidaDTO.setRecursos(datosGenerales.getString("associatedResources").replaceAll("\\\\n", "<br>"));
						medidaDTO.setNoMedida(Boolean.FALSE);
						medidaDTO.setRecurrente(datosGenerales.getString("apply"));
						
						Boolean prioridadAlta = Boolean.FALSE;
							
						if("si".equals(datosGenerales.getString(PlanIgualdadGenerarInformePortletKeys.APPLY)) || "Si".equals(datosGenerales.getString(PlanIgualdadGenerarInformePortletKeys.APPLY))) {
							nMedidasPlan++;
							aplicaSi = Boolean.TRUE;
							
							if(datosGenerales.getInt(PlanIgualdadGenerarInformePortletKeys.PRIORITY)==2) {
								prioridadAlta = Boolean.TRUE;
							}
						}
						
						if(aplicaSi) {
							if(Validator.isNotNull(medida.getCumplimentacion()) && !medida.getCumplimentacion().isEmpty()) {
								JSONObject cumplimentacion = JSONFactoryUtil.createJSONObject(medida.getCumplimentacion());
								medidaDTO.setResponsable(cumplimentacion.getString("responsible"));
								medidaDTO.setFechaImplantacion(cumplimentacion.getString("implantationDate"));
								medidaDTO.setFechaSeguimiento(cumplimentacion.getString("tracingDate"));
								medidaDTO.setFechaPrevista(cumplimentacion.getString("plannedDate"));
								medidaDTO.setNivelEjecucion(Integer.toString(cumplimentacion.getInt("execution")));
								medidaDTO.setMotivo(Integer.toString(cumplimentacion.getInt("reason")));
								medidaDTO.setOtrosMotivos(cumplimentacion.getString("reasonOther"));
								medidaDTO.setRecursosAsociados(GeneralUtils.deleteHTML(cumplimentacion.getString("resourcesAssigns"), Boolean.FALSE, Boolean.TRUE));
								medidaDTO.setDificultadesEncontradas(GeneralUtils.deleteHTML(cumplimentacion.getString("difficultiesImplantation"), Boolean.FALSE, Boolean.TRUE));
								medidaDTO.setSolucionesAdoptadas(GeneralUtils.deleteHTML(cumplimentacion.getString("solutionsAdopted"), Boolean.FALSE, Boolean.TRUE));
								medidaDTO.setReduccionDesigualdades(GeneralUtils.deleteHTML(cumplimentacion.getString("reductionInequalities"), Boolean.FALSE, Boolean.TRUE));
								medidaDTO.setMejorasProducidas(GeneralUtils.deleteHTML(cumplimentacion.getString("producedImprovements"), Boolean.FALSE, Boolean.TRUE));
								medidaDTO.setPropuestasFuturo(GeneralUtils.deleteHTML(cumplimentacion.getString("proposalFuture"), Boolean.FALSE, Boolean.TRUE));
								medidaDTO.setDocumentacionAcreditativa(GeneralUtils.deleteHTML(cumplimentacion.getString("documentationText"), Boolean.FALSE, Boolean.TRUE));
								
								if(Validator.isNotNull(cumplimentacion.getLong("uploadFile")) && cumplimentacion.getLong("uploadFile")!=0L) {
									FileEntry entry = DLAppServiceUtil.getFileEntry(cumplimentacion.getLong("uploadFile"));
									if(Validator.isNotNull(entry)) {
										String dateSubida = new SimpleDateFormat("dd/MM/yyyy").format(entry.getCreateDate());
		
										medidaDTO.setFechaSubidaDocumento(dateSubida);
										medidasConDocumento.add(medidaDTO);
									}
								}
								
								if(cumplimentacion.getString(PlanIgualdadGenerarInformePortletKeys.TRACING_DATE) != null && !cumplimentacion.getString(PlanIgualdadGenerarInformePortletKeys.TRACING_DATE).isEmpty()) {
									nMedidasInformadoSeguimiento++;
								}
								
								if(cumplimentacion.getInt(PlanIgualdadGenerarInformePortletKeys.EXECUTION) == 1) {
									nMedidasPendientes++;
								} else if(cumplimentacion.getInt(PlanIgualdadGenerarInformePortletKeys.EXECUTION) == 2) {
									nMedidasEjecucion++;
								} else if(cumplimentacion.getInt(PlanIgualdadGenerarInformePortletKeys.EXECUTION) == 3) {
									nMedidasFinalizadas++;
									
									if(prioridadAlta) {
										nMedidasPrioritariasFinalizadas++;
									}
								}
								
								if(Validator.isNotNull(cumplimentacion)) {
									if(matter ==1) medidasSeleccionContratacionSeg.add(medidaDTO);
									if(matter ==2) medidasAuditoriaSeg.add(medidaDTO);
									if(matter ==3) medidasFormacionSeg.add(medidaDTO);
									if(matter ==4) medidasPromocionProfesionalSeg.add(medidaDTO);
									if(matter ==5) medidasCondicionesTrabajoSeg.add(medidaDTO);
									if(matter ==6) medidasEjercicioSeg.add(medidaDTO);
									if(matter ==7) medidasPrevencionAcosoSeg.add(medidaDTO);
									if(matter ==8) medidasDerechosLaboralesSeg.add(medidaDTO);
									if(matter ==9) medidasLenguajeComunicacionSeg.add(medidaDTO);
									if(matter ==10) medidasClasificacionProfesionalSeg.add(medidaDTO);
									if(matter ==11) medidasInfraestructuraFemeninaSeg.add(medidaDTO);
									
									if(priority==2) medidasAltaPrioridadSeg.add(medidaDTO);
								}
							}else {
								medidaDTO.setResponsable(StringPool.BLANK);
								medidaDTO.setFechaImplantacion(StringPool.BLANK);
								medidaDTO.setFechaSeguimiento(StringPool.BLANK);
								medidaDTO.setNivelEjecucion("1");
								medidaDTO.setMotivo("1000");
								medidaDTO.setRecursosAsociados(StringPool.BLANK);
								medidaDTO.setDificultadesEncontradas(StringPool.BLANK);
								medidaDTO.setSolucionesAdoptadas(StringPool.BLANK);
								medidaDTO.setReduccionDesigualdades(StringPool.BLANK);
								medidaDTO.setMejorasProducidas(StringPool.BLANK);
								medidaDTO.setPropuestasFuturo(StringPool.BLANK);
								medidaDTO.setDocumentacionAcreditativa(StringPool.BLANK);
								
								nMedidasPendientes++;
							}
							
							medidasDTO.add(medidaDTO);
		
							if(matter ==1) medidasSeleccionContratacion.add(medidaDTO);
							if(matter ==2) medidasAuditoria.add(medidaDTO);
							if(matter ==3) medidasFormacion.add(medidaDTO);
							if(matter ==4) medidasPromocionProfesional.add(medidaDTO);
							if(matter ==5) medidasCondicionesTrabajo.add(medidaDTO);
							if(matter ==6) medidasEjercicio.add(medidaDTO);
							if(matter ==7) medidasPrevencionAcoso.add(medidaDTO);
							if(matter ==8) medidasDerechosLaborales.add(medidaDTO);
							if(matter ==9) medidasLenguajeComunicacion.add(medidaDTO);
							if(matter ==10) medidasClasificacionProfesional.add(medidaDTO);
							if(matter ==11) medidasInfraestructuraFemenina.add(medidaDTO);
							
							if(priority==2) medidasAltaPrioridad.add(medidaDTO);
						}
					}
				}
			}
		}
		
		if(getNoMedida) {
			if(medidasSeleccionContratacion.isEmpty()) medidasSeleccionContratacion.add(getNoMedida());
			if(medidasClasificacionProfesional.isEmpty())medidasClasificacionProfesional.add(getNoMedida());
			if(medidasFormacion.isEmpty())medidasFormacion.add(getNoMedida());
			if(medidasPromocionProfesional.isEmpty())medidasPromocionProfesional.add(getNoMedida());
			if(medidasAuditoria.isEmpty()) medidasAuditoria.add(getNoMedida());
			if(medidasCondicionesTrabajo.isEmpty()) medidasCondicionesTrabajo.add(getNoMedida());
			if(medidasEjercicio.isEmpty())medidasEjercicio.add(getNoMedida());
			if(medidasInfraestructuraFemenina.isEmpty())medidasInfraestructuraFemenina.add(getNoMedida());
			if(medidasPrevencionAcoso.isEmpty())medidasPrevencionAcoso.add(getNoMedida());
			if(medidasDerechosLaborales.isEmpty()) medidasDerechosLaborales.add(getNoMedida());
			if(medidasLenguajeComunicacion.isEmpty()) medidasLenguajeComunicacion.add(getNoMedida());
			if(medidasSeleccionContratacionSeg.isEmpty()) medidasSeleccionContratacionSeg.add(getNoMedida());
			if(medidasClasificacionProfesionalSeg.isEmpty())medidasClasificacionProfesionalSeg.add(getNoMedida());
			if(medidasFormacionSeg.isEmpty())medidasFormacionSeg.add(getNoMedida());
			if(medidasPromocionProfesionalSeg.isEmpty())medidasPromocionProfesionalSeg.add(getNoMedida());
			if(medidasAuditoriaSeg.isEmpty()) medidasAuditoriaSeg.add(getNoMedida());
			if(medidasCondicionesTrabajoSeg.isEmpty()) medidasCondicionesTrabajoSeg.add(getNoMedida());
			if(medidasEjercicioSeg.isEmpty())medidasEjercicioSeg.add(getNoMedida());
			if(medidasInfraestructuraFemeninaSeg.isEmpty())medidasInfraestructuraFemeninaSeg.add(getNoMedida());
			if(medidasPrevencionAcosoSeg.isEmpty())medidasPrevencionAcosoSeg.add(getNoMedida());
			if(medidasDerechosLaboralesSeg.isEmpty()) medidasDerechosLaboralesSeg.add(getNoMedida());
			if(medidasLenguajeComunicacionSeg.isEmpty()) medidasLenguajeComunicacionSeg.add(getNoMedida());
		}
		
		JRBeanCollectionDataSource beanMedidasSeleccionContratacion = new JRBeanCollectionDataSource(medidasSeleccionContratacion);
		parameters.put(PlanIgualdadGenerarInformePortletKeys.MEDIDA_SELECCION_CONTRATACION, beanMedidasSeleccionContratacion);
		JRBeanCollectionDataSource beanmedidasAuditorian = new JRBeanCollectionDataSource(medidasAuditoria);
		parameters.put(PlanIgualdadGenerarInformePortletKeys.MEDIDA_AUDITORIA_RETRIBUTIVA, beanmedidasAuditorian);
		JRBeanCollectionDataSource beanmedidasAuditorianCopy = new JRBeanCollectionDataSource(medidasAuditoria);
		parameters.put(PlanIgualdadGenerarInformePortletKeys.MEDIDA_AUDITORIA_RETRIBUTIVA_COPY, beanmedidasAuditorianCopy);
		JRBeanCollectionDataSource beanmedidasFormacion = new JRBeanCollectionDataSource(medidasFormacion);
		parameters.put(PlanIgualdadGenerarInformePortletKeys.MEDIDA_FORMACION, beanmedidasFormacion);
		JRBeanCollectionDataSource beanmedidasPromocionProfesional = new JRBeanCollectionDataSource(medidasPromocionProfesional);
		parameters.put(PlanIgualdadGenerarInformePortletKeys.MEDIDA_PROMOCION_PROFESIONAL, beanmedidasPromocionProfesional);
		JRBeanCollectionDataSource beanmedidasCondicionesTrabajo = new JRBeanCollectionDataSource(medidasCondicionesTrabajo);
		parameters.put(PlanIgualdadGenerarInformePortletKeys.MEDIDA_CONDICIONES_TRABAJO, beanmedidasCondicionesTrabajo);
		JRBeanCollectionDataSource beanmedidasEjercicio = new JRBeanCollectionDataSource(medidasEjercicio);
		parameters.put(PlanIgualdadGenerarInformePortletKeys.MEDIDA_EJERCICIO, beanmedidasEjercicio);
		JRBeanCollectionDataSource beanmedidasPrevencionAcoso = new JRBeanCollectionDataSource(medidasPrevencionAcoso);
		parameters.put(PlanIgualdadGenerarInformePortletKeys.MEDIDA_PREVENCION, beanmedidasPrevencionAcoso);
		JRBeanCollectionDataSource beanmedidasDerechosLaborales = new JRBeanCollectionDataSource(medidasDerechosLaborales);
		parameters.put(PlanIgualdadGenerarInformePortletKeys.MEDIDA_DERECHOS, beanmedidasDerechosLaborales);
		JRBeanCollectionDataSource beanmedidasLenguajeComunicacion = new JRBeanCollectionDataSource(medidasLenguajeComunicacion);
		parameters.put(PlanIgualdadGenerarInformePortletKeys.MEDIDA_LENGUAJE_COMUNICACION, beanmedidasLenguajeComunicacion);
		JRBeanCollectionDataSource beanmedidasClasificacionProfesional = new JRBeanCollectionDataSource(medidasClasificacionProfesional);
		parameters.put(PlanIgualdadGenerarInformePortletKeys.MEDIDA_CLASIFICACION_PROFESIONAL, beanmedidasClasificacionProfesional);
		JRBeanCollectionDataSource beanmedidasInfraestructuraFemenina = new JRBeanCollectionDataSource(medidasInfraestructuraFemenina);
		parameters.put(PlanIgualdadGenerarInformePortletKeys.MEDIDA_INFRARREPRE_FEMENINA, beanmedidasInfraestructuraFemenina);
		JRBeanCollectionDataSource beanMedidasAltaPrioridad = new JRBeanCollectionDataSource(medidasAltaPrioridad);
		parameters.put(PlanIgualdadGenerarInformePortletKeys.MEDIDA_ALTA_PRIORIDAD, beanMedidasAltaPrioridad);
		JRBeanCollectionDataSource beanMedidasConDocumento = new JRBeanCollectionDataSource(medidasConDocumento);
		parameters.put(PlanIgualdadGenerarInformePortletKeys.MEDIDAS_CON_DOCUMENTO, beanMedidasConDocumento);

		JRBeanCollectionDataSource beanMedidasSeleccionContratacionSeg = new JRBeanCollectionDataSource(medidasSeleccionContratacionSeg);
		parameters.put(PlanIgualdadGenerarInformePortletKeys.MEDIDA_SELECCION_CONTRATACION_SEG, beanMedidasSeleccionContratacionSeg);
		JRBeanCollectionDataSource beanmedidasAuditorianSeg = new JRBeanCollectionDataSource(medidasAuditoriaSeg);
		parameters.put(PlanIgualdadGenerarInformePortletKeys.MEDIDA_AUDITORIA_RETRIBUTIVA_SEG, beanmedidasAuditorianSeg);
		JRBeanCollectionDataSource beanmedidasFormacionSeg = new JRBeanCollectionDataSource(medidasFormacionSeg);
		parameters.put(PlanIgualdadGenerarInformePortletKeys.MEDIDA_FORMACION_SEG, beanmedidasFormacionSeg);
		JRBeanCollectionDataSource beanmedidasPromocionProfesionalSeg = new JRBeanCollectionDataSource(medidasPromocionProfesionalSeg);
		parameters.put(PlanIgualdadGenerarInformePortletKeys.MEDIDA_PROMOCION_PROFESIONAL_SEG, beanmedidasPromocionProfesionalSeg);
		JRBeanCollectionDataSource beanmedidasCondicionesTrabajoSeg = new JRBeanCollectionDataSource(medidasCondicionesTrabajoSeg);
		parameters.put(PlanIgualdadGenerarInformePortletKeys.MEDIDA_CONDICIONES_TRABAJO_SEG, beanmedidasCondicionesTrabajoSeg);
		JRBeanCollectionDataSource beanmedidasEjercicioSeg = new JRBeanCollectionDataSource(medidasEjercicioSeg);
		parameters.put(PlanIgualdadGenerarInformePortletKeys.MEDIDA_EJERCICIO_SEG, beanmedidasEjercicioSeg);
		JRBeanCollectionDataSource beanmedidasPrevencionAcosoSeg = new JRBeanCollectionDataSource(medidasPrevencionAcosoSeg);
		parameters.put(PlanIgualdadGenerarInformePortletKeys.MEDIDA_PREVENCION_SEG, beanmedidasPrevencionAcosoSeg);
		JRBeanCollectionDataSource beanmedidasDerechosLaboralesSeg = new JRBeanCollectionDataSource(medidasDerechosLaboralesSeg);
		parameters.put(PlanIgualdadGenerarInformePortletKeys.MEDIDA_DERECHOS_SEG, beanmedidasDerechosLaboralesSeg);
		JRBeanCollectionDataSource beanmedidasLenguajeComunicacionSeg = new JRBeanCollectionDataSource(medidasLenguajeComunicacionSeg);
		parameters.put(PlanIgualdadGenerarInformePortletKeys.MEDIDA_LENGUAJE_COMUNICACION_SEG, beanmedidasLenguajeComunicacionSeg);
		JRBeanCollectionDataSource beanmedidasClasificacionProfesionalSeg = new JRBeanCollectionDataSource(medidasClasificacionProfesionalSeg);
		parameters.put(PlanIgualdadGenerarInformePortletKeys.MEDIDA_CLASIFICACION_PROFESIONAL_SEG, beanmedidasClasificacionProfesionalSeg);
		JRBeanCollectionDataSource beanmedidasInfraestructuraFemeninaSeg = new JRBeanCollectionDataSource(medidasInfraestructuraFemeninaSeg);
		parameters.put(PlanIgualdadGenerarInformePortletKeys.MEDIDA_INFRARREPRE_FEMENINA_SEG, beanmedidasInfraestructuraFemeninaSeg);
		JRBeanCollectionDataSource beanMedidasAltaPrioridadSeg = new JRBeanCollectionDataSource(medidasAltaPrioridadSeg);
		parameters.put(PlanIgualdadGenerarInformePortletKeys.MEDIDA_ALTA_PRIORIDAD_SEG, beanMedidasAltaPrioridadSeg);
		
		Collections.sort(medidasDTO, Comparator.comparing(MedidaDTO::getMateria,String.CASE_INSENSITIVE_ORDER));
		JRBeanCollectionDataSource beanMedidasTotal = new JRBeanCollectionDataSource(medidasDTO);
		parameters.put("medidaListTotal", beanMedidasTotal);

		parameters.put(PlanIgualdadGenerarInformePortletKeys.N_MEDIDAS_PLAN, nMedidasPlan);
		parameters.put(PlanIgualdadGenerarInformePortletKeys.N_MEDIDAS_INFORMADO_SEGUIMIENTO, nMedidasInformadoSeguimiento);
		parameters.put(PlanIgualdadGenerarInformePortletKeys.N_MEDIDAS_PENDIENTES, nMedidasPendientes);
		parameters.put(PlanIgualdadGenerarInformePortletKeys.N_MEDIDAS_EJECUCION, nMedidasEjecucion);
		parameters.put(PlanIgualdadGenerarInformePortletKeys.N_MEDIDAS_FINALIZADAS, nMedidasFinalizadas);
		parameters.put(PlanIgualdadGenerarInformePortletKeys.N_MEDIDAS_PRIORITARIAS_FINALIZADAS, nMedidasPrioritariasFinalizadas);

		return parameters;
	}
	
	public static MedidaDTO getNoMedida() {
		MedidaDTO medida = new MedidaDTO();
		medida.setNoMedida(Boolean.TRUE);
		medida.setTextNoMedida(PlanIgualdadGenerarInformePortletKeys.DATO_NO_INFORMADO);
		return medida;
	}
}

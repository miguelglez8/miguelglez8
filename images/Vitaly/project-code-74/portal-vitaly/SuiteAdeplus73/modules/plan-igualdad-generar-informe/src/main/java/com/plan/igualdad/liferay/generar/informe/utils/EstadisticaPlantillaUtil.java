package com.plan.igualdad.liferay.generar.informe.utils;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Validator;
import com.plan.igualdad.liferay.generar.informe.constants.PlanIgualdadGenerarInformePortletKeys;
import com.plan.igualdad.liferay.generar.informe.dto.EstadisticaTablaAreaPuestoDTO;
import com.plan.igualdad.liferay.generar.informe.dto.EstadisticaTablaHorarioTurnoDTO;
import com.plan.igualdad.liferay.generar.informe.dto.EstadisticaTablaPuestoAntiguedadDTO;
import com.plan.igualdad.liferay.generar.informe.dto.EstadisticasTablaAuditoriaDTO;
import com.plan.igualdad.liferay.generar.informe.dto.EstadisticasTablaCeseDTO;
import com.plan.igualdad.liferay.generar.informe.dto.EstadisticasTablaDistribucionPuestoDTO;
import com.plan.igualdad.liferay.generar.informe.dto.EstadisticasTablaIncapacidadDTO;
import com.plan.igualdad.liferay.generar.informe.dto.EstadisticasTablaNivelEstudiosYPuestoDTO;
import com.plan.igualdad.liferay.generar.informe.dto.EstadisticasTablaValoracionesDTO;
import com.plan.igualdad.liferay.generar.informe.dto.GraphicDTO;
import com.plan.igualdad.liferay.portlet.manager.model.Estadistica;
import com.plan.igualdad.liferay.portlet.manager.model.ImagenCanvasEst;
import com.plan.igualdad.liferay.portlet.manager.model.PuestoTrabajo;
import com.plan.igualdad.liferay.portlet.manager.service.EstadisticaLocalServiceUtil;
import com.plan.igualdad.liferay.portlet.manager.service.ImagenCanvasEstLocalServiceUtil;
import com.plan.igualdad.liferay.portlet.manager.service.PuestoTrabajoLocalServiceUtil;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class EstadisticaPlantillaUtil {
	/**
	 * 
	 * @param themeDisplay
	 * @param compId
	 * @param versionId
	 * @param sectionId
	 * @return
	 * @throws IOException 
	 * @throws PortalException 
	 */
	public static Map<String, Object> getParameter(ThemeDisplay themeDisplay, Long compId, Long versionId, Long sectionId) throws IOException, PortalException{
		Map<String, Object> parameters = new HashMap<>();

		String comentario = StringPool.BLANK;
		String valoracion = StringPool.BLANK;
		String comentario2 = StringPool.BLANK;
		String comentario3 = StringPool.BLANK;
		Boolean isGraphic = Boolean.TRUE;
		
		Estadistica estadistica = EstadisticaLocalServiceUtil.fetchEstadisticasBySection(compId, versionId, sectionId);
		if(Validator.isNotNull(estadistica)) {
			JSONObject jsonEstadistica = JSONFactoryUtil.createJSONObject(estadistica.getEstadistica());
			if(Validator.isNotNull(jsonEstadistica)) {
				valoracion = jsonEstadistica.getString(PlanIgualdadGenerarInformePortletKeys.VALORACION);
				if(sectionId==17L) {
					parameters.put(PlanIgualdadGenerarInformePortletKeys.CONCEPTOS, GeneralUtils.getImagen(jsonEstadistica.getString(PlanIgualdadGenerarInformePortletKeys.CONCEPTOS)));
					parameters.put(PlanIgualdadGenerarInformePortletKeys.PROMEDIO_GP, GeneralUtils.getImagen(jsonEstadistica.getString(PlanIgualdadGenerarInformePortletKeys.PROMEDIO_GP)));
					parameters.put(PlanIgualdadGenerarInformePortletKeys.MEDIANA_GP, GeneralUtils.getImagen(jsonEstadistica.getString(PlanIgualdadGenerarInformePortletKeys.MEDIANA_GP)));
					parameters.put(PlanIgualdadGenerarInformePortletKeys.PROMEDIO_EQ, GeneralUtils.getImagen(jsonEstadistica.getString(PlanIgualdadGenerarInformePortletKeys.PROMEDIO_EQ)));
					parameters.put(PlanIgualdadGenerarInformePortletKeys.MEDIANA_EQ, GeneralUtils.getImagen(jsonEstadistica.getString(PlanIgualdadGenerarInformePortletKeys.MEDIANA_EQ)));
					parameters.put(PlanIgualdadGenerarInformePortletKeys.PROMEDIO_PT, GeneralUtils.getImagen(jsonEstadistica.getString(PlanIgualdadGenerarInformePortletKeys.PROMEDIO_PT)));
					parameters.put(PlanIgualdadGenerarInformePortletKeys.MEDIANA_PT, GeneralUtils.getImagen(jsonEstadistica.getString(PlanIgualdadGenerarInformePortletKeys.MEDIANA_PT)));
					parameters.put(PlanIgualdadGenerarInformePortletKeys.PROMEDIO_VT, GeneralUtils.getImagen(jsonEstadistica.getString(PlanIgualdadGenerarInformePortletKeys.PROMEDIO_VT)));
					parameters.put(PlanIgualdadGenerarInformePortletKeys.MEDIANA_VT, GeneralUtils.getImagen(jsonEstadistica.getString(PlanIgualdadGenerarInformePortletKeys.MEDIANA_VT)));
					comentario = (jsonEstadistica.getString(PlanIgualdadGenerarInformePortletKeys.COMENTARIO)!=null ?GeneralUtils.deleteHTML(jsonEstadistica.getString(PlanIgualdadGenerarInformePortletKeys.COMENTARIO), Boolean.FALSE, Boolean.FALSE) : PlanIgualdadGenerarInformePortletKeys.DATO_NO_INFORMADO);
				}else if(sectionId==16L) {
					comentario = (jsonEstadistica.getString("comentarioPuntuacion")!=null ?GeneralUtils.deleteHTML(jsonEstadistica.getString("comentarioPuntuacion"), Boolean.FALSE, Boolean.FALSE ) : PlanIgualdadGenerarInformePortletKeys.DATO_NO_INFORMADO);
					comentario2 = (jsonEstadistica.getString("comentarioAgrupaciones")!=null ?GeneralUtils.deleteHTML(jsonEstadistica.getString("comentarioAgrupaciones"), Boolean.FALSE, Boolean.FALSE) : PlanIgualdadGenerarInformePortletKeys.DATO_NO_INFORMADO);
					parameters.put("comentarioCategorias", GeneralUtils.getImagen(jsonEstadistica.getString("comentarioCategorias")));
					parameters.put("comentarioSexo", GeneralUtils.getImagen(jsonEstadistica.getString("comentarioSexo")));

				}else if(sectionId==15L) { 
					comentario = (jsonEstadistica.getString(PlanIgualdadGenerarInformePortletKeys.COMENTARIO_PROMOCION)!=null ?GeneralUtils.deleteHTML(jsonEstadistica.getString(PlanIgualdadGenerarInformePortletKeys.COMENTARIO_PROMOCION), Boolean.FALSE, Boolean.FALSE) : PlanIgualdadGenerarInformePortletKeys.DATO_NO_INFORMADO);
					comentario2 = (jsonEstadistica.getString(PlanIgualdadGenerarInformePortletKeys.COMENTARIO_PERSONAS)!=null ?GeneralUtils.deleteHTML(jsonEstadistica.getString(PlanIgualdadGenerarInformePortletKeys.COMENTARIO_PERSONAS), Boolean.FALSE, Boolean.FALSE) : PlanIgualdadGenerarInformePortletKeys.DATO_NO_INFORMADO);
				}else if(sectionId==14L) {
					comentario = (jsonEstadistica.getString(PlanIgualdadGenerarInformePortletKeys.COMENTARIO_FORMACION)!=null ?GeneralUtils.deleteHTML(jsonEstadistica.getString(PlanIgualdadGenerarInformePortletKeys.COMENTARIO_FORMACION), Boolean.FALSE, Boolean.FALSE) : PlanIgualdadGenerarInformePortletKeys.DATO_NO_INFORMADO);
					comentario2 = (jsonEstadistica.getString(PlanIgualdadGenerarInformePortletKeys.COMENTARIO_DURACION)!=null ?GeneralUtils.deleteHTML(jsonEstadistica.getString(PlanIgualdadGenerarInformePortletKeys.COMENTARIO_DURACION), Boolean.FALSE, Boolean.FALSE) : PlanIgualdadGenerarInformePortletKeys.DATO_NO_INFORMADO);
				}else if(sectionId==8L) {
					comentario = (jsonEstadistica.getString(PlanIgualdadGenerarInformePortletKeys.COMENTARIO_PLANTILLAS)!=null ?GeneralUtils.deleteHTML(jsonEstadistica.getString(PlanIgualdadGenerarInformePortletKeys.COMENTARIO_PLANTILLAS), Boolean.FALSE, Boolean.FALSE) : PlanIgualdadGenerarInformePortletKeys.DATO_NO_INFORMADO);
					comentario2 = (jsonEstadistica.getString(PlanIgualdadGenerarInformePortletKeys.COMENTARIO_MUJERES)!=null ?GeneralUtils.deleteHTML(jsonEstadistica.getString(PlanIgualdadGenerarInformePortletKeys.COMENTARIO_MUJERES), Boolean.FALSE, Boolean.FALSE) : PlanIgualdadGenerarInformePortletKeys.DATO_NO_INFORMADO);
					comentario3 = (jsonEstadistica.getString(PlanIgualdadGenerarInformePortletKeys.COMENTARIO_HOMBRES)!=null ?GeneralUtils.deleteHTML(jsonEstadistica.getString(PlanIgualdadGenerarInformePortletKeys.COMENTARIO_HOMBRES), Boolean.FALSE, Boolean.FALSE) : PlanIgualdadGenerarInformePortletKeys.DATO_NO_INFORMADO);
				}else {
					comentario = (jsonEstadistica.getString(PlanIgualdadGenerarInformePortletKeys.COMENTARIO)!=null ?GeneralUtils.deleteHTML(jsonEstadistica.getString(PlanIgualdadGenerarInformePortletKeys.COMENTARIO), Boolean.FALSE, Boolean.FALSE) : PlanIgualdadGenerarInformePortletKeys.DATO_NO_INFORMADO);
				}
			}
			
			if(sectionId==2L || sectionId==5L || sectionId==6L || sectionId==7L 
					 || sectionId==10L || sectionId==12L || sectionId==13L || sectionId==18L || sectionId==16L) {
				isGraphic = Boolean.FALSE;
			}
			
			if(sectionId==15L || sectionId==14L) {
				parameters.putAll(GeneralUtils.setParameterComentario(sectionId, GeneralUtils.deleteHTML(comentario, Boolean.FALSE, Boolean.FALSE), ".1"));
				parameters.putAll(GeneralUtils.setParameterComentario(sectionId, GeneralUtils.deleteHTML(comentario2, Boolean.FALSE, Boolean.FALSE), ".2"));
				
				List<GraphicDTO> listGraphics = getListGraphics(compId, sectionId, versionId, PlanIgualdadGenerarInformePortletKeys.GRAPHIC+ sectionId+ ".1");
				if(listGraphics!=null && !listGraphics.isEmpty()) {
					JRBeanCollectionDataSource beanGraphic = new JRBeanCollectionDataSource(listGraphics);
					parameters.put(PlanIgualdadGenerarInformePortletKeys.GRAPHIC+ sectionId+ ".1", beanGraphic);	
				}
				
				List<GraphicDTO> listGraphics2 = getListGraphics(compId, sectionId, versionId, PlanIgualdadGenerarInformePortletKeys.GRAPHIC+ sectionId+ ".2");
				if(listGraphics!=null && !listGraphics.isEmpty()) {
					JRBeanCollectionDataSource beanGraphic2 = new JRBeanCollectionDataSource(listGraphics2);
					parameters.put(PlanIgualdadGenerarInformePortletKeys.GRAPHIC+ sectionId+ ".2", beanGraphic2);
				}
			}else if(sectionId==8L) {
				parameters.putAll(GeneralUtils.setParameterComentario(sectionId, GeneralUtils.deleteHTML(comentario, Boolean.FALSE, Boolean.FALSE), ".1"));
				parameters.putAll(GeneralUtils.setParameterComentario(sectionId, GeneralUtils.deleteHTML(comentario2, Boolean.FALSE, Boolean.FALSE), ".2"));
				parameters.putAll(GeneralUtils.setParameterComentario(sectionId, GeneralUtils.deleteHTML(comentario3, Boolean.FALSE, Boolean.FALSE), ".3"));

				List<GraphicDTO> listGraphics1 = getListGraphics(compId, sectionId, versionId, PlanIgualdadGenerarInformePortletKeys.GRAPHIC+ sectionId+ ".1");
				JRBeanCollectionDataSource beanGraphic1 = new JRBeanCollectionDataSource(listGraphics1);
				parameters.put(PlanIgualdadGenerarInformePortletKeys.GRAPHIC+ sectionId+ ".1", beanGraphic1);	
				
				List<GraphicDTO> listGraphics2 = getListGraphics(compId, sectionId, versionId, PlanIgualdadGenerarInformePortletKeys.GRAPHIC+ sectionId+ ".2");
				JRBeanCollectionDataSource beanGraphic2 = new JRBeanCollectionDataSource(listGraphics2);
				parameters.put(PlanIgualdadGenerarInformePortletKeys.GRAPHIC+ sectionId+ ".2", beanGraphic2);	
				
				List<GraphicDTO> listGraphics3 = getListGraphics(compId, sectionId, versionId, PlanIgualdadGenerarInformePortletKeys.GRAPHIC+ sectionId+ ".3");
				JRBeanCollectionDataSource beanGraphic3 = new JRBeanCollectionDataSource(listGraphics3);
				parameters.put(PlanIgualdadGenerarInformePortletKeys.GRAPHIC+ sectionId+ ".3", beanGraphic3);	
			
			}else {
				parameters.putAll(GeneralUtils.setParameterComentario(sectionId, comentario, ""));

				String subHeaderDifferent=StringPool.BLANK;
				Boolean printHeader = Boolean.TRUE;
				
				if(isGraphic) {
					List<GraphicDTO> listGraphics = getListGraphics(compId, sectionId, versionId, PlanIgualdadGenerarInformePortletKeys.GRAPHIC+ sectionId);
					JRBeanCollectionDataSource beanGraphic = new JRBeanCollectionDataSource(listGraphics);
					parameters.put(PlanIgualdadGenerarInformePortletKeys.GRAPHIC+ sectionId, beanGraphic);	
				}else if(sectionId==2L) {
					List<EstadisticaTablaAreaPuestoDTO> areaPuestoDTOs = new ArrayList<>();
					JSONArray arrayAreasPuestos = JSONArrayInformeUtils.getAreasPuestosEstadisticas(compId, versionId);
					
					if(arrayAreasPuestos.length()>0) {
						for(int i=0; i<arrayAreasPuestos.length(); i++) {
							JSONObject areaPuesto = arrayAreasPuestos.getJSONObject(i);
							
							if(i>0) {
								printHeader = Boolean.FALSE;
							}

							if(!subHeaderDifferent.equals(areaPuesto.getString("area"))) {
								areaPuestoDTOs.add(getAreaPuestoDTO(areaPuesto, Boolean.TRUE, printHeader));
								subHeaderDifferent=areaPuesto.getString("area");
							}else {
								areaPuestoDTOs.add(getAreaPuestoDTO(areaPuesto, Boolean.FALSE, printHeader));
							}
						}
					}else {
						EstadisticaTablaAreaPuestoDTO areaPuestoDTO = new EstadisticaTablaAreaPuestoDTO();
						areaPuestoDTO.setNoTable(Boolean.TRUE);
						areaPuestoDTO.setTextNoTable(PlanIgualdadGenerarInformePortletKeys.DATO_NO_INFORMADO);
						areaPuestoDTOs.add(areaPuestoDTO);
					}
					
					JRBeanCollectionDataSource beanGraphic = new JRBeanCollectionDataSource(areaPuestoDTOs);
					parameters.put(PlanIgualdadGenerarInformePortletKeys.GRAPHIC+ sectionId, areaPuestoDTOs.size()>0 ? beanGraphic : null);	
				}else if(sectionId==5L) {
					List<EstadisticaTablaPuestoAntiguedadDTO> puestoAntiguedads = new ArrayList<>();
			        ResourceBundle bundle = ResourceBundle.getBundle("content/Language", themeDisplay.getUser().getLocale());

			        if(!estadistica.getEstadistica().isEmpty()) {
			        	JSONArray arrayPuestosAntiguedad = JSONArrayInformeUtils.getPuestosAntiguedadEstadisticas(compId, versionId, bundle,estadistica);
						
			        	if(arrayPuestosAntiguedad.length()>0) {
							for(int i=0; i<arrayPuestosAntiguedad.length(); i++) {
								JSONObject puestoAntiguedad = arrayPuestosAntiguedad.getJSONObject(i);
								
								if(i>0) {
									printHeader = Boolean.FALSE;
								}
								
								if(!subHeaderDifferent.equals(puestoAntiguedad.getString("puesto"))) {
									puestoAntiguedads.add(getPuestoAntiguedadDTO(puestoAntiguedad, Boolean.TRUE, printHeader));
									subHeaderDifferent=puestoAntiguedad.getString("puesto");
								}else {
									puestoAntiguedads.add(getPuestoAntiguedadDTO(puestoAntiguedad, Boolean.FALSE, printHeader));
								}
								
							}
			        	}else {
			        		EstadisticaTablaPuestoAntiguedadDTO areaPuestoDTO = new EstadisticaTablaPuestoAntiguedadDTO();
							areaPuestoDTO.setNoTable(Boolean.TRUE);
							areaPuestoDTO.setTextNoTable(PlanIgualdadGenerarInformePortletKeys.DATO_NO_INFORMADO);
							puestoAntiguedads.add(areaPuestoDTO);
						}
			        }
					
					JRBeanCollectionDataSource beanGraphic = new JRBeanCollectionDataSource(puestoAntiguedads);
					parameters.put(PlanIgualdadGenerarInformePortletKeys.GRAPHIC+ sectionId, puestoAntiguedads.size()>0 ? beanGraphic : null);
				}else if(sectionId==6L) {
					List<EstadisticasTablaNivelEstudiosYPuestoDTO> estudiosYPuestos =  new ArrayList<>();
					ResourceBundle bundle = ResourceBundle.getBundle("content/Language", themeDisplay.getUser().getLocale());
					parameters.putAll(JSONArrayInformeUtils.getTable2PuestoEstudios(compId, versionId, bundle, estadistica));
					if(!estadistica.getEstadistica().isEmpty()) {
						JSONArray arrayNivelEstudios = JSONArrayInformeUtils.getPuestosNivelEstudiosEstadisticas(compId, versionId, bundle, estadistica);
						if(arrayNivelEstudios.length()>0) {
							for(int i=0; i<arrayNivelEstudios.length(); i++) {
								JSONObject nivelEstudio = arrayNivelEstudios.getJSONObject(i);
							
								if(i==0) {
									estudiosYPuestos.add(getPuestoNivelEstudioDTO(nivelEstudio, Boolean.TRUE, Boolean.TRUE));
								}
								
								if(!subHeaderDifferent.equals(nivelEstudio.getString("puesto"))) {
									estudiosYPuestos.add(getPuestoNivelEstudioDTO(nivelEstudio, Boolean.TRUE, Boolean.FALSE));
									subHeaderDifferent=nivelEstudio.getString("puesto");
								}
								
								estudiosYPuestos.add(getPuestoNivelEstudioDTO(nivelEstudio, Boolean.FALSE, Boolean.FALSE));
							}
						}else {
							EstadisticasTablaNivelEstudiosYPuestoDTO areaPuestoDTO = new EstadisticasTablaNivelEstudiosYPuestoDTO();
							areaPuestoDTO.setNoTable(Boolean.TRUE);
							areaPuestoDTO.setTextNoTable(PlanIgualdadGenerarInformePortletKeys.DATO_NO_INFORMADO);
							estudiosYPuestos.add(areaPuestoDTO);
						}
					}
					JRBeanCollectionDataSource beanGraphic = new JRBeanCollectionDataSource(estudiosYPuestos);
					parameters.put(PlanIgualdadGenerarInformePortletKeys.GRAPHIC+ sectionId, estudiosYPuestos.size()>0 ? beanGraphic : null);
				}else if(sectionId==7L) {
					List<EstadisticasTablaNivelEstudiosYPuestoDTO> estudiosYPuestos =  new ArrayList<>();
					
					ResourceBundle bundle = ResourceBundle.getBundle("content/Language", themeDisplay.getUser().getLocale());
					
					JSONArray arrayPuestoFamiliar  = JSONArrayInformeUtils.getPuestosFamiliaresEstadisticas(compId, versionId, bundle);
					
					if(arrayPuestoFamiliar.length()>0) {
						for(int i=0; i<arrayPuestoFamiliar.length(); i++) {
							JSONObject puestoFamiliar = arrayPuestoFamiliar.getJSONObject(i);
							if(i==0) {
								estudiosYPuestos.add(getPuestoNivelEstudioDTO(puestoFamiliar, Boolean.FALSE,  Boolean.TRUE));
							}
							
							if(!subHeaderDifferent.equals(puestoFamiliar.getString("puesto"))) {
								estudiosYPuestos.add(getPuestoNivelEstudioDTO(puestoFamiliar, Boolean.TRUE,  Boolean.FALSE));
								subHeaderDifferent=puestoFamiliar.getString("puesto");
							}
							estudiosYPuestos.add(getPuestoNivelEstudioDTO(puestoFamiliar, Boolean.FALSE,  Boolean.FALSE));
	
						}
					}else {
						EstadisticasTablaNivelEstudiosYPuestoDTO areaPuestoDTO = new EstadisticasTablaNivelEstudiosYPuestoDTO();
						areaPuestoDTO.setNoTable(Boolean.TRUE);
						areaPuestoDTO.setTextNoTable(PlanIgualdadGenerarInformePortletKeys.DATO_NO_INFORMADO);
						estudiosYPuestos.add(areaPuestoDTO);
					}
					
					JRBeanCollectionDataSource beanGraphic = new JRBeanCollectionDataSource(estudiosYPuestos);
					parameters.put(PlanIgualdadGenerarInformePortletKeys.GRAPHIC+ sectionId, estudiosYPuestos.size()>0 ? beanGraphic : null);
				
			        List<PuestoTrabajo> puestos = PuestoTrabajoLocalServiceUtil.findPuestoTrabajoActivo(compId, versionId);

			        long totalHombres = 0;
			        long totalMujeres = 0;
			        long nHombresResponsabilidad = 0;
			        long nMujeresResponsabilidad = 0;
			        
			        long hombresConCargas = 0;
			        long hombresPorcConCargas = 0;
			        long mujeresConCargas = 0;
			        long mujeresPorcConCargas = 0;
			        
			        if(Validator.isNotNull(puestos)) {
			        	for(PuestoTrabajo puesto : puestos) {
			        		if (puesto.getResponsabilidad() == 0) {
			                    nHombresResponsabilidad = nHombresResponsabilidad + puesto.getNHombres();
			                    nMujeresResponsabilidad = nMujeresResponsabilidad + puesto.getNMujeres();
			                }
			        		totalHombres = totalHombres + puesto.getNHombres();
			        		totalMujeres = totalMujeres + puesto.getNMujeres();
			        	}
			        }
			        
			        JSONObject seccion7Object = JSONFactoryUtil.createJSONObject(estadistica.getEstadistica());
			        if(Validator.isNotNull(seccion7Object)) {
			        	hombresConCargas = seccion7Object.getString("hombresCarga")!=null && !seccion7Object.getString("hombresCarga").isEmpty()? Long.parseLong(seccion7Object.getString("hombresCarga")) : 0L;
			        	hombresPorcConCargas = seccion7Object.getString("hombresRespCarga")!=null && !seccion7Object.getString("hombresRespCarga").isEmpty()?  Long.parseLong(seccion7Object.getString("hombresRespCarga")) : 0L;
			        	mujeresConCargas = seccion7Object.getString("mujeresCarga")!=null && !seccion7Object.getString("mujeresCarga").isEmpty()?  Long.parseLong(seccion7Object.getString("mujeresCarga")) : 0L;
			        	mujeresPorcConCargas = seccion7Object.getString("mujeresRespCarga")!=null && !seccion7Object.getString("mujeresRespCarga").isEmpty()?  Long.parseLong(seccion7Object.getString("mujeresRespCarga")) : 0L;
			        }
			        
					parameters.put("nHombresConCargas", Long.toString(hombresConCargas));
					parameters.put("nHombresRespConCargas", Long.toString(hombresPorcConCargas));
					parameters.put("nMujeresConCargas", Long.toString(mujeresConCargas));
					parameters.put("nMujeresRespConCargas", Long.toString(mujeresPorcConCargas));
					
					parameters.put("nHombresConCargasPorc", Float.toString(JSONArrayInformeUtils.calculatePercentage(hombresConCargas, totalHombres)) + "%");
					parameters.put("nHombresRespConCargasPorc", Float.toString(JSONArrayInformeUtils.calculatePercentage(hombresPorcConCargas, nHombresResponsabilidad)) + "%");
					parameters.put("nMujeresConCargasPorc", Float.toString(JSONArrayInformeUtils.calculatePercentage(mujeresConCargas, totalMujeres)) + "%");
					parameters.put("nMujeresRespConCargasPorc", Float.toString(JSONArrayInformeUtils.calculatePercentage(mujeresPorcConCargas, nMujeresResponsabilidad)) + "%");
					
				}else if(sectionId==10L) {
					List<EstadisticaTablaHorarioTurnoDTO> horarioTurnosDTO =  new ArrayList<>();
					ResourceBundle bundle = ResourceBundle.getBundle("content/Language", themeDisplay.getUser().getLocale());
					if(!estadistica.getEstadistica().isEmpty()) {
						JSONArray arrayPuestoFamiliar  = JSONArrayInformeUtils.getHorarioTurnosEstadisticas(compId, versionId, bundle, estadistica);
						if(arrayPuestoFamiliar.length()>0) {
							for(int i=0; i<arrayPuestoFamiliar.length(); i++) {
								JSONObject horarioTurno = arrayPuestoFamiliar.getJSONObject(i);
								
								if(i>0) {
									printHeader = Boolean.FALSE;
								}
								
								if(!subHeaderDifferent.equals(horarioTurno.getString("horario"))) {
									horarioTurnosDTO.add(getHorarioTurnoDTO(horarioTurno, Boolean.TRUE, printHeader));
									subHeaderDifferent=horarioTurno.getString("horario");
								}else {
									horarioTurnosDTO.add(getHorarioTurnoDTO(horarioTurno, Boolean.FALSE, printHeader));
								}
								
							}
						}else {
							EstadisticaTablaHorarioTurnoDTO areaPuestoDTO = new EstadisticaTablaHorarioTurnoDTO();
							areaPuestoDTO.setNoTable(Boolean.TRUE);
							areaPuestoDTO.setTextNoTable(PlanIgualdadGenerarInformePortletKeys.DATO_NO_INFORMADO);
							horarioTurnosDTO.add(areaPuestoDTO);
						}
					}
					
					JRBeanCollectionDataSource beanGraphic = new JRBeanCollectionDataSource(horarioTurnosDTO);
					parameters.put(PlanIgualdadGenerarInformePortletKeys.GRAPHIC+ sectionId, horarioTurnosDTO.size()>0 ? beanGraphic : null);
				}else if(sectionId==12L) {
					List<EstadisticasTablaCeseDTO> cesesDTO =  new ArrayList<>();
					ResourceBundle bundle = ResourceBundle.getBundle("content/Language", themeDisplay.getUser().getLocale());
					if(!estadistica.getEstadistica().isEmpty()) {
						JSONArray array = JSONArrayInformeUtils.getCesesEstadisticas(compId, versionId, bundle, estadistica);
						if(array.length()>0) {
							for(int i=0; i<array.length(); i++) {
								JSONObject object = array.getJSONObject(i);
								if(i==0) {
									cesesDTO.add(getCeseDTO(object, Boolean.TRUE));
								}
								cesesDTO.add(getCeseDTO(object, Boolean.FALSE));
							}
						}else {
							EstadisticasTablaCeseDTO areaPuestoDTO = new EstadisticasTablaCeseDTO();
							areaPuestoDTO.setNoTable(Boolean.TRUE);
							areaPuestoDTO.setTextNoTable(PlanIgualdadGenerarInformePortletKeys.DATO_NO_INFORMADO);
							cesesDTO.add(areaPuestoDTO);
						}
					}
					JRBeanCollectionDataSource beanGraphic = new JRBeanCollectionDataSource(cesesDTO);
					parameters.put(PlanIgualdadGenerarInformePortletKeys.GRAPHIC+ sectionId, cesesDTO.size()>0 ? beanGraphic : null);
				}else if(sectionId==13L) {
					List<EstadisticasTablaIncapacidadDTO> incapacidadDTO =  new ArrayList<>();
					ResourceBundle bundle = ResourceBundle.getBundle("content/Language", themeDisplay.getUser().getLocale());
					if(!estadistica.getEstadistica().isEmpty()) {
						JSONArray array = JSONArrayInformeUtils.getIncapacidadesEstadisticas(compId, versionId, bundle, estadistica);
						if(array.length()>0) {
							for(int i=0; i<array.length(); i++) {
								JSONObject object = array.getJSONObject(i);
								if(i==0) {
									incapacidadDTO.add(getIncapacidadDTO(object, Boolean.TRUE));
								}
								
								incapacidadDTO.add(getIncapacidadDTO(object, Boolean.FALSE));
							}
						}else {
							EstadisticasTablaIncapacidadDTO areaPuestoDTO = new EstadisticasTablaIncapacidadDTO();
							areaPuestoDTO.setNoTable(Boolean.TRUE);
							areaPuestoDTO.setTextNoTable(PlanIgualdadGenerarInformePortletKeys.DATO_NO_INFORMADO);
							incapacidadDTO.add(areaPuestoDTO);
						}
					}
					JRBeanCollectionDataSource beanGraphic = new JRBeanCollectionDataSource(incapacidadDTO);
					parameters.put(PlanIgualdadGenerarInformePortletKeys.GRAPHIC+ sectionId, incapacidadDTO.size()>0 ? beanGraphic : null);
				}else if(sectionId==18L) {
					List<EstadisticasTablaAuditoriaDTO> incapacidadDTO =  new ArrayList<>();
					ResourceBundle bundle = ResourceBundle.getBundle("content/Language", themeDisplay.getUser().getLocale());
					if(!estadistica.getEstadistica().isEmpty()) {
						JSONArray array = JSONArrayInformeUtils.getAuditoriasEstadisticas(compId, versionId, bundle, estadistica);
						if(array.length()>0) {
							for(int i=0; i<array.length(); i++) {
								JSONObject object = array.getJSONObject(i);
								if(i==0) {
									incapacidadDTO.add(getAuditoriaDTO(object, Boolean.FALSE,  Boolean.TRUE));
								}
								
								if(!subHeaderDifferent.equals(object.getString("tipoSalario"))) {
									incapacidadDTO.add(getAuditoriaDTO(object, Boolean.TRUE,  Boolean.FALSE));
									subHeaderDifferent=object.getString("tipoSalario");
								}
		
								incapacidadDTO.add(getAuditoriaDTO(object, Boolean.FALSE,  Boolean.FALSE));
							}
						}else {
							EstadisticasTablaAuditoriaDTO areaPuestoDTO = new EstadisticasTablaAuditoriaDTO();
							areaPuestoDTO.setNoTable(Boolean.TRUE);
							areaPuestoDTO.setTextNoTable(PlanIgualdadGenerarInformePortletKeys.DATO_NO_INFORMADO);
							incapacidadDTO.add(areaPuestoDTO);
						}
					}
					JRBeanCollectionDataSource beanGraphic = new JRBeanCollectionDataSource(incapacidadDTO);
					parameters.put(PlanIgualdadGenerarInformePortletKeys.GRAPHIC+ sectionId, incapacidadDTO.size()>0 ? beanGraphic : null);
				}else if(sectionId==16L){
					List<EstadisticasTablaDistribucionPuestoDTO> distribucionesDTO =  new ArrayList<>();
					List<EstadisticasTablaDistribucionPuestoDTO> distribucionesAgrupadosDTO =  new ArrayList<>();
					List<EstadisticasTablaValoracionesDTO> valoracionesDTO =  new ArrayList<>();

					List<GraphicDTO> listGraphics1 = getListGraphics(compId, sectionId, versionId, PlanIgualdadGenerarInformePortletKeys.GRAPHIC+ sectionId+ ".1");
					JRBeanCollectionDataSource beanGraphic1Img = new JRBeanCollectionDataSource(listGraphics1);
					parameters.put(PlanIgualdadGenerarInformePortletKeys.GRAPHIC+ sectionId+ ".1", beanGraphic1Img);

					List<GraphicDTO> listGraphics2 = getListGraphics(compId, sectionId, versionId, PlanIgualdadGenerarInformePortletKeys.GRAPHIC+ sectionId+ ".2");
					JRBeanCollectionDataSource beanGraphic2Img = new JRBeanCollectionDataSource(listGraphics2);
					parameters.put(PlanIgualdadGenerarInformePortletKeys.GRAPHIC+ sectionId+ ".2", beanGraphic2Img);

					
					if(!estadistica.getEstadistica().isEmpty()) {
						JSONArray array = JSONArrayInformeUtils.getDistribuciones(compId, versionId, estadistica);
						if(array.length()>0) {
							for(int i=0; i<array.length(); i++) {
								JSONObject object = array.getJSONObject(i);
								if(i==0) {
									distribucionesDTO.add(getDistribucionDTO(object, Boolean.FALSE, Boolean.TRUE));
									distribucionesAgrupadosDTO.add(getDistribucionDTO(object, Boolean.FALSE,  Boolean.TRUE));
								}
								
								if(!subHeaderDifferent.equals(object.getString("agrupacion"))) {
									distribucionesAgrupadosDTO.add(getDistribucionDTO(object, Boolean.TRUE,  Boolean.FALSE));
									subHeaderDifferent=object.getString("agrupacion");
								}
								distribucionesDTO.add(getDistribucionDTO(object, Boolean.FALSE, Boolean.FALSE));
								distribucionesAgrupadosDTO.add(getDistribucionDTO(object, Boolean.FALSE,  Boolean.FALSE));
							}
						}
						
						JSONArray arrayValoraciones = JSONArrayInformeUtils.getValoraciones(compId, versionId, estadistica);
						if(arrayValoraciones.length()>0) {
							for(int i=0; i<arrayValoraciones.length(); i++) {
								JSONObject object = arrayValoraciones.getJSONObject(i);
								if(i==0) {
									valoracionesDTO.add(getValoracionDTO(object, Boolean.TRUE));
								}else {
									valoracionesDTO.add(getValoracionDTO(object, Boolean.FALSE));
								}
							}
						}
					}else {
						EstadisticasTablaDistribucionPuestoDTO distribucionPuestoDTO = new EstadisticasTablaDistribucionPuestoDTO();
						distribucionPuestoDTO.setNoTable(Boolean.TRUE);
						distribucionPuestoDTO.setTextNoTable(PlanIgualdadGenerarInformePortletKeys.DATO_NO_INFORMADO);
						distribucionesDTO.add(distribucionPuestoDTO);
						
						EstadisticasTablaDistribucionPuestoDTO distribucionAgrupacionesPuestoDTO = new EstadisticasTablaDistribucionPuestoDTO();
						distribucionAgrupacionesPuestoDTO.setNoTable(Boolean.TRUE);
						distribucionAgrupacionesPuestoDTO.setTextNoTable(PlanIgualdadGenerarInformePortletKeys.DATO_NO_INFORMADO);
						distribucionesAgrupadosDTO.add(distribucionAgrupacionesPuestoDTO);
						
						EstadisticasTablaValoracionesDTO valoracionesPuestoDTO = new EstadisticasTablaValoracionesDTO();
						valoracionesPuestoDTO.setNoTable(Boolean.TRUE);
						valoracionesPuestoDTO.setTextNoTable(PlanIgualdadGenerarInformePortletKeys.DATO_NO_INFORMADO);
						valoracionesDTO.add(valoracionesPuestoDTO);
					}
					
					JRBeanCollectionDataSource beanGraphic = new JRBeanCollectionDataSource(distribucionesDTO);
					parameters.put(PlanIgualdadGenerarInformePortletKeys.GRAPHIC+ sectionId, distribucionesDTO.size()>0 ? beanGraphic : null);
					
					JRBeanCollectionDataSource beanGraphic1 = new JRBeanCollectionDataSource(distribucionesAgrupadosDTO);
					parameters.put(PlanIgualdadGenerarInformePortletKeys.GRAPHIC+ sectionId+"-1", distribucionesAgrupadosDTO.size()>0 ? beanGraphic1 : null);
					
					JRBeanCollectionDataSource beanGraphic2 = new JRBeanCollectionDataSource(valoracionesDTO);
					parameters.put(PlanIgualdadGenerarInformePortletKeys.GRAPHIC+ sectionId+"-2", valoracionesDTO.size()>0 ? beanGraphic2 : null);
				
					parameters.put(PlanIgualdadGenerarInformePortletKeys.COMENTARIO+ sectionId + ".1", !comentario.isEmpty() ? GeneralUtils.deleteHTML(comentario, Boolean.FALSE, Boolean.FALSE) : null);
					parameters.put(PlanIgualdadGenerarInformePortletKeys.COMENTARIO+ sectionId + ".2", !comentario2.isEmpty() ? GeneralUtils.deleteHTML(comentario2, Boolean.FALSE, Boolean.FALSE) : null);	
				}
			}
		}else {
			if(sectionId==15L || sectionId==14L) {					

				List<GraphicDTO> listGraphics = getListGraphics(compId, sectionId, versionId, PlanIgualdadGenerarInformePortletKeys.GRAPHIC+ sectionId+ ".1");
				JRBeanCollectionDataSource beanGraphic = new JRBeanCollectionDataSource(listGraphics);
				parameters.put(PlanIgualdadGenerarInformePortletKeys.GRAPHIC+ sectionId+ ".1", beanGraphic);	
				
				List<GraphicDTO> listGraphics2 = getListGraphics(compId, sectionId, versionId, PlanIgualdadGenerarInformePortletKeys.GRAPHIC+ sectionId+ ".2");
				JRBeanCollectionDataSource beanGraphic2 = new JRBeanCollectionDataSource(listGraphics2);
				parameters.put(PlanIgualdadGenerarInformePortletKeys.GRAPHIC+ sectionId+ ".2", beanGraphic2);	
			}else if(sectionId==8L) {
				List<GraphicDTO> listGraphics1 = getListGraphics(compId, sectionId, versionId, PlanIgualdadGenerarInformePortletKeys.GRAPHIC+ sectionId+ ".1");
				JRBeanCollectionDataSource beanGraphic1 = new JRBeanCollectionDataSource(listGraphics1);
				parameters.put(PlanIgualdadGenerarInformePortletKeys.GRAPHIC+ sectionId+ ".1", beanGraphic1);	
				
				List<GraphicDTO> listGraphics2 = getListGraphics(compId, sectionId, versionId, PlanIgualdadGenerarInformePortletKeys.GRAPHIC+ sectionId+ ".2");
				JRBeanCollectionDataSource beanGraphic2 = new JRBeanCollectionDataSource(listGraphics2);
				parameters.put(PlanIgualdadGenerarInformePortletKeys.GRAPHIC+ sectionId+ ".2", beanGraphic2);	
				
				List<GraphicDTO> listGraphics3 = getListGraphics(compId, sectionId, versionId, PlanIgualdadGenerarInformePortletKeys.GRAPHIC+ sectionId+ ".3");
				JRBeanCollectionDataSource beanGraphic3 = new JRBeanCollectionDataSource(listGraphics3);
				parameters.put(PlanIgualdadGenerarInformePortletKeys.GRAPHIC+ sectionId+ ".3", beanGraphic3);	
			
			}else if(sectionId==2L) {
				List<EstadisticaTablaAreaPuestoDTO> areaPuestoDTOs = new ArrayList<>();
				EstadisticaTablaAreaPuestoDTO areaPuestoDTO = new EstadisticaTablaAreaPuestoDTO();
				areaPuestoDTO.setNoTable(Boolean.TRUE);
				areaPuestoDTO.setTextNoTable(PlanIgualdadGenerarInformePortletKeys.DATO_NO_INFORMADO);
				areaPuestoDTOs.add(areaPuestoDTO);
				JRBeanCollectionDataSource beanGraphic = new JRBeanCollectionDataSource(areaPuestoDTOs);
				parameters.put(PlanIgualdadGenerarInformePortletKeys.GRAPHIC+ sectionId, areaPuestoDTOs.size()>0 ? beanGraphic : null);	
			}else if(sectionId==5L) {
				List<EstadisticaTablaPuestoAntiguedadDTO> puestoAntiguedads = new ArrayList<>();
				EstadisticaTablaPuestoAntiguedadDTO areaPuestoDTO = new EstadisticaTablaPuestoAntiguedadDTO();
				areaPuestoDTO.setNoTable(Boolean.TRUE);
				areaPuestoDTO.setTextNoTable(PlanIgualdadGenerarInformePortletKeys.DATO_NO_INFORMADO);
				puestoAntiguedads.add(areaPuestoDTO);
		
				JRBeanCollectionDataSource beanGraphic = new JRBeanCollectionDataSource(puestoAntiguedads);
				parameters.put(PlanIgualdadGenerarInformePortletKeys.GRAPHIC+ sectionId, puestoAntiguedads.size()>0 ? beanGraphic : null);
			}else if(sectionId==6L) {
				List<EstadisticasTablaNivelEstudiosYPuestoDTO> estudiosYPuestos =  new ArrayList<>();
				EstadisticasTablaNivelEstudiosYPuestoDTO areaPuestoDTO = new EstadisticasTablaNivelEstudiosYPuestoDTO();
				areaPuestoDTO.setNoTable(Boolean.TRUE);
				areaPuestoDTO.setTextNoTable(PlanIgualdadGenerarInformePortletKeys.DATO_NO_INFORMADO);
				estudiosYPuestos.add(areaPuestoDTO);
				JRBeanCollectionDataSource beanGraphic = new JRBeanCollectionDataSource(estudiosYPuestos);
				parameters.put(PlanIgualdadGenerarInformePortletKeys.GRAPHIC+ sectionId, estudiosYPuestos.size()>0 ? beanGraphic : null);
				ResourceBundle bundle = ResourceBundle.getBundle("content/Language", themeDisplay.getUser().getLocale());
				parameters.putAll(JSONArrayInformeUtils.getTable2PuestoEstudios(compId, versionId, bundle, estadistica));
			}else if(sectionId==7L) {
				List<EstadisticasTablaNivelEstudiosYPuestoDTO> estudiosYPuestos =  new ArrayList<>();
				EstadisticasTablaNivelEstudiosYPuestoDTO areaPuestoDTO = new EstadisticasTablaNivelEstudiosYPuestoDTO();
				areaPuestoDTO.setNoTable(Boolean.TRUE);
				areaPuestoDTO.setTextNoTable(PlanIgualdadGenerarInformePortletKeys.DATO_NO_INFORMADO);
				estudiosYPuestos.add(areaPuestoDTO);
				JRBeanCollectionDataSource beanGraphic = new JRBeanCollectionDataSource(estudiosYPuestos);
				parameters.put(PlanIgualdadGenerarInformePortletKeys.GRAPHIC+ sectionId, estudiosYPuestos.size()>0 ? beanGraphic : null);
			}else if(sectionId==10L) {
				List<EstadisticaTablaHorarioTurnoDTO> horarioTurnosDTO =  new ArrayList<>();
				EstadisticaTablaHorarioTurnoDTO areaPuestoDTO = new EstadisticaTablaHorarioTurnoDTO();
				areaPuestoDTO.setNoTable(Boolean.TRUE);
				areaPuestoDTO.setTextNoTable(PlanIgualdadGenerarInformePortletKeys.DATO_NO_INFORMADO);
				horarioTurnosDTO.add(areaPuestoDTO);
				JRBeanCollectionDataSource beanGraphic = new JRBeanCollectionDataSource(horarioTurnosDTO);
				parameters.put(PlanIgualdadGenerarInformePortletKeys.GRAPHIC+ sectionId, horarioTurnosDTO.size()>0 ? beanGraphic : null);
			}else if(sectionId==12L) {
				List<EstadisticasTablaCeseDTO> cesesDTO =  new ArrayList<>();
				EstadisticasTablaCeseDTO areaPuestoDTO = new EstadisticasTablaCeseDTO();
				areaPuestoDTO.setNoTable(Boolean.TRUE);
				areaPuestoDTO.setTextNoTable(PlanIgualdadGenerarInformePortletKeys.DATO_NO_INFORMADO);
				cesesDTO.add(areaPuestoDTO);
				JRBeanCollectionDataSource beanGraphic = new JRBeanCollectionDataSource(cesesDTO);
				parameters.put(PlanIgualdadGenerarInformePortletKeys.GRAPHIC+ sectionId, cesesDTO.size()>0 ? beanGraphic : null);
			}else if(sectionId==13L) {
				List<EstadisticasTablaIncapacidadDTO> incapacidadDTO =  new ArrayList<>();
				EstadisticasTablaIncapacidadDTO areaPuestoDTO = new EstadisticasTablaIncapacidadDTO();
				areaPuestoDTO.setNoTable(Boolean.TRUE);
				areaPuestoDTO.setTextNoTable(PlanIgualdadGenerarInformePortletKeys.DATO_NO_INFORMADO);
				incapacidadDTO.add(areaPuestoDTO);
				JRBeanCollectionDataSource beanGraphic = new JRBeanCollectionDataSource(incapacidadDTO);
				parameters.put(PlanIgualdadGenerarInformePortletKeys.GRAPHIC+ sectionId, incapacidadDTO.size()>0 ? beanGraphic : null);
			}else if(sectionId==18L) {
				List<EstadisticasTablaAuditoriaDTO> incapacidadDTO =  new ArrayList<>();
				EstadisticasTablaAuditoriaDTO areaPuestoDTO = new EstadisticasTablaAuditoriaDTO();
				areaPuestoDTO.setNoTable(Boolean.TRUE);
				areaPuestoDTO.setTextNoTable(PlanIgualdadGenerarInformePortletKeys.DATO_NO_INFORMADO);
				incapacidadDTO.add(areaPuestoDTO);
				JRBeanCollectionDataSource beanGraphic = new JRBeanCollectionDataSource(incapacidadDTO);
				parameters.put(PlanIgualdadGenerarInformePortletKeys.GRAPHIC+ sectionId, incapacidadDTO.size()>0 ? beanGraphic : null);
			}else if(sectionId==16L){
				List<EstadisticasTablaDistribucionPuestoDTO> distribucionesDTO =  new ArrayList<>();
				EstadisticasTablaDistribucionPuestoDTO distribucionPuestoDTO = new EstadisticasTablaDistribucionPuestoDTO();
				distribucionPuestoDTO.setNoTable(Boolean.TRUE);
				distribucionPuestoDTO.setTextNoTable(PlanIgualdadGenerarInformePortletKeys.DATO_NO_INFORMADO);
				distribucionesDTO.add(distribucionPuestoDTO);
				JRBeanCollectionDataSource beanGraphic = new JRBeanCollectionDataSource(distribucionesDTO);
				parameters.put(PlanIgualdadGenerarInformePortletKeys.GRAPHIC+ sectionId, distribucionesDTO.size()>0 ? beanGraphic : null);
			
				List<EstadisticasTablaDistribucionPuestoDTO> distribucionesAgrupacionesDTO =  new ArrayList<>();
				EstadisticasTablaDistribucionPuestoDTO distribucionAgrupacionPuestoDTO = new EstadisticasTablaDistribucionPuestoDTO();
				distribucionAgrupacionPuestoDTO.setNoTable(Boolean.TRUE);
				distribucionAgrupacionPuestoDTO.setTextNoTable(PlanIgualdadGenerarInformePortletKeys.DATO_NO_INFORMADO);
				distribucionesAgrupacionesDTO.add(distribucionAgrupacionPuestoDTO);
				JRBeanCollectionDataSource beanGraphic1 = new JRBeanCollectionDataSource(distribucionesAgrupacionesDTO);
				parameters.put(PlanIgualdadGenerarInformePortletKeys.GRAPHIC+ sectionId+ "-1", distribucionesAgrupacionesDTO.size()>0 ? beanGraphic1 : null);
				
				List<EstadisticasTablaValoracionesDTO> valoracionesDTO =  new ArrayList<>();
				EstadisticasTablaValoracionesDTO valoracionDTO = new EstadisticasTablaValoracionesDTO();
				valoracionDTO.setNoTable(Boolean.TRUE);
				valoracionDTO.setTextNoTable(PlanIgualdadGenerarInformePortletKeys.DATO_NO_INFORMADO);
				valoracionesDTO.add(valoracionDTO);
				JRBeanCollectionDataSource beanGraphic2 = new JRBeanCollectionDataSource(valoracionesDTO);
				parameters.put(PlanIgualdadGenerarInformePortletKeys.GRAPHIC+ sectionId + "-2", valoracionesDTO.size()>0 ? beanGraphic2 : null);
			}else {
				List<GraphicDTO> listGraphics = getListGraphics(compId, sectionId, versionId, PlanIgualdadGenerarInformePortletKeys.GRAPHIC+ sectionId);
				JRBeanCollectionDataSource beanGraphic = new JRBeanCollectionDataSource(listGraphics);			
				parameters.put(PlanIgualdadGenerarInformePortletKeys.GRAPHIC+ sectionId, beanGraphic);	

			}
			
		}
		parameters.put(PlanIgualdadGenerarInformePortletKeys.VALORACION+ sectionId, !valoracion.isEmpty() ? GeneralUtils.deleteHTML(valoracion, Boolean.FALSE, Boolean.FALSE) : PlanIgualdadGenerarInformePortletKeys.DATO_NO_INFORMADO);
		
		return parameters;
	}
	
	private static EstadisticasTablaValoracionesDTO getValoracionDTO(JSONObject object, Boolean printHeader) {
		EstadisticasTablaValoracionesDTO valoracionDTO = new EstadisticasTablaValoracionesDTO();
		
		if(printHeader) {
			valoracionDTO.setPrintHeader(Boolean.TRUE);
		}
		
		valoracionDTO.setArea(object.getString("area"));
		valoracionDTO.setCentroTrabajo(object.getString("centro"));
		valoracionDTO.setConvenio(object.getString("convenio"));
		valoracionDTO.setDepartamento(object.getString("departamento"));
		valoracionDTO.setIsComponentValoracion(Boolean.TRUE);
		valoracionDTO.setTituloPuesto(object.getString("tituloPuesto"));
		
		return valoracionDTO;
	}
	
	private static EstadisticasTablaDistribucionPuestoDTO getDistribucionDTO(JSONObject object, Boolean isComponentHeader,  Boolean printHeader) {
		EstadisticasTablaDistribucionPuestoDTO distribucionDTO = new EstadisticasTablaDistribucionPuestoDTO();
		
		if(printHeader) {
			distribucionDTO.setPrintHeader(Boolean.TRUE);
		}else {
			if(isComponentHeader) {
				distribucionDTO.setIsComponentAgrupacion(Boolean.TRUE);
			}else {
				distribucionDTO.setIsComponentDistribucion(Boolean.TRUE);
			}
			
			distribucionDTO.setAgrupacion(object.getString("agrupacion"));
			distribucionDTO.setCategorizacion(object.getString("categorizacion"));
			distribucionDTO.setnHombres(object.getString("nHombres"));
			distribucionDTO.setnMujeres(object.getString("nMujeres"));
			distribucionDTO.setPuntos(object.getString("puntos"));
			distribucionDTO.setTituloPuesto(object.getString("tituloPuesto"));
			
		}

		return distribucionDTO;
	}
	
	
	/**
	 * 
	 * @param object
	 * @param isComponentHeader
	 * @param printHeader
	 * @return
	 */
	private static EstadisticasTablaAuditoriaDTO getAuditoriaDTO(JSONObject object, Boolean isComponentHeader, Boolean printHeader) {
		EstadisticasTablaAuditoriaDTO tablaIncapacidad = new EstadisticasTablaAuditoriaDTO();
		
		if(printHeader) {
			tablaIncapacidad.setPrintHeader(printHeader);
		}else {
			if(isComponentHeader) {
				tablaIncapacidad.setIsComponentSalario(Boolean.TRUE);
			}else {
				tablaIncapacidad.setIsComponentSalarioInfo(Boolean.TRUE);
			}
			
			tablaIncapacidad.setTipoSalario(object.getString("tipoSalario"));
			tablaIncapacidad.setSalario(object.getString("salario"));
			tablaIncapacidad.setHombres(object.getString("hombres"));
			tablaIncapacidad.setMujeres(object.getString("mujeres"));
			tablaIncapacidad.setDiferencias(object.getString("diferencias"));
			tablaIncapacidad.setConceptos(object.getString("conceptos"));
				
		}
		
		return tablaIncapacidad;
	}
	
	/**
	 * 
	 * @param object
	 * @param printHeader
	 * @return
	 */
	private static EstadisticasTablaIncapacidadDTO getIncapacidadDTO(JSONObject object, Boolean printHeader) {
		EstadisticasTablaIncapacidadDTO tablaIncapacidad = new EstadisticasTablaIncapacidadDTO();
		
		if(printHeader) {
			tablaIncapacidad.setPrintHeader(printHeader);
		}else {
			if(object.getString("tipoIncapacidad").equals("Total")) {
				tablaIncapacidad.setIsComponentTotal(Boolean.TRUE);
			}else {
				tablaIncapacidad.setIsComponentIncapacidad(Boolean.TRUE);
			}
			
			tablaIncapacidad.setTipoIncapacidad(object.getString("tipoIncapacidad"));
			tablaIncapacidad.setnHombres(object.getString("nHombres"));
			tablaIncapacidad.setnMujeres(object.getString("nMujeres"));
			tablaIncapacidad.setDiasHombres(object.getString("diasHombres"));
			tablaIncapacidad.setDiasMujeres(object.getString("diasMujeres"));
			tablaIncapacidad.setDiasTotal(object.getString("diasTotal"));
			tablaIncapacidad.setPorcHombres(object.getString("porcHombres"));
			tablaIncapacidad.setPorcMujeres(object.getString("porcMujeres"));
			tablaIncapacidad.setPorcTotal(object.getString("porcTotal"));
			tablaIncapacidad.setPromedioHombres(object.getString("promedioHombres"));
			tablaIncapacidad.setPromedioMujeres(object.getString("promedioMujeres"));
			tablaIncapacidad.setPromedioTotal(object.getString("promedioTotal"));	
		}
		
		return tablaIncapacidad;
	}
	
	/**
	 * 
	 * @param object
	 * @param printHeader
	 * @return
	 */
	private static EstadisticasTablaCeseDTO getCeseDTO(JSONObject object, Boolean printHeader) {
		EstadisticasTablaCeseDTO tablaCese = new EstadisticasTablaCeseDTO();
		
		if(printHeader) {
			tablaCese.setPrintHeader(printHeader);
		}else {
			if(object.getString("tipoCese").equals("Total")) {
				tablaCese.setIsComponentTotal(Boolean.TRUE);
			}else {
				tablaCese.setIsComponentCese(Boolean.TRUE);
			}
			
			tablaCese.setTipoCese(object.getString("tipoCese"));
			tablaCese.setnHombres(object.getString("nHombres"));
			tablaCese.setnMujeres(object.getString("nMujeres"));
			tablaCese.setPorcHombres(object.getString("nHombresPorc"));
			tablaCese.setPorcMujeres(object.getString("nMujeresPorc"));
			tablaCese.setPorcTotal(object.getString("totalPorc"));
			tablaCese.setTotal(object.getString("total"));

		}
		
		return tablaCese;
	}
	
	/**
	 * 
	 * @param horarioTurno
	 * @param isComponentHroario
	 * @param printHeader
	 * @return
	 */
	private static EstadisticaTablaHorarioTurnoDTO getHorarioTurnoDTO(JSONObject horarioTurno, Boolean isComponentHroario, Boolean printHeader) {
		
		EstadisticaTablaHorarioTurnoDTO horarioTurnoDTO = new EstadisticaTablaHorarioTurnoDTO();
		if(printHeader) {
			horarioTurnoDTO.setPrintHeader(printHeader);
		}
		
		if(isComponentHroario) {
			horarioTurnoDTO.setIsComponentHorario(Boolean.TRUE);
			horarioTurnoDTO.setHorario(horarioTurno.getString("horario"));
		}
		
		if(horarioTurno.getString("turno").equals("Total")) {
			horarioTurnoDTO.setIsComponentTotal(Boolean.TRUE);
		}else {
			horarioTurnoDTO.setIsComponentTurno(Boolean.TRUE);
		}
		
		horarioTurnoDTO.setTurno(horarioTurno.getString("turno"));
		horarioTurnoDTO.setnHombres(horarioTurno.getString("nHombres"));
		horarioTurnoDTO.setnMujeres(horarioTurno.getString("nMujeres"));
		horarioTurnoDTO.setPorcHombres(horarioTurno.getString("porcHombres"));
		horarioTurnoDTO.setPorcMujeres(horarioTurno.getString("porcMujeres"));
		horarioTurnoDTO.setTotal(horarioTurno.getString("total"));
		horarioTurnoDTO.setPorcTotal(horarioTurno.getString("porcTotal"));
		
		return horarioTurnoDTO;
	}
	
	/**
	 * 
	 * @param puestoNivelEstudio
	 * @param isComponentArea
	 * @param printHeader
	 * @return
	 */
	private static EstadisticasTablaNivelEstudiosYPuestoDTO getPuestoNivelEstudioDTO(JSONObject puestoNivelEstudio, Boolean isComponentArea, Boolean printHeader) {
		
		EstadisticasTablaNivelEstudiosYPuestoDTO puestoNivelEstudioDTO = new EstadisticasTablaNivelEstudiosYPuestoDTO();
		if(printHeader) {
			puestoNivelEstudioDTO.setPrintHeader(printHeader);
		}else {
			if(isComponentArea) {
				puestoNivelEstudioDTO.setIsComponentPuesto(Boolean.TRUE);
				puestoNivelEstudioDTO.setPuesto(puestoNivelEstudio.getString("puesto"));
			}else {
				puestoNivelEstudioDTO.setIsComponentEstudios(Boolean.TRUE);
			}
		
			puestoNivelEstudioDTO.setNivelEstudios(puestoNivelEstudio.getString("nivelEstudio"));
			puestoNivelEstudioDTO.setnHombres(puestoNivelEstudio.getString("hombres"));
			puestoNivelEstudioDTO.setnMujeres(puestoNivelEstudio.getString("mujeres"));
			puestoNivelEstudioDTO.setTotal(puestoNivelEstudio.getString("total"));
		}

		return puestoNivelEstudioDTO;
	}
	
	
	/**
	 * 
	 * @param puestoAntiguedad
	 * @param isComponentArea
	 * @param printHeader
	 * @return
	 */
	private static EstadisticaTablaPuestoAntiguedadDTO getPuestoAntiguedadDTO(JSONObject puestoAntiguedad, Boolean isComponentArea, Boolean printHeader) {
		
		EstadisticaTablaPuestoAntiguedadDTO puestoAntiguedadDTO = new EstadisticaTablaPuestoAntiguedadDTO();
		if(printHeader) {
			puestoAntiguedadDTO.setPrintHeader(printHeader);
		}
		
		if(isComponentArea) {
			puestoAntiguedadDTO.setIsComponentPuesto(Boolean.TRUE);
			puestoAntiguedadDTO.setPuesto(puestoAntiguedad.getString("puesto"));
		}
		
		if(puestoAntiguedad.getString("rangoEdad").equals("Total")) {
			puestoAntiguedadDTO.setIsComponentTotal(Boolean.TRUE);
		}else {
			puestoAntiguedadDTO.setIsComponentEdad(Boolean.TRUE);
		}
		puestoAntiguedadDTO.setRangoEdad(puestoAntiguedad.getString("rangoEdad"));
		puestoAntiguedadDTO.setTotalHombres(puestoAntiguedad.getString("hombres"));
		puestoAntiguedadDTO.setrFamiliarHombres(puestoAntiguedad.getString("hombresR"));
		puestoAntiguedadDTO.setrFamiliarPorcHombres(puestoAntiguedad.getString("pHombres"));
		puestoAntiguedadDTO.setTotalMujeres(puestoAntiguedad.getString("mujeres"));
		puestoAntiguedadDTO.setrFamiliarMujeres(puestoAntiguedad.getString("mujeresR"));
		puestoAntiguedadDTO.setrFamiliarPorcMujeres(puestoAntiguedad.getString("pMujeres"));
		
		return puestoAntiguedadDTO;
	}
	
	/**
	 * 
	 * @param areaPuesto
	 * @param isComponentArea
	 * @param printHeader
	 * @return
	 */
	private static EstadisticaTablaAreaPuestoDTO getAreaPuestoDTO(JSONObject areaPuesto, Boolean isComponentArea, Boolean printHeader) {
		EstadisticaTablaAreaPuestoDTO areaPuestoDTO = new EstadisticaTablaAreaPuestoDTO();
		
		if(printHeader) {
			areaPuestoDTO.setPrintHeader(printHeader);
		}
		
		if(isComponentArea) {
			areaPuestoDTO.setIsComponentArea(Boolean.TRUE);
			areaPuestoDTO.setArea(areaPuesto.getString("area"));
		}

		if(areaPuesto.getString("puesto").equals("Total")) {
			areaPuestoDTO.setIsComponentTotal(Boolean.TRUE);
		}else {
			areaPuestoDTO.setIsComponentPuesto(Boolean.TRUE);
		}
		
		areaPuestoDTO.setAnalisis(areaPuesto.getString("analisis"));
		areaPuestoDTO.setnHombres(areaPuesto.getString("nHombres"));
		areaPuestoDTO.setnMujeres(areaPuesto.getString("nMujeres"));
		areaPuestoDTO.setPersonas(areaPuesto.getString("personas"));
		areaPuestoDTO.setpHombres(areaPuesto.getString("pHombres"));
		areaPuestoDTO.setPuesto(areaPuesto.getString("puesto"));
		areaPuestoDTO.setpMujeres(areaPuesto.getString("pMujeres"));
		
		return areaPuestoDTO;
	}
	
	/**
	 * 
	 * @param compId
	 * @param sectionId
	 * @param versionId
	 * @param canvasId
	 * @return
	 * @throws IOException
	 */
	private static List<GraphicDTO> getListGraphics (Long compId, Long sectionId, Long versionId, String canvasId) throws IOException{
		 List<GraphicDTO> listGraphics = new ArrayList<>();
		 
		 List<ImagenCanvasEst> canvasEsts = ImagenCanvasEstLocalServiceUtil.fetchImagenesCanvasBySection(compId, versionId, sectionId, canvasId);
		
		 if(Validator.isNotNull(canvasEsts) && !canvasEsts.isEmpty()) {
			 for(ImagenCanvasEst canvasEst : canvasEsts) {
				 GraphicDTO dto = new GraphicDTO();
				 String canvasBase64 = canvasEst.getCanvasImage();
				 BufferedImage img = null;
				if(Validator.isNotNull(canvasBase64) && !canvasBase64.isEmpty() && !canvasBase64.equals("data:,")) {
					String base64Image = canvasBase64.split(",")[1];
					byte[] imageBytes = javax.xml.bind.DatatypeConverter.parseBase64Binary(base64Image);
					img = ImageIO.read(new ByteArrayInputStream(imageBytes));
					 dto.setGraphic(img);
					 dto.setHaveGraphic(Boolean.TRUE);
					 dto.setTextNoGraphic(StringPool.BLANK);
					 listGraphics.add(dto);
				}else {
					 dto.setHaveGraphic(Boolean.FALSE);
					 dto.setTextNoGraphic(PlanIgualdadGenerarInformePortletKeys.DATO_NO_INFORMADO);
					 listGraphics.add(dto);
				}
				
			 }
		 }else {
			 GraphicDTO dto = new GraphicDTO();
			 dto.setHaveGraphic(Boolean.FALSE);
			 dto.setTextNoGraphic(PlanIgualdadGenerarInformePortletKeys.DATO_NO_INFORMADO);
			 listGraphics.add(dto);
		 }
		 
		 return listGraphics;
	}
}
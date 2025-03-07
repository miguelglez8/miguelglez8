package com.adeplus.liferay.portlet.documentary.repository.web.util;

import com.adeplus.liferay.portlet.documentary.repository.web.constants.AdeplusDocumentaryRepositoryPortletKeys;
import com.adeplus.liferay.portlet.documentary.repository.web.portlet.dto.EstadisticaTablaAreaPuestoDTO;
import com.adeplus.liferay.portlet.documentary.repository.web.portlet.dto.EstadisticaTablaHorarioTurnoDTO;
import com.adeplus.liferay.portlet.documentary.repository.web.portlet.dto.EstadisticaTablaPuestoAntiguedadDTO;
import com.adeplus.liferay.portlet.documentary.repository.web.portlet.dto.EstadisticasTablaAuditoriaDTO;
import com.adeplus.liferay.portlet.documentary.repository.web.portlet.dto.EstadisticasTablaCeseDTO;
import com.adeplus.liferay.portlet.documentary.repository.web.portlet.dto.EstadisticasTablaDistribucionPuestoDTO;
import com.adeplus.liferay.portlet.documentary.repository.web.portlet.dto.EstadisticasTablaIncapacidadDTO;
import com.adeplus.liferay.portlet.documentary.repository.web.portlet.dto.EstadisticasTablaNivelEstudiosYPuestoDTO;
import com.adeplus.liferay.portlet.documentary.repository.web.portlet.dto.EstadisticasTablaValoracionesDTO;
import com.adeplus.liferay.portlet.documentary.repository.web.portlet.dto.GraphicDTO;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.Validator;
import com.plan.igualdad.liferay.portlet.manager.model.ImagenCanvasEst;
import com.plan.igualdad.liferay.portlet.manager.service.ImagenCanvasEstLocalServiceUtil;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class GraphicUtils {
	
	public static Map<String, Object> getAllGraphics (Long compId, Long versionId) throws IOException{
		Map<String, Object> parameters = new HashMap<>();
		List<GraphicDTO> listGraphics0 =GraphicUtils.getListGraphics(compId, 0L,versionId, "graphic0");
		JRBeanCollectionDataSource beanGraphic0 = new JRBeanCollectionDataSource(listGraphics0);
		parameters.put("graphic0", beanGraphic0);
		
		List<GraphicDTO> listGraphics1 =GraphicUtils.getListGraphics(compId, 1L, versionId, "graphic1");
		JRBeanCollectionDataSource beanGraphic1 = new JRBeanCollectionDataSource(listGraphics1);
		parameters.put("graphic1", beanGraphic1);
		
		List<GraphicDTO> listGraphic151 =GraphicUtils.getListGraphics(compId, 15L, versionId, "graphic15.1");
		JRBeanCollectionDataSource beanGraphic151 = new JRBeanCollectionDataSource(listGraphic151);
		parameters.put("graphic15.1", beanGraphic151);
		
		List<GraphicDTO> listGraphic3 =GraphicUtils.getListGraphics(compId, 3L, versionId, "graphic3");
		JRBeanCollectionDataSource beanGraphic3 = new JRBeanCollectionDataSource(listGraphic3);
		parameters.put("graphic3", beanGraphic3);
		
		List<GraphicDTO> listGraphic4 =GraphicUtils.getListGraphics(compId, 4L,versionId, "graphic4");
		JRBeanCollectionDataSource beanGraphic4 = new JRBeanCollectionDataSource(listGraphic4);
		parameters.put("graphic4", beanGraphic4);
		
		List<GraphicDTO> listGraphic141 =GraphicUtils.getListGraphics(compId, 14L, versionId, "graphic14.1");
		JRBeanCollectionDataSource beanGraphic141 = new JRBeanCollectionDataSource(listGraphic141);
		parameters.put("graphic14.1", beanGraphic141);
		
		List<GraphicDTO> listGraphic142 =GraphicUtils.getListGraphics(compId, 14L,versionId, "graphic14.2");
		JRBeanCollectionDataSource beanGraphic142 = new JRBeanCollectionDataSource(listGraphic142);
		parameters.put("graphic14.2", beanGraphic142);
		
		List<GraphicDTO> listGraphic161 =GraphicUtils.getListGraphics(compId, 16L, versionId, "graphic16.1");
		JRBeanCollectionDataSource beanGraphic161 = new JRBeanCollectionDataSource(listGraphic161);
		parameters.put("graphic16.1", beanGraphic161);
		
		List<GraphicDTO> listGraphic162 =GraphicUtils.getListGraphics(compId, 16L,versionId, "graphic16.2");
		JRBeanCollectionDataSource beanGraphic162 = new JRBeanCollectionDataSource(listGraphic162);
		parameters.put("graphic16.2", beanGraphic162);
		
		List<GraphicDTO> listGraphic81 =GraphicUtils.getListGraphics(compId, 8L, versionId, "graphic8.1");
		JRBeanCollectionDataSource beanGraphic81 = new JRBeanCollectionDataSource(listGraphic81);
		parameters.put("graphic8.1", beanGraphic81);
		
		List<GraphicDTO> listGraphic82 =GraphicUtils.getListGraphics(compId, 8L,versionId, "graphic8.2");
		JRBeanCollectionDataSource beanGraphic82 = new JRBeanCollectionDataSource(listGraphic82);
		parameters.put("graphic8.2", beanGraphic82);
		
		List<GraphicDTO> listGraphic9 =GraphicUtils.getListGraphics(compId, 9L, versionId, "graphic9");
		JRBeanCollectionDataSource beanGraphic9 = new JRBeanCollectionDataSource(listGraphic9);
		parameters.put("graphic9", beanGraphic9);
		
		List<GraphicDTO> listGraphic11 =GraphicUtils.getListGraphics(compId, 11L, versionId, "graphic11");
		JRBeanCollectionDataSource beanGraphic11 = new JRBeanCollectionDataSource(listGraphic11);
		parameters.put("graphic11", beanGraphic11);
		return parameters;
	}

	public static List<GraphicDTO> getListGraphics (Long compId, Long sectionId, Long versionId, String canvasId) throws IOException{
		 List<GraphicDTO> listGraphics = new ArrayList<>();
		 List<ImagenCanvasEst> canvasEsts = ImagenCanvasEstLocalServiceUtil.fetchImagenesCanvasBySection(compId, versionId, sectionId, canvasId);
		 if(Validator.isNotNull(canvasEsts) || canvasEsts.size()>0) {
			 for(ImagenCanvasEst canvasEst : canvasEsts) {
				 GraphicDTO dto = new GraphicDTO();
				 String canvasBase64 = canvasEst.getCanvasImage();
				 BufferedImage img = null;
				if(Validator.isNotNull(canvasBase64) && !canvasBase64.isEmpty()) {
					String base64Image = canvasBase64.split(",")[1];
					byte[] imageBytes = javax.xml.bind.DatatypeConverter.parseBase64Binary(base64Image);
					img = ImageIO.read(new ByteArrayInputStream(imageBytes));
					 dto.setGraphic(img);
					 dto.setHaveGraphic(Boolean.TRUE);
					 dto.setTextNoGraphic(StringPool.BLANK);
					 listGraphics.add(dto);
				}else {
					 dto.setHaveGraphic(Boolean.FALSE);
					 dto.setTextNoGraphic(AdeplusDocumentaryRepositoryPortletKeys.DATO_NO_INFORMADO);
					 listGraphics.add(dto);
				}
				
			 }
		 }else {
			 GraphicDTO dto = new GraphicDTO();
			 dto.setHaveGraphic(Boolean.FALSE);
			 dto.setTextNoGraphic(AdeplusDocumentaryRepositoryPortletKeys.DATO_NO_INFORMADO);
			 listGraphics.add(dto);
		 }
		 
		 return listGraphics;
	}
	
	public static List<EstadisticasTablaValoracionesDTO> getListValoraciones(JSONArray ja_data) {
		List<EstadisticasTablaValoracionesDTO> dtoList = new ArrayList<>();
		if(ja_data.length()>0) {
			for(int i=0; i<ja_data.length(); i++) {
				JSONObject object = ja_data.getJSONObject(i);
				if(i==0) {
					dtoList.add(getValoracionDTO(object, Boolean.TRUE));
				}else {
					dtoList.add(getValoracionDTO(object, Boolean.FALSE));
				}
			}
		}else {
			EstadisticasTablaValoracionesDTO areaPuestoDTO = new EstadisticasTablaValoracionesDTO();
			areaPuestoDTO.setNoTable(Boolean.TRUE);
			areaPuestoDTO.setTextNoTable(AdeplusDocumentaryRepositoryPortletKeys.DATO_NO_INFORMADO);
			dtoList.add(areaPuestoDTO);
		}
		return dtoList;
	}
	
	public static List<EstadisticasTablaDistribucionPuestoDTO> getListDistribuciones(JSONArray ja_data, Boolean haveHeader) {
		List<EstadisticasTablaDistribucionPuestoDTO> dtoList= new ArrayList<>();
		String subHeaderDifferent = StringPool.BLANK;
		if(ja_data.length()>0) {
			for(int i=0; i<ja_data.length(); i++) {
				JSONObject object = ja_data.getJSONObject(i);
				if(i==0) {
					dtoList.add(getDistribucionDTO(object, Boolean.FALSE, Boolean.TRUE));
				}
				
				if( haveHeader && !subHeaderDifferent.equals(object.getString("agrupacion"))) {
					dtoList.add(getDistribucionDTO(object, Boolean.TRUE,  Boolean.FALSE));
					subHeaderDifferent=object.getString("agrupacion");
				}
				dtoList.add(getDistribucionDTO(object, Boolean.FALSE, Boolean.FALSE));
			}
		}else {
			EstadisticasTablaDistribucionPuestoDTO areaPuestoDTO = new EstadisticasTablaDistribucionPuestoDTO();
			areaPuestoDTO.setNoTable(Boolean.TRUE);
			areaPuestoDTO.setTextNoTable(AdeplusDocumentaryRepositoryPortletKeys.DATO_NO_INFORMADO);
			dtoList.add(areaPuestoDTO);
		}
		return dtoList;
	}
	
	public static List<EstadisticaTablaAreaPuestoDTO> getListAreaPuestos(JSONArray ja_data) {
		List<EstadisticaTablaAreaPuestoDTO> areaPuestoDTOs = new ArrayList<>();
		String subHeaderDifferent=StringPool.BLANK;
		Boolean printHeader = Boolean.TRUE;
		
		if(ja_data.length()>0) {
			for(int i=0; i<ja_data.length(); i++) {
				JSONObject areaPuesto = ja_data.getJSONObject(i);
				
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
			areaPuestoDTO.setTextNoTable(AdeplusDocumentaryRepositoryPortletKeys.DATO_NO_INFORMADO);
			areaPuestoDTOs.add(areaPuestoDTO);
		}
		
		return areaPuestoDTOs;
	}
	
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
	
	public static List<EstadisticasTablaAuditoriaDTO> getListAuditorias(JSONArray ja_data) {
		List<EstadisticasTablaAuditoriaDTO> areaPuestoDTOs = new ArrayList<>();
		String subHeaderDifferent=StringPool.BLANK;
		if(ja_data.length()>0) {
			for(int i=0; i<ja_data.length(); i++) {
				JSONObject object = ja_data.getJSONObject(i);
				if(i==0) {
					areaPuestoDTOs.add(getAuditoriaDTO(object, Boolean.FALSE,  Boolean.TRUE));
				}
				
				if(!subHeaderDifferent.equals(object.getString("tipoSalario"))) {
					areaPuestoDTOs.add(getAuditoriaDTO(object, Boolean.TRUE,  Boolean.FALSE));
					subHeaderDifferent=object.getString("tipoSalario");
				}

				areaPuestoDTOs.add(getAuditoriaDTO(object, Boolean.FALSE,  Boolean.FALSE));
			}
		}else {
			EstadisticasTablaAuditoriaDTO areaPuestoDTO = new EstadisticasTablaAuditoriaDTO();
			areaPuestoDTO.setNoTable(Boolean.TRUE);
			areaPuestoDTO.setTextNoTable(AdeplusDocumentaryRepositoryPortletKeys.DATO_NO_INFORMADO);
			areaPuestoDTOs.add(areaPuestoDTO);
		}
		
		return areaPuestoDTOs;
	}
	
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

	public static List<EstadisticasTablaIncapacidadDTO> getListIncapacidades(JSONArray ja_data) {
		List<EstadisticasTablaIncapacidadDTO> areaPuestoDTOs = new ArrayList<>();

		if(ja_data.length()>0) {
			for(int i=0; i<ja_data.length(); i++) {
				JSONObject object = ja_data.getJSONObject(i);
				if(i==0) {
					areaPuestoDTOs.add(getIncapacidadDTO(object, Boolean.TRUE));
				}
				
				areaPuestoDTOs.add(getIncapacidadDTO(object, Boolean.FALSE));
			}
		}else {
			EstadisticasTablaIncapacidadDTO areaPuestoDTO = new EstadisticasTablaIncapacidadDTO();
			areaPuestoDTO.setNoTable(Boolean.TRUE);
			areaPuestoDTO.setTextNoTable(AdeplusDocumentaryRepositoryPortletKeys.DATO_NO_INFORMADO);
			areaPuestoDTOs.add(areaPuestoDTO);
		}
		
		return areaPuestoDTOs;
	}
	
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

	public static List<EstadisticasTablaCeseDTO> getListCeses(JSONArray ja_data) {
		List<EstadisticasTablaCeseDTO> areaPuestoDTOs = new ArrayList<>();
		
		if(ja_data.length()>0) {
			for(int i=0; i<ja_data.length(); i++) {
				JSONObject object = ja_data.getJSONObject(i);
				if(i==0) {
					areaPuestoDTOs.add(getCeseDTO(object, Boolean.TRUE));
				}
				
				areaPuestoDTOs.add(getCeseDTO(object, Boolean.FALSE));
			}
		}else {
			EstadisticasTablaCeseDTO areaPuestoDTO = new EstadisticasTablaCeseDTO();
			areaPuestoDTO.setNoTable(Boolean.TRUE);
			areaPuestoDTO.setTextNoTable(AdeplusDocumentaryRepositoryPortletKeys.DATO_NO_INFORMADO);
			areaPuestoDTOs.add(areaPuestoDTO);
		}
		
		return areaPuestoDTOs;
	}
	
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

	public static List<EstadisticaTablaHorarioTurnoDTO> getListHorarioTurnos(JSONArray ja_data) {
		List<EstadisticaTablaHorarioTurnoDTO> areaPuestoDTOs = new ArrayList<>();
		String subHeaderDifferent=StringPool.BLANK;
		Boolean printHeader = Boolean.TRUE;
		
		if(ja_data.length()>0) {
			for(int i=0; i<ja_data.length(); i++) {
				JSONObject horarioTurno = ja_data.getJSONObject(i);
				
				if(i>0) {
					printHeader = Boolean.FALSE;
				}
				
				if(!subHeaderDifferent.equals(horarioTurno.getString("horario"))) {
					areaPuestoDTOs.add(getHorarioTurnoDTO(horarioTurno, Boolean.TRUE, printHeader));
					subHeaderDifferent=horarioTurno.getString("horario");
				}else {
					areaPuestoDTOs.add(getHorarioTurnoDTO(horarioTurno, Boolean.FALSE, printHeader));
				}
				
			}
		}else {
			EstadisticaTablaHorarioTurnoDTO areaPuestoDTO = new EstadisticaTablaHorarioTurnoDTO();
			areaPuestoDTO.setNoTable(Boolean.TRUE);
			areaPuestoDTO.setTextNoTable(AdeplusDocumentaryRepositoryPortletKeys.DATO_NO_INFORMADO);
			areaPuestoDTOs.add(areaPuestoDTO);
		}
		return areaPuestoDTOs;
	}
	
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

	public static List<EstadisticasTablaNivelEstudiosYPuestoDTO> getListEstudioYPuestos(JSONArray ja_data) {
		List<EstadisticasTablaNivelEstudiosYPuestoDTO> areaPuestoDTOs = new ArrayList<>();
		String subHeaderDifferent=StringPool.BLANK;
		
		if(ja_data.length()>0) {
			for(int i=0; i<ja_data.length(); i++) {
				JSONObject nivelEstudio = ja_data.getJSONObject(i);
			
				if(i==0) {
					areaPuestoDTOs.add(getPuestoNivelEstudioDTO(nivelEstudio, Boolean.TRUE, Boolean.TRUE));
				}
				
				if(!subHeaderDifferent.equals(nivelEstudio.getString("puesto"))) {
					areaPuestoDTOs.add(getPuestoNivelEstudioDTO(nivelEstudio, Boolean.TRUE, Boolean.FALSE));
					subHeaderDifferent=nivelEstudio.getString("puesto");
				}
				
				areaPuestoDTOs.add(getPuestoNivelEstudioDTO(nivelEstudio, Boolean.FALSE, Boolean.FALSE));
			}
		}else {
			EstadisticasTablaNivelEstudiosYPuestoDTO areaPuestoDTO = new EstadisticasTablaNivelEstudiosYPuestoDTO();
			areaPuestoDTO.setNoTable(Boolean.TRUE);
			areaPuestoDTO.setTextNoTable(AdeplusDocumentaryRepositoryPortletKeys.DATO_NO_INFORMADO);
			areaPuestoDTOs.add(areaPuestoDTO);
		}
		return areaPuestoDTOs;
	}
	
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

	public static List<EstadisticaTablaPuestoAntiguedadDTO> getListPuestoAntiguedad(JSONArray ja_data) {
		List<EstadisticaTablaPuestoAntiguedadDTO> areaPuestoDTOs = new ArrayList<>();
		String subHeaderDifferent=StringPool.BLANK;
		Boolean printHeader = Boolean.TRUE;
		
		if(ja_data.length()>0) {
			for(int i=0; i<ja_data.length(); i++) {
				JSONObject puestoAntiguedad = ja_data.getJSONObject(i);
				
				if(i>0) {
					printHeader = Boolean.FALSE;
				}
				
				if(!subHeaderDifferent.equals(puestoAntiguedad.getString("puesto"))) {
					areaPuestoDTOs.add(getPuestoAntiguedadDTO(puestoAntiguedad, Boolean.TRUE, printHeader));
					subHeaderDifferent=puestoAntiguedad.getString("puesto");
				}else {
					areaPuestoDTOs.add(getPuestoAntiguedadDTO(puestoAntiguedad, Boolean.FALSE, printHeader));
				}
				
			}
		}else {
			EstadisticaTablaPuestoAntiguedadDTO areaPuestoDTO = new EstadisticaTablaPuestoAntiguedadDTO();
			areaPuestoDTO.setNoTable(Boolean.TRUE);
			areaPuestoDTO.setTextNoTable(AdeplusDocumentaryRepositoryPortletKeys.DATO_NO_INFORMADO);
			areaPuestoDTOs.add(areaPuestoDTO);
		}
		return areaPuestoDTOs;
	}
	
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
	
	

}

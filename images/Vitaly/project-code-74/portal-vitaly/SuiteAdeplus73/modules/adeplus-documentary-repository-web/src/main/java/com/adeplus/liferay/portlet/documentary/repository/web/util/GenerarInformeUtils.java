package com.adeplus.liferay.portlet.documentary.repository.web.util;

import com.adeplus.liferay.portlet.documentary.repository.web.constants.AdeplusDocumentaryRepositoryPortletKeys;
import com.adeplus.liferay.portlet.documentary.repository.web.portlet.dto.MedidaDTO;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.Validator;
import com.plan.igualdad.liferay.portlet.manager.model.Estadistica;
import com.plan.igualdad.liferay.portlet.manager.service.EstadisticaLocalServiceUtil;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

public class GenerarInformeUtils {

	public static Map<String, Object> getImagenes17(Long compId, Long versionId) throws PortalException, IOException {
		Map<String, Object> parameters = new HashMap<>();
		Estadistica estadistica = EstadisticaLocalServiceUtil.fetchEstadisticasBySection(compId, versionId, 17L);
		if(Validator.isNotNull(estadistica)) {
			JSONObject jsonEstadistica = JSONFactoryUtil.createJSONObject(estadistica.getEstadistica());
			if(Validator.isNotNull(jsonEstadistica)) {
				parameters.put(AdeplusDocumentaryRepositoryPortletKeys.CONCEPTOS, getImagen(jsonEstadistica.getString(AdeplusDocumentaryRepositoryPortletKeys.CONCEPTOS)));
				parameters.put(AdeplusDocumentaryRepositoryPortletKeys.PROMEDIO_GP, getImagen(jsonEstadistica.getString(AdeplusDocumentaryRepositoryPortletKeys.PROMEDIO_GP)));
				parameters.put(AdeplusDocumentaryRepositoryPortletKeys.MEDIANA_GP, getImagen(jsonEstadistica.getString(AdeplusDocumentaryRepositoryPortletKeys.MEDIANA_GP)));
				parameters.put(AdeplusDocumentaryRepositoryPortletKeys.PROMEDIO_EQ, getImagen(jsonEstadistica.getString(AdeplusDocumentaryRepositoryPortletKeys.PROMEDIO_EQ)));
				parameters.put(AdeplusDocumentaryRepositoryPortletKeys.MEDIANA_EQ, getImagen(jsonEstadistica.getString(AdeplusDocumentaryRepositoryPortletKeys.MEDIANA_EQ)));
				parameters.put(AdeplusDocumentaryRepositoryPortletKeys.PROMEDIO_PT, getImagen(jsonEstadistica.getString(AdeplusDocumentaryRepositoryPortletKeys.PROMEDIO_PT)));
				parameters.put(AdeplusDocumentaryRepositoryPortletKeys.MEDIANA_PT, getImagen(jsonEstadistica.getString(AdeplusDocumentaryRepositoryPortletKeys.MEDIANA_PT)));
				parameters.put(AdeplusDocumentaryRepositoryPortletKeys.PROMEDIO_VT, getImagen(jsonEstadistica.getString(AdeplusDocumentaryRepositoryPortletKeys.PROMEDIO_VT)));
				parameters.put(AdeplusDocumentaryRepositoryPortletKeys.MEDIANA_VT, getImagen(jsonEstadistica.getString(AdeplusDocumentaryRepositoryPortletKeys.MEDIANA_VT)));	
			}
		}
		return parameters;
	}
	
	public static BufferedImage getImagen(String base64) throws PortalException, IOException {
		
		BufferedImage bufferedImage = null;
		
		if(Validator.isNotNull(base64) && !base64.isEmpty()) {
			String base64Image = base64.split(",")[1];
			byte[] imageBytes = javax.xml.bind.DatatypeConverter.parseBase64Binary(base64Image);
			bufferedImage = ImageIO.read(new ByteArrayInputStream(imageBytes));
		}
		
		return bufferedImage;
	}
	
	public static List<MedidaDTO> getMedidas(JSONArray medidas){
		List<MedidaDTO> listMedidas = new ArrayList<>();
		if(Validator.isNotNull(medidas)) {
			for(int i=0; i<medidas.length();i++) {
				JSONObject medida = medidas.getJSONObject(i);
		
				MedidaDTO medidaDTO = new MedidaDTO();
				medidaDTO.setDescripcion(medida.getString("descripcion"));
				medidaDTO.setIndicadores(medida.getString("indicadores"));
				medidaDTO.setMateria(medida.getString("materia"));
				medidaDTO.setNombreMedida(medida.getString("medidaNombre"));
				medidaDTO.setObjetivos(medida.getString("objetivos"));
				medidaDTO.setPrioridad(medida.getString("prioridad"));
				medidaDTO.setRecursos(medida.getString("recursos"));
				medidaDTO.setRecurrente(medida.getString("recurrente"));
				medidaDTO.setResponsable(medida.getString("responsable"));
				medidaDTO.setFechaImplantacion(medida.getString("fechaImplantacion"));
				medidaDTO.setFechaSeguimiento(medida.getString("fechaSeguimiento"));
				medidaDTO.setFechaPrevista(medida.getString("fechaPrevista"));
				medidaDTO.setNivelEjecucion(medida.getString("nivelEjecucion"));
				medidaDTO.setMotivo(medida.getString("motivo"));
				medidaDTO.setOtrosMotivos(medida.getString("otrosMotivos"));
				medidaDTO.setRecursosAsociados(medida.getString("recursosAsociados"));
				medidaDTO.setDificultadesEncontradas(medida.getString("dificultadesEncontradas"));
				medidaDTO.setSolucionesAdoptadas(medida.getString("solucionesAdoptadas"));
				medidaDTO.setReduccionDesigualdades(medida.getString("reduccionDesigualdades"));
				medidaDTO.setMejorasProducidas(medida.getString("mejorasProducidas"));
				medidaDTO.setPropuestasFuturo(medida.getString("propuestasFuturo"));
				medidaDTO.setDocumentacionAcreditativa(medida.getString("documentacionAcreditativa"));
				medidaDTO.setFechaSubidaDocumento(medida.getString("fechaSubidaDocumento"));
				medidaDTO.setNoMedida(medida.getBoolean("noMedida"));
				
				listMedidas.add(medidaDTO);
			}
		}
		
		return listMedidas;
	}
}

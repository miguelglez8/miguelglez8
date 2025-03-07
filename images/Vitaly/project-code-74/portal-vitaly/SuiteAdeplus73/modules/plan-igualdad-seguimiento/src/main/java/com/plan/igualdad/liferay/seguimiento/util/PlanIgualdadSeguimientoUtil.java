package com.plan.igualdad.liferay.seguimiento.util;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.dynamic.data.lists.model.DDLRecord;
import com.liferay.dynamic.data.lists.model.DDLRecordSet;
import com.liferay.dynamic.data.lists.service.DDLRecordLocalServiceUtil;
import com.liferay.dynamic.data.lists.service.DDLRecordSetLocalServiceUtil;
import com.liferay.dynamic.data.mapping.storage.DDMFormFieldValue;
import com.liferay.dynamic.data.mapping.storage.DDMFormValues;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.plan.igualdad.liferay.portlet.manager.model.Medida;
import com.plan.igualdad.liferay.portlet.manager.model.Version;
import com.plan.igualdad.liferay.portlet.manager.service.MedidaLocalServiceUtil;
import com.plan.igualdad.liferay.portlet.manager.service.VersionLocalServiceUtil;
import com.plan.igualdad.liferay.portlet.manager.service.persistence.VersionPK;
import com.plan.igualdad.liferay.seguimiento.constants.PlanIgualdadSeguimientoPortletKeys;
import com.plan.igualdad.liferay.seguimiento.dto.CumplimentacionDTO;
import com.plan.igualdad.liferay.seguimiento.dto.DatosGeneralesDTO;
import com.plan.igualdad.liferay.seguimiento.dto.PlanIgualdadMedidaDTO;
import com.plan.igualdad.liferay.seguimiento.enums.CategoriaEnum;
import com.plan.igualdad.liferay.seguimiento.enums.NivelEjecucionEnum;
import com.plan.igualdad.liferay.seguimiento.enums.PrioridadEnum;
import com.plan.igualdad.liferay.seguimiento.portlet.PlanIgualdadSeguimientoPortlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.portlet.ActionRequest;
import javax.portlet.RenderRequest;

public class PlanIgualdadSeguimientoUtil {
	
	private static Log _log = LogFactoryUtil.getLog(PlanIgualdadSeguimientoUtil.class);
	
	/**
	 * 
	 * @param medidas
	 * @return
	 */
	public static Set<Integer> getVersionsMedidas(List<Medida> medidas){
		List<Integer> versionsMedidas = new ArrayList<>();
		
		if(Validator.isNotNull(medidas)) {
			for(Medida medida : medidas) {
				versionsMedidas.add(medida.getVersionMedida());
			}
		}
	
		Set<Integer> uniqueVersions = new HashSet<Integer>(versionsMedidas);
		
		return uniqueVersions;
	}
	
	/**
	 * 
	 * @param medidasList
	 * @param medida
	 * @return
	 */
	private static Boolean isLastVersion(List<Medida> medidasList, Medida medida) {
		Boolean isLastVersion = Boolean.FALSE;
		
		if(Validator.isNotNull(medidasList)) {
			List<Integer> intValues = new ArrayList<>();
			for(Medida medida_: medidasList) {
				if(medida_.getVersionOriginalMedidaId() == medida.getVersionOriginalMedidaId()) {
					intValues.add(medida_.getVersionMedida());
				}
			}
			
			Integer max = Collections.max(intValues);
			if(max == medida.getVersionMedida()) {
				isLastVersion = Boolean.TRUE;
			}
		}
		
		return isLastVersion;
	}
	
	/**
	 * 
	 * @param actionRequest
	 * @param medidaDuplicate 
	 * @param duplicate 
	 * @param clienteRole 
	 * @return
	 * @throws JSONException 
	 */
	public static JSONObject getJSONDatosGenerales(ActionRequest actionRequest, Boolean duplicate, Medida medidaDuplicate, boolean clienteRole) throws JSONException {
		JSONObject jsonDatosGenerales = JSONFactoryUtil.createJSONObject();
		
		if(duplicate || clienteRole) {
			jsonDatosGenerales = JSONFactoryUtil.createJSONObject(medidaDuplicate.getDatosGenerales()) ;
		}else {
			jsonDatosGenerales.put(PlanIgualdadSeguimientoPortletKeys.MATTER,  ParamUtil.getInteger(actionRequest, PlanIgualdadSeguimientoPortletKeys.MATTER));
			jsonDatosGenerales.put(PlanIgualdadSeguimientoPortletKeys.MEASURE_NAME,  ParamUtil.getString(actionRequest, PlanIgualdadSeguimientoPortletKeys.MEASURE_NAME));
			jsonDatosGenerales.put(PlanIgualdadSeguimientoPortletKeys.MEASURE_DESCRIPTION,  ParamUtil.getString(actionRequest, PlanIgualdadSeguimientoPortletKeys.MEASURE_DESCRIPTION));
			jsonDatosGenerales.put(PlanIgualdadSeguimientoPortletKeys.OBJECTIVES_PURSUED,  ParamUtil.getString(actionRequest, PlanIgualdadSeguimientoPortletKeys.OBJECTIVES_PURSUED));
			jsonDatosGenerales.put(PlanIgualdadSeguimientoPortletKeys.PRIORITY,  ParamUtil.getInteger(actionRequest, PlanIgualdadSeguimientoPortletKeys.PRIORITY));
			jsonDatosGenerales.put(PlanIgualdadSeguimientoPortletKeys.APPLY,  ParamUtil.getString(actionRequest, PlanIgualdadSeguimientoPortletKeys.APPLY));
			jsonDatosGenerales.put(PlanIgualdadSeguimientoPortletKeys.ASSOCIATED_RESOURCES,  ParamUtil.getString(actionRequest, PlanIgualdadSeguimientoPortletKeys.ASSOCIATED_RESOURCES));
			jsonDatosGenerales.put(PlanIgualdadSeguimientoPortletKeys.MONITORING_INDICATORS,  ParamUtil.getString(actionRequest, PlanIgualdadSeguimientoPortletKeys.MONITORING_INDICATORS));

		}
		
		return jsonDatosGenerales;
	}
	
	/**
	 * 
	 * @param actionRequest
	 * @param medidaDuplicate 
	 * @param duplicate 
	 * @return
	 * @throws JSONException 
	 */
	public static JSONObject getJSONCumplimentacion(ActionRequest actionRequest, FileEntry fileEntry, Long documentoId, Boolean newVersion, Boolean duplicate, Medida medidaDuplicate) throws JSONException {
		JSONObject jsonCumplimentacion = JSONFactoryUtil.createJSONObject();
		
		if(duplicate) {
			jsonCumplimentacion = JSONFactoryUtil.createJSONObject(medidaDuplicate.getCumplimentacion()) ;
		}else {
			jsonCumplimentacion.put(PlanIgualdadSeguimientoPortletKeys.RESPONSIBLE,  ParamUtil.getString(actionRequest, PlanIgualdadSeguimientoPortletKeys.RESPONSIBLE));
			
			if(newVersion) {
				jsonCumplimentacion.put(PlanIgualdadSeguimientoPortletKeys.PLANNED_DATE,  ParamUtil.getString(actionRequest, PlanIgualdadSeguimientoPortletKeys.TRACING_DATE));
				jsonCumplimentacion.put(PlanIgualdadSeguimientoPortletKeys.IMPLANTATION_DATE,  "");
				jsonCumplimentacion.put(PlanIgualdadSeguimientoPortletKeys.TRACING_DATE,  "");
				jsonCumplimentacion.put(PlanIgualdadSeguimientoPortletKeys.EXECUTION,  2);

			}else {
				jsonCumplimentacion.put(PlanIgualdadSeguimientoPortletKeys.PLANNED_DATE,  ParamUtil.getString(actionRequest, PlanIgualdadSeguimientoPortletKeys.PLANNED_DATE));
				jsonCumplimentacion.put(PlanIgualdadSeguimientoPortletKeys.IMPLANTATION_DATE,  ParamUtil.getString(actionRequest, PlanIgualdadSeguimientoPortletKeys.IMPLANTATION_DATE));
				jsonCumplimentacion.put(PlanIgualdadSeguimientoPortletKeys.TRACING_DATE,  ParamUtil.getString(actionRequest, PlanIgualdadSeguimientoPortletKeys.TRACING_DATE));
				jsonCumplimentacion.put(PlanIgualdadSeguimientoPortletKeys.EXECUTION,  ParamUtil.getInteger(actionRequest, PlanIgualdadSeguimientoPortletKeys.EXECUTION));
			}
			jsonCumplimentacion.put(PlanIgualdadSeguimientoPortletKeys.REASON,  ParamUtil.getInteger(actionRequest, PlanIgualdadSeguimientoPortletKeys.REASON));
			jsonCumplimentacion.put(PlanIgualdadSeguimientoPortletKeys.REASON_OTHER,  ParamUtil.getString(actionRequest, PlanIgualdadSeguimientoPortletKeys.REASON_OTHER));
			jsonCumplimentacion.put(PlanIgualdadSeguimientoPortletKeys.RESOURCES_ASSIGNS,  ParamUtil.getString(actionRequest, PlanIgualdadSeguimientoPortletKeys.RESOURCES_ASSIGNS));
			jsonCumplimentacion.put(PlanIgualdadSeguimientoPortletKeys.DIFFICULTIES_IMPLANTATION,  ParamUtil.getString(actionRequest, PlanIgualdadSeguimientoPortletKeys.DIFFICULTIES_IMPLANTATION));
			jsonCumplimentacion.put(PlanIgualdadSeguimientoPortletKeys.SOLUTIONS_ADOPTED,  ParamUtil.getString(actionRequest, PlanIgualdadSeguimientoPortletKeys.SOLUTIONS_ADOPTED));
			jsonCumplimentacion.put(PlanIgualdadSeguimientoPortletKeys.REDUCTION_INEQUALITIES,  ParamUtil.getString(actionRequest, PlanIgualdadSeguimientoPortletKeys.REDUCTION_INEQUALITIES));
			jsonCumplimentacion.put(PlanIgualdadSeguimientoPortletKeys.PRODUCED_IMPROVEMENTS,  ParamUtil.getString(actionRequest, PlanIgualdadSeguimientoPortletKeys.PRODUCED_IMPROVEMENTS));
			jsonCumplimentacion.put(PlanIgualdadSeguimientoPortletKeys.PROPOSAL_FUTURE,  ParamUtil.getString(actionRequest, PlanIgualdadSeguimientoPortletKeys.PROPOSAL_FUTURE));
			jsonCumplimentacion.put(PlanIgualdadSeguimientoPortletKeys.DOCUMENTATION_TEXT,  ParamUtil.getString(actionRequest, PlanIgualdadSeguimientoPortletKeys.DOCUMENTATION_TEXT));
			if(Validator.isNull(ParamUtil.getString(actionRequest, PlanIgualdadSeguimientoPortletKeys.FILE)) || ParamUtil.getString(actionRequest, PlanIgualdadSeguimientoPortletKeys.FILE).isEmpty()) {
				jsonCumplimentacion.put(PlanIgualdadSeguimientoPortletKeys.UPLOAD_FILE, fileEntry!=null ? fileEntry.getFileEntryId() : 0L);
			}else {
				jsonCumplimentacion.put(PlanIgualdadSeguimientoPortletKeys.UPLOAD_FILE, documentoId!=null && documentoId!=0L ? documentoId : 0L);
			}
		}
		
		return jsonCumplimentacion;
	}
	
	/**
	 * 
	 * @param themeDisplay
	 * @return
	 */
	public static Folder getFolder(ThemeDisplay themeDisplay, String parentFolderConfiguration, String nameFolder){
		Folder folder = null;
		try {
			Folder parentFolder =DLAppServiceUtil.getFolder(themeDisplay.getScopeGroupId(), DLFolderConstants.DEFAULT_PARENT_FOLDER_ID,  parentFolderConfiguration);
			
			folder = DLAppServiceUtil.getFolder(themeDisplay.getScopeGroupId(), parentFolder.getFolderId(), nameFolder);
			
		} catch (Exception e) {	
			_log.error("ERROR: " , e);
		}
		return folder;
	}
	
	/**
	 * Crear subcarpeta en la carpeta guardada en la configuracion del portlet
	 * @param themeDisplay
	 * @return
	 */
	public static Folder createFolder(ThemeDisplay themeDisplay, ActionRequest actionRequest, String parentFolderConfiguration, String compId){
		Folder folder = null;

		try {
			UploadPortletRequest uploadRequest = PortalUtil.getUploadPortletRequest(actionRequest);

			ServiceContext serviceContext = ServiceContextFactory.getInstance(PlanIgualdadSeguimientoPortlet.class.getName(), uploadRequest);

			Folder parentFolder = DLAppServiceUtil.getFolder(themeDisplay.getScopeGroupId(), DLFolderConstants.DEFAULT_PARENT_FOLDER_ID,  parentFolderConfiguration);
			
			folder =DLAppServiceUtil.addFolder(themeDisplay.getScopeGroupId(), parentFolder.getFolderId(), compId , StringPool.BLANK, serviceContext);
			
		} catch (Exception e) {	
			_log.error("ERROR: " , e);
		}
		return folder;
	}
	
	/**
	 * 
	 * @param medidasList
	 * @param renderRequest 
	 * @param themeDisplay 
	 * @return
	 */
	public static List<PlanIgualdadMedidaDTO> getMedidas (List<Medida> medidasList, RenderRequest renderRequest, ThemeDisplay themeDisplay){
		List<PlanIgualdadMedidaDTO> medidasDTOList = new ArrayList<>();
		
		if(Validator.isNotNull(medidasList) && !medidasList.isEmpty()) {
			for(Medida medida : medidasList) {
				PlanIgualdadMedidaDTO medidaDTO = new PlanIgualdadMedidaDTO();
				
				medidaDTO.setMedidaId(medida.getMedidaId());
				medidaDTO.setUserId(medida.getUserId());
				
				VersionPK versionPK = new VersionPK();
                versionPK.setVersionId(medida.getVersionId());
                versionPK.setCompId(medida.getCompId());

                Version version = VersionLocalServiceUtil.fetchVersion(versionPK);
                
				medidaDTO.setVersionId(version.getVersionId());
				medidaDTO.setVersionName(version.getNombre());
				medidaDTO.setIsMedidaPredefinida(medida.getIsMedidaPredefinida());
				medidaDTO.setVersionMedida(medida.getVersionMedida());
				medidaDTO.setVersionOriginalMedidaId(medida.getVersionOriginalMedidaId());
				medidaDTO.setLastVersion(isLastVersion(medidasList, medida));
				try {
					if(Validator.isNotNull(medida.getDatosGenerales()) && !medida.getDatosGenerales().isEmpty()) {
						JSONObject jsonGeneralData = JSONFactoryUtil.createJSONObject(medida.getDatosGenerales());
						DatosGeneralesDTO datosGeneralesDto = new DatosGeneralesDTO();
						
						datosGeneralesDto.setMateriaId(jsonGeneralData.getInt(PlanIgualdadSeguimientoPortletKeys.MATTER));
						datosGeneralesDto.setMateriaNombre(CategoriaEnum.getDescripcionByCodigo(jsonGeneralData.getInt(PlanIgualdadSeguimientoPortletKeys.MATTER)));
						datosGeneralesDto.setNombreMedida(jsonGeneralData.getString(PlanIgualdadSeguimientoPortletKeys.MEASURE_NAME));
						datosGeneralesDto.setPrioridadId(jsonGeneralData.getInt(PlanIgualdadSeguimientoPortletKeys.PRIORITY));
						datosGeneralesDto.setPrioridadNombre(PrioridadEnum.getDescripcionByCodigo(jsonGeneralData.getInt(PlanIgualdadSeguimientoPortletKeys.PRIORITY)));
						datosGeneralesDto.setAplica(jsonGeneralData.getString(PlanIgualdadSeguimientoPortletKeys.APPLY));
						datosGeneralesDto.setDescripcionMedida(jsonGeneralData.getString(PlanIgualdadSeguimientoPortletKeys.MEASURE_DESCRIPTION));
						medidaDTO.setDatosGenerales(datosGeneralesDto);
					}
					
					if(Validator.isNotNull(medida.getCumplimentacion()) && !medida.getCumplimentacion().isEmpty()) {
						JSONObject jsonCumplimentacion = JSONFactoryUtil.createJSONObject(medida.getCumplimentacion());
						CumplimentacionDTO cumplimentacionDTO = new CumplimentacionDTO();
						
						cumplimentacionDTO.setNivelEjecucionId(jsonCumplimentacion.getInt(PlanIgualdadSeguimientoPortletKeys.EXECUTION));
						cumplimentacionDTO.setNivelEjecucionNombre(NivelEjecucionEnum.getDescripcionFormByCodigo(jsonCumplimentacion.getInt(PlanIgualdadSeguimientoPortletKeys.EXECUTION)));
						cumplimentacionDTO.setFechaPrevista(jsonCumplimentacion.getString(PlanIgualdadSeguimientoPortletKeys.PLANNED_DATE));
						cumplimentacionDTO.setFechaImplantacion(jsonCumplimentacion.getString(PlanIgualdadSeguimientoPortletKeys.IMPLANTATION_DATE));
						cumplimentacionDTO.setFechaSeguimiento(jsonCumplimentacion.getString(PlanIgualdadSeguimientoPortletKeys.TRACING_DATE));
						cumplimentacionDTO.setResponsable(jsonCumplimentacion.getString(PlanIgualdadSeguimientoPortletKeys.RESPONSIBLE));
						cumplimentacionDTO.setDocumentoId(jsonCumplimentacion.getLong(PlanIgualdadSeguimientoPortletKeys.UPLOAD_FILE));
						
						medidaDTO.setCumplimentacion(cumplimentacionDTO);
					}else {
						CumplimentacionDTO cumplimentacionDTO = new CumplimentacionDTO();
						cumplimentacionDTO.setNivelEjecucionId(1);
						cumplimentacionDTO.setNivelEjecucionNombre(NivelEjecucionEnum.getDescripcionFormByCodigo(1));
						
						medidaDTO.setCumplimentacion(cumplimentacionDTO);
					}
				} catch (JSONException e) {
					_log.debug("MEDIDA NO TIENE DATOS GENERALES O CUMPLIMENTACION");
				}

				medidasDTOList.add(medidaDTO);
			}
		}
		
		return medidasDTOList;
	}
	
	/**
	 * 
	 * @param medidasList 
	 * @param medidasList
	 * @param medidasRecordList
	 * @param renderRequest
	 * @param themeDisplay
	 * @return
	 * @throws PortalException 
	 */
	public static List<PlanIgualdadMedidaDTO> getMedidasSelect(List<Medida> medidasList, List<DDLRecord> medidasRecordList, RenderRequest renderRequest, ThemeDisplay themeDisplay) throws PortalException {
		List<PlanIgualdadMedidaDTO> medidasDTOList = new ArrayList<>();
		if(Validator.isNotNull(medidasRecordList) && medidasRecordList.size()>0) {
			for(DDLRecord medidaRecord : medidasRecordList) {
				DDMFormValues medidaValuesRecord = medidaRecord.getDDMFormValues();
				List<DDMFormFieldValue> medidaValuesList = medidaValuesRecord.getDDMFormFieldValues();
				if(Validator.isNotNull(medidaValuesList)) {
					Boolean existMedidaPredefinida = Boolean.FALSE;
					PlanIgualdadMedidaDTO dto = new PlanIgualdadMedidaDTO();
					dto.setIsMedidaPredefinida(Boolean.TRUE);

					DatosGeneralesDTO datosGeneralesDto = new DatosGeneralesDTO();
					for(DDMFormFieldValue formFieldMedida : medidaValuesList) {
						String valueInputMedida = formFieldMedida.getValue().getString(themeDisplay.getLocale());
						String nameInputMedida = formFieldMedida.getName();
						
						if(PlanIgualdadSeguimientoPortletKeys.ID.equals(nameInputMedida)) {
							dto.setMedidaId(0L);
							dto.setMedidaPredefinidaId(valueInputMedida);
							if(Validator.isNotNull(medidasList) && !medidasList.isEmpty()) {
								for(Medida medida : medidasList) {
									if(medida.getIsMedidaPredefinida() && medida.getMedidaPredefinidaId().equals(valueInputMedida) ) {
										existMedidaPredefinida = Boolean.TRUE;
									}
								}
							}
						}else if(PlanIgualdadSeguimientoPortletKeys.DESHABILITADA.equals(nameInputMedida)) {
							datosGeneralesDto.setDeshabilitada(valueInputMedida);
						}else if(PlanIgualdadSeguimientoPortletKeys.MATERIA.equals(nameInputMedida)) {
							datosGeneralesDto.setMateriaId(Integer.parseInt(valueInputMedida));
							datosGeneralesDto.setMateriaNombre(CategoriaEnum.getDescripcionByCodigo(Integer.parseInt(valueInputMedida)));
						}else if(PlanIgualdadSeguimientoPortletKeys.MEDIDA_PARAM.equals(nameInputMedida)) {
							datosGeneralesDto.setNombreMedida(valueInputMedida);
						}else if(PlanIgualdadSeguimientoPortletKeys.OBJETIVOS.equals(nameInputMedida)) {
							datosGeneralesDto.setObjetivos(valueInputMedida);
						}else if(PlanIgualdadSeguimientoPortletKeys.DESCRIPCION.equals(nameInputMedida)) {
							datosGeneralesDto.setDescripcionMedida(valueInputMedida);
						}else if(PlanIgualdadSeguimientoPortletKeys.PRIORIDAD.equals(nameInputMedida)) {
							datosGeneralesDto.setPrioridadId(Integer.parseInt(valueInputMedida));
							datosGeneralesDto.setPrioridadNombre(PrioridadEnum.getDescripcionByCodigo(Integer.parseInt(valueInputMedida)));
						}else if(PlanIgualdadSeguimientoPortletKeys.RECURRENTE.equals(nameInputMedida)) {
							datosGeneralesDto.setAplica(valueInputMedida);
						}else if(PlanIgualdadSeguimientoPortletKeys.RECURSOSASOCIADOS.equals(nameInputMedida)) {
							datosGeneralesDto.setRecursosAsociados(valueInputMedida);
						}else if(PlanIgualdadSeguimientoPortletKeys.INDICADORES.equals(nameInputMedida)) {
							datosGeneralesDto.setIndicadoresSeguimiento(valueInputMedida);
						}
					}
					dto.setDatosGenerales(datosGeneralesDto);
					if(!existMedidaPredefinida && (Validator.isNull(datosGeneralesDto.getDeshabilitada()) || (Validator.isNotNull(datosGeneralesDto.getDeshabilitada()) && !datosGeneralesDto.getDeshabilitada().equals("true")))) {
						medidasDTOList.add(dto);
					}
				}
			}
		}		
		return medidasDTOList;
	}

	/**
	 * 
	 * @param medida
	 * @return
	 */
	public static PlanIgualdadMedidaDTO getMedidaDTO(Medida medida, long recordSetMedida, ThemeDisplay themeDisplay) {
		PlanIgualdadMedidaDTO medidaDTO = new PlanIgualdadMedidaDTO();
		
		try {
			medidaDTO.setMedidaId(medida.getMedidaId());
							
			if(medida.getIsMedidaPredefinida()) {
				medidaDTO.setPeriocidad(periocidad(medida.getMedidaPredefinidaId(), recordSetMedida, themeDisplay));
			}
			
			if(Validator.isNotNull(medida.getDatosGenerales()) && !medida.getDatosGenerales().isEmpty()) {
				JSONObject jsonGeneralData = JSONFactoryUtil.createJSONObject(medida.getDatosGenerales());
				
				DatosGeneralesDTO datosGeneralesDTO = new DatosGeneralesDTO();
				datosGeneralesDTO.setMateriaId(jsonGeneralData.getInt(PlanIgualdadSeguimientoPortletKeys.MATTER));
				datosGeneralesDTO.setNombreMedida(jsonGeneralData.getString(PlanIgualdadSeguimientoPortletKeys.MEASURE_NAME));
				datosGeneralesDTO.setDescripcionMedida(jsonGeneralData.getString(PlanIgualdadSeguimientoPortletKeys.MEASURE_DESCRIPTION));
				datosGeneralesDTO.setObjetivos(jsonGeneralData.getString(PlanIgualdadSeguimientoPortletKeys.OBJECTIVES_PURSUED));
				datosGeneralesDTO.setPrioridadId(jsonGeneralData.getInt(PlanIgualdadSeguimientoPortletKeys.PRIORITY));
				datosGeneralesDTO.setAplica(jsonGeneralData.getString(PlanIgualdadSeguimientoPortletKeys.APPLY));
				datosGeneralesDTO.setRecursosAsociados(jsonGeneralData.getString(PlanIgualdadSeguimientoPortletKeys.ASSOCIATED_RESOURCES));
				datosGeneralesDTO.setIndicadoresSeguimiento(jsonGeneralData.getString(PlanIgualdadSeguimientoPortletKeys.MONITORING_INDICATORS));

				medidaDTO.setDatosGenerales(datosGeneralesDTO);
			}
			
			if(Validator.isNotNull(medida.getCumplimentacion()) && !medida.getCumplimentacion().isEmpty()) {
				JSONObject jsonCumplimentacion = JSONFactoryUtil.createJSONObject(medida.getCumplimentacion());
				
				CumplimentacionDTO cumplementacionDTO = new CumplimentacionDTO();
				cumplementacionDTO.setResponsable(jsonCumplimentacion.getString(PlanIgualdadSeguimientoPortletKeys.RESPONSIBLE));
				cumplementacionDTO.setFechaPrevista(jsonCumplimentacion.getString(PlanIgualdadSeguimientoPortletKeys.PLANNED_DATE));
				cumplementacionDTO.setFechaImplantacion(jsonCumplimentacion.getString(PlanIgualdadSeguimientoPortletKeys.IMPLANTATION_DATE));
				cumplementacionDTO.setFechaSeguimiento(jsonCumplimentacion.getString(PlanIgualdadSeguimientoPortletKeys.TRACING_DATE));
				cumplementacionDTO.setNivelEjecucionId(jsonCumplimentacion.getInt(PlanIgualdadSeguimientoPortletKeys.EXECUTION));
				cumplementacionDTO.setMotivoNoIniciado(jsonCumplimentacion.getString(PlanIgualdadSeguimientoPortletKeys.REASON));
				cumplementacionDTO.setMotivoNoIniciadoText(jsonCumplimentacion.getString(PlanIgualdadSeguimientoPortletKeys.REASON_OTHER));
				cumplementacionDTO.setAdecuacionRecursos(jsonCumplimentacion.getString(PlanIgualdadSeguimientoPortletKeys.RESOURCES_ASSIGNS));
				cumplementacionDTO.setDificultadesImplantacion(jsonCumplimentacion.getString(PlanIgualdadSeguimientoPortletKeys.DIFFICULTIES_IMPLANTATION));
				cumplementacionDTO.setSolucionesAdoptadas(jsonCumplimentacion.getString(PlanIgualdadSeguimientoPortletKeys.SOLUTIONS_ADOPTED));
				cumplementacionDTO.setReduccionDesigualdades(jsonCumplimentacion.getString(PlanIgualdadSeguimientoPortletKeys.REDUCTION_INEQUALITIES));
				cumplementacionDTO.setMejorasProductivas(jsonCumplimentacion.getString(PlanIgualdadSeguimientoPortletKeys.PRODUCED_IMPROVEMENTS));
				cumplementacionDTO.setPropuestaDeFuturo(jsonCumplimentacion.getString(PlanIgualdadSeguimientoPortletKeys.PROPOSAL_FUTURE));
				cumplementacionDTO.setDocumentacionEjecucion(jsonCumplimentacion.getString(PlanIgualdadSeguimientoPortletKeys.DOCUMENTATION_TEXT));
				cumplementacionDTO.setDocumentoId(jsonCumplimentacion.getLong(PlanIgualdadSeguimientoPortletKeys.UPLOAD_FILE));
			
				medidaDTO.setCumplimentacion(cumplementacionDTO);
			}
		} catch (NumberFormatException | PortalException e) {
			_log.error("ERROR: ", e );
		}
		return medidaDTO;
	}
	
	/**
	 * 
	 * @param medidaPredefinida
	 * @param themeDisplay
	 * @return
	 */
	private static String periocidad(String medidaPredefinida, long RecordSetMedida, ThemeDisplay themeDisplay) {
		String periocidad = StringPool.BLANK;
		try {
			List<DDLRecord> medidasRecordList = DDLRecordLocalServiceUtil.getRecords(RecordSetMedida);
			if(Validator.isNotNull(medidasRecordList) && medidasRecordList.size()>0) {
				for(DDLRecord medidaRecord : medidasRecordList) {
					DDMFormValues medidaValuesRecord = medidaRecord.getDDMFormValues();
					List<DDMFormFieldValue> medidaValuesList = medidaValuesRecord.getDDMFormFieldValues();
					if(Validator.isNotNull(medidaValuesList)) {
						Boolean isMedida = Boolean.FALSE;
						for(DDMFormFieldValue formFieldMedida : medidaValuesList) {
							String valueInputMedida = formFieldMedida.getValue().getString(themeDisplay.getLocale());
							String nameInputMedida = formFieldMedida.getName();
							
							if("ID".equals(nameInputMedida) && valueInputMedida.equals(medidaPredefinida)) {
								isMedida = Boolean.TRUE;
							}else if(isMedida && "PERIODICIDAD".equals(nameInputMedida)) {
								periocidad = valueInputMedida;
							}

						}
					}
				}
			}
		} catch (NumberFormatException | PortalException e) {
			_log.error("Error: ", e);
		}
		return periocidad;
	}
	
	public static Medida createMedidaParametrizada(PlanIgualdadMedidaDTO medidaDTO, ActionRequest actionRequest, ThemeDisplay themeDisplay, Boolean newVersion, Boolean duplicate, String compId) throws JSONException {
		Medida medida = MedidaLocalServiceUtil.createMedida(CounterLocalServiceUtil.increment(Medida.class.getName()));		
		medida.setCreateDate(new Date());
		Version version = VersionLocalServiceUtil.findVersionActiva(Long.parseLong(compId));
		
		medida.setVersionId(version.getVersionId());
		medida.setCompId(Long.parseLong(compId));
		medida.setIsMedidaPredefinida(Boolean.FALSE);
		medida.setUserId(themeDisplay.getUserId());
		medida.setVersionMedida(1);
		medida.setMedidaPredefinidaId(medidaDTO.getMedidaPredefinidaId());
		medida.setVersionOriginalMedidaId(medida.getMedidaId());
		
		JSONObject jsonDatosGenerales = JSONFactoryUtil.createJSONObject();
		jsonDatosGenerales.put(PlanIgualdadSeguimientoPortletKeys.MATTER,  medidaDTO.getDatosGenerales().getMateriaId());
		jsonDatosGenerales.put(PlanIgualdadSeguimientoPortletKeys.MEASURE_NAME,  medidaDTO.getDatosGenerales().getNombreMedida());
		jsonDatosGenerales.put(PlanIgualdadSeguimientoPortletKeys.MEASURE_DESCRIPTION,  medidaDTO.getDatosGenerales().getDescripcionMedida());
		jsonDatosGenerales.put(PlanIgualdadSeguimientoPortletKeys.OBJECTIVES_PURSUED,  medidaDTO.getDatosGenerales().getObjetivos());
		jsonDatosGenerales.put(PlanIgualdadSeguimientoPortletKeys.PRIORITY,  medidaDTO.getDatosGenerales().getPrioridadId());
		jsonDatosGenerales.put(PlanIgualdadSeguimientoPortletKeys.APPLY,  medidaDTO.getDatosGenerales().getAplica());
		jsonDatosGenerales.put(PlanIgualdadSeguimientoPortletKeys.ASSOCIATED_RESOURCES,  medidaDTO.getDatosGenerales().getRecursosAsociados());
		jsonDatosGenerales.put(PlanIgualdadSeguimientoPortletKeys.MONITORING_INDICATORS,  medidaDTO.getDatosGenerales().getIndicadoresSeguimiento());

		medida.setDatosGenerales(StringUtil.replace(jsonDatosGenerales.toString(), StringPool.BACK_SLASH, StringPool.DOUBLE_BACK_SLASH));
		
		return MedidaLocalServiceUtil.updateMedida(medida);
	}
	
	/**
	 * 
	 * @param medida
	 * @param actionRequest
	 * @param themeDisplay
	 * @param clienteRole 
	 * @throws JSONException 
	 */
	public static Medida createMedida(Medida medida, ActionRequest actionRequest, ThemeDisplay themeDisplay, Boolean newVersion, Boolean duplicate, String compId, boolean clienteRole) throws JSONException {
		UploadPortletRequest uploadRequest = PortalUtil.getUploadPortletRequest(actionRequest);
		ServiceContext serviceContext = null;
		Medida medidaDuplicate = medida;
		try {
			serviceContext = ServiceContextFactory.getInstance(PlanIgualdadSeguimientoPortlet.class.getName(), uploadRequest);
		} catch (PortalException e) {
			_log.error("ERROR: " , e);
		}
		
		Long documentoMedida = 0L;
		PlanIgualdadMedidaDTO medidaDTO = null;
		int versionMedida=1;
		Long versionMedidaOriginalId=0L;

		if(Validator.isNull(medida) || duplicate) {
			medida = MedidaLocalServiceUtil.createMedida(CounterLocalServiceUtil.increment(Medida.class.getName()));
			medida.setIsMedidaPredefinida(Boolean.FALSE);
			versionMedidaOriginalId = medida.getMedidaId();
			
			medida.setCreateDate(new Date());
		}else {
			try {
				DDLRecordSet recordSetMedida = DDLRecordSetLocalServiceUtil.getRecordSet(Long.parseLong(actionRequest.getPreferences().getValue(PlanIgualdadSeguimientoPortletKeys.ID_MEDIDAS, "0")));
				medidaDTO = PlanIgualdadSeguimientoUtil.getMedidaDTO(medida, recordSetMedida.getRecordSetId(), themeDisplay);

			} catch (Exception e) {
				_log.error("ERROR: ", e);
			}

			if(Validator.isNotNull(medidaDTO.getCumplimentacion())) {
				documentoMedida = medidaDTO.getCumplimentacion().getDocumentoId();
			}
			versionMedida = newVersion ? medida.getVersionMedida()+1 :medida.getVersionMedida();
			versionMedidaOriginalId = medida.getVersionOriginalMedidaId();
			
			if(newVersion) {
				medida = MedidaLocalServiceUtil.createMedida(CounterLocalServiceUtil.increment(Medida.class.getName()));
				medida.setCreateDate(new Date());
			}
		}
		
		Version version = VersionLocalServiceUtil.findVersionActiva(Long.parseLong(compId));
		
		medida.setVersionId(version.getVersionId());
		medida.setCompId(Long.parseLong(compId));

		medida.setUserId(themeDisplay.getUserId());
		medida.setVersionMedida(versionMedida);
		medida.setVersionOriginalMedidaId(versionMedidaOriginalId);
		
		JSONObject jsonDatosGenerales = PlanIgualdadSeguimientoUtil.getJSONDatosGenerales(actionRequest, duplicate, medidaDuplicate, clienteRole);
		medida.setDatosGenerales(StringUtil.replace(jsonDatosGenerales.toString(), StringPool.BACK_SLASH+"n", StringPool.DOUBLE_BACK_SLASH+"n"));
		
		String sourceFileName = uploadRequest.getFileName(PlanIgualdadSeguimientoPortletKeys.UPLOAD_FILE);

		FileEntry fileEntry = null;
		if(Validator.isNotNull(sourceFileName) && !sourceFileName.isEmpty()) {
			File file = (File) uploadRequest.getFile(PlanIgualdadSeguimientoPortletKeys.UPLOAD_FILE);
			String selectedFile = ParamUtil.getString(actionRequest, PlanIgualdadSeguimientoPortletKeys.SELECTED_FILE, "");
			
			try {
				InputStream is = new FileInputStream( file );
				
				String parentFolderConfiguration = actionRequest.getPreferences().getValue(PlanIgualdadSeguimientoPortletKeys.FOLDER_FILES, StringPool.BLANK);

				Folder folder = PlanIgualdadSeguimientoUtil.getFolder(themeDisplay, parentFolderConfiguration, compId);			
				
				if(Validator.isNull(folder)) {
					folder = PlanIgualdadSeguimientoUtil.createFolder(themeDisplay, actionRequest, parentFolderConfiguration, compId);
				}
				
				try {
					FileEntry entryOld = DLAppServiceUtil.getFileEntry(themeDisplay.getScopeGroupId(), folder.getFolderId(), Long.toString(medida.getMedidaId()));
					
					if(Validator.isNotNull(entryOld)) {
						DLAppServiceUtil.deleteFileEntry(entryOld.getFileEntryId());
					}
				}catch(SystemException | PortalException ex){
					_log.debug(ex.getMessage());
				}
				
				fileEntry = DLAppServiceUtil.addFileEntry(themeDisplay.getScopeGroupId(), folder.getFolderId(), sourceFileName, uploadRequest.getContentType(PlanIgualdadSeguimientoPortletKeys.UPLOAD_FILE), 
						Long.toString(medida.getMedidaId()), StringPool.BLANK, StringPool.BLANK, is, uploadRequest.getSize(PlanIgualdadSeguimientoPortletKeys.UPLOAD_FILE), serviceContext);
				
			} catch (FileNotFoundException | PortalException e) {
				_log.error("ERROR: ", e);
			}
			
			if(!selectedFile.equals("false") && Validator.isNotNull(file)) {
				_log.debug("file: " + file.getAbsolutePath());
			}
		}
		
		JSONObject jsonCumplimentacion = PlanIgualdadSeguimientoUtil.getJSONCumplimentacion(actionRequest, fileEntry, documentoMedida, newVersion, duplicate, medidaDuplicate);
		medida.setCumplimentacion(StringUtil.replace(jsonCumplimentacion.toString(), StringPool.BACK_SLASH+"n", StringPool.DOUBLE_BACK_SLASH+"n"));
		
		return MedidaLocalServiceUtil.updateMedida(medida);
		
	}

}
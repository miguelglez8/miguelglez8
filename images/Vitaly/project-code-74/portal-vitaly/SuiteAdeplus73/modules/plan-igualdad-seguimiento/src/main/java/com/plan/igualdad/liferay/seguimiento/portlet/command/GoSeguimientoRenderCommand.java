package com.plan.igualdad.liferay.seguimiento.portlet.command;

import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.dynamic.data.lists.model.DDLRecord;
import com.liferay.dynamic.data.lists.model.DDLRecordSet;
import com.liferay.dynamic.data.lists.service.DDLRecordLocalServiceUtil;
import com.liferay.dynamic.data.lists.service.DDLRecordSetLocalServiceUtil;
import com.liferay.dynamic.data.mapping.storage.DDMFormFieldValue;
import com.liferay.dynamic.data.mapping.storage.DDMFormValues;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.plan.igualdad.liferay.portlet.manager.model.Medida;
import com.plan.igualdad.liferay.portlet.manager.service.MedidaLocalServiceUtil;
import com.plan.igualdad.liferay.seguimiento.constants.PlanIgualdadSeguimientoPortletKeys;
import com.plan.igualdad.liferay.seguimiento.dto.DatosGeneralesDTO;
import com.plan.igualdad.liferay.seguimiento.dto.PlanIgualdadMedidaDTO;
import com.plan.igualdad.liferay.seguimiento.enums.CategoriaEnum;
import com.plan.igualdad.liferay.seguimiento.enums.PrioridadEnum;
import com.plan.igualdad.liferay.seguimiento.util.PlanIgualdadSeguimientoUtil;

import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

@Component(
        immediate=true,
        property= {
                "javax.portlet.name=" + PlanIgualdadSeguimientoPortletKeys.PLANIGUALDADSEGUIMIENTO,
                "mvc.command.name=goSeguimiento"
        },
        service = MVCRenderCommand.class
)
public class GoSeguimientoRenderCommand implements MVCRenderCommand{

	private static Log _log = LogFactoryUtil.getLog(GoSeguimientoRenderCommand.class);
	
	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		
		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		Long medidaId = ParamUtil.getLong(renderRequest, PlanIgualdadSeguimientoPortletKeys.MEDIDA_ID, -1);
		
		String medidaPredefinidaId = ParamUtil.getString(renderRequest, PlanIgualdadSeguimientoPortletKeys.MEDIDA_PREDEFINIDA_ID, StringPool.BLANK);
		
		String task = ParamUtil.getString(renderRequest, PlanIgualdadSeguimientoPortletKeys.TASK);

		PlanIgualdadMedidaDTO medidaDTO = null;

		if(!medidaPredefinidaId.isEmpty()) {
			DDLRecordSet recordSetMedida = null;
			try {
				recordSetMedida = DDLRecordSetLocalServiceUtil.getRecordSet(Long.parseLong(renderRequest.getPreferences().getValue(PlanIgualdadSeguimientoPortletKeys.ID_MEDIDAS, "0")));
				
				List<DDLRecord> medidasRecordList = DDLRecordLocalServiceUtil.getRecords(recordSetMedida.getRecordSetId());
				
				if(Validator.isNotNull(medidasRecordList) && medidasRecordList.size()>0) {
					for(DDLRecord medidaRecord : medidasRecordList) {
						if(medidaDTO==null) {
							DDMFormValues medidaValuesRecord = medidaRecord.getDDMFormValues();
							
							List<DDMFormFieldValue> medidaValuesList = medidaValuesRecord.getDDMFormFieldValues();
							if(Validator.isNotNull(medidaValuesList)) {
								PlanIgualdadMedidaDTO dto = new PlanIgualdadMedidaDTO();
								dto.setIsMedidaPredefinida(Boolean.TRUE);

								DatosGeneralesDTO datosGeneralesDto = new DatosGeneralesDTO();
								for(DDMFormFieldValue formFieldMedida : medidaValuesList) {
									String valueInputMedida = formFieldMedida.getValue().getString(themeDisplay.getLocale());
									String nameInputMedida = formFieldMedida.getName();
									
									if(Validator.isNotNull(valueInputMedida) && !valueInputMedida.isEmpty()) {
										if(PlanIgualdadSeguimientoPortletKeys.ID.equals(nameInputMedida)) {
											dto.setMedidaPredefinidaId(valueInputMedida);
										}else if(PlanIgualdadSeguimientoPortletKeys.MATERIA.equals(nameInputMedida)) {
											datosGeneralesDto.setMateriaId(Integer.parseInt(valueInputMedida));
											datosGeneralesDto.setMateriaNombre(CategoriaEnum.getDescripcionByCodigo(Integer.parseInt(valueInputMedida)));
										}else if(PlanIgualdadSeguimientoPortletKeys.MEDIDA_PARAM.equals(nameInputMedida)) {
											datosGeneralesDto.setNombreMedida(valueInputMedida);
										}else if(PlanIgualdadSeguimientoPortletKeys.OBJETIVOS.equals(nameInputMedida)) {
											datosGeneralesDto.setObjetivos(valueInputMedida.replaceAll("\n","\\\\n"));
										}else if(PlanIgualdadSeguimientoPortletKeys.DESCRIPCION.equals(nameInputMedida)) {
											datosGeneralesDto.setDescripcionMedida(valueInputMedida.replaceAll("\n","\\\\n"));
										}else if(PlanIgualdadSeguimientoPortletKeys.PRIORIDAD.equals(nameInputMedida)) {
											datosGeneralesDto.setPrioridadId(Integer.parseInt(valueInputMedida));
											datosGeneralesDto.setPrioridadNombre(PrioridadEnum.getDescripcionByCodigo(Integer.parseInt(valueInputMedida)));
										}else if(PlanIgualdadSeguimientoPortletKeys.RECURRENTE.equals(nameInputMedida)) {
											datosGeneralesDto.setAplica(valueInputMedida);
										}else if(PlanIgualdadSeguimientoPortletKeys.RECURSOSASOCIADOS.equals(nameInputMedida)) {
											datosGeneralesDto.setRecursosAsociados(valueInputMedida.replaceAll("\n","\\\\n"));
										}else if(PlanIgualdadSeguimientoPortletKeys.INDICADORES.equals(nameInputMedida)) {
											datosGeneralesDto.setIndicadoresSeguimiento(valueInputMedida.replaceAll("\n","\\\\n"));
											_log.info(">>>11: " + valueInputMedida);
											_log.info(">>>22: " + valueInputMedida.replaceAll("\n","\\\\n"));
										
										}
									}
								}
								dto.setDatosGenerales(datosGeneralesDto);
								
								if(dto.getMedidaPredefinidaId().equals(medidaPredefinidaId)) {
									medidaDTO = dto;
								}
							}
						}else {
							break;
						}
					}
				}
			} catch (NumberFormatException | PortalException e) {
				_log.error("ERROR: ", e);
			}
		}else {
			_log.debug("VER MEDIDA: " + medidaId + " para: " + task);
			Medida medida = MedidaLocalServiceUtil.fetchMedida(medidaId);
			
			if(Validator.isNull(medida)) {
				SessionErrors.add(renderRequest, "measure-view-error");
				_log.error("Error: No existe la medida: " + medidaId);
				
				return "/view.jsp";
			}
			
			DDLRecordSet recordSetMedida = null;
			try {
				recordSetMedida = DDLRecordSetLocalServiceUtil.getRecordSet(Long.parseLong(renderRequest.getPreferences().getValue(PlanIgualdadSeguimientoPortletKeys.ID_MEDIDAS, "0")));
			} catch (Exception e1) {
				_log.error("ERROR: ", e1);
			}
			
			medidaDTO = PlanIgualdadSeguimientoUtil.getMedidaDTO(medida, recordSetMedida.getRecordSetId(), themeDisplay);
		}

		Boolean view = Boolean.FALSE;
		_log.debug("MEDIDA_OBJECT: " + medidaDTO.getDatosGenerales().getNombreMedida());
        renderRequest.setAttribute(PlanIgualdadSeguimientoPortletKeys.MEDIDA_OBJECT, medidaDTO);

        FileEntry dlFileEntry = null;
        if(Validator.isNotNull(medidaDTO.getCumplimentacion()) &&  Validator.isNotNull(medidaDTO.getCumplimentacion().getDocumentoId()) && medidaDTO.getCumplimentacion().getDocumentoId() != 0L) {
        	try {
				dlFileEntry = DLAppServiceUtil.getFileEntry(medidaDTO.getCumplimentacion().getDocumentoId());

        	} catch (Exception e) {
				_log.error("Error al recoger documento: " , e);
			}
        }
        renderRequest.setAttribute(PlanIgualdadSeguimientoPortletKeys.DOCUMENT_MEDIDA, dlFileEntry);
        
        if(Validator.isNotNull(task) && !task.isEmpty() && task.equals("view")) {
        	view = Boolean.TRUE;
        }
        
        renderRequest.setAttribute(PlanIgualdadSeguimientoPortletKeys.VIEW, view);
        
        renderRequest.setAttribute(PlanIgualdadSeguimientoPortletKeys.PAGE_BACK_PARAM, ParamUtil.getString(renderRequest, PlanIgualdadSeguimientoPortletKeys.PAGE_BACK_PARAM));
        
        SessionMessages.add(renderRequest, "measure-view-success");
        
        _log.debug("MEDIDA RECOGIDA CORRECTAMENTE");
        
		return "/measure.jsp";
	}

}

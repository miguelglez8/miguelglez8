package com.plan.igualdad.liferay.seguimiento.portlet.command;

import com.liferay.dynamic.data.lists.model.DDLRecord;
import com.liferay.dynamic.data.lists.model.DDLRecordSet;
import com.liferay.dynamic.data.lists.service.DDLRecordLocalServiceUtil;
import com.liferay.dynamic.data.lists.service.DDLRecordSetLocalServiceUtil;
import com.liferay.dynamic.data.mapping.storage.DDMFormFieldValue;
import com.liferay.dynamic.data.mapping.storage.DDMFormValues;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
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

import java.io.IOException;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

import org.osgi.service.component.annotations.Component;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + PlanIgualdadSeguimientoPortletKeys.PLANIGUALDADSEGUIMIENTO,
                "mvc.command.name=/planigualdad/selectMeasure"
        },
        service = MVCActionCommand.class
)
public class SelectMedidasMVCActionCommand implements MVCActionCommand{

	private static Log _log = LogFactoryUtil.getLog(SelectMedidasMVCActionCommand.class);

	public boolean processAction(ActionRequest actionRequest, ActionResponse actionResponse) throws PortletException {
		_log.debug("init SELECT seguimiento medida");
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		String selectMedidas = ParamUtil.getString(actionRequest, "checkedInputs");
		String compId = ParamUtil.getString(actionRequest, PlanIgualdadSeguimientoPortletKeys.COMPID_PARAM);
		
		if(Validator.isNotNull(selectMedidas) && !selectMedidas.isEmpty()) {
			String[] parts = selectMedidas.split("-");
			for(String part : parts) {
				if(!part.isEmpty()) {
					
					if(part.contains("M")|| part.contains("m")) {
						Boolean isMedida = Boolean.FALSE;
						Boolean saveMedida = Boolean.TRUE;
						DDLRecordSet recordSetMedida = null;
						try {
							recordSetMedida = DDLRecordSetLocalServiceUtil.getRecordSet(Long.parseLong(actionRequest.getPreferences().getValue(PlanIgualdadSeguimientoPortletKeys.ID_MEDIDAS, "0")));
							
							List<DDLRecord> medidasRecordList = DDLRecordLocalServiceUtil.getRecords(recordSetMedida.getRecordSetId());
							if(Validator.isNotNull(medidasRecordList) && medidasRecordList.size()>0) {
								for(DDLRecord medidaRecord : medidasRecordList) {
									DDMFormValues medidaValuesRecord = medidaRecord.getDDMFormValues();
									
									List<DDMFormFieldValue> medidaValuesList = medidaValuesRecord.getDDMFormFieldValues();
									if(Validator.isNotNull(medidaValuesList)) {
										PlanIgualdadMedidaDTO dto = new PlanIgualdadMedidaDTO();

										DatosGeneralesDTO datosGeneralesDto = new DatosGeneralesDTO();
										for(DDMFormFieldValue formFieldMedida : medidaValuesList) {
											String valueInputMedida = formFieldMedida.getValue().getString(themeDisplay.getLocale());
											String nameInputMedida = formFieldMedida.getName();
											
											if(Validator.isNotNull(valueInputMedida) && !valueInputMedida.isEmpty()) {
												if(PlanIgualdadSeguimientoPortletKeys.ID.equals(nameInputMedida)) {
													if(!isMedida && valueInputMedida.toUpperCase().equals(part.toUpperCase())) {
														dto.setMedidaPredefinidaId(valueInputMedida);
														dto.setIsMedidaPredefinida(Boolean.TRUE);
														isMedida=Boolean.TRUE;
													}
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
												}else if(PlanIgualdadSeguimientoPortletKeys.RECURSOSASOCIADOS.equals(nameInputMedida)) {
													datosGeneralesDto.setRecursosAsociados(valueInputMedida);
												}else if(PlanIgualdadSeguimientoPortletKeys.INDICADORES.equals(nameInputMedida)) {
													datosGeneralesDto.setIndicadoresSeguimiento(valueInputMedida);
												}
											}
										}
										datosGeneralesDto.setAplica("Si");
										dto.setDatosGenerales(datosGeneralesDto);
										
										if(isMedida && saveMedida) {
											PlanIgualdadSeguimientoUtil.createMedidaParametrizada(dto, actionRequest, themeDisplay, Boolean.FALSE, Boolean.TRUE, compId);
											saveMedida =Boolean.FALSE;
										}
									}
								}
							}
						} catch (NumberFormatException | PortalException e) {
							_log.error("ERROR: ", e);
						}
					}else {
						Medida medida = MedidaLocalServiceUtil.fetchMedida(Long.parseLong(part));
						if(Validator.isNotNull(medida)) {
							try {
								PlanIgualdadSeguimientoUtil.createMedida(medida, actionRequest, themeDisplay, Boolean.FALSE, Boolean.TRUE, compId, Boolean.FALSE);
							} catch (JSONException e) {
								_log.error("ERROR: ", e);
							}
						}
					}
				}
			}
			
			SessionMessages.add(actionRequest, "measure-add-success");
		}
		
		try {
			actionResponse.sendRedirect(redirectURL(actionRequest, themeDisplay, compId, "/"));
		} catch (IOException e) {
			_log.error("ERROR: ",  e); 
		}
		
		_log.debug("end SELECT seguimiento medida");
		return false;
	}
	
	/**
	 * 
	 * @param actionRequest
	 * @param themeDisplay
	 * @param compId
	 * @param renderUrl
	 * @return
	 */
	private String redirectURL(ActionRequest actionRequest, ThemeDisplay themeDisplay, String compId, String renderUrl) {
        String portletName = (String) actionRequest.getAttribute(WebKeys.PORTLET_ID);
        PortletURL redirectURL = PortletURLFactoryUtil.create(actionRequest, portletName, themeDisplay.getLayout().getPlid(), PortletRequest.RENDER_PHASE);
        redirectURL.setParameter("mvcRenderCommandName", renderUrl);
        redirectURL.setParameter(PlanIgualdadSeguimientoPortletKeys.COMPID_PARAM, compId);
        return redirectURL.toString();
    }
}

package com.plan.igualdad.liferay.portlet.estadisticas.portlet.command;

import com.liferay.dynamic.data.lists.model.DDLRecord;
import com.liferay.dynamic.data.lists.model.DDLRecordSet;
import com.liferay.dynamic.data.lists.service.DDLRecordLocalServiceUtil;
import com.liferay.dynamic.data.lists.service.DDLRecordSetLocalServiceUtil;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.*;
import com.plan.igualdad.liferay.portlet.commons.web.parametrizaciones.PlanIgualdadCuestionarioUtils;
import com.plan.igualdad.liferay.portlet.commons.web.parametrizaciones.PlanIgualdadEstadisticasUtils;
import com.plan.igualdad.liferay.portlet.estadisticas.constants.PlanIgualdadEstadisticasPortletKeys;
import com.plan.igualdad.liferay.portlet.manager.model.Estadistica;
import com.plan.igualdad.liferay.portlet.manager.service.EstadisticaLocalServiceUtil;
import com.plan.igualdad.liferay.portlet.manager.service.EstadoLocalServiceUtil;
import com.plan.igualdad.liferay.portlet.manager.service.persistence.EstadisticaPK;

import java.util.List;

import org.osgi.service.component.annotations.Component;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;

@Component(
        immediate=true,
        property= {
                "javax.portlet.name=" + PlanIgualdadEstadisticasPortletKeys.PLANIGUALDADESTADISTICAS,
                "mvc.command.name=saveEstadistica"
        },
        service = MVCResourceCommand.class
)
public class SaveEstadisticaResourceCommand extends BaseMVCResourceCommand {

	private static Log _log = LogFactoryUtil.getLog(SaveEstadisticaResourceCommand.class);

    @Override
    protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) {

        HttpServletRequest request = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(resourceRequest));
		ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);

        Long compId = ParamUtil.getLong(resourceRequest, PlanIgualdadEstadisticasPortletKeys.COMPID_PARAM);
        Long versionId = ParamUtil.getLong(resourceRequest, PlanIgualdadEstadisticasPortletKeys.VERSIONID_PARAM);
        String seccionId = request.getParameter(PlanIgualdadEstadisticasPortletKeys.SECCIONID_PARAM);
        String estadistica = request.getParameter(PlanIgualdadEstadisticasPortletKeys.ESTADISTICA);
        String changeRadioButton = request.getParameter(PlanIgualdadEstadisticasPortletKeys.CHANGE_RADIO_BUTTON_PARAM);

        try {
            EstadisticaPK estadisticaPK = new EstadisticaPK();
            estadisticaPK.setCompId(compId);
            estadisticaPK.setVersionId(versionId);
            estadisticaPK.setSeccionId(Long.parseLong(seccionId));

            Estadistica estadisticas = EstadisticaLocalServiceUtil.fetchEstadistica(estadisticaPK);
            if (Validator.isNull(estadisticas)) {
                estadisticas = EstadisticaLocalServiceUtil.createEstadistica(estadisticaPK);
            }

            estadisticas.setEstadistica(estadistica);

            EstadisticaLocalServiceUtil.updateEstadistica(estadisticas);
            _log.info("Estadistica creada: " + seccionId+" por el usuarion: "+themeDisplay.getUser().getEmailAddress()+" para la empresa: "+compId);

            String idEstado = com.plan.igualdad.liferay.portlet.commons.web.estado.EstadoUtils.getIdEstado(compId);
            Boolean allowManagementSetting = Boolean.TRUE;
            if(Validator.isNotNull(idEstado) && !idEstado.isEmpty() && !idEstado.equals("0")) {
            	allowManagementSetting = EstadoLocalServiceUtil.allowManagementSettings(Long.parseLong(idEstado));
            }
            
            if ("1".equals(changeRadioButton) && allowManagementSetting) {
                DDLRecordSet recordSetParam = DDLRecordSetLocalServiceUtil.getRecordSet(Long.parseLong(resourceRequest.getPreferences().getValue(PlanIgualdadEstadisticasPortletKeys.ID_PARAMETRIZACIONES, "0")));
                List<DDLRecord> recordsParamList = DDLRecordLocalServiceUtil.getRecords(recordSetParam.getRecordSetId());

                DDLRecordSet recordSetMedida = DDLRecordSetLocalServiceUtil.getRecordSet(Long.parseLong(resourceRequest.getPreferences().getValue(PlanIgualdadEstadisticasPortletKeys.ID_MEDIDAS, "0")));
                List<DDLRecord> medidasRecordList = DDLRecordLocalServiceUtil.getRecords(recordSetMedida.getRecordSetId());

                DDLRecordSet recordSetDebilidad = DDLRecordSetLocalServiceUtil.getRecordSet(Long.parseLong(resourceRequest.getPreferences().getValue(PlanIgualdadEstadisticasPortletKeys.ID_DEBILIDAD, "0")));
                List<DDLRecord> debilidadRecordList = DDLRecordLocalServiceUtil.getRecords(recordSetDebilidad.getRecordSetId());

                DDLRecordSet recordSetFortaleza = DDLRecordSetLocalServiceUtil.getRecordSet(Long.parseLong(resourceRequest.getPreferences().getValue(PlanIgualdadEstadisticasPortletKeys.ID_FORTALEZA, "0")));
                List<DDLRecord> fortalezaRecordList = DDLRecordLocalServiceUtil.getRecords(recordSetFortaleza.getRecordSetId());

                DDLRecordSet recordSetDiagnostico = DDLRecordSetLocalServiceUtil.getRecordSet(Long.parseLong(resourceRequest.getPreferences().getValue(PlanIgualdadEstadisticasPortletKeys.ID_DIAGNOSTICOS, "0")));
                List<DDLRecord> diagnosticoRecordList = DDLRecordLocalServiceUtil.getRecords(recordSetDiagnostico.getRecordSetId());

                PlanIgualdadCuestionarioUtils.createMedida(themeDisplay, compId, versionId, recordsParamList, medidasRecordList, debilidadRecordList, fortalezaRecordList, diagnosticoRecordList);
                PlanIgualdadEstadisticasUtils.createMedida(themeDisplay, compId, versionId, recordsParamList, medidasRecordList, debilidadRecordList, fortalezaRecordList);

            }
            _log.debug(">>>SaveEstadisticaResourceCommand- compId:  " + compId + " | seccion: "+seccionId);
            JSONObject json = JSONFactoryUtil.createJSONObject();

            resourceResponse.setContentType("application/json");
            resourceResponse.setCharacterEncoding("UTF-8");
            resourceResponse.getWriter().write(json.toString());

        }catch(Exception e){
            _log.info("Error al guardar las estadisticas: " + seccionId+" por el usuarion: "+themeDisplay.getUser().getEmailAddress()+" para la empresa: "+compId);
            _log.error("El error al guardar las estadisticas es : "+e.getMessage());
        }
    }

}

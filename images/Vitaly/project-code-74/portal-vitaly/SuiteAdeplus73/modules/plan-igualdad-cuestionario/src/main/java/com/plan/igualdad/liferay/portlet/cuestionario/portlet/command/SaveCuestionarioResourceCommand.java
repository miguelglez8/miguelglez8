package com.plan.igualdad.liferay.portlet.cuestionario.portlet.command;

import com.liferay.dynamic.data.lists.model.DDLRecord;
import com.liferay.dynamic.data.lists.model.DDLRecordSet;
import com.liferay.dynamic.data.lists.service.DDLRecordLocalServiceUtil;
import com.liferay.dynamic.data.lists.service.DDLRecordSetLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.*;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.plan.igualdad.liferay.portlet.commons.web.parametrizaciones.PlanIgualdadCuestionarioUtils;
import com.plan.igualdad.liferay.portlet.commons.web.parametrizaciones.PlanIgualdadEstadisticasUtils;
import com.plan.igualdad.liferay.portlet.cuestionario.constants.PlanIgualdadCuestionarioPortletKeys;
import com.plan.igualdad.liferay.portlet.cuestionario.util.EstadoUtils;
import com.plan.igualdad.liferay.portlet.manager.model.Respuesta;
import com.plan.igualdad.liferay.portlet.manager.service.EstadoLocalServiceUtil;
import com.plan.igualdad.liferay.portlet.manager.service.RespuestaLocalServiceUtil;
import com.plan.igualdad.liferay.portlet.manager.service.persistence.RespuestaPK;

import java.util.List;

import org.osgi.service.component.annotations.Component;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;

@Component(
        immediate=true,
        property= {
                "javax.portlet.name=" + PlanIgualdadCuestionarioPortletKeys.PLANIGUALDADCUESTIONARIO,
                "mvc.command.name=saveCuestionario"
        },
        service = MVCResourceCommand.class
)
public class SaveCuestionarioResourceCommand extends BaseMVCResourceCommand {
	private static Log _log = LogFactoryUtil.getLog(SaveCuestionarioResourceCommand.class);

    @Override
    protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
    	_log.debug("PASA POR AQUIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII2222222222");
        HttpServletRequest request = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(resourceRequest));
		ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);

        Long compId = ParamUtil.getLong(resourceRequest, PlanIgualdadCuestionarioPortletKeys.COMPID_PARAM);
        Long versionId = ParamUtil.getLong(resourceRequest, PlanIgualdadCuestionarioPortletKeys.VERSIONID_PARAM);
        Long seccionId = Long.parseLong(request.getParameter(PlanIgualdadCuestionarioPortletKeys.SECCIONID_PARAM));
        String respuestas = request.getParameter(PlanIgualdadCuestionarioPortletKeys.RESPUESTAS);

        RespuestaPK respuestaPK = new RespuestaPK();
        respuestaPK.setCompId(compId);
        respuestaPK.setVersionId(versionId);
        respuestaPK.setSeccionId(seccionId);

        Respuesta respuesta = RespuestaLocalServiceUtil.fetchRespuesta(respuestaPK);
        if (Validator.isNull(respuesta)) {
            respuesta = RespuestaLocalServiceUtil.createRespuesta(respuestaPK);
        }

        Long estado = EstadoUtils.getEstadoSeccion(seccionId, respuestas);
        
        String respuestaString = StringUtil.replace(respuestas, StringPool.BACK_SLASH, StringPool.DOUBLE_BACK_SLASH);

        respuesta.setRespuestas(respuestaString.replace("\'", "\\'"));
        respuesta.setEstado(estado);
        RespuestaLocalServiceUtil.updateRespuesta(respuesta);

        String idEstado = com.plan.igualdad.liferay.portlet.commons.web.estado.EstadoUtils.getIdEstado(compId);
        Boolean allowManagementSetting = Boolean.TRUE;
        if(Validator.isNotNull(idEstado) && !idEstado.isEmpty() && !idEstado.equals("0")) {
        	allowManagementSetting = EstadoLocalServiceUtil.allowManagementSettings(Long.parseLong(idEstado));
        }
        
        if (PlanIgualdadCuestionarioPortletKeys.SECCION_PERSONAS != seccionId && allowManagementSetting) {
        	DDLRecordSet recordSetParam = DDLRecordSetLocalServiceUtil.getRecordSet(Long.parseLong(resourceRequest.getPreferences().getValue(PlanIgualdadCuestionarioPortletKeys.ID_PARAMETRIZACIONES, "0")));
			List<DDLRecord> recordsParamList = DDLRecordLocalServiceUtil.getRecords(recordSetParam.getRecordSetId());
			
			DDLRecordSet recordSetMedida = DDLRecordSetLocalServiceUtil.getRecordSet(Long.parseLong(resourceRequest.getPreferences().getValue(PlanIgualdadCuestionarioPortletKeys.ID_MEDIDAS, "0")));
			List<DDLRecord> medidasRecordList = DDLRecordLocalServiceUtil.getRecords(recordSetMedida.getRecordSetId());
			
			DDLRecordSet recordSetDebilidad = DDLRecordSetLocalServiceUtil.getRecordSet(Long.parseLong(resourceRequest.getPreferences().getValue(PlanIgualdadCuestionarioPortletKeys.ID_DEBILIDAD, "0")));
			List<DDLRecord> debilidadRecordList = DDLRecordLocalServiceUtil.getRecords(recordSetDebilidad.getRecordSetId());
			
			DDLRecordSet recordSetFortaleza = DDLRecordSetLocalServiceUtil.getRecordSet(Long.parseLong(resourceRequest.getPreferences().getValue(PlanIgualdadCuestionarioPortletKeys.ID_FORTALEZA, "0")));
			List<DDLRecord> fortalezaRecordList = DDLRecordLocalServiceUtil.getRecords(recordSetFortaleza.getRecordSetId());
			
			DDLRecordSet recordSetDiagnostico = DDLRecordSetLocalServiceUtil.getRecordSet(Long.parseLong(resourceRequest.getPreferences().getValue(PlanIgualdadCuestionarioPortletKeys.ID_DIAGNOSTICOS, "0")));
			List<DDLRecord> diagnosticoRecordList = DDLRecordLocalServiceUtil.getRecords(recordSetDiagnostico.getRecordSetId());

			PlanIgualdadCuestionarioUtils.createMedida(themeDisplay, compId, versionId, recordsParamList, medidasRecordList, debilidadRecordList, fortalezaRecordList, diagnosticoRecordList);	
			PlanIgualdadEstadisticasUtils.createMedida(themeDisplay, compId, versionId, recordsParamList, medidasRecordList, debilidadRecordList, fortalezaRecordList);
        }

        JSONObject json = JSONFactoryUtil.createJSONObject();
        json.put("estado", estado);
        json.put("seccion", seccionId);

        resourceResponse.setContentType("application/json");
        resourceResponse.setCharacterEncoding("UTF-8");
        resourceResponse.getWriter().write(json.toString());
    }

}

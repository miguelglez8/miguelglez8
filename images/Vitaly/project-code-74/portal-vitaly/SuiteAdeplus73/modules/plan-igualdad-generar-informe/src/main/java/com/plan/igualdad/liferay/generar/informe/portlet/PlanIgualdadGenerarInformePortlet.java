package com.plan.igualdad.liferay.generar.informe.portlet;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.plan.igualdad.liferay.generar.informe.constants.PlanIgualdadGenerarInformePortletKeys;
import com.plan.igualdad.liferay.generar.informe.utils.GeneralUtils;
import com.plan.igualdad.liferay.generar.informe.utils.GenerarInformeUtils;

import com.plan.igualdad.liferay.portlet.manager.model.Version;
import com.plan.igualdad.liferay.portlet.manager.service.VersionLocalServiceUtil;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

import net.sf.jasperreports.engine.JRException;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.Map;
import java.io.IOException;

@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=PlanIgualdadGenerarInforme",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + PlanIgualdadGenerarInformePortletKeys.PLANIGUALDADGENERARINFORME,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class PlanIgualdadGenerarInformePortlet extends MVCPortlet {
	private static Log _log = LogFactoryUtil.getLog(PlanIgualdadGenerarInformePortlet.class);

	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
        
        super.render(renderRequest, renderResponse);
	}
	
	@Override
	public void serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws IOException, PortletException {
		ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
        Long compId = ParamUtil.getLong(resourceRequest, PlanIgualdadGenerarInformePortletKeys.COMPID_PARAM);
		Version version = VersionLocalServiceUtil.findVersionActiva(compId);
		try {
			_log.info("entro aqui");
			if(PlanIgualdadGenerarInformePortletKeys.SAVE_COMMENT.equals(resourceRequest.getResourceID())) {
				GeneralUtils.saveComment(resourceRequest);
			}else if(PlanIgualdadGenerarInformePortletKeys.SAVE_EXTENSION.equals(resourceRequest.getResourceID())) {
				GeneralUtils.saveExtension(resourceRequest);
			}else {
				_log.info("llego al global");
	        	Map<String, Object> parameters = GenerarInformeUtils.getParametersGlobal(resourceRequest, compId, themeDisplay, version);
				_log.info("salgo del global");
				if(PlanIgualdadGenerarInformePortletKeys.PLAN_IGUALDAD.equals(resourceRequest.getResourceID())) {
		        	parameters.putAll(GenerarInformeUtils.generarInformePlanIgualdad(compId, themeDisplay, version, resourceResponse, resourceRequest)); 
		        	GenerarInformeUtils.exporterDocument(parameters, themeDisplay, resourceRequest, resourceResponse, PlanIgualdadGenerarInformePortletKeys.PLAN_IGUALDAD);
		        }else if(PlanIgualdadGenerarInformePortletKeys.INFORME_SEGUIMIENTO_PLAN_IGUALDAD.equals(resourceRequest.getResourceID())) {
		        	parameters.putAll(GenerarInformeUtils.generarInformeSeguimiento(compId, themeDisplay, version, resourceResponse));
		        	GenerarInformeUtils.exporterDocument(parameters, themeDisplay, resourceRequest, resourceResponse, PlanIgualdadGenerarInformePortletKeys.INFORME_SEGUIMIENTO_PLAN_IGUALDAD);
		        }else if(PlanIgualdadGenerarInformePortletKeys.INFORME_EVALUACION_PLAN_IGUALDAD.equals(resourceRequest.getResourceID())) {
		        	parameters.putAll(GenerarInformeUtils.generarInformeEvaluacion(compId, themeDisplay, version, resourceResponse));
		        	GenerarInformeUtils.exporterDocument(parameters, themeDisplay, resourceRequest, resourceResponse, PlanIgualdadGenerarInformePortletKeys.INFORME_EVALUACION_PLAN_IGUALDAD);
		        }else if(PlanIgualdadGenerarInformePortletKeys.INFORME_AUDITORIA_PLAN_IGUALDAD.equals(resourceRequest.getResourceID())) {
		        	parameters.putAll(GenerarInformeUtils.generarInformeAuditoria(compId, themeDisplay, version, resourceResponse));
		        	GenerarInformeUtils.exporterDocument(parameters, themeDisplay, resourceRequest, resourceResponse, PlanIgualdadGenerarInformePortletKeys.INFORME_AUDITORIA_PLAN_IGUALDAD);
		        }else if(PlanIgualdadGenerarInformePortletKeys.INFORME_DIAGNOSTICO_PLAN_IGUALDAD.equals(resourceRequest.getResourceID())) {
		        	parameters.putAll(GenerarInformeUtils.generarInformeDiagnostico(compId, themeDisplay, version, resourceResponse, resourceRequest)); 	        	
		        	GenerarInformeUtils.exporterDocument(parameters, themeDisplay, resourceRequest, resourceResponse, PlanIgualdadGenerarInformePortletKeys.INFORME_DIAGNOSTICO_PLAN_IGUALDAD);
		        }else if(PlanIgualdadGenerarInformePortletKeys.DATOS_CUALITATIVOS_ORGANIZACION.equals(resourceRequest.getResourceID())) {
		        	parameters.putAll(GenerarInformeUtils.generarCuestionarioEmpresa(compId, themeDisplay, version, resourceResponse));
		        	GenerarInformeUtils.exporterDocument(parameters, themeDisplay, resourceRequest, resourceResponse, PlanIgualdadGenerarInformePortletKeys.DATOS_CUALITATIVOS_ORGANIZACION);	        
		        }else if(PlanIgualdadGenerarInformePortletKeys.PLAN_IGUALDAD.concat("YEnviar").equals(resourceRequest.getResourceID())) {
		        	parameters.putAll(GenerarInformeUtils.generarInformePlanIgualdad(compId, themeDisplay, version, resourceResponse, resourceRequest)); 
					GenerarInformeUtils.exporterDocument(parameters, themeDisplay, resourceRequest, resourceResponse, PlanIgualdadGenerarInformePortletKeys.PLAN_IGUALDAD);
					GenerarInformeUtils.saveInforme(parameters, compId, version.getVersionId(), themeDisplay, PlanIgualdadGenerarInformePortletKeys.PLAN_IGUALDAD, resourceRequest);
		        }else if(PlanIgualdadGenerarInformePortletKeys.INFORME_SEGUIMIENTO_PLAN_IGUALDAD.concat("YEnviar").equals(resourceRequest.getResourceID())) {
		        	parameters.putAll(GenerarInformeUtils.generarInformeSeguimiento(compId, themeDisplay, version, resourceResponse));
		        	GenerarInformeUtils.exporterDocument(parameters, themeDisplay, resourceRequest, resourceResponse, PlanIgualdadGenerarInformePortletKeys.INFORME_SEGUIMIENTO_PLAN_IGUALDAD);
					GenerarInformeUtils.saveInforme(parameters, compId, version.getVersionId(), themeDisplay, PlanIgualdadGenerarInformePortletKeys.INFORME_SEGUIMIENTO_PLAN_IGUALDAD, resourceRequest);
		        }else if(PlanIgualdadGenerarInformePortletKeys.INFORME_EVALUACION_PLAN_IGUALDAD.concat("YEnviar").equals(resourceRequest.getResourceID())) {
		        	parameters.putAll(GenerarInformeUtils.generarInformeEvaluacion(compId, themeDisplay, version, resourceResponse));
		        	GenerarInformeUtils.exporterDocument(parameters, themeDisplay, resourceRequest, resourceResponse, PlanIgualdadGenerarInformePortletKeys.INFORME_EVALUACION_PLAN_IGUALDAD);
					GenerarInformeUtils.saveInforme(parameters, compId, version.getVersionId(), themeDisplay, PlanIgualdadGenerarInformePortletKeys.INFORME_EVALUACION_PLAN_IGUALDAD, resourceRequest);
		        }else if(PlanIgualdadGenerarInformePortletKeys.INFORME_AUDITORIA_PLAN_IGUALDAD.concat("YEnviar").equals(resourceRequest.getResourceID())) {
		        	parameters.putAll(GenerarInformeUtils.generarInformeAuditoria(compId, themeDisplay, version, resourceResponse));
		        	GenerarInformeUtils.exporterDocument(parameters, themeDisplay, resourceRequest, resourceResponse, PlanIgualdadGenerarInformePortletKeys.INFORME_AUDITORIA_PLAN_IGUALDAD);
					GenerarInformeUtils.saveInforme(parameters, compId, version.getVersionId(), themeDisplay, PlanIgualdadGenerarInformePortletKeys.INFORME_AUDITORIA_PLAN_IGUALDAD, resourceRequest);
		        }else if(PlanIgualdadGenerarInformePortletKeys.INFORME_DIAGNOSTICO_PLAN_IGUALDAD.concat("YEnviar").equals(resourceRequest.getResourceID())) {
		        	parameters.putAll(GenerarInformeUtils.generarInformeDiagnostico(compId, themeDisplay, version, resourceResponse, resourceRequest)); 
					GenerarInformeUtils.exporterDocument(parameters, themeDisplay, resourceRequest, resourceResponse, PlanIgualdadGenerarInformePortletKeys.INFORME_DIAGNOSTICO_PLAN_IGUALDAD);
					GenerarInformeUtils.saveInforme(parameters, compId, version.getVersionId(), themeDisplay, PlanIgualdadGenerarInformePortletKeys.INFORME_DIAGNOSTICO_PLAN_IGUALDAD, resourceRequest);
		        }else if(PlanIgualdadGenerarInformePortletKeys.DATOS_CUALITATIVOS_ORGANIZACION.concat("YEnviar").equals(resourceRequest.getResourceID())) {
		        	parameters.putAll(GenerarInformeUtils.generarCuestionarioEmpresa(compId, themeDisplay, version, resourceResponse));
		        	GenerarInformeUtils.exporterDocument(parameters, themeDisplay, resourceRequest, resourceResponse, PlanIgualdadGenerarInformePortletKeys.DATOS_CUALITATIVOS_ORGANIZACION);
					GenerarInformeUtils.saveInforme(parameters, compId, version.getVersionId(), themeDisplay, PlanIgualdadGenerarInformePortletKeys.DATOS_CUALITATIVOS_ORGANIZACION, resourceRequest);
		        }
			}
		} catch (PortalException | JRException e) {
		
		}
		
		super.serveResource(resourceRequest, resourceResponse);
	}
}
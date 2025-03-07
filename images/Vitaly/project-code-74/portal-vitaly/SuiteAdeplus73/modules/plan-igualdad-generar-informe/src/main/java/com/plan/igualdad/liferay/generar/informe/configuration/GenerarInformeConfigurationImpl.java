package com.plan.igualdad.liferay.generar.informe.configuration;

import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ParamUtil;
import com.plan.igualdad.liferay.generar.informe.constants.PlanIgualdadGenerarInformePortletKeys;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletPreferences;

import org.osgi.service.component.annotations.Component;

@Component(
        configurationPid = PlanIgualdadGenerarInformePortletKeys.PLANIGUALDADGENERARINFORME,
        immediate = true,
        property = "javax.portlet.name=" + PlanIgualdadGenerarInformePortletKeys.PLANIGUALDADGENERARINFORME,
        service = ConfigurationAction.class
)
public class GenerarInformeConfigurationImpl  extends DefaultConfigurationAction{

	 @Override
		public void processAction(PortletConfig portletConfig, ActionRequest actionRequest, ActionResponse actionResponse)
				throws Exception {
		 String portletResource = ParamUtil.getString(actionRequest, "portletResource");
		 PortletPreferences preferences = PortletPreferencesFactoryUtil.getPortletSetup(actionRequest, portletResource);
	        
		 String idDebilidad = ParamUtil.getString(actionRequest, PlanIgualdadGenerarInformePortletKeys.ID_DEBILIDAD);
		 preferences.setValue(PlanIgualdadGenerarInformePortletKeys.ID_DEBILIDAD, idDebilidad);
		 
		 String idFortaleza = ParamUtil.getString(actionRequest, PlanIgualdadGenerarInformePortletKeys.ID_FORTALEZA);
	     preferences.setValue(PlanIgualdadGenerarInformePortletKeys.ID_FORTALEZA, idFortaleza);
		 
	     String idDiagnosticos = ParamUtil.getString(actionRequest, PlanIgualdadGenerarInformePortletKeys.ID_DIAGNOSTICOS);
	     preferences.setValue(PlanIgualdadGenerarInformePortletKeys.ID_DIAGNOSTICOS, idDiagnosticos);
	     
	     String idMedidas = ParamUtil.getString(actionRequest, PlanIgualdadGenerarInformePortletKeys.ID_MEDIDAS);
	     preferences.setValue(PlanIgualdadGenerarInformePortletKeys.ID_MEDIDAS, idMedidas);
        
	     String idParametrizaciones = ParamUtil.getString(actionRequest, PlanIgualdadGenerarInformePortletKeys.ID_PARAMETRIZACIONES);
	     preferences.setValue(PlanIgualdadGenerarInformePortletKeys.ID_PARAMETRIZACIONES, idParametrizaciones);
	        
		 String planIgualdad = ParamUtil.getString(actionRequest, PlanIgualdadGenerarInformePortletKeys.PLAN_IGUALDAD);
		 preferences.setValue(PlanIgualdadGenerarInformePortletKeys.PLAN_IGUALDAD, planIgualdad); 
		 
		 String diagnostico = ParamUtil.getString(actionRequest, PlanIgualdadGenerarInformePortletKeys.INFORME_DIAGNOSTICO_PLAN_IGUALDAD);
		 preferences.setValue(PlanIgualdadGenerarInformePortletKeys.INFORME_DIAGNOSTICO_PLAN_IGUALDAD, diagnostico); 
		 
		 String auditoria = ParamUtil.getString(actionRequest, PlanIgualdadGenerarInformePortletKeys.INFORME_AUDITORIA_PLAN_IGUALDAD);
		 preferences.setValue(PlanIgualdadGenerarInformePortletKeys.INFORME_AUDITORIA_PLAN_IGUALDAD, auditoria); 
		 
		 String seguimiento = ParamUtil.getString(actionRequest, PlanIgualdadGenerarInformePortletKeys.INFORME_SEGUIMIENTO_PLAN_IGUALDAD);
		 preferences.setValue(PlanIgualdadGenerarInformePortletKeys.INFORME_SEGUIMIENTO_PLAN_IGUALDAD, seguimiento); 
		 
		 String evaluacion = ParamUtil.getString(actionRequest, PlanIgualdadGenerarInformePortletKeys.INFORME_EVALUACION_PLAN_IGUALDAD);
		 preferences.setValue(PlanIgualdadGenerarInformePortletKeys.INFORME_EVALUACION_PLAN_IGUALDAD, evaluacion); 
		 
		 String cuestionario = ParamUtil.getString(actionRequest, PlanIgualdadGenerarInformePortletKeys.DATOS_CUALITATIVOS_ORGANIZACION);
		 preferences.setValue(PlanIgualdadGenerarInformePortletKeys.DATOS_CUALITATIVOS_ORGANIZACION, cuestionario); 
	
		 preferences.store();
	        
		 SessionMessages.add(actionRequest, portletConfig.getPortletName() + ".doConfigure");
		 String successMessage = ParamUtil.getString(actionRequest, "successMessage");
		 SessionMessages.add(actionRequest, "request_processed", successMessage);
        
		 super.processAction(portletConfig, actionRequest, actionResponse);
	 }
}

package com.plan.igualdad.liferay.portlet.estadisticas.configuration;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ParamUtil;
import com.plan.igualdad.liferay.portlet.estadisticas.constants.PlanIgualdadEstadisticasPortletKeys;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletPreferences;

import org.osgi.service.component.annotations.Component;

@Component(
        configurationPid = PlanIgualdadEstadisticasPortletKeys.PLANIGUALDADESTADISTICAS,
        immediate = true,
        property = "javax.portlet.name=" + PlanIgualdadEstadisticasPortletKeys.PLANIGUALDADESTADISTICAS,
        service = ConfigurationAction.class
)
public class PlanIgualdadEstadisticasConfigurationImpl extends DefaultConfigurationAction {

    public static Log _log = LogFactoryUtil.getLog(PlanIgualdadEstadisticasConfigurationImpl.class);

    @Override
	public void processAction(PortletConfig portletConfig, ActionRequest actionRequest, ActionResponse actionResponse)
			throws Exception {
    	String portletResource = ParamUtil.getString(actionRequest, "portletResource");
        PortletPreferences preferences = PortletPreferencesFactoryUtil.getPortletSetup(actionRequest, portletResource);
        
        String idMedidas = ParamUtil.getString(actionRequest, PlanIgualdadEstadisticasPortletKeys.ID_MEDIDAS);
        preferences.setValue(PlanIgualdadEstadisticasPortletKeys.ID_MEDIDAS, idMedidas);
        _log.debug("idMedidas: " + idMedidas);
        
        String idParametrizaciones = ParamUtil.getString(actionRequest, PlanIgualdadEstadisticasPortletKeys.ID_PARAMETRIZACIONES);
        preferences.setValue(PlanIgualdadEstadisticasPortletKeys.ID_PARAMETRIZACIONES, idParametrizaciones);
        _log.debug("idParametrizaciones: " + idParametrizaciones);
        
        String idDebilidad = ParamUtil.getString(actionRequest, PlanIgualdadEstadisticasPortletKeys.ID_DEBILIDAD);
        preferences.setValue(PlanIgualdadEstadisticasPortletKeys.ID_DEBILIDAD, idDebilidad);
        _log.debug("idDebilidad: " + idDebilidad);
        
        String idFortaleza = ParamUtil.getString(actionRequest, PlanIgualdadEstadisticasPortletKeys.ID_FORTALEZA);
        preferences.setValue(PlanIgualdadEstadisticasPortletKeys.ID_FORTALEZA, idFortaleza);
        _log.debug("idFortaleza: " + idFortaleza);
        
        String idDiagnostico = ParamUtil.getString(actionRequest, PlanIgualdadEstadisticasPortletKeys.ID_DIAGNOSTICOS);
        preferences.setValue(PlanIgualdadEstadisticasPortletKeys.ID_DIAGNOSTICOS, idDiagnostico);
        _log.debug("idDiagnostico: " + idDiagnostico);

        preferences.store();
        
        SessionMessages.add(actionRequest, portletConfig.getPortletName() + ".doConfigure");
        String successMessage = ParamUtil.getString(actionRequest, "successMessage");
        SessionMessages.add(actionRequest, "request_processed", successMessage);
        
        super.processAction(portletConfig, actionRequest, actionResponse);
    }
}

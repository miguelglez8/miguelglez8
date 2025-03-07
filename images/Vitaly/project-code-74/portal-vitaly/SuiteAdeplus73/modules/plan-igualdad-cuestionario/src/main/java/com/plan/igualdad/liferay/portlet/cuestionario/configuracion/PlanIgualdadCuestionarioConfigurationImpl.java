package com.plan.igualdad.liferay.portlet.cuestionario.configuracion;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ParamUtil;
import com.plan.igualdad.liferay.portlet.cuestionario.constants.PlanIgualdadCuestionarioPortletKeys;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletPreferences;

import org.osgi.service.component.annotations.Component;

@Component(
        configurationPid = PlanIgualdadCuestionarioPortletKeys.PLANIGUALDADCUESTIONARIO,
        immediate = true,
        property = "javax.portlet.name=" + PlanIgualdadCuestionarioPortletKeys.PLANIGUALDADCUESTIONARIO,
        service = ConfigurationAction.class
)
public class PlanIgualdadCuestionarioConfigurationImpl extends DefaultConfigurationAction{

    public static Log _log = LogFactoryUtil.getLog(PlanIgualdadCuestionarioConfigurationImpl.class);

    @Override
	public void processAction(PortletConfig portletConfig, ActionRequest actionRequest, ActionResponse actionResponse)
			throws Exception {
    	String portletResource = ParamUtil.getString(actionRequest, "portletResource");
        PortletPreferences preferences = PortletPreferencesFactoryUtil.getPortletSetup(actionRequest, portletResource);
        
        String idDebilidad = ParamUtil.getString(actionRequest, PlanIgualdadCuestionarioPortletKeys.ID_DEBILIDAD);
        preferences.setValue(PlanIgualdadCuestionarioPortletKeys.ID_DEBILIDAD, idDebilidad);
        _log.debug("idDebilidad: " + idDebilidad);
        
        String idDiagnosticos = ParamUtil.getString(actionRequest, PlanIgualdadCuestionarioPortletKeys.ID_DIAGNOSTICOS);
        preferences.setValue(PlanIgualdadCuestionarioPortletKeys.ID_DIAGNOSTICOS, idDiagnosticos);
        _log.debug("idDiagnosticos: " + idDiagnosticos);
        
        String idFortaleza = ParamUtil.getString(actionRequest, PlanIgualdadCuestionarioPortletKeys.ID_FORTALEZA);
        preferences.setValue(PlanIgualdadCuestionarioPortletKeys.ID_FORTALEZA, idFortaleza);
        _log.debug("idFortaleza: " + idFortaleza);
        
        String idMedidas = ParamUtil.getString(actionRequest, PlanIgualdadCuestionarioPortletKeys.ID_MEDIDAS);
        preferences.setValue(PlanIgualdadCuestionarioPortletKeys.ID_MEDIDAS, idMedidas);
        _log.debug("idMedidas: " + idMedidas);
        
        String idParametrizaciones = ParamUtil.getString(actionRequest, PlanIgualdadCuestionarioPortletKeys.ID_PARAMETRIZACIONES);
        preferences.setValue(PlanIgualdadCuestionarioPortletKeys.ID_PARAMETRIZACIONES, idParametrizaciones);
        _log.debug("idParametrizaciones: " + idParametrizaciones);

        preferences.store();
        
        SessionMessages.add(actionRequest, portletConfig.getPortletName() + ".doConfigure");
        String successMessage = ParamUtil.getString(actionRequest, "successMessage");
        SessionMessages.add(actionRequest, "request_processed", successMessage);
        
        super.processAction(portletConfig, actionRequest, actionResponse);
    }
}

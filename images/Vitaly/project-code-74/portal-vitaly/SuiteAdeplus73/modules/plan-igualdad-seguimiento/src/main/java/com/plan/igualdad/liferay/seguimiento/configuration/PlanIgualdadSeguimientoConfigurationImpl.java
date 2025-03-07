package com.plan.igualdad.liferay.seguimiento.configuration;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ParamUtil;
import com.plan.igualdad.liferay.seguimiento.constants.PlanIgualdadSeguimientoPortletKeys;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletPreferences;

import org.osgi.service.component.annotations.Component;

@Component(
        configurationPid = PlanIgualdadSeguimientoPortletKeys.PLANIGUALDADSEGUIMIENTO,
        immediate = true,
        property = "javax.portlet.name=" + PlanIgualdadSeguimientoPortletKeys.PLANIGUALDADSEGUIMIENTO,
        service = ConfigurationAction.class
)
public class PlanIgualdadSeguimientoConfigurationImpl extends DefaultConfigurationAction{

    public static Log _log = LogFactoryUtil.getLog(PlanIgualdadSeguimientoConfigurationImpl.class);

	@Override
	public void processAction(PortletConfig portletConfig, ActionRequest actionRequest, ActionResponse actionResponse)
			throws Exception {
		
		String portletResource = ParamUtil.getString(actionRequest, "portletResource");
        PortletPreferences preferences = PortletPreferencesFactoryUtil.getPortletSetup(actionRequest, portletResource);
        
        String folderFiles = ParamUtil.getString(actionRequest, PlanIgualdadSeguimientoPortletKeys.FOLDER_FILES);
        preferences.setValue(PlanIgualdadSeguimientoPortletKeys.FOLDER_FILES, folderFiles);
        _log.debug("folderFiles: " + folderFiles);
        
        String idMedidas = ParamUtil.getString(actionRequest, PlanIgualdadSeguimientoPortletKeys.ID_MEDIDAS);
        preferences.setValue(PlanIgualdadSeguimientoPortletKeys.ID_MEDIDAS, idMedidas);
        _log.debug("idMedidas: " + idMedidas);
        
        String idParametrizaciones = ParamUtil.getString(actionRequest, PlanIgualdadSeguimientoPortletKeys.ID_PARAMETRIZACIONES);
        preferences.setValue(PlanIgualdadSeguimientoPortletKeys.ID_PARAMETRIZACIONES, idParametrizaciones);
        _log.debug("idParametrizaciones: " + idParametrizaciones);
        
        String idDebilidad = ParamUtil.getString(actionRequest, PlanIgualdadSeguimientoPortletKeys.ID_DEBILIDAD);
        preferences.setValue(PlanIgualdadSeguimientoPortletKeys.ID_DEBILIDAD, idDebilidad);
        _log.debug("idDebilidad: " + idDebilidad);
        
        String idFortaleza = ParamUtil.getString(actionRequest, PlanIgualdadSeguimientoPortletKeys.ID_FORTALEZA);
        preferences.setValue(PlanIgualdadSeguimientoPortletKeys.ID_FORTALEZA, idFortaleza);
        _log.debug("idFortaleza: " + idFortaleza);
        
        String idDiagnostico = ParamUtil.getString(actionRequest, PlanIgualdadSeguimientoPortletKeys.ID_DIAGNOSTICOS);
        preferences.setValue(PlanIgualdadSeguimientoPortletKeys.ID_DIAGNOSTICOS, idDiagnostico);
        _log.debug("idDiagnostico: " + idDiagnostico);
        
        preferences.store();
        
        SessionMessages.add(actionRequest, portletConfig.getPortletName() + ".doConfigure");
        String successMessage = ParamUtil.getString(actionRequest, "successMessage");
        SessionMessages.add(actionRequest, "request_processed", successMessage);
        
		super.processAction(portletConfig, actionRequest, actionResponse);
	}
}

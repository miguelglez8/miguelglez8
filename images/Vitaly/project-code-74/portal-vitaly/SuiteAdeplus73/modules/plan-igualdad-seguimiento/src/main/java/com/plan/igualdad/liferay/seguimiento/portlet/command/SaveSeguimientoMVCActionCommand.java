package com.plan.igualdad.liferay.seguimiento.portlet.command;

import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.plan.igualdad.liferay.portlet.commons.web.role.PlanIgualdadRoleUtil;
import com.plan.igualdad.liferay.portlet.manager.model.Medida;
import com.plan.igualdad.liferay.portlet.manager.service.MedidaLocalServiceUtil;
import com.plan.igualdad.liferay.seguimiento.constants.PlanIgualdadSeguimientoPortletKeys;
import com.plan.igualdad.liferay.seguimiento.util.PlanIgualdadSeguimientoUtil;
import com.plan.igualdad.liferay.seguimiento.validators.PlanIgualdadSeguimientoValidator;

import java.io.IOException;

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
                "mvc.command.name=/planigualdad/saveMeasure"
        },
        service = MVCActionCommand.class
)
public class SaveSeguimientoMVCActionCommand implements MVCActionCommand{

	private static Log _log = LogFactoryUtil.getLog(SaveSeguimientoMVCActionCommand.class);
	
	public boolean processAction(ActionRequest actionRequest, ActionResponse actionResponse) throws PortletException {
		
		_log.debug("init add seguimiento medida");
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

		boolean clienteRole = PlanIgualdadRoleUtil.isConsultorRole(themeDisplay.getCompanyId(), themeDisplay.getUser()) || PlanIgualdadRoleUtil.isAdministradorRole(themeDisplay.getCompanyId(), themeDisplay.getUser()) ? Boolean.FALSE : Boolean.TRUE;

		if(!PlanIgualdadSeguimientoValidator.validSeguimiento(actionRequest) && !clienteRole) {
			SessionErrors.add(actionRequest, "measure-add-error");
			_log.error("Error: No llegaron todos los datos necesarios");
		}else {
			String compId = ParamUtil.getString(actionRequest, PlanIgualdadSeguimientoPortletKeys.COMPID_PARAM);

			Long idMedida = ParamUtil.getLong(actionRequest, PlanIgualdadSeguimientoPortletKeys.ID_MEASURE);
			_log.debug(">>idMedida: " + idMedida);
			Medida medida = MedidaLocalServiceUtil.fetchMedida(idMedida);
			
			try {
				medida = PlanIgualdadSeguimientoUtil.createMedida(medida, actionRequest, themeDisplay, Boolean.FALSE, Boolean.FALSE, compId, clienteRole);
			} catch (JSONException e1) {
				_log.error("ERROR: ", e1);
			}
			
			String fechaSeguimiento = ParamUtil.getString(actionRequest, PlanIgualdadSeguimientoPortletKeys.TRACING_DATE);
			
			if(Validator.isNotNull(fechaSeguimiento) && !fechaSeguimiento.isEmpty()) {
				_log.debug("Crea nueva version");
				try {
					PlanIgualdadSeguimientoUtil.createMedida(medida, actionRequest, themeDisplay, Boolean.TRUE,Boolean.FALSE, compId, clienteRole);
				} catch (JSONException e) {
					_log.error("ERROR: ", e);
				}
			}

			SessionMessages.add(actionRequest, "measure-add-success");

			try {
				actionResponse.sendRedirect(redirectURL(actionRequest, themeDisplay, compId, "/"));
			} catch (IOException e) {
				_log.error("ERROR: ",  e); 
			}
		}
		
		_log.debug("end add seguimiento medida");
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

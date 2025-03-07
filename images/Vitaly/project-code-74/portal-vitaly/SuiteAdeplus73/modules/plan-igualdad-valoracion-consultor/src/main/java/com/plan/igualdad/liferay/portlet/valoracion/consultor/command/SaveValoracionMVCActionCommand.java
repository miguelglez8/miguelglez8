package com.plan.igualdad.liferay.portlet.valoracion.consultor.command;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.plan.igualdad.liferay.portlet.manager.model.Valoracion;
import com.plan.igualdad.liferay.portlet.manager.model.Version;
import com.plan.igualdad.liferay.portlet.manager.service.ValoracionLocalServiceUtil;
import com.plan.igualdad.liferay.portlet.manager.service.VersionLocalServiceUtil;
import com.plan.igualdad.liferay.portlet.valoracion.consultor.constants.PlanIgualdadValoracionConsultorPortletKeys;
import com.plan.igualdad.liferay.portlet.valoracion.consultor.util.ValoracionUtil;
import com.plan.igualdad.liferay.portlet.valoracion.consultor.validators.PlanIgualdadValoracionConsultorValidator;

import java.io.IOException;
import java.util.Date;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

import org.osgi.service.component.annotations.Component;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + PlanIgualdadValoracionConsultorPortletKeys.PLANIGUALDADVALORACIONCONSULTOR,
                "mvc.command.name=/planigualdad/saveAssessment"
        },
        service = MVCActionCommand.class
)
public class SaveValoracionMVCActionCommand implements MVCActionCommand{

	private static Log _log = LogFactoryUtil.getLog(SaveValoracionMVCActionCommand.class);

	public boolean processAction(ActionRequest actionRequest, ActionResponse actionResponse) throws PortletException {
		_log.info("init add valoracion");
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

		Long idValoracion = ParamUtil.getLong(actionRequest, PlanIgualdadValoracionConsultorPortletKeys.VALORACION_ID);
		Valoracion valoracion = ValoracionLocalServiceUtil.fetchValoracion(idValoracion);
		String compId = StringPool.BLANK;
		
		if(valoracion == null && !PlanIgualdadValoracionConsultorValidator.validValoracion(actionRequest)) {
			SessionErrors.add(actionRequest, "questionary-add-error");
			_log.error("Error: No llegaron todos los datos necesarios");
		}else {
			if(Validator.isNull(valoracion)) {
				valoracion = ValoracionLocalServiceUtil.createValoracion(CounterLocalServiceUtil.increment(Valoracion.class.getName()));
				
				compId = ParamUtil.getString(actionRequest, PlanIgualdadValoracionConsultorPortletKeys.COMPID_PARAM);

	            Version version = VersionLocalServiceUtil.findVersionActiva(Long.parseLong(compId));
	            
	            valoracion.setCompId(Long.parseLong(compId));
	            valoracion.setVersionId(version.getVersionId());
	            valoracion.setUserId(themeDisplay.getUserId());
	            valoracion.setCreateDate(new Date());
	            valoracion.setVersionValoracion(ValoracionLocalServiceUtil.countByComp(Long.parseLong(compId))+1);
	            
	            JSONObject jsonRespuestas = ValoracionUtil.getJSONRespuestas(actionRequest);
	            valoracion.setRespuestasValoracion(StringUtil.replace(jsonRespuestas.toString(), StringPool.BACK_SLASH, StringPool.DOUBLE_BACK_SLASH));
				

			}
			
			valoracion.setObservaciones(StringUtil.replace(ParamUtil.getString(actionRequest, PlanIgualdadValoracionConsultorPortletKeys.OBSERVATIONS), StringPool.BACK_SLASH, StringPool.DOUBLE_BACK_SLASH));
		
			ValoracionLocalServiceUtil.updateValoracion(valoracion);
			
			SessionMessages.add(actionRequest, "questionary-add-success");

			try {
				actionResponse.sendRedirect(redirectURL(actionRequest, themeDisplay, compId, "/"));
			} catch (IOException e) {
				_log.error("ERROR: ",  e); 
			}

		}
		
		_log.info("end add valoracion");
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
        redirectURL.setParameter(PlanIgualdadValoracionConsultorPortletKeys.COMPID_PARAM, compId);
        return redirectURL.toString();
    }
}

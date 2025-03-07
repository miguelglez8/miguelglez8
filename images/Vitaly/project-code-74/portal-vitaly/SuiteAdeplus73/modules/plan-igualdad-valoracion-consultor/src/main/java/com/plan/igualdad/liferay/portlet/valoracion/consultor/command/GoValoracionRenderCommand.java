package com.plan.igualdad.liferay.portlet.valoracion.consultor.command;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.plan.igualdad.liferay.portlet.manager.model.Valoracion;
import com.plan.igualdad.liferay.portlet.manager.service.ValoracionLocalServiceUtil;
import com.plan.igualdad.liferay.portlet.valoracion.consultor.constants.PlanIgualdadValoracionConsultorPortletKeys;
import com.plan.igualdad.liferay.portlet.valoracion.consultor.dto.ValoracionDTO;
import com.plan.igualdad.liferay.portlet.valoracion.consultor.util.ValoracionUtil;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

@Component(
        immediate=true,
        property= {
                "javax.portlet.name=" + PlanIgualdadValoracionConsultorPortletKeys.PLANIGUALDADVALORACIONCONSULTOR,
                "mvc.command.name=goAssessment"
        },
        service = MVCRenderCommand.class
)
public class GoValoracionRenderCommand implements MVCRenderCommand{

	private static Log _log = LogFactoryUtil.getLog(GoValoracionRenderCommand.class);

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
	
		Long valoracionId = ParamUtil.getLong(renderRequest, PlanIgualdadValoracionConsultorPortletKeys.VALORACION_ID, -1);

		Valoracion cuestionario = ValoracionLocalServiceUtil.fetchValoracion(valoracionId);

		if(Validator.isNull(cuestionario)) {
			SessionErrors.add(renderRequest, "questionary-view-error");
			_log.error("Error: No existe el cuestionario: " + cuestionario);
			
			return "/view.jsp";
		}	
		
		ValoracionDTO valoracionDTO = ValoracionUtil.getValoracionDto(cuestionario);
		 
        renderRequest.setAttribute(PlanIgualdadValoracionConsultorPortletKeys.VALORACION_OBJECT, valoracionDTO);

		SessionMessages.add(renderRequest, "assessment-view-success");
		_log.info("VALORACION RECOGIDO CORRECTAMENTE");
		
		return "/valoracion.jsp";
	}
}

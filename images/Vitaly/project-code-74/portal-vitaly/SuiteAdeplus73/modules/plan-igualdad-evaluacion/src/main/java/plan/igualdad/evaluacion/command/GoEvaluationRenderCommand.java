package plan.igualdad.evaluacion.command;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.plan.igualdad.liferay.portlet.manager.model.Evaluacion;
import com.plan.igualdad.liferay.portlet.manager.service.EvaluacionLocalServiceUtil;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

import plan.igualdad.evaluacion.constants.PlanIgualdadEvaluacionPortletKeys;
import plan.igualdad.evaluacion.dto.PlanIgualdadEvaluacionDTO;
import plan.igualdad.evaluacion.util.PlanIgualdadEvaluacionUtil;

@Component(
        immediate=true,
        property= {
                "javax.portlet.name=" + PlanIgualdadEvaluacionPortletKeys.PLANIGUALDADEVALUACION,
                "mvc.command.name=goEvaluation"
        },
        service = MVCRenderCommand.class
)
public class GoEvaluationRenderCommand implements MVCRenderCommand{
	
	private static Log _log = LogFactoryUtil.getLog(GoEvaluationRenderCommand.class);
	
	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
        
		Long evaluationId = ParamUtil.getLong(renderRequest, PlanIgualdadEvaluacionPortletKeys.EVALUATION_ID, -1);

		Evaluacion evaluacion = EvaluacionLocalServiceUtil.fetchEvaluacion(evaluationId);
		
		if(Validator.isNull(evaluacion)) {
			SessionErrors.add(renderRequest, "evaluation-view-error");
			_log.error("Error: No existe la evaluacion: " + evaluationId);
			
			 return "/view.jsp";
		}
		
		PlanIgualdadEvaluacionDTO planIgualdadEvaluacionDTO = PlanIgualdadEvaluacionUtil.getEvaluationDTO(evaluacion);
		
        renderRequest.setAttribute(PlanIgualdadEvaluacionPortletKeys.EVALUATION_OBJECT, planIgualdadEvaluacionDTO);
        renderRequest.setAttribute(PlanIgualdadEvaluacionPortletKeys.EVALUATION_HIDDEN_SAVE, Boolean.TRUE);

        SessionMessages.add(renderRequest, "evaluation-view-success");
        
        return "/evaluation.jsp";
	}

}

package plan.igualdad.evaluacion.command;

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
import com.liferay.portal.kernel.util.WebKeys;
import com.plan.igualdad.liferay.portlet.manager.model.Evaluacion;
import com.plan.igualdad.liferay.portlet.manager.model.Version;
import com.plan.igualdad.liferay.portlet.manager.service.EvaluacionLocalServiceUtil;
import com.plan.igualdad.liferay.portlet.manager.service.VersionLocalServiceUtil;

import java.io.IOException;
import java.util.Date;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

import org.osgi.service.component.annotations.Component;

import plan.igualdad.evaluacion.constants.PlanIgualdadEvaluacionPortletKeys;
import plan.igualdad.evaluacion.util.PlanIgualdadEvaluacionUtil;
import plan.igualdad.evaluacion.validators.PlanIgualdadEvaluacionValidator;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + PlanIgualdadEvaluacionPortletKeys.PLANIGUALDADEVALUACION,
                "mvc.command.name=/planigualdad/saveEvaluation"
        },
        service = MVCActionCommand.class
)
public class SaveEvaluacionMVCActionCommand implements MVCActionCommand{

	private static Log _log = LogFactoryUtil.getLog(SaveEvaluacionMVCActionCommand.class);
	
	public boolean processAction(ActionRequest actionRequest, ActionResponse actionResponse) throws PortletException {
		_log.debug("init add evaluation");
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		String compId = StringPool.BLANK;
		if(!PlanIgualdadEvaluacionValidator.validEvaluation(actionRequest)) {
			SessionErrors.add(actionRequest, "evaluation-add-error");
			_log.error("Error: No llegaron todos los datos necesarios");
		}else {			
			Evaluacion evaluacion = EvaluacionLocalServiceUtil.createEvaluacion(CounterLocalServiceUtil.increment(Evaluacion.class.getName()));
			
			compId = ParamUtil.getString(actionRequest, PlanIgualdadEvaluacionPortletKeys.COMPID_PARAM);
			
			Version version = VersionLocalServiceUtil.findVersionActiva(Long.parseLong(compId));
			
			evaluacion.setVersionId(version.getVersionId());
			evaluacion.setCompId(Long.parseLong(compId));
			evaluacion.setUserId(themeDisplay.getUserId());
			evaluacion.setCreateDate(new Date());
			
			JSONObject jsonDatosGenerales = PlanIgualdadEvaluacionUtil.getJSONDatosGenerales(actionRequest);
			evaluacion.setDatosGenerales(StringUtil.replace(jsonDatosGenerales.toString(), StringPool.BACK_SLASH, " " + StringPool.DOUBLE_BACK_SLASH));
			
			JSONObject jsonInformacionResultados = PlanIgualdadEvaluacionUtil.getJSONInformacionResultados(actionRequest);
			evaluacion.setInformacionResultados(StringUtil.replace(jsonInformacionResultados.toString(), StringPool.BACK_SLASH, " " + StringPool.DOUBLE_BACK_SLASH));
			
			JSONObject jsonInformacionImplantacion = PlanIgualdadEvaluacionUtil.getJSONInformacionImplantacion(actionRequest);
			evaluacion.setInformacionImplantacion(StringUtil.replace(jsonInformacionImplantacion.toString(), StringPool.BACK_SLASH, " " + StringPool.DOUBLE_BACK_SLASH));
			
			JSONObject jsonInformacionImpacto = PlanIgualdadEvaluacionUtil.getJSONInformacionImpacto(actionRequest);
			evaluacion.setInformacionImpacto(StringUtil.replace(jsonInformacionImpacto.toString(), StringPool.BACK_SLASH, " " + StringPool.DOUBLE_BACK_SLASH));
			
			String conclusions = ParamUtil.getString(actionRequest, PlanIgualdadEvaluacionPortletKeys.EVALUATION_CONCLUSIONS);
			evaluacion.setConclusion(conclusions);
			
			String observations = ParamUtil.getString(actionRequest, PlanIgualdadEvaluacionPortletKeys.EVALUATION_OBSERVATIONS);
			evaluacion.setObservaciones(observations);
			
			evaluacion.setVersionEvaluacion(EvaluacionLocalServiceUtil.countByComp(Long.parseLong(compId))+1);
			
			EvaluacionLocalServiceUtil.updateEvaluacion(evaluacion);
			
			SessionMessages.add(actionRequest, "evaluation-add-success");
			
			try {
				actionResponse.sendRedirect(redirectURL(actionRequest, themeDisplay, compId, "/"));
			} catch (IOException e) {
				_log.error("ERROR: " , e);
			}
		}

		_log.debug("end add evaluation");
		
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
	public static String redirectURL(ActionRequest actionRequest, ThemeDisplay themeDisplay, String compId, String renderUrl) {
        String portletName = (String) actionRequest.getAttribute(WebKeys.PORTLET_ID);
        PortletURL redirectURL = PortletURLFactoryUtil.create(actionRequest, portletName, themeDisplay.getLayout().getPlid(), PortletRequest.RENDER_PHASE);
        redirectURL.setParameter("mvcRenderCommandName", renderUrl);
        redirectURL.setParameter(PlanIgualdadEvaluacionPortletKeys.COMPID_PARAM, compId);
        return redirectURL.toString();
    }
}

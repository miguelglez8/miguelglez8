package plan.igualdad.evaluacion.validators;

import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;

import javax.portlet.ActionRequest;

import plan.igualdad.evaluacion.constants.PlanIgualdadEvaluacionPortletKeys;

public class PlanIgualdadEvaluacionValidator {

	/**
	 * ValidEvaluation
	 * @param actionRequest
	 * @return
	 */
	public static final boolean validEvaluation(ActionRequest actionRequest) {
		
		if (Validator.isBlank(ParamUtil.getString(actionRequest, PlanIgualdadEvaluacionPortletKeys.GENERAL_DATA_TYPE))) return false;
		if (Validator.isBlank(ParamUtil.getString(actionRequest, PlanIgualdadEvaluacionPortletKeys.GENERAL_DATA_DATE_FROM))) return false;
		if (Validator.isBlank(ParamUtil.getString(actionRequest, PlanIgualdadEvaluacionPortletKeys.GENERAL_DATA_TYPE_UNTIL))) return false;
		if (Validator.isBlank(ParamUtil.getString(actionRequest, PlanIgualdadEvaluacionPortletKeys.GENERAL_DATA_TEXTAREA))) return false;
		if (Validator.isBlank(ParamUtil.getString(actionRequest, PlanIgualdadEvaluacionPortletKeys.GENERAL_DATA_RADIO))) return false;
		if (Validator.isBlank(ParamUtil.getString(actionRequest, PlanIgualdadEvaluacionPortletKeys.EVALUATION_RESULT_RADIO_1))) return false;
		if (Validator.isBlank(ParamUtil.getString(actionRequest, PlanIgualdadEvaluacionPortletKeys.EVALUATION_RESULT_RADIO_2))) return false;
		if (Validator.isBlank(ParamUtil.getString(actionRequest, PlanIgualdadEvaluacionPortletKeys.EVALUATION_RESULT_RADIO_3))) return false;
		if (Validator.isBlank(ParamUtil.getString(actionRequest, PlanIgualdadEvaluacionPortletKeys.EVALUATION_RESULT_RADIO_4))) return false;
		if (Validator.isBlank(ParamUtil.getString(actionRequest, PlanIgualdadEvaluacionPortletKeys.EVALUATION_RESULT_RADIO_5))) return false;
		if (Validator.isBlank(ParamUtil.getString(actionRequest, PlanIgualdadEvaluacionPortletKeys.EVALUATION_RESULT_RADIO_6))) return false;
		if (Validator.isBlank(ParamUtil.getString(actionRequest, PlanIgualdadEvaluacionPortletKeys.EVALUATION_RESULT_RADIO_7))) return false;
		if (Validator.isBlank(ParamUtil.getString(actionRequest, PlanIgualdadEvaluacionPortletKeys.EVALUATION_RESULT_TEXTAREA))) return false;
		if (Validator.isBlank(ParamUtil.getString(actionRequest, PlanIgualdadEvaluacionPortletKeys.EVALUATION_IMPLANTATION_RADIO_1))) return false;
		if (Validator.isBlank(ParamUtil.getString(actionRequest, PlanIgualdadEvaluacionPortletKeys.EVALUATION_IMPLANTATION_RADIO_2))) return false;
		if (Validator.isBlank(ParamUtil.getString(actionRequest, PlanIgualdadEvaluacionPortletKeys.EVALUATION_IMPLANTATION_RADIO_3))) return false;
		if (Validator.isBlank(ParamUtil.getString(actionRequest, PlanIgualdadEvaluacionPortletKeys.EVALUATION_IMPLANTATION_RADIO_4))) return false;
		if (Validator.isBlank(ParamUtil.getString(actionRequest, PlanIgualdadEvaluacionPortletKeys.EVALUATION_IMPLANTATION_TEXTAREA))) return false;
		if (Validator.isBlank(ParamUtil.getString(actionRequest, PlanIgualdadEvaluacionPortletKeys.EVALUATION_IMPACT_RADIO_1))) return false;
		if (Validator.isBlank(ParamUtil.getString(actionRequest, PlanIgualdadEvaluacionPortletKeys.EVALUATION_IMPACT_RADIO_2))) return false;
		if (Validator.isBlank(ParamUtil.getString(actionRequest, PlanIgualdadEvaluacionPortletKeys.EVALUATION_IMPACT_RADIO_3))) return false;
		if (Validator.isBlank(ParamUtil.getString(actionRequest, PlanIgualdadEvaluacionPortletKeys.EVALUATION_IMPACT_RADIO_4))) return false;
		if (Validator.isBlank(ParamUtil.getString(actionRequest, PlanIgualdadEvaluacionPortletKeys.EVALUATION_IMPACT_RADIO_5))) return false;
		if (Validator.isBlank(ParamUtil.getString(actionRequest, PlanIgualdadEvaluacionPortletKeys.EVALUATION_IMPACT_RADIO_6))) return false;
		if (Validator.isBlank(ParamUtil.getString(actionRequest, PlanIgualdadEvaluacionPortletKeys.EVALUATION_IMPACT_TEXTAREA))) return false;
		if (Validator.isBlank(ParamUtil.getString(actionRequest, PlanIgualdadEvaluacionPortletKeys.EVALUATION_CONCLUSIONS))) return false;
		
		return true;
		
	}
}
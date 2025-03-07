package com.plan.igualdad.liferay.seguimiento.validators;

import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.plan.igualdad.liferay.seguimiento.constants.PlanIgualdadSeguimientoPortletKeys;

import javax.portlet.ActionRequest;

public class PlanIgualdadSeguimientoValidator {

	/**
	 * 
	 * @param actionRequest
	 * @return
	 */
	public static final boolean validSeguimiento(ActionRequest actionRequest) {
		
		if (Validator.isBlank(ParamUtil.getString(actionRequest, PlanIgualdadSeguimientoPortletKeys.MATTER))) return false;
		if (Validator.isBlank(ParamUtil.getString(actionRequest, PlanIgualdadSeguimientoPortletKeys.MEASURE_NAME))) return false;
		if (Validator.isBlank(ParamUtil.getString(actionRequest, PlanIgualdadSeguimientoPortletKeys.MEASURE_DESCRIPTION))) return false;
		if (Validator.isBlank(ParamUtil.getString(actionRequest, PlanIgualdadSeguimientoPortletKeys.PRIORITY))) return false;
		if (Validator.isBlank(ParamUtil.getString(actionRequest, PlanIgualdadSeguimientoPortletKeys.APPLY))) return false;		
		if (Validator.isBlank(ParamUtil.getString(actionRequest, PlanIgualdadSeguimientoPortletKeys.ASSOCIATED_RESOURCES))) return false;
		if (Validator.isBlank(ParamUtil.getString(actionRequest, PlanIgualdadSeguimientoPortletKeys.MONITORING_INDICATORS))) return false;
		
		String apply = ParamUtil.getString(actionRequest, PlanIgualdadSeguimientoPortletKeys.APPLY);
		
		if(Validator.isNotNull(apply) && apply.isEmpty()) {
			if (Validator.isBlank(ParamUtil.getString(actionRequest, PlanIgualdadSeguimientoPortletKeys.RESPONSIBLE))) return false;
			if (Validator.isBlank(ParamUtil.getString(actionRequest, PlanIgualdadSeguimientoPortletKeys.PLANNED_DATE))) return false;
			if (Validator.isBlank(ParamUtil.getString(actionRequest, PlanIgualdadSeguimientoPortletKeys.IMPLANTATION_DATE))) return false;
			if (Validator.isBlank(ParamUtil.getString(actionRequest, PlanIgualdadSeguimientoPortletKeys.EXECUTION))) return false;
			if (Validator.isBlank(ParamUtil.getString(actionRequest, PlanIgualdadSeguimientoPortletKeys.RESOURCES_ASSIGNS))) return false;
			if (Validator.isBlank(ParamUtil.getString(actionRequest, PlanIgualdadSeguimientoPortletKeys.DIFFICULTIES_IMPLANTATION))) return false;
			if (Validator.isBlank(ParamUtil.getString(actionRequest, PlanIgualdadSeguimientoPortletKeys.REDUCTION_INEQUALITIES))) return false;
			if (Validator.isBlank(ParamUtil.getString(actionRequest, PlanIgualdadSeguimientoPortletKeys.PRODUCED_IMPROVEMENTS))) return false;
			if (Validator.isBlank(ParamUtil.getString(actionRequest, PlanIgualdadSeguimientoPortletKeys.PROPOSAL_FUTURE))) return false;
		}
		
		return true;
	}
}

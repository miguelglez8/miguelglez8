package plan.igualdad.evaluacion.util;

import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.plan.igualdad.liferay.portlet.manager.model.Evaluacion;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.portlet.ActionRequest;

import plan.igualdad.evaluacion.constants.PlanIgualdadEvaluacionPortletKeys;
import plan.igualdad.evaluacion.dto.EvaluationImpactDTO;
import plan.igualdad.evaluacion.dto.EvaluationImplantationDTO;
import plan.igualdad.evaluacion.dto.EvaluationResultDTO;
import plan.igualdad.evaluacion.dto.GeneralDataDTO;
import plan.igualdad.evaluacion.dto.PlanIgualdadEvaluacionDTO;

public class PlanIgualdadEvaluacionUtil {

	private static Log _log = LogFactoryUtil.getLog(PlanIgualdadEvaluacionUtil.class);
	
	/**
	 * 
	 * @param evaluationsList
	 * @return
	 */
	public static List<PlanIgualdadEvaluacionDTO> getEvaluations(List<Evaluacion> evaluationsList){
		List<PlanIgualdadEvaluacionDTO> evaluationsDTOList = new ArrayList<>();
				
		if(Validator.isNotNull(evaluationsList) && !evaluationsList.isEmpty()) {
			for(Evaluacion evaluacion : evaluationsList) {
				PlanIgualdadEvaluacionDTO evaluacionDTO = new PlanIgualdadEvaluacionDTO();
				evaluacionDTO.setEvaluationId(evaluacion.getEvaluacionId());
				
				String dateEvaluation = new SimpleDateFormat("dd/MM/yyyy").format(evaluacion.getCreateDate());
				evaluacionDTO.setDateEvaluation(dateEvaluation);
				
				User user = UserLocalServiceUtil.fetchUser(evaluacion.getUserId());
				
				if(Validator.isNotNull(user)) {
					evaluacionDTO.setUserId(user.getUserId());
					evaluacionDTO.setUserName(user.getFirstName() + " " + user.getLastName());
				}
				
				evaluacionDTO.setVersionEvaluacion(evaluacion.getVersionEvaluacion());
				evaluacionDTO.setObservations(evaluacion.getObservaciones());

				evaluationsDTOList.add(evaluacionDTO);
			}
		}
		
		_log.debug("Total evaluaciones: " + evaluationsDTOList.size());
		return evaluationsDTOList;
	}
	
	/**
	 * 
	 * @param actionRequest
	 * @return
	 */
	public static JSONObject getJSONDatosGenerales(ActionRequest actionRequest) {
		JSONObject jsonDatosGenerales = JSONFactoryUtil.createJSONObject();
		
		jsonDatosGenerales.put(PlanIgualdadEvaluacionPortletKeys.GENERAL_DATA_TYPE,  ParamUtil.getString(actionRequest, PlanIgualdadEvaluacionPortletKeys.GENERAL_DATA_TYPE));
		jsonDatosGenerales.put(PlanIgualdadEvaluacionPortletKeys.GENERAL_DATA_DATE_FROM,  ParamUtil.getString(actionRequest, PlanIgualdadEvaluacionPortletKeys.GENERAL_DATA_DATE_FROM));
		jsonDatosGenerales.put(PlanIgualdadEvaluacionPortletKeys.GENERAL_DATA_TYPE_UNTIL,  ParamUtil.getString(actionRequest, PlanIgualdadEvaluacionPortletKeys.GENERAL_DATA_TYPE_UNTIL));
		jsonDatosGenerales.put(PlanIgualdadEvaluacionPortletKeys.GENERAL_DATA_TEXTAREA,  ParamUtil.getString(actionRequest, PlanIgualdadEvaluacionPortletKeys.GENERAL_DATA_TEXTAREA));
		jsonDatosGenerales.put(PlanIgualdadEvaluacionPortletKeys.GENERAL_DATA_RADIO,  ParamUtil.getString(actionRequest, PlanIgualdadEvaluacionPortletKeys.GENERAL_DATA_RADIO));
		
		return jsonDatosGenerales;
	}
	
	/**
	 * 
	 * @param actionRequest
	 * @return
	 */
	public static JSONObject getJSONInformacionResultados(ActionRequest actionRequest) {
		JSONObject jsonInformacionResultados = JSONFactoryUtil.createJSONObject();
		
		jsonInformacionResultados.put(PlanIgualdadEvaluacionPortletKeys.EVALUATION_RESULT_RADIO_1,  ParamUtil.getString(actionRequest, PlanIgualdadEvaluacionPortletKeys.EVALUATION_RESULT_RADIO_1));
		jsonInformacionResultados.put(PlanIgualdadEvaluacionPortletKeys.EVALUATION_RESULT_RADIO_2,  ParamUtil.getString(actionRequest, PlanIgualdadEvaluacionPortletKeys.EVALUATION_RESULT_RADIO_2));
		jsonInformacionResultados.put(PlanIgualdadEvaluacionPortletKeys.EVALUATION_RESULT_RADIO_3,  ParamUtil.getString(actionRequest, PlanIgualdadEvaluacionPortletKeys.EVALUATION_RESULT_RADIO_3));
		jsonInformacionResultados.put(PlanIgualdadEvaluacionPortletKeys.EVALUATION_RESULT_RADIO_4,  ParamUtil.getString(actionRequest, PlanIgualdadEvaluacionPortletKeys.EVALUATION_RESULT_RADIO_4));
		jsonInformacionResultados.put(PlanIgualdadEvaluacionPortletKeys.EVALUATION_RESULT_RADIO_5,  ParamUtil.getString(actionRequest, PlanIgualdadEvaluacionPortletKeys.EVALUATION_RESULT_RADIO_5));
		jsonInformacionResultados.put(PlanIgualdadEvaluacionPortletKeys.EVALUATION_RESULT_RADIO_6,  ParamUtil.getString(actionRequest, PlanIgualdadEvaluacionPortletKeys.EVALUATION_RESULT_RADIO_6));
		jsonInformacionResultados.put(PlanIgualdadEvaluacionPortletKeys.EVALUATION_RESULT_RADIO_7,  ParamUtil.getString(actionRequest, PlanIgualdadEvaluacionPortletKeys.EVALUATION_RESULT_RADIO_7));
		jsonInformacionResultados.put(PlanIgualdadEvaluacionPortletKeys.EVALUATION_RESULT_TEXTAREA,  ParamUtil.getString(actionRequest, PlanIgualdadEvaluacionPortletKeys.EVALUATION_RESULT_TEXTAREA));

		return jsonInformacionResultados;
	}
	
	/**
	 * 
	 * @param actionRequest
	 * @return
	 */
	public static JSONObject getJSONInformacionImplantacion(ActionRequest actionRequest) {
		JSONObject jsonInformacionImplantacion = JSONFactoryUtil.createJSONObject();
		
		jsonInformacionImplantacion.put(PlanIgualdadEvaluacionPortletKeys.EVALUATION_IMPLANTATION_RADIO_1, ParamUtil.getString(actionRequest, PlanIgualdadEvaluacionPortletKeys.EVALUATION_IMPLANTATION_RADIO_1));
		jsonInformacionImplantacion.put(PlanIgualdadEvaluacionPortletKeys.EVALUATION_IMPLANTATION_RADIO_2, ParamUtil.getString(actionRequest, PlanIgualdadEvaluacionPortletKeys.EVALUATION_IMPLANTATION_RADIO_2));
		jsonInformacionImplantacion.put(PlanIgualdadEvaluacionPortletKeys.EVALUATION_IMPLANTATION_RADIO_3, ParamUtil.getString(actionRequest, PlanIgualdadEvaluacionPortletKeys.EVALUATION_IMPLANTATION_RADIO_3));
		jsonInformacionImplantacion.put(PlanIgualdadEvaluacionPortletKeys.EVALUATION_IMPLANTATION_RADIO_4, ParamUtil.getString(actionRequest, PlanIgualdadEvaluacionPortletKeys.EVALUATION_IMPLANTATION_RADIO_4));
		jsonInformacionImplantacion.put(PlanIgualdadEvaluacionPortletKeys.EVALUATION_IMPLANTATION_TEXTAREA, ParamUtil.getString(actionRequest, PlanIgualdadEvaluacionPortletKeys.EVALUATION_IMPLANTATION_TEXTAREA));
		
		return jsonInformacionImplantacion;
	}
	
	/**
	 * 
	 * @param actionRequest
	 * @return
	 */
	public static JSONObject getJSONInformacionImpacto(ActionRequest actionRequest) {
		JSONObject jsonInformacionImpacto = JSONFactoryUtil.createJSONObject();
		
		jsonInformacionImpacto.put(PlanIgualdadEvaluacionPortletKeys.EVALUATION_IMPACT_RADIO_1, ParamUtil.getString(actionRequest, PlanIgualdadEvaluacionPortletKeys.EVALUATION_IMPACT_RADIO_1));
		jsonInformacionImpacto.put(PlanIgualdadEvaluacionPortletKeys.EVALUATION_IMPACT_RADIO_2, ParamUtil.getString(actionRequest, PlanIgualdadEvaluacionPortletKeys.EVALUATION_IMPACT_RADIO_2));
		jsonInformacionImpacto.put(PlanIgualdadEvaluacionPortletKeys.EVALUATION_IMPACT_RADIO_3, ParamUtil.getString(actionRequest, PlanIgualdadEvaluacionPortletKeys.EVALUATION_IMPACT_RADIO_3));
		jsonInformacionImpacto.put(PlanIgualdadEvaluacionPortletKeys.EVALUATION_IMPACT_RADIO_4, ParamUtil.getString(actionRequest, PlanIgualdadEvaluacionPortletKeys.EVALUATION_IMPACT_RADIO_4));
		jsonInformacionImpacto.put(PlanIgualdadEvaluacionPortletKeys.EVALUATION_IMPACT_RADIO_5, ParamUtil.getString(actionRequest, PlanIgualdadEvaluacionPortletKeys.EVALUATION_IMPACT_RADIO_5));
		jsonInformacionImpacto.put(PlanIgualdadEvaluacionPortletKeys.EVALUATION_IMPACT_RADIO_6, ParamUtil.getString(actionRequest, PlanIgualdadEvaluacionPortletKeys.EVALUATION_IMPACT_RADIO_6));
		jsonInformacionImpacto.put(PlanIgualdadEvaluacionPortletKeys.EVALUATION_IMPACT_TEXTAREA, ParamUtil.getString(actionRequest, PlanIgualdadEvaluacionPortletKeys.EVALUATION_IMPACT_TEXTAREA));
		
		return jsonInformacionImpacto;
	}
	
	/**
	 * 
	 * @param evaluacion
	 * @return
	 */
	public static PlanIgualdadEvaluacionDTO getEvaluationDTO (Evaluacion evaluacion) {
		PlanIgualdadEvaluacionDTO evaluacionDTO = new PlanIgualdadEvaluacionDTO();
		
		try {
			if(Validator.isNotNull(evaluacion.getDatosGenerales()) && !evaluacion.getDatosGenerales().isEmpty()) {
				JSONObject jsonGeneralData = JSONFactoryUtil.createJSONObject(evaluacion.getDatosGenerales());
				
				GeneralDataDTO generalDataDTO = new GeneralDataDTO();
				generalDataDTO.setGeneralDataType(jsonGeneralData.getString(PlanIgualdadEvaluacionPortletKeys.GENERAL_DATA_TYPE));
				generalDataDTO.setGeneralDataDateFrom(jsonGeneralData.getString(PlanIgualdadEvaluacionPortletKeys.GENERAL_DATA_DATE_FROM));
				generalDataDTO.setGeneralDataDateUntil(jsonGeneralData.getString(PlanIgualdadEvaluacionPortletKeys.GENERAL_DATA_TYPE_UNTIL));
				generalDataDTO.setGeneralDataTextarea(jsonGeneralData.getString(PlanIgualdadEvaluacionPortletKeys.GENERAL_DATA_TEXTAREA));
				generalDataDTO.setGeneralDataRadio(jsonGeneralData.getString(PlanIgualdadEvaluacionPortletKeys.GENERAL_DATA_RADIO));
				
				evaluacionDTO.setDatosGenerales(generalDataDTO);
			}
			
			if(Validator.isNotNull(evaluacion.getInformacionResultados()) && !evaluacion.getInformacionResultados().isEmpty()) {
				JSONObject jsonInformacionResultados = JSONFactoryUtil.createJSONObject(evaluacion.getInformacionResultados());

				EvaluationResultDTO evaluationResultDTO = new EvaluationResultDTO();
				evaluationResultDTO.setEvaluationResultRadio1(jsonInformacionResultados.getString(PlanIgualdadEvaluacionPortletKeys.EVALUATION_RESULT_RADIO_1));
				evaluationResultDTO.setEvaluationResultRadio2(jsonInformacionResultados.getString(PlanIgualdadEvaluacionPortletKeys.EVALUATION_RESULT_RADIO_2));
				evaluationResultDTO.setEvaluationResultRadio3(jsonInformacionResultados.getString(PlanIgualdadEvaluacionPortletKeys.EVALUATION_RESULT_RADIO_3));
				evaluationResultDTO.setEvaluationResultRadio4(jsonInformacionResultados.getString(PlanIgualdadEvaluacionPortletKeys.EVALUATION_RESULT_RADIO_4));
				evaluationResultDTO.setEvaluationResultRadio5(jsonInformacionResultados.getString(PlanIgualdadEvaluacionPortletKeys.EVALUATION_RESULT_RADIO_5));
				evaluationResultDTO.setEvaluationResultRadio6(jsonInformacionResultados.getString(PlanIgualdadEvaluacionPortletKeys.EVALUATION_RESULT_RADIO_6));
				evaluationResultDTO.setEvaluationResultRadio7(jsonInformacionResultados.getString(PlanIgualdadEvaluacionPortletKeys.EVALUATION_RESULT_RADIO_7));
				evaluationResultDTO.setEvaluationResultTextarea(jsonInformacionResultados.getString(PlanIgualdadEvaluacionPortletKeys.EVALUATION_RESULT_TEXTAREA));
				
				evaluacionDTO.setInformacionResultados(evaluationResultDTO);
			}
			
			if(Validator.isNotNull(evaluacion.getInformacionImplantacion()) && !evaluacion.getInformacionImplantacion().isEmpty()) {
				JSONObject jsonInformacionImplantacion = JSONFactoryUtil.createJSONObject(evaluacion.getInformacionImplantacion());

				EvaluationImplantationDTO evaluationImplantationDTO = new EvaluationImplantationDTO();
				evaluationImplantationDTO.setEvaluationImplantationRadio1(jsonInformacionImplantacion.getString(PlanIgualdadEvaluacionPortletKeys.EVALUATION_IMPLANTATION_RADIO_1));
				evaluationImplantationDTO.setEvaluationImplantationRadio2(jsonInformacionImplantacion.getString(PlanIgualdadEvaluacionPortletKeys.EVALUATION_IMPLANTATION_RADIO_2));
				evaluationImplantationDTO.setEvaluationImplantationRadio3(jsonInformacionImplantacion.getString(PlanIgualdadEvaluacionPortletKeys.EVALUATION_IMPLANTATION_RADIO_3));
				evaluationImplantationDTO.setEvaluationImplantationRadio4(jsonInformacionImplantacion.getString(PlanIgualdadEvaluacionPortletKeys.EVALUATION_IMPLANTATION_RADIO_4));
				evaluationImplantationDTO.setEvaluationImplantationTextarea(jsonInformacionImplantacion.getString(PlanIgualdadEvaluacionPortletKeys.EVALUATION_IMPLANTATION_TEXTAREA));

				evaluacionDTO.setInformacionImplantacion(evaluationImplantationDTO);
			}
			
			if(Validator.isNotNull(evaluacion.getInformacionImpacto()) && !evaluacion.getInformacionImpacto().isEmpty()) {
				JSONObject jsonInformacionImpacto = JSONFactoryUtil.createJSONObject(evaluacion.getInformacionImpacto());
				
				EvaluationImpactDTO evaluationImpactDTO = new EvaluationImpactDTO();
				evaluationImpactDTO.setEvaluationImpactRadio1(jsonInformacionImpacto.getString(PlanIgualdadEvaluacionPortletKeys.EVALUATION_IMPACT_RADIO_1));
				evaluationImpactDTO.setEvaluationImpactRadio2(jsonInformacionImpacto.getString(PlanIgualdadEvaluacionPortletKeys.EVALUATION_IMPACT_RADIO_2));
				evaluationImpactDTO.setEvaluationImpactRadio3(jsonInformacionImpacto.getString(PlanIgualdadEvaluacionPortletKeys.EVALUATION_IMPACT_RADIO_3));
				evaluationImpactDTO.setEvaluationImpactRadio4(jsonInformacionImpacto.getString(PlanIgualdadEvaluacionPortletKeys.EVALUATION_IMPACT_RADIO_4));
				evaluationImpactDTO.setEvaluationImpactRadio5(jsonInformacionImpacto.getString(PlanIgualdadEvaluacionPortletKeys.EVALUATION_IMPACT_RADIO_5));
				evaluationImpactDTO.setEvaluationImpactRadio6(jsonInformacionImpacto.getString(PlanIgualdadEvaluacionPortletKeys.EVALUATION_IMPACT_RADIO_6));
				evaluationImpactDTO.setEvaluationImpactTextarea(jsonInformacionImpacto.getString(PlanIgualdadEvaluacionPortletKeys.EVALUATION_IMPACT_TEXTAREA));
				
				evaluacionDTO.setInformacionImpacto(evaluationImpactDTO);
			}
		
		} catch (JSONException e) {
			_log.error("ERROR: ", e );
		}
		
		evaluacionDTO.setConclusions(evaluacion.getConclusion());
		evaluacionDTO.setObservations(evaluacion.getObservaciones());
		
		return evaluacionDTO;
	}

}

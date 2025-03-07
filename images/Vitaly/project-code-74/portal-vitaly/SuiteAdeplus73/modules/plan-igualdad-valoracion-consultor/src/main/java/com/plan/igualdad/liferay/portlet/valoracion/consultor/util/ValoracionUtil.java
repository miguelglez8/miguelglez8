package com.plan.igualdad.liferay.portlet.valoracion.consultor.util;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.plan.igualdad.liferay.portlet.manager.model.Cuestionario;
import com.plan.igualdad.liferay.portlet.manager.model.Valoracion;
import com.plan.igualdad.liferay.portlet.valoracion.consultor.constants.PlanIgualdadValoracionConsultorPortletKeys;
import com.plan.igualdad.liferay.portlet.valoracion.consultor.dto.CuestionarioDTO;
import com.plan.igualdad.liferay.portlet.valoracion.consultor.dto.RespuestasCuestionarioDTO;
import com.plan.igualdad.liferay.portlet.valoracion.consultor.dto.RespuestasDTO;
import com.plan.igualdad.liferay.portlet.valoracion.consultor.dto.ValoracionDTO;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.portlet.ActionRequest;

public class ValoracionUtil {

	private static Log _log = LogFactoryUtil.getLog(ValoracionUtil.class);
	
	/**
	 * listValoracionesDTO
	 * @param cuestionariolist
	 * @return
	 */
	public static List<ValoracionDTO> listValoracionesDTO (List<Valoracion> valoracioneslist){
		List<ValoracionDTO> listValoracionesDTO = new ArrayList<>();
		
		if(Validator.isNotNull(valoracioneslist) && !valoracioneslist.isEmpty()) {
			for(Valoracion valoracion: valoracioneslist) {

				listValoracionesDTO.add(getValoracionDto(valoracion));
			}
		}
		
		return listValoracionesDTO;
	}
	
	/**
	 * getJSONRespuestas
	 * @param actionRequest
	 * @return
	 */
	public static JSONObject getJSONRespuestas(ActionRequest actionRequest) {
		JSONObject jsonRespuestas = JSONFactoryUtil.createJSONObject();
		
		jsonRespuestas.put(PlanIgualdadValoracionConsultorPortletKeys.ANSWER_1,  ParamUtil.getString(actionRequest, PlanIgualdadValoracionConsultorPortletKeys.ANSWER_1));
		jsonRespuestas.put(PlanIgualdadValoracionConsultorPortletKeys.ANSWER_2,  ParamUtil.getString(actionRequest, PlanIgualdadValoracionConsultorPortletKeys.ANSWER_2));
		jsonRespuestas.put(PlanIgualdadValoracionConsultorPortletKeys.ANSWER_3,  ParamUtil.getString(actionRequest, PlanIgualdadValoracionConsultorPortletKeys.ANSWER_3));

		return jsonRespuestas;
	}
	
	/**
	 * 
	 * @param cuestionario
	 * @return
	 */
	public static ValoracionDTO getValoracionDto (Valoracion valoracion) {
		ValoracionDTO valoracionDTO = new ValoracionDTO();
		
		valoracionDTO.setValoracionId(valoracion.getValoracionId());;
		
		User user = null;
		try {
			user = UserLocalServiceUtil.getUser(valoracion.getUserId());
		} catch (PortalException e1) {
			_log.error("Error: ", e1);
		}
		valoracionDTO.setUsuario(user.getFirstName() + " " + user.getLastName());
		
		valoracionDTO.setVersion(valoracion.getVersionValoracion());
		
		String fechaValoracion = new SimpleDateFormat("dd/MM/yyyy").format(valoracion.getCreateDate());
		valoracionDTO.setFechaValoracion(fechaValoracion);
		
		valoracionDTO.setObservaciones(valoracion.getObservaciones());

		try {
			if(Validator.isNotNull(valoracion.getRespuestasValoracion()) && !valoracion.getRespuestasValoracion().isEmpty()) {
				JSONObject jsonRespuestas = JSONFactoryUtil.createJSONObject(valoracion.getRespuestasValoracion());
				RespuestasDTO respuestasDTO = new RespuestasDTO();
				
				respuestasDTO.setRespuesta1(jsonRespuestas.getString(PlanIgualdadValoracionConsultorPortletKeys.ANSWER_1));
				respuestasDTO.setRespuesta2(jsonRespuestas.getString(PlanIgualdadValoracionConsultorPortletKeys.ANSWER_2));
				respuestasDTO.setRespuesta3(jsonRespuestas.getString(PlanIgualdadValoracionConsultorPortletKeys.ANSWER_3));
		
				valoracionDTO.setRespuestas(respuestasDTO);
			}
		}catch (JSONException e) {
			_log.debug("VALORACION NO TIENE RESPUESTAS");
		}
		
		return valoracionDTO;
	}
	
	/**
	 * 
	 * @param cuestionario
	 * @return
	 */
	public static CuestionarioDTO getCuestionarioDto (Cuestionario cuestionario) {
		CuestionarioDTO cuestionarioDTO = new CuestionarioDTO();
		
		cuestionarioDTO.setCuestionarioId(cuestionario.getCuestionarioId());
		
		User user = null;
		try {
			user = UserLocalServiceUtil.getUser(cuestionario.getUserId());
		} catch (PortalException e1) {
			_log.error("Error: ", e1);
		}
		cuestionarioDTO.setUsuario(user.getFirstName() + " " + user.getLastName());
		
		cuestionarioDTO.setVersion(cuestionario.getVersionCuestionario());
		
		String fechaCuestionario = new SimpleDateFormat("dd/MM/yyyy").format(cuestionario.getCreateDate());
		cuestionarioDTO.setFechaCuestionario(fechaCuestionario);
		
		cuestionarioDTO.setObservaciones(cuestionario.getObservaciones());

		try {
			if(Validator.isNotNull(cuestionario.getRespuestasCuestionario()) && !cuestionario.getRespuestasCuestionario().isEmpty()) {
				JSONObject jsonRespuestas = JSONFactoryUtil.createJSONObject(cuestionario.getRespuestasCuestionario());
				RespuestasCuestionarioDTO respuestasDTO = new RespuestasCuestionarioDTO();
				
				respuestasDTO.setPregunta1(jsonRespuestas.getString(PlanIgualdadValoracionConsultorPortletKeys.TEXTAREA_1));
				respuestasDTO.setPregunta2(jsonRespuestas.getString(PlanIgualdadValoracionConsultorPortletKeys.TEXTAREA_2));
				respuestasDTO.setPregunta3(jsonRespuestas.getString(PlanIgualdadValoracionConsultorPortletKeys.TEXTAREA_3));
				respuestasDTO.setPregunta4(jsonRespuestas.getString(PlanIgualdadValoracionConsultorPortletKeys.TEXTAREA_4));
				respuestasDTO.setPregunta5(jsonRespuestas.getString(PlanIgualdadValoracionConsultorPortletKeys.TEXTAREA_5));
				respuestasDTO.setPregunta6(jsonRespuestas.getString(PlanIgualdadValoracionConsultorPortletKeys.TEXTAREA_6));
				
				cuestionarioDTO.setRespuestas(respuestasDTO);
			}
		}catch (JSONException e) {
			_log.debug("CUESTIONARIO NO TIENE RESPUESTAS");
		}
		
		return cuestionarioDTO;
	}
}

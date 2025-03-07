package com.plan.igualdad.liferay.portlet.commons.web.estado;

import com.adeplus.liferay.portlet.suite.manager.model.Comp;
import com.adeplus.liferay.portlet.suite.manager.model.CompClientApplication;
import com.adeplus.liferay.portlet.suite.manager.service.CompClientApplicationLocalServiceUtil;
import com.adeplus.liferay.portlet.suite.manager.service.CompLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import com.plan.igualdad.liferay.portlet.commons.web.constants.PlanIgualdadCommonsPortletKeys;
import com.plan.igualdad.liferay.portlet.commons.web.dto.EstadoDTO;
import com.plan.igualdad.liferay.portlet.manager.model.Estado;
import com.plan.igualdad.liferay.portlet.manager.model.Version;
import com.plan.igualdad.liferay.portlet.manager.service.EstadoLocalServiceUtil;
import com.plan.igualdad.liferay.portlet.manager.service.VersionLocalServiceUtil;

import java.util.ArrayList;
import java.util.List;

public class EstadoUtils {

	private static Log _log = LogFactoryUtil.getLog(EstadoUtils.class);

	public static void updateEstado(Long compId, String estadoId) {
		Comp comp;
		long idCompany = 0;
		CompClientApplication compClientApplication;
		try {
			if(!PlanIgualdadCommonsPortletKeys.ITS_NEW_VERSION) {
				comp = CompLocalServiceUtil.getComp(compId);
			}else{
				compClientApplication = CompClientApplicationLocalServiceUtil.fetchCompClientApplication(compId);
				if(Validator.isNotNull(compClientApplication)){
					compClientApplication.setIdEstado(estadoId);
				}
				comp = CompLocalServiceUtil.getComp(compClientApplication.getCompId());
				CompClientApplicationLocalServiceUtil.updateCompClientApplication(compClientApplication);
			}

			comp.setIdEstado(estadoId);
			CompLocalServiceUtil.updateComp(comp);
			
		} catch (PortalException e) {
			_log.error("ERROR: " , e);
		}
	}
	
	public static List<EstadoDTO> getEstados (){
		List<EstadoDTO> listEstadosDTO = new ArrayList<>();
		List<Estado> listEstados = EstadoLocalServiceUtil.getEstadosByActive();

		if(Validator.isNotNull(listEstados)) {
			for(Estado estado : listEstados) {
				listEstadosDTO.add(new EstadoDTO(estado.getEstadoId(), estado.getNombre()));
			}
		}

		return listEstadosDTO;
	}
	
	public static String getIdEstado(Long compId) {
		String estadoString = "0"; //StringPool.BLANK;
		try {
			//Comp comp = null;
			/*if(!PlanIgualdadCommonsPortletKeys.ITS_NEW_VERSION) {
				 comp = CompLocalServiceUtil.getComp(compId);
			}else{
				comp = CompLocalServiceUtil.fetchComp(compId);
			}*/
			CompClientApplication comp = CompClientApplicationLocalServiceUtil.fetchCompClientApplication(compId);
			if(Validator.isNotNull(comp) && Validator.isNotNull(comp.getIdEstado()) && !comp.getIdEstado().isEmpty()){
				estadoString = comp.getIdEstado();
			}

		} catch (Exception e) {
			_log.error("ERROR: ", e);
		}
		
		return estadoString;
	}
	
	public static String getEstado(Long compId) {
		String estadoString = StringPool.BLANK;
		
		try {
			Comp comp = null;
			if(!PlanIgualdadCommonsPortletKeys.ITS_NEW_VERSION) {
				comp = CompLocalServiceUtil.getComp(compId);
			}else{
				comp = CompLocalServiceUtil.fetchComp(compId);
			}
			if(Validator.isNotNull(comp.getIdEstado()) || !comp.getIdEstado().isEmpty()) {
				Estado estado = EstadoLocalServiceUtil.getEstado(Long.parseLong(comp.getIdEstado()));
				
				estadoString = estado.getNombre();
			}
		} catch (PortalException e) {
			_log.error("ERROR: ", e);
		}
		
		return estadoString;
	}
	
	public static void updateEstadoAllComps() {
		List<Comp> listAllComps = CompLocalServiceUtil.getAllComps();
	
		if(Validator.isNotNull(listAllComps)) {
			for(Comp comp : listAllComps) {
				if(Validator.isNull(comp.getIdEstado()) || comp.getIdEstado().isEmpty()) {
					Version version = VersionLocalServiceUtil.findVersionActiva(comp.getCompId());
				
					if(Validator.isNotNull(version)) {
						comp.setIdEstado("1");
						
						CompLocalServiceUtil.updateComp(comp);
					}
				}
			}
		}
	}
}

package com.plan.igualdad.liferay.seguimiento.portlet;

import com.plan.igualdad.liferay.portlet.commons.web.constants.PlanIgualdadCommonsPortletKeys;
import com.plan.igualdad.liferay.portlet.commons.web.parametrizaciones.PlanIgualdadCuestionarioUtils;
import com.plan.igualdad.liferay.portlet.commons.web.parametrizaciones.PlanIgualdadEstadisticasUtils;
import com.plan.igualdad.liferay.portlet.commons.web.role.PlanIgualdadRoleUtil;
import com.plan.igualdad.liferay.portlet.manager.model.Medida;
import com.plan.igualdad.liferay.portlet.manager.model.ParametricasFDD;
import com.plan.igualdad.liferay.portlet.manager.model.Version;
import com.plan.igualdad.liferay.portlet.manager.service.MedidaLocalServiceUtil;
import com.plan.igualdad.liferay.portlet.manager.service.ParametricasFDDLocalServiceUtil;
import com.plan.igualdad.liferay.portlet.manager.service.VersionLocalServiceUtil;
import com.plan.igualdad.liferay.seguimiento.constants.PlanIgualdadSeguimientoPortletKeys;
import com.plan.igualdad.liferay.seguimiento.enums.CategoriaEnum;
import com.plan.igualdad.liferay.seguimiento.enums.NivelEjecucionEnum;
import com.plan.igualdad.liferay.seguimiento.enums.PrioridadEnum;
import com.plan.igualdad.liferay.seguimiento.util.PlanIgualdadSeguimientoUtil;

import java.io.IOException;
import java.util.List;

import com.adeplus.liferay.portlet.suite.manager.model.UserCompany;
import com.adeplus.liferay.portlet.suite.manager.service.UserCompanyLocalServiceUtil;
import com.liferay.dynamic.data.lists.model.DDLRecord;
import com.liferay.dynamic.data.lists.model.DDLRecordSet;
import com.liferay.dynamic.data.lists.service.DDLRecordLocalServiceUtil;
import com.liferay.dynamic.data.lists.service.DDLRecordSetLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;

@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.planigualdad",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=PlanIgualdadSeguimiento",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + PlanIgualdadSeguimientoPortletKeys.PLANIGUALDADSEGUIMIENTO,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class PlanIgualdadSeguimientoPortlet extends MVCPortlet {

	private static Log _log = LogFactoryUtil.getLog(PlanIgualdadSeguimientoPortlet.class);
	
	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {

		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		String compId = StringPool.BLANK;

        if (PlanIgualdadRoleUtil.isAdministradorRole(themeDisplay.getCompanyId(), themeDisplay.getUser())
                || PlanIgualdadRoleUtil.isConsultorRole(themeDisplay.getCompanyId(), themeDisplay.getUser())) {

            HttpServletRequest request = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(renderRequest));
            
            compId = request.getParameter(PlanIgualdadSeguimientoPortletKeys.QUERY_PARAM);
            if(compId == null || compId.isEmpty()) {
            	compId = renderRequest.getParameter(PlanIgualdadSeguimientoPortletKeys.QUERY_PARAM);
            }
            if (Validator.isNull(compId)) compId = ParamUtil.getString(renderRequest, PlanIgualdadSeguimientoPortletKeys.COMPID_PARAM);

        }
        
        if(compId.isEmpty()) {
			UserCompany company = UserCompanyLocalServiceUtil.getUserDefaultCompany(themeDisplay.getUserId());
			//if(PlanIgualdadCommonsPortletKeys.ITS_NEW_VERSION) {
			//	compId = String.valueOf(company.getDefaultEmpresaId());
			//}else{
				compId = String.valueOf(company.getDefaultEmpresaId());
			//}
        }
		if(Validator.isNotNull(compId) && !compId.isEmpty()) {

			Version version = VersionLocalServiceUtil.findVersionActiva(Long.parseLong(compId));
			List<Medida> medidasList = MedidaLocalServiceUtil.findByCompVersion(Long.parseLong(compId), version.getVersionId());
			//List<Medida> medidasList = MedidaLocalServiceUtil.findByComp(Long.parseLong(compId));
			_log.debug("Medidas size: " + medidasList.size());
    		renderRequest.setAttribute("medidasList", PlanIgualdadSeguimientoUtil.getMedidas(medidasList, renderRequest, themeDisplay));

    		DDLRecordSet recordSetMedida = null;
			try {
				recordSetMedida = DDLRecordSetLocalServiceUtil.getRecordSet(Long.parseLong(renderRequest.getPreferences().getValue(PlanIgualdadSeguimientoPortletKeys.ID_MEDIDAS, "0")));
				
				List<DDLRecord> medidasRecordList = DDLRecordLocalServiceUtil.getRecords(recordSetMedida.getRecordSetId());

				renderRequest.setAttribute("medidasListSelect", PlanIgualdadSeguimientoUtil.getMedidasSelect(medidasList, medidasRecordList, renderRequest, themeDisplay));
				
				//Version version = VersionLocalServiceUtil.findVersionActiva(Long.parseLong(compId));
				if(Validator.isNotNull(version)) {
					List<ParametricasFDD> listParametricas = ParametricasFDDLocalServiceUtil.findParametricasByCompIdAndVersion(Long.parseLong(compId), version.getVersionId());
					if(Validator.isNull(listParametricas) || listParametricas.isEmpty()) {
						DDLRecordSet recordSetParam = DDLRecordSetLocalServiceUtil.getRecordSet(Long.parseLong(renderRequest.getPreferences().getValue(PlanIgualdadSeguimientoPortletKeys.ID_PARAMETRIZACIONES, "0")));
						List<DDLRecord> recordsParamList = DDLRecordLocalServiceUtil.getRecords(recordSetParam.getRecordSetId());

						DDLRecordSet recordSetDebilidad = DDLRecordSetLocalServiceUtil.getRecordSet(Long.parseLong(renderRequest.getPreferences().getValue(PlanIgualdadSeguimientoPortletKeys.ID_DEBILIDAD, "0")));
						List<DDLRecord> debilidadRecordList = DDLRecordLocalServiceUtil.getRecords(recordSetDebilidad.getRecordSetId());
						
						DDLRecordSet recordSetFortaleza = DDLRecordSetLocalServiceUtil.getRecordSet(Long.parseLong(renderRequest.getPreferences().getValue(PlanIgualdadSeguimientoPortletKeys.ID_FORTALEZA, "0")));
						List<DDLRecord> fortalezaRecordList = DDLRecordLocalServiceUtil.getRecords(recordSetFortaleza.getRecordSetId());	
						
						DDLRecordSet recordSetDiagnostico = DDLRecordSetLocalServiceUtil.getRecordSet(Long.parseLong(renderRequest.getPreferences().getValue(PlanIgualdadSeguimientoPortletKeys.ID_DIAGNOSTICOS, "0")));
						List<DDLRecord> diagnosticoRecordList = DDLRecordLocalServiceUtil.getRecords(recordSetDiagnostico.getRecordSetId());
						
						PlanIgualdadCuestionarioUtils.createMedida(themeDisplay, Long.parseLong(compId), version.getVersionId(), recordsParamList, medidasRecordList, debilidadRecordList, fortalezaRecordList, diagnosticoRecordList);	
						PlanIgualdadEstadisticasUtils.createMedida(themeDisplay, Long.parseLong(compId), version.getVersionId(), recordsParamList, medidasRecordList, debilidadRecordList, fortalezaRecordList);

					}
				}
			} catch (NumberFormatException | PortalException e) {
				_log.error("ERROR: ", e);
			}
			
    		renderRequest.setAttribute("versionList", PlanIgualdadSeguimientoUtil.getVersionsMedidas(medidasList));
		}
		
		PrioridadEnum[] prioridades = PrioridadEnum.values();
		renderRequest.setAttribute("prioridadesList", prioridades);
		
		NivelEjecucionEnum[] nivelesEjecucion = NivelEjecucionEnum.values();
		renderRequest.setAttribute("nivelesEjecucionList", nivelesEjecucion);
		
		CategoriaEnum[] categorias = CategoriaEnum.values();
		renderRequest.setAttribute("categoriasList", categorias);
		
		boolean clienteRole = PlanIgualdadRoleUtil.isConsultorRole(themeDisplay.getCompanyId(), themeDisplay.getUser()) || PlanIgualdadRoleUtil.isAdministradorRole(themeDisplay.getCompanyId(), themeDisplay.getUser()) ? Boolean.FALSE : Boolean.TRUE;

		renderRequest.setAttribute(PlanIgualdadSeguimientoPortletKeys.COMPID_PARAM, compId);

		renderRequest.setAttribute(PlanIgualdadSeguimientoPortletKeys.CLIENTE_ROLE, clienteRole);

		super.render(renderRequest, renderResponse);
	}
}
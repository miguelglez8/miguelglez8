package com.plan.igualdad.liferay.portlet.valoracion.consultor.portlet;

import com.plan.igualdad.liferay.portlet.commons.web.constants.PlanIgualdadCommonsPortletKeys;
import com.plan.igualdad.liferay.portlet.commons.web.role.PlanIgualdadRoleUtil;
import com.plan.igualdad.liferay.portlet.manager.model.Cuestionario;
import com.plan.igualdad.liferay.portlet.manager.model.Medida;
import com.plan.igualdad.liferay.portlet.manager.model.Valoracion;
import com.plan.igualdad.liferay.portlet.manager.service.CuestionarioLocalServiceUtil;
import com.plan.igualdad.liferay.portlet.manager.service.MedidaLocalServiceUtil;
import com.plan.igualdad.liferay.portlet.manager.service.ValoracionLocalServiceUtil;
import com.plan.igualdad.liferay.portlet.valoracion.consultor.constants.PlanIgualdadValoracionConsultorPortletKeys;
import com.plan.igualdad.liferay.portlet.valoracion.consultor.dto.CuestionarioDTO;
import com.plan.igualdad.liferay.portlet.valoracion.consultor.util.ValoracionUtil;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

import com.adeplus.liferay.portlet.suite.manager.model.UserCompany;
import com.adeplus.liferay.portlet.suite.manager.service.UserCompanyLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
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
		"javax.portlet.display-name=PlanIgualdadValoracionConsultor",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + PlanIgualdadValoracionConsultorPortletKeys.PLANIGUALDADVALORACIONCONSULTOR,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class PlanIgualdadValoracionConsultorPortlet extends MVCPortlet {

	private static Log _log = LogFactoryUtil.getLog(PlanIgualdadValoracionConsultorPortlet.class);

	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);

		String compId = StringPool.BLANK;

        if (PlanIgualdadRoleUtil.isAdministradorRole(themeDisplay.getCompanyId(), themeDisplay.getUser())
                || PlanIgualdadRoleUtil.isConsultorRole(themeDisplay.getCompanyId(), themeDisplay.getUser())) {

            HttpServletRequest request = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(renderRequest));
            
            compId = request.getParameter(PlanIgualdadValoracionConsultorPortletKeys.QUERY_PARAM);
            if(compId == null || compId.isEmpty()) {
            	compId = renderRequest.getParameter(PlanIgualdadValoracionConsultorPortletKeys.QUERY_PARAM);
            }
            if (Validator.isNull(compId)) compId = ParamUtil.getString(renderRequest, PlanIgualdadValoracionConsultorPortletKeys.COMPID_PARAM);

        }
        
        if(compId.isEmpty()) {
			UserCompany company = UserCompanyLocalServiceUtil.getUserDefaultCompany(themeDisplay.getUserId());
			if(PlanIgualdadCommonsPortletKeys.ITS_NEW_VERSION) {
				compId = String.valueOf(company.getDefaultEmpresaId());
			}else{
				compId = String.valueOf(company.getCompId());
			}
        }		
        
		if (Validator.isNotNull(compId) && !compId.isEmpty()) {
		
			int versions = ValoracionLocalServiceUtil.countByComp(Long.parseLong(compId));
			_log.info("Versiones: " + versions);
			renderRequest.setAttribute(PlanIgualdadValoracionConsultorPortletKeys.VERSIONS, versions);

			List<Valoracion> valoracionesList = ValoracionLocalServiceUtil.findByComp(Long.parseLong(compId));
			_log.info("Valoracion: " + valoracionesList.size());
			renderRequest.setAttribute(PlanIgualdadValoracionConsultorPortletKeys.VALORACIONES, ValoracionUtil.listValoracionesDTO(valoracionesList));
			
			List<Medida> medidasList = MedidaLocalServiceUtil.findByComp(Long.parseLong(compId));

			int medidasEjecutadasTotal= 0;
			int medidasPrioritariasEjecutadas = 0;
			int medidasRepositorioDocumental= 0;
			
			try {
				if(Validator.isNotNull(medidasList) && !medidasList.isEmpty()) {
					for(Medida medida : medidasList) {
						
						Boolean prioridadAlta = Boolean.FALSE;
						
						if(Validator.isNotNull(medida.getDatosGenerales()) && !medida.getDatosGenerales().isEmpty()) {
							JSONObject jsonGeneralData = JSONFactoryUtil.createJSONObject(medida.getDatosGenerales());
							
							if(jsonGeneralData.getInt(PlanIgualdadValoracionConsultorPortletKeys.PRIORITY)==2) {
								prioridadAlta = Boolean.TRUE;
							}
						}
						if(Validator.isNotNull(medida.getCumplimentacion()) && !medida.getCumplimentacion().isEmpty()) {
							JSONObject jsonCumplimentacion = JSONFactoryUtil.createJSONObject(medida.getCumplimentacion());
							
							if(jsonCumplimentacion.getInt(PlanIgualdadValoracionConsultorPortletKeys.EXECUTION) == 3) {
								medidasEjecutadasTotal++;
								
								if(prioridadAlta) {
									medidasPrioritariasEjecutadas++;
								}
							}
							
							if(jsonCumplimentacion.getLong(PlanIgualdadValoracionConsultorPortletKeys.UPLOAD_FILE) != 0L) {
								medidasRepositorioDocumental++;
							}
						}
					}
				}
			}catch (JSONException e) {
				_log.error("ERROR: ", e);
			}
			
			float medidasEjecutadasTotalPor = 0;

			float medidasPrioritariasEjecutadasPor = 0;

			if(medidasList.size()>0){
				medidasEjecutadasTotalPor = ((float)medidasEjecutadasTotal/medidasList.size())*100;
				medidasPrioritariasEjecutadasPor = ((float)medidasPrioritariasEjecutadas/medidasList.size())*100;
			}

			String pregunta1 = StringPool.BLANK;
			String pregunta2 = StringPool.BLANK;
			String pregunta3 = StringPool.BLANK;
			String pregunta5 = StringPool.BLANK;
			
			DecimalFormat df = new DecimalFormat();
			df.setMaximumFractionDigits(2);
			renderRequest.setAttribute(PlanIgualdadValoracionConsultorPortletKeys.MEDIDAS_EJECUTADAS_TOTAL, df.format(medidasEjecutadasTotalPor) );
			renderRequest.setAttribute(PlanIgualdadValoracionConsultorPortletKeys.MEDIDAS_PRIORITARIAS_EJECUTADAS, df.format(medidasPrioritariasEjecutadasPor));
			renderRequest.setAttribute(PlanIgualdadValoracionConsultorPortletKeys.MEDIDAS_REPO_DOCUMENTAL, medidasRepositorioDocumental);
		
			List<Cuestionario> cuestionarios = CuestionarioLocalServiceUtil.findByComp(Long.parseLong(compId));
			
			if(Validator.isNotNull(cuestionarios) && !cuestionarios.isEmpty()) {
				int version = CuestionarioLocalServiceUtil.countByComp(Long.parseLong(compId));
				for(Cuestionario cuestionario : cuestionarios) {
					if(cuestionario.getVersionCuestionario() == version) {
						CuestionarioDTO cuestionarioDTO = ValoracionUtil.getCuestionarioDto(cuestionario);
						
						pregunta1 = cuestionarioDTO.getRespuestas().getPregunta1();
						pregunta2 = cuestionarioDTO.getRespuestas().getPregunta2();
						pregunta3 = cuestionarioDTO.getRespuestas().getPregunta3();
						pregunta5 = cuestionarioDTO.getRespuestas().getPregunta5();
					}
				}
			}
			
			renderRequest.setAttribute(PlanIgualdadValoracionConsultorPortletKeys.CUESTIONARIO_PREGUNTA_1, pregunta1);
			renderRequest.setAttribute(PlanIgualdadValoracionConsultorPortletKeys.CUESTIONARIO_PREGUNTA_2, pregunta2);
			renderRequest.setAttribute(PlanIgualdadValoracionConsultorPortletKeys.CUESTIONARIO_PREGUNTA_3, pregunta3);
			renderRequest.setAttribute(PlanIgualdadValoracionConsultorPortletKeys.CUESTIONARIO_PREGUNTA_5, pregunta5);

			renderRequest.setAttribute(PlanIgualdadValoracionConsultorPortletKeys.COMPID_PARAM, compId);

		}
		
		super.render(renderRequest, renderResponse);
	}
	
	
}
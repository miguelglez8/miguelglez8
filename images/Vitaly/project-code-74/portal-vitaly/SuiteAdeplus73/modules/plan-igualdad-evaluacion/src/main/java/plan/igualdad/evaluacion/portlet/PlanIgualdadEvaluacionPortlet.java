package plan.igualdad.evaluacion.portlet;

import com.plan.igualdad.liferay.portlet.commons.web.constants.PlanIgualdadCommonsPortletKeys;
import plan.igualdad.evaluacion.constants.PlanIgualdadEvaluacionPortletKeys;
import plan.igualdad.evaluacion.util.PlanIgualdadEvaluacionUtil;

import com.adeplus.liferay.portlet.suite.manager.model.UserCompany;
import com.adeplus.liferay.portlet.suite.manager.service.UserCompanyLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.plan.igualdad.liferay.portlet.commons.web.role.PlanIgualdadRoleUtil;
import com.plan.igualdad.liferay.portlet.manager.model.Evaluacion;
import com.plan.igualdad.liferay.portlet.manager.service.EvaluacionLocalServiceUtil;

import java.io.IOException;
import java.util.List;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;

/**
 * @author ccarrasco
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.planigualdad",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=PlanIgualdadEvaluacion",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + PlanIgualdadEvaluacionPortletKeys.PLANIGUALDADEVALUACION,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class PlanIgualdadEvaluacionPortlet extends MVCPortlet {
	
	private static Log _log = LogFactoryUtil.getLog(PlanIgualdadEvaluacionPortlet.class);
	
	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		String compId = StringPool.BLANK;

        if (PlanIgualdadRoleUtil.isAdministradorRole(themeDisplay.getCompanyId(), themeDisplay.getUser())
                || PlanIgualdadRoleUtil.isConsultorRole(themeDisplay.getCompanyId(), themeDisplay.getUser())) {

            HttpServletRequest request = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(renderRequest));
            
            compId = request.getParameter(PlanIgualdadEvaluacionPortletKeys.QUERY_PARAM);
            if(compId == null || compId.isEmpty()) {
            	compId = renderRequest.getParameter(PlanIgualdadEvaluacionPortletKeys.QUERY_PARAM);
            }
            if (Validator.isNull(compId)) compId = ParamUtil.getString(renderRequest, PlanIgualdadEvaluacionPortletKeys.COMPID_PARAM);

        }
        
        if(compId.isEmpty()) {
			UserCompany company = UserCompanyLocalServiceUtil.getUserDefaultCompany(themeDisplay.getUserId());
			if(PlanIgualdadCommonsPortletKeys.ITS_NEW_VERSION) {
				compId = String.valueOf(company.getDefaultEmpresaId());
			}else{
				compId = String.valueOf(company.getCompId());
			}
        }
                				
		if(Validator.isNotNull(compId) && !compId.isEmpty()) {
        	    		
    		int versions = EvaluacionLocalServiceUtil.countByComp(Long.parseLong(compId));
    		_log.debug("Versions size: " + versions);
    		renderRequest.setAttribute("versionList", versions);
    		
    		List<Evaluacion> evaluacionList = EvaluacionLocalServiceUtil.findByComp(Long.parseLong(compId));
    		_log.debug("Evaluations size: " + evaluacionList.size());
    		renderRequest.setAttribute("evaluationsList", PlanIgualdadEvaluacionUtil.getEvaluations(evaluacionList));

        }
		
		renderRequest.setAttribute(PlanIgualdadEvaluacionPortletKeys.COMPID_PARAM, compId);
        renderRequest.setAttribute(PlanIgualdadEvaluacionPortletKeys.EVALUATION_HIDDEN_SAVE, Boolean.FALSE);

		super.render(renderRequest, renderResponse);
	}

}
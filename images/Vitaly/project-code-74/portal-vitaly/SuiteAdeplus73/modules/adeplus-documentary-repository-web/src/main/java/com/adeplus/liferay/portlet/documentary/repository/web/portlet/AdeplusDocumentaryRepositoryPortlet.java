package com.adeplus.liferay.portlet.documentary.repository.web.portlet;

import com.adeplus.liferay.portlet.documentary.repository.web.constants.AdeplusDocumentaryRepositoryPortletKeys;
import com.adeplus.liferay.portlet.documentary.repository.web.util.AdeplusDocumentRepositoryUtil;
import com.adeplus.liferay.portlet.suite.manager.model.UserCompany;
import com.adeplus.liferay.portlet.suite.manager.service.UserCompanyLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.plan.igualdad.liferay.portlet.commons.web.role.PlanIgualdadRoleUtil;

import java.io.IOException;
import java.util.List;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;

@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.adeplus",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=AdeplusDocumentaryRepository",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + AdeplusDocumentaryRepositoryPortletKeys.ADEPLUSDOCUMENTARYREPOSITORY,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class AdeplusDocumentaryRepositoryPortlet extends MVCPortlet {

	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);

		String applicationConfiguration = renderRequest.getPreferences().getValue(AdeplusDocumentaryRepositoryPortletKeys.APPLICATION, StringPool.BLANK);

		String parentFolderConfiguration = renderRequest.getPreferences().getValue(AdeplusDocumentaryRepositoryPortletKeys.FOLDER_FILES, StringPool.BLANK);

		String compId = StringPool.BLANK;
		
		if (PlanIgualdadRoleUtil.isAdministradorRole(themeDisplay.getCompanyId(), themeDisplay.getUser())
                || PlanIgualdadRoleUtil.isConsultorRole(themeDisplay.getCompanyId(), themeDisplay.getUser())) {

            HttpServletRequest request = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(renderRequest));
            
            compId = request.getParameter(AdeplusDocumentaryRepositoryPortletKeys.QUERY_PARAM);
            if(compId == null || compId.isEmpty()) {
            	compId = renderRequest.getParameter(AdeplusDocumentaryRepositoryPortletKeys.QUERY_PARAM);
            }
            if (Validator.isNull(compId)) compId = ParamUtil.getString(renderRequest, AdeplusDocumentaryRepositoryPortletKeys.COMPID_PARAM);

        }
        
        if(compId.isEmpty()) {
        	List<UserCompany> companies = UserCompanyLocalServiceUtil.getCompaniesFromUser(themeDisplay.getUserId());
            UserCompany company = companies.stream().findFirst().orElse(null);
            compId = String.valueOf(company.getCompId());
        }
		
        renderRequest.setAttribute(AdeplusDocumentaryRepositoryPortletKeys.COMPID_PARAM, compId);
        
		renderRequest.setAttribute("documentsRepository", AdeplusDocumentRepositoryUtil.getFiles(themeDisplay, applicationConfiguration, parentFolderConfiguration, compId));

		super.render(renderRequest, renderResponse);
	}

}
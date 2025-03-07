package com.adeplus.liferay.portlet.company.configuration.web.portlet;

import com.adeplus.liferay.portlet.commons.web.company.AdeplusCompanyUtil;
import com.adeplus.liferay.portlet.commons.web.logo.AdeplusLogoUtil;
import com.adeplus.liferay.portlet.commons.web.user.AdeplusUserUtil;
import com.adeplus.liferay.portlet.company.configuration.web.constants.AdeplusCompanyConfigurationPortletKeys;
import com.adeplus.liferay.portlet.suite.manager.model.Comp;
import com.adeplus.liferay.portlet.suite.manager.model.CompClientApplication;
import com.adeplus.liferay.portlet.suite.manager.service.CompClientApplicationLocalServiceUtil;
import com.adeplus.liferay.portlet.suite.manager.service.CompLocalServiceUtil;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author agarciap
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.adeplus",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=false",
		"javax.portlet.display-name=AdeplusCompanyConfiguration",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + AdeplusCompanyConfigurationPortletKeys.ADEPLUSCOMPANYCONFIGURATION,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user",
		"javax.portlet.supported-public-render-parameter=userCompId"
	},
	service = Portlet.class
)
public class AdeplusCompanyConfigurationPortlet extends MVCPortlet {

	private static Log _log = LogFactoryUtil.getLog(AdeplusCompanyConfigurationPortlet.class);

	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException {

		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);

		long userCompId = ParamUtil.getLong(renderRequest, AdeplusCompanyConfigurationPortletKeys.USER_COMPANY_ID_EDIT, 0);

		List<Comp> companiesActiveFromUser = AdeplusUserUtil.getCompaniesActiveFromUser(themeDisplay.getUserId());



		if(userCompId == 0 && companiesActiveFromUser.size() > 0){
			userCompId = companiesActiveFromUser.get(0).getCompId();
		}

		renderRequest.setAttribute(AdeplusCompanyConfigurationPortletKeys.USER_COMPANY_ID_EDIT, userCompId);

		super.doView(renderRequest, renderResponse);
	}

	public void saveCompanyConfiguration(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

		UploadPortletRequest uploadRequest = PortalUtil.getUploadPortletRequest(actionRequest);

		Comp company = null;

		long companyEditId 	= ParamUtil.getLong(actionRequest,   AdeplusCompanyConfigurationPortletKeys.COMPANY_ID_EDIT, 0);
		Map<Locale, String> description = LocalizationUtil.getLocalizationMap(actionRequest, AdeplusCompanyConfigurationPortletKeys.COMPANY_DESCRIPTION);
		Map<Locale, String> agreement = LocalizationUtil.getLocalizationMap(actionRequest, AdeplusCompanyConfigurationPortletKeys.COMPANY_AGREEMENT);
		Map<Locale, String> observation = LocalizationUtil.getLocalizationMap(actionRequest, AdeplusCompanyConfigurationPortletKeys.COMPANY_OBSERVATION);

		String sourceFileName = uploadRequest.getFileName(AdeplusCompanyConfigurationPortletKeys.COMPANY_LOGO);

		File logo = (File) uploadRequest.getFile(AdeplusCompanyConfigurationPortletKeys.COMPANY_LOGO);

		if(companyEditId > 0){
			//Update company
			try {
				ServiceContext serviceContext = ServiceContextFactory.getInstance(DLFileEntry.class.getName(), actionRequest);

				company = AdeplusCompanyUtil.updateCompanyConfiguration(
						themeDisplay.getScopeGroupId(),
						themeDisplay.getUserId(),
						companyEditId,
						description,
						observation,
						agreement,
						sourceFileName,
						logo,
						serviceContext);

				List<CompClientApplication> listaCompCLient = CompClientApplicationLocalServiceUtil.getClientsByCompanyId(companyEditId);
				for(CompClientApplication compClient:listaCompCLient){
					compClient.setLogo(company.getLogo());
					CompClientApplicationLocalServiceUtil.updateCompClientApplication(compClient);
				}
			} catch (PortalException e) {
				_log.error(e);
			}

		}

	}
}
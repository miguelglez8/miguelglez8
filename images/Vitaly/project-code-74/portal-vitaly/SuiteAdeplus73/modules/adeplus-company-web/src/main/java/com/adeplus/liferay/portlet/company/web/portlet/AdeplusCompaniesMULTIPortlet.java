package com.adeplus.liferay.portlet.company.web.portlet;

import com.adeplus.liferay.portlet.commons.web.user.AdeplusUserUtil;
import com.adeplus.liferay.portlet.company.web.constants.AdeplusCompaniesPortletKeys;
import com.adeplus.liferay.portlet.suite.manager.model.Application;
import com.adeplus.liferay.portlet.suite.manager.model.Comp;
import com.adeplus.liferay.portlet.suite.manager.service.ApplicationLocalServiceUtil;
import com.adeplus.liferay.portlet.suite.manager.service.CompLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.util.ParamUtil;
import org.osgi.service.component.annotations.Component;

import javax.portlet.*;
import java.io.IOException;
import java.util.List;

/**
 * @author agarciap
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.adeplus",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=false",
		"javax.portlet.display-name=AdeplusCompaniesMULTI",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view_MULTI.jsp",
		"javax.portlet.name=" + AdeplusCompaniesPortletKeys.ADEPLUSCOMPANIESMULTI,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class AdeplusCompaniesMULTIPortlet extends MVCPortlet {
	private static Log _log = LogFactoryUtil.getLog(AdeplusUserUtil.class);
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException {

		long companyEditId 	= ParamUtil.getLong(renderRequest, AdeplusCompaniesPortletKeys.COMPANY_ID_EDIT, 0);

		List<Comp> compList = CompLocalServiceUtil.getComps(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
		List<Application> appList = ApplicationLocalServiceUtil.getApplications(QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		renderRequest.setAttribute(AdeplusCompaniesPortletKeys.COMPANY_LIST, compList);
		renderRequest.setAttribute(AdeplusCompaniesPortletKeys.APPLICATION_LIST, appList);

		renderRequest.setAttribute(AdeplusCompaniesPortletKeys.COMPANY_ID_EDIT, companyEditId);



		super.doView(renderRequest, renderResponse);
	}

	public void cancel(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {

		List<Comp> compList = CompLocalServiceUtil.getComps(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
		List<Application> appList = ApplicationLocalServiceUtil.getApplications(QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		actionRequest.setAttribute(AdeplusCompaniesPortletKeys.COMPANY_LIST, compList);
		actionRequest.setAttribute(AdeplusCompaniesPortletKeys.APPLICATION_LIST, appList);

	}

	public void search(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {

		long companyEditId 	= ParamUtil.getLong(actionRequest, AdeplusCompaniesPortletKeys.COMPANY_ID_EDIT, 0);

		_log.debug("companyEditId " + companyEditId);

		actionRequest.setAttribute(AdeplusCompaniesPortletKeys.COMPANY_ID_EDIT, companyEditId);
		actionRequest.setAttribute("multi", 1);

		actionResponse.setRenderParameter("jspPage", "/company_MULTI.jsp");
	}

}
package com.adeplus.liferay.portlet.dashboard.web.portlet;

import com.adeplus.liferay.portlet.dashboard.web.constants.AdeplusDashboardPortletKeys;
import com.adeplus.liferay.portlet.suite.manager.model.*;
import com.adeplus.liferay.portlet.suite.manager.service.*;
import com.adeplus.liferay.portlet.suite.manager.service.persistence.UserApplicationClientPK;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import java.io.IOException;
import java.util.ArrayList;
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
		"javax.portlet.display-name=AdeplusDashboard",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + AdeplusDashboardPortletKeys.ADEPLUSDASHBOARD,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user",
		"javax.portlet.supported-public-render-parameter=" + AdeplusDashboardPortletKeys.USER_COMP_ID
	},
	service = Portlet.class
)
public class AdeplusDashboardPortlet extends MVCPortlet {
	private static Log _log = LogFactoryUtil.getLog(AdeplusDashboardPortlet.class);
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException {

		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);

		long userCompId = ParamUtil.getLong(renderRequest, AdeplusDashboardPortletKeys.USER_COMP_ID, 0);
		UserCompany defaultCompany = UserCompanyLocalServiceUtil.getUserDefaultCompany(themeDisplay.getUserId());
		List<Long> listaApps = new ArrayList<Long>();
		long clientId = 0;
		long contractId = 0;
		Comp comp;
		if(Validator.isNotNull(defaultCompany)){
			userCompId = defaultCompany.getCompId();
			clientId = defaultCompany.getDefaultClientId();
			contractId = defaultCompany.getDefaultContractId();
		}
		try {
			comp = CompLocalServiceUtil.getComp(userCompId);
		}catch (Exception e){
			comp=null;
		}
		_log.info(comp);
		if(Validator.isNotNull(comp)){
			if (clientId>0 && contractId>0) {
				//Obtenemos el objeto del cliente
				UserApplicationClient userApp = obtenerUsuario(themeDisplay.getUserId(),userCompId,clientId,contractId);
				if(Validator.isNotNull(userApp)){
					listaApps.addAll(getAppsUserClient(userApp));

				}
			}else{
				List<UserApplicationClient> usersApps=UserApplicationClientLocalServiceUtil.getApplicationsFromUser(themeDisplay.getUserId(),userCompId);
				if(Validator.isNotNull(usersApps) && usersApps.size()>0 ) {
					UserApplicationClient userApp = usersApps.get(0);

					if (Validator.isNotNull(userApp)) {
						listaApps.addAll(getAppsUserClient(userApp));
					}
				}
			}
		}
		List<Application> appList = ApplicationLocalServiceUtil.getApplications(QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		String infoURL = PrefsPropsUtil.getString(AdeplusDashboardPortletKeys.PROPERTY_GET_INFO_CALL_TO_ACTION, "#");

		renderRequest.setAttribute(AdeplusDashboardPortletKeys.USER_APPS, listaApps);
		renderRequest.setAttribute(AdeplusDashboardPortletKeys.USER_COMP, comp);
		renderRequest.setAttribute(AdeplusDashboardPortletKeys.APPLICATIONS_LIST, appList);
		renderRequest.setAttribute(AdeplusDashboardPortletKeys.INFO_URL, infoURL);

		super.doView(renderRequest, renderResponse);
	}

	private UserApplicationClient obtenerUsuario(long userId,long compId, long clientId,long contractId)  {
		UserApplicationClient userApplication=null;
		UserApplicationClientPK appPk = new UserApplicationClientPK();
		long idEmpresa = CompClientApplicationLocalServiceUtil.getClientByCompAndClientAndContract(compId,clientId,contractId).getEmpresaId();

		appPk.setEmpresaId(idEmpresa);
		appPk.setUserId(userId);
		try {
			userApplication = UserApplicationClientLocalServiceUtil.getUserApplicationClient(appPk);
		} catch (PortalException e) {
			//No existen las aplicaciones
		}
		return userApplication;
	}

	private	List<Long> getAppsUserClient(UserApplicationClient cp){
		List<Long> listaApps=new ArrayList<Long>();

		try {
			JSONObject jsonApp = null;
			String value_attr = "";
			JSONArray jsonClientContratApps = JSONFactoryUtil.createJSONArray(cp.getApplicationId());
			for (Object objApp : jsonClientContratApps) {
				jsonApp = (JSONObject) objApp;
				if (!value_attr.isEmpty()) value_attr += ",";
				value_attr += jsonApp.getString("appId");

				//Añadimos todas las aplicaciones a la lista
				listaApps.add(ApplicationLocalServiceUtil.getApplication(Long.parseLong(jsonApp.getString("appId"))).getApplicationId());
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return listaApps;
	}

}
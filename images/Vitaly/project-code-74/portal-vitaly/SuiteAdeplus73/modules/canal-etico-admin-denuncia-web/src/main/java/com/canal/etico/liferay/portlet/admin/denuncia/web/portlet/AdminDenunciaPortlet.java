package com.canal.etico.liferay.portlet.admin.denuncia.web.portlet;

import com.canal.etico.liferay.portlet.admin.denuncia.web.constants.AdminDenunciaPortletKeys;
import com.canal.etico.liferay.portlet.canal.manager.model.Categoria;
import com.canal.etico.liferay.portlet.canal.manager.model.Comp;
import com.canal.etico.liferay.portlet.canal.manager.model.Denuncia;
import com.canal.etico.liferay.portlet.canal.manager.model.UserCompany;
import com.canal.etico.liferay.portlet.canal.manager.service.CategoriaLocalServiceUtil;
import com.canal.etico.liferay.portlet.canal.manager.service.DenunciaLocalServiceUtil;
import com.canal.etico.liferay.portlet.canal.manager.service.UserCompanyLocalServiceUtil;
import com.canal.etico.liferay.portlet.commons.web.user.CanalEticoUserUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.*;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * @author agarciap
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.canal.etico",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=false",
		"javax.portlet.display-name=AdminDenuncia",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + AdminDenunciaPortletKeys.ADMINDENUNCIA,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user",
		"javax.portlet.supported-public-render-parameter=userCompId"
	},
	service = Portlet.class
)
public class AdminDenunciaPortlet extends MVCPortlet {

	private static Log _log = LogFactoryUtil.getLog(AdminDenunciaPortlet.class);

	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");


	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException {

		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);

		dateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Madrid"));

		Comp companyFromUser = CanalEticoUserUtil.getUserDefaultCompanyInCanalEtico(themeDisplay.getUserId());

		renderRequest.setAttribute(AdminDenunciaPortletKeys.HAS_PERMISSION_IN_COMP, false);

		if(Validator.isNotNull(companyFromUser)) {
			UserCompany companyFromUserCompany = UserCompanyLocalServiceUtil.getCompanyFromUserCompany(themeDisplay.getUserId(), companyFromUser.getCompId());

			//User is in this comp.
			if (Validator.isNotNull(companyFromUserCompany)) {
				List<Denuncia> denuncias = DenunciaLocalServiceUtil.getDenunciasFromCompany(companyFromUser.getCompId());
				renderRequest.setAttribute(AdminDenunciaPortletKeys.DENUNCIAS, denuncias);

				List<Categoria> allCategoriasFromCompany = CategoriaLocalServiceUtil.getAllCategoriasFromCompany(companyFromUser.getCompId());
				renderRequest.setAttribute(AdminDenunciaPortletKeys.CATEGORIAS, allCategoriasFromCompany);

				renderRequest.setAttribute(AdminDenunciaPortletKeys.HAS_PERMISSION_IN_COMP, true);

			}
		}

		String startDate= ParamUtil.getString(renderRequest, AdminDenunciaPortletKeys.START_DATE, "");
		String endDate 	= ParamUtil.getString(renderRequest, AdminDenunciaPortletKeys.END_DATE, "");

		Date stDate = null;
		Date enDate = null;

		try {
			if(!Validator.isBlank(startDate)){
				stDate = dateFormat.parse(startDate);
			}else{
				Calendar calStart = Calendar.getInstance();
				calStart.set(Calendar.DAY_OF_MONTH, 1);
				stDate = calStart.getTime();
			}

			if(!Validator.isBlank(endDate)){
				enDate = dateFormat.parse(endDate);
			}else{
				Calendar calEnd = Calendar.getInstance();
				enDate = calEnd.getTime();
			}
		} catch (ParseException e) {
			_log.error(e);
		}

		renderRequest.setAttribute(AdminDenunciaPortletKeys.START_DATE, dateFormat.format(stDate));
		renderRequest.setAttribute(AdminDenunciaPortletKeys.END_DATE, dateFormat.format(enDate));

		super.doView(renderRequest, renderResponse);
	}

	public void search(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {

		long denunciaId = ParamUtil.getLong(actionRequest, AdminDenunciaPortletKeys.DENUNCIA_ID, 0);

		actionRequest.setAttribute(AdminDenunciaPortletKeys.DENUNCIA_ID, denunciaId);

		actionResponse.setRenderParameter("jspPage", "/denuncia.jsp");
	}

}
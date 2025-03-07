package com.canal.etico.liferay.portlet.categoria.web.portlet;

/*import com.canal.etico.liferay.portlet.canal.manager.model.Categoria;
import com.canal.etico.liferay.portlet.canal.manager.model.Comp;
import com.canal.etico.liferay.portlet.canal.manager.service.CategoriaLocalServiceUtil;
import com.canal.etico.liferay.portlet.canal.manager.service.UserCompanyLocalServiceUtil;*/
import com.canal.etico.liferay.portlet.categoria.web.constants.CategoriasPortletKeys;
import com.canal.etico.liferay.portlet.commons.web.role.CanalEticoRoleUtil;
import com.canal.etico.liferay.portlet.commons.web.user.CanalEticoUserUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author agarciap
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.canal.etico",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=false",
		"javax.portlet.display-name=Categorias_v2",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + CategoriasPortletKeys.CATEGORIAS,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class CategoriasPortlet extends MVCPortlet {

	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException {

		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);

/*		long companyIdFromUser = UserCompanyLocalServiceUtil.getCompanyIdFromUser(themeDisplay.getUserId());

		Comp companyFromUser = CanalEticoUserUtil.getUserDefaultCompanyInCanalEtico(themeDisplay.getUserId());

		if(Validator.isNotNull(companyFromUser)){
			companyIdFromUser = companyFromUser.getCompId();
		}

		boolean omniAdminRole = CanalEticoRoleUtil.isOmniAdminRole(themeDisplay.getCompanyId(), themeDisplay.getUser());
		if(omniAdminRole){
			companyIdFromUser = 0;
		}

		List<Categoria> list = CategoriaLocalServiceUtil.getAllCategoriasFromCompany(companyIdFromUser);

		renderRequest.setAttribute(CategoriasPortletKeys.COMPANY_ID, companyIdFromUser);
		renderRequest.setAttribute(CategoriasPortletKeys.CATEGORIAS_LIST, list);
*/
		super.doView(renderRequest, renderResponse);
	}
}
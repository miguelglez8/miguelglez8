package canal.etico.selector.web.portlet;

import canal.etico.selector.web.constants.CanalEticoSelectorWebPortletKeys;

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

import com.canal.etico.liferay.portlet.canal.manager.model.Comp;
import com.canal.etico.liferay.portlet.canal.manager.model.UserCompany;
import com.canal.etico.liferay.portlet.canal.manager.service.CompLocalServiceUtil;
import com.canal.etico.liferay.portlet.canal.manager.service.UserCompanyLocalServiceUtil;
/**
 * @author ymusalaeva
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.canal.etico",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=CanalEticoSelectorWeb",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + CanalEticoSelectorWebPortletKeys.CANALETICOSELECTORWEB,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class CanalEticoSelectorWebPortlet extends MVCPortlet {
	
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException {

		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);

		String userName = themeDisplay.getUser().getFullName();
		long companyIdFromUser = UserCompanyLocalServiceUtil.getCompanyIdFromUser(themeDisplay.getUserId());
		Comp comp = null;
		String companyName="";
		if(companyIdFromUser > 0) {
			comp = CompLocalServiceUtil.fetchComp(companyIdFromUser);
			companyName = comp.getName();
		}
		
		DLFileEntry dlFileEntry = DLFileEntryLocalServiceUtil.fetchDLFileEntry(Long.valueOf(comp.getLogo()));
		String url = themeDisplay.getPortalURL() + themeDisplay.getPathContext() + "/documents/" + themeDisplay.getScopeGroupId() + "/" +
				dlFileEntry.getFolderId() +  "/" +dlFileEntry.getTitle() ;
        
		renderRequest.setAttribute(CanalEticoSelectorWebPortletKeys.USER_COMP_ID, companyIdFromUser);
		renderRequest.setAttribute(CanalEticoSelectorWebPortletKeys.USER_NAME, userName);
		renderRequest.setAttribute(CanalEticoSelectorWebPortletKeys.USER_LOGO_URL, url);
		renderRequest.setAttribute(CanalEticoSelectorWebPortletKeys.USER_COMP_NAME, companyName);
		
		super.doView(renderRequest, renderResponse);
	}
}
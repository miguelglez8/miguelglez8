package com.plan.igualdad.liferay.portlet.admin.empresas.web.portlet.command;

import com.plan.igualdad.liferay.portlet.admin.empresas.web.constants.AdminEmpresasPortletKeys;
import com.plan.igualdad.liferay.portlet.commons.web.estado.EstadoUtils;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

@Component(
        immediate=true,
        property= {
                "javax.portlet.name=" + AdminEmpresasPortletKeys.ADMINEMPRESAS,
                "mvc.command.name=/"
        },
        service = MVCRenderCommand.class
)
public class GoListEmpresasRenderCommand implements MVCRenderCommand {

    @Override
    public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {

        ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
        ResourceBundle bundle = ResourceBundle.getBundle("content/Language", themeDisplay.getUser().getLocale());

        long consultorRoleId = Long.parseLong(renderRequest.getPreferences().getValue(AdminEmpresasPortletKeys.CONSULTOR_ROL, "0"));
        long adminRoleId = Long.parseLong(renderRequest.getPreferences().getValue(AdminEmpresasPortletKeys.ADMIN_ROL, "0"));

        List<User> userList = UserLocalServiceUtil.getRoleUsers(consultorRoleId);

        String url = LanguageUtil.get(bundle, "company.view.legislaciones.url");
        String legislacionesUrl = themeDisplay.getScopeGroup().getDisplayURL(themeDisplay, false) + url;
        
		renderRequest.setAttribute(AdminEmpresasPortletKeys.ESTADOS_LIST, EstadoUtils.getEstados());
        renderRequest.setAttribute(AdminEmpresasPortletKeys.LEGISLACIONES_URL, legislacionesUrl);
        renderRequest.setAttribute(AdminEmpresasPortletKeys.USER_LIST, userList);

        if(Arrays.stream(themeDisplay.getUser().getRoleIds()).anyMatch(userRoleId -> userRoleId == adminRoleId)) {
            return "/view_admin.jsp";
        }

        return "/view.jsp";
    }
}

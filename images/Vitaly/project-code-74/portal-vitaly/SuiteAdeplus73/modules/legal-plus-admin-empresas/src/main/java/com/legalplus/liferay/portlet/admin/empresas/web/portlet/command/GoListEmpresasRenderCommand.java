package com.legalplus.liferay.portlet.admin.empresas.web.portlet.command;

import com.adeplus.liferay.portlet.suite.manager.model.ApplicationLicense;
import com.adeplus.liferay.portlet.suite.manager.service.*;
import com.legalplus.liferay.portlet.admin.empresas.web.constants.AdminEmpresasPortletKeys;
import com.legalplus.liferay.portlet.commons.web.role.LegalplusRoleUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
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

    private static Log _log = LogFactoryUtil.getLog(GoListEmpresasRenderCommand.class);

    @Override
    public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
        try {

            ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
            ResourceBundle bundle = ResourceBundle.getBundle("content/Language", themeDisplay.getUser().getLocale());

            Role rol = RoleLocalServiceUtil.getRole(themeDisplay.getCompanyId(), LegalplusRoleUtil.getConsultorRol());
            List<User> userList = UserLocalServiceUtil.getRoleUsers(rol.getRoleId());
            List<ApplicationLicense> licenseList = ApplicationLicenseLocalServiceUtil.getApplicationLicenses(QueryUtil.ALL_POS, QueryUtil.ALL_POS);

            String url = LanguageUtil.get(bundle, "company.view.legislaciones.url");
            String legislacionesUrl = themeDisplay.getScopeGroup().getDisplayURL(themeDisplay, false) + url;

            renderRequest.setAttribute(AdminEmpresasPortletKeys.LEGISLACIONES_URL, legislacionesUrl);
            renderRequest.setAttribute(AdminEmpresasPortletKeys.LICENSE_LIST, licenseList);
            renderRequest.setAttribute(AdminEmpresasPortletKeys.USER_LIST, userList);

            return "/view.jsp";
        } catch (PortalException e) {
            _log.error(e, e);
            throw  new PortletException(e);
        }
    }
}

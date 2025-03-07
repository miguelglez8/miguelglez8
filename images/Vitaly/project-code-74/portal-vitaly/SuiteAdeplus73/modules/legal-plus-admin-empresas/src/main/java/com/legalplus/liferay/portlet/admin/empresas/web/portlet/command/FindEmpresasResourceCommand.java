package com.legalplus.liferay.portlet.admin.empresas.web.portlet.command;

import com.legalplus.liferay.portlet.admin.empresas.web.constants.AdminEmpresasPortletKeys;
import com.legalplus.liferay.portlet.commons.web.role.LegalplusRoleUtil;
import com.legalplus.liferay.portlet.legalplus.manager.service.ConsultorCompanyLocalServiceUtil;
import com.legalplus.liferay.portlet.legalplus.manager.service.LegislacionLocalServiceUtil;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

@Component(
        immediate=true,
        property= {
                "javax.portlet.name=" + AdminEmpresasPortletKeys.ADMINEMPRESAS,
                "mvc.command.name=buscarEmpresas"
        },
        service = MVCResourceCommand.class
)
public class FindEmpresasResourceCommand extends BaseMVCResourceCommand {

    private static Log _log = LogFactoryUtil.getLog(FindEmpresasResourceCommand.class);

    @Override
    protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
        ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
        Long userId = themeDisplay.getUserId();

        if (LegalplusRoleUtil.isAdministradorRole(themeDisplay.getCompanyId(), themeDisplay.getUser())) {
            userId = 0L;
        }

        String resultados= ConsultorCompanyLocalServiceUtil.getCompByUserId(userId);
        JSONObject json = JSONFactoryUtil.createJSONObject(resultados);

        resourceResponse.setContentType("application/json");
        resourceResponse.setCharacterEncoding("UTF-8");
        resourceResponse.getWriter().write(json.toString());
    }

}

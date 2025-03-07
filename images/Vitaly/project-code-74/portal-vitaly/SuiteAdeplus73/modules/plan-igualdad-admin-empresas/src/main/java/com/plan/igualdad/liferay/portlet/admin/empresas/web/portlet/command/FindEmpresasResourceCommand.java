package com.plan.igualdad.liferay.portlet.admin.empresas.web.portlet.command;

import com.legalplus.liferay.portlet.legalplus.manager.service.ConsultorCompanyLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.plan.igualdad.liferay.portlet.admin.empresas.web.constants.AdminEmpresasPortletKeys;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;
import com.plan.igualdad.liferay.portlet.commons.web.role.PlanIgualdadRoleUtil;

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

    @Override
    protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
        ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);

        String appAlias = resourceRequest.getPreferences().getValue(AdminEmpresasPortletKeys.APP_ALIAS, StringPool.BLANK);
        Long userId = themeDisplay.getUserId();

        if (PlanIgualdadRoleUtil.isAdministradorRole(themeDisplay.getCompanyId(), themeDisplay.getUser())) {
            userId = 0L;
        }

        String resultados= ConsultorCompanyLocalServiceUtil.getCompsByUserIdAndApp(userId, appAlias);
        resultados.contains("igualdad");
        JSONObject json = JSONFactoryUtil.createJSONObject(resultados);
        

        resourceResponse.setContentType("application/json");
        resourceResponse.setCharacterEncoding("UTF-8");
        resourceResponse.getWriter().write(json.toString());
    }

}

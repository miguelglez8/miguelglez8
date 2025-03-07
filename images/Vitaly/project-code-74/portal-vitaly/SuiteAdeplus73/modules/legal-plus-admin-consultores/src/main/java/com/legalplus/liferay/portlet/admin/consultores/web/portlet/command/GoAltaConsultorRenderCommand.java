package com.legalplus.liferay.portlet.admin.consultores.web.portlet.command;


import com.adeplus.liferay.portlet.suite.manager.model.UserCompany;
import com.adeplus.liferay.portlet.suite.manager.service.UserCompanyLocalServiceUtil;
import com.legalplus.liferay.portlet.admin.consultores.web.constants.AdminConsultoresPortletKeys;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import org.osgi.service.component.annotations.Component;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import java.util.ArrayList;
import java.util.List;

@Component(
        immediate=true,
        property= {
                "javax.portlet.name=" + AdminConsultoresPortletKeys.ADMINCONSULTORES,
                "mvc.command.name=goAltaConsultor"
        },
        service = MVCRenderCommand.class
)
public class GoAltaConsultorRenderCommand implements MVCRenderCommand {

    @Override
    public String render(RenderRequest renderRequest, RenderResponse renderResponse) {

        String rol = renderRequest.getPreferences().getValue(AdminConsultoresPortletKeys.CONSULTOR_ROL, StringPool.BLANK);
        String empresa = renderRequest.getPreferences().getValue(AdminConsultoresPortletKeys.CONSULTOR_EMPRESA, StringPool.BLANK);

        List<User> consultores = UserLocalServiceUtil.getRoleUsers(Long.parseLong(rol));
        List<UserCompany> companyUsers = UserCompanyLocalServiceUtil.getUsersFromCompany(Long.parseLong(empresa));
        List<UserCompany> result = new ArrayList<>();

        for (UserCompany companyUser : companyUsers) {
            if (!consultores.stream().anyMatch(consultor -> consultor.getUserId() == companyUser.getUserId())) {
                result.add(companyUser);
            }
        }

        renderRequest.setAttribute(AdminConsultoresPortletKeys.USER_LIST, result);

        return "/user.jsp";
    }

}

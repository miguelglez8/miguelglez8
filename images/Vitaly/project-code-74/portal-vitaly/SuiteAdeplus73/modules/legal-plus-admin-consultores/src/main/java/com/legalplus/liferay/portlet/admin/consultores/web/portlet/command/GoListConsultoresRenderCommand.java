package com.legalplus.liferay.portlet.admin.consultores.web.portlet.command;

import com.legalplus.liferay.portlet.admin.consultores.web.constants.AdminConsultoresPortletKeys;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.Validator;
import org.osgi.service.component.annotations.Component;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import java.util.List;

@Component(
        immediate=true,
        property= {
                "javax.portlet.name=" + AdminConsultoresPortletKeys.ADMINCONSULTORES,
                "mvc.command.name=/"
        },
        service = MVCRenderCommand.class
)
public class GoListConsultoresRenderCommand implements MVCRenderCommand {

        @Override
        public String render(RenderRequest renderRequest, RenderResponse renderResponse) {

                String rolConsultor = renderRequest.getPreferences().getValue(AdminConsultoresPortletKeys.CONSULTOR_ROL, StringPool.BLANK);
                String rolCliente = renderRequest.getPreferences().getValue(AdminConsultoresPortletKeys.CLIENTE_ROL, StringPool.BLANK);
                String empresa = renderRequest.getPreferences().getValue(AdminConsultoresPortletKeys.CONSULTOR_EMPRESA, StringPool.BLANK);

                if (Validator.isBlank(rolConsultor) || Validator.isBlank(rolCliente) || Validator.isBlank(empresa)) return "/error.jsp";

                List<User> userList = UserLocalServiceUtil.getRoleUsers(Long.parseLong(rolConsultor));
                renderRequest.setAttribute(AdminConsultoresPortletKeys.USER_LIST, userList);

                return "/view.jsp";
        }
}

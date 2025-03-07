package com.legalplus.liferay.portlet.admin.consultores.web.portlet.command;

import com.legalplus.liferay.portlet.admin.consultores.web.constants.AdminConsultoresPortletKeys;
import com.legalplus.liferay.portlet.commons.web.role.LegalplusRoleUtil;
import com.legalplus.liferay.portlet.legalplus.manager.model.ConsultorCompany;
import com.legalplus.liferay.portlet.legalplus.manager.service.ConsultorCompanyLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import java.util.List;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + AdminConsultoresPortletKeys.ADMINCONSULTORES,
                "mvc.command.name=/consultor/delete"
        },
        service = MVCActionCommand.class
)
public class DeleteConsultorActionCommand implements MVCActionCommand {

    private static Log _log = LogFactoryUtil.getLog(DeleteConsultorActionCommand.class);

    @Override
    public boolean processAction(ActionRequest actionRequest, ActionResponse actionResponse) throws PortletException {
        try {
            Long userDeleteId = ParamUtil.getLong(actionRequest, AdminConsultoresPortletKeys.CONSULTOR_ID, 0);

            User user = UserLocalServiceUtil.fetchUser(userDeleteId);
            String rol = actionRequest.getPreferences().getValue(AdminConsultoresPortletKeys.CONSULTOR_ROL, StringPool.BLANK);
            if (Validator.isNotNull(user)) {

                    List<ConsultorCompany> companies = ConsultorCompanyLocalServiceUtil.fetchByUserId(userDeleteId);
                    for (ConsultorCompany consultor : companies) {
                        ConsultorCompanyLocalServiceUtil.deleteConsultorCompany(consultor);
                    }

                    RoleLocalServiceUtil.deleteUserRole(user.getUserId(), Long.parseLong(rol));
            }

            SessionMessages.add(actionRequest, "consultor-delete-success");

            return false;
        } catch (Exception e) {
            _log.error(e, e);
            throw  new PortletException(e);
        }
    }

}

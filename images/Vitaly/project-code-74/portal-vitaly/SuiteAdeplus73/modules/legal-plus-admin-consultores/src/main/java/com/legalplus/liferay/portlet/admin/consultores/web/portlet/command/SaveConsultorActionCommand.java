package com.legalplus.liferay.portlet.admin.consultores.web.portlet.command;

import com.adeplus.liferay.portlet.commons.web.user.AdeplusUserUtil;
import com.legalplus.liferay.portlet.admin.consultores.web.constants.AdminConsultoresPortletKeys;
import com.legalplus.liferay.portlet.mailing.web.mail.ConsultorMailing;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.*;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + AdminConsultoresPortletKeys.ADMINCONSULTORES,
                "mvc.command.name=/consultor/save"
        },
        service = MVCActionCommand.class
)
public class SaveConsultorActionCommand implements MVCActionCommand {

    private static final Log _log = LogFactoryUtil.getLog(SaveConsultorActionCommand.class);

    @Override
    public boolean processAction(ActionRequest actionRequest, ActionResponse actionResponse) throws PortletException {
        try {

            String rolConsultor = actionRequest.getPreferences().getValue(AdminConsultoresPortletKeys.CONSULTOR_ROL, StringPool.BLANK);
            String rolCliente = actionRequest.getPreferences().getValue(AdminConsultoresPortletKeys.CLIENTE_ROL, StringPool.BLANK);
            long userId = ParamUtil.getLong(actionRequest, AdminConsultoresPortletKeys.CONSULTOR_ID, 0);

            User user = UserLocalServiceUtil.fetchUser(userId);
            RoleLocalServiceUtil.addUserRole(user.getUserId(), Long.parseLong(rolConsultor));
            RoleLocalServiceUtil.deleteUserRole(user.getUserId(), Long.parseLong(rolCliente));

            ConsultorMailing.altaConsultor(
                    user.getEmailAddress(),
                    user,
                    user.getFullName(),
                    user.getEmailAddress());

            SessionMessages.add(actionRequest, "consultor-save-success");

            return false;
        } catch (Exception e) {
           _log.error(e, e);
           throw  new PortletException(e);
        }
    }

}

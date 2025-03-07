package com.adeplus.liferay.portlet.application.web.action;

import com.adeplus.liferay.portlet.application.web.constants.AdeplusApplicationsPortletKeys;
import com.adeplus.liferay.portlet.suite.manager.model.Role;
import com.adeplus.liferay.portlet.suite.manager.service.RoleLocalServiceUtil;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + AdeplusApplicationsPortletKeys.ADEPLUSAPPLICATIONS,
                "mvc.command.name=/role/save"
        },
        service = MVCActionCommand.class
)
public class SaveRoleMCVActionCommand implements MVCActionCommand {

    private static Log _log = LogFactoryUtil.getLog(SaveRoleMCVActionCommand.class);

    @Override
    public boolean processAction(ActionRequest actionRequest, ActionResponse actionResponse) throws PortletException {

        long roleEditId = ParamUtil.getLong(actionRequest, AdeplusApplicationsPortletKeys.ROLE, 0);
        long applicationId = ParamUtil.getLong(actionRequest, AdeplusApplicationsPortletKeys.APPLICATION_ID_EDIT, 0);

        String name = ParamUtil.getString(actionRequest, AdeplusApplicationsPortletKeys.ROLE_NAME, "");
        String alias = ParamUtil.getString(actionRequest, AdeplusApplicationsPortletKeys.ROLE_ALIAS, "");

        Role role = null;

        if(roleEditId == 0){
            //Create role
            role = RoleLocalServiceUtil.createRole(CounterLocalServiceUtil.increment(Role.class.getName()));
        } else {
            //Edit role
            role = RoleLocalServiceUtil.fetchRole(roleEditId);
        }

        if(Validator.isNotNull(role)) {

            role.setApplicationId(applicationId);
            role.setName(name);
            role.setAlias(alias);

            RoleLocalServiceUtil.updateRole(role);
        }

        actionResponse.setRenderParameter(AdeplusApplicationsPortletKeys.APPLICATION_ID_EDIT, String.valueOf(applicationId));
        actionResponse.setRenderParameter("jspPage", "/application.jsp");

        return true;
    }

}

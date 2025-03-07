package com.adeplus.liferay.portlet.company.configuration.web.action;

import com.adeplus.liferay.portlet.commons.web.company.AdeplusCompanyUtil;
import com.adeplus.liferay.portlet.company.configuration.web.constants.AdeplusCompanyConfigurationPortletKeys;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + AdeplusCompanyConfigurationPortletKeys.ADEPLUSCOMPANYCONFIGURATION,
                "mvc.command.name=/company/delete/logo"
        },
        service = MVCActionCommand.class
)
public class DeleteLogoMVCActionCommand implements MVCActionCommand {

    private static Log _log = LogFactoryUtil.getLog(DeleteLogoMVCActionCommand.class);

    @Override
    public boolean processAction(ActionRequest actionRequest, ActionResponse actionResponse) throws PortletException {

        long userCompId = ParamUtil.getLong(actionRequest, AdeplusCompanyConfigurationPortletKeys.USER_COMPANY_ID_EDIT, 0);

        AdeplusCompanyUtil.deleteLogoCompany(userCompId);

        return true;
    }
}

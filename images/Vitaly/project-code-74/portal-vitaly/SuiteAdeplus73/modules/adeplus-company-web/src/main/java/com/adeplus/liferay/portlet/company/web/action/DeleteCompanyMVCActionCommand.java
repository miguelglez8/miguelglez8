package com.adeplus.liferay.portlet.company.web.action;

import com.adeplus.liferay.portlet.commons.web.company.AdeplusCompanyUtil;
import com.adeplus.liferay.portlet.company.web.constants.AdeplusCompaniesPortletKeys;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + AdeplusCompaniesPortletKeys.ADEPLUSCOMPANIES,
                "mvc.command.name=/company/delete"
        },
        service = MVCActionCommand.class
)
public class DeleteCompanyMVCActionCommand implements MVCActionCommand {

    @Override
    public boolean processAction(ActionRequest actionRequest, ActionResponse actionResponse) throws PortletException {

        long compId = ParamUtil.getLong(actionRequest, AdeplusCompaniesPortletKeys.COMPANY_ID, 0);

        AdeplusCompanyUtil.deleteCompany(compId);

        return true;
    }
}

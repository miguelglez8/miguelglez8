package com.adeplus.liferay.portlet.application.web.action;

import com.adeplus.liferay.portlet.application.web.constants.AdeplusApplicationsPortletKeys;
import com.adeplus.liferay.portlet.suite.manager.model.ApplicationLicense;
import com.adeplus.liferay.portlet.suite.manager.service.ApplicationLicenseLocalServiceUtil;
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
                "mvc.command.name=/license/save"
        },
        service = MVCActionCommand.class
)
public class SaveLicenseMCVActionCommand implements MVCActionCommand {

    private static Log _log = LogFactoryUtil.getLog(SaveLicenseMCVActionCommand.class);

    @Override
    public boolean processAction(ActionRequest actionRequest, ActionResponse actionResponse) throws PortletException {

        long licenseEditId = ParamUtil.getLong(actionRequest, AdeplusApplicationsPortletKeys.LICENSE, 0);
        long applicationId = ParamUtil.getLong(actionRequest, AdeplusApplicationsPortletKeys.APPLICATION_ID_EDIT, 0);

        String name = ParamUtil.getString(actionRequest, AdeplusApplicationsPortletKeys.LICENSE_NAME, "");
        String key = ParamUtil.getString(actionRequest, AdeplusApplicationsPortletKeys.LICENSE_KEY, "");

        ApplicationLicense license = null;

        if(licenseEditId == 0){
            //Create
            license = ApplicationLicenseLocalServiceUtil.createApplicationLicense(CounterLocalServiceUtil.increment(ApplicationLicense.class.getName()));
        } else {
            //Edit
            license = ApplicationLicenseLocalServiceUtil.fetchApplicationLicense(licenseEditId);
        }

        if(Validator.isNotNull(license)) {

            license.setApplicationId(applicationId);
            license.setName(name);
            license.setPermissionRoleKey(key);

            ApplicationLicenseLocalServiceUtil.updateApplicationLicense(license);
        }

        actionResponse.setRenderParameter(AdeplusApplicationsPortletKeys.APPLICATION_ID_EDIT, String.valueOf(applicationId));
        actionResponse.setRenderParameter("jspPage", "/application.jsp");

        return true;
    }

}

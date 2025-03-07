package com.legalplus.liferay.portlet.synchronization.web.listener;

import com.adeplus.liferay.portlet.suite.manager.model.Application;
import com.adeplus.liferay.portlet.suite.manager.model.ApplicationLicense;
import com.adeplus.liferay.portlet.suite.manager.model.CompApplication;
import com.adeplus.liferay.portlet.suite.manager.model.UserApplication;
import com.adeplus.liferay.portlet.suite.manager.service.ApplicationLicenseLocalServiceUtil;
import com.adeplus.liferay.portlet.suite.manager.service.ApplicationLocalServiceUtil;
import com.adeplus.liferay.portlet.suite.manager.service.CompApplicationLocalServiceUtil;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.*;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.Validator;
import org.osgi.service.component.annotations.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component(
        immediate = true,
        service = ModelListener.class
)
public class SuiteUserApplicationModelListener extends BaseModelListener<UserApplication> {

    private static final Log log = LogFactoryUtil.getLog(SuiteUserApplicationModelListener.class);

    static SimpleDateFormat dateFormatLog = new SimpleDateFormat("yyyy-dd-MM HH:mm:ss.SSS");

    @Override
    public void onAfterCreate(UserApplication userApplication) throws ModelListenerException {

        log.debug(dateFormatLog.format(new Date()) + " onAfterCreate user " + userApplication.getUserId() + " app " + userApplication.getApplicationId());

        updateLicenseToUser(userApplication);

        super.onAfterCreate(userApplication);
    }

    @Override
    public void onAfterUpdate(UserApplication userApplication) throws ModelListenerException {

        log.debug(dateFormatLog.format(new Date()) + " onAfterUpdate user " + userApplication.getUserId() + " app " + userApplication.getApplicationId());

        updateLicenseToUser(userApplication);

        super.onAfterUpdate(userApplication);
    }

    @Override
    public void onAfterRemove(UserApplication userApplication) throws ModelListenerException {

        log.debug(dateFormatLog.format(new Date()) + " onAfterRemove user " + userApplication.getUserId() + " app " + userApplication.getApplicationId());

        updateLicenseToUser(userApplication);

        super.onAfterRemove(userApplication);
    }

    private void updateLicenseToUser(UserApplication userApplication){

        //Get license for application
        CompApplication companyApplication = CompApplicationLocalServiceUtil.getCompanyApplication(userApplication.getCompId(), userApplication.getApplicationId());

        if(Validator.isNotNull(companyApplication)) {

            Application application = ApplicationLocalServiceUtil.fetchApplication(userApplication.getApplicationId());
            if(!"LEGAL_PLUS".equals(application.getAlias())){
                return;
            }

            //Get user
            User userUpdate = UserLocalServiceUtil.fetchUser(userApplication.getUserId());

            //Get group
            Group group = null;
            try {
                group = GroupLocalServiceUtil.getFriendlyURLGroup(PortalUtil.getDefaultCompanyId(), "/legal");
            } catch (PortalException e) {
                e.printStackTrace();
            }

            if (Validator.isNotNull(userUpdate) && Validator.isNotNull(group)) {

                //Get all applications
                List<ApplicationLicense> byApplicationId = ApplicationLicenseLocalServiceUtil.findByApplicationId(userApplication.getApplicationId());
                for (ApplicationLicense al : byApplicationId) {

                    String roleName = PrefsPropsUtil.getString(al.getPermissionRoleKey());
                    Role role = null;
                    try {
                        role = RoleLocalServiceUtil.getRole(PortalUtil.getDefaultCompanyId(), roleName);
                    } catch (PortalException e) {
                        log.error("Error getting rol name: " + roleName, e);
                    }

                    if (Validator.isNotNull(role)) {
                        //Add permission
                        if (al.getLicenseId() == companyApplication.getLicenseId()) {
                            GroupLocalServiceUtil.addUserGroup(userUpdate.getUserId(), group.getGroupId());
                            UserLocalServiceUtil.addRoleUser(role.getRoleId(), userUpdate);
                            UserLocalServiceUtil.updateUser(userUpdate);
                        }
                    }
                }

            }
        }
    }

}

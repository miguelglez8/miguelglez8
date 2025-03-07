package com.adeplus.liferay.portlet.scheduler.web.scheduler;

import com.adeplus.liferay.portlet.commons.web.audit.AdeplusAuditUtil;
import com.adeplus.liferay.portlet.commons.web.constants.AdeplusAuditPortletKeys;
import com.adeplus.liferay.portlet.commons.web.keycloak.Realm;
import com.adeplus.liferay.portlet.mailing.web.mail.UserMailing;
import com.adeplus.liferay.portlet.suite.manager.model.Comp;
import com.adeplus.liferay.portlet.suite.manager.model.UserCompany;
import com.adeplus.liferay.portlet.suite.manager.service.CompLocalServiceUtil;
import com.adeplus.liferay.portlet.suite.manager.service.UserCompanyLocalServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.scheduler.*;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import org.osgi.service.component.annotations.*;

import java.text.SimpleDateFormat;
import java.util.*;

@Component(
        immediate = true,
        property = {
                "cron.expression= 0 0/01 1/1 1/1 * ?"   // scheduler runs every 1 min.
        },
        service = InactiveCompaniesByDate.class
)
public class InactiveCompaniesByDate extends BaseMessageListener {

    public static Log _log = LogFactoryUtil.getLog(InactiveCompaniesByDate.class);

    static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-dd-MM ");
    static SimpleDateFormat dateFormatLog = new SimpleDateFormat("yyyy-dd-MM HH:mm:ss.SSS");

    private volatile boolean initialized;
    private SchedulerEngineHelper schedulerEngineHelper;
    private SchedulerEntryImpl schedulerEntryImpl = null;

    private static final String REALNAME 	= PrefsPropsUtil.getString("preving.keycloak.configuration.realname");
    private static final String URL 		= PrefsPropsUtil.getString("preving.keycloak.configuration.url");
    private static final String USERNAME 	= PrefsPropsUtil.getString("preving.keycloak.configuration.username");
    private static final String PASS 		= PrefsPropsUtil.getString("preving.keycloak.configuration.pass");
    private static final String CLIENT_ID 	= PrefsPropsUtil.getString("preving.keycloak.configuration.clientid");
    private static final String SECRET 		= PrefsPropsUtil.getString("preving.keycloak.configuration.secret");

    @Override
    protected void doReceive(Message message) throws Exception {

        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        calendar.add(Calendar.DAY_OF_MONTH, -30);

        List<Comp> comps = CompLocalServiceUtil.getCompsToInactive(calendar.getTime());

        _log.debug(dateFormatLog.format(new Date()) + " SCHEDULER START ==========================================================");
        _log.debug(dateFormatLog.format(new Date()) + " SCHEDULER InactiveCompaniesByDate. Set inactive user from companies with end date : " + dateFormat.format(calendar.getTime()));

        AdeplusAuditUtil.addAudit(PortalUtil.getDefaultCompanyId(), 0, 0, AdeplusAuditPortletKeys.AUDIT_SCHEDULER_INACTIVE_COMPANIES, "Scheduler InactiveUserCompaniesByDate " + dateFormat.format(calendar.getTime()));
        List<UserCompany> userCompanies = null;
        User user = null;
        Vector<String> vUsers = new java.util.Vector<String>();
        for(Comp comp:comps){

            userCompanies = UserCompanyLocalServiceUtil.getUsersFromCompany(comp.getCompId());

            for(UserCompany userCompany:userCompanies) {

                userCompany.setUserEndDate(comp.getDeleteDate());
                UserCompanyLocalServiceUtil.updateUserCompany(userCompany);

                _log.debug(dateFormatLog.format(new Date()) + "  Set deleteDate to user " + userCompany.getEmail() + " by company end date. Company " + comp.getName() + " date end: " + comp.getDeleteDate());

                user = UserLocalServiceUtil.fetchUser(userCompany.getUserId());

                if (Validator.isNotNull(user) && user.isActive() && (UserCompanyLocalServiceUtil.getActiveUserCompaniesFromUser(userCompany.getUserId())).size() == 0) {
                        user.setStatus(WorkflowConstants.STATUS_INACTIVE);
                        _log.debug(dateFormatLog.format(new Date()) + "   - Inactive user " + user.getEmailAddress() + " by company end date. Company date end: " + comp.getDeleteDate());
                        UserLocalServiceUtil.updateUser(user);
                        UserMailing.userDelete(user);
                        AdeplusAuditUtil.addAudit(user.getCompanyId(), user.getGroupId(), user.getUserId(), AdeplusAuditPortletKeys.AUDIT_USER_INACTIVE,
                                "Set Delete Date " + user.getEmailAddress() + " by company by scheduler.");
                    vUsers.add(user.getEmailAddress());
                }
            }

        }

        Realm realm = new Realm(URL,
                REALNAME,
                USERNAME,
                PASS,
                SECRET,
                CLIENT_ID);

        for(int i = 0; i < vUsers.size(); i++){
            realm.setUserDisabled(vUsers.get(i));
        }

        realm.close();
        _log.debug(dateFormatLog.format(new Date()) + " SCHEDULER END ============================================================");
    }


    @Activate
    @Modified
    protected void activate(Map<String, Object> properties) throws SchedulerException {

        try {

            String cronExpression = "0 0 6 * * ? *"; // A las 6:00h de la mañana.
            //String cronExpression = "0 1/1 * 1/1 * ? *"; // Cada minuto
            _log.info("InactiveCompaniesByDate cronExpression: " + cronExpression);

            String listenerClass = getClass().getName();
            Trigger jobTrigger = TriggerFactoryUtil.createTrigger(listenerClass, listenerClass, new Date(), null, cronExpression);

            SchedulerEntryImpl schedulerEntryImpl = new SchedulerEntryImpl(listenerClass, jobTrigger);

            SchedulerEngineHelperUtil.register(this, schedulerEntryImpl, DestinationNames.SCHEDULER_DISPATCH);

        } catch (Exception e) {
            _log.error(e);
        }
    }

    /**
     * getStorageType: Utility method to get the storage type from the scheduler entry wrapper.
     * @return StorageType The storage type to use.
     */
    protected StorageType getStorageType() {
        if (schedulerEntryImpl instanceof StorageTypeAware) {
            return ((StorageTypeAware) schedulerEntryImpl).getStorageType();
        }

        return StorageType.MEMORY_CLUSTERED;
    }

    @Reference(target = ModuleServiceLifecycle.PORTAL_INITIALIZED, unbind = "-")
    protected void setModuleServiceLifecycle(ModuleServiceLifecycle moduleServiceLifecycle) {
    }

    @Reference(unbind = "-")
    protected void setTriggerFactory(TriggerFactory triggerFactory) {
        triggerFactory = triggerFactory;
    }

    @Reference(unbind = "-")
    protected void setSchedulerEngineHelper(SchedulerEngineHelper schedulerEngineHelper) {
        schedulerEngineHelper = schedulerEngineHelper;
    }

    @Deactivate
    protected void deactive() {

        if (initialized) {
            // unschedule the job so it is cleaned up
            try {
                schedulerEngineHelper.unschedule(schedulerEntryImpl, getStorageType());
            } catch (SchedulerException se) {
                if (_log.isWarnEnabled()) {
                    _log.warn("Unable to unschedule trigger", se);
                }
            }

            // unregister this listener
            schedulerEngineHelper.unregister(this);
        }

        SchedulerEngineHelperUtil.unregister(this);
        initialized = false;
    }

}

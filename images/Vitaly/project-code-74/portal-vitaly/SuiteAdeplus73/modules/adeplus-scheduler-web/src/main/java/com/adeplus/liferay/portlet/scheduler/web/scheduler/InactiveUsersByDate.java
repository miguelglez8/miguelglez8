package com.adeplus.liferay.portlet.scheduler.web.scheduler;

import com.adeplus.liferay.portlet.commons.web.audit.AdeplusAuditUtil;
import com.adeplus.liferay.portlet.commons.web.constants.AdeplusAuditPortletKeys;
import com.adeplus.liferay.portlet.mailing.web.mail.UserMailing;
import com.adeplus.liferay.portlet.suite.manager.model.UserCompany;
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
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import org.osgi.service.component.annotations.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Component(
        immediate = true,
        property = {
                //"cron.expression= 0 0/01 1/1 1/1 * ?"   // scheduler runs every 1 min.
        		"cron.expression= 0 0 7 * * ? *" // A las 7:00h de la mañana.
        },
        service = InactiveUsersByDate.class
)
public class InactiveUsersByDate extends BaseMessageListener {

    public static Log _log = LogFactoryUtil.getLog(InactiveUsersByDate.class);

    static SimpleDateFormat dateFormatLog = new SimpleDateFormat("yyyy-dd-MM HH:mm:ss.SSS");

    private volatile boolean initialized;
    private SchedulerEngineHelper schedulerEngineHelper;
    private SchedulerEntryImpl schedulerEntryImpl = null;

    @Override
    protected void doReceive(Message message) throws Exception {

        Date now = new Date();

        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        calendar.add(Calendar.DAY_OF_MONTH, -30);
        boolean desactivo=true;

        List<UserCompany> usersToInactive = UserCompanyLocalServiceUtil.getUsersToInactive(calendar.getTime());

        _log.debug(dateFormatLog.format(new Date()) + " SCHEDULER START ============================================================");
        _log.debug(dateFormatLog.format(new Date()) + " SCHEDULER InactiveUsersByDate " + usersToInactive.size() + ". Set inactive users in date: " + calendar.getTime() +".");

        AdeplusAuditUtil.addAudit(PortalUtil.getDefaultCompanyId(), 0, 0, AdeplusAuditPortletKeys.AUDIT_SCHEDULER_INACTIVE_USERS, "Scheduler InactiveUsersByDate " + calendar.getTime());

        for(UserCompany usercomp: usersToInactive){
            desactivo=true;
            User user = UserLocalServiceUtil.fetchUser(usercomp.getUserId());

            if(Validator.isNotNull(user) && user.isActive()) {

                List<UserCompany> activeUserCompaniesFromUser = UserCompanyLocalServiceUtil.getActiveUserCompaniesFromUser(usercomp.getUserId());
                for(UserCompany usuario:activeUserCompaniesFromUser){
                    if(Validator.isNull(usuario.getUserEndDate())){
                        desactivo=false;
                        break;
                    }
                }
                if (desactivo) {

                    user.setStatus(WorkflowConstants.STATUS_INACTIVE);
                    UserLocalServiceUtil.updateUser(user);

                    //Send mail.
                    UserMailing.userDelete(user);
                    AdeplusAuditUtil.addAudit(user.getCompanyId(), user.getGroupId(), user.getUserId(), AdeplusAuditPortletKeys.AUDIT_USER_INACTIVE, "Set inactive "+ user.getEmailAddress() +" by scheduler.");

                }
            }

        }
    }


    @Activate
    @Modified
    protected void activate(Map<String, Object> properties) throws SchedulerException {

        try {

            String cronExpression = "0 0 7 * * ? *"; // A las 7:00h de la mañana.
            //String cronExpression = "0 1/1 * 1/1 * ? *"; // Cada minuto
            _log.info("InactiveUsersByDate cronExpression: " + cronExpression);

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

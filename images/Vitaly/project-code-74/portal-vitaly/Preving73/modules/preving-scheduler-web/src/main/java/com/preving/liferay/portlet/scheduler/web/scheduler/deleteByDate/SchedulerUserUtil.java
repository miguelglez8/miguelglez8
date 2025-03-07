package com.preving.liferay.portlet.scheduler.web.scheduler.deleteByDate;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.scheduler.*;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.preving.liferay.portlet.calendar.manager.model.UserData;
import com.preving.liferay.portlet.calendar.manager.service.UserDataLocalServiceUtil;
import com.preving.liferay.portlet.mailing.web.mail.UserMailing;
import com.preving.liferay.portlet.scheduler.web.constants.PrevingSchedulerPortletKeys;
import com.preving.liferay.portlet.scheduler.web.util.PrevingRoleUtil;
import org.osgi.service.component.annotations.*;

import java.text.SimpleDateFormat;
import java.util.*;

@Component(
        immediate = true,
        property = {
                "cron.expression= 0 0/01 1/1 1/1 * ?"   // scheduler runs every 1 min.
        },
        service = SchedulerUserUtil.class
)
public class SchedulerUserUtil extends BaseMessageListener {

    public static Log _log = LogFactoryUtil.getLog(SchedulerUserUtil.class);

    static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    static SimpleDateFormat dateFormatLog = new SimpleDateFormat("yyyy-dd-MM HH:mm:ss.SSS");

    private volatile boolean initialized;
    private SchedulerEngineHelper schedulerEngineHelper;
    private SchedulerEntryImpl schedulerEntryImpl = null;

    @Override
    protected void doReceive(Message message) throws Exception {

        Date now = new Date();

        List<UserData> userDataList = UserDataLocalServiceUtil.getUserDataWithDeleteDate(now);

        System.out.println(dateFormatLog.format(new Date()) + " SCHEDULER START ======================================================");
        System.out.println(dateFormatLog.format(new Date()) + " SCHEDULER SchedulerUserUtil. Users active with delete date to set inactive today: " + userDataList.size() );

        for(UserData userData:userDataList) {

            User user = UserLocalServiceUtil.getUser(userData.getUserId());

            userData.setActive(false);
            userData.setDeleteDate(now);
            UserDataLocalServiceUtil.updateUserData(userData);

            PrevingRoleUtil.deleteUserRole(userData.getGroupId(), userData.getUserId());

            //Inactive admin and user if is inactive in all groups
            boolean hasActiveGroupAdmin = PrevingRoleUtil.isUserActiveRoleInAnyActiveGroups(user.getUserId(), PrefsPropsUtil.getString(PrevingSchedulerPortletKeys.PROPERTY_ROLE_COMPANY_SITE_ADMIN));
            boolean hasActiveGroupUser = PrevingRoleUtil.isUserActiveRoleInAnyActiveGroups(user.getUserId(), PrefsPropsUtil.getString(PrevingSchedulerPortletKeys.PROPERTY_ROLE_COMPANY_SITE_USER));

            if( !hasActiveGroupAdmin  && !hasActiveGroupUser){

                user.setStatus(WorkflowConstants.STATUS_INACTIVE);
                UserLocalServiceUtil.updateUser(user);

                System.out.println(dateFormatLog.format(new Date()) + "The user " + userData.getEmail() + " in groupId " + userData.getGroupId() +  " is inactive now  in all the portal.");

            } else {
                System.out.println(dateFormatLog.format(new Date()) + "The user " + userData.getEmail() + " in groupId " + userData.getGroupId() +  " is inactive now in this company.");
            }

            UserMailing.userDeleteToUser(user);

        }

        System.out.println(dateFormatLog.format(new Date()) + " SCHEDULER END ========================================================");

    }

    @Activate
    @Modified
    protected void activate(Map<String, Object> properties) throws SchedulerException {

        try {
            //String cronExpression = GetterUtil.getString(properties.get(PrevingSchedulerPortletKeys.CONFIGURATION_USER_END_DATE_CRON_EXPRESION), "1 0 * * * ?");
            String cronExpression = "0 45 9 * * ? *";
            //String cronExpression = "0 1/1 * 1/1 * ? *"; // Cada minuto
            _log.info(" cronExpression: " + cronExpression);

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

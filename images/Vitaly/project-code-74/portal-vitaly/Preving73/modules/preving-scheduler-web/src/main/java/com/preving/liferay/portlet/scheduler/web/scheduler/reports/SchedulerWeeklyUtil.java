package com.preving.liferay.portlet.scheduler.web.scheduler.reports;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.scheduler.*;
import com.liferay.portal.kernel.util.Validator;
import com.preving.liferay.portlet.calendar.manager.model.CompanyConf;
import com.preving.liferay.portlet.calendar.manager.service.CompanyConfLocalServiceUtil;
import com.preving.liferay.portlet.scheduler.web.constants.PrevingSchedulerPortletKeys;
import com.preving.liferay.portlet.scheduler.web.util.PrevingRegisterSummaryUtil;
import org.osgi.service.component.annotations.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Component(
        immediate = true,
        property = {
                "cron.expression= 15 1 * * * ? *"
        },
        service = SchedulerWeeklyUtil.class
)
public class SchedulerWeeklyUtil extends BaseMessageListener  {

    public static Log _log = LogFactoryUtil.getLog(SchedulerWeeklyUtil.class);

    private volatile boolean initialized;
    private SchedulerEngineHelper schedulerEngineHelper;
    private SchedulerEntryImpl schedulerEntryImpl = null;

    @Override
    protected void doReceive(Message message) throws Exception {

        try {
            Date now = new Date();

            List<CompanyConf> companyConfActive = CompanyConfLocalServiceUtil.getCompanyConfActive();

            System.out.println("SchedulerWeeklyUtil report " + new Date() + ". Size " + companyConfActive.size());

            for(CompanyConf comp:companyConfActive){

                if(_log.isDebugEnabled()) {
                    _log.debug("  Company groupId " + comp.getGroupId()+ ", Periodicity: " + comp.getNotificationAdminPeriodicity());
                }

                if((Validator.isNull(comp.getDeleteDate()) || comp.getDeleteDate().after(now))
                        && PrevingSchedulerPortletKeys.NOTIFICATION_PERIODICITY_WEEKLY.equals(comp.getNotificationAdminPeriodicity())) {

                    PrevingRegisterSummaryUtil.sendMailWeekly(comp.getCompanyId(), comp.getGroupId());

                }

            }
        } catch (SystemException e) {
            _log.error(e);
            e.printStackTrace();
        }

    }

    @Activate
    @Modified
    protected void activate(Map<String, Object> properties) throws SchedulerException {

        try {
             String cronExpression = "0 50 10 ? * MON *"; // Los lunes a las 10:50h.
            //String cronExpression = "0 1/1 * 1/1 * ? *"; // Cada minuto, sólo para probar.
            _log.info("SchedulerWeeklyUtil cronExpression: " + cronExpression);

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

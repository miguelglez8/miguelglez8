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

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Resumen diarío de fichajes.
 * Se envía a los administradores de las empresas.
 */
@Component(
        immediate = true,
        property = {
                "cron.expression= 0 1 * * * ? *"
        },
        service = SchedulerDailyUtil.class
)
public class SchedulerDailyUtil extends BaseMessageListener  {

    public static Log _log = LogFactoryUtil.getLog(SchedulerDailyUtil.class);

    static SimpleDateFormat dateFormatLog = new SimpleDateFormat("yyyy-dd-MM HH:mm:ss.SSS");

    private volatile boolean initialized;
    private SchedulerEngineHelper schedulerEngineHelper;
    private SchedulerEntryImpl schedulerEntryImpl = null;

    @Override
    protected void doReceive(Message message) throws Exception {

        try {
            Date now = new Date();

            List<CompanyConf> companyConfActive = CompanyConfLocalServiceUtil.getCompanyConfActive();

            System.out.println(dateFormatLog.format(new Date()) + " SCHEDULER START ======================================================");
            System.out.println(dateFormatLog.format(new Date()) + " SCHEDULER SchedulerDailyUtil. Report " + new Date() + ". Size " + companyConfActive.size());

            for(CompanyConf comp:companyConfActive){

                if(_log.isDebugEnabled()) {
                    _log.debug("  Company groupId " + comp.getGroupId()+ ", Periodicity: " + comp.getNotificationAdminPeriodicity());
                }

                //Daily
                if((Validator.isNull(comp.getDeleteDate()) || comp.getDeleteDate().after(now))
                        && PrevingSchedulerPortletKeys.NOTIFICATION_PERIODICITY_DAILY.equals(comp.getNotificationAdminPeriodicity())) {

                    PrevingRegisterSummaryUtil.sendMailDaily(comp.getCompanyId(), comp.getGroupId());

                }

            }
        } catch (SystemException e) {
            _log.error(e);
            e.printStackTrace();
        }
        System.out.println(dateFormatLog.format(new Date()) + " SCHEDULER END ========================================================");

    }


    @Activate
    @Modified
    protected void activate(Map<String, Object> properties) throws SchedulerException {

        try {
            String cronExpression = "0 45 10 * * ? *"; //Todos los días a las 01:15h
            //String cronExpression = "0 1/1 * 1/1 * ? *"; // Cada minuto, sólo para probar.
            //String cronExpression = "0 26 9 1/1 * ? *";
            _log.info("SchedulerDailyUtil cronExpression: " + cronExpression);

            String listenerClass = getClass().getName();
            Trigger jobTrigger = TriggerFactoryUtil.createTrigger(listenerClass, listenerClass, new Date(), null, cronExpression);

            SchedulerEntryImpl schedulerEntryImpl = new SchedulerEntryImpl(listenerClass, jobTrigger);

            SchedulerEngineHelperUtil.update(jobTrigger, StorageType.PERSISTED);

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

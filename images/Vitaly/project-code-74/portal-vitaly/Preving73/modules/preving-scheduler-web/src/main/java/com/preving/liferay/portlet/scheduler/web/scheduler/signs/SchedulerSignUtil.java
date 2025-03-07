package com.preving.liferay.portlet.scheduler.web.scheduler.signs;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.scheduler.*;
import com.liferay.portal.kernel.scheduler.messaging.SchedulerResponse;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.util.ContentUtil;
import com.preving.liferay.portlet.calendar.manager.model.CompanyConf;
import com.preving.liferay.portlet.calendar.manager.model.Sign;
import com.preving.liferay.portlet.calendar.manager.service.CompanyConfLocalServiceUtil;
import com.preving.liferay.portlet.calendar.manager.service.SignLocalServiceUtil;
import com.preving.liferay.portlet.calendar.manager.service.UserDataLocalServiceUtil;
import com.preving.liferay.portlet.mailing.web.mail.ReportMailing;
import com.preving.liferay.portlet.scheduler.web.constants.PrevingSchedulerPortletKeys;
import com.preving.liferay.portlet.scheduler.web.util.NotificacionUtil;
import org.osgi.service.component.annotations.*;

import java.text.SimpleDateFormat;
import java.util.*;

@Component(
        immediate = true,
        property = {
                "cron.expression= 30 0 * * * ? *"
        },
        service = SchedulerSignUtil.class
)
public class SchedulerSignUtil extends BaseMessageListener  {

    public static Log _log = LogFactoryUtil.getLog(SchedulerSignUtil.class);

    private volatile boolean initialized;
    private TriggerFactory triggerFactory;
    private SchedulerEngineHelper schedulerEngineHelper;
    private SchedulerEntryImpl schedulerEntryImpl = null;

    static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    static SimpleDateFormat dateFormatLog = new SimpleDateFormat("yyyy-dd-MM HH:mm:ss.SSS");


    @Override
    protected void doReceive(Message message) throws Exception {

        try {

            Date now = new Date();

            Calendar calendar = Calendar.getInstance();

            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);

            calendar.add(Calendar.DAY_OF_MONTH, -1);
            Date yesterday = calendar.getTime();

            List<Sign> signsWithoutFinishDate = SignLocalServiceUtil.getSignsWithoutFinishDate(yesterday);

            System.out.println(dateFormatLog.format(new Date()) + " SCHEDULER START ======================================================");
            System.out.println(dateFormatLog.format(new Date()) + " SCHEDULER SchedulerSignUtil. Users without endDate sign of " + yesterday + ", day of week: " + calendar.get(Calendar.DAY_OF_WEEK) + ", num signs: " + signsWithoutFinishDate.size() );

            if(Calendar.SATURDAY == calendar.get(Calendar.DAY_OF_WEEK)
                    || Calendar.SUNDAY == calendar.get(Calendar.DAY_OF_WEEK)){
                System.out.println("  SchedulerSignUtil not in Saturday or Sunday.");
                System.out.println(dateFormatLog.format(new Date()) + " SCHEDULER END ========================================================");

                return;
            }

            for(Sign sign:signsWithoutFinishDate){

                CompanyConf companyConf = CompanyConfLocalServiceUtil.getCompanyConfByCompanyIdAndGroupId(sign.getCompanyId(), sign.getGroupId());

                if(Validator.isNotNull(companyConf)
                        && (Validator.isNull(companyConf.getDeleteDate()) || companyConf.getDeleteDate().after(now))
                        && companyConf.isNotificationFinishDateSign()) {

                    User user = UserLocalServiceUtil.getUser(sign.getUserId());

                    if(!user.isActive()){

                        System.out.println(dateFormatLog.format(new Date()) + "   Don't send email. The user " + user.getEmailAddress() + " in group " +  companyConf.getGroupId() + " because is inactive. ");

                        continue;
                    }

                    if(!UserDataLocalServiceUtil.canSendMailToUser(companyConf.getGroupId(), user.getUserId())){

                        System.out.println(dateFormatLog.format(new Date()) + "   Don't send email. The user " + user.getEmailAddress() + " in group " +  companyConf.getGroupId() + " have notifications disabled today. ");

                        continue;
                    }

                    //Send mail.
                    ReportMailing.signUnclosedToUser(user);

                    //Send notification.
                    String info = LanguageUtil.get(ResourceBundle.getBundle("content/Language", user.getLocale()), "previngmailing.mail.sign.subject");
                    NotificacionUtil.sendNotificacion(sign.getUserId(), sign.getUserId(), info , info);

                    System.out.println(dateFormatLog.format(new Date()) + "   Send email. The user " + user.getEmailAddress() + " in group " +  companyConf.getGroupId() + " have unclosed signs. ");

                }

            }

            System.out.println(dateFormatLog.format(new Date()) + " ======================================================================");
            System.out.println(dateFormatLog.format(new Date()) + " NEXT FIRE TIME " +SchedulerEngineHelperUtil.getNextFireTime(getClass().getName(),getClass().getName(), StorageType.PERSISTED));
            System.out.println(dateFormatLog.format(new Date()) + " SCHEDULER END ========================================================");

        } catch (SystemException e) {
            _log.error(e);
            e.printStackTrace();
        }

    }

    @Activate
    @Modified
    protected void activate(Map<String, Object> properties) throws SchedulerException {

        try {
            String cronExpression = "0 30 10 * * ? *"; //Todos los días a las 10:30h
            //String cronExpression = "0 1/1 * 1/1 * ? *"; // Cada minuto
            _log.info("SchedulerSignUtil cronExpression: " + cronExpression);

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

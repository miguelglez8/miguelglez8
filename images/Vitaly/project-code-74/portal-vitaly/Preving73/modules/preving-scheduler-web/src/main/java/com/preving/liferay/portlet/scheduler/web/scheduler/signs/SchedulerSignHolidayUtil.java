package com.preving.liferay.portlet.scheduler.web.scheduler.signs;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.scheduler.*;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.Validator;
import com.preving.liferay.portlet.calendar.manager.model.CompanyConf;
import com.preving.liferay.portlet.calendar.manager.model.Holiday;
import com.preving.liferay.portlet.calendar.manager.model.Sign;
import com.preving.liferay.portlet.calendar.manager.service.CompanyConfLocalServiceUtil;
import com.preving.liferay.portlet.calendar.manager.service.HolidayLocalServiceUtil;
import com.preving.liferay.portlet.calendar.manager.service.SignLocalServiceUtil;
import com.preving.liferay.portlet.calendar.manager.service.UserDataLocalServiceUtil;
import com.preving.liferay.portlet.mailing.web.mail.ReportMailing;
import com.preving.liferay.portlet.scheduler.web.constants.PrevingSchedulerPortletKeys;
import com.preving.liferay.portlet.scheduler.web.util.NotificacionUtil;
import com.preving.liferay.portlet.scheduler.web.util.PrevingRoleUtil;
import org.osgi.service.component.annotations.*;

import java.text.SimpleDateFormat;
import java.util.*;

@Component(
        immediate = true,
        property = {
                "cron.expression= 30 0 * * * ? *"
        },
        service = SchedulerSignHolidayUtil.class
)
public class SchedulerSignHolidayUtil extends BaseMessageListener {

    public static Log _log = LogFactoryUtil.getLog(SchedulerSignHolidayUtil.class);

    static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    static SimpleDateFormat dateFormatLog = new SimpleDateFormat("yyyy-dd-MM HH:mm:ss.SSS");

    private volatile boolean initialized;
    private SchedulerEngineHelper schedulerEngineHelper;
    private SchedulerEntryImpl schedulerEntryImpl = null;

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

            String roleUserName = PrefsPropsUtil.getString(PrevingSchedulerPortletKeys.PROPERTY_ROLE_COMPANY_SITE_USER);

            List<CompanyConf> companyConfActive = CompanyConfLocalServiceUtil.getCompanyConfActive();

            System.out.println(dateFormatLog.format(new Date()) + " SCHEDULER START ======================================================");
            System.out.println(dateFormatLog.format(new Date()) + " SCHEDULER SchedulerSignHolidayUtil. Users without sign in " + yesterday + ", day of week: " + calendar.get(Calendar.DAY_OF_WEEK) + ", num companies active: " + companyConfActive.size() );

            if(Calendar.SATURDAY == calendar.get(Calendar.DAY_OF_WEEK)
                    || Calendar.SUNDAY == calendar.get(Calendar.DAY_OF_WEEK)){

                System.out.println(dateFormatLog.format(new Date()) + "   No send notification to companies in Saturday or Sunday.");
                return;
            }

            for(CompanyConf conf:companyConfActive){

                if(!conf.isNotificationNotWorkable()
                        || (Validator.isNotNull(conf.getDeleteDate()) && conf.getDeleteDate().before(now))){

                    System.out.println(dateFormatLog.format(new Date()) + "   No send notification to group " + conf.getGroupId() + ", isNotificationNotWorkable:  " + conf.isNotificationNotWorkable());

                    continue;
                }

                List<Holiday> holidays = HolidayLocalServiceUtil.getHolidaysFromGroup(conf.getCompanyId(), conf.getGroupId());

                if(isInHolidays(yesterday, holidays)){

                    System.out.println(dateFormatLog.format(new Date()) + "   No send notification to group " +  conf.getGroupId() + " is on holiday.");

                    continue;
                }


                Group group = null;
                try {
                    group = GroupLocalServiceUtil.getGroup(conf.getGroupId());

                    System.out.println(dateFormatLog.format(new Date()) + " Company " +  group.getName("es_ES") + ":");

                    if(Validator.isNull(group) || !group.isActive()){
                        System.out.println(dateFormatLog.format(new Date()) + "   No send notification to group " +  conf.getGroupId() + " is inactive or not exits.");
                        continue;
                    }
                } catch (PortalException e) {
                    System.out.println(dateFormatLog.format(new Date()) + " Group " + conf.getGroupId() + " doesn't exists.");
                }

                List<User> groupUsers = UserLocalServiceUtil.getGroupUsers(conf.getGroupId());

                for(User user:groupUsers) {

                    if (group.getCreatorUserId() == user.getUserId()) {
                        continue;
                    }

                    if(!UserDataLocalServiceUtil.canSendMailToUser(group.getGroupId(), user.getUserId())){

                        System.out.println(dateFormatLog.format(new Date()) + "   Don't send email. The user " + user.getEmailAddress() + " in group " +  conf.getGroupId() + " have notifications disabled today. ");

                        continue;
                    }

                    //If user is inactive in this group.
                    if(!user.isActive() || !PrevingRoleUtil.hasRoleUser(conf.getGroupId(), user.getUserId(), roleUserName)){

                        System.out.println(dateFormatLog.format(new Date()) + "   Don't send email. The user " + user.getEmailAddress() + " in group " +  conf.getGroupId() + " is inactive. ");

                        continue;
                    }

                    List<Sign> signsByUserIdBetweenDates = SignLocalServiceUtil.getSignsByUserIdBetweenDates(conf.getCompanyId(), conf.getGroupId(), user.getUserId(), yesterday, yesterday);

                    if (signsByUserIdBetweenDates.size() == 0) {

                        System.out.println(dateFormatLog.format(new Date()) + "   Send email. No signs for " + user.getEmailAddress()+ " in group " + conf.getGroupId() + ". " );

                        //Send mail.
                        ReportMailing.userWithoutSignToUser(user, Validator.isNotNull(group)?group.getName(user.getLocale()):"");

                        //Notification.
                        String info = LanguageUtil.get(ResourceBundle.getBundle("content/Language", user.getLocale()), "previngmailing.mail.holiday.subject");
                        NotificacionUtil.sendNotificacion(user.getUserId(), user.getUserId(), info, info);

                    } else {

                        System.out.println(dateFormatLog.format(new Date()) + "   Don't send email. There are signs for " +  user.getEmailAddress() + " in group " + conf.getGroupId() + ", num signs: " + signsByUserIdBetweenDates.size() + ".");

                    }

                }

            }
            System.out.println(dateFormatLog.format(new Date()) + " ======================================================================");
            System.out.println(dateFormatLog.format(new Date()) + " SCHEDULER END ========================================================");

        } catch (SystemException e) {
            _log.error(e);
            e.printStackTrace();
        }

    }

    private boolean isInHolidays(Date date, List<Holiday> holidays){
        for (Holiday holiday : holidays) {
            if (isHoliday(date, holiday)) {

                if (_log.isDebugEnabled()) {
                    _log.debug(" SchedulerSignHolidayUtil not in holiday " + holiday.getName());
                }

                return true;
            }
        }
        return false;
    }

    private boolean isHoliday(Date date, Holiday holiday) {

        if(holiday.isAllowSign()){
            return false;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

/*        if (_log.isDebugEnabled()) {
            _log.debug(calendar.get(Calendar.DAY_OF_MONTH) +" == "+ holiday.getDay() + " && " + (calendar.get(Calendar.MONTH) +1 ) +" == "+ holiday.getMonth());
        }*/

        return calendar.get(Calendar.DAY_OF_MONTH) == holiday.getDay() && (calendar.get(Calendar.MONTH) + 1 ) == holiday.getMonth();
    }

    @Activate
    @Modified
    protected void activate(Map<String, Object> properties) throws SchedulerException {

        try {
            String cronExpression = "0 0 10 * * ? *"; //Todos los días a las 10:30h
            //String cronExpression = "0 1/1 * 1/1 * ? *"; // Cada minuto
            _log.info("SchedulerSignHolidayUtil cronExpression: " + cronExpression);

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

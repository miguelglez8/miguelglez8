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
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.Validator;
import com.preving.liferay.portlet.calendar.manager.model.CompanyConf;
import com.preving.liferay.portlet.calendar.manager.model.Sign;
import com.preving.liferay.portlet.calendar.manager.service.CompanyConfLocalServiceUtil;
import com.preving.liferay.portlet.calendar.manager.service.SignLocalServiceUtil;
import com.preving.liferay.portlet.calendar.manager.service.UserDataLocalServiceUtil;
import com.preving.liferay.portlet.mailing.web.mail.ReportMailing;
import com.preving.liferay.portlet.scheduler.web.util.NotificacionUtil;
import org.osgi.service.component.annotations.*;

import java.text.SimpleDateFormat;
import java.util.*;

@Component(
        immediate = true,
        property = {
                "cron.expression= 30 0 * * * ? *"
        },
        service = SchedulerSignWeeklyUtil.class
)
public class SchedulerSignWeeklyUtil extends BaseMessageListener  {

    public static Log _log = LogFactoryUtil.getLog(SchedulerSignWeeklyUtil.class);

    static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    static SimpleDateFormat dateFormatHour = new SimpleDateFormat("dd/MM/yyyy HH:mm");
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

            calendar.add(Calendar.DAY_OF_MONTH, -6);
            Date oneWeekAgo = calendar.getTime();

            List<Sign> signsWithoutFinishDate = SignLocalServiceUtil.getSignsWithoutFinishDateBetweenDates(oneWeekAgo, yesterday);

            List<Long> usersFromSignList = getUsersFromSignList(signsWithoutFinishDate);

            System.out.println(dateFormatLog.format(new Date()) + " SCHEDULER START ======================================================");
            System.out.println(dateFormatLog.format(new Date()) + " SCHEDULER SchedulerSignWeeklyUtil. Users without endDate from" + oneWeekAgo +" to " + yesterday +", num users: " + usersFromSignList.size() );

            for(Long userId:usersFromSignList){

                User user = UserLocalServiceUtil.getUser(userId);

                CompanyConf companyConf = CompanyConfLocalServiceUtil.getCompanyConfByCompanyIdAndGroupId(user.getCompanyId(), user.getGroupId());

                if(!user.isActive()){

                    System.out.println(dateFormatLog.format(new Date()) + "   Don't send email. The user " + user.getEmailAddress() + " in group " +  companyConf.getGroupId() + " because is inactive. ");

                    continue;
                }

                if(!UserDataLocalServiceUtil.canSendMailToUser(companyConf.getGroupId(), user.getUserId())){

                    System.out.println(dateFormatLog.format(new Date()) + "   Don't send email. The user " + user.getEmailAddress() + " in group " +  companyConf.getGroupId() + " have notifications disabled today. ");

                    continue;
                }


                if(Validator.isNotNull(companyConf)
                        && (Validator.isNull(companyConf.getDeleteDate()) || companyConf.getDeleteDate().after(now))
                        && companyConf.isNotificationFinishDateSign()) {

                    List<Sign> signsWithoutFinishDateBetweenDatesByUser = SignLocalServiceUtil.getSignsWithoutFinishDateBetweenDatesByUser(userId, oneWeekAgo, yesterday);

                    String info = "<div><ul>";

                    for(Sign sign:signsWithoutFinishDateBetweenDatesByUser){
                        info += "<li>"+dateFormatHour.format(sign.getStartDate())+" " + sign.getObservations() + " " + sign.getObservationsAdmin() +"</li>";
                    }
                    info += "</ul></div>";

                    //Send mail.
                    ReportMailing.signUnclosedWeeklyToUser(user, info);

                    //Send notification.
                    String data = LanguageUtil.get(ResourceBundle.getBundle("content/Language", user.getLocale()), "previngmailing.mail.daily.subject");
                    NotificacionUtil.sendNotificacion(userId, userId, data , info);

                    System.out.println(dateFormatLog.format(new Date()) + "   Send email. The user " + user.getEmailAddress() + " in group " +  companyConf.getGroupId() + " have unclosed signs this week. ");

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

    private List<Long> getUsersFromSignList(List<Sign> signList){

        List<Long> users = new ArrayList<Long>();

        for(Sign sign:signList){
            if(!users.contains(sign.getUserId())){
                users.add(sign.getUserId());
            }
        }

        return users;
    }

    @Activate
    @Modified
    protected void activate(Map<String, Object> properties) throws SchedulerException {

        try {
            //http://www.cronmaker.com
            String cronExpression = "0 15 10 ? * MON *"; //Los lunes a las 00:15h
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

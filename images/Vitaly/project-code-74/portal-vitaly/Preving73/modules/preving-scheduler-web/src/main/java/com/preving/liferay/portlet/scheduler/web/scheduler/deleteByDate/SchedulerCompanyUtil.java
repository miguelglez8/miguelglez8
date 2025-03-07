package com.preving.liferay.portlet.scheduler.web.scheduler.deleteByDate;

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
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.util.ContentUtil;
import com.preving.liferay.portlet.calendar.manager.model.CompanyConf;
import com.preving.liferay.portlet.calendar.manager.model.UserData;
import com.preving.liferay.portlet.calendar.manager.service.CompanyConfLocalServiceUtil;
import com.preving.liferay.portlet.calendar.manager.service.UserDataLocalServiceUtil;
import com.preving.liferay.portlet.mailing.web.mail.CompanyMailing;
import com.preving.liferay.portlet.mailing.web.mail.UserMailing;
import com.preving.liferay.portlet.scheduler.web.constants.PrevingSchedulerPortletKeys;
import com.preving.liferay.portlet.scheduler.web.util.NotificacionUtil;
import com.preving.liferay.portlet.scheduler.web.util.PrevingRoleUtil;
import com.preving.liferay.portlet.scheduler.web.util.PrevingUserUtil;
import org.osgi.service.component.annotations.*;

import java.text.SimpleDateFormat;
import java.util.*;

@Component(
        immediate = true,
        property = {
                "cron.expression= 0 0/01 1/1 1/1 * ?"   // scheduler runs every 1 min.
        },
        service = SchedulerCompanyUtil.class
)
public class SchedulerCompanyUtil extends BaseMessageListener {

    public static Log _log = LogFactoryUtil.getLog(SchedulerCompanyUtil.class);

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

        calendar.add(Calendar.MONTH, -1);

        List<CompanyConf> companyConfByDeleteDateAMonthAgo = CompanyConfLocalServiceUtil.getCompanyConfByDeleteDate(calendar.getTime());

        System.out.println(dateFormatLog.format(new Date()) + " SCHEDULER START ======================================================");
        System.out.println(dateFormatLog.format(new Date()) + " SCHEDULER SchedulerCompanyUtil. Companies to set inactive group with end date 1 month ago: " + companyConfByDeleteDateAMonthAgo.size() + ". Delete date " + calendar.getTime() );

        for(CompanyConf comp:companyConfByDeleteDateAMonthAgo) {

            Group group = GroupLocalServiceUtil.fetchGroup(comp.getGroupId());

            group.setActive(false);

            GroupLocalServiceUtil.updateGroup(group);

            System.out.println("  The company " + group.getFriendlyURL() + " is inactive now, because the finish date was one month ago.");

            //Inactive admins and send Mails.
            List<User> listAdminIdFromGroup = PrevingUserUtil.getListAdminIdFromGroup(comp.getGroupId());
            for(User admin:listAdminIdFromGroup){

                UserData userData = PrevingUserUtil.getUserData(comp.getGroupId(), admin.getUserId());

                CompanyMailing.companyDeleteToAdministrator(admin,group.getName(admin.getLocale()),comp.getCif());

                String info = LanguageUtil.get(ResourceBundle.getBundle("content/Language", admin.getLocale()), "previngmailing.mail.company.admin.subject");
                NotificacionUtil.sendNotificacion(admin.getUserId(), group.getCreatorUserId(), info, info);

                System.out.println("    Admin " + admin.getEmailAddress() + " was notificated.");
            }

        }


        List<CompanyConf> companyConfByDeleteDateToday = CompanyConfLocalServiceUtil.getCompanyConfByDeleteDate(now);

        System.out.println(dateFormatLog.format(new Date()) + " INACTIVE USERS --------------------------------------------------------");
        System.out.println(dateFormatLog.format(new Date()) + " SCHEDULER SchedulerCompanyUtil. Companies to set inactive users today: " + companyConfByDeleteDateToday.size() + ". Delete date " + now );

        for(CompanyConf comp:companyConfByDeleteDateToday) {
            //Inactive users for the company.
            Group group = GroupLocalServiceUtil.fetchGroup(comp.getGroupId());
            List<User> userList = UserLocalServiceUtil.getGroupUsers(group.getGroupId());
            for(User u:userList) {

                if (group.getCreatorUserId() != u.getUserId()) {

                    UserData userDataUser = PrevingUserUtil.getUserData(comp.getGroupId(), u.getUserId());

                    userDataUser.setActive(false);
                    userDataUser.setDeleteDate(now);

                    UserDataLocalServiceUtil.updateUserData(userDataUser);

                    PrevingRoleUtil.deleteUserRole(comp.getGroupId(), userDataUser.getUserId());
                    PrevingRoleUtil.deleteAdminRole(comp.getGroupId(), userDataUser.getUserId());

                    //Inactive admin and user if is inactive in all groups
                    boolean hasActiveGroupAdmin = PrevingRoleUtil.isUserActiveRoleInAnyActiveGroups(u.getUserId(), PrefsPropsUtil.getString(PrevingSchedulerPortletKeys.PROPERTY_ROLE_COMPANY_SITE_ADMIN));
                    boolean hasActiveGroupUser = PrevingRoleUtil.isUserActiveRoleInAnyActiveGroups(u.getUserId(), PrefsPropsUtil.getString(PrevingSchedulerPortletKeys.PROPERTY_ROLE_COMPANY_SITE_USER));

                    if( !hasActiveGroupAdmin  && !hasActiveGroupUser){

                        u.setStatus(WorkflowConstants.STATUS_INACTIVE);
                        UserLocalServiceUtil.updateUser(u);

                        System.out.println(dateFormatLog.format(new Date()) +"  For company " + group.getFriendlyURL() +  ". The user " +  u.getEmailAddress() + " is inactive now in all the portal.");

                    } else {
                        System.out.println(dateFormatLog.format(new Date()) +"  For company " + group.getFriendlyURL() +  ". The user " +  u.getEmailAddress() + " is inactive now in this company.");
                    }

                    UserMailing.userDeleteToUser(u);

                    String info = LanguageUtil.get(ResourceBundle.getBundle("content/Language", u.getLocale()), "previngmailing.mail.company.user.subject");
                    NotificacionUtil.sendNotificacion(u.getUserId(), group.getCreatorUserId(), info, info);

                }
            }

        }

        System.out.println(dateFormatLog.format(new Date()) + " SCHEDULER END ========================================================");

    }


    @Activate
    @Modified
    protected void activate(Map<String, Object> properties) throws SchedulerException {

        try {
            String cronExpression = "0 30 9 * * ? *";
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

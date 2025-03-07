package com.canal.etico.liferay.portlet.synchronization.web.synchro;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.scheduler.*;
import org.osgi.service.component.annotations.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Component(
        immediate = true,
        property = {
                "cron.expression= 0 0/01 1/1 1/1 * ?"   // scheduler runs every 1 min.
        },
        service = SynchronizeUsers.class
)
public class SynchronizeUsers extends BaseMessageListener {

    public static Log _log = LogFactoryUtil.getLog(SynchronizeUsers.class);

    static SimpleDateFormat dateFormatLog = new SimpleDateFormat("yyyy-dd-MM HH:mm:ss.SSS");

    private volatile boolean initialized;
    private SchedulerEngineHelper schedulerEngineHelper;
    private SchedulerEntryImpl schedulerEntryImpl = null;

    @Override
    protected void doReceive(Message message) throws Exception {
/*
        Date now = new Date();

        System.out.println(dateFormatLog.format(new Date()) + " SCHEDULER START ============================================================");
        System.out.println(dateFormatLog.format(new Date()) + " SCHEDULER SynchronizeUsers. Copy users from suite to canal etico.");


        String applicationAlias = PrefsPropsUtil.getString(CanalEticoSynchronizationPortletKeys.PROPERTY_APPLICATION_CANAL_ETICO_NAME);
        if(Validator.isBlank(applicationAlias)){
            System.out.println(dateFormatLog.format(new Date()) + "No existe la propiedad " + CanalEticoSynchronizationPortletKeys.PROPERTY_APPLICATION_CANAL_ETICO_NAME + " en el portal-ext.properties con el alias del Canal Denuncias.");
            return;
        }

        Application applicationByName = ApplicationLocalServiceUtil.getApplicationByAlias(applicationAlias);
        if(Validator.isBlank(applicationAlias)){
            System.out.println(dateFormatLog.format(new Date()) + "No existe la aplicacion con alias " + applicationAlias + " en la suite adeplus para la app canal denuncias.");
            return;
        }

        List<Comp> compList = CompLocalServiceUtil.getComps(QueryUtil.ALL_POS, QueryUtil.ALL_POS);

        for(Comp comp:compList) {

            List<UserApplication> userApplicationList = UserApplicationLocalServiceUtil.getUsersFromApplication(comp.getCompId(), applicationByName.getApplicationId());

            for (UserApplication ua : userApplicationList) {

                if (ua.getUserId() > 0) {

                    User user = UserLocalServiceUtil.fetchUser(ua.getUserId());

                    if (Validator.isNotNull(user) && user.isActive()) {

                        com.adeplus.liferay.portlet.suite.manager.model.UserCompany companyFromUser = UserCompanyLocalServiceUtil.getUserCompany(comp.getCompId(), user.getUserId());
                        UserCompany userCompanyFromUser = com.canal.etico.liferay.portlet.canal.manager.service.UserCompanyLocalServiceUtil.getUserCompanyFromUser(user.getUserId());

                        if (companyFromUser.getCompId() > 0 && Validator.isNull(userCompanyFromUser)) {

                            //Copy user to canal etico
                            CanalEticoUserUtil.createUser(user.getUserId(), companyFromUser.getCompId());

                            System.out.println(dateFormatLog.format(new Date()) + "  The user " + user.getEmailAddress() + " copied to Canal Etico in company " + companyFromUser);

                        } else {

                            System.out.println(dateFormatLog.format(new Date()) + "  The user " + user.getEmailAddress() + " already exists in Canal Etico in company " + companyFromUser);

                        }
                    }
                }
            }
        }

        System.out.println(dateFormatLog.format(new Date()) + " SCHEDULER END ============================================================");
        */

    }


    @Activate
    @Modified
    protected void activate(Map<String, Object> properties) throws SchedulerException {

        try {


            String cronExpression = "0 10 7 * * ? *"; // Cada 30 minutos.
            //String cronExpression = "0 1/1 * 1/1 * ? *"; // Cada minuto
            _log.info("SynchronizeUsers cronExpression: " + cronExpression);

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

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
        service = SynchronizeCompanies.class
)
public class SynchronizeCompanies extends BaseMessageListener {

    public static Log _log = LogFactoryUtil.getLog(SynchronizeCompanies.class);

    static SimpleDateFormat dateFormatLog = new SimpleDateFormat("yyyy-dd-MM HH:mm:ss.SSS");

    private volatile boolean initialized;
    private SchedulerEngineHelper schedulerEngineHelper;
    private SchedulerEntryImpl schedulerEntryImpl = null;

    @Override
    protected void doReceive(Message message) throws Exception {
/*
        Date now = new Date();

        System.out.println(dateFormatLog.format(new Date()) + " SCHEDULER START ============================================================");
        System.out.println(dateFormatLog.format(new Date()) + " SCHEDULER SynchronizeCompanies. Copy companies from suite to canal etico.");


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

        List<CompApplication> compApplicationList = CompApplicationLocalServiceUtil.getCompaniesWithApplication(applicationByName.getApplicationId());

        for(CompApplication ca:compApplicationList){

            if(ca.getCompId() > 0) {

                Comp comp = CompLocalServiceUtil.fetchComp(ca.getCompId());

                if (Validator.isNotNull(comp) && (Validator.isNull(comp.getDeleteDate()) || comp.getDeleteDate().after(now))) {

                    List<com.canal.etico.liferay.portlet.canal.manager.model.Comp> companiesByCIF = com.canal.etico.liferay.portlet.canal.manager.service.CompLocalServiceUtil.getCompaniesByCIF(comp.getCif());

                    if(Validator.isNotNull(companiesByCIF) && companiesByCIF.size() == 0) {

                        //Copy comp to canal etico
                        com.canal.etico.liferay.portlet.canal.manager.model.Comp company = CanalEticoCompUtil.createCompany(comp.getCompId(), comp.getName(), comp.getCif(), comp.getDescription(), comp.getObservations(), comp.getAgreement());

                        System.out.println(dateFormatLog.format(new Date()) + "  The company " + company.getName() + " copied to Canal Etico. CIF: " + company.getCif());

                    } else {

                        System.out.println(dateFormatLog.format(new Date()) + "  The company " + comp.getName() + " already exists in Canal Etico. CIF: " + comp.getCif());

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


            String cronExpression = "0 0 7 * * ? *";
            //String cronExpression = "0 1/1 * 1/1 * ? *"; // Cada minuto
            _log.info("SynchronizeCompanies cronExpression: " + cronExpression);

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

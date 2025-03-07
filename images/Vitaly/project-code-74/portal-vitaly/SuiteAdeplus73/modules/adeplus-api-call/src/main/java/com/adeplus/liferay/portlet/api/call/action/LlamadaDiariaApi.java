package com.adeplus.liferay.portlet.api.call.action;

import com.adeplus.liferay.portlet.commons.web.company.AdeplusCompanyUtil;
import com.adeplus.liferay.portlet.suite.manager.model.AuditadoDataApi;
import com.adeplus.liferay.portlet.suite.manager.model.TemporalDataApi;
import com.adeplus.liferay.portlet.suite.manager.service.AuditadoDataApiLocalServiceUtil;
import com.adeplus.liferay.portlet.suite.manager.service.TemporalDataApiLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
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
import java.util.List;
import java.util.Map;

@Component(
        immediate = true,
        property = {
                "cron.expression= 0 0/01 1/1 1/1 * ?"   // scheduler runs every 1 min.
        },
        service = LlamadaDiariaApi.class
)

public class LlamadaDiariaApi extends BaseMessageListener {

    public static Log _log = LogFactoryUtil.getLog(LlamadaDiariaApi.class);

    static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-dd-MM ");
    static SimpleDateFormat dateFormatLog = new SimpleDateFormat("yyyy-dd-MM HH:mm:ss.SSS");

    private volatile boolean initialized;
    private SchedulerEngineHelper schedulerEngineHelper;
    private SchedulerEntryImpl schedulerEntryImpl = null;
    @Override
    protected void doReceive(Message message) throws Exception {
        String dataCompany = StringPool.BLANK;
        String dataApplicaciones = StringPool.BLANK;
        String dataUsuarios = StringPool.BLANK;
        //Sera necesario
        //Llamamos al endpoint de la api y recorremos los datos recibidos
        /*
        JSONObject nuevoJson= JSONFactoryUtil.createJSONObject();
        JSONObject companyJson= JSONFactoryUtil.createJSONObject();
        JSONObject userJson= JSONFactoryUtil.createJSONObject();
        JSONArray contractJson= JSONFactoryUtil.createJSONArray();
        JSONObject applicationJson= JSONFactoryUtil.createJSONObject();
        JSONArray contractAppiJson= JSONFactoryUtil.createJSONArray();
        JSONArray licenses= JSONFactoryUtil.createJSONArray();
        JSONObject applicationAppiJson= JSONFactoryUtil.createJSONObject();

        Date now = new Date();
        companyJson.put("cif","pruebaApi");
        companyJson.put("name","pruebaApi");
        companyJson.put("description","pruebaApi");
        companyJson.put("observations","pruebaApi");
        companyJson.put("startDate",now);
        companyJson.put("deleteDate","");
        companyJson.put("modifiedDate",now);


        userJson.put("admin",false);
        userJson.put("nif","pruebaApi");
        userJson.put("name","pruebaApi");
        userJson.put("lastName","pruebaApi");
        userJson.put("email","pruebaApi@test.com");
        userJson.put("phone","");
        userJson.put("deleteDate","");
        userJson.put("modifiedDate",now);

        applicationJson.put("clientId",1);
        applicationJson.put("contractId",1);
        applicationJson.put("description","pruebaApi");

        applicationAppiJson.put("appId",2);
        applicationAppiJson.put("license",licenses);
        contractAppiJson.put(applicationAppiJson);

        applicationJson.put("applicationId",contractAppiJson);

        contractJson.put(applicationJson);


        nuevoJson.put("company",companyJson);
        nuevoJson.put("user",userJson);
        nuevoJson.put("contracts",contractJson);
        //una vez recibidos los datos los seteamos en la tabla temporal
        dataCompany=companyJson.toString();
        dataUsuarios=userJson.toString();
        dataApplicaciones=contractJson.toString();
        AdeplusCompanyUtil.createTemporalData(dataCompany,dataApplicaciones,dataUsuarios);




        AdeplusCompanyUtil.proccesTemporalData();

         */


    }

    @Activate
    @Modified
    protected void activate(Map<String, Object> properties) throws SchedulerException {

        try {

            String cronExpression = "0 00 01 * * ? *"; // Todos los dias a las 02:00 AM
            //String cronExpression = "0 1/1 * 1/1 * ? *"; // Cada minuto
            _log.info("InformeMensualLegislacionesScheduler cronExpression: " + cronExpression);

            String listenerClass = getClass().getName();
            Trigger jobTrigger = TriggerFactoryUtil.createTrigger(listenerClass, listenerClass, new Date(), null, cronExpression);

            SchedulerEntryImpl schedulerEntryImpl = new SchedulerEntryImpl(listenerClass, jobTrigger);

            SchedulerEngineHelperUtil.register(this, schedulerEntryImpl, DestinationNames.SCHEDULER_DISPATCH);

        } catch (Exception e) {
            _log.error(e);
        }
    }

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

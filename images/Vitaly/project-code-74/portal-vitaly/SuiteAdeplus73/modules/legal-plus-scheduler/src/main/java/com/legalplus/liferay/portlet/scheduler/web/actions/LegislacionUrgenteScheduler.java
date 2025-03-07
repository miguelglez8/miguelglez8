package com.legalplus.liferay.portlet.scheduler.web.actions;

import com.adeplus.liferay.portlet.suite.manager.model.*;
import com.adeplus.liferay.portlet.suite.manager.service.*;
import com.legalplus.liferay.portlet.commons.web.constants.LegalPlusCommonsPortletKeys;
import com.legalplus.liferay.portlet.legalplus.manager.model.*;
import com.legalplus.liferay.portlet.legalplus.manager.service.*;
import com.legalplus.liferay.portlet.mailing.web.mail.LegislacionMailing;
import com.legalplus.liferay.portlet.scheduler.web.constants.SchedulerPortletKeys;
import com.liferay.petra.string.StringPool;
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
import org.osgi.service.component.annotations.*;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Component(
        immediate = true,
        property = {
                "cron.expression= 0 30 21 * * ? *"   // scheduler runs every day at 21:30h.
        },
        service = LegislacionUrgenteScheduler.class
)
public class LegislacionUrgenteScheduler extends BaseMessageListener {

    public static Log _log = LogFactoryUtil.getLog(InformeMensualLegislacionesScheduler.class);

    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    static SimpleDateFormat dateFormatLog = new SimpleDateFormat("yyyy-dd-MM HH:mm:ss.SSS");

    private volatile boolean initialized;
    private SchedulerEngineHelper schedulerEngineHelper;
    private SchedulerEntryImpl schedulerEntryImpl = null;

    @Override
    protected void doReceive(Message message) throws Exception {

        //Obtener legislaciones urgentes
        List<Legislacion> legislaciones = LegislacionLocalServiceUtil.getLegislacionesUrgentes();
        Date date = new Date();
        //Obtener los clientes activos de Legal+
        Application application = ApplicationLocalServiceUtil.getApplicationByName(LegalPlusCommonsPortletKeys.PROPERTY_APP_LEGAL);
        List<CompApplication> companies = CompApplicationLocalServiceUtil.getCompaniesWithApplication(application.getApplicationId());

        for (CompApplication company : companies) {
            Comp compa = CompLocalServiceUtil.fetchComp(company.getCompId());
            Calendar calendarEndCompany = Calendar.getInstance();
            if(Validator.isNotNull(compa.getDeleteDate())) {
                calendarEndCompany.setTime(compa.getDeleteDate());
            }else{
                calendarEndCompany.setTime(date);
            }
            calendarEndCompany.add(Calendar.DAY_OF_YEAR, 30);
            Date fechaCaducidad = calendarEndCompany.getTime();
            if (Validator.isNull(compa.getDeleteDate()) || fechaCaducidad.after(date)) {

                for (Legislacion legislacion : legislaciones) {

                    Comp comp = CompLocalServiceUtil.fetchComp(company.getCompId());
                    ConsultorCompany consultorCompany = ConsultorCompanyLocalServiceUtil.fetchByCompId(comp.getCompId()).stream().findFirst().orElse(null);
                    User consultor = consultorCompany != null ? UserLocalServiceUtil.fetchUser(consultorCompany.getUserId()) : null;
                    List<LegislacionCNAES> cnaes = LegislacionCNAESLocalServiceUtil.findByLegislacion(legislacion.getLegislacionId());
                    List<ContratoCompany> contratos = ContratoCompanyLocalServiceUtil.fetchByLegislacion(
                            legislacion.getFamilia(),
                            legislacion.getCcaa(),
                            legislacion.getAyuntamiento(),
                            cnaes.stream().map(cnae -> cnae.getCnae()).collect(Collectors.joining(StringPool.SEMICOLON)));

                    if (contratos.stream().anyMatch(contratoCompany -> comp.getCompId() == company.getCompId())) {

                        StringBuilder sb = new StringBuilder();
                        List<Requisito> requisitos = RequisitoLocalServiceUtil.findRequisitoActivoByLegislacionAndCompId(legislacion.getLegislacionId(), comp.getCompId());
                        if (!requisitos.isEmpty()) {
                            sb.append("<ul>");
                            for (Requisito requisito : requisitos) {
                                sb.append("<li>" + requisito.getDescripcion() + "</li>");
                            }
                            sb.append("</ul>");
                        }

                        //List<UserCompany> users = UserCompanyLocalServiceUtil.getUsersFromCompany(company.getCompId());
                        List<UserCompany> users =  GetAllUsersByCompanyAndAppLegal.getAllUserActiveByApp(company.getCompId());


                        for (UserCompany user : users) {
                            //Enviar el mail a los usuarios.
                            User curUser = UserLocalServiceUtil.fetchUser(user.getUserId());
                            LegislacionMailing.legislacionUrgente(
                                        user.getEmail(),
                                        curUser,
                                        comp.getName(),
                                        dateFormat.format(legislacion.getPublicacion()),
                                        legislacion.getNombre(),
                                        legislacion.getDescripcion(),
                                        legislacion.getEnlace(),
                                        sb.toString(),
                                        consultor != null ? consultor.getFullName() : StringPool.BLANK,
                                        consultor != null ? consultor.getEmailAddress() : StringPool.BLANK);

                        }
                    }

                }

            }
        }

    }


    @Activate
    @Modified
    protected void activate(Map<String, Object> properties) throws SchedulerException {

        try {

            String cronExpression = "0 30 21 * * ? *"; // scheduler runs every day at 21:30h.
            //pruebas
            //String cronExpression = "0 1/1 * 1/1 * ? *"; // Cada minuto
            //String cronExpression = "0 0/2 * * * ? *"; // Cada 10 mins


            _log.info("LegislacionUrgenteScheduler cronExpression: " + cronExpression);

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

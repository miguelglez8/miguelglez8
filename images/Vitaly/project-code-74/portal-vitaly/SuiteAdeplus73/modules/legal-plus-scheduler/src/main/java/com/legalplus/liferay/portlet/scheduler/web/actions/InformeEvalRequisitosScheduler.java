package com.legalplus.liferay.portlet.scheduler.web.actions;

import com.adeplus.liferay.portlet.suite.manager.model.*;
import com.adeplus.liferay.portlet.suite.manager.service.*;
import com.legalplus.liferay.portlet.commons.web.constants.LegalPlusCommonsPortletKeys;
import com.legalplus.liferay.portlet.legalplus.manager.model.ConsultorCompany;
import com.legalplus.liferay.portlet.legalplus.manager.service.ConsultorCompanyLocalServiceUtil;
import com.legalplus.liferay.portlet.mailing.web.constants.MailingPortletKeys;
import com.legalplus.liferay.portlet.mailing.web.constants.MailingTemplateImageKeys;
import com.legalplus.liferay.portlet.mailing.web.mail.EvalRequisitoMailing;
import com.legalplus.liferay.portlet.scheduler.web.constants.SchedulerPortletKeys;
import com.liferay.petra.string.StringPool;
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
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.Validator;
import org.osgi.service.component.annotations.*;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Component(
        immediate = true,
        property = {
                "cron.expression= 0 30 23 5 * ? *"   // scheduler runs every month 5th at 23:30h.
        },
        service = InformeEvalRequisitosScheduler.class
)
public class InformeEvalRequisitosScheduler extends BaseMessageListener {

    public static Log _log = LogFactoryUtil.getLog(InformeMensualLegislacionesScheduler.class);

    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    static SimpleDateFormat dateFormatLog = new SimpleDateFormat("yyyy-dd-MM HH:mm:ss.SSS");

    private static final long PREMIUM = 3;

    private volatile boolean initialized;
    private SchedulerEngineHelper schedulerEngineHelper;
    private SchedulerEntryImpl schedulerEntryImpl = null;


    @Override
    protected void doReceive(Message message) throws Exception {

        //Obtener empresas premium/advance Legal+
        Application application = ApplicationLocalServiceUtil.getApplicationByName(LegalPlusCommonsPortletKeys.PROPERTY_APP_LEGAL);
        //List<CompApplication> companies = CompApplicationLocalServiceUtil.getCompaniesWithApplication(application.getApplicationId());
        List<CompClientApplication> companies = CompClientApplicationLocalServiceUtil.getIdCompanyByApplicationAndLicense(application.getApplicationId(),PREMIUM);

        //Mes
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);

        Calendar month = Calendar.getInstance();
        month.add(Calendar.DAY_OF_MONTH, -35);
        String monthStr = String.valueOf( month.getTimeInMillis() );

        Calendar today = Calendar.getInstance();
        month.add(Calendar.DAY_OF_MONTH, 0);
        String todayStr = String.valueOf( today.getTimeInMillis() );
        Date now=new Date();
        //Enviar email a los clientes premium
        for (CompClientApplication company : companies) {

            Comp comp = CompLocalServiceUtil.fetchComp(company.getEmpresaId());
            Calendar calendarEndCompany = Calendar.getInstance();
            if(Validator.isNotNull(company.getDeleteDate())) {
                calendarEndCompany.setTime(company.getDeleteDate());
            }else{
                calendarEndCompany.setTime(now);
            }
            calendarEndCompany.add(Calendar.DAY_OF_YEAR, 30);
            Date fechaCaducidad = calendarEndCompany.getTime();
            if(Validator.isNull(company.getDeleteDate()) || fechaCaducidad.after(now)) {
                //List<UserCompany> users = UserCompanyLocalServiceUtil.getUsersFromCompany(company.getCompId());
                List<UserCompany> users =  GetAllUsersByCompanyAndAppLegal.getAllUserActiveByApp(company.getCompId());

                ConsultorCompany consultorCompany = ConsultorCompanyLocalServiceUtil.fetchByCompId(comp.getCompId()).stream().findFirst().orElse(null);
                User consultor = consultorCompany != null ? UserLocalServiceUtil.fetchUser(consultorCompany.getUserId()) : null;

                for (UserCompany userCompany : users) {
                    User user = UserLocalServiceUtil.fetchUser(userCompany.getUserId());

                    ResourceBundle bundle = ResourceBundle.getBundle("content/Language", user.getLocale());

                    EvalRequisitoMailing.informeMensualEvalRequisitos(
                                user.getEmailAddress(),
                                user,
                                comp.getName(),
                                calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, user.getLocale()),
                                consultor != null ? consultor.getFullName() : StringPool.BLANK,
                                consultor != null ? consultor.getEmailAddress() : StringPool.BLANK,
                                MailingTemplateImageKeys.MAIL_TEMPLATE_SITE +
                                        LanguageUtil.format(bundle, "evaluacion.dates.cliente.url", new String[]{monthStr, todayStr}));

                }
            }
        }

    }

    @Activate
    @Modified
    protected void activate(Map<String, Object> properties) throws SchedulerException {

        try {

            String cronExpression = "0 30 23 5 * ? *"; // scheduler runs every month 5th at 23:30h.
            //pruebas:
            //String cronExpression = "0 1/1 * 1/1 * ? *"; // Cada minuto
            //String cronExpression = "0 0/2 * * * ? *"; // Cada 10 mins

            _log.info("InformeEvalRequisitosScheduler cronExpression: " + cronExpression);

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


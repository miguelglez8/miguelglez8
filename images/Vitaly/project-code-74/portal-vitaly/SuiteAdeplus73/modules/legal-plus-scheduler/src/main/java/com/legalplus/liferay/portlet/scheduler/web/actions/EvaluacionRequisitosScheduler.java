package com.legalplus.liferay.portlet.scheduler.web.actions;

import com.adeplus.liferay.portlet.suite.manager.model.*;
import com.adeplus.liferay.portlet.suite.manager.service.*;
import com.legalplus.liferay.portlet.commons.web.constants.LegalPlusCommonsPortletKeys;
import com.legalplus.liferay.portlet.legalplus.manager.model.*;
import com.legalplus.liferay.portlet.legalplus.manager.service.ConsultorCompanyLocalServiceUtil;
import com.legalplus.liferay.portlet.legalplus.manager.service.EvalRequisitoLocalServiceUtil;
import com.legalplus.liferay.portlet.legalplus.manager.service.LegislacionLocalServiceUtil;
import com.legalplus.liferay.portlet.legalplus.manager.service.RequisitoLocalServiceUtil;
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
import com.liferay.portal.kernel.util.Validator;
import org.osgi.service.component.annotations.*;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Component(
        immediate = true,
        property = {
                "cron.expression= 0 30 22 * * ? *"   // scheduler runs every day at 22:30h.
        },
        service = EvaluacionRequisitosScheduler.class
)
public class EvaluacionRequisitosScheduler extends BaseMessageListener {

    public static Log _log = LogFactoryUtil.getLog(InformeMensualLegislacionesScheduler.class);

    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    static SimpleDateFormat dateFormatLog = new SimpleDateFormat("yyyy-dd-MM HH:mm:ss.SSS");

    private static final long ADVANCED = 2;
    private static final long PREMIUM = 3;

    private volatile boolean initialized;
    private SchedulerEngineHelper schedulerEngineHelper;
    private SchedulerEntryImpl schedulerEntryImpl = null;

    @Override
    protected void doReceive(Message message) throws Exception {

        //Obtener empresas premium/advance Legal+
        Application application = ApplicationLocalServiceUtil.getApplicationByName(LegalPlusCommonsPortletKeys.PROPERTY_APP_LEGAL);
        //List<CompApplication> companies = CompApplicationLocalServiceUtil.getCompaniesWithApplication(application.getApplicationId());
        //companies = companies.stream().filter(comp -> comp.getLicenseId() == ADVANCED || comp.getLicenseId() == PREMIUM).collect(Collectors.toList());
        List<CompClientApplication> companies = CompClientApplicationLocalServiceUtil.getIdCompanyByApplicationAndLicense(application.getApplicationId(),PREMIUM);

        //Enviar email evalRequisitos fechaEvaluacion 1 día antes
        Calendar  calendarDay = Calendar.getInstance();
        calendarDay.setTime(new Date());
        calendarDay.add(Calendar.DATE, 1);
        calendarDay.set(Calendar.HOUR_OF_DAY, 0);
        calendarDay.set(Calendar.MINUTE, 0);
        calendarDay.set(Calendar.SECOND, 0);
        calendarDay.set(Calendar.MILLISECOND, 0);
        Date tomorrow = calendarDay.getTime();
        String tomorrowStr = String.valueOf( calendarDay.getTimeInMillis() );

        //Enviar email evalRequisitos fechaEvaluacion 7 días antes
        Calendar calendarWeek = Calendar.getInstance();
        calendarWeek.setTime(new Date());
        calendarWeek.add(Calendar.DAY_OF_YEAR, 7);
        calendarWeek.set(Calendar.HOUR_OF_DAY, 0);
        calendarWeek.set(Calendar.MINUTE, 0);
        calendarWeek.set(Calendar.SECOND, 0);
        calendarWeek.set(Calendar.MILLISECOND, 0);
        Date week = calendarWeek.getTime();
        String weekStr = String.valueOf( calendarWeek.getTimeInMillis() );


        Date today=new Date();
        for (CompClientApplication company : companies) {

            //Enviar email evalRequisitos fechaEvaluacion 1 día antes
            List<EvalRequisito> tomorrowReq = EvalRequisitoLocalServiceUtil.getEvalRequisitoByFechaComp(tomorrow, company.getEmpresaId());

            //Enviar email evalRequisitos fechaEvaluacion 7 días antes
            List<EvalRequisito> weekReq = EvalRequisitoLocalServiceUtil.getEvalRequisitoByFechaComp(week, company.getEmpresaId());

            _log.info("tomorrowReq: " + tomorrowReq.size());
            _log.info("weekReq: " + weekReq.size());

            //Usuarios empresa
            //List<UserCompany> users = UserApplicationClientLocalServiceUtil.getUsersByEmpresaIdAndApplicationWithLicense(SchedulerPortletKeys.ID_LONG_APP_LEGAL,0, company.getEmpresaId());
            List<UserCompany> users =  GetAllUsersByCompanyAndAppLegal.getAllUserActiveByApp(company.getCompId());

            if(users.size() > 0 ){
                _log.info("companyId: " + company.getCompId() + " / users.size(): " + users.size());
            }

            //Empresa
            Comp comp = CompLocalServiceUtil.fetchComp(company.getEmpresaId());
            //añadir 30 dias a la fecha de caducidad de la empresa
            Calendar calendarEndCompany = Calendar.getInstance();
            if(Validator.isNotNull(company.getDeleteDate())) {
                calendarEndCompany.setTime(company.getDeleteDate());
            }else{
                calendarEndCompany.setTime(today);
            }
            calendarEndCompany.add(Calendar.DAY_OF_YEAR, 30);
            Date fechaCaducidad = calendarEndCompany.getTime();
            if( Validator.isNotNull(comp) && (Validator.isNull(company.getDeleteDate()) || fechaCaducidad.after(today)) ) {

                String compStr = String.valueOf( comp.getCompId() );

                //Consultor/es
                List<ConsultorCompany> consultoresCompany = ConsultorCompanyLocalServiceUtil.fetchByCompId(comp.getCompId());
                ConsultorCompany consultorCompany = consultoresCompany.stream().findFirst().orElse(null);
                User consultor = consultorCompany != null ? UserLocalServiceUtil.fetchUser(consultorCompany.getUserId()) : null;



                //Enviar email
                if (!tomorrowReq.isEmpty() || !weekReq.isEmpty()) {

                    _log.info("entra john week");

                    //Email today
                    if (!tomorrowReq.isEmpty()) {

                        _log.info("entra tomorrow land");

                        StringBuilder sbRequisitos = new StringBuilder();
                        for (EvalRequisito evalRequisito : tomorrowReq) {
                            Requisito requisito = RequisitoLocalServiceUtil.findByLegislacionRequisito(evalRequisito.getLegislacionId(), evalRequisito.getRequisitoId());
                            Legislacion legislacion = LegislacionLocalServiceUtil.fetchLegislacion(requisito.getLegislacionId());

                            sbRequisitos.append("<p><b>" + legislacion.getNombre() + "</b></p>");
                            sbRequisitos.append("<p>" + requisito.getDescripcion() + "</p>");
                        }

                        for (UserCompany user : users) {
                            //if(company.getEmpresaId() == 30303){
                                _log.info("user: " + user.getEmail() + " / " + user.getUserEndDate());
                            //}
                            if(Validator.isNull(user.getUserEndDate())) {
                                User curUser = UserLocalServiceUtil.fetchUser(user.getUserId());
                                ResourceBundle bundle = ResourceBundle.getBundle("content/Language", curUser.getLocale());

                                StringBuilder sbTomorrow = new StringBuilder();
                                sbTomorrow.append("<p>" + LanguageUtil.get(bundle, "evaluacion.proxima.tomorrow") + "</p>");
                                sbTomorrow.append(sbRequisitos);

                                EvalRequisitoMailing.proximaFechaEvaluacionDiaria(
                                        user.getEmail(),
                                        curUser,
                                        comp.getName(),
                                        sbTomorrow.toString(),
                                        consultor != null ? consultor.getFullName() : StringPool.BLANK,
                                        consultor != null ? consultor.getEmailAddress() : StringPool.BLANK,
                                        MailingTemplateImageKeys.MAIL_TEMPLATE_SITE +
                                                LanguageUtil.format(bundle, "evaluacion.dates.cliente.url", new String[]{tomorrowStr, tomorrowStr}));
                            }//end userEndDate

                        }

                        for (ConsultorCompany cons : consultoresCompany) {
                            User userConsultor = UserLocalServiceUtil.fetchUser(cons.getUserId());
                            ResourceBundle bundle = ResourceBundle.getBundle("content/Language", userConsultor.getLocale());

                            StringBuilder sbTomorrow = new StringBuilder();
                            sbTomorrow.append("<p>" + LanguageUtil.get(bundle, "evaluacion.proxima.tomorrow") + "</p>");
                            sbTomorrow.append(sbRequisitos);

                            EvalRequisitoMailing.proximaFechaEvaluacionDiaria(
                                    userConsultor.getEmailAddress(),
                                    userConsultor,
                                    comp.getName(),
                                    sbTomorrow.toString(),
                                    consultor != null ? consultor.getFullName() : StringPool.BLANK,
                                    consultor != null ? consultor.getEmailAddress() : StringPool.BLANK,
                                    MailingTemplateImageKeys.MAIL_TEMPLATE_SITE +
                                            LanguageUtil.format(bundle, "evaluacion.dates.consultor.url", new String[]{compStr, tomorrowStr, tomorrowStr}));
                        }

                    }


                    //Email week
                    if (!weekReq.isEmpty()) {

                        StringBuilder sbRequisitos = new StringBuilder();
                        for (EvalRequisito evalRequisito : weekReq) {
                            Requisito requisito = RequisitoLocalServiceUtil.findByLegislacionRequisito(evalRequisito.getLegislacionId(), evalRequisito.getRequisitoId());
                            Legislacion legislacion = LegislacionLocalServiceUtil.fetchLegislacion(requisito.getLegislacionId());

                            sbRequisitos.append("<p><b>" + legislacion.getNombre() + "</b></p>");
                            sbRequisitos.append("<p>" + requisito.getDescripcion() + "</p>");
                        }

                        for (UserCompany user : users) {
                            if(Validator.isNull(user.getUserEndDate())) {
                                User curUser = UserLocalServiceUtil.fetchUser(user.getUserId());
                                ResourceBundle bundle = ResourceBundle.getBundle("content/Language", curUser.getLocale());

                                StringBuilder sbWeek = new StringBuilder();
                                sbWeek.append("<p>" + LanguageUtil.format(bundle, "evaluacion.proxima.week", dateFormat.format(week)) + "</p>");
                                sbWeek.append(sbRequisitos);

                                EvalRequisitoMailing.proximaFechaEvaluacionSemanal(
                                        user.getEmail(),
                                        curUser,
                                        comp.getName(),
                                        sbWeek.toString(),
                                        consultor != null ? consultor.getFullName() : StringPool.BLANK,
                                        consultor != null ? consultor.getEmailAddress() : StringPool.BLANK,
                                        MailingTemplateImageKeys.MAIL_TEMPLATE_SITE +
                                                LanguageUtil.format(bundle, "evaluacion.dates.cliente.url", new String[]{weekStr, weekStr}));
                            }//end userEndDate

                        }

                        for (ConsultorCompany cons : consultoresCompany) {
                            User userConsultor = UserLocalServiceUtil.fetchUser(cons.getUserId());
                            ResourceBundle bundle = ResourceBundle.getBundle("content/Language", userConsultor.getLocale());

                            StringBuilder sbWeek = new StringBuilder();
                            sbWeek.append("<p>" + LanguageUtil.format(bundle, "evaluacion.proxima.week", dateFormat.format(week)) + "</p>");
                            sbWeek.append(sbRequisitos);

                            EvalRequisitoMailing.proximaFechaEvaluacionSemanal(
                                    userConsultor.getEmailAddress(),
                                    userConsultor,
                                    comp.getName(),
                                    sbWeek.toString(),
                                    consultor != null ? consultor.getFullName() : StringPool.BLANK,
                                    consultor != null ? consultor.getEmailAddress() : StringPool.BLANK,
                                    MailingTemplateImageKeys.MAIL_TEMPLATE_SITE +
                                            LanguageUtil.format(bundle, "evaluacion.dates.consultor.url", new String[]{compStr, weekStr, weekStr}));
                        }

                    }

                }

                //Enviar email evalRequisitos fechaEvaluacion 15 días después
                List<EvalRequisito> requisitos = EvalRequisitoLocalServiceUtil.getPeriodoQuincenalByFechaComp(company.getEmpresaId());
                if (!requisitos.isEmpty()) {

                    StringBuilder sbRequisitos = new StringBuilder();
                    sbRequisitos.append("<ul>");
                    for (EvalRequisito requisito : requisitos) {
                        Requisito curRequisitos = RequisitoLocalServiceUtil.findByLegislacionRequisito(requisito.getLegislacionId(), requisito.getRequisitoId());
                        sbRequisitos.append("<li>" + dateFormat.format(requisito.getFecha()) + " - " + curRequisitos.getDescripcion() + "</li>");
                    }
                    sbRequisitos.append("</ul>");

                    //Email usuario
                    for (UserCompany user : users) {
                        if(Validator.isNull(user.getUserEndDate())) {
                            User curUser = UserLocalServiceUtil.fetchUser(user.getUserId());
                            ResourceBundle bundle = ResourceBundle.getBundle("content/Language", curUser.getLocale());

                            EvalRequisitoMailing.periodoFechaEvaluacion(
                                    user.getEmail(),
                                    curUser,
                                    comp.getName(),
                                    sbRequisitos.toString(),
                                    consultor != null ? consultor.getFullName() : StringPool.BLANK,
                                    consultor != null ? consultor.getEmailAddress() : StringPool.BLANK,
                                    MailingTemplateImageKeys.MAIL_TEMPLATE_SITE +
                                            LanguageUtil.get(bundle, "evaluacion.eval.cliente.url"));
                        }//end userEndDate

                    }

                    //Email consultores
                    for (ConsultorCompany cons : consultoresCompany) {
                        User userConsultor = UserLocalServiceUtil.fetchUser(cons.getUserId());
                        ResourceBundle bundle = ResourceBundle.getBundle("content/Language", userConsultor.getLocale());

                        EvalRequisitoMailing.periodoFechaEvaluacion(
                                userConsultor.getEmailAddress(),
                                userConsultor,
                                comp.getName(),
                                sbRequisitos.toString(),
                                consultor != null ? consultor.getFullName() : StringPool.BLANK,
                                consultor != null ? consultor.getEmailAddress() : StringPool.BLANK,
                                MailingTemplateImageKeys.MAIL_TEMPLATE_SITE +
                                        LanguageUtil.format(bundle, "evaluacion.eval.consultor.url", new String[]{compStr}));
                    }
                }
            }
        }

    }


    @Activate
    @Modified
    protected void activate(Map<String, Object> properties) throws SchedulerException {

        try {

            String cronExpression = "0 30 22 * * ? *"; // scheduler runs every day at 22:30h.
            //pruebas:
            //String cronExpression = "0 1/1 * 1/1 * ? *"; // Cada minuto
            //String cronExpression = "0 0/2 * * * ? *"; // Cada 10 mins

            _log.info("EvaluacionRequisitosScheduler cronExpression: " + cronExpression);

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

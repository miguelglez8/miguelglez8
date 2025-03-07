package com.canal.etico.liferay.portlet.scheduler.web.messages;

import com.canal.etico.liferay.portlet.canal.manager.model.Denuncia;
import com.canal.etico.liferay.portlet.canal.manager.model.UserCompany;
import com.canal.etico.liferay.portlet.canal.manager.service.DenunciaAccionLocalServiceUtil;
import com.canal.etico.liferay.portlet.canal.manager.service.DenunciaLocalServiceUtil;
import com.canal.etico.liferay.portlet.canal.manager.service.UserCompanyLocalServiceUtil;
import com.canal.etico.liferay.portlet.scheduler.web.constants.TareasProgramadasPortletKeys;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.scheduler.*;
import org.osgi.service.component.annotations.*;

import java.text.SimpleDateFormat;
import java.util.*;

@Component(
        immediate = true,
        property = {
                "cron.expression= 0 0/01 1/1 1/1 * ?"   // scheduler runs every 1 min.
        },
        service = FinalizarDenuncia7diasAntesScheduler.class
)
public class FinalizarDenuncia7diasAntesScheduler extends BaseMessageListener {

    public static Log _log = LogFactoryUtil.getLog(FinalizarDenuncia7diasAntesScheduler.class);

    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    static SimpleDateFormat dateFormatLog = new SimpleDateFormat("yyyy-dd-MM HH:mm:ss.SSS");

    private volatile boolean initialized;
    private SchedulerEngineHelper schedulerEngineHelper;
    private SchedulerEntryImpl schedulerEntryImpl = null;

    @Override
    protected void doReceive(Message message) throws Exception {

        Date date = new Date();

        ResourceBundle bundle = ResourceBundle.getBundle("content/Language", new Locale("es","ES"));
        String subject  	= LanguageUtil.get(bundle, "scheduler.message.finalizar.aviso.subject");
        String registrado  	= LanguageUtil.get(bundle, "scheduler.message.finalizar.aviso.estado.registrado");
        String enProceso  	= LanguageUtil.get(bundle, "scheduler.message.finalizar.aviso.estado.en.proceso");
        String finalizado  	= LanguageUtil.get(bundle, "scheduler.message.finalizar.aviso.estado.finalizado");
        String sinFinalizar = LanguageUtil.get(bundle, "scheduler.message.finalizar.aviso.estado.sin.finalizar");

        Calendar denunciaCreateDate = Calendar.getInstance();

        denunciaCreateDate.setTime(date);
        denunciaCreateDate.add(Calendar.DAY_OF_YEAR, -83);

        System.out.println(dateFormatLog.format(new Date()) + " SCHEDULER START ============================================================");
        System.out.println(dateFormatLog.format(new Date()) + " SCHEDULER FinalizarDenuncia7diasAntesScheduler. Enviar mail a los administradores de denuncias creadas en " + dateFormat.format(denunciaCreateDate.getTime()) + ".");

        List<Denuncia> denuncias = DenunciaLocalServiceUtil.getDenunciasNotFinishedCreatedInDate(denunciaCreateDate.getTime());

        for(Denuncia denuncia:denuncias){

            if(denuncia.getEstado() != 2 && denuncia.getEstado() != 3) {

                String estado = registrado;
                if(denuncia.getEstado() == TareasProgramadasPortletKeys.ESTADO_EN_PROCESO){
                    estado = enProceso;
                }else if(denuncia.getEstado() == TareasProgramadasPortletKeys.ESTADO_FINALIZADO){
                    estado = finalizado;
                }else if(denuncia.getEstado() == TareasProgramadasPortletKeys.ESTADO_SIN_FINALIZAR){
                    estado = sinFinalizar;
                }

                String accionesHtmlFromDenuncia = DenunciaAccionLocalServiceUtil.getAccionesHtmlFromDenuncia(denuncia.getDenunciaId());

                // Send mail to administrators.
                List<UserCompany> usersFromCompany = UserCompanyLocalServiceUtil.getUsersFromCompany(denuncia.getCompId());
                for (UserCompany uc : usersFromCompany) {

                   /* DenunciaMailing.finalizacionDenunciaAvisoToAdmin(
                            uc.getUserId(),
                            denuncia.getCodigo(),
                            dateFormat.format(denuncia.getCreateDate()),
                            estado,
                            accionesHtmlFromDenuncia,
                            new Locale("es","ES"));*/
                }

                System.out.println(dateFormatLog.format(new Date()) + " Enviado aviso de " + denuncia.getCodigo() + " a los administradores. Fecha creacion " + dateFormat.format(denuncia.getCreateDate()));

            }
        }

        System.out.println(dateFormatLog.format(new Date()) + " SCHEDULER END ============================================================");
    }


    @Activate
    @Modified
    protected void activate(Map<String, Object> properties) throws SchedulerException {

        try {

            String cronExpression = "0 30 15 * * ? *"; //Todos los días a las 15:30h
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

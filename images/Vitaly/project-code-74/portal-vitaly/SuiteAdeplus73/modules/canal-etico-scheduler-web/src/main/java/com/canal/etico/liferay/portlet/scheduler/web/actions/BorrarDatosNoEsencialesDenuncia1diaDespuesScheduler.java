package com.canal.etico.liferay.portlet.scheduler.web.actions;

import com.canal.etico.liferay.portlet.canal.manager.model.Denuncia;
import com.canal.etico.liferay.portlet.canal.manager.service.DenunciaLocalServiceUtil;
import com.canal.etico.liferay.portlet.commons.web.denuncia.CanalEticoDenunciaUtil;
import com.canal.etico.liferay.portlet.scheduler.web.constants.TareasProgramadasPortletKeys;
import com.canal.etico.v2.liferay.portlet.canal.manager.model.Comunicado;
import com.canal.etico.v2.liferay.portlet.canal.manager.service.ComunicadoLocalServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.scheduler.*;
import org.osgi.service.component.annotations.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Component(
        immediate = true,
        property = {
                "cron.expression= 0 0/01 1/1 1/1 * ?"   // scheduler runs every 1 min.
        },
        service = BorrarDatosNoEsencialesDenuncia1diaDespuesScheduler.class
)
public class BorrarDatosNoEsencialesDenuncia1diaDespuesScheduler extends BaseMessageListener {

    public static Log _log = LogFactoryUtil.getLog(BorrarDatosNoEsencialesDenuncia1diaDespuesScheduler.class);

    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    static SimpleDateFormat dateFormatLog = new SimpleDateFormat("yyyy-dd-MM HH:mm:ss.SSS");

    private volatile boolean initialized;
    private SchedulerEngineHelper schedulerEngineHelper;
    private SchedulerEntryImpl schedulerEntryImpl = null;

    @Override
    protected void doReceive(Message message) throws Exception {

        Date date = new Date();

        Calendar denunciaEndDate = Calendar.getInstance();

        denunciaEndDate.setTime(date);
        denunciaEndDate.add(Calendar.DAY_OF_YEAR, -1);

        _log.info(dateFormatLog.format(new Date()) + " SCHEDULER START ============================================================");
        _log.debug(dateFormatLog.format(new Date()) + " SCHEDULER BorrarDatosNoEsencialesDenuncia1diaDespuesScheduler. Borrar datos no esenciales " + dateFormat.format(denunciaEndDate.getTime()) + ".");

        List<Comunicado> comunicados = ComunicadoLocalServiceUtil.getComunicadosFinishedInDate(denunciaEndDate.getTime());

        for(Comunicado comunicado:comunicados){

            if(comunicado.getEstado() == 4 || comunicado.getEstado() == 5) {

                CanalEticoDenunciaUtil.borrarDatosNoEsencialesComunicado( comunicado.getComunicadoId(), comunicado.getCodigo());

                _log.debug(dateFormatLog.format(new Date()) + " Borrados datos no esenciales de " + comunicado.getCodigo() + ". Fecha creacion: " + dateFormat.format(comunicado.getCreateDate())+ ". Fecha fin: " + dateFormat.format(comunicado.getEndDate()));

            }
        }

        List<Comunicado> comunicadosCaducados = ComunicadoLocalServiceUtil.getComunicadosCaducadoInDate(denunciaEndDate.getTime());

        for(Comunicado comunicado:comunicadosCaducados){

            if(comunicado.getEstado() == 4 || comunicado.getEstado() == 5) {

                CanalEticoDenunciaUtil.borrarDatosNoEsencialesComunicado( comunicado.getComunicadoId(), comunicado.getCodigo());

                //_log.debug(dateFormatLog.format(new Date()) + " Borrados datos no esenciales de " + comunicado.getCodigo() + ". Fecha creacion: " + dateFormat.format(comunicado.getCreateDate())+ ". Fecha fin: " + dateFormat.format(comunicado.getEndDate()));

            }
        }

        _log.debug(dateFormatLog.format(new Date()) + " SCHEDULER END ============================================================");
    }


    @Activate
    @Modified
    protected void activate(Map<String, Object> properties) throws SchedulerException {

        try {

            String cronExpression = "0 00 01 * * ? *"; //Todos los días a las 01:00h
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

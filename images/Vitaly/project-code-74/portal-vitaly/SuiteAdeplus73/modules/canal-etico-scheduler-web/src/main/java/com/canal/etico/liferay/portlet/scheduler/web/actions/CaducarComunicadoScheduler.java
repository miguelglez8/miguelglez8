package com.canal.etico.liferay.portlet.scheduler.web.actions;

import com.canal.etico.liferay.portlet.commons.web.constants.CanalEticoCommonsPortletKeys;
import com.canal.etico.liferay.portlet.mailing.web.v2.mail.ComunicadoMailing;
import com.canal.etico.liferay.portlet.mailing.web.v2.mail.TaskComunicados;
import com.canal.etico.v2.liferay.portlet.canal.manager.model.Comunicado;
import com.canal.etico.v2.liferay.portlet.canal.manager.service.ComunicadoLocalServiceUtil;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskManagerUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.scheduler.*;

import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.util.Validator;
import org.osgi.service.component.annotations.*;

import java.text.SimpleDateFormat;
import java.util.*;

@Component(
        immediate = true,
        property = {
                "cron.expression= 0 0/01 1/1 1/1 * ?"   // scheduler runs every 1 min.
        },
        service = CaducarComunicadoScheduler.class
)
public class CaducarComunicadoScheduler extends BaseMessageListener {
    public static Log _log = LogFactoryUtil.getLog(CaducarComunicadoScheduler.class);

    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    static SimpleDateFormat dateFormatLog = new SimpleDateFormat("yyyy-dd-MM HH:mm:ss.SSS");

    private volatile boolean initialized;
    private SchedulerEngineHelper schedulerEngineHelper;
    private SchedulerEntryImpl schedulerEntryImpl = null;
    @Override
    protected void doReceive(Message message) throws Exception {

        long idRole = 0;
        List<Role> roles = RoleLocalServiceUtil.getRoles(QueryUtil.ALL_POS,QueryUtil.ALL_POS);
        for (Role role : roles){
            if(role.getName().equals("Administrator")){
                idRole = role.getRoleId();
                break;
            }
        }
        List<User> lstUsers = UserLocalServiceUtil.getRoleUsers(idRole);
        User user = (lstUsers.size() > 0)? lstUsers.get(0) : null;
        _log.info("#### usuario admin envio : " + user.getEmailAddress());


        Date date = new Date();
        Calendar denunciaEndDate = Calendar.getInstance();
        //User user = UserLocalServiceUtil.getUser(20125);


        denunciaEndDate.setTime(date);
        List<Comunicado> comunicadosCaducados = ComunicadoLocalServiceUtil.getComunicadosCaducadoInDate(denunciaEndDate.getTime());
        Map map = null;
        for(Comunicado comunicado:comunicadosCaducados){
            if(Validator.isNull(comunicado.getEndDate()) && comunicado.getEstado()!=5) {
                comunicado.setEstado(5);
                ComunicadoLocalServiceUtil.updateComunicado(comunicado);
                ComunicadoMailing.comunicadoCaducado(user, comunicado);
            }


        }

    }

    @Activate
    @Modified
    protected void activate(Map<String, Object> properties) throws SchedulerException {

        try {

            String cronExpression = "0 00 01 * * ? *"; //Todos los días a las 23:45h
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

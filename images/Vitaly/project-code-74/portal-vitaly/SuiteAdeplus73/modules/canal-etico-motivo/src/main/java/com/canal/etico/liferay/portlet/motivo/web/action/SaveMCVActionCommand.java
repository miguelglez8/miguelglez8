package com.canal.etico.liferay.portlet.motivo.web.action;

import com.canal.etico.liferay.portlet.canal.manager.model.Motivo;
import com.canal.etico.liferay.portlet.canal.manager.service.MotivoLocalServiceUtil;
import com.canal.etico.liferay.portlet.motivo.web.constants.MotivoPortletKeys;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + MotivoPortletKeys.MOTIVO,
                "mvc.command.name=/motivo/save"
        },
        service = MVCActionCommand.class
)
public class SaveMCVActionCommand implements MVCActionCommand {

        private static Log _log = LogFactoryUtil.getLog(SaveMCVActionCommand.class);

        @Override
        public boolean processAction(ActionRequest actionRequest, ActionResponse actionResponse) throws PortletException {

                long motivoEditId = ParamUtil.getLong(actionRequest, MotivoPortletKeys.MOTIVO_ID_EDIT, 0);
                long companyId = ParamUtil.getLong(actionRequest, MotivoPortletKeys.COMPANY_ID, 0);
                String name = ParamUtil.getString(actionRequest, MotivoPortletKeys.NAME, "");
                String observaciones = ParamUtil.getString(actionRequest, MotivoPortletKeys.OBSERVATIONS, "");
                String activeStr = ParamUtil.getString(actionRequest, MotivoPortletKeys.ACTIVE, "");

                //System.out.println("Motivos finalización SaveMCVActionCommand: motivoEditId " + motivoEditId + ", compId: " + companyId);

                boolean active = false;
                if(MotivoPortletKeys.ACTIVE_YES.equals(activeStr)){
                        active = true;
                }

                Motivo motivo = null;
                // New.
                if(motivoEditId == 0){
                        motivo = MotivoLocalServiceUtil.createMotivo(CounterLocalServiceUtil.increment(Motivo.class.getName()));

                //Update.
                }else{
                        motivo = MotivoLocalServiceUtil.fetchMotivo(motivoEditId);
                }

                if(Validator.isNotNull(motivo)) {

                        motivo.setCompId(companyId);
                        motivo.setNombre(name);
                        motivo.setObservaciones(observaciones);
                        motivo.setActivo(active);

                        MotivoLocalServiceUtil.updateMotivo(motivo);
                }

                return true;
        }
}

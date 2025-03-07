package com.canal.etico.liferay.portlet.accion.web.action;

import com.canal.etico.liferay.portlet.accion.web.constants.AccionPortletKeys;
import com.canal.etico.liferay.portlet.canal.manager.model.Accion;
import com.canal.etico.liferay.portlet.canal.manager.service.AccionLocalServiceUtil;
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
                "javax.portlet.name=" + AccionPortletKeys.ACCION,
                "mvc.command.name=/accion/save"
        },
        service = MVCActionCommand.class
)
public class SaveMCVActionCommand implements MVCActionCommand {

        private static Log _log = LogFactoryUtil.getLog(SaveMCVActionCommand.class);

        @Override
        public boolean processAction(ActionRequest actionRequest, ActionResponse actionResponse) throws PortletException {

                long accionEditId = ParamUtil.getLong(actionRequest, AccionPortletKeys.ACCION_ID_EDIT, 0);
                long companyId = ParamUtil.getLong(actionRequest, AccionPortletKeys.COMPANY_ID, 0);
                String global = ParamUtil.getString(actionRequest, AccionPortletKeys.GLOBAL, "");
                String name = ParamUtil.getString(actionRequest, AccionPortletKeys.NAME, "");
                long estado = ParamUtil.getLong(actionRequest, AccionPortletKeys.ESTADO, 0);
                String activeStr = ParamUtil.getString(actionRequest, AccionPortletKeys.ACTIVE, "");

                System.out.println("Accion SaveMCVActionCommand: accionEditId " + accionEditId + ", compId: " + companyId);

                boolean active = false;
                if(AccionPortletKeys.ACTIVE_YES.equals(activeStr)){
                        active = true;
                }

                Accion accion = null;
                // New.
                if(accionEditId == 0){
                        accion = AccionLocalServiceUtil.createAccion(CounterLocalServiceUtil.increment(Accion.class.getName()));

                //Update.
                }else{
                        accion = AccionLocalServiceUtil.fetchAccion(accionEditId);
                }

                if(Validator.isNotNull(accion)) {

                        accion.setCompId(companyId);
                        accion.setNombre(name);
                        accion.setEstado(estado);
                        accion.setActivo(active);

                        AccionLocalServiceUtil.updateAccion(accion);
                }

                return true;
        }
}

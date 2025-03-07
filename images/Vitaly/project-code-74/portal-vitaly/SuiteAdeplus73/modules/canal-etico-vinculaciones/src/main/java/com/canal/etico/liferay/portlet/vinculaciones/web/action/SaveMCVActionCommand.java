package com.canal.etico.liferay.portlet.vinculaciones.web.action;

import com.canal.etico.liferay.portlet.canal.manager.model.Vinculacion;
import com.canal.etico.liferay.portlet.canal.manager.service.VinculacionLocalServiceUtil;
import com.canal.etico.liferay.portlet.vinculaciones.web.constants.VinculacionesPortletKeys;
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
                "javax.portlet.name=" + VinculacionesPortletKeys.VINCULACIONES,
                "mvc.command.name=/vinculacion/save"
        },
        service = MVCActionCommand.class
)
public class SaveMCVActionCommand implements MVCActionCommand {

        private static Log _log = LogFactoryUtil.getLog(SaveMCVActionCommand.class);

        @Override
        public boolean processAction(ActionRequest actionRequest, ActionResponse actionResponse) throws PortletException {

                long vinculacionEditId 	= ParamUtil.getLong(actionRequest, VinculacionesPortletKeys.VINCULACION_ID_EDIT, 0);
                String name = ParamUtil.getString(actionRequest, VinculacionesPortletKeys.NAME, "");
                long companyId = ParamUtil.getLong(actionRequest, VinculacionesPortletKeys.COMPANY_ID, 0);
                String global = ParamUtil.getString(actionRequest, VinculacionesPortletKeys.GLOBAL, "");
                String activeStr = ParamUtil.getString(actionRequest, VinculacionesPortletKeys.ACTIVE, "");

                boolean active = false;
                if(VinculacionesPortletKeys.ACTIVE_YES.equals(activeStr)){
                        active = true;
                }

                Vinculacion vinculacion = null;
                // New vinculacion.
                if(vinculacionEditId == 0){

                        vinculacion = VinculacionLocalServiceUtil.createVinculacion(CounterLocalServiceUtil.increment(Vinculacion.class.getName()));

                //Update vinculación.
                }else{

                        vinculacion = VinculacionLocalServiceUtil.fetchVinculacion(vinculacionEditId);

                }

                if(Validator.isNotNull(vinculacion)) {

                        vinculacion.setNombre(name);
                        vinculacion.setActivo(active);
                        vinculacion.setCompId(companyId);


                        VinculacionLocalServiceUtil.updateVinculacion(vinculacion);
                }

                return true;
        }
}

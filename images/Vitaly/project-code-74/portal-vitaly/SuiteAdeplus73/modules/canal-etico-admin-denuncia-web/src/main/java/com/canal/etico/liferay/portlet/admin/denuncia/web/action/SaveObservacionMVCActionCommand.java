package com.canal.etico.liferay.portlet.admin.denuncia.web.action;

import com.canal.etico.liferay.portlet.admin.denuncia.web.constants.AdminDenunciaPortletKeys;
import com.canal.etico.liferay.portlet.canal.manager.model.Observacion;
import com.canal.etico.liferay.portlet.canal.manager.service.ObservacionLocalServiceUtil;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + AdminDenunciaPortletKeys.ADMINDENUNCIA,
                "mvc.command.name=/observacion/save"
        },
        service = MVCActionCommand.class
)
public class SaveObservacionMVCActionCommand implements MVCActionCommand {

    @Override
    public boolean processAction(ActionRequest actionRequest, ActionResponse actionResponse) throws PortletException {

        ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

        long denunciaId     = ParamUtil.getLong(actionRequest, AdminDenunciaPortletKeys.DENUNCIA_ID, 0);
        String observacionStr 	= ParamUtil.getString(actionRequest, AdminDenunciaPortletKeys.OBSERVACION, "");

        Observacion observacion = ObservacionLocalServiceUtil.createObservacion(CounterLocalServiceUtil.increment(Observacion.class.getName()));

        observacion.setDenunciaId(denunciaId);
        observacion.setUserId(themeDisplay.getUserId());
        observacion.setDescripcion(observacionStr);

        ObservacionLocalServiceUtil.updateObservacion(observacion);

        actionResponse.setRenderParameter( AdminDenunciaPortletKeys.DENUNCIA_ID, String.valueOf(denunciaId));
        actionResponse.setRenderParameter("jspPage", "/denuncia.jsp");

        return false;
    }
    
}

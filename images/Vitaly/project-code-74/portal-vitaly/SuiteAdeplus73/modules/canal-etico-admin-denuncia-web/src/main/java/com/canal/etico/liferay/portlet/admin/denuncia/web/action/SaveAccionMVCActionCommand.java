package com.canal.etico.liferay.portlet.admin.denuncia.web.action;

import com.canal.etico.liferay.portlet.admin.denuncia.web.constants.AdminDenunciaPortletKeys;
import com.canal.etico.liferay.portlet.canal.manager.model.Accion;
import com.canal.etico.liferay.portlet.canal.manager.model.Denuncia;
import com.canal.etico.liferay.portlet.canal.manager.model.DenunciaAccion;
import com.canal.etico.liferay.portlet.canal.manager.service.AccionLocalServiceUtil;
import com.canal.etico.liferay.portlet.canal.manager.service.DenunciaAccionLocalServiceUtil;
import com.canal.etico.liferay.portlet.canal.manager.service.DenunciaLocalServiceUtil;
import com.canal.etico.liferay.portlet.commons.web.estado.CanalEticoEstadoUtil;
import com.canal.etico.liferay.portlet.mailing.web.mail.DenunciaMailing;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + AdminDenunciaPortletKeys.ADMINDENUNCIA,
                "mvc.command.name=/accion/save"
        },
        service = MVCActionCommand.class
)
public class SaveAccionMVCActionCommand implements MVCActionCommand {

    @Override
    public boolean processAction(ActionRequest actionRequest, ActionResponse actionResponse) throws PortletException {

        ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

        long denunciaId = ParamUtil.getLong(actionRequest, AdminDenunciaPortletKeys.DENUNCIA_ID, 0);
        long accionId 	= ParamUtil.getLong(actionRequest, AdminDenunciaPortletKeys.ACCION, 0);
        String observaciones = ParamUtil.getString(actionRequest, AdminDenunciaPortletKeys.OBSERVACIONES_ACCION, "");

        if(denunciaId > 0 && accionId > 0) {

            Accion accion = AccionLocalServiceUtil.fetchAccion(accionId);
            Denuncia denuncia = DenunciaLocalServiceUtil.fetchDenuncia(denunciaId);

            if(Validator.isNotNull(accion) && Validator.isNotNull(denuncia)){

                //Create new accion for denuncia.
                DenunciaAccion denunciaAccion = DenunciaAccionLocalServiceUtil.createDenunciaAccion(CounterLocalServiceUtil.increment(DenunciaAccion.class.getName()));

                denunciaAccion.setAccionId(accionId);
                denunciaAccion.setDenunciaId(denunciaId);
                denunciaAccion.setUserId(themeDisplay.getUserId());
                denunciaAccion.setObservaciones(observaciones);

                DenunciaAccionLocalServiceUtil.addDenunciaAccion(denunciaAccion);

                //Update state of denuncia.
                denuncia.setEstado(accion.getEstado());
                DenunciaLocalServiceUtil.updateDenuncia(denuncia);

                //Send notification to user
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                dateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Madrid"));

                String estado = CanalEticoEstadoUtil.getNombreEstadoPorId(accion.getEstado(), themeDisplay.getLocale());

                DenunciaMailing.cambiarEstadoDenunciaToUser(
                        denuncia.getEmail(),
                        denuncia.getNombre(),
                        denuncia.getCodigo(),
                        dateFormat.format(new Date()),
                        estado,
                        accion.getNombre(),
                        themeDisplay.getLocale());
            }

        }

        actionResponse.setRenderParameter( AdminDenunciaPortletKeys.DENUNCIA_ID, String.valueOf(denunciaId));
        actionResponse.setRenderParameter("jspPage", "/denuncia.jsp");

        return false;
    }
    
}

package com.canal.etico.liferay.portlet.admin.denuncia.web.action;

import com.canal.etico.liferay.portlet.admin.denuncia.web.constants.AdminDenunciaPortletKeys;
import com.canal.etico.liferay.portlet.canal.manager.model.Denuncia;
import com.canal.etico.liferay.portlet.canal.manager.model.Motivo;
import com.canal.etico.liferay.portlet.canal.manager.model.UserCompany;
import com.canal.etico.liferay.portlet.canal.manager.service.DenunciaLocalServiceUtil;
import com.canal.etico.liferay.portlet.canal.manager.service.MotivoLocalServiceUtil;
import com.canal.etico.liferay.portlet.canal.manager.service.UserCompanyLocalServiceUtil;
import com.canal.etico.liferay.portlet.mailing.web.mail.DenunciaMailing;
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
import java.util.List;
import java.util.TimeZone;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + AdminDenunciaPortletKeys.ADMINDENUNCIA,
                "mvc.command.name=/accion/finalizar"
        },
        service = MVCActionCommand.class
)
public class SaveFinalizacionMVCActionCommand implements MVCActionCommand {

    @Override
    public boolean processAction(ActionRequest actionRequest, ActionResponse actionResponse) throws PortletException {

        ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

        long denunciaId = ParamUtil.getLong(actionRequest, AdminDenunciaPortletKeys.DENUNCIA_ID, 0);
        long motivoId	= ParamUtil.getLong(actionRequest, AdminDenunciaPortletKeys.MOTIVO, 0);
        String observaciones = ParamUtil.getString(actionRequest, AdminDenunciaPortletKeys.OBSERVACIONES_FINALIZACION, "");

        if(denunciaId > 0 && motivoId > 0) {

            Denuncia denuncia = DenunciaLocalServiceUtil.fetchDenuncia(denunciaId);
            Motivo motivo = MotivoLocalServiceUtil.fetchMotivo(motivoId);

            if(Validator.isNotNull(denuncia) && Validator.isNotNull(denuncia)) {

                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                dateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Madrid"));
                Date now = new Date();

                denuncia.setMotivoId(motivoId);
                denuncia.setEndDate(now);
                denuncia.setEstado(AdminDenunciaPortletKeys.ESTADO_FINALIZADO);
                denuncia.setObservaciones(observaciones);

                DenunciaLocalServiceUtil.updateDenuncia(denuncia);

                //TODO Borrar datos de la denuncia no necesarios.

                //Send finalizacion to user
                DenunciaMailing.finalizacionDenunciaToUser(
                        denuncia.getEmail(),
                        denuncia.getNombre(),
                        denuncia.getCodigo(),
                        dateFormat.format(now),
                        motivo.getNombre(themeDisplay.getLocale()),
                        motivo.getObservaciones(),
                        themeDisplay.getLocale());


                // Send mail to administrators.
                List<UserCompany> usersFromCompany = UserCompanyLocalServiceUtil.getUsersFromCompany(denuncia.getCompId());
                for (UserCompany uc : usersFromCompany) {

                    DenunciaMailing.finalizacionDenunciaToAdmin(
                            uc.getUserId(),
                            denuncia.getCodigo(),
                            dateFormat.format(now),
                            motivo.getNombre(themeDisplay.getLocale()),
                            motivo.getObservaciones(),
                            themeDisplay.getLocale());
                }
            }
        }

        actionResponse.setRenderParameter( AdminDenunciaPortletKeys.DENUNCIA_ID, String.valueOf(denunciaId));
        actionResponse.setRenderParameter("jspPage", "/success.jsp");

        return false;
    }
    
}

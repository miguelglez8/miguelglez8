package com.plan.igualdad.liferay.portlet.fechas.web.portlet.command;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.DateFormatFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.plan.igualdad.liferay.portlet.fechas.web.constants.PlanIgualdadFechasWebPortletKeys;
import com.plan.igualdad.liferay.portlet.fechas.web.util.FechasUtils;
import com.plan.igualdad.liferay.portlet.manager.model.FechaHito;
import com.plan.igualdad.liferay.portlet.manager.service.FechaHitoLocalServiceUtil;
import com.plan.igualdad.liferay.portlet.manager.service.persistence.FechaHitoPK;
import org.osgi.service.component.annotations.Component;

import javax.portlet.*;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + PlanIgualdadFechasWebPortletKeys.PLANIGUALDADFECHASWEB,
                "mvc.command.name=/date/save"
        },
        service = MVCActionCommand.class
)
public class SaveHitoActionCommand implements MVCActionCommand {

    private static Log _log = LogFactoryUtil.getLog(SaveHitoActionCommand.class);

    private static DateFormat dateFormat = DateFormatFactoryUtil.getSimpleDateFormat("dd/MM/yyyy");

    @Override
    public boolean processAction(ActionRequest actionRequest, ActionResponse actionResponse) throws PortletException {
        try {

            ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

            Long compId = ParamUtil.getLong(actionRequest, PlanIgualdadFechasWebPortletKeys.COMPID_PARAM, 0);
            Long versionId = ParamUtil.getLong(actionRequest, PlanIgualdadFechasWebPortletKeys.VERSIONID_PARAM, 0);
            Long hitoId = ParamUtil.getLong(actionRequest, PlanIgualdadFechasWebPortletKeys.HITOID_PARAM, 0);
            Date fecha = ParamUtil.getDate(actionRequest, PlanIgualdadFechasWebPortletKeys.FECHA_PARAM, dateFormat, null);

            if (Validator.isNull(fecha)) {

                SessionErrors.add(actionRequest, "fecha-save-error");
                actionResponse.sendRedirect(FechasUtils.redirectURL(actionRequest, themeDisplay, compId, versionId));

            } else {

                FechaHitoPK fechaHitoPK = new FechaHitoPK();
                fechaHitoPK.setCompId(compId);
                fechaHitoPK.setVersionId(versionId);
                fechaHitoPK.setHitoId(hitoId);

                FechaHito fechaHito = FechaHitoLocalServiceUtil.fetchFechaHito(fechaHitoPK);
                if (Validator.isNull(fechaHito)) {
                    fechaHito = FechaHitoLocalServiceUtil.createFechaHito(fechaHitoPK);
                }

                fechaHito.setFecha(fecha);

                FechaHitoLocalServiceUtil.updateFechaHito(fechaHito);

                SessionMessages.add(actionRequest, "fecha-save-success");
                actionResponse.sendRedirect(FechasUtils.redirectURL(actionRequest, themeDisplay, compId, versionId));
            }

        } catch (IOException e) {
            _log.error(e, e);
        }

        return false;
    }
}

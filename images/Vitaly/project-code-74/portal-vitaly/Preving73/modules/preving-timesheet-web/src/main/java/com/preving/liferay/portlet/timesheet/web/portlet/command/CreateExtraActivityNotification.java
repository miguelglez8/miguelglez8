package com.preving.liferay.portlet.timesheet.web.portlet.command;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.preving.liferay.portlet.calendar.manager.model.Activity;
import com.preving.liferay.portlet.calendar.manager.service.ActivityLocalServiceUtil;
import com.preving.liferay.portlet.mailing.web.mail.UserMailing;
import com.preving.liferay.portlet.timesheet.web.constants.TimesheetPortletKeys;
import com.preving.liferay.portlet.timesheet.web.util.NotificacionUtil;
import com.preving.liferay.portlet.timesheet.web.util.PrevingMailUtil;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import java.util.ResourceBundle;

@Component(
        immediate=true,
        property= {
                "javax.portlet.name=" + TimesheetPortletKeys.TIMESHEET ,
                "mvc.command.name=createExtraActivityNotification"
        },
        service = MVCResourceCommand.class
)
public class CreateExtraActivityNotification extends BaseMVCResourceCommand {
    private static Log _log = LogFactoryUtil.getLog(CreateExtraActivityNotification.class);
    @Override
    protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
        ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
        long idActivity= Long.parseLong(ParamUtil.getString(resourceRequest,"idActivity", "0"));
        String nombreGestor= ParamUtil.getString(resourceRequest,"mailGestor", "");
        String fechaRegistro=ParamUtil.getString(resourceRequest,"fechaRegistro", "");
        String horaInicio= ParamUtil.getString(resourceRequest,"horaIncio", "");
        String horaFin= ParamUtil.getString(resourceRequest,"horaFin", "");
        String observaciones=ParamUtil.getString(resourceRequest,"observaciones", "");
        _log.info(idActivity);
        Activity actividad= ActivityLocalServiceUtil.fetchActivity(idActivity);
        _log.info("mailGestor: "+nombreGestor);
        if(Validator.isNotNull(actividad) && actividad.getType()==3 && (!Validator.isBlank(actividad.getUsersToInform()) || !Validator.isBlank(nombreGestor))){
            String info = LanguageUtil.get(ResourceBundle.getBundle("content/Language", themeDisplay.getLocale()), "previngmailing.mail.extra.activity")+" "+themeDisplay.getSiteGroupName();
            UserMailing.userActivityExtraCreate(nombreGestor,themeDisplay.getUser(),themeDisplay,actividad,horaInicio,horaFin,fechaRegistro,themeDisplay.getSiteGroupName(),observaciones);
            NotificacionUtil.sendNotificacion(themeDisplay.getUser().getUserId(), themeDisplay.getUser().getUserId(), info , info);

            _log.info("CreateExtraActivityNotification: " + actividad.getInfoRespo());
        }
    }
}

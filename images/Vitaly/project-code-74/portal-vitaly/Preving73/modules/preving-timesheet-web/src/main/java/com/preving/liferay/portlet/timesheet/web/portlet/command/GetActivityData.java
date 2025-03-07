package com.preving.liferay.portlet.timesheet.web.portlet.command;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.preving.liferay.portlet.calendar.manager.model.Activity;
import com.preving.liferay.portlet.calendar.manager.service.ActivityLocalServiceUtil;
import com.preving.liferay.portlet.timesheet.web.constants.TimesheetPortletKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

@Component(
        immediate=true,
        property= {
                "javax.portlet.name=" + TimesheetPortletKeys.TIMESHEET ,
                "mvc.command.name=getActivityData"
        },
        service = MVCResourceCommand.class
)

public class GetActivityData extends BaseMVCResourceCommand {
    private static Log _log = LogFactoryUtil.getLog(GetActivityData.class);
    @Override
    protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
        JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();
        JSONObject json =null;
        long idActivity= Long.parseLong(ParamUtil.getString(resourceRequest,"idActivity", "0"));
        _log.info(idActivity);
        Activity actividad= ActivityLocalServiceUtil.fetchActivity(idActivity);
        if(Validator.isNotNull(actividad) && actividad.getType()==3){
            jsonObject.put("observaciones",actividad.getObservations());
            _log.info(actividad.getInfoRespo());
            jsonObject.put("mailGestor",actividad.getInfoRespo());
        }

        resourceResponse.setContentType("application/json");
        resourceResponse.setCharacterEncoding("UTF-8");
        resourceResponse.getWriter().write(jsonObject.toString());
    }
}

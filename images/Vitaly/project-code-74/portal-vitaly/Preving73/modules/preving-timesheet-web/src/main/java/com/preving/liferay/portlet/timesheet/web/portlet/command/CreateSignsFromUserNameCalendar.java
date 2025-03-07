package com.preving.liferay.portlet.timesheet.web.portlet.command;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.*;
import com.preving.liferay.portlet.calendar.manager.model.UserDataCalendars;
import com.preving.liferay.portlet.calendar.manager.service.CompanyConfLocalServiceUtil;
import com.preving.liferay.portlet.calendar.manager.service.UserDataCalendarsLocalServiceUtil;
import com.preving.liferay.portlet.timesheet.web.constants.TimesheetPortletKeys;
import com.preving.liferay.portlet.timesheet.web.util.TimesheetUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.List;

@Component(
        immediate=true,
        property= {
                "javax.portlet.name=" + TimesheetPortletKeys.TIMESHEET ,
                "mvc.command.name=createSignsFromUserNameCalendar"
        },
        service = MVCResourceCommand.class
)

public class CreateSignsFromUserNameCalendar extends BaseMVCResourceCommand {
    private static Log _log = LogFactoryUtil.getLog(CreateSignsFromUserNameCalendar.class);
    @Reference
    private Portal _portal;
    @Override
    protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
        UploadPortletRequest uploadPortletRequest = _portal.getUploadPortletRequest(resourceRequest);
        ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
        long companyId = ParamUtil.getLong(uploadPortletRequest, "companyId", 0);
        long groupId = ParamUtil.getLong(uploadPortletRequest, "groupId", 0);
        String userNameCalendarIdStr = ParamUtil.getString(uploadPortletRequest, "userNameCalendarId", "0");
        String dateStr = ParamUtil.getString(uploadPortletRequest, "date", StringPool.BLANK);

        long userNameCalendarId = Long.parseLong(userNameCalendarIdStr);
        long userId = themeDisplay.getUserId();

        JSONObject json = JSONFactoryUtil.createJSONObject();
        _log.info("companyId: " + companyId + " / groupId: " + groupId + " / userId: " + userId + " / dateStr: " + dateStr + " / " + userNameCalendarId);
        if(companyId == 0 || groupId == 0 ||userId == 0 || dateStr.isEmpty()) return;

        if(CompanyConfLocalServiceUtil.getCompanyConf(companyId).isIsOwnCalendars()){
            List<UserDataCalendars> lstUserDataCalandars = UserDataCalendarsLocalServiceUtil.getUserDataCalendarsByNameId(userNameCalendarId);

            _log.info("lstUserDataCalandars: " + lstUserDataCalandars.size());
            if(Validator.isNotNull(lstUserDataCalandars) && lstUserDataCalandars.size() > 0){
                String[] arrDate = dateStr.split("-");
                _log.info("arrDate.length: " + arrDate.length);
                Calendar dateStart = Calendar.getInstance();
                Calendar dateEnd = Calendar.getInstance();
                String[] start = null, end = null;

                for(UserDataCalendars uData: lstUserDataCalandars){
                    start = uData.getStartDate().split(":");
                    end = uData.getFinishDate().split(":");
                    dateStart.set(Integer.parseInt(arrDate[2]),Integer.parseInt(arrDate[1])-1,Integer.parseInt(arrDate[0]),Integer.parseInt(start[0]),Integer.parseInt(start[1]),00);
                    dateEnd.set(Integer.parseInt(arrDate[2]),Integer.parseInt(arrDate[1])-1,Integer.parseInt(arrDate[0]),Integer.parseInt(end[0]),Integer.parseInt(end[1]),00);
                    dateStart.set(Calendar.MILLISECOND,0);
                    dateEnd.set(Calendar.MILLISECOND,0);
                    //TimesheetUtil.addSign(companyId, groupId, userId, dateStart.getTime(), dateEnd.getTime(), uData.getActivityId(), "");
                    TimesheetUtil.addSign(themeDisplay.getCompanyId(), groupId, userId, dateStart.getTime(), dateEnd.getTime(), uData.getActivityId(), "");

                    _log.info("creando -> dateStart " + dateStart.toString());
                }
                json.put("status","ok");
            }
        }
        resourceResponse.setContentType("application/json");
        resourceResponse.setCharacterEncoding("UTF-8");
        resourceResponse.getWriter().write(json.toString());
    }
}

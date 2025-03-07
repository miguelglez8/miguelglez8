package com.preving.liferay.portlet.user.custom.calendar.portlet.commands;


import com.liferay.portal.kernel.json.JSONArray;
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
import com.preving.liferay.portlet.calendar.manager.model.UserNameCalendars;
import com.preving.liferay.portlet.calendar.manager.model.WorkCenters;
import com.preving.liferay.portlet.calendar.manager.service.ActivityLocalServiceUtil;
import com.preving.liferay.portlet.calendar.manager.service.CompanyConfLocalServiceUtil;
import com.preving.liferay.portlet.calendar.manager.service.UserDataCalendarsLocalServiceUtil;
import com.preving.liferay.portlet.calendar.manager.service.UserNameCalendarsLocalServiceUtil;
import com.preving.liferay.portlet.user.custom.calendar.constants.PrevingUserCustomCalendarPortletKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.List;



@Component(
        immediate=true,
        property= {
                "javax.portlet.name=" + PrevingUserCustomCalendarPortletKeys.PREVINGUSERCUSTOMCALENDAR ,
                "mvc.command.name=getUserCustomCalendarsDetails"
        },
        service = MVCResourceCommand.class
)

public class GetUserCustomCalendarsDetails extends BaseMVCResourceCommand {

        private static Log _log = LogFactoryUtil.getLog(GetUserCustomCalendarsDetails.class);

        @Reference
        private Portal _portal;
        @Override
        protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
                //HttpServletRequest httpReq = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(resourceRequest));
                UploadPortletRequest httpReq = _portal.getUploadPortletRequest(resourceRequest);
                ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
                long companyId = ParamUtil.getLong(httpReq, "companyId", 0);
                long groupId = ParamUtil.getLong(httpReq, "groupId", 0);
                long userCustomCalendarId = ParamUtil.getLong(httpReq, "calendarId", 0);
                long userId = themeDisplay.getUserId();

                JSONObject json = JSONFactoryUtil.createJSONObject();
                JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

                /*Devuelve los detalles del custom calendar (actividad, inicio-fin )*/

                if(CompanyConfLocalServiceUtil.getCompanyConf(companyId).isIsOwnCalendars()){
                        UserNameCalendars userNameCalendars = UserNameCalendarsLocalServiceUtil.getUserNameCalendars(userCustomCalendarId);
                        if(Validator.isNotNull(userNameCalendars)) {
                                json.put("userCustomCalendarId", userNameCalendars.getUserNameCalendarId());
                                json.put("name", userNameCalendars.getName());
                        }else{
                                json.put("data", "[]");
                                json.put("msg", "userNameCalendar not found with id " + userCustomCalendarId);
                                resourceResponse.setContentType("application/json");
                                resourceResponse.setCharacterEncoding("UTF-8");
                                resourceResponse.getWriter().write(json.toString());
                                return;
                        }
                        List<UserDataCalendars> lstUserDataCalendars =
                                UserDataCalendarsLocalServiceUtil.getUserDataCalendarsByNameId(userCustomCalendarId);
                        if( lstUserDataCalendars.size() > 0) {

                                if (Validator.isNotNull(userNameCalendars)) {

                                        JSONObject jsonItem = null;
                                        for (UserDataCalendars uData : lstUserDataCalendars) {
                                                jsonItem = JSONFactoryUtil.createJSONObject();
                                                jsonItem.put("id", uData.getUserDataCalendarId());
                                                jsonItem.put("activityId", uData.getActivityId());
                                                jsonItem.put("activityType", ActivityLocalServiceUtil.getActivity(uData.getActivityId()).getType());
                                                jsonItem.put("start", uData.getStartDate());
                                                jsonItem.put("end", uData.getFinishDate());
                                                jsonArray.put(jsonItem);
                                        }
                                }
                        }

                }

                json.put("data", jsonArray);
                resourceResponse.setContentType("application/json");
                resourceResponse.setCharacterEncoding("UTF-8");
                resourceResponse.getWriter().write(json.toString());


        }
}

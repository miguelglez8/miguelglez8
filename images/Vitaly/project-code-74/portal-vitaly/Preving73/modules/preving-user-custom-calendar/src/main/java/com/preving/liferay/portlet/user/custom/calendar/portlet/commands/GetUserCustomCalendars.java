package com.preving.liferay.portlet.user.custom.calendar.portlet.commands;


import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.preving.liferay.portlet.calendar.manager.model.*;
import com.preving.liferay.portlet.calendar.manager.service.ActivityLocalServiceUtil;
import com.preving.liferay.portlet.calendar.manager.service.CompanyConfLocalServiceUtil;
import com.preving.liferay.portlet.calendar.manager.service.UserDataCalendarsLocalServiceUtil;
import com.preving.liferay.portlet.calendar.manager.service.UserNameCalendarsLocalServiceUtil;
import com.preving.liferay.portlet.user.custom.calendar.constants.PrevingUserCustomCalendarPortletKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Component(
        immediate=true,
        property= {
                "javax.portlet.name=" + PrevingUserCustomCalendarPortletKeys.PREVINGUSERCUSTOMCALENDAR ,
                "mvc.command.name=getUserCustomCalendars"
        },
        service = MVCResourceCommand.class
)

public class GetUserCustomCalendars extends BaseMVCResourceCommand {

        private static Log _log = LogFactoryUtil.getLog(GetUserCustomCalendars.class);

        @Override
        protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
                HttpServletRequest httpReq = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(resourceRequest));
                ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
                long companyId = ParamUtil.getLong(httpReq, "companyId", 0);
                long groupId = ParamUtil.getLong(httpReq, "groupId", 0);
                long userId = themeDisplay.getUserId();
                JSONObject json = JSONFactoryUtil.createJSONObject();
                JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

                /*Devuelve solo las filas del datatable, nombre, tiempo, creación e ID */
                //_log.info("companyId: " + companyId + " / groupId: " + groupId + " / userId: " + userId);
                // com.preving.liferay.portlet.calendar.manager.service.CompanyConfLocalService c = null;

                if(companyId == 0 || groupId == 0 ||userId == 0) return;


                if(CompanyConfLocalServiceUtil.getCompanyConf(companyId).isIsOwnCalendars()){
                        List<UserNameCalendars> lstUserNameCalendars = UserNameCalendarsLocalServiceUtil.getUserNamesCustomCalendars(companyId,groupId,userId);
                        if(lstUserNameCalendars.size() > 0) {
                                JSONObject jsonItem = null;
                                for (UserNameCalendars uCal : lstUserNameCalendars) {
                                        jsonItem = JSONFactoryUtil.createJSONObject();
                                        jsonItem.put("userCustomCalendarId", uCal.getUserNameCalendarId());
                                        jsonItem.put("name", uCal.getName());
                                        jsonItem.put("time", getTime(uCal.getUserNameCalendarId()));
                                        jsonItem.put("createdDate", uCal.getCreateDate());
                                        jsonArray.put(jsonItem);

                                }
                        }


                }

                json.put("data", jsonArray);
                resourceResponse.setContentType("application/json");
                resourceResponse.setCharacterEncoding("UTF-8");
                resourceResponse.getWriter().write(json.toString());

        }


        private int getTime(long userNameCalendarId) throws PortalException {
                List<UserDataCalendars> lstUserDataCalendars = UserDataCalendarsLocalServiceUtil.getUserDataCalendarsByNameId(userNameCalendarId);
                if(lstUserDataCalendars.size() == 0) return 0;
                int start = 0, end = 0, res = 0;
                String[] arrTimeStart = null, arrTimeEnd = null;
                Activity activity = null;
                for(UserDataCalendars uData : lstUserDataCalendars){
                        activity = ActivityLocalServiceUtil.fetchActivity(uData.getActivityId());

                        if(Validator.isNotNull(activity) && activity.getType() == 1) {

                                arrTimeStart = uData.getStartDate().split(":");
                                arrTimeEnd = uData.getFinishDate().split(":");


                                start = (Integer.parseInt(arrTimeStart[0]) * 60) + Integer.parseInt(arrTimeStart[1]);
                                end = (Integer.parseInt(arrTimeEnd[0]) * 60) + Integer.parseInt(arrTimeEnd[1]);
                                res += (end - start);
                        }
                }

                return res;

        }

}

package com.preving.liferay.portlet.user.custom.calendar.portlet.commands;


import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.preving.liferay.portlet.calendar.manager.model.CompanyConf;
import com.preving.liferay.portlet.calendar.manager.model.UserDataCalendars;
import com.preving.liferay.portlet.calendar.manager.model.UserNameCalendars;
import com.preving.liferay.portlet.calendar.manager.model.WorkCenters;
import com.preving.liferay.portlet.calendar.manager.service.*;
import com.preving.liferay.portlet.user.custom.calendar.constants.PrevingUserCustomCalendarPortletKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component(
        immediate=true,
        property= {
                "javax.portlet.name=" + PrevingUserCustomCalendarPortletKeys.PREVINGUSERCUSTOMCALENDAR ,
                "mvc.command.name=createUserCustomCalendar"
        },
        service = MVCResourceCommand.class
)
public class CreateUserCustomCalendar extends BaseMVCResourceCommand {
        private static Log _log = LogFactoryUtil.getLog(CreateUserCustomCalendar.class);
        @Reference
        private Portal _portal;

        @Override
        protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
                UploadPortletRequest uploadPortletRequest = _portal.getUploadPortletRequest(resourceRequest);
                ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);

                long companyId = Long.parseLong(ParamUtil.getString(uploadPortletRequest, "companyId","0"));
                long groupId = Long.parseLong(ParamUtil.getString(uploadPortletRequest, "groupId", "0"));
                long userId = themeDisplay.getUserId();


                _log.info("companyId: " + companyId + " / groupId: " + groupId + " / userId: " + userId);
                if(companyId == 0 || groupId == 0 ||userId == 0) return;
                if(!CompanyConfLocalServiceUtil.getCompanyConf(companyId).isIsOwnCalendars()) return;

                String dataJsonStr = ParamUtil.getString(uploadPortletRequest, "data","");
                _log.info("dataJsonStr: " + dataJsonStr);
                if(dataJsonStr.isEmpty()) return;
                JSONObject json =  JSONFactoryUtil.createJSONObject(dataJsonStr);


                CompanyConf companyConf = CompanyConfLocalServiceUtil.getCompanyConfByCompanyIdAndGroupId(themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId());

                boolean isMaxHour = companyConf.getMaxHoursDay();
                float maxHourDay = companyConf.getMaxHoursDayValue();

                _log.info("isMaxHour: " + isMaxHour + " / maxHourDay: " + maxHourDay);

                //Tiene limite y supera las horas
                if(isMaxHour && isOverLimitHoursDay(json.getJSONArray("signs"),maxHourDay)){
                        return;
                }

                UserNameCalendars userNameCalendar = null;
                if(!json.getString("userNameCalendarId").equals("0")) {
                        //si tenia uno creado con ese nombre, y lo ha modificado, lo eliminamos del userDataCalendar
                        removeOldCalendar(json.getLong("userNameCalendarId"));
                        userNameCalendar = UserNameCalendarsLocalServiceUtil.getUserNameCalendars(Long.parseLong(json.getString("userNameCalendarId")));
                        userNameCalendar.setModifiedDate(new Date());
                }else{
                        userNameCalendar = UserNameCalendarsLocalServiceUtil
                                .createUserNameCalendars(CounterLocalServiceUtil.increment(UserNameCalendars.class.getName()));
                        userNameCalendar.setCreateDate( new Date());
                        userNameCalendar.setModifiedDate(new Date());
                        userNameCalendar.setActive(true);
                        userNameCalendar.setCompanyId(companyId);
                        userNameCalendar.setGroupId(groupId);
                        userNameCalendar.setUserId(userId);
                }
                userNameCalendar.setName(json.getString("name"));
                UserNameCalendarsLocalServiceUtil.updateUserNameCalendars(userNameCalendar);



                JSONArray jsonArray = json.getJSONArray("signs");
                JSONObject jsonSign = null;
                UserDataCalendars userDataCalendars = null;
                //[{ start, end, activityId}]
                for(Object sign : jsonArray){
                        jsonSign  = (JSONObject) sign;
                        userDataCalendars = UserDataCalendarsLocalServiceUtil.createUserDataCalendars(CounterLocalServiceUtil.increment(UserDataCalendars.class.getName()));
                        userDataCalendars.setUserNameCalendarId(userNameCalendar.getUserNameCalendarId());
                        userDataCalendars.setCreateDate(new Date());
                        userDataCalendars.setModifiedDate(new Date());
                        userDataCalendars.setStartDate(jsonSign.getString("start"));
                        userDataCalendars.setFinishDate(jsonSign.getString("end"));
                        userDataCalendars.setActivityId(Long.parseLong(jsonSign.getString("activityId")));

                        UserDataCalendarsLocalServiceUtil.updateUserDataCalendars(userDataCalendars);
                }


        }

        private void removeOldCalendar(long userNameCalendarId) throws PortalException {
                List<UserDataCalendars> lstuserDataCalendars = UserDataCalendarsLocalServiceUtil.getUserDataCalendarsByNameId(userNameCalendarId);

                _log.info("userNameCalendarId: " + userNameCalendarId);
                _log.info("lstuserDataCalendars: " + lstuserDataCalendars.size());

                for(UserDataCalendars uData : lstuserDataCalendars){
                        UserDataCalendarsLocalServiceUtil.deleteUserDataCalendars(uData.getUserDataCalendarId());
                }

        }

        private boolean isOverLimitHoursDay(JSONArray arrJson, float max){
                if(arrJson.length() == 0) return false;
                int start = 0, end = 0, res = 0;
                String[] arrTimeStart = null, arrTimeEnd = null;

                JSONObject item = null;
                for(int i = 0; i < arrJson.length(); i++ ){
                        item = arrJson.getJSONObject(i);
                        if(item.getString("activityType").equals("1")){
                                arrTimeStart =item.get("start").toString().split(":");
                                arrTimeEnd = item.get("end").toString().split(":");
                                start = (Integer.parseInt(arrTimeStart[0]) * 60) + Integer.parseInt(arrTimeStart[1]);
                                end = (Integer.parseInt(arrTimeEnd[0]) * 60) + Integer.parseInt(arrTimeEnd[1]);
                                res += (end - start) ;
                        }
                }
                _log.info("res: " + res + " / max*60: " + (max*60));
                if(res > (max*60)) return true;
                return false;
        }
}

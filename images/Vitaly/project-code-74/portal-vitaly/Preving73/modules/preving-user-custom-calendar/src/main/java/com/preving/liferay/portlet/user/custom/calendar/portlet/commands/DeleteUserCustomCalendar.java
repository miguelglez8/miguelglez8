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
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.preving.liferay.portlet.calendar.manager.model.UserDataCalendars;
import com.preving.liferay.portlet.calendar.manager.model.UserNameCalendars;
import com.preving.liferay.portlet.calendar.manager.service.CompanyConfLocalServiceUtil;
import com.preving.liferay.portlet.calendar.manager.service.UserDataCalendarsLocalServiceUtil;
import com.preving.liferay.portlet.calendar.manager.service.UserNameCalendarsLocalServiceUtil;
import com.preving.liferay.portlet.user.custom.calendar.constants.PrevingUserCustomCalendarPortletKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import java.util.Date;
import java.util.List;

@Component(
        immediate=true,
        property= {
                "javax.portlet.name=" + PrevingUserCustomCalendarPortletKeys.PREVINGUSERCUSTOMCALENDAR ,
                "mvc.command.name=deleteUserCustomCalendar"
        },
        service = MVCResourceCommand.class
)
public class DeleteUserCustomCalendar extends BaseMVCResourceCommand {
        private static Log _log = LogFactoryUtil.getLog(DeleteUserCustomCalendar.class);
        @Reference
        private Portal _portal;

        @Override
        protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
                UploadPortletRequest uploadPortletRequest = _portal.getUploadPortletRequest(resourceRequest);
                ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);

                long userNameCalendarId = Long.parseLong(ParamUtil.getString(uploadPortletRequest, "userNameCalendarId","0"));
                long companyId = Long.parseLong(ParamUtil.getString(uploadPortletRequest, "companyId","0"));
                long groupId = Long.parseLong(ParamUtil.getString(uploadPortletRequest, "groupId", "0"));
                long userId = themeDisplay.getUserId();


                _log.info("companyId: " + companyId + " / groupId: " + groupId + " / userId: " + userId);
                if(userNameCalendarId == 0  || companyId == 0 || groupId == 0 ||userId == 0) return;
                if(!CompanyConfLocalServiceUtil.getCompanyConf(companyId).isIsOwnCalendars()) return;

                JSONObject json =  JSONFactoryUtil.createJSONObject();
                UserNameCalendars userNameCalendar = UserNameCalendarsLocalServiceUtil.getUserNameCalendars(userNameCalendarId);
                if(Validator.isNotNull(userNameCalendar)){
                        removeOldCalendar(userNameCalendarId);
                        UserNameCalendarsLocalServiceUtil.deleteUserNameCalendars(userNameCalendarId);
                        json.put("success","ok");
                }else{
                        json.put("success","fail, calendar not found with id: " + userNameCalendarId);
                }
                resourceResponse.setContentType("application/json");
                resourceResponse.setCharacterEncoding("UTF-8");
                resourceResponse.getWriter().write(json.toString());

        }

        private void removeOldCalendar(long userNameCalendarId) throws PortalException {
                List<UserDataCalendars> lstuserDataCalendars = UserDataCalendarsLocalServiceUtil.getUserDataCalendarsByNameId(userNameCalendarId);

                _log.info("userNameCalendarId: " + userNameCalendarId);
                _log.info("lstuserDataCalendars: " + lstuserDataCalendars.size());

                for(UserDataCalendars uData : lstuserDataCalendars){
                        UserDataCalendarsLocalServiceUtil.deleteUserDataCalendars(uData.getUserDataCalendarId());
                }

        }
}

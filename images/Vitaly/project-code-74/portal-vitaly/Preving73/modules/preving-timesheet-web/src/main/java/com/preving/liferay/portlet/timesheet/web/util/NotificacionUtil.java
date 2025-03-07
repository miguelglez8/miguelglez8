package com.preving.liferay.portlet.timesheet.web.util;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.UserNotificationDeliveryConstants;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserNotificationEventLocalServiceUtil;
import com.preving.liferay.portlet.timesheet.web.constants.TimesheetPortletKeys;

public class NotificacionUtil {

    private static Log _log = LogFactoryUtil.getLog(NotificacionUtil.class);

    public static void sendNotificacion(long userIdTo, long userIdFrom, String title, String body){
        try {

            JSONObject payloadJSON = JSONFactoryUtil.createJSONObject();
            payloadJSON.put("userId", userIdTo);
            payloadJSON.put("title", title);
            payloadJSON.put("body", body);

            UserNotificationEventLocalServiceUtil.addUserNotificationEvent(
                    userIdTo,
                    TimesheetPortletKeys.TIMESHEET,
                    System.currentTimeMillis(),
                    UserNotificationDeliveryConstants.TYPE_WEBSITE,
                    userIdFrom,
                    payloadJSON.toString(),
                    false,
                    new ServiceContext()
            );

        } catch (PortalException e) {
            _log.error(e);
        }
    }

}

package com.preving.liferay.portlet.timesheet.web.notification;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.UserNotificationEvent;
import com.liferay.portal.kernel.notifications.BaseUserNotificationHandler;
import com.liferay.portal.kernel.notifications.UserNotificationHandler;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.StringUtil;
import com.preving.liferay.portlet.timesheet.web.constants.TimesheetPortletKeys;
import org.osgi.service.component.annotations.Component;

@Component(service = UserNotificationHandler.class)
public class SendNotificationHandler extends BaseUserNotificationHandler {

    public SendNotificationHandler() {
        setPortletId(TimesheetPortletKeys.TIMESHEET);
    }

    @Override
    protected String getBody(UserNotificationEvent userNotificationEvent, ServiceContext serviceContext) throws Exception {

        JSONObject jsonObject = JSONFactoryUtil.createJSONObject(userNotificationEvent.getPayload());

        String title = jsonObject.getString("title");
        String body = jsonObject.getString("body");

        String notification = StringUtil.replace(getBodyTemplate(),
                new String[]{"[$TITLE$]", "[$BODY$]"},
                new String[]{title, body});

        return notification;
    }

    @Override
    protected String getBodyTemplate() throws Exception {
        StringBuilder sb = new StringBuilder(5);
        sb.append("<h3>[$TITLE$]</h3>");
        sb.append("<div class=\"body\">[$BODY$]</div>");
        return sb.toString();
    }
}

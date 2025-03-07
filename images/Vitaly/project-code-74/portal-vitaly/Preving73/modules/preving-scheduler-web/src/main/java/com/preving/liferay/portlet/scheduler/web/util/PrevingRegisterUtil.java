package com.preving.liferay.portlet.scheduler.web.util;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.Validator;
import com.preving.liferay.portlet.calendar.manager.model.Activity;
import com.preving.liferay.portlet.calendar.manager.model.Sign;
import com.preving.liferay.portlet.calendar.manager.service.ActivityLocalServiceUtil;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PrevingRegisterUtil {

    private static Log _log = LogFactoryUtil.getLog(PrevingRegisterUtil.class);

    static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    static SimpleDateFormat dateFormatHour = new SimpleDateFormat("HH:mm");

    public static String getSignsTable(List<Sign> signs){

        long groupId;
        long companyId;

        if(signs.size() == 0){
            return "<p><strong>No se han creado registros horarios.</strong></p>";
        }

        companyId = signs.get(0).getCompanyId();
        groupId = signs.get(0).getGroupId();

        List<Activity> activityList = ActivityLocalServiceUtil.getActivitiesFromGroup(companyId, groupId);
        Map<Long, Activity> map = new HashMap<Long, Activity>();
        for(Activity act:activityList){
            map.put(act.getActivityId(),act);
        }

        List<User> groupUsers = UserLocalServiceUtil.getGroupUsers(groupId);
        Map<Long, String> mapUsers = new HashMap<Long, String>();
        for(User user:groupUsers){
            mapUsers.put(user.getUserId(), user.getFullName());
        }

        StringBuilder sb = new StringBuilder();

        sb.append("<div><table><tbody>");
        sb.append("<thead class=\"text-center\" style=\"background: #2f4050;color: #a7b1c2;\">");
        sb.append("<tr>");
        sb.append("<th>Nombre</th>");
        sb.append("<th>Fecha</th>");
        sb.append("<th>Entrada</th>");
        sb.append("<th>Salida</th>");
        sb.append("<th>Actividad</th>");
        sb.append("<th>Observaciones</th>");
        sb.append("<th>Observaciones empresa</th>");
        sb.append("</tr>");
        sb.append("</thead>");
        sb.append("<tbody style=\"background: #a7b1c2;\">");

        for(Sign s:signs){

            String activityName = "";
            if(Validator.isNotNull(map.get(s.getActivityId()))){
                activityName = map.get(s.getActivityId()).getName();
            }

            String finishDate = "";
            if(Validator.isNotNull(s.getFinishDate())){
                finishDate = dateFormatHour.format(s.getFinishDate());
            }


            sb.append("<tr>");
            sb.append("<td>" + mapUsers.get(s.getUserId()) + "</td>");
            sb.append("<td>" + dateFormat.format(s.getStartDate()) + "</td>");
            sb.append("<td>" + dateFormatHour.format(s.getStartDate()) + "</td>");
            sb.append("<td>" + finishDate + "</td>");
            sb.append("<td>" + activityName + "</td>");
            sb.append("<td>" + s.getObservations() + "</td>");
            sb.append("<td>" + s.getObservationsAdmin() + "</td>");
            sb.append("</tr>");
        }
        sb.append("</tbody></table></div>");

        return sb.toString();
    }
}

package com.preving.liferay.portlet.timesheet.web.util;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import com.preving.liferay.portlet.calendar.manager.model.Activity;
import com.preving.liferay.portlet.calendar.manager.model.Sign;
import com.preving.liferay.portlet.calendar.manager.service.ActivityLocalServiceUtil;
import com.preving.liferay.portlet.calendar.manager.service.SignLocalServiceUtil;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TimesheetUtil {

    private static Log _log = LogFactoryUtil.getLog(TimesheetUtil.class);

    public static void addSign(long companyId, long groupId, long userId, Date startDate, Date finishDate, long activityId, String observations){

        SignLocalServiceUtil.addSign(companyId, groupId, userId, activityId, startDate, finishDate, observations);

    }

    public static String getActivityName(long activityId){

        String activityName = "";

        if(activityId == 0){
            return activityName;
        }

        Activity activity = null;
        try {

            activity = ActivityLocalServiceUtil.fetchActivity(activityId);

            if(Validator.isNotNull(activity)){
                activityName = activity.getName();
            }

        } catch (Exception e) {
            _log.error("Not exist activity with id " + activityId, e);
        }

        return activityName;
    }

    public static String getTimeElapsed(Date startDate, Date finishDate){

        long diff = finishDate.getTime() - startDate.getTime();

        long hours = diff / (60 * 60 * 1000) % 60;
        long minutes = diff / (60 * 1000) % 60;

        return String.format("%02d", hours) + ":" + String.format("%02d", minutes);
    }

    public static boolean getActivityWorkTime(long activityId){

        if(activityId == 0){
            return false;
        }

        Activity activity = null;
        try {

            activity = ActivityLocalServiceUtil.fetchActivity(activityId);

            return activity.isWorkTime();

        } catch (Exception e) {
            _log.error("Not exist activity with id " + activityId, e);
        }

        return false;
    }
}

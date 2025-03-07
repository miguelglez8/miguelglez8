package com.preving.liferay.portlet.activity.configuration.web.util;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import com.preving.liferay.portlet.calendar.manager.model.Activity;
import com.preving.liferay.portlet.calendar.manager.model.Holiday;
import com.preving.liferay.portlet.calendar.manager.service.ActivityLocalServiceUtil;

import java.util.List;
import java.util.Locale;
import java.util.Map;

public class PrevingActivityUtil {

    private static Log _log = LogFactoryUtil.getLog(PrevingActivityUtil.class);

    public static Activity createActivityExtra(long companyId, long groupId, Map<Locale, String> nameLocalized, boolean workTime, int type, String color, boolean active,
                                               String observation,boolean respo, boolean infoUser, String usersToInform ) {

        Activity activity = null;

        try {
            usersToInform=usersToInform.replaceAll(StringPool.COMMA,StringPool.SEMICOLON);
            activity = ActivityLocalServiceUtil.createActivity(CounterLocalServiceUtil.increment(Holiday.class.getName()));

            activity.setCompanyId(companyId);
            activity.setGroupId(groupId);
            activity.setNameMap(nameLocalized);
            activity.setWorkTime(workTime);
            activity.setType(type);
            activity.setColor(color);
            activity.setActive(active);
            activity.setObservations(observation);
            activity.setInfoRespo(respo);
            activity.setInfoUser(infoUser);
            activity.setUsersToInform(usersToInform);


            ActivityLocalServiceUtil.updateActivity(activity);

            if (_log.isDebugEnabled()) {
                _log.debug("Created new activity: ");
                _log.debug("   - companyId : " + companyId);
                _log.debug("   - groupId : " + groupId);
                _log.debug("   - name : " + nameLocalized);
                _log.debug("   - workTime : " + workTime);
                _log.debug("   - type : " + type);
                _log.debug("   - color : " + color);
                _log.debug("   - active : " + active);
            }

        } catch (Exception e) {
            _log.error("Can't create activity with name: " + nameLocalized + ".", e);
        }

        return activity;
    }
    public static Activity createActivity(long companyId, long groupId, Map<Locale, String> nameLocalized, boolean workTime, int type, String color, boolean active) {

        Activity activity = null;

        try {

            activity = ActivityLocalServiceUtil.createActivity(CounterLocalServiceUtil.increment(Holiday.class.getName()));

            activity.setCompanyId(companyId);
            activity.setGroupId(groupId);
            activity.setNameMap(nameLocalized);
            activity.setWorkTime(workTime);
            activity.setType(type);
            activity.setColor(color);
            activity.setActive(active);

            ActivityLocalServiceUtil.updateActivity(activity);

            if (_log.isDebugEnabled()) {
                _log.debug("Created new activity: ");
                _log.debug("   - companyId : " + companyId);
                _log.debug("   - groupId : " + groupId);
                _log.debug("   - name : " + nameLocalized);
                _log.debug("   - workTime : " + workTime);
                _log.debug("   - type : " + type);
                _log.debug("   - color : " + color);
                _log.debug("   - active : " + active);
            }

        } catch (Exception e) {
            _log.error("Can't create activity with name: " + nameLocalized + ".", e);
        }

        return activity;
    }

    public static Activity getActivityByName(String activityName) {
        Activity activity = null;
        try {
            List<Activity> listActivities = ActivityLocalServiceUtil.getActivitiesFromName(activityName);
            activity = listActivities.get(0);

            if (Validator.isNotNull(activity)) {
                return activity;
            }
        } catch (Exception e) {
            if (_log.isDebugEnabled()) {
                _log.debug("The activity with id: " + activity.getActivityId() + " doesn't exist .");
            }
        }

        return null;
    }

    public static Activity updateActivity(long activityId, long companyId, long groupId, Map<Locale, String> nameLocalized, boolean workTime, int type, String color, boolean active) {
        Activity activity = null;

        try {
            activity = ActivityLocalServiceUtil.getActivity(activityId);
        } catch (Exception e) {
            _log.error(e.getCause(), e);
        }

        if (Validator.isNotNull(activity)) {
            activity.setNameMap(nameLocalized);
            activity.setWorkTime(workTime);
            activity.setType(type);
            activity.setColor(color);
            activity.setActive(active);
            ActivityLocalServiceUtil.updateActivity(activity);

            if (_log.isDebugEnabled()) {
                _log.debug("Activity updated with id: " + activityId +". Name: " + activity.getName() + ", active: " + activity.isActive() + ", worktime: " + activity.isWorkTime());
            }
        }

        return activity;
    }

    public static Activity updateActivityExtra(long activityId,long companyId, long groupId, Map<Locale, String> nameLocalized, boolean workTime, int type, String color, boolean active,
                                               String observation,boolean respo, boolean infoUser, String usersToInform ) {

            usersToInform=usersToInform.replaceAll(StringPool.COMMA,StringPool.SEMICOLON);

            Activity activity = null;

            try {
                activity = ActivityLocalServiceUtil.getActivity(activityId);
            } catch (Exception e) {
                _log.error(e.getCause(), e);
            }
            if (Validator.isNotNull(activity)) {

                activity.setCompanyId(companyId);
                activity.setGroupId(groupId);
                activity.setNameMap(nameLocalized);
                activity.setWorkTime(workTime);
                activity.setType(type);
                activity.setColor(color);
                activity.setActive(active);
                activity.setObservations(observation);
                activity.setInfoRespo(respo);
                activity.setInfoUser(infoUser);
                activity.setUsersToInform(usersToInform);


                ActivityLocalServiceUtil.updateActivity(activity);

                if (_log.isDebugEnabled()) {
                    _log.debug("Created new activity: ");
                    _log.debug("   - companyId : " + companyId);
                    _log.debug("   - groupId : " + groupId);
                    _log.debug("   - name : " + nameLocalized);
                    _log.debug("   - workTime : " + workTime);
                    _log.debug("   - type : " + type);
                    _log.debug("   - color : " + color);
                    _log.debug("   - active : " + active);
                }
            }

        return activity;
    }

    public static void deleteActivity(long activityId){
        try {
            ActivityLocalServiceUtil.deleteActivity(activityId);

            if (_log.isDebugEnabled()) {
                _log.debug("Activity deleted with id: " + activityId);
            }
        } catch (Exception e) {
            _log.error(e.getCause(), e);
        }

    }
}

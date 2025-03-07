package com.preving.liferay.portlet.sign.register.report.web.util;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import com.preving.liferay.portlet.calendar.manager.model.Activity;
import com.preving.liferay.portlet.calendar.manager.model.Sign;
import com.preving.liferay.portlet.calendar.manager.model.UserData;
import com.preving.liferay.portlet.calendar.manager.service.ActivityLocalServiceUtil;
import com.preving.liferay.portlet.calendar.manager.service.SignLocalServiceUtil;
import com.preving.liferay.portlet.calendar.manager.service.UserDataLocalServiceUtil;
import com.preving.liferay.portlet.sign.register.report.web.bean.PrevingSign;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class PrevingSignUtil {

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    private static Log _log = LogFactoryUtil.getLog(PrevingSignUtil.class);

    public static List<PrevingSign> getSignsBetwenDates(long companyId, long groupId, long userId, String jobTitle, Date startDate, Date endDate){

        List<PrevingSign> previngSigns = new ArrayList<>();

        if(Validator.isNull(endDate)){
            return previngSigns;
        }

        List<Date> datesBetweenDates = getDatesBetweenDates(companyId, groupId, userId, startDate, endDate);;

        List<Long> usersBetweenDates = new ArrayList<>();

        if(userId == 0){
            usersBetweenDates = getUsersBetweenDates(companyId, groupId, startDate, endDate);
        }else{
            usersBetweenDates.add(userId);
        }

        for (long userIdSign : usersBetweenDates) {

            //Check jobtitle
            if(!Validator.isBlank(jobTitle)) {
                String userJobTitle = getUserJobtitle(groupId, userIdSign);
                if(!jobTitle.equals(userJobTitle)){
                    continue;
                }
            }

            for (Date dateSign: datesBetweenDates) {

                if(_log.isDebugEnabled()) {
                    _log.debug("DATE " + dateSign + " USER " + userIdSign);
                }

                List<Sign> signs = SignLocalServiceUtil.getSignsByCompanyIdAndGroupIdAndUserIdAndDate(companyId, groupId, userIdSign, dateSign);

                if(signs.size() > 0) {

                    PrevingSign pSign = new PrevingSign();

                    pSign.setGroupId(groupId);
                    pSign.setUserId(userIdSign);
                    pSign.setDate(dateSign);
                    pSign.setSigns(signs);

                    previngSigns.add(pSign);

                }

            }
        }

        if(_log.isDebugEnabled()){
            _log.debug("Users with dates:");
            for(PrevingSign previngSign: previngSigns){
                _log.debug("  " + previngSign);
            }
        }

        return previngSigns;
    }


    public static List<Long> getUsersBetweenDates(long companyId, long groupId, Date startDate, Date endDate){

        List<Long> userList = new ArrayList<>();

        List<Sign> signsByDates = SignLocalServiceUtil.getSignsByDates(companyId, groupId, startDate, endDate);

        if(Validator.isNull(endDate)){
            return userList;
        }

        for(Sign sign: signsByDates){
            if(!userList.contains(sign.getUserId())){
                userList.add(sign.getUserId());
            }
        }

        try {
            Group group = GroupLocalServiceUtil.getGroup(groupId);
            User userCreator = UserLocalServiceUtil.getUser(group.getCreatorUserId());
            userList.remove(userCreator);
        } catch (PortalException e) {
            _log.error(e);
        }

        if(_log.isDebugEnabled()){
            _log.debug("Users:");
            for(long user: userList){
                _log.debug("  " + user);
            }
        }

        return userList;
    }

    public static List<Date> getDatesBetweenDates(long companyId, long groupId, long userId, Date startDate, Date endDate){

        List<Date> dateList = new ArrayList<>();

        List<Sign> signsByDates = new ArrayList<>();

        if(Validator.isNull(endDate)){
            return dateList;
        }

        if(userId == 0){
            signsByDates = SignLocalServiceUtil.getSignsByDates(companyId, groupId, startDate, endDate);
        }else{
            signsByDates = SignLocalServiceUtil.getSignsByUserIdBetweenDates(companyId, groupId, userId, startDate, endDate);
        }

        List<Sign> signsByDates2 = new ArrayList<>(signsByDates);
        signsByDates2.sort(Comparator.comparing(Sign::getStartDate));



        for(Sign sign: signsByDates){

            Calendar cal = Calendar.getInstance();
            cal.setTime(sign.getStartDate());
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);

            if(!containsDate(dateList, cal.getTime())){
                dateList.add(cal.getTime());
            }
        }

        if(_log.isDebugEnabled()){
            _log.debug("Dates:");
            for(Date date: dateList){
                _log.debug("  " + date);
            }
        }

        return dateList;
    }

    private static boolean containsDate(List<Date> dates, Date date){

        for(Date d:dates){

            if(_log.isDebugEnabled()) {
                _log.debug(dateFormat.format(d) + " == " + dateFormat.format(date));
            }

            if(dateFormat.format(d).compareTo(dateFormat.format(date)) == 0){
                return true;
            }
        }
        return false;
    }

    public static String getTotalHoursByUserBetweenDates(long companyId, long groupId, long userId, Date startDate, Date endDate){

        long total = 0;

        if(Validator.isNull(endDate)){
            return "";
        }

        List<Long> activitiesWorkTime = new ArrayList<>();
        List<Activity> activityList = ActivityLocalServiceUtil.getActivitiesFromGroup(companyId, groupId);

        for(Activity act:activityList){

            if(_log.isDebugEnabled()) {
                _log.debug("activity: " + act.getActivityId() + " " + act.isWorkTime());
            }

            if(act.isWorkTime()){
                activitiesWorkTime.add(act.getActivityId());
            }
        }

        List<Sign> signsByUserIdBetweenDates = SignLocalServiceUtil.getSignsByUserIdBetweenDates(companyId, groupId, userId, startDate, endDate);

        for(Sign s:signsByUserIdBetweenDates){
            if(activitiesWorkTime.contains(s.getActivityId()) && Validator.isNotNull(s.getFinishDate())) {
                total += s.getFinishDate().getTime() - s.getStartDate().getTime();
            }
        }

        if(_log.isDebugEnabled()){
            _log.debug("groupId: " + groupId);
            _log.debug("userId: " + userId);
            _log.debug("startDate: " + startDate);
            _log.debug("endDate: " + endDate);
            long totalDebug = 0;
            for(Sign s:signsByUserIdBetweenDates){
                //_log.debug("  activity: " + s.getActivityId() + "  signId: " + s.getSignId());
                if(activitiesWorkTime.contains(s.getActivityId()) && Validator.isNotNull(s.getFinishDate())) {
                    totalDebug += s.getFinishDate().getTime() - s.getStartDate().getTime();
                    _log.debug("  totalDebug: " + totalDebug +"   format: " + getTimeString(totalDebug) + " added: " + getTimeString(s.getFinishDate().getTime() - s.getStartDate().getTime()));
                }
            }
        }

        return getTimeString(total);
    }


    public static String getTotalHoursExtraByUserBetweenDates(long companyId, long groupId, long userId, Date startDate, Date endDate){

        long total = 0;

        if(Validator.isNull(endDate)){
            return "";
        }

        List<Long> activitiesWorkTime = new ArrayList<>();
        List<Activity> activityList = ActivityLocalServiceUtil.getActivitiesFromGroup(companyId, groupId);

        for(Activity act:activityList){

            if(_log.isDebugEnabled()) {
                _log.debug("activity: " + act.getActivityId() + " " + act.isWorkTime());
            }

            if(act.getType()==3){
                activitiesWorkTime.add(act.getActivityId());
            }
        }

        List<Sign> signsByUserIdBetweenDates = SignLocalServiceUtil.getSignsByUserIdBetweenDates(companyId, groupId, userId, startDate, endDate);

        for(Sign s:signsByUserIdBetweenDates){
            if(activitiesWorkTime.contains(s.getActivityId()) && Validator.isNotNull(s.getFinishDate())) {
                total += s.getFinishDate().getTime() - s.getStartDate().getTime();
            }
        }

        if(_log.isDebugEnabled()){
            _log.debug("groupId: " + groupId);
            _log.debug("userId: " + userId);
            _log.debug("startDate: " + startDate);
            _log.debug("endDate: " + endDate);
            long totalDebug = 0;
            for(Sign s:signsByUserIdBetweenDates){
                //_log.debug("  activity: " + s.getActivityId() + "  signId: " + s.getSignId());
                if(activitiesWorkTime.contains(s.getActivityId()) && Validator.isNotNull(s.getFinishDate())) {
                    totalDebug += s.getFinishDate().getTime() - s.getStartDate().getTime();
                    _log.debug("  totalDebug: " + totalDebug +"   format: " + getTimeString(totalDebug) + " added: " + getTimeString(s.getFinishDate().getTime() - s.getStartDate().getTime()));
                }
            }
        }

        return getTimeString(total);
    }

    private static String getTimeString(long time){

        long hours = time / (60 * 60 * 1000);
        long minutes = time / (60 * 1000) % 60;

        return String.format("%d", hours) + ":" + String.format("%02d", minutes);
    }

    private static String getUserJobtitle(long groupId, long userId){
        try {

            if(groupId == 0 || userId == 0){
                return "";
            }

            String userJob = "";
            User user = UserLocalServiceUtil.fetchUser(userId);
            if(Validator.isNotNull(user)){
                userJob = user.getJobTitle();
            }

            UserData userData = null;
            List<UserData> list = UserDataLocalServiceUtil.findByGroupIdAndUserId(groupId, userId);

            if(list.size()>0){
                userData = list.get(0);
            }

            return Validator.isNotNull(userData)?userData.getJobTitle():userJob;

        } catch (Exception e) {
            _log.error(e);
        }
        return "";
    }
}

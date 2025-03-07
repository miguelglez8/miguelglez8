package com.preving.liferay.portlet.timesheet.report.web.bean;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.Validator;
import com.preving.liferay.portlet.calendar.manager.model.Activity;
import com.preving.liferay.portlet.calendar.manager.model.Sign;
import com.preving.liferay.portlet.calendar.manager.model.UserData;
import com.preving.liferay.portlet.calendar.manager.service.ActivityLocalServiceUtil;
import com.preving.liferay.portlet.calendar.manager.service.UserDataLocalServiceUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class PrevingSign {

    private SimpleDateFormat dateFormatTime = new SimpleDateFormat("H:mm");

    private long groupId;
    private long userId;
    private String jobTitle;
    private Date date;

    private List<Sign> signs = new ArrayList<>();


    public PrevingSign() {}

    public PrevingSign(long groupId, long userId, Date date) {
        this.groupId = groupId;
        this.userId = userId;
        this.date = date;
    }

    @Override
    public boolean equals(Object obj) {
        if(Validator.isNull(obj) || getClass().equals(obj.getClass())){
            return false;
        }

        PrevingSign sign = (PrevingSign) obj;

        return this.userId == sign.userId && this.date.equals(sign.date);
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    public List<Sign> getSigns() {
        return signs;
    }

    public void setSigns(List<Sign> signs) {
        this.signs = signs;
    }

    public String getUserName() {

        try {

            String userName = "";
            User user = UserLocalServiceUtil.fetchUser(this.userId);
            if(Validator.isNotNull(user)){
                userName = user.getFullName();
            }

            UserData userData = null;
            List<UserData> list = UserDataLocalServiceUtil.findByGroupIdAndUserId(groupId, userId);

            if(list.size()>0){
                userData = list.get(0);
            }

            return Validator.isNotNull(userData)?userData.getName() + " " + userData.getLastName():userName;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public String getJobTitle(){
        try {

            String userJob = "";
            User user = UserLocalServiceUtil.fetchUser(this.userId);
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
            e.printStackTrace();
        }
        return "";
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStartTime() {

        if(signs.size() == 0){
            return "";
        }
        Sign sign = signs.get(0);

        return dateFormatTime.format(sign.getStartDate());
    }

    public String getEndTime() {

        if(signs.size() == 0){
            return "";
        }

        Sign sign = signs.get(signs.size() - 1);

        if(Validator.isNull(sign.getFinishDate())){
            return "";
        }

        return dateFormatTime.format(sign.getFinishDate());
    }

    public String getTotalTime() {

        long totalTime = 0;
        Activity activity = null;
        for(Sign s: this.signs){

            try {
                activity = ActivityLocalServiceUtil.getActivity(s.getActivityId());

                if(activity.getWorkTime()&& Validator.isNotNull(s.getFinishDate())) {
                    totalTime += getTimeElapsedTimestamp(s.getStartDate(), s.getFinishDate());
                }

            } catch (PortalException e) {
                e.printStackTrace();
            }
        }

        return getTimeString(totalTime);
    }

    public String getTotalExtraTime() {

        long totalExtraTime = 0;
        Activity activity = null;
        for(Sign s: this.signs){

            try {
                activity = ActivityLocalServiceUtil.getActivity(s.getActivityId());

                if(activity.getType()==3 && activity.getWorkTime() && Validator.isNotNull(s.getFinishDate())) {
                    totalExtraTime += getTimeElapsedTimestamp(s.getStartDate(), s.getFinishDate());
                }

            } catch (PortalException e) {
                e.printStackTrace();
            }
        }

        return getTimeString(totalExtraTime);
    }


    public String getRest() {

        String rest = "<ul>";
        for(Sign s: this.signs){
            try {
                Activity activity = ActivityLocalServiceUtil.getActivity(s.getActivityId());
                if(!activity.getWorkTime()) {
                    rest += "<li>" + activity.getName() + "</li>";
                }
            } catch (PortalException e) {
                e.printStackTrace();
            }
        }
        rest += "</ul>";

        return rest;
    }
    public String getRest(Locale locale) {

        String rest = "<ul>";
        for(Sign s: this.signs){
            try {
                Activity activity = ActivityLocalServiceUtil.getActivity(s.getActivityId());
                if(!activity.getWorkTime()) {
                    rest += "<li>" + activity.getName(locale) + "</li>";
                }
            } catch (PortalException e) {
                e.printStackTrace();
            }
        }
        rest += "</ul>";

        return rest;
    }

    public String getObservations() {

        String rest = "<ul>";
        for(Sign s: this.signs){
            if(!"".equals(s.getObservations())) {
                rest += "<li>" + s.getObservations() + "</li>";
            }
        }
        rest += "</ul>";

        return rest;
    }

    public String getObservationsAdmin() {

        String rest = "<ul>";
        for(Sign s: this.signs){
            if(!"".equals(s.getObservationsAdmin())) {
                rest += "<li>" + s.getObservationsAdmin() + "</li>";
            }
        }
        rest += "</ul>";

        return rest;
    }


    @Override
    public String toString() {
        return "userName='" + getUserName() + '\'' +
                ", date=" + date +
                ", startTime='" + getStartTime() + '\'' +
                ", endTime='" + getEndTime() + '\'' +
                ", totalTime='" + getTotalTime() + '\'' +
                ", totalExtraTime='" + getTotalExtraTime() + '\'' +
                ", rest='" + getRest() + '\'';
    }

    public static long getTimeElapsedTimestamp(Date startDate, Date finishDate){

        if(Validator.isNull(finishDate)){
            return 0;
        }

        return finishDate.getTime() - startDate.getTime();
    }

    public static String getTimeString(long time){

        long hours = time / (60 * 60 * 1000) % 60;
        long minutes = time / (60 * 1000) % 60;

        return String.format("%02d", hours) + ":" + String.format("%02d", minutes);
    }

    public static String getTimeElapsed(Date startDate, Date finishDate){

        if(Validator.isNull(finishDate)){
            return "00:00";
        }

        long diff = finishDate.getTime() - startDate.getTime();

        long hours = diff / (60 * 60 * 1000) % 60;
        long minutes = diff / (60 * 1000) % 60;

        return String.format("%02d", hours) + ":" + String.format("%02d", minutes);
    }
}

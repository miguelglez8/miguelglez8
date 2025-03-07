package com.preving.liferay.portlet.timesheet.report.web.util;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.Validator;
import com.preving.liferay.portlet.calendar.manager.model.Sign;
import com.preving.liferay.portlet.calendar.manager.model.UserData;
import com.preving.liferay.portlet.calendar.manager.service.SignLocalServiceUtil;
import com.preving.liferay.portlet.calendar.manager.service.UserDataLocalServiceUtil;
import com.preving.liferay.portlet.calendar.manager.service.WorkCentersLocalServiceUtil;
import com.preving.liferay.portlet.timesheet.report.web.bean.PrevingSign;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class PrevingSignUtil {

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    private static Log _log = LogFactoryUtil.getLog(PrevingSignUtil.class);

    public static List<PrevingSign> getSignsBetwenDates(long companyId, long groupId, String jobTitle, Date startDate, Date endDate, List<Long> lstWorkCenterId) {



        /* -|- */


        List<PrevingSign> previngSigns = new ArrayList<>();

        List<Date> datesBetweenDates = getDatesBetweenDates(companyId, groupId, startDate, endDate);

        List<Long> usersBetweenDates = getUsersBetweenDates(companyId, groupId, startDate, endDate);

        List<Sign> signs = null;
        UserData userData = null;
        String userJobTitle = "";
        boolean isAdd = true;
        for (Date dateSign: datesBetweenDates) {

            for (long userId : usersBetweenDates) {

                //Check jobtitle
                if(!Validator.isBlank(jobTitle)) {
                    userJobTitle = getUserJobtitle(groupId, userId);
                    if(!jobTitle.equals(userJobTitle)){
                        continue;
                    }
                }



                signs = SignLocalServiceUtil.getSignsByCompanyIdAndGroupIdAndUserIdAndDate(companyId, groupId, userId, dateSign);

                isAdd = true;
                for(Long idWK: lstWorkCenterId){
                    if(idWK != 0){
                        userData = UserDataLocalServiceUtil.getUserDataByGroupIdAndUserId(groupId, userId);
                        if(Validator.isNotNull(userData) && userData.getWorkCenterId() == idWK){
                            isAdd = true;
                            break;
                        }else{
                            isAdd = false;
                        }
                    }
                }
                //_log.info("DATE " + dateSign + " USER " + userId + " isAdd: " + isAdd + " signs.size(): " + signs.size());
                if(signs.size() > 0 && isAdd) {
                    PrevingSign pSign = new PrevingSign();


                    pSign.setGroupId(groupId);
                    pSign.setUserId(userId);
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

    public static List<Date> getDatesBetweenDates(long companyId, long groupId, Date startDate, Date endDate){

        List<Date> dateList = new ArrayList<>();

        List<Sign> signsByDates = SignLocalServiceUtil.getSignsByDates(companyId, groupId, startDate, endDate);

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

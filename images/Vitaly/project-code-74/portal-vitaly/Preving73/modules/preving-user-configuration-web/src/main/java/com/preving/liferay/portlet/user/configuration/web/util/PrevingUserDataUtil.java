package com.preving.liferay.portlet.user.configuration.web.util;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.preving.liferay.portlet.calendar.manager.model.UserData;
import com.preving.liferay.portlet.calendar.manager.model.WorkCenters;
import com.preving.liferay.portlet.calendar.manager.service.UserDataLocalServiceUtil;
import com.preving.liferay.portlet.calendar.manager.service.WorkCentersLocalServiceUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class PrevingUserDataUtil {

    public static Log _log = LogFactoryUtil.getLog(PrevingUserDataUtil.class);

    public static UserData getUserData(long groupId, long userId){

        List<UserData> list = UserDataLocalServiceUtil.findByGroupIdAndUserId(groupId, userId);

/*        if(_log.isDebugEnabled()){
            _log.debug("groupId " + groupId + "userId " + userId + "UserData size " + list.size());
        }*/

        if(list.size()>0){
            return list.get(0);
        }

        return null;
    }

    public static UserData getUserData(long groupId, String email){

        List<UserData> list = UserDataLocalServiceUtil.findByGroupIdAndEmail(groupId, email);

        if(_log.isDebugEnabled()){
            _log.debug("groupId " + groupId+", email " + email+", UserData size " + list.size());
        }

        if(list.size()>0){
            return list.get(0);
        }

        return null;
    }

    public static UserData updateUserDataValues(long companyId, long groupId, long userId, String nif,
                                                String name, String lastName, String email, String jobTitle, long workCenterId,
                                                String salary, String genre, String notificationEndDate, boolean active, String strWorkCenter){

        UserData userData = getUserData(groupId, userId);

        if(_log.isDebugEnabled()){
            //_log.debug("UserData userId " + Validator.isNotNull(userData)?userData.getUserId():"null");
            _log.debug("UserData userId " + userData);
        }

        if(Validator.isNull(userData)){
            return createUserDataValues(companyId, groupId, userId, nif, name, lastName, email, jobTitle, workCenterId,
                    salary, genre, active, strWorkCenter);
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        Date notificationDate = null;
        if(!Validator.isBlank(notificationEndDate)){
            try {
                notificationDate = dateFormat.parse(notificationEndDate);
            } catch (ParseException e) {
                _log.error(e);
            }
        }

        userData.setCompanyId(companyId);
        userData.setGroupId(groupId);
        userData.setUserId(userId);
        userData.setNif(nif);
        userData.setName(name);
        userData.setLastName(lastName);
        userData.setEmail(email);
        userData.setJobTitle(jobTitle);
        if(workCenterId == 0){
            if(Validator.isNotNull(strWorkCenter)) userData.setWorkCenter(strWorkCenter);
        }else{
            WorkCenters wk =  WorkCentersLocalServiceUtil.fetchWorkCenters(workCenterId);
            if(Validator.isNotNull(wk)){
                userData.setWorkCenter(wk.getName());
            }
        }


        //userData.setWorkCenter(workCenter);
        userData.setSalary(salary);
        userData.setActive(active);
        userData.setGenre(genre);
        userData.setWorkCenterId(workCenterId);
        userData.setNotificationEndDate(notificationDate);

        UserDataLocalServiceUtil.updateUserData(userData);

        return userData;

    }

    public static UserData createUserDataValues(long companyId, long groupId, long userId, String nif, String name,
                                                String lastName, String email, String jobTitle, long workCenterId,
                                                String salary, String genre, boolean active, String strWorkCenter){

        UserData userData = getUserData(groupId, userId);;

        if(Validator.isNull(userData)){
            userData = UserDataLocalServiceUtil.createUserData(CounterLocalServiceUtil.increment(UserData.class.getName()));
        }

        userData.setCompanyId(companyId);
        userData.setGroupId(groupId);
        userData.setUserId(userId);
        userData.setNif(nif);
        userData.setName(name);
        userData.setLastName(lastName);
        userData.setEmail(email);
        userData.setJobTitle(jobTitle);
        //userData.setWorkCenter(strWorkCenter);
        userData.setSalary(salary);
        userData.setActive(active);
        userData.setGenre(genre);
        userData.setWorkCenterId(workCenterId);
        if(workCenterId == 0){
            if(Validator.isNotNull(strWorkCenter)) userData.setWorkCenter(strWorkCenter);
        }else{
            WorkCenters wk =  WorkCentersLocalServiceUtil.fetchWorkCenters(workCenterId);
            if(Validator.isNotNull(wk)){
                userData.setWorkCenter(wk.getName());
            }
        }

        UserDataLocalServiceUtil.updateUserData(userData);

        return userData;

    }

}

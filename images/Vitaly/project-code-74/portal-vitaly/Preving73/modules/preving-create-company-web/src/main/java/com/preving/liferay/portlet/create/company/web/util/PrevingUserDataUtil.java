package com.preving.liferay.portlet.create.company.web.util;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.Validator;
import com.preving.liferay.portlet.calendar.manager.model.UserData;
import com.preving.liferay.portlet.calendar.manager.service.UserDataLocalServiceUtil;
import com.preving.liferay.portlet.create.company.web.constants.CreateCompanyPortletKeys;

import java.util.List;

public class PrevingUserDataUtil {

    public static Log _log = LogFactoryUtil.getLog(PrevingUserDataUtil.class);

    public static UserData getUserData(long groupId, long userId){

        List<UserData> list = UserDataLocalServiceUtil.findByGroupIdAndUserId(groupId, userId);

        if(_log.isDebugEnabled()){
            _log.debug("groupId " + groupId + " userId " + userId +" UserData size " + list.size());
        }

        if(list.size()>1) {
            _log.error(" Too many UserData for userId: " + userId + ". UserData size " + list.size());
        }

        if(list.size()>0){
            return list.get(0);
        }

        return null;
    }

    public static UserData createNewUserData( long companyId, long groupId, User user){

        UserData userData = UserDataLocalServiceUtil.getUserDataByGroupIdAndUserId(groupId, user.getUserId());

        //Create user data
        if(Validator.isNull(userData)){

            String nif = "";
            String expandoUserNifName = PrefsPropsUtil.getString(CreateCompanyPortletKeys.PROPERTY_EXPANDO_USER_NIF);
            if(user.getExpandoBridge().hasAttribute(expandoUserNifName)) {
                nif = (String) user.getExpandoBridge().getAttribute(expandoUserNifName);
            }

            String salary = "";
            String expandoUserSalaryName = PrefsPropsUtil.getString(CreateCompanyPortletKeys.PROPERTY_EXPANDO_USER_SALARY);
            if(user.getExpandoBridge().hasAttribute(expandoUserSalaryName)) {
                salary = (String) user.getExpandoBridge().getAttribute(expandoUserSalaryName);
            }

            String centroTrabajo = "";
            String expandoUserCentroTrabajoName = "centroTrabajo";
            if(user.getExpandoBridge().hasAttribute(expandoUserSalaryName)) {
                centroTrabajo = (String) user.getExpandoBridge().getAttribute(expandoUserCentroTrabajoName);
            }

            userData = PrevingUserDataUtil.updateUserDataValues(companyId, groupId, user.getUserId(), nif, user.getFirstName(), user.getLastName(), user.getEmailAddress(), user.getJobTitle(), centroTrabajo, salary, true);

            if(_log.isDebugEnabled()){
                _log.debug("Create new UserData to " + user.getEmailAddress() + ", userDataId: " + userData.getUserDataId());
                _log.debug(" - userId " + userData.getUserId());
                _log.debug(" - nif " + userData.getUserId());
                _log.debug(" - jobTitle " + userData.getJobTitle());
                _log.debug(" - workCenter " + userData.getWorkCenter());
            }

        }

        UserDataLocalServiceUtil.updateUserData(userData);

        return userData;
    }

    public static UserData updateUserDataValues(long companyId, long groupId, long userId, String nif, String name, String lastName, String email, String jobTitle, String workCenter, String salary, boolean active){

        UserData userData = getUserData(groupId, userId);

        if(_log.isDebugEnabled()){
            _log.debug("UserData " + userData);
        }

        if(Validator.isNull(userData)){
            return createUserDataValues(companyId, groupId, userId, nif, name, lastName, email, jobTitle, workCenter, salary, active);
        }

        userData.setCompanyId(companyId);
        userData.setGroupId(groupId);
        userData.setUserId(userId);
        userData.setNif(nif);
        userData.setName(name);
        userData.setLastName(lastName);
        userData.setEmail(email);
        userData.setJobTitle(jobTitle);
        userData.setWorkCenter(workCenter);
        userData.setSalary(salary);
        userData.setActive(active);

        UserDataLocalServiceUtil.updateUserData(userData);

        return userData;

    }

    public static UserData createUserDataValues(long companyId, long groupId, long userId, String nif, String name, String lastName, String email, String jobTitle, String workCenter, String salary, boolean active){

        UserData userData = UserDataLocalServiceUtil.createUserData(CounterLocalServiceUtil.increment(UserData.class.getName()));

        userData.setCompanyId(companyId);
        userData.setGroupId(groupId);
        userData.setUserId(userId);
        userData.setNif(nif);
        userData.setName(name);
        userData.setLastName(lastName);
        userData.setEmail(email);
        userData.setJobTitle(jobTitle);
        userData.setWorkCenter(workCenter);
        userData.setSalary(salary);
        userData.setActive(active);

        UserDataLocalServiceUtil.updateUserData(userData);

        return userData;

    }

}

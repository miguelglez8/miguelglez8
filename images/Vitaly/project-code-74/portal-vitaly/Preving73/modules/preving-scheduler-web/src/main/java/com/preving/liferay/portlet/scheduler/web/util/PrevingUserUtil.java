package com.preving.liferay.portlet.scheduler.web.util;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.service.RoleServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.Validator;
import com.preving.liferay.portlet.calendar.manager.model.UserData;
import com.preving.liferay.portlet.calendar.manager.service.UserDataLocalServiceUtil;
import com.preving.liferay.portlet.scheduler.web.constants.PrevingSchedulerPortletKeys;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class PrevingUserUtil {

    private static Log _log = LogFactoryUtil.getLog(PrevingUserUtil.class);

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");


    public static boolean hasSiteAdminRole(long userId){

        try {

            User user = UserLocalServiceUtil.getUser(userId);

            String roleAdminName = PrefsPropsUtil.getString(PrevingSchedulerPortletKeys.PROPERTY_ROLE_COMPANY_ADMIN);

            for(Role role:user.getRoles()){
                if(Validator.isNotNull(role) && roleAdminName.equals(role.getName())){
                    return true;
                }
            }

        } catch (Exception e) {
            _log.error("Error checking roles.", e);
        }

        return false;
    }


    public static List<User> getListAdminIdFromGroup(long groupId){
        List<User> admins = new ArrayList<User>();

        List<User> groupUsers = UserLocalServiceUtil.getGroupUsers(groupId);

        for(User user:groupUsers){
            if(user.isActive() && hasSiteAdminRole(user.getUserId())){
                admins.add(user);
            }
        }

        return admins;
    }



    public static String getExpandoValueFromUser(User user, String expandoName){

        String res = "";

        try {

            if(Validator.isNotNull(user) && user.getExpandoBridge().hasAttribute(expandoName)) {

                PermissionChecker checker = PermissionCheckerFactoryUtil.create(user);
                PermissionThreadLocal.setPermissionChecker(checker);

                if(Validator.isNotNull(user.getExpandoBridge().getAttribute(expandoName))){
                    res = user.getExpandoBridge().getAttribute(expandoName).toString();
                }
            }

        } catch (Exception e) {
            _log.error("Error getting expando value from user ", e);
        }

        return res;
    }

    public static UserData getUserData(long groupId, long userId){

        List<UserData> list = UserDataLocalServiceUtil.findByGroupIdAndUserId(groupId, userId);

        if(_log.isDebugEnabled()){
            _log.debug("groupId " + groupId);
            _log.debug("userId " + userId);
            _log.debug("UserData size " + list.size());
        }

        if(list.size()>0){
            return list.get(0);
        }

        return null;
    }

}

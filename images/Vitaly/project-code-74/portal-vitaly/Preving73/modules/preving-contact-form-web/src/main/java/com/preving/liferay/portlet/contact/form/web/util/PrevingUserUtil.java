package com.preving.liferay.portlet.contact.form.web.util;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserGroupRoleLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.Validator;
import com.preving.liferay.portlet.calendar.manager.model.UserData;
import com.preving.liferay.portlet.calendar.manager.service.UserDataLocalServiceUtil;
import com.preving.liferay.portlet.contact.form.web.constants.ContactFormPortletKeys;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class PrevingUserUtil {

    private static Log _log = LogFactoryUtil.getLog(PrevingUserUtil.class);

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");


    public static boolean hasSiteAdminRole(long groupId, long userId){

        try {

            User user = UserLocalServiceUtil.getUser(userId);

            String roleAdminName = PrefsPropsUtil.getString(ContactFormPortletKeys.PROPERTY_ROLE_COMPANY_SITE_ADMIN);

/*            for(Role role:user.getRoles()){
                if(roleAdminName.equals(role.getName())){
                    return true;
                }
            }*/

            return UserGroupRoleLocalServiceUtil.hasUserGroupRole(userId, groupId, roleAdminName);

        } catch (Exception e) {
            _log.error("Error checking roles.", e);
        }

        return false;
    }


    public static List<User> getListAdminIdFromGroup(long groupId){
        List<User> admins = new ArrayList<User>();

        List<User> groupUsers = UserLocalServiceUtil.getGroupUsers(groupId);

        for(User user:groupUsers){
            UserData userData = UserDataLocalServiceUtil.getUserDataByGroupIdAndUserId(groupId, user.getUserId());
            if(Validator.isNotNull(userData) && userData.isActive() && hasSiteAdminRole(groupId, user.getUserId())){
                admins.add(user);
            }
        }

        return admins;
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

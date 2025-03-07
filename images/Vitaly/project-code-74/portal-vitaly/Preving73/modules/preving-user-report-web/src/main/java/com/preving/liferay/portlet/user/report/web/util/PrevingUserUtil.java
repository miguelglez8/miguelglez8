package com.preving.liferay.portlet.user.report.web.util;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Contact;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ContactLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.Validator;
import com.preving.liferay.portlet.calendar.manager.service.UserDataLocalServiceUtil;

public class PrevingUserUtil {

    public static String getExpandoValueFromUser(User user, String expandoName){

        String res = "";

        try {

            if(Validator.isNotNull(user) && user.getExpandoBridge().hasAttribute(expandoName)) {
                res = user.getExpandoBridge().getAttribute(expandoName).toString();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return res;
    }

    public static String getGenreFromUser(long groupId, long  userId){

        String res = "";

        try {

            boolean maleGenre = UserDataLocalServiceUtil.getMaleGenre(groupId, userId);
            boolean femaleGenre = UserDataLocalServiceUtil.getFemaleGenre(groupId, userId);

            if(maleGenre){
                res = "userreport.view.genre.man";
            }

            if(femaleGenre){
                res = "userreport.view.genre.woman";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return res;
    }



}

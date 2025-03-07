package com.preving.liferay.portlet.timesheet.web.util;

import com.liferay.mail.kernel.model.MailMessage;
import com.liferay.petra.mail.MailEngine;
import com.liferay.petra.mail.MailEngineException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.Validator;
import com.preving.liferay.portlet.timesheet.web.constants.TimesheetPortletKeys;

import javax.mail.internet.InternetAddress;
import java.io.UnsupportedEncodingException;

public class PrevingMailUtil {

    private static Log _log = LogFactoryUtil.getLog(PrevingMailUtil.class);

    public static void sendMail(long userId, String subject, String message){

        String adminEmailAddress = "test@liferay.com";
        String adminEmailAddressProps = PrefsPropsUtil.getString(TimesheetPortletKeys.TIMESHEET);
        if(!Validator.isBlank(adminEmailAddressProps)){
            adminEmailAddress = adminEmailAddressProps;
        }

        String adminEmailName = "Test";
        String adminEmailNameProps = PrefsPropsUtil.getString(TimesheetPortletKeys.CONFIGURATION_COMPANY_CONF_USER_NAME);
        if(!Validator.isBlank(adminEmailAddressProps)){
            adminEmailName = adminEmailNameProps;
        }

        try {

            User user = UserLocalServiceUtil.getUser(userId);

            sendMail(adminEmailAddress, adminEmailName, user.getEmailAddress(), user.getFullName(), subject, message);

        } catch (PortalException e) {
            _log.error("Error sending mail sign summary.", e);
        }


    }

    private static void sendMail(String from, String fromName, String to, String toName, String subject, String message){

        try {

            MailMessage mail = new MailMessage();

            mail.setFrom(new InternetAddress(from, fromName));
            mail.setTo(new InternetAddress(to, toName));

            mail.setSubject(subject);
            mail.setBody(message);
            mail.setHTMLFormat(true);

            MailEngine.send(mail);

        } catch (UnsupportedEncodingException e) {
            _log.error(e);
        } catch (MailEngineException e) {
            _log.error(e);
        }

    }


}

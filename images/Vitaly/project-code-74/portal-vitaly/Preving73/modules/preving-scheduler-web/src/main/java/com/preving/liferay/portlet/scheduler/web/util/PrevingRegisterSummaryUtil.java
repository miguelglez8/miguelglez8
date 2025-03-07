package com.preving.liferay.portlet.scheduler.web.util;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.scheduler.SchedulerEngineHelperUtil;
import com.liferay.portal.kernel.scheduler.StorageType;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.util.ContentUtil;
import com.preving.liferay.portlet.calendar.manager.model.Sign;
import com.preving.liferay.portlet.calendar.manager.model.UserData;
import com.preving.liferay.portlet.calendar.manager.service.SignLocalServiceUtil;
import com.preving.liferay.portlet.calendar.manager.service.UserDataLocalServiceUtil;
import com.preving.liferay.portlet.mailing.web.mail.ReportMailing;
import com.preving.liferay.portlet.scheduler.web.constants.PrevingSchedulerPortletKeys;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class PrevingRegisterSummaryUtil {

    private static Log _log = LogFactoryUtil.getLog(PrevingRegisterSummaryUtil.class);

    static SimpleDateFormat dateFormatLog = new SimpleDateFormat("yyyy-dd-MM HH:mm:ss.SSS");

    public static void sendMailDaily(long companyId, long groupId){

        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        calendar.add(Calendar.DAY_OF_MONTH, -1);
        Date yesterdayStart = calendar.getTime();

        System.out.println(dateFormatLog.format(new Date()) + " SCHEDULER START ======================================================");
        System.out.println(dateFormatLog.format(new Date()) + " SCHEDULER sendMailDaily.  Send daily summary to company: " + companyId + " groupId: " + groupId + ", day: " + new Date());

        if(Calendar.SATURDAY == calendar.get(Calendar.DAY_OF_WEEK)
                || Calendar.SUNDAY == calendar.get(Calendar.DAY_OF_WEEK)){
            System.out.println("  SchedulerDailyUtil not in Saturday or Sunday.");

            return;
        }

        if(_log.isDebugEnabled()) {
            _log.debug("Send daily summary to company: " + companyId + " groupId: " + groupId + ", start: " + yesterdayStart);
        }

        String info = getRegistersTable(companyId, groupId, yesterdayStart, yesterdayStart);

        for(User user:PrevingUserUtil.getListAdminIdFromGroup(groupId)) {

            if(!UserDataLocalServiceUtil.canSendMailToUser(groupId, user.getUserId())){

                System.out.println(dateFormatLog.format(new Date()) + "   Don't send email daily. The user " + user.getEmailAddress() + " in group " +  groupId + " have notifications disabled today. ");

                continue;
            }

            ReportMailing.signReportToAdminDaily(user,info);

            String subject = LanguageUtil.get(ResourceBundle.getBundle("content/Language", user.getLocale()), "previngmailing.mail.daily.subject");
            NotificacionUtil.sendNotificacion(user.getUserId(), user.getUserId(), subject, info);

            System.out.println(dateFormatLog.format(new Date()) + "   Send email. The user " + user.getEmailAddress() + " in group " +  groupId + " with daily reports. ");

        }

        System.out.println(dateFormatLog.format(new Date()) + " ======================================================================");
        System.out.println(dateFormatLog.format(new Date()) + " SCHEDULER END ========================================================");

    }

    public static void sendMailWeekly(long companyId, long groupId) {

        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        calendar.add(Calendar.WEEK_OF_YEAR, -1);
        Date lastWeekStart = calendar.getTime();

        calendar.add(Calendar.WEEK_OF_YEAR, 1);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        Date yesterdayStart = calendar.getTime();

        System.out.println(dateFormatLog.format(new Date()) + " SCHEDULER START ======================================================");
        System.out.println(dateFormatLog.format(new Date()) + " SCHEDULER sendMailWeekly.  Send weekly summary to company: " + companyId + " groupId: " + groupId + ", start: " + lastWeekStart);

        String info = getRegistersTable(companyId, groupId, lastWeekStart, yesterdayStart);

        for(User user:PrevingUserUtil.getListAdminIdFromGroup(groupId)) {

            if(!UserDataLocalServiceUtil.canSendMailToUser(groupId, user.getUserId())){

                System.out.println(dateFormatLog.format(new Date()) + "   Don't send email weekly. The user " + user.getEmailAddress() + " in group " +  groupId + " have notifications disabled today. ");

                continue;
            }

            ReportMailing.signReportToAdminWeekly(user,info);

            String subject = LanguageUtil.get(ResourceBundle.getBundle("content/Language", user.getLocale()), "previngmailing.mail.weekly.subject");
            NotificacionUtil.sendNotificacion(user.getUserId(), user.getUserId(), subject, info);

            System.out.println(dateFormatLog.format(new Date()) + "   Send email. The user " + user.getEmailAddress() + " in group " +  groupId + " with weekly reports. ");

        }

        System.out.println(dateFormatLog.format(new Date()) + " ======================================================================");
        System.out.println(dateFormatLog.format(new Date()) + " SCHEDULER END ========================================================");

    }

    public static void sendMailMonthly(long companyId, long groupId) {

        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        calendar.add(Calendar.MONTH, -1);

        Date lastMonthStart = calendar.getTime();

        calendar.add(Calendar.MONTH, 1);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        Date yesterdayStart = calendar.getTime();

        System.out.println(dateFormatLog.format(new Date()) + " SCHEDULER START ======================================================");
        System.out.println(dateFormatLog.format(new Date()) + " SCHEDULER sendMailMonthly.  Send monthly summary to company: " + companyId + " groupId: " + groupId);


        if(_log.isDebugEnabled()) {
            _log.debug("  Send monthly summary to company: " + companyId + " groupId: " + groupId + ", start: " + lastMonthStart + ", end: " + yesterdayStart);
        }

        String info = getRegistersTable(companyId, groupId, lastMonthStart, yesterdayStart);

        for(User user:PrevingUserUtil.getListAdminIdFromGroup(groupId)) {

            if(!UserDataLocalServiceUtil.canSendMailToUser(groupId, user.getUserId())){

                System.out.println(dateFormatLog.format(new Date()) + "   Don't send email monthly. The user " + user.getEmailAddress() + " in group " +  groupId + " have notifications disabled today. ");

                continue;
            }

            ReportMailing.signReportToAdminWeekly(user,info);

            String subject = LanguageUtil.get(ResourceBundle.getBundle("content/Language", user.getLocale()), "previngmailing.mail.monthly.subject");
            NotificacionUtil.sendNotificacion(user.getUserId(), user.getUserId(), subject, info);

            System.out.println(dateFormatLog.format(new Date()) + "   Send email. The user " + user.getEmailAddress() + " in group " +  groupId + " with monthly reports. ");

        }

        System.out.println(dateFormatLog.format(new Date()) + " ======================================================================");
        System.out.println(dateFormatLog.format(new Date()) + " SCHEDULER END ========================================================");

    }


    private static String getRegistersTable(long companyId, long groupId, Date start, Date end){

        try {

            List<Sign> signsByDates = SignLocalServiceUtil.getSignsByDates(companyId, groupId, start, end);

            return PrevingRegisterUtil.getSignsTable(signsByDates);

        } catch (SystemException e) {
            _log.error(e);
        }
        return "";
    }
}

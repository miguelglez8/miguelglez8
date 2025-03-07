package com.preving.liferay.portlet.company.configuration.web.util;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.preving.liferay.portlet.calendar.manager.model.Sign;
import com.preving.liferay.portlet.calendar.manager.service.SignLocalServiceUtil;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class PrevingRegisterSummaryUtil {

    private static Log _log = LogFactoryUtil.getLog(PrevingRegisterSummaryUtil.class);

    public static void sendMailDaily(long companyId, long groupId, long userId){

        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        calendar.add(Calendar.DAY_OF_WEEK_IN_MONTH, -1);
        Date yesterdayStart = calendar.getTime();

        if(_log.isDebugEnabled()) {
            _log.debug("  Send daily summary to company: " + companyId + " groupId: " + groupId + ", userId: " + userId+ ", start: " + yesterdayStart);
        }

        String info = getRegistersTable(companyId, groupId, yesterdayStart, new Date());

        PrevingMailUtil.sendMail(userId, "Resumen diario de registros", info);

        NotificacionUtil.sendNotificacion(userId, userId,
                "Resumen diario de registros",
                "Resumen diario <br />" + info + "");

    }

    public static void sendMailWeekly(long companyId, long groupId, long userId) {

        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        calendar.add(Calendar.WEEK_OF_YEAR, -1);
        Date lastWeekStart = calendar.getTime();

        if(_log.isDebugEnabled()) {
            _log.debug("  Send weekly summary to company: " + companyId + " groupId: " + groupId + ", userId: " + userId+ ", start: " + lastWeekStart);
        }

        String info = getRegistersTable(companyId, groupId, lastWeekStart, new Date());

        PrevingMailUtil.sendMail(userId, "Resumen semanal de registros", info);

        NotificacionUtil.sendNotificacion(userId, userId,
                "Resumen semanal de registros",
                "Las estadísticas semanales: <br />" + info + "");

    }

    public static void sendMailMonthly(long companyId, long groupId, long userId) {

        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        calendar.add(Calendar.MONTH, -1);

        Date lastMonthStart = calendar.getTime();

        if(_log.isDebugEnabled()) {
            _log.debug("  Send monthly summary to company: " + companyId + " groupId: " + groupId + ", userId: " + userId + ", start: " + lastMonthStart);
        }

        String info = getRegistersTable(companyId, groupId, lastMonthStart, new Date());

        PrevingMailUtil.sendMail(userId, "Resumen mensual de registros", info);

        NotificacionUtil.sendNotificacion(userId, userId,
                "Resumen mensual de registros",
                "Resumen mensual: <br />" + info + "");
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

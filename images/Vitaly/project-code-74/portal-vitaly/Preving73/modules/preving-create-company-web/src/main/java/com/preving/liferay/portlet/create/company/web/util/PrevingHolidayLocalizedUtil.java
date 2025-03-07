package com.preving.liferay.portlet.create.company.web.util;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.preving.liferay.portlet.calendar.manager.model.Holiday;
import com.preving.liferay.portlet.calendar.manager.service.HolidayLocalServiceUtil;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class PrevingHolidayLocalizedUtil {

    private static Log _log = LogFactoryUtil.getLog(PrevingHolidayLocalizedUtil.class);

    public static void createDefaultHolidays(long companyId, long groupId) {

        String HOLIDAY_TYPE_NACIONAL_VALUE = "nacional";

        createHoliday(companyId, groupId, 1, 1, 0,
                getLanguageMap("Año nuevo","Cap d'any"),
                true, true, HOLIDAY_TYPE_NACIONAL_VALUE);

        createHoliday(companyId, groupId, 6, 1, 0,
                getLanguageMap("Día de Reyes","Reis"),
                true, true, HOLIDAY_TYPE_NACIONAL_VALUE);

        createHoliday(companyId, groupId, 10, 4, 0,
                getLanguageMap("Viernes Santo", "Divendres Sant"),
                true, true, HOLIDAY_TYPE_NACIONAL_VALUE);

        createHoliday(companyId, groupId, 1, 5, 0,
                getLanguageMap("Día del trabajador", "Festa del treball"),
                true, true, HOLIDAY_TYPE_NACIONAL_VALUE);

        createHoliday(companyId, groupId, 15, 8, 0,
                getLanguageMap("Asunción de la Virgen", "L'Assumpció"),
                true, true, HOLIDAY_TYPE_NACIONAL_VALUE);

        createHoliday(companyId, groupId, 12, 10, 0,
                getLanguageMap("Día de la Hispanidad", "Festa Nacional d'Espanya"),
                true, true, HOLIDAY_TYPE_NACIONAL_VALUE);

        createHoliday(companyId, groupId, 8, 12, 0,
                getLanguageMap("Inmaculada Concepción", "La Immaculada"),
                true, true, HOLIDAY_TYPE_NACIONAL_VALUE);

        createHoliday(companyId, groupId, 25, 12, 0,
                getLanguageMap("Navidad","Nadal"),
                true, true, HOLIDAY_TYPE_NACIONAL_VALUE);


    }

    private static void createHoliday(long companyId, long groudId, int day, int month, int year, Map<Locale, String> nameMap, boolean active, boolean allowSign, String typeHoliday) {

        try {

            Holiday holiday = HolidayLocalServiceUtil.createHoliday(CounterLocalServiceUtil.increment(Holiday.class.getName()));

            holiday.setCompanyId(companyId);
            holiday.setGroupId(groudId);
            holiday.setDay(day);
            holiday.setMonth(month);
            holiday.setYear(year);
            holiday.setNameMap(nameMap);
            holiday.setActive(active);
            holiday.setAllowSign(allowSign);
            holiday.setTypeHoliday(typeHoliday);

            HolidayLocalServiceUtil.updateHoliday(holiday);

        } catch (Exception e) {
            _log.error(e);
        }

    }

    private static Map<Locale, String> getLanguageMap(String es_ES_Value, String ca_ES_Value){

        Locale defaultLocale = Locale.getDefault();

        Map<Locale, String> localizationMap = new HashMap<Locale, String>();

        localizationMap.put(defaultLocale, es_ES_Value);
        localizationMap.put(Locale.forLanguageTag("es-ES"), es_ES_Value);
        localizationMap.put(Locale.forLanguageTag("ca-ES"), ca_ES_Value);
        //localizationMap.put(Locale.forLanguageTag("ga-ES"), ca_ESValue);
        //localizationMap.put(Locale.forLanguageTag("eu-ES"), ca_ESValue);
        //localizationMap.put(Locale.forLanguageTag("en-US"), ca_ESValue);

        return localizationMap;
    }
}

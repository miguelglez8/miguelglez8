package com.preving.liferay.portlet.holiday.configuration.web.util;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import com.preving.liferay.portlet.calendar.manager.model.Activity;
import com.preving.liferay.portlet.calendar.manager.model.Holiday;
import com.preving.liferay.portlet.calendar.manager.service.HolidayLocalServiceUtil;
import com.preving.liferay.portlet.holiday.configuration.web.constants.HolidayConfigurationPortletKeys;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

public class PrevingHolidayUtil {

    private static Log _log = LogFactoryUtil.getLog(PrevingHolidayUtil.class);

    public static Holiday createHoliday(long companyId, long groupId, Map<Locale, String> nameLocalized, int day, int month, int year, boolean active, boolean allowSign, String typeHoliday) {

        Holiday holiday = null;

        try {

            holiday = HolidayLocalServiceUtil.createHoliday(CounterLocalServiceUtil.increment(Holiday.class.getName()));

            holiday.setCompanyId(companyId);
            holiday.setGroupId(groupId);
            holiday.setNameMap(nameLocalized);
            holiday.setDay(day);
            holiday.setMonth(month);
            holiday.setYear(year);
            holiday.setActive(active);
            holiday.setAllowSign(allowSign);
            holiday.setTypeHoliday(typeHoliday);

            HolidayLocalServiceUtil.updateHoliday(holiday);

            if (_log.isDebugEnabled()) {
                _log.debug("Created new holiday: ");
                _log.debug("   - companyId : " + companyId);
                _log.debug("   - groupId : " + groupId);
                _log.debug("   - name : " + nameLocalized);
                _log.debug("   - day : " + day);
                _log.debug("   - month : " + month);
                _log.debug("   - year : " + year);
                _log.debug("   - active : " + active);
                _log.debug("   - allowSign : " + allowSign);
            }

        } catch (Exception e) {
            _log.error("Can't create holiday with name: " + nameLocalized + ".", e);
        }

        return holiday;
    }

    public static Holiday getHolidayByName(String holidayName) {
        Holiday holiday = null;
        try {
            List<Holiday> listHolidays = HolidayLocalServiceUtil.getHolidaysFromName(holidayName);
            holiday = listHolidays.get(0);

            if (Validator.isNotNull(holiday)) {
                return holiday;
            }
        } catch (Exception e) {
            if (_log.isDebugEnabled()) {
                _log.debug("The holiday with id: " + holiday.getHolidayId() + " doesn't exist .");
            }
        }

        return null;
    }

    public static Holiday updateHoliday(long holidayId, long companyId, long groupId, Map<Locale, String> nameLocalized, int day, int month, int year, boolean active, boolean allowSign, String typeHoliday) {
        Holiday holiday = null;

        try {
            holiday = HolidayLocalServiceUtil.getHoliday(holidayId);
        } catch (Exception e) {
            _log.error(e.getCause(), e);
        }

        if (Validator.isNotNull(holiday)) {
            holiday.setNameMap(nameLocalized);
            holiday.setDay(day);
            holiday.setMonth(month);
            holiday.setYear(year);
            holiday.setActive(active);
            holiday.setAllowSign(allowSign);
            holiday.setTypeHoliday(typeHoliday);
            HolidayLocalServiceUtil.updateHoliday(holiday);

            if (_log.isDebugEnabled()) {
                _log.debug("Holiday updated with id: " + holidayId);
            }
        }

        return holiday;
    }

    public static void deleteHoliday(long holidayId){
        try {
            HolidayLocalServiceUtil.deleteHoliday(holidayId);

            if (_log.isDebugEnabled()) {
                _log.debug("Holiday deleted with id: " + holidayId);
            }
        } catch (Exception e) {
            _log.error(e.getCause(), e);
        }

    }

    public static String getAlexGoodNombre(String name){
        CharsetDecoder decoder = Charset.forName("UTF-8").newDecoder();
        try {
            ByteBuffer buffer = ByteBuffer.wrap(name.getBytes(StandardCharsets.ISO_8859_1));
            CharBuffer charBuffer = decoder.decode(buffer);
            name  = charBuffer.toString();
        } catch (CharacterCodingException e) {
            e.printStackTrace();
        }
        return name;

    }
    public static String getTitleOfType(String typeHoliday, Locale locale){
        ResourceBundle bundle = ResourceBundle.getBundle("content/Language", locale);

        String type = LanguageUtil.get(bundle, "holiday.configuration.company");
        if(HolidayConfigurationPortletKeys.HOLIDAY_TYPE_NACIONAL_VALUE.equals(typeHoliday)){
            type = LanguageUtil.get(bundle, "holiday.configuration.nacional");
        }else if(HolidayConfigurationPortletKeys.HOLIDAY_TYPE_AUTONOMICO_VALUE.equals(typeHoliday)){
            type = LanguageUtil.get(bundle, "holiday.configuration.autonomico");
        }else if(HolidayConfigurationPortletKeys.HOLIDAY_TYPE_LOCAL_VALUE.equals(typeHoliday)){
            type = LanguageUtil.get(bundle, "holiday.configuration.local");
        }
        byte[] bytesUtf8 = type.getBytes(StandardCharsets.UTF_8);
        type = new String(bytesUtf8, StandardCharsets.UTF_8);
        return type;
    }
}

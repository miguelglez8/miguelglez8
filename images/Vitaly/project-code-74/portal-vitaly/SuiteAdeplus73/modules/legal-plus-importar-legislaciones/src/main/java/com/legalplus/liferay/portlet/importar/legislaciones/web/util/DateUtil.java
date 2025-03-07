package com.legalplus.liferay.portlet.importar.legislaciones.web.util;

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Date;
import java.util.ResourceBundle;

public class DateUtil {

    public static Date getDate(String date) {
        try {
            String format = LanguageUtil.get(ResourceBundle.getBundle("content/Language", LocaleUtil.getDefault()), "import.date.format");
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            Date nDate = sdf.parse(StringUtil.trim(date));

            return nDate;
        } catch (ParseException e) {
            return null;
        }
    }

    public static boolean isValid(final String date) {
        boolean valid = true;
        try {
            LocalDate today = LocalDate.now();
            LocalDate cur = LocalDate.parse(StringUtil.trim(date),
                                    DateTimeFormatter.ofPattern("dd/MM/uuuu")
                                            .withResolverStyle(ResolverStyle.STRICT)
                            );

            if (cur.isAfter(today)) {
                valid = false;
            }
        } catch (DateTimeParseException e) {
            valid = false;
        }
        return valid;
    }
}

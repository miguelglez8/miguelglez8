package com.adeplus.liferay.portlet.contact.form.web.util;

import com.liferay.petra.string.StringPool;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

public class AdeplusCategoryUtil {

    public static List<String> getCategoryListWithoutDuplicates(List<String> categoriesList){
        return categoriesList.stream()
                .distinct()
                .collect(Collectors.toList());
    }

    public static List<String> getStringPositionInList(List<String> categoryList, List<String> emailList, String selectedValue){
        List<String> emails = new ArrayList<>();
        int i=0;
        for(String category:categoryList){
            if(selectedValue.equals(category) && emailList.size() >= i){
                emails.add(emailList.get(i));
            }
            i++;
        }

        return emails;
    }

    public static String getXmlFromMap(Map<Locale, String> contentMap) {

        StringBuilder sb = new StringBuilder();
        String comma = StringPool.BLANK;

        sb.append("<?xml version='1.0' encoding='UTF-8'?><root available-locales='");
        for (Locale l : contentMap.keySet()) {
            sb.append(comma);
            sb.append(l);
            comma = StringPool.COMMA;
        }
        sb.append("' default-locale='" + "es_ES" + "'>");

        for (Locale l : contentMap.keySet()) {
            sb.append("<Title language-id='");
            sb.append(l);
            sb.append("'>");
            sb.append(contentMap.get(l));
            sb.append("</Title>");
        }
        sb.append("</root>");
        return sb.toString();
    }
}

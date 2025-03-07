package com.preving.liferay.portlet.create.company.web.util;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import com.preving.liferay.portlet.calendar.manager.model.Activity;
import com.preving.liferay.portlet.calendar.manager.service.ActivityLocalServiceUtil;
import com.preving.liferay.portlet.create.company.web.constants.CreateCompanyPortletKeys;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class PrevingActivityLocalizedUtil {

    private static Log _log = LogFactoryUtil.getLog(PrevingActivityLocalizedUtil.class);

    public static void createDefaultActivities(long companyId, long groupId){

        /**
         *  Blue #0000FF
         *  Green #008000
         *  Red #FF0000
         *  Yellow #FFFF00
         *  Gray #C0C0C0
         *  Lime #00FF00
         *  Purple #800080
         *  Aqua #00FFFF
         */

        List<Activity> activityList = ActivityLocalServiceUtil.getActivitiesFromGroup(companyId,groupId);
        if(Validator.isNotNull(activityList) && activityList.size() > 0) return; // si ya las ha creado en otro punto no crearlas de nuevo.
        createActivity(companyId, groupId, 1, getLanguageMap(CreateCompanyPortletKeys.ACTIVITY_TYPE_OFFICE, "Jornada laboral en oficina"), true, true, "#0000FF");
        createActivity(companyId, groupId, 1, getLanguageMap(CreateCompanyPortletKeys.ACTIVITY_TYPE_EXTERIOR,"Jornada laboral en exterior"), true, true, "#00FFFF");
        createActivity(companyId, groupId, 1, getLanguageMap(CreateCompanyPortletKeys.ACTIVITY_TYPE_TELEWORK,"Jornada laboral en teletreball"), true, true, "#008000");
        createActivity(companyId, groupId, 1, getLanguageMap(CreateCompanyPortletKeys.ACTIVITY_TYPE_OTHER_WORK,"Jornada laboral - altres"), true, true, "#800080");
        createActivity(companyId, groupId, 2, getLanguageMap(CreateCompanyPortletKeys.ACTIVITY_TYPE_BREAK,"Pausa - descans"), true, false, "#FF0000");
        createActivity(companyId, groupId, 2, getLanguageMap(CreateCompanyPortletKeys.ACTIVITY_TYPE_LUNCH,"Pausa - dinar"), true, false, "#FFFF00");
        createActivity(companyId, groupId, 2, getLanguageMap(CreateCompanyPortletKeys.ACTIVITY_TYPE_HEALTH,"Pausa - visita mèdica"), true, false, "#C0C0C0");
        createActivity(companyId, groupId, 2, getLanguageMap(CreateCompanyPortletKeys.ACTIVITY_TYPE_OTHER,"Pausa - altres"), true, false, "#800080");

    }

    private static void createActivity(long companyId, long groudId, int type, Map<Locale, String> nameMap, boolean active, boolean workTime, String color){

        try {

            Activity activity = ActivityLocalServiceUtil.createActivity(CounterLocalServiceUtil.increment(Activity.class.getName()));

            activity.setCompanyId(companyId);
            activity.setGroupId(groudId);
            activity.setType(type);
            activity.setNameMap(nameMap);
            activity.setActive(active);
            activity.setWorkTime(workTime);
            activity.setColor(color);

            ActivityLocalServiceUtil.updateActivity(activity);

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

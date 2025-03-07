package com.preving.liferay.portlet.mailing.web.util;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.util.ContentUtil;

public class PrevingTemplateUtil {

    private static Log _log = LogFactoryUtil.getLog(PrevingTemplateUtil.class);

    public static String getTemplate(User user, String templateName){

        String bodyTemplate = "";
        try {

            bodyTemplate = ContentUtil.get(templateName + "_" + user.getLocale() +".tmpl");

        } catch (Exception e) {
            _log.error("Error obteniendo la plantilla: " + templateName+ "_" + user.getLocale() +".tmpl");

        }

        try {
            if(Validator.isNull(bodyTemplate) || Validator.isBlank(bodyTemplate)){
                bodyTemplate = ContentUtil.get(templateName+"_es_ES.tmpl");
            }
        } catch (Exception e) {
            _log.error("Error obteniendo la plantilla por defecto: " + templateName+"_es_ES.tmpl");

        }

        if(_log.isDebugEnabled()){
            _log.debug("Lenght plantilla: " + bodyTemplate.length());
        }

        return bodyTemplate;
    }

    public static String getTemplate(ThemeDisplay themeDisplay, String templateName){

        String bodyTemplate = "";
        try {

            bodyTemplate = ContentUtil.get(templateName + "_" + themeDisplay.getLocale() +".tmpl");

        } catch (Exception e) {
            _log.error("Error obteniendo la plantilla: " + templateName+ "_" + themeDisplay.getLocale() +".tmpl");

        }

        try {
            if(Validator.isNull(bodyTemplate) || Validator.isBlank(bodyTemplate)){
                bodyTemplate = ContentUtil.get(templateName+"_es_ES.tmpl");
            }
        } catch (Exception e) {
            _log.error("Error obteniendo la plantilla por defecto: " + templateName+"_es_ES.tmpl");

        }

        if(_log.isDebugEnabled()){
            _log.debug("Lenght plantilla: " + bodyTemplate.length());
        }

        return bodyTemplate;
    }

    public static String replaceImageBase64ToTemplate(String bodyTemplate, String[] keys, String[] srcBase64s){

        return StringUtil.replace(bodyTemplate, keys, srcBase64s);

    }

}

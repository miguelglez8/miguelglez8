package com.canal.etico.liferay.portlet.mailing.web.v2.util;

import com.liferay.petra.content.ContentUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.*;

import java.util.Locale;

public class PrevingTemplateUtil {
    private static Log _log = LogFactoryUtil.getLog(PrevingTemplateUtil.class);

    public static String getTemplate(Locale locale, String templateName){

        ClassLoader classLoader = PortalClassLoaderUtil.getClassLoader();

        String bodyTemplate = "";
        try {

            bodyTemplate = ContentUtil.get(PrevingTemplateUtil.class.getClassLoader(), templateName + "_" + locale +".tmpl");

        } catch (Exception e) {
            _log.error("Error obteniendo la plantilla: " + templateName+ "_" + locale +".tmpl");
        }

        try {
            if(Validator.isNull(bodyTemplate) || Validator.isBlank(bodyTemplate)){
                bodyTemplate = ContentUtil.get(PrevingTemplateUtil.class.getClassLoader(),templateName+"_es_ES.tmpl");
            }
        } catch (Exception e) {
            _log.error("Error obteniendo la plantilla por defecto: " + templateName+"_es_ES.tmpl");

        }

        if(_log.isDebugEnabled()){
            _log.debug("Length plantilla: " + bodyTemplate.length());
        }

        return bodyTemplate;
    }

    public static String replaceImageBase64ToTemplate(String bodyTemplate, String[] keys, String[] srcBase64s){

        return StringUtil.replace(bodyTemplate, keys, srcBase64s);

    }


}

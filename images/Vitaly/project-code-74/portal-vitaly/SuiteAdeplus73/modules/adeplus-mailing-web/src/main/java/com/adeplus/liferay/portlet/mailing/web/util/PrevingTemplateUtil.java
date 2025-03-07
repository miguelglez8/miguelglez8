package com.adeplus.liferay.portlet.mailing.web.util;

import com.liferay.petra.content.ContentUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;


public class PrevingTemplateUtil {

    private static Log _log = LogFactoryUtil.getLog(PrevingTemplateUtil.class);

    public static String getTemplate(User user, String templateName){
    	
    	
    	_log.info("Plantilla: " + templateName );

        ClassLoader classLoader = PortalClassLoaderUtil.getClassLoader();

        String bodyTemplate = "";
        try {

            String userLocale = "es_ES";
            if("ca_ES".equals(user.getLocale())){
                userLocale = "ca_ES";
            }else if("en_US".equals(user.getLocale())){
                userLocale = "en_US";
            }

            bodyTemplate = ContentUtil.get(PrevingTemplateUtil.class.getClassLoader(), templateName + "_" + user.getLocale() +".tmpl");
            _log.info("Es catalan: "+"ca_ES".equals(user.getLocale()));
            _log.info("locale: "+user.getLocale());
        } catch (Exception e) {
            _log.error("Error obteniendo la plantilla: " + templateName+ "_" + user.getLocale() +".tmpl");
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

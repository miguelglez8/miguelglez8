package com.legalplus.liferay.portlet.admin.legislaciones.web.util;

import com.liferay.portal.kernel.language.LanguageUtil;

import java.util.Locale;
import java.util.ResourceBundle;

public class FamiliaNormasUtil {

    public static String getFamiliaNormasLanguageKey(String familias, Locale locale){

        ResourceBundle bundle = ResourceBundle.getBundle("content/Language", locale);

        String res = "";

        if(familias.contains("99")){
            res = "<p>" + LanguageUtil.get(bundle, "legislaciones.familia.todas") + "</p>";
        }else{
            if(familias.contains("0")){
                res += "<p>" + LanguageUtil.get(bundle, "legislaciones.familia.prl") + "</p>";
            }
            if(familias.contains("1")){
                res += "<p>" + LanguageUtil.get(bundle, "legislaciones.familia.medioambiente") + "</p>";
            }
            if(familias.contains("2")){
                res += "<p>" + LanguageUtil.get(bundle, "legislaciones.familia.seguridad.industrial") + "</p>";
            }
            if(familias.contains("3")){
                res += "<p>" + LanguageUtil.get(bundle, "legislaciones.familia.seguridad.alimentaria") + "</p>";
            }

        }

        return res;
    }

}

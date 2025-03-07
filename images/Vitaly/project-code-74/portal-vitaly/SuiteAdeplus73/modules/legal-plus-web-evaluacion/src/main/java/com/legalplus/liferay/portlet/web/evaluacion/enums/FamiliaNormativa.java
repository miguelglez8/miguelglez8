package com.legalplus.liferay.portlet.web.evaluacion.enums;

import com.legalplus.liferay.portlet.web.evaluacion.constants.WebEvaluacionPortletKeys;
import com.liferay.portal.kernel.language.LanguageUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public enum FamiliaNormativa {

    TODAS("99", "legislaciones.familia.todas"),
    PRL("0", "legislaciones.familia.prl"),
    MEDIOAMBIENTE("1", "legislaciones.familia.medioambiente"),
    SEGURIDAD_INDUSTRIAL("2", "legislaciones.familia.seguridad.industrial"),
    SEGURIDAD_ALIMENTARIA("3", "legislaciones.familia.seguridad.alimentaria");

    // ---------------------------------------------------------
    // PROPIEDADES
    // ---------------------------------------------------------
    private String codigo;
    private String descripcion;

    // ---------------------------------------------------------
    // CONTRUCTORES
    // ---------------------------------------------------------
    FamiliaNormativa(String codigo, String descripcion) {
        this.codigo = codigo;
        this.descripcion = descripcion;
    }

    // ---------------------------------------------------------
    // GETTERS Y SETTERS
    // ---------------------------------------------------------
    public String getCodigo() {
        return codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public static String getFamilias(String value, ResourceBundle bundle) {
        List<String> familias = new ArrayList<>();
        for (FamiliaNormativa familia : values()) {
            if (value.contains(familia.getCodigo())) {
                familias.add(LanguageUtil.get(bundle, familia.getDescripcion()));
            }
        }
        return String.join(WebEvaluacionPortletKeys.COMMA_DELIMITER, familias);
    }
}

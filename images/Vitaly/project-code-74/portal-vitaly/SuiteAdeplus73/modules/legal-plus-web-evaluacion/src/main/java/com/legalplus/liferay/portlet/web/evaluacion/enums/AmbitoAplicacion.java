package com.legalplus.liferay.portlet.web.evaluacion.enums;

import com.liferay.portal.kernel.language.LanguageUtil;

import java.util.Locale;
import java.util.ResourceBundle;

public enum AmbitoAplicacion {

    EUROPEA("0", "legislaciones.ambito.europa"),
    NACIONAL("1", "legislaciones.ambito.nacional"),
    CCAA("2", "legislaciones.ambito.ccaa"),
    AYUNTAMIENTO("3", "legislaciones.ambito.ayuntamientos");

    // ---------------------------------------------------------
    // PROPIEDADES
    // ---------------------------------------------------------
    private String codigo;
    private String descripcion;

    // ---------------------------------------------------------
    // CONTRUCTORES
    // ---------------------------------------------------------
    AmbitoAplicacion(String codigo, String descripcion) {
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

    public static String getAmbito(String value, ResourceBundle bundle) {
        for (AmbitoAplicacion ambito : values()) {
            if (ambito.getCodigo().equalsIgnoreCase(value)) {
                return LanguageUtil.get(bundle, ambito.getDescripcion());
            }
        }
        return null;
    }
}

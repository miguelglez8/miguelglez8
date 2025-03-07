package com.plan.igualdad.liferay.portlet.informacion.web.enums;

import com.liferay.portal.kernel.language.LanguageUtil;

import java.util.ResourceBundle;

public enum Ambito {

    NACIONAL("0", "organizacion.ambito.nacional"),
    CCAA("1", "organizacion.ambito.ccaa"),
    PROVINCIA("2", "organizacion.ambito.provincial");

    // ---------------------------------------------------------
    // PROPIEDADES
    // ---------------------------------------------------------
    private String codigo;
    private String descripcion;

    // ---------------------------------------------------------
    // CONTRUCTORES
    // ---------------------------------------------------------
    Ambito(String codigo, String descripcion) {
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
        for (Ambito ambito : values()) {
            if (ambito.getCodigo().equalsIgnoreCase(value)) {
                return LanguageUtil.get(bundle, ambito.getDescripcion());
            }
        }
        return null;
    }

}

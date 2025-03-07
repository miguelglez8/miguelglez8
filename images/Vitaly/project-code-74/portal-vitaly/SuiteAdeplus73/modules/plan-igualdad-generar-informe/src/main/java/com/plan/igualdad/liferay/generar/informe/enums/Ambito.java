package com.plan.igualdad.liferay.generar.informe.enums;

import com.liferay.portal.kernel.language.LanguageUtil;

import java.util.ResourceBundle;

public enum Ambito {

    NACIONAL("0", "informe.ambito.nacional"),
    CCAA("1", "informe.ambito.ccaa"),
    PROVINCIA("2", "informe.ambito.provincial");

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

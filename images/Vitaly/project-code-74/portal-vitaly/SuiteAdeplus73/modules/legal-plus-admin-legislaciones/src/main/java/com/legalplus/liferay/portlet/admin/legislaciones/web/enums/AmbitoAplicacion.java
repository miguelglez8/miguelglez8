package com.legalplus.liferay.portlet.admin.legislaciones.web.enums;

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

    public static AmbitoAplicacion getAmbito(String value) {
        for (AmbitoAplicacion ambito : values()) {
            if (ambito.getCodigo().equalsIgnoreCase(value)) {
                return ambito;
            }
        }
        return null;
    }
}

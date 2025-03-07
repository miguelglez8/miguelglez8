package com.legalplus.liferay.portlet.admin.legislaciones.web.enums;

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
}

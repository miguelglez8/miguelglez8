package com.legalplus.liferay.portlet.web.legislaciones.enums;

public enum AmbitoAplicacion {

    EUROPEA("0", "contract.view.europea"),
    NACIONAL("1", "contract.view.nacional"),
    CCAA("2", "contract.view.ccaa"),
    AYUNTAMIENTO("3", "contract.view.ayuntamientos");

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
}


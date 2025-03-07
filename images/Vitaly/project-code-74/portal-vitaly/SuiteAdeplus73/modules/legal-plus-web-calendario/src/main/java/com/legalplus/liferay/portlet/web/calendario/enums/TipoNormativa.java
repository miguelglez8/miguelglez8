package com.legalplus.liferay.portlet.web.calendario.enums;

public enum TipoNormativa {

    ACUERDO("1","legislaciones.tipo.acuerdo"),
    APLICACION("2","legislaciones.tipo.aplicacion"),
    BANDO("3","legislaciones.tipo.bando"),
    CORRECION_DE_ERRORES("4","legislaciones.tipo.correcion_de_errores"),
    DECISION("5","legislaciones.tipo.decision"),
    DECRETO("6","legislaciones.tipo.decreto"),
    DECRETO_FORAL("7","legislaciones.tipo.decreto_foral"),
    DECRETO_LEY("8","legislaciones.tipo.decreto-ley"),
    DECRETO_LEY_FORAL("9","legislaciones.tipo.decreto_ley_foral"),
    DICTAMEN("10","legislaciones.tipo.dictamen"),
    DIRECTIVA("11","legislaciones.tipo.directiva"),
    INSTRUCCION("12","legislaciones.tipo.instruccion"),
    INSTRUMENTO_DE_RATIFICACION("13","legislaciones.tipo.instrumento_de_ratificacion"),
    LEY("14","legislaciones.tipo.ley"),
    LEY_AUTONOMICA("15","legislaciones.tipo.ley_autonomica"),
    LEY_FORAL("16","legislaciones.tipo.ley_foral"),
    LEY_ORGANICA("17","legislaciones.tipo.ley_organica"),
    ORDEN("18","legislaciones.tipo.orden"),
    ORDEN_FORAL("19","legislaciones.tipo.orden_foral"),
    ORDENANZA("20","legislaciones.tipo.ordenanza"),
    REAL_DECRETO("21","legislaciones.tipo.real_decreto"),
    REAL_DECRETO_LEY("22","legislaciones.tipo.real_decreto-ley"),
    REGLAMENTO("23","legislaciones.tipo.reglamento"),
    RESOLUCION("24","legislaciones.tipo.resolucion"),
    PROPIA("25", "legislaciones.tipo.propia");

    // ---------------------------------------------------------
    // PROPIEDADES
    // ---------------------------------------------------------
    private String codigo;
    private String descripcion;

    // ---------------------------------------------------------
    // CONTRUCTORES
    // ---------------------------------------------------------
    TipoNormativa(String codigo, String descripcion) {
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


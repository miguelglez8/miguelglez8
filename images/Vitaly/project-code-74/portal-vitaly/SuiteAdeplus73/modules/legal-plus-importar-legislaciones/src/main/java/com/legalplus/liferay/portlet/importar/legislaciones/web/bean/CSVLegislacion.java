package com.legalplus.liferay.portlet.importar.legislaciones.web.bean;

public class CSVLegislacion {

    private String legislacionId;
    private String nombre;
    private String tipo;
    private String familia;
    private String ambito;
    private String ccaa;
    private String ayuntamiento;
    private String publicacion;
    private String derogacion;
    private String etiquetas;
    private String cnae;
    private String enlace;
    private String descripcion;

    public CSVLegislacion(String legislacionId, String nombre, String tipo, String familia, String ambito, String ccaa, String ayuntamiento, String publicacion, String derogacion, String etiquetas, String cnae, String enlace, String descripcion) {
        this.legislacionId = legislacionId;
        this.nombre = nombre;
        this.tipo = tipo;
        this.familia = familia;
        this.ambito = ambito;
        this.ccaa = ccaa;
        this.ayuntamiento = ayuntamiento;
        this.publicacion = publicacion;
        this.derogacion = derogacion;
        this.etiquetas = etiquetas;
        this.cnae = cnae;
        this.enlace = enlace;
        this.descripcion = descripcion;
    }

    public String getLegislacionId() {
        return legislacionId;
    }

    public void setLegislacionId(String legislacionId) {
        this.legislacionId = legislacionId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getFamilia() {
        return familia;
    }

    public void setFamilia(String familia) {
        this.familia = familia;
    }

    public String getAmbito() {
        return ambito;
    }

    public void setAmbito(String ambito) {
        this.ambito = ambito;
    }

    public String getCcaa() {
        return ccaa;
    }

    public void setCcaa(String ccaa) {
        this.ccaa = ccaa;
    }

    public String getAyuntamiento() {
        return ayuntamiento;
    }

    public void setAyuntamiento(String ayuntamiento) {
        this.ayuntamiento = ayuntamiento;
    }

    public String getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(String publicacion) {
        this.publicacion = publicacion;
    }

    public String getDerogacion() {
        return derogacion;
    }

    public void setDerogacion(String derogacion) {
        this.derogacion = derogacion;
    }

    public String getEtiquetas() {
        return etiquetas;
    }

    public void setEtiquetas(String etiquetas) {
        this.etiquetas = etiquetas;
    }

    public String getCnae() {
        return cnae;
    }

    public void setCnae(String cnae) {
        this.cnae = cnae;
    }

    public String getEnlace() {
        return enlace;
    }

    public void setEnlace(String enlace) {
        this.enlace = enlace;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}

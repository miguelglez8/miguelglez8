package com.legalplus.liferay.portlet.importar.legislaciones.web.bean;

public class CSVRequisito {

    private String legislacionId;
    private String requisitoId;
    private String descripcion;
    private String cnae;
    private String fechaBaja;

    public CSVRequisito(String legislacionId, String requisitoId, String descripcion, String fechaBaja, String cnae) {
        this.legislacionId = legislacionId;
        this.requisitoId = requisitoId;
        this.descripcion = descripcion;
        this.cnae = cnae;
        this.fechaBaja = fechaBaja;
    }

    public String getLegislacionId() {
        return legislacionId;
    }

    public void setLegislacionId(String legislacionId) {
        this.legislacionId = legislacionId;
    }

    public String getRequisitoId() {
        return requisitoId;
    }

    public void setRequisitoId(String requisitoId) {
        this.requisitoId = requisitoId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCnae() {
        return cnae;
    }

    public void setCnae(String cnae) {
        this.cnae = cnae;
    }

    public String getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(String fechaBaja) {
        this.fechaBaja = fechaBaja;
    }
}

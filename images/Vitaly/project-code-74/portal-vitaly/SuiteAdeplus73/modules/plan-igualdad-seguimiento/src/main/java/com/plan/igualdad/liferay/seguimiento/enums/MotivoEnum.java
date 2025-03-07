package com.plan.igualdad.liferay.seguimiento.enums;

public enum MotivoEnum {
	
	FALTA_RECURSOS_HUMANOS("1", "planigualdadseguimiento.form.reason.option-1"),
	FALTA_RECURSOS_MATERIALES("2", "planigualdadseguimiento.form.reason.option-2"),
	FALTA_TIEMPO("3", "planigualdadseguimiento.form.reason.option-3"),
	FALTA_PARTICIPACION("4", "planigualdadseguimiento.form.reason.option-4"),
	DESCOOR_OTROS_DEPARTAMENTOS("5", "planigualdadseguimiento.form.reason.option-5"),
	DESCON_DESARROLLO("6", "planigualdadseguimiento.form.reason.option-6"),
	OTROS_MOTIVOS("7", "planigualdadseguimiento.form.reason.option-7");
    
	// ---------------------------------------------------------
    // PROPIEDADES
    // ---------------------------------------------------------
    private String codigo;
    private String descripcion;
    
    // ---------------------------------------------------------
    // CONTRUCTORES
    // ---------------------------------------------------------
    
    MotivoEnum(String codigo, String descripcion){
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

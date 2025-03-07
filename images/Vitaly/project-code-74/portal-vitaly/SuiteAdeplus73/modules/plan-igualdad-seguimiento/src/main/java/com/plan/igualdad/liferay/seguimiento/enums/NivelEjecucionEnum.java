package com.plan.igualdad.liferay.seguimiento.enums;

import com.liferay.petra.string.StringPool;

public enum NivelEjecucionEnum {

	PENDIENTE(1, "planigualdadseguimiento.level-execution.pending", "planigualdadseguimiento.form.pending-planning"),
    EJECUCION(2, "planigualdadseguimiento.level-execution.execute", "planigualdadseguimiento.level-execution.execute"),
    FINALIZADA(3, "planigualdadseguimiento.level-execution.finished", "planigualdadseguimiento.form.finished");
	
	// ---------------------------------------------------------
    // PROPIEDADES
    // ---------------------------------------------------------
    private Integer codigo;
    private String descripcionFiltro;
    private String descripcionForm;
    
    // ---------------------------------------------------------
    // CONTRUCTORES
    // ---------------------------------------------------------
    NivelEjecucionEnum(Integer codigo, String descripcionFiltro, String descripcionForm){
    	this.codigo = codigo;
        this.descripcionFiltro = descripcionFiltro;
        this.descripcionForm = descripcionForm;
    }
    
    // ---------------------------------------------------------
    // GETTERS Y SETTERS
    // ---------------------------------------------------------
    public Integer getCodigo() {
		return codigo;
	}

	public String getDescripcionFiltro() {
		return descripcionFiltro;
	}

	public String getDescripcionForm() {
		return descripcionForm;
	}
	
	public static String getDescripcionFormByCodigo(Integer codigo) {
    	String descripcion = StringPool.BLANK;
    	for(NivelEjecucionEnum nivelEjecucionEnum : values()) {
    		if(nivelEjecucionEnum.getCodigo() == codigo) {
    			descripcion = nivelEjecucionEnum.getDescripcionForm();
    		}
    	}
    	return descripcion;
    }
}

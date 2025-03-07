package com.plan.igualdad.liferay.generar.informe.enums;

import com.liferay.petra.string.StringPool;

public enum PrioridadEnum {

	MEDIA(1, "planigualdadseguimiento.priority.mean"),
    ALTA(2, "planigualdadseguimiento.priority.high");
	
	// ---------------------------------------------------------
    // PROPIEDADES
    // ---------------------------------------------------------
    private Integer codigo;
    private String descripcion;
    
    // ---------------------------------------------------------
    // CONTRUCTORES
    // ---------------------------------------------------------
    
    PrioridadEnum(Integer codigo, String descripcion){
    	this.codigo = codigo;
        this.descripcion = descripcion;
    }
    
    // ---------------------------------------------------------
    // GETTERS Y SETTERS
    // ---------------------------------------------------------
    public Integer getCodigo() {
        return codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }
    
    public static String getDescripcionByCodigo(Integer codigo) {
    	String descripcion = StringPool.BLANK;
    	for(PrioridadEnum prioridadEnum : values()) {
    		if(prioridadEnum.getCodigo() == codigo) {
    			descripcion = prioridadEnum.getDescripcion();
    		}
    	}
    	return descripcion;
    }
}

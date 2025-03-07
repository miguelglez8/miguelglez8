package com.plan.igualdad.liferay.generar.informe.enums;

import com.liferay.petra.string.StringPool;

public enum MateriaEnum {
	
	SELECCION_CONTRATACION(1, "planigualdadseguimiento.category.selection"),
	AUDITORIA_RETRIBUTIVA(2, "planigualdadseguimiento.category.auditory"),
	FORMACION(3, "planigualdadseguimiento.category.training"),
	PROMOCION_PROFESIONAL(4, "planigualdadseguimiento.category.promotion"),
	CONDICIONES_TRABAJO(5, "planigualdadseguimiento.category.conditions"),
	EJERCICIO(6, "planigualdadseguimiento.category.exercise"),
	PREVENCION(7, "planigualdadseguimiento.category.prevention"),
	DERECHOS(8, "planigualdadseguimiento.category.rights"),
	LENGUAJE_COMUNICACION(9, "planigualdadseguimiento.category.language"),
	CLASIFICACION_PROFESIONAL(10, "planigualdadseguimiento.category.clasification"),
	INFRARREPRE_FEMENINA(11, "planigualdadseguimiento.category.female-underrepresentation");
	
	// ---------------------------------------------------------
    // PROPIEDADES
    // ---------------------------------------------------------
    private Integer codigo;
    private String descripcion;
    
    // ---------------------------------------------------------
    // CONTRUCTORES
    // ---------------------------------------------------------
    MateriaEnum(Integer codigo, String descripcion){
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
    	for(MateriaEnum categoriaEnum : values()) {
    		if(categoriaEnum.getCodigo() == codigo) {
    			descripcion = categoriaEnum.getDescripcion();
    		}
    	}
    	return descripcion;
    }
}

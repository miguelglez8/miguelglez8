package com.legalplus.liferay.portlet.web.calendario.enums;

public enum FamiliaNormativa {

    TODAS("99", "contract.view.familia.todas"),
    PRL("0", "contract.view.familia.prl"),
    MEDIOAMBIENTE("1", "contract.view.familia.medioambiente"),
    SEGURIDAD_INDUSTRIAL("2", "contract.view.familia.seguridad.industrial"),
    SEGURIDAD_ALIMENTARIA("3", "contract.view.familia.seguridad.alimentaria");

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

    public static FamiliaNormativa getFamilia(String value) {
        for (FamiliaNormativa familia : values()) {
            if (familia.getCodigo().equalsIgnoreCase(value)) {
                return familia;
            }
        }
        return null;
    }
}

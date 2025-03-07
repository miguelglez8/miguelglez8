package com.plan.igualdad.liferay.portlet.informacion.web.validators;

import com.liferay.portal.kernel.util.Validator;

public class OrganizacionValidator {

    public static final boolean validOrganizacion(Long compId, Long versionId, String[] cnaes, Long nCentros, String convenio, String ambito,
                                                   String representacionLegal, String departamentoPersonas, String sindicatos) {

        if (Validator.isNull(compId)) return false;
        if (Validator.isNull(versionId)) return false;
        if (Validator.isNull(cnaes)) return false;
        if (Validator.isNull(nCentros)) return false;
        if (Validator.isBlank(convenio)) return false;
        if (Validator.isBlank(ambito)) return false;
        if (Validator.isBlank(representacionLegal)) return false;
        if (Validator.isBlank(departamentoPersonas)) return false;
        if (Validator.isBlank(sindicatos)) return false;
        return true;

    }

}

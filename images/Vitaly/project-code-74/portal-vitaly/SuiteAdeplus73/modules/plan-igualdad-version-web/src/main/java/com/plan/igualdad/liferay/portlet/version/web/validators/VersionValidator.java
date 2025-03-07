package com.plan.igualdad.liferay.portlet.version.web.validators;

import com.liferay.portal.kernel.util.Validator;
import java.util.Date;

public class VersionValidator {

    public static final boolean validVersion(String nombre, Date inicioPeriodoDatos, Date finPeriodoDatos, Date inicioPeriodoIgualdad, Date finPeriodoIgualdad) {

        if (Validator.isNull(nombre)) return false;
        return true;

    }

}

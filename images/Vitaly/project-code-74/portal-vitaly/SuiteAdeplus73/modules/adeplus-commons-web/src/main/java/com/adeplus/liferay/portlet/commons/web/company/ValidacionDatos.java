package com.adeplus.liferay.portlet.commons.web.company;

import com.adeplus.liferay.portlet.commons.web.bean.CompanyApi;
import com.adeplus.liferay.portlet.commons.web.bean.UserApi;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.util.Validator;

public class ValidacionDatos {

    public static String validacionDatosApi(String dataUsuario, String dataEmpresa){
        String error = StringPool.BLANK;
        try {
            UserApi userApi = new UserApi(dataUsuario);
            CompanyApi companyApi = new CompanyApi(dataEmpresa);

            //Validacion datos usuarios
            if(Validator.isBlank(userApi.getEmail())){
                error="Error al crear Usuario con email vacio";
            }else if(Validator.isBlank(userApi.getUsername())){
                error="Error al crear Usuario con Username vacio";
            }

            if(Validator.isBlank(companyApi.getCif())){
                error="Error al crear empresa con Cif vacio";
            }else if(Validator.isBlank(companyApi.getNombreEmpresa())){
                error="Error al crear empresa con NombreEmpresa vacio";
            }


        }catch (Exception e){
            error="Json mal formado";
        }
        return error;
    }
}

package com.adeplus.liferay.portlet.commons.web.bean;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

public class CompanyApi {
    private String cif;

    private String nombreEmpresa;

    public CompanyApi(String dataApi) throws JSONException {
        JSONObject jsonObject =  JSONFactoryUtil.createJSONObject(dataApi);
        this.nombreEmpresa = jsonObject.getString("name");
        this.cif = jsonObject.getString("cif");

    }
    public String getCif(){
        return cif;
    }
    public String getNombreEmpresa(){
        return nombreEmpresa;
    }
}

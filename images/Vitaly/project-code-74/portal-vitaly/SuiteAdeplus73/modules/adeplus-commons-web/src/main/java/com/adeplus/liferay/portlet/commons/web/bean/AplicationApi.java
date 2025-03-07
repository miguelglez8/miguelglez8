package com.adeplus.liferay.portlet.commons.web.bean;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class AplicationApi {

    private long idCliente;
    private long idContrato;
    private String apps;
    private String nombre;
    private String typeEvent;

    public AplicationApi(String dataApi, int posicion) throws JSONException {
        JSONArray jsonArray = null;
        JSONObject jsonObject = null;
        jsonArray = JSONFactoryUtil.createJSONArray(dataApi);

        jsonObject = jsonArray.getJSONObject(posicion);
        this.idCliente = jsonObject.getLong("clientId");
        this.idContrato = jsonObject.getLong("contractId");
        this.typeEvent =jsonObject.getString("typeEvent");
        this.nombre=jsonObject.getString("nombre");
        this.apps = jsonObject.getString("applicationId").replaceAll("\"license\"","\"licenses\"").replaceAll("1001","1101");


    }

    public String getTypeEvent() {
        return typeEvent;
    }

    public String getApps() {
        return apps;
    }

    public String getRealApps(String applications){
        String realApp=applications;
        // Expresión regular para encontrar los números


        // Expresión regular para encontrar los números que siguen a "appId":
        String regex = "(?<=appId\":)(\\d+)";

        // Crear un patrón y un matcher para la cadena
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(realApp);

        // Usar un StringBuffer para construir la cadena resultante
        StringBuffer result = new StringBuffer();

        // Reemplazar todos los números encontrados con versiones entrecomilladas
        while (matcher.find()) {
            matcher.appendReplacement(result, "\"" + matcher.group(1) + "\"");
        }
        matcher.appendTail(result);




        return result.toString();
    }

    public String getNombre() {
        return nombre;
    }
    public long getIdCliente() {
        return idCliente;
    }

    public long getIdContrato() {
        return idContrato;
    }



}


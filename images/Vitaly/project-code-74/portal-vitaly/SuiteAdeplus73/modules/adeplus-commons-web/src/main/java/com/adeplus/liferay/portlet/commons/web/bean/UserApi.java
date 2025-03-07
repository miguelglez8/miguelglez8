package com.adeplus.liferay.portlet.commons.web.bean;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

public class UserApi {

    private String username;
    private String lastName;
    private String email;



    public UserApi(String dataApi) throws JSONException {

        JSONObject jsonObject =  JSONFactoryUtil.createJSONObject(dataApi);

        this.username=jsonObject.getString("username");
        this.lastName=jsonObject.getString("lastName");
        this.email=jsonObject.getString("email");


    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getLastName() {
        return lastName;
    }

}

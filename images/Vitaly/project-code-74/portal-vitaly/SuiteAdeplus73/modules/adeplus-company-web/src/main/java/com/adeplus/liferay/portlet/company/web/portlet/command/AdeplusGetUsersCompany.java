package com.adeplus.liferay.portlet.company.web.portlet.command;

import com.adeplus.liferay.portlet.company.web.constants.AdeplusCompaniesPortletKeys;
import com.adeplus.liferay.portlet.suite.manager.model.UserApplication;
import com.adeplus.liferay.portlet.suite.manager.model.UserCompany;
import com.adeplus.liferay.portlet.suite.manager.service.ApplicationLocalServiceUtil;
import com.adeplus.liferay.portlet.suite.manager.service.UserApplicationLocalServiceUtil;
import com.adeplus.liferay.portlet.suite.manager.service.UserCompanyLocalServiceUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Component(
        immediate=true,
        property= {
                "javax.portlet.name=" + AdeplusCompaniesPortletKeys.ADEPLUSCOMPANIES ,
                "mvc.command.name=getAllUserByCompany"
        },
        service = MVCResourceCommand.class
)

public class AdeplusGetUsersCompany extends BaseMVCResourceCommand {

    private static Log _log = LogFactoryUtil.getLog(AdeplusGetUsersCompany.class);

    private long companyEditId = 0L;

    @Override
    protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {



        ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
        HttpServletRequest httpReq = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(resourceRequest));

        companyEditId 	= ParamUtil.getLong(resourceRequest, AdeplusCompaniesPortletKeys.COMPANY_ID_EDIT, 0);
        List<UserCompany> users = UserCompanyLocalServiceUtil.getUsersFromCompany(companyEditId);




        List<UserApplication> apps = null;
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();
        JSONArray jsonArrayApps = null;
        JSONObject jsonObject = JSONFactoryUtil.createJSONObject(), jsonApp = null;
        JSONObject json = JSONFactoryUtil.createJSONObject();




        User us = null;
        for(UserCompany user : users){
            jsonObject = JSONFactoryUtil.createJSONObject();
            jsonObject.put("userId", user.getUserId());
            jsonObject.put("nombre", user.getName());
            jsonObject.put("apellidos", user.getLastName());
            jsonObject.put("nif", user.getNif());
            jsonObject.put("email", user.getEmail());
            us = UserLocalServiceUtil.getUser(user.getUserId());



            apps = UserApplicationLocalServiceUtil.getApplicationsFromUser(us.getUserId(),companyEditId);
            jsonArrayApps = JSONFactoryUtil.createJSONArray();

            for(UserApplication ua : apps){
                jsonApp = JSONFactoryUtil.createJSONObject();
                jsonApp.put("id",ua.getApplicationId());
                jsonApp.put("name", ApplicationLocalServiceUtil.getApplication(ua.getApplicationId()).getName());
                jsonArrayApps.put(jsonApp);
            }

            jsonObject.put("apps", jsonArrayApps);
            jsonObject.put("lastLogin",  (Validator.isNotNull(us.getLoginDate()))? us.getLoginDate().getTime() : "");
            jsonObject.put("fechaCreacion", user.getCreateDate().getTime());
            jsonObject.put("fechaBaja", (Validator.isNotNull(user.getUserEndDate()))? user.getUserEndDate().getTime() : "");
            jsonObject.put("activo", (Validator.isNotNull(user.getUserEndDate()))? false : true);
            jsonObject.put("isAdmin", user.isAdmin());

            jsonArray.put(jsonObject);
        }

        json.put("data",jsonArray);
        resourceResponse.setContentType("application/json");
        resourceResponse.setCharacterEncoding("UTF-8");
        resourceResponse.getWriter().write(json.toString());
    }
}

package com.adeplus.liferay.portlet.user.web.portlet.command;

import com.adeplus.liferay.portlet.commons.web.permission.AdeplusPermissionUserUtil;

import com.adeplus.liferay.portlet.suite.manager.model.CompClientApplication;
import com.adeplus.liferay.portlet.suite.manager.model.UserApplication;
import com.adeplus.liferay.portlet.suite.manager.model.UserCompany;
import com.adeplus.liferay.portlet.suite.manager.service.CompClientApplicationLocalServiceUtil;
import com.adeplus.liferay.portlet.suite.manager.service.UserCompanyLocalServiceUtil;
import com.adeplus.liferay.portlet.user.web.constants.AdeplusUsersPortletKeys;
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
                "javax.portlet.name=" + AdeplusUsersPortletKeys.ADEPLUSUSERS ,
                "mvc.command.name=getAllUserByCompanyMULTI"
        },
        service = MVCResourceCommand.class
)

public class AdeplusGetUsersCompanyMULTI extends BaseMVCResourceCommand {

    private static Log _log = LogFactoryUtil.getLog(AdeplusGetUsersCompanyMULTI.class);

    private long companyEditId = 0L;

    @Override
    protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {



        ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
        HttpServletRequest httpReq = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(resourceRequest));

        companyEditId 	= ParamUtil.getLong(resourceRequest, AdeplusUsersPortletKeys.USER_COMPANY_ID_EDIT, 0);
        List<UserCompany> users = UserCompanyLocalServiceUtil.getUsersFromCompany(companyEditId);

        //_log.info("companyEditId: " + companyEditId + " / users.size(): " + users.size());


        List<UserApplication> apps = null;
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();
        JSONObject jsonObject = JSONFactoryUtil.createJSONObject(), jsonApp = null;
        JSONObject json = JSONFactoryUtil.createJSONObject();




        User us = null;
        for(UserCompany user : users){
            _log.info(user.getUserId());
            jsonObject = JSONFactoryUtil.createJSONObject();
            jsonObject.put("userId", user.getUserId());
            jsonObject.put("nombre", user.getName());
            jsonObject.put("apellidos", user.getLastName());
            jsonObject.put("nif", user.getNif());
            jsonObject.put("email", user.getEmail());
            us = UserLocalServiceUtil.getUser(user.getUserId());
            String res = AdeplusPermissionUserUtil.getUserApplicationClientByCompId(us.getUserId(),companyEditId);
            res = res.replaceAll("]\\[",",");

            jsonObject.put("apps", res);

            if(Validator.isNotNull(us.getLastLoginDate())  && Validator.isNotNull(us.getLoginDate())){
                if(us.getLastLoginDate().getTime() > us.getLoginDate().getTime()){
                    jsonObject.put("lastLogin", us.getLastLoginDate().getTime());
                }else{
                    jsonObject.put("lastLogin", us.getLoginDate().getTime());
                }
            }else if(Validator.isNotNull(us.getLoginDate())){
                jsonObject.put("lastLogin", us.getLoginDate().getTime());

            }else{
                jsonObject.put("lastLogin", "");
            }

            //jsonObject.put("lastLogin",  (Validator.isNotNull(us.getLastLoginDate()))? us.getLastLoginDate().getTime() : "");
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

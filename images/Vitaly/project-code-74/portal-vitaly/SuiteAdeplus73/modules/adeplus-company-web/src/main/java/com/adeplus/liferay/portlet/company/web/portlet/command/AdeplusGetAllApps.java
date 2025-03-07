package com.adeplus.liferay.portlet.company.web.portlet.command;

import com.adeplus.liferay.portlet.company.web.constants.AdeplusCompaniesPortletKeys;
import com.adeplus.liferay.portlet.suite.manager.model.Application;
import com.adeplus.liferay.portlet.suite.manager.model.ApplicationLicense;
import com.adeplus.liferay.portlet.suite.manager.model.Comp;
import com.adeplus.liferay.portlet.suite.manager.model.Role;
import com.adeplus.liferay.portlet.suite.manager.service.ApplicationLicenseLocalServiceUtil;
import com.adeplus.liferay.portlet.suite.manager.service.ApplicationLocalServiceUtil;
import com.adeplus.liferay.portlet.suite.manager.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
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
                "javax.portlet.name=" + AdeplusCompaniesPortletKeys.ADEPLUSCOMPANIESMULTI ,
                "mvc.command.name=getAllApplications"
        },
        service = MVCResourceCommand.class
)

public class AdeplusGetAllApps extends BaseMVCResourceCommand {

    private static Log _log = LogFactoryUtil.getLog(AdeplusGetAllApps.class);

    @Override
    protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
        ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
        HttpServletRequest httpReq = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(resourceRequest));

        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();
        JSONArray jsonArrayLicenses = null, jsonArrayRoles = null;
        JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
        JSONObject json = null, jsonLicense = null, jsonRole = null;

        List<Application> appList = ApplicationLocalServiceUtil.getApplications(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
        List<ApplicationLicense> licensebyApplicationId =  null;
        List<Role> roles = null;

        for(Application app :  appList){
            json = JSONFactoryUtil.createJSONObject();
            json.put("appId", app.getApplicationId() );
            json.put("name", app.getName());
            json.put("limitAdmins", app.getLimitAdmins());
            json.put("limitNumAdmins", app.getLimitNumAdmins());
            json.put("limitUsers", app.getLimitUsers());
            json.put("limitNumUsers", app.getLimitNumUsers());

            licensebyApplicationId = ApplicationLicenseLocalServiceUtil.findByApplicationId(app.getApplicationId());
            if(licensebyApplicationId.size() > 0){
                jsonArrayLicenses = JSONFactoryUtil.createJSONArray();
                for(ApplicationLicense license : licensebyApplicationId){
                    jsonLicense = JSONFactoryUtil.createJSONObject();
                    jsonLicense.put("licenseId",license.getLicenseId());
                    jsonLicense.put("name",license.getName());
                    jsonArrayLicenses.put(jsonLicense);
                }
                json.put("licenses",jsonArrayLicenses);
            }

            roles = RoleLocalServiceUtil.getRoleByApplicationId(app.getApplicationId());
            if(roles.size() > 0){
                jsonArrayRoles = JSONFactoryUtil.createJSONArray();
                for(Role rol : roles){
                    String description="";
                    jsonRole = JSONFactoryUtil.createJSONObject();
                    jsonRole.put("roleId",rol.getRoleId());
                    jsonRole.put("name",rol.getName());
                    //Debemos llamar al rol de liferay con el paquete completo debido a que las clases se llaman igual
                    com.liferay.portal.kernel.model.Role rolLife=com.liferay.portal.kernel.service.RoleLocalServiceUtil.fetchRole(PortalUtil.getDefaultCompanyId(),rol.getAlias());
                    if(Validator.isNotNull(rolLife)){
                        description= rolLife.getDescription(PortalUtil.getLocale(resourceRequest));
                    }

                    jsonRole.put("description",description);
                    jsonArrayRoles.put(jsonRole);
                }
                json.put("roles",jsonArrayRoles);
            }


            jsonArray.put(json);
        }

        jsonObject.put("data", jsonArray);
        resourceResponse.setContentType("application/json");
        resourceResponse.setCharacterEncoding("UTF-8");
        resourceResponse.getWriter().write(jsonObject.toString());
        appList = null;
        jsonArray = null;






    }
}

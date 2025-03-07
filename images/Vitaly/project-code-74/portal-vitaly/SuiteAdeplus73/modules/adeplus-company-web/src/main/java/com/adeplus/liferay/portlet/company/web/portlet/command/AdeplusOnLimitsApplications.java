package com.adeplus.liferay.portlet.company.web.portlet.command;

import com.adeplus.liferay.portlet.suite.manager.model.Application;
import com.adeplus.liferay.portlet.suite.manager.service.ApplicationLocalServiceUtil;
import com.adeplus.liferay.portlet.suite.manager.service.UserApplicationLocalServiceUtil;
import com.adeplus.liferay.portlet.suite.manager.service.UserCompanyLocalServiceUtil;
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
import com.liferay.portal.kernel.util.WebKeys;
import org.osgi.service.component.annotations.Component;
import com.adeplus.liferay.portlet.company.web.constants.AdeplusCompaniesPortletKeys;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;

import java.util.List;



@Component(
        immediate=true,
        property= {
                "javax.portlet.name=" + AdeplusCompaniesPortletKeys.ADEPLUSCOMPANIES,
                "mvc.command.name=onLimitApplications13"
        },
        service = MVCResourceCommand.class
)

public class AdeplusOnLimitsApplications extends BaseMVCResourceCommand {

    private static Log _log = LogFactoryUtil.getLog(AdeplusOnLimitsApplications.class);

    public static final String ID_APP         = "id_app"; //array de IDs de las apps Ej.: ?id_app=122345,55561&id_comp=123
    public static final String ID_COMP         = "id_comp";

    @Override
    protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {



        ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
        HttpServletRequest httpReq = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(resourceRequest));

        JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
        JSONObject jsonApp = null;
        JSONArray jsonItems = JSONFactoryUtil.createJSONArray();

        if(!httpReq.getParameterMap().containsKey(ID_COMP)){
            jsonObject.put("msg","Faltan parametros necesarios para realizar la consulta");
            resourceResponse.setContentType("application/json");
            resourceResponse.setCharacterEncoding("UTF-8");
            resourceResponse.getWriter().write(jsonObject.toString());
            return;
        }

        long idComp = Long.parseLong(httpReq.getParameter(ID_COMP));

        List<Application> apps = ApplicationLocalServiceUtil.getApplications(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
        //{adminNumNow , userNumNow,  apps: [{id_app , adminLimit, adminNumLimit , userLimit, , userNumLimit }]}
        jsonObject.put("adminNumNow", UserCompanyLocalServiceUtil.getCountAdminCompany(idComp));
        jsonObject.put("userNumNow", UserCompanyLocalServiceUtil.getCountUserCompany(idComp));
        for(Application app : apps){
            jsonApp = JSONFactoryUtil.createJSONObject();
            jsonApp.put("id_app", app.getApplicationId());
            jsonApp.put("name", app.getName());
            jsonApp.put("adminLimit", app.getLimitAdmins());
            jsonApp.put("adminNumLimit", app.getLimitNumAdmins());
            jsonApp.put("userLimit", app.getLimitUsers());
            jsonApp.put("userNumLimit", app.getLimitNumUsers());

            jsonItems.put(jsonApp);

        }

        jsonObject.put("apps", jsonItems);


        resourceResponse.setContentType("application/json");
        resourceResponse.setCharacterEncoding("UTF-8");
        resourceResponse.getWriter().write(jsonObject.toString());
    }

    private JSONObject setInfoAppIncorrect(Application app){
        JSONObject jsonInfo = JSONFactoryUtil.createJSONObject();
        jsonInfo.put("correct",false);
        jsonInfo.put("idApp",app.getApplicationId());
        jsonInfo.put("appName",app.getName());
        jsonInfo.put("max-adms", app.getLimitNumAdmins());
        return jsonInfo;
    }
}

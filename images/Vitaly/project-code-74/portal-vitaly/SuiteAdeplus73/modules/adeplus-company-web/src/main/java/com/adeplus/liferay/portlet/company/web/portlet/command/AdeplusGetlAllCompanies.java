package com.adeplus.liferay.portlet.company.web.portlet.command;

import com.adeplus.liferay.portlet.suite.manager.model.Comp;
import com.adeplus.liferay.portlet.suite.manager.service.CompClientApplicationLocalServiceUtil;
import com.adeplus.liferay.portlet.suite.manager.service.CompLocalServiceUtil;
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
import com.liferay.portal.kernel.language.LanguageUtil;

import java.util.ResourceBundle;
import java.text.SimpleDateFormat;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;

import com.adeplus.liferay.portlet.company.web.constants.AdeplusCompaniesPortletKeys;
import com.adeplus.liferay.portlet.commons.web.permission.AdeplusPermissionCompanyUtil;
import com.adeplus.liferay.portlet.suite.manager.service.UserCompanyLocalServiceUtil;


import java.util.Calendar;
import java.util.List;

@Component(
        immediate=true,
        property= {
                "javax.portlet.name=" + AdeplusCompaniesPortletKeys.ADEPLUSCOMPANIES ,
                "mvc.command.name=getAllCompanies"
        },
        service = MVCResourceCommand.class
)
public class AdeplusGetlAllCompanies extends BaseMVCResourceCommand {

    private static Log _log = LogFactoryUtil.getLog(AdeplusGetlAllCompanies.class);

    @Override
    protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {


        ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
        HttpServletRequest httpReq = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(resourceRequest));

        List<Comp> comps = CompLocalServiceUtil.getAllComps();
        JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();
        JSONObject json = null;
        Calendar hoyMasMes = Calendar.getInstance();
        Calendar fechaBaja = Calendar.getInstance();
        hoyMasMes.add(Calendar.DATE, +30);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        ResourceBundle bundle = ResourceBundle.getBundle("content/Language", themeDisplay.getUser().getLocale());
        String yes = LanguageUtil.get(bundle, "user.view.yes");
        String no  = LanguageUtil.get(bundle, "user.view.no");

        boolean isActive = true;
        for( Comp comp :  comps){
            //es modelo mono
            if(Validator.isNull(CompClientApplicationLocalServiceUtil.getClientsByCompanyId(comp.getCompId()))) {
                json = JSONFactoryUtil.createJSONObject();
                json.put("compId", comp.getCompId());
                json.put("nombre", comp.getName());
                json.put("cif", comp.getCif());
                json.put("aplicaciones", AdeplusPermissionCompanyUtil.getCompanyApplicationNames(comp.getCompId()));
                isActive = true;
                if (comp.getDeleteDate() != null) {
                    fechaBaja.setTime(comp.getDeleteDate());
                    if (hoyMasMes.after(fechaBaja)) isActive = false;
                }

                if (!isActive) {
                    json.put("activa", no);
                    json.put("fechaBaja", dateFormat.format(comp.getDeleteDate()));
                    json.put("fechaBajaTime", comp.getDeleteDate().getTime());
                } else {
                    json.put("activa", yes);
                    json.put("fechaBaja", " ");
                    json.put("fechaBajaTime", " ");
                }


                json.put("empleados", UserCompanyLocalServiceUtil.getUsersCountFromCompany(comp.getCompId()));
                json.put("fechaAlta", dateFormat.format(comp.getCreateDate()));
                json.put("fechaAltaTime", comp.getCreateDate().getTime());

                jsonArray.put(json);
            }
        }

        jsonObject.put("data", jsonArray);

        resourceResponse.setContentType("application/json");
        resourceResponse.setCharacterEncoding("UTF-8");
        resourceResponse.getWriter().write(jsonObject.toString());

        comps = null;
        jsonArray = null;


    }

}

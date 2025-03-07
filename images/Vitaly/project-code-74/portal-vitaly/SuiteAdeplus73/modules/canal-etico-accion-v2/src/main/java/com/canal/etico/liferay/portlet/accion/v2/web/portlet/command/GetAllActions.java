package com.canal.etico.liferay.portlet.accion.v2.web.portlet.command;

import com.adeplus.liferay.portlet.suite.manager.model.UserCompany;
import com.adeplus.liferay.portlet.suite.manager.service.UserCompanyLocalServiceUtil;
import com.canal.etico.liferay.portlet.accion.v2.web.constants.CanalEticoAccionV2PortletKeys;
import com.canal.etico.liferay.portlet.commons.web.user.CanalEticoUserUtil;
import com.canal.etico.v2.liferay.portlet.canal.manager.model.Accion;
import com.canal.etico.v2.liferay.portlet.canal.manager.model.Categoria;
import com.canal.etico.v2.liferay.portlet.canal.manager.service.AccionLocalService;
import com.canal.etico.v2.liferay.portlet.canal.manager.service.AccionLocalServiceUtil;
import com.canal.etico.v2.liferay.portlet.canal.manager.service.CategoriaLocalServiceUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
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
import java.text.SimpleDateFormat;
import java.util.List;

@Component(
        immediate=true,
        property= {
                "javax.portlet.name=" + CanalEticoAccionV2PortletKeys.CANALETICOACCIONV2 ,
                "mvc.command.name=getAllActions"
        },
        service = MVCResourceCommand.class
)

public class GetAllActions extends BaseMVCResourceCommand {
    @Override
    protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
        JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
        ThemeDisplay themeDisplay= (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
        UserCompany userCompany= CanalEticoUserUtil.getUserDefaultCompany(themeDisplay.getUserId());
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        long idLanguage=CanalEticoAccionV2PortletKeys.ID_ADEPLUS;
        String locale=themeDisplay.getUser().getLocale().toString();
        if(locale.equalsIgnoreCase("ca_ES")){
            idLanguage=CanalEticoAccionV2PortletKeys.ID_ADEPLUS_CAT;
        }else if(locale.equalsIgnoreCase("en_US")){
            idLanguage=CanalEticoAccionV2PortletKeys.ID_ADEPLUS_ENG;
        }

        if(Validator.isNull(userCompany)){
            jsonObject = JSONFactoryUtil.createJSONObject();
            jsonObject.put("status","fail");
            jsonObject.put("where","GetAllActions()");
            jsonObject.put("msg","fail: userCompany not found");
            jsonObject.put("idUser", themeDisplay.getUserId());
            resourceResponse.setContentType("application/json");
            resourceResponse.setCharacterEncoding("UTF-8");
            resourceResponse.getWriter().write(jsonObject.toString());
            return;
        }
        long idCompany=userCompany.getCompId();
        List<Accion> accionList = AccionLocalServiceUtil.getAllAccionesByComp(idCompany);

        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();
        JSONObject json = null;
        for (Accion accion : accionList){
            json = JSONFactoryUtil.createJSONObject();
            json.put("id", accion.getPrimaryKey());
            json.put("status",accion.getEstado());
            json.put("compId", accion.getCompId());

            if(accion.getNombre_cat().isEmpty() || accion.getNombre_eng().isEmpty()){
                json.put("name", accion.getNombre());
            }else if(idLanguage == 1){
                json.put("name", accion.getNombre());
            }else if(idLanguage == 2 && !accion.getNombre_cat().isEmpty()){
                json.put("name", accion.getNombre_cat());
            }else if(idLanguage == 3 && !accion.getNombre_eng().isEmpty()){
                json.put("name", accion.getNombre_eng());
            }
            json.put("createBy", accion.getCreateBy());
            json.put("fechaAlta", accion.getCreateDate().getTime());
            json.put("fechaBaja", (Validator.isNotNull(accion.getEndDate())? accion.getEndDate().getTime() : ""));
            jsonArray.put(json);
        }

        jsonObject.put("data", jsonArray);

        resourceResponse.setContentType("application/json");
        resourceResponse.setCharacterEncoding("UTF-8");
        resourceResponse.getWriter().write(jsonObject.toString());

    }
}

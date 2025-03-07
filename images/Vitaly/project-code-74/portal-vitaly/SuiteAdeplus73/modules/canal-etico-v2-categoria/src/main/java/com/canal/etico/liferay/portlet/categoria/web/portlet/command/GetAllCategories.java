package com.canal.etico.liferay.portlet.categoria.web.portlet.command;


import com.adeplus.liferay.portlet.suite.manager.model.UserCompany;
import com.adeplus.liferay.portlet.suite.manager.service.UserCompanyLocalServiceUtil;
import com.canal.etico.liferay.portlet.categoria.web.constants.CategoriasPortletKeys;
import com.canal.etico.v2.liferay.portlet.canal.manager.model.Categoria;
import com.canal.etico.v2.liferay.portlet.canal.manager.service.CategoriaLocalServiceUtil;
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
                "javax.portlet.name=" + CategoriasPortletKeys.CATEGORIAS ,
                "mvc.command.name=getAllCategories"
        },
        service = MVCResourceCommand.class
)


public class GetAllCategories extends BaseMVCResourceCommand {


        @Override
        protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
                JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
                ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);

                HttpServletRequest httpReq = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(resourceRequest));

                //UserCompany userCompany = UserCompanyLocalServiceUtil.getUserDefaultCompany(themeDisplay.getUserId());
                long companyId = Long.valueOf(ParamUtil.getString(resourceRequest,"companyId","0"));
                long idLanguage=CategoriasPortletKeys.ID_ADEPLUS;

                String locale=themeDisplay.getUser().getLocale().toString();
                _log.info("locale "+locale);
                if(locale.equalsIgnoreCase("ca_ES")){
                        idLanguage=CategoriasPortletKeys.ID_ADEPLUS_CAT;
                }else if(locale.equalsIgnoreCase("en_US")){
                        idLanguage=CategoriasPortletKeys.ID_ADEPLUS_ENG;
                }
                /*if(Validator.isNull(userCompany)){
                        jsonObject = JSONFactoryUtil.createJSONObject();
                        jsonObject.put("status","fail");
                        jsonObject.put("where","GetAllCategories()");
                        jsonObject.put("msg","fail: userCompany not found");
                        jsonObject.put("idUser", themeDisplay.getUserId());
                        resourceResponse.setContentType("application/json");
                        resourceResponse.setCharacterEncoding("UTF-8");
                        resourceResponse.getWriter().write(jsonObject.toString());
                        return;
                }*/

                List<Categoria> categoriaList = CategoriaLocalServiceUtil.getAllCategoriesByComp(companyId);
                //List<Categoria> categoriaList = CategoriaLocalServiceUtil.getAllCategoriesByComp(userCompany.getCompId());

                JSONArray jsonArray = JSONFactoryUtil.createJSONArray();
                JSONObject json = null;
                for (Categoria cat : categoriaList){
                        json = JSONFactoryUtil.createJSONObject();
                        json.put("id", cat.getPrimaryKey());
                        json.put("compId", cat.getCompId());

                        if(cat.getNombre_cat().isEmpty() || cat.getNombre_eng().isEmpty()){
                                json.put("name", cat.getNombre());
                        }else if(idLanguage == 1){
                                json.put("name", cat.getNombre());
                        }else if(idLanguage == 2 && !cat.getNombre_cat().isEmpty()){
                                json.put("name", cat.getNombre_cat());
                        }else if(idLanguage == 3 && !cat.getNombre_eng().isEmpty()){
                                json.put("name", cat.getNombre_eng());
                        }

                        json.put("createBy", cat.getCreateBy());
                        json.put("fechaAlta", cat.getCreateDate().getTime());
                        json.put("fechaBaja", (Validator.isNotNull(cat.getDeleteData())? cat.getDeleteData().getTime() : ""));
                        jsonArray.put(json);
                }

                jsonObject.put("data", jsonArray);

                resourceResponse.setContentType("application/json");
                resourceResponse.setCharacterEncoding("UTF-8");
                resourceResponse.getWriter().write(jsonObject.toString());

        }

        private static Log _log = LogFactoryUtil.getLog(GetAllCategories.class);
}

package com.canal.etico.liferay.portlet.categoria.web.portlet.command;


import com.adeplus.liferay.portlet.suite.manager.model.UserCompany;
import com.adeplus.liferay.portlet.suite.manager.service.UserCompanyLocalServiceUtil;
import com.canal.etico.liferay.portlet.categoria.web.constants.CategoriasPortletKeys;
import com.canal.etico.liferay.portlet.commons.web.user.CanalEticoUserUtil;
import com.canal.etico.v2.liferay.portlet.canal.manager.model.Categoria;
import com.canal.etico.v2.liferay.portlet.canal.manager.service.CategoriaLocalServiceUtil;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Date;


@Component(
        immediate=true,
        property= {
                "javax.portlet.name=" + CategoriasPortletKeys.CATEGORIAS ,
                "mvc.command.name=customCategory"
        },
        service = MVCResourceCommand.class
)
public class CustomCategory extends BaseMVCResourceCommand {


    @Override
    protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
        ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);

        UserCompany userCompany = CanalEticoUserUtil.getUserDefaultCompany(themeDisplay.getUserId());
        long compId = userCompany.getCompId();

        HttpServletRequest httpReq = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(resourceRequest));

        long idCat = ParamUtil.getLong(httpReq, CategoriasPortletKeys.PARAM_ID_CAT, 0);
        String nameCat = ParamUtil.getString(httpReq, CategoriasPortletKeys.PARAM_NAME_CAT, "");
        String deleteDate = ParamUtil.getString(httpReq, CategoriasPortletKeys.PARAM_DELETEDATE_CAT, "");

        _log.info("compId: " + compId + " / idCat: " + idCat + " / nameCat:" + nameCat + " / deleteDate: " +deleteDate);

        if(compId == 0 || nameCat.isEmpty()) return;
        JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
        JSONObject json = JSONFactoryUtil.createJSONObject();

        try {
            Categoria cat = null;
            if(idCat != 0){
                cat = CategoriaLocalServiceUtil.fetchCategoria(idCat);
                //Solo puede las suyas
                if(cat.getCompId() != compId) return;
                cat.setModifiedDate(new Date());
            }else{
                cat = CategoriaLocalServiceUtil.createCategoria(CounterLocalServiceUtil.increment(Categoria.class.getName()));
                //cat.setCreateBy(themeDisplay.getUser().getFullName());
                cat.setCreateBy(themeDisplay.getUser().getEmailAddress());
                cat.setCreateDate(new Date());
            }


            cat.setCompId(compId);
            cat.setNombre(nameCat);

            if(!deleteDate.isEmpty()){
                String[] arr = deleteDate.split("/");
                Calendar cal = Calendar.getInstance();
                Calendar now = Calendar.getInstance();
                now.set(Calendar.HOUR_OF_DAY, 0);
                now.set(Calendar.MINUTE, 0);
                now.set(Calendar.SECOND, 0);
                now.set(Calendar.MILLISECOND, 0);
                cal.set(Integer.parseInt(arr[2]), Integer.parseInt(arr[1])-1,Integer.parseInt(arr[0]));
                if(cal.after(now)){
                    cat.setDeleteData(cal.getTime());
                }
            }else{
                cat.setDeleteData(null);
            }

            CategoriaLocalServiceUtil.updateCategoria(cat);
            json.put("msg", "sucess");

        }catch (Exception e){
            json.put("msg", "error");
            json.put("error", e.toString());
        }

        jsonObject.put("data", json);

        resourceResponse.setContentType("application/json");
        resourceResponse.setCharacterEncoding("UTF-8");
        resourceResponse.getWriter().write(jsonObject.toString());
    }
    private static Log _log = LogFactoryUtil.getLog(CustomCategory.class);
}

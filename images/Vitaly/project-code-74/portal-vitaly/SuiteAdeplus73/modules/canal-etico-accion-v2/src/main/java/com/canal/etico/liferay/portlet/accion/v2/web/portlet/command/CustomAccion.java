package com.canal.etico.liferay.portlet.accion.v2.web.portlet.command;


import com.adeplus.liferay.portlet.suite.manager.model.UserCompany;
import com.adeplus.liferay.portlet.suite.manager.service.UserCompanyLocalServiceUtil;
import com.canal.etico.liferay.portlet.accion.v2.web.constants.CanalEticoAccionV2PortletKeys;
import com.canal.etico.liferay.portlet.commons.web.user.CanalEticoUserUtil;
import com.canal.etico.v2.liferay.portlet.canal.manager.model.Accion;
import com.canal.etico.v2.liferay.portlet.canal.manager.service.AccionLocalServiceUtil;
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
                "javax.portlet.name=" + CanalEticoAccionV2PortletKeys.CANALETICOACCIONV2 ,
                "mvc.command.name=customAccion"
        },
        service = MVCResourceCommand.class
)
public class CustomAccion extends BaseMVCResourceCommand {


    @Override
    protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
        ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);

        UserCompany userCompany = CanalEticoUserUtil.getUserDefaultCompany(themeDisplay.getUserId());
        long compId = userCompany.getCompId();

        HttpServletRequest httpReq = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(resourceRequest));

        long idAcc = ParamUtil.getLong(httpReq, CanalEticoAccionV2PortletKeys.PARAM_ID_ACC, 0);
        String nameAcc = ParamUtil.getString(httpReq, CanalEticoAccionV2PortletKeys.PARAM_NAME_ACC, "");
        long estadoAcc =  ParamUtil.getLong(httpReq, CanalEticoAccionV2PortletKeys.PARAM_ID_ESTADO_ACC, 0);
        String deleteDate = ParamUtil.getString(httpReq, CanalEticoAccionV2PortletKeys.PARAM_DELETEDATE_ACC, "");
        _log.info("estado: "+estadoAcc);
        _log.info("compId: " + compId + " / idCat: " + idAcc + " / nameCat:" + nameAcc + " / deleteDate: " +deleteDate);

        if(compId == 0 || nameAcc.isEmpty()) return;
        JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
        JSONObject json = JSONFactoryUtil.createJSONObject();
        try {
            Accion acc = null;
            if(idAcc != 0){
                acc = AccionLocalServiceUtil.fetchAccion(idAcc);
                //Solo puede las suyas
                if(acc.getCompId() != compId) return;
                acc.setModifiedDate(new Date());
            }else{
                acc = AccionLocalServiceUtil.createAccion(CounterLocalServiceUtil.increment(Accion.class.getName()));
                acc.setCreateBy(themeDisplay.getUser().getEmailAddress());
                acc.setCreateDate(new Date());
            }
            acc.setEstado(estadoAcc);
            acc.setCompId(compId);
            acc.setNombre(nameAcc);

            if(!deleteDate.isEmpty() && !Validator.isBlank(deleteDate)){
                String[] arr = deleteDate.split("/");
                Calendar cal = Calendar.getInstance();
                Calendar now = Calendar.getInstance();
                now.set(Calendar.HOUR_OF_DAY, 0);
                now.set(Calendar.MINUTE, 0);
                now.set(Calendar.SECOND, 0);
                now.set(Calendar.MILLISECOND, 0);
                cal.set(Integer.parseInt(arr[2]), Integer.parseInt(arr[1])-1,Integer.parseInt(arr[0]));
                if(cal.after(now)){
                    acc.setEndDate(cal.getTime());
                }
            }else{
                acc.setEndDate(null);
            }

            AccionLocalServiceUtil.updateAccion(acc);
            json.put("msg", "sucess");

        }catch (Exception e){
            json.put("msg", "error");
            json.put("error", e.toString());
            _log.info("error"+e.getMessage());
        }

        jsonObject.put("data", json);

        resourceResponse.setContentType("application/json");
        resourceResponse.setCharacterEncoding("UTF-8");
        resourceResponse.getWriter().write(jsonObject.toString());
    }
    private static Log _log = LogFactoryUtil.getLog(CustomAccion.class);
}

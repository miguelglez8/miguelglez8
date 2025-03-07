package com.canal.etico.liferay.portlet.comunicado.portlet.commands;

import com.canal.etico.liferay.portlet.comunicado.constants.CanalEticoV2ComunicadosPortletKeys;
import com.canal.etico.v2.liferay.portlet.canal.manager.model.Comunicado;
import com.canal.etico.v2.liferay.portlet.canal.manager.service.ComunicadoLocalServiceUtil;
import com.liferay.portal.kernel.json.JSONArray;
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
import java.util.List;

@Component(
        immediate=true,
        property= {
                "javax.portlet.name=" + CanalEticoV2ComunicadosPortletKeys.CANALETICOV2COMUNICADOS ,
                "mvc.command.name=getComunicadoByCodigo"
        },
        service = MVCResourceCommand.class
)
public class GetComunicadoByCodigo extends BaseMVCResourceCommand {
    @Override
    protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
        HttpServletRequest httpReq = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(resourceRequest));
        ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
        JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
        String codigo = ParamUtil.getString(httpReq, "comunicadoCode", "");
        long idComp = ParamUtil.getLong(httpReq, "idComp", 0);
        _log.info("############### codigo: " + codigo);

        List<Comunicado>listaCom=ComunicadoLocalServiceUtil.getAllComunicadosByCodigo(codigo);
        if(listaCom.size()==0){
            resourceResponse.setProperty(ResourceResponse.HTTP_STATUS_CODE, "404");
            return;
        }

        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();
        JSONObject json = null;
        Comunicado com=listaCom.get(0);
        if(com.getCompId()==idComp && com.getCodigo().equals(codigo)){
            json = JSONFactoryUtil.createJSONObject();
            json.put("id", com.getComunicadoId());
            json.put("estado", com.getEstado());
            jsonArray.put(json);
            jsonObject.put("data", jsonArray);
            resourceResponse.setContentType("application/json");
            resourceResponse.setCharacterEncoding("UTF-8");
            resourceResponse.getWriter().write(jsonObject.toString());
        }else{
            resourceResponse.setProperty(ResourceResponse.HTTP_STATUS_CODE, "404");
        }

    }
    private static Log _log = LogFactoryUtil.getLog(GetComunicadoByCodigo.class);
}

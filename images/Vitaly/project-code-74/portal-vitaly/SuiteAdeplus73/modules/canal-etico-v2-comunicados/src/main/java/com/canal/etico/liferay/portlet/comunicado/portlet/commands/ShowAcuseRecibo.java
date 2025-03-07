package com.canal.etico.liferay.portlet.comunicado.portlet.commands;

import com.canal.etico.liferay.portlet.comunicado.constants.CanalEticoV2ComunicadosPortletKeys;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.util.PortalUtil;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;

@Component(
        immediate=true,
        property= {
                "javax.portlet.name=" + CanalEticoV2ComunicadosPortletKeys.CANALETICOV2COMUNICADOS ,
                "mvc.command.name=getEmailAcuseRecibo"
        },
        service = MVCResourceCommand.class
)
public class ShowAcuseRecibo extends BaseMVCResourceCommand {
    @Override
    protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
        //ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
        HttpServletRequest httpReq = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(resourceRequest));




    }

    private JSONObject getJSONComunicadoFromRequest (HttpServletRequest httpReq){

        JSONObject json = JSONFactoryUtil.createJSONObject();



        return json;
    }
}

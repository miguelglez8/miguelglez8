package com.canal.etico.liferay.portlet.comunicado.portlet.commands;

import com.canal.etico.liferay.portlet.comunicado.constants.CanalEticoV2ComunicadosPortletKeys;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import org.osgi.service.component.annotations.Component;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;


@Component(
        immediate = true,
        property = {
                "javax.portlet.name="+ CanalEticoV2ComunicadosPortletKeys.CANALETICOV2COMUNICADOS,
                "mvc.command.name=sucessComunicado"
        }
)

public class SucessComunicado  implements MVCRenderCommand {

    @Override
    public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {

        return "/sucess.jsp";
    }
}

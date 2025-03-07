package com.adeplus.liferay.portlet.menu.web.portlet.command;

import com.adeplus.liferay.portlet.menu.web.constants.MenuPortletKeys;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.plan.igualdad.liferay.portlet.commons.web.estado.EstadoUtils;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;

@Component(
        immediate=true,
        property= {
                "javax.portlet.name=" + MenuPortletKeys.MENU,
                "mvc.command.name=saveEstado"
        },
        service = MVCResourceCommand.class
)
public class SaveEstadoResourceCommand extends BaseMVCResourceCommand{

    @Override
    protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
        HttpServletRequest request = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(resourceRequest));

        Long compId = ParamUtil.getLong(resourceRequest, MenuPortletKeys.COMP_PARAM);
        String idEstado = request.getParameter(MenuPortletKeys.ESTADO_PARAM);

        String currentEstado = EstadoUtils.getIdEstado(compId);

        if(Validator.isNotNull(idEstado) && !idEstado.isEmpty()
                && currentEstado != idEstado) {
            EstadoUtils.updateEstado(compId, idEstado);
        }

        JSONObject json = JSONFactoryUtil.createJSONObject();

        resourceResponse.setContentType("application/json");
        resourceResponse.setCharacterEncoding("UTF-8");
        resourceResponse.getWriter().write(json.toString());
    }
}

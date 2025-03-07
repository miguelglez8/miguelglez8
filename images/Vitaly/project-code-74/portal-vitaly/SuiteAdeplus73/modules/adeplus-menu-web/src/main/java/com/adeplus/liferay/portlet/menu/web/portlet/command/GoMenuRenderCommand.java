package com.adeplus.liferay.portlet.menu.web.portlet.command;

import com.adeplus.liferay.portlet.menu.web.constants.MenuPortletKeys;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil;
import com.liferay.portal.kernel.util.*;
import org.osgi.service.component.annotations.Component;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Component(
        immediate=true,
        property= {
                "javax.portlet.name=" + MenuPortletKeys.MENU,
                "mvc.command.name=/"
        },
        service = MVCRenderCommand.class
)
public class GoMenuRenderCommand implements MVCRenderCommand {

    @Override
    public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {

        //EMPRESA
        HttpServletRequest request = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(renderRequest));
        Map<String, String[]> parameters = request.getParameterMap();
        Map.Entry<String, String[]> param = parameters.entrySet()
                .stream()
                .filter(entry -> entry.getKey().contains(MenuPortletKeys.COMP_PARAM)
                        || entry.getKey().contains(MenuPortletKeys.QUERY_PARAM))
                .findFirst()
                .orElse(null);

        if (Validator.isNotNull(param)) {
            String compId = param.getValue()[0];
            renderRequest.setAttribute(MenuPortletKeys.COMP_PARAM, compId);
        }

        //PAGINAS
        String pagina = renderRequest.getPreferences().getValue(MenuPortletKeys.LAYOUT, "0");
        Layout parentLayout = LayoutLocalServiceUtil.fetchLayout(Long.parseLong(pagina));
        if (Validator.isNotNull(parentLayout)) {
            renderRequest.setAttribute(MenuPortletKeys.LAYOUTS, parentLayout.getChildren());
        }

        return "/view.jsp";
    }

}

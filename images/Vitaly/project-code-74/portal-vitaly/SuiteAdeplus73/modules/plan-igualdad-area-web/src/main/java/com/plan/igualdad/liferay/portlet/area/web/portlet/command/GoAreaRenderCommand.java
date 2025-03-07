package com.plan.igualdad.liferay.portlet.area.web.portlet.command;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.plan.igualdad.liferay.portlet.area.web.constants.PlanIgualdadAreaWebPortletKeys;
import com.plan.igualdad.liferay.portlet.manager.model.Area;
import com.plan.igualdad.liferay.portlet.manager.service.AreaLocalServiceUtil;
import com.plan.igualdad.liferay.portlet.manager.service.persistence.AreaPK;
import org.osgi.service.component.annotations.Component;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

@Component(
        immediate=true,
        property= {
                "javax.portlet.name=" + PlanIgualdadAreaWebPortletKeys.PLANIGUALDADAREAWEB,
                "mvc.command.name=goArea"
        },
        service = MVCRenderCommand.class
)
public class GoAreaRenderCommand implements MVCRenderCommand {

    @Override
    public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {

        Long compId = ParamUtil.getLong(renderRequest, PlanIgualdadAreaWebPortletKeys.COMPID_PARAM, 0);
        Long versionId = ParamUtil.getLong(renderRequest, PlanIgualdadAreaWebPortletKeys.VERSIONID_PARAM, 0);
        Long areaId = ParamUtil.getLong(renderRequest, PlanIgualdadAreaWebPortletKeys.AREAID_PARAM, 0);

        AreaPK areaPK = new AreaPK();
        areaPK.setCompId(compId);
        areaPK.setVersionId(versionId);
        areaPK.setAreaId(areaId);

        Area area = AreaLocalServiceUtil.fetchArea(areaPK);

        renderRequest.setAttribute(PlanIgualdadAreaWebPortletKeys.AREA_PARAM, area);
        renderRequest.setAttribute(PlanIgualdadAreaWebPortletKeys.VERSIONID_PARAM, versionId);
        renderRequest.setAttribute(PlanIgualdadAreaWebPortletKeys.COMPID_PARAM, compId);

        return "/area.jsp";
    }

}

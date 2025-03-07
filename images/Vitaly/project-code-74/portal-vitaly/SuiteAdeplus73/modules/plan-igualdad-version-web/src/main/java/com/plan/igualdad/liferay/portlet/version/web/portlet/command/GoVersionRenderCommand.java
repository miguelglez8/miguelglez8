package com.plan.igualdad.liferay.portlet.version.web.portlet.command;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.plan.igualdad.liferay.portlet.manager.model.Version;
import com.plan.igualdad.liferay.portlet.manager.service.VersionLocalServiceUtil;
import com.plan.igualdad.liferay.portlet.manager.service.persistence.VersionPK;
import com.plan.igualdad.liferay.portlet.version.web.constants.PlanIgualdadVersionWebPortletKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

@Component(
        immediate=true,
        property= {
                "javax.portlet.name=" + PlanIgualdadVersionWebPortletKeys.PLANIGUALDADVERSIONWEB,
                "mvc.command.name=goVersionPlan"
        },
        service = MVCRenderCommand.class
)
public class GoVersionRenderCommand implements MVCRenderCommand  {

    @Override
    public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {

        Long compId = ParamUtil.getLong(renderRequest, PlanIgualdadVersionWebPortletKeys.COMPID_PARAM, -1);
        Long versionId = ParamUtil.getLong(renderRequest, PlanIgualdadVersionWebPortletKeys.VERSIONID_PARAM, -1);

        VersionPK versionPK = new VersionPK();
        versionPK.setCompId(compId);
        versionPK.setVersionId(versionId);

        Version version = VersionLocalServiceUtil.fetchVersion(versionPK);

        renderRequest.setAttribute(PlanIgualdadVersionWebPortletKeys.VERSION_PARAM, version);
        renderRequest.setAttribute(PlanIgualdadVersionWebPortletKeys.COMPID_PARAM, compId);

        return "/version.jsp";
    }

}

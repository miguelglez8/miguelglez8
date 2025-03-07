package com.plan.igualdad.liferay.portlet.puestos.web.portlet.command;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.plan.igualdad.liferay.portlet.manager.model.Area;
import com.plan.igualdad.liferay.portlet.manager.model.PuestoTrabajo;
import com.plan.igualdad.liferay.portlet.manager.service.AreaLocalServiceUtil;
import com.plan.igualdad.liferay.portlet.manager.service.PuestoTrabajoLocalServiceUtil;
import com.plan.igualdad.liferay.portlet.manager.service.persistence.PuestoTrabajoPK;
import com.plan.igualdad.liferay.portlet.puestos.web.constants.PlanIgualdadPuestosWebPortletKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import java.util.List;

@Component(
        immediate=true,
        property= {
                "javax.portlet.name=" + PlanIgualdadPuestosWebPortletKeys.PLANIGUALDADPUESTOSWEB,
                "mvc.command.name=goPuesto"
        },
        service = MVCRenderCommand.class
)
public class GoPuestoRenderCommand implements MVCRenderCommand {

    @Override
    public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {

        Long compId = ParamUtil.getLong(renderRequest, PlanIgualdadPuestosWebPortletKeys.COMPID_PARAM, 0);
        Long versionId = ParamUtil.getLong(renderRequest, PlanIgualdadPuestosWebPortletKeys.VERSIONID_PARAM, 0);
        Long puestoId = ParamUtil.getLong(renderRequest, PlanIgualdadPuestosWebPortletKeys.PUESTOID_PARAM, 0);
        List<Area> areas = AreaLocalServiceUtil.findByAreaActiva(compId, versionId);

        PuestoTrabajoPK puestoTrabajoPK = new PuestoTrabajoPK();
        puestoTrabajoPK.setCompId(compId);
        puestoTrabajoPK.setVersionId(versionId);
        puestoTrabajoPK.setPuestoId(puestoId);

        PuestoTrabajo puesto = PuestoTrabajoLocalServiceUtil.fetchPuestoTrabajo(puestoTrabajoPK);

        renderRequest.setAttribute(PlanIgualdadPuestosWebPortletKeys.PUESTO_PARAM, puesto);
        renderRequest.setAttribute(PlanIgualdadPuestosWebPortletKeys.VERSIONID_PARAM, versionId);
        renderRequest.setAttribute(PlanIgualdadPuestosWebPortletKeys.COMPID_PARAM, compId);
        renderRequest.setAttribute(PlanIgualdadPuestosWebPortletKeys.PUESTO_AREAS, areas);

        return "/puesto.jsp";
    }

}

package com.plan.igualdad.liferay.portlet.fechas.web.portlet.command;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.search.Query;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.plan.igualdad.liferay.portlet.fechas.web.constants.PlanIgualdadFechasWebPortletKeys;
import com.plan.igualdad.liferay.portlet.manager.model.FechaHito;
import com.plan.igualdad.liferay.portlet.manager.model.Hito;
import com.plan.igualdad.liferay.portlet.manager.service.FechaHitoLocalServiceUtil;
import com.plan.igualdad.liferay.portlet.manager.service.HitoLocalServiceUtil;
import com.plan.igualdad.liferay.portlet.manager.service.persistence.FechaHitoPK;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Component(
        immediate=true,
        property= {
                "javax.portlet.name=" + PlanIgualdadFechasWebPortletKeys.PLANIGUALDADFECHASWEB,
                "mvc.command.name=buscarHitos"
        },
        service = MVCResourceCommand.class
)
public class FindHitosResourceCommand extends BaseMVCResourceCommand {

    @Override
    protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {

        HttpServletRequest httpReq = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(resourceRequest));
        String compId = httpReq.getParameter(PlanIgualdadFechasWebPortletKeys.COMPID_PARAM);
        String versionId = httpReq.getParameter(PlanIgualdadFechasWebPortletKeys.VERSIONID_PARAM);

        JSONObject json = JSONFactoryUtil.createJSONObject();
        JSONArray array = JSONFactoryUtil.createJSONArray();

        List<Hito> hitos = HitoLocalServiceUtil.getHitos(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
        for (Hito hito : hitos) {

            FechaHitoPK fechaHitoPK = new FechaHitoPK();
            fechaHitoPK.setCompId(Long.parseLong(compId));
            fechaHitoPK.setVersionId(Long.parseLong(versionId));
            fechaHitoPK.setHitoId(hito.getHitoId());

            FechaHito fechaHito = FechaHitoLocalServiceUtil.fetchFechaHito(fechaHitoPK);

            JSONObject jsonHito = JSONFactoryUtil.createJSONObject();
            jsonHito.put(PlanIgualdadFechasWebPortletKeys.HITOID_PARAM, hito.getHitoId());
            jsonHito.put(PlanIgualdadFechasWebPortletKeys.NOMBRE_PARAM, hito.getNombre());
            jsonHito.put(PlanIgualdadFechasWebPortletKeys.FECHA_PARAM, Validator.isNotNull(fechaHito) ? fechaHito.getFecha() : StringPool.BLANK);

            array.put(jsonHito);
        }
        json.put(PlanIgualdadFechasWebPortletKeys.DATA_PARAM, array);

        resourceResponse.setContentType("application/json");
        resourceResponse.setCharacterEncoding("UTF-8");
        resourceResponse.getWriter().write(json.toString());
    }

}

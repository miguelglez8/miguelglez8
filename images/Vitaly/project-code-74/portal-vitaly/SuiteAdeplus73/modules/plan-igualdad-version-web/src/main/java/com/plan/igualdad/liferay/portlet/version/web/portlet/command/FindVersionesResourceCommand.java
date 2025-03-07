package com.plan.igualdad.liferay.portlet.version.web.portlet.command;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.plan.igualdad.liferay.portlet.manager.model.Version;
import com.plan.igualdad.liferay.portlet.manager.service.VersionLocalServiceUtil;
import com.plan.igualdad.liferay.portlet.version.web.constants.PlanIgualdadVersionWebPortletKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Component(
        immediate=true,
        property= {
                "javax.portlet.name=" + PlanIgualdadVersionWebPortletKeys.PLANIGUALDADVERSIONWEB,
                "mvc.command.name=buscarVersiones"
        },
        service = MVCResourceCommand.class
)
public class FindVersionesResourceCommand extends BaseMVCResourceCommand {

    @Override
    protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {

        HttpServletRequest httpReq = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(resourceRequest));
        String compId = httpReq.getParameter(PlanIgualdadVersionWebPortletKeys.COMPID_PARAM);

        JSONObject json = JSONFactoryUtil.createJSONObject();
        JSONArray array = JSONFactoryUtil.createJSONArray();

        List<Version> versiones = VersionLocalServiceUtil.findByComp(Long.parseLong(compId));
        for (Version version : versiones) {
            JSONObject jsonVersion = JSONFactoryUtil.createJSONObject();
            jsonVersion.put(PlanIgualdadVersionWebPortletKeys.VERSIONID_PARAM, version.getVersionId());
            jsonVersion.put(PlanIgualdadVersionWebPortletKeys.COMPID_PARAM, version.getCompId());
            jsonVersion.put(PlanIgualdadVersionWebPortletKeys.VERSION_NOMBRE, version.getNombre());
            jsonVersion.put(PlanIgualdadVersionWebPortletKeys.VERSION_INICIO_DATOS, version.getInicioPeriodoDatos());
            jsonVersion.put(PlanIgualdadVersionWebPortletKeys.VERSION_FIN_DATOS, version.getFinPeriodoDatos());
            jsonVersion.put(PlanIgualdadVersionWebPortletKeys.VERSION_INICIO_IGUALDAD, version.getInicioPeriodoPlan());
            jsonVersion.put(PlanIgualdadVersionWebPortletKeys.VERSION_FIN_IGUALDAD, version.getFinPeriodoPlan());
            jsonVersion.put(PlanIgualdadVersionWebPortletKeys.VERSION_BAJA, Validator.isNotNull(version.getBaja()) ? version.getBaja() : StringPool.BLANK);
            jsonVersion.put(PlanIgualdadVersionWebPortletKeys.VERSION_CREACION, version.getCreateDate());

            array.put(jsonVersion);
        }
        json.put(PlanIgualdadVersionWebPortletKeys.DATA_PARAM, array);

        resourceResponse.setContentType("application/json");
        resourceResponse.setCharacterEncoding("UTF-8");
        resourceResponse.getWriter().write(json.toString());
    }

}

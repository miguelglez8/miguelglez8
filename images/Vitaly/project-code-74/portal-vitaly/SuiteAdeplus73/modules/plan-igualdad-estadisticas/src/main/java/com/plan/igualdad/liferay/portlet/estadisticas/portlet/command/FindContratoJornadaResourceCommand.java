package com.plan.igualdad.liferay.portlet.estadisticas.portlet.command;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.plan.igualdad.liferay.portlet.estadisticas.constants.PlanIgualdadEstadisticasPortletKeys;
import com.plan.igualdad.liferay.portlet.manager.model.Estadistica;
import com.plan.igualdad.liferay.portlet.manager.service.EstadisticaLocalServiceUtil;
import com.plan.igualdad.liferay.portlet.manager.service.persistence.EstadisticaPK;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

@Component(
        immediate=true,
        property= {
                "javax.portlet.name=" + PlanIgualdadEstadisticasPortletKeys.PLANIGUALDADESTADISTICAS,
                "mvc.command.name=findContratoJornada"
        },
        service = MVCResourceCommand.class
)
public class FindContratoJornadaResourceCommand extends BaseMVCResourceCommand {

    @Override
    protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {

        Long compId = ParamUtil.getLong(resourceRequest, PlanIgualdadEstadisticasPortletKeys.COMPID_PARAM);
        Long versionId = ParamUtil.getLong(resourceRequest, PlanIgualdadEstadisticasPortletKeys.VERSIONID_PARAM);

        EstadisticaPK estadisticaPK = new EstadisticaPK();
        estadisticaPK.setCompId(compId);
        estadisticaPK.setVersionId(versionId);
        estadisticaPK.setSeccionId(PlanIgualdadEstadisticasPortletKeys.CONTRATO_JORNADA);

        Estadistica estadistica = EstadisticaLocalServiceUtil.fetchEstadistica(estadisticaPK);

        JSONObject json = JSONFactoryUtil.createJSONObject();
        if (Validator.isNotNull(estadistica)) {
            json = JSONFactoryUtil.createJSONObject(estadistica.getEstadistica());
        } else {

            String[] jornadas = {"0", "1", "2"};
            String[] contratos = {"IND", "TEM", "DIS"};

            JSONArray array = JSONFactoryUtil.createJSONArray();

            for (String jornada : jornadas) {
                for (String contrato : contratos) {

                    JSONObject contratoJSON = JSONFactoryUtil.createJSONObject();
                    contratoJSON.put("jornada", jornada);
                    contratoJSON.put("contrato", contrato);
                    contratoJSON.put("hombres", 0);
                    contratoJSON.put("mujeres", 0);
                    array.put(contratoJSON);

                }
            }

            json.put("data", array);

        }

        resourceResponse.setContentType("application/json");
        resourceResponse.setCharacterEncoding("UTF-8");
        resourceResponse.getWriter().write(json.toString());

    }

}

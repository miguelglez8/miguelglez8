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
import java.util.*;

@Component(
        immediate=true,
        property= {
                "javax.portlet.name=" + PlanIgualdadEstadisticasPortletKeys.PLANIGUALDADESTADISTICAS,
                "mvc.command.name=findAuditoria"
        },
        service = MVCResourceCommand.class
)
public class FindAuditoriaResourceCommand extends BaseMVCResourceCommand {

    @Override
    protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {

        Long compId = ParamUtil.getLong(resourceRequest, PlanIgualdadEstadisticasPortletKeys.COMPID_PARAM);
        Long versionId = ParamUtil.getLong(resourceRequest, PlanIgualdadEstadisticasPortletKeys.VERSIONID_PARAM);

        EstadisticaPK estadisticaPK = new EstadisticaPK();
        estadisticaPK.setCompId(compId);
        estadisticaPK.setVersionId(versionId);
        estadisticaPK.setSeccionId(PlanIgualdadEstadisticasPortletKeys.AUDITORIA);

        Estadistica estadistica = EstadisticaLocalServiceUtil.fetchEstadistica(estadisticaPK);

        JSONObject json = JSONFactoryUtil.createJSONObject();
        if (Validator.isNotNull(estadistica)) {
            json = JSONFactoryUtil.createJSONObject(estadistica.getEstadistica());
        } else {

            Map<String, List<String>> auditorias = new HashMap<String, List<String>>(){{
                put("00", Arrays.asList("PRT"));                   //Masa salarial (MSA)
                put("01", Arrays.asList("SBA", "CSA", "CEX"));     //Promedio de retribuciones satisfechas (PRS)
                put("02", Arrays.asList("SBA", "CSA", "CEX"));     //Mediana de retribuciones (MRE)
            }};

            JSONArray array = JSONFactoryUtil.createJSONArray();
            for (String key : auditorias.keySet()) {
                for (String value : auditorias.get(key))  {

                    JSONObject auditoriaJSON = JSONFactoryUtil.createJSONObject();
                    auditoriaJSON.put("tipo", key);
                    auditoriaJSON.put("auditoria", value);
                    auditoriaJSON.put("nMujeres", 0);
                    auditoriaJSON.put("nHombres", 0);
                    auditoriaJSON.put("conceptos", "");
                    array.put(auditoriaJSON);

                }
            }
            json.put("data", array);

        }

        resourceResponse.setContentType("application/json");
        resourceResponse.setCharacterEncoding("UTF-8");
        resourceResponse.getWriter().write(json.toString());

    }

}

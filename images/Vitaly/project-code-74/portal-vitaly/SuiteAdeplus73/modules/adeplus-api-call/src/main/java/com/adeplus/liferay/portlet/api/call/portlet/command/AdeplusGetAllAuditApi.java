package com.adeplus.liferay.portlet.api.call.portlet.command;

import com.adeplus.liferay.portlet.api.call.constants.AdeplusApiCallPortletKeys;
import com.adeplus.liferay.portlet.suite.manager.model.AuditadoDataApi;
import com.adeplus.liferay.portlet.suite.manager.model.TemporalDataApi;
import com.adeplus.liferay.portlet.suite.manager.service.AuditadoDataApiLocalServiceUtil;
import com.adeplus.liferay.portlet.suite.manager.service.TemporalDataApiLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.util.Validator;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import java.util.List;

@Component(
        immediate=true,
        property= {
                "javax.portlet.name=" + AdeplusApiCallPortletKeys.ADEPLUSAPICALL ,
                "mvc.command.name=getAllAuditApi"
        },
        service = MVCResourceCommand.class
)
public class AdeplusGetAllAuditApi extends BaseMVCResourceCommand {
    @Override
    protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
        JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();
        JSONObject json = null;
        TemporalDataApi data = null;
        List<AuditadoDataApi> auditadoDataApis= AuditadoDataApiLocalServiceUtil.getAuditadoDataApis(QueryUtil.ALL_POS,QueryUtil.ALL_POS);
        for(AuditadoDataApi auditado:auditadoDataApis){
            json = JSONFactoryUtil.createJSONObject();
            data=TemporalDataApiLocalServiceUtil.fetchTemporalDataApi(auditado.getIdDataTemporal());
            if((auditado.getProcesado()==0 || auditado.getProcesado()>=13) && Validator.isNotNull(data)) {
                json.put("idDataTemporal", auditado.getIdDataTemporal());
                json.put("mensaje", auditado.getMensaje());
                json.put("estado", auditado.getProcesado());
                json.put("evento", auditado.getEvento());
                json.put("idCliente", auditado.getIdClient());
                json.put("idContrato", auditado.getIdContract());
                json.put("dataEmpresa", data.getDataApiEmpresa());
                jsonArray.put(json);
            }
        }
        jsonObject.put("data", jsonArray);
        resourceResponse.setContentType("application/json");
        resourceResponse.setCharacterEncoding("UTF-8");
        resourceResponse.getWriter().write(jsonObject.toString());
    }
}

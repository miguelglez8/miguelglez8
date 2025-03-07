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
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.Validator;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import java.util.List;

@Component(
        immediate=true,
        property= {
                "javax.portlet.name=" + AdeplusApiCallPortletKeys.ADEPLUSAPICALL ,
                "mvc.command.name=getAllAuditApiError"
        },
        service = MVCResourceCommand.class
)

public class AdeplusGetAllAuditApiError extends BaseMVCResourceCommand {
    @Override
    protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
        JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();
        JSONObject json = null;
        List<AuditadoDataApi> auditadoDataApis= AuditadoDataApiLocalServiceUtil.getAuditadoDataApis(QueryUtil.ALL_POS,QueryUtil.ALL_POS);
        TemporalDataApi data=null;
        User user = null;
        for(AuditadoDataApi auditado:auditadoDataApis){
            //json = JSONFactoryUtil.createJSONObject();
            data=TemporalDataApiLocalServiceUtil.fetchTemporalDataApi(auditado.getIdDataTemporal());

            if(Validator.isNotNull(data) && auditado.getProcesado()>0 && auditado.getProcesado()<13) {
                json = JSONFactoryUtil.createJSONObject();
                json.put("idDataTemporal", auditado.getIdDataTemporal());
                json.put("mensaje", auditado.getMensaje());
                json.put("estado", auditado.getProcesado());
                json.put("dataTemporalEmpresa", data.getDataApiEmpresa());
                json.put("dataTemporalUsuarios", data.getDataApiUsuarios());
                json.put("dataTemporalApplication", data.getDataApiApplicaciones());
                json.put("fechaCreacion", data.getCreateDate().getTime());
                json.put("procesadoAdmin", auditado.getState());
                if(Validator.isNotNull(auditado.getChangeStateDate())){
                    json.put("fechaEstadoAdmin", auditado.getChangeStateDate().getTime());
                    if(auditado.getUserIdChangeState() != 0){
                        user =  UserLocalServiceUtil.getUser(auditado.getUserIdChangeState());
                        if(Validator.isNotNull(user)) json.put("usuarioEstadoAdmin", user.getFullName());
                    }else{
                        json.put("usuarioEstadoAdmin", "");
                    }


                }else{
                    json.put("fechaEstadoAdmin", "");
                    json.put("usuarioEstadoAdmin", "");
                }


                jsonArray.put(json);
            }
        }
        jsonObject.put("data", jsonArray);
        resourceResponse.setContentType("application/json");
        resourceResponse.setCharacterEncoding("UTF-8");
        resourceResponse.getWriter().write(jsonObject.toString());
    }
}
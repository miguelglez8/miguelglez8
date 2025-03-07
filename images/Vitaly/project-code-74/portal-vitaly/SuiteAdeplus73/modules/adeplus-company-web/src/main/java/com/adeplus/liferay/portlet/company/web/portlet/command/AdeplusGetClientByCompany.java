package com.adeplus.liferay.portlet.company.web.portlet.command;

import com.adeplus.liferay.portlet.commons.web.client.AdeplusClientUtil;
import com.adeplus.liferay.portlet.company.web.constants.AdeplusCompaniesPortletKeys;
import com.adeplus.liferay.portlet.suite.manager.model.CompClientApplication;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import java.util.List;
@Component(
        immediate=true,
        property= {
                "javax.portlet.name=" + AdeplusCompaniesPortletKeys.ADEPLUSCOMPANIESMULTI ,
                "mvc.command.name=getAllClients"
        },
        service = MVCResourceCommand.class
)
public class AdeplusGetClientByCompany extends BaseMVCResourceCommand {
    private static Log _log = LogFactoryUtil.getLog(AdeplusGetClientByCompany.class);
    @Override
    protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
        long idComp= ParamUtil.getLong(resourceRequest, AdeplusCompaniesPortletKeys.COMPANY_ID_EDIT, 0);


        List<CompClientApplication> clients= AdeplusClientUtil.getClientFromCompany(idComp);
        _log.info(clients);
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();
        if(Validator.isNotNull(clients)) {
            _log.info(clients.size());
            for (CompClientApplication client : clients) {
                if(Validator.isNull(client.getDeleteDate())) {
                    JSONArray jsonArrayApps = JSONFactoryUtil.createJSONArray();
                    JSONObject jsonObjectApp = JSONFactoryUtil.createJSONObject();
                    JSONObject json = JSONFactoryUtil.createJSONObject();

                    //Aniadimos el Json de ids al Json de array para las aplicaciones

                    json.put("contrato", client.getContractId());
                    json.put("cliente", client.getClientId());
                    json.put("nombre", client.getDescription());
                    json.put("apps", JSONFactoryUtil.createJSONArray(client.getApplicationId()));
                    jsonArray.put(json);
                }
            }
            resourceResponse.setContentType("application/json");
            resourceResponse.setCharacterEncoding("UTF-8");
            resourceResponse.getWriter().write(jsonArray.toString());
        }
    }
}

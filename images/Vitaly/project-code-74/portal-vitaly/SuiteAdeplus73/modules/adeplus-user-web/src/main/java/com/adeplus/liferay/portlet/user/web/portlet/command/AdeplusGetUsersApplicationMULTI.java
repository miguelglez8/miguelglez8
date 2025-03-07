package com.adeplus.liferay.portlet.user.web.portlet.command;

import com.adeplus.liferay.portlet.suite.manager.model.CompClientApplication;
import com.adeplus.liferay.portlet.suite.manager.model.UserApplicationClient;
import com.adeplus.liferay.portlet.suite.manager.service.CompClientApplicationLocalServiceUtil;
import com.adeplus.liferay.portlet.suite.manager.service.UserApplicationClientLocalServiceUtil;
import com.adeplus.liferay.portlet.user.web.constants.AdeplusUsersPortletKeys;
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
                "javax.portlet.name=" + AdeplusUsersPortletKeys.ADEPLUSUSERS ,
                "mvc.command.name=getAllApplicationUser"
        },
        service = MVCResourceCommand.class
)
public class AdeplusGetUsersApplicationMULTI extends BaseMVCResourceCommand {
    private static Log _log = LogFactoryUtil.getLog(AdeplusGetUsersApplicationMULTI.class);

    //Obtendremos todas las aplicaciones para el usuario, incluido contrato cliente a excepción del nombre ya que no es relevante
    @Override
    protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
        long idComp= ParamUtil.getLong(resourceRequest, AdeplusUsersPortletKeys.USER_COMPANY_ID_EDIT, 0);
        long idUser= ParamUtil.getLong(resourceRequest, AdeplusUsersPortletKeys.USER_EDIT_ID, 0);
        List<UserApplicationClient> usersApplication= UserApplicationClientLocalServiceUtil.getApplicationsFromUser(idUser,idComp);
        if(Validator.isNotNull(usersApplication)) {
            JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

            CompClientApplication compClientContract = null;
            /*CompClientApplicationPK companyPK = new CompClientApplicationPK();
            companyPK.setCompId(idComp);*/

            for (UserApplicationClient userAppClient : usersApplication) {
                JSONArray jsonArrayApps = JSONFactoryUtil.createJSONArray();
                JSONObject jsonObjectApp = JSONFactoryUtil.createJSONObject();
                JSONObject json = JSONFactoryUtil.createJSONObject();


                //Aniadimos el Json de ids al Json de array para las aplicaciones

                json.put("contrato", userAppClient.getContractId());
                json.put("cliente", userAppClient.getClientId());


                /*companyPK.setClientId(userAppClient.getClientId());
                companyPK.setContractId(userAppClient.getContractId());*/

                compClientContract =CompClientApplicationLocalServiceUtil.fetchCompClientApplication(userAppClient.getEmpresaId());
                //compClientContract = CompClientApplicationLocalServiceUtil.getClientByCompAndClient(userAppClient.getClientId(), String.valueOf(userAppClient.getContractId()));
                if(Validator.isNotNull(compClientContract)){
                    json.put("nombre", compClientContract.getDescription());
                }else{
                    json.put("nombre","");
                }

                json.put("apps", JSONFactoryUtil.createJSONArray(userAppClient.getApplicationId()));

                jsonArray.put(json);
            }
            resourceResponse.setContentType("application/json");
            resourceResponse.setCharacterEncoding("UTF-8");
            resourceResponse.getWriter().write(jsonArray.toString());
        }
    }
}

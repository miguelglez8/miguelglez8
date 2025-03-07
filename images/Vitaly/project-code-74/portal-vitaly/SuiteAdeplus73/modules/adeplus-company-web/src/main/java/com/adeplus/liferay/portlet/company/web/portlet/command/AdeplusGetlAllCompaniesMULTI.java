package com.adeplus.liferay.portlet.company.web.portlet.command;

import com.adeplus.liferay.portlet.commons.web.permission.AdeplusPermissionCompanyUtil;
import com.adeplus.liferay.portlet.company.web.constants.AdeplusCompaniesPortletKeys;
import com.adeplus.liferay.portlet.suite.manager.model.Application;
import com.adeplus.liferay.portlet.suite.manager.model.Comp;
import com.adeplus.liferay.portlet.suite.manager.model.CompClientApplication;
import com.adeplus.liferay.portlet.suite.manager.service.CompClientApplicationLocalServiceUtil;
import com.adeplus.liferay.portlet.suite.manager.service.CompLocalServiceUtil;
import com.adeplus.liferay.portlet.suite.manager.service.UserCompanyLocalServiceUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;

@Component(
        immediate=true,
        property= {
                "javax.portlet.name=" + AdeplusCompaniesPortletKeys.ADEPLUSCOMPANIESMULTI ,
                "mvc.command.name=getAllCompaniesMULTI"
        },
        service = MVCResourceCommand.class
)
public class AdeplusGetlAllCompaniesMULTI extends BaseMVCResourceCommand {

    private static Log _log = LogFactoryUtil.getLog(AdeplusGetlAllCompaniesMULTI.class);

    @Override
    protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {


        ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
        HttpServletRequest httpReq = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(resourceRequest));

        List<Comp> comps = CompLocalServiceUtil.getAllComps();
        JSONObject jsonObject = JSONFactoryUtil.createJSONObject(), jsonClientContrat = null, jsonApp = null;
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray(), jsonClientContrats = null, jsonClientContratApps = null, jsonClientContratAppsName = null;
        JSONObject json = null;
        String resumenApps = "";
        List<CompClientApplication> clientContrats = null;
        Application appEntity = null;

        Calendar hoyMasMes = Calendar.getInstance();
        Calendar fechaBaja = Calendar.getInstance();
        hoyMasMes.add(Calendar.DATE, +30);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        ResourceBundle bundle = ResourceBundle.getBundle("content/Language", themeDisplay.getUser().getLocale());
        String yes = LanguageUtil.get(bundle, "user.view.yes");
        String no  = LanguageUtil.get(bundle, "user.view.no");

        boolean isActive = true;

        for( Comp comp :  comps){
            //es modelo multi
            if(Validator.isNotNull(CompClientApplicationLocalServiceUtil.getClientsByCompanyId(comp.getCompId()) )) {

                json = JSONFactoryUtil.createJSONObject();
                json.put("compId", comp.getCompId());
                json.put("nombre", comp.getName());
                json.put("cif", comp.getCif());

                isActive = true;
                if (comp.getDeleteDate() != null) {
                    fechaBaja.setTime(comp.getDeleteDate());
                    if (hoyMasMes.after(fechaBaja)) isActive = false;
                }

                if (!isActive) {
                    json.put("activa", no);
                    json.put("fechaBaja", dateFormat.format(comp.getDeleteDate()));
                    json.put("fechaBajaTime", comp.getDeleteDate().getTime());
                } else {
                    json.put("activa", yes);
                    json.put("fechaBaja", " ");
                    json.put("fechaBajaTime", " ");
                }

                //Obtener clientes + contrato de la empresa
                clientContrats = CompClientApplicationLocalServiceUtil.getClientsByCompanyId(comp.getCompId());
                resumenApps = "";
                if (Validator.isNotNull(clientContrats) && clientContrats.size() > 0) {
                    jsonClientContrats = JSONFactoryUtil.createJSONArray();
                    for (CompClientApplication clientContrat : clientContrats) {
                        jsonClientContrat = JSONFactoryUtil.createJSONObject();
                        jsonClientContrat.put("cliente", clientContrat.getClientId());
                        jsonClientContrat.put("contrato", clientContrat.getContractId());
                        jsonClientContrat.put("nombre", clientContrat.getDescription());

                        jsonClientContratApps = JSONFactoryUtil.createJSONArray(clientContrat.getApplicationId());

                        for (int i = 0; i < jsonClientContratApps.length(); i++) {
                            jsonApp = jsonClientContratApps.getJSONObject(i);
                            if (resumenApps.isEmpty() || resumenApps.indexOf("#" + jsonApp.getString("appId")) == -1) {
                                resumenApps += "#" + jsonApp.getString("appId");
                            }
                        }

                        jsonClientContrat.put("apps", jsonClientContratApps);
                        jsonClientContrats.put(jsonClientContrat);

                    }

                    json.put("aplicaciones", resumenApps);
                    json.put("clienteContratos", jsonClientContrats);
                } else { // son los antiguos
                    json.put("aplicaciones", AdeplusPermissionCompanyUtil.getCompanyApplicationNames(comp.getCompId()));
                }


                json.put("empleados", UserCompanyLocalServiceUtil.getUsersCountFromCompany(comp.getCompId()));


                json.put("fechaAlta", dateFormat.format(comp.getCreateDate()));
                json.put("fechaAltaTime", comp.getCreateDate().getTime());

                jsonArray.put(json);
            }
        }

        jsonObject.put("data", jsonArray);

        resourceResponse.setContentType("application/json");
        resourceResponse.setCharacterEncoding("UTF-8");
        resourceResponse.getWriter().write(jsonObject.toString());

        comps = null;
        jsonArray = null;


    }
}

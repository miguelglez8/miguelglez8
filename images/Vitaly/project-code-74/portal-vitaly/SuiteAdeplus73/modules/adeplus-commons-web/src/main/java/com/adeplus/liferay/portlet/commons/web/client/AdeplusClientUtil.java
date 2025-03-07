package com.adeplus.liferay.portlet.commons.web.client;

import com.adeplus.liferay.portlet.commons.web.audit.AdeplusAuditUtil;
import com.adeplus.liferay.portlet.commons.web.constants.AdeplusAuditPortletKeys;
import com.adeplus.liferay.portlet.suite.manager.model.Comp;
import com.adeplus.liferay.portlet.suite.manager.model.CompClientApplication;
import com.adeplus.liferay.portlet.suite.manager.service.CompClientApplicationLocalServiceUtil;
import com.adeplus.liferay.portlet.suite.manager.service.persistence.CompClientApplicationPK;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Date;
import java.util.List;

public class AdeplusClientUtil {
    private static Log _log = LogFactoryUtil.getLog(AdeplusClientUtil.class);

    /*Metodo para crar  los cliente-contratos-aplicaciones y aniadir descripcion del client,
    si ya existe este devolvera null*/
    public static CompClientApplication createClient(long compId,long clientId, long idContrato,String applicationId, String cif, String name, String description){
        CompClientApplication client = null;
        try {
            if (Validator.isNull(CompClientApplicationLocalServiceUtil.getClientByCompAndClientAndContract(compId, clientId, idContrato))) {
                client=CompClientApplicationLocalServiceUtil.createCompClientApplication(CounterLocalServiceUtil.increment(CompClientApplication.class.getName()));
                client.setCompId(compId);
                client.setClientId(clientId);
                client.setContractId(idContrato);
                client.setApplicationId(applicationId);
                client.setDescription(description);

                client.setCif(cif);
                client.setName(name);


                CompClientApplicationLocalServiceUtil.updateCompClientApplication(client);
            } else {
                _log.debug("Ya existe en la BBDD una relación igual por lo tanto esta no se creara");

            }
        }catch (Exception e){
            _log.error("fallo al crear el cliente: "+e.getMessage());
        }
        return client;
    }

    public static CompClientApplication updateClient(long compId,long clientId, long idContrato,String cif, String applicationId,String description){
        CompClientApplication client = null;
        try {
            if (Validator.isNull(CompClientApplicationLocalServiceUtil.getClientByCompAndClientAndContract(compId, clientId, idContrato))) {
                client=CompClientApplicationLocalServiceUtil.createCompClientApplication(CounterLocalServiceUtil.increment(CompClientApplication.class.getName()));
                client.setCompId(compId);
                client.setClientId(clientId);
                client.setContractId(idContrato);
            } else {
                client=CompClientApplicationLocalServiceUtil.getClientByCompAndClientAndContract(compId, clientId, idContrato);
            }
            client.setCif(cif);
            client.setApplicationId(applicationId);
            client.setDescription(description);
            CompClientApplicationLocalServiceUtil.updateCompClientApplication(client);
        }catch (Exception e){
            _log.error("fallo al crear el cliente: "+e.getMessage());
        }
        return client;
    }



    //Metodo para actualizar las aplicaciones o la descripcion del client, si no se encuentra este devolvera null
    /*
    public static CompClientApplication updateClient(long compId,long clientId, long idContrato,String applicationId,String description){
        CompClientApplication client = CompClientApplicationLocalServiceUtil.getClientByCompAndClientAndContract(compId,clientId,idContrato);

        if(Validator.isNotNull(client)){
            client.setApplicationId(applicationId);
            client.setDescription(description);
            CompClientApplicationLocalServiceUtil.updateCompClientApplication(client);
        }

        return client;
    }
    */
    //Metodo para eliminar un contrato, si en el form deciden elimianrlo este se llamara mediante una llamada ajax
    public static boolean deleteClient(long compId,long clientId, long idContrato){
        boolean deleted=false;
        CompClientApplication client = CompClientApplicationLocalServiceUtil.getClientByCompAndClientAndContract(compId,clientId,idContrato);

        if(Validator.isNotNull(client)){
            CompClientApplicationLocalServiceUtil.deleteCompClientApplication(client);
            deleted=true;
        }
        return deleted;
    }
    //Obtenemos todos los clientes por el id de compañia
    public static List<CompClientApplication> getClientFromCompany(long companyId){
        return CompClientApplicationLocalServiceUtil.getClientsByCompanyId(companyId);
    }

    public static List<CompClientApplication> getClientsApplicationsByCompanyAndClient(long companyId,long clientId){
        return CompClientApplicationLocalServiceUtil.getClientsApplicationsByCompAndClient(companyId, clientId);
    }

}

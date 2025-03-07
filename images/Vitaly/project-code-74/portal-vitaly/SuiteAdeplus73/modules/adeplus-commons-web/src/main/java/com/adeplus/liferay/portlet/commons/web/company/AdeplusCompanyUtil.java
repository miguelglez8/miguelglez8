package com.adeplus.liferay.portlet.commons.web.company;

import com.adeplus.liferay.portlet.commons.web.audit.AdeplusAuditUtil;
import com.adeplus.liferay.portlet.commons.web.bean.AplicationApi;
import com.adeplus.liferay.portlet.commons.web.bean.CompanyApi;
import com.adeplus.liferay.portlet.commons.web.bean.UserApi;
import com.adeplus.liferay.portlet.commons.web.client.AdeplusClientUtil;
import com.adeplus.liferay.portlet.commons.web.constants.AdeplusAuditPortletKeys;
import com.adeplus.liferay.portlet.commons.web.keycloak.AdeplusKeycloakMultiBackgroundUtil;
import com.adeplus.liferay.portlet.commons.web.keycloak.RealmBackground;
import com.adeplus.liferay.portlet.commons.web.logo.AdeplusLogoUtil;
import com.adeplus.liferay.portlet.commons.web.permission.AdeplusPermissionUserUtil;
import com.adeplus.liferay.portlet.commons.web.user.AdeplusUserUtil;
import com.adeplus.liferay.portlet.commons.web.user.AdeplusUserUtilMulti;
import com.adeplus.liferay.portlet.mailing.web.mail.CompanyMailing;
import com.adeplus.liferay.portlet.mailing.web.mail.UserMailing;
import com.adeplus.liferay.portlet.suite.manager.model.*;
import com.adeplus.liferay.portlet.suite.manager.service.*;
import com.adeplus.liferay.portlet.suite.manager.service.persistence.UserCompanyPK;
import com.adeplus.liferay.portlet.suite.manager.service.persistence.UserRolePK;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import org.keycloak.admin.client.Keycloak;

import java.io.File;
import java.util.*;

public class AdeplusCompanyUtil {


    private static Keycloak kc = null;
    private static RealmBackground realm;

    private static String isoPlusAlias="ISO+";

    private static Log _log = LogFactoryUtil.getLog(AdeplusCompanyUtil.class);

    public static Comp createCompany(long compId, String name, String cif, String description, String observations, String agreement, Date deleteDate, String IdCrm, String idContrato){

        Comp comp = null;
        if(compId == 0){
            comp = CompLocalServiceUtil.createComp(CounterLocalServiceUtil.increment(Comp.class.getName()));
        } else {
            comp = CompLocalServiceUtil.createComp(compId);
        }

        comp.setName(name);
        comp.setCif(cif);
        comp.setDescription(description);
        comp.setObservations(observations);
        comp.setAgreement(agreement);
        comp.setDeleteDate(deleteDate);
        comp.setIdCrm(IdCrm);
        comp.setIdContrato(idContrato);

        CompLocalServiceUtil.updateComp(comp);

        //AdeplusAuditUtil.addAudit(PortalUtil.getDefaultCompanyId(), 0, 0, AdeplusAuditPortletKeys.AUDIT_COMPANY_CREATE, "Create company "+ cif + " - " + name +". CompId" + comp.getCompId());

        return comp;
    }

    public static Comp updateCompany(long compId, String name, String cif, String description, String observations, String agreement, Date deleteDate, String idCrm, String idContrato){

        Comp comp = null;

        Date now = new Date();

        try {

            comp = CompLocalServiceUtil.getComp(compId);

            comp.setName(name);
            comp.setCif(cif);
            comp.setDescription(description);
            comp.setObservations(observations);
            comp.setAgreement(agreement);
            comp.setDeleteDate(deleteDate);
            comp.setIdCrm(idCrm);
            comp.setIdContrato(idContrato);

            CompLocalServiceUtil.updateComp(comp);
            

            AdeplusAuditUtil.addAudit(PortalUtil.getDefaultCompanyId(), 0, 0, AdeplusAuditPortletKeys.AUDIT_COMPANY_UPDATE, "Update company "+ cif + " - " + name +". CompId" + comp.getCompId());

            if(Validator.isNotNull(deleteDate) && deleteDate.before(now)) {

                //Send mail to administrators.
                List<UserCompany> userCompanies = UserCompanyLocalServiceUtil.getUsersFromCompany(comp.getCompId());
                for(UserCompany userCompany:userCompanies) {

                    User user = UserLocalServiceUtil.fetchUser(userCompany.getUserId());

                    if (Validator.isNotNull(user) && userCompany.isAdmin()
                            && (Validator.isNull(userCompany.getUserEndDate()) || userCompany.getUserEndDate().after(now))){

                        CompanyMailing.companyDelete(user);
                    }
                }

                //AdeplusUserUtil.inactiveAllUsersFromCompany(comp.getCompId());

                AdeplusUserUtil.setEndDateAllUsersFromCompany(comp.getCompId(), deleteDate);
            }
            //AdeplusKeycloakUtil.createCompanyKeycloak(compId, name, cif, description, observations,deleteDate);

        } catch (PortalException e) {
            _log.error(e);
        }

        return comp;
    }

    public static Comp updateCompanyConfiguration(long groupId, long userId, long compId, Map<Locale, String> description, Map<Locale, String> observations, Map<Locale, String> agreement, String logoName, File logo, ServiceContext serviceContext){

        Comp comp = null;

        try {

            comp = CompLocalServiceUtil.getComp(compId);

            if(Validator.isNotNull(comp)) {
                comp.setDescriptionMap(description);
                comp.setObservationsMap(observations);
                comp.setAgreementMap(agreement);

                //Save logo
                if(!Validator.isBlank(logoName) && Validator.isNotNull(logo)) {
                    DLFileEntry dlFileEntry = AdeplusLogoUtil.saveLogo(groupId, userId, comp.getCif(), logoName, logo, serviceContext);
                    if (Validator.isNotNull(dlFileEntry)) {
                        //Delete the old logo.
                        if (comp.getLogo() > 0) {
                            AdeplusLogoUtil.deleteFile(comp.getLogo());
                        }

                        comp.setLogo(dlFileEntry.getFileEntryId());
                    }
                }
            }

            CompLocalServiceUtil.updateComp(comp);

            AdeplusAuditUtil.addAudit(PortalUtil.getDefaultCompanyId(), 0, 0, AdeplusAuditPortletKeys.AUDIT_COMPANY_CONFIGURATION_UPDATE, "Update company configuration "+ comp.getCif() + " - " + comp.getName() +". CompId" + comp.getCompId());


        } catch (PortalException e) {
            _log.error(e);
        }

        return comp;
    }

    public static void deleteCompany(long compId){

        Comp comp = null;

        try {

            comp = CompLocalServiceUtil.getComp(compId);

            comp.setDeleteDate(new Date());

            CompLocalServiceUtil.updateComp(comp);

            //Send mail to administrators.
            Date now = new Date();
            List<UserCompany> userCompanies = UserCompanyLocalServiceUtil.getUsersFromCompany(comp.getCompId());
            for(UserCompany userCompany:userCompanies) {

                User user = UserLocalServiceUtil.fetchUser(userCompany.getUserId());

                if (Validator.isNotNull(user) && user.isActive() && userCompany.isAdmin()
                        && (Validator.isNull(userCompany.getUserEndDate()) || userCompany.getUserEndDate().after(now))){

                    CompanyMailing.companyDelete(user);
                }
            }

            AdeplusUserUtil.inactiveAllUsersFromCompany(comp.getCompId());

            AdeplusAuditUtil.addAudit(PortalUtil.getDefaultCompanyId(), 0, 0, AdeplusAuditPortletKeys.AUDIT_COMPANY_DELETE, "Inactive company "+ compId + ".");

        } catch (PortalException e) {
            _log.error(e);
        }

    }

    public static void deleteLogoCompany(long compId){

        Comp comp = null;

        try {

            comp = CompLocalServiceUtil.getComp(compId);

            if(Validator.isNotNull(comp)) {
                //Delete logo
                if(comp.getLogo() > 0) {
                    AdeplusLogoUtil.deleteFile(comp.getLogo());
                    comp.setLogo(0);
                }
            }

            CompLocalServiceUtil.updateComp(comp);

            AdeplusAuditUtil.addAudit(PortalUtil.getDefaultCompanyId(), 0, 0, AdeplusAuditPortletKeys.AUDIT_COMPANY_CONFIGURATION_DELETE_LOGO, "Update company configuration delete logo"+ comp.getCif() + " - " + comp.getName() +". CompId" + comp.getCompId());


        } catch (PortalException e) {
            _log.error(e);
        }

    }

    public static long createTemporalData(String typeEvent, String dataCompany,String dataApplicaciones,String dataUsuarios){
        Date now=new Date();
        TemporalDataApi temporalDataApi =TemporalDataApiLocalServiceUtil.createTemporalDataApi(CounterLocalServiceUtil.increment(TemporalDataApi.class.getName()));
        temporalDataApi.setTypeEvent(typeEvent);
        _log.info("Type event: "+typeEvent);
        temporalDataApi.setDataApiEmpresa(dataCompany);
        _log.info("dataCompany: "+dataCompany);
        temporalDataApi.setDataApiApplicaciones(dataApplicaciones);
        _log.info("dataApplicaciones: "+dataApplicaciones);
        temporalDataApi.setDataApiUsuarios(dataUsuarios);
        _log.info("dataUsuarios: "+dataUsuarios);
        temporalDataApi.setCreateDate(now);
        temporalDataApi.setProcesado(0);
        _log.info("idTemporal: "+temporalDataApi.getIdDataTemporal());
        TemporalDataApiLocalServiceUtil.updateTemporalDataApi(temporalDataApi);
        return temporalDataApi.getIdDataTemporal();

    }

    public static void proccesTemporalData(long idTemporal) {
        TemporalDataApi temporalDataApi = TemporalDataApiLocalServiceUtil.fetchTemporalDataApi(idTemporal);
        AuditadoDataApi auditado = null;
        CompanyApi companyApi = null;
        AplicationApi aplicationApi = null;
        List<CompClientApplication> listaAplicaciones=new ArrayList<CompClientApplication>();
        Comp company = null;
        Date now = null;
        UserApi userApi=null;
        User user=null;
        long idCompanyAdeplus=0;
        long companyId=PortalUtil.getDefaultCompanyId();
        boolean userCreator=true;
        boolean applicationCreator=true;
        boolean companyCreator=false;
        boolean isKCCompany = false , isKCUser = false;
        Company compania = CompanyLocalServiceUtil.fetchCompany(companyId);
        String path="/es"+PortalUtil.getPathMain();
        String portalURL = PortalUtil.getPortalURL(compania.getVirtualHostname(), PortalUtil.getPortalServerPort(true), true);
        String passwordRandon = "";
        boolean admin = false;
        JSONArray jsonArrayAppsUser=null;
        realm = new RealmBackground();
        JSONArray jsonArrayDataApp=null;
        boolean existCompany=true;
        String apellido="";
        // Utiliza el companyId como necesites


        //Recorremos la tabla temporal

        String validacion=ValidacionDatos.validacionDatosApi(temporalDataApi.getDataApiUsuarios(),temporalDataApi.getDataApiEmpresa());
        now=new Date();
        //Miramos si ya se realizo la auditoria o no de dicha tabla para reutilizarla o crearla
        auditado = AuditadoDataApiLocalServiceUtil.fetchAuditadoDataApi(temporalDataApi.getIdDataTemporal());
        if (Validator.isNull(auditado)){
            auditado=AuditadoDataApiLocalServiceUtil.createAuditadoDataApi(temporalDataApi.getIdDataTemporal());
        }
        if(!Validator.isBlank(validacion)){
            auditado.setProcesado(11);
            auditado.setEvento("Otros");
            auditado.setMensaje(validacion);
            auditado.setProcesadoDate(now);
            AuditadoDataApiLocalServiceUtil.updateAuditadoDataApi(auditado);
            temporalDataApi.setProcesado(1);
            TemporalDataApiLocalServiceUtil.updateTemporalDataApi(temporalDataApi);
            realm.close();
            return;
        }

        if(!temporalDataApi.getTypeEvent().equalsIgnoreCase("alta")&&
                !temporalDataApi.getTypeEvent().equalsIgnoreCase("modificacion")&&
                !temporalDataApi.getTypeEvent().equalsIgnoreCase("baja")){
            auditado.setProcesado(7);
            auditado.setEvento("No existe evento");
            auditado.setMensaje("No existe evento");
            auditado.setProcesadoDate(now);
            AuditadoDataApiLocalServiceUtil.updateAuditadoDataApi(auditado);
            temporalDataApi.setProcesado(1);
            TemporalDataApiLocalServiceUtil.updateTemporalDataApi(temporalDataApi);
            realm.close();
            return;
        }
        //Lo metemos dentro del un try catch para setear el estado si falla la creacion de la compania
        try {
            //Creamos el bean de la compania del json de la api
            companyApi = new CompanyApi(temporalDataApi.getDataApiEmpresa());
            company=CompLocalServiceUtil.getCompByCif(companyApi.getCif());
            if(temporalDataApi.getTypeEvent().equalsIgnoreCase("alta")) {
                if (Validator.isNull(company)) {
                    company = createCompany(0, companyApi.getNombreEmpresa(), companyApi.getCif(),
                            StringPool.BLANK, StringPool.BLANK, StringPool.BLANK, null,
                            StringPool.BLANK, StringPool.BLANK);
                    existCompany=false;
                }
            }else if(temporalDataApi.getTypeEvent().equalsIgnoreCase("modificacion") ||
                    temporalDataApi.getTypeEvent().equalsIgnoreCase("baja")) {
                if (Validator.isNull(company)) {
                    _log.info("Error la empresa no existe y es necesaria para el update/baja del contrato");
                    companyCreator=false;
                    auditado.setProcesado(11);
                    auditado.setEvento("Otros");
                    auditado.setMensaje("Error la empresa no existe y es necesaria para el update/baja del contrato");
                    auditado.setProcesadoDate(now);
                    AuditadoDataApiLocalServiceUtil.updateAuditadoDataApi(auditado);
                    temporalDataApi.setProcesado(1);
                    TemporalDataApiLocalServiceUtil.updateTemporalDataApi(temporalDataApi);
                    realm.close();
                    return;
                }
            }

            idCompanyAdeplus=company.getCompId();
            companyCreator=true;
        } catch (Exception e) {
            /*Si no es posible la creación de la compania y datos asociado adjuntamos el error en el
            apartado observaciones del dato de la tabla api*/
            _log.info("Error al crear empresa");
            companyCreator=false;
            auditado.setProcesado(11);
            auditado.setEvento("Otros");
            auditado.setMensaje("Error en la creacion de la empresa "+ e.getMessage());
            auditado.setProcesadoDate(now);
            AuditadoDataApiLocalServiceUtil.updateAuditadoDataApi(auditado);
            temporalDataApi.setProcesado(1);
            TemporalDataApiLocalServiceUtil.updateTemporalDataApi(temporalDataApi);
            realm.close();
            return;
        }
        try{
            jsonArrayDataApp=JSONFactoryUtil.createJSONArray(temporalDataApi.getDataApiApplicaciones());
            _log.info(jsonArrayDataApp.length());
            for(int posicion = 0; posicion < jsonArrayDataApp.length(); posicion++) {
                aplicationApi = new AplicationApi(temporalDataApi.getDataApiApplicaciones(), posicion);

                if (temporalDataApi.getTypeEvent().equalsIgnoreCase("alta") &&
                        aplicationApi.getTypeEvent().equalsIgnoreCase("alta")) {
                    //Si devuelve false signifa que ocurrio una excepcion
                    applicationCreator = altaCompClientApplication(applicationCreator, auditado, now, aplicationApi,
                            companyApi, idCompanyAdeplus,listaAplicaciones);
                    if(!applicationCreator){
                        //Si da error la creación de aplicaciones debemos eliminar
                        break;
                    }else{
                        auditado.setEvento("Alta contrato");
                        if(!existCompany){
                            auditado.setProcesado(12);
                            auditado.setIdClient(aplicationApi.getIdCliente());
                            auditado.setIdContract(aplicationApi.getIdContrato());
                            auditado.setApp(aplicationApi.getApps());
                            auditado.setProcesadoDate(now);
                            auditado.setMensaje("Alta empresa (CIF, ID cliente, ID contrato, Servicios)");
                        }
                    }

                } else if (temporalDataApi.getTypeEvent().equalsIgnoreCase("modificacion") &&
                        aplicationApi.getTypeEvent().equalsIgnoreCase("modificacion")) {
                    applicationCreator = updateCompClientApplication(applicationCreator, auditado, now, aplicationApi,
                            idCompanyAdeplus, listaAplicaciones);
                    if(!applicationCreator){
                        break;
                    }else{
                        auditado.setEvento("Anexo contrato");
                        auditado.setProcesado(15);
                        auditado.setMensaje("Modificación de servicios");
                        auditado.setIdClient(aplicationApi.getIdCliente());
                        auditado.setIdContract(aplicationApi.getIdContrato());
                        auditado.setApp(aplicationApi.getApps());
                        auditado.setProcesadoDate(now);
                        AuditadoDataApiLocalServiceUtil.updateAuditadoDataApi(auditado);
                    }
                } else if (temporalDataApi.getTypeEvent().equalsIgnoreCase("baja") &&
                        aplicationApi.getTypeEvent().equalsIgnoreCase("baja")) {
                    applicationCreator = bajaCompClientApplication(applicationCreator, auditado, now, aplicationApi,
                            idCompanyAdeplus);
                    if(!applicationCreator){
                        break;
                    }else{
                        auditado.setEvento("Alta contrato");
                    }
                }
            }
            if(!applicationCreator){
                if (temporalDataApi.getTypeEvent().equalsIgnoreCase("alta")) {
                    //Si es un alta debemos eliminar todas las anteriormente creadas dentro del for para evitar nuevos duplicados
                    for(CompClientApplication aplicacion:listaAplicaciones) {
                        CompClientApplicationLocalServiceUtil.deleteCompClientApplication(aplicacion);
                    }
                    if(!existCompany){
                        CompLocalServiceUtil.deleteComp(company);
                    }
                }
            }
        }catch(Exception e){
            _log.info("Error al crear las aplicaciones para la empresa "+e.getMessage());
            applicationCreator=false;
            auditado.setProcesado(11);
            auditado.setEvento("Otros");
            auditado.setMensaje("Error en la creacion/modificacion/eliminacion de las aplicaciones de la empresa "+e.getMessage());
            auditado.setProcesadoDate(now);
            AuditadoDataApiLocalServiceUtil.updateAuditadoDataApi(auditado);
            temporalDataApi.setProcesado(1);
            TemporalDataApiLocalServiceUtil.updateTemporalDataApi(temporalDataApi);
            realm.close();
            if(!existCompany){
                CompLocalServiceUtil.deleteComp(company);
            }
            return;

        }

        //Solo se puede dar de alta o modificar un usuario si asi lo requiere la accion
        try{
            if(temporalDataApi.getTypeEvent().equalsIgnoreCase("alta") || temporalDataApi.getTypeEvent().equalsIgnoreCase("modificacion")) {
                userApi = new UserApi(temporalDataApi.getDataApiUsuarios());
                if(Validator.isBlank(userApi.getLastName())){
                    apellido=companyApi.getCif();
                }else{
                    apellido=userApi.getLastName();
                }
                user = UserLocalServiceUtil.fetchUserByEmailAddress(companyId, userApi.getEmail());
                if (Validator.isNotNull(user)) {
                    admin = true;
                    //COMENTAR CON RAUL, DENTRO DEL UPDATE SE REALIZA UN UPDATE EN KC
                    user = AdeplusUserUtilMulti.updateUserApi(
                            companyId,
                            user.getUserId(), idCompanyAdeplus,
                            userApi.getUsername(), apellido, StringPool.BLANK, userApi.getEmail(),
                            StringPool.BLANK, admin, null,
                            LocaleUtil.fromLanguageId("es_ES"));
                } else {
                    passwordRandon = AdeplusUserUtilMulti.generatePasswordRandon();
                    admin = true;
                    user = AdeplusUserUtilMulti.createUser(
                            0,
                            companyId,
                            portalURL,
                            path,
                            userApi.getUsername(), apellido, StringPool.BLANK, userApi.getEmail(), passwordRandon,
                            StringPool.BLANK, admin, null, idCompanyAdeplus,
                            LocaleUtil.fromLanguageId("es_ES"));
                    //MANDAMOS POR CORREO EL WELCOME PACK
                    UserMailing.userCreate(user, idCompanyAdeplus, passwordRandon, true);
                }
                userCreator = true;
                //Una vez creado el usuario creamos las lista de permisos a las aplicaciones
                try {
                    _log.info("JsonArra: " + temporalDataApi.getDataApiApplicaciones());
                    for(CompClientApplication compApp:listaAplicaciones) {
                        _log.info(compApp.getClientId());
                        AdeplusPermissionUserUtil.saveUserPermissionApplicationsMultiApi(user, idCompanyAdeplus,compApp.getContractId(),compApp.getClientId(), compApp.getApplicationId());
                    }
                } catch (Exception e) {
                    userCreator=false;
                }


            }

        }catch(Exception e){
            _log.info("Error usuarios: "+e.getMessage());
            userCreator=false;
            auditado.setProcesado(11);
            auditado.setEvento("Otros");
            auditado.setMensaje("Error al crear los usuarios: "+e.getMessage());
            auditado.setProcesadoDate(now);
            AuditadoDataApiLocalServiceUtil.updateAuditadoDataApi(auditado);
            temporalDataApi.setProcesado(1);
            TemporalDataApiLocalServiceUtil.updateTemporalDataApi(temporalDataApi);
            realm.close();
            if(!existCompany){
                CompLocalServiceUtil.deleteComp(company);
            }
            return;
        }

        //Si ningun valor es nulo procedemos a enviarlo a keycloak
        _log.info("companyCreator: "+companyCreator+" applicationCreator: "+applicationCreator+" userCreator: "+userCreator);
        if(companyCreator && applicationCreator && userCreator){
            //company
            isKCCompany = AdeplusKeycloakMultiBackgroundUtil.createCompanyKeycloak(idCompanyAdeplus,
                    company.getName(),
                    company.getCif(),
                    company.getDescription(),
                    company.getObservations(),
                    company.getDeleteDate(),
                    realm);


            _log.info("isKCCompany : " + isKCCompany);
            isKCUser = false;
            //user
            if(isKCCompany && (temporalDataApi.getTypeEvent().equalsIgnoreCase("alta") || (temporalDataApi.getTypeEvent().equalsIgnoreCase("modificacion"))) ){
                List<UserCompany> userCompanyList = UserCompanyLocalServiceUtil.getUsersFromCompany(idCompanyAdeplus);
                JSONArray jsonAppsResult = null;
                JSONObject jsonContrato = null;
                List<UserApplicationClient> userApps = null;
                CompClientApplication compApp = null;
                try{
                    for(UserCompany userCompany:userCompanyList) {
                        if (Validator.isNotNull(userCompany) ) {
                            jsonAppsResult = JSONFactoryUtil.createJSONArray();
                            userApps = UserApplicationClientLocalServiceUtil.getApplicationsFromUser(user.getUserId(), idCompanyAdeplus);
                            if (Validator.isNotNull(userApps) && userApps.size() > 0) {
                                for (UserApplicationClient u : userApps) {
                                    jsonContrato = JSONFactoryUtil.createJSONObject();
                                    jsonContrato.put("cliente", String.valueOf(u.getClientId()));
                                    jsonContrato.put("contrato", String.valueOf(u.getContractId()));
                                    compApp = CompClientApplicationLocalServiceUtil.fetchCompClientApplication(u.getEmpresaId());
                                    if (Validator.isNotNull(compApp)) {
                                        jsonContrato.put("nombre", compApp.getDescription());
                                    }
                                    jsonContrato.put("apps", JSONFactoryUtil.createJSONArray(u.getApplicationId()));

                                    jsonAppsResult.put(jsonContrato);
                                }
                            }
                            _log.info("user" + user.getEmailAddress() + " NO es admin");
                            _log.info("JsonArray to send" + jsonAppsResult);
                            _log.info("JsonArray to send tostring" + jsonAppsResult.toString());
                            AdeplusUserUtilMulti.createUserKeycloak(idCompanyAdeplus, user, StringPool.BLANK, StringPool.BLANK, passwordRandon, "", true, false, jsonAppsResult);


                        }
                    }
                    isKCUser=true;
                }catch(Exception e){
                    auditado.setProcesado(11);
                    auditado.setEvento("Otros");
                    auditado.setMensaje("Error usuario en KC: '" + userApi.getEmail() + "' para la empresa: '" + companyApi.getNombreEmpresa() + "'");
                    AuditadoDataApiLocalServiceUtil.updateAuditadoDataApi(auditado);
                    temporalDataApi.setProcesado(1);
                    TemporalDataApiLocalServiceUtil.updateTemporalDataApi(temporalDataApi);
                    realm.close();
                    return;
                }
                        /*
                            isKCUser = AdeplusKeycloakMultiBackgroundUtil.createUserKeycloak(
                                idCompanyAdeplus,
                                user,
                                StringPool.BLANK,
                                StringPool.BLANK,
                                passwordRandon,
                                null,
                                admin,
                                realm);
                         */
            }

                _log.info("isKCUser : " + isKCUser);


        }else{
            isKCUser=true;
        }
        auditado.setProcesadoDate(now);
        if(!isKCCompany || !isKCUser){
            auditado.setProcesado(11);
            auditado.setEvento("Otros");
            if(!isKCCompany){
                auditado.setMensaje("Error empresa en KC: " + companyApi.getNombreEmpresa() + "'" );
            }else{
                auditado.setMensaje("Error usuario en KC: '" + userApi.getEmail() + "' para la empresa: '" + companyApi.getNombreEmpresa() + "'");
            }
            AuditadoDataApiLocalServiceUtil.updateAuditadoDataApi(auditado);
        }

        //Una vez procesado el dato lo agregamos como ya procesado
        temporalDataApi.setProcesado(1);
        TemporalDataApiLocalServiceUtil.updateTemporalDataApi(temporalDataApi);

        realm.close();

    }



    private static boolean altaCompClientApplication(boolean applicationCreator, AuditadoDataApi auditado, Date now, AplicationApi aplicationApi,
                                                     CompanyApi companyApi,long idCompanyAdeplus, List<CompClientApplication> listaAplicaciones) throws PortalException {
        boolean asociadoAlCif=true;
        _log.info(CompClientApplicationLocalServiceUtil.findByContractId(aplicationApi.getIdContrato()));
        _log.info(CompClientApplicationLocalServiceUtil.findByClient(aplicationApi.getIdCliente()));
        CompClientApplication aplicacionesCompany;
        if(CompClientApplicationLocalServiceUtil.findByContractId(aplicationApi.getIdContrato())){
            applicationCreator=false;
            auditado.setProcesado(1);
            auditado.setEvento("Alta contrato");
            auditado.setMensaje("Se ha tratado de dar de alta un ID contrato que ya existe en la suite");
            auditado.setIdClient(aplicationApi.getIdCliente());
            auditado.setIdContract(aplicationApi.getIdContrato());
            auditado.setApp(aplicationApi.getApps());
            auditado.setProcesadoDate(now);
            AuditadoDataApiLocalServiceUtil.updateAuditadoDataApi(auditado);


        }else if(!CompClientApplicationLocalServiceUtil.findByClient(aplicationApi.getIdCliente())){
            //Si no existe ningun con esa aplicacion lo creamos
            String nombreCompleto=companyApi.getNombreEmpresa();
            if(!Validator.isBlank(aplicationApi.getNombre())){
                nombreCompleto=nombreCompleto+" - "+aplicationApi.getNombre();
            }
            aplicacionesCompany = AdeplusClientUtil.createClient(idCompanyAdeplus, aplicationApi.getIdCliente(),aplicationApi.getIdContrato(),aplicationApi.getRealApps(aplicationApi.getApps()),companyApi.getCif(), nombreCompleto,nombreCompleto);
            auditado.setProcesado(13);
            auditado.setEvento("Alta contrato");
            auditado.setMensaje("Alta ID cliente (ID cliente, ID contrato, Servicios)");
            auditado.setIdClient(aplicationApi.getIdCliente());
            auditado.setIdContract(aplicationApi.getIdContrato());
            AuditadoDataApiLocalServiceUtil.updateAuditadoDataApi(auditado);
            listaAplicaciones.add(aplicacionesCompany);
            return true;

        }else{
            //Si existe comprobamos que solo este asociado a la empresa con nuestro cif, de otra forma indicamos error
            List<CompClientApplication> listaCompClientApp=CompClientApplicationLocalServiceUtil.getAllCompByIdClient(aplicationApi.getIdCliente());
            for(CompClientApplication aplicaciones:listaCompClientApp){
                if(!CompLocalServiceUtil.getComp(aplicaciones.getCompId()).getCif().equalsIgnoreCase(companyApi.getCif())){
                    asociadoAlCif=false;
                }
            }
            if(!asociadoAlCif){
                applicationCreator=false;
                auditado.setProcesado(2);
                auditado.setEvento("Alta contrato");
                auditado.setMensaje("El ID de cliente en la suite está asociado a un CIF distinto al que viaja en el json");
                auditado.setIdClient(aplicationApi.getIdCliente());
                auditado.setIdContract(aplicationApi.getIdContrato());
                auditado.setApp(aplicationApi.getApps());
                auditado.setProcesadoDate(now);
                AuditadoDataApiLocalServiceUtil.updateAuditadoDataApi(auditado);

            }else{
                listaCompClientApp=CompClientApplicationLocalServiceUtil.getClientsApplicationsByCompAndClient(idCompanyAdeplus,aplicationApi.getIdCliente());
                JSONArray jsonArray=JSONFactoryUtil.createJSONArray(aplicationApi.getApps());
                Set<Integer> appIdSet = new HashSet<>();
                boolean existeEnOtroArray=false;
                for (int i = 0; i < jsonArray.length(); i++) {
                    int appId1 = jsonArray.getJSONObject(i).getInt("appId");
                    appIdSet.add(appId1);
                }
                JSONArray jsonArray1=null;
                for(CompClientApplication aplicaciones:listaCompClientApp) {
                    jsonArray1 = JSONFactoryUtil.createJSONArray(aplicaciones.getApplicationId());
                    for (int i = 0; i < jsonArray1.length(); i++) {
                        int appId2 = jsonArray1.getJSONObject(i).getInt("appId");
                        if (appIdSet.contains(appId2)) {
                            existeEnOtroArray = true;
                            break;
                        }
                    }
                    if (existeEnOtroArray){
                        break;
                    }
                }

                if(existeEnOtroArray){
                    applicationCreator=false;
                    auditado.setProcesado(3);
                    auditado.setEvento("Alta contrato");
                    auditado.setMensaje("Algunos servicios del ID contrato a dar de alta, coincide con los servicios de otro ID de contrato de ese ID de cliente");
                    auditado.setIdClient(aplicationApi.getIdCliente());
                    auditado.setIdContract(aplicationApi.getIdContrato());
                    auditado.setApp(aplicationApi.getApps());
                    auditado.setProcesadoDate(now);
                    AuditadoDataApiLocalServiceUtil.updateAuditadoDataApi(auditado);

                }

            }
            if(applicationCreator){
                String nombreCompleto=companyApi.getNombreEmpresa();
                if(!Validator.isBlank(aplicationApi.getNombre())){
                    nombreCompleto=nombreCompleto+" - "+aplicationApi.getNombre();
                }
                aplicacionesCompany = AdeplusClientUtil.createClient(idCompanyAdeplus, aplicationApi.getIdCliente(),aplicationApi.getIdContrato(),aplicationApi.getRealApps(aplicationApi.getApps()),companyApi.getCif(), nombreCompleto, nombreCompleto);
                auditado.setProcesado(14);
                auditado.setMensaje("Alta ID contrato (ID contrato, Servicios)");
                auditado.setIdClient(aplicationApi.getIdCliente());
                auditado.setIdContract(aplicationApi.getIdContrato());
                auditado.setApp(aplicationApi.getApps());
                auditado.setProcesadoDate(now);
                AuditadoDataApiLocalServiceUtil.updateAuditadoDataApi(auditado);
                listaAplicaciones.add(aplicacionesCompany);
            }

        }
        return applicationCreator;

    }

    private static boolean updateCompClientApplication(boolean applicationCreator, AuditadoDataApi auditado, Date now, AplicationApi aplicationApi,
                                                       long idCompanyAdeplus,List<CompClientApplication> listaAplicaciones ) {
        if(!CompClientApplicationLocalServiceUtil.findByContractId(aplicationApi.getIdContrato())){
            applicationCreator=false;
            auditado.setProcesado(4);
            auditado.setEvento("Anexo de contrato");
            auditado.setMensaje("No existe el ID de contrato en la suite");
            auditado.setIdClient(aplicationApi.getIdCliente());
            auditado.setIdContract(aplicationApi.getIdContrato());
            auditado.setApp(aplicationApi.getApps());
            auditado.setProcesadoDate(now);
            AuditadoDataApiLocalServiceUtil.updateAuditadoDataApi(auditado);

        }else if(!CompClientApplicationLocalServiceUtil.findByContractAndClient(aplicationApi.getIdContrato(),aplicationApi.getIdCliente())){
            applicationCreator=false;
            auditado.setProcesado(5);
            auditado.setEvento("Anexo de contrato");
            auditado.setMensaje("El ID de contrato en la suite está asociado a un ID cliente distinto al que viaja en el json");
            auditado.setIdClient(aplicationApi.getIdCliente());
            auditado.setIdContract(aplicationApi.getIdContrato());
            auditado.setApp(aplicationApi.getApps());
            auditado.setProcesadoDate(now);
            AuditadoDataApiLocalServiceUtil.updateAuditadoDataApi(auditado);
        }else{
            CompClientApplication aplicacionesCompany = CompClientApplicationLocalServiceUtil.getClientByCompAndClientAndContract(idCompanyAdeplus, aplicationApi.getIdCliente(), aplicationApi.getIdContrato());
            if(Validator.isNotNull(aplicacionesCompany)){
                aplicacionesCompany.setApplicationId(aplicationApi.getRealApps(aplicationApi.getApps()));
                CompClientApplicationLocalServiceUtil.updateCompClientApplication(aplicacionesCompany);
                listaAplicaciones.add(aplicacionesCompany);
            }else{
                applicationCreator=false;
                auditado.setProcesado(6);
                auditado.setEvento("Anexo de contrato");
                auditado.setMensaje("El ID de cliente en la suite está asociado a un CIF distinto al que viaja en el json");
                auditado.setIdClient(aplicationApi.getIdCliente());
                auditado.setIdContract(aplicationApi.getIdContrato());
                auditado.setApp(aplicationApi.getApps());
                auditado.setProcesadoDate(now);
                AuditadoDataApiLocalServiceUtil.updateAuditadoDataApi(auditado);
            }

        }

        return applicationCreator;

    }


    private static boolean bajaCompClientApplication(boolean applicationCreator, AuditadoDataApi auditado, Date now, AplicationApi aplicationApi,
                                                     long idCompanyAdeplus ) throws PortalException {
        boolean bajaEmpresa=false;
        if(!CompClientApplicationLocalServiceUtil.findByContractId(aplicationApi.getIdContrato())){
            applicationCreator=false;
            auditado.setProcesado(8);
            auditado.setEvento("Baja de contrato");
            auditado.setMensaje("No existe el ID de contrato en la suite");
            auditado.setIdClient(aplicationApi.getIdCliente());
            auditado.setIdContract(aplicationApi.getIdContrato());
            auditado.setApp(aplicationApi.getApps());
            auditado.setProcesadoDate(now);
            AuditadoDataApiLocalServiceUtil.updateAuditadoDataApi(auditado);

        }else if(!CompClientApplicationLocalServiceUtil.findByContractAndClient(aplicationApi.getIdContrato(),aplicationApi.getIdCliente())){
            applicationCreator=false;
            auditado.setProcesado(9);
            auditado.setEvento("Baja de contrato");
            auditado.setMensaje("El ID de contrato en la suite está asociado a un ID cliente distinto al que viaja en el json");
            auditado.setIdClient(aplicationApi.getIdCliente());
            auditado.setIdContract(aplicationApi.getIdContrato());
            auditado.setApp(aplicationApi.getApps());
            auditado.setProcesadoDate(now);
            AuditadoDataApiLocalServiceUtil.updateAuditadoDataApi(auditado);
        }else{
            CompClientApplication aplicacionesCompany = CompClientApplicationLocalServiceUtil.getClientByCompAndClientAndContract(idCompanyAdeplus, aplicationApi.getIdCliente(), aplicationApi.getIdContrato());
            Application aplicationIso=ApplicationLocalServiceUtil.getApplicationByAlias(isoPlusAlias);

            CompClientApplication aplicacionesCompanyIso = CompClientApplicationLocalServiceUtil.getCompClientAppByIdEmpresaIdApp(aplicacionesCompany.getEmpresaId(),aplicationIso.getApplicationId());
            if(Validator.isNotNull(aplicacionesCompanyIso)){
                applicationCreator=false;
                auditado.setProcesado(11);
                auditado.setEvento("Baja de contrato");
                auditado.setMensaje("Se intento dar de baja un cliente contrato de ISO +");
                auditado.setIdClient(aplicationApi.getIdCliente());
                auditado.setIdContract(aplicationApi.getIdContrato());
                auditado.setApp(aplicationApi.getApps());
                auditado.setProcesadoDate(now);
                AuditadoDataApiLocalServiceUtil.updateAuditadoDataApi(auditado);
            }
            if(Validator.isNull(aplicacionesCompany)){
                applicationCreator=false;
                auditado.setProcesado(10);
                auditado.setEvento("Baja de contrato");
                auditado.setMensaje("El ID de cliente en la suite está asociado a un CIF distinto al que viaja en el json");
                auditado.setIdClient(aplicationApi.getIdCliente());
                auditado.setIdContract(aplicationApi.getIdContrato());
                auditado.setApp(aplicationApi.getApps());
                auditado.setProcesadoDate(now);
                AuditadoDataApiLocalServiceUtil.updateAuditadoDataApi(auditado);
            }else{
                //damos de baja la empresa y eliminamos el cliente contrato empresa si solo existe ese cliente contrato
                if(CompClientApplicationLocalServiceUtil.getClientsByCompanyId(idCompanyAdeplus).size()==1) {
                    Comp compania = CompLocalServiceUtil.getComp(idCompanyAdeplus);
                    compania.setDeleteDate(now);
                    CompLocalServiceUtil.updateComp(compania);
                    bajaEmpresa=true;
                    auditado.setEvento("Baja contrato");
                    auditado.setIdClient(aplicationApi.getIdCliente());
                    auditado.setIdContract(aplicationApi.getIdContrato());
                    auditado.setProcesado(18);
                    auditado.setMensaje("Baja empresa (CIF, ID cliente, ID contrato, Servicios)");
                    AuditadoDataApiLocalServiceUtil.updateAuditadoDataApi(auditado);
                }
                //Eliminamos tanto los usuariosClienteContratos como companyClientesContratos de ese cliente contrato y empresa
                aplicacionesCompany.setDeleteDate(now);
                CompClientApplicationLocalServiceUtil.updateCompClientApplication(aplicacionesCompany);
                List<UserApplicationClient> listaUsuarios=UserApplicationClientLocalServiceUtil.findByCompClientContractId(
                        idCompanyAdeplus,aplicationApi.getIdCliente(),aplicationApi.getIdContrato());
                for(UserApplicationClient usuario:listaUsuarios){
                    usuario.setDeleteDate(new Date());
                    UserApplicationClientLocalServiceUtil.updateUserApplicationClient(usuario);
                    if(UserApplicationClientLocalServiceUtil.getApplicationsFromUser(usuario.getUserId(),idCompanyAdeplus).size()==0){
                        UserCompany userCompany=UserCompanyLocalServiceUtil.getUserCompany(usuario.getUserId(),idCompanyAdeplus);
                        userCompany.setUserEndDate(new Date());
                    }
                }

                if(!bajaEmpresa) {
                    if (CompClientApplicationLocalServiceUtil.findByClientCompany(idCompanyAdeplus, aplicationApi.getIdCliente())) {
                        auditado.setEvento("Baja contrato");
                        auditado.setProcesado(16);
                        auditado.setIdClient(aplicationApi.getIdCliente());
                        auditado.setIdContract(aplicationApi.getIdContrato());
                        auditado.setMensaje("Baja ID contrato (ID contrato, Servicios)");
                        AuditadoDataApiLocalServiceUtil.updateAuditadoDataApi(auditado);
                    } else {
                        auditado.setEvento("Baja contrato");
                        auditado.setProcesado(17);
                        auditado.setIdClient(aplicationApi.getIdCliente());
                        auditado.setIdContract(aplicationApi.getIdContrato());
                        auditado.setMensaje("Baja ID cliente (ID cliente, ID contrato, Servicios)");
                        AuditadoDataApiLocalServiceUtil.updateAuditadoDataApi(auditado);
                    }
                }

            }

        }

        return applicationCreator;

    }



}

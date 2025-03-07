package com.canal.etico.liferay.portlet.mailing.web.v2.mail;

import com.adeplus.liferay.portlet.suite.manager.model.*;
import com.adeplus.liferay.portlet.suite.manager.service.*;
import com.adeplus.liferay.portlet.suite.manager.service.persistence.UserApplicationClientPK;
import com.adeplus.liferay.portlet.suite.manager.service.persistence.UserApplicationPK;
import com.adeplus.liferay.portlet.suite.manager.service.persistence.UserCompanyPK;
import com.canal.etico.liferay.portlet.commons.web.constants.CanalEticoCommonsPortletKeys;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.petra.string.CharPool;
import com.liferay.portal.kernel.backgroundtask.*;


import com.canal.etico.liferay.portlet.mailing.web.v2.constants.MailingPortletKeys;
import com.canal.etico.liferay.portlet.mailing.web.v2.constants.MailingTemplateImageKeys;
import com.canal.etico.liferay.portlet.mailing.web.v2.util.PrevingMailUtil;
import com.canal.etico.liferay.portlet.mailing.web.v2.util.PrevingTemplateUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.backgroundtask.display.BackgroundTaskDisplay;
import com.liferay.portal.kernel.backgroundtask.display.BackgroundTaskDisplayFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.*;

import com.canal.etico.v2.liferay.portlet.canal.manager.model.Comunicado;
import com.canal.etico.v2.liferay.portlet.canal.manager.service.ComunicadoLocalServiceUtil;
import org.osgi.service.component.annotations.Component;

import java.io.*;
import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.*;


@Component(
        immediate = true,
        property = {"background.task.executor.class.name=com.canal.etico.liferay.portlet.mailing.web.v2.mail.TaskComunicados"},
        // Without this property osgi will not register this as background executor/handler
        service = BackgroundTaskExecutor.class
)
public class TaskComunicados  implements BackgroundTaskExecutor {

    private static Log _log = LogFactoryUtil.getLog(TaskComunicados.class);
    private static boolean itsNewVersion = CanalEticoCommonsPortletKeys.ITS_NEW_VERSION;
    private static String[] imageKeys = {"[$ADEPLUS_LOGO_SRC$]",
            "[$ADEPLUS_LOGO_FOOTER_SRC$]",
            "[$FACEBOOK_LOGO_SRC$]",
            "[$TWITTER_LOGO_SRC$]",
            "[$YOUTUBE_LOGO_SRC$]",
            "[$LINKEDIN_LOGO_SRC$]",
            "[$SUBTITLE_BACKGROUND_SRC$]"};

    private static String[] imageBase64s = {
            MailingTemplateImageKeys.MAIL_TEMPLATE_IMAGE_LOGO,
            MailingTemplateImageKeys.MAIL_TEMPLATE_IMAGE_LOGO_FOOTER,
            MailingTemplateImageKeys.MAIL_TEMPLATE_IMAGE_LOGO_FACEBOOK,
            MailingTemplateImageKeys.MAIL_TEMPLATE_IMAGE_LOGO_TWITTER,
            MailingTemplateImageKeys.MAIL_TEMPLATE_IMAGE_LOGO_YOUTUBE,
            MailingTemplateImageKeys.MAIL_TEMPLATE_IMAGE_LOGO_LINKEDIN,
            MailingTemplateImageKeys.MAIL_TEMPLATE_IMAGE_BACKGROUND_TITLE};





    @Override
    public BackgroundTaskExecutor clone() {
        return this;
    }

    @Override
    public BackgroundTaskResult execute(BackgroundTask backgroundTask) throws Exception {

        Map taskContextMap = backgroundTask.getTaskContextMap();
        int action = (int) taskContextMap.get("action"); //Que cominicado queremos enviar

        switch(action){
            case CanalEticoCommonsPortletKeys.EMAIL_NUEVO_COMUNICADO:
                comunicadoNuevo(taskContextMap);
                break;
            case CanalEticoCommonsPortletKeys.EMAIL_NUEVA_INFORMACION:
                anadirInfo(taskContextMap);
                break;
            case CanalEticoCommonsPortletKeys.EMAIL_PRORROGADO:
                comunicadoProrrogado(taskContextMap);
                break;
            case CanalEticoCommonsPortletKeys.EMAIL_ASIGNAR_CONSULTOR:
                comunicadoAsignado(taskContextMap);
                break;
            case CanalEticoCommonsPortletKeys.EMAIL_CAMBIO_ESTADO:
                comunicadoCambioEstado(taskContextMap);
                break;
            case CanalEticoCommonsPortletKeys.EMAIL_FINALIZAR:
                comunicadoFinalizar(taskContextMap);
                break;
            case CanalEticoCommonsPortletKeys.EMAIL_AVISO_CADUCIDAD:
                break;
            case CanalEticoCommonsPortletKeys.EMAIL_COMUNICACION_CADUCADA:
                comunicadoSchedulerFinalizar(taskContextMap);
                break;

        }

        return BackgroundTaskResult.SUCCESS;
    }

    /*@see Manda un comunicado al usuario si tiene email y otro al administrador o usuario responsable */
    public void comunicadoNuevo(Map map) throws PortalException, UnsupportedEncodingException {

        String codigo = (String) map.get("codigo");
        String categoria = (String) map.get("categoria");
        String email = (String) map.get("email");
        String asunto = (String) map.get("asunto");
        String consulta = (String) map.get("consulta");

        String url = (String) map.get("url");
        String portalURL = (String) map.get("portalURL");
        Long companiaId = (Long) map.get("companiaId");
        String registroId = (String) map.get("registroId");

        Long comunicadoId = (Long) map.get("comunicadoId");

        Comunicado comunicado = ComunicadoLocalServiceUtil.fetchComunicado(comunicadoId);

        long userID = (long) map.get("userID");
        User user = UserLocalServiceUtil.getUser(userID);

        File fichero0 = null;
        String idioma= (String) map.get("idioma");

        String resourceName = "content/Language";
        Locale locale=null;
        if(idioma.equalsIgnoreCase("es_ES")){
            locale=new Locale("es","ES");
        }else if(idioma.equalsIgnoreCase("ca_ES")){
            locale=new Locale("ca","ES");
        }else{
            locale=new Locale("en","US");
        }
        ResourceBundle bundle = ResourceBundle.getBundle(resourceName, locale);
        //String subject = new String(bundle.getString("mailing.mail.subject.comunicado.nuevo.usuario").getBytes("ISO-8859-1"), "UTF-8")  + " " + codigo;;
        String subject = new String(bundle.getString("mailing.mail.subject.comunicado.nuevo.admin").getBytes("ISO-8859-1"), "UTF-8")  + " " + codigo;;


        String body=StringPool.BLANK;
        if(!email.isEmpty() && comunicado.getNotificacion().contains("EMAIL") ) { //Email usuario
            //body = PrevingTemplateUtil.getTemplate(user.getLocale(), MailingPortletKeys.MAIL_TEMPLATE_COMUNICADO_NUEVO_USER);
            body = PrevingTemplateUtil.getTemplate(locale, "templates/ComunicacionToUsuarioTemplate_v2");

            _log.info("(D) MailingPortletKeys.MAIL_TEMPLATE_COMUNICADO_NUEVO_USER: " + MailingPortletKeys.MAIL_TEMPLATE_COMUNICADO_NUEVO_USER);
            body = PrevingTemplateUtil.replaceImageBase64ToTemplate(body, imageKeys, imageBase64s);

            body = StringUtil.replace(body,
                    new String[]{"[$COMUNICADO_IDENTIFICADOR$]", "[$CATEGORY_MESSAGE$]", "[$SUBJECT_MESSAGE$]", "[$FILE_NAME$]", "[$BODY_MESSAGE$]","[$HOST$]", "[$REGISTRO_ID$]","[$URL$]","[$IDCOM$]","[$COMPID$]"},
                    new String[]{codigo, categoria, asunto, "fichero0", consulta, portalURL, registroId, portalURL,String.valueOf(comunicadoId),String.valueOf(companiaId)});
            PrevingMailUtil.sendMailWithAttachment(email,email, subject, body, fichero0, "filename");
        }


        //Email al administrador
        //subject = new String(bundle.getString("mailing.mail.subject.comunicado.nuevo.admin").getBytes("ISO-8859-1"), "UTF-8")  + " " + codigo;;
        subject = new String(bundle.getString("mailing.mail.subject.comunicado.nuevo.usuario").getBytes("ISO-8859-1"), "UTF-8")  + " " + codigo;;



        body = PrevingTemplateUtil.getTemplate(locale, MailingPortletKeys.MAIL_TEMPLATE_COMUNICADO_NUEVO_ADMIN);
        //body = PrevingTemplateUtil.getTemplate(user.getLocale(), "templates/ComunicacionToAdministratorTemplate_v2");

        body = PrevingTemplateUtil.replaceImageBase64ToTemplate(body, imageKeys, imageBase64s);

        body = StringUtil.replace(body,
                new String[]{"[$COMUNICADO_IDENTIFICADOR$]", "[$CATEGORY_MESSAGE$]", "[$SUBJECT_MESSAGE$]", "[$FILE_NAME$]", "[$BODY_MESSAGE$]","[$HOST$]","[$REGISTRO_ID$]"},
                new String[]{codigo, categoria, asunto, "fichero0", consulta, portalURL, registroId });


        Application app = ApplicationLocalServiceUtil.getApplicationByAlias("CANAL_DENUNCIAS");
        if(!itsNewVersion) {
            //List<UserCompany> adminUsers = UserCompanyLocalServiceUtil.getUsersFromCompany(companiaId);
            List<UserCompany> adminUsers =  GetAllUsersByCompanyAnddApp.getAllUserActiveByAppCanal(companiaId);
            for (UserCompany adminUser : adminUsers) {
                if (hasAccesoCompleto(adminUser, Long.valueOf(companiaId))) {
                    PrevingMailUtil.sendMailWithAttachment(adminUser.getEmail(), adminUser.getEmail(), subject, body, fichero0, "filename");
                }
            }
        }else{
            long idRoleCompleto = 0;
            List<com.adeplus.liferay.portlet.suite.manager.model.Role> allRoles = com.adeplus.liferay.portlet.suite.manager.service.RoleLocalServiceUtil.getRoleByApplicationId(app.getApplicationId());
            for(com.adeplus.liferay.portlet.suite.manager.model.Role role: allRoles ){
                if(role.getAlias().equalsIgnoreCase("Acceso completo")){
                    //isHasApp = true;
                    idRoleCompleto = role.getRoleId();
                    break;
                }
            }
            List<UserCompany> adminUsers=UserApplicationClientLocalServiceUtil.getUsersByEmpresaIdAndApplicationWithLicense(
                    app.getApplicationId(),idRoleCompleto,Long.valueOf(companiaId));
            for (UserCompany adminUser : adminUsers) {
                    PrevingMailUtil.sendMailWithAttachment(adminUser.getEmail(), adminUser.getEmail(), subject, body, fichero0, "filename");
                }
        }
        map.clear();
        map = null;
    }

    private boolean hasAccesoCompleto(UserCompany user, long compId) throws PortalException {
        boolean isHasApp = false;
        Application app = ApplicationLocalServiceUtil.getApplicationByAlias("CANAL_DENUNCIAS");

        /*UserApplicationPK pk = new UserApplicationPK(user.getUserId(), compId, app.getApplicationId());
        UserApplication userApp = UserApplicationLocalServiceUtil.getUserApplication(pk);*/


        UserApplicationClientPK pk = new UserApplicationClientPK();
        pk.setEmpresaId(compId);
        pk.setUserId(user.getUserId());
        UserApplicationClient userApp =  UserApplicationClientLocalServiceUtil.getUserApplicationClient(pk);


        long idRoleCompleto = 0;
        if(Validator.isNotNull(userApp)){ //tiene la app
            List<com.adeplus.liferay.portlet.suite.manager.model.Role> allRoles = com.adeplus.liferay.portlet.suite.manager.service.RoleLocalServiceUtil.getRoleByApplicationId(app.getApplicationId());
            for(com.adeplus.liferay.portlet.suite.manager.model.Role role: allRoles ){
                if(role.getAlias().equalsIgnoreCase("Acceso completo")){
                    //isHasApp = true;
                    idRoleCompleto = role.getRoleId();
                    break;
               }
            }
            try {
                com.adeplus.liferay.portlet.suite.manager.model.UserRole userRoles = UserRoleLocalServiceUtil.getUserRole(user.getUserId(), compId, idRoleCompleto);
                if(Validator.isNotNull(userRoles)) isHasApp = true;
            }catch (Exception e){
                isHasApp = false;
            }

        }
        return isHasApp;
    }


    // Comunicante que desea estar informado/ usuarios acceso completo y restringido con comunicación asignada.
    public void anadirInfo(Map map) throws PortalException, UnsupportedEncodingException {
        String codigo = (String) map.get("codigo");

        String asunto = (String) map.get("asunto");
        String consulta = (String) map.get("consulta");

        String url = (String) map.get("url");
        String portalURL = (String) map.get("portalURL");
        String registroId = (String) map.get("registroId");
        String descripcion = (String) map.get("descripcion");

        String emailConsultor = StringPool.BLANK;
        String[] gestoresId = {};
        if( map.containsKey("gestoresId")  && !((String) map.get("gestoresId")).isEmpty()){

        }
        String email = StringPool.BLANK;
        Long companiaId = 0L;
        List<Comunicado> lstComunicados = ComunicadoLocalServiceUtil.getAllComunicadosByCodigo(codigo);
        Comunicado comunicado = null;
        if(lstComunicados.size() >= 1){
            comunicado = lstComunicados.get(0);
            companiaId = comunicado.getCompId();
            email = comunicado.getEmail();
            if(!comunicado.getGestorId().isEmpty() && !comunicado.getGestorId().equals("0")){
                gestoresId = comunicado.getGestorId().split(",");
            }

        }
        long userID = (long) map.get("userID");
        User user = UserLocalServiceUtil.getUser(userID);

        //String subject = LanguageUtil.get(ResourceBundle.getBundle("content/Language", user.getLocale()), "mailing.mail.subject.comunicado.anadir") + " " +codigo;
        String resourceName = "content/Language";
        ResourceBundle bundle = ResourceBundle.getBundle(resourceName, user.getLocale());
        String subject = new String(bundle.getString("mailing.mail.subject.comunicado.anadir").getBytes("ISO-8859-1"), "UTF-8")  + " " + codigo;;

        String body=StringPool.BLANK;
        body = PrevingTemplateUtil.getTemplate(user.getLocale(), MailingPortletKeys.MAIL_TEMPLATE_COMUNICADO_ADICIONAL);
        //body = PrevingTemplateUtil.getTemplate(locale, MailingPortletKeys.MAIL_TEMPLATE_COMUNICADO_ADICIONAL);
        _log.info("(D) MailingPortletKeys.MAIL_TEMPLATE_COMUNICADO_ADICIONAL: " + MailingPortletKeys.MAIL_TEMPLATE_COMUNICADO_ADICIONAL);
        body = PrevingTemplateUtil.replaceImageBase64ToTemplate(body, imageKeys, imageBase64s);
        //body = PrevingTemplateUtil.getTemplate(user.getLocale(), "templates/ComunicacionToUsuarioTemplate_v2");
        body = StringUtil.replace(body,
                new String[]{"[$COMUNICADO_IDENTIFICADOR$]", "[$SUBJECT_MESSAGE$]", "[$FILE_NAME$]", "[$BODY_MESSAGE$]","[$HOST$]","[$REGISTRO_ID$]"},
                new String[]{codigo, asunto, "fichero0", consulta, portalURL, registroId });

        if(!email.isEmpty() && comunicado.getNotificacion().contains("EMAIL") ) { //Email usuario ... ajustar dirección o mensaje de enlace
            PrevingMailUtil.sendMailWithAttachment (email,email, subject, body, null,null);
        }
        User gestor=null;
        for(int i = 0; i < gestoresId.length; i++){
            gestor = UserLocalServiceUtil.fetchUser(Long.valueOf(gestoresId[i]));
            if(Validator.isNotNull(gestor) && Validator.isNotNull(gestor.getEmailAddress())) {
                _log.info("Email gestor -> enviando a  " + gestor.getEmailAddress());
                if(Validator.isNotNull(gestor) && Validator.isNotNull(gestor.getEmailAddress()))
                    PrevingMailUtil.sendMailWithAttachment (gestor.getEmailAddress(),gestor.getEmailAddress(), subject, body, null,null);
            }
        }



        //email para administradores
        body = PrevingTemplateUtil.getTemplate(user.getLocale(), MailingPortletKeys.MAIL_TEMPLATE_COMUNICADO_ADICIONAL);
        //body = PrevingTemplateUtil.getTemplate(locale, MailingPortletKeys.MAIL_TEMPLATE_COMUNICADO_ADICIONAL);

        body = PrevingTemplateUtil.replaceImageBase64ToTemplate(body, imageKeys, imageBase64s);

        body = StringUtil.replace(body,
                new String[]{"[$COMUNICADO_IDENTIFICADOR$]", "[$SUBJECT_MESSAGE$]", "[$FILE_NAME$]", "[$BODY_MESSAGE$]","[$HOST$]","[$REGISTRO_ID$]"},
                new String[]{codigo, asunto, "fichero0", consulta, portalURL, registroId });

        Application app = ApplicationLocalServiceUtil.getApplicationByAlias("CANAL_DENUNCIAS");
        if(!itsNewVersion) {
            //List<UserCompany> adminUsers = UserCompanyLocalServiceUtil.getUsersFromCompany(companiaId);
            List<UserCompany> adminUsers = GetAllUsersByCompanyAnddApp.getAllUserActiveByAppCanal(companiaId);
            for (UserCompany adminUser : adminUsers) {
                if (hasAccesoCompleto(adminUser, Long.valueOf(companiaId))) {
                    PrevingMailUtil.sendMailWithAttachment(adminUser.getEmail(), adminUser.getEmail(), subject, body, null, null);
                }
            }
        }else{
            long idRoleCompleto = 0;
            List<com.adeplus.liferay.portlet.suite.manager.model.Role> allRoles = com.adeplus.liferay.portlet.suite.manager.service.RoleLocalServiceUtil.getRoleByApplicationId(app.getApplicationId());
            for(com.adeplus.liferay.portlet.suite.manager.model.Role role: allRoles ){
                if(role.getAlias().equalsIgnoreCase("Acceso completo")){
                    //isHasApp = true;
                    idRoleCompleto = role.getRoleId();
                    break;
                }
            }
            List<UserCompany> adminUsers=UserApplicationClientLocalServiceUtil.getUsersByEmpresaIdAndApplicationWithLicense(
                    app.getApplicationId(),idRoleCompleto,Long.valueOf(companiaId));
            for (UserCompany adminUser : adminUsers) {
                PrevingMailUtil.sendMailWithAttachment(adminUser.getEmail(), adminUser.getEmail(), subject, body, null, null);
            }
        }

        map.clear();
        map = null;
    }

    public void comunicadoProrrogado(Map map) throws PortalException, UnsupportedEncodingException {
        String codigo = (String) map.get("codigo");
        String email = (String) map.get("email");
        Long companiaId = (Long) map.get("companiaId");
        long userID = (long) map.get("userID");
        String[] gestoresId = {};
        if( map.containsKey("gestoresId")  && !((String) map.get("gestoresId")).isEmpty()){
            gestoresId = ((String) map.get("gestoresId")).split(",");
        }

        User user = UserLocalServiceUtil.getUser(userID);

        String resourceName = "content/Language";
        ResourceBundle bundle = ResourceBundle.getBundle(resourceName, user.getLocale());
        String subject = new String(bundle.getString("mailing.mail.subject.comunicado.prorrogado").getBytes("ISO-8859-1"), "UTF-8")  + " " + codigo;;

        String body = PrevingTemplateUtil.getTemplate(user.getLocale(), MailingPortletKeys.MAIL_TEMPLATE_COMUNICADO_PRORROGADO);

        body = PrevingTemplateUtil.replaceImageBase64ToTemplate(body, imageKeys, imageBase64s);

        body = StringUtil.replace(body,
                new String[] { "[$COMUNICADO_IDENTIFICADOR$]" },
                new String[] {codigo});

        for(String mail: email.split(StringPool.COMMA)) {
            PrevingMailUtil.sendMailWithAttachment(mail, mail, subject, body, null, StringPool.BLANK);
        }

        //Envio a usuario acceso restrigido / completo
        Application app = ApplicationLocalServiceUtil.getApplicationByAlias("CANAL_DENUNCIAS");
        if(!itsNewVersion) {
            // List<UserCompany> adminUsers = UserCompanyLocalServiceUtil.getUsersFromCompany(companiaId);
            List<UserCompany> adminUsers =  GetAllUsersByCompanyAnddApp.getAllUserActiveByAppCanal(companiaId);
            for (UserCompany adminUser : adminUsers) {
                if (hasAccesoCompleto(adminUser, Long.valueOf(companiaId))) {
                    PrevingMailUtil.sendMailWithAttachment(adminUser.getEmail(), adminUser.getEmail(), subject, body, null, null);
                }
            }
        }else{
            long idRoleCompleto = 0;
            List<com.adeplus.liferay.portlet.suite.manager.model.Role> allRoles = com.adeplus.liferay.portlet.suite.manager.service.RoleLocalServiceUtil.getRoleByApplicationId(app.getApplicationId());
            for(com.adeplus.liferay.portlet.suite.manager.model.Role role: allRoles ){
                if(role.getAlias().equalsIgnoreCase("Acceso completo")){
                    //isHasApp = true;
                    idRoleCompleto = role.getRoleId();
                    break;
                }
            }
            List<UserCompany> adminUsers=UserApplicationClientLocalServiceUtil.getUsersByEmpresaIdAndApplicationWithLicense(
                    app.getApplicationId(),idRoleCompleto,Long.valueOf(companiaId));
            for (UserCompany adminUser : adminUsers) {
                PrevingMailUtil.sendMailWithAttachment(adminUser.getEmail(), adminUser.getEmail(), subject, body, null, null);
            }
        }
        User gestor = null;
        for(int i = 0; i < gestoresId.length; i++){
            gestor = UserLocalServiceUtil.fetchUser(Long.valueOf(gestoresId[i]));
            if(Validator.isNotNull(gestor) && Validator.isNotNull(gestor.getEmailAddress())) {
                _log.info("Email gestor -> enviando a  " + gestor.getEmailAddress());
                if(Validator.isNotNull(gestor) && Validator.isNotNull(gestor.getEmailAddress()))
                    PrevingMailUtil.sendMailWithAttachment(gestor.getEmailAddress(), gestor.getEmailAddress(), subject, body, null, "");
            }
        }

        map.clear();
        map = null;
    }

    public void comunicadoAsignado(Map map) throws PortalException, UnsupportedEncodingException {

        String codigo = (String) map.get("codigo");
        String asunto = (String) map.get("asunto");
        String consulta = (String) map.get("consulta");
        String[] gestoresId = ((String) map.get("gestoresId")).split(",");
        String portalURL = (String) map.get("portalURL");
        long userID = (long) map.get("userID");
        long companiaId = (long) map.get("companiaId");

        User user = UserLocalServiceUtil.getUser(userID);

        String resourceName = "content/Language";
        ResourceBundle bundle = ResourceBundle.getBundle(resourceName, user.getLocale());
        String subject = new String(bundle.getString("mailing.mail.subject.comunicado.asignado").getBytes("ISO-8859-1"), "UTF-8")  + " " + codigo;

        String body = PrevingTemplateUtil.getTemplate(user.getLocale(), MailingPortletKeys.MAIL_TEMPLATE_COMUNICADO_ASIGNADO);

        body = PrevingTemplateUtil.replaceImageBase64ToTemplate(body, imageKeys, imageBase64s);

        body = StringUtil.replace(body,
                new String[] { "[$COMUNICADO_IDENTIFICADOR$]","[$SUBJECT_MESSAGE$]","[$BODY_MESSAGE$]","[$HOST$]" },
                new String[] {codigo, asunto,consulta,portalURL});

        User gestor = null;

        Application app = ApplicationLocalServiceUtil.getApplicationByAlias("CANAL_DENUNCIAS");
        if(!itsNewVersion) {
            //List<UserCompany> adminUsers = UserCompanyLocalServiceUtil.getUsersFromCompany(companiaId);
            List<UserCompany> adminUsers =  GetAllUsersByCompanyAnddApp.getAllUserActiveByAppCanal(companiaId);
            for (UserCompany adminUser : adminUsers) {
                if (hasAccesoCompleto(adminUser, Long.valueOf(companiaId))) {
                    PrevingMailUtil.sendMailWithAttachment(adminUser.getEmail(), adminUser.getEmail(), subject, body, null, null);
                }
            }
        }else{
            long idRoleCompleto = 0;
            List<com.adeplus.liferay.portlet.suite.manager.model.Role> allRoles = com.adeplus.liferay.portlet.suite.manager.service.RoleLocalServiceUtil.getRoleByApplicationId(app.getApplicationId());
            for(com.adeplus.liferay.portlet.suite.manager.model.Role role: allRoles ){
                if(role.getAlias().equalsIgnoreCase("Acceso completo")){
                    //isHasApp = true;
                    idRoleCompleto = role.getRoleId();
                    break;
                }
            }
            List<UserCompany> adminUsers=UserApplicationClientLocalServiceUtil.getUsersByEmpresaIdAndApplicationWithLicense(
                    app.getApplicationId(),idRoleCompleto,Long.valueOf(companiaId));
            for (UserCompany adminUser : adminUsers) {
                PrevingMailUtil.sendMailWithAttachment(adminUser.getEmail(), adminUser.getEmail(), subject, body, null, null);
            }
        }

        for(int i = 0; i < gestoresId.length; i++){
            gestor = UserLocalServiceUtil.fetchUser(Long.valueOf(gestoresId[i]));
            if(Validator.isNotNull(gestor)) {
                _log.info("Email gestor -> enviando a  " + gestor.getEmailAddress());
                if(Validator.isNotNull(gestor) && Validator.isNotNull(gestor.getEmailAddress()))
                    PrevingMailUtil.sendMailWithAttachment(gestor.getEmailAddress(), gestor.getEmailAddress(), subject, body, null, "");
            }
        }
    }

    public void comunicadoCambioEstado(Map map) throws PortalException, IOException, URISyntaxException {

        String codigo = (String) map.get("codigo");
        String asunto = (String) map.get("asunto");
        String consulta = (String) map.get("consulta");
        String estado = (String) map.get("estado");
        String email = (String) map.get("email");
        String idFiles = (String) map.get("idFiles");

        long  comunicadoId= (long) map.get("comunicadoId");

        Comunicado comunicado = ComunicadoLocalServiceUtil.fetchComunicado(comunicadoId);

        long companiaId = (long) map.get("companiaId");
        String[] gestoresId = {};
        if( map.containsKey("gestoresId")  && !((String) map.get("gestoresId")).isEmpty()){
            gestoresId = ((String) map.get("gestoresId")).split(",");
        }

        java.util.Calendar cal = java.util.Calendar.getInstance();
        Date date = cal.getTime();
        SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
        String fechaFormateada = formater.format(date);

        //ficheros adjuntos


        _log.info("###### (D) idEstado:"  + estado);

        ArrayList<DLFileEntry> ficheros = null;
        DLFileEntry dlFileEntry = null;
        HashMap<String, DLFileEntry> filesMap = new HashMap<String, DLFileEntry>();

        if(Validator.isNotNull(idFiles) && !idFiles.isEmpty()){
            ficheros = new ArrayList<DLFileEntry>();
            String[] arrFilesId = idFiles.split(",");
            for(int i = 0; i < arrFilesId.length; i++){

                dlFileEntry = DLFileEntryLocalServiceUtil.fetchDLFileEntry(Long.valueOf(arrFilesId[i]));
                if(Validator.isNotNull(dlFileEntry)){
                    _log.info("########### dlFileEntry.getTitle(): " + dlFileEntry.getTitle() );
                    filesMap.put(dlFileEntry.getTitle(),dlFileEntry);
                }
            }
        }


        long userID = (long) map.get("userID");
        User user = UserLocalServiceUtil.getUser(userID);

        //estados
        ResourceBundle bundle = ResourceBundle.getBundle("content/Language", user.getLocale());
        String[] estados = {
                new String(bundle.getString("estado.datatable.filtro.activa.pendiente").getBytes("ISO-8859-1"), "UTF-8"),
                new String(bundle.getString("estado.datatable.filtro.activa.admision").getBytes("ISO-8859-1"), "UTF-8"),
                new String(bundle.getString("estado.datatable.filtro.activa.investigacion").getBytes("ISO-8859-1"), "UTF-8"),
                new String(bundle.getString("estado.datatable.filtro.activa.finalizacion").getBytes("ISO-8859-1"), "UTF-8"),
                new String(bundle.getString("estado.datatable.filtro.activa.finalizada").getBytes("ISO-8859-1"), "UTF-8"),
                new String(bundle.getString("estado.datatable.filtro.activa.caducada").getBytes("ISO-8859-1"), "UTF-8")
                };

        String subject = new String(bundle.getString("mailing.mail.subject.comunicado.actualizado").getBytes("ISO-8859-1"), "UTF-8")  + " " + codigo;

        String body = PrevingTemplateUtil.getTemplate(user.getLocale(), MailingPortletKeys.MAIL_TEMPLATE_COMUNICADO_UPDATE);

        body = PrevingTemplateUtil.replaceImageBase64ToTemplate(body, imageKeys, imageBase64s);

        body = StringUtil.replace(body,
                new String[] { "[$COMUNICADO_IDENTIFICADOR$]","[$COMUNICADO_FECHA_UPDATE$]","[$COMUNICADO_ESTADO$]" ,"[$BODY_MESSAGE$]","[$FILE_NAME$]" },
                new String[] {codigo, fechaFormateada, estados[Integer.valueOf(estado)], consulta,""});

        //Comunicante
        if(!email.isEmpty() && comunicado.getNotificacion().contains("EMAIL") )
            //PrevingMailUtil.sendMailWithAttachment(email, email, subject, body, null, null);
            PrevingMailUtil.sendMailWithAttachmentMultiple(email, email, subject, body, filesMap);


        //Usuarios con acceso completo
        Application app = ApplicationLocalServiceUtil.getApplicationByAlias("CANAL_DENUNCIAS");
        if(!itsNewVersion) {
            //List<UserCompany> adminUsers = UserCompanyLocalServiceUtil.getUsersFromCompany(companiaId);
            List<UserCompany> adminUsers =  GetAllUsersByCompanyAnddApp.getAllUserActiveByAppCanal(companiaId);
            for (UserCompany adminUser : adminUsers) {
                if (hasAccesoCompleto(adminUser, Long.valueOf(companiaId))) {
                    PrevingMailUtil.sendMailWithAttachmentMultiple(adminUser.getEmail(), adminUser.getEmail(), subject, body, filesMap);
                }
            }
        }else{
            long idRoleCompleto = 0;
            List<com.adeplus.liferay.portlet.suite.manager.model.Role> allRoles = com.adeplus.liferay.portlet.suite.manager.service.RoleLocalServiceUtil.getRoleByApplicationId(app.getApplicationId());
            for(com.adeplus.liferay.portlet.suite.manager.model.Role role: allRoles ){
                if(role.getAlias().equalsIgnoreCase("Acceso completo")){
                    //isHasApp = true;
                    idRoleCompleto = role.getRoleId();
                    break;
                }
            }
            List<UserCompany> adminUsers=UserApplicationClientLocalServiceUtil.getUsersByEmpresaIdAndApplicationWithLicense(
                    app.getApplicationId(),idRoleCompleto,Long.valueOf(companiaId));
            for (UserCompany adminUser : adminUsers) {
                PrevingMailUtil.sendMailWithAttachmentMultiple(adminUser.getEmail(), adminUser.getEmail(), subject, body, filesMap);
            }
        }
        //Consultor/es
        User gestor = null;
        for(int i = 0; i < gestoresId.length; i++){
            gestor = UserLocalServiceUtil.fetchUser(Long.valueOf(gestoresId[i]));
            if(Validator.isNotNull(gestor) && Validator.isNotNull(gestor.getEmailAddress())) {
                PrevingMailUtil.sendMailWithAttachmentMultiple(gestor.getEmailAddress(), gestor.getEmailAddress(), subject, body, filesMap);
            }
        }

    }

    public void comunicadoFinalizar(Map map) throws PortalException, UnsupportedEncodingException {

        String codigo = (String) map.get("codigo");
        String asunto = (String) map.get("asunto");
        String consulta = (String) map.get("consulta");
        String estado = (String) map.get("estado");
        String email = (String) map.get("email");


        long companiaId = (long) map.get("companiaId");
        String[] gestoresId = {};
        if( map.containsKey("gestoresId")  && !((String) map.get("gestoresId")).isEmpty()){
            gestoresId = ((String) map.get("gestoresId")).split(",");
        }

        java.util.Calendar cal = java.util.Calendar.getInstance();
        Date date = cal.getTime();
        SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
        String fechaFormateada = formater.format(date);

        long userID = (long) map.get("userID");
        User user = UserLocalServiceUtil.getUser(userID);

        //estados
        ResourceBundle bundle = ResourceBundle.getBundle("content/Language", user.getLocale());
        String[] estados = {
                new String(bundle.getString("estado.datatable.filtro.activa.pendiente").getBytes("ISO-8859-1"), "UTF-8"),
                new String(bundle.getString("estado.datatable.filtro.activa.admision").getBytes("ISO-8859-1"), "UTF-8"),
                new String(bundle.getString("estado.datatable.filtro.activa.investigacion").getBytes("ISO-8859-1"), "UTF-8"),
                new String(bundle.getString("estado.datatable.filtro.activa.finalizacion").getBytes("ISO-8859-1"), "UTF-8"),
                new String(bundle.getString("estado.datatable.filtro.activa.finalizada").getBytes("ISO-8859-1"), "UTF-8"),
                new String(bundle.getString("estado.datatable.filtro.activa.caducada").getBytes("ISO-8859-1"), "UTF-8")
        };


        String subject = "", body = "";
        body = PrevingTemplateUtil.replaceImageBase64ToTemplate(body, imageKeys, imageBase64s);


        //Comunicante
        if(!email.isEmpty()) {
            _log.info("############# Email COMUNICANTE - Comunicado Finalizado");
            subject = new String(bundle.getString("mailing.mail.subject.comunicado.finalizado.user").getBytes("ISO-8859-1"), "UTF-8")  + " " + codigo;
            body = PrevingTemplateUtil.getTemplate(user.getLocale(), MailingPortletKeys.MAIL_TEMPLATE_COMUNICADO_FINALIZADO_USER);

            body = StringUtil.replace(body,
                    new String[]{"[$COMUNICADO_IDENTIFICADOR$]", "[$COMUNICADO_FECHA_FIN$]"
                            ,"[$SUBTITLE_BACKGROUND_SRC$]",
                            "[$ADEPLUS_LOGO_SRC$]",
                            "[$ADEPLUS_LOGO_FOOTER_SRC$]",
                            "[$LINKEDIN_LOGO_SRC$]",
                            "[$FACEBOOK_LOGO_SRC$]"},
                    new String[]{codigo, fechaFormateada,
                            PrefsPropsUtil.getString("preving.canal.etico.mail.template.background.url"),
                            PrefsPropsUtil.getString("preving.canal.etico.mail.template.logo.url"),
                            PrefsPropsUtil.getString("preving.canal.etico.mail.template.logo.footer.url"),
                            PrefsPropsUtil.getString("preving.canal.etico.mail.template.logo.linkedin.url"),
                            PrefsPropsUtil.getString("preving.canal.etico.mail.template.logo.facebook.url")

            });

            PrevingMailUtil.sendMailWithAttachment(email, email, subject, body, null, null);
        }

        subject = new String(bundle.getString("mailing.mail.subject.comunicado.finalizado.admin").getBytes("ISO-8859-1"), "UTF-8")  + " " + codigo;

        body = PrevingTemplateUtil.getTemplate(user.getLocale(), MailingPortletKeys.MAIL_TEMPLATE_COMUNICADO_FINALIZADO_ADMIN);
        body = StringUtil.replace(body,
                new String[]{"[$COMUNICADO_IDENTIFICADOR$]", "[$COMUNICADO_FECHA_FIN$]"
                        ,"[$SUBTITLE_BACKGROUND_SRC$]",
                        "[$ADEPLUS_LOGO_SRC$]",
                        "[$ADEPLUS_LOGO_FOOTER_SRC$]",
                        "[$LINKEDIN_LOGO_SRC$]",
                        "[$FACEBOOK_LOGO_SRC$]"},
                new String[]{codigo, fechaFormateada,
                        PrefsPropsUtil.getString("preving.canal.etico.mail.template.background.url"),
                        PrefsPropsUtil.getString("preving.canal.etico.mail.template.logo.url"),
                        PrefsPropsUtil.getString("preving.canal.etico.mail.template.logo.footer.url"),
                        PrefsPropsUtil.getString("preving.canal.etico.mail.template.logo.linkedin.url"),
                        PrefsPropsUtil.getString("preving.canal.etico.mail.template.logo.facebook.url")

                });


        _log.info("############# Email ACCESO COMPLETO Y RESTRINGIDO - Comunicado Finalizado");
        //Usuarios con acceso completo y restringido
        Application app = ApplicationLocalServiceUtil.getApplicationByAlias("CANAL_DENUNCIAS");
        if(!itsNewVersion) {
            //List<UserCompany> adminUsers = UserCompanyLocalServiceUtil.getUsersFromCompany(companiaId);
            List<UserCompany> adminUsers =  GetAllUsersByCompanyAnddApp.getAllUserActiveByAppCanal(companiaId);
            for (UserCompany adminUser : adminUsers) {
                if (hasAccesoCompleto(adminUser, Long.valueOf(companiaId))) {
                    PrevingMailUtil.sendMailWithAttachment(adminUser.getEmail(), adminUser.getEmail(), subject, body, null, null);
                }
            }
        }else{
            long idRoleCompleto = 0;
            List<com.adeplus.liferay.portlet.suite.manager.model.Role> allRoles = com.adeplus.liferay.portlet.suite.manager.service.RoleLocalServiceUtil.getRoleByApplicationId(app.getApplicationId());
            for(com.adeplus.liferay.portlet.suite.manager.model.Role role: allRoles ){
                if(role.getAlias().equalsIgnoreCase("Acceso completo")){
                    //isHasApp = true;
                    idRoleCompleto = role.getRoleId();
                    break;
                }
            }
            List<UserCompany> adminUsers=UserApplicationClientLocalServiceUtil.getUsersByEmpresaIdAndApplicationWithLicense(
                    app.getApplicationId(),idRoleCompleto,Long.valueOf(companiaId));
            for (UserCompany adminUser : adminUsers) {
                PrevingMailUtil.sendMailWithAttachment(adminUser.getEmail(), adminUser.getEmail(), subject, body, null, null);
            }
        }

            _log.info("############# Email GESTORES - Comunicado Finalizado");
            //Consultor/es
            User gestor = null;
            for(int i = 0; i < gestoresId.length; i++){
                gestor = UserLocalServiceUtil.fetchUser(Long.valueOf(gestoresId[i]));
                if(Validator.isNotNull(gestor) && Validator.isNotNull(gestor.getEmailAddress())) {
                    PrevingMailUtil.sendMailWithAttachment(gestor.getEmailAddress(), gestor.getEmailAddress(), subject, body, null, null);
                }
            }


    }

    public void comunicadoSchedulerFinalizar(Map map) throws PortalException, UnsupportedEncodingException {

        String codigo = (String) map.get("codigo");
        String asunto = (String) map.get("asunto");
        String consulta = (String) map.get("consulta");
        String estado = (String) map.get("estado");
        String email = (String) map.get("email");


        long companiaId = (long) map.get("companiaId");
        String[] gestoresId = {};
        if( map.containsKey("gestoresId")  && !((String) map.get("gestoresId")).isEmpty()){
            gestoresId = ((String) map.get("gestoresId")).split(",");
        }

        java.util.Calendar cal = java.util.Calendar.getInstance();
        Date date = cal.getTime();
        SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
        String fechaFormateada = formater.format(date);

        long userID = (long) map.get("userID");
        User user = UserLocalServiceUtil.getUser(userID);

        //estados
        ResourceBundle bundle = ResourceBundle.getBundle("content/Language", user.getLocale());
        String[] estados = {
                new String(bundle.getString("estado.datatable.filtro.activa.pendiente").getBytes("ISO-8859-1"), "UTF-8"),
                new String(bundle.getString("estado.datatable.filtro.activa.admision").getBytes("ISO-8859-1"), "UTF-8"),
                new String(bundle.getString("estado.datatable.filtro.activa.investigacion").getBytes("ISO-8859-1"), "UTF-8"),
                new String(bundle.getString("estado.datatable.filtro.activa.finalizacion").getBytes("ISO-8859-1"), "UTF-8"),
                new String(bundle.getString("estado.datatable.filtro.activa.finalizada").getBytes("ISO-8859-1"), "UTF-8"),
                new String(bundle.getString("estado.datatable.filtro.activa.caducada").getBytes("ISO-8859-1"), "UTF-8")
        };


        String subject = "", body = "";
        subject = new String(bundle.getString("mailing.mail.subject.comunicado.caducado").getBytes("ISO-8859-1"), "UTF-8")  + " " + codigo;
        body = PrevingTemplateUtil.replaceImageBase64ToTemplate(body, imageKeys, imageBase64s);
        body = PrevingTemplateUtil.getTemplate(user.getLocale(), MailingPortletKeys.MAIL_TEMPLATE_COMUNICADO_CADUCADO);

        body = StringUtil.replace(body,
                new String[]{"[$COMUNICADO_IDENTIFICADOR$]", "[$COMUNICADO_FECHA_PRORROGA$]"},
                new String[]{codigo, fechaFormateada});

        //Comunicante
        if(!email.isEmpty()  ) {
            _log.info("############# Email SCHEDULER COMUNICANTE - Comunicado Finalizado");
            PrevingMailUtil.sendMailWithAttachment(email, email, subject, body, null, null);
        }

        _log.info("############# Email SCHEDULER ACCESO COMPLETO Y RESTRINGIDO - Comunicado Finalizado");
        //Usuarios con acceso completo y restringido
        Application app = ApplicationLocalServiceUtil.getApplicationByAlias("CANAL_DENUNCIAS");
        if(!itsNewVersion) {
            //List<UserCompany> adminUsers = UserCompanyLocalServiceUtil.getUsersFromCompany(companiaId);
            List<UserCompany> adminUsers =  GetAllUsersByCompanyAnddApp.getAllUserActiveByAppCanal(companiaId);
            for (UserCompany adminUser : adminUsers) {
                if (hasAccesoCompleto(adminUser, Long.valueOf(companiaId))) {
                    PrevingMailUtil.sendMailWithAttachment(adminUser.getEmail(), adminUser.getEmail(), subject, body, null, null);
                }
            }
        }else{
            long idRoleCompleto = 0;
            List<com.adeplus.liferay.portlet.suite.manager.model.Role> allRoles = com.adeplus.liferay.portlet.suite.manager.service.RoleLocalServiceUtil.getRoleByApplicationId(app.getApplicationId());
            for(com.adeplus.liferay.portlet.suite.manager.model.Role role: allRoles ){
                if(role.getAlias().equalsIgnoreCase("Acceso completo")){
                    //isHasApp = true;
                    idRoleCompleto = role.getRoleId();
                    break;
                }
            }
            List<UserCompany> adminUsers=UserApplicationClientLocalServiceUtil.getUsersByEmpresaIdAndApplicationWithLicense(
                    app.getApplicationId(),idRoleCompleto,Long.valueOf(companiaId));
            for (UserCompany adminUser : adminUsers) {
                PrevingMailUtil.sendMailWithAttachment(adminUser.getEmail(), adminUser.getEmail(), subject, body, null, null);
            }
        }

        _log.info("############# Email SCHEDULER GESTORES - Comunicado Finalizado");
        //Consultor/es
        User gestor = null;
        for(int i = 0; i < gestoresId.length; i++) {
            if (!gestoresId[i].isEmpty() && !gestoresId[i].equals("0")) {
                gestor = UserLocalServiceUtil.fetchUser(Long.valueOf(gestoresId[i]));
                if(Validator.isNotNull(gestor) && Validator.isNotNull(gestor.getEmailAddress())) {
                    PrevingMailUtil.sendMailWithAttachment(gestor.getEmailAddress(), gestor.getEmailAddress(), subject, body, null, null);
                }
            }
        }


    }

    private boolean hasRoleUserApp(UserCompany user, String roleName) throws PortalException {
        //String roleName = PrefsPropsUtil.getString("canal.etico.rol.company.admin");
        Role role=null;
        role = RoleLocalServiceUtil.getRole(PortalUtil.getDefaultCompanyId(), roleName);
        boolean hasRole=false;
        for (Role rol: UserLocalServiceUtil.fetchUser(user.getUserId()).getRoles()){
            if(rol.equals(role)){
                hasRole=true;
                break;
            }
        }
        return hasRole;
    }

    @Override
    public String generateLockKey(BackgroundTask backgroundTask) {
        return null;
    }

    @Override
    public BackgroundTaskDisplay getBackgroundTaskDisplay(BackgroundTask backgroundTask) {
        return BackgroundTaskDisplayFactoryUtil.getBackgroundTaskDisplay(backgroundTask);
    }

    @Override
    public BackgroundTaskStatusMessageTranslator getBackgroundTaskStatusMessageTranslator() {
        return null;
    }

    @Override
    public int getIsolationLevel() {
        //return 0;
        return BackgroundTaskConstants.ISOLATION_LEVEL_TASK_NAME;
    }

    @Override
    public String handleException(BackgroundTask backgroundTask, Exception exception) {
        return null;
    }

    @Override
    public boolean isSerial() {
        return true;
    }
}

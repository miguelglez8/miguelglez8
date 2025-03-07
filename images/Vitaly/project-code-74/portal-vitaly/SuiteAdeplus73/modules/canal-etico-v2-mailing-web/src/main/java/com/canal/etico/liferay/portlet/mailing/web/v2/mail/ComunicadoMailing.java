package com.canal.etico.liferay.portlet.mailing.web.v2.mail;



import com.adeplus.liferay.portlet.suite.manager.model.Application;
import com.adeplus.liferay.portlet.suite.manager.model.UserApplication;
import com.adeplus.liferay.portlet.suite.manager.model.UserCompany;
import com.adeplus.liferay.portlet.suite.manager.service.*;
import com.adeplus.liferay.portlet.suite.manager.service.persistence.UserApplicationPK;
import com.canal.etico.liferay.portlet.commons.web.constants.CanalEticoCommonsPortletKeys;
import com.canal.etico.liferay.portlet.mailing.web.v2.constants.MailingPortletKeys;
import com.canal.etico.liferay.portlet.mailing.web.v2.constants.MailingTemplateImageKeys;
import com.canal.etico.liferay.portlet.mailing.web.v2.util.PrevingMailUtil;
import com.canal.etico.liferay.portlet.mailing.web.v2.util.PrevingTemplateUtil;
import com.canal.etico.v2.liferay.portlet.canal.manager.model.Comunicado;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class ComunicadoMailing {
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

    //public static void avisoCaducidad(String mailTo, String mailtToName,User userFrom, String identificador, String fechaAlta, String fechaProrroga){
    public static void avisoCaducidad(User user, Comunicado comunicado) throws PortalException, UnsupportedEncodingException {

        SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
        String fechaAlta = formater.format(comunicado.getCreateDate());
        String fechaCaducidad = formater.format(comunicado.getCaducidadDate());

        String resourceName = "content/Language";
        ResourceBundle bundle = ResourceBundle.getBundle(resourceName, user.getLocale());
        //String subject = LanguageUtil.get(ResourceBundle.getBundle("content/Language", user.getLocale()), "mailing.mail.subject.aviso.caducidad");
        String subject = new String(bundle.getString("mailing.mail.subject.aviso.caducidad").getBytes("ISO-8859-1"), "UTF-8")  + " " + comunicado.getCodigo();;

        String body = PrevingTemplateUtil.getTemplate(user.getLocale(), MailingPortletKeys.MAIL_TEMPLATE_COMUNICADO_CADUCIDAD_AVISO);

        body = PrevingTemplateUtil.replaceImageBase64ToTemplate(body, imageKeys, imageBase64s);

        body = StringUtil.replace(body,
                new String[] { "[$COMUNICADO_IDENTIFICADOR$]","[$COMUNICADO_FECHA_PRORROGA$]","[$COMUNICADO_FECHA_ALTA$]" },
                new String[] {comunicado.getCodigo(), fechaCaducidad, fechaAlta});

        //Usuarios con acceso completo y restringido
        Application app = ApplicationLocalServiceUtil.getApplicationByAlias("CANAL_DENUNCIAS");
        if(!CanalEticoCommonsPortletKeys.ITS_NEW_VERSION) {
            List<UserCompany> adminUsers = UserCompanyLocalServiceUtil.getUsersFromCompany(comunicado.getCompId());
            for (UserCompany adminUser : adminUsers) {
                if (hasAccesoCompleto(adminUser, comunicado.getCompId())) {
                    PrevingMailUtil.sendMailWithAttachment(adminUser.getEmail(), adminUser.getEmail(), subject, body, null,null);
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
                    app.getApplicationId(),idRoleCompleto,comunicado.getCompId());
            for (UserCompany adminUser : adminUsers) {
                PrevingMailUtil.sendMailWithAttachment(adminUser.getEmail(), adminUser.getEmail(), subject, body, null,null);
            }
        }
        User gestor = null;
        String [] gestoresId=comunicado.getGestorId().split(",");
        for(int i = 0; i < gestoresId.length; i++){
            gestor = UserLocalServiceUtil.fetchUser(Long.valueOf(gestoresId[i]));
            if(Validator.isNotNull(gestor) && Validator.isNotNull(gestor.getEmailAddress())){
                PrevingMailUtil.sendMailWithAttachment(gestor.getEmailAddress(), gestor.getEmailAddress(), subject, body, null, "");
            }
        }

    }


    private static boolean hasAccesoCompleto(UserCompany user, long compId) throws PortalException {
        boolean isHasApp = false;
        Application app = ApplicationLocalServiceUtil.getApplicationByAlias("CANAL_DENUNCIAS");
        //UserApplicationLocalServiceUtil.hasUserApplication(user.getUserId(), compId, app.getApplicationId();
        UserApplicationPK pk = new UserApplicationPK(user.getUserId(), compId, app.getApplicationId());
        UserApplication userApp = UserApplicationLocalServiceUtil.getUserApplication(pk);
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


    //public static void comunicadoCaducado(long userId, String mailtToName , String identificador ) {
    public static void comunicadoCaducado(User user, Comunicado comunicado ) throws PortalException {

        ResourceBundle bundle = ResourceBundle.getBundle("content/Language", user.getLocale());
        String subject ="Comunicado caducado";
        java.util.Calendar cal = java.util.Calendar.getInstance();
        Date date = cal.getTime();
        SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
        String fechaFormateada = formater.format(date);
        try {
            subject = new String(bundle.getString("mailing.mail.subject.comunicado.caducado").getBytes("ISO-8859-1"), "UTF-8")  + " " + comunicado.getCodigo();
        } catch (UnsupportedEncodingException e) {

        }
        String body = PrevingTemplateUtil.getTemplate(user.getLocale(), MailingPortletKeys.MAIL_TEMPLATE_COMUNICADO_CADUCADO);

        body = PrevingTemplateUtil.replaceImageBase64ToTemplate(body, imageKeys, imageBase64s);

        body = StringUtil.replace(body,
                new String[]{"[$COMUNICADO_IDENTIFICADOR$]", "[$COMUNICADO_FECHA_PRORROGA$]"},
                new String[]{comunicado.getCodigo(), fechaFormateada});


        if(Validator.isNotNull(comunicado.getEmail()) && !comunicado.getEmail().isEmpty() && comunicado.getNotificacion().contains("EMAIL")) {
            PrevingMailUtil.sendMail(comunicado.getEmail(), comunicado.getEmail(), subject, body);
        }
        String allIdUser="";
        //Usuarios con acceso completo y restringido
        Application app = ApplicationLocalServiceUtil.getApplicationByAlias("CANAL_DENUNCIAS");
        if(!CanalEticoCommonsPortletKeys.ITS_NEW_VERSION) {
            List<UserCompany> adminUsers = UserCompanyLocalServiceUtil.getUsersFromCompany(comunicado.getCompId());
            for (UserCompany adminUser : adminUsers) {
                if (hasAccesoCompleto(adminUser, comunicado.getCompId())) {
                    allIdUser=allIdUser+adminUser.getUserId()+" ";
                    PrevingMailUtil.sendMail(adminUser.getEmail(),adminUser.getEmail(),subject,body );
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
            List<UserCompany> adminUsers= UserApplicationClientLocalServiceUtil.getUsersByEmpresaIdAndApplicationWithLicense(
                    app.getApplicationId(),idRoleCompleto,comunicado.getCompId());
            for (UserCompany adminUser : adminUsers) {
                allIdUser=allIdUser+adminUser.getUserId()+" ";
                PrevingMailUtil.sendMail(adminUser.getEmail(),adminUser.getEmail(),subject,body );
            }
        }

        //Consultor/es
        User gestor = null;
        String [] gestoresId=comunicado.getGestorId().split(",");
        for(int i = 0; i < gestoresId.length; i++){
            gestor = UserLocalServiceUtil.fetchUser(Long.valueOf(gestoresId[i]));
            if(Validator.isNotNull(gestor) && Validator.isNotNull(gestor.getEmailAddress()) && allIdUser.indexOf(gestoresId[i])==-1){
                PrevingMailUtil.sendMailWithAttachment(gestor.getEmailAddress(), gestor.getEmailAddress(), subject, body, null, "");
            }
        }
    }

    public static void comunicadoAsignado(String mailTo, String mailtToName, User userFrom, String identificador, String asunto,String descripcion){
        String subject = LanguageUtil.get(ResourceBundle.getBundle("content/Language", userFrom.getLocale()), "mailing.mail.subject.comunicado.asignado");

        String body = PrevingTemplateUtil.getTemplate(userFrom.getLocale(), MailingPortletKeys.MAIL_TEMPLATE_COMUNICADO_ASIGNADO);

        body = PrevingTemplateUtil.replaceImageBase64ToTemplate(body, imageKeys, imageBase64s);

        body = StringUtil.replace(body,
                new String[] { "[$COMUNICADO_IDENTIFICADOR$]","[$SUBJECT_MESSAGE$]","[$BODY_MESSAGE$]" },
                new String[] {identificador, asunto,descripcion});

        PrevingMailUtil.sendMailWithAttachment(mailTo, mailtToName, subject, body, null, "");

    }


    public static void comunicadoAdicional(String mailTo, User userFrom, String mailtToName, String identificador, String motivo,File file,String filename,String consulta, String categoria){
        String subject = LanguageUtil.get(ResourceBundle.getBundle("content/Language", userFrom.getLocale()), "mailing.mail.subject.comunicado.infromacion.adicional");

        String body = PrevingTemplateUtil.getTemplate(userFrom.getLocale(), MailingPortletKeys.MAIL_TEMPLATE_COMUNICADO_ADICIONAL);

        body = PrevingTemplateUtil.replaceImageBase64ToTemplate(body, imageKeys, imageBase64s);

        body = StringUtil.replace(body,
                new String[] { "[$COMUNICADO_IDENTIFICADOR$]","[$CATEGORY_MESSAGE$]","[$SUBJECT_MESSAGE$]","[$FILE_NAME$]","[$BODY_MESSAGE$]" },
                new String[] {identificador, categoria, motivo,filename,consulta});

        PrevingMailUtil.sendMailWithAttachment(mailTo, mailtToName, subject, body, file, filename);

    }

    public static void comunicadoProrrogado(String mailTo, String mailtToName,User userFrom, String identificador){
        String subject = LanguageUtil.get(ResourceBundle.getBundle("content/Language", userFrom.getLocale()), "mailing.mail.subject.comunicado.prorrogado");

        String body = PrevingTemplateUtil.getTemplate(userFrom.getLocale(), MailingPortletKeys.MAIL_TEMPLATE_COMUNICADO_PRORROGADO);

        body = PrevingTemplateUtil.replaceImageBase64ToTemplate(body, imageKeys, imageBase64s);

        body = StringUtil.replace(body,
                new String[] { "[$COMUNICADO_IDENTIFICADOR$]" },
                new String[] {identificador});

        PrevingMailUtil.sendMailWithAttachment(mailTo, mailtToName, subject, body, null, StringPool.BLANK);

    }

    public static void comunicadoEstadoActualizado(String mailTo, String mailtToName,User userFrom, String identificador, String fechaUpdate,String estado, String consulta, File file, String fileName){

        String subject = LanguageUtil.get(ResourceBundle.getBundle("content/Language", userFrom.getLocale()), "mailing.mail.subject.comunicado.actualizado");

        String body = PrevingTemplateUtil.getTemplate(userFrom.getLocale(), MailingPortletKeys.MAIL_TEMPLATE_COMUNICADO_UPDATE);

        body = PrevingTemplateUtil.replaceImageBase64ToTemplate(body, imageKeys, imageBase64s);

        body = StringUtil.replace(body,
                new String[] { "[$COMUNICADO_IDENTIFICADOR$]","[$COMUNICADO_FECHA_UPDATE$]","[$COMUNICADO_ESTADO$]" ,"[$BODY_MESSAGE$]","[$FILE_NAME$]" },
                new String[] {identificador, fechaUpdate, estado, consulta,fileName});

        PrevingMailUtil.sendMailWithAttachment(mailTo, mailtToName, subject, body, file,fileName);

    }

    public static void comunicadoNuevo(String mailTo, String mailtToName,User userFrom, String identificador, String motivo,File file,String filename,String consulta, boolean admin){
        String subject = LanguageUtil.get(ResourceBundle.getBundle("content/Language", userFrom.getLocale()), "mailing.mail.subject.comunicado.nuevo");
        String body=StringPool.BLANK;
        if(admin) {
            body = PrevingTemplateUtil.getTemplate(userFrom.getLocale(), MailingPortletKeys.MAIL_TEMPLATE_COMUNICADO_NUEVO_ADMIN);

            body = PrevingTemplateUtil.replaceImageBase64ToTemplate(body, imageKeys, imageBase64s);

            body = StringUtil.replace(body,
                    new String[]{"[$COMUNICADO_IDENTIFICADOR$]",  "[$SUBJECT_MESSAGE$]", "[$BODY_MESSAGE$]"},
                    new String[]{identificador, motivo, consulta});
        }else{
            body = PrevingTemplateUtil.getTemplate(userFrom.getLocale(), MailingPortletKeys.MAIL_TEMPLATE_COMUNICADO_NUEVO_USER);

            body = PrevingTemplateUtil.replaceImageBase64ToTemplate(body, imageKeys, imageBase64s);

            body = StringUtil.replace(body,
                    new String[]{"[$COMUNICADO_IDENTIFICADOR$]", "[$FILE_NAME$]", "[$BODY_MESSAGE$]"},
                    new String[]{identificador, filename, consulta,});
        }
        PrevingMailUtil.sendMailWithAttachment(mailTo, mailtToName, subject, body, file, filename);

    }

    public static void comunicadoFinalizado(String mailTo, String mailtToName,User userFrom, String identificador, String fechaFin,File file,String filename,String motivo, String observaciones, boolean gestor){
        String subject = StringPool.BLANK;
        String body = StringPool.BLANK;
        if(gestor) {
            subject = LanguageUtil.get(ResourceBundle.getBundle("content/Language", userFrom.getLocale()), "mailing.mail.subject.comunicado.finalizado.admin");
            body = PrevingTemplateUtil.getTemplate(userFrom.getLocale(), MailingPortletKeys.MAIL_TEMPLATE_COMUNICADO_FINALIZADO_ADMIN);

            body = PrevingTemplateUtil.replaceImageBase64ToTemplate(body, imageKeys, imageBase64s);

            body = StringUtil.replace(body,
                    new String[]{"[$COMUNICADO_IDENTIFICADOR$]", "[$COMUNICADO_FECHA_FIN$]", "[$SUBJECT_MESSAGE$]", "[$BODY_MESSAGE$]"},
                    new String[]{identificador, fechaFin, motivo, observaciones});
        }else{
            subject = LanguageUtil.get(ResourceBundle.getBundle("content/Language", userFrom.getLocale()), "mailing.mail.subject.comunicado.finalizado.user");
            body = PrevingTemplateUtil.getTemplate(userFrom.getLocale(), MailingPortletKeys.MAIL_TEMPLATE_COMUNICADO_FINALIZADO_USER);

            body = PrevingTemplateUtil.replaceImageBase64ToTemplate(body, imageKeys, imageBase64s);

            body = StringUtil.replace(body,
                    new String[]{"[$COMUNICADO_IDENTIFICADOR$]", "[$COMUNICADO_FECHA_FIN$]", "[$SUBJECT_MESSAGE$]", "[$BODY_MESSAGE$]"},
                    new String[]{identificador, fechaFin, motivo, observaciones});
        }



        PrevingMailUtil.sendMailWithAttachment(mailTo, mailtToName, subject, body, file, filename);

    }



}

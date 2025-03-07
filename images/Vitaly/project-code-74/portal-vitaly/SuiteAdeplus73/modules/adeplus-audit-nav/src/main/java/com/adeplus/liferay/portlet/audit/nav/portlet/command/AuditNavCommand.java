package com.adeplus.liferay.portlet.audit.nav.portlet.command;

import com.adeplus.liferay.portlet.audit.nav.constants.AuditNavPortletKeys;
import com.adeplus.liferay.portlet.audit.nav.utils.AuditUtils;
import com.adeplus.liferay.portlet.suite.manager.model.UserCompany;
import com.adeplus.liferay.portlet.suite.manager.service.CompLocalServiceUtil;
import com.adeplus.liferay.portlet.suite.manager.service.UserCompanyLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.RollingFileAppender;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;


@Component(
        immediate = true,
        property = {
                "javax.portlet.name="+ AuditNavPortletKeys.AUDITNAV ,
                "mvc.command.name=auditNavigation"
        },
        service = MVCResourceCommand.class
)
public class AuditNavCommand extends BaseMVCResourceCommand {

    private static Log _log = LogFactoryUtil.getLog(AuditNavCommand.class);

    @Override
    protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {

        HttpServletRequest httpReq = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(resourceRequest));
        String link=httpReq.getParameter("link");
        long userId=Long.parseLong(httpReq.getParameter("userId"));
        String pagingaActual=httpReq.getParameter("url");

        String auditoria="";
        ThemeDisplay themeDisplay=(ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);

        User usuarioReal = themeDisplay.getRealUser();
        User usuario= UserLocalServiceUtil.getUser(userId);



        String dateTime = DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm:ss a")
                .format(LocalDateTime.now());
        String nombreEmpresa= StringPool.BLANK;

        Calendar fecha = Calendar.getInstance();
        int anio = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH) + 1;
        String nombreFichero=AuditNavPortletKeys.NOMBRE_FICHERO;
        try {
            UserCompany uc=UserCompanyLocalServiceUtil.getUserDefaultCompany(usuario.getUserId());
            if(Validator.isNotNull(uc)) {
                long idEmpresa = uc.getCompId();
                String empresaName=CompLocalServiceUtil.getComp(idEmpresa).getName();
                nombreEmpresa = "Empresa desde la que esta accediendo: "+empresaName+";";
                nombreFichero="/tmp/auditoria/Auditoria_"+empresaName+"_"+mes+"_"+anio+"";
            }
        } catch (PortalException e) {
            //no tiene empresa asociada
        }

        //Comprobamos si realiza el logout
        if(link.contains("c/portal/logout")){

            auditoria = "IP: " + usuarioReal.getLoginIP() + "; Fecha y hora: " + dateTime + "; Accion Realizada: Logout; Usuario: " +
                    usuarioReal.getEmailAddress() + "";
        }else {

            auditoria = "IP: " + usuarioReal.getLoginIP() + "(Simulando); Fecha y hora: " + dateTime + "; Accion Realizada: Navegar a " + link + " desde " + pagingaActual + "; Usuario: " +
                    usuarioReal.getEmailAddress() + "(Simulando)";
            if (!themeDisplay.isImpersonated()) {
                auditoria = auditoria.replaceAll("Simulando", "");
                auditoria = auditoria.replaceAll("[()]", "");
            } else {
                auditoria = auditoria + " usuario simulado: " + usuario.getEmailAddress();
            }


        }
        File archivo = AuditUtils.createNewFileVersion(nombreFichero);
        if (Validator.isNotNull(archivo)) {
            FileWriter escritor = new FileWriter(archivo, true);
            _log.info(auditoria + ";" + nombreEmpresa);
            escritor.write(auditoria + ";\n");
            escritor.close();
        }else{
            _log.info("No se pudo acceder al fichero");
        }

    }


}
package com.adeplus.liferay.portlet.audit.login.listener;

import com.adeplus.liferay.portlet.audit.login.constants.AdeplusAuditLoginPortletKeys;
import com.adeplus.liferay.portlet.audit.login.utils.LoginUtils;
import com.adeplus.liferay.portlet.suite.manager.model.UserCompany;
import com.adeplus.liferay.portlet.suite.manager.service.CompLocalServiceUtil;
import com.adeplus.liferay.portlet.suite.manager.service.UserCompanyLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.Validator;
import org.osgi.service.component.annotations.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

@Component(
        immediate = true,
        service = ModelListener.class
)

public class LoginListener extends BaseModelListener<User> {
    private static final Log _log = LogFactoryUtil.getLog(LoginListener.class);
    @Override
    public void onBeforeUpdate(User usuario) throws ModelListenerException {
        try {
            auditarUsuario(usuario);
        }catch (Exception e){
            _log.error(e);
        }
        super.onAfterUpdate(usuario);
    }

    private void auditarUsuario(User usuario) throws IOException {
        long idUsuario = usuario.getUserId();
        User usuarioAntig = UserLocalServiceUtil.fetchUser(idUsuario);
        String dateTime = DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm:ss a")
                .format(LocalDateTime.now());

        String nombreEmpresa= StringPool.BLANK;
        Calendar fecha = Calendar.getInstance();
        int anio = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH) + 1;
        String nombreFichero= AdeplusAuditLoginPortletKeys.NOMBRE_FICHERO;

        if (Validator.isNotNull(usuarioAntig)) {
            if (Validator.isNotNull(usuarioAntig.getLoginDate()) && usuario.getLoginDate().after(usuarioAntig.getLoginDate())) {

                String auditoria="IP: " + usuario.getLoginIP() + "; Fecha y hora: " + dateTime + "; Accion Realizada: Login; Usuario: " +
                        usuario.getEmailAddress() + "; ";
                try {

                    UserCompany uc = UserCompanyLocalServiceUtil.getUserDefaultCompany(usuario.getUserId());

                    if(Validator.isNotNull(uc)) {
                        long idEmpresa = uc.getCompId();
                        String empresaName= CompLocalServiceUtil.getComp(idEmpresa).getName();
                        nombreEmpresa = "Empresa desde la que esta accediendo:" +empresaName;
                        nombreFichero="/tmp/auditoria/Auditoria_"+empresaName+"_"+mes+"_"+anio+"";
                    }
                } catch (Exception e) {

                }
                auditoria=auditoria+nombreEmpresa;
                File archivo = LoginUtils.createNewFileVersion(nombreFichero);
                if (Validator.isNotNull(archivo)) {
                    FileWriter escritor = new FileWriter(archivo, true);
                    escritor.write(auditoria + ";\n");
                    escritor.close();
                }else{
                    _log.info("No se pudo acceder al fichero");
                }
            }
        }

    }

}

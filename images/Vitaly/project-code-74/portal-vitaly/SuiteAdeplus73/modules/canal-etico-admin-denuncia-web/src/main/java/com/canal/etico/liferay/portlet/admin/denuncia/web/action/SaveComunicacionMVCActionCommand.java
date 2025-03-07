package com.canal.etico.liferay.portlet.admin.denuncia.web.action;

import com.canal.etico.liferay.portlet.admin.denuncia.web.constants.AdminDenunciaPortletKeys;
import com.canal.etico.liferay.portlet.canal.manager.model.Comunicacion;
import com.canal.etico.liferay.portlet.canal.manager.model.Denuncia;
import com.canal.etico.liferay.portlet.canal.manager.service.ComunicacionLocalServiceUtil;
import com.canal.etico.liferay.portlet.canal.manager.service.DenunciaLocalServiceUtil;
import com.canal.etico.liferay.portlet.commons.web.file.CanalEticoDocumentLibraryUtil;
import com.canal.etico.liferay.portlet.mailing.web.mail.DenunciaMailing;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import java.io.File;
import java.util.ResourceBundle;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + AdminDenunciaPortletKeys.ADMINDENUNCIA,
                "mvc.command.name=/comunicacion/save"
        },
        service = MVCActionCommand.class
)
public class SaveComunicacionMVCActionCommand implements MVCActionCommand {

    @Override
    public boolean processAction(ActionRequest actionRequest, ActionResponse actionResponse) throws PortletException {

        ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

        ResourceBundle bundle = ResourceBundle.getBundle("content/Language", themeDisplay.getUser().getLocale());
        String mailSubject  = LanguageUtil.get(bundle, "canal-etico-denuncia-web");

        long denunciaId     = ParamUtil.getLong(actionRequest, AdminDenunciaPortletKeys.DENUNCIA_ID, 0);
        String descripcion 	= ParamUtil.getString(actionRequest, AdminDenunciaPortletKeys.DESCRIPTION, "");
        String observaciones = ParamUtil.getString(actionRequest, AdminDenunciaPortletKeys.OBSERVACIONES_COMUNICACION, "");

        UploadPortletRequest uploadRequest = PortalUtil.getUploadPortletRequest(actionRequest);
        String fileName     = uploadRequest.getFileName(AdminDenunciaPortletKeys.ADJUNTO);
        File file           = (File) uploadRequest.getFile(AdminDenunciaPortletKeys.ADJUNTO);

        if(denunciaId > 0) {

            Denuncia denuncia = DenunciaLocalServiceUtil.fetchDenuncia(denunciaId);

            if(Validator.isNotNull(denuncia)){

                DLFileEntry dlFileEntry = null;
                if(Validator.isNotNull(file) && file.exists()) {
                    //Save file
                    ServiceContext serviceContext = null;
                    try {
                        serviceContext = ServiceContextFactory.getInstance(DLFileEntry.class.getName(), actionRequest);

                        dlFileEntry = CanalEticoDocumentLibraryUtil.saveFile(
                                themeDisplay.getScopeGroupId(),
                                themeDisplay.getUserId(),
                                AdminDenunciaPortletKeys.COMUNICACION_ADJUNTO_DESCRIPTION,
                                AdminDenunciaPortletKeys.PARENT_FOLDER_NAME,
                                denuncia.getCodigo(),
                                fileName,
                                file,
                                serviceContext);

                    } catch (PortalException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }


                //Create new comunicacion for denuncia.
                Comunicacion comunicacion = ComunicacionLocalServiceUtil.createComunicacion(CounterLocalServiceUtil.increment(Comunicacion.class.getName()));

                comunicacion.setDenunciaId(denunciaId);
                comunicacion.setDescripcion(descripcion);
                comunicacion.setUserId(themeDisplay.getUserId());
                comunicacion.setObservaciones(observaciones);

                if(Validator.isNotNull(file) && file.exists()) {
                    comunicacion.setAdjunto(dlFileEntry.getFileEntryId());
                }

                ComunicacionLocalServiceUtil.addComunicacion(comunicacion);

                //Send comunicacion to user
                DenunciaMailing.comunicacionToUser(
                        denuncia.getEmail(),
                        denuncia.getNombre(),
                        denuncia.getCodigo(),
                        descripcion,
                        file,
                        "".equals(fileName)?"-":fileName,
                        themeDisplay.getLocale());

                }

        }

        actionResponse.setRenderParameter( AdminDenunciaPortletKeys.DENUNCIA_ID, String.valueOf(denunciaId));
        actionResponse.setRenderParameter("jspPage", "/denuncia.jsp");

        return false;
    }
    
}

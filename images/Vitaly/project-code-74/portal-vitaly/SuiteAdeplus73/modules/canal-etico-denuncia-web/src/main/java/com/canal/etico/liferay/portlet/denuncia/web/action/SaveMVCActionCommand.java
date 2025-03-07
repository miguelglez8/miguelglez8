package com.canal.etico.liferay.portlet.denuncia.web.action;

import com.canal.etico.liferay.portlet.canal.manager.model.Categoria;
import com.canal.etico.liferay.portlet.canal.manager.model.Comp;
import com.canal.etico.liferay.portlet.canal.manager.model.Denuncia;
import com.canal.etico.liferay.portlet.canal.manager.model.UserCompany;
import com.canal.etico.liferay.portlet.canal.manager.service.CategoriaLocalServiceUtil;
import com.canal.etico.liferay.portlet.canal.manager.service.CompLocalServiceUtil;
import com.canal.etico.liferay.portlet.canal.manager.service.DenunciaLocalServiceUtil;
import com.canal.etico.liferay.portlet.canal.manager.service.UserCompanyLocalServiceUtil;
import com.canal.etico.liferay.portlet.commons.web.file.CanalEticoDocumentLibraryUtil;
import com.canal.etico.liferay.portlet.denuncia.web.constants.DenunciaPortletKeys;
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
import java.text.SimpleDateFormat;
import java.util.*;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + DenunciaPortletKeys.DENUNCIA,
                "mvc.command.name=/denuncia/save"
        },
        service = MVCActionCommand.class
)
public class SaveMVCActionCommand implements MVCActionCommand {

    @Override
    //public boolean serveResource(actionRequest actionRequest, ResourceResponse resourceResponse) throws PortletException {
    public boolean processAction(ActionRequest actionRequest, ActionResponse actionResponse) throws PortletException {
        ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

        String cif 	        = ParamUtil.getString(actionRequest, DenunciaPortletKeys.CIF, "");
        long companyId 	    = ParamUtil.getLong(actionRequest, DenunciaPortletKeys.COMPANY_ID, 0);
        String companyIdentification = ParamUtil.getString(actionRequest, DenunciaPortletKeys.COMPANY_IDENTIFICATION, String.valueOf(companyId));
        String vinculacion 	= ParamUtil.getString(actionRequest, DenunciaPortletKeys.VINCULACION, "");

        String email 	    = ParamUtil.getString(actionRequest, DenunciaPortletKeys.EMAIL, "");
        String name 	    = ParamUtil.getString(actionRequest, DenunciaPortletKeys.NAME, "");
        String lastName     = ParamUtil.getString(actionRequest, DenunciaPortletKeys.LAST_NAME, "");
        String nif 	        = ParamUtil.getString(actionRequest, DenunciaPortletKeys.NIF, "");
        String phone 	    = ParamUtil.getString(actionRequest, DenunciaPortletKeys.PHONE, "");
        String anonimaStr 	= ParamUtil.getString(actionRequest, DenunciaPortletKeys.ANONIMA, "");

        long tipo 	    = ParamUtil.getLong(actionRequest, DenunciaPortletKeys.TIPO, 0);
        long catConsulta  = ParamUtil.getLong(actionRequest, DenunciaPortletKeys.CATEGORIA_CONSULTA, 0);
        long catDenuncia  = ParamUtil.getLong(actionRequest, DenunciaPortletKeys.CATEGORIA_DENUNCIA, 0);

        String asunto       = ParamUtil.getString(actionRequest, DenunciaPortletKeys.ASUNTO, "");
        String descripcion  = ParamUtil.getString(actionRequest, DenunciaPortletKeys.DESCRIPTION, "");

        UploadPortletRequest uploadRequest = PortalUtil.getUploadPortletRequest(actionRequest);
        String fileName     = uploadRequest.getFileName(DenunciaPortletKeys.ADJUNTO);
        File file           = (File) uploadRequest.getFile(DenunciaPortletKeys.ADJUNTO);

        ResourceBundle bundle = ResourceBundle.getBundle("content/Language", themeDisplay.getUser().getLocale());
        String mailSubject = LanguageUtil.get(bundle, "mailing.mail.subject.denuncia.user");

        boolean anonima = false;
        if(DenunciaPortletKeys.ANONIMA_YES.equals(anonimaStr)){
            anonima = true;
        }

        long categoria = catConsulta;
        if ("1".equals(tipo)) {
            categoria = catDenuncia;
        }

        String compCod = String.valueOf(companyId);
        Comp comp = CompLocalServiceUtil.fetchComp(companyId);
        List<UserCompany> usersFromCompany = new ArrayList<>();
        if(Validator.isNotNull(comp) && comp.getName().length() >= 4){
            compCod = comp.getName().substring(0,4).toUpperCase();
            usersFromCompany = UserCompanyLocalServiceUtil.getUsersFromCompany(comp.getCompId());
        }

        String categoriaCod = String.valueOf(categoria);
        Categoria categoriaObj = CategoriaLocalServiceUtil.fetchCategoria(Long.valueOf(categoria));
        if(Validator.isNotNull(categoriaObj)){
            categoriaCod = categoriaObj.getCodigo();
        }

        List<Denuncia> denunciasFromCompany = DenunciaLocalServiceUtil.getDenunciasFromCompany(companyId);

        Date d=new Date();
        int year=d.getYear();

        String codigo = compCod + "-" + categoriaCod + "-" + (year + 1900) + "-" + (denunciasFromCompany.size() + 1);

        Denuncia denuncia = DenunciaLocalServiceUtil.createDenuncia(CounterLocalServiceUtil.increment(Denuncia.class.getName()));

        denuncia.setCodigo(codigo);
        denuncia.setCif(cif);
        denuncia.setCompId(companyId);
        denuncia.setVinculacion(vinculacion);
        denuncia.setEmail(email);
        denuncia.setNombre(name);
        denuncia.setApellidos(lastName);
        denuncia.setNif(nif);
        denuncia.setTelefono(phone);
        denuncia.setAnonimo(anonima);
        denuncia.setTipo(tipo);
        denuncia.setCategoria(categoria);
        denuncia.setAsunto(asunto);
        denuncia.setDescripcion(descripcion);
        denuncia.setCreateDate(new Date());

        Calendar calendar = Calendar.getInstance();
        denuncia.setCreateDate(calendar.getTime());

        SimpleDateFormat dateFormat = new SimpleDateFormat();
        TimeZone.getTimeZone("Europe/Madrid");

        dateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Madrid"));

        DenunciaLocalServiceUtil.updateDenuncia(denuncia);

        //Save file
        ServiceContext serviceContext = null;
        try {
            serviceContext = ServiceContextFactory.getInstance(DLFileEntry.class.getName(), actionRequest);
        } catch (PortalException e) {
            e.printStackTrace();
        }

        if(Validator.isNotNull(file)) {
            DLFileEntry dlFileEntry = CanalEticoDocumentLibraryUtil.saveFile(
                    themeDisplay.getScopeGroupId(),
                    themeDisplay.getScopeGroup().getCreatorUserId(),
                    DenunciaPortletKeys.FOLDER_DESCRIPTION + codigo,
                    DenunciaPortletKeys.PARENT_FOLDER_NAME,
                    codigo,
                    fileName,
                    file,
                    serviceContext);
        }

        DenunciaMailing.denunciaToUser(
                email,
                name,
                Validator.isNotNull(comp)?comp.getName():compCod,
                codigo,
                asunto,
                descripcion,
                Validator.isNotNull(categoriaObj)?categoriaObj.getNombre():categoriaCod,
                file,
                "".equals(fileName)?"-":fileName,
                themeDisplay.getLocale());

        // Send mail to administrators.
        for(UserCompany uc:usersFromCompany){

            DenunciaMailing.denunciaToAdmin(
                    uc.getUserId(),
                    Validator.isNotNull(comp)?comp.getName():compCod,
                    codigo,
                    asunto,
                    descripcion,
                    Validator.isNotNull(categoriaObj)?categoriaObj.getNombre():categoriaCod,
                    file,
                    "".equals(fileName)?"-":fileName,
                    themeDisplay.getLocale());
        }


        actionResponse.setRenderParameter(DenunciaPortletKeys.COMPANY_IDENTIFICATION, companyIdentification);
        actionResponse.setRenderParameter(DenunciaPortletKeys.CODIGO, codigo);
        actionResponse.setRenderParameter("jspPage", "/success.jsp");

        return false;
    }
    
}

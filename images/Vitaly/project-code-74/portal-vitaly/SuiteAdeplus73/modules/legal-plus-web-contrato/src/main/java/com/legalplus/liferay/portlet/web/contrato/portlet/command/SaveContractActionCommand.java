package com.legalplus.liferay.portlet.web.contrato.portlet.command;

import com.legalplus.liferay.portlet.legalplus.manager.model.ContratoCompany;
import com.legalplus.liferay.portlet.legalplus.manager.service.ContratoCompanyLocalServiceUtil;
import com.legalplus.liferay.portlet.legalplus.manager.service.persistence.ContratoCompanyPK;
import com.legalplus.liferay.portlet.web.contrato.constants.WebContratoPortletKeys;
import com.legalplus.liferay.portlet.web.contrato.util.MailUtil;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.document.library.kernel.model.DLFolder;
import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.*;
import java.io.IOException;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + WebContratoPortletKeys.WEBCONTRATO,
                "mvc.command.name=/contract/save"
        },
        service = MVCActionCommand.class
)
public class SaveContractActionCommand implements MVCActionCommand {

    private static Log _log = LogFactoryUtil.getLog(SaveContractActionCommand.class);

    private static final String FIELD_DELIMITER = ";";
    private static final String PARENT_FOLDER = "EMPRESAS";

    @Override
    public boolean processAction(ActionRequest actionRequest, ActionResponse actionResponse) throws PortletException {
        try {
            ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
            String compId =  ParamUtil.getString(actionRequest, WebContratoPortletKeys.COMPANY_ID);

            String[] ccaaIds = ParamUtil.getStringValues(actionRequest, "cmb_ccaa");
            String[] aytoIds = ParamUtil.getStringValues(actionRequest, "cmb_ayto");
            String[] cnaesIds = ParamUtil.getStringValues(actionRequest, "cnaes");

            String familiaPRL = ParamUtil.getString(actionRequest, WebContratoPortletKeys.FAMILIA_PRL);
            String familiaMedioAmbiente = ParamUtil.getString(actionRequest, WebContratoPortletKeys.FAMILIA_MEDIO_AMBIENTE);
            String familiaSeguridadIndustrial = ParamUtil.getString(actionRequest, WebContratoPortletKeys.FAMILIA_SEGURIDAD_INDUSTRIAL);
            String familiaAlimentaria = ParamUtil.getString(actionRequest, WebContratoPortletKeys.FAMILIA_SEGURIDAD_ALIMENTARIA);

            String familias = "";
            familias += "true".equals(familiaPRL)?"0;":"";
            familias += "true".equals(familiaMedioAmbiente)?"1;":"";
            familias += "true".equals(familiaSeguridadIndustrial)?"2;":"";
            familias += "true".equals(familiaAlimentaria)?"3;":"";

            String ccaa = String.join(FIELD_DELIMITER, ccaaIds);
            String ayto = String.join(FIELD_DELIMITER, aytoIds);
            String cnaes = String.join(FIELD_DELIMITER, cnaesIds);

            ContratoCompany contract = ContratoCompanyLocalServiceUtil.fetchByCompId(Long.valueOf(compId));
            if (Validator.isNull(contract)) {
                long contractId = CounterLocalServiceUtil.increment(ContratoCompany.class.getName());

                ContratoCompanyPK contratoCompanyPK = new ContratoCompanyPK();
                contratoCompanyPK.setContractId(contractId);
                contratoCompanyPK.setCompId(Long.valueOf(compId));

                contract = ContratoCompanyLocalServiceUtil.createContratoCompany(contratoCompanyPK);
            }

            contract.setFamilia(familias);
            contract.setCcaa(ccaa);
            contract.setAyuntamiento(ayto);
            contract.setCnaes(cnaes);

            ContratoCompanyLocalServiceUtil.updateContratoCompany(contract);

            String folder = "EMPRESA_" + compId;
            createFolder(actionRequest, themeDisplay, folder);

            MailUtil.sendEmail(themeDisplay.getUser(), Long.valueOf(compId));

            SessionMessages.add(actionRequest, "contrato-save-success");

            String portletName = (String) actionRequest.getAttribute(WebKeys.PORTLET_ID);
            PortletURL redirectURL = PortletURLFactoryUtil.create(actionRequest, portletName, themeDisplay.getLayout().getPlid(), PortletRequest.RENDER_PHASE);
            redirectURL.setParameter("mvcRenderCommandName", "/");
            redirectURL.setParameter(WebContratoPortletKeys.COMPANY_ID, compId);

            actionResponse.sendRedirect(redirectURL.toString());

            return false;
        } catch (PortalException | IOException e) {
            _log.error(e, e);
            throw new PortletException(e);
        }
    }

    public void createFolder(ActionRequest actionRequest, ThemeDisplay themeDisplay, String name) throws PortalException {
        long repositoryId = themeDisplay.getScopeGroupId();
        long parentFolderId = DLFolderConstants.DEFAULT_PARENT_FOLDER_ID;

        Folder folder = DLAppServiceUtil.getFolder(themeDisplay.getScopeGroupId(), parentFolderId, PARENT_FOLDER);

        try {
            DLAppServiceUtil.getFolder(themeDisplay.getScopeGroupId(), folder.getFolderId(), name);
        } catch (PortalException | SystemException e) {
            ServiceContext serviceContext = ServiceContextFactory.getInstance(DLFolder.class.getName(), actionRequest);
            DLAppServiceUtil.addFolder(repositoryId, folder.getFolderId(), name, "", serviceContext);
        }
    }
}

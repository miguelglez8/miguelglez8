package com.canal.etico.liferay.portlet.admin.denuncia.web.action;

import com.canal.etico.liferay.portlet.admin.denuncia.web.constants.AdminDenunciaPortletKeys;
import com.canal.etico.liferay.portlet.canal.manager.model.Denuncia;
import com.canal.etico.liferay.portlet.canal.manager.service.DenunciaLocalServiceUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + AdminDenunciaPortletKeys.ADMINDENUNCIA,
                "mvc.command.name=/denuncia/asunto"
        },
        service = MVCActionCommand.class
)
public class SaveAsuntoMVCActionCommand implements MVCActionCommand {

    @Override
    public boolean processAction(ActionRequest actionRequest, ActionResponse actionResponse) throws PortletException {

        ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

        long denunciaId = ParamUtil.getLong(actionRequest, AdminDenunciaPortletKeys.DENUNCIA_ID, 0);
        String asunto 	= ParamUtil.getString(actionRequest, AdminDenunciaPortletKeys.ASUNTO, "");

        if(denunciaId > 0) {

            Denuncia denuncia = DenunciaLocalServiceUtil.fetchDenuncia(denunciaId);

            if(Validator.isNotNull(denuncia)){

                //Save subject for denuncia.
                denuncia.setAsunto(asunto);

                DenunciaLocalServiceUtil.updateDenuncia(denuncia);
            }

        }

        actionResponse.setRenderParameter( AdminDenunciaPortletKeys.DENUNCIA_ID, String.valueOf(denunciaId));
        actionResponse.setRenderParameter("jspPage", "/denuncia.jsp");

        return false;
    }
    
}

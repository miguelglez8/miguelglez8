package com.canal.etico.liferay.portlet.comunicado.action;

import com.canal.etico.liferay.portlet.commons.web.constants.CanalEticoCommonsPortletKeys;
import com.canal.etico.liferay.portlet.comunicado.constants.CanalEticoV2ComunicadosPortletKeys;
import com.canal.etico.liferay.portlet.mailing.web.v2.mail.TaskComunicados;
import com.canal.etico.v2.liferay.portlet.canal.manager.model.Comunicado;
import com.canal.etico.v2.liferay.portlet.canal.manager.model.ComunicadoAdicional;
import com.canal.etico.v2.liferay.portlet.canal.manager.service.CategoriaLocalServiceUtil;
import com.canal.etico.v2.liferay.portlet.canal.manager.service.ComunicadoAdicionalLocalServiceUtil;
import com.canal.etico.v2.liferay.portlet.canal.manager.service.ComunicadoLocalServiceUtil;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskManagerUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.servlet.SessionMessages;
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
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name="+ CanalEticoV2ComunicadosPortletKeys.CANALETICOV2COMUNICADOS,
                "mvc.command.name=/aditional/save"
        },
        service = MVCActionCommand.class
)
public class SaveAditionalInfoComunicado implements MVCActionCommand {

    private static Log _log = LogFactoryUtil.getLog(SaveAditionalInfoComunicado.class);
    @Override
    public boolean processAction(ActionRequest actionRequest, ActionResponse actionResponse) throws PortletException {
        String codigoComunicado = "", descripcion = "", asunto = "";
        ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
       try{
            asunto= ParamUtil.getString(actionRequest,"asuntoAdicional", StringPool.BLANK);
            descripcion= ParamUtil.getString(actionRequest,"descripcionAdicional", StringPool.BLANK);
            codigoComunicado= ParamUtil.getString(actionRequest,"codigoComunicado", StringPool.BLANK);
            UploadPortletRequest uploadRequest = PortalUtil.getUploadPortletRequest(actionRequest);
            themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
            String fileId="";
            ComunicadoAdicional comAdic= ComunicadoAdicionalLocalServiceUtil.createComunicadoAdicional(CounterLocalServiceUtil.increment(ComunicadoAdicional.class.getName()));
            comAdic.setAsunto(asunto);
            comAdic.setDescripcion(descripcion);
            comAdic.setCodigoComunicado(codigoComunicado);
            comAdic.setCreateDate(new Date());
            try {
                fileId = SaveNewComunicado.fileAdjuntos(uploadRequest, actionRequest, codigoComunicado, themeDisplay, false);
            }catch (Exception e){
                _log.info("######ERROR####"+e.getMessage());
            }
            comAdic.setFileId(fileId);
            ComunicadoAdicionalLocalServiceUtil.updateComunicadoAdicional(comAdic);

        }catch (Exception e){
            _log.info("######ERROR####"+e.getMessage());
           SessionMessages.add(actionRequest, "comunicado-save-success");
           actionResponse.setRenderParameter("jspPage", "/error-add.jsp");
            return false;
        }

        Comunicado comunicado = ComunicadoLocalServiceUtil.getAllComunicadosByCodigo(codigoComunicado).get(0);


       //Email
        Map map = new HashMap();
        map.put("action", CanalEticoCommonsPortletKeys.EMAIL_NUEVA_INFORMACION);

        map.put("codigo", comunicado.getCodigo());
        if(Validator.isNotNull(comunicado.getCategoria())  && comunicado.getCategoria() != 0){
            map.put("categoria",CategoriaLocalServiceUtil.fetchCategoria(comunicado.getCategoria()).getNombre());
        }else{
            map.put("categoria", "--");
        }

        //map.put("categoria", (CategoriaLocalServiceUtil.getCategoria(comunicado.getCategoria())).getNombre());
        map.put("email", comunicado.getEmail()); //Si tiene email manda al usuario, sino solo al administrador
        map.put("userID", themeDisplay.getUser().getUserId());
        map.put("asunto", comunicado.getAsunto());
        map.put("consulta", comunicado.getDescripcion());

        map.put("url", themeDisplay.getURLCurrent());
        map.put("portalURL",themeDisplay.getPortalURL());
        /*String registroId =
                com.canal.etico.v2.liferay.portlet.canal.manager.service.CompLocalServiceUtil.fetchComp(compId).getUrlId();
        map.put("registroId", registroId);

        _log.info("############### compId : " +  compId);
        _log.info("############### registroId : " + registroId);
        _log.info("############### getCompId : " + com.canal.etico.v2.liferay.portlet.canal.manager.service.CompLocalServiceUtil.fetchComp(compId).getCompId());
        */
        _log.info("############### url : " +  themeDisplay.getURLCurrent());
        _log.info("############### portalURL : " + themeDisplay.getPortalURL());






        _log.info("Envio correo back Canal Ético");

        try {
            BackgroundTaskManagerUtil.addBackgroundTask(themeDisplay.getUserId(), themeDisplay.getScopeGroupId(),
                    TaskComunicados.class.getName(), TaskComunicados.class.getName(), map,
                    new ServiceContext());
        } catch (PortalException e) {
            e.printStackTrace();
        }

        SessionMessages.add(actionRequest, "comunicado-save-success");
        actionResponse.setRenderParameter("jspPage", "/sucess-add.jsp");
        actionResponse.setRenderParameter("idCom",String.valueOf(comunicado.getComunicadoId()));
        actionResponse.setRenderParameter("compId",String.valueOf(comunicado.getCompId()));
        return true;
    }
}

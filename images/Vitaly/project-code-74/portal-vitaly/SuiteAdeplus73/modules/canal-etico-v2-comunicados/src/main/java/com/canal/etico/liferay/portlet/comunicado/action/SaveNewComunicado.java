package com.canal.etico.liferay.portlet.comunicado.action;

import com.adeplus.liferay.portlet.suite.manager.model.Comp;
import com.adeplus.liferay.portlet.suite.manager.service.CompLocalServiceUtil;
import com.canal.etico.liferay.portlet.commons.web.constants.CanalEticoCommonsPortletKeys;
import com.canal.etico.liferay.portlet.commons.web.file.CanalEticoDocumentLibraryUtil;
import com.canal.etico.liferay.portlet.commons.web.util.CanalEticoUtil;
import com.canal.etico.liferay.portlet.comunicado.constants.CanalEticoV2ComunicadosPortletKeys;
import com.canal.etico.liferay.portlet.comunicado.util.CaptchaV2response;
import com.canal.etico.liferay.portlet.comunicado.util.NetworkHelper;
import com.canal.etico.liferay.portlet.mailing.web.v2.mail.TaskComunicados;
import com.canal.etico.v2.liferay.portlet.canal.manager.model.Comunicado;
import com.canal.etico.v2.liferay.portlet.canal.manager.model.GrupoEmpresa;
import com.canal.etico.v2.liferay.portlet.canal.manager.service.CategoriaLocalServiceUtil;
import com.canal.etico.v2.liferay.portlet.canal.manager.service.ComunicadoLocalServiceUtil;
import com.canal.etico.v2.liferay.portlet.canal.manager.service.GrupoEmpresaLocalServiceUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskManagerUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.upload.UploadRequest;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import org.osgi.service.component.annotations.Component;
import com.canal.etico.liferay.portlet.mailing.web.v2.mail.ComunicadoMailing;


import javax.portlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.*;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name="+CanalEticoV2ComunicadosPortletKeys.CANALETICOV2COMUNICADOS,
                "mvc.command.name=/company/save"
        },
        service = MVCActionCommand.class
)
public class SaveNewComunicado implements MVCActionCommand {
    private static Log _log = LogFactoryUtil.getLog(SaveNewComunicado.class);

    @Override
    public boolean processAction(ActionRequest actionRequest, ActionResponse actionResponse) throws PortletException {
        HttpServletRequest request= PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(actionRequest));

        String json = ParamUtil.getString(actionRequest,"dataJson");

        //String json = request.getParameter("dataJson");
        //_log.info("#################### JSON (1): " + json);

        ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
        ParamUtil.getString(request, "dataJson");
        JSONArray jsonArray = null;
        JSONObject jsonObject = null;
        Comunicado comunicado = null;
        String codigo = null;
        boolean anonimo = false;
        long compId = 0;
        String nombre = StringPool.BLANK;
        String apellidos = StringPool.BLANK;
        String mail = StringPool.BLANK;
        String telefono = StringPool.BLANK;
        String codPostal = StringPool.BLANK;
        String otro = StringPool.BLANK;
        String asunto = StringPool.BLANK;
        String observaciones = StringPool.BLANK;
        String descripcion = StringPool.BLANK;
        String vinculacion = StringPool.BLANK;
        String nif = StringPool.BLANK;
        String datosNotificados = StringPool.BLANK;
        String idFiles = StringPool.BLANK;
        String idioma="es_ES";
        UploadPortletRequest uploadRequest = PortalUtil.getUploadPortletRequest(actionRequest);
        long idGrupo = 0;

        boolean notificado = false;
        String captchaSecreto = CanalEticoV2ComunicadosPortletKeys.PRIVATE_KEY_CAPTCHA;
        String gRecaptchaResponse = ParamUtil.getString(actionRequest,"g-recaptcha-response");
        String recapUrlParams = "secret=" + captchaSecreto + "&response=" + gRecaptchaResponse;

        if (Validator.isNotNull(gRecaptchaResponse)) {

            try {

                String jsonGoogle = NetworkHelper.postContent("https://www.google.com/recaptcha/api/siteverify", recapUrlParams);
                comunicado = ComunicadoLocalServiceUtil.createComunicado(CounterLocalServiceUtil.increment(Comunicado.class.getName()));

                Gson gson = new GsonBuilder().create();
                CaptchaV2response objeto = gson.fromJson(jsonGoogle, CaptchaV2response.class);

                if(!objeto.isSuccess()){
                    _log.info("################ ERRRORRRRR CAPTCHA NO SUCCES");
                    actionResponse.setRenderParameter("jspPage", "/error.jsp");
                }


                do {
                    codigo = CanalEticoUtil.generatePasswordRandon();
                } while (ComunicadoLocalServiceUtil.getAllComunicadosByCodigo(codigo).size() >= 1);


                //Cuando se implemente el finder esta linea se borra y se seteara con la variable codigo

                comunicado.setCodigo(codigo);
                if (json.isEmpty()) {
                    /*jsonArray = JSONFactoryUtil.createJSONArray();*/
                    actionResponse.setRenderParameter("jspPage", "/error.jsp");
                    return false;
                }

                //for(int i = 0; i < jsonArray.length(); i++){
                jsonObject = JSONFactoryUtil.createJSONObject(json);//jsonArray.getJSONObject(i);
                anonimo = jsonObject.getBoolean(CanalEticoV2ComunicadosPortletKeys.ANONYMOUS);
                notificado = jsonObject.getBoolean(CanalEticoV2ComunicadosPortletKeys.INFORMADO);
                if (notificado) {
                    datosNotificados = jsonObject.getString(CanalEticoV2ComunicadosPortletKeys.NOTIFICION);
                }
                idGrupo = Long.valueOf(jsonObject.getString(CanalEticoV2ComunicadosPortletKeys.EMPRESAGRUPO));
                compId = Long.valueOf(jsonObject.getString(CanalEticoV2ComunicadosPortletKeys.COMP_ID));
                if (!anonimo) {
                    mail = jsonObject.getString(CanalEticoV2ComunicadosPortletKeys.EMAIL);
                    nombre = jsonObject.getString(CanalEticoV2ComunicadosPortletKeys.NOMBRE);
                    apellidos = jsonObject.getString(CanalEticoV2ComunicadosPortletKeys.APELLIDOS);
                    telefono = jsonObject.getString(CanalEticoV2ComunicadosPortletKeys.TELEFONO);
                    nif = jsonObject.getString(CanalEticoV2ComunicadosPortletKeys.NIF);
                } else {
                    mail = jsonObject.getString(CanalEticoV2ComunicadosPortletKeys.EMAIL_ANONYMOUS);
                }

                otro = jsonObject.getString(CanalEticoV2ComunicadosPortletKeys.OTROS_ANONYMOUS);
                codPostal = jsonObject.getString(CanalEticoV2ComunicadosPortletKeys.POSTAL_ANONYMOUS);
                vinculacion = jsonObject.getString(CanalEticoV2ComunicadosPortletKeys.RELACION);
                asunto = jsonObject.getString(CanalEticoV2ComunicadosPortletKeys.ASUNTO);
                descripcion = jsonObject.getString(CanalEticoV2ComunicadosPortletKeys.DESCRIPCION);
                idioma = jsonObject.getString(CanalEticoV2ComunicadosPortletKeys.IDIOMA);
                GrupoEmpresa ge = GrupoEmpresaLocalServiceUtil.fetchGrupoEmpresa(idGrupo);
                if (Validator.isNotNull(ge)) {
                    comunicado.setNombreEmpresa(ge.getEntidad());
                    comunicado.setCif(ge.getCif());
                } else {
                    comunicado.setNombreEmpresa(CompLocalServiceUtil.fetchComp(compId).getName());
                    comunicado.setCif(CompLocalServiceUtil.fetchComp(compId).getCif());

                }
                Date now = new Date();
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(now);

                calendar.add(Calendar.DAY_OF_YEAR, 90);


                Date fechaCaducidad = calendar.getTime();
                comunicado.setCompId(compId);
                comunicado.setAnonimo(anonimo);
                comunicado.setGestorId("0");


                if (!anonimo) {
                    comunicado.setEmail(mail);
                    comunicado.setNombre(nombre);
                    comunicado.setApellidos(apellidos);
                    comunicado.setTelefono(telefono);
                    comunicado.setNif(nif);
                } else {
                    comunicado.setEmail(mail);
                }

                comunicado.setOtros(otro);
                comunicado.setDireccionPostal(codPostal);
                comunicado.setVinculacion(vinculacion);
                comunicado.setEstado(0);
                comunicado.setCategoria(0);
                comunicado.setAsunto(asunto);
                comunicado.setDescripcion(descripcion);
                comunicado.setObservaciones(observaciones);
                comunicado.setCreateDate(now);
                comunicado.setCaducidadDate(fechaCaducidad);
                comunicado.setNotificacion(datosNotificados);
                comunicado.setIdGrupoEmpresa(idGrupo);
                idFiles = fileAdjuntos(uploadRequest, actionRequest, codigo, themeDisplay, true);
                comunicado.setAdjuntosId(idFiles);
                ComunicadoLocalServiceUtil.updateComunicado(comunicado);


                //if(!comunicado.getEmail().isEmpty()) {
                File fichero0 = (File) uploadRequest.getFile("inpFile_00");
                Map map = new HashMap();
                map.put("action", CanalEticoCommonsPortletKeys.EMAIL_NUEVO_COMUNICADO);

                map.put("codigo", comunicado.getCodigo());
                map.put("companiaId", compId);

                map.put("categoria", comunicado.getCategoriaDesc()); // siendo nuevo realmente no esta categorizado
                //map.put("categoria", (CategoriaLocalServiceUtil.getCategoria(comunicado.getCategoria())).getNombre());
                map.put("email", comunicado.getEmail()); //Si tiene email manda al usuario, sino solo al administrador
                map.put("userID", themeDisplay.getUser().getUserId());
                map.put("asunto", comunicado.getAsunto());
                map.put("consulta", comunicado.getDescripcion());

                map.put("url", themeDisplay.getURLCurrent());
                map.put("portalURL", themeDisplay.getPortalURL());
                map.put("comunicadoId", comunicado.getComunicadoId());
                map.put("idioma", idioma);
                _log.info("idioma: "+idioma);
                String registroId =
                        com.canal.etico.v2.liferay.portlet.canal.manager.service.CompLocalServiceUtil.fetchComp(compId).getUrlId();
                map.put("registroId", registroId);


                map.put("files", idFiles);
                map.put("isAdmin", true);

                _log.info("Envio correo back Canal Ético");

                BackgroundTaskManagerUtil.addBackgroundTask(themeDisplay.getUserId(), themeDisplay.getScopeGroupId(),
                        TaskComunicados.class.getName(), TaskComunicados.class.getName(), map,
                        new ServiceContext());

                //}


                SessionMessages.add(actionRequest, "comunicado-save-success");
                //actionResponse.setRenderParameter("idCom",String.valueOf(comunicado.getComunicadoId()));
                //actionResponse.setRenderParameter("compId",String.valueOf(compId));
                //actionResponse.sendRedirect("/sucess.jsp");

                actionResponse.sendRedirect(redirectURL(actionRequest, themeDisplay, String.valueOf(comunicado.getComunicadoId()),
                        String.valueOf(compId), "sucessComunicado"));

                //actionResponse.setRenderParameter("jspPage", "/sucess.jsp");

            /*actionResponse.sendRedirect(themeDisplay.getPortalURL()
                    + "/web/" + themeDisplay.getScopeGroupName() + "/sucess.jsp");*/

                return true;
            } catch (Exception e) {
                _log.info("################ ERRRORRRRR ");
                e.printStackTrace();
                actionResponse.setRenderParameter("jspPage", "/error.jsp");

            }
        }else{
            _log.info("################ ERRRORRRRR CAPTCHA VACIO");
            actionResponse.setRenderParameter("jspPage", "/error.jsp");
        }
        return false;
    }

    private String redirectURL(ActionRequest actionRequest, ThemeDisplay themeDisplay, String idCom, String compId, String renderUrl) {
        String portletName = (String) actionRequest.getAttribute(WebKeys.PORTLET_ID);
        PortletURL redirectURL = PortletURLFactoryUtil.create(actionRequest, portletName, themeDisplay.getLayout().getPlid(),
                PortletRequest.RENDER_PHASE);
        redirectURL.setParameter("mvcRenderCommandName", renderUrl);
        redirectURL.setParameter("idCom", idCom);
        redirectURL.setParameter("compId", compId);
        return redirectURL.toString();
    }

    public static String fileAdjuntos(UploadRequest uploadRequest,ActionRequest actionRequest,String codigo, ThemeDisplay themeDisplay, boolean isNew){
        String idFiles=StringPool.BLANK;
        DLFileEntry dlFileEntry0 = null, dlFileEntry1 = null, dlFileEntry2 = null, dlFileEntry3 = null, dlFileEntry4 = null;
        String stringFile0 = "", stringFile1 = "", stringFile2 = "", stringFile3 = "", stringFile4 = "";
        File fichero0 = null, fichero1 = null, fichero2 = null, fichero3 = null, fichero4 = null;
        if(isNew){
            stringFile0 = uploadRequest.getFileName("inpFile_00");
            fichero0 = (File) uploadRequest.getFile("inpFile_00");

            stringFile1 = uploadRequest.getFileName("inpFile_01");
            fichero1 = (File) uploadRequest.getFile("inpFile_01");

            stringFile2 = uploadRequest.getFileName("inpFile_02");
            fichero2 = (File) uploadRequest.getFile("inpFile_02");

            stringFile3 = uploadRequest.getFileName("inpFile_03");
            fichero3 = (File) uploadRequest.getFile("inpFile_03");

            stringFile4 = uploadRequest.getFileName("inpFile_04");
            fichero4 = (File) uploadRequest.getFile("inpFile_04");
        }else{
            stringFile0 = uploadRequest.getFileName("inpFile_0100");
            fichero0 = (File) uploadRequest.getFile("inpFile_0100");

            stringFile1 = uploadRequest.getFileName("inpFile_0101");
            fichero1 = (File) uploadRequest.getFile("inpFile_0101");

            stringFile2 = uploadRequest.getFileName("inpFile_0102");
            fichero2 = (File) uploadRequest.getFile("inpFile_0102");

            stringFile3 = uploadRequest.getFileName("inpFile_0103");
            fichero3 = (File) uploadRequest.getFile("inpFile_0103");

            stringFile4 = uploadRequest.getFileName("inpFile_0104");
            fichero4 = (File) uploadRequest.getFile("inpFile_0104");
        }


        if(Validator.isFileName(stringFile0)||Validator.isFileName(stringFile1)||Validator.isFileName(stringFile2)
            ||Validator.isFileName(stringFile3)||Validator.isFileName(stringFile4)) {
            ServiceContext serviceContext = null;
            try {

                serviceContext = ServiceContextFactory.getInstance(DLFileEntry.class.getName(), actionRequest);
                //El usuario sera un usuario tonto solo habilitado para subir archivos de momento utilizamos agarciap

                if(Validator.isFileName(stringFile0)) {
                    idFiles+=creacionFicheros(dlFileEntry0,stringFile0,fichero0,themeDisplay,codigo,serviceContext);
                }
                if(Validator.isFileName(stringFile1)) {
                    idFiles+=creacionFicheros(dlFileEntry1,stringFile1,fichero1,themeDisplay,codigo,serviceContext);
                }
                if(Validator.isFileName(stringFile2)) {
                    idFiles+=creacionFicheros(dlFileEntry2,stringFile2,fichero2,themeDisplay,codigo,serviceContext);
                }
                if(Validator.isFileName(stringFile3)) {
                    idFiles+=creacionFicheros(dlFileEntry3,stringFile3,fichero3,themeDisplay,codigo,serviceContext);
                }
                if(Validator.isFileName(stringFile4)) {
                    idFiles+=creacionFicheros(dlFileEntry4,stringFile4,fichero4,themeDisplay,codigo,serviceContext);
                }
                idFiles=idFiles.substring(0,idFiles.length()-1);

            } catch (PortalException e) {
                throw new RuntimeException(e);
            }


        }
        return idFiles;
    }

    public static String creacionFicheros(DLFileEntry dlFileEntry, String nombreFichero, File fichero, ThemeDisplay themeDisplay, String codigo, ServiceContext serviceContext){

        String idFile=StringPool.BLANK;
        if(Validator.isFileName(nombreFichero)) {
            dlFileEntry=CanalEticoDocumentLibraryUtil.saveFile(
                    themeDisplay.getScopeGroupId(),
                    themeDisplay.getScopeGroup().getCreatorUserId(),
                    CanalEticoV2ComunicadosPortletKeys.COMUNICACION_ADJUNTO_DESCRIPTION,
                    CanalEticoV2ComunicadosPortletKeys.PARENT_FOLDER_NAME,
                    codigo,
                    nombreFichero,
                    fichero,
                    serviceContext);
            if (Validator.isNotNull(dlFileEntry)) {
                idFile += dlFileEntry.getFileEntryId() + StringPool.COMMA;
            }
        }

        return idFile;
    }

}

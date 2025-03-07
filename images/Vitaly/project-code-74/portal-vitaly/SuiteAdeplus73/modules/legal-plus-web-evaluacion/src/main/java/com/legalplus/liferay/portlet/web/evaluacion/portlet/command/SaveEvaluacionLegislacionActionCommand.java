package com.legalplus.liferay.portlet.web.evaluacion.portlet.command;

import com.legalplus.liferay.portlet.legalplus.manager.model.EvalLegislacion;
import com.legalplus.liferay.portlet.legalplus.manager.service.EvalLegislacionLocalServiceUtil;
import com.legalplus.liferay.portlet.legalplus.manager.service.persistence.EvalLegislacionPK;
import com.legalplus.liferay.portlet.web.evaluacion.constants.WebEvaluacionPortletKeys;
import com.legalplus.liferay.portlet.web.evaluacion.utils.DLUtil;
import com.legalplus.liferay.portlet.web.evaluacion.utils.EvaluacionUtil;
import com.legalplus.liferay.portlet.web.evaluacion.utils.RedirectUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
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
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + WebEvaluacionPortletKeys.WEBEVALUACION,
                "mvc.command.name=/evaluacion/legislacion/save"
        },
        service = MVCActionCommand.class
)
public class SaveEvaluacionLegislacionActionCommand implements MVCActionCommand {

    private static final Log _log = LogFactoryUtil.getLog(SaveEvaluacionLegislacionActionCommand.class);

    @Override
    public boolean processAction(ActionRequest actionRequest, ActionResponse actionResponse) throws PortletException {
        try {
            ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

            String compId = EvaluacionUtil.getCompany(themeDisplay, actionRequest);
            String legislacionId = EvaluacionUtil.getLegislacion(actionRequest, Long.parseLong(compId));
            long version = ParamUtil.getLong(actionRequest, WebEvaluacionPortletKeys.VERSION_PARAM);

            if (!Validator.isBlank(compId) && !Validator.isBlank(legislacionId)) {

                UploadPortletRequest uploadRequest = PortalUtil.getUploadPortletRequest(actionRequest);

                String fechaEvaluacion = ParamUtil.getString(actionRequest, WebEvaluacionPortletKeys.FECHA_EVALUACION, "");
                String proximaFecha = ParamUtil.getString(actionRequest, WebEvaluacionPortletKeys.FECHA_PROXIMA, "");
                int cumplimiento = ParamUtil.getInteger(actionRequest, WebEvaluacionPortletKeys.CUMPLIMIENTO, 2);
                String observaciones = ParamUtil.getString(actionRequest, WebEvaluacionPortletKeys.OBSERVACIONES, "");
                String sourceFileName = uploadRequest.getFileName(WebEvaluacionPortletKeys.FICHERO);

                ResourceBundle bundle = ResourceBundle.getBundle("content/Language", themeDisplay.getUser().getLocale());
                SimpleDateFormat format = new SimpleDateFormat(LanguageUtil.get(bundle, "legislacion.view.dateFormat"));

                EvalLegislacionPK evalLegislacionPK = new EvalLegislacionPK();
                evalLegislacionPK.setLegislacionId(legislacionId);
                evalLegislacionPK.setCompId(Long.parseLong(compId));
                evalLegislacionPK.setVersion(version);

                /* Obtenemos la evaluación  */
                EvalLegislacion evalLegislacion = version != 0 ?
                        EvalLegislacionLocalServiceUtil.getEvalLegislacion(evalLegislacionPK) :
                        EvalLegislacionLocalServiceUtil.getLastEvalLegislacion(Long.parseLong(compId), legislacionId);

                if (Validator.isNull(evalLegislacion)) {
                    evalLegislacion = EvalLegislacionLocalServiceUtil.createEvalLegislacion(evalLegislacionPK);
                }

                /* Sumamos la versión si no vamos a editar  */
                if (version == 0) {
                    evalLegislacion.setVersion(evalLegislacion.getVersion() + 1);
                }

                /* Revisamos si existe fecha proxima y si la hay creamos las revisión si no existe */
                if (Validator.isNotNull(proximaFecha)) {
                    evalLegislacion.setProxima(format.parse(proximaFecha));
                    crearProximaRevision(evalLegislacion);
                } else {
                    evalLegislacion.setProxima(null);
                    deleteProximaRevision(evalLegislacion);
                }

                /* Editamos/Creamos el documento de la versión  */
                Long fileId = version != 0 ? evalLegislacion.getAdjunto() : 0;
                if (!Validator.isBlank(sourceFileName)) {
                    long fileEntryId = DLUtil.updateFile(actionRequest, uploadRequest, themeDisplay, compId, legislacionId,
                            evalLegislacion.getVersion(), fileId, sourceFileName);
                    evalLegislacion.setAdjunto(fileEntryId);
                }

                evalLegislacion.setFecha(format.parse(fechaEvaluacion));
                evalLegislacion.setCumplimiento(cumplimiento);
                evalLegislacion.setObservaciones(observaciones);
                evalLegislacion.setUsuario(themeDisplay.getUserId());

                EvalLegislacionLocalServiceUtil.updateEvalLegislacion(evalLegislacion);

            } else {

                Map<String, String> params = new HashMap<>();
                params.put("mvcRenderCommandName", "goEvaluacionLegislacion");

                String redirectURL = RedirectUtil.getRedirectURL(actionRequest, themeDisplay, params);
                actionResponse.sendRedirect(redirectURL);
            }

            SessionMessages.add(actionRequest, "evaluacion-save-success");

            Map<String, String> params = new HashMap<>();
            params.put("mvcRenderCommandName", "/");
            params.put(WebEvaluacionPortletKeys.LEGISLACION_PARAM, legislacionId);
            params.put(WebEvaluacionPortletKeys.COMP_PARAM, compId);

            String redirectURL = RedirectUtil.getRedirectURL(actionRequest, themeDisplay, params);
            actionResponse.sendRedirect(redirectURL);

            return false;
        } catch (IOException | ParseException | PortalException e) {
            _log.error(e, e);
            throw new PortletException();
        }
    }

    private EvalLegislacion crearProximaRevision(EvalLegislacion evalLegislacion) {

        EvalLegislacionPK evalLegislacionPK = new EvalLegislacionPK();
        evalLegislacionPK.setLegislacionId(evalLegislacion.getLegislacionId());
        evalLegislacionPK.setCompId(evalLegislacion.getCompId());
        evalLegislacionPK.setVersion(evalLegislacion.getVersion() + 1);

        EvalLegislacion evaluacion = EvalLegislacionLocalServiceUtil.fetchEvalLegislacion(evalLegislacionPK);
        if (Validator.isNull(evaluacion)) {
            evaluacion = EvalLegislacionLocalServiceUtil.createEvalLegislacion(evalLegislacionPK);
            evaluacion.setProxima(null);
            evaluacion.setUsuario(-1);
            evaluacion.setCumplimiento(-1);
        }

        if (evaluacion.getCumplimiento() == -1) {
            evaluacion.setFecha(evalLegislacion.getProxima());
            EvalLegislacionLocalServiceUtil.updateEvalLegislacion(evaluacion);
        }

        return evaluacion;
    }

    private EvalLegislacion deleteProximaRevision(EvalLegislacion evalLegislacion) {

        EvalLegislacionPK evalLegislacionPK = new EvalLegislacionPK();
        evalLegislacionPK.setLegislacionId(evalLegislacion.getLegislacionId());
        evalLegislacionPK.setCompId(evalLegislacion.getCompId());
        evalLegislacionPK.setVersion(evalLegislacion.getVersion() + 1);

        EvalLegislacion evaluacion = EvalLegislacionLocalServiceUtil.fetchEvalLegislacion(evalLegislacionPK);
        if (Validator.isNotNull(evaluacion) && evaluacion.getCumplimiento() == -1) {
            EvalLegislacionLocalServiceUtil.deleteEvalLegislacion(evaluacion);
        }

        return evaluacion;
    }

}

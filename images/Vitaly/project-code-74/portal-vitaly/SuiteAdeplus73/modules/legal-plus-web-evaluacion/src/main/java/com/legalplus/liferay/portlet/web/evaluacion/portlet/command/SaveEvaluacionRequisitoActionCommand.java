package com.legalplus.liferay.portlet.web.evaluacion.portlet.command;

import com.legalplus.liferay.portlet.legalplus.manager.model.*;
import com.legalplus.liferay.portlet.legalplus.manager.service.*;
import com.legalplus.liferay.portlet.legalplus.manager.service.persistence.EvalLegislacionPK;
import com.legalplus.liferay.portlet.legalplus.manager.service.persistence.EvalRequisitoPK;
import com.legalplus.liferay.portlet.web.evaluacion.constants.WebEvaluacionPortletKeys;
import com.legalplus.liferay.portlet.web.evaluacion.utils.DLUtil;
import com.legalplus.liferay.portlet.web.evaluacion.utils.EvaluacionUtil;
import com.legalplus.liferay.portlet.web.evaluacion.utils.RedirectUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.*;
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
                "mvc.command.name=/evaluacion/requisito/save"
        },
        service = MVCActionCommand.class
)
public class SaveEvaluacionRequisitoActionCommand implements MVCActionCommand {

    private static final Log _log = LogFactoryUtil.getLog(SaveEvaluacionRequisitoActionCommand.class);

    @Override
    public boolean processAction(ActionRequest actionRequest, ActionResponse actionResponse) throws PortletException {

        try {
            ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

            String compId = EvaluacionUtil.getCompany(themeDisplay, actionRequest);
            String legislacionId = EvaluacionUtil.getLegislacion(actionRequest, Long.parseLong(compId));
            String requisitoId = ParamUtil.getString(actionRequest, WebEvaluacionPortletKeys.REQUISITO_PARAM);
            long version = ParamUtil.getLong(actionRequest, WebEvaluacionPortletKeys.VERSION_PARAM);

            if (!Validator.isBlank(compId) && !Validator.isBlank(legislacionId)) {

                if (Validator.isBlank(requisitoId)) {

                    EvalLegislacion evalLegislacion = EvalLegislacionLocalServiceUtil.getLastEvalLegislacion(Long.parseLong(compId), legislacionId);
                    boolean isLastCompleted = EvaluacionUtil.isLastEvalRequisitoCompleted(Long.parseLong(compId), legislacionId, evalLegislacion);

                    if (isLastCompleted) {
                        crearEvalRequisitosLegislacion(Long.parseLong(compId), legislacionId);

                    } else {

                        SessionErrors.add(actionRequest, "evaluacion-save-error");

                        Map<String, String> params = new HashMap<>();
                        params.put("mvcRenderCommandName", "/");
                        params.put(WebEvaluacionPortletKeys.LEGISLACION_PARAM, legislacionId);
                        params.put(WebEvaluacionPortletKeys.COMP_PARAM, compId);

                        String redirectURL = RedirectUtil.getRedirectURL(actionRequest, themeDisplay, params);
                        actionResponse.sendRedirect(redirectURL);

                    }

                } else {

                    UploadPortletRequest uploadRequest = PortalUtil.getUploadPortletRequest(actionRequest);

                    String fechaEvaluacion = ParamUtil.getString(actionRequest, WebEvaluacionPortletKeys.FECHA_EVALUACION, "");
                    String proximaFecha = ParamUtil.getString(actionRequest, WebEvaluacionPortletKeys.FECHA_PROXIMA, "");
                    int cumplimiento = ParamUtil.getInteger(actionRequest, WebEvaluacionPortletKeys.CUMPLIMIENTO, 2);
                    String observaciones = ParamUtil.getString(actionRequest, WebEvaluacionPortletKeys.OBSERVACIONES, "");
                    String sourceFileName = uploadRequest.getFileName(WebEvaluacionPortletKeys.FICHERO);

                    ResourceBundle bundle = ResourceBundle.getBundle("content/Language", themeDisplay.getUser().getLocale());
                    SimpleDateFormat format = new SimpleDateFormat(LanguageUtil.get(bundle, "legislacion.view.dateFormat"));

                    EvalRequisitoPK evalRequisitoPK = new EvalRequisitoPK();
                    evalRequisitoPK.setCompId(Long.parseLong(compId));
                    evalRequisitoPK.setLegislacionId(legislacionId);
                    evalRequisitoPK.setRequisitoId(requisitoId);
                    evalRequisitoPK.setVersion(version);

                    /* Obtenemos la evaluación  */
                    EvalRequisito evalRequisito = EvalRequisitoLocalServiceUtil.fetchEvalRequisito(evalRequisitoPK);

                    /* Revisamos si existe fecha proxima y si la hay creamos las revisión si no existe */
                    if (Validator.isNotNull(proximaFecha)) {
                        evalRequisito.setProxima(format.parse(proximaFecha));
                        crearProximaRevision(evalRequisito);
                    } else {
                        evalRequisito.setProxima(null);
                        deleteProximaRevision(evalRequisito);
                    }

                    /* Editamos/Creamos el documento de la versión  */
                    if (!Validator.isBlank(sourceFileName)) {
                        String evaluacionId = legislacionId + StringPool.UNDERLINE + requisitoId;
                        long fileEntryId = DLUtil.updateFile(actionRequest, uploadRequest, themeDisplay, compId, evaluacionId,
                                evalRequisito.getVersion(), evalRequisito.getAdjunto(), sourceFileName);
                        evalRequisito.setAdjunto(fileEntryId);
                    }

                    evalRequisito.setFecha(format.parse(fechaEvaluacion));
                    evalRequisito.setCumplimiento(cumplimiento);
                    evalRequisito.setObservaciones(observaciones);
                    evalRequisito.setUsuario(themeDisplay.getUserId());

                    EvalRequisitoLocalServiceUtil.updateEvalRequisito(evalRequisito);
                    createEvalLegislacion(Long.parseLong(compId), legislacionId, version, themeDisplay, bundle, format);
                }

            } else {

                Map<String, String> params = new HashMap<>();
                params.put("mvcRenderCommandName", "goEvaluacionRequisito");

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
        } catch (IOException | ParseException e) {
            _log.error(e, e);
            throw new PortletException();
        }
    }

    private void crearEvalRequisitosLegislacion(Long compId, String legislacionId) {
        List<Requisito> requisitosList = RequisitoLocalServiceUtil.findRequisitoActivoByLegislacionAndCompId(legislacionId, compId);
        EvalLegislacion evalLegislacion = EvalLegislacionLocalServiceUtil.getLastEvalLegislacion(compId, legislacionId);

        for (Requisito requisito : requisitosList) {
            EvalRequisitoPK evalRequisitoPK = new EvalRequisitoPK();
            evalRequisitoPK.setCompId(compId);
            evalRequisitoPK.setLegislacionId(legislacionId);
            evalRequisitoPK.setRequisitoId(requisito.getRequisitoId());
            evalRequisitoPK.setVersion( Validator.isNotNull(evalLegislacion) ? evalLegislacion.getVersion() + 1 : 1 );

            EvalRequisito evalRequisito = EvalRequisitoLocalServiceUtil.fetchEvalRequisito(evalRequisitoPK);
            if (Validator.isNull(evalRequisito)) {
                evalRequisito = EvalRequisitoLocalServiceUtil.createEvalRequisito(evalRequisitoPK);
                evalRequisito.setFecha(null);
                evalRequisito.setProxima(null);
                evalRequisito.setUsuario(-1);
                evalRequisito.setCumplimiento(-1);

                EvalRequisitoLocalServiceUtil.updateEvalRequisito(evalRequisito);
            }
        }
    }

    private EvalRequisito crearProximaRevision(EvalRequisito evalRequisito) {

        EvalRequisitoPK evalRequisitoPK = new EvalRequisitoPK();
        evalRequisitoPK.setCompId(evalRequisito.getCompId());
        evalRequisitoPK.setLegislacionId(evalRequisito.getLegislacionId());
        evalRequisitoPK.setRequisitoId(evalRequisito.getRequisitoId());
        evalRequisitoPK.setVersion(evalRequisito.getVersion() + 1);

        EvalRequisito evaluacion = EvalRequisitoLocalServiceUtil.fetchEvalRequisito(evalRequisitoPK);
        if (Validator.isNull(evaluacion)) {
            evaluacion = EvalRequisitoLocalServiceUtil.createEvalRequisito(evalRequisitoPK);
            evaluacion.setProxima(null);
            evaluacion.setUsuario(-1);
            evaluacion.setCumplimiento(-1);
        }

        if (evaluacion.getCumplimiento() == -1) {
            evaluacion.setFecha(evalRequisito.getProxima());
            EvalRequisitoLocalServiceUtil.updateEvalRequisito(evaluacion);
        }

        return evaluacion;
    }

    private EvalRequisito deleteProximaRevision(EvalRequisito evalRequisito) {

        EvalRequisitoPK evalRequisitoPK = new EvalRequisitoPK();
        evalRequisitoPK.setCompId(evalRequisito.getCompId());
        evalRequisitoPK.setLegislacionId(evalRequisito.getLegislacionId());
        evalRequisitoPK.setRequisitoId(evalRequisito.getRequisitoId());
        evalRequisitoPK.setVersion(evalRequisito.getVersion() + 1);

        EvalRequisito evaluacion = EvalRequisitoLocalServiceUtil.fetchEvalRequisito(evalRequisitoPK);
        if (Validator.isNotNull(evaluacion) && evaluacion.getCumplimiento() == -1) {
            EvalRequisitoLocalServiceUtil.deleteEvalRequisito(evaluacion);
        }

        return evaluacion;
    }

    private void createEvalLegislacion(long compId, String legislacionId, long version, ThemeDisplay themeDisplay, ResourceBundle bundle, SimpleDateFormat format) {
        Integer eval = EvalRequisitoLocalServiceUtil.isEvalRequisitoVersionCompleted(compId, legislacionId, version);

        if (Validator.isNotNull(eval)) {

            EvalLegislacionPK evalLegislacionPK = new EvalLegislacionPK();
            evalLegislacionPK.setLegislacionId(legislacionId);
            evalLegislacionPK.setCompId(compId);
            evalLegislacionPK.setVersion(version);

            EvalLegislacion evaluacion = EvalLegislacionLocalServiceUtil.fetchEvalLegislacion(evalLegislacionPK);
            if (Validator.isNull(evaluacion)) {
                evaluacion = EvalLegislacionLocalServiceUtil.createEvalLegislacion(evalLegislacionPK);
            }

            evaluacion.setFecha(new Date());
            evaluacion.setProxima(null);
            evaluacion.setUsuario(themeDisplay.getUserId());
            evaluacion.setCumplimiento(eval);

            EvalLegislacionLocalServiceUtil.updateEvalLegislacion(evaluacion);
        }
    }
}

package com.plan.igualdad.liferay.portlet.version.web.portlet.command;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.DateFormatFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.plan.igualdad.liferay.portlet.commons.web.estado.EstadoUtils;
import com.plan.igualdad.liferay.portlet.manager.model.Version;
import com.plan.igualdad.liferay.portlet.manager.service.VersionLocalServiceUtil;
import com.plan.igualdad.liferay.portlet.manager.service.persistence.VersionPK;
import com.plan.igualdad.liferay.portlet.version.web.constants.PlanIgualdadVersionWebPortletKeys;
import com.plan.igualdad.liferay.portlet.version.web.validators.VersionValidator;
import org.osgi.service.component.annotations.Component;

import javax.portlet.*;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + PlanIgualdadVersionWebPortletKeys.PLANIGUALDADVERSIONWEB,
                "mvc.command.name=/version/save"
        },
        service = MVCActionCommand.class
)
public class SaveVersionMVCActionCommand implements MVCActionCommand {

    private static Log _log = LogFactoryUtil.getLog(SaveVersionMVCActionCommand.class);

    private static DateFormat dateFormat = DateFormatFactoryUtil.getSimpleDateFormat("dd/MM/yyyy");

    @Override
    public boolean processAction(ActionRequest actionRequest, ActionResponse actionResponse) throws PortletException {
        try {

            ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
            
            Long compId = ParamUtil.getLong(actionRequest, PlanIgualdadVersionWebPortletKeys.COMPID_PARAM, 0);
            Long versionId = ParamUtil.getLong(actionRequest, PlanIgualdadVersionWebPortletKeys.VERSIONID_PARAM, 0);
            
            String nombre = ParamUtil.getString(actionRequest, PlanIgualdadVersionWebPortletKeys.VERSION_NOMBRE, StringPool.BLANK);
            Date inicioPeriodoDatos = ParamUtil.getDate(actionRequest, PlanIgualdadVersionWebPortletKeys.VERSION_INICIO_DATOS, dateFormat, null);
            Date finPeriodoDatos = ParamUtil.getDate(actionRequest, PlanIgualdadVersionWebPortletKeys.VERSION_FIN_DATOS, dateFormat, null);
            Date inicioPeriodoIgualdad = ParamUtil.getDate(actionRequest, PlanIgualdadVersionWebPortletKeys.VERSION_INICIO_IGUALDAD, dateFormat, null);
            Date finPeriodoIgualdad = ParamUtil.getDate(actionRequest, PlanIgualdadVersionWebPortletKeys.VERSION_FIN_IGUALDAD, dateFormat, null);
            Date fechaBaja = ParamUtil.getDate(actionRequest, PlanIgualdadVersionWebPortletKeys.VERSION_BAJA, dateFormat, null);

            if (!VersionValidator.validVersion(nombre, inicioPeriodoDatos, finPeriodoDatos, inicioPeriodoIgualdad, finPeriodoIgualdad)) {

                SessionErrors.add(actionRequest, "version-save-error");
                actionResponse.sendRedirect(redirectURL(actionRequest, themeDisplay, compId, versionId, "goVersionPlan"));

            } else {

                VersionPK versionPK = new VersionPK();
                versionPK.setVersionId(versionId);
                versionPK.setCompId(compId);

                Version version = VersionLocalServiceUtil.fetchVersion(versionPK);
                if (Validator.isNull(version)) {
                    versionPK.setVersionId(CounterLocalServiceUtil.increment(Version.class.getName()));
                    version = VersionLocalServiceUtil.createVersion(versionPK);
                    
                    EstadoUtils.updateEstado(compId, "1");
                }

                version.setNombre(nombre);
                version.setInicioPeriodoDatos(inicioPeriodoDatos);
                version.setFinPeriodoDatos(finPeriodoDatos);
                version.setInicioPeriodoPlan(inicioPeriodoIgualdad);
                version.setFinPeriodoPlan(finPeriodoIgualdad);
                version.setBaja(fechaBaja);

                VersionLocalServiceUtil.updateVersion(version);

                SessionMessages.add(actionRequest, "version-save-success");
                actionResponse.sendRedirect(redirectURL(actionRequest, themeDisplay, compId, versionId, "/"));
            }

        } catch (IOException e) {
            _log.error(e, e);
        }

        return false;
    }

    private String redirectURL(ActionRequest actionRequest, ThemeDisplay themeDisplay, Long compId, Long versionId, String renderUrl) {
        String portletName = (String) actionRequest.getAttribute(WebKeys.PORTLET_ID);
        PortletURL redirectURL = PortletURLFactoryUtil.create(actionRequest, portletName, themeDisplay.getLayout().getPlid(), PortletRequest.RENDER_PHASE);
        redirectURL.setParameter("mvcRenderCommandName", renderUrl);
        redirectURL.setParameter(PlanIgualdadVersionWebPortletKeys.COMPID_PARAM, String.valueOf(compId));
        redirectURL.setParameter(PlanIgualdadVersionWebPortletKeys.VERSIONID_PARAM, String.valueOf(versionId));
        return redirectURL.toString().concat("&query=" + String.valueOf(compId));
    }

}

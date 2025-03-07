package com.plan.igualdad.liferay.portlet.puestos.web.portlet.command;

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
import com.plan.igualdad.liferay.portlet.manager.model.PuestoTrabajo;
import com.plan.igualdad.liferay.portlet.manager.service.PuestoTrabajoLocalServiceUtil;
import com.plan.igualdad.liferay.portlet.manager.service.persistence.PuestoTrabajoPK;
import com.plan.igualdad.liferay.portlet.puestos.web.constants.PlanIgualdadPuestosWebPortletKeys;
import org.osgi.service.component.annotations.Component;
import org.w3c.dom.css.Counter;

import javax.portlet.*;
import java.awt.*;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + PlanIgualdadPuestosWebPortletKeys.PLANIGUALDADPUESTOSWEB,
                "mvc.command.name=/puesto/save"
        },
        service = MVCActionCommand.class
)
public class SavePuestoMVCActionCommand implements MVCActionCommand {

    private static Log _log = LogFactoryUtil.getLog(SavePuestoMVCActionCommand.class);

    private static DateFormat dateFormat = DateFormatFactoryUtil.getSimpleDateFormat("dd/MM/yyyy");

    @Override
    public boolean processAction(ActionRequest actionRequest, ActionResponse actionResponse) throws PortletException {
        try {

            ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

            Long compId = ParamUtil.getLong(actionRequest, PlanIgualdadPuestosWebPortletKeys.COMPID_PARAM, 0);
            Long versionId = ParamUtil.getLong(actionRequest, PlanIgualdadPuestosWebPortletKeys.VERSIONID_PARAM, 0);
            Long puestoId = ParamUtil.getLong(actionRequest, PlanIgualdadPuestosWebPortletKeys.PUESTOID_PARAM, 0);

            Long areaId = ParamUtil.getLong(actionRequest, PlanIgualdadPuestosWebPortletKeys.PUESTO_AREA, 0);
            String nombre = ParamUtil.getString(actionRequest, PlanIgualdadPuestosWebPortletKeys.PUESTO_NOMBRE, StringPool.BLANK);
            Long responsabilidad = ParamUtil.getLong(actionRequest, PlanIgualdadPuestosWebPortletKeys.PUESTO_RESPONSABILIDAD, 0);
            Long nHombres = ParamUtil.getLong(actionRequest, PlanIgualdadPuestosWebPortletKeys.PUESTO_NHOMBRES, 0);
            Long nMujeres = ParamUtil.getLong(actionRequest, PlanIgualdadPuestosWebPortletKeys.PUESTO_NMUJERES, 0);
            Date baja = ParamUtil.getDate(actionRequest, PlanIgualdadPuestosWebPortletKeys.PUESTO_BAJA, dateFormat, null);

            if ( Validator.isBlank(nombre) ) {

                SessionErrors.add(actionRequest, "puesto-save-error");
                actionResponse.sendRedirect(redirectURL(actionRequest, themeDisplay, compId, versionId, "goPuesto"));

            } else {

                PuestoTrabajoPK puestoTrabajoPK = new PuestoTrabajoPK();
                puestoTrabajoPK.setCompId(compId);
                puestoTrabajoPK.setVersionId(versionId);
                puestoTrabajoPK.setPuestoId(puestoId);

                PuestoTrabajo puestoTrabajo = PuestoTrabajoLocalServiceUtil.fetchPuestoTrabajo(puestoTrabajoPK);
                if (Validator.isNull(puestoTrabajo)) {
                    puestoTrabajoPK.setPuestoId(CounterLocalServiceUtil.increment(PuestoTrabajo.class.getName()));
                    puestoTrabajo = PuestoTrabajoLocalServiceUtil.createPuestoTrabajo(puestoTrabajoPK);
                }

                puestoTrabajo.setAreaId(areaId);
                puestoTrabajo.setNombre(nombre);
                puestoTrabajo.setResponsabilidad(responsabilidad);
                puestoTrabajo.setNHombres(nHombres);
                puestoTrabajo.setNMujeres(nMujeres);
                puestoTrabajo.setBaja(baja);

                PuestoTrabajoLocalServiceUtil.updatePuestoTrabajo(puestoTrabajo);

                SessionMessages.add(actionRequest, "puesto-save-success");
                actionResponse.sendRedirect(redirectURL(actionRequest, themeDisplay, compId, versionId, "/"));

            }

        }  catch (IOException e) {
            _log.error(e, e);
        }

        return false;
    }

    private String redirectURL(ActionRequest actionRequest, ThemeDisplay themeDisplay, Long compId, Long versionId, String renderUrl) {
        String portletName = (String) actionRequest.getAttribute(WebKeys.PORTLET_ID);
        PortletURL redirectURL = PortletURLFactoryUtil.create(actionRequest, portletName, themeDisplay.getLayout().getPlid(), PortletRequest.RENDER_PHASE);
        redirectURL.setParameter("mvcRenderCommandName", renderUrl);
        redirectURL.setParameter(PlanIgualdadPuestosWebPortletKeys.COMPID_PARAM, String.valueOf(compId));
        redirectURL.setParameter(PlanIgualdadPuestosWebPortletKeys.VERSIONID_PARAM, String.valueOf(versionId));
        return redirectURL.toString();
    }

}

package com.plan.igualdad.liferay.portlet.area.web.portlet.command;

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
import com.plan.igualdad.liferay.portlet.area.web.constants.PlanIgualdadAreaWebPortletKeys;
import com.plan.igualdad.liferay.portlet.manager.model.Area;
import com.plan.igualdad.liferay.portlet.manager.model.PuestoTrabajo;
import com.plan.igualdad.liferay.portlet.manager.service.AreaLocalServiceUtil;
import com.plan.igualdad.liferay.portlet.manager.service.PuestoTrabajoLocalServiceUtil;
import com.plan.igualdad.liferay.portlet.manager.service.persistence.AreaPK;
import org.osgi.service.component.annotations.Component;

import javax.portlet.*;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + PlanIgualdadAreaWebPortletKeys.PLANIGUALDADAREAWEB,
                "mvc.command.name=/area/save"
        },
        service = MVCActionCommand.class
)
public class SaveAreaMVCActionCommand implements MVCActionCommand {

    private static Log _log = LogFactoryUtil.getLog(SaveAreaMVCActionCommand.class);

    private static DateFormat dateFormat = DateFormatFactoryUtil.getSimpleDateFormat("dd/MM/yyyy");

    @Override
    public boolean processAction(ActionRequest actionRequest, ActionResponse actionResponse) throws PortletException {
        try {

            ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

            Long compId = ParamUtil.getLong(actionRequest, PlanIgualdadAreaWebPortletKeys.COMPID_PARAM, 0);
            Long versionId = ParamUtil.getLong(actionRequest, PlanIgualdadAreaWebPortletKeys.VERSIONID_PARAM, 0);
            Long areaId = ParamUtil.getLong(actionRequest, PlanIgualdadAreaWebPortletKeys.AREAID_PARAM, 0);

            String nombre = ParamUtil.getString(actionRequest, PlanIgualdadAreaWebPortletKeys.AREA_NOMBRE, StringPool.BLANK);
            Date baja = ParamUtil.getDate(actionRequest, PlanIgualdadAreaWebPortletKeys.AREA_BAJA, dateFormat, null);

            if (Validator.isBlank(nombre)) {

                SessionErrors.add(actionRequest, "area-save-error");
                actionResponse.sendRedirect(redirectURL(actionRequest, themeDisplay, compId, versionId, "goArea"));

            } else {

                AreaPK areaPK = new AreaPK();
                areaPK.setCompId(compId);
                areaPK.setVersionId(versionId);
                areaPK.setAreaId(areaId);

                Area area = AreaLocalServiceUtil.fetchArea(areaPK);
                if (Validator.isNull(area)) {
                    areaPK.setAreaId(CounterLocalServiceUtil.increment(Area.class.getName()));
                    area = AreaLocalServiceUtil.createArea(areaPK);
                }else {
                	if(Validator.isNotNull(baja) && baja.before(new Date())) {
                		List<PuestoTrabajo> puestosTrabajo = PuestoTrabajoLocalServiceUtil.findByPuestoArea(compId, versionId, areaId);
                	
                		if(Validator.isNotNull(puestosTrabajo) && !puestosTrabajo.isEmpty()) {
                			for(PuestoTrabajo puestoTrabajo : puestosTrabajo) {
                				puestoTrabajo.setBaja(baja);
                				
                				PuestoTrabajoLocalServiceUtil.updatePuestoTrabajo(puestoTrabajo);
                			}
                		}
                	}
                }

                area.setNombre(nombre);
                area.setBaja(baja);

                AreaLocalServiceUtil.updateArea(area);

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
        redirectURL.setParameter(PlanIgualdadAreaWebPortletKeys.COMPID_PARAM, String.valueOf(compId));
        redirectURL.setParameter(PlanIgualdadAreaWebPortletKeys.VERSIONID_PARAM, String.valueOf(versionId));
        return redirectURL.toString();
    }

}

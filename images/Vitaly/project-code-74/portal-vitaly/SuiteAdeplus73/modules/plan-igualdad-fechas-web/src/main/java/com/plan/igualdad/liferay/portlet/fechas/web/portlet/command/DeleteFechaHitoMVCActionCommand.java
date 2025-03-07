package com.plan.igualdad.liferay.portlet.fechas.web.portlet.command;

import com.adeplus.liferay.portlet.suite.manager.model.UserCompany;
import com.adeplus.liferay.portlet.suite.manager.service.UserCompanyLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.plan.igualdad.liferay.portlet.fechas.web.constants.PlanIgualdadFechasWebPortletKeys;
import com.plan.igualdad.liferay.portlet.fechas.web.util.FechasUtils;
import com.plan.igualdad.liferay.portlet.manager.model.Version;
import com.plan.igualdad.liferay.portlet.manager.service.FechaHitoLocalServiceUtil;
import com.plan.igualdad.liferay.portlet.manager.service.VersionLocalServiceUtil;
import com.plan.igualdad.liferay.portlet.manager.service.persistence.FechaHitoPK;

import java.io.IOException;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;

import org.osgi.service.component.annotations.Component;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + PlanIgualdadFechasWebPortletKeys.PLANIGUALDADFECHASWEB,
                "mvc.command.name=/hito/deteleDate"
        },
        service = MVCActionCommand.class
)
public class DeleteFechaHitoMVCActionCommand implements MVCActionCommand {

    private static Log _log = LogFactoryUtil.getLog(DeleteFechaHitoMVCActionCommand.class);

	@Override
	public boolean processAction(ActionRequest actionRequest, ActionResponse actionResponse) throws PortletException {

		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		FechaHitoPK fechaHitoPK = new FechaHitoPK();

		Long compId = ParamUtil.getLong(actionRequest, PlanIgualdadFechasWebPortletKeys.COMPID_PARAM);
		Long idHito = ParamUtil.getLong(actionRequest, PlanIgualdadFechasWebPortletKeys.HITOID_PARAM_DELETE);
		Version version = VersionLocalServiceUtil.findVersionActiva(compId);

		fechaHitoPK.setCompId(compId);
		fechaHitoPK.setVersionId(version.getVersionId());
		fechaHitoPK.setHitoId(idHito);

		try {
			FechaHitoLocalServiceUtil.deleteFechaHito(fechaHitoPK);

			SessionMessages.add(actionRequest, "fecha-delete-success");
			actionResponse.sendRedirect(FechasUtils.redirectURL(actionRequest, themeDisplay, compId, version.getVersionId()));

		} catch (PortalException | IOException e) {
			_log.error("ERROR: ", e);
		}

		return false;
	}
}

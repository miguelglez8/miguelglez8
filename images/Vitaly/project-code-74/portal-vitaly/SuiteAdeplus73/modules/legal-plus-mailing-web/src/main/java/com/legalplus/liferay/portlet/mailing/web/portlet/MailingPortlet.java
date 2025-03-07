package com.legalplus.liferay.portlet.mailing.web.portlet;

import com.legalplus.liferay.portlet.legalplus.manager.model.Legislacion;
import com.legalplus.liferay.portlet.legalplus.manager.service.LegislacionLocalServiceUtil;
import com.legalplus.liferay.portlet.mailing.web.constants.MailingPortletKeys;
import com.legalplus.liferay.portlet.mailing.web.mail.*;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author agarciap
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.legalplus",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=false",
		"javax.portlet.display-name=Mailing",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + MailingPortletKeys.MAILING,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class MailingPortlet extends MVCPortlet {

	public void nuevaLegislacion(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		LegislacionMailing.nuevaLegislacion(
				themeDisplay.getUser().getEmailAddress(),
				themeDisplay.getUser(),
				"Ley 3/1983, de 25 de febrero, de Estatuto de la Comunidad de Madrid.",
				"12340",
				"PRL",
				"Ley",
				"Comunidad",
				"01/03/1983",
				"-",
				"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
				"https://www.boe.es/eli/es/lo/1983/02/25/3",
				"Madrid",
				"Madrid");
	}

	public void informeMensualLegislaciones(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

		List<Legislacion> legislacionesMonthBefore = LegislacionLocalServiceUtil.getLegislacionesMonthBefore(new Date());

		String body = "<table><tr><th>Legislacion</th><th>Enlace</th></tr>";
		for(Legislacion legis:legislacionesMonthBefore){
			body += "<tr><td>" + legis.getNombre() + "</td><td>" + legis.getEnlace()+ "</td></tr>";
		}
		body += "</table>";

		LegislacionMailing.informeMensualLegislaciones(
				themeDisplay.getUser().getEmailAddress(),
				themeDisplay.getUser(),
				body);
	}

	public void importarLegislaciones(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

		List<String> errors = new ArrayList<>();
		errors.add("ERROR 1");
		errors.add("ERROR 2");
		errors.add("ERROR 3");

		ImportadorMailing.importadorLegislaciones(
				themeDisplay.getUser().getEmailAddress(),
				themeDisplay.getUser(),
				errors);
	}

	public void importarRequisitos(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

		List<String> errors = new ArrayList<>();
		errors.add("ERROR 1");
		errors.add("ERROR 2");
		errors.add("ERROR 3");

		ImportadorMailing.importadorRequisitos(
				themeDisplay.getUser().getEmailAddress(),
				themeDisplay.getUser(),
				errors);
	}

	public void newConsultor(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

		ConsultorMailing.altaConsultor(
				themeDisplay.getUser().getEmailAddress(),
				themeDisplay.getUser(),
				"Consultor SKT",
				"consultor@serikat.com");
	}

	public void altaConsultor(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

		ConsultorMailing.asignarConsultor(
				themeDisplay.getUser().getEmailAddress(),
				themeDisplay.getUser(),
				"SKT",
				"Consultor SKT",
				"consultor@serikat.com");
	}

	public void  nuevaEvaluacionCumplimientoLegislacion(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

		EvalLegislacionMailing.nuevaEvaluacionCumplimiento(
				themeDisplay.getUser().getEmailAddress(),
				themeDisplay.getUser(),
				"SKT",
				"L0000007",
				"Cliente SKT Optimo",
				"27/04/2022",
				"Si");
	}

	public void  nuevaEvaluacionCumplimientoRequisito(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

		EvalRequisitoMailing.nuevaEvaluacionCumplimiento(
				themeDisplay.getUser().getEmailAddress(),
				themeDisplay.getUser(),
				"SKT",
				"L0000007",
				"Cliente SKT Premium",
				"27/04/2022",
				"No");
	}

	public void proximaFechaEvaluacionRequisito(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

		EvalRequisitoMailing.proximaFechaEvaluacionDiaria(
				themeDisplay.getUser().getEmailAddress(),
				themeDisplay.getUser(),
				"SKT",
				"<p><b>Legislación 1</b></p><p>Requisito 1</p>",
				"Alain Barrero",
				"abarrero@serikat.es",
				"#");

		EvalRequisitoMailing.proximaFechaEvaluacionSemanal(
				themeDisplay.getUser().getEmailAddress(),
				themeDisplay.getUser(),
				"SKT",
				"<p><b>Legislación 1</b></p><p>Requisito 1</p>",
				"Alain Barrero",
				"abarrero@serikat.es",
				"#");
	}

	public void legislacionUrgente(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

		LegislacionMailing.legislacionUrgente(
				themeDisplay.getUser().getEmailAddress(),
				themeDisplay.getUser(),
				"SKT",
				"02/05/2022",
				"Legislacion 1",
				"Legislacion desc",
				"https://www.boe.es/buscar/pdf/2007/BOE-A-2007-19744-consolidado.pdf",
				"<ul><li>Requisito 1</li></ul>",
				"Alain Barrero",
				"abarrero@serikat.es");
	}

	public void informeMensualEvaluacion(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

		EvalRequisitoMailing.informeMensualEvalRequisitos(
				themeDisplay.getUser().getEmailAddress(),
				themeDisplay.getUser(),
				"SKT",
				"Abril",
				"Alain Barrero",
				"abarrero@serikat.es",
				"#");

	}

	public void periodoFechaEvaluacion(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

		EvalRequisitoMailing.periodoFechaEvaluacion(
				themeDisplay.getUser().getEmailAddress(),
				themeDisplay.getUser(),
				"SKT",
				"<ul><li>09/05/2022 - Requisito 1</li></ul>",
				"Alain Barrero",
				"abarrero@serikat.es",
				"#");

	}

	public void cambioContrato(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

		ContratoMailing.cambioContrato(
				themeDisplay.getUser().getEmailAddress(),
				themeDisplay.getUser(),
				"SKT",
				"Premium",
				"9105 (Actividades de biblioteca), XXXX (Actividad comercial N)",
				"Alain Barrero - abarrero@serikat.es",
				"<ul><li>Seguridad Industrial</li><li>Medio ambiete</li></ul>",
				"<ul><li>CA1</li><li>CA2</li></ul>",
				"<ul><li>A2</li><li>A2</li></ul>",
				"Alain Barrero",
				"abarrero@serikat.es");
	}
}
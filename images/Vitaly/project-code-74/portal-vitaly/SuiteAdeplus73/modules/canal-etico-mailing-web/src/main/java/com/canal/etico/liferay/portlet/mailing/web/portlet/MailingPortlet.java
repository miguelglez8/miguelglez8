package com.canal.etico.liferay.portlet.mailing.web.portlet;

import com.canal.etico.liferay.portlet.mailing.web.constants.MailingPortletKeys;
import com.canal.etico.liferay.portlet.mailing.web.mail.ContactFormMailing;
import com.canal.etico.liferay.portlet.mailing.web.mail.DenunciaMailing;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;

/**
 * @author agarciap
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.canal.etico",
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

	public void contactToAdmin(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		ContactFormMailing.contactToAdmin(
				themeDisplay.getUser().getEmailAddress(),
				themeDisplay.getUser(),
				"Nombre de la empresa",
				"Asunto: mail al administrador.",
				"Pruebas formulario contacto: Mail al administrador.",
				null,
				"Prueba",
				null,
				null);
	}
	public void contactToUser(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		ContactFormMailing.contactToUser(
				themeDisplay.getUser(),
				"Asunto: mail al usuario.",
				"Pruebas formulario contacto: Mail al usuario.",
				"Prueba.",
				null,
				"Filename.txt");
	}

	public void sendDenunciaToAdmin(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		DenunciaMailing.denunciaToAdmin(
				themeDisplay.getUser().getEmailAddress(),
				themeDisplay.getUser().getFullName(),
				"Empresa Test",
				"EMPR-XX-2021-999",
				"Denuncia Test",
				"Mei eu mollis albucius, ex nisl contentiones vix. Duo persius volutpat at, cu iuvaret epicuri mei. Duo posse pertinacia no, ex dolor contentiones mea. Nec omnium utamur dignissim ne. Mundi lucilius salutandi an sea, ne sea aeque iudico comprehensam. Populo delicatissimi ad pri. Ex vitae accusam vivendum pro.",
				"Testing",
				null,
				"-",
				themeDisplay.getLocale());
	}

	public void sendDenunciaToUser(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		DenunciaMailing.denunciaToUser(
				themeDisplay.getUser().getEmailAddress(),
				themeDisplay.getUser().getFullName(),
				"Empresa Test",
				"EMPR-XX-2021-999",
				"Denuncia Test",
				"Mei eu mollis albucius, ex nisl contentiones vix. Duo persius volutpat at, cu iuvaret epicuri mei. Duo posse pertinacia no, ex dolor contentiones mea. Nec omnium utamur dignissim ne. Mundi lucilius salutandi an sea, ne sea aeque iudico comprehensam. Populo delicatissimi ad pri. Ex vitae accusam vivendum pro.",
				"Testing",
				null,
				"-",
				themeDisplay.getLocale());
	}


	public void sendFinalizarDenunciaToAdmin(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		DenunciaMailing.finalizacionDenunciaToAdmin(
				themeDisplay.getUser().getUserId(),
				"EMPR-XX-2021-999",
				"01/01/2021",
				"Motivo del fin",
				"Eam ex integre quaeque bonorum, ea assum solet scriptorem pri, et usu nonummy accusata interpretaris. Debitis necessitatibus est no. Eu probo graeco eum, at eius choro sit, possit recusabo corrumpit vim ne. Noster diceret delicata vel id.",
				themeDisplay.getLocale());
	}

	public void sendFinalizarDenunciaToUser(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		DenunciaMailing.finalizacionDenunciaToUser(
				themeDisplay.getUser().getEmailAddress(),
				themeDisplay.getUser().getFullName(),
				"EMPR-XX-2021-999",
				"01/01/2021",
				"Motivo del fin",
				"Eam ex integre quaeque bonorum, ea assum solet scriptorem pri, et usu nonummy accusata interpretaris. Debitis necessitatibus est no. Eu probo graeco eum, at eius choro sit, possit recusabo corrumpit vim ne. Noster diceret delicata vel id.",
				themeDisplay.getLocale());
	}

	public void sendFinalizarAvisoDenunciaToUser(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		DenunciaMailing.finalizacionDenunciaAvisoToAdmin(
				themeDisplay.getUserId(),
				"EMPR-XX-2021-999",
				"01/01/2021",
				"En Proceso",
				"<ul>" +
						"<li>Solicitud info adicional (01/01/2021)</li>" +
						"<li>Acuse de recibo (03/01/2021)</li>" +
						"<li>Nuevo mensaje (04/01/2021)</li>" +
						"<li>Desestimado (05/01/2021)</li>" +
						"<li>Revisar datos (10/01/2021)</li>" +
						"</ul>",
				themeDisplay.getLocale());
	}

}
package com.preving.liferay.portlet.mailing.web.portlet;

import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;
import com.preving.liferay.portlet.mailing.web.constants.PrevingMailingPortletKeys;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.*;

import com.preving.liferay.portlet.mailing.web.mail.CompanyMailing;
import com.preving.liferay.portlet.mailing.web.mail.ContactMailing;
import com.preving.liferay.portlet.mailing.web.mail.ReportMailing;
import com.preving.liferay.portlet.mailing.web.mail.UserMailing;
import org.osgi.service.component.annotations.Component;

import java.io.IOException;

/**
 * @author agarciap
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.preving",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=false",
		"javax.portlet.display-name=Envío mails con plantillas",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + PrevingMailingPortletKeys.PREVINGMAILING,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class PrevingMailingPortlet extends MVCPortlet {

	public void createCompanyToCreatorTest(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		CompanyMailing.companyCreateToCreator(themeDisplay.getUser(),"Empresa de test", "11111111-NIF");
	}

	public void createCompanyToAdminTest(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		CompanyMailing.companyCreateToAdministrator(themeDisplay.getUser(),"*******");
	}

	public void deleteCompanyToAdminTest(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		CompanyMailing.companyDeleteToAdministrator(themeDisplay.getUser(),"Empresa de test", "11111111-NIF");
	}

	public void createUserToUserTest(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		UserMailing.userCreateToUser(themeDisplay.getUser(),"*******");
	}

	public void deleteUserToUserTest(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		UserMailing.userDeleteToUser(themeDisplay.getUser());
	}

	public void remindPasswordToUserTest(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		UserMailing.userRemingPasswordToUser(themeDisplay.getUser(),"*******");
	}

	public void activeUserToUserTest(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		UserMailing.userActivateToUser(themeDisplay.getUser(),"*******");
	}

	public void signUnclosedToUserTest(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		ReportMailing.signUnclosedToUser(themeDisplay.getUser());
	}
	public void signUnclosedToUserWeeklyTest(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		ReportMailing.signUnclosedWeeklyToUser(themeDisplay.getUser(), "-Tabla de fichajes-");
	}
	public void withoutSignsToUserTest(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		ReportMailing.userWithoutSignToUser(themeDisplay.getUser(),"Empresa de test");
	}
	public void signReportToUserTest(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		ReportMailing.signReportToAdminDaily(themeDisplay.getUser(),"-Tabla de fichajes diaria-");
	}
	public void signReportToUserTestWeekly(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		ReportMailing.signReportToAdminWeekly(themeDisplay.getUser(),"-Tabla de fichajes semanal-");
	}
	public void signReportToUserTestMonthly(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		ReportMailing.signReportToAdminMonthly(themeDisplay.getUser(),"-Tabla de fichajes mensual-");
	}

	public void contactToAdmin(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		ContactMailing.contactToAdmin(themeDisplay.getUser(),themeDisplay.getUser(),"Empresa de test","Asunto","Mensaje de contacto.","Archivo adjunto","Categor&#237;a",null,null);
	}
	public void contactToUser(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		ContactMailing.contactToUser(themeDisplay.getUser(),"Asunto","Mensaje de contacto.","Categor&#237;a");
	}

}
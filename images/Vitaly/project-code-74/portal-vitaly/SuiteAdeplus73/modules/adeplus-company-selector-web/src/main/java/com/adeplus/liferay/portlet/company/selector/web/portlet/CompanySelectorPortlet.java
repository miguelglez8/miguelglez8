package com.adeplus.liferay.portlet.company.selector.web.portlet;

import com.adeplus.liferay.portlet.commons.web.keycloak.AdeplusKeycloakMultiUtil;
import com.adeplus.liferay.portlet.commons.web.user.AdeplusUserUtil;
import com.adeplus.liferay.portlet.company.selector.web.constants.CompanySelectorPortletKeys;
import com.adeplus.liferay.portlet.suite.manager.model.Comp;
import com.adeplus.liferay.portlet.suite.manager.model.CompClientApplication;
import com.adeplus.liferay.portlet.suite.manager.model.UserApplicationClient;
import com.adeplus.liferay.portlet.suite.manager.model.UserCompany;
import com.adeplus.liferay.portlet.suite.manager.service.CompClientApplicationLocalServiceUtil;
import com.adeplus.liferay.portlet.suite.manager.service.CompLocalServiceUtil;
import com.adeplus.liferay.portlet.suite.manager.service.UserApplicationClientLocalServiceUtil;
import com.adeplus.liferay.portlet.suite.manager.service.UserCompanyLocalServiceUtil;
import com.adeplus.liferay.portlet.suite.manager.service.persistence.UserApplicationClientPK;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author agarciap
 */
@Component(
		immediate = true,
		property = {
				"com.liferay.portlet.display-category=category.sample",
				"com.liferay.portlet.header-portlet-css=/css/main.css",
				"com.liferay.portlet.instanceable=false",
				"javax.portlet.display-name=CompanySelector",
				"javax.portlet.init-param.template-path=/",
				"javax.portlet.init-param.view-template=/view.jsp",
				"javax.portlet.name=" + CompanySelectorPortletKeys.COMPANYSELECTOR,
				"javax.portlet.resource-bundle=content.Language",
				"javax.portlet.security-role-ref=power-user,user",
				"javax.portlet.supported-public-render-parameter=userCompId"
		},
		service = Portlet.class
)
public class CompanySelectorPortlet extends MVCPortlet {
	private static final Log _log = LogFactoryUtil.getLog(CompanySelectorPortlet.class);
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException {

		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);

		String userName = themeDisplay.getUser().getFullName();

		String  userCompIdAndClientIdString = ParamUtil.getString(renderRequest, CompanySelectorPortletKeys.USER_COMP_CLIENT_ID, "");

		long userCompId = 0;
		long userClientId= 0;
		long userContractId=0;
		long userEmpresaId=0;
		if(Validator.isNotNull(userCompIdAndClientIdString) && !Validator.isBlank(userCompIdAndClientIdString)){
			String [] userCompIdAndClientId=userCompIdAndClientIdString.split("-");
			userCompId=Long.parseLong(userCompIdAndClientId[0]);
			userClientId=Long.parseLong(userCompIdAndClientId[1]);
			userContractId=Long.parseLong(userCompIdAndClientId[2]);
			userEmpresaId=CompClientApplicationLocalServiceUtil.getClientByCompAndClientAndContract(userCompId,userClientId,userContractId).getEmpresaId();
		}


		if(userCompId == 0){
			UserCompany defaultCompany = UserCompanyLocalServiceUtil.getUserDefaultCompany(themeDisplay.getUserId());
			if(Validator.isNotNull(defaultCompany)){
				userCompId = defaultCompany.getCompId();
				userClientId=defaultCompany.getDefaultClientId();
				userContractId=defaultCompany.getDefaultContractId();
				userEmpresaId=defaultCompany.getDefaultEmpresaId();
			}
		}

		UserCompany userCompany = UserCompanyLocalServiceUtil.getUserCompany(themeDisplay.getUserId(), userCompId);
		if (Validator.isNotNull(userCompany)) {
			userName = userCompany.getName() + " " + userCompany.getLastName();
		}

		String logoSrcFromUserCompany = UserCompanyLocalServiceUtil.getLogoSrcFromUserCompany(themeDisplay.getUserId(), userCompId);
		String url = "";
		if(!Validator.isBlank(logoSrcFromUserCompany)) {
			url = themeDisplay.getPortalURL() + themeDisplay.getPathContext() + "/documents/" + themeDisplay.getScopeGroupId() + "/" + logoSrcFromUserCompany;
		}
		List<CompClientApplication> allClientsCompany=new ArrayList<CompClientApplication>();
		List<Comp> companiesActiveFromUser = AdeplusUserUtil.getCompaniesActiveFromUser(themeDisplay.getUserId());
		//Recorremos todas las empresas y
		for(Comp company:companiesActiveFromUser){
			List<UserApplicationClient> clientsCompany = UserApplicationClientLocalServiceUtil.getApplicationsFromUser(themeDisplay.getUserId(),company.getCompId());
			_log.info("clientsCompany.size() "+clientsCompany.size());
			if(clientsCompany.size()>0) {
				if ((Validator.isNull(userEmpresaId) || userEmpresaId == 0) && company.getCompId() == userCompId) {
					userCompany.setDefaultClientId(clientsCompany.get(0).getClientId());
					userCompany.setDefaultContractId(clientsCompany.get(0).getContractId());
					userEmpresaId=CompClientApplicationLocalServiceUtil.getClientByCompAndClientAndContract(userCompId,clientsCompany.get(0).getClientId(),clientsCompany.get(0).getContractId()).getEmpresaId();
					userCompany.setDefaultEmpresaId(userEmpresaId);
					UserCompanyLocalServiceUtil.updateUserCompany(userCompany);
				}
				for(UserApplicationClient userClient:clientsCompany){
					allClientsCompany.add(CompClientApplicationLocalServiceUtil.getClientByCompAndClientAndContract(userClient.getCompId(),userClient.getClientId(),userClient.getContractId()));
				}
			}

		}

		//recalcular los roles del usuario
		if(userCompId != 0) setRolesSuite(userCompany.getUserId(), userEmpresaId);


		renderRequest.setAttribute(CompanySelectorPortletKeys.USER_COMP_ID, userCompId);
		renderRequest.setAttribute(CompanySelectorPortletKeys.USER_CONTRACT_ID, userContractId);
		renderRequest.setAttribute(CompanySelectorPortletKeys.USER_CLIENT_ID, userClientId);
		renderRequest.setAttribute(CompanySelectorPortletKeys.USER_NAME, userName);
		renderRequest.setAttribute(CompanySelectorPortletKeys.USER_LOGO_URL, url);
		renderRequest.setAttribute(CompanySelectorPortletKeys.USER_COMPS_ACTIVE, companiesActiveFromUser);
		renderRequest.setAttribute(CompanySelectorPortletKeys.USER_CLIENTS_ACTIVE, allClientsCompany);

		super.doView(renderRequest, renderResponse);
	}

	public void setRolesSuite(long userId, long userEmpresaId)  {
		try {
			List<Role> roles = RoleLocalServiceUtil.getRoles(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
			User user = UserLocalServiceUtil.fetchUser(userId);
			if(Validator.isNull(user)){
				_log.error("Usuario con ID: " + userId + " no encontrado, en actualizar roles");
				return;
			}
			long eticoAdmin = 0, eticoUser = 0, igualdadAdmin = 0, igualdadRestrictivo = 0;
			boolean isDelete = false;
			for (Role rol : roles) {
				isDelete = false;
				if (rol.getName().equals("Canal Etico Company Administrator")){
					eticoAdmin = rol.getRoleId();
					isDelete = true;
				}

				if (rol.getName().equals("Canal Etico Company User")){
					eticoUser = rol.getRoleId();
					isDelete = true;
				}

				if (rol.getName().equals("Plan de Igualdad Cliente")){
					igualdadAdmin = rol.getRoleId();
					isDelete = true;
				}

				if (rol.getName().equals("Plan de igualdad cliente restrictivo")){
					igualdadRestrictivo = rol.getRoleId();
					isDelete = true;
				}

				if(isDelete){
					UserLocalServiceUtil.deleteRoleUser(rol.getRoleId(), UserLocalServiceUtil.fetchUser(userId));
					//UserLocalServiceUtil.updateUser(user);
				}
			}

			UserApplicationClientPK pk =  new UserApplicationClientPK();
			pk.setUserId(userId);
			pk.setEmpresaId(userEmpresaId);

			UserApplicationClient  app = UserApplicationClientLocalServiceUtil.getUserApplicationClient(pk);
			if(app.getApplicationId().indexOf("[{\"licenses\":[\"401\"],\"appId\":\"401\"}]") != -1){ // Canal + Completo
				UserLocalServiceUtil.addRoleUser(eticoAdmin, userId);
			}else if(app.getApplicationId().indexOf("[{\"licenses\":[\"402\"],\"appId\":\"401\"}]") != -1){ // Canal + Restringido
				UserLocalServiceUtil.addRoleUser(eticoUser, userId);
			}

			if(app.getApplicationId().indexOf("[{\"licenses\":[\"301\"],\"appId\":\"2\"}]") != -1){ // Igualdad + Completo
				UserLocalServiceUtil.addRoleUser(igualdadAdmin, userId);
			}else if(app.getApplicationId().indexOf("[{\"licenses\":[\"302\"],\"appId\":\"2\"}]") != -1){ // Igualdad + Restringido
				UserLocalServiceUtil.addRoleUser(igualdadRestrictivo, userId);
			}

			UserLocalServiceUtil.updateUser(user);


		}catch(Exception e){
			_log.error("Error al cambiar los roles al usuario : " + userId);
			e.printStackTrace();
		}

	}


	public void selectCompany(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

		String [] userCompIdAndClientId = ParamUtil.getString(actionRequest, CompanySelectorPortletKeys.USER_COMP_CLIENT_ID, "").split("-");
		long userCompId = 0;
		long clientId = 0;
		long contractId = 0;
		long empresaId = 0;
		if(Validator.isNotNull(userCompIdAndClientId)){
			userCompId = Long.parseLong(userCompIdAndClientId[0]);
			clientId = Long.parseLong(userCompIdAndClientId[1]);
			contractId = Long.parseLong(userCompIdAndClientId[2]);
			empresaId = CompClientApplicationLocalServiceUtil.getClientByCompAndClientAndContract(userCompId,clientId,contractId).getEmpresaId();
		}

		_log.info("empresaId " + empresaId);
		UserCompanyLocalServiceUtil.setUserDefaultCompany(themeDisplay.getUserId(), userCompId,clientId,contractId,empresaId);

		UserCompany userCompany = UserCompanyLocalServiceUtil.getUserCompany(themeDisplay.getUserId(), userCompId);
		_log.info(userCompany.getCompId());
		AdeplusKeycloakMultiUtil.setKCdefaultCompany(userCompId, userCompany.getEmail(),clientId,contractId);

		createAuditLogin(themeDisplay, userCompId);


		actionResponse.getRenderParameters().setValue(CompanySelectorPortletKeys.USER_COMP_ID, String.valueOf(userCompId));

	}

	private void createAuditLogin(ThemeDisplay themeDisplay, long userCompId) throws Exception {

		User usuario= UserLocalServiceUtil.getUser(themeDisplay.getUserId());
		User usuarioReal = themeDisplay.getRealUser();

		String nombreEmpresa= StringPool.BLANK;
		String dateTime = DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm:ss a")
				.format(LocalDateTime.now());

		String auditoria = "IP: " + usuarioReal.getLoginIP() + "(Simulando); Fecha y hora: " + dateTime + "; Accion Realizada: Login; Usuario: " +
				usuarioReal.getEmailAddress() + "(Simulando)";

		if (!themeDisplay.isImpersonated()) {
			auditoria = auditoria.replaceAll("Simulando", "");
			auditoria = auditoria.replaceAll("[()]", "");
		} else {
			auditoria = auditoria + " usuario simulado: " + usuario.getEmailAddress();
		}

		try {
			nombreEmpresa="Empresa a la que esta accediendo:"+ CompLocalServiceUtil.getComp(userCompId).getName();
		} catch (PortalException e) {
			_log.error("No tiene una empresa por defecto");
		}
		File archivo = new File("/tmp/auditoria/Auditoria.csv");
		FileWriter escritor = new FileWriter(archivo,true);

		escritor.write(auditoria+ "; "+nombreEmpresa+";\n");
		escritor.close();

	}

}
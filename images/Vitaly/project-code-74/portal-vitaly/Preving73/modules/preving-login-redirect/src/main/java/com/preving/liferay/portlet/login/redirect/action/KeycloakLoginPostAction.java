package com.preving.liferay.portlet.login.redirect.action;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auto.login.AutoLogin;
import com.liferay.portal.kernel.security.auto.login.BaseAutoLogin;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.service.PortalPreferencesLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.*;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.security.sso.openid.connect.OpenIdConnect;
import com.liferay.portal.security.sso.openid.connect.OpenIdConnectSession;
import com.liferay.portal.security.sso.openid.connect.constants.OpenIdConnectWebKeys;
import com.preving.liferay.portlet.calendar.manager.model.CompanyConf;
import com.preving.liferay.portlet.calendar.manager.model.UserData;
import com.preving.liferay.portlet.calendar.manager.service.CompanyConfLocalServiceUtil;
import com.preving.liferay.portlet.calendar.manager.service.UserDataLocalServiceUtil;
import com.preving.liferay.portlet.commons.web.constants.PrevingCommonsPortletKeys;
import com.preving.liferay.portlet.create.company.web.util.*;
import com.preving.liferay.portlet.login.redirect.constants.LoginRedirectPortletKeys;
import com.preving.liferay.portlet.login.redirect.util.Realm;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.representations.idm.GroupRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

import static java.util.Arrays.asList;

@Component(immediate = true, service = AutoLogin.class)
public class KeycloakLoginPostAction extends BaseAutoLogin {

	private static Log _log = LogFactoryUtil.getLog(KeycloakLoginPostAction.class);

	private static final String ADMIN_ROL = "#REGISTRO_HORARIO#ADMIN";
	private static final String USER_ROL = "#REGISTRO_HORARIO#USER";
	private static final String APP_NAME_REGISTRO_HORARIO = "REGISTRO_HORARIO";

	private static final String REALNAME 	= PrefsPropsUtil.getString(LoginRedirectPortletKeys.KEYCLOAK_CONFIGURATION_REALNAME);
	private static final String URL 		= PrefsPropsUtil.getString(LoginRedirectPortletKeys.KEYCLOAK_CONFIGURATION_URL);
    private static final String USERNAME 	= PrefsPropsUtil.getString(LoginRedirectPortletKeys.KEYCLOAK_CONFIGURATION_USERNAME);
    private static final String PASS 		= PrefsPropsUtil.getString(LoginRedirectPortletKeys.KEYCLOAK_CONFIGURATION_PASSWORD);
    private static final String CLIENT_ID 	= PrefsPropsUtil.getString(LoginRedirectPortletKeys.KEYCLOAK_CONFIGURATION_CLIENT_ID);
    private static final String SECRET 		= PrefsPropsUtil.getString(LoginRedirectPortletKeys.KEYCLOAK_CONFIGURATION_SECRET);

    private static final String PREVING_SITE_ADMIN = "Preving Company Site Admin";
    private static final String PREVING_SITE_USER = "Preving Company Site User";


	@Override
	protected String[] doLogin(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
			throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay) httpServletRequest.getAttribute(WebKeys.THEME_DISPLAY);

		_log.debug("-- Sincronizar usuario con keycloak --");

		try {
			List<Company> companies = CompanyLocalServiceUtil.getCompanies();
			for (Company company : companies) {
				javax.portlet.PortletPreferences preferences = com.liferay.portal.util.PrefsPropsUtil.getPreferences(company.getCompanyId(), true);
				preferences.setValue(com.liferay.portal.kernel.util.PropsKeys.COMPANY_SECURITY_STRANGERS, "true");
				preferences.setValue(com.liferay.portal.kernel.util.PropsKeys.COMPANY_SECURITY_STRANGERS_WITH_MX, "true");

				if (_log.isDebugEnabled()) {
					_log.debug("Activado COMPANY_SECURITY_STRANGERS para companyId " + com.liferay.portal.util.PropsUtil.get(company, com.liferay.portal.kernel.util.PropsKeys.COMPANY_SECURITY_STRANGERS));
				}

			}
		} catch (Exception e) {
			_log.error("Error activando COMPANY_SECURITY_STRANGERS. ", e);
		}

		//Precondiciones
		if (!_openIdConnect.isEnabled(_portal.getCompanyId(httpServletRequest))) {
			_log.error(" openIdConnect isEnabled: " + _openIdConnect.isEnabled(_portal.getCompanyId(httpServletRequest)));
			return null;
		}

		HttpSession httpSession = httpServletRequest.getSession(false);
		if (httpSession == null) {
			_log.error(" httpSession is null: " + httpSession);
			return null;
		}

		OpenIdConnectSession openIdConnectSession = (OpenIdConnectSession) httpSession.getAttribute(OpenIdConnectWebKeys.OPEN_ID_CONNECT_SESSION);
		if (openIdConnectSession == null) {
			_log.error(" openIdConnectSession is null: " + openIdConnectSession);
			return null;
		}


		HttpSession session = httpServletRequest.getSession();
		String jremote = (String) ((HttpServletRequest) httpServletRequest).getSession().getAttribute("j_remoteuser");
		User userP = (User) httpServletRequest.getAttribute(WebKeys.USER);
		Long userIdObj = (Long) httpServletRequest.getAttribute(WebKeys.USER_ID);
		User user = PortalUtil.getUser(httpServletRequest);

		if (user == null) {
			_log.error(" user is null.");

			if (themeDisplay.isSignedIn()) {
				user = themeDisplay.getUser();
				_log.error(" The user is : " + user.getEmailAddress());
			}
			return null;
		}
		Thread.currentThread().setContextClassLoader(KeycloakBuilder.builder().getClass().getClassLoader());
		Realm keyCloack = new Realm();
		UserRepresentation userRepresentation = keyCloack.getUserFromKC(user.getEmailAddress());

		Date now = new Date();
		List<Long> userCompaniesKC = getCompaniesIdsFromUser(userRepresentation);


		CompanyConf companyConf = null;
		GroupRepresentation groupRepresentation = null;
		String[] arrKey = null;
		String nameContrato = "";


		_log.info(" VERIFICANDO .................... userCompaniesKC: " + userCompaniesKC.size());

		for (Long companyId : userCompaniesKC) {
			groupRepresentation = keyCloack.getGroup("Empresa_suite_" +  companyId);
			companyConf = CompanyConfLocalServiceUtil.getCompanyConfByCif(groupRepresentation.getAttributes().get("cif").get(0));



			//if(Validator.isNull(companyConf) && groupRepresentation.getAttributes().containsKey("modelVersionMulti")){ //no existe, se crea con el nuevo modelo

			if( groupRepresentation.getAttributes().containsKey("modelVersionMulti")){
				Iterator iterator = userRepresentation.getAttributes().entrySet().iterator();
				Map.Entry<String, List<String>> entry = null;



				while (iterator.hasNext()) {
					entry = (Map.Entry<String, List<String>>) iterator.next();
					if(entry.getKey().indexOf("cc_") == 0){
						arrKey = entry.getKey().split("_");

						//_log.info("arrKey.length : " + arrKey.length );
						//_log.info("arrKey.length == 5 && arrKey[4].equals(\"apps\") : " + (arrKey.length == 5 && arrKey[4].equals("apps")));

						if(arrKey.length == 5 && arrKey[4].equals("apps")){
							if(userRepresentation.getAttributes().containsKey("cc_" + arrKey[1] + "_" + arrKey[2] + "_" + arrKey[3] + "_name") ){
								nameContrato = userRepresentation.getAttributes().get("cc_" + arrKey[1] + "_" + arrKey[2] + "_" + arrKey[3] + "_name").get(0);
								_log.info("creando contrato ................ " + nameContrato);
								//associateModeMulti(keyCloack,userRepresentation, user, companyId, Long.valueOf(arrKey[2]), Long.valueOf(arrKey[3]), nameContrato);
								associateModeMulti(keyCloack,userRepresentation, user, Long.valueOf(arrKey[1]) , Long.valueOf(arrKey[2]), Long.valueOf(arrKey[3]), nameContrato);
							}
						}
					}
				}

			}else if(!groupRepresentation.getAttributes().containsKey("modelVersionMulti")){//modelo antiguo
				associateModelMono(keyCloack,userRepresentation, user, companyId); //Modelo antiguo
			}
		}



		String[] credentials = new String[3];

		credentials[0] = String.valueOf(user.getUserId());
		credentials[1] = user.getPassword();
		credentials[2] = Boolean.TRUE.toString();




		return credentials;



	}




	public static List<Long> getCompaniesIdsFromUser(UserRepresentation userRepresentation){

		List<Long> companiesIds = new ArrayList<>();

		List<String> userCompaniesKC = userRepresentation.getGroups();

		for(String compName:userCompaniesKC){
			String[] compData = compName.split("_");
			if(compData.length == 3 && Validator.isNumber(compData[2])){
				companiesIds.add(Long.parseLong(compData[2]));
				if(_log.isDebugEnabled()){
					_log.debug("Company Id: " + compData[2]);
				}
			}
		}

		_log.debug("companiesIds " + companiesIds.size());

		return companiesIds;
	}


	/****************/
	/* MULTI */
	/***************/
	public void  associateModeMulti(Realm keyCloack,
								UserRepresentation userRepresentation,
								User user,
								long companyId,
								long clientId,
								long contractId,
								String nameContrato){
		try {
			Date now = new Date();
			CompanyConf companyConf = null;
			boolean hasRegistroHorario = hasApplicationFromCompanyByNameMulti(userRepresentation,clientId, contractId, companyId, APP_NAME_REGISTRO_HORARIO);

			if (hasRegistroHorario) {

				//Obtener empresa de KC
				GroupRepresentation groupRepresentation = keyCloack.getGroup("Empresa_suite_" + companyId);
				Group company = null;
				companyConf = CompanyConfLocalServiceUtil.getCompanyByClientContract(clientId, contractId);


				//Si viene del modelo antiguo ya creada.
				if(Validator.isNull(companyConf)){
					companyConf = CompanyConfLocalServiceUtil.fetchCompanyConf(companyId);
					if(Validator.isNotNull(companyConf) && companyConf.getClientId() == 0){
						companyConf.setClientId(clientId);
						companyConf.setContracId(contractId);
						CompanyConfLocalServiceUtil.updateCompanyConf(companyConf);
					}else{
						companyConf = null;
					}
				}



				//Si no existe se crea.
				if (Validator.isNull(companyConf)) {
					_log.info("NO EXISTE LA EMPRESA");
					_log.info(" - nombre:" + groupRepresentation.getAttributes().get("nombre").get(0));
					_log.info(" - cif:" + groupRepresentation.getAttributes().get("cif").get(0));


					company = PrevingCreateCompanyUtil.createCompany(
							user.getCompanyId(),
							//groupRepresentation.getAttributes().get("nombre").get(0),
							nameContrato,
							user,
							groupRepresentation.getAttributes().get("cif").get(0),
							"suiteAdeplusMulti",
							now,
							now,
							translateDate(groupRepresentation.getAttributes().get("deleteDate").get(0)),
							clientId,
							contractId);

					//Aplicar plantilla de sitio web al group creado.
					String templateId = "34804";
					PrevingCreateCompanyUtil.selectSiteTemplateToGroup(company, Long.parseLong(templateId));
					companyConf  = CompanyConfLocalServiceUtil.getCompanyByClientContract(clientId, contractId);

				} else {
					//Update Company
					PrevingCompanyUtil.updateCompany(companyConf.getCompanyConfId(),
							//groupRepresentation.getAttributes().get("nombre").get(0),
							nameContrato,
							groupRepresentation.getAttributes().get("cif").get(0),
							now,
							now,
							translateDate(groupRepresentation.getAttributes().get("deleteDate").get(0)),
							"suiteAdeplusMulti",
							clientId,
							contractId);



				}
				//Actualizar el usuario.
				if (!user.isActive()) {
					user.setStatus(WorkflowConstants.STATUS_APPROVED);
					user.setPasswordReset(false);
					user.setEmailAddressVerified(true);
					UserLocalServiceUtil.updateUser(user);
				}


				UserData userData = PrevingUserDataUtil.updateUserDataValues(companyId,
						companyConf.getGroupId(),
						user.getUserId(),
						userRepresentation.getAttributes().get("dni").get(0),
						userRepresentation.getFirstName(),
						userRepresentation.getLastName(),
						userRepresentation.getEmail(), "", "", "", true);


				userData.setDeleteDate(null);
				UserDataLocalServiceUtil.updateUserData(userData);
				PrevingCompanyUtil.addUserToCompany(user.getUserId(), companyConf.getGroupId());



				//Actualizar Rol
				boolean hasUserAdminRole = hasUserAdminRole(userRepresentation, companyId);
				if (hasUserAdminRole) {
					PrevingRoleUtil.addAdminRole(companyConf.getGroupId(), user.getUserId());
					PrevingRoleUtil.deleteUserRole(companyConf.getGroupId(), user.getUserId());
				} else {
					PrevingRoleUtil.deleteAdminRole(companyConf.getGroupId(), user.getUserId());
					PrevingRoleUtil.addUserRole(companyConf.getGroupId(), user.getUserId());
				}

			}else{
				_log.info("Quitando usuario : " + user.getEmailAddress());
				companyConf = CompanyConfLocalServiceUtil.getCompanyByClientContract(clientId, contractId);
				if(Validator.isNotNull(companyConf)) {
					UserData userData = PrevingUserDataUtil.getUserData(companyConf.getGroupId(), user.getUserId());
					userData.setDeleteDate(new Date());
					UserDataLocalServiceUtil.updateUserData(userData);
					PrevingUserUtil.deleteAdminRole(companyConf.getGroupId(), user.getUserId());
					PrevingRoleUtil.deleteUserRole(companyConf.getGroupId(), user.getUserId());
				}else{
					_log.info("companyConf no encontradas .. con clientId : " + clientId + " / contractId: " + contractId);
				}
			}





		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public static boolean hasApplicationFromCompanyByNameMulti(UserRepresentation userRepresentation,
															   long clienteId, long contractId, long companyId, String applicationName){

		boolean hasPermission = false;
		Iterator iterator = userRepresentation.getAttributes().entrySet().iterator();
		Map.Entry<String, List<String>> entry = null;
		List<String> values = null;
		String[] arrApps = null;
		while (iterator.hasNext()) { // tiene en algún contrato indicado RH
			entry = (Map.Entry<String, List<String>>) iterator.next();

			if(entry.getKey().indexOf("cc_" + companyId + "_" + clienteId + "_" + contractId + "_apps") == 0 ){ // contrato
				/*_log.info("key ... " + entry.getKey());
				_log.info("value ... " + entry.getValue());
				_log.info("applicationName ... " + applicationName);*/

				for(String str : entry.getValue()){
					arrApps = str.split(";");
					for(int i = 0; i < arrApps.length; i++){
						//if(arrApps[i].equals(applicationName) ){
						if(arrApps[i].indexOf(applicationName) == 0 ){
							hasPermission = true;
							break;
						}
					}
					if(hasPermission) break;
				}
			}
		}

		_log.info("hasPermission: " + hasPermission);
		return hasPermission;
	}
	/**** END MULTI **/

	/****** MONO **********/

	public void associateModelMono(Realm keyCloack, UserRepresentation userRepresentation, User user, Long companyId) {
		Date now = new Date();
		_log.info(" Procesar creación/actualización empresa " + companyId);

		boolean hasRegistroHorario = hasApplicationFromCompanyByNameMono(userRepresentation, companyId, APP_NAME_REGISTRO_HORARIO);

		_log.info(" hasRegistroHorario: " + hasRegistroHorario);
		if (hasRegistroHorario) {

			//Obtener empresa de KC
			GroupRepresentation groupRepresentation = keyCloack.getGroup("Empresa_suite_" + companyId);

			_log.info(" groupRepresentation: " + groupRepresentation);

			//Obtener empresa de Liferay.
			Group company = null;
			CompanyConf companyConf = CompanyConfLocalServiceUtil.getCompanyConfByCif(groupRepresentation.getAttributes().get("cif").get(0));


			_log.info(" companyConf: " + companyConf);

			if (Validator.isNull(companyConf)) {//Si no existe se crea.
				_log.info("NO EXISTE LA EMPRESA");
				_log.info(" - nombre:" + groupRepresentation.getAttributes().get("nombre").get(0));
				_log.info(" - cif:" + groupRepresentation.getAttributes().get("cif").get(0));


				company = PrevingCreateCompanyUtil.createCompany(
						user.getCompanyId(),
						groupRepresentation.getAttributes().get("nombre").get(0),
						user,
						groupRepresentation.getAttributes().get("cif").get(0),
						"suiteAdeplus",
						now,
						now,
						translateDate(groupRepresentation.getAttributes().get("deleteDate").get(0))
						,0,0);


				_log.info(" company: " + company);

				//Aplicar plantilla de sitio web al group creado.
				String templateId = "34804";
				PrevingCreateCompanyUtil.selectSiteTemplateToGroup(company, Long.parseLong(templateId));

				companyConf = CompanyConfLocalServiceUtil.getCompanyConfByCif(groupRepresentation.getAttributes().get("cif").get(0));

				if (_log.isDebugEnabled()) {
					_log.debug("  Creada empresa " + companyId + ".");
				}

				//Si existe se actualiza.
			} else {

				//Update Company
				PrevingCompanyUtil.updateCompany(companyConf.getCompanyConfId(),
						groupRepresentation.getAttributes().get("nombre").get(0),
						groupRepresentation.getAttributes().get("cif").get(0),
						now,
						now,
						translateDate(groupRepresentation.getAttributes().get("deleteDate").get(0)),
						"suiteAdeplus",
						0,0);

				if (_log.isDebugEnabled()) {
					_log.debug("  Actualizada empresa " + companyId + ".");
				}

			}

			//Actualizar el usuario.

			if (_log.isDebugEnabled()) {
				_log.debug("  Actualizar user " + user.getUserId() + ".");
			}

			if (!user.isActive()) {
				user.setStatus(WorkflowConstants.STATUS_APPROVED);
				user.setPasswordReset(false);
				user.setEmailAddressVerified(true);
				UserLocalServiceUtil.updateUser(user);
				_log.debug("  Usuario activado: " + user.getEmailAddress() + ".");
			}

			UserData userData = PrevingUserDataUtil.updateUserDataValues(companyId,
					companyConf.getGroupId(),
					user.getUserId(),
					userRepresentation.getAttributes().get("dni").get(0),
					userRepresentation.getFirstName(),
					userRepresentation.getLastName(),
					userRepresentation.getEmail(), "", "", "", true);

			userData.setDeleteDate(null);
			UserDataLocalServiceUtil.updateUserData(userData);

			PrevingCompanyUtil.addUserToCompany(user.getUserId(), companyConf.getGroupId());

			if (_log.isDebugEnabled()) {
				_log.debug("  Asignar empresa a user " + user.getUserId() + ".");
			}

			//Actualizar Rol
			boolean hasUserAdminRole = hasUserAdminRole(userRepresentation, companyId);
			if (hasUserAdminRole) {

				PrevingRoleUtil.addAdminRole(companyConf.getGroupId(), user.getUserId());
				PrevingRoleUtil.deleteUserRole(companyConf.getGroupId(), user.getUserId());

				if (_log.isDebugEnabled()) {
					_log.debug("  Es admin en " + companyId + ".");
				}

			} else {

				PrevingRoleUtil.deleteAdminRole(companyConf.getGroupId(), user.getUserId());
				PrevingRoleUtil.addUserRole(companyConf.getGroupId(), user.getUserId());

				if (_log.isDebugEnabled()) {
					_log.debug("  Es user en " + companyId + ".");
				}
			}

		}

		//Se ha borrado su acceso
		_log.debug("deleteDateRegistroHorario ");
		Date deleteDateRegistroHorario = null;
		//Date deleteDateRegistroHorario = hasApplicationDeletedFromCompanyByName(userRepresentation, companyId, APP_NAME_REGISTRO_HORARIO);

		_log.debug("deleteDateRegistroHorario " + deleteDateRegistroHorario);

		if (Validator.isNotNull(deleteDateRegistroHorario)) {
			_log.debug(" Procesar baja empresa " + companyId);

			Date date31DaysBefore = getDate31DaysBefore();

			GroupRepresentation groupRepresentation = keyCloack.getGroup("Empresa_suite_" + companyId);
			CompanyConf companyConf = CompanyConfLocalServiceUtil.getCompanyConfByCif(groupRepresentation.getAttributes().get("cif").get(0));

			//Poner la fecha de fin del usuario en la empresa sin desactivar.
			UserData userData = PrevingUserDataUtil.getUserData(companyConf.getGroupId(), user.getUserId());
			userData.setDeleteDate(deleteDateRegistroHorario);


			//Si cumplieron los 31 dias, desactivamos el usuario.
			if (deleteDateRegistroHorario.before(date31DaysBefore)) {
				//Dar de baja al usuario de la empresa.
				userData.setActive(false);
				//Borrar sus roles
				PrevingUserUtil.deleteAdminRole(companyConf.getGroupId(), user.getUserId());
				PrevingRoleUtil.deleteUserRole(companyConf.getGroupId(), user.getUserId());

				if (_log.isDebugEnabled()) {
					_log.debug("  El usuario ha dejado de tener acceso a " + companyId + ". Fecha baja: " + deleteDateRegistroHorario + ", fecha efectiva: " + date31DaysBefore);
				}

			}

			UserDataLocalServiceUtil.updateUserData(userData);

			if (_log.isDebugEnabled()) {
				_log.debug("  La fecha de baja del usuario es " + deleteDateRegistroHorario);
			}


		}

	}

	public static boolean hasApplicationFromCompanyByNameMono(UserRepresentation userRepresentation, long companyId, String applicationName){

		//List<String> applicationNamesFromCompany = userRepresentation.getAttributes().get(companyId + PrevingCommonsPortletKeys.KEYCLOAK_NAME_APPS);
		String [] arr = userRepresentation.getAttributes().get(companyId + PrevingCommonsPortletKeys.KEYCLOAK_NAME_APPS).get(0).split(";");
		List<String> applicationNamesFromCompany = asList(arr);
		for(String appName:applicationNamesFromCompany){
			if(applicationName.equals(appName)){
				if(_log.isDebugEnabled()){
					_log.debug("User has permission for " + applicationName + " in " + companyId +".");
				}
				return true;
			}
		}
		if(_log.isDebugEnabled()){
			_log.debug("User has no permission for " + applicationName + " in " + companyId +".");
		}
		return false;
	}
	/***** END MONO *****/
	public static Date hasApplicationDeletedFromCompanyByName(UserRepresentation userRepresentation, long companyId, String applicationName){
		try {

			if(Validator.isNull(userRepresentation)
					|| Validator.isNull(userRepresentation.getAttributes())
					|| !userRepresentation.getAttributes().containsKey(companyId + PrevingCommonsPortletKeys.KEYCLOAK_NAME_APPS_DELETED)){
				return null;
			}

			//List<String> applicationDeletedNamesFromCompany = userRepresentation.getAttributes().get(companyId + PrevingCommonsPortletKeys.KEYCLOAK_NAME_APPS_DELETED);
			String [] arr = userRepresentation.getAttributes().get(companyId + PrevingCommonsPortletKeys.KEYCLOAK_NAME_APPS_DELETED).get(0).split(";");
			List<String> applicationDeletedNamesFromCompany = asList(arr);
			if(Validator.isNotNull(applicationDeletedNamesFromCompany)) {
				for (String appDeleteName : applicationDeletedNamesFromCompany) {
					String[] appData = appDeleteName.split("\\|");
					if (appData.length == 2 && applicationName.equals(appData[0])) {
						if (_log.isDebugEnabled()) {
							_log.debug("User has no permission for " + applicationName + " in " + companyId + ".");
						}
						return new Date(Long.parseLong(appData[1]));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(_log.isDebugEnabled()){
			_log.debug("User has permission for " + applicationName + " in " + companyId +".");
		}
		return null;
	}
	public Date translateDate(String fecha) {
		if(fecha==null || fecha.equals("")) {
			return null;
		}else {
			Date d = new Date(Long.parseLong(fecha) * 1000);
			return d;
		}
	}

	public static boolean hasUserAdminRole(UserRepresentation userRepresentation, long companyId){
		return userRepresentation.getRealmRoles().contains(companyId + PrevingCommonsPortletKeys.KEYCLOAK_ADMIN_ROL);
	}

	public static Date getDate31DaysBefore(){
		Calendar calendar = Calendar.getInstance();

		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		calendar.add(Calendar.MONTH, -1);

		return calendar.getTime();
	}

	@Reference
    private OpenIdConnect _openIdConnect;
    
    @Reference
    private Portal _portal;

}

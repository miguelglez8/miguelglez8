package com.preving.liferay.portlet.commons.web.keycloak;


/*import com.adeplus.liferay.portlet.commons.web.permission.AdeplusPermissionCompanyUtil;
import com.adeplus.liferay.portlet.suite.manager.model.*;
import com.adeplus.liferay.portlet.suite.manager.service.*;*/
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.util.PrefsPropsUtil;
import org.keycloak.representations.idm.UserRepresentation;

import java.util.*;

import static java.util.Arrays.asList;

public class PrevingKeycloakUtil {

	private static final String STR_EMPRESA = "Empresa_suite_";
	private static final String STR_ROL = "Rol_suite_";

	private static Log _log = LogFactoryUtil.getLog(PrevingKeycloakUtil.class);

	private static final String REALNAME 	= PrefsPropsUtil.getString("preving.keycloak.configuration.realname");
	private static final String URL 		= PrefsPropsUtil.getString("preving.keycloak.configuration.url");
	private static final String USERNAME 	= PrefsPropsUtil.getString("preving.keycloak.configuration.username");
	private static final String PASS 		= PrefsPropsUtil.getString("preving.keycloak.configuration.pass");
	private static final String CLIENT_ID 	= PrefsPropsUtil.getString("preving.keycloak.configuration.clientid");
	private static final String SECRET 		= PrefsPropsUtil.getString("preving.keycloak.configuration.secret");

	private static final String DEFAULT_ROLES 		= "PROTECCION_DATOS,COMPLIANCE,PLAN_IGUALDAD,OTROS_SERVICIOS";

	//@see Para crear/actualizar grupo/empresa
	public static void createCompanyKeycloak(long compId,
											 String name,
											 String cif,
											 String description,
											 String observations,
											 Date deleteDate,
											 String idCRM){


		Realm realm = new Realm(URL, REALNAME, USERNAME, PASS, SECRET, CLIENT_ID);

		_log.debug("+ creando grupo/empresa en keycloak(2) : '" + name + "' con id : " + String.valueOf(compId));

		Company company = new Company(String.valueOf(STR_EMPRESA + compId)); //ponemos empresa_suite ¿?
		company.setAttribute("liferay_compId", asList(String.valueOf(compId)));
		company.setAttribute("nombre", asList(name));
		company.setAttribute("cif", asList(cif));
		company.setAttribute("description", asList(description));
		if(deleteDate == null) {
			company.setAttribute("deleteDate", asList(""));
		}else {
			company.setAttribute("deleteDate", asList(String.valueOf(deleteDate.getTime())));
		}

		company.setAttribute("idCRM", asList("PONER ID CRM"));
		company.setAttribute("idCRM", asList(idCRM));
		company.addRole("EMPRESAS SUITEADEPLUS");
		realm.createCompany(company);

		_log.debug("+ grupo/empresa '" + name + "' creada");

		realm.close();
	}


	public static void setKCdefaultCompany(long compId, String email) {
		Realm realm = new Realm(URL,
				REALNAME,
				USERNAME,
				PASS,
				SECRET,
				CLIENT_ID);

		UserRepresentation user = new UserRepresentation();
		user.setEmail(email);
		System.out.println(" + email: " + email);
		if(realm.isExistUser(user)) {
			System.out.println(" + usuario existe cambiando a "  + String.valueOf(compId));
			user = realm.getUserFromKC(email);
			Map<String,List<String>> map = user.getAttributes();
			map.put("suite_default_company", asList(String.valueOf(compId)));
			user.setAttributes(map);
			realm.updateUser(user);
		}

		realm.close();
	}

	public static void operationUser(boolean isCreateUser,
									 long companyId,
									 long userId,
									 String email,
									 String name,
									 String lastName,
									 String nif,
									 String phone,
									 String pass,
									 String endDate,
									 boolean isAdmin) {


		Realm realm = new Realm(URL,
				REALNAME,
				USERNAME,
				PASS,
				SECRET,
				CLIENT_ID);


		if(!realm.isConnected()) {
			_log.debug("+ no se pudo conectar con el servidor de keycloak en '" + realm.url + "' ");

			return;

		}

		/*Map<String, List<String>> attr2 = new HashMap<String, List<String>>();
		UserRepresentation user2 = new UserRepresentation();
		user2.setEmail(email);
		user2.setUsername(email);
		user2.setFirstName("PPP111");
		user2.setLastName("PP111");
		attr2.put("PRUEBA",asList("PRUEBA"));
		user2.setAttributes(attr2);
		realm.createUser(user2);
		if(true) return;*/

		_log.debug("+++ creando bean user (1) : " + email);
		_log.debug("  --- > name : " + name);
		_log.debug("  --- > lastName : " + lastName);
		_log.debug("  --- > lastName : " + lastName);
		_log.debug("  --- > userId : " + userId);
		_log.debug("  --- > companyId : " + companyId);

		List<String> arrRol = new ArrayList<String>();
		UserRepresentation user = new UserRepresentation();
		boolean isUserKCnew = true;

		Map<String, List<String>> attr = null;
		user.setEmail(email);


		_log.debug("+ existe el usuario kc: " + realm.isExistUser(user));
		//existe el usuario añadidos el grupo
		if(realm.isExistUser(user)) {
			user = realm.getUserFromKC(email);
			List<String> grp = user.getGroups();
			grp.add(STR_EMPRESA + String.valueOf(companyId));
			user.setGroups(grp);
			isUserKCnew = false;
			attr = user.getAttributes();
			arrRol = user.getRealmRoles();
			//limpiamos los roles de la company actual
			List<String> arrRol_aux = new ArrayList<String>();
			for(String rol : arrRol) {
				if(rol.indexOf(String.valueOf(companyId)) != 0) { //si no es de esta compñia lo respetamos
					arrRol_aux.add(rol);
				}
			}
			arrRol = arrRol_aux;
			arrRol_aux = null;

		}else{
			user.setGroups(asList(STR_EMPRESA + String.valueOf(companyId)));
			attr = new HashMap<String,List<String>>();
		}

		user.setUsername(email);
		user.setFirstName(name);
		user.setLastName(lastName);



		String[] arr = DEFAULT_ROLES.split(",");
		arrRol.add(companyId+"#REGISTRO_HORARIO");
		for(String def : arr) {
			arrRol.add(def);
		}


		if(arrRol.size() > 0) user.setRealmRoles(arrRol);

		//atributos


		attr.put("actualizado", asList(new Date().toLocaleString()));
		attr.put("origen", asList("RegistroDeHoras"));

		_log.debug(" + attr empezando");

		attr.put("dni", asList(nif));
		attr.put("phone",asList(phone));
		attr.put("endDate",asList(endDate));
		attr.put(companyId + "_apps", asList("REGISTRO_HORARIO"));

		attr.put("suite_default_company", asList(String.valueOf(companyId)));

        if (isAdmin) {
        	 attr.put("isAdmin", asList("true"));
         } else {
        	 attr.put("isAdmin", asList("false"));
         }

		user.setAttributes(attr);

		for (Map.Entry<String, List<String>> entry : attr.entrySet()) {
			_log.debug("+ attributo: Key = " + entry.getKey() + ", Value = " + entry.getValue());
		}

		_log.debug(" + attr añadidos");

		try {

		if(isUserKCnew) {
			_log.debug("+ creando bean user (6) : " + email);
			realm.createUser(user);
			_log.debug("+ creando bean user (7) : " + email);
			realm.setUserEnabled(user, true);
			_log.debug("+ creando bean user (8) : " + email);
			realm.setUserPass(email, pass);
		}else {
			_log.debug("+ actualizando usuario");
			realm.updateUser(user);
			realm.setUserEnabled(user, true);
		}
		}catch(Exception e) {
			_log.error("error crear : " + e.toString());
        }

		_log.debug("+ usuario creado");
		realm.close();

	}

	public static void changeKCpass(String email, String pass) {
		Realm realm = new Realm(URL,
				REALNAME,
				USERNAME,
				PASS,
				SECRET,
				CLIENT_ID);

		realm.setUserPass(email, pass);
		realm.close();
	}


	public static void createUserImportation(Realm realm,
											 long companyId,
											 String email,
											 String name,
											 String lastName,
											 String nif,
											 String phone,
											 String pass,
											 boolean isAdmin) {

		if(!realm.isConnected()) {
			_log.debug("+ no se pudo conectar con el servidor de keycloak en '" + realm.url + "' ");
			return;
		}

		_log.debug("+ creando bean user (1) : " + email);
		_log.debug("  --- > name : " + name);
		/*_log.debug("  --- > lastName : " + lastName);
		_log.debug("  --- > lastName : " + lastName);
		_log.debug("  --- > companyId : " + companyId);*/

		UserRepresentation user = new UserRepresentation();


		boolean isUserKCnew = true;

		List<String> arrRol = new ArrayList<String>();


		Map<String, List<String>> attr = null;
		user.setEmail(email);


		System.out.println("+ existe el usuario kc: " + realm.isExistUser(user));
		//existe el usuario añadidos el grupo
		if(realm.isExistUser(user)) {
			user = realm.getUserFromKC(email);
			List<String> grp = user.getGroups();
			grp.add(STR_EMPRESA + String.valueOf(companyId));
			user.setGroups(grp);
			isUserKCnew = false;
			attr = user.getAttributes();
			arrRol = user.getRealmRoles();
			//limpiamos los roles de la company actual
			List<String> arrRol_aux = new ArrayList<String>();
			for(String rol : arrRol) {
				if(rol.indexOf(String.valueOf(companyId)) != 0) { //si no es de esta compñia lo respetamos
					arrRol_aux.add(rol);
				}
			}
			arrRol = arrRol_aux;
			arrRol_aux = null;

		}else{
			user.setGroups(asList(STR_EMPRESA + String.valueOf(companyId)));
			attr = new HashMap<String,List<String>>();
		}

		user.setUsername(email);
		user.setFirstName(name);
		user.setLastName(lastName);

		//roles por defecto .. hacerlo config ¿?
		String[] arr = DEFAULT_ROLES.split(",");
		arrRol.add(companyId+"#REGISTRO_HORARIO");
		for(String def : arr) {
			arrRol.add(def);
		}

		//user.setGroups(asList(STR_EMPRESA + String.valueOf(companyId)));
		if(arrRol.size() > 0) user.setRealmRoles(arrRol);

		//atributos
		attr.put("actualizado", asList(new Date().toLocaleString()));
		attr.put("origen", asList("SuiteAdePlus"));

		_log.debug(" + attr empezando");

		attr.put("dni", asList(nif));

		attr.put("phone",asList(phone));

		attr.put(companyId + "_apps", asList("REGISTRO_HORARIO"));

		attr.put("suite_default_company", asList(String.valueOf(companyId)));

		if (isAdmin) {
			attr.put("isAdmin", asList("true"));
		} else {
			attr.put("isAdmin", asList("false"));
		}
		user.setAttributes(attr);

		for (Map.Entry<String, List<String>> entry : attr.entrySet()) {
			System.out.println("+ attributo: Key = " + entry.getKey() + ", Value = " + entry.getValue());
		}

		_log.debug(" + attr añadidos");

		try {

			if(/*isCreateUser && */isUserKCnew) {
				realm.createUser(user);
				realm.setUserEnabled(user, true);
				realm.setUserPass(email, pass);
			}else {
				System.out.println("+ actualizando usuario");
				realm.updateUser(user);
				realm.setUserEnabled(user, true);
			}
		}catch(Exception e) {
			_log.error("error crear : " + e.toString());
		}

		_log.info("+ usuario creado");
		//realm.close();

	}




	//crear usuario
	public static void createUserKeycloak(long companyId,
										  User userLiferay,
										  String nif,
										  String phone,
										  String pass,
										  String endDate,
										  boolean isAdmin){

		PrevingKeycloakUtil.operationUser(true,
				companyId, userLiferay.getUserId(), userLiferay.getEmailAddress(), userLiferay.getFirstName(), userLiferay.getLastName(),
				nif, phone, pass, endDate, isAdmin);

	}

	public static void updateUserKeycloak(long userId, long companyId, String name, String lastName, String nif,
										  String email, String phone, boolean isAdmin, Date endDate) {

		PrevingKeycloakUtil.operationUser(false,
				companyId, userId, email, name, lastName,
				nif, phone, null, String.valueOf( (endDate != null)? endDate.getTime() : ""), isAdmin);
	}
}

package com.adeplus.liferay.portlet.commons.web.keycloak;




import com.adeplus.liferay.portlet.commons.web.client.AdeplusClientUtil;
import com.adeplus.liferay.portlet.commons.web.permission.AdeplusPermissionCompanyUtil;
import com.adeplus.liferay.portlet.suite.manager.model.*;
import com.adeplus.liferay.portlet.suite.manager.service.*;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.util.PrefsPropsUtil;
import io.smallrye.common.function.ExceptionBiPredicate;
import org.keycloak.representations.idm.GroupRepresentation;
import org.keycloak.representations.idm.UserRepresentation;

import java.util.*;

import static java.util.Arrays.asList;

public class AdeplusKeycloakMultiUtil {
	
	private static Log _log = LogFactoryUtil.getLog(AdeplusKeycloakMultiUtil.class);
	
	private static final String STR_EMPRESA = "Empresa_suite_";
	private static final String STR_ROL = "Rol_suite_";

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
    											Date deleteDate){

		Realm realm = new Realm(URL,

					REALNAME,
					USERNAME,
					PASS,
					SECRET,
					CLIENT_ID);


    	_log.info("+ creando grupo/empresa en keycloak(2) : '" + name + "' con id : " + String.valueOf(compId));
        Company company = new Company(String.valueOf(STR_EMPRESA + compId)); //ponemos empresa_suite �?
        company.setAttribute("liferay_compId", asList(String.valueOf(compId)));
        company.setAttribute("nombre", asList(name));       
        company.setAttribute("cif", asList(cif));        
        company.setAttribute("description", asList(description));
		company.setAttribute("createDate", asList(String.valueOf((new Date()).getTime())));
		company.setAttribute("modelVersionMulti", asList("true"));
		if(deleteDate == null) {
        	company.setAttribute("deleteDate", asList(""));
        }else {
        	company.setAttribute("deleteDate", asList(String.valueOf(deleteDate.getTime())));
        }
        
        

        List<String> arrApps = new ArrayList<String>(); 
        List<Application> apps = AdeplusPermissionCompanyUtil.getCompanyApplications(compId);

		String arrValues = "";
		try {
			String name_attr = "", value_attr = "";
			JSONArray jsonClientContratApps = null;
			JSONObject jsonApp = null;
			Application app = null;
			List<CompClientApplication> clients = AdeplusClientUtil.getClientFromCompany(compId);

			if(Validator.isNull(clients)){
				_log.info("AdeplusClientUtil.getClientFromCompany(compId) es NULO");
				return;
			}
			_log.info("clients: " + clients.size());

			for (CompClientApplication cp : clients) {
				name_attr = "cc_" + cp.getClientId() + "_" + cp.getContractId() + "_txt";
				company.setAttribute(name_attr, asList(cp.getDescription()));
				jsonClientContratApps = JSONFactoryUtil.createJSONArray(cp.getApplicationId());
				for (Object objApp : jsonClientContratApps) {
					jsonApp = (JSONObject) objApp;
					if(!value_attr.isEmpty()) value_attr += ",";
					value_attr += jsonApp.getString("appId");
					// Start: para mantener lo antiguo ... eliminar
					app = ApplicationLocalServiceUtil.getApplication(Long.parseLong(jsonApp.getString("appId")));
					if(Validator.isNotNull(app)){
						if (arrValues.isEmpty() || arrValues.indexOf(app.getAlias()) == -1) {
							if (!arrValues.isEmpty()) arrValues += ";";
							arrValues += app.getAlias();
						}
					}
					// End: para mantener lo antiguo ... eliminar

					if(jsonApp.getJSONArray("licenses").length() > 0){
						//value_attr += "#" + jsonApp.getJSONArray("licenses").get(0).toString();
						arrValues += "#" + jsonApp.getJSONArray("licenses").get(0).toString();
					}
				}
				name_attr = "cc_" + cp.getClientId() + "_" + cp.getContractId() + "_apps";
				//company.setAttribute(name_attr, asList(value_attr));
				company.setAttribute(name_attr, asList(arrValues));

			}
		}catch(Exception e){
			e.printStackTrace();
		}





		company.setAttribute("apps", asList(arrValues));
		//END : Quitar cuando se implante solo metodo multiple contrato+cliente

		company.addRole("EMPRESAS SUITEADEPLUS");

        realm.createCompany(company);

        _log.info("+ grupo/empresa '" + name + "' creada");

        realm.close();
    }





    
    public static void setIdCRM(Realm realm, String companyId, String idCRM, String idContrato) {
    	List<UserRepresentation> users = realm.getUserByGroups(asList(STR_EMPRESA + companyId));
    	GroupRepresentation grp = realm.getGroup(STR_EMPRESA + companyId);
        Map<String,List<String>> attr = null;
        UserRepresentation userKC = null;    
        //List<String> arrAppsGrp = grp.getAttributes().get("apps");
        String [] arr = grp.getAttributes().get("apps").get(0).split(";");
    	List<String> arrAppsGrp = asList(arr);
        
        
        List<String> arrAppsUser = null;
        List<String> arrAppsUser_clean = null;
        List<String> arrAppsUserUnchecked = null;
        Date hoy = new Date();
        boolean isExist = false;
        for(UserRepresentation u : users){
        	arrAppsUser_clean = new ArrayList<String>();
        	arrAppsUserUnchecked = new ArrayList<String>();
        	
        	_log.debug("+> update user: " + u.getEmail());
        	userKC = realm.getUserFromKC(u.getEmail());
        	//arrAppsUser = userKC.getAttributes().get(companyId + "_apps");
        	
        	arr = userKC.getAttributes().get(companyId + "_apps").get(0).split(";");
        	arrAppsUser = asList(arr);
        	
        	for(String strUserApp : arrAppsUser) {
        		isExist = false;
        		for(String strApp : arrAppsGrp) {
        			if(strApp.equalsIgnoreCase(strUserApp)) {
        				isExist = true;
        				break;
        			}
        		}
        		
        		if(!isExist) { //si no lo tiene tengo que quitarselo
        			arrAppsUserUnchecked.add(strUserApp + "|" + hoy.getTime());  
        		}else {
        			_log.debug("	+> update user arrAppsUser_clean: " + strUserApp);
        			arrAppsUser_clean.add(strUserApp);        			
        		}
    		}
        	      	
        	_log.debug("+> update user atributos: " + u.getEmail());
        	
        	try {
        	attr = userKC.getAttributes();
        	
        	
        	//pongo los quitados ... respetando los que ya tenia y no es los que han deseleccionado
        	List<String> listaUnchecked = userKC.getAttributes().get(companyId + "_apps_unchecked");
        	
        	if(listaUnchecked != null && listaUnchecked.size() > 0) {
        		List<String> lista_tmp = new ArrayList<String>(arrAppsUserUnchecked);
        		String[] arrOld = null;
        		String[] arrNew = null;
        		for(String unc : listaUnchecked) {
        			arrOld = unc.split("|");
        			isExist = false;
        			for(String unc_new : lista_tmp) {
        				arrNew = unc_new.split("|");
        				if(arrOld.equals(arrNew)) {
        					isExist = true;
        					break;
        				}        				
        			}
        			if(!isExist) {
        				arrAppsUserUnchecked.add(unc);
        			}
        		}
        		
        	}
        	}catch(Exception e) {_log.error(e);}
        	
        	//los permitidos ... si es administrador, todos, si es usuario normal solo los que tenia seleccionados
        	if(((List<String>)userKC.getAttributes().get("isAdmin")).get(0).equals("true")) {
        		attr.put(companyId + "_apps",arrAppsGrp);
        		attr.remove(companyId + "_apps_unchecked");
        	}else {
        		attr.put(companyId + "_apps",arrAppsUser_clean);	
        		attr.put(companyId + "_apps_unchecked",arrAppsUserUnchecked);
        	}
        	
        	//attr.put(companyId + "_apps_unchecked",arrAppsUserUnchecked);
        	
        	attr.put(companyId + "_idClientCRM",asList(idCRM));
        	attr.put(companyId + "_idClientContrato",asList(idContrato));
        	
        	attr.put("idClientContrato", asList(idContrato));            
        	attr.put("idClientCRM",asList(idCRM));
        	
        	userKC.setAttributes(attr);
        	
        	//user = AdeplusKeycloakUtil.getUnchecked(user,attr, arrAppsUser, companyId);
        	
                    
        	realm.updateUser(userKC);
        }
    	
    }

    public static void changeKCpass(String email, String pass) {
    	Realm realm = new Realm(URL,
                REALNAME,
                USERNAME,
                PASS,
                SECRET,
                CLIENT_ID);

    	
    	UserRepresentation user = new UserRepresentation();
    	user.setEmail(email);
    	_log.debug(" + email: " + email); //+ " / pass: " + pass);
    	if(realm.isExistUser(user)) {
    		realm.setUserPass(email, pass);  
    		_log.debug(" + pass actualizada"); 
    	}else{
    		_log.debug(" + pass no actualizada ... not user found");
    	}
    	realm.close();
    	
    }
    
    
    public static void setKCdefaultCompany(long compId, String email,long clientId, long contractId) {
    	Realm realm = new Realm(URL,
                REALNAME,
                USERNAME,
                PASS,
                SECRET,
                CLIENT_ID);

    	
    	UserRepresentation user = new UserRepresentation();
    	user.setEmail(email);
    	_log.debug(" + email: " + email);
    	if(realm.isExistUser(user)) {    		
    		 user = realm.getUserFromKCwithOutRoles(email);
    		 Map<String,List<String>> map = user.getAttributes();
    		 map.put("suite_default_company", asList(String.valueOf(compId)));
			 map.put("idClientCRM", asList(String.valueOf(clientId)));
			 map.put("idClientContrato", asList(String.valueOf(contractId)));
    		 user.setAttributes(map);
    		 realm.updateUser(user);
    		 
    		 
    	}
    	
    	
    	realm.close();
    	
    }

	public static void setKCdefaultClientContract(long compId, String email) {
		Realm realm = new Realm(URL,
				REALNAME,
				USERNAME,
				PASS,
				SECRET,
				CLIENT_ID);


		UserRepresentation user = new UserRepresentation();
		user.setEmail(email);
		_log.debug(" + email: " + email);
		if(realm.isExistUser(user)) {
			user = realm.getUserFromKC(email);
			Map<String,List<String>> map = user.getAttributes();
			map.put("suite_default_company", asList(String.valueOf(compId)));
			user.setAttributes(map);
			realm.updateUser(user);


		}


		realm.close();

	}


	public static void operationUserMulti(boolean isCreateUser,
									 long companyId,
									 long userId,
									 String email,
									 String name,
									 String lastName,
									 String nif,
									 String phone,
									 String pass,
									 String endDate,
									 boolean isAdmin,
									 boolean isNewEmpresa,
									 JSONArray listApp) {

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
		_log.info("+ creando bean user (1) : " + email);
		_log.info("  --- > name : " + name);
		_log.info("  --- > lastName : " + lastName);
		_log.info("  --- > lastName : " + lastName);
		_log.info("  --- > userId : " + userId);
		_log.info("  --- > companyId : " + companyId);
		_log.info("  --- > pass : " + pass);

		UserRepresentation user = new UserRepresentation();
		UserCompany usrCompany = UserCompanyLocalServiceUtil.getUserDefaultCompany(userId);
		String defaultCompany = String.valueOf(usrCompany.getCompId()); //compa�ia seleccionada y/o por defecto
		Comp comp = null;
		List<String> arrRol = new ArrayList<String>();

		try {
			comp = CompLocalServiceUtil.getComp(companyId);
		} catch (PortalException e1) {
			e1.printStackTrace();
		}

		Map<String, List<String>> attr = null;
		user.setEmail(email);

		//List<String> apps_old = null;
		//existe el usuario anadidos el grupo
		isCreateUser = realm.isExistUser(user);


		if(isCreateUser) { //CAMBIAR QUE NO RECIBA EL PARAMETRO, SIEMPRE REVISAR SI EXISTE EN KC
			user = realm.getUserFromKC(email);
			List<String> grp = user.getGroups();
			grp.add(STR_EMPRESA + String.valueOf(companyId));
			user.setGroups(grp);
			attr = user.getAttributes();
			arrRol = user.getRealmRoles();

			//limpiar contratos en attr
			Iterator iterator = attr.entrySet().iterator();
			Map.Entry<String, String> entry = null;
			while (iterator.hasNext()) {
				entry = (Map.Entry<String, String>) iterator.next();
				if(entry.getKey().toString().indexOf("cc_" + companyId + "_") == 0){
					iterator.remove();
				}
			}

			//limpiamos los roles de la company actual
			List<String> arrRol_aux = new ArrayList<String>();
			for(String rol : arrRol) {
				if(rol.indexOf(String.valueOf(companyId)) != 0) { //si no es de esta comp�ia lo respetamos
					arrRol_aux.add(rol);
				}
			}
			arrRol = arrRol_aux;
			arrRol_aux = null;

		}else{
			user.setGroups(asList(STR_EMPRESA + String.valueOf(companyId)));
			attr = new HashMap<String,List<String>>();
		}
		//attr = new HashMap<String,List<String>>();
		user.setUsername(email);
		user.setFirstName(name);
		user.setLastName(lastName);

		//roles
		List<String> arrAppsUser = new ArrayList<String>();
		//List<Role> roles = RoleLocalServiceUtil.getRoles(QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		Application app = null;
		JSONArray jsonClientContratApps = null, jsonClientContratAppsOld = null, jsonAllApps = null;
		List<UserApplicationClient> clients = UserApplicationClientLocalServiceUtil.getApplicationsFromUser(userId,companyId);
		_log.info("clients: " + clients.size());
		String name_attr = "", value_attr = "";
		JSONObject jsonApp = null;
		Role rol = null;
		List<String> arrAppdIds = null;
		List<Long> allAppIds= null;
		Date hoy = new Date();
		JSONObject jsonObject=null;
		try {
			long clientId = 0;
			long contractId = 0;
			//debemos recuperar el array ya que los clientes se actualizan de forma posterior
			for (int i = 0; i < listApp.length(); i++) {
				jsonObject = listApp.getJSONObject(i);
				clientId=Long.parseLong(jsonObject.getString("cliente"));
				contractId=Long.parseLong(jsonObject.getString("contrato"));

				jsonClientContratApps = JSONFactoryUtil.createJSONArray(jsonObject.getJSONArray("apps").toString());
				arrAppdIds = new ArrayList<String>();
				allAppIds =new ArrayList<Long>();
				List<String> uncheckedApps=new ArrayList<String>();
				String[] arr = null;

				if (isCreateUser && user.getAttributes().size() > 0 && user.getAttributes().containsKey("cc_" + clientId + "_" + contractId + "_apps_unchecked")) {
					arr = user.getAttributes().get("cc_" + clientId + "_" + contractId + "_apps_unchecked").get(0).split(";");
					uncheckedApps=asList(arr);
				}
				for (Object objApp : jsonClientContratApps) {
					jsonApp = (JSONObject) objApp;
					if (!value_attr.isEmpty()) value_attr += ",";
					value_attr += jsonApp.getString("appId");
					// Start: para mantener lo antiguo ... eliminar

					_log.info("jsonApp.getString(\"appId\"): " + jsonApp.getString("appId"));

					app = ApplicationLocalServiceUtil.getApplication(Long.parseLong(jsonApp.getString("appId")));
					if(Validator.isNotNull(app)){
						if(!isNewEmpresa && jsonApp.getJSONArray("licenses").length() > 0){
							rol = RoleLocalServiceUtil.getRole(Long.parseLong(jsonApp.getJSONArray("licenses").get(0).toString()));

							_log.info("jsonApp.getJSONArray(\"licenses\").get(0).toString(): " + jsonApp.getJSONArray("licenses").get(0).toString());
							_log.info("rol.getAlias(): " + rol.getAlias());

							//arrRol.add(companyId + "#" + app.getAlias() + "#" + rol.getAlias()); //modo mono
							arrRol.add(companyId + "-" + clientId + "-" + contractId + "#" + app.getAlias() + "#" + rol.getAlias()); //modo multi
							arrAppdIds.add(app.getAlias() + "#" + rol.getAlias());
							allAppIds.add(app.getApplicationId());
						}else{
							//arrRol.add(companyId + "#" + app.getAlias() ); //modo mono
							arrRol.add(companyId + "-" + clientId + "-" + contractId + "#" + app.getAlias() ); //modo multi
							arrAppdIds.add(String.valueOf(app.getAlias()));
							allAppIds.add(app.getApplicationId());
						}
						arrAppsUser.add(app.getAlias());
					}
					// End: para mantener lo antiguo ... eliminar
				}

				for(UserApplicationClient cp : clients){
					_log.info("jsonObject apps: " + jsonObject.toString());
					if(!Validator.isNotNull(cp.getApplicationId()) ||(cp.getClientId()==clientId
					&& contractId==cp.getContractId())){
						try{
							jsonClientContratAppsOld = JSONFactoryUtil.createJSONArray(cp.getApplicationId());
							for (Object objApp : jsonClientContratAppsOld) {
								jsonApp = (JSONObject) objApp;
								if (!value_attr.isEmpty()) value_attr += ",";
								long appOld= Long.valueOf(jsonApp.getString("appId"));
								//Si el listado de aplicaciones no contiene la app significa que esta unchecked
								if (!allAppIds.contains(appOld)) {
									uncheckedApps.add(appOld + "|" + hoy.getTime());
								}else if (uncheckedApps.indexOf(appOld + "|") >= 0) {
									uncheckedApps.removeIf(unchApp->unchApp.contains(appOld + "|"));
								}
							}
						}catch(Exception e){
							_log.error("Error: "+e.getMessage());
						}
					}
				}
				attr.put(companyId + "_apps", (arrAppsUser.size() > 0)? arrAppsUser : asList(""));
				attr.put("cc_"  + companyId + "_" +  clientId + "_" + contractId + "_apps",  (arrAppdIds.size() > 0)? arrAppdIds : asList(""));
				attr.put("cc_"  + companyId + "_" +  clientId + "_" + contractId + "_name",
						Collections.singletonList((CompClientApplicationLocalServiceUtil.getClientByCompAndClientAndContract(companyId, clientId, contractId)).getDescription()));

				if(uncheckedApps.size()>0) {
					attr.put("cc_" + companyId + "_" + clientId + "_" + contractId + "_apps_unchecked", uncheckedApps);
				}

			}


			if(!isCreateUser){ //es nuevo
				for (UserApplicationClient cp : clients) {
					contractId=cp.getContractId();
					clientId=cp.getClientId();
					jsonClientContratApps = JSONFactoryUtil.createJSONArray(cp.getApplicationId());
					arrAppdIds = new ArrayList<String>();
					for (Object objApp : jsonClientContratApps) {
						jsonApp = (JSONObject) objApp;
						if (!value_attr.isEmpty()) value_attr += ",";
						value_attr += jsonApp.getString("appId");
						// Start: para mantener lo antiguo ... eliminar
						_log.info("jsonApp.getString(\"appId\"): " + jsonApp.getString("appId"));
						app = ApplicationLocalServiceUtil.getApplication(Long.parseLong(jsonApp.getString("appId")));
						if(Validator.isNotNull(app)){
							if(!isNewEmpresa && jsonApp.getJSONArray("licenses").length() > 0){
								rol = RoleLocalServiceUtil.getRole(Long.parseLong(jsonApp.getJSONArray("licenses").get(0).toString()));

								_log.info("jsonApp.getJSONArray(\"licenses\").get(0).toString(): " + jsonApp.getJSONArray("licenses").get(0).toString());
								_log.info("rol.getAlias(): " + rol.getAlias());

								//arrRol.add(companyId + "#" + app.getAlias() + "#" + rol.getAlias());//modo mono
								arrRol.add(companyId + "-" + clientId + "-" + contractId + "#" + app.getAlias() + "#" + rol.getAlias()); //modo multi
								//arrAppdIds.add(app.getApplicationId() + "#" + rol.getRoleId());
								arrAppdIds.add(app.getAlias() + "#" + rol.getAlias());

							}else{
								//arrRol.add(companyId + "#" + app.getAlias() ); //modo mono
								arrRol.add(companyId + "-" + clientId + "-" + contractId + "#" + app.getAlias() ); //modo multi
								arrAppdIds.add(String.valueOf(app.getAlias()));
							}
							arrAppsUser.add(app.getAlias());
						}
						// End: para mantener lo antiguo ... eliminar
					}
					attr.put(companyId + "_apps", (arrAppsUser.size() > 0)? arrAppsUser : asList(""));
					attr.put("cc_" + companyId + "_" + clientId + "_" + contractId + "_apps",  (arrAppdIds.size() > 0)? arrAppdIds : asList(""));
					attr.put("cc_"  + companyId + "_" +  clientId + "_" + contractId + "_name",
							Collections.singletonList((CompClientApplicationLocalServiceUtil.getClientByCompAndClientAndContract(companyId, clientId, contractId)).getDescription()));
					attr.put("idClientCRM", asList(String.valueOf(clientId)));
					attr.put("idClientContrato", asList(String.valueOf(contractId)));
				}
			}

		}catch(Exception e){
			e.printStackTrace();
		}
		_log.info("ROL SIZE: " + arrRol.size());
		if(arrRol.size() > 0) user.setRealmRoles(arrRol);

		//atributos
		attr.put("actualizado", asList(new Date().toLocaleString()));
		attr.put("origen", asList("SuiteAdePlus"));

		attr.put("dni", asList(nif));



		attr.put("phone",asList(phone));
		attr.put("endDate",asList(endDate));

		attr.put("suite_default_company", asList(String.valueOf(usrCompany.getCompId())));

		if (isAdmin) {
			attr.put("isAdmin", asList("true"));
		} else {
			attr.put("isAdmin", asList("false"));
		}

		if(!isCreateUser) {
			_log.info("KC creación");
			user.setAttributes(attr);
			realm.createUser(user);
			realm.setUserEnabled(user, true);
			realm.setUserPass(email, pass);
		}else{
			_log.info("KC actualizacion (1)");
			user = AdeplusKeycloakMultiUtil.getUnchecked(user,attr, arrAppsUser, companyId);
			_log.info("KC actualizacion without (2)");
			//realm.updateUser(user);
			realm.updateUserWithoutGroups(user, usrCompany.getCompId(), listApp);
			_log.info("KC actualizacion (3)");
			realm.setUserEnabled(user, true);
			_log.info("KC actualizacion (4)");
		}

		_log.info("+ (Multi) usuario creado nuevo");
		realm.close();

	}

	public static UserRepresentation getUnchecked(UserRepresentation user,
												  Map<String, List<String>> attr,
												  List<String> arrAppsUser,
												  long companyId) {
		try {
			if(true)return user; // necesidad por comp + cliente + contrato (borrandolo¿?)

			_log.info("+ kc getUnchecked(0)");
			// formato attr : ID-EMPRESA_apps_unchecked --> ALIAS_APP#TIMESTAMP
			// TIMESTAMP --> hoy			
			Date hoy = new Date();
			// obtener los anteriores del campo apps
			List<String> old_values = null;
			String[] arr = null;
			if (user.getAttributes().containsKey(companyId + "_apps")) {
				arr = user.getAttributes().get(companyId + "_apps").get(0).split(";");
				old_values = asList(arr);
			} else {
				old_values = new ArrayList();
			}
			_log.info("+ kc getUnchecked(1)");

			List <String> old_values_unchecked = null;
			List <String> listTemp = null;
			if (user.getAttributes().containsKey(companyId + "_apps_unchecked")) {			
				arr = user.getAttributes().get(companyId + "_apps_unchecked").get(0).split(";");
				listTemp = asList(arr);
				old_values_unchecked = new ArrayList<>(listTemp);
			}else{
				old_values_unchecked = new ArrayList();;
			}
			_log.info("+ kc getUnchecked(2)");

			// Comparo si han cambiado
			boolean isEqual = false;
			for (String strOld : old_values) {
				isEqual = false;
				for (String strNew : arrAppsUser) {
					if (strOld.equalsIgnoreCase(strNew)) {
						isEqual = true;
						break;
					}
				}
				if (!isEqual) {
					old_values_unchecked.add(String.valueOf(strOld + "|" + hoy.getTime()));
				}
			}
			_log.info("+ kc getUnchecked(3)");
			
			// hay nuevos ?
			List<String> arrAppsUser_tmp = new ArrayList<String>(arrAppsUser);
			arrAppsUser_tmp.removeAll(old_values);
			List  newOldUnchecked = new ArrayList(old_values_unchecked);
						
			if (arrAppsUser.size() > 0) {
				for (String str : arrAppsUser_tmp) {					
					// lo quitamos del unchecked
					for (Object strUnchecked : old_values_unchecked) {

						if (((String)strUnchecked).indexOf(str + "|") == 0) {
							newOldUnchecked.remove(strUnchecked);
							break;
						}
					}
				}

			}

			_log.info("+ kc getUnchecked(4)");

			// guardo cuales faltan
			attr.put(companyId + "_apps_unchecked", newOldUnchecked);
			// guardo nuevas
			attr.put(companyId + "_apps", arrAppsUser);
			user.setAttributes(attr);

			_log.info("+ kc getUnchecked(5)");

		} catch (Exception e) {
			_log.error("getUnchecked : " + e.toString());
			e.printStackTrace();
			
		}
		return user;
	}
    
    //crear usuario
    public static void createUserKeycloak(long companyId, 
			User userLiferay, 
			String nif, 
			String phone, 
			String pass, 
			String endDate, 
			boolean isAdmin){

    	AdeplusKeycloakMultiUtil.operationUserMulti(true,
    			companyId, userLiferay.getUserId(), userLiferay.getEmailAddress(), userLiferay.getFirstName(), userLiferay.getLastName(),
    			nif, phone, pass, endDate, isAdmin, false, JSONFactoryUtil.createJSONArray());
    	
    
    	
    }



	public static void updateUserKeycloakMulti(long userId, long companyId, String name, String lastName, String nif,
										  String email, String phone, boolean isAdmin, Date endDate,JSONArray listApp) {

		AdeplusKeycloakMultiUtil.operationUserMulti(false,
				companyId, userId, email, name, lastName,
				nif, phone, null, String.valueOf( (endDate != null)? endDate.getTime() : ""), isAdmin,
				false,listApp);
	}
    
    public static void enableUserFromKC (String email, boolean isActive) {
    	Realm realm = new Realm(URL,
                REALNAME,
                USERNAME,
                PASS,
                SECRET,
                CLIENT_ID);
    	
    	UserRepresentation  user = realm.getUserFromKC(email);
    	if(user != null) {
    		realm.updateUser(user);
    		realm.setUserEnabled(user, isActive);
    		_log.debug("+ usuario deshabilitado");
    	}
    	
   		realm.close();
    	
    }
}

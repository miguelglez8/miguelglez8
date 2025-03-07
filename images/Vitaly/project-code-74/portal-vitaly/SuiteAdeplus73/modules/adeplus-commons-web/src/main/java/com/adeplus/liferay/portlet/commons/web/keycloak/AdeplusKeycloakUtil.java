package com.adeplus.liferay.portlet.commons.web.keycloak;


import com.adeplus.liferay.portlet.commons.web.bean.AppPermission;
import com.adeplus.liferay.portlet.commons.web.client.AdeplusClientUtil;
import com.adeplus.liferay.portlet.commons.web.permission.AdeplusPermissionCompanyUtil;
import com.adeplus.liferay.portlet.commons.web.user.AdeplusUserUtil;
import com.adeplus.liferay.portlet.suite.manager.model.*;
import com.adeplus.liferay.portlet.suite.manager.service.*;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.util.PrefsPropsUtil;
import org.keycloak.representations.idm.GroupRepresentation;
import org.keycloak.representations.idm.UserRepresentation;

import java.util.*;

import static java.util.Arrays.asList;

public class AdeplusKeycloakUtil {

    private static Log _log = LogFactoryUtil.getLog(AdeplusKeycloakUtil.class);

    private static final String STR_EMPRESA = "Empresa_suite_";
    private static final String STR_ROL = "Rol_suite_";

    //private static final String REALNAME = "PREVING";
    //private static final String URL = "http://10.0.1.13:8180/auth";
    //private static final String USERNAME = "admin";
    //private static final String PASS = "test";
    //private static final String CLIENT_ID = "realm-management";
    //private static final String SECRET = "f7f79e46-ee5f-4ba1-b9e8-cc0e287264cc";

    private static final String REALNAME = PrefsPropsUtil.getString("preving.keycloak.configuration.realname");
    private static final String URL = PrefsPropsUtil.getString("preving.keycloak.configuration.url");
    private static final String USERNAME = PrefsPropsUtil.getString("preving.keycloak.configuration.username");
    private static final String PASS = PrefsPropsUtil.getString("preving.keycloak.configuration.pass");
    private static final String CLIENT_ID = PrefsPropsUtil.getString("preving.keycloak.configuration.clientid");
    private static final String SECRET = PrefsPropsUtil.getString("preving.keycloak.configuration.secret");

    private static final String DEFAULT_ROLES = "PROTECCION_DATOS,COMPLIANCE,PLAN_IGUALDAD,OTROS_SERVICIOS";


    //private static Realm realm = null;

    //@see Para crear/actualizar grupo/empresa
    public static void createCompanyKeycloak(long compId,
                                             String name,
                                             String cif,
                                             String description,
                                             String observations,
                                             Date deleteDate,
                                             String idCRM,
                                             String idContrato) {


        //if(realm == null) {
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
        if (deleteDate == null) {
            company.setAttribute("deleteDate", asList(""));
        } else {
            company.setAttribute("deleteDate", asList(String.valueOf(deleteDate.getTime())));
        }


        List<String> arrApps = new ArrayList<String>();
        List<Application> apps = AdeplusPermissionCompanyUtil.getCompanyApplications(compId);

        //lo ponemos con separador ;
        String arrValues = "";
        for (Application app : apps) {
            if (!arrValues.isEmpty()) arrValues += ";";
            arrValues += app.getAlias();
        }
        company.setAttribute("apps", asList(arrValues));

        company.setAttribute("idCRM", asList(idCRM));
        company.setAttribute("idContrato", asList(idContrato));

        company.addRole("EMPRESAS SUITEADEPLUS");

        realm.createCompany(company);


        //actualizar el IDCRM de sus usuarios en la empresa
        AdeplusKeycloakUtil.setIdCRM(realm, String.valueOf(compId), idCRM, idContrato);

        _log.debug("+ grupo/empresa '" + name + "' creada");

        realm.close();
    }


    public static void setIdCRM(Realm realm, String companyId, String idCRM, String idContrato) {
        List<UserRepresentation> users = realm.getUserByGroups(asList(STR_EMPRESA + companyId));
        GroupRepresentation grp = realm.getGroup(STR_EMPRESA + companyId);
        Map<String, List<String>> attr = null;
        UserRepresentation userKC = null;
        //List<String> arrAppsGrp = grp.getAttributes().get("apps");
        String[] arr = grp.getAttributes().get("apps").get(0).split(";");
        List<String> arrAppsGrp = asList(arr);


        List<String> arrAppsUser = null;
        List<String> arrAppsUser_clean = null;
        List<String> arrAppsUserUnchecked = null;
        Date hoy = new Date();
        boolean isExist = false;
        for (UserRepresentation u : users) {
            arrAppsUser_clean = new ArrayList<String>();
            arrAppsUserUnchecked = new ArrayList<String>();

            _log.debug("+> update user: " + u.getEmail());
            userKC = realm.getUserFromKC(u.getEmail());
            //arrAppsUser = userKC.getAttributes().get(companyId + "_apps");

            arr = userKC.getAttributes().get(companyId + "_apps").get(0).split(";");
            arrAppsUser = asList(arr);

            for (String strUserApp : arrAppsUser) {
                isExist = false;
                for (String strApp : arrAppsGrp) {
                    if (strApp.equalsIgnoreCase(strUserApp)) {
                        isExist = true;
                        break;
                    }
                }

                if (!isExist) { //si no lo tiene tengo que quitarselo
                    arrAppsUserUnchecked.add(strUserApp + "|" + hoy.getTime());
                } else {
                    _log.debug("	+> update user arrAppsUser_clean: " + strUserApp);
                    arrAppsUser_clean.add(strUserApp);
                }
            }

            _log.debug("+> update user atributos: " + u.getEmail());

            try {
                attr = userKC.getAttributes();


                //pongo los quitados ... respetando los que ya tenia y no es los que han deseleccionado
                List<String> listaUnchecked = userKC.getAttributes().get(companyId + "_apps_unchecked");

                if (listaUnchecked != null && listaUnchecked.size() > 0) {
                    List<String> lista_tmp = new ArrayList<String>(arrAppsUserUnchecked);
                    String[] arrOld = null;
                    String[] arrNew = null;
                    for (String unc : listaUnchecked) {
                        arrOld = unc.split("|");
                        isExist = false;
                        for (String unc_new : lista_tmp) {
                            arrNew = unc_new.split("|");
                            if (arrOld.equals(arrNew)) {
                                isExist = true;
                                break;
                            }
                        }
                        if (!isExist) {
                            arrAppsUserUnchecked.add(unc);
                        }
                    }

                }
            } catch (Exception e) {
                _log.error(e);
            }

            //los permitidos ... si es administrador, todos, si es usuario normal solo los que tenia seleccionados
            if (((List<String>) userKC.getAttributes().get("isAdmin")).get(0).equals("true")) {
                attr.put(companyId + "_apps", arrAppsGrp);
                attr.remove(companyId + "_apps_unchecked");
            } else {
                attr.put(companyId + "_apps", arrAppsUser_clean);
                attr.put(companyId + "_apps_unchecked", arrAppsUserUnchecked);
            }

            //attr.put(companyId + "_apps_unchecked",arrAppsUserUnchecked);

            attr.put(companyId + "_idClientCRM", asList(idCRM));
            attr.put(companyId + "_idClientContrato", asList(idContrato));

            attr.put("idClientContrato", asList(idContrato));
            attr.put("idClientCRM", asList(idCRM));

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
        if (realm.isExistUser(user)) {
            realm.setUserPass(email, pass);
            _log.debug(" + pass actualizada");
        } else {
            _log.debug(" + pass no actualizada ... not user found");
        }
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
        _log.debug(" + email: " + email);
        if (realm.isExistUser(user)) {
            user = realm.getUserFromKC(email);
            Map<String, List<String>> map = user.getAttributes();
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
                                     boolean isAdmin,
                                     List<AppPermission> appPermissionList) {


        Realm realm = new Realm(URL,
                REALNAME,
                USERNAME,
                PASS,
                SECRET,
                CLIENT_ID);

        if (!realm.isConnected()) {
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


        _log.info("KC PASO 1");

        if (isCreateUser) { //CAMBIAR QUE NO RECIBA EL PARAMETRO, SIEMPRE REBISE SI EXISTE EN KC
            user = realm.getUserFromKC(email);
            List<String> grp = user.getGroups();
            grp.add(STR_EMPRESA + String.valueOf(companyId));
            user.setGroups(grp);
            //isUserKCnew = false;
            attr = user.getAttributes();
            arrRol = user.getRealmRoles();
            //limpiamos los roles de la company actual
            List<String> arrRol_aux = new ArrayList<String>();
            for (String rol : arrRol) {
                if (rol.indexOf(String.valueOf(companyId)) != 0) { //si no es de esta comp�ia lo respetamos
                    arrRol_aux.add(rol);
                }
            }
            arrRol = arrRol_aux;
            arrRol_aux = null;

        } else {
            user.setGroups(asList(STR_EMPRESA + String.valueOf(companyId)));
            attr = new HashMap<String, List<String>>();
        }

        _log.info("KC PASO 2");

        user.setUsername(email);
        user.setFirstName(name);
        user.setLastName(lastName);

        //roles
        List<String> arrAppsUser = new ArrayList<String>();
        List<Role> roles = RoleLocalServiceUtil.getRoles(QueryUtil.ALL_POS, QueryUtil.ALL_POS);

        Application app = null;
        UserRole rolUser = null;
        if (roles != null) {
            for (Role rol : roles) {
                try {
                    rolUser = UserRoleLocalServiceUtil.getUserRole(userId, companyId, rol.getRoleId());
                    app = ApplicationLocalServiceUtil.getApplication(rol.getApplicationId());

                    if (rolUser != null && app != null) {
                        arrRol.add(companyId + "#" + app.getAlias() + "#" + rol.getAlias());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }//end for
        }
        //NEW

        _log.info("KC PASO 3");

        List<UserApplication> lstApp = null;

        if (appPermissionList == null) {
			lstApp = UserApplicationLocalServiceUtil.getApplicationsFromUser(userId, companyId);
			if (lstApp != null) {
				for (UserApplication appUser : lstApp) {
					try {
						app = ApplicationLocalServiceUtil.getApplication(appUser.getApplicationId());
						if (app != null && !app.getAlias().toString().isEmpty()) {
							arrAppsUser.add(app.getAlias());
							arrRol.add(companyId + "#" + app.getAlias().toString());
						}
					} catch (PortalException e) {
						e.printStackTrace();
					}
				}//end for
			}
        }else{

			for (AppPermission appPermision : appPermissionList) {
				try {
                    if(appPermision.isHasPermission()) {
                        app = ApplicationLocalServiceUtil.getApplication(appPermision.getApplication().getApplicationId());
                        if (app != null && !app.getAlias().toString().isEmpty()) {
                            arrAppsUser.add(app.getAlias());
                            arrRol.add(companyId + "#" + app.getAlias().toString());
                        }
                    }
				} catch (PortalException e) {
					e.printStackTrace();
				}
			}//end for
		}


        _log.info("KC PASO 4");


        if (arrRol.size() > 0) user.setRealmRoles(arrRol);

        //atributos
        attr.put("actualizado", asList(new Date().toLocaleString()));
        attr.put("origen", asList("SuiteAdePlus"));

        attr.put("dni", asList(nif));
        //attr.put("idClientCRM",asList("PONER ID CRM")); // <- !!! tenemos que obtenerlo de algun lado
        if (comp != null) {
            attr.put("idClientCRM", asList(comp.getIdCrm()));
            attr.put(companyId + "_idClientCRM", asList(comp.getIdCrm()));
            attr.put("idClientContrato", asList(comp.getIdContrato()));
            attr.put(companyId + "_idClientContrato", asList(comp.getIdContrato()));
        }


        attr.put("phone", asList(phone));
        attr.put("endDate", asList(endDate));


        attr.put("suite_default_company", asList(String.valueOf(usrCompany.getCompId())));

        if (isAdmin) {
            attr.put("isAdmin", asList("true"));
        } else {
            attr.put("isAdmin", asList("false"));
        }

        _log.info("KC PASO 5");

        //new apps
        //attr.put("apps", attr.get(String.valueOf(usrCompany.getCompId()) + "_apps"));
        attr.put("apps", (arrAppsUser.size() > 0) ? arrAppsUser : asList(""));
        attr.put("idClientContrato", asList(comp.getIdContrato()));
        attr.put("idClientCRM", asList(comp.getIdCrm()));

        try {
            _log.debug("isCreateUser en KC: " + isCreateUser);
            if (!isCreateUser) {
                attr.put(companyId + "_apps", (arrAppsUser.size() > 0) ? arrAppsUser : asList(""));
                user.setAttributes(attr);
                realm.createUser(user);
                realm.setUserEnabled(user, true);
                realm.setUserPass(email, pass);

            } else {
                //obtener los permisos/roles anteriores (tema de los 30 dias)
                user = AdeplusKeycloakUtil.getUnchecked(user, attr, arrAppsUser, companyId);
                realm.updateUser(user);
                realm.setUserEnabled(user, true);
            }
        	
        	/*if(isCreateUser && isUserKCnew) {
        		attr.put(companyId + "_apps", (arrAppsUser.size() > 0)? arrAppsUser : asList(""));
        		user.setAttributes(attr);        		
        		realm.createUser(user);
        		realm.setUserEnabled(user, true);
        		realm.setUserPass(email, pass);
        	}else {  
        		
        		//obtener los permisos/roles anteriores (tema de los 30 dias)
           	 	user = AdeplusKeycloakUtil.getUnchecked(user,attr, arrAppsUser, companyId);
   				realm.updateUser(user);
   				realm.setUserEnabled(user, true); 
        	}*/
        } catch (Exception e) {
            _log.error("error crear : " + e.toString());
        }

        _log.info("KC PASO 6");
        _log.info("+ usuario creado");
        realm.close();

    }


    public static UserRepresentation getUnchecked(UserRepresentation user, Map<String, List<String>> attr, List<String> arrAppsUser, long companyId) {
        try {
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

            List<String> old_values_unchecked = null;
            List<String> listTemp = null;
            if (user.getAttributes().containsKey(companyId + "_apps_unchecked")) {
                arr = user.getAttributes().get(companyId + "_apps_unchecked").get(0).split(";");
                listTemp = asList(arr);
                old_values_unchecked = new ArrayList<>(listTemp);
            } else {
                old_values_unchecked = new ArrayList();
                ;
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

            List newOldUnchecked = new ArrayList(old_values_unchecked);

            if (arrAppsUser.size() > 0) {
                for (String str : arrAppsUser_tmp) {
                    // lo quitamos del unchecked
                    for (Object strUnchecked : old_values_unchecked) {

                        if (((String) strUnchecked).indexOf(str + "|") == 0) {
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

			/*for (Map.Entry<String, List<String>> entry : attr.entrySet()) {
				_log.debug("+ attributo: Key = " + entry.getKey() + ", Value = " + entry.getValue());
			}*/
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
                                          boolean isAdmin,
										  List<AppPermission> appPermissionList) {
        AdeplusKeycloakUtil.operationUser(true,
                companyId, userLiferay.getUserId(), userLiferay.getEmailAddress(), userLiferay.getFirstName(), userLiferay.getLastName(),
                nif, phone, pass, endDate, isAdmin,appPermissionList);


    }

    public static void updateUserKeycloak(long userId, long companyId, String name, String lastName, String nif,
                                          String email, String phone, boolean isAdmin, Date endDate) {

        AdeplusKeycloakUtil.operationUser(false,
                companyId, userId, email, name, lastName,
                nif, phone, null, String.valueOf((endDate != null) ? endDate.getTime() : ""), isAdmin, null);
    }

    public static void enableUserFromKC(String email, boolean isActive) {
        Realm realm = new Realm(URL,
                REALNAME,
                USERNAME,
                PASS,
                SECRET,
                CLIENT_ID);

        UserRepresentation user = realm.getUserFromKC(email);
        if (user != null) {
            realm.updateUser(user);
            realm.setUserEnabled(user, isActive);
            _log.debug("+ usuario deshabilitado");
        }

        realm.close();

    }
}

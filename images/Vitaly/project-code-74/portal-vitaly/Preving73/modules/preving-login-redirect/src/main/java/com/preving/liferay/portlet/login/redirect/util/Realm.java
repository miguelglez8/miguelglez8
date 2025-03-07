package com.preving.liferay.portlet.login.redirect.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import static java.util.Arrays.asList;

import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.preving.liferay.portlet.login.redirect.constants.LoginRedirectPortletKeys;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.*;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.GroupRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.jboss.resteasy.client.jaxrs.internal.ResteasyClientBuilderImpl;


public class Realm implements Serializable{
	
	private final int poolSize = 10;

/*    private String url;
    private String realmName;
    private String userAdmin;
    private String passAdmin;
    private String secret;
    private String clientId;*/

    private static /*final*/ String realmName 	= PrefsPropsUtil.getString(LoginRedirectPortletKeys.KEYCLOAK_CONFIGURATION_REALNAME);
    private static /*final*/ String url 		= PrefsPropsUtil.getString(LoginRedirectPortletKeys.KEYCLOAK_CONFIGURATION_URL);
    private static /*final*/ String userAdmin 	= PrefsPropsUtil.getString(LoginRedirectPortletKeys.KEYCLOAK_CONFIGURATION_USERNAME);
    private static /*final*/ String passAdmin 		= PrefsPropsUtil.getString(LoginRedirectPortletKeys.KEYCLOAK_CONFIGURATION_PASSWORD);
    private static /*final*/ String clientId 	= PrefsPropsUtil.getString(LoginRedirectPortletKeys.KEYCLOAK_CONFIGURATION_CLIENT_ID);
    private static /*final*/ String secret 		= PrefsPropsUtil.getString(LoginRedirectPortletKeys.KEYCLOAK_CONFIGURATION_SECRET);

    private boolean isConnected = false;
    public String msg;

    private Keycloak kc;
    
	public Realm(String url, String realmName, String userAdmin, String passAdmin, String secret, String clientId){
        this.url = url;
        this.realmName = realmName;
        this.userAdmin = userAdmin;
        this.passAdmin = passAdmin;
        this.secret = secret;
        this.clientId = clientId;
        
        try{

            System.out.println("+ conectando a KC (5).... : '" + realmName + "'");

            Thread.currentThread().setContextClassLoader(KeycloakBuilder.builder().getClass().getClassLoader());

            //Verifier verifier = new Verifier();

            kc = KeycloakBuilder.builder().serverUrl(this.url).realm(this.realmName).username(this.userAdmin)
                    .password(this.passAdmin).clientSecret(this.secret).clientId(this.clientId)
                    .resteasyClient(new ResteasyClientBuilderImpl()
                            .disableTrustManager()
                            .connectionPoolSize(this.poolSize).build())
                    .build();

            System.out.println("+ kc.serverInfo() : " + kc.serverInfo());

            isConnected = true;
            msg = "connected<br/>";
        }catch(Exception e){
            isConnected = false;
            msg = e.toString();
            e.printStackTrace();
            //System.err.println(e.toString());
        }

    }
	public Realm(){

        try{

            System.out.println("+ conectando a KC (5).... : '" + realmName + "'");

            Thread.currentThread().setContextClassLoader(KeycloakBuilder.builder().getClass().getClassLoader());

            //Verifier verifier = new Verifier();

            kc = KeycloakBuilder.builder().serverUrl(this.url).realm(this.realmName).username(this.userAdmin)
                    .password(this.passAdmin).clientSecret(this.secret).clientId(this.clientId)
                    .resteasyClient(new ResteasyClientBuilderImpl()
                            .disableTrustManager()
                            .connectionPoolSize(this.poolSize).build())
                    .build();

            System.out.println("+ kc.serverInfo() : " + kc.serverInfo());

            isConnected = true;
            msg = "connected<br/>";
        }catch(Exception e){
            isConnected = false;
            msg = e.toString();
            e.printStackTrace();
        }

    }
	
	public UserRepresentation getUserFromKC(String email){
        UserRepresentation userIs = null;

        List<String> lista = null;
        List<RoleRepresentation> roles = null;
        List<GroupRepresentation> groups = null;

        List<UserRepresentation> u = kc.realm (this.realmName).users().search(null, null, null, email, null, null);
        if(u.size() > 0 ){
            userIs = u.get(0);
            roles = kc.realm (this.realmName).users().get(userIs.getId()).roles().realmLevel().listAll();
            if(roles.size() > 0) lista = new ArrayList<String>();
            for(RoleRepresentation r : roles) lista.add(r.getName());
            userIs.setRealmRoles(lista);

            groups = kc.realm (this.realmName).users().get(userIs.getId()).groups();
            if(groups.size() > 0) lista = new ArrayList<String>();
            for(GroupRepresentation g : groups) lista.add(g.getName());
            userIs.setGroups(lista);
        }


        return userIs;
    }
	
	public boolean isExistUser(UserRepresentation userPreving){
        //return false;
        List<UserRepresentation> u = kc.realm (this.realmName).users().search(null, null, null, userPreving.getEmail(), null, null);
        if(u.size() == 0) return false;
        return true;
    }
	
	public void setUserPass(String email, String pass){

        UserRepresentation user = this.getUserFromKC(email);
        if(user == null) return;
        CredentialRepresentation passwordCred = new CredentialRepresentation();
        passwordCred.setTemporary(false);
        passwordCred.setType(CredentialRepresentation.PASSWORD);
        passwordCred.setValue(pass);
        user.setCredentials(asList(passwordCred));
        UserResource userResource = kc.realm(this.realmName).users().get(user.getId());
        userResource.update(user);

    }

	public GroupRepresentation getGroup(String name){

        return  kc.realm(this.realmName).getGroupByPath("/" + name);
        
    }
	
	
    public void close(){
        kc.close();
    }
}

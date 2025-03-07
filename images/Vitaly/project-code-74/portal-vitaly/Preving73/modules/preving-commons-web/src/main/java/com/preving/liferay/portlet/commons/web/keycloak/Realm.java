package com.preving.liferay.portlet.commons.web.keycloak;


import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.jboss.resteasy.client.jaxrs.internal.ResteasyClientBuilderImpl;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.*;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.GroupRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;

import javax.ws.rs.core.Response;
import java.io.Serializable;
import java.util.*;

import static java.util.Arrays.asList;



public class Realm implements Serializable{
    private final int poolSize = 10;

    public String url;
    private String realmName;
    private String userAdmin;
    private String passAdmin;
    private String secret;
    private String clientId;

    private boolean isConnected = false;
    public String msg;

    private Keycloak kc;

    private static Log _log = LogFactoryUtil.getLog(Realm.class);


    public Realm(String url, String realmName, String userAdmin, String passAdmin, String secret, String clientId){
        this.url = url;
        this.realmName = realmName;
        this.userAdmin = userAdmin;
        this.passAdmin = passAdmin;
        this.secret = secret;
        this.clientId = clientId;



        try{

            _log.debug("+ conectando a KC (6).... : '" + realmName + "'");

            Thread.currentThread().setContextClassLoader(KeycloakBuilder.builder().getClass().getClassLoader());

            //Verifier verifier = new Verifier();

            kc = KeycloakBuilder.builder().serverUrl(this.url).realm(this.realmName).username(this.userAdmin)
                    .password(this.passAdmin).clientSecret(this.secret).clientId(this.clientId)
                    .resteasyClient(new ResteasyClientBuilderImpl()
                            .disableTrustManager()
                            .connectionPoolSize(this.poolSize).build())
                    .build();

            _log.debug("+ kc.serverInfo() : " + kc.serverInfo());

            isConnected = true;
            msg = "connected<br/>";
        }catch(Exception e){
            isConnected = false;
            msg = e.toString();
            e.printStackTrace();
            _log.error(e);
        }

    }

    public boolean isConnected(){
        return this.isConnected;
    }

    public void createUser(UserRepresentation userPreving){
        //try {
        Response result = null;
        UserResource userResource = null;
        String userId = null;
        boolean isInGroup = false;

        if (this.isExistUser(userPreving)) {
            this.msg += "No se ha creado el usuario, ya existe." + "<br/>";
            return;
        }
        userPreving = customAttributes(userPreving);//Cambiar separador de multiple value de ## a ; -> lo pasamos a una unica string con ; como separador de valores
        _log.debug("this.realmName: " + this.realmName);
        _log.debug("kc.isClosed(): " + kc.isClosed());

        result = kc.realm(this.realmName).users().create(userPreving);
        _log.debug(result.getStatus() + "/ " + result.getStatusInfo());
        if (result.getStatus() != 201) {
            this.msg += "No se ha creado el usuario." + "<br/>";
            return;
        } else {
            this.msg += "Usuario creado." + "<br/>";
        }
        UsersResource usersRessource = kc.realm(this.realmName).users();
        userId = CreatedResponseUtil.getCreatedId(result);
        userResource = usersRessource.get(userId);
        userPreving.setId(userId);


        GroupRepresentation grpRepresentation = null;
        for (int i = 0; i < kc.realm(this.realmName).groups().groups().size(); i++) { //actualizar grupos/empresas
            isInGroup = false;
            grpRepresentation = (GroupRepresentation) kc.realm(this.realmName).groups().groups().get(i);

            for (int x = 0; userPreving.getGroups() != null && x < userPreving.getGroups().size(); x++) {
                if (grpRepresentation.getName().equalsIgnoreCase(userPreving.getGroups().get(x))) {
                    isInGroup = true;
                    break;
                }
            }
            if (isInGroup) {
                kc.realm(this.realmName).users().get(userId).joinGroup(grpRepresentation.getId());
                this.msg += "aï¿½adiendo del grupo: '" + grpRepresentation.getName() + "' <br/>";
            } else {
                kc.realm(this.realmName).users().get(userId).leaveGroup(grpRepresentation.getId());
                this.msg += "eliminando del grupo: '" + grpRepresentation.getName() + "' <br/>";
            }
        }

        this.setRolesUser(userPreving, userResource);
        /*}catch(Exception e){
            _log.error(e);
        }*/
    }



    /*
     * @see Actualizar el usuario
     */
    public UserRepresentation customAttributes(UserRepresentation userPreving){
        //Cambiar separador de multiple value de ## a ; -> lo pasamos a una unica string con ; como separador de valores
        Map<String,List<String>> mapAttr = userPreving.getAttributes();
        Iterator entries = mapAttr.entrySet().iterator();
        String newStr = "";
        Map.Entry entry = null;
        String key = null;
        List<String> value = null;
        while (entries.hasNext()) {
            entry = (Map.Entry) entries.next();
            key = (String)entry.getKey();
            value = (List<String>)entry.getValue();
            _log.debug("Key = " + key + ", Value = " + value);
            if(value.size() > 1){
                newStr = "";
                for(String str : value){
                    if(!newStr.isEmpty()) newStr += ";";
                    newStr += str;
                }
                mapAttr.put(key, asList(newStr));
            }
        }//end while
        userPreving.setAttributes(mapAttr);
        return userPreving;
    }
    public boolean updateUser(UserRepresentation userPreving){
        boolean isOk = false;
        Response result = null;
        UserRepresentation userIs = null; //this.isExistUser(userPreving);
        if(!this.isExistUser(userPreving)){
            this.msg += "Update -> usuario no existe, creandolo";
            this.createUser(userPreving);
            return true;
        }

        userPreving = customAttributes(userPreving);//Cambiar separador de multiple value de ## a ; -> lo pasamos a una unica string con ; como separador de valores

        GroupRepresentation grpRepresentation = null;
        UserResource userResource = null;
        String userId = null;
        boolean isInGroup = false;

        try{
            userIs = this.getUserFromKC(userPreving.getEmail());
            userId = userIs.getId();
            userResource = kc.realm(this.realmName).users().get(userIs.getId());

            for(int i = 0; i < kc.realm(this.realmName).groups().groups().size() ;i++){ //actualizar grupos/empresas
                isInGroup = false;
                grpRepresentation = (GroupRepresentation)kc.realm(this.realmName).groups().groups().get(i);
                for(int x = 0; userPreving.getGroups() != null && x < userPreving.getGroups().size(); x++){
                    if(grpRepresentation.getName().equalsIgnoreCase(userPreving.getGroups().get(x))){
                        isInGroup = true;
                    }
                }
                if(isInGroup){
                    kc.realm(this.realmName).users().get(userId).joinGroup(grpRepresentation.getId());
                    this.msg += "añadiendo del grupo: '" + grpRepresentation.getName() + "' <br/>";
                }else{
                    kc.realm(this.realmName).users().get(userId).leaveGroup(grpRepresentation.getId());
                    this.msg += "eliminando del grupo: '" + grpRepresentation.getName() + "' <br/>";
                }
            }
            userResource.update(userPreving);
            userPreving.setId(userId);

            this.setRolesUser(userPreving,userResource);

            isOk = true;
        }catch(Exception e){
            msg = e.toString();
            e.printStackTrace();
        }
        return isOk;
    }

    public void deleteUser(UserRepresentation user){
        if(user.getId() == null) user = this.getUserFromKC(user.getEmail());
        if(user != null) kc.realm(this.realmName).users().delete(user.getId());

    }

    /*
     * @see Crear una empresa, es un grupo en keycloak
     */
    public void createCompany(Company company){
        String res = "";
        Response result = null;
        String grpId = null;
        try {
            /*GroupRepresentation grp = new GroupRepresentation();
            grp.setName(company.getName());			*/
            //grp.setAttributes(company.getAttributes());

            GroupRepresentation grp = isExistGroup(company.getName());
            GroupResource grpResource = null;

            if(grp == null){
                grp = grp = new GroupRepresentation();
                grp.setName(company.getName());
                _log.debug("+ kc: " + kc);
                _log.debug("+ this.realmName: " + this.realmName);
                Response response =  kc.realm(this.realmName).groups().add(grp);
                this.msg += "Crear grupo -> " + " response: " + response.getStatus() + "<br/>";
                grpId = CreatedResponseUtil.getCreatedId(response);
                this.msg += " -- > grpId: " + grpId + " <br/>";
                response.close();
            }else{
                grpId = grp.getId();
            }


            grp.setAttributes(company.getAttributes());
            grpResource = kc.realm(this.realmName).groups().group(grpId);
            grpResource.toRepresentation().setAttributes(company.getAttributes());

            grpResource.toRepresentation().setRealmRoles(company.getRoles());
            grpResource.roles().realmLevel().add(this.getRealmRole(company.getRoles()));

            grpResource.update(grp);
            grpResource = null;
        }catch(Exception e) {
            this.msg += " Grupo no creado: " + e.toString() + "<br/>";
            e.printStackTrace();
        }
    }

    public void removeCompany(String name){
        GroupRepresentation grpR = this.getGroup(name);
        if(grpR == null) return;
        GroupResource grp = kc.realm(this.realmName).groups().group(grpR.getId());
        kc.realm(this.realmName).groups().group(grp.toRepresentation().getId()).remove();
        grp = null;
    }

    public void removeCompanyByAttr(String nameAttr, String value){
        //List<GroupRepresentation> grpR = this.getGroupByAttr(name, nameAttr, value);
        //if(grpR.size() == 0) return;

        //GroupResource grp = kc.realm(this.realmName).groups().group(grpR.getId());
        //kc.realm(this.realmName).groups().group(grp.toRepresentation().getId()).remove();
        //grp = null;
    }

    public GroupRepresentation isExistGroup(String name){

        //if(true) return null;
        GroupRepresentation grp = null;

        GroupsResource gruposAll = kc.realm(this.realmName).groups();
        List<GroupRepresentation> grupos = gruposAll.groups();

        for (GroupRepresentation g : grupos) {
            if (g.getName().equalsIgnoreCase(name)) {
                grp = g;
                break;
            }
        }
        return grp;
    }

    /*
     * unicos por email (UNIQUE)
     */

    public boolean isExistUser(UserRepresentation userPreving){
        //return false;
        List<UserRepresentation> u = kc.realm (this.realmName).users().search(null, null, null, userPreving.getEmail(), null, null);
        if(u.size() == 0) return false;
        return true;
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

    public GroupRepresentation getGroup(String name){

        return  kc.realm(this.realmName).getGroupByPath("/" + name);
        /*GroupRepresentation grp = null;
        GroupRepresentation grpArr = null;
        for(int i = 0; i < kc.realm(this.realmName).groups().groups().size() ;i++){
            grpArr = (GroupRepresentation)kc.realm(this.realmName).groups().groups().get(i);
            if(name.equalsIgnoreCase(grpArr.getName())){
                grp = grpArr;
                break;
            }
        }
        return grp;*/
    }

    public List<GroupRepresentation> getGroupsByAttr(String nameAttr, String value){

        List<GroupRepresentation> list = new ArrayList<GroupRepresentation>();
        GroupRepresentation grp = null;
        GroupRepresentation grpArr = null;
        String name = null;
        Iterator it = null;
        List<String> values = null;
        for(int i = 0; i < kc.realm(this.realmName).groups().groups().size() ;i++){
            grpArr = (GroupRepresentation)kc.realm(this.realmName).groups().groups().get(i);
            grp = kc.realm(this.realmName).getGroupByPath("/" + grpArr.getName());
            if(grp != null && grp.getAttributes().containsKey(nameAttr)){
                values =  grp.getAttributes().get(nameAttr);
                for(int c = 0; c < values.size(); c++){
                    if(values.get(c).toString().equalsIgnoreCase(value)){
                        list.add(grp);
                    }
                }
            }
        }
        return list;
    }




    public void setUserEnabledByGroup(List<String> groups, boolean isEnabled){
        List<UserRepresentation> usersG = this.getUserByGroups(groups);
        for(UserRepresentation userRep : usersG){
            this.setUserEnabled(userRep, isEnabled);
        }
    }

    public void setUserEnabled(UserRepresentation user, boolean isEnabled){
        user.setEnabled(isEnabled);
        UsersResource usersRessource = kc.realm (this.realmName).users();
        UserResource userResource = null;
        if(user.getId() == null){
            UserRepresentation usertmp = this.getUserFromKC(user.getEmail());
            userResource = usersRessource.get(usertmp.getId());
        }else{
            userResource = usersRessource.get(user.getId());
        }
        userResource.update(user);

    }
    /*
     * Roles
     */
    public void setRolesUser(UserRepresentation userPreving, UserResource userResource){
        try{
            RoleRepresentation testerRealmRole = null;
            RoleResource roleResource = null;

            //borrar en keycloak los que tiene que son de NUESTROS APLICATIVOS
            List<RoleRepresentation> roles = kc.realms().realm(this.realmName).roles().list(false);
            for(RoleRepresentation rol : roles){
                try{
                    rol.setComposite(true);

                    if(rol.getAttributes() != null
                            && rol.getAttributes().get("preving-app") != null
                            && rol.getAttributes().get("preving-app").toString().equals("[true]")){

                        userResource.roles().realmLevel().remove(Arrays.asList(rol));

                    }
                }catch(Exception e){
                    _log.error(e);
                }
            }

            for (int i = 0; userPreving.getRealmRoles() != null && i < userPreving.getRealmRoles().size(); i++) {
                this.createRole(userPreving.getRealmRoles().get(i).toString() ); //si el rol no existe crearlo
                roleResource = kc.realm(this.realmName).roles().get(userPreving.getRealmRoles().get(i).toString());
                try{
                    if(roleResource != null && roleResource.toRepresentation() != null){
                        testerRealmRole = roleResource.toRepresentation();
                        userResource.roles().realmLevel().remove(Arrays.asList(testerRealmRole));
                    }
                }catch(Exception e){ msg = e.toString(); e.printStackTrace(); }

                try{
                    testerRealmRole = kc.realm (this.realmName).roles().get(userPreving.getRealmRoles().get(i).toString()).toRepresentation();
                    if(testerRealmRole != null) userResource.roles().realmLevel().add(Arrays.asList(testerRealmRole));
                }catch(Exception e){ msg = e.toString(); e.printStackTrace(); }
            }

        }catch(Exception e){
            msg = e.toString();
            e.printStackTrace();
        }
    }

    private  void createRole(String roleName) {
        try{
            RolesResource rolesResource =  kc.realm(this.realmName).roles();
            RoleResource roleResource = null;
            RoleRepresentation newRole = null;

            List<RoleRepresentation> roleList = kc.realm(this.realmName).roles().list(true);
            if (roleList == null || roleList.isEmpty()) {
                newRole = new RoleRepresentation(roleName, roleName, true);
                newRole.singleAttribute("preving-app", "true");
                rolesResource.create(newRole);
                roleResource = kc.realm (this.realmName).roles().get(roleName);
                roleResource.update(newRole);
            }else{
                boolean exists = false;
                for(RoleRepresentation r : roleList){
                    if(r.getName().equals(roleName)){
                        exists = true;
                        break;
                    }
                }
                if (!exists) {
                    newRole = new RoleRepresentation();
                    newRole.singleAttribute("preving-app", "true");
                    newRole.setClientRole(true);
                    newRole.setName(roleName);
                    rolesResource.create(newRole);

                    roleResource = kc.realm (this.realmName).roles().get(roleName);
                    roleResource.update(newRole);
                }
            }

        }catch(Exception e){
            msg = e.toString();
            _log.error(e);
        }
    }

    public void removeRoles(List<String> roles){
        for(String rol : roles){
            kc.realm(this.realmName).roles().deleteRole(rol);
            this.msg += " eliminado rol  '" + rol + "'<br/>";
        }
    }

    public List<RoleRepresentation> getRealmRole(List<String> names){
        List<RoleRepresentation> roles =  kc.realm(this.realmName).roles().list();
        List<RoleRepresentation> rolesFound = new ArrayList<RoleRepresentation>();
        for(RoleRepresentation rol : roles){

            for(String name : names){
                if(name.equalsIgnoreCase(rol.getName())){
                    rolesFound.add(rol);
                    break;
                }
            }
        }

        return rolesFound;
    }

    public Set<UserRepresentation>  getUsersByRoles(List<String> roles){
        Set<UserRepresentation> users = null;
        RoleResource roleResource = null;
        for(int i = 0; i < roles.size(); i++){
            roleResource = kc.realm(this.realmName).roles().get(roles.get(i));
            if(users == null){
                users = roleResource.getRoleUserMembers();
            }else{
                users.addAll(roleResource.getRoleUserMembers());
            }
        }

        return users;
    }

    /*
     * @see Grupos
     */

    public List<UserRepresentation> getUserByGroups(List<String> groups){
        GroupsResource gruposAll = kc.realm(this.realmName).groups();
        List<GroupRepresentation> grupos = gruposAll.groups();

        List<UserRepresentation> listaUsuriosGrupo = null;
        for(int i = 0; i< groups.size();i++){
            for (GroupRepresentation g : grupos) {
                if (g.getName().equalsIgnoreCase(groups.get(i))) {
                    if(listaUsuriosGrupo == null){
                        listaUsuriosGrupo = kc.realm(this.realmName).groups().group(g.getId()).members();
                    }else{
                        listaUsuriosGrupo.addAll( kc.realm(this.realmName).groups().group(g.getId()).members());
                    }
                }
            }
        }
        gruposAll = null;
        grupos = null;
        return listaUsuriosGrupo;
    }
    /*
     * @see Que tengan a la vez el grupo/s + el rol/es
     */
    public List<UserRepresentation> getUserByGroupRoles(List<String> groups, List<String> roles){
        GroupsResource gruposAll = kc.realm(this.realmName).groups();
        List<UserRepresentation> listaUsuriosResultado = new ArrayList<UserRepresentation>();
        List<GroupRepresentation> grupos = gruposAll.groups();
        Set<UserRepresentation> usersRoles = this.getUsersByRoles(roles);

        List<UserRepresentation> listaUsuriosGrupo = null;
        UserRepresentation userRes = null;
        boolean isIn = false;

        for(int i = 0; i< groups.size();i++){
            listaUsuriosGrupo = null;
            kc.realm(this.realmName).roles().list(true);
            for (GroupRepresentation g : grupos) {
                if (g.getName().equalsIgnoreCase(groups.get(i))) {
                    listaUsuriosGrupo = kc.realm(this.realmName).groups().group(g.getId()).members();
                    //verificamos que tiene el usuario el rol
                    isIn = false;
                    userRes = null;
                    if(listaUsuriosGrupo != null && usersRoles != null){
                        for(UserRepresentation userG : listaUsuriosGrupo){
                            for(UserRepresentation userR : usersRoles){
                                if(userG.getEmail().equalsIgnoreCase(userR.getEmail())){
                                    isIn = true;
                                    userRes = userR;
                                    break;
                                }
                            }
                        }
                        if(isIn && !listaUsuriosResultado.contains(userRes)) listaUsuriosResultado.add(userRes);
                    }
                }
            }
        }
        listaUsuriosGrupo.clear();
        usersRoles.clear();
        return listaUsuriosResultado;
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

    public void close(){
        kc.close();
    }

}




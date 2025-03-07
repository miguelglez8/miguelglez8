package com.preving.liferay.portlet.commons.web.keycloak;


import java.util.*;

/**
 *
 * @author Ra�l Rod. Naranjo
 */

public class Company {

    private String name;
    private Map<String,List<String>> attr = new HashMap<String,List<String>>();
    private ArrayList roles = new ArrayList();


    public Company(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setRoles(ArrayList roles){
        this.roles = roles;
    }

    public void addRole(String name){
        roles.add(name);
    }

    public ArrayList getRoles(){
        return roles;
    }

    public boolean hasRole(String name){
        boolean is = false;
        for(int i = 0; i < roles.size(); i++){
            if(roles.get(i).toString().equalsIgnoreCase(name)){
                is = true;
                break;
            }
        }
        return is;
    }

    public void setAttribute(String name, List<String> values){
        this.attr.put(name, values);

    }

    public void setAttributes(Map<String, List<String>> attr){
        this.attr = attr;
    }

    public String getAttribute(String name){
        String res = null;
        Iterator entries = attr.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry entry = (Map.Entry) entries.next();
            if(((String)entry.getKey()).equalsIgnoreCase(name) ){
                res = (String)entry.getValue();
                break;
            }
        }

        return res;
    }

    public Map<String, List<String>> getAttributes(){
        return this.attr;
    }

    public boolean hasAttribute(String name){
        boolean is = false;

        Iterator entries = attr.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry entry = (Map.Entry) entries.next();
            if(((String)entry.getKey()).equalsIgnoreCase(name) ){
                is = true;
                break;
            }
        }
        return is;
    }


}


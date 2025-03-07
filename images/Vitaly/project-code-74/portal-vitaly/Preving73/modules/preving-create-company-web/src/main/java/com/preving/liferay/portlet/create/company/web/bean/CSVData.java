package com.preving.liferay.portlet.create.company.web.bean;

public class CSVData {

    private String name;
    private String cif;
    private String adminName;
    private String adminLastName;
    private String adminNIF;
    private String adminEmail;
    private String locale;

    public CSVData(String name, String cif, String adminName, String adminLastName, String adminNIF, String adminEmail, String locale) {
        this.name = name;
        this.cif = cif;
        this.adminName = adminName;
        this.adminLastName = adminLastName;
        this.adminNIF = adminNIF;
        this.adminEmail = adminEmail;
        this.locale = locale;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminLastName() {
        return adminLastName;
    }

    public void setAdminLastName(String adminLastName) {
        this.adminLastName = adminLastName;
    }

    public String getAdminNIF() {
        return adminNIF;
    }

    public void setAdminNIF(String adminNIF) {
        this.adminNIF = adminNIF;
    }

    public String getAdminEmail() {
        return adminEmail;
    }

    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }
}

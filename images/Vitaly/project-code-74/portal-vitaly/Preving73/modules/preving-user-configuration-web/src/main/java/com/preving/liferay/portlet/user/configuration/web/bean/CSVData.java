package com.preving.liferay.portlet.user.configuration.web.bean;

public class CSVData {

    private String nif;
    private String name;
    private String lastName;
    private String email;
    private String jobTitle;
    private String workcenter;
    private String salary;
    private String locale;

    public CSVData(String nif, String name, String lastName, String email, String jobTitle, String workcenter, String locale) {
        this.nif = nif;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.jobTitle = jobTitle;
        this.workcenter = workcenter;
        this.locale = locale;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String cif) {
        this.nif = cif;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getWorkcenter() {
        return workcenter;
    }

    public void setWorkcenter(String workcenter) {
        this.workcenter = workcenter;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }
}

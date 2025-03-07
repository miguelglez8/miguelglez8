package com.adeplus.liferay.portlet.commons.web.bean;

import com.adeplus.liferay.portlet.suite.manager.model.Application;

public class AppPermission {

    private Application application;
    private boolean hasPermission;
    private long licenseId;

    public AppPermission(Application application, boolean hasPermission, long licenseId) {
        this.application = application;
        this.hasPermission = hasPermission;
        this.licenseId = licenseId;
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public boolean isHasPermission() {
        return hasPermission;
    }

    public void setHasPermission(boolean hasPermission) {
        this.hasPermission = hasPermission;
    }

    public long getLicenseId() {
        return licenseId;
    }

    public void setLicenseId(long licenseId) {
        this.licenseId = licenseId;
    }
}

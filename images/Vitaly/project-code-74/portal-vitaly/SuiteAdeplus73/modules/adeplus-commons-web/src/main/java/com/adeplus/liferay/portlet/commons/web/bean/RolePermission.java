package com.adeplus.liferay.portlet.commons.web.bean;

import com.adeplus.liferay.portlet.suite.manager.model.Role;

public class RolePermission {

    private Role role;
    private boolean hasPermission;

    public RolePermission(Role role, boolean hasPermission) {
        this.role = role;
        this.hasPermission = hasPermission;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isHasPermission() {
        return hasPermission;
    }

    public void setHasPermission(boolean hasPermission) {
        this.hasPermission = hasPermission;
    }
}

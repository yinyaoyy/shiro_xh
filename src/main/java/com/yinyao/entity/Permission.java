package com.yinyao.entity;

import java.util.Set;

public class Permission {
    private String permission;
    private String permission_id;
    private Set<Role> roleset;

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission_id() {
        return permission_id;
    }

    public void setPermission_id(String permission_id) {
        this.permission_id = permission_id;
    }

    public Set<Role> getRoleset() {
        return roleset;
    }

    public void setRoleset(Set<Role> roleset) {
        this.roleset = roleset;
    }
}

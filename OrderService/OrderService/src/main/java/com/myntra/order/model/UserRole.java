package com.myntra.order.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserRole {

    private String roleID;
    private String roleName;
    private String roleDescription;

    public UserRole(){
    }

    public UserRole(String roleID, String roleName, String roleDescription) {
        this.roleID = roleID;
        this.roleName = roleName;
        this.roleDescription = roleDescription;
    }

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDescription() {
        return roleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }
}

package com.rmt.program.RuralMassionTraining.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="RMT_ROLE_USER")
public class UserRole {

    @Id
    @Column(name="ROLE_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int roleID;
    @Column(name="ROLE_NAME")
    private String roleName;
    @Column(name="ROLE_DESC")
    private String roleDescription;

    public UserRole(){
    }

    public UserRole(int roleID, String roleName, String roleDescription) {
        this.roleID = roleID;
        this.roleName = roleName;
        this.roleDescription = roleDescription;
    }

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
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

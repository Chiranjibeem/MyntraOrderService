package com.myntra.order.model;

import javax.persistence.*;

@Entity(name = "M_CUSTOMER")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CUST_ID")
    private String custId;
    @Column(name = "CUST_NAME")
    private String custName;
    @Column(name = "ADDRESS")
    private String address;
    @Column(name = "CUST_PHONE")
    private String phoneNumber;
    @Column(name = "ROLE_ID",insertable = false,updatable = false)
    private String roleID;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "ROLE_ID")
    private UserRole role;
    @Column(name="CUST_PWD")
    private String password;

    public Customer() {

    }

    public Customer(String custId, String custName, String address, String phoneNumber, String roleID, UserRole role,String password) {
        this.custId = custId;
        this.custName = custName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.roleID = roleID;
        this.role = role;
        this.password = password;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

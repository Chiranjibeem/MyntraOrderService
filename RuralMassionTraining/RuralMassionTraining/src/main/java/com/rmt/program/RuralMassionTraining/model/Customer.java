package com.rmt.program.RuralMassionTraining.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity(name = "RMT_CUSTOMER")
@EntityListeners(AuditingEntityListener.class)
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CUST_ID")
    private int custId;
    @Column(name = "CUST_NAME")
    private String custName;
    @Column(name = "CUST_EMAIL")
    private String email;
    @Column(name = "CUST_PWD")
    private String password;
    @Column(name = "CUST_PHONE")
    private String phoneNumber;
    @Column(name = "ROLE_ID", insertable = false, updatable = false)
    private String roleID;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "ROLE_ID")
    private UserRole role;
    @Column(name="CREATED_DATE")
    @CreatedDate
    private Timestamp createDate;
    @Column(name="LAST_MODIFY_DATE")
    @LastModifiedDate
    private Timestamp lastModifyDate;

    public Customer() {
    }

    public Customer(Customer customer) {
        this.custId = customer.getCustId();
        this.custName = customer.getCustName();
        this.email = customer.getEmail();
        this.phoneNumber = customer.getPhoneNumber();
        this.role = customer.getRole();
        this.password = customer.getPassword();
    }

    public Customer(int custId, String custName, String email, String password, String phoneNumber, String roleID, UserRole role) {
        this.custId = custId;
        this.custName = custName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.roleID = roleID;
        this.role = role;
    }

    public int getCustId() {
        return custId;
    }

    public void setCustId(int custId) {
        this.custId = custId;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}



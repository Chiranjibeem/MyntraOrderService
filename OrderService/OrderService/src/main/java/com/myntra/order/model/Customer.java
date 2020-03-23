package com.myntra.order.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity(name = "M_ORD_TRAN_CUST")
public class Customer {

    @Column(name = "CUST_ID")
    private String custId;
    @Column(name = "CUST_NAME")
    private String custName;
    @Column(name = "ADDRESS")
    private String address;
    @Column(name = "CUST_PHONE")
    private String phoneNumber;
    @Id
    @Column(name = "ORDER_ID")
    private String orderId;
    @Transient
    private UserRole role;
    @Transient
    private String password;
    @OneToOne(fetch = FetchType.LAZY,mappedBy = "customer")
    @JoinColumn(name = "order_id")
    @JsonBackReference
    private Order order;

    public Customer() {
    }

    public Customer(Customer customer){
        this.custId = customer.getCustId();
        this.custName  = customer.getCustName();
        this.address = customer.getAddress();
        this.phoneNumber = customer.getPhoneNumber();
        this.role = customer.getRole();
        this.password = customer.getPassword();
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

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}

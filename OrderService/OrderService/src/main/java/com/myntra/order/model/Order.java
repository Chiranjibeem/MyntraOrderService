package com.myntra.order.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "M_ORD_TRAN_HEADER")
@EntityListeners(AuditingEntityListener.class)
public class Order {

    @Id
    //@GeneratedValue(generator="system-uuid")
    //@GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name = "ORDER_ID")
    private String orderId;
    @Column(name = "ORDER_AMT")
    private BigDecimal amount;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id")
    private Customer customer;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id")
    private List<Item> item;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id")
    private Payment payment;

    @Column(name = "created_date")
    @CreatedDate
    private Timestamp createdDate;

    @Column(name = "lastModify_date")
    @LastModifiedDate
    private Timestamp lastModifyDate;

    public Order() {

    }

    public Order(Order order){
        this.setOrderId(order.getOrderId());
        this.setPayment(order.getPayment());
        this.setItem(order.getItem());
        this.setCustomer(order.getCustomer());
        this.setAmount(order.getAmount());
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Item> getItem() {
        if (this.item == null) {
            return new ArrayList<Item>();
        }
        return item;
    }

    public void setItem(List<Item> item) {
        this.item = item;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

}

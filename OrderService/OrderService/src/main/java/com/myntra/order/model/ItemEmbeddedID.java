package com.myntra.order.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnoreType;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import java.io.Serializable;

@Embeddable
@JsonIgnoreType
public class ItemEmbeddedID implements Serializable {

    @Column(name = "ITEM_ID")
    private String itm;

    @JsonIgnore
    @Column(name="ORDER_ID")
    private String orderId;

    public ItemEmbeddedID(){}

    public String getItemId() {
        return itm;
    }

    public void setItemId(String itm) {
        this.itm = itm;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}

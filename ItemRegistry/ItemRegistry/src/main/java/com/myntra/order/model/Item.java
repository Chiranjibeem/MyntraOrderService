package com.myntra.order.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity(name="M_ITEM")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ITEM_ID")
    private String itemId;
    @Column(name = "ITEM_NAME")
    private String itemName;
    @Column(name = "ITEM_PRICE")
    private BigDecimal itemPrice;
    @Column(name = "ITEM_AVAL_QTY")
    private BigDecimal itemAvalQty;

    public Item() {

    }

    public Item(String itemId, String itemName, BigDecimal itemPrice, BigDecimal itemAvalQty) {
        super();
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemAvalQty = itemAvalQty;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public BigDecimal getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(BigDecimal itemPrice) {
        this.itemPrice = itemPrice;
    }

    public BigDecimal getItemAvalQty() {
        return itemAvalQty;
    }

    public void setItemAvalQty(BigDecimal itemAvalQty) {
        this.itemAvalQty = itemAvalQty;
    }
}

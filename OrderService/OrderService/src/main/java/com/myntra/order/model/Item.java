package com.myntra.order.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity(name="M_ORD_TRAN_ITM")
@JsonIgnoreProperties(value="itm")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Item {


	@Column(name = "ITEM_NAME")
	private String itemName;
	@Column(name = "ITEM_PRICE")
	private BigDecimal itemPrice;
	@Column(name = "ITEM_AVAL_QTY")
	private BigDecimal itemAvalQty;
	@EmbeddedId
	@JsonIgnore
	private ItemEmbeddedID itm;
	@Transient
	private String itemId;

	public Item() {
	}

	public Item(String itemName, BigDecimal itemPrice, BigDecimal itemAvalQty, String itemId) {
		this.itemName = itemName;
		this.itemPrice = itemPrice;
		this.itemAvalQty = itemAvalQty;
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

	public ItemEmbeddedID getItm() {
		return itm;
	}

	public void setItm(ItemEmbeddedID itm) {
		this.itm = itm;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
}





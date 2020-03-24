package com.myntra.order.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.math.BigDecimal;

@Entity(name = "M_ORD_TRAN_PAY")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Payment {

	@Column(name = "PAYMENT_TYPE_ID")
	private String paymentId;
	@Column(name = "PAYMENT_TYPE")
	private String paymentType;
	@Column(name="PAYMENT_AMOUNT")
	private BigDecimal paymentAmount;
	@Column(name="ORDER_ID")
	@Id
	private String orderId;



	public Payment(){

	}

	public Payment(String paymentId, String paymentType, BigDecimal paymentAmount,String orderId) {
		super();
		this.paymentId = paymentId;
		this.paymentType = paymentType;
		this.paymentAmount = paymentAmount;
		this.orderId = orderId;
	}


	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public BigDecimal getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(BigDecimal paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
}

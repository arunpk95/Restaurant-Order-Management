package com.food.order.model;

import org.springframework.stereotype.Component;

@Component
public class Payment {

	private String payMethod;
	
	private int payAmount;
	
	private int orderId;
	

	public String getPayMethod() {
		return payMethod;
	}

	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}

	public int getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(int payAmount) {
		this.payAmount = payAmount;
	}

	public int getOrderId(){
		return orderId;
	}
	
	public void setOrderId(int orderId){
		this.orderId = orderId;
	}
}

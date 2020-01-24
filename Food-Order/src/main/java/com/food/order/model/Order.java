package com.food.order.model;

import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class Order {
	private int orderId;
	private String orderStatus;
	private Date orderCreatedDate;
	private Date orderCompletedDate;
	private int customerId;
	private int labourId;
	private List<OrderItem> orderItems;
	
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public Date getOrderCreatedDate() {
		return orderCreatedDate;
	}
	public void setOrderCreatedDate(Date orderCreatedDate) {
		this.orderCreatedDate = orderCreatedDate;
	}
	public Date getOrderCompletedDate() {
		return orderCompletedDate;
	}
	public void setOrderCompletedDate(Date orderCompletedDate) {
		this.orderCompletedDate = orderCompletedDate;
	}

	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public int getLabourId() {
		return labourId;
	}
	public void setLabourId(int labourId) {
		this.labourId = labourId;
	}
	public List<OrderItem> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
}

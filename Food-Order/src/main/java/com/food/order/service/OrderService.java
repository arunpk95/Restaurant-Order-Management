package com.food.order.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.food.order.dao.OrderDao;
import com.food.order.model.Order;

@Service
public class OrderService {

	@Autowired
	private OrderDao orderDao;
	
	public Order createOrder(Order order) {
		return orderDao.createOrder(order);
	}

	public boolean updateOrder(Order order) {
		return orderDao.updateOrder(order);
	}

	public List<Order> getOrders() {
		return orderDao.getOrder();
	}

	public boolean deleteOrder(Order order) {
		return orderDao.deleteOrder(order);
	}

}

package com.food.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.food.order.model.Order;
import com.food.order.service.OrderService;

@RestController
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@RequestMapping(value="/createOrder", method = RequestMethod.POST)
	public Order createOrder(@RequestBody Order order){
		return orderService.createOrder(order);
	}

	@RequestMapping(value="/updateOrder", method = RequestMethod.POST)
	public boolean updateOrder(@RequestBody Order order){
		return orderService.updateOrder(order);
	}
	
	@RequestMapping(value="/getOrders", method = RequestMethod.GET)
	public List<Order> getOrders(){
		return orderService.getOrders();
	}
	
	@RequestMapping(value= "/deleteOrder", method = RequestMethod.POST)
	public boolean deleteOrder(@RequestBody Order order){
		return orderService.deleteOrder(order);
	}
		
}

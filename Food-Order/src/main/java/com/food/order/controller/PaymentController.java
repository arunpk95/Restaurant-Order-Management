package com.food.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.food.order.model.Payment;
import com.food.order.service.PaymentService;

@RestController
public class PaymentController {

	@Autowired
	private PaymentService paymentService;
	
	@RequestMapping(value="/createPayment", method = RequestMethod.POST)
	public boolean createPayment(@RequestBody Payment payment){
		return paymentService.createPayment(payment);
	}
}

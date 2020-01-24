package com.food.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.food.order.dao.PaymentDao;
import com.food.order.model.Payment;

@Service
public class PaymentService {

	@Autowired
	private PaymentDao paymentDao;
	
	public boolean createPayment(Payment payment) {
		return paymentDao.createPayment(payment);
	}

}

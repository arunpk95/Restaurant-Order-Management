package com.food.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.food.order.dao.CustomerDao;
import com.food.order.model.Customer;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerDao customerDao;
	
	public Customer getCustomer(int phoneNumber) {
		return customerDao.getCustomer(phoneNumber);
	}

	public boolean saveCustomer(Customer customer) {
		return customerDao.saveCustomer(customer);
	}
}

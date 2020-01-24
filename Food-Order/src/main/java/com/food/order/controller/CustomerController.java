package com.food.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.food.order.model.Customer;
import com.food.order.service.CustomerService;


@RestController
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	/*
	@RequestMapping(value= "/", method= RequestMethod.GET)
	public String test(){
		return "heyyyy !!";
	}*/
	
	@RequestMapping(value = "/getCustomer", method = RequestMethod.GET)
	public Customer getCustomer(@RequestParam("phoneNumber") int phoneNumber){
		return customerService.getCustomer(phoneNumber);
	}
	
	@RequestMapping(value = "/saveCustomer", method= RequestMethod.POST)
	public boolean saveCustomer(@RequestBody Customer customer){
		return customerService.saveCustomer(customer);
	}
}

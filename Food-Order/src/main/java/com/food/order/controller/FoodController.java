package com.food.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.food.order.model.Food;
import com.food.order.service.FoodService;

@RestController
public class FoodController {
	
	@Autowired
	private FoodService foodService;
	
	@RequestMapping(value="/getFoods")
	public List<Food> getFoods(){
		return foodService.getFoods();
	}
	
}

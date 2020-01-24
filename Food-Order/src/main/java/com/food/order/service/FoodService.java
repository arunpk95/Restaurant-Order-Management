package com.food.order.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.food.order.dao.FoodDao;
import com.food.order.model.Food;

@Service
public class FoodService {
	
	@Autowired
	private FoodDao foodDao;
	
	public List<Food> getFoods(){
		return foodDao.getFoods();
	}
	
}

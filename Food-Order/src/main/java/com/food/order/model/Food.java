package com.food.order.model;

import org.springframework.stereotype.Component;

@Component
public class Food {
	private int foodId;
	private String foodName;
	private int foodAvailability;
	private String foodCategory;
	private int foodPrice;
	private String foodDesc;
	
	public int getFoodId() {
		return foodId;
	}
	public void setFoodId(int foodId) {
		this.foodId = foodId;
	}
	public String getFoodName() {
		return foodName;
	}
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
	public int getFoodAvailability() {
		return foodAvailability;
	}
	public void setFoodAvailability(int foodAvailability) {
		this.foodAvailability = foodAvailability;
	}
	public String getFoodCategory() {
		return foodCategory;
	}
	public void setFoodCategory(String foodCategory) {
		this.foodCategory = foodCategory;
	}
	public void setFoodPrice(int foodPrice) {
		this.foodPrice = foodPrice;
	}
	public int getFoodPrice() {
		return foodPrice;
	}
	public String getFoodDesc() {
		return foodDesc;
	}
	public void setFoodDesc(String foodDesc) {
		this.foodDesc = foodDesc;
	}
}

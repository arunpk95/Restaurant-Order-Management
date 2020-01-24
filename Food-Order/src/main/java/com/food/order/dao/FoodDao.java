package com.food.order.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Component;

import com.food.order.dao.util.DatabaseConnection;
import com.food.order.model.Food;

@Component
public class FoodDao {

	public List<Food> getFoods() {
		try {
			Connection con = DatabaseConnection.getConnection();
			PreparedStatement ps = con.prepareStatement("select * from food_items");
			ResultSet resultSet = ps.executeQuery();
			List<Food> foods = null;
			while (resultSet.next()) {
				if (Objects.isNull(foods)) {
					foods = new ArrayList<>();
				}
				Food food = new Food();
				food.setFoodId(resultSet.getInt("f_id"));
				food.setFoodName(resultSet.getString("f_name"));
				food.setFoodAvailability(resultSet.getInt("f_availability"));
				food.setFoodCategory(resultSet.getString("f_category"));
				food.setFoodPrice(resultSet.getInt("f_price"));
				food.setFoodDesc(resultSet.getString("f_description"));
				foods.add(food);
			}
			return foods;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}

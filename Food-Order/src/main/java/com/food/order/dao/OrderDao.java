package com.food.order.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.food.order.dao.util.DatabaseConnection;
import com.food.order.model.Order;
import com.food.order.model.OrderItem;

@Component
public class OrderDao {

	public Order createOrder(Order order) {
		try {
			Connection connection = DatabaseConnection.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(
					"insert into orders values(default, 'ORDER NOT CONFIRMED', now(), null, ?, null)",
					Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1, order.getCustomerId());
			int isSaved = preparedStatement.executeUpdate();
			if (isSaved == 0) {
				throw new Exception("Order is not saved");
			}

			Long orderId = this.getId(preparedStatement);
			if (CollectionUtils.isEmpty(order.getOrderItems())) {
				throw new Exception("Cart is Empty");
			}
			this.createOrderItems(order.getOrderItems(), orderId);
			
			Order responseOrder = new Order();
			responseOrder.setOrderId(orderId.intValue());
			return responseOrder;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private Long getId(PreparedStatement preparedStatement) {
		try {
			Long orderId = null;
			ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
			if (generatedKeys.next()) {
				orderId = generatedKeys.getLong(1);
			} else {
				throw new Exception("Order key is not retrievable");
			}
			return orderId;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private boolean createOrderItems(List<OrderItem> orderItems, Long orderId) {
		try {
			Connection connection = DatabaseConnection.getConnection();
			for (OrderItem orderItem : orderItems) {
				PreparedStatement preparedStatement;
				preparedStatement = connection.prepareStatement("insert into order_items values(?,?,?)");
				preparedStatement.setInt(1, orderItem.getFoodId());
				preparedStatement.setLong(2, orderId);
				preparedStatement.setInt(3, orderItem.getQuantity());
				preparedStatement.execute();
			}
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean updateOrder(Order order) {
		String updateStatus = order.getOrderStatus();
		try {
			Connection connection = DatabaseConnection.getConnection();
			
			switch(updateStatus){
				case "ORDER_CONFIRMED":{
					PreparedStatement preparedStatement =  connection.prepareStatement("update orders set o_status=? where o_id = ?");
					preparedStatement.setString(1, order.getOrderStatus());
					preparedStatement.setInt(2, order.getOrderId());
					return preparedStatement.executeUpdate() == 1;
				}
				case "PREPARING_ORDER":{
					PreparedStatement preparedStatement =  connection.prepareStatement("update orders set o_status=?, l_id=? where o_id = ?");
					preparedStatement.setString(1, order.getOrderStatus());
					preparedStatement.setInt(2, order.getLabourId());
					preparedStatement.setInt(3, order.getOrderId());
					return preparedStatement.executeUpdate() == 1;
				}
				case "ORDER_COMPLETED":{
					PreparedStatement preparedStatement =  connection.prepareStatement("update orders set o_status=?, o_completed_time=now() where o_id = ?");
					preparedStatement.setString(1, order.getOrderStatus());
					preparedStatement.setInt(2, order.getOrderId());
					return preparedStatement.executeUpdate() == 1;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public List<Order> getOrder() {
		try {
			List<Order> orders = null;
			Connection con = DatabaseConnection.getConnection();
			PreparedStatement orderPS = con.prepareStatement("select * from orders where o_status in ('ORDER_CONFIRMED','PREPARING_ORDER','ORDER_COMPLETED')");
			ResultSet orderRS = orderPS.executeQuery();
			while(orderRS.next()){
				PreparedStatement orderItemsPS = con.prepareStatement("select * from order_items where o_Id = ? ");
				orderItemsPS.setInt(1, orderRS.getInt("o_id"));
				ResultSet orderItemsRS = orderItemsPS.executeQuery();
				List <OrderItem> orderItems = null;
				while(orderItemsRS.next()){
					if(Objects.isNull(orderItems)){
						orderItems = new ArrayList<>();
					}
					OrderItem orderItem = new OrderItem();
					orderItem.setFoodId(orderItemsRS.getInt("f_id"));
					orderItem.setFoodId(orderItemsRS.getInt("o_id"));
					orderItem.setFoodId(orderItemsRS.getInt("quantity"));
					orderItems.add(orderItem);
				}
				
				if(Objects.isNull(orders)){
					orders = new ArrayList<>();
				}
				
				Order order = new Order();
				order.setOrderId(orderRS.getInt("o_id"));
				order.setOrderStatus(orderRS.getString("o_status"));
				order.setOrderCreatedDate(orderRS.getDate("o_create_date"));
				order.setOrderCompletedDate(orderRS.getDate("o_completed_time"));
				order.setCustomerId(orderRS.getInt("c_id"));
				order.setLabourId(orderRS.getInt("l_id"));
				order.setOrderItems(orderItems);
				orders.add(order);
			}
			return orders;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		return null;
	}

	public boolean deleteOrder(Order order) {
		try {
			Connection connection = DatabaseConnection.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement("delete from orders where o_id = ?");
			preparedStatement.setInt(1, order.getOrderId());
			preparedStatement.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}

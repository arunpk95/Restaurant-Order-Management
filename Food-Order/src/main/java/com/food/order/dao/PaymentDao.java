package com.food.order.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.food.order.dao.util.DatabaseConnection;
import com.food.order.model.Order;
import com.food.order.model.Payment;

@Component
public class PaymentDao {

	@Autowired
	private OrderDao orderDao;

	public boolean createPayment(Payment payment) {
		try {
			Connection connection = DatabaseConnection.getConnection();
			PreparedStatement preparedStatement = connection
					.prepareStatement("insert into payments values(default,?,?,?,?)");
			int randomNumber = (new Random()).nextInt(90000000) + 10000000;
			preparedStatement.setInt(1, randomNumber);
			preparedStatement.setString(2, payment.getPayMethod());
			preparedStatement.setInt(3, payment.getPayAmount());
			preparedStatement.setInt(4, payment.getOrderId());
			preparedStatement.execute();

			Order order = new Order();
			order.setOrderStatus("ORDER_CONFIRMED");
			order.setOrderId(payment.getOrderId());
			orderDao.updateOrder(order);

			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}

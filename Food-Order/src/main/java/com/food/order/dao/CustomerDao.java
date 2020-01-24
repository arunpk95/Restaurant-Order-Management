package com.food.order.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

import com.food.order.dao.util.DatabaseConnection;
import com.food.order.model.Customer;



@Component
public class CustomerDao {

	public Customer getCustomer(int phoneNumber) {
		try {
			Connection con = DatabaseConnection.getConnection();
			PreparedStatement ps = con.prepareStatement("select * from customers where c_phone_number = ?");
			ps.setInt(1, phoneNumber);
			ResultSet resultSet =  ps.executeQuery();
			if(resultSet.next()){
				Customer customer = new Customer();
				customer.setCustomerId(resultSet.getInt("c_id"));
				customer.setCustomerName(resultSet.getString("c_Name"));
				customer.setCustomerPhoneNumber(resultSet.getInt("c_phone_number"));
				customer.setCustomerEmail(resultSet.getString("c_email"));
				return customer;
			}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean saveCustomer(Customer customer) {
		try {
			Connection con = DatabaseConnection.getConnection();
			PreparedStatement ps = con.prepareStatement("insert into customers values (default,?, ?, ?)");
			ps.setString(1, customer.getCustomerName());
			ps.setInt(2, customer.getCustomerPhoneNumber());
			ps.setString(3, customer.getCustomerEmail());
			return ps.executeUpdate() > 0 ;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
}

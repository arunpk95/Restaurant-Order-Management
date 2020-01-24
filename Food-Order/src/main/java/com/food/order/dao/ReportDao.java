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
import com.food.order.model.Report;

@Component
public class ReportDao {
	public List<Report> getReport(String fromDate,String toDate) {
		List <Report> reportData = null;
		try {
			Connection con = DatabaseConnection.getConnection();
			PreparedStatement reportPS = con.prepareStatement("SELECT o.o_id,c.c_name, p.p_amount,l.l_name,o.o_status, o.o_create_date"
					+" FROM Orders AS o"
					+" INNER JOIN Payments AS p ON o.o_id = p.o_id" 
					+" INNER JOIN labour AS l ON o.l_id = l.l_id"
					+" INNER JOIN customers AS c ON o.c_id = c.c_id"
					+" WHERE o.o_create_date > ? and o.o_create_date < ?"
					+" order by o.o_create_date desc;");
			reportPS.setString(1, fromDate);
			reportPS.setString(2, toDate);
			ResultSet reportRS = reportPS.executeQuery();
			while(reportRS.next()){
				if (Objects.isNull(reportData)) {
					reportData = new ArrayList<>();
				}
				Report reportValue = new Report();
				reportValue.setOrderId(reportRS.getInt("o_id"));
				reportValue.setCustomerName(reportRS.getString("c_Name"));
				reportValue.setPaymentAmount(reportRS.getInt("p_amount"));
				reportValue.setLabourName(reportRS.getString("l_name"));
				reportValue.setOrderStatus(reportRS.getString("o_status"));
				reportValue.setCreateDate(reportRS.getString("o_create_date"));
				reportData.add(reportValue);
			}
			return reportData;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}

package com.food.order.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.food.order.dao.ReportDao;
import com.food.order.model.Report;

@Service
public class ReportService {

	@Autowired
	private ReportDao reportDao;

	public List<Report> getReport(String fromDate,String toDate) {
		return reportDao.getReport(fromDate,toDate);
	}

}

package com.food.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.food.order.model.Report;
import com.food.order.service.ReportService;

@RestController
public class ReportController {

	@Autowired
	private ReportService reportService;
	
	@RequestMapping(value="/getReport", method = RequestMethod.GET)
	public List<Report> getReport(@RequestParam("fromDate") String fromDate,@RequestParam("toDate") String toDate){
		return reportService.getReport(fromDate,toDate);
	}
}

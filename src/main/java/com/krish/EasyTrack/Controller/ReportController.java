package com.krish.EasyTrack.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.krish.EasyTrack.Model.Report;
import com.krish.EasyTrack.Service.ReportService;

@RestController
@RequestMapping("/report")
public class ReportController {

	@Autowired
	private ReportService reportService;

	@GetMapping("/{id}")
	ResponseEntity<Report> generateReport(@PathVariable int id) {

		return ResponseEntity.ok(reportService.generateReport(id));
	}
}

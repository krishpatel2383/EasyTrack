package com.krish.EasyTrack.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.krish.EasyTrack.Model.Attendance;
import com.krish.EasyTrack.Service.AttendanceService;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {

	@Autowired
	private AttendanceService attendanceService;

	@GetMapping("/{employeeId}")
	ResponseEntity<List<Attendance>> getAttendances(@PathVariable int employeeId) {
		try {
			return ResponseEntity.ok(attendanceService.getAttendancesByEmployeeId(employeeId));

		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping("/check-in/{employeeId}")
	ResponseEntity<String> checkIn(@PathVariable int employeeId) {

		attendanceService.checkIn(employeeId);
		return ResponseEntity.ok("check-in recorded.");
	}

	@PostMapping("/check-out/{employeeId}")
	ResponseEntity<String> checkOut(@PathVariable int employeeId) {
		attendanceService.checkOut(employeeId);
		return ResponseEntity.ok("check-out recorded.");
	}
}

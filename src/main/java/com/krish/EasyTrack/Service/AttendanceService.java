package com.krish.EasyTrack.Service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.krish.EasyTrack.Model.Attendance;
import com.krish.EasyTrack.Model.Employee;
import com.krish.EasyTrack.Repository.AttendanceRepository;

@Service
public class AttendanceService {

	@Autowired
	private AttendanceRepository attendanceRepository;

	@Autowired
	private EmployeeService employeeService;

	public List<Attendance> getAttendancesByEmployeeId(int employeeId) {
		return attendanceRepository.findByEmployeeEmpId(employeeId);
	}

	public void checkIn(int employeeId) {

		// check if the employee exists
		Employee employee = employeeService.getEmployee(employeeId);
		if (employee == null) {
			throw new EmptyResultDataAccessException("employee not found", 1);
		}
		List<Attendance> attendancelList = getAttendancesByEmployeeId(employeeId);

		// get the last attendance
		Attendance lastAttendance = attendancelList.isEmpty() ? null : attendancelList.get(attendancelList.size() - 1);

		if (lastAttendance != null && lastAttendance.getCheckOutTime().isAfter(LocalDateTime.now())) {
			throw new IllegalArgumentException("Cannot check-in.Last check-out is after current time.");
		}
		Attendance attendance = new Attendance();
		attendance.setEmployee(employee);
		attendance.setCheckInTime(LocalDateTime.now());
		attendanceRepository.save(attendance);
	}

	public void checkOut(int employeeId) {
		// check if employee exists
		Employee employee = employeeService.getEmployee(employeeId);
		if (employee == null) {
			throw new EmptyResultDataAccessException("Employee not found", 1);
		}
		// get last attendance of that employee
		List<Attendance> attendanceList = getAttendancesByEmployeeId(employeeId);
		Attendance lastAttendance = attendanceList.isEmpty() ? null : attendanceList.get(attendanceList.size() - 1);

		// check if already done or not
		if (lastAttendance == null || lastAttendance.getCheckOutTime() != null) {
			throw new IllegalStateException("cannot check out.no active check-in found");
		}

		// get check in of last attendance
		LocalDateTime lastCheckInTime = lastAttendance.getCheckInTime();
		if (lastCheckInTime == null || lastCheckInTime.isAfter(LocalDateTime.now())) {
			throw new IllegalArgumentException("last check-in time is after current time.");
		}

		lastAttendance.setCheckOutTime(LocalDateTime.now());
		attendanceRepository.save(lastAttendance);

	}

}

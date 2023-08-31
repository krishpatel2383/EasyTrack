package com.krish.EasyTrack.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.krish.EasyTrack.Model.Attendance;
import com.krish.EasyTrack.Model.Report;

@Service
public class ReportService {

	@Autowired
	private AttendanceService attendanceService;

	public Report generateReport(int id) {
		Report report = new Report();
		report.setTotalDaysWorked(getTotalDaysworked(id));
		report.setTotalHoursWorked(getTotalHoursWorked(id));
		report.setAvgCheckInTime(getAvgCheckInTime(id));
		report.setAvgCheckOuTime(getAvgCheckOutTime(id));

		return report;
	}

	public int getTotalDaysworked(int id) {
		List<Attendance> attendanceList = attendanceService.getAttendancesByEmployeeId(id);
		int totalDaysWorked = 0;
		for (Attendance attendance : attendanceList) {
			LocalDateTime checkInTime = attendance.getCheckInTime();
			LocalDateTime checkOutTime = attendance.getCheckOutTime();

			if (checkInTime != null && checkOutTime != null) {
				totalDaysWorked += 1;
			}
		}

		return totalDaysWorked;
	}

	public String getTotalHoursWorked(int id) {
		List<Attendance> attendanceList = attendanceService.getAttendancesByEmployeeId(id);
		Duration totalHoursWorked = Duration.ZERO;
		for (Attendance attendance : attendanceList) {
			if (attendance.getCheckInTime() != null && attendance.getCheckOutTime() != null) {
				LocalTime checkInTime = attendance.getCheckInTime().toLocalTime();
				LocalTime checkOutTime = attendance.getCheckOutTime().toLocalTime();

				totalHoursWorked = totalHoursWorked.plus(Duration.between(checkInTime, checkOutTime));
			}
		}

		long days = totalHoursWorked.toDays();
		long hours = totalHoursWorked.toHoursPart();
		long minutes = totalHoursWorked.toMinutesPart();
		long seconds = totalHoursWorked.toSecondsPart();

		String TotalWorking = String.format("%02d Days %02d Hours %02d Minutes %02d Seconds", days, hours, minutes,
				seconds);

		return TotalWorking;
	}

	public LocalTime getAvgCheckInTime(int id) {
		List<Attendance> attendanceList = attendanceService.getAttendancesByEmployeeId(id);

		List<LocalTime> times = new ArrayList<>();
		for (Attendance attendance : attendanceList) {
			if (attendance.getCheckInTime() != null) {
				times.add(attendance.getCheckInTime().toLocalTime());
			}
		}

		long totalSeconds = 0;

		for (LocalTime time : times) {
			totalSeconds += time.toSecondOfDay();
		}

		long avgTime = totalSeconds / times.size();
		return LocalTime.ofSecondOfDay(avgTime);
	}

	public LocalTime getAvgCheckOutTime(int id) {
		List<Attendance> attendanceList = attendanceService.getAttendancesByEmployeeId(id);

		List<LocalTime> times = new ArrayList<>();
		for (Attendance attendance : attendanceList) {
			if (attendance.getCheckOutTime() != null) {
				times.add(attendance.getCheckOutTime().toLocalTime());
			}
		}

		long totalSeconds = 0;

		for (LocalTime time : times) {
			totalSeconds += time.toSecondOfDay();
		}

		long avgTime = totalSeconds / times.size();
		return LocalTime.ofSecondOfDay(avgTime);
	}
}

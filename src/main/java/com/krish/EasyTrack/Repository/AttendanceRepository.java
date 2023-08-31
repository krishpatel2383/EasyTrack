package com.krish.EasyTrack.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.krish.EasyTrack.Model.Attendance;

public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {

	List<Attendance> findByEmployeeEmpId(int employeeId);

}

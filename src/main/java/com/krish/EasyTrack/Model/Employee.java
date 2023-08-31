package com.krish.EasyTrack.Model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Employees")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int empId;

	@Column(nullable = false, unique = true, name = "email")
	private String email;

	@Column(nullable = false, name = "first_name")
	private String first_name;

	@Column(nullable = false, name = "last_name")
	private String last_name;

	@OneToMany(mappedBy = "employee")
	private List<Attendance> attendanceList;

	public List<Attendance> getAttendanceList() {
		return attendanceList;
	}

	public void setAttendanceList(List<Attendance> attendanceList) {
		this.attendanceList = attendanceList;
	}

	public int getempId() {
		return empId;
	}

	public void setempId(int empId) {
		this.empId = empId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getfirst_name() {
		return first_name;
	}

	public void setfirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getlast_name() {
		return last_name;
	}

	public void setlast_name(String last_name) {
		this.last_name = last_name;
	}

	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", email=" + email + ", first_name=" + first_name + ", last_name="
				+ last_name + ", attendanceList=" + attendanceList + "]";
	}

	public Employee(int empId, String email, String first_name, String last_name, List<Attendance> attendanceList) {
		super();
		this.empId = empId;
		this.email = email;
		this.first_name = first_name;
		this.last_name = last_name;
		this.attendanceList = attendanceList;
	}

	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

}

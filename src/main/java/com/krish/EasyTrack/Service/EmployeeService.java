package com.krish.EasyTrack.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.krish.EasyTrack.Model.Employee;
import com.krish.EasyTrack.Repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	public List<Employee> getAllEmployees() {
		for (Employee e : employeeRepository.findAll()) {
			System.out.println(e);
		}
		return employeeRepository.findAll();
	}

	public Employee getEmployee(int id) {
		Optional<Employee> optionalEmployeeOptional = employeeRepository.findByEmpId(id);
		if (optionalEmployeeOptional.isPresent()) {
			return optionalEmployeeOptional.get();
		}
		throw new EmptyResultDataAccessException("Employee not found", 1);
	}

	public Employee creatEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	public Employee updatEmployee(int id, Employee employee) {
		if (employeeRepository.existsById(id)) {
			Optional<Employee> existingEmployee = employeeRepository.findByEmpId(id);

			if (existingEmployee.isPresent()) {
				existingEmployee.get().setfirst_name(employee.getfirst_name());
				existingEmployee.get().setlast_name(employee.getlast_name());
				existingEmployee.get().setEmail(employee.getEmail());
			}

			return employeeRepository.save(existingEmployee.get());

		}
		throw new EmptyResultDataAccessException("Employee not found", 1);
	}

	public void deleteEmployee(int id) {
		employeeRepository.deleteById(id);
	}
}

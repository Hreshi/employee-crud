package com.hrushi.employee.configuration;

import org.springframework.core.annotation.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.hrushi.employee.entity.Employee;
import com.hrushi.employee.service.EmployeeService;

import java.util.List;
import java.time.LocalDate;

@Component
@Order(3)
public class Test implements CommandLineRunner{
	
	@Autowired EmployeeService service;

	public void run(String[] args) {
		Employee emp = new Employee();
		emp.name = "hrushi";
		emp.dateOfJoining = LocalDate.now();
		emp.yearsOfExperience = 0;
		emp.designation = "Intern";

		Employee e1 = service.insertEmployee(emp);
		Employee e2 = service.insertEmployee(emp);
		Employee e3 = service.insertEmployee(emp);
		Employee e4 = service.insertEmployee(emp);
		Employee e5 = service.insertEmployee(emp);
		emp.designation = "Founder and CTO MazeBlocks";
		Employee e6 = service.updateEmployee(emp);

		service.deleteEmployee(emp);
		List<Employee> list = service.readAllEmployees();

		for (Employee employee : list) {
			System.out.println(employee.toString());
		}
	}
}
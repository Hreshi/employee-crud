package com.hrushi.employee.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import com.hrushi.employee.entity.Employee;
import com.hrushi.employee.service.EmployeeService;

@RestController
public class EmployeeController {
	
	@Autowired EmployeeService service;

	@GetMapping("employee/all")
	public List<Employee> getAllEmployees() {
		return service.readAllEmployees();
	}

	@DeleteMapping("employee/delete")
	public void deleteEmployee(@RequestBody Employee emp) {
		service.deleteEmployee(emp);
	}

	@PostMapping("employee/insert")
	public Employee insertEmployee(@RequestBody Employee emp) {
		return service.insertEmployee(emp);
	}

	@PostMapping("employee/update")
	public Employee updateEmployee(@RequestBody Employee emp) {
		return service.updateEmployee(emp);
	}
}
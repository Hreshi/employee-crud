package com.hrushi.employee.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import com.hrushi.employee.entity.Employee;
import com.hrushi.employee.entity.Query;

@Component
public class EmployeeService {
	
	@Autowired Query queries;
	@Autowired JdbcTemplate connection;

	public List<Employee> readAllEmployees () {
		String query = queries.getReadAllQuery();
		List<Employee> list = connection.query(query, (res, index) -> {
			Employee emp = new Employee();
			emp.id = res.getInt("ID");
			emp.name = res.getString("NAME");
			emp.dateOfJoining = res.getDate("DATE_OF_JOINING").toLocalDate();
			emp.yearsOfExperience = res.getInt("YEARS_OF_EXPERIENCE");
			emp.designation = res.getString("DESIGNATION");
			return emp;
		});
		return list;
	}

	public Employee insertEmployee (Employee emp) {
		String query = queries.getInsertQuery();
		connection.update(query, emp.id, emp.name, emp.dateOfJoining.toString(), emp.yearsOfExperience, emp.designation);
		return emp;
	}

	public Employee updateEmployee (Employee emp) {
		String query = queries.getUpdateQuery();
		connection.update(query, emp.yearsOfExperience, emp.designation, emp.id);
		return emp;
	}

	public void deleteEmployee (Employee emp) {
		String query = queries.getDeleteQuery();
		connection.update(query, emp.id);
	}
}
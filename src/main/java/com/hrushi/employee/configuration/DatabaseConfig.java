package com.hrushi.employee.configuration;

import org.springframework.core.annotation.Order;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;

import com.hrushi.employee.entity.Query;

@Configuration
@Order(2)
public class DatabaseConfig implements CommandLineRunner {
	
	@Autowired JdbcTemplate connection;
	@Autowired Query queries;

	public void run (String[] args) {
		createEmployeeTable();
	}

	private void createEmployeeTable () {
		try 
		{
			String query = queries.getCreateTableQuery();
			connection.execute(query);
			System.out.println(query);
		} 
		catch (Exception e) {
			System.out.println(e.toString());
		}
	}
}
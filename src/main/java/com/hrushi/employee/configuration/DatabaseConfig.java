package com.hrushi.employee.configuration;

import org.springframework.core.io.ClassPathResource;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;

import java.io.File;
import java.nio.file.Files;

@Configuration
public class DatabaseConfig implements CommandLineRunner {
	
	@Value("${sql.query.create_employee_table}") 
	String tableCreateQueryPath;

	public void run (String[] args) {
		createEmployeeTable();
	}

	private void createEmployeeTable () {
		try 
		{
			String query = read(tableCreateQueryPath);
			System.out.println(query);
		} 
		catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	private String read (String filePath) throws Exception {
		File resource = new ClassPathResource(filePath).getFile();
		String text = new String(Files.readAllBytes(resource.toPath()));
		return text;
	}
}
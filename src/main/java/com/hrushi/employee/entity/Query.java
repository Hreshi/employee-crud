package com.hrushi.employee.entity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ClassPathResource;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Files;

@Component
@Order(1)
public class Query implements CommandLineRunner {

	@Value("${sql.query.create_employee_table}") 
	String createTableQueryPath;

	@Value("${sql.query.read_all_employees}") 
	String readAllQueryPath;

	@Value("${sql.query.insert_employee}") 
	String insertQueryPath;

	@Value("${sql.query.update_employee}") 
	String updateQueryPath;

	@Value("${sql.query.delete_employee}") 
	String deleteQueryPath;

	private String createTableQuery;
	private String readAllQuery;
	private String insertQuery;
	private String updateQuery;
	private String deleteQuery;

	public void run (String[] args) {
		try 
		{
			createTableQuery = read(createTableQueryPath);
			readAllQuery = read(readAllQueryPath);
			insertQuery = read(insertQueryPath);
			updateQuery = read(updateQueryPath);
			deleteQuery = read(deleteQueryPath);
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
	
	public String getCreateTableQuery() {
		return createTableQuery;
	}

	public String getReadAllQuery() {
		return readAllQuery;
	}

	public String getInsertQuery() {
		return insertQuery;
	}

	public String getUpdateQuery() {
		return updateQuery;
	}

	public String getDeleteQuery() {
		return deleteQuery;
	}
}
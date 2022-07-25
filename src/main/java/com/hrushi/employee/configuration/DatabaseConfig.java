package com.hrushi.employee.configuration;

import org.springframework.core.io.ClassPathResource;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.File;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;

import com.hrushi.employee.entity.Employee;

@Configuration
public class DatabaseConfig implements CommandLineRunner {
	
	@Autowired JdbcTemplate connection;

	@Value("${sql.query.create_employee_table}") 
	String tableCreateQueryPath;

	@Value("${sql.query.delete_employee}") 
	String deleteQueryPath;

	@Value("${sql.query.insert_employee}") 
	String insertQueryPath;

	@Value("${sql.query.read_all_employees}") 
	String readAllQueryPath;

	@Value("${sql.query.update_employee}") 
	String updateQueryPath;

	public void run (String[] args) {
		createEmployeeTable();
		test();
	}

	private void createEmployeeTable () {
		try 
		{
			String query = read(tableCreateQueryPath);
			connection.execute(query);
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

	private void test () {
		try 
		{
			String query = read(insertQueryPath);
			connection.update(query, 1, "hrushi", "2022-8-1", 0, "Intern");
			connection.update(query, 2, "hrushi", "2022-8-1", 0, "Intern");
			connection.update(query, 3, "hrushi", "2022-8-1", 0, "Intern");
			connection.update(query, 4, "hrushi", "2022-8-1", 0, "Intern");
			System.out.println("Worked\n");
			int result = connection.queryForObject("select count(*) from EMPLOYEE_DETAILS;", Integer.class);
			System.out.println("Result : " + result);

			String updateQuery = read(updateQueryPath);
			connection.update(updateQuery, 3, "Software Engineer", 1);
			String readAllQuery = read(readAllQueryPath);
			List<Map<String, Object>> list = connection.queryForList(readAllQuery);

			for (Map<String, Object> emp : list) {
				System.out.print((int)emp.get("ID") + " ");
				System.out.print((String)emp.get("NAME")+ " ");
				System.out.print(emp.get("DATE_OF_JOINING").toString()+ " ");
				System.out.print((int)emp.get("YEARS_OF_EXPERIENCE")+ " ");
				System.out.print((String)emp.get("DESIGNATION")+ " ");
				System.out.println();
			}
		} 
		catch (Exception e) {
			System.out.println(e.toString());
		}
	}
}
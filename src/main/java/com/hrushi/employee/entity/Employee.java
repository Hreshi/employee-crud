package com.hrushi.employee.entity;

import java.time.LocalDate;

public class Employee {
	public Integer id;
	public String name;
	public LocalDate dateOfJoining;
	public Integer yearsOfExperience;
	public String designation;

	public String toString () {
		String s = "ID:" + id + ", NAME:" + name + ", DATE_OF_JOINING:" + dateOfJoining.toString();
		return s + ", EXP:" + yearsOfExperience + ", Desi:" + designation;
	}
}
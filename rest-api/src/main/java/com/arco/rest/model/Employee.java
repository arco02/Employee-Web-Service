package com.arco.rest.model;

import java.io.Serializable;

public class Employee implements Serializable {
	int id;
	String name;
	String department;
	double salary;

	public Employee() {
	}

	public Employee(int id, String name, String depepartment, double salary) {
		super();
		this.id = id;
		this.name = name;
		this.department = department;
		this.salary = salary;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String dept) {
		this.department = dept;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

}

package com.arco.rest.service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.arco.rest.model.Employee;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
@Service
public class EmployeeService {
	
	private List<Employee> emp;
	private ObjectMapper mapper=new ObjectMapper();
	private final File file = new File("C:\\Users\\hp\\Projects\\rest-api\\data\\employee.json");
	
	public List getAllEmployees()
	{
		try {
			emp=mapper.readValue(file, new TypeReference<List<Employee>>() {});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Failed to load employees",e);
		}
		return emp;
	}
	
	public Optional<Employee> getEmployeeByID(int id)
	{
		return emp.stream().filter(e->e.getId()==id).findFirst();
	}
	
	public Employee createEmployee(Employee employee)
	{
		int nextId=emp.isEmpty()? 1:emp.get(emp.size()-1).getId()+1;
		employee.setId(nextId);
		emp.add(employee);
		try {
			mapper.writerWithDefaultPrettyPrinter().writeValue(file, emp);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Failed to save employee",e);
		}
		return employee;
	}
	public Employee updateEmployee(int id, Employee employee)
	{
		for(int i=0; i<emp.size();i++)
		{
			if(emp.get(i).getId()==id)
			{
				employee.setId(id);
				emp.set(i, employee);
				try {
					mapper.writerWithDefaultPrettyPrinter().writeValue(file, emp);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					throw new RuntimeException("Failed to save employee",e);
				}
				break;
			}
		}
		return null;
	}
	public boolean deleteEmployee(int id)
	{
		boolean removed=emp.removeIf(e->e.getId()==id);
		if(removed)
		{
			try {
				mapper.writerWithDefaultPrettyPrinter().writeValue(file, emp);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				throw new RuntimeException("Failed to save employee",e);
			}
		}
		return removed;
	}

}

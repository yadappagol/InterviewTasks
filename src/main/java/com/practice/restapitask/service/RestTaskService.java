package com.practice.restapitask.service;

import java.util.List;

import com.practice.restapitask.dto.AddEmployeeDto;
import com.practice.restapitask.entity.Employee;

public interface RestTaskService {

	Employee addEmployee(AddEmployeeDto employeeDto);

	Employee getEmployee(Integer empId);

	String removeEmployee(Integer empId);
	
	String updateUser(AddEmployeeDto employeeDto);

	List<Employee> getAllEmployee();

}

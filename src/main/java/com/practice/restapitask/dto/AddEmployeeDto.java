package com.practice.restapitask.dto;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.practice.restapitask.entity.Department;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddEmployeeDto {
	private int employeeId;
	@NotBlank(message = "Enter The Employee Name")
	@NotNull(message = "Employee Name Cannot be Null")
	private String employeeName;
	@NotBlank(message = "Enter The Employee State")
	@NotNull(message = "Employee State Cannot be Null")
	private String state;
	@NotBlank(message = "Enter The Employee City Name")
	@NotNull(message = "Employee City Cannot be Null")
	private String city;
	@Email(message = "Please,Enter The Valid Mail Id")
	private String mailId;
	@NotBlank(message = "Enter The Employee Phone Number")
	@NotNull(message = "Employee City Cannot be Null")
	private String phoneNumber;
	private List<Department> departments;
}

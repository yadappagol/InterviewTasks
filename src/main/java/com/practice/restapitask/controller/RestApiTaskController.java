package com.practice.restapitask.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.restapitask.dto.AddEmployeeDto;
import com.practice.restapitask.entity.Employee;
import com.practice.restapitask.exception.InvalidDetailsException;
import com.practice.restapitask.response.ResponseMessage;
import com.practice.restapitask.service.RestTaskService;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1")
@Slf4j
@Validated
public class RestApiTaskController {

	@Autowired
	private RestTaskService restTaskService;

	@GetMapping("/getAllEmployees")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "All Employee Details Fetched Successfully.."),
			@ApiResponse(responseCode = "404", description = "All Employee Details Were Not Found!!"),
			@ApiResponse(responseCode = "403", description = "Forbidden Error!!") })
	public ResponseEntity<ResponseMessage> fetchAll() {
		List<Employee> fetchAll = restTaskService.getAllEmployee();
		if (fetchAll != null) {
			log.info(" Employee Data Stored Successfully");
			ResponseMessage responseMessage = new ResponseMessage(HttpStatus.OK.value(), new java.util.Date(), false,
					" All Employee Details Fetched Successfully", fetchAll);
			return new ResponseEntity<>(responseMessage, HttpStatus.OK);

		}
		log.error("User Details are not Present In Db");
		throw new InvalidDetailsException("Something Went Wrong,Details are wrong");

	}

	@PostMapping("/addEmployee")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "All Employee Details Added Successfully.."),
			@ApiResponse(responseCode = "404", description = "All Employee Details Were Not Added!!"),
			@ApiResponse(responseCode = "403", description = "Forbidden Error!!") })
	public ResponseEntity<ResponseMessage> addEmployee(@RequestBody @Valid AddEmployeeDto employeeDto,
			BindingResult result) {
		if (employeeDto != null ) {
			Employee addEmployee = restTaskService.addEmployee(employeeDto);
			log.info(" Employee Data Stored Successfully");
			ResponseMessage responseMessage = new ResponseMessage(HttpStatus.OK.value(), new java.util.Date(), false,
					"Details of Your Employee " + addEmployee.getEmployeeName() + " added Successfully", addEmployee);
			return new ResponseEntity<>(responseMessage, HttpStatus.OK);
		}
		log.error("User Details are not Stored Into Db");
		throw new InvalidDetailsException("Something Went Wrong,Details are wrong");
	}

	@GetMapping("/getEmployee/{id}")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "All Employee Details Fetched Successfully.."),
			@ApiResponse(responseCode = "404", description = "All Employee Details Were Not Found!!"),
			@ApiResponse(responseCode = "403", description = "Forbidden Error!!") })
	public ResponseEntity<ResponseMessage> getEmployeeById(@PathVariable("id") Integer empId) {
		if (empId >0 && empId!=null) {
			Employee employee = restTaskService.getEmployee(empId);
			log.info(" Employee Data Fetched Successfully");
			ResponseMessage responseMessage = new ResponseMessage(HttpStatus.OK.value(), new java.util.Date(), false,
					"Details of employee Id : " + employee.getEmployeeId() + " Fetched Successfully", employee);
			return new ResponseEntity<>(responseMessage, HttpStatus.OK);
		}
		log.error("User Details are not Fetched From Db");
		throw new InvalidDetailsException("Something Went Wrong,Details are wrong");
	}

	@DeleteMapping("/deleteEmployee/{id}")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "All Employee Details Deleted Successfully.."),
			@ApiResponse(responseCode = "404", description = "All Employee Details Were Not Found!!"),
			@ApiResponse(responseCode = "403", description = "Forbidden Error!!") })
	public ResponseEntity<ResponseMessage> removeEmployee(@PathVariable("id") Integer empId) {
		if (empId >0 && empId!=null) {
			String status = restTaskService.removeEmployee(empId);
			log.info(" Employee Data Removed Successfully");
			ResponseMessage responseMessage = new ResponseMessage(HttpStatus.OK.value(), new java.util.Date(), false,
					"Deleted successfully", status);
			return new ResponseEntity<>(responseMessage, HttpStatus.OK);
		}
		log.error("User Details are not Deleted From Db");
		throw new InvalidDetailsException("Something Went Wrong,Details are wrong");
	}

}

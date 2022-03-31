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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.restapitask.constant.Message;
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
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = Message.FETCHED_SUCCESSFULLY),
			@ApiResponse(responseCode = "404", description = Message.DETAILS_NOTFOUND),
			@ApiResponse(responseCode = "403", description = Message.FORBIDDEN) })
	public ResponseEntity<ResponseMessage> fetchAll() {
		List<Employee> fetchAll = restTaskService.getAllEmployee();
		if (fetchAll != null) {
			log.info(Message.FETCHED_SUCCESSFULLY);
			ResponseMessage responseMessage = new ResponseMessage(HttpStatus.OK.value(), new java.util.Date(), false,
					Message.FETCHED_SUCCESSFULLY, fetchAll);
			return new ResponseEntity<>(responseMessage, HttpStatus.OK);

		}
		log.error(Message.DETAILS_NOTFOUND);
		throw new InvalidDetailsException(Message.EXCEPTION_MSG);

	}

	@PostMapping("/addEmployee")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = Message.ADDED_SUCCESSFULLY),
			@ApiResponse(responseCode = "404", description = Message.NOT_ADDED),
			@ApiResponse(responseCode = "403", description = Message.FORBIDDEN) })
	public ResponseEntity<ResponseMessage> addEmployee(@RequestBody @Valid AddEmployeeDto employeeDto,
			BindingResult result) {
		if (employeeDto != null) {
			Employee addEmployee = restTaskService.addEmployee(employeeDto);
			log.info(Message.ADDED_SUCCESSFULLY);
			ResponseMessage responseMessage = new ResponseMessage(HttpStatus.OK.value(), new java.util.Date(), false,
					"Details of Your Employee " + addEmployee.getEmployeeName() + " added Successfully", addEmployee);
			return new ResponseEntity<>(responseMessage, HttpStatus.OK);
		}
		log.error(Message.NOT_ADDED);
		throw new InvalidDetailsException(Message.EXCEPTION_MSG);
	}

	@PutMapping("/updateEmployee")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = Message.UPDATED_SUCCESSFULLY),
			@ApiResponse(responseCode = "404", description = Message.NOT_ADDED),
			@ApiResponse(responseCode = "403", description = Message.FORBIDDEN) })
	public ResponseEntity<ResponseMessage> updateEmployee(@RequestBody @Valid AddEmployeeDto employeeDto) {
		if (employeeDto != null) {
			String update = restTaskService.updateUser(employeeDto);
			log.info(Message.UPDATED_SUCCESSFULLY);
			ResponseMessage responseMessage = new ResponseMessage(HttpStatus.OK.value(), new java.util.Date(), false,
					Message.UPDATED_SUCCESSFULLY, update);
			return new ResponseEntity<>(responseMessage, HttpStatus.OK);
		}
		log.error(Message.NOT_UPDATED);
		throw new InvalidDetailsException(Message.EXCEPTION_MSG);
	}

	@GetMapping("/getEmployee/{id}")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = Message.FETCHED_SUCCESSFULLY),
			@ApiResponse(responseCode = "404", description = Message.DETAILS_NOTFOUND),
			@ApiResponse(responseCode = "403", description = Message.FORBIDDEN) })
	public ResponseEntity<ResponseMessage> getEmployeeById(@PathVariable("id") Integer empId) {
		if (empId > 0 && empId != null) {
			Employee employee = restTaskService.getEmployee(empId);
			log.info(Message.FETCHED_SUCCESSFULLY);
			ResponseMessage responseMessage = new ResponseMessage(HttpStatus.OK.value(), new java.util.Date(), false,
					"Details of employee Id : " + employee.getEmployeeId() + " Fetched Successfully", employee);
			return new ResponseEntity<>(responseMessage, HttpStatus.OK);
		}
		log.error(Message.DETAILS_NOTFOUND);
		throw new InvalidDetailsException(Message.EXCEPTION_MSG);
	}

	@DeleteMapping("/deleteEmployee/{id}")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = Message.DELETED_SUCCESSFULLY),
			@ApiResponse(responseCode = "404", description = Message.DETAILS_NOTFOUND),
			@ApiResponse(responseCode = "403", description = Message.FORBIDDEN) })
	public ResponseEntity<ResponseMessage> removeEmployee(@PathVariable("id") Integer empId) {
		if (empId > 0 && empId != null) {
			String status = restTaskService.removeEmployee(empId);
			log.info(Message.DELETED_SUCCESSFULLY);
			ResponseMessage responseMessage = new ResponseMessage(HttpStatus.OK.value(), new java.util.Date(), false,
					Message.DELETED_SUCCESSFULLY, status);
			return new ResponseEntity<>(responseMessage, HttpStatus.OK);
		}
		log.error(Message.DELETED_SUCCESSFULLY);
		throw new InvalidDetailsException(Message.EXCEPTION_MSG);
	}

}

package com.example.demo.controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Employee;
import com.example.demo.response.EmployeeResponse;
import com.example.demo.service.EmployeeService;

@RestController
public class EmployeeController {

    
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("/employee/{id}")
	ResponseEntity<EmployeeResponse> getEmployeeDetails(@PathVariable("id") int id) throws ParseException {
		EmployeeResponse employeeResponse = employeeService.getEmployeeDetails(id);
		return ResponseEntity.status(HttpStatus.OK).body(employeeResponse);
	}
	
	@PostMapping("/employee")
	ResponseEntity<EmployeeResponse> saveEmployeeDetails(@RequestBody	Employee employee) throws ParseException{
		EmployeeResponse saveEmployeeDetails = employeeService.saveEmployeeDetails(employee);
		return ResponseEntity.ok().body(saveEmployeeDetails);
	}
}

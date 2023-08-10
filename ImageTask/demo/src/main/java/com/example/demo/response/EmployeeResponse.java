package com.example.demo.response;

import java.math.BigDecimal;

public class EmployeeResponse {

	private int id;

	private String firstname;

	private String lastname;

	private BigDecimal taxamount;

	private BigDecimal yearlySalary;

	private BigDecimal cessamount;

	private String message;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public BigDecimal getTaxamount() {
		return taxamount;
	}

	public void setTaxamount(BigDecimal taxamount) {
		this.taxamount = taxamount;
	}

	public BigDecimal getYearlySalary() {
		return yearlySalary;
	}

	public void setYearlySalary(BigDecimal yearlySalary) {
		this.yearlySalary = yearlySalary;
	}

	public BigDecimal getCessamount() {
		return cessamount;
	}

	public void setCessamount(BigDecimal cessamount) {
		this.cessamount = cessamount;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}

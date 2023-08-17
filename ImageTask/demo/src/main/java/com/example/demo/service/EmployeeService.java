package com.example.demo.service;

import java.math.BigDecimal;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Employee;
import com.example.demo.repository.EmployeeRepo;
import com.example.demo.response.EmployeeResponse;

@Service
public class EmployeeService {
	@Autowired
	private EmployeeRepo employeeRepo;

	@Autowired
	private ModelMapper modelMapper;

	public EmployeeResponse getEmployeeDetails(int id) throws ParseException {
		Employee employee = employeeRepo.findById(id).get();
		EmployeeResponse response = tax(employee);
		EmployeeResponse employeeResponse = modelMapper.map(response, EmployeeResponse.class);
		return employeeResponse;

	}

	@SuppressWarnings("deprecation")
	public EmployeeResponse saveEmployeeDetails(Employee employee) throws ParseException {

		int j = validateEmp(employee);
		if (j == 1) {
			EmployeeResponse response = tax(employee);
			return response;
		} else {
			EmployeeResponse employeeResponse = modelMapper.map(employee, EmployeeResponse.class);
			employeeResponse.setMessage("Make sure all fields were filled. i.e; All fields are mandatory");
			return employeeResponse;
		}
	}

	public int validateEmp(Employee employee) {
		if (employee.getFirstname().equals("")) {
			return 0;
		}
		if (employee.getLastname().equals("")) {
			return 0;
		}
		if (employee.getEmail().equals("")) {
			return 0;
		}
		if (employee.getPhonenumber().equals("")) {
			return 0;
		}
		if (employee.getDoj() == null) {
			return 0;
		}
		if (employee.getSalary().compareTo(BigDecimal.ZERO) == 0) {
			return 0;
		}
		return 1;

	}

	public EmployeeResponse tax(Employee employee) throws ParseException {

		int months = 12;
		BigDecimal salary;
		BigDecimal totalsalary;
		BigDecimal ts;
		BigDecimal cs;
		int i = 0;
		int[] integerValues = { 250000, 500000, 1000000, 2500000 };
                employeeRepo.save(employee);
		EmployeeResponse employeeResponse = modelMapper.map(employee, EmployeeResponse.class);
		employeeResponse.setMessage("Success");
		Date date = employee.getDoj();
		String str = "2013-05-16";
		Date date1 = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(str);

		if ((date.getMonth() == date1.getMonth()) && (date.getDay() == date1.getDay())) {
			months = (int) 10.5;
			salary = employee.getSalary();
			totalsalary = salary.multiply(BigDecimal.valueOf(months));
			employeeResponse.setYearlySalary(totalsalary);
		} else {
			months = 12;
			salary = employee.getSalary();
			totalsalary = salary.multiply(BigDecimal.valueOf(months));
			employeeResponse.setYearlySalary(totalsalary);
		}

		for (int intValue : integerValues) {
			BigDecimal intAsBigDecimal = BigDecimal.valueOf(intValue);
			int comparisonResult = totalsalary.compareTo(intAsBigDecimal);
			if (comparisonResult > 0) {
				i++;
				if (i == 1) {
					ts = totalsalary.multiply(BigDecimal.valueOf(0.05));
					employeeResponse.setTaxamount(totalsalary.subtract(ts));
				} else if (i == 2) {
					ts = totalsalary.multiply(BigDecimal.valueOf(0.1));
					employeeResponse.setTaxamount(totalsalary.subtract(ts));
				} else if (i == 3) {
					ts = totalsalary.multiply(BigDecimal.valueOf(0.2));
					employeeResponse.setTaxamount(totalsalary.subtract(ts));
				} else if (i == 4) {
					ts = totalsalary.multiply(BigDecimal.valueOf(0.2));
					cs = totalsalary.multiply(BigDecimal.valueOf(0.02));
					BigDecimal ts1 = ts.subtract(cs);
					employeeResponse.setTaxamount(totalsalary.subtract(ts1));
					employeeResponse.setCessamount(cs);
				}

			} else if (comparisonResult < 0) {
				employeeResponse.setYearlySalary(totalsalary);
				employeeResponse.setTaxamount(BigDecimal.ZERO);
				employeeResponse.setCessamount(BigDecimal.ZERO);
			} else {
				if (i == 1) {
					ts = totalsalary.multiply(BigDecimal.valueOf(0.05));
					employeeResponse.setTaxamount(totalsalary.subtract(ts));
				} else if (i == 2) {
					ts = totalsalary.multiply(BigDecimal.valueOf(0.1));
					employeeResponse.setTaxamount(totalsalary.subtract(ts));
				} else if (i == 3) {
					ts = totalsalary.multiply(BigDecimal.valueOf(0.2));
					employeeResponse.setTaxamount(totalsalary.subtract(ts));
				} else if (i == 4) {
					ts = totalsalary.multiply(BigDecimal.valueOf(0.2));
					cs = totalsalary.multiply(BigDecimal.valueOf(0.02));
					BigDecimal ts1 = ts.subtract(cs);
					employeeResponse.setTaxamount(totalsalary.subtract(ts1));
					employeeResponse.setCessamount(cs);
				}
			}
		}
		return employeeResponse;
	}
}

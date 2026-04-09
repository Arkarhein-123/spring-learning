package com.solt.service;

import java.sql.Date;

import org.springframework.stereotype.Service;

import com.solt.dao.EmployeeDao;
import com.solt.ds.Employee;

@Service
public class EmployeeService {
	private final EmployeeDao employeeDao;

	public EmployeeService(EmployeeDao employeeDao) {
		super();
		this.employeeDao = employeeDao;
	}
	
	public void doAction() {
		System.out.println("List Employee Emails.");
		employeeDao.listEmployeeEmail()
		.forEach(System.out::println);
		System.out.println("Employee list.");
		employeeDao.listAllEmployee()
		.forEach(System.out::println);
		System.out.println("Average RollCallbackHandler::" + 
		employeeDao.getAverageRollCallbackHandaler());
		System.out.println("Average ResultSetExtractor::" + 
		employeeDao.getAverageResultSetExtractor());
		System.out.println("Average NativeWay" +
		employeeDao.getAverageNativeWay());
		System.out.println("Average MordenWay" +
		employeeDao.getAverageMordenWay());
		System.out.println("EmployeeByEmail::" + 
		employeeDao.findEmployeeByEmail("mary@mail.com"));
		System.out.println("EmployeeByEmailV2::" +
		employeeDao.findEmployeeByEmailV2("mary@mail.com"));
		System.out.println("EmployeeByEmailId::" + 
		employeeDao.findEmployeeById(0));
		System.out.println("HightSalaryEmployee::" +
		employeeDao.hightSalaryEmployee());
		System.out.println("HightSalaryEmployeeV2::" + 
		employeeDao.hightSalaryEmployeeV2());
		employeeDao.createEmployee(new Employee(null, "Khin", "Thazin", "thazin@mail.com", "55-555-59", Date.valueOf("2024-03-11"), 2500));
		System.out.println("List All Employee::");
		employeeDao.listAllEmployee().forEach(System.out::println);
		employeeDao.changeSalaryEmployee(1, 3000);
		System.out.println("List All Employee::");
		employeeDao.listAllEmployee().forEach(System.out::println);
		System.out.println("Delete Employee.");
		employeeDao.deleteById(1);
		System.out.println("List All Employee::");
		employeeDao.listAllEmployee().forEach(System.out::println);
		

		
		
	}
	

}

package com.solt.service;

import org.springframework.stereotype.Service;

import com.solt.dao.EmployeeDao;

@Service
public class EmployeeService {
	
	private final EmployeeDao employeeDao ;
	
	public EmployeeService(EmployeeDao employeeDao) {
		super();
		this.employeeDao = employeeDao;
	}
	
	public void doAction() {
		System.out.println("List Employee Emails.");
		employeeDao.listEmployeeEmails().forEach(System.out::println);
		
		System.out.println("Employee List::");
		employeeDao.listAllEmployee().forEach(System.out::println);
		
		System.out.println("Average RollCallbackHandler::"+employeeDao.getAverageRollCallbackhandler());
		
		System.out.println("Average ResultSetExtractor::"+employeeDao.getAverageResultSetExtractor());
		
		System.out.println("Average NativeWay::"+employeeDao.getAverageNativeWay());
		
		System.out.println("Average ModernWay::"+employeeDao.getAverageModernWay());
		
		System.out.println("EmployeeByEmail::"+employeeDao.findEmployeeByEmail("mary@mail.com"));
		
		System.out.println("EmployeeByEmailV2::" + employeeDao.findEmployeeByEmailV2("charles@mail.com"));
		
		System.out.println("EmployeeById::" + employeeDao.findEmployeeById(2));
		
		System.out.println("EmployeeByFirstNameAndLastName::" + employeeDao.findEmployeeByFirstNameAndLastName("John", "Doe"));
		
		System.out.println("EmployeeByHightSalaryEmployee::" + employeeDao.hightSalaryEmployee());
		
		System.out.println("EmployeeByHighSalaryEmployeeV2::" + employeeDao.highSalaryEmployeeV2());
	}

}

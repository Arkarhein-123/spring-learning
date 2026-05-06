package com.example.demo.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.dto.EmployeeDto;
import com.example.demo.dto.Employees;
import com.example.demo.exception.TransportErrorException;

@Service
public class EmployeeService {
	private final String BACKEND_URL="http://localhost:8080/api/employees";
	
	private RestTemplate restTemplate;

	public EmployeeService() {
		restTemplate = new RestTemplate();
	}
	
	public List<EmployeeDto> listAllEmployees(){
		ResponseEntity<Employees> response = restTemplate
				.getForEntity(this.BACKEND_URL, Employees.class);
		if(response.getStatusCode().is2xxSuccessful()) {
			return response.getBody().getEmployees();
		}
		throw new TransportErrorException();
	}
}

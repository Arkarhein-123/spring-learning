package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ContractEmployeeDao;
import com.example.demo.dao.PartTimeEmployeeDao;
import com.example.demo.dao.FullTimeEmployeeDao;
import com.example.demo.entity.ContractEmployee;
import com.example.demo.entity.FullTimeEmployee;
import com.example.demo.entity.PartTimeEmployee;


@Service
public class EmployeeService {
	@Autowired
	private FullTimeEmployeeDao fullTimeEmployeeDao;
	@Autowired
	private PartTimeEmployeeDao partTimeEmployeeDao;
	@Autowired
	private ContractEmployeeDao contractEmployeeDao;


	public void createDb() {
		ContractEmployee cem1 = new ContractEmployee("Arkar",200);
		ContractEmployee cem2 = new ContractEmployee("Hein",240);
		
		FullTimeEmployee fem1 = new FullTimeEmployee("Justin",20,2345);
		FullTimeEmployee fem2 = new FullTimeEmployee("Thomas",15,232);
		
		PartTimeEmployee pem1 = new PartTimeEmployee("Thuza",20,100);
		PartTimeEmployee pem2 = new PartTimeEmployee("thinza",20,200);
		
		contractEmployeeDao.save(cem1);
		contractEmployeeDao.save(cem2);
		
		fullTimeEmployeeDao.save(fem1);
		fullTimeEmployeeDao.save(fem2);
		
		partTimeEmployeeDao.save(pem1);
		partTimeEmployeeDao.save(pem2);
	}
}

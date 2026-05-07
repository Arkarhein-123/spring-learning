package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dao.AddressDao;
import com.example.demo.dto.AddressDto;
import com.example.demo.entity.util.AddressUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AddressService {
	private final  AddressDao addressDao;
	
	public List<AddressDto> listAllAddress(){
		return addressDao.findAll()
				.stream()
				.map(AddressUtil::toDto)
				.toList(); 
	}
}


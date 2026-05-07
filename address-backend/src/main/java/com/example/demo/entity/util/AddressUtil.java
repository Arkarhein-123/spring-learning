package com.example.demo.entity.util;

import org.springframework.beans.BeanUtils;

import com.example.demo.dto.AddressDto;
import com.example.demo.entity.Address;

public class AddressUtil {
	public static AddressDto toDto(Address address) {
		AddressDto addressDto = new AddressDto();
		BeanUtils.copyProperties(address, addressDto);
		return addressDto;
	}
}

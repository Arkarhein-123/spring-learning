package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDto {
	private Long id;
	private String streetName;
	private String city;
	private String state;
	private String zipCode;
}

package com.example.demo.service;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.resilience.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.example.demo.client.AddressClient;
import com.example.demo.dto.AddressDto;
import com.example.demo.exception.TransportErrorException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AddressService {
	
	private final AddressClient addressClient;
	private final RestClient restClient;
	
	@Retryable(
            maxRetries = 8,
            includes = TransportErrorException.class,
            delay = 1000,
            multiplier = 2
    )
	public List<AddressDto> listAllAddresses(){
		return addressClient.getAllAddresses();
//		return restClient
//				.get()
//				.retrieve()
//				.body(new ParameterizedTypeReference<List<AddressDto>>() {
//				});
		
	}
}

package com.example.demo.security;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomeUserDetailsService implements UserDetailsService{
	
	private List<SecurityUser> userDetails;
	
	
	public CustomeUserDetailsService(List<SecurityUser> users) {
		super();
		this.userDetails = users;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userDetails.stream()
				.filter(c -> c.getUsername().equals(username))
				.findFirst()
				.orElseThrow(() -> new UsernameNotFoundException(username + "Not found.")); 
	}

}

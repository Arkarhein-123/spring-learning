package com.example.demo.entity;

import java.math.BigDecimal;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@DiscriminatorValue("SITEOWNER")
public class SiteOwner extends User{
	
	private BigDecimal platFormShare;

	public SiteOwner(String username, String password, String email) {
		super(username, password, email);

	}
	
	
	

}

package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Cage extends IdClass {
	
	private String cageNo;
	private String location;
	@OneToOne
	private Animal animal;
	
	public Cage(String cageNo, String location) {
		super();
		this.cageNo = cageNo;
		this.location = location;
	}	
}

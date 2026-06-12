package com.example.demo.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Course extends IdClass{
	
	private String title;
	private BigDecimal fees;
	@Column(columnDefinition = "text")
	private String description;
    private int studentCount;
    
    @Lob
    private byte[] image;
    
    @ManyToOne
    private Category category;
    
    @ManyToOne
    private Teacher teacher;
    

}

package com.example.demo.dto;

import java.math.BigDecimal;
import java.util.Base64;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CourseDto {
	
	
   private Long courseId;
   private String title;
   private BigDecimal fees;
   private String description;
   private String category;
   private String teacherName;
   private String imagebase64;
   
   public CourseDto(Long courseId,String title, BigDecimal fees, String description, String category, String teacherName,
		byte[] imagebase64) {
	super();
	this.courseId = courseId;
	this.title = title;
	this.fees = fees;
	this.description = description;
	this.category = category;
	this.teacherName = teacherName;	
	this.imagebase64 = imagebase64 != null ? Base64.getEncoder().encodeToString(imagebase64) :  null;
   }
   
   
   
   
   
}

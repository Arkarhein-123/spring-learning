package com.example.demo;

public record StudentInfoRecord(
			Long studendId,
			String provinceName,
			String name,
			String school,
			int marks,
			double fees,
			String subjectName
		) {
	
}

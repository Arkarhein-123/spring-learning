package com.solt.ds;

import java.util.Date;

public record Employee(
		int id,
		String firstName,
		String lastName,
		String email,
		String phoneNumber,
		Date hiredDate,
		Double salary
		) {
	
}

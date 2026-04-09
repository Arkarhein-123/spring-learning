package com.solt.ds;

import java.sql.Date;

public record Employee (
		Integer id,
		String firstName,
		String lastName,
		String email,
		String phoneNumber,
		Date hiredDate,
		double salary
		){

}

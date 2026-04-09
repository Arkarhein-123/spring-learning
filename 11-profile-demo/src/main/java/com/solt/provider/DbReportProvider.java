package com.solt.provider;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.solt.ds.Report;
import com.solt.profile.ReportProvider;

@Component @Profile("db")
public class DbReportProvider implements ReportProvider{
	@Override
	public Report getReport() {
		System.out.println("DbReportProvider.getReport()");
		return new Report(); 
	}
}

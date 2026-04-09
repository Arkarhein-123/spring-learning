package com.solt.provider;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.solt.ds.Report;
import com.solt.profile.ReportProvider;

@Component @Profile("file")
public class FileReportProvider implements ReportProvider{
	@Override
	public Report getReport() {
		System.out.println("FileReportProvider.getReport()");
		return new Report();
	}
}

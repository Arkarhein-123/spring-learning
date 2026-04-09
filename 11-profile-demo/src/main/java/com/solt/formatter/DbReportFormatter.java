package com.solt.formatter;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.solt.ds.FormattedReport;
import com.solt.ds.Report;

@Component @Profile("db")
public class DbReportFormatter implements ReportFormatter{
	@Override
	public FormattedReport format(Report report) {
		System.out.println("DbReportFormatter.format(Report report)");
		return new FormattedReport();
	}
}

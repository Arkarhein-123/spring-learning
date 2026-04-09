package com.solt.formatter;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.solt.ds.FormattedReport;
import com.solt.ds.Report;

@Component @Profile("file")
public class FileReportFormatter implements ReportFormatter{
	@Override
	public FormattedReport format(Report report) {
		System.out.println("FileReportFormatter.format(Report report)");
		return new FormattedReport();
	}
}

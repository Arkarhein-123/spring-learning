package com.solt.repository;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.solt.ds.FormattedReport;

@Component @Profile("file")
public class FileReportRepository implements ReportRepository {
	@Override
	public void save(FormattedReport report) {
		System.out.println("FileReportRepository.save(FormattedReport report");
	}
}

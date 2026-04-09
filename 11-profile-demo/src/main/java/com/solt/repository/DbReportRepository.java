package com.solt.repository;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.solt.ds.FormattedReport;

@Component @Profile("db")
public class DbReportRepository implements ReportRepository {

	@Override
	public void save(FormattedReport report) {
		System.out.println("DbReportRepository.save(FormattedReport report)");
	}

}

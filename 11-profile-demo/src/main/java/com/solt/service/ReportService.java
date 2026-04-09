package com.solt.service;

import org.springframework.stereotype.Component;

import com.solt.ds.FormattedReport;
import com.solt.ds.Report;
import com.solt.formatter.ReportFormatter;
import com.solt.profile.ReportProvider;
import com.solt.repository.ReportRepository;

@Component
public class ReportService {
	private final ReportRepository reportRepository;
	private final ReportProvider reportProvider;
	private final ReportFormatter reportFormatter;

	public ReportService(ReportRepository reportRepository, ReportProvider reportProvider,
			ReportFormatter reportFormatter) {
		super();
		this.reportRepository = reportRepository;
		this.reportProvider = reportProvider;
		this.reportFormatter = reportFormatter;
	}
	
	public void doAction() {
		Report report = reportProvider.getReport();
		FormattedReport formattedReport = reportFormatter.format(report);
		reportRepository.save(formattedReport);
	}

}

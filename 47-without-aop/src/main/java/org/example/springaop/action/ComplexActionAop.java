package org.example.springaop.action;

import lombok.RequiredArgsConstructor;
import org.example.springaop.ds.Report;
import org.example.springaop.formatter.ReportFormatter;
import org.example.springaop.provider.ReportProvider;
import org.example.springaop.repository.ReportRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ComplexActionAop {
    private final ReportFormatter reportFormatter;
    private final ReportProvider reportProvider;
    private final ReportRepository reportRepository;

    public void doAction(){
        Report report = reportProvider.getReport();
        Report formattedReport = reportFormatter.formatReport(report);
        reportRepository.saveReport(formattedReport);
    }
}

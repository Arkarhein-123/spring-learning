package org.example.springaop.formatter;

import org.example.springaop.ds.Report;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class ReportFormatter {
    public Report formatReport(Report report){
        try{
            Thread.sleep(Duration.ofSeconds(2).toMillis());
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
            throw new RuntimeException("Thread was interrupted", e);
        }
        System.out.println("ReportFormatter::formatReport()");
        return new Report();
    }

}

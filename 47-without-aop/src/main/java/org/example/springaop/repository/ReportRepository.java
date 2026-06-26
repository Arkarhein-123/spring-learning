package org.example.springaop.repository;

import org.example.springaop.ds.Report;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class ReportRepository {

    public  void saveReport(Report formattedReport){
        try{
            Thread.sleep(Duration.ofSeconds(3).toMillis());
        }catch (InterruptedException e){
            throw new RuntimeException();
        }
        System.out.println("ReportRepository::saveReport");
    }
}

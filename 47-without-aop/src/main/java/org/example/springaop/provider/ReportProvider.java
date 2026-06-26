package org.example.springaop.provider;

import org.example.springaop.ds.Report;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class ReportProvider {
    public Report getReport(){
        try{
            Thread.sleep(Duration.ofSeconds(3).toMillis());
        }catch (InterruptedException e){
            throw new RuntimeException();
        }
        System.out.println("ReportProvider :: getReport()");
        return new Report();
    }
}

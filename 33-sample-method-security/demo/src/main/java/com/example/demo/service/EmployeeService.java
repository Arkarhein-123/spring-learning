package com.example.demo.service;

import com.example.demo.model.Employee;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class EmployeeService {
    private Map<String , Employee> records = Map.of(
            "Arkar",new Employee("Arkar Htet",
                    List.of("A Tale of Two cities","Karamazov Brothers"),
                    List.of("Programmer","Reader")
                    ),
            "Hein", new Employee(
                    "Hein Min Thant",
                    List.of("A Pale View of Hill","Far From then Maddening Crowd"),
                    List.of("Researcher")
            )
    );

    @PostAuthorize("returnObject.roles.contains('Reader')")
    public Employee getEmployeeDetails(String name){
        return records.get(name);
    }
}

package com.example.demo.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Subject extends IdClass {
    private String subjectName;
    private int durationMonth;
    private double fees;
    
    // Fixed: Changed List type from Subject to StudentSubject
    @OneToMany(mappedBy = "subject")
    private List<StudentSubject> studentSubjects = new ArrayList<>();
    
    public Subject(String subjectName, int durationMonth, double fees) {
        super();
        this.subjectName = subjectName;
        this.durationMonth = durationMonth;
        this.fees = fees;
    }
    
    // Utility method
    public void addStudentSubject(StudentSubject studentSubject) {
        studentSubject.setSubject(this);
        this.studentSubjects.add(studentSubject);
    }
} // Added missing brace
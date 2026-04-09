package com.example.demo.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
public class StudentSubject extends IdClass {
    private int marks;
    

    public StudentSubject(int marks) {
        super();
        this.marks = marks;
    }
    

    @ManyToOne
    private Student student;
    
    
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Subject subject;
    
    
    public StudentSubject() {
    	
    }
}
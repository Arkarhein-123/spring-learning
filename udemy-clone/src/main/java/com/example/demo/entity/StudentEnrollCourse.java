package com.example.demo.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class StudentEnrollCourse extends IdClass{
    private String orderId;
    private LocalDate orderDate;
    private int completionState;
    @ManyToOne
    private Student student;
    @ManyToOne
    private Course course;

    public StudentEnrollCourse(String orderId, LocalDate orderDate, int completionState) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.completionState = completionState;
    }
}

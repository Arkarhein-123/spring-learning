package org.example.simplerestapi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private LocalDate hiredDate;
    private double salary;

    public Employee(
            String firstName,
            String lastName,
            String email,
            String phoneNumber,
            LocalDate hiredDate,
            double salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.hiredDate = hiredDate;
        this.salary = salary;
    }
}

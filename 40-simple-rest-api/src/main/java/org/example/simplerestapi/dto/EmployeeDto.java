package org.example.simplerestapi.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;

@Getter
@Setter
public class EmployeeDto {
    private Long id;
    @NotEmpty(message = "first name is required.")
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private LocalDate hiredDate;
    private double salary;
}

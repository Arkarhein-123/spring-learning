package org.example._4springdatajparevison.entity2;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@DiscriminatorValue("STUDENT")
public class Student extends Person{
    private String grade;
}

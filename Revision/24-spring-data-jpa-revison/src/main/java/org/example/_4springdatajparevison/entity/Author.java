package org.example._4springdatajparevison.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="author_tbl")

public class Author extends BaseEntity{

    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false,unique = true)
    private String email;

    @ManyToMany(mappedBy = "authors")
    private List<Course> courses = new ArrayList<>();
}

package org.example._4springdatajparevison.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name="course_tbl")
public class Course extends BaseEntity{
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String description;

    @ManyToMany
    @JoinTable(
            name = "authors_courses",
            joinColumns = {
                    @JoinColumn(name = "course_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "author_id")
            }
    )
    private List<Author>  authors = new ArrayList<>();

    @OneToMany(mappedBy = "course")
    private List<Section> sections = new ArrayList<>();
}

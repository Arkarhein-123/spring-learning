package org.example._4springdatajparevison.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = "lecture_tbl")
public class Lecture extends BaseEntity{
    private String name;
    @ManyToOne
    @JoinColumn(name = "section_id")
    private Section section;
    @OneToOne
    @JoinColumn(name = "resource_id",unique = true)
    private Resource resource;
}

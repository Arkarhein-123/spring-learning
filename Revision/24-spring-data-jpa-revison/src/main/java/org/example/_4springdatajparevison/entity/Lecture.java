package org.example._4springdatajparevison.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "lecture_tbl")
public class Lecture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "section_id")
    private Section section;
    @OneToOne
    @JoinColumn(name = "resource_id",unique = true)
    private Resource resource;
}

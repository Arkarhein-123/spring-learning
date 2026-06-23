package org.example._4springdatajparevison.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Resource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int size;
    private String url;
    @OneToOne()
    @JoinColumn(name = "lecture_id")
    private Lecture lecture;
}

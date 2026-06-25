package org.example._4springdatajparevison.entity.embedded;

import jakarta.persistence.Embeddable;

@Embeddable
public class Address {
    private String street;
    private String tsp;
    private String region;
}

package org.example._4springdatajparevison.entity.embedded;

import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "_order")
public class Order {
    @EmbeddedId
    private OrderId orderId;
    @Embedded
    private Address address;
    private String orderInfo;
    private String orderName;
}

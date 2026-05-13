package com.example.demo.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ProductInventoryAudit { // audit - (who , what , when)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String username;
	private int debutQuantity;  // 5
	@CreationTimestamp
	private LocalDateTime createdAt;
	@Enumerated(EnumType.STRING)
	private InventoryStatus inventoryStatus;  // debut 
	@ManyToOne
	private ProductInventory productInventory;
	
	public ProductInventoryAudit(String username, int debutQuantity, InventoryStatus inventoryStatus) {
		super();
		this.username = username;
		this.debutQuantity = debutQuantity;
		this.inventoryStatus = inventoryStatus;
	}
	
	
	
}

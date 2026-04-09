package com.example.demo.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private double price;
	@Column(columnDefinition = "text")
	private String description;
	private LocalDate lastUpdated;
	@JoinTable(
			name = "product_category",
			joinColumns = 
				@JoinColumn(name = "product_id_fk",referencedColumnName = "id"),
			inverseJoinColumns = 
				@JoinColumn(name = "category_id_fk",referencedColumnName = "id")
			)
	@ManyToOne
	private Category category;
	@OneToMany(mappedBy = "product",
			cascade = CascadeType.PERSIST)
	private List<CustomerOrderProduct> customerOrderProducts = 
			new ArrayList<>();
	
	public Product(String name, double price, String description, LocalDate lastUpdated) {
		super();
		this.name = name;
		this.price = price;
		this.description = description;
		this.lastUpdated = lastUpdated;
	}
	
	public void addCustomerOrderProduct(CustomerOrderProduct orderProduct) {
		orderProduct.setProduct(this);
		customerOrderProducts.add(orderProduct);
	}
	
	
	
}

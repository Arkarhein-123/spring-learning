package com.example.demo.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
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
public class CustomerOrder {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private double amount;
	private LocalDate dateCreated;
	private int confirmationNumber;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinTable(
			name = "customer_order_customer", 
			joinColumns =
				@JoinColumn(name = "customer_order_id_fk", referencedColumnName = "id"),
			inverseJoinColumns = 
				@JoinColumn(name = "customer_id_fk", referencedColumnName = "id"))
	private Customer customer;
	
	@OneToMany(mappedBy = "customerOrder")
	private List<CustomerOrderProduct> orderProducts = 
			new ArrayList<>();
	
	public CustomerOrder(double amount, LocalDate dateCreated, int confirmationNumber) {
		super();
		this.amount = amount;
		this.dateCreated = dateCreated;
		this.confirmationNumber = confirmationNumber;
	}

	public void addCustomerOrderProduct(CustomerOrderProduct orderProduct) {
		orderProduct.setCustomerOrder(this);
		orderProducts.add(orderProduct);
	}
	
	
}

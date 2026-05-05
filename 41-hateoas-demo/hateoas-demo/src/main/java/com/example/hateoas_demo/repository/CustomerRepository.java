package com.example.hateoas_demo.repository;

import com.example.hateoas_demo.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
}

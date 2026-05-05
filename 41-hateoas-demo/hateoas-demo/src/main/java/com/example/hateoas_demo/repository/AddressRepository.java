package com.example.hateoas_demo.repository;

import com.example.hateoas_demo.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address,Long> {
}

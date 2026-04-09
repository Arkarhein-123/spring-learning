package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.CustomerOrderProduct;
import com.example.demo.entity.OrderProductId;

public interface CustomerOrderProductDao extends JpaRepository<CustomerOrderProduct, OrderProductId>{

}

package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Supplier;

public interface SupplierDao extends JpaRepository<Supplier, Long>{

}

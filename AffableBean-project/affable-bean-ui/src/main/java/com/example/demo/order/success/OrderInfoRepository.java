package com.example.demo.order.success;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderInfoRepository extends JpaRepository<OrderInfo, Long>{
	Optional<OrderInfo> findByOrderId(String orderId);
}

package com.nus.arkar.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nus.arkar.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
	Optional<UserEntity> findByUserName(String userName);
}

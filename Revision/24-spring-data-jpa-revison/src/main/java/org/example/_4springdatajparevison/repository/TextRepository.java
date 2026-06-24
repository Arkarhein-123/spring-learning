package org.example._4springdatajparevison.repository;

import org.example._4springdatajparevison.entity.Text;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface TextRepository extends JpaRepository<Text,Long> {
}

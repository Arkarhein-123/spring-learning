package org.example._4springdatajparevison.repository;

import org.example._4springdatajparevison.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author,Long> {

}

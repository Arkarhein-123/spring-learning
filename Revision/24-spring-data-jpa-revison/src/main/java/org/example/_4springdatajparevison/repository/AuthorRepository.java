package org.example._4springdatajparevison.repository;

import jakarta.transaction.Transactional;
import org.example._4springdatajparevison.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    List<Author> findAllByFirstNameContainingIgnoreCase(String fn);

    List<Author> findAllByFirstNameStartingWithIgnoreCase(String fn);

    List<Author> findByIdContaining(Long id);

    @Modifying
    @Transactional
    @Query("""
                update Author a set a.firstName = :firstname where a.id = :id
            """)
    int updateFirstName(@Param("id") Long id, @Param("firstname") String firstName);


    @Modifying
    @Transactional
    @Query("""
                update Author a set a.lastName = :lastname where a.id = :id
            """)
    int updateLastName(@Param("id") Long id, @Param("lastname") String lastName);


    // Named Query
    List<Author>  findByFirstName(@Param("firstName")String firstName);
}

package org.example._4springdatajparevison.repository;

import org.example._4springdatajparevison.entity2.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher,Long> {
}

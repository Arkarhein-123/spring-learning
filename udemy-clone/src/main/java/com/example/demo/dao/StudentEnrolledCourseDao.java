package com.example.demo.dao;

import com.example.demo.dto.CourseDto;
import com.example.demo.entity.StudentEnrollCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StudentEnrolledCourseDao extends JpaRepository<StudentEnrollCourse, Long> {
    //AlreadyEnrolledCourse
    @Query("""
        select sc from StudentEnrollCourse sc 
        where sc.student.username = :username and sc.course.id = :id
    """)
    Optional<StudentEnrollCourse> testAlreadyEnrolledCourse(@Param("username")String username,@Param("id")long id);

    @Query("""
        SELECT new com.example.demo.dto.CourseDto(
            c.id,
            c.title,
            c.description,
            c.fees,
            cat.categoryName,
            t.username,
            c.image
        ) 
        FROM StudentEnrollCourse sc 
        JOIN sc.student s 
        JOIN sc.course c 
        JOIN c.category cat
        JOIN c.teacher t 
        WHERE s.username = :username
    """)
    List<CourseDto> findCoursesByLoggedInUserName(String username);
}

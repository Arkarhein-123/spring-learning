package org.example.simplerestapi.doa;

import org.example.simplerestapi.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeDao extends JpaRepository<Employee,Long> {
}

package org.example.simplerestapi.util;

import org.example.simplerestapi.dto.EmployeeDto;
import org.example.simplerestapi.entity.Employee;
import org.springframework.beans.BeanUtils;

public class EmployeeUtil {

    public static EmployeeDto toEmployeeDto(Employee employee) {
        EmployeeDto employeeDto=new EmployeeDto();
        BeanUtils.copyProperties(employee,employeeDto);
        return employeeDto;
    }

    public static Employee toEmployee(EmployeeDto employeeDto) {
        Employee employee=new Employee();
        BeanUtils.copyProperties(employeeDto,employee);
        return employee;
    }
}

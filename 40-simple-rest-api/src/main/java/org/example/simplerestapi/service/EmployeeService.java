package org.example.simplerestapi.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.simplerestapi.doa.EmployeeDao;
import org.example.simplerestapi.dto.EmployeeDto;
import org.example.simplerestapi.entity.Employee;
import org.example.simplerestapi.util.EmployeeUtil;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeDao employeeDao;
    @Transactional
    public EmployeeDto changeEmailEmployee(long id,String email){
        Employee employee = employeeDao.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        employee.setEmail(email);
        return EmployeeUtil.toEmployeeDto(employee);
    }

    public List<EmployeeDto> listEmployee(){
        return employeeDao
                .findAll()
                .stream()
                .map(EmployeeUtil::toEmployeeDto)
                .toList();
    }

    public EmployeeDto updateEmployee(long id,EmployeeDto employeeDto){
        if(employeeDao.existsById(id)){
            employeeDto.setId(id);
            Employee employee =employeeDao
                    .save(EmployeeUtil.toEmployee(employeeDto));
            return EmployeeUtil.toEmployeeDto(employee);
        }
        throw new RuntimeException("Employee not found");
    }

    public void deleteEmployee(Long id){
        employeeDao.deleteById(id);
    }
    public EmployeeDto createEmployee(EmployeeDto employeeDto){
        Employee employee=EmployeeUtil.toEmployee(employeeDto);
        employee=employeeDao.save(employee);
        return EmployeeUtil.toEmployeeDto(employee);
    }
    public EmployeeDto getEmployeeById(Long id){
        return EmployeeUtil
                .toEmployeeDto(
                        employeeDao
                        .findById(id)
                        .orElseThrow()
                );
    }
}

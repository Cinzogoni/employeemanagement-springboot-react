package com.javaguides.emsbackend.service;

import com.javaguides.emsbackend.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    EmployeeDto createEmployee(EmployeeDto employDto);
    EmployeeDto getEmployeeById(Long employeeId);
    List<EmployeeDto> getAllEmployees();
    EmployeeDto updateEmployee(long employeeId, EmployeeDto updateEmployee);
    void deleteEmployee(long employeeId);

}

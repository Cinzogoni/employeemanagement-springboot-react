package com.javaguides.emsbackend.mapper;

import com.javaguides.emsbackend.dto.EmployeeDto;
import com.javaguides.emsbackend.entity.Employee;

public class EmployeeMapper {
    public static EmployeeDto mapToEmployeeDto (Employee employee){
        return new EmployeeDto(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastname(),
                employee.getEmail()
        );
    }

    public static Employee maptoEmployee (EmployeeDto employDto){
        return new Employee(
                employDto.getId(),
                employDto.getFirstName(),
                employDto.getLastName(),
                employDto.getEmail()
        );
    }
}

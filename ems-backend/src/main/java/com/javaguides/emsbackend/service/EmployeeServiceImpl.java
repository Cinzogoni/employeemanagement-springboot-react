package com.javaguides.emsbackend.service;

import com.javaguides.emsbackend.dto.EmployeeDto;
import com.javaguides.emsbackend.entity.DeleteEmployeeHistory;
import com.javaguides.emsbackend.entity.Employee;
import com.javaguides.emsbackend.exception.ResourceNotFoundException;
import com.javaguides.emsbackend.mapper.EmployeeMapper;
import com.javaguides.emsbackend.repository.EmployeeRepository;
import com.javaguides.emsbackend.repository.histories.DeleteEmployeeHistoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DeleteEmployeeHistoryRepository deleteEmployeeHistoryRepository;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employDto) {
        Employee employee = EmployeeMapper.maptoEmployee(employDto);
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(()->
                        new ResourceNotFoundException("Employee not found with the given ID: " + employeeId));
        return EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream()
                .map(EmployeeMapper::mapToEmployeeDto)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(long employeeId, EmployeeDto updateEmployee) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with the given ID: " + employeeId));
        employee.setFirstName(updateEmployee.getFirstName());
        employee.setLastname(updateEmployee.getLastName());
        employee.setEmail(updateEmployee.getEmail());
        Employee updateEmployeeObj = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(updateEmployeeObj);
    }

    @Override
    public void deleteEmployee(long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with the given ID: " + employeeId));
        DeleteEmployeeHistory history = new DeleteEmployeeHistory(employee);
        deleteEmployeeHistoryRepository.save(history);
        employeeRepository.deleteById(employeeId);
    }
}

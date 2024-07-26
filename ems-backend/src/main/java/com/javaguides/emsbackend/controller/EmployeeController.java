package com.javaguides.emsbackend.controller;

import com.javaguides.emsbackend.dto.EmployeeDto;
import com.javaguides.emsbackend.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    EmployeeService employeeService;

    //Build Add Employee REST API
    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee (@RequestBody EmployeeDto employDto) {
        EmployeeDto savedEmployee = employeeService.createEmployee(employDto);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    //Build GetById Employee REST API
    @GetMapping("{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById (@PathVariable("id") Long employeeId) {
        EmployeeDto employDto = employeeService.getEmployeeById(employeeId);
        return ResponseEntity.ok(employDto);
    }

    //Build GetAll Employee REST API
    @GetMapping
    public ResponseEntity <List<EmployeeDto>> getAllEmployees() {
        List<EmployeeDto> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees) ;
    }

    //Build Update Employee REST API
    @PutMapping("{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable(value = "id") long employeeId, @RequestBody EmployeeDto updateEmployee) {
        EmployeeDto employeeDto = employeeService.updateEmployee(employeeId, updateEmployee);
    return ResponseEntity.ok(employeeDto);
    }

    //Build Delete Employee REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee (@PathVariable("id") long employeeId){
        employeeService.deleteEmployee(employeeId);
        return ResponseEntity.ok("Employee deleted success!");
    }

}

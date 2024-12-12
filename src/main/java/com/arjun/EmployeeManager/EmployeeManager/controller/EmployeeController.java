package com.arjun.EmployeeManager.EmployeeManager.controller;

import com.arjun.EmployeeManager.EmployeeManager.dto.Response;
import com.arjun.EmployeeManager.EmployeeManager.entity.Employee;
import com.arjun.EmployeeManager.EmployeeManager.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<Response<Employee>> addEmployee(@Valid @RequestBody Employee employee) {
        return new ResponseEntity<>(employeeService.addEmployee(employee), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<Employee>> getEmployeeById(@PathVariable UUID code) {
        return ResponseEntity.ok(employeeService.getEmployeeById(code));
    }

    @GetMapping
    public ResponseEntity<Response<List<Employee>>> getEmployees(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(employeeService.getAllEmployees(page, size));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<Employee>> updateEmployee(
            @PathVariable UUID code, @Valid @RequestBody Employee employee) {
        return ResponseEntity.ok(employeeService.updateEmployee(code, employee));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Void>> deleteEmployee(@PathVariable UUID code) {
        return ResponseEntity.ok(employeeService.deleteEmployee(code));
    }
}

package com.arjun.EmployeeManager.EmployeeManager.controller;

import com.arjun.EmployeeManager.EmployeeManager.dto.Response;
import com.arjun.EmployeeManager.EmployeeManager.entity.Department;
import com.arjun.EmployeeManager.EmployeeManager.service.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<Response<Department>> addDepartment(@Valid @RequestBody Department department) {
        return new ResponseEntity<>(departmentService.addDepartment(department), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<Department>> getDepartmentById(@PathVariable UUID code) {
        return ResponseEntity.ok(departmentService.getDepartmentById(code));
    }

    @GetMapping
    public ResponseEntity<Response<List<Department>>> getDepartments(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(departmentService.getDepartments(page, size));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<Department>> updateDepartment(
            @PathVariable UUID code, @Valid @RequestBody Department department) {
        return ResponseEntity.ok(departmentService.updateDepartment(code, department));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Void>> deleteDepartment(@PathVariable UUID code) {
        return ResponseEntity.ok(departmentService.deleteDepartment(code));
    }
}

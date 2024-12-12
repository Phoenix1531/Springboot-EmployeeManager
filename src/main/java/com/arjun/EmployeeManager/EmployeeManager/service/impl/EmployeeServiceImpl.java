package com.arjun.EmployeeManager.EmployeeManager.service.impl;

import com.arjun.EmployeeManager.EmployeeManager.dto.Response;
import com.arjun.EmployeeManager.EmployeeManager.entity.Employee;
import com.arjun.EmployeeManager.EmployeeManager.repo.EmployeeRepo;
import com.arjun.EmployeeManager.EmployeeManager.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepository;

    @Override
    public Response<Employee> addEmployee(Employee employee) {
        Employee savedEmployee = employeeRepository.save(employee);
        return new Response<>(true, "Employee added successfully", savedEmployee);
    }

    @Override
    public Response<List<Employee>> getAllEmployees(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Employee> employeePage = employeeRepository.findAll(pageable);
        List<Employee> employees = employeePage.getContent();
        if (employees.isEmpty()) {
            return new Response<>(false, "No employees found", null);
        }
        return new Response<>(true, "Employees fetched successfully", employees);
    }

    @Override
    public Response<Employee> getEmployeeById(UUID code) {
        Employee employee = employeeRepository.findById(code)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found"));
        return new Response<>(true, "Employee fetched successfully", employee);
    }

    @Override
    public Response<Employee> updateEmployee(UUID code, Employee employee) {
        if (!employeeRepository.existsById(code)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found");
        }
        employee.setCode(code);
        Employee updatedEmployee = employeeRepository.save(employee);
        return new Response<>(true, "Employee updated successfully", updatedEmployee);
    }

    @Override
    public Response<Void> deleteEmployee(UUID code) {
        if (!employeeRepository.existsById(code)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found");
        }
        employeeRepository.deleteById(code);
        return new Response<>(true, "Employee deleted successfully", null);
    }
}


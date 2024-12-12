package com.arjun.EmployeeManager.EmployeeManager.service;

import com.arjun.EmployeeManager.EmployeeManager.dto.Response;
import com.arjun.EmployeeManager.EmployeeManager.entity.Employee;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface EmployeeService {
    Response<Employee> addEmployee(Employee employee);
    Response<List<Employee>> getAllEmployees(int page, int size);
    Response<Employee> getEmployeeById(UUID code);
    Response<Employee> updateEmployee(UUID code, Employee employee);
    Response<Void> deleteEmployee(UUID code);
}


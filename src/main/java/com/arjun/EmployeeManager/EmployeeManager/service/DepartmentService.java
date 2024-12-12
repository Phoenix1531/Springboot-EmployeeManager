package com.arjun.EmployeeManager.EmployeeManager.service;

import com.arjun.EmployeeManager.EmployeeManager.dto.Response;
import com.arjun.EmployeeManager.EmployeeManager.entity.Department;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface DepartmentService {

    Response<Department> addDepartment(Department department);
    Response<Department> getDepartmentById(UUID code);
    Response<List<Department>> getDepartments(int page, int size);
    Response<Department> updateDepartment(UUID code, Department department);
    Response<Void> deleteDepartment(UUID code);
}

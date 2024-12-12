package com.arjun.EmployeeManager.EmployeeManager.service.impl;

import com.arjun.EmployeeManager.EmployeeManager.dto.Response;
import com.arjun.EmployeeManager.EmployeeManager.entity.Department;
import com.arjun.EmployeeManager.EmployeeManager.repo.DepartmentRepo;
import com.arjun.EmployeeManager.EmployeeManager.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepo departmentRepo;

    @Override
    public Response<Department> addDepartment(Department department) {
        try {
            Department savedDepartment = departmentRepo.save(department);
            return new Response<>(true, "Department added successfully", savedDepartment);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Failed to add department");
        }
    }

    @Override
    public Response<Department> getDepartmentById(UUID code) {
        Optional<Department> department = departmentRepo.findById(code);
        if (department.isPresent()) {
            return new Response<>(true, "Department fetched successfully", department.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Department not found");
        }
    }

    @Override
    public Response<List<Department>> getDepartments(int page, int size) {
        Page<Department> departmentPage = departmentRepo.findAll(PageRequest.of(page, size));
        List<Department> departments = departmentPage.getContent();
        return new Response<>(true, "Paginated departments fetched successfully", departments);
    }

    @Override
    public Response<Department> updateDepartment(UUID code, Department department) {
        if (departmentRepo.existsById(code)) {
            department.setCode(code);
            Department updatedDepartment = departmentRepo.save(department);
            return new Response<>(true, "Department updated successfully", updatedDepartment);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Department not found");
        }
    }

    @Override
    public Response<Void> deleteDepartment(UUID code) {
        if (departmentRepo.existsById(code)) {
            departmentRepo.deleteById(code);
            return new Response<>(true, "Department deleted successfully", null);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Department not found");
        }
    }
}

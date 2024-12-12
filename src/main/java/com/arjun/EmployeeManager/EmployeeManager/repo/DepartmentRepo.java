package com.arjun.EmployeeManager.EmployeeManager.repo;

import com.arjun.EmployeeManager.EmployeeManager.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DepartmentRepo extends JpaRepository<Department, UUID> {

}
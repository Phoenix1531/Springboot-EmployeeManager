package com.arjun.EmployeeManager.EmployeeManager.repo;

import com.arjun.EmployeeManager.EmployeeManager.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, UUID>{

}

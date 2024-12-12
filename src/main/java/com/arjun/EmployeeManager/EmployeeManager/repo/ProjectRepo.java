package com.arjun.EmployeeManager.EmployeeManager.repo;

import com.arjun.EmployeeManager.EmployeeManager.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProjectRepo extends JpaRepository<Project, UUID> {

}
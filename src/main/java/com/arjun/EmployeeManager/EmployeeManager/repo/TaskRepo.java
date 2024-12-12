package com.arjun.EmployeeManager.EmployeeManager.repo;

import com.arjun.EmployeeManager.EmployeeManager.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TaskRepo extends JpaRepository<Task, UUID> {

}

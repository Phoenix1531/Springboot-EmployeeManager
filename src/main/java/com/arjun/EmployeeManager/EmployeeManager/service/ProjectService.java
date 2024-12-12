package com.arjun.EmployeeManager.EmployeeManager.service;

import com.arjun.EmployeeManager.EmployeeManager.dto.Response;
import com.arjun.EmployeeManager.EmployeeManager.entity.Project;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface ProjectService {
    Response<Project> addProject(Project project);
    Response<Project> getProjectById(UUID code);
    Response<List<Project>> getAllProjects(int page, int size);
    Response<Project> updateProject(UUID code, Project project);
    Response<Void> deleteProject(UUID code);
}
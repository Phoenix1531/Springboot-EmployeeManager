package com.arjun.EmployeeManager.EmployeeManager.service.impl;

import com.arjun.EmployeeManager.EmployeeManager.dto.Response;
import com.arjun.EmployeeManager.EmployeeManager.entity.Employee;
import com.arjun.EmployeeManager.EmployeeManager.entity.Project;
import com.arjun.EmployeeManager.EmployeeManager.repo.ProjectRepo;
import com.arjun.EmployeeManager.EmployeeManager.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepo projectRepository;

    @Override
    public Response<Project> addProject(Project project) {
        try {
            Project savedProject = projectRepository.save(project);
            return new Response<>(true, "Project added successfully", savedProject);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Failed to add project");
        }
    }

    @Override
    public Response<Project> getProjectById(UUID code) {
        Optional<Project> project = projectRepository.findById(code);
        if (project.isPresent()) {
            return new Response<>(true, "Project fetched successfully", project.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Project not found");
        }
    }

    @Override
    public Response<List<Project>> getAllProjects(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Project> projectPage = projectRepository.findAll(pageable);
        List<Project> projects = projectPage.getContent();
        if (projects.isEmpty()) {
            return new Response<>(false, "No projects found", null);
        }
        return new Response<>(true, "Projects fetched successfully", projects);
    }

    @Override
    public Response<Project> updateProject(UUID code, Project project) {
        if (projectRepository.existsById(code)) {
            project.setCode(code);
            Project updatedProject = projectRepository.save(project);
            return new Response<>(true, "Project updated successfully", updatedProject);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Project not found");
        }
    }

    @Override
    public Response<Void> deleteProject(UUID code) {
        if (projectRepository.existsById(code)) {
            projectRepository.deleteById(code);
            return new Response<>(true, "Project deleted successfully", null);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Project not found");
        }
    }
}

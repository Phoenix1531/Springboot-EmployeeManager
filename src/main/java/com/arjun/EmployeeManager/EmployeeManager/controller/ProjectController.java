package com.arjun.EmployeeManager.EmployeeManager.controller;

import com.arjun.EmployeeManager.EmployeeManager.dto.Response;
import com.arjun.EmployeeManager.EmployeeManager.entity.Project;
import com.arjun.EmployeeManager.EmployeeManager.service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping
    public ResponseEntity<Response<Project>> addProject(@Valid @RequestBody Project project) {
        return new ResponseEntity<>(projectService.addProject(project), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<Project>> getProjectById(@PathVariable UUID code) {
        return ResponseEntity.ok(projectService.getProjectById(code));
    }

    @GetMapping
    public ResponseEntity<Response<List<Project>>> getProjects(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(projectService.getAllProjects(page, size));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<Project>> updateProject(
            @PathVariable UUID code, @Valid @RequestBody Project project) {
        return ResponseEntity.ok(projectService.updateProject(code, project));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Void>> deleteProject(@PathVariable UUID code) {
        return ResponseEntity.ok(projectService.deleteProject(code));
    }
}

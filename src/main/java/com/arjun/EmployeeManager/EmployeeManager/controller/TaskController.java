package com.arjun.EmployeeManager.EmployeeManager.controller;

import com.arjun.EmployeeManager.EmployeeManager.dto.Response;
import com.arjun.EmployeeManager.EmployeeManager.entity.Task;
import com.arjun.EmployeeManager.EmployeeManager.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping
    public ResponseEntity<Response<Task>> addTask(@Valid @RequestBody Task task) {
        return new ResponseEntity<>(taskService.addTask(task), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<Task>> getTaskById(@PathVariable UUID code) {
        return ResponseEntity.ok(taskService.getTaskById(code));
    }

    @GetMapping
    public ResponseEntity<Response<List<Task>>> getTasks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(taskService.getAllTasks(page, size));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<Task>> updateTask(
            @PathVariable UUID code, @Valid @RequestBody Task task) {
        return ResponseEntity.ok(taskService.updateTask(code, task));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Void>> deleteTask(@PathVariable UUID code) {
        return ResponseEntity.ok(taskService.deleteTask(code));
    }
}

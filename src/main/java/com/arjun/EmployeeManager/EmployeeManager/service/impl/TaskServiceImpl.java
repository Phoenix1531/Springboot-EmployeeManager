package com.arjun.EmployeeManager.EmployeeManager.service.impl;

import com.arjun.EmployeeManager.EmployeeManager.dto.Response;
import com.arjun.EmployeeManager.EmployeeManager.entity.Project;
import com.arjun.EmployeeManager.EmployeeManager.entity.Task;
import com.arjun.EmployeeManager.EmployeeManager.repo.TaskRepo;
import com.arjun.EmployeeManager.EmployeeManager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepo taskRepository;

    @Override
    public Response<Task> addTask(Task task) {
        try {
            Task savedTask = taskRepository.save(task);
            return new Response<>(true, "Task added successfully", savedTask);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Failed to add task");
        }
    }

    @Override
    public Response<Task> getTaskById(UUID code) {
        Optional<Task> task = taskRepository.findById(code);
        if (task.isPresent()) {
            return new Response<>(true, "Task fetched successfully", task.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found");
        }
    }

   @Override
    public Response<List<Task>> getAllTasks(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Task> taskPage = taskRepository.findAll(pageable);
        List<Task> tasks = taskPage.getContent();
        if (tasks.isEmpty()) {
            return new Response<>(false, "No tasks found", null);
        }
        return new Response<>(true, "Tasks fetched successfully", tasks);
    }

    @Override
    public Response<Task> updateTask(UUID code, Task task) {
        if (taskRepository.existsById(code)) {
            task.setCode(code);
            Task updatedTask = taskRepository.save(task);
            return new Response<>(true, "Task updated successfully", updatedTask);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found");
        }
    }

    @Override
    public Response<Void> deleteTask(UUID code) {
        if (taskRepository.existsById(code)) {
            taskRepository.deleteById(code);
            return new Response<>(true, "Task deleted successfully", null);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found");
        }
    }
}

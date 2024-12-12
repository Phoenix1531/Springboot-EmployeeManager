package com.arjun.EmployeeManager.EmployeeManager.service;

import com.arjun.EmployeeManager.EmployeeManager.dto.Response;
import com.arjun.EmployeeManager.EmployeeManager.entity.Task;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface TaskService {
    Response<Task> addTask(Task task);

    Response<Task> getTaskById(UUID code);

    Response<List<Task>> getAllTasks(int page, int size);

    Response<Task> updateTask(UUID code, Task task);

    Response<Void> deleteTask(UUID code);
}
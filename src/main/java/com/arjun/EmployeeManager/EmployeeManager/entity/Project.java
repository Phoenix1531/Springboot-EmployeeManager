package com.arjun.EmployeeManager.EmployeeManager.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Project {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "code")
    private UUID code;

    @NotNull(message = "Project name cannot be null")
    private String projectName;
    @NotNull(message = "Client name cannot be null")
    private String client;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<Employee> employees;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JsonManagedReference
    private List<Task> tasks;

}

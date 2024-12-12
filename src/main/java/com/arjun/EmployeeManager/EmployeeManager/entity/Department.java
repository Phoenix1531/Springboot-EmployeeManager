package com.arjun.EmployeeManager.EmployeeManager.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Department {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "code")
    private UUID code;

    @NotNull(message = "Department name cannot be null")
    @Size(min = 2, max = 50, message = "Department name must be between 2 and 50 characters")
    private String name;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Employee> employees;
}

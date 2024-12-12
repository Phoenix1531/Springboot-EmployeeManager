package com.arjun.EmployeeManager.EmployeeManager.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import lombok.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Employee {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "code", updatable = false, nullable = false, unique = true)
    private UUID code;

    @NotNull(message = "Name cannot be null")
    @Size(min = 2, max = 50, message = "name must be between 2 and 50 characters")
    private String name;

    @Email(message = "Invalid email format")
    @NotNull(message = "Email cannot be null")
    private String email;

    @NotNull(message = "Position cannot be null")
    private String position;

    @NotNull(message = "Salary must be specified")
    @Min(value = 0, message = "Salary must be a positive number")
    private Double salary;

    @Past(message = "Birthdate must be in the past")
    private LocalDate birthdate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "code")
//    @JsonIgnore
    private Address address;

    @ManyToOne
    @JoinColumn(name = "department_id")
    @JsonIgnore
    private Department department;

    @ManyToOne
    @JoinColumn(name = "project_id")
    @JsonIgnore
    private Project project;
}


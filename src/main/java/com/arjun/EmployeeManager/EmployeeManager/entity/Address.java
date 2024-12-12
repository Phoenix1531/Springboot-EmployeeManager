package com.arjun.EmployeeManager.EmployeeManager.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Address {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "code")
    private UUID code;

    @NotNull(message = "Street cannot be null")
    @Size(min = 5, max = 100)
    private String street;

    @NotNull(message = "City cannot be null")
    @Size(min = 2, max = 50, message = "City name must be between 2 and 50 characters")
    private String city;

    @NotNull
    @Size(min = 2, max = 50)
    private String state;

    @NotNull
    @Pattern(regexp = "\\d{6}", message = "Zip code must be 6 digits")
    private String zipCode;

    @OneToOne(mappedBy = "address")
    @JsonIgnore
    private Employee employee;
}
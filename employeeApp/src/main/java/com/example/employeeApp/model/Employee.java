package com.example.employeeApp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Please enter a name")
    private String name;

    @Email(message = "Please enter a valid email")
    private String email;

    @NotBlank(message = "Please state your department")
    private String department;

    @NotBlank(message = "please enter the position")
    private String position;

    @Min(value = 0, message = "Salary must be positive")
    private Double salary;

    @PastOrPresent(message = "Joining date cannot be in the future")
    private LocalDate joiningDate;

}

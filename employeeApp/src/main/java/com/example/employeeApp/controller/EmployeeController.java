package com.example.employeeApp.controller;

import com.example.employeeApp.model.Employee;
import com.example.employeeApp.service.EmployeeService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    //add a new employee
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Employee> createEmployee(@Valid @RequestBody Employee employee) {
        Employee newEmployee = employeeService.addEmployee(employee);
        return new ResponseEntity<Employee>(newEmployee, HttpStatus.CREATED);
    }

    //get all employees in pages
    @GetMapping
    public ResponseEntity<Page<Employee>> getAllEmployees(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        return new ResponseEntity<Page<Employee>>(employeeService.getAllEmployees(page, size), HttpStatus.OK);
    }

    //get employee by id
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        Employee employee = employeeService.getEmployeeById(id).orElseThrow(() -> new RuntimeException("Cannot find employee of id: " + id));
        return new ResponseEntity<Employee>(employee, HttpStatus.OK);
    }

    //Update employee
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployeeById(@PathVariable Long id, @Valid @RequestBody Employee employee) {
        return new ResponseEntity<Employee>(employeeService.updateEmployee(id, employee), HttpStatus.OK);
    }

    //delete employee
    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        if(employeeService.deleteEmployeeById(id)) {
            employeeService.deleteEmployeeById(id);
            return "Employee deleted successfully";
        }
        else {
            throw new RuntimeException("Cannot find employee of id: " + id);
        }
    }
}

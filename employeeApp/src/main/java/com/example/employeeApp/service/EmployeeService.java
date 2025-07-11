package com.example.employeeApp.service;

import com.example.employeeApp.model.Employee;
import com.example.employeeApp.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    //add a new employee
    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    //get all employees with pagination
    public Page<Employee> getAllEmployees(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return employeeRepository.findAll(pageable);
    }

    //get employee by id
    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    //Update employee
    public Employee updateEmployee(Long id, Employee employee) {
        Employee emp = employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));
        emp.setName(employee.getName());
        emp.setDepartment(employee.getDepartment());
        emp.setEmail(employee.getEmail());
        emp.setPosition(employee.getPosition());
        emp.setSalary(employee.getSalary());
        emp.setJoiningDate(employee.getJoiningDate());

        return emp;
    }

    //delete employee
    public boolean deleteEmployeeById(Long id) {
        if(employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
            return true;
        }
        else {
            return false;
        }
    }
}

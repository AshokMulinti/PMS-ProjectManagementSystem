package com.ashok.service;

import com.ashok.entity.Employee;
import com.ashok.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    // Get all employees
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // Get employee by ID
    public Employee getEmployee(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    // Save or update employee
    @Transactional(rollbackForClassName = {"java.lang.Exception"})
    public void saveEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    // Delete employee by ID
    @Transactional(rollbackForClassName = {"java.lang.Exception"})
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
    @Transactional(rollbackForClassName = {"java.lang.Exception"})
    public Employee updateEmployee(Employee employee){
        return employeeRepository.save(employee);
    }
}
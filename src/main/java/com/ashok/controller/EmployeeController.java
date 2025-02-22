package com.ashok.controller;

import com.ashok.entity.Employee;
import com.ashok.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/getAllEmployees")
    public String getAllEmployees(Model model) {
        List<Employee> employees = employeeService.getAllEmployees();
        model.addAttribute("employees", employees);
        return "employees";
    }

    @GetMapping("/addEmployee")
    public String addEmployee(Model model) {
        model.addAttribute("employee", new Employee());
        return "add_employee";
    }

    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {
        employeeService.saveEmployee(employee);
        return "redirect:/getAllEmployees";
    }

    @GetMapping("/editEmployee/{id}")
    public String showEditEmployeeForm(@PathVariable("id") Long id, Model model) {
        Employee employee = employeeService.getEmployee(id);
        model.addAttribute("employee", employee); // Fixed: Added explicit attribute name
        return "update_employee";
    }

    @PostMapping("/updateEmployee")
    public String updateEmployee(@ModelAttribute("employee") Employee employee) {
        employeeService.updateEmployee(employee);
        return "redirect:/getAllEmployees";
    }

    @GetMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable("id") Long id) {
        employeeService.deleteEmployee(id);
        return "redirect:/getAllEmployees";
    }
}
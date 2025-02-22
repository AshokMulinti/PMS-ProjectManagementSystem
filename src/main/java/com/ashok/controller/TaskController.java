package com.ashok.controller;

import com.ashok.entity.Task;
import com.ashok.service.EmployeeService;
import com.ashok.service.ProjectService;
import com.ashok.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TaskController { // Changed the name from TasController to TaskController

    @Autowired
    private TaskService taskService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ProjectService projectService;

    @GetMapping("/getAllTasks")
    public String getAllTasks(Model model) {
        System.out.println("add task is working");
        List<Task> tasks = taskService.getAllTasks();
        model.addAttribute("tasks", tasks);
        return "tasks"; // tasks.html
    }

    @GetMapping("/addTask")
    public String addTask(Model model) {
        model.addAttribute("task", new Task());
        model.addAttribute("employees", employeeService.getAllEmployees());
        model.addAttribute("projects", projectService.getAllProjects());
        return "add_task"; // add_task.html
    }

    @PostMapping("/saveTask")
    public String saveTask(@ModelAttribute("task") Task task) {
        taskService.saveTask(task);
        return "redirect:/getAllTasks";
    }

    @GetMapping("/editTask/{id}")
    public String editTask(@PathVariable("id") Long id, Model model) {
        Task task = taskService.getTaskById(id);
        model.addAttribute("task", task);
        model.addAttribute("employees", employeeService.getAllEmployees());
        model.addAttribute("projects", projectService.getAllProjects());
        return "update_task"; // update_task.html
    }

    @PostMapping("/updateTask")
    public String updateTask(@ModelAttribute("task") Task task) {
        taskService.updateTask(task);
        return "redirect:/getAllTasks";
    }

    @GetMapping("/deleteTask/{id}")
    public String deleteTask(@PathVariable("id") Long id) {
        taskService.deleteTask(id);
        return "redirect:/getAllTasks";
    }
}

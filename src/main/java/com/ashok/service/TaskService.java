package com.ashok.service;

import com.ashok.entity.Employee;
import com.ashok.entity.Project;
import com.ashok.entity.Task;
import com.ashok.repository.EmployeeRepository;
import com.ashok.repository.ProjectRepository;
import com.ashok.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ProjectRepository projectRepository;

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task Not Found"));
    }

    public void saveTask(Task task) {
        // Optional: Validate employee and project existence
        validateTaskAssociations(task);
        taskRepository.save(task);
    }

    public void updateTask(Task task) {
        // Optional: Validate employee and project existence
        validateTaskAssociations(task);
        taskRepository.save(task); // JPA will update if ID exists
    }

    public void deleteTask(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new RuntimeException("Task Not Found");
        }
        taskRepository.deleteById(id);
    }

    public List<Task> getTasksByEmployee(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee Not Found"));
        return taskRepository.findByEmployee(employee);
    }

    public List<Task> getTasksByProject(Long projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project Not Found"));
        return taskRepository.findByProject(project);
    }

    private void validateTaskAssociations(Task task) {
        if (task.getEmployee() == null || !employeeRepository.existsById(task.getEmployee().getId())) {
            throw new RuntimeException("Invalid or non-existent Employee");
        }
        if (task.getProject() == null || !projectRepository.existsById(task.getProject().getId())) {
            throw new RuntimeException("Invalid or non-existent Project");
        }
    }
}
package com.ashok.repository;

import com.ashok.entity.Employee;
import com.ashok.entity.Project;
import com.ashok.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByEmployee(Employee employee);
    List<Task> findByProject(Project project);
}

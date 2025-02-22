package com.ashok.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Project {
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "project_employee",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id")
    )
    private List<Employee> employees = new ArrayList<>();

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL) // Added mappedBy
    private List<Task> tasks = new ArrayList<>();
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Project(String name) {
        this.name = name;
    }

    public Project() {
        super();
    }

}

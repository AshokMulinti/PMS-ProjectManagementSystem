package com.ashok.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String status;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employee") // Added mappedBy
    private List<Task> tasks = new ArrayList<>();

    @ManyToMany(mappedBy = "employees") // Added mappedBy to make it bidirectional
    private List<Project> projects = new ArrayList<>();
    public Employee() {
        super();
    }

    public Employee(String name) {
        this.name = name;
    }
    public Long getId() { return id; }
    public String getName() { return name; }
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}

package com.ashok.controller;

import com.ashok.entity.Project;
import com.ashok.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @GetMapping(value = {"/", "/home"})
    public String home() {
        return "home";
    }

    @GetMapping("/getAllProjects")
    public String getAllProjects(Model model) {
        List<Project> projects = projectService.getAllProjects();
        model.addAttribute("projects", projects);
        return "projects";
    }

    @GetMapping("/addProject")
    public String addProject(Model model) {
        model.addAttribute("project", new Project());
        return "add_project";
    }

    @PostMapping("/saveProject")
    public String saveProject(@ModelAttribute("project") Project project) {
        projectService.saveProject(project);
        return "redirect:/getAllProjects";
    }

    @PostMapping("/updateProject")
    public String updateProject(@ModelAttribute("project") Project project) {
        projectService.updateProject(project);
        return "redirect:/getAllProjects";
    }

    @GetMapping("/deleteProject/{id}")
    public String deleteProject(@PathVariable("id") Long id) {
        projectService.deleteProject(id);
        return "redirect:/getAllProjects";
    }

    @GetMapping("/editProject/{id}")
    public String editProject(@PathVariable("id") Long id, Model model) {
        Project project = projectService.getProject(id);
        model.addAttribute("project", project); // Fixed: Added explicit attribute name
        return "update_project";
    }
}
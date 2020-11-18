package io.hexlet.hexletproject4.controller;

import io.hexlet.hexletproject4.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    TaskRepository repository;

    @GetMapping
    public String getTasks(Model model, Principal principal) {
        model.addAttribute("tasks", repository.findAll());
        return "/task/list";
    }
}

package io.hexlet.hexletproject4.controller;

import io.hexlet.hexletproject4.domain.Task;
import io.hexlet.hexletproject4.domain.User;
import io.hexlet.hexletproject4.repository.StatusRepository;
import io.hexlet.hexletproject4.repository.TaskRepository;
import io.hexlet.hexletproject4.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.Optional;

import static java.lang.String.format;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    TaskRepository taskRepository;
    @Autowired
    StatusRepository statusRepository;
    @Autowired
    UserRepository userRepository;

    @GetMapping
    public String getTasks(Model model) {
        model.addAttribute("tasks", taskRepository.findAll());
        return "/task/list";
    }

    @GetMapping(value = "/new")
    public String getTaskCreationForm(Model model) {
        model.addAttribute("task", new Task());
        model.addAttribute("statuses", statusRepository.findAll());
        return "/task/new";
    }

    @PostMapping
    public String createTask(@ModelAttribute Task task, Model model, Principal principal) {
        Optional<User> userFound = userRepository.findByEmail(principal.getName());
        userFound.ifPresentOrElse(
                user -> {
                    task.setUser(user);
                    taskRepository.save(task);
                },
                () -> model.addAttribute("error", format("User with name=%s not found", principal.getName()))
        );

        return "redirect:/tasks";
    }

    @DeleteMapping("/{id}")
    public String deleteTask(@PathVariable Integer id) {
        taskRepository.deleteById(id);
        return "redirect:/tasks";
    }
}

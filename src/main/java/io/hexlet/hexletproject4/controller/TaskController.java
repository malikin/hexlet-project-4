package io.hexlet.hexletproject4.controller;

import io.hexlet.hexletproject4.domain.Task;
import io.hexlet.hexletproject4.domain.User;
import io.hexlet.hexletproject4.repository.StatusRepository;
import io.hexlet.hexletproject4.repository.TaskRepository;
import io.hexlet.hexletproject4.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

import static java.lang.String.format;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    private final TaskRepository taskRepository;
    private final StatusRepository statusRepository;
    private final UserRepository userRepository;

    @Autowired
    public TaskController(TaskRepository taskRepository, StatusRepository statusRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.statusRepository = statusRepository;
        this.userRepository = userRepository;
    }

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

    @GetMapping(value = "/{id}/edit")
    public String getTaskEditForm(@PathVariable Integer id, Model model) {
        model.addAttribute("statuses", statusRepository.findAll());
        taskRepository.findById(id).ifPresentOrElse(
                task -> model.addAttribute("task", task),
                () -> model.addAttribute("error", format("Task with id=%s not found", id))
        );

        return "/task/edit";
    }

    @PutMapping(value = "/{id}")
    public String updateTask(@ModelAttribute Task task, Model model, Principal principal) {
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

package io.hexlet.hexletproject4.controller;

import io.hexlet.hexletproject4.domain.Status;
import io.hexlet.hexletproject4.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/statuses")
public class StatusController {

    @Autowired
    StatusRepository repository;

    @GetMapping
    public String getTasks(Model model, Principal principal) {
        model.addAttribute("statuses", repository.findAll());
        return "/status/list";
    }

    @GetMapping(value = "/new")
    public String getTaskCreationForm(Model model) {
        model.addAttribute("status", new Status());
        return "/status/new";
    }

    @PostMapping
    public String createTask(@ModelAttribute Status status,
                             Model model) {
        repository.save(status);
        return "redirect:/statuses";
    }

    @PostMapping("/{id}")
    public String deleteStatus(@PathVariable Integer id) {
        repository.deleteById(id);
        return "redirect:/statuses";
    }
}

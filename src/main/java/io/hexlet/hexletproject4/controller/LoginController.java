package io.hexlet.hexletproject4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Locale;

@Controller
@RequestMapping("/login")
public class LoginController {

    private final MessageSource messageSource;

    @Autowired
    public LoginController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ModelAttribute("module")
    String module(Model model, String error, Locale locale) {
        if (error != null) {
            String messageError = messageSource.getMessage("error.login", null, locale);
            model.addAttribute("error", messageError);
        }
        return "login";
    }

    @GetMapping
    public String loginPage() {
        return "login";
    }
}

package io.hexlet.hexletproject4.controller;

import io.hexlet.hexletproject4.controller.validator.UserRegistrationValidator;
import io.hexlet.hexletproject4.domain.User;
import io.hexlet.hexletproject4.domain.UserRegistration;
import io.hexlet.hexletproject4.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Locale;
import java.util.Optional;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    MessageSource messageSource;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;
    @Autowired
    UserRegistrationValidator userRegistrationValidator;

//    @ModelAttribute("module")
//    String module() {
//        return "registration";
//    }

    @GetMapping
    public String registration(UserRegistration userRegistration) {
        return "registration";
    }

    @PostMapping
    public String registration(@Validated @ModelAttribute("user") UserRegistration userRegistration,
                               BindingResult bindingResult,
                               Model model,
                               Locale locale) {
        userRegistrationValidator.validate(userRegistration, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        Optional<User> user = userRepository.findByEmail(userRegistration.getEmail());
        if (user.isPresent()) {
            String errorMessage = messageSource.getMessage("error.registration.user.exist", null, locale);
            model.addAttribute("error", errorMessage);
            return "registration";
        }

        userRepository.save(toUser(userRegistration));

        return "redirect:login";
    }

    private User toUser(UserRegistration userRegistration) {
        return User.builder()
                .name(userRegistration.getName())
                .email(userRegistration.getEmail())
                .password(passwordEncoder.encode(userRegistration.getPassword()))
                .build();
    }
}

package io.hexlet.hexletproject4.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserRegistration {
    private String name;

    private String password;

    private String passwordConfirm;

    private String email;
}

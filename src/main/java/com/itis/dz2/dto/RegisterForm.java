package com.itis.dz2.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterForm {

    private String name;

    private String email;

    @NotBlank(message = "Password is required")
    private String password;
}
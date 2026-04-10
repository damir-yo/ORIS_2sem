package com.itis.dz2.controller;

import com.itis.dz2.dto.RegisterForm;
import com.itis.dz2.entity.UserEntity;
import com.itis.dz2.entity.UserStatus;
import com.itis.dz2.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute RegisterForm form, BindingResult bindingResult) {
        System.out.println("username = " + form.getName());
        System.out.println("mail = " + form.getEmail());
        System.out.println("pass = " + form.getPassword());
        if (bindingResult.hasErrors()) {
            return "register";
        }

        UserEntity user = new UserEntity();

        user.setName(form.getName());
        user.setEmail(form.getEmail());
        user.setPassword(form.getPassword());
        user.setStatus(UserStatus.ACTIVE);

        userService.registerUser(user);

        return "redirect:/success";
    }

    @GetMapping("/success")
    public String successPage() {
        return "success";
    }
}
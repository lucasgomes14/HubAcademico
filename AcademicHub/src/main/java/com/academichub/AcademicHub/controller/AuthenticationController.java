package com.academichub.AcademicHub.controller;

import com.academichub.AcademicHub.model.Course;
import com.academichub.AcademicHub.model.UserType;
import com.academichub.AcademicHub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Boolean register(@RequestParam String name, @RequestParam String lastName,
                            @RequestParam LocalDateTime dateAndTimeOfUserCreation, @RequestParam String email,
                            @RequestParam String password, @RequestParam UserType userType, @RequestParam Course course) {

        return userService.cadasterUser(name, lastName, dateAndTimeOfUserCreation, email, password, userType, course);
    }

    @PostMapping("/login")
    public Boolean login(@RequestParam String email, @RequestParam String password) {
        return userService.loginUser(email, password);
    }
}

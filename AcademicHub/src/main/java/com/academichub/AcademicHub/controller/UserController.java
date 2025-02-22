package com.academichub.AcademicHub.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.academichub.AcademicHub.model.user.User;
import com.academichub.AcademicHub.repository.UserRepository;

@RestController
@RequestMapping("/usuarios")
public class UserController {
    
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public ResponseEntity<List<User>> buscarUsuarios(@RequestParam String nome) {
        List<User> usuarios = userRepository.findByUsernameContainingIgnoreCase(nome);
        return ResponseEntity.ok(usuarios);
    }
}

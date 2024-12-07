package com.academichub.AcademicHub.service;

import com.academichub.AcademicHub.dto.RegisterDTO;
import com.academichub.AcademicHub.exceptions.*;
import com.academichub.AcademicHub.model.user.Course;
import com.academichub.AcademicHub.model.user.User;
import com.academichub.AcademicHub.model.user.UserRole;
import com.academichub.AcademicHub.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User cadasterUser(RegisterDTO data) {
        validatesCadaster(data.name(), data.lastName(), data.email(), data.password(), data.username());

        User user = createUser(data);

        return userRepository.save(user);
    }

    public User userLogin(String email) {
        return userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
    }

    private User createUser(RegisterDTO data) {
        UserRole role = UserRole.valueOf(data.role());
        Course course = Course.valueOf(data.course());

        User user = new User();
        user.setName(data.name());
        user.setLastName(data.lastName());
        user.setUsername(data.username());
        user.setEmail(data.email());
        user.setPassword(passwordEncoder.encode(data.password()));
        user.setRole(role);
        user.setCourse(course);
        user.setDateAndTimeOfUserCreation(LocalDateTime.now());

        return user;
    }

    private void validatesCadaster(String name, String lastName, String email, String password, String username) {
        validateEmail(email);
        validateName(name);
        validateLastName(lastName);
        validatePassword(password);
        validateUsername(username);
    }

    private void validateEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new EmptyEmailException();
        }

        if (!email.endsWith("@academico.ifpb.edu.br")) {
            throw new WrongEmailDomain();
        }

        if (userRepository.findByEmail(email).isPresent()) {
            throw new ExistingEmailException();
        }
    }

    private void validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new EmptyNameException();
        }
    }

    private void validateLastName(String lastName) {
        if (lastName == null || lastName.isBlank()) {
            throw new EmptyLastNameException();
        }
    }

    private void validatePassword(String password) {
        if (password == null || password.length() < 8) {
            throw new SecurityPasswordException();
        }

        if (!password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$")) {
            throw new SecurityPasswordException();
        }
    }

    private void validateUsername(String username) {
        if (username == null || username.isBlank()) {
            throw new EmptyUsernameException();
        }

        if (userRepository.findByUsername(username).isPresent()) {
            throw new ExistingUsernameException();
        }
    }
}

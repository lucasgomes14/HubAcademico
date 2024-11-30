package com.academichub.AcademicHub.service;

import com.academichub.AcademicHub.exceptions.*;
import com.academichub.AcademicHub.model.Course;
import com.academichub.AcademicHub.model.User;
import com.academichub.AcademicHub.model.UserType;
import com.academichub.AcademicHub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public boolean cadasterUser(String name, String lastName, LocalDateTime dateAndTimeOfUserCreation, String email, String password, UserType userType, Course course) {
        validatesCadaster(name, lastName, email, password);

        if (userRepository.findByEmail(email) != null) {
            throw new ExistingEmailException("Email já existe");
        }

        String encryptedPassword = passwordEncoder.encode(password);

        User user = createUser(name, lastName, dateAndTimeOfUserCreation, email, encryptedPassword, userType, course);

        userRepository.save(user);

        return true;
    }

    public boolean loginUser(String email, String password) {
        User user = userRepository.findByEmail(email);

        return user != null && passwordEncoder.matches(password, user.getPassword());
    }

    private void validatesCadaster(String name, String lastName, String email, String password) {
        validateName(name);
        validateLastName(lastName);
        validateEmail(email);
        validatePassword(password);
    }

    private User createUser(String name, String lastName, LocalDateTime dateAndTimeOfUserCreation, String email, String encryptedPassword, UserType userType, Course course) {
        User user = new User();

        user.setName(name);
        user.setLastName(lastName);
        user.setDateAndTimeOfUserCreation(dateAndTimeOfUserCreation);
        user.setEmail(email);
        user.setPassword(encryptedPassword);
        user.setUserType(userType);
        user.setCourse(course);

        return user;
    }

    private void validateEmail(String email) {
        if (email == null) {
            throw new NullAndDomainEmailException("Campo email vazio!");
        }
        if (!email.endsWith("@academico.ifpb.edu.br")) {
            throw new NullAndDomainEmailException("Email não tem domínio acadêmico do IFPB");
        }
    }

    private void validateName(String name) {
        if (name == null) {
            throw new NullNameException("Campo nome vazio!");
        }
    }

    private void validateLastName(String lastName) {
        if (lastName == null) {
            throw new NullLastNameException("Campo último nome vazio!");
        }
    }

    private void validatePassword(String password) {
        if (password.length() < 8) {
            throw new SecurityPasswordException("Senha tem que ter no mínimo 8 dígitos");
        }

        boolean securePassword = password.chars().anyMatch(Character::isUpperCase)
                && password.chars().anyMatch(Character::isLowerCase)
                && password.chars().anyMatch(Character::isDigit);

        if (!securePassword) {
            throw new SecurityPasswordException("Senha tem que ter digitos maiúsculo, minúsculo e digito");
        }
    }
}

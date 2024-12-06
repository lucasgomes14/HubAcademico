package com.academichub.AcademicHub.service;

import com.academichub.AcademicHub.exceptions.*;
import com.academichub.AcademicHub.model.user.User;
import com.academichub.AcademicHub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void cadasterUser(User user) {
        validatesCadaster(user.getName(), user.getLastName(), user.getEmail(), user.getPassword(), user.getUsername());

        String encryptedPassword = new BCryptPasswordEncoder().encode(user.getPassword());

        user.setPassword(encryptedPassword);

        userRepository.save(user);
    }

    private void validatesCadaster(String name, String lastName, String email, String password, String username) {
        validateName(name);
        validateLastName(lastName);
        validateEmail(email);
        validatePassword(password);
        validateUsername(username);
    }

    private void validateEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new NullAndDomainEmailException("Campo email vazio!");
        }
        if (!email.endsWith("@academico.ifpb.edu.br")) {
            throw new NullAndDomainEmailException("Email não tem domínio acadêmico do IFPB");
        }

        if (userRepository.findByEmail(email) != null) {
            throw new ExistingEmailException("Email já existe");
        }
    }

    private void validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new NullNameException("Nome vazio!");
        }
    }

    private void validateLastName(String lastName) {
        if (lastName == null || lastName.isBlank()) {
            throw new NullLastNameException("Último nome vazio!");
        }
    }

    private void validatePassword(String password) {
        if (password == null || password.length() < 8) {
            throw new SecurityPasswordException("Senha tem que ter no mínimo 8 dígitos");
        }

        if (!password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$")) {
            throw new SecurityPasswordException("Senha precisa ter maiúsculas, minúsculas e números.");
        }
    }

    private void validateUsername(String username) {
        if (username == null || username.isBlank()) {
            throw new NullUsernameException("Nome de usuário vazio!");
        }

        if (userRepository.findByUsername(username) != null) {
            throw new ExistingUsernameException("Nome de usuário já existe");
        }
    }
}

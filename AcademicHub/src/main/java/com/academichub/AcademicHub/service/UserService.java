package com.academichub.AcademicHub.service;

import com.academichub.AcademicHub.dto.RegisterDTO;
import com.academichub.AcademicHub.exceptions.UserNotFoundException;
import com.academichub.AcademicHub.mapper.UserMapper;
import com.academichub.AcademicHub.model.user.User;
import com.academichub.AcademicHub.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public User cadasterUser(User user) {
//        validatesCadaster(data.name(), data.lastName(), data.email(), data.password(), data.username());

        return userRepository.save(user);
    }

    public User userLogin(String email) {
        return userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
    }

//    private void validatesCadaster(String name, String lastName, String email, String password, String username) {
//        validateEmail(email);
//        validateName(name);
//        validateLastName(lastName);
//        validatePassword(password);
//        validateUsername(username);
//    }
//
//    private void validateEmail(String email) {
//        if (email == null || email.isBlank()) {
//            throw new EmptyEmailException();
//        }
//
//        if (!email.endsWith("@academico.ifpb.edu.br")) {
//            throw new WrongEmailDomain();
//        }
//
//        if (userRepository.findByEmail(email).isPresent()) {
//            throw new ExistingEmailException();
//        }
//    }
//
//    private void validateName(String name) {
//        if (name == null || name.isBlank()) {
//            throw new EmptyNameException();
//        }
//    }
//
//    private void validateLastName(String lastName) {
//        if (lastName == null || lastName.isBlank()) {
//            throw new EmptyLastNameException();
//        }
//    }
//
//    private void validatePassword(String password) {
//        if (password == null || password.length() < 8) {
//            throw new SecurityPasswordException();
//        }
//
//        if (!password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$")) {
//            throw new SecurityPasswordException();
//        }
//    }
//
//    private void validateUsername(String username) {
//        if (username == null || username.isBlank()) {
//            throw new EmptyUsernameException();
//        }
//
//        if (userRepository.findByUsername(username).isPresent()) {
//            throw new ExistingUsernameException();
//        }
//
//        if (username.contains(" ")) {
//            throw new ContainsSpaceException();
//        }
//
//        if (username.matches(".*[<>\"'%20%3C%3E%27%22%2F].*")) {
//            throw new ContainsSpecialCharactersException();
//        }
//    }
}

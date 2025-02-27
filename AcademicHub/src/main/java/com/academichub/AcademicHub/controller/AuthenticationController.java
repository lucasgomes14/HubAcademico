package com.academichub.AcademicHub.controller;

import com.academichub.AcademicHub.dto.LoginRequestDTO;
import com.academichub.AcademicHub.dto.LoginResponseDTO;
import com.academichub.AcademicHub.dto.RegisterDTO;
import com.academichub.AcademicHub.mapper.UserMapper;
import com.academichub.AcademicHub.infra.security.TokenService;
import com.academichub.AcademicHub.model.user.User;
import com.academichub.AcademicHub.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final UserService userService;
    private final TokenService tokenService;
    private final UserMapper userMapper;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Validated RegisterDTO body) throws IOException {
        User user = userMapper.from(body);
        User userSave = userService.cadasterUser(user);

        return (userSave != null) ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Validated LoginRequestDTO body) {
        try {
            User user = userService.authenticateUser(body.email(), body.password());
            String token = tokenService.generateToken(user);
            return ResponseEntity.ok(new LoginResponseDTO(user.getEmail(), user.getUsername(), token));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}

package com.academichub.AcademicHub.controller;

import com.academichub.AcademicHub.dto.LoginRequestDTO;
import com.academichub.AcademicHub.dto.LoginResponseDTO;
import com.academichub.AcademicHub.dto.RegisterDTO;
import com.academichub.AcademicHub.infra.security.TokenService;
import com.academichub.AcademicHub.mapper.UserMapper;
import com.academichub.AcademicHub.model.user.*;
import com.academichub.AcademicHub.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    private final TokenService tokenService;

    private final UserMapper userMapper;

    /* a entrada vai ser um json que vai ser validado e o paramentro está
       em model/user/RegisterDTO coloquei assim para ficar mais organizado e limpo*/
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Validated RegisterDTO body) throws IOException {

        User user = userMapper.from(body);
        User userSave = userService.cadasterUser(user);

        if (userSave != null) {
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.badRequest().build();
    }

    /* a entrada vai ser um json que vai ser validado e o paramentro está
       em model/user/AuthenticationDTO coloquei assim para ficar mais organizado e limpo.
       esse método ele faz o hash do login e senha e compara com os usuários que estão no banco
    */
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Validated LoginRequestDTO body) {
        User user = userService.userLogin(body.email());

        if (passwordEncoder.matches(body.password(), user.getPassword())) {
            String token = tokenService.generateToken(user);
            return ResponseEntity.ok(new LoginResponseDTO(user.getEmail(), user.getUsername(), token));
        }
        return ResponseEntity.badRequest().build();
    }
}

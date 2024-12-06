package com.academichub.AcademicHub.controller;

import com.academichub.AcademicHub.infra.security.TokenService;
import com.academichub.AcademicHub.model.user.*;
import com.academichub.AcademicHub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    /* a entrada vai ser um json que vai ser validado e o paramentro está
       em model/user/RegisterDTO coloquei assim para ficar mais organizado e limpo*/
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Validated RegisterDTO data) {

        User user = createUser(data);

        userService.cadasterUser(user);

        return ResponseEntity.ok().build();
    }

    /* a entrada vai ser um json que vai ser validado e o paramentro está
       em model/user/AuthenticationDTO coloquei assim para ficar mais organizado e limpo.
       esse método ele faz o hash do login e senha e compara com os usuários que estão no banco
    */
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Validated AuthenticationDTO data) {
        // o spring security consulta no service/AuthenticationService
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());

        // authentica o usuario e senha
        var auth = authenticationManager.authenticate(usernamePassword);

        // gera o token do usuario
        var token = tokenService.generateToken((User) auth.getPrincipal());

        // retorna o ok e o token
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    private User createUser(RegisterDTO data) {
        UserRole role = UserRole.valueOf(data.role());
        Course course = Course.valueOf(data.course());

        User user = new User();
        user.setName(data.name());
        user.setLastName(data.lastName());
        user.setUsername(data.username());
        user.setEmail(data.email());
        user.setPassword(data.password());
        user.setRole(role);
        user.setCourse(course);
        user.setDateAndTimeOfUserCreation(LocalDateTime.now());

        return user;
    }
}

package com.academichub.AcademicHub.infra.security;

import com.academichub.AcademicHub.model.user.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

// classe de geração de token
@Service
public class TokenService {

    /*
        parametro exclusivo para que adicione algo na criptografia e se torne unico
        e esse atributo vai ser pego pelas variáveis de ambiente onde são definidas
        quando fazemos o deploy da aplicação olhar o application.properties que lá
        tá configurado e por lá que consulta as variáveis de ambiente
     */
    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(User user) {
        try {
            // algoritmo que faz que o hash seja único para nossa aplicação
            Algorithm algorithm =Algorithm.HMAC256(secret);
            // gera token
            String token = JWT.create()
                    .withIssuer("HubAcademico") // quem criou o token
                    .withSubject(user.getEmail()) // usuario que ta recebendo o token
                    .withExpiresAt(generateExpirationDate()) // tempo de expiração do token
                    .sign(algorithm); // assinatura e geração final

            return token;
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro enquanto gerava o token", exception);
        }
    }

    /*
        valida o token
    */
    public String validateToken(String token) {
        try {
            // algoritmo que faz que o hash seja único para nossa aplicação
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("HubAcademico")
                    .build()
                    .verify(token) // decriptografa
                    .getSubject(); // devolve o usuario do token
        } catch (JWTVerificationException exception) {
            return null;
        }
    }

    // vai expirar o token em 1 dia
    private Instant generateExpirationDate() {
        return LocalDateTime.now().plusDays(1).toInstant(ZoneOffset.of("-03:00"));
    }
}

package com.academichub.AcademicHub.infra.security;

import com.academichub.AcademicHub.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

// extende OncePerRequestFilter que um filtro que acontece uma vez a cada requisição
@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    TokenService tokenService;

    @Autowired
    UserRepository userRepository;

    // quando passa o método de filtro antes dos matchers do securityConfiguration é esse método que vai verificar
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = this.recoveryToken(request);

        if (token != null) {
            // verifica o token e caso nao há exceção vai ter o usuario que pega no banco
            var login = tokenService.validateToken(token);
            UserDetails user = userRepository.findByEmail(login);

            // verifica o usuario passando todas as informações que o spring security vai precisar para fazer as validações nas proximos end points
            var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        // chamando o proximo filtro que é o que chama la no SecurityConfiguration que vai ser o UsernamePasswoedAuthenticationFilter.class
        filterChain.doFilter(request, response);
    }

    // vai verificar o token que o usuario passar
    private String recoveryToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null) return null;
        return authHeader.replace("Bearer ", ""); // substitui o Bearer por vazio, bearer token é padrao nas requisiçoes de quando manda um header de autorização que contem um token, que identifica o token que vai ser o Bearer
    }
}

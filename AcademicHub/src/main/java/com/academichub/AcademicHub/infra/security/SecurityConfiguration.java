package com.academichub.AcademicHub.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    SecurityFilter securityFilter;

    /*
        método que faz validações de usuário se ele ta
        apto ou não para fazer uma requisição de um tipo espécifico
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .cors(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // diz ao spring que queremos fazer uma autenticação STETELESS(faz autenticação via token)
                .authorizeHttpRequests(authorize -> authorize // diz quais são as requisições http que vão ser autorizada
                        .requestMatchers("/").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll() // permite que qualquer role faça um post no link /auth/login
                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll() // permite que qualquer role faça um post no link /auth/register
                        .requestMatchers(HttpMethod.GET, "/dashboard/feed").authenticated()
                        .anyRequest().authenticated() // qualquer outra requisição tem que ter usuario autenticado
                )
                // adiciona o filtro antes dos requestMatchers, para fazer uma verificação, vê o token, usuario, a role
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    // indica ao spring quem é o AuthenticationManager que precisa no controller/AuthenticaionController no método login
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    // indica ao spring quem é o PasswordEncoder, que serve para criptografar a senha do usuário
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

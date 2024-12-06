package com.academichub.AcademicHub.service;

import com.academichub.AcademicHub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/* esse serviço vai ser chamado automaticamente pelo spring
    security toda vez que um usuário precisar se autenticar
*/
@Service
public class AuthorizationService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    /* método que toda vez que alguem tenta se autenticar,
        o spring security vai ter essa forma de consultar no banco de dados
    */
    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        if (login.endsWith(".ifpb.edu.br")) {
            return userRepository.findByEmail(login);
        }
        return userRepository.findByUsername(login);
    }
}

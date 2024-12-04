package com.academichub.AcademicHub.repository;

import com.academichub.AcademicHub.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

// classe que tem métodos pré definidos para fazer crud
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // métodos mais específicos
    UserDetails findByEmail(String email);
    UserDetails findByUsername(String username);
}

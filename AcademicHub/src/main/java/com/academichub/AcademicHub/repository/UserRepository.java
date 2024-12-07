package com.academichub.AcademicHub.repository;

import com.academichub.AcademicHub.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// classe que tem métodos pré definidos para fazer crud
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // métodos mais específicos
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
}

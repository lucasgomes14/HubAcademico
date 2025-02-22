package com.academichub.AcademicHub.repository;

import com.academichub.AcademicHub.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

// classe que tem métodos pré definidos para fazer crud
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // métodos mais específicos
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
    @Modifying
    @Query("UPDATE User u SET u.name = ?2, u.username = ?3, u.bio = ?4, u.profilePicture = ?5, u.userUpdateDateAndTime = ?6 WHERE u.username = ?1")
    int updateUser(String username, String name, String usernameNew, String bio, String profilePicture, LocalDateTime userUpdateDateAndTime);
    List<User> findByUsernameContainingIgnoreCase(String username);
}

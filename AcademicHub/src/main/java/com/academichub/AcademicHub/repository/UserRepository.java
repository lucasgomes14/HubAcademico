package com.academichub.AcademicHub.repository;

import com.academichub.AcademicHub.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    List<User> findByUsernameContainingIgnoreCase(String username);

    @Query("SELECT COUNT(f) > 0 FROM User u JOIN u.following f WHERE u.id = :userId AND f.id = :followingId")
    boolean existsByUserIdAndFollowingId(@Param("userId") Long userId, @Param("followingId") Long followingId);

    @Modifying
    @Query(value = "DELETE FROM user_following WHERE user_id = :userId AND following_id = :followingId", nativeQuery = true)
    void deleteByUserIdAndFollowingId(@Param("userId") Long userId, @Param("followingId") Long followingId);
}

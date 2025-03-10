package com.academichub.AcademicHub.repository;

import com.academichub.AcademicHub.model.post.Post;
import com.academichub.AcademicHub.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    @Query("SELECT p FROM Post p WHERE p.user IN :following OR p.user = :user ORDER BY p.dateAndTimeOfPublication DESC")
    List<Post> findPostsByFollowingAndUser(@Param("following") List<User> following, @Param("user") User user);

    @Query("SELECT p FROM Post p WHERE p.user.id = :userId ORDER BY p.dateAndTimeOfPublication DESC")
    List<Post> findPostsByUserIdOrderByDateDesc(@Param("userId") Long userId);
}

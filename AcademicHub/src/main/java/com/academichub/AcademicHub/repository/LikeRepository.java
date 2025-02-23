package com.academichub.AcademicHub.repository;

import com.academichub.AcademicHub.model.like.Like;
import com.academichub.AcademicHub.model.post.Post;
import com.academichub.AcademicHub.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
    @Query("SELECT l FROM Like l WHERE l.user = :user AND l.post = :post")
    Optional<Like> findByUserAndPost(@Param("user") User user, @Param("post") Post post);
}

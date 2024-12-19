package com.academichub.AcademicHub.repository;

import com.academichub.AcademicHub.model.likePost.LikePost;
import com.academichub.AcademicHub.model.post.Post;
import com.academichub.AcademicHub.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LikePostRepository extends JpaRepository<LikePost, Long> {
    boolean existsByPostAndUser(Post post, User user);

    @Modifying
    @Query("DELETE FROM LikePost lp WHERE lp.post = :post AND lp.user = :user")
    void deleteByPostAndUser(@Param("post") Post post, @Param("user") User user);
}
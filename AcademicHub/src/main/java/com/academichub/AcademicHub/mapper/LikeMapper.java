package com.academichub.AcademicHub.mapper;

import com.academichub.AcademicHub.model.like.Like;
import com.academichub.AcademicHub.model.post.Post;
import com.academichub.AcademicHub.model.user.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class LikeMapper {
    public Like from(User user, Post post) {
        var like = new Like();

        like.setUser(user);
        like.setPost(post);
        like.setDateTimeLike(LocalDateTime.now());

        return like;
    }
}

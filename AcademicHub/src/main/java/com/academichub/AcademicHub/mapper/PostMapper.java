package com.academichub.AcademicHub.mapper;

import com.academichub.AcademicHub.dto.PostDTO;
import com.academichub.AcademicHub.model.post.Post;
import com.academichub.AcademicHub.model.user.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class PostMapper {
    public Post from(PostDTO postDTO, User user) {
        var post = new Post();

        post.setDescription(postDTO.description());
        post.setContent(postDTO.content());
        post.setDateAndTimeOfPublication(LocalDateTime.now());
        post.setLikes(0);
        post.setUser(user);

        return post;
    }
}

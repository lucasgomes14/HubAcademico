package com.academichub.AcademicHub.mapper;

import com.academichub.AcademicHub.dto.CommentDTO;
import com.academichub.AcademicHub.model.comment.Comment;
import com.academichub.AcademicHub.model.post.Post;
import com.academichub.AcademicHub.model.user.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CommentMapper {
    public Comment from(CommentDTO commentDTO, User user, Post post) {
        Comment comment = new Comment();

        comment.setText(commentDTO.comment());
        comment.setDateAndTimeOfPublication(LocalDateTime.now());
        comment.setUser(user);
        comment.setPost(post);

        return comment;
    }
}

package com.academichub.AcademicHub.interfaces;

import com.academichub.AcademicHub.model.comment.Comment;
import com.academichub.AcademicHub.model.post.Post;
import com.academichub.AcademicHub.model.user.User;
import com.academichub.AcademicHub.model.user.UserRole;

import java.util.List;

public interface Features {
    Boolean login(String email, String password);
    Boolean register(String name, String lastName, String email, String password, UserRole type);
    Boolean emailVerify(String email);
    Boolean insert(String name, String lastName, String email, String password, UserRole type);
    User getUser(Long id);
    Post getPost(Long id);
    Boolean updatePost(Post post,User user);
    Boolean updateUser(User user);
    Boolean deletePost(Post post,User user);
    Boolean deleteUser(User user);
    Boolean newPost(String description, byte[] content);
    Integer getLikes(Long idPost);
    void addLike(Long idPost, Long idUser);
    List<Comment> getComments(Long idPost);
    void addComment(Long idPost, Long idUser, Comment comment);
    Boolean like();
    Boolean comment(Comment comment);

}


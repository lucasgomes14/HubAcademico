package com.academichub.AcademicHub.interfaces;

import com.academichub.AcademicHub.model.Post;
import com.academichub.AcademicHub.model.User;

public interface Features {
    Boolean login(String email, String password);
    Boolean register(String name, String lastName, String email, String password, Enum type);
    Boolean emailVerify(String email);
    Boolean insert(String name, String lastName, String email, String password, Enum type);
    User getUser(Long id);
    Post getPost(Long id);
    Boolean updatePost(Post post,User user);
    Boolean updateUser(User user);
    Boolean deletePost(Post post,User user);
    Boolean deleteUser(User user);
    Boolean newPost(String description, T content);
    Integer getLikes(Long idPost);
    void addLike(Long idPost, Long idUser);
    List<Comment> getComments(Long idPost);
    void addComment(Long idPost, Long idUser, Comment comment);
    Boolean like();
    Boolean comment(Comment comment);

}


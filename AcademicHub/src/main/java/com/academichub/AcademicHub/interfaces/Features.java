package com.academichub.AcademicHub.interfaces;

import com.academichub.AcademicHub.model.User;

public interface Features {
    Boolean login(String email, String password);
    Boolean register(String name, String lastName, String email, String password, Enum type);
    Boolean emailVerify(String email);
    Boolean insert(String name, String lastName, String email, String password, Enum type);
    User getUser(Integer id);
    Post getPost(Integer id);
    Boolean updatePost(Post post,User user);
    Boolean updateUser(User user);
    Boolean deletePost(Post post,User user);
    Boolean deleteUser(User user);
    Boolean newPost(String description, T content);
    Integer getLikes(Integer idPost);
    void addLike(Integer idPost, Integer idUser);
    List<Comment> getComments(Integer idPost);
    void addComment(Integer idPost, Integer idUser, Comment comment);
    Boolean like();
    Boolean comment(Comment comment);

}


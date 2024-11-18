package com.academichub.AcademicHub.interfaces;

public interface Features {
    Boolean login(String email, String password);
    Boolean register(String name, String lastName, String email, String password, Enum type);
    Boolean emailVerify(String email);
    Boolean insert(String name, String lastName, String email, String password, Enum type);
    User getUser(Integer id);
    Post getPost(Integer id);
    Boolean update(Post post);
    Boolean update(User user);
    Boolean delete(Post post);
    Boolean delete(User user);
    Boolean newPost(String description, T content);
    void likes(Integer idPost);
    void addLike(Integer idPost, Integer idUser);
    List<String> comments(Integer idPost);
    void addComment(Integer idPost, Integer idUser, String comment);
    Boolean post(Post<T> post);
    void like();
    void comment(String comment);

}

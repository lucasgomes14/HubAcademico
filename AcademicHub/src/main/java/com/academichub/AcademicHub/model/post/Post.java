package com.academichub.AcademicHub.model.post;

import com.academichub.AcademicHub.model.comment.Comment;
import com.academichub.AcademicHub.model.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tb_post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "content")
    private byte[] content;

    @Column(name = "date_and_time_of_publication", nullable = false)
    private LocalDateTime dateAndTimeOfPublication;

    @Column(name = "publication_update_date_and_time")
    private LocalDateTime publicationUpdateDateAndTime;

    @Column(name = "likes")
    private Integer likes;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();
}
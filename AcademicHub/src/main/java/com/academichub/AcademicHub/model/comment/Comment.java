package com.academichub.AcademicHub.model.comment;

import com.academichub.AcademicHub.model.post.Post;
import com.academichub.AcademicHub.model.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "tb_comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "text", nullable = false)
    private String text;

    @Column(name = "likes", nullable = false)
    private Integer likes;

    @Column(name = "date_and_time_of_publication", nullable = false)
    private LocalDateTime dateAndTimeOfPublication;

    @Column(name = "publication_update_date_and_time")
    private LocalDateTime publicationUpdateDateAndTime;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;
}
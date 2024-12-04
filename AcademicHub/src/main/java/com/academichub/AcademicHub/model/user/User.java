package com.academichub.AcademicHub.model.user;

import com.academichub.AcademicHub.model.comment.Comment;
import com.academichub.AcademicHub.model.post.Post;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* implementa o userDetails que é uma classe do spring security,
    que é usada para identificar uma classe que represente um usuário
    que será autenticado na aplicação
*/

@Getter
@Setter
@Entity
@Table(name = "tb_user")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "date_and_time_of_user_creation", nullable = false)
    private LocalDateTime dateAndTimeOfUserCreation;

    @Column(name = "user_update_date_and_time")
    private LocalDateTime userUpdateDateAndTime;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_role", nullable = false)
    private UserRole role;

    @Enumerated(EnumType.STRING)
    @Column(name = "course", nullable = false)
    private Course course;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "user_friend",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id")
    )
    private List<User> friends = new ArrayList<>();

    // método para quando o spring security for consultar um entidade para vê quais são os papéis de cada tipo de usuário(student, teacher, coordinator, admin)
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == UserRole.STUDENT) return List.of(new SimpleGrantedAuthority("ROLE_STUDENT"));
        else if (this.role == UserRole.TEACHER) return List.of(new SimpleGrantedAuthority("ROLE_TEACHER"));
        else if (this.role == UserRole.COORDINATOR) return List.of(new SimpleGrantedAuthority("ROLE_COORDINATOR"));
        else if (this.role == UserRole.ADMIN)
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"),
                new SimpleGrantedAuthority("ROLE_STUDENT"),
                new SimpleGrantedAuthority("ROLE_TEACHER"),
                new SimpleGrantedAuthority("ROLE_COORDINATOR"));

        return List.of();
    }

    // método que retorna o login do usuário(email ou username)
    @Override
    public String getUsername() {
        return username;
    }

    // esses outros métodos são mais avançados e tem que estudar eles para fazer
    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
package com.academichub.AcademicHub.mapper;

import com.academichub.AcademicHub.dto.DashboardPostDTO;
import com.academichub.AcademicHub.dto.PostDTO;
import com.academichub.AcademicHub.model.post.Post;
import com.academichub.AcademicHub.model.user.User;
import com.academichub.AcademicHub.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class PostMapper {

    private final PostService postService;

    public List<DashboardPostDTO> from(List<Post> posts, User user) {
        return posts.stream()
                .map(post -> new DashboardPostDTO(
                        post.getId(),
                        post.getUser().getProfilePicture(),
                        post.getUser().getUsername(),
                        postTime(post.getDateAndTimeOfPublication()),
                        post.getDescription(),
                        post.getLikes().size(),
                        post.getComments().size(),
                        postService.findByUserAndPost(user, post)

                ))
                .collect(Collectors.toList());
    }

    public Post from(PostDTO postDTO, User user) {
        var post = new Post();

        post.setDescription(postDTO.description());
        post.setDateAndTimeOfPublication(LocalDateTime.now());
        post.setUser(user);

        return post;
    }

    private String postTime(LocalDateTime timeLast) {
        var time = Duration.between(timeLast, LocalDateTime.now());

        if (time.toDays() > 0) {
            return time.toDays() + "d atrás";
        } else if (time.toHours() > 0) {
            return time.toHours() + "h atrás";
        } else if (time.toMinutes() == 0 )
            return "Agora mesmo";
        return time.toMinutes() + "m atrás";
    }
}

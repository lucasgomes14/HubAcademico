package com.academichub.AcademicHub.mapper;

import com.academichub.AcademicHub.dto.DashboardPostDTO;
import com.academichub.AcademicHub.model.post.Post;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DashboardMapper {
    public List<DashboardPostDTO> from(List<Post> posts) {
        return posts.stream()
                .map(post -> new DashboardPostDTO(
                        post.getUser().getProfilePicture(),
                        post.getUser().getUsername(),
                        postTime(post.getDateAndTimeOfPublication()),
                        post.getDescription(),
                        post.getContent(),
                        post.getLikes(),
                        post.getComments().size()
                ))
                .collect(Collectors.toList());
    }

    private String postTime(LocalDateTime timeLast) {
        var time = Duration.between(timeLast, LocalDateTime.now());

        if (time.toDays() > 0) {
            return time.toDays() + "d atrás";
        } else if (time.toHours() > 0) {
            return time.toHours() + "h atrás";
        }

        return time.toMinutes() + "m atrás";
    }
}

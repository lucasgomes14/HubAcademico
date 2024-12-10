package com.academichub.AcademicHub.dto;

import com.academichub.AcademicHub.model.post.Post;
import com.academichub.AcademicHub.model.user.Course;
import com.academichub.AcademicHub.model.user.UserRole;

import java.time.LocalDateTime;
import java.util.List;

public record UserProfilleResponseDTO(String name, String lastName, String username,
                                      LocalDateTime dateAndTimeOfUserCreation, LocalDateTime userUpdateDateAndTime,
                                      String email, UserRole role, Course course, List<Post> posts) {
}

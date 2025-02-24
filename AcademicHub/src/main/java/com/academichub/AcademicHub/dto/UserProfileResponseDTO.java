package com.academichub.AcademicHub.dto;

import com.academichub.AcademicHub.model.user.Course;
import com.academichub.AcademicHub.model.user.UserRole;

import java.time.LocalDateTime;

public record UserProfileResponseDTO(String name,
                                     String lastName,
                                     String username,
                                     String bio,
                                     LocalDateTime dateAndTimeOfUserCreation,
                                     LocalDateTime userUpdateDateAndTime,
                                     String email,
                                     UserRole role,
                                     Course course,
                                     byte[] profilePicture,
                                     int followingCount,
                                     int followersCount,
                                     boolean isFollowing) {
}

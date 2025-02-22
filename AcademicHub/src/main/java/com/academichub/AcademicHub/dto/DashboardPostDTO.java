package com.academichub.AcademicHub.dto;

public record DashboardPostDTO(Long id,
                               byte[] profilePicture,
                               String username,
                               String minutesAgo,
                               String description,
                               int likes,
                               int comments,
                               boolean isLiked) {
}

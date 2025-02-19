package com.academichub.AcademicHub.dto;

public record DashboardPostDTO(byte[] profilePicture,
                               String username,
                               String minutesAgo,
                               String description,
                               int likes,
                               int comments) {
}

package com.academichub.AcademicHub.dto;

public record DashboardPostDTO(byte[] profilePicture,
                               String username,
                               String date,
                               String description,
                               byte[] content,
                               int likes,
                               int comments) {
}

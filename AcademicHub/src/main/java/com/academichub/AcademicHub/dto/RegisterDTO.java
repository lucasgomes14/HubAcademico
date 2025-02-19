package com.academichub.AcademicHub.dto;

public record RegisterDTO(String name,
                          String lastName,
                          String username,
                          String bio,
                          String email,
                          String role,
                          String course,
                          String password,
                          byte[] profilePicture) {
}

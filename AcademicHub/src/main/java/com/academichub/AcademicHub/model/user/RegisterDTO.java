package com.academichub.AcademicHub.model.user;

public record RegisterDTO(String name, String lastName, String username, String email, String password, String role, String course) {
}

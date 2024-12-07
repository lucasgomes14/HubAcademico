package com.academichub.AcademicHub.model.user;

import lombok.Getter;

@Getter
public enum UserRole {
    STUDENT("student"),
    TEACHER("professor"),
    COORDINATOR("coordinator"),
    ADMIN("admin");

    private final String role;

    UserRole(String role) {
        this.role = role;
    }

}

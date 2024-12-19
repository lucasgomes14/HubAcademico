package com.academichub.AcademicHub.dto;

import com.academichub.AcademicHub.model.user.Course;
import com.academichub.AcademicHub.model.user.User;
import com.academichub.AcademicHub.model.user.UserRole;
import java.util.List;

public record DashboardResponseDTO(String username, UserRole role, Course course, String profilePicture, List<User> followings) {
}

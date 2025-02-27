package com.academichub.AcademicHub.mapper;

import com.academichub.AcademicHub.dto.RegisterDTO;
import com.academichub.AcademicHub.model.user.Course;
import com.academichub.AcademicHub.model.user.User;
import com.academichub.AcademicHub.model.user.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Component
public class UserMapper {

    private final PasswordEncoder passwordEncoder;

    public User from(RegisterDTO data) throws IOException {
        UserRole role = UserRole.valueOf(data.role());
        Course course = Course.valueOf(data.course());

        User user = new User();
        user.setName(data.name());
        user.setLastName(data.lastName());
        user.setUsername(data.username());
        user.setBio(data.bio());
        user.setEmail(data.email());
        user.setPassword(passwordEncoder.encode(data.password()));
        user.setRole(role);
        user.setCourse(course);
        user.setDateAndTimeOfUserCreation(LocalDateTime.now());

        var inputStream = getClass().getClassLoader().getResourceAsStream("static/Default-Profile-Picture.png");

        System.out.println(inputStream);

        var defaultProfilePicture = inputStream.readAllBytes();

        user.setProfilePicture(defaultProfilePicture);

        return user;
    }
}

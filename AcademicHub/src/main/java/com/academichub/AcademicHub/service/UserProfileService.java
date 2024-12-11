package com.academichub.AcademicHub.service;

import com.academichub.AcademicHub.dto.UserProfileResponseDTO;
import com.academichub.AcademicHub.exceptions.EmptyUsernameException;
import com.academichub.AcademicHub.exceptions.UserNotFoundException;
import com.academichub.AcademicHub.model.user.User;
import com.academichub.AcademicHub.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.function.EntityResponse;

@Service
@RequiredArgsConstructor
public class UserProfileService {

    private final UserRepository userRepository;

    public UserProfileResponseDTO getUserProfile(String username) {
        validateUsername(username);

        User user = userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);

        return new UserProfileResponseDTO(user.getName(), user.getLastName(),
                user.getUsername(), user.getBio(), user.getDateAndTimeOfUserCreation(), user.getUserUpdateDateAndTime(),
                user.getEmail(), user.getRole(), user.getCourse(), user.getProfilePicture(), user.getPosts());
    }

    private void validateUsername(String username) {
        if (username.trim().isEmpty()) {
            throw new EmptyUsernameException();
        }
    }
}

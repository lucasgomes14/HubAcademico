package com.academichub.AcademicHub.service;

import com.academichub.AcademicHub.dto.UserProfileResponseDTO;
import com.academichub.AcademicHub.exceptions.EmptyUsernameException;
import com.academichub.AcademicHub.exceptions.UserNotFoundException;
import com.academichub.AcademicHub.model.user.User;
import com.academichub.AcademicHub.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserProfileService {

    private final UserRepository userRepository;

    public UserProfileResponseDTO getUserProfile(String username) {
        validateUsername(username);

        User user = userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);

        return createUserProfile(user);
    }

    private void validateUsername(String username) {
        if (username.trim().isEmpty()) {
            throw new EmptyUsernameException();
        }
    }

    private UserProfileResponseDTO createUserProfile(User user) {
        return new UserProfileResponseDTO(user.getName(), user.getLastName(),
                user.getUsername(), user.getBio(), user.getDateAndTimeOfUserCreation(), user.getUserUpdateDateAndTime(),
                user.getEmail(), user.getRole(), user.getCourse(), user.getProfilePicture(), user.getPosts(),
                user.getFollowing().size(), user.getFollowers().size());
    }

    @Transactional
    public boolean updateUserProfile(String username, String name, String newUsername, String bio, String profilePicture) {
        if (userRepository.findByUsername(newUsername).isPresent()) {
            return false;
        }

        int rowsAffected = userRepository.updateUser(username, name, newUsername, bio, profilePicture, LocalDateTime.now());
        return rowsAffected > 0;
    }
}

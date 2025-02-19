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

    public User getUserProfile(String username) {
//        validateUsername(username);

        return userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
    }

//    private void validateUsername(String username) {
//        if (username.trim().isEmpty()) {
//            throw new EmptyUsernameException();
//        }
//    }

    @Transactional
    public boolean updateUserProfile(String username, String name, String newUsername, String bio, String profilePicture) {
        if (userRepository.findByUsername(newUsername).isPresent()) {
            return false;
        }

        int rowsAffected = userRepository.updateUser(username, name, newUsername, bio, profilePicture, LocalDateTime.now());
        return rowsAffected > 0;
    }
}

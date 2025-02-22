package com.academichub.AcademicHub.service;

import com.academichub.AcademicHub.exceptions.EmptyUsernameException;
import com.academichub.AcademicHub.exceptions.UserNotFoundException;
import com.academichub.AcademicHub.model.user.User;
import com.academichub.AcademicHub.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserProfileService {

    private final UserRepository userRepository;

    public User getUserProfile(String username) {
        validateUsername(username);

        return userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
    }

    private void validateUsername(String username) {
        if (username.trim().isEmpty()) {
            throw new EmptyUsernameException();
        }
    }

    @Transactional
    public void updateUserProfile(User user) {
        if (userRepository.findById(user.getId()).isEmpty()) {
            throw new UserNotFoundException();
        }

        getUserProfile(user.getUsername());

        userRepository.save(user);
    }
}

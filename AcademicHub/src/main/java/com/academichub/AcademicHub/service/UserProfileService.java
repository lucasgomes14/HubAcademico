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

    public boolean isFollowing(User user, User friend) {
        return userRepository.existsByUserIdAndFollowingId(user.getId(), friend.getId());
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

    public void followUser(User user, String username) {
        User following = userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);

        user.getFollowing().add(following);

        userRepository.save(user);
    }

    @Transactional
    public void unfollowUser(User user, String username) {
        User following = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuário seguido não encontrado"));

        user.getFollowing().remove(following);

        userRepository.deleteByUserIdAndFollowingId(user.getId(), following.getId());
        userRepository.save(user);
    }
}

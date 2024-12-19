package com.academichub.AcademicHub.service;

import com.academichub.AcademicHub.dto.DashboardResponseDTO;
import com.academichub.AcademicHub.exceptions.UserNotFoundException;
import com.academichub.AcademicHub.model.user.User;
import com.academichub.AcademicHub.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final UserRepository userRepository;

    public DashboardResponseDTO getUserProfile(String username) {

        User user = userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);

        return createUserProfile(user);
    }

    private DashboardResponseDTO createUserProfile(User user) {
        return new DashboardResponseDTO(
                user.getUsername(),
                user.getRole(),
                user.getCourse(),
                user.getProfilePicture(),
                user.getFollowing()
        );
    }
}

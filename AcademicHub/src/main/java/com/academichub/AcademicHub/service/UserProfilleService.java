package com.academichub.AcademicHub.service;

import com.academichub.AcademicHub.dto.UserProfilleResponseDTO;
import com.academichub.AcademicHub.exceptions.UserNotFoundException;
import com.academichub.AcademicHub.model.user.User;
import com.academichub.AcademicHub.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserProfilleService {

    private final UserRepository userRepository;

    public UserProfilleResponseDTO getUserProfille(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);

        return new UserProfilleResponseDTO(user.getName(), user.getLastName(),
                user.getUsername(), user.getDateAndTimeOfUserCreation(), user.getUserUpdateDateAndTime(),
                user.getEmail(), user.getRole(), user.getCourse(), user.getPosts());
    }
}

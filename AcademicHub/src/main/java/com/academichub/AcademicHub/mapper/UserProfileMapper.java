package com.academichub.AcademicHub.mapper;

import com.academichub.AcademicHub.dto.UpdateUserProfileDTO;
import com.academichub.AcademicHub.dto.UserProfileResponseDTO;
import com.academichub.AcademicHub.model.user.User;
import org.springframework.stereotype.Component;

@Component
public class UserProfileMapper {
    public UserProfileResponseDTO from(User user) {
        return new UserProfileResponseDTO(user.getName(), user.getLastName(),
                user.getUsername(), user.getBio(), user.getDateAndTimeOfUserCreation(), user.getUserUpdateDateAndTime(),
                user.getEmail(), user.getRole(), user.getCourse(), user.getProfilePicture(),
                user.getFollowing().size(), user.getFollowers().size());
    }

    public User from(User user, UpdateUserProfileDTO updateUserProfileDTO) {
        user.setName(updateUserProfileDTO.name());
        user.setUsername(updateUserProfileDTO.username());
        user.setBio(updateUserProfileDTO.bio());

        if (updateUserProfileDTO.profilePicture() != null) {
            user.setProfilePicture(updateUserProfileDTO.profilePicture());
        }

        return user;
    }
}

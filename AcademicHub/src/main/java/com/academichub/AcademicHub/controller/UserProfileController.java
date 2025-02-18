package com.academichub.AcademicHub.controller;

import com.academichub.AcademicHub.dto.UpdateUserProfileDTO;
import com.academichub.AcademicHub.dto.UserProfileResponseDTO;
import com.academichub.AcademicHub.mapper.UserProfileMapper;
import com.academichub.AcademicHub.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/profile")
public class UserProfileController {

    private final UserProfileService userProfileService;

    private final UserProfileMapper userProfileMapper;

    @GetMapping("/{username}")
    public ResponseEntity<UserProfileResponseDTO> getUserProfile(@PathVariable String username) {
        var userProfile = userProfileService.getUserProfile(username);

        if (userProfile != null) {
            return ResponseEntity.ok(userProfileMapper.from(userProfile));
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{username}")
    public ResponseEntity<?> updateUserProfile(@PathVariable String username, @RequestBody UpdateUserProfileDTO updateUserProfileDTO) {
        boolean updated = userProfileService.updateUserProfile(username, updateUserProfileDTO.name(), updateUserProfileDTO.username(), updateUserProfileDTO.bio(), updateUserProfileDTO.profilePicture());

        if (updated) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}

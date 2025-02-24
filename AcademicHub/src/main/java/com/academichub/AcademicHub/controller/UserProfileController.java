package com.academichub.AcademicHub.controller;

import com.academichub.AcademicHub.dto.UpdateUserProfileDTO;
import com.academichub.AcademicHub.dto.UserProfileResponseDTO;
import com.academichub.AcademicHub.dto.UsernameDTO;
import com.academichub.AcademicHub.mapper.UserProfileMapper;
import com.academichub.AcademicHub.model.user.User;
import com.academichub.AcademicHub.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/profile")
public class UserProfileController {

    private final UserProfileService userProfileService;

    private final UserProfileMapper userProfileMapper;

    @GetMapping("/{username}")
    public ResponseEntity<UserProfileResponseDTO> getUserProfile(@AuthenticationPrincipal User user, @PathVariable String username) {
        var userProfile = user;
        var userProfileDTO = userProfileMapper.from(userProfile, false);

        if (!user.getUsername().equals(username)) {
            userProfile = userProfileService.getUserProfile(username);
            var isFollowing = userProfileService.isFollowing(user, userProfile);

            userProfileDTO = userProfileMapper.from(userProfile, isFollowing);
        }

        return ResponseEntity.ok().body(userProfileDTO);
    }

    @PutMapping("/{username}")
    public ResponseEntity<?> updateUserProfile(@PathVariable String username, @RequestBody UpdateUserProfileDTO updateUserProfileDTO) {

        try {
            var user = userProfileService.getUserProfile(username);

            user = userProfileMapper.from(user, updateUserProfileDTO);

            userProfileService.updateUserProfile(user);

            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PostMapping("/followUser")
    public ResponseEntity<HttpStatus> followUser(@AuthenticationPrincipal User user, @RequestBody UsernameDTO usernameDTO) {
        userProfileService.followUser(user, usernameDTO.username());

        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @PostMapping("/unfollowUser")
    public ResponseEntity<HttpStatus> unfollowUser(@AuthenticationPrincipal User user, @RequestBody UsernameDTO usernameDTO) {
        userProfileService.unfollowUser(user, usernameDTO.username());

        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}

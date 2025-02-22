package com.academichub.AcademicHub.controller;

import com.academichub.AcademicHub.dto.DashboardPostDTO;
import com.academichub.AcademicHub.dto.UpdateUserProfileDTO;
import com.academichub.AcademicHub.dto.UserProfileResponseDTO;
import com.academichub.AcademicHub.mapper.PostMapper;
import com.academichub.AcademicHub.mapper.UserProfileMapper;
import com.academichub.AcademicHub.model.user.User;
import com.academichub.AcademicHub.service.PostService;
import com.academichub.AcademicHub.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/profile")
public class UserProfileController {

    private final UserProfileService userProfileService;
    private final PostService postService;

    private final UserProfileMapper userProfileMapper;
    private final PostMapper postMapper;

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

        try {
            var user = userProfileService.getUserProfile(username);

            user = userProfileMapper.from(user, updateUserProfileDTO);

            userProfileService.updateUserProfile(user);

            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}

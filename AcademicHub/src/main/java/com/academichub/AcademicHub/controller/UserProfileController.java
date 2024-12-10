package com.academichub.AcademicHub.controller;

import com.academichub.AcademicHub.dto.UserProfileResponseDTO;
import com.academichub.AcademicHub.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/profile")
public class UserProfileController {

    private final UserProfileService userProfileService;

    @GetMapping("/{username}")
    public ResponseEntity<UserProfileResponseDTO> getUserProfile(@PathVariable String username) {
        UserProfileResponseDTO userProfile = userProfileService.getUserProfile(username);

        if (userProfile != null) {
            return ResponseEntity.ok(userProfile);
        }

        return ResponseEntity.notFound().build();
    }
}

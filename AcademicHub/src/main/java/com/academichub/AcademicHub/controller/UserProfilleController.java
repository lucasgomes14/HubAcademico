package com.academichub.AcademicHub.controller;

import com.academichub.AcademicHub.dto.UserProfilleResponseDTO;
import com.academichub.AcademicHub.service.UserProfilleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserProfilleController {

    private final UserProfilleService userProfilleService;

    @GetMapping("/{username}")
    public ResponseEntity<UserProfilleResponseDTO> getUserProfile(@PathVariable String username) {
        UserProfilleResponseDTO userProfille = userProfilleService.getUserProfille(username);

        if (userProfille != null) {
            return ResponseEntity.ok(userProfille);
        }

        return ResponseEntity.notFound().build();
    }
}

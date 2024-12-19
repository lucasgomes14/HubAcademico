package com.academichub.AcademicHub.controller;

import com.academichub.AcademicHub.dto.DashboardResponseDTO;
import com.academichub.AcademicHub.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("")
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping("/{username}")
    public ResponseEntity<DashboardResponseDTO> getDashboard(@PathVariable String username) {
        DashboardResponseDTO user = dashboardService.getUserProfile(username);

        return ResponseEntity.ok(user);
    }

    //TODO : FAZER A CRIAÇÂO DE POST, COMENTARIOS, LIKES
}

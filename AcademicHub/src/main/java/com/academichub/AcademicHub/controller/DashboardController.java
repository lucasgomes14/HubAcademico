package com.academichub.AcademicHub.controller;

import com.academichub.AcademicHub.dto.DashboardPostDTO;
import com.academichub.AcademicHub.mapper.DashboardMapper;
import com.academichub.AcademicHub.model.user.User;
import com.academichub.AcademicHub.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    private final DashboardMapper dashboardMapper;

    @GetMapping("/posts")
    public ResponseEntity<List<DashboardPostDTO>> getfriendPosts(@AuthenticationPrincipal User authenticatedUser, @RequestParam(defaultValue = "30") int limit) {


        System.out.println(authenticatedUser);
        System.out.println(limit);

        var friendPosts = dashboardService.getFriendPosts(authenticatedUser, limit);

        return ResponseEntity.ok().body(dashboardMapper.from(friendPosts));
    }
}

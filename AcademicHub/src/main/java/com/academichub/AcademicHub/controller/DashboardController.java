package com.academichub.AcademicHub.controller;

import com.academichub.AcademicHub.dto.DashboardPostDTO;
import com.academichub.AcademicHub.dto.PostDTO;
import com.academichub.AcademicHub.mapper.DashboardMapper;
import com.academichub.AcademicHub.mapper.PostMapper;
import com.academichub.AcademicHub.model.user.User;
import com.academichub.AcademicHub.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    private final DashboardMapper dashboardMapper;
    private final PostMapper postMapper;

    @GetMapping("/feed")
    public ResponseEntity<List<DashboardPostDTO>> getfriendPosts(@AuthenticationPrincipal User authenticatedUser) {
        var friendPosts = dashboardService.getFriendPosts(authenticatedUser);

        return ResponseEntity.ok().body(dashboardMapper.from(friendPosts, authenticatedUser));
    }

    @PostMapping("/post")
    public ResponseEntity<?> createPost(@AuthenticationPrincipal User authenticatedUser, @RequestBody PostDTO postDTO) {
        try {
            var post = postMapper.from(postDTO, authenticatedUser);
            var createdPost = dashboardService.saveNewPost(post);
            dashboardService.addPostFromUser(authenticatedUser, createdPost);

            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}

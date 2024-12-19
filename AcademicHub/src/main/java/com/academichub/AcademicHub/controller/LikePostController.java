package com.academichub.AcademicHub.controller;

import com.academichub.AcademicHub.service.LikePostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/likes")
public class LikePostController {

    private final LikePostService likePostService;

    @PostMapping
    public ResponseEntity<Boolean> likePost(@RequestParam Long postId, @RequestParam String username) {
        boolean isLiked = likePostService.likePost(postId, username);
        return ResponseEntity.ok(isLiked);
    }
}

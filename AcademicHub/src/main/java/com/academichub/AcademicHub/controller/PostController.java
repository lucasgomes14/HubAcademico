package com.academichub.AcademicHub.controller;

import com.academichub.AcademicHub.dto.DashboardPostDTO;
import com.academichub.AcademicHub.dto.LikeDTO;
import com.academichub.AcademicHub.dto.LikeResponseDTO;
import com.academichub.AcademicHub.mapper.LikeMapper;
import com.academichub.AcademicHub.mapper.PostMapper;
import com.academichub.AcademicHub.model.post.Post;
import com.academichub.AcademicHub.model.user.User;
import com.academichub.AcademicHub.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    private final LikeMapper likeMapper;
    private final PostMapper postMapper;

    @GetMapping("/feed")
    public ResponseEntity<List<DashboardPostDTO>> getPosts(@AuthenticationPrincipal User authenticatedUser, @RequestParam String page, @RequestParam String username) {
        
        List<Post> posts;
        
        if (page.equals("dashboard")) {
            posts = postService.getFriendPosts(authenticatedUser);
        } else {
            posts = postService.findAllPosts(username);
        }

        return ResponseEntity.ok().body(postMapper.from(posts, authenticatedUser));
    }

    @PostMapping("/like")
    public ResponseEntity<LikeResponseDTO> like(@AuthenticationPrincipal User user, @RequestBody LikeDTO likeDTO) {
        try {
            var post = postService.findPostById(likeDTO.idPost());

            var like = likeMapper.from(user, post);

            var likeResponse = postService.like(like);

            return ResponseEntity.status(HttpStatus.ACCEPTED).body(likeResponse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

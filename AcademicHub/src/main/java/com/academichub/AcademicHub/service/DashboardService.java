package com.academichub.AcademicHub.service;

import com.academichub.AcademicHub.model.post.Post;
import com.academichub.AcademicHub.model.user.User;
import com.academichub.AcademicHub.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DashboardService {

    private final PostRepository postRepository;

    public List<Post> getFriendPosts(User user, int limit) {

        PageRequest pageRequest = PageRequest.of(0, limit);

        var posts = postRepository.findPostsByFollowing(user.getFollowing(), pageRequest);

        return posts;
    }
}

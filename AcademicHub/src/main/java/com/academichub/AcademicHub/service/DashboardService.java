package com.academichub.AcademicHub.service;

import com.academichub.AcademicHub.model.post.Post;
import com.academichub.AcademicHub.model.user.User;
import com.academichub.AcademicHub.repository.PostRepository;
import com.academichub.AcademicHub.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DashboardService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public List<Post> getFriendPosts(User user) {

        var posts = postRepository.findPostsByFollowingAndUser(user.getFollowing(), user);

        return posts;
    }

    public Post saveNewPost(Post post) {
        return postRepository.save(post);
    }

    public void addPostFromUser(User user, Post post) {
        var userUpdate = userRepository.findByUsername(user.getUsername()).orElseThrow(() -> new RuntimeException("User not found"));
        var userPost = userUpdate.getPosts();

        userPost.add(post);
        userUpdate.setPosts(userPost);

        userRepository.save(userUpdate);
    }
}

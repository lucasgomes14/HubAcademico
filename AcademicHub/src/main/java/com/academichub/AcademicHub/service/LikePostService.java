package com.academichub.AcademicHub.service;

import com.academichub.AcademicHub.exceptions.PostNotFoundException;
import com.academichub.AcademicHub.exceptions.UserNotFoundException;
import com.academichub.AcademicHub.model.likePost.LikePost;
import com.academichub.AcademicHub.model.post.Post;
import com.academichub.AcademicHub.model.user.User;
import com.academichub.AcademicHub.repository.LikePostRepository;
import com.academichub.AcademicHub.repository.PostRepository;
import com.academichub.AcademicHub.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LikePostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final LikePostRepository likePostRepository;

    @Transactional
    public boolean likePost(Long postId, String username) {
        Post post = postRepository.findById(postId).orElseThrow(PostNotFoundException::new);
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException("User not found"));

        boolean alreadyLiked = likePostRepository.existsByPostAndUser(post, user);

        if (alreadyLiked) {
            likePostRepository.deleteByPostAndUser(post, user);
            post.setTotalLikes(post.getTotalLikes());
            return false;
        } else {
            LikePost like = new LikePost();
            like.setPost(post);
            like.setUser(user);
            likePostRepository.save(like);
            post.setTotalLikes(post.getTotalLikes());
            return true;
        }
    }
}

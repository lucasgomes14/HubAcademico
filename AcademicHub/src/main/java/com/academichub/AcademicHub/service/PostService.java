package com.academichub.AcademicHub.service;

import com.academichub.AcademicHub.dto.LikeResponseDTO;
import com.academichub.AcademicHub.exceptions.UserNotFoundException;
import com.academichub.AcademicHub.model.like.Like;
import com.academichub.AcademicHub.model.post.Post;
import com.academichub.AcademicHub.model.user.User;
import com.academichub.AcademicHub.repository.LikeRepository;
import com.academichub.AcademicHub.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;
    private final LikeRepository likeRepository;

    public Post findPostById(Long id) {
        return postRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User Not Found"));
    }

    public boolean findByUserAndPost(User user, Post post) {
        var like = likeRepository.findByUserAndPost(user, post).orElse(null);
        if (like == null) {
            return false;
        }
        return true;
    }

    public LikeResponseDTO like(Like newLike) {
        var like = likeRepository.findByUserAndPost(newLike.getUser(), newLike.getPost()).orElse(null);
        var post = findPostById(newLike.getPost().getId());

        if (like == null) {
            addLike(newLike);

            return new LikeResponseDTO(post.getLikes().size(), true);
        }

        deleteLike(like);
        return new LikeResponseDTO(post.getLikes().size(), false);
    }

    public void addLike(Like like) {
        likeRepository.save(like);
    }

    public void deleteLike(Like like) {
        likeRepository.delete(like);
    }
}

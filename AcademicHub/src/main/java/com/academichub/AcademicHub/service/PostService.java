package com.academichub.AcademicHub.service;

import com.academichub.AcademicHub.dto.CommentResponseDTO;
import com.academichub.AcademicHub.dto.LikeResponseDTO;
import com.academichub.AcademicHub.exceptions.CommentNullException;
import com.academichub.AcademicHub.exceptions.UserNotFoundException;
import com.academichub.AcademicHub.model.comment.Comment;
import com.academichub.AcademicHub.model.like.Like;
import com.academichub.AcademicHub.model.post.Post;
import com.academichub.AcademicHub.model.user.User;
import com.academichub.AcademicHub.repository.CommentRepository;
import com.academichub.AcademicHub.repository.LikeRepository;
import com.academichub.AcademicHub.repository.PostRepository;
import com.academichub.AcademicHub.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;
    private final LikeRepository likeRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    public Post findPostById(Long id) {
        return postRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User Not Found"));
    }

    public List<Post> findAllPosts(String username) {
        var user = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException("User Not Found"));
        return postRepository.findPostsByUserIdOrderByDateDesc(user.getId());
    }

    public List<Post> getFriendPosts(User user) {
        return postRepository.findPostsByFollowingAndUser(user.getFollowing(), user);
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

    public CommentResponseDTO addComment(Comment comment) {
        if (comment.getText().isEmpty()) {
            throw new CommentNullException();
        }

        commentRepository.save(comment);

        return new CommentResponseDTO(comment.getText(), comment.getUser().getName(), comment.getPublicationUpdateDateAndTime());
    }
}

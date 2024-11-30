package com.youtube.service;

import com.youtube.entity.CommentLikeEntity;
import com.youtube.enums.LikeType;
import com.youtube.repository.CommentLikeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentLikeService {
    private final CommentLikeRepository commentLikeRepository;

    public CommentLikeService(CommentLikeRepository commentLikeRepository) {
        this.commentLikeRepository = commentLikeRepository;
    }
    public CommentLikeEntity createCommentLike(String profileId, String commentId, LikeType type) {
        Optional<CommentLikeEntity> existingLike = commentLikeRepository.findByProfileIdAndCommentId(profileId, commentId);

        if (existingLike.isPresent()) {
            CommentLikeEntity commentLike = existingLike.get();
            commentLike.setType(type);
            return commentLikeRepository.save(commentLike);
        } else {
            CommentLikeEntity commentLike = new CommentLikeEntity();
            commentLike.setProfileId(profileId);
            commentLike.setCommentId(commentId);
            commentLike.setType(type);
            return commentLikeRepository.save(commentLike);
        }
    }

    public void removeCommentLike(String profileId, String commentId) {
        commentLikeRepository.deleteByProfileIdAndCommentId(profileId, commentId);
    }

    public List<CommentLikeEntity> getUserLikedComments(String profileId) {
        return commentLikeRepository.findByProfileIdOrderByCreatedDateDesc(profileId);
    }

    public List<CommentLikeEntity> getUserLikedCommentsByUserId(String profileId) {
        return commentLikeRepository.findByProfileId(profileId);
    }
}

package com.youtube.repository;

import com.youtube.entity.CommentLikeEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CommentLikeRepository extends CrudRepository<CommentLikeEntity,String> {

    void deleteByProfileIdAndCommentId(String profileId, String commentId);

    List<CommentLikeEntity> findByProfileIdOrderByCreatedDateDesc(String profileId);

    List<CommentLikeEntity> findByProfileId(String profileId);

    Optional<CommentLikeEntity> findByProfileIdAndCommentId(String profileId, String commentId);

}

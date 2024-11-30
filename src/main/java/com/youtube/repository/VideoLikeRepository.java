package com.youtube.repository;

import com.youtube.entity.VideoLikeEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VideoLikeRepository extends CrudRepository<VideoLikeEntity,String> {

    List<VideoLikeEntity> findByProfileIdOrderByCreatedDateDesc(String profileId);


}

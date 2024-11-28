package com.youtube.repository;

import com.youtube.entity.VideoTagEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VideoTagRepository extends CrudRepository<VideoTagEntity,String> {
    List<VideoTagEntity> findByVideoId(String videoId);

    boolean existsByVideoIdAndTagId(String videoId, String tagId);

    void deleteByVideoIdAndTagId(String videoId, String tagId);
}

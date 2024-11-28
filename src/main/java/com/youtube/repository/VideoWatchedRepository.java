package com.youtube.repository;

import com.youtube.entity.VideoWatchedEntity;
import org.springframework.data.repository.CrudRepository;

public interface VideoWatchedRepository extends CrudRepository<VideoWatchedEntity,String> {
}
